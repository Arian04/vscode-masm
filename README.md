# vscode-masm

This repo includes some files for running MASM through VS Code and using Visual Studio as a debugger (but not an IDE).

### Requirements

TL;DR: go [here](https://learn.microsoft.com/en-us/visualstudio/install/install-visual-studio?view=vs-2022) and install Visual Studio. Make sure to include a C++ workload when it asks you about which workloads you want.

- MASM (ml.exe and link.exe)
- Visual Studio (devenv.exe)

You can either install MASM alone, or Visual Studio and MASM together. Visual Studio is only required if you want to debug, but I can't imagine anyone NOT wanting to debug their Assembly. 

### Installation Instructions

1. Download the files in the repository's `.vscode` directory
2. Drop them into the `.vscode` folder of a fresh project folder that you're using for your ASM files
3. Open the .PS1 script that you downloaded and make sure that the vscode installation directory path is correct. I plan to automate this eventually.
4. *(Optional)* I recommend downloading the [MASM](https://vscode.dev/github/Arian04/vscode-masm/blob/main) extension for syntax highlighting.

### Usage

After "installing" it to your vscode project, just open your ASM file and run the `build` or `debug` build tasks using the command palette or whatever keyboard shortcut(s) you've set up. Close Visual Studio after every debugging operation. I'm trying to figure out how to re-use the same instance every time so that this won't be necessary.

### Why Use This Over Visual Studio?

Visual Studio makes you jump through several hoops to run even a simple MASM program. It's definitely worth using if you were making a massive project with tens or hundreds of MASM source files and/or some complicated build procedure, but if you're just wanting to run a single simple MASM source file and debug it without setting up a whole project and dealing with Visual Studio's quirks, then this could be much easier to use. My use-case is for running simple MASM programs for a class that uses the 8th edition of "Assembly Language for x86 Processors" textbook by Kip Irvine.

The reason I still handle debugging by launching Visual Studio is because their debugger is the best one I could get working, it's user-friendly, and simply launching the debugger doesn't make the build process and harder or clutter your project with files.
