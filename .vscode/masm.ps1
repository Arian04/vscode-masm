param (
    [Parameter(Mandatory=$true)][string]$command,
	[Parameter(Mandatory=$true)][string]$asm_file,
	[Parameter(Mandatory=$true)][string]$asm_basename
)
#Set-PSDebug -Trace 2 # For debugging
#Set-PSDebug -Trace 1 # For less verbose debugging

##### Constants #####
$build_dir = "build"

# Irvine library constants
$EXTRA_LIB_NAME = "Irvine32.lib"
$IRVINE_LIB_URL = "https://github.com/surferkip/asmbook/raw/main/Irvine.zip"
$TEMP_IRVINE_ZIP_PATH = "$env:TEMP/temp-irvine.zip"
$LIB_DEST_PATH = "$env:LOCALAPPDATA/irvine"
$IRVINE_UNZIPPED_SUBDIR = "Irvine" # If you ever change the URL or the zip file that the URL points to changes, make sure this is still correct

# Executable constants
$ML = "ml.exe"
$LINK = "link.exe"
$DEVENV = "devenv.exe"
$VCVARS_SUBPATH = "VC/Auxiliary/Build/vcvars32.bat" # 32bit build environment

# We expect some environment vars defined in tasks.json so make sure those are non-null
if ($null -eq $env:VS_INSTALL_DIR) {
	Write-Error "VS_INSTALL_DIR environment variable unset"
	exit 1
}
if ($null -eq $env:EXTRA_LIB_PATH) {
	Write-Error "EXTRA_LIB_PATH environment variable unset"
	exit 1
}

$global:EXTRA_LIB_PATH = "$env:EXTRA_LIB_PATH"
$global:VS_INSTALL_DIR = "$env:VS_INSTALL_DIR"

#####################

function Find-Visual-Studio-Install-Path {
	param (
		[Parameter(Mandatory=$true)][string]$program_files_path
	)

	$MS_VS_DIR = "$program_files_path/Microsoft Visual Studio"
	$VS_EDITIONS = @("Enterprise", "Professional", "Community")
	
	# if "Microsoft Visual Studio" directory exists
	if (Test-Path -PathType Container -LiteralPath $MS_VS_DIR) {
		# get list of subdirectories and iterate over it in "greatest integer first" order because I want to prefer newer Visual Studio installations
		$subdir_list = Get-ChildItem -Directory -Name -Filter "20??" -LiteralPath "$MS_VS_DIR" | Sort-Object -Descending
		foreach($vs_year in $subdir_list) {
			foreach ($edition in $VS_EDITIONS) {
				$possible_vs_install_dir = "$MS_VS_DIR/$vs_year/$edition"

				# if edition is installed (directory exists)
				if (Test-Path -PathType Container -LiteralPath $possible_vs_install_dir) {
					# return that path
					$possible_vs_install_dir
					return
				}
			}

			# This line only runs if the earlier loop didn't already cause this function to return
			Write-Output "Could not find any edition of Visual Studio in: $MS_VS_DIR/$vs_year"
		}
	} else {
		Write-Output "Could not find a Visual Studio installation in: $program_files_path"
	}

	# Return null if we don't find an installation directory (if we found it, we would've returned before ever hitting this line)
	$null
}

function Initialize-Required-Directories {
	# Autodetect if necessary
	if ($VS_INSTALL_DIR -eq "auto-detect") {
		$path_array = @("C:/Program Files", "C:/Program Files (x86)")

		$found_it = $false
		foreach ($path_string in $path_array) {
			$detected_install_dir = Find-Visual-Studio-Install-Path "$path_string"

			if ($null -ne $detected_install_dir) {
				Write-Output "Detected Visual Studio installation at $detected_install_dir"
				$global:VS_INSTALL_DIR = $detected_install_dir
				$found_it = $true
				break
			}
		}

		if (!$found_it) {
			Write-Error "Failed to auto-detect Visual Studio installation directory."
			exit 1
		}
	}
	if ($EXTRA_LIB_PATH -eq "auto-download") {
		# If it doesn't exist at the expected path, download it
		if (!(Test-Path -PathType Container -Path $LIB_DEST_PATH)) {
			Write-Output "Extra library path doesn't exist and EXTRA_LIB_PATH defined in tasks.json is set to "$EXTRA_LIB_PATH", so attempting to download the library."

			# Silence progress bar (not sure if I need to fix this afterwards or if it'll be fine)
			$ProgressPreference = 'SilentlyContinue'

			Write-Output "Downloading Irvine library..."
			Invoke-WebRequest -uri "$IRVINE_LIB_URL" -Method "GET" -Outfile "$TEMP_IRVINE_ZIP_PATH"
			# TODO: get and verify checksum for safety? since we're downloading data from an arbitrary URL programatically.

			Write-Output "Extracting Irvine library zip file..."
			Add-Type -Assembly "System.IO.Compression.Filesystem"
			[System.IO.Compression.ZipFile]::ExtractToDirectory("$TEMP_IRVINE_ZIP_PATH", "$LIB_DEST_PATH")
			if (!($?)) { exit 1 }
		}

		# Set the now-correct library path
		$global:EXTRA_LIB_PATH = "$LIB_DEST_PATH/$IRVINE_UNZIPPED_SUBDIR"
		Write-Output "Using Irvine library at path: $EXTRA_LIB_PATH"
	}

	# Verify that directories exist
	$either_directory_missing = $false
	if (!(Test-Path -PathType Container -Path $VS_INSTALL_DIR)) {
		Write-Error "Visual Studio installation directory does not exist: $VS_INSTALL_DIR"
		$either_directory_missing = $true
	}
	if (!(Test-Path -PathType Container -Path $EXTRA_LIB_PATH)) {
		Write-Error "Irvine library directory does not exist: $EXTRA_LIB_PATH"
		$either_directory_missing = $true
	}

	# Exit after checking existence of both directories if one (or both) are missing
	if ($either_directory_missing) {
		exit 1
	}
}

# Source for this amazing function: https://github.com/majkinetor/posh/blob/ee5ef42f8e2337ea6bcfb6ead8b1f7f6427f2a03/MM_Admin/Invoke-Environment.ps1
# NOTE: It's pretty slow (relatively). I used the "Profiler" module to find out that it takes up > 95% of the script's
# 		execution time, but it still takes under a second on my underpowered Windows VM, so it's probably fine.
function Invoke-Environment {
    param (
        # Any cmd shell command, normally a configuration batch file.
        [Parameter(Mandatory=$true)]
        [string] $Command
    )

    $Command = "`"" + $Command + "`""
    cmd /c "$Command > nul 2>&1 && set" | . { process {
        if ($_ -match '^([^=]+)=(.*)') {
            [System.Environment]::SetEnvironmentVariable($matches[1], $matches[2])
        }
    }}

}

function debug {
	& build
	& $DEVENV /NoSplash /Command Debug.StepInto /debugexe $(Resolve-Path "${asm_basename}.exe").Path
	if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

function buildrun {
	&build

	Write-Output "Running the produced executable..."
	& $(Resolve-Path "${asm_basename}.exe").Path
}

function build {
    & $ML @ML_ARGS
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

    & $LINK $LINK_ARGS
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

	Write-Output "Successfully built the program!"
}

function main {
	Initialize-Required-Directories

	# args
	$ML_OPTIONS = @(
		"/nologo"
		"/c"
		"/Fllisting_file.lst"
		"/Zd"
		"/Zi"
		"/coff"
		"/I$EXTRA_LIB_PATH" # Irvine
	)
	$ML_ARGS = @(
		$ML_OPTIONS
		$asm_file
	)

	$LINK_OPTIONS = @(
		"/NOLOGO"
		"/DEBUG"
		"/ASSEMBLYDEBUG"
		"/MANIFEST"
		"/NXCOMPAT"
		"/SUBSYSTEM:CONSOLE"
		"/INCREMENTAL:NO"
		"/TLBID:1"
		"/DYNAMICBASE"
		"/LIBPATH:$EXTRA_LIB_PATH" # Irvine
	)
	$LINK_LIBS = @(
		"kernel32.lib"
		"user32.lib"
		"gdi32.lib"
		"winspool.lib"
		"comdlg32.lib"
		"advapi32.lib"
		"shell32.lib"
		"ole32.lib"
		"oleaut32.lib"
		"uuid.lib"
		"odbc32.lib"
		"odbccp32.lib"
		"$EXTRA_LIB_NAME" # Irvine
	)
	$LINK_ARGS = @(
		$LINK_OPTIONS
		$LINK_LIBS
		"$asm_basename.obj"
	)

	# calls batch script that sets important environment vars
	$VCVARS = "$VS_INSTALL_DIR/$VCVARS_SUBPATH"
	if (!(Test-Path -PathType Leaf -LiteralPath $VCVARS)) {
		Write-Error "Provided vcvars script path does not exist: $VCVARS"
		exit 1
	}
	Invoke-Environment "$VCVARS"

	# make sure asm file is an asm file
	$extension = $asm_file.Substring($asm_file.Length - 3)
	if ($extension -ne "asm") {
		Write-Output "Given file is NOT an assembly file"
		exit 1
	}

	# if $build_dir does not exist, create it, then cd into it
	if (! (Test-Path $build_dir)) {
		New-Item -ItemType Directory -Path $build_dir | Out-Null
	}
	Set-Location $build_dir

	# make sure command is valid
	switch ($command.ToLower()) {
		"build" {
			& build
			break
		}
		"buildrun" {
			& buildrun
			break
		}
		"debug" {
			& debug
			break
		}
		default {
			Write-Output "Invalid command: $command"
			exit 1
		}
	}
}

& main

