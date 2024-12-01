import * as vscode from "vscode";
import * as path from "path";

export class MasmTaskProvider implements vscode.TaskProvider {
	static readonly TaskType = "masm" as const;
	private readonly scriptName = "masm.ps1" as const;
	private readonly scriptPath;
	private readonly extensionName: string;
	private readonly definition: vscode.TaskDefinition = {
		type: MasmTaskProvider.TaskType,
	} as const;
	private readonly defaultShellOptions: vscode.ShellExecutionOptions;
	private readonly presentationOptions: vscode.TaskPresentationOptions = {
		echo: false,
		reveal: vscode.TaskRevealKind.Always,
		focus: false,
		panel: vscode.TaskPanelKind.Shared,
		showReuseMessage: false,
		clear: true,
	} as const;
	private readonly resourcesPath: string;

	constructor(source: string, resourcesPath: string) {
		this.extensionName = source;
		this.resourcesPath = resourcesPath;
		this.scriptPath = path.join(this.resourcesPath, this.scriptName);
		this.defaultShellOptions = {
			// Default CWD is workspace root
			executable: "powershell",
			shellArgs: [
				"-ExecutionPolicy",
				"Bypass",
				"-NoProfile",
				"-File",
				`${this.scriptPath}`,
			],
			env: {
				VS_INSTALL_DIR: "auto-detect",
				EXTRA_LIB_PATH: "auto-download",

				// If the script fails to automatically handle these (or you want it to run faster or something), you can hardcode the paths
				// "VS_INSTALL_DIR": "C:/Program Files/Microsoft Visual Studio/2022/Community",
				// "EXTRA_LIB_PATH": "C:/Users/EXAMPLE_USER/Documents/Irvine"
			},
		};
	}

	public provideTasks(token?: vscode.CancellationToken) {
		const currentActiveTextEditor = vscode.window.activeTextEditor;

		if (!currentActiveTextEditor) {
			console.log("currentActiveTextEditor is undefined");
			return;
		}

		const config = vscode.workspace.getConfiguration(this.extensionName);
		const assemblerIncludePaths = config.get<String[]>('assembler.includePaths');
		const linkerLibraryPaths = config.get<String[]>('linker.libraryPaths');
		const linkerLibraries = config.get<String[]>('linker.libraries');

		const filePath = currentActiveTextEditor.document.fileName;
		const fileBasenameNoExtension = this.basenameNoExtension(filePath);
		const taskList: vscode.Task[] = [
			this.makeTask("Build", [
				"build",
				`"${filePath}"`,
				`"${fileBasenameNoExtension}"`,
			]),
			this.makeTask("Build + Run", [
				"buildrun",
				`"${filePath}"`,
				`"${fileBasenameNoExtension}"`,
			]),
			this.makeTask("Debug", [
				"debug",
				`"${filePath}"`,
				`"${fileBasenameNoExtension}"`,
			]),
		];

		return taskList;
	}

	public resolveTask(task: vscode.Task, token?: vscode.CancellationToken) {
		return task;
	}

	private makeTask(
		name: string,
		args: (string | vscode.ShellQuotedString)[]
	): vscode.Task {
		const task = new vscode.Task(
			this.definition,
			vscode.TaskScope.Workspace,
			name,
			this.extensionName,
			this.makeScriptExecution(args)
		);

		task.presentationOptions = this.presentationOptions;
		task.group = vscode.TaskGroup.Build;
		return task;
	}

	private makeScriptExecution(
		args: (string | vscode.ShellQuotedString)[]
	): vscode.ShellExecution {
		return new vscode.ShellExecution(
			args.join(" "),
			this.defaultShellOptions
		);
	}

	public basenameNoExtension(path: string) {
		const pathBasename = path.split(/[\\/]/).pop();
		const pathBasenameNoExtension =
			pathBasename?.replace(/\.[^/.]+$/, "") ?? "";

		return pathBasenameNoExtension;
	}
}