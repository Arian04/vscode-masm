// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from "vscode";
import { MasmTaskProvider } from "./MasmTaskProvider";

// Constants
const RESOURCES_DIR: string = "resources" as const;

// Variables
let masmTaskProvider: vscode.Disposable | undefined;

// This method is called when your extension is activated
export function activate(context: vscode.ExtensionContext) {
	const resourcesPath = context.asAbsolutePath(RESOURCES_DIR);

	masmTaskProvider = vscode.tasks.registerTaskProvider(
		MasmTaskProvider.TaskType,
		new MasmTaskProvider(
			context.extension.packageJSON["name"],
			resourcesPath
		)
	);
}

// This method is called when your extension is deactivated
export function deactivate() {
	if (masmTaskProvider) {
		masmTaskProvider.dispose();
	}
}
