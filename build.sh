# This file is just here as my unorganized notes

#ml.exe /c /nologo /Zi /Fo"Debug\%(FileName).obj" /Fl"" /W3 /errorReport:prompt  /Ta

ml.exe /c /nologo /Zi /Fo"main.obj" /Fl"" /W3 /errorReport:prompt {file}
#link.exe /ERRORREPORT:PROMPT /OUT:"Assignment3.exe" /INCREMENTAL /ILK:"Debug\Assignment3.ilk" /NOLOGO kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /MANIFEST /MANIFESTUAC:"level='asInvoker' uiAccess='false'" /manifest:embed /DEBUG /PDB:"C:\Users\Arian\Documents\projects\Assignment3\Debug\Assignment3.pdb" /ASSEMBLYDEBUG /SUBSYSTEM:CONSOLE /TLBID:1 /DYNAMICBASE /NXCOMPAT /IMPLIB:"C:\Users\Arian\Documents\projects\Assignment3\Debug\Assignment3.lib" /MACHINE:X86 test.obj
link.exe /subsystem:windows /NOLOGO kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib .\test.obj