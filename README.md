# vscode-masm

This repo includes some files for running MASM through VS Code and using Visual Studio as a debugger (but not an IDE).

### Requirements

- MASM (ml.exe and link.exe)
- Visual Studio (devenv.exe)

### Installation Instructions

1. Download the files in the repository's `.vscode` directory
2. Drop them into the `.vscode` folder of a fresh project folder that you're using for your ASM files
3. Open the .PS1 script that you downloaded and make sure that the vscode installation directory path is correct. I plan to automate this eventually.
4. *(Optional)* I recommend downloading the [MASM](https://vscode.dev/github/Arian04/vscode-masm/blob/main) extension for syntax highlighting.

### Usage

After "installing" it to your vscode project, just open your ASM file and run the `build` or `debug` build tasks using the command palette or whatever keyboard shortcut(s) you've set up. Close Visual Studio after every debugging operation. I'm trying to figure out how to re-use the same instance every time so that this won't be necessary.