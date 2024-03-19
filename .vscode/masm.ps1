param (
    [Parameter(Mandatory=$true)][string]$command,
	[Parameter(Mandatory=$true)][string]$asm_file,
	[Parameter(Mandatory=$true)][string]$asm_basename
)
#Set-PSDebug -Trace 2 # For debugging

# TODO: find this programatically
# Visual Studio install directory. Necessary for finding where `vcvarsall` is stored in order to call it
if ($null -eq $env:VS_INSTALL_DIR) {
	# TODO: descriptive error
	exit 1
}
if ($null -eq $env:EXTRA_LIB_PATH) {
	# TODO: descriptive error
	exit 1
}

$EXTRA_LIB_PATH = "$env:EXTRA_LIB_PATH"
$VS_INSTALL_DIR = "$env:VS_INSTALL_DIR"
$EXTRA_LIB_NAME = "Irvine32.lib"

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

# NOTE: the linker is a LIAR!! do NOT listen to it about /LTCG being unused, VS will no longer open source code while debugging without it
$LINK_OPTIONS = @(
	"/NOLOGO"
	"/DEBUG"
	"/ASSEMBLYDEBUG"
	"/MANIFEST"
	"/NXCOMPAT"
	"/SUBSYSTEM:CONSOLE"
	"/LTCG"
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

# Source for this amazing function: https://github.com/majkinetor/posh/blob/ee5ef42f8e2337ea6bcfb6ead8b1f7f6427f2a03/MM_Admin/Invoke-Environment.ps1
function Invoke-Environment {
    param
    (
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
	& $DEVENV /NoSplash /DoNotLoadProjects /Command Debug.StepInto /debugexe $(Resolve-Path "${asm_basename}.exe").Path
	if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

function build {
    & $ML @ML_ARGS
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

    & $LINK $LINK_ARGS
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

function main {
	# constants
	$build_dir = "build"

	# executable locations
	$VCVARS = "$VS_INSTALL_DIR/VC/Auxiliary/Build/vcvars32.bat" # 32bit build environment
	$ML = "ml.exe"
	$LINK = "link.exe"
	$DEVENV = "devenv.exe"

	# calls batch script that sets important environment vars
	Invoke-Environment "$VCVARS"

	# make sure asm file is an asm file
	$extension = $asm_file.Substring($asm_file.Length - 3)
	if ($extension -ne "asm") {
		Write-Host "Given file is NOT an assembly file"
		exit 1
	}

	# if $build_dir does not exist, create it, then cd into it
	if (-not (Test-Path $build_dir)) {
		New-Item -ItemType Directory -Path $build_dir | Out-Null
	}
	Set-Location $build_dir

	# make sure command is valid
	switch ($command.ToLower()) {
		"build" {
			& build
		}
		"debug" {
			& debug
		}
		default {
			Write-Host "Invalid command: $command"
			exit 1
		}
	}
}

& main
