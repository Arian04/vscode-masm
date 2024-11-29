const val EXTENSION_NAME: String = "masm-helper"
const val RESOURCES_DIR: String = "./kotlin"

var masmTaskProvider: vscode.Disposable? = null

// This is the main entry point of the application.
@JsExport
@JsName("activate")
fun activate(context: vscode.ExtensionContext) {
    console.log("activate()")
    vscode.window.showInformationMessage("Hello World from Kotlin/JS!", "")

    val resourcesPath = context.asAbsolutePath(RESOURCES_DIR)

    masmTaskProvider = vscode.tasks.registerTaskProvider(
        MasmTaskProvider.TASK_TYPE,
        MasmTaskProvider(EXTENSION_NAME, resourcesPath)
    )
}

@JsExport
@JsName("deactivate")
fun deactivate() {
    masmTaskProvider?.dispose()
}