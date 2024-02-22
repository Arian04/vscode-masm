param (
    [Parameter(Mandatory=$true)][string]$command,
	[Parameter(Mandatory=$true)][string]$asm_file,
	[Parameter(Mandatory=$true)][string]$asm_basename
)
# Set-PSDebug -Trace 1

function debug {
	& build
	& $DEVENV /NoSplash /DoNotLoadProjects /Command Debug.StepInto /debugexe $(Resolve-Path "${asm_basename}.exe").Path
	if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

function build {
    & $ML /c /Fl"listing_file.lst" /Zd /Zi /coff $asm_file
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

	# the linker is a LIAR!! do not listen to it about /LTCG being unused, VS will no longer open source code while debugging without it
    & $LINK "/SUBSYSTEM:CONSOLE" "$asm_basename.obj" /DEBUG /ASSEMBLYDEBUG /MANIFEST /NXCOMPAT /DYNAMICBASE /LTCG /TLBID:1
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

function main() {
	# constants
	$build_dir = "build"

	# executable locations
	$ML = "ml.exe"
	$LINK = "C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.38.33130\bin\Hostx86\x86\link"
	$DEVENV = "C:/Program Files/Microsoft Visual Studio/2022/Community/Common7/IDE/devenv"

	# code to run before build/debug

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