:: args
set command=%1
set asm_file=%2
set asm_basename=%3

:: constants
set build_dir=build

:: executable locations
set ML="ml.exe"
set LINK="C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.38.33130\bin\Hostx86\x86\link"
set DEVENV="C:/Program Files/Microsoft Visual Studio/2022/Community/Common7/IDE/devenv"


:: code to run before build/debug

:: make sure command is valid
if not %command%==build (
	if not %command%==debug (
		echo invalid command %command%
		exit /1
	)
)

:: make sure asm file is an asm file
set extension=%asm_file:~-3%
if not %extension%==asm (
	echo Given file is NOT an assembly file
	exit /1
)

:: if %build_dir% does not exist, create it, then cd into it
if not exist %build_dir% (
	mkdir %build_dir%
)
cd %build_dir%

call :%command%
exit /b

:build
	%ML% %asm_file% /c /Zd /Zi /coff /Fl=listing_file.lst
	if %errorlevel% neq 0 exit /b %errorlevel%

	%LINK% /SUBSYSTEM:CONSOLE %asm_basename%.obj /DEBUG /ASSEMBLYDEBUG /MANIFEST /NXCOMPAT /DYNAMICBASE /LTCG /TLBID:1
	if %errorlevel% neq 0 exit /b %errorlevel%

	exit /b

:debug
	call :build
	%DEVENV% /NoSplash /DoNotLoadProjects /Command Debug.StepInto /debugexe %asm_basename%.exe
	if %errorlevel% neq 0 exit /b %errorlevel%
	
	exit /b