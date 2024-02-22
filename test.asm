; Just a simple ASM source file that I test on

.386
.model flat,stdcall
.stack 4096
;ExitProcess proto, dwExitCode:dword

.code
main PROC
    add eax, 0199h

    xor eax, eax
    ret
main ENDP

END main