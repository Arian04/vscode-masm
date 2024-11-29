@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode

import org.khronos.webgl.Uint8Array
import tsstdlib.Iterable
import tsstdlib.PromiseLike
import tsstdlib.Record
import kotlin.js.Date
import kotlin.js.Json

external interface TreeDragAndDropController<T> {
    val dropMimeTypes: Array<String>
    val dragMimeTypes: Array<String>
    val handleDrag: ((source: Array<T>, dataTransfer: DataTransfer, token: CancellationToken) -> dynamic)?
    val handleDrop: ((target: T?, dataTransfer: DataTransfer, token: CancellationToken) -> dynamic)?
}

external interface ViewBadge {
    val tooltip: String
    val value: Number
}

external interface TreeCheckboxChangeEvent<T> {
    val items: Array<dynamic /* JsTuple<T, TreeItemCheckboxState> */>
}

external interface `T$44` {
    val select: Boolean?
        get() = definedExternally
    val focus: Boolean?
        get() = definedExternally
    val expand: dynamic /* Boolean? | Number? */
        get() = definedExternally
}

external interface TreeView<T> : Disposable {
    val onDidExpandElement: Event<TreeViewExpansionEvent<T>>
    val onDidCollapseElement: Event<TreeViewExpansionEvent<T>>
    val selection: Array<T>
    val onDidChangeSelection: Event<TreeViewSelectionChangeEvent<T>>
    val visible: Boolean
    val onDidChangeVisibility: Event<TreeViewVisibilityChangeEvent>
    val onDidChangeCheckboxState: Event<TreeCheckboxChangeEvent<T>>
    var message: String?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var badge: ViewBadge?
        get() = definedExternally
        set(value) = definedExternally

    fun reveal(element: T, options: `T$44` = definedExternally): Thenable<Unit>
}

external interface TreeDataProvider<T> {
    var onDidChangeTreeData: Event<dynamic /* T? | Array<T>? | Unit? */>?
        get() = definedExternally
        set(value) = definedExternally

    fun getTreeItem(element: T): dynamic /* TreeItem | Thenable<TreeItem> */
    fun getChildren(element: T = definedExternally): dynamic /* Array<T>? | Thenable<Array<T>?>? */
    val getParent: ((element: T) -> dynamic)?
    val resolveTreeItem: ((item: TreeItem, element: T, token: CancellationToken) -> dynamic)?
}

external open class TreeItem {
    open var label: dynamic /* String | TreeItemLabel */
    open var id: String
    open var iconPath: dynamic /* String | Uri | `T$45` | ThemeIcon */
    open var description: dynamic /* String | Boolean */
    open var resourceUri: Uri
    open var tooltip: dynamic /* String? | MarkdownString? */
    open var command: Command
    open var collapsibleState: TreeItemCollapsibleState
    open var contextValue: String
    open var accessibilityInformation: AccessibilityInformation
    open var checkboxState: dynamic /* TreeItemCheckboxState | `T$46` */

    constructor(label: String, collapsibleState: TreeItemCollapsibleState = definedExternally)
    constructor(label: String)
    constructor(label: TreeItemLabel, collapsibleState: TreeItemCollapsibleState = definedExternally)
    constructor(label: TreeItemLabel)
    constructor(resourceUri: Uri, collapsibleState: TreeItemCollapsibleState = definedExternally)
    constructor(resourceUri: Uri)
}

external enum class TreeItemCollapsibleState {
    None /* = 0 */,
    Collapsed /* = 1 */,
    Expanded /* = 2 */
}

external interface TreeItemLabel {
    var label: String
    var highlights: Array<dynamic /* JsTuple<Number, Number> */>?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class TreeItemCheckboxState {
    Unchecked /* = 0 */,
    Checked /* = 1 */
}

external interface `T$47` {
    @nativeGetter
    operator fun get(key: String): String?

    @nativeSetter
    operator fun set(key: String, value: String?)
}

external interface TerminalOptions {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var shellPath: String?
        get() = definedExternally
        set(value) = definedExternally
    var shellArgs: dynamic /* Array<String>? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var cwd: dynamic /* String? | Uri? */
        get() = definedExternally
        set(value) = definedExternally
    var env: `T$47`?
        get() = definedExternally
        set(value) = definedExternally
    var strictEnv: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var hideFromUser: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var message: String?
        get() = definedExternally
        set(value) = definedExternally
    var iconPath: dynamic /* Uri? | `T$7`? | ThemeIcon? */
        get() = definedExternally
        set(value) = definedExternally
    var color: ThemeColor?
        get() = definedExternally
        set(value) = definedExternally
    var location: dynamic /* TerminalLocation? | TerminalEditorLocationOptions? | TerminalSplitLocationOptions? */
        get() = definedExternally
        set(value) = definedExternally
    var isTransient: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ExtensionTerminalOptions {
    var name: String
    var pty: Pseudoterminal
    var iconPath: dynamic /* Uri? | `T$7`? | ThemeIcon? */
        get() = definedExternally
        set(value) = definedExternally
    var color: ThemeColor?
        get() = definedExternally
        set(value) = definedExternally
    var location: dynamic /* TerminalLocation? | TerminalEditorLocationOptions? | TerminalSplitLocationOptions? */
        get() = definedExternally
        set(value) = definedExternally
    var isTransient: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Pseudoterminal {
    var onDidWrite: Event<String>
    var onDidOverrideDimensions: Event<TerminalDimensions?>?
        get() = definedExternally
        set(value) = definedExternally
    var onDidClose: Event<dynamic /* Unit | Number */>?
        get() = definedExternally
        set(value) = definedExternally
    var onDidChangeName: Event<String>?
        get() = definedExternally
        set(value) = definedExternally

    fun open(initialDimensions: TerminalDimensions?)
    fun close()
    val handleInput: ((data: String) -> Unit)?
    val setDimensions: ((dimensions: TerminalDimensions) -> Unit)?
}

external interface TerminalDimensions {
    val columns: Number
    val rows: Number
}

external interface TerminalExitStatus {
    val code: Number?
    val reason: TerminalExitReason
}

external enum class TerminalExitReason {
    Unknown /* = 0 */,
    Shutdown /* = 1 */,
    Process /* = 2 */,
    User /* = 3 */,
    Extension /* = 4 */
}

external enum class EnvironmentVariableMutatorType {
    Replace /* = 1 */,
    Append /* = 2 */,
    Prepend /* = 3 */
}

external interface EnvironmentVariableMutatorOptions {
    var applyAtProcessCreation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var applyAtShellIntegration: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EnvironmentVariableMutator {
    val type: EnvironmentVariableMutatorType
    val value: String
    val options: EnvironmentVariableMutatorOptions
}

typealias EnvironmentVariableCollection = Iterable<dynamic /* JsTuple<variable> */>

external interface GlobalEnvironmentVariableCollection : EnvironmentVariableCollection {
    fun getScoped(scope: EnvironmentVariableScope): EnvironmentVariableCollection
}

external interface EnvironmentVariableScope {
    var workspaceFolder: WorkspaceFolder?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class ProgressLocation {
    SourceControl /* = 1 */,
    Window /* = 10 */,
    Notification /* = 15 */
}

external interface ProgressOptions {
    var location: dynamic /* ProgressLocation | `T$48` */
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var cancellable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface QuickInput {
    var title: String?
    var step: Number?
    var totalSteps: Number?
    var enabled: Boolean
    var busy: Boolean
    var ignoreFocusOut: Boolean
    fun show()
    fun hide()
    var onDidHide: Event<Unit>
    fun dispose()
}

external interface QuickPick<T : QuickPickItem> : QuickInput {
    var value: String
    var placeholder: String?
    val onDidChangeValue: Event<String>
    val onDidAccept: Event<Unit>
    var buttons: Array<QuickInputButton>
    val onDidTriggerButton: Event<QuickInputButton>
    val onDidTriggerItemButton: Event<QuickPickItemButtonEvent<T>>
    var items: Array<T>
    var canSelectMany: Boolean
    var matchOnDescription: Boolean
    var matchOnDetail: Boolean
    var keepScrollPosition: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var activeItems: Array<T>
    val onDidChangeActive: Event<Array<T>>
    var selectedItems: Array<T>
    val onDidChangeSelection: Event<Array<T>>
}

external interface InputBox : QuickInput {
    var value: String
    var valueSelection: dynamic /* JsTuple<Number, Number> */
        get() = definedExternally
        set(value) = definedExternally
    var placeholder: String?
    var password: Boolean
    val onDidChangeValue: Event<String>
    val onDidAccept: Event<Unit>
    var buttons: Array<QuickInputButton>
    val onDidTriggerButton: Event<QuickInputButton>
    var prompt: String?
    var validationMessage: dynamic /* String? | InputBoxValidationMessage? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface QuickInputButton {
    val iconPath: dynamic /* Uri | `T$7` | ThemeIcon */
        get() = definedExternally
    val tooltip: String?
        get() = definedExternally
}

external open class QuickInputButtons {
    companion object {
        val Back: QuickInputButton
    }
}

external interface QuickPickItemButtonEvent<T : QuickPickItem> {
    val button: QuickInputButton
    val item: T
}

external interface TextDocumentContentChangeEvent {
    val range: Range
    val rangeOffset: Number
    val rangeLength: Number
    val text: String
}

external enum class TextDocumentChangeReason {
    Undo /* = 1 */,
    Redo /* = 2 */
}

external interface TextDocumentChangeEvent {
    val document: TextDocument
    val contentChanges: Array<TextDocumentContentChangeEvent>
    val reason: TextDocumentChangeReason?
}

external enum class TextDocumentSaveReason {
    Manual /* = 1 */,
    AfterDelay /* = 2 */,
    FocusOut /* = 3 */
}

external interface TextDocumentWillSaveEvent {
    val document: TextDocument
    val reason: TextDocumentSaveReason
    fun waitUntil(thenable: Thenable<Array<TextEdit>>)
    fun waitUntil(thenable: Thenable<Any>)
}

external interface FileWillCreateEvent {
    val token: CancellationToken
    val files: Array<Uri>
    fun waitUntil(thenable: Thenable<WorkspaceEdit>)
    fun waitUntil(thenable: Thenable<Any>)
}

external interface FileCreateEvent {
    val files: Array<Uri>
}

external interface FileWillDeleteEvent {
    val token: CancellationToken
    val files: Array<Uri>
    fun waitUntil(thenable: Thenable<WorkspaceEdit>)
    fun waitUntil(thenable: Thenable<Any>)
}

external interface FileDeleteEvent {
    val files: Array<Uri>
}

external interface `T$49` {
    val oldUri: Uri
    val newUri: Uri
}

external interface FileWillRenameEvent {
    val token: CancellationToken
    val files: Array<`T$49`>
    fun waitUntil(thenable: Thenable<WorkspaceEdit>)
    fun waitUntil(thenable: Thenable<Any>)
}

external interface FileRenameEvent {
    val files: Array<`T$49`>
}

external interface WorkspaceFoldersChangeEvent {
    val added: Array<WorkspaceFolder>
    val removed: Array<WorkspaceFolder>
}

external interface WorkspaceFolder {
    val uri: Uri
    val name: String
    val index: Number
}

external interface `T$61` {
    var uri: Uri?
        get() = definedExternally
        set(value) = definedExternally
    var languageId: String
}

external interface ConfigurationChangeEvent {
    fun affectsConfiguration(section: String, scope: Uri = definedExternally): Boolean
    fun affectsConfiguration(section: String): Boolean
    fun affectsConfiguration(section: String, scope: TextDocument = definedExternally): Boolean
    fun affectsConfiguration(section: String, scope: WorkspaceFolder = definedExternally): Boolean
    fun affectsConfiguration(section: String, scope: `T$61` = definedExternally): Boolean
}

external enum class NotebookEditorRevealType {
    Default /* = 0 */,
    InCenter /* = 1 */,
    InCenterIfOutsideViewport /* = 2 */,
    AtTop /* = 3 */
}

external interface NotebookEditor {
    val notebook: NotebookDocument
    var selection: NotebookRange
    var selections: Array<NotebookRange>
    val visibleRanges: Array<NotebookRange>
    val viewColumn: ViewColumn?
        get() = definedExternally

    fun revealRange(range: NotebookRange, revealType: NotebookEditorRevealType = definedExternally)
}

external interface `T$53` {
    val editor: NotebookEditor
    val message: Any
}

external interface NotebookRendererMessaging {
    val onDidReceiveMessage: Event<`T$53`>
    fun postMessage(message: Any, editor: NotebookEditor = definedExternally): Thenable<Boolean>
}

external enum class NotebookCellKind {
    Markup /* = 1 */,
    Code /* = 2 */
}

external interface NotebookCell {
    val index: Number
    val notebook: NotebookDocument
    val kind: NotebookCellKind
    val document: TextDocument
    val metadata: Json
    val outputs: Array<NotebookCellOutput>
    val executionSummary: NotebookCellExecutionSummary?
}

external interface NotebookDocument {
    val uri: Uri
    val notebookType: String
    val version: Number
    val isDirty: Boolean
    val isUntitled: Boolean
    val isClosed: Boolean
    val metadata: Json
    val cellCount: Number
    fun cellAt(index: Number): NotebookCell
    fun getCells(range: NotebookRange = definedExternally): Array<NotebookCell>
    fun save(): Thenable<Boolean>
}

external interface NotebookDocumentCellChange {
    val cell: NotebookCell
    val document: TextDocument?
    val metadata: Json?
    val outputs: Array<NotebookCellOutput>?
    val executionSummary: NotebookCellExecutionSummary?
}

external interface NotebookDocumentContentChange {
    val range: NotebookRange
    val addedCells: Array<NotebookCell>
    val removedCells: Array<NotebookCell>
}

external interface NotebookDocumentChangeEvent {
    val notebook: NotebookDocument
    val metadata: Json?
    val contentChanges: Array<NotebookDocumentContentChange>
    val cellChanges: Array<NotebookDocumentCellChange>
}

external interface NotebookDocumentWillSaveEvent {
    val token: CancellationToken
    val notebook: NotebookDocument
    val reason: TextDocumentSaveReason
    fun waitUntil(thenable: Thenable<WorkspaceEdit>)
    fun waitUntil(thenable: Thenable<Any>)
}

external interface `T$54` {
    val startTime: Number
    val endTime: Number
}

external interface NotebookCellExecutionSummary {
    val executionOrder: Number?
        get() = definedExternally
    val success: Boolean?
        get() = definedExternally
    val timing: `T$54`?
        get() = definedExternally
}

external interface `T$55` {
    var start: Number?
        get() = definedExternally
        set(value) = definedExternally
    var end: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external open class NotebookRange(start: Number, end: Number) {
    open val start: Number
    open val end: Number
    open val isEmpty: Boolean
    open fun with(change: `T$55`): NotebookRange
}

external open class NotebookCellOutputItem(data: Uint8Array, mime: String) {
    open var mime: String
    open var data: Uint8Array

    companion object {
        fun text(value: String, mime: String = definedExternally): NotebookCellOutputItem
        fun json(value: Any, mime: String = definedExternally): NotebookCellOutputItem
        fun stdout(value: String): NotebookCellOutputItem
        fun stderr(value: String): NotebookCellOutputItem
        fun error(value: Error): NotebookCellOutputItem
    }
}

external open class NotebookCellOutput(items: Array<NotebookCellOutputItem>, metadata: Json = definedExternally) {
    open var items: Array<NotebookCellOutputItem>
    open var metadata: Json
}

external open class NotebookCellData(kind: NotebookCellKind, value: String, languageId: String) {
    open var kind: NotebookCellKind
    open var value: String
    open var languageId: String
    open var outputs: Array<NotebookCellOutput>
    open var metadata: Json
    open var executionSummary: NotebookCellExecutionSummary
}

external open class NotebookData(cells: Array<NotebookCellData>) {
    open var cells: Array<NotebookCellData>
    open var metadata: Json
}

external interface NotebookSerializer {
    fun deserializeNotebook(
        content: Uint8Array,
        token: CancellationToken
    ): dynamic /* NotebookData | Thenable<NotebookData> */

    fun serializeNotebook(data: NotebookData, token: CancellationToken): dynamic /* Uint8Array | Thenable<Uint8Array> */
}

external interface `T$56` {
    @nativeGetter
    operator fun get(key: String): Boolean?

    @nativeSetter
    operator fun set(key: String, value: Boolean?)
}

external interface NotebookDocumentContentOptions {
    var transientOutputs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var transientCellMetadata: `T$56`?
        get() = definedExternally
        set(value) = definedExternally
    var transientDocumentMetadata: `T$56`?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class NotebookControllerAffinity {
    Default /* = 1 */,
    Preferred /* = 2 */
}

external interface `T$57` {
    val notebook: NotebookDocument
    val selected: Boolean
}

external interface NotebookController {
    val id: String
    val notebookType: String
    var supportedLanguages: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var label: String
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
    var supportsExecutionOrder: Boolean?
        get() = definedExternally
        set(value) = definedExternally

    fun createNotebookCellExecution(cell: NotebookCell): NotebookCellExecution
    var executeHandler: (cells: Array<NotebookCell>, notebook: NotebookDocument, controller: NotebookController) -> dynamic
    var interruptHandler: ((notebook: NotebookDocument) -> dynamic)?
        get() = definedExternally
        set(value) = definedExternally
    val onDidChangeSelectedNotebooks: Event<`T$57`>
    fun updateNotebookAffinity(notebook: NotebookDocument, affinity: NotebookControllerAffinity)
    fun dispose()
}

external interface NotebookCellExecution {
    val cell: NotebookCell
    val token: CancellationToken
    var executionOrder: Number?
    fun start(startTime: Number = definedExternally)
    fun end(success: Boolean?, endTime: Number = definedExternally)
    fun clearOutput(cell: NotebookCell = definedExternally): Thenable<Unit>
    fun replaceOutput(out: NotebookCellOutput, cell: NotebookCell = definedExternally): Thenable<Unit>
    fun replaceOutput(out: NotebookCellOutput): Thenable<Unit>
    fun replaceOutput(out: Array<NotebookCellOutput>, cell: NotebookCell = definedExternally): Thenable<Unit>
    fun replaceOutput(out: Array<NotebookCellOutput>): Thenable<Unit>
    fun appendOutput(out: NotebookCellOutput, cell: NotebookCell = definedExternally): Thenable<Unit>
    fun appendOutput(out: NotebookCellOutput): Thenable<Unit>
    fun appendOutput(out: Array<NotebookCellOutput>, cell: NotebookCell = definedExternally): Thenable<Unit>
    fun appendOutput(out: Array<NotebookCellOutput>): Thenable<Unit>
    fun replaceOutputItems(items: NotebookCellOutputItem, output: NotebookCellOutput): Thenable<Unit>
    fun replaceOutputItems(items: Array<NotebookCellOutputItem>, output: NotebookCellOutput): Thenable<Unit>
    fun appendOutputItems(items: NotebookCellOutputItem, output: NotebookCellOutput): Thenable<Unit>
    fun appendOutputItems(items: Array<NotebookCellOutputItem>, output: NotebookCellOutput): Thenable<Unit>
}

external enum class NotebookCellStatusBarAlignment {
    Left /* = 1 */,
    Right /* = 2 */
}

external open class NotebookCellStatusBarItem(text: String, alignment: NotebookCellStatusBarAlignment) {
    open var text: String
    open var alignment: NotebookCellStatusBarAlignment
    open var command: dynamic /* String | Command */
    open var tooltip: String
    open var priority: Number
    open var accessibilityInformation: AccessibilityInformation
}

external interface NotebookCellStatusBarItemProvider {
    var onDidChangeCellStatusBarItems: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideCellStatusBarItems(
        cell: NotebookCell,
        token: CancellationToken
    ): dynamic /* NotebookCellStatusBarItem? | Array<NotebookCellStatusBarItem>? | Thenable<dynamic /* NotebookCellStatusBarItem? | Array<NotebookCellStatusBarItem>? */>? */
}

external interface SourceControlInputBox {
    var value: String
    var placeholder: String
    var enabled: Boolean
    var visible: Boolean
}

external interface QuickDiffProvider {
    val provideOriginalResource: ((uri: Uri, token: CancellationToken) -> dynamic)?
}

external interface SourceControlResourceThemableDecorations {
    val iconPath: dynamic /* String? | Uri? | ThemeIcon? */
        get() = definedExternally
}

external interface SourceControlResourceDecorations : SourceControlResourceThemableDecorations {
    val strikeThrough: Boolean?
        get() = definedExternally
    val faded: Boolean?
        get() = definedExternally
    val tooltip: String?
        get() = definedExternally
    val light: SourceControlResourceThemableDecorations?
        get() = definedExternally
    val dark: SourceControlResourceThemableDecorations?
        get() = definedExternally
}

external interface SourceControlResourceState {
    val resourceUri: Uri
    val command: Command?
        get() = definedExternally
    val decorations: SourceControlResourceDecorations?
        get() = definedExternally
    val contextValue: String?
        get() = definedExternally
}

external interface SourceControlResourceGroup {
    val id: String
    var label: String
    var hideWhenEmpty: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var resourceStates: Array<SourceControlResourceState>
    fun dispose()
}

external interface SourceControl {
    val id: String
    val label: String
    val rootUri: Uri?
    val inputBox: SourceControlInputBox
    var count: Number?
        get() = definedExternally
        set(value) = definedExternally
    var quickDiffProvider: QuickDiffProvider?
        get() = definedExternally
        set(value) = definedExternally
    var commitTemplate: String?
        get() = definedExternally
        set(value) = definedExternally
    var acceptInputCommand: Command?
        get() = definedExternally
        set(value) = definedExternally
    var statusBarCommands: Array<Command>?
        get() = definedExternally
        set(value) = definedExternally

    fun createResourceGroup(id: String, label: String): SourceControlResourceGroup
    fun dispose()
}

external interface DebugProtocolMessage

external interface DebugProtocolSource

external interface DebugProtocolBreakpoint

external interface DebugConfiguration {
    var type: String
    var name: String
    var request: String

    @nativeGetter
    operator fun get(key: String): Any?

    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface DebugSession {
    val id: String
    val type: String
    val parentSession: DebugSession?
        get() = definedExternally
    var name: String
    val workspaceFolder: WorkspaceFolder?
    val configuration: DebugConfiguration
    fun customRequest(command: String, args: Any = definedExternally): Thenable<Any>
    fun getDebugProtocolBreakpoint(breakpoint: Breakpoint): Thenable<DebugProtocolBreakpoint?>
}

external interface DebugSessionCustomEvent {
    val session: DebugSession
    val event: String
    val body: Any
}

external interface DebugConfigurationProvider {
    val provideDebugConfigurations: ((folder: WorkspaceFolder?, token: CancellationToken) -> dynamic)?
    val resolveDebugConfiguration: ((folder: WorkspaceFolder?, debugConfiguration: DebugConfiguration, token: CancellationToken) -> dynamic)?
    val resolveDebugConfigurationWithSubstitutedVariables: ((folder: WorkspaceFolder?, debugConfiguration: DebugConfiguration, token: CancellationToken) -> dynamic)?
}

external open class DebugAdapterExecutable(
    command: String,
    args: Array<String> = definedExternally,
    options: DebugAdapterExecutableOptions = definedExternally
) {
    open val command: String
    open val args: Array<String>
    open val options: DebugAdapterExecutableOptions
}

external interface DebugAdapterExecutableOptions {
    var env: `T$28`?
        get() = definedExternally
        set(value) = definedExternally
    var cwd: String?
        get() = definedExternally
        set(value) = definedExternally
}

external open class DebugAdapterServer(port: Number, host: String = definedExternally) {
    open val port: Number
    open val host: String?
}

external open class DebugAdapterNamedPipeServer(path: String) {
    open val path: String
}

external interface DebugAdapter : Disposable {
    val onDidSendMessage: Event<DebugProtocolMessage>
    fun handleMessage(message: DebugProtocolMessage)
}

external open class DebugAdapterInlineImplementation(implementation: DebugAdapter)

external interface DebugAdapterDescriptorFactory {
    fun createDebugAdapterDescriptor(
        session: DebugSession,
        executable: DebugAdapterExecutable?
    ): dynamic /* DebugAdapterExecutable? | DebugAdapterServer? | DebugAdapterNamedPipeServer? | DebugAdapterInlineImplementation? | Thenable<dynamic /* DebugAdapterExecutable? | DebugAdapterServer? | DebugAdapterNamedPipeServer? | DebugAdapterInlineImplementation? */>? */
}

external interface DebugAdapterTracker {
    val onWillStartSession: (() -> Unit)?
    val onWillReceiveMessage: ((message: Any) -> Unit)?
    val onDidSendMessage: ((message: Any) -> Unit)?
    val onWillStopSession: (() -> Unit)?
    val onError: ((error: Error) -> Unit)?
    val onExit: ((code: Number?, signal: String?) -> Unit)?
}

external interface DebugAdapterTrackerFactory {
    fun createDebugAdapterTracker(session: DebugSession): dynamic /* DebugAdapterTracker? | Thenable<DebugAdapterTracker?>? */
}

external interface DebugConsole {
    fun append(value: String)
    fun appendLine(value: String)
}

external interface BreakpointsChangeEvent {
    val added: Array<Breakpoint>
    val removed: Array<Breakpoint>
    val changed: Array<Breakpoint>
}

external open class Breakpoint(
    enabled: Boolean = definedExternally,
    condition: String = definedExternally,
    hitCondition: String = definedExternally,
    logMessage: String = definedExternally
) {
    open val id: String
    open val enabled: Boolean
    open val condition: String?
    open val hitCondition: String?
    open val logMessage: String?
}

external open class SourceBreakpoint(
    location: Location,
    enabled: Boolean = definedExternally,
    condition: String = definedExternally,
    hitCondition: String = definedExternally,
    logMessage: String = definedExternally
) : Breakpoint {
    open val location: Location
}

external open class FunctionBreakpoint(
    functionName: String,
    enabled: Boolean = definedExternally,
    condition: String = definedExternally,
    hitCondition: String = definedExternally,
    logMessage: String = definedExternally
) : Breakpoint {
    open val functionName: String
}

external enum class DebugConsoleMode {
    Separate /* = 0 */,
    MergeWithParent /* = 1 */
}

external interface DebugSessionOptions {
    var parentSession: DebugSession?
        get() = definedExternally
        set(value) = definedExternally
    var lifecycleManagedByParent: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var consoleMode: DebugConsoleMode?
        get() = definedExternally
        set(value) = definedExternally
    var noDebug: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var compact: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var suppressSaveBeforeStart: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var suppressDebugToolbar: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var suppressDebugStatusbar: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var suppressDebugView: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var testRun: TestRun?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class DebugConfigurationProviderTriggerKind {
    Initial /* = 1 */,
    Dynamic /* = 2 */
}

external open class DebugThread(session: DebugSession, threadId: Number) {
    open val session: DebugSession
    open val threadId: Number
}

external open class DebugStackFrame(session: DebugSession, threadId: Number, frameId: Number) {
    open val session: DebugSession
    open val threadId: Number
    open val frameId: Number
}

external enum class CommentThreadCollapsibleState {
    Collapsed /* = 0 */,
    Expanded /* = 1 */
}

external enum class CommentMode {
    Editing /* = 0 */,
    Preview /* = 1 */
}

external enum class CommentThreadState {
    Unresolved /* = 0 */,
    Resolved /* = 1 */
}

external interface CommentThread {
    val uri: Uri
    var range: Range
    var comments: Array<Comment>
    var collapsibleState: CommentThreadCollapsibleState
    var canReply: Boolean
    var contextValue: String?
        get() = definedExternally
        set(value) = definedExternally
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
    var state: CommentThreadState?
        get() = definedExternally
        set(value) = definedExternally

    fun dispose()
}

external interface CommentAuthorInformation {
    var name: String
    var iconPath: Uri?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CommentReaction {
    val label: String
    val iconPath: dynamic /* String | Uri */
        get() = definedExternally
    val count: Number
    val authorHasReacted: Boolean
}

external interface Comment {
    var body: dynamic /* String | MarkdownString */
        get() = definedExternally
        set(value) = definedExternally
    var mode: CommentMode
    var author: CommentAuthorInformation
    var contextValue: String?
        get() = definedExternally
        set(value) = definedExternally
    var reactions: Array<CommentReaction>?
        get() = definedExternally
        set(value) = definedExternally
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
    var timestamp: Date?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CommentReply {
    var thread: CommentThread
    var text: String
}

external interface CommentingRangeProvider {
    fun provideCommentingRanges(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* Array<Range>? | Thenable<Array<Range>?>? */
}

external interface CommentOptions {
    var prompt: String?
        get() = definedExternally
        set(value) = definedExternally
    var placeHolder: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CommentController {
    val id: String
    val label: String
    var options: CommentOptions?
        get() = definedExternally
        set(value) = definedExternally
    var commentingRangeProvider: CommentingRangeProvider?
        get() = definedExternally
        set(value) = definedExternally

    fun createCommentThread(uri: Uri, range: Range, comments: Array<Comment>): CommentThread
    var reactionHandler: ((comment: Comment, reaction: CommentReaction) -> Thenable<Unit>)?
        get() = definedExternally
        set(value) = definedExternally

    fun dispose()
}

external interface AuthenticationSession {
    val id: String
    val accessToken: String
    val account: AuthenticationSessionAccountInformation
    val scopes: Array<String>
}

external interface AuthenticationSessionAccountInformation {
    val id: String
    val label: String
}

external interface AuthenticationForceNewSessionOptions {
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AuthenticationGetSessionOptions {
    var clearSessionPreference: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var createIfNone: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var forceNewSession: dynamic /* Boolean? | AuthenticationForceNewSessionOptions? */
        get() = definedExternally
        set(value) = definedExternally
    var silent: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var account: AuthenticationSessionAccountInformation?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AuthenticationProviderInformation {
    val id: String
    val label: String
}

external interface AuthenticationSessionsChangeEvent {
    val provider: AuthenticationProviderInformation
}

external interface AuthenticationProviderOptions {
    val supportsMultipleAccounts: Boolean?
        get() = definedExternally
}

external interface AuthenticationProviderAuthenticationSessionsChangeEvent {
    val added: Array<AuthenticationSession>?
    val removed: Array<AuthenticationSession>?
    val changed: Array<AuthenticationSession>?
}

external interface AuthenticationProviderSessionOptions {
    var account: AuthenticationSessionAccountInformation?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AuthenticationProvider {
    val onDidChangeSessions: Event<AuthenticationProviderAuthenticationSessionsChangeEvent>
    fun getSessions(
        scopes: Array<String>?,
        options: AuthenticationProviderSessionOptions
    ): Thenable<Array<AuthenticationSession>>

    fun createSession(
        scopes: Array<String>,
        options: AuthenticationProviderSessionOptions
    ): Thenable<AuthenticationSession>

    fun removeSession(sessionId: String): Thenable<Unit>
}

external enum class TestRunProfileKind {
    Run /* = 1 */,
    Debug /* = 2 */,
    Coverage /* = 3 */
}

external open class TestTag(id: String) {
    open val id: String
}

external interface TestRunProfile {
    var label: String
    val kind: TestRunProfileKind
    var isDefault: Boolean
    var onDidChangeDefault: Event<Boolean>
    var supportsContinuousRun: Boolean
    var tag: TestTag?
    var configureHandler: (() -> Unit)?
    var runHandler: (request: TestRunRequest, token: CancellationToken) -> dynamic
    var loadDetailedCoverage: ((testRun: TestRun, fileCoverage: FileCoverage, token: CancellationToken) -> Thenable<Array<dynamic /* StatementCoverage | DeclarationCoverage */>>)?
        get() = definedExternally
        set(value) = definedExternally

    fun dispose()
}

external interface TestController {
    val id: String
    var label: String
    val items: TestItemCollection
    fun createRunProfile(
        label: String,
        kind: TestRunProfileKind,
        runHandler: (request: TestRunRequest, token: CancellationToken) -> Any,
        isDefault: Boolean = definedExternally,
        tag: TestTag = definedExternally,
        supportsContinuousRun: Boolean = definedExternally
    ): TestRunProfile

    var resolveHandler: ((item: TestItem?) -> dynamic)?
        get() = definedExternally
        set(value) = definedExternally
    var refreshHandler: ((token: CancellationToken) -> dynamic)?
    fun createTestRun(
        request: TestRunRequest,
        name: String = definedExternally,
        persist: Boolean = definedExternally
    ): TestRun

    fun createTestItem(id: String, label: String, uri: Uri = definedExternally): TestItem
    fun invalidateTestResults(items: TestItem = definedExternally)
    fun invalidateTestResults()
    fun invalidateTestResults(items: Array<TestItem> = definedExternally)
    fun dispose()
}

external open class TestRunRequest(
    include: Array<TestItem> = definedExternally,
    exclude: Array<TestItem> = definedExternally,
    profile: TestRunProfile = definedExternally,
    continuous: Boolean = definedExternally,
    preserveFocus: Boolean = definedExternally
) {
    open val include: Array<TestItem>?
    open val exclude: Array<TestItem>?
    open val profile: TestRunProfile?
    open val continuous: Boolean
    open val preserveFocus: Boolean
}

external interface TestRun {
    val name: String?
    val token: CancellationToken
    val isPersisted: Boolean
    fun enqueued(test: TestItem)
    fun started(test: TestItem)
    fun skipped(test: TestItem)
    fun failed(test: TestItem, message: TestMessage, duration: Number = definedExternally)
    fun failed(test: TestItem, message: TestMessage)
    fun failed(test: TestItem, message: Array<TestMessage>, duration: Number = definedExternally)
    fun failed(test: TestItem, message: Array<TestMessage>)
    fun errored(test: TestItem, message: TestMessage, duration: Number = definedExternally)
    fun errored(test: TestItem, message: TestMessage)
    fun errored(test: TestItem, message: Array<TestMessage>, duration: Number = definedExternally)
    fun errored(test: TestItem, message: Array<TestMessage>)
    fun passed(test: TestItem, duration: Number = definedExternally)
    fun appendOutput(output: String, location: Location = definedExternally, test: TestItem = definedExternally)
    fun addCoverage(fileCoverage: FileCoverage)
    fun end()
    var onDidDispose: Event<Unit>
}

typealias TestItemCollection = Iterable<dynamic /* JsTuple<id> */>

external interface TestItem {
    val id: String
    val uri: Uri?
    val children: TestItemCollection
    val parent: TestItem?
    var tags: Array<TestTag>
    var canResolveChildren: Boolean
    var busy: Boolean
    var label: String
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var sortText: String?
        get() = definedExternally
        set(value) = definedExternally
    var range: Range?
    var error: dynamic /* String? | MarkdownString? */
        get() = definedExternally
        set(value) = definedExternally
}

external open class TestMessageStackFrame(
    label: String,
    uri: Uri = definedExternally,
    position: Position = definedExternally
) {
    open var uri: Uri
    open var position: Position
    open var label: String
}

external open class TestMessage {
    open var message: dynamic /* String | MarkdownString */
    open var expectedOutput: String
    open var actualOutput: String
    open var location: Location
    open var contextValue: String
    open var stackTrace: Array<TestMessageStackFrame>

    constructor(message: String)
    constructor(message: MarkdownString)

    companion object {
        fun diff(message: String, expected: String, actual: String): TestMessage
        fun diff(message: MarkdownString, expected: String, actual: String): TestMessage
    }
}

external open class TestCoverageCount(covered: Number, total: Number) {
    open var covered: Number
    open var total: Number
}

external open class FileCoverage(
    uri: Uri,
    statementCoverage: TestCoverageCount,
    branchCoverage: TestCoverageCount = definedExternally,
    declarationCoverage: TestCoverageCount = definedExternally
) {
    open val uri: Uri
    open var statementCoverage: TestCoverageCount
    open var branchCoverage: TestCoverageCount
    open var declarationCoverage: TestCoverageCount

    companion object {
        fun fromDetails(uri: Uri, details: Array<Any /* StatementCoverage | DeclarationCoverage */>): FileCoverage
    }
}

external open class StatementCoverage {
    open var executed: dynamic /* Number | Boolean */
    open var location: dynamic /* Position | Range */
    open var branches: Array<BranchCoverage>

    constructor(executed: Number, location: Position, branches: Array<BranchCoverage> = definedExternally)
    constructor(executed: Number, location: Position)
    constructor(executed: Number, location: Range, branches: Array<BranchCoverage> = definedExternally)
    constructor(executed: Number, location: Range)
    constructor(executed: Boolean, location: Position, branches: Array<BranchCoverage> = definedExternally)
    constructor(executed: Boolean, location: Position)
    constructor(executed: Boolean, location: Range, branches: Array<BranchCoverage> = definedExternally)
    constructor(executed: Boolean, location: Range)
}

external open class BranchCoverage {
    open var executed: dynamic /* Number | Boolean */
    open var location: dynamic /* Position | Range */
    open var label: String

    constructor(executed: Number, location: Position = definedExternally, label: String = definedExternally)
    constructor(executed: Number)
    constructor(executed: Number, location: Position = definedExternally)
    constructor(executed: Number, location: Range = definedExternally, label: String = definedExternally)
    constructor(executed: Number, location: Range = definedExternally)
    constructor(executed: Boolean, location: Position = definedExternally, label: String = definedExternally)
    constructor(executed: Boolean)
    constructor(executed: Boolean, location: Position = definedExternally)
    constructor(executed: Boolean, location: Range = definedExternally, label: String = definedExternally)
    constructor(executed: Boolean, location: Range = definedExternally)
}

external open class DeclarationCoverage {
    open var name: String
    open var executed: dynamic /* Number | Boolean */
    open var location: dynamic /* Position | Range */

    constructor(name: String, executed: Number, location: Position)
    constructor(name: String, executed: Number, location: Range)
    constructor(name: String, executed: Boolean, location: Position)
    constructor(name: String, executed: Boolean, location: Range)
}

external open class TabInputText(uri: Uri) {
    open val uri: Uri
}

external open class TabInputTextDiff(original: Uri, modified: Uri) {
    open val original: Uri
    open val modified: Uri
}

external open class TabInputCustom(uri: Uri, viewType: String) {
    open val uri: Uri
    open val viewType: String
}

external open class TabInputWebview(viewType: String) {
    open val viewType: String
}

external open class TabInputNotebook(uri: Uri, notebookType: String) {
    open val uri: Uri
    open val notebookType: String
}

external open class TabInputNotebookDiff(original: Uri, modified: Uri, notebookType: String) {
    open val original: Uri
    open val modified: Uri
    open val notebookType: String
}

external open class TabInputTerminal

external interface Tab {
    val label: String
    val group: TabGroup
    val input: dynamic /* TabInputText | TabInputTextDiff | TabInputCustom | TabInputWebview | TabInputNotebook | TabInputNotebookDiff | TabInputTerminal | Any */
        get() = definedExternally
    val isActive: Boolean
    val isDirty: Boolean
    val isPinned: Boolean
    val isPreview: Boolean
}

external interface TabChangeEvent {
    val opened: Array<Tab>
    val closed: Array<Tab>
    val changed: Array<Tab>
}

external interface TabGroupChangeEvent {
    val opened: Array<TabGroup>
    val closed: Array<TabGroup>
    val changed: Array<TabGroup>
}

external interface TabGroup {
    val isActive: Boolean
    val viewColumn: ViewColumn
    val activeTab: Tab?
    val tabs: Array<Tab>
}

external interface TabGroups {
    val all: Array<TabGroup>
    val activeTabGroup: TabGroup
    val onDidChangeTabGroups: Event<TabGroupChangeEvent>
    val onDidChangeTabs: Event<TabChangeEvent>
    fun close(tab: Tab, preserveFocus: Boolean = definedExternally): Thenable<Boolean>
    fun close(tab: Tab): Thenable<Boolean>
    fun close(tab: Array<Tab>, preserveFocus: Boolean = definedExternally): Thenable<Boolean>
    fun close(tab: Array<Tab>): Thenable<Boolean>
    fun close(tabGroup: TabGroup, preserveFocus: Boolean = definedExternally): Thenable<Boolean>
    fun close(tabGroup: TabGroup): Thenable<Boolean>
    fun close(tabGroup: Array<TabGroup>, preserveFocus: Boolean = definedExternally): Thenable<Boolean>
    fun close(tabGroup: Array<TabGroup>): Thenable<Boolean>
}

external open class TelemetryTrustedValue<T>(value: T) {
    open val value: T
}

external open class TelemetryTrustedValue__0 : TelemetryTrustedValue<Any>

external interface TelemetryLogger {
    val onDidChangeEnableStates: Event<TelemetryLogger>
    val isUsageEnabled: Boolean
    val isErrorsEnabled: Boolean
    fun logUsage(eventName: String, data: Record<String, Any /* Any | TelemetryTrustedValue__0 */> = definedExternally)
    fun logError(eventName: String, data: Record<String, Any /* Any | TelemetryTrustedValue__0 */> = definedExternally)
    fun logError(eventName: String)
    fun logError(error: Error, data: Record<String, Any /* Any | TelemetryTrustedValue__0 */> = definedExternally)
    fun logError(error: Error)
    fun dispose()
}

external interface TelemetrySender {
    fun sendEventData(eventName: String, data: Record<String, Any> = definedExternally)
    fun sendErrorData(error: Error, data: Record<String, Any> = definedExternally)
    val flush: (() -> dynamic)?
}

external interface TelemetryLoggerOptions {
    val ignoreBuiltInCommonProperties: Boolean?
        get() = definedExternally
    val ignoreUnhandledErrors: Boolean?
        get() = definedExternally
    val additionalCommonProperties: Record<String, Any>?
        get() = definedExternally
}

external open class ChatRequestTurn(
    prompt: String,
    command: String?,
    references: Array<ChatPromptReference>,
    participant: String,
    toolReferences: Array<ChatLanguageModelToolReference>
) {
    open val prompt: String
    open val participant: String
    open val command: String
    open val references: Array<ChatPromptReference>
    open val toolReferences: Array<ChatLanguageModelToolReference>
}

external open class ChatResponseTurn(
    response: Array<Any /* ChatResponseMarkdownPart | ChatResponseFileTreePart | ChatResponseAnchorPart | ChatResponseCommandButtonPart */>,
    result: ChatResult,
    participant: String
) {
    open val response: Array<dynamic /* ChatResponseMarkdownPart | ChatResponseFileTreePart | ChatResponseAnchorPart | ChatResponseCommandButtonPart */>
    open val result: ChatResult
    open val participant: String
    open val command: String
}

external interface ChatContext {
    val history: Array<dynamic /* ChatRequestTurn | ChatResponseTurn */>
}

external interface ChatErrorDetails {
    var message: String
    var responseIsFiltered: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ChatResult {
    var errorDetails: ChatErrorDetails?
        get() = definedExternally
        set(value) = definedExternally
    val metadata: Json?
        get() = definedExternally
}

external enum class ChatResultFeedbackKind {
    Unhelpful /* = 0 */,
    Helpful /* = 1 */
}

external interface ChatResultFeedback {
    val result: ChatResult
    val kind: ChatResultFeedbackKind
}

external interface ChatFollowup {
    var prompt: String
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
    var participant: String?
        get() = definedExternally
        set(value) = definedExternally
    var command: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ChatFollowupProvider {
    fun provideFollowups(
        result: ChatResult,
        context: ChatContext,
        token: CancellationToken
    ): dynamic /* Array<ChatFollowup>? | Thenable<Array<ChatFollowup>?>? */
}

typealias ChatRequestHandler = (request: ChatRequest, context: ChatContext, response: ChatResponseStream, token: CancellationToken) -> dynamic

external interface ChatParticipant {
    val id: String
    var iconPath: dynamic /* Uri? | `T$7`? | ThemeIcon? */
        get() = definedExternally
        set(value) = definedExternally
    var requestHandler: ChatRequestHandler
    var followupProvider: ChatFollowupProvider?
        get() = definedExternally
        set(value) = definedExternally
    var onDidReceiveFeedback: Event<ChatResultFeedback>
    fun dispose()
}

external interface ChatPromptReference {
    val id: String
    val range: dynamic /* JsTuple<start, Number, end, Number> */
        get() = definedExternally
    val modelDescription: String?
        get() = definedExternally
    val value: dynamic /* String | Uri | Location | Any */
        get() = definedExternally
}

external interface ChatRequest {
    val prompt: String
    val command: String?
    val references: Array<ChatPromptReference>
    val toolReferences: Array<ChatLanguageModelToolReference>
    val toolInvocationToken: ChatParticipantToolToken
    val model: LanguageModelChat
}

external interface ChatResponseStream {
    fun markdown(value: String)
    fun markdown(value: MarkdownString)
    fun anchor(value: Uri, title: String = definedExternally)
    fun anchor(value: Uri)
    fun anchor(value: Location, title: String = definedExternally)
    fun anchor(value: Location)
    fun button(command: Command)
    fun filetree(value: Array<ChatResponseFileTree>, baseUri: Uri)
    fun progress(value: String)
    fun reference(value: Uri, iconPath: Uri = definedExternally)
    fun reference(value: Uri)
    fun reference(value: Uri, iconPath: ThemeIcon = definedExternally)
    fun reference(value: Uri, iconPath: `T$7` = definedExternally)
    fun reference(value: Location, iconPath: Uri = definedExternally)
    fun reference(value: Location)
    fun reference(value: Location, iconPath: ThemeIcon = definedExternally)
    fun reference(value: Location, iconPath: `T$7` = definedExternally)
    fun push(part: ChatResponseMarkdownPart)
    fun push(part: ChatResponseFileTreePart)
    fun push(part: ChatResponseAnchorPart)
    fun push(part: ChatResponseProgressPart)
    fun push(part: ChatResponseReferencePart)
    fun push(part: ChatResponseCommandButtonPart)
}

external open class ChatResponseMarkdownPart {
    open var value: MarkdownString

    constructor(value: String)
    constructor(value: MarkdownString)
}

external interface ChatResponseFileTree {
    var name: String
    var children: Array<ChatResponseFileTree>?
        get() = definedExternally
        set(value) = definedExternally
}

external open class ChatResponseFileTreePart(value: Array<ChatResponseFileTree>, baseUri: Uri) {
    open var value: Array<ChatResponseFileTree>
    open var baseUri: Uri
}

external open class ChatResponseAnchorPart {
    open var value: dynamic /* Uri | Location */
    open var title: String

    constructor(value: Uri, title: String = definedExternally)
    constructor(value: Uri)
    constructor(value: Location, title: String = definedExternally)
    constructor(value: Location)
}

external open class ChatResponseProgressPart(value: String) {
    open var value: String
}

external open class ChatResponseReferencePart {
    open var value: dynamic /* Uri | Location */
    open var iconPath: dynamic /* Uri | ThemeIcon | `T$7` */

    constructor(value: Uri, iconPath: Uri = definedExternally)
    constructor(value: Uri)
    constructor(value: Uri, iconPath: ThemeIcon = definedExternally)
    constructor(value: Uri, iconPath: `T$7` = definedExternally)
    constructor(value: Location, iconPath: Uri = definedExternally)
    constructor(value: Location)
    constructor(value: Location, iconPath: ThemeIcon = definedExternally)
    constructor(value: Location, iconPath: `T$7` = definedExternally)
}

external open class ChatResponseCommandButtonPart(value: Command) {
    open var value: Command
}

external enum class LanguageModelChatMessageRole {
    User /* = 1 */,
    Assistant /* = 2 */
}

external open class LanguageModelChatMessage {
    open var role: LanguageModelChatMessageRole
    open var content: Array<dynamic /* LanguageModelTextPart | LanguageModelToolResultPart | LanguageModelToolCallPart */>
    open var name: String?

    constructor(role: LanguageModelChatMessageRole, content: String, name: String = definedExternally)
    constructor(role: LanguageModelChatMessageRole, content: String)
    constructor(
        role: LanguageModelChatMessageRole,
        content: Array<Any /* LanguageModelTextPart | LanguageModelToolResultPart | LanguageModelToolCallPart */>,
        name: String = definedExternally
    )

    constructor(
        role: LanguageModelChatMessageRole,
        content: Array<Any /* LanguageModelTextPart | LanguageModelToolResultPart | LanguageModelToolCallPart */>
    )

    companion object {
        fun User(content: String, name: String = definedExternally): LanguageModelChatMessage
        fun User(
            content: Array<Any /* LanguageModelTextPart | LanguageModelToolResultPart */>,
            name: String = definedExternally
        ): LanguageModelChatMessage

        fun Assistant(content: String, name: String = definedExternally): LanguageModelChatMessage
        fun Assistant(
            content: Array<Any /* LanguageModelTextPart | LanguageModelToolCallPart */>,
            name: String = definedExternally
        ): LanguageModelChatMessage
    }
}

external interface LanguageModelChatResponse {
    var stream: Any
    var text: Any
}

external interface LanguageModelChat {
    val name: String
    val id: String
    val vendor: String
    val family: String
    val version: String
    val maxInputTokens: Number
    fun sendRequest(
        messages: Array<LanguageModelChatMessage>,
        options: LanguageModelChatRequestOptions = definedExternally,
        token: CancellationToken = definedExternally
    ): Thenable<LanguageModelChatResponse>

    fun countTokens(text: String, token: CancellationToken = definedExternally): Thenable<Number>
    fun countTokens(text: String): Thenable<Number>
    fun countTokens(text: LanguageModelChatMessage, token: CancellationToken = definedExternally): Thenable<Number>
    fun countTokens(text: LanguageModelChatMessage): Thenable<Number>
}

external interface LanguageModelChatSelector {
    var vendor: String?
        get() = definedExternally
        set(value) = definedExternally
    var family: String?
        get() = definedExternally
        set(value) = definedExternally
    var version: String?
        get() = definedExternally
        set(value) = definedExternally
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
}

typealias LanguageModelError = Error

external interface LanguageModelChatRequestOptions {
    var justification: String?
        get() = definedExternally
        set(value) = definedExternally
    var modelOptions: Json?
        get() = definedExternally
        set(value) = definedExternally
    var tools: Array<LanguageModelChatTool>?
        get() = definedExternally
        set(value) = definedExternally
    var toolMode: LanguageModelChatToolMode?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LanguageModelAccessInformation {
    var onDidChange: Event<Unit>
    fun canSendRequest(chat: LanguageModelChat): Boolean?
}

external interface LanguageModelChatTool {
    var name: String
    var description: String
    var inputSchema: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class LanguageModelChatToolMode {
    Auto /* = 1 */,
    Required /* = 2 */
}

external open class LanguageModelToolCallPart(callId: String, name: String, input: Any?) {
    open var callId: String
    open var name: String
    open var input: Any?
}

external open class LanguageModelToolResultPart(
    callId: String,
    content: Array<Any /* LanguageModelTextPart | LanguageModelPromptTsxPart | Any */>
) {
    open var callId: String
    open var content: Array<dynamic /* LanguageModelTextPart | LanguageModelPromptTsxPart | Any */>
}

external open class LanguageModelTextPart(value: String) {
    open var value: String
}

external open class LanguageModelPromptTsxPart(value: Any) {
    open var value: Any
}

external open class LanguageModelToolResult(content: Array<Any /* LanguageModelTextPart | LanguageModelPromptTsxPart */>) {
    open var content: Array<dynamic /* LanguageModelTextPart | LanguageModelPromptTsxPart | Any */>
}

typealias ChatParticipantToolToken = Any

external interface LanguageModelToolInvocationOptions<T> {
    var toolInvocationToken: ChatParticipantToolToken?
    var input: T
    var tokenizationOptions: LanguageModelToolTokenizationOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LanguageModelToolTokenizationOptions {
    var tokenBudget: Number
    fun countTokens(text: String, token: CancellationToken = definedExternally): Thenable<Number>
}

external interface LanguageModelToolInformation {
    val name: String
    val description: String
    val inputSchema: Any?
    val tags: Array<String>
}

external interface LanguageModelToolInvocationPrepareOptions<T> {
    var input: T
}

external interface LanguageModelTool<T> {
    fun invoke(
        options: LanguageModelToolInvocationOptions<T>,
        token: CancellationToken
    ): dynamic /* LanguageModelToolResult? | Thenable<LanguageModelToolResult?>? */

    val prepareInvocation: ((options: LanguageModelToolInvocationPrepareOptions<T>, token: CancellationToken) -> dynamic)?
}

external interface LanguageModelToolConfirmationMessages {
    var title: String
    var message: dynamic /* String | MarkdownString */
        get() = definedExternally
        set(value) = definedExternally
}

external interface PreparedToolInvocation {
    var invocationMessage: String?
        get() = definedExternally
        set(value) = definedExternally
    var confirmationMessages: LanguageModelToolConfirmationMessages?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ChatLanguageModelToolReference {
    val name: String
    val range: dynamic /* JsTuple<start, Number, end, Number> */
        get() = definedExternally
}

external interface Thenable<T> : PromiseLike<T>
