# vscode-masm

This repo includes some files for running MASM through VS Code and using Visual Studio as a debugger (but not an IDE).

### TODO

Right now the paths of the ml, link, and devenv programs are hardcoded, you're gonna wanna either add those to your system's PATH or change the PS1 script to use the right paths to each program for your system.

### Requirements

- MASM (ml.exe and link.exe)
- Visual Studio (devenv.exe)

### Installation Instructions

right now it's just 2 files in the `.vscode` directory, so you should just be able to download `tasks.json` and `masm.ps1` and drop them into the `.vscode` folder of a fresh project folder that you're using for your ASM files.

### Usage

After "installing" it to your vscode project, just open your ASM file, and run the `build` or `debug` build tasks using the command palette