@file:JsModule("vscode")
@file:JsQualifier("window")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.window

import vscode.*

external var tabGroups: TabGroups

external var activeTextEditor: TextEditor?

external var visibleTextEditors: Array<TextEditor>

external var onDidChangeActiveTextEditor: Event<TextEditor?>

external var onDidChangeVisibleTextEditors: Event<Array<TextEditor>>

external var onDidChangeTextEditorSelection: Event<TextEditorSelectionChangeEvent>

external var onDidChangeTextEditorVisibleRanges: Event<TextEditorVisibleRangesChangeEvent>

external var onDidChangeTextEditorOptions: Event<TextEditorOptionsChangeEvent>

external var onDidChangeTextEditorViewColumn: Event<TextEditorViewColumnChangeEvent>

external var visibleNotebookEditors: Array<NotebookEditor>

external var onDidChangeVisibleNotebookEditors: Event<Array<NotebookEditor>>

external var activeNotebookEditor: NotebookEditor?

external var onDidChangeActiveNotebookEditor: Event<NotebookEditor?>

external var onDidChangeNotebookEditorSelection: Event<NotebookEditorSelectionChangeEvent>

external var onDidChangeNotebookEditorVisibleRanges: Event<NotebookEditorVisibleRangesChangeEvent>

external var terminals: Array<Terminal>

external var activeTerminal: Terminal?

external var onDidChangeActiveTerminal: Event<Terminal?>

external var onDidOpenTerminal: Event<Terminal>

external var onDidCloseTerminal: Event<Terminal>

external var onDidChangeTerminalState: Event<Terminal>

external var onDidChangeTerminalShellIntegration: Event<TerminalShellIntegrationChangeEvent>

external var onDidStartTerminalShellExecution: Event<TerminalShellExecutionStartEvent>

external var onDidEndTerminalShellExecution: Event<TerminalShellExecutionEndEvent>

external var state: WindowState

external var onDidChangeWindowState: Event<WindowState>

external fun showTextDocument(
    document: TextDocument,
    column: ViewColumn = definedExternally,
    preserveFocus: Boolean = definedExternally
): Thenable<TextEditor>

external fun showTextDocument(document: TextDocument): Thenable<TextEditor>

external fun showTextDocument(document: TextDocument, column: ViewColumn = definedExternally): Thenable<TextEditor>

external fun showTextDocument(
    document: TextDocument,
    options: TextDocumentShowOptions = definedExternally
): Thenable<TextEditor>

external fun showTextDocument(uri: Uri, options: TextDocumentShowOptions = definedExternally): Thenable<TextEditor>

external fun showTextDocument(uri: Uri): Thenable<TextEditor>

external fun showNotebookDocument(
    document: NotebookDocument,
    options: NotebookDocumentShowOptions = definedExternally
): Thenable<NotebookEditor>

external fun createTextEditorDecorationType(options: DecorationRenderOptions): TextEditorDecorationType

external fun <T : String> showInformationMessage(message: String, vararg items: T): Thenable<T?>

external fun <T : String> showInformationMessage(
    message: String,
    options: MessageOptions,
    vararg items: T
): Thenable<T?>

external fun <T : String> showWarningMessage(message: String, vararg items: T): Thenable<T?>

external fun <T : String> showWarningMessage(message: String, options: MessageOptions, vararg items: T): Thenable<T?>

external fun <T : String> showErrorMessage(message: String, vararg items: T): Thenable<T?>

external fun <T : String> showErrorMessage(message: String, options: MessageOptions, vararg items: T): Thenable<T?>

external interface `T$37` {
    var canPickMany: Boolean
}

external fun showQuickPick(
    items: Array<String>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */,
    token: CancellationToken = definedExternally
): dynamic /* Thenable | Thenable */

external fun showQuickPick(
    items: Array<String>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */
): dynamic /* Thenable | Thenable */

external fun showQuickPick(
    items: Thenable<Array<String>>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */,
    token: CancellationToken = definedExternally
): dynamic /* Thenable | Thenable */

external fun showQuickPick(
    items: Thenable<Array<String>>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */
): dynamic /* Thenable | Thenable */

external fun showQuickPick(items: Array<String>): Thenable<String?>

external fun showQuickPick(items: Thenable<Array<String>>): Thenable<String?>

external fun <T : QuickPickItem> showQuickPick(
    items: Array<T>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */,
    token: CancellationToken = definedExternally
): dynamic /* Thenable | Thenable */

external fun <T : QuickPickItem> showQuickPick(
    items: Array<T>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */
): dynamic /* Thenable | Thenable */

external fun <T : QuickPickItem> showQuickPick(
    items: Thenable<Array<T>>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */,
    token: CancellationToken = definedExternally
): dynamic /* Thenable | Thenable */

external fun <T : QuickPickItem> showQuickPick(
    items: Thenable<Array<T>>,
    options: QuickPickOptions /* QuickPickOptions & `T$37` */
): dynamic /* Thenable | Thenable */

external fun <T : QuickPickItem> showQuickPick(items: Array<T>): Thenable<T?>

external fun <T : QuickPickItem> showQuickPick(items: Thenable<Array<T>>): Thenable<T?>

external fun showWorkspaceFolderPick(options: WorkspaceFolderPickOptions = definedExternally): Thenable<WorkspaceFolder?>

external fun showOpenDialog(options: OpenDialogOptions = definedExternally): Thenable<Array<Uri>?>

external fun showSaveDialog(options: SaveDialogOptions = definedExternally): Thenable<Uri?>

external fun showInputBox(
    options: InputBoxOptions = definedExternally,
    token: CancellationToken = definedExternally
): Thenable<String?>

external fun <T : QuickPickItem> createQuickPick(): QuickPick<T>

external fun createInputBox(): InputBox

external fun createOutputChannel(name: String, languageId: String = definedExternally): OutputChannel

external fun createOutputChannel(name: String): OutputChannel

external interface `T$38` {
    var log: Boolean
}

external fun createOutputChannel(name: String, options: `T$38`): LogOutputChannel

external interface `T$39` {
    val viewColumn: ViewColumn
    val preserveFocus: Boolean?
        get() = definedExternally
}

external fun createWebviewPanel(
    viewType: String,
    title: String,
    showOptions: ViewColumn,
    options: WebviewPanelOptions /* WebviewPanelOptions & WebviewOptions */ = definedExternally
): WebviewPanel

external fun createWebviewPanel(viewType: String, title: String, showOptions: ViewColumn): WebviewPanel

external fun createWebviewPanel(
    viewType: String,
    title: String,
    showOptions: `T$39`,
    options: WebviewPanelOptions /* WebviewPanelOptions & WebviewOptions */ = definedExternally
): WebviewPanel

external fun createWebviewPanel(viewType: String, title: String, showOptions: `T$39`): WebviewPanel

external fun setStatusBarMessage(text: String, hideAfterTimeout: Number): Disposable

external fun setStatusBarMessage(text: String, hideWhenDone: Thenable<Any>): Disposable

external fun setStatusBarMessage(text: String): Disposable

external fun <R> withScmProgress(task: (progress: Progress<Number>) -> Thenable<R>): Thenable<R>

external interface `T$40` {
    var message: String?
        get() = definedExternally
        set(value) = definedExternally
    var increment: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <R> withProgress(
    options: ProgressOptions,
    task: (progress: Progress<`T$40`>, token: CancellationToken) -> Thenable<R>
): Thenable<R>

external fun createStatusBarItem(
    id: String,
    alignment: StatusBarAlignment = definedExternally,
    priority: Number = definedExternally
): StatusBarItem

external fun createStatusBarItem(id: String): StatusBarItem

external fun createStatusBarItem(id: String, alignment: StatusBarAlignment = definedExternally): StatusBarItem

external fun createStatusBarItem(
    alignment: StatusBarAlignment = definedExternally,
    priority: Number = definedExternally
): StatusBarItem

external fun createStatusBarItem(): StatusBarItem

external fun createStatusBarItem(alignment: StatusBarAlignment = definedExternally): StatusBarItem

external fun createTerminal(
    name: String = definedExternally,
    shellPath: String = definedExternally,
    shellArgs: Array<String> = definedExternally
): Terminal

external fun createTerminal(): Terminal

external fun createTerminal(name: String = definedExternally): Terminal

external fun createTerminal(name: String = definedExternally, shellPath: String = definedExternally): Terminal

external fun createTerminal(
    name: String = definedExternally,
    shellPath: String = definedExternally,
    shellArgs: String = definedExternally
): Terminal

external fun createTerminal(options: TerminalOptions): Terminal

external fun createTerminal(options: ExtensionTerminalOptions): Terminal

external fun <T> registerTreeDataProvider(viewId: String, treeDataProvider: TreeDataProvider<T>): Disposable

external fun <T> createTreeView(viewId: String, options: TreeViewOptions<T>): TreeView<T>

external fun registerUriHandler(handler: UriHandler): Disposable

external fun registerWebviewPanelSerializer(viewType: String, serializer: WebviewPanelSerializer__0): Disposable

external interface `T$41` {
    val retainContextWhenHidden: Boolean?
        get() = definedExternally
}

external interface `T$42` {
    val webviewOptions: `T$41`?
        get() = definedExternally
}

external fun registerWebviewViewProvider(
    viewId: String,
    provider: WebviewViewProvider,
    options: `T$42` = definedExternally
): Disposable

external interface `T$43` {
    val webviewOptions: WebviewPanelOptions?
        get() = definedExternally
    val supportsMultipleEditorsPerDocument: Boolean?
        get() = definedExternally
}

external fun registerCustomEditorProvider(
    viewType: String,
    provider: CustomTextEditorProvider,
    options: `T$43` = definedExternally
): Disposable

external fun registerCustomEditorProvider(viewType: String, provider: CustomTextEditorProvider): Disposable

external fun registerCustomEditorProvider(
    viewType: String,
    provider: CustomReadonlyEditorProvider__0,
    options: `T$43` = definedExternally
): Disposable

external fun registerCustomEditorProvider(viewType: String, provider: CustomReadonlyEditorProvider__0): Disposable

external fun registerCustomEditorProvider(
    viewType: String,
    provider: CustomEditorProvider__0,
    options: `T$43` = definedExternally
): Disposable

external fun registerCustomEditorProvider(viewType: String, provider: CustomEditorProvider__0): Disposable

external fun registerTerminalLinkProvider(provider: TerminalLinkProvider__0): Disposable

external fun registerTerminalProfileProvider(id: String, provider: TerminalProfileProvider): Disposable

external fun registerFileDecorationProvider(provider: FileDecorationProvider): Disposable

external var activeColorTheme: ColorTheme

external var onDidChangeActiveColorTheme: Event<ColorTheme>