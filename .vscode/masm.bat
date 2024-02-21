:: args
set asm_filename="%1"

:: executable locations
set ML="ml.exe"
set LINK="C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Tools\MSVC\14.38.33130\bin\Hostx86\x86\link.exe"
set DEVENV="C:/Program Files/Microsoft Visual Studio/2022/Community/Common7/IDE/devenv.exe"

:build
	%ML% /c /Zd /Zi /coff /Fl listing_file.lst ../%asm_filename%.asm
	%LINK% /SUBSYSTEM:CONSOLE %asm_filename%.obj /DEBUG /ASSEMBLYDEBUG /MANIFEST /NXCOMPAT /DYNAMICBASE /LTCG /TLBID:1

:debug
	CALL :build
	%DEVENV% /NoSplash /DoNotLoadProjects /Command Debug.StepInto /debugexe %asm_filename%.exe
