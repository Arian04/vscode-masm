import vscode.CancellationToken
import vscode.ShellExecution
import vscode.Task
import vscode.TaskGroup
import vscode.TaskScope
import kotlin.js.collections.JsReadonlyArray

@JsExport
@JsName("MasmTaskProvider")
class MasmTaskProvider(source: String, resourcesPath: String) : vscode.TaskProvider__0 {
    private val extensionName: String = source

    // TODO: check if this works on Windows
    private val scriptPath: String = "${resourcesPath}/${SCRIPT_NAME}"

    private val defaultShellOptions: vscode.ShellExecutionOptions = makeJsObject {
        executable = "powershell"
        shellArgs = arrayOf(
            "-ExecutionPolicy",
            "Bypass",
            "-NoProfile",
            "-File",
            scriptPath
        )
        env = kotlin.js.json(
            "VS_INSTALL_DIR" to "auto-detect",
            "EXTRA_LIB_PATH" to "auto-download"
        ).toObject()
    }

    companion object {
        const val TASK_TYPE = "masm"
        private const val SCRIPT_NAME = "masm.ps1"
        private val definition: vscode.TaskDefinition = makeJsObject {
            type = TASK_TYPE
        }
        private val presentationOptions: vscode.TaskPresentationOptions = makeJsObject {
            echo = false
            reveal = vscode.TaskRevealKind.Always
            focus = false
            panel = vscode.TaskPanelKind.Shared
            showReuseMessage = false
            clear = true
        }
    }

    @OptIn(ExperimentalJsCollectionsApi::class)
    override fun provideTasks(token: CancellationToken): JsReadonlyArray<Task> {
        console.log("provideTasks()")

        val currentActiveTextEditor = vscode.window.activeTextEditor
        if (currentActiveTextEditor == null) {
            console.warn("currentActiveTextEditor is undefined")
            return emptyList<Task>().asJsReadonlyArrayView()
        }

        val config = vscode.workspace.getConfiguration(extensionName)
        val assemblerIncludePaths = config.get<List<String>>("assembler.includePaths") ?: emptyList()
        val linkerLibraryPaths = config.get<List<String>>("linker.libraryPaths") ?: emptyList()
        val linkerLibraries = config.get<List<String>>("linker.libraries") ?: emptyList()

        val str = buildString {
            append("assemblerIncludePaths: ")
            appendLine(JSON.stringify(assemblerIncludePaths, space = "\t"))

            append("linkerLibraryPaths: ")
            appendLine(JSON.stringify(linkerLibraryPaths, space = "\t"))

            append("linkerLibraries: ")
            appendLine(JSON.stringify(linkerLibraries, space = "\t"))
        }
        console.info(str)

        val filePath = currentActiveTextEditor.document.fileName
        val fileBasenameNoExtension = basenameNoExtension(filePath)
        return listOf(
            makeTask("Build", listOf("build", "\"$filePath\"", "\"$fileBasenameNoExtension\"")),
            makeTask("Build + Run", listOf("buildrun", "\"$filePath\"", "\"$fileBasenameNoExtension\"")),
            makeTask("Debug", listOf("debug", "\"$filePath\"", "\"$fileBasenameNoExtension\""))
        ).asJsReadonlyArrayView()
    }

    override fun resolveTask(task: Task, token: CancellationToken): Task {
        return task
    }

    private fun makeTask(name: String, args: List<Any>): Task {
        val task = Task(
            definition,
            TaskScope.Workspace,
            name,
            extensionName,
            makeScriptExecution(args)
        )
        task.presentationOptions = presentationOptions
        task.group = TaskGroup.Build
        return task
    }

    private fun makeScriptExecution(args: List<Any>): ShellExecution {
        return ShellExecution(args.joinToString(" "), defaultShellOptions)
    }
}

private fun basenameNoExtension(path: String): String {
    val pathBasename = path.substringAfterLast('/')
    return pathBasename.substringBeforeLast('.')
}