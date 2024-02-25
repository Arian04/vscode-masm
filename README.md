# vscode-masm

This repo includes some files for running MASM through VS Code and using Visual Studio as a debugger (but not an IDE).

## Why Use This Over Visual Studio?

Visual Studio makes you jump through several hoops to run even a simple MASM program. It's definitely worth using if you were making a massive project with tens or hundreds of MASM source files and/or some complicated build procedure, but if you're just wanting to run a single simple MASM source file and debug it without setting up a whole project and dealing with Visual Studio's quirks, then this could be much easier to use. My use-case is for running simple MASM programs for a class that uses the 8th edition of "Assembly Language for x86 Processors" textbook by Kip Irvine.

The reason I still handle debugging by launching Visual Studio is because their debugger is the best one I could get working, it's user-friendly, and simply launching the debugger doesn't make the build process any harder or clutter your project with files.

## Requirements

1. Go [here](https://learn.microsoft.com/en-us/visualstudio/install/install-visual-studio?view=vs-2022) and install Visual Studio
2. Make sure to include a C++ workload when it asks you about which workloads you want to install.

If you already have those, you're done with this section.

## Installation Instructions

1. Download the files in the repository's `.vscode` directory
2. Drop them into the `.vscode` folder of a fresh project folder that you're using for your ASM files
3. Open the .PS1 script that you downloaded and make sure that the VS Code installation directory path is correct. I plan to automate this eventually.
4. *(Optional)* I recommend downloading the [MASM](https://marketplace.visualstudio.com/items?itemName=blindtiger.masm) VS Code extension for syntax highlighting.
5. *(Optional)* I recommend downloading the [Tasks](https://marketplace.visualstudio.com/items?itemName=actboy168.tasks) VS Code extension so the Build and Debug tasks are shown in your statusbar (bottom left by default I believe) as clickable buttons.

Since the files will only be stored in the project-specific `.vscode` directory, it makes no changes to your global configuration. This means that it's easy to get rid of the files if you want to, but also means if you want to work on MASM programs in another directory (and thus has a different `.vscode` directory), you'll need to copy the files into that new `.vscode` directory.

## Usage

1. Make sure you "installed" it to your VS Code project.
2. Just open your ASM file and run the `build` or `debug` build tasks using the command palette or whatever keyboard shortcut(s) you've set up. The ASM file must be open when you run the task, since it targets the file you currently have open.
3. Close Visual Studio after every debugging operation (I'm still trying to figure out how to re-use the same instance every time so that this won't be necessary)
