@file:JsModule("vscode")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode

import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import tsstdlib.Readonly
import kotlin.js.Json
import kotlin.js.RegExp
import kotlin.js.collections.JsReadonlyArray

external var version: String

external interface Command {
    var title: String
    var command: String
    var tooltip: String?
        get() = definedExternally
        set(value) = definedExternally
    var arguments: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TextLine {
    val lineNumber: Number
    val text: String
    val range: Range
    val rangeIncludingLineBreak: Range
    val firstNonWhitespaceCharacterIndex: Number
    val isEmptyOrWhitespace: Boolean
}

external interface TextDocument {
    val uri: Uri
    val fileName: String
    val isUntitled: Boolean
    val languageId: String
    val version: Number
    val isDirty: Boolean
    val isClosed: Boolean
    fun save(): Thenable<Boolean>
    val eol: EndOfLine
    val lineCount: Number
    fun lineAt(line: Number): TextLine
    fun lineAt(position: Position): TextLine
    fun offsetAt(position: Position): Number
    fun positionAt(offset: Number): Position
    fun getText(range: Range = definedExternally): String
    fun getWordRangeAtPosition(position: Position, regex: RegExp = definedExternally): Range?
    fun validateRange(range: Range): Range
    fun validatePosition(position: Position): Position
}

external interface `T$0` {
    var lineDelta: Number?
        get() = definedExternally
        set(value) = definedExternally
    var characterDelta: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$1` {
    var line: Number?
        get() = definedExternally
        set(value) = definedExternally
    var character: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Position(line: Number, character: Number) {
    open val line: Number
    open val character: Number
    open fun isBefore(other: Position): Boolean
    open fun isBeforeOrEqual(other: Position): Boolean
    open fun isAfter(other: Position): Boolean
    open fun isAfterOrEqual(other: Position): Boolean
    open fun isEqual(other: Position): Boolean
    open fun compareTo(other: Position): Number
    open fun translate(lineDelta: Number = definedExternally, characterDelta: Number = definedExternally): Position
    open fun translate(): Position
    open fun translate(lineDelta: Number = definedExternally): Position
    open fun translate(change: `T$0`): Position
    open fun with(line: Number = definedExternally, character: Number = definedExternally): Position
    open fun with(): Position
    open fun with(line: Number = definedExternally): Position
    open fun with(change: `T$1`): Position
}

external interface `T$2` {
    var start: Position?
        get() = definedExternally
        set(value) = definedExternally
    var end: Position?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Range {
    open val start: Position
    open val end: Position

    constructor(start: Position, end: Position)
    constructor(startLine: Number, startCharacter: Number, endLine: Number, endCharacter: Number)

    open var isEmpty: Boolean
    open var isSingleLine: Boolean
    open fun contains(positionOrRange: Position): Boolean
    open fun contains(positionOrRange: Range): Boolean
    open fun isEqual(other: Range): Boolean
    open fun intersection(range: Range): Range?
    open fun union(other: Range): Range
    open fun with(start: Position = definedExternally, end: Position = definedExternally): Range
    open fun with(): Range
    open fun with(start: Position = definedExternally): Range
    open fun with(change: `T$2`): Range
}

external open class Selection : Range {
    constructor(anchor: Position, active: Position)
    constructor(anchorLine: Number, anchorCharacter: Number, activeLine: Number, activeCharacter: Number)

    open var anchor: Position
    open var active: Position
    open var isReversed: Boolean
}

external enum class TextEditorSelectionChangeKind {
    Keyboard /* = 1 */,
    Mouse /* = 2 */,
    Command /* = 3 */
}

external interface TextEditorSelectionChangeEvent {
    val textEditor: TextEditor
    val selections: Array<Selection>
    val kind: TextEditorSelectionChangeKind?
}

external interface TextEditorVisibleRangesChangeEvent {
    val textEditor: TextEditor
    val visibleRanges: Array<Range>
}

external interface TextEditorOptionsChangeEvent {
    val textEditor: TextEditor
    val options: TextEditorOptions
}

external interface TextEditorViewColumnChangeEvent {
    val textEditor: TextEditor
    val viewColumn: ViewColumn
}

external enum class TextEditorCursorStyle {
    Line /* = 1 */,
    Block /* = 2 */,
    Underline /* = 3 */,
    LineThin /* = 4 */,
    BlockOutline /* = 5 */,
    UnderlineThin /* = 6 */
}

external enum class TextEditorLineNumbersStyle {
    Off /* = 0 */,
    On /* = 1 */,
    Relative /* = 2 */,
    Interval /* = 3 */
}

external interface TextEditorOptions {
    var tabSize: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var indentSize: dynamic /* Number? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var insertSpaces: dynamic /* Boolean? | String? */
        get() = definedExternally
        set(value) = definedExternally
    var cursorStyle: TextEditorCursorStyle?
        get() = definedExternally
        set(value) = definedExternally
    var lineNumbers: TextEditorLineNumbersStyle?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TextEditorDecorationType {
    val key: String
    fun dispose()
}

external enum class TextEditorRevealType {
    Default /* = 0 */,
    InCenter /* = 1 */,
    InCenterIfOutsideViewport /* = 2 */,
    AtTop /* = 3 */
}

external enum class OverviewRulerLane {
    Left /* = 1 */,
    Center /* = 2 */,
    Right /* = 4 */,
    Full /* = 7 */
}

external enum class DecorationRangeBehavior {
    OpenOpen /* = 0 */,
    ClosedClosed /* = 1 */,
    OpenClosed /* = 2 */,
    ClosedOpen /* = 3 */
}

external interface TextDocumentShowOptions {
    var viewColumn: ViewColumn?
        get() = definedExternally
        set(value) = definedExternally
    var preserveFocus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var preview: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var selection: Range?
        get() = definedExternally
        set(value) = definedExternally
}

external interface NotebookEditorSelectionChangeEvent {
    val notebookEditor: NotebookEditor
    val selections: Array<NotebookRange>
}

external interface NotebookEditorVisibleRangesChangeEvent {
    val notebookEditor: NotebookEditor
    val visibleRanges: Array<NotebookRange>
}

external interface NotebookDocumentShowOptions {
    val viewColumn: ViewColumn?
        get() = definedExternally
    val preserveFocus: Boolean?
        get() = definedExternally
    val preview: Boolean?
        get() = definedExternally
    val selections: Array<NotebookRange>?
        get() = definedExternally
}

external open class ThemeColor(id: String) {
    open val id: String
}

external open class ThemeIcon(id: String, color: ThemeColor = definedExternally) {
    open val id: String
    open val color: ThemeColor?

    companion object {
        val File: ThemeIcon
        val Folder: ThemeIcon
    }
}

external interface ThemableDecorationRenderOptions {
    var backgroundColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var outline: String?
        get() = definedExternally
        set(value) = definedExternally
    var outlineColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var outlineStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var outlineWidth: String?
        get() = definedExternally
        set(value) = definedExternally
    var border: String?
        get() = definedExternally
        set(value) = definedExternally
    var borderColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var borderRadius: String?
        get() = definedExternally
        set(value) = definedExternally
    var borderSpacing: String?
        get() = definedExternally
        set(value) = definedExternally
    var borderStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var borderWidth: String?
        get() = definedExternally
        set(value) = definedExternally
    var fontStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var fontWeight: String?
        get() = definedExternally
        set(value) = definedExternally
    var textDecoration: String?
        get() = definedExternally
        set(value) = definedExternally
    var cursor: String?
        get() = definedExternally
        set(value) = definedExternally
    var color: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var opacity: String?
        get() = definedExternally
        set(value) = definedExternally
    var letterSpacing: String?
        get() = definedExternally
        set(value) = definedExternally
    var gutterIconPath: dynamic /* String? | Uri? */
        get() = definedExternally
        set(value) = definedExternally
    var gutterIconSize: String?
        get() = definedExternally
        set(value) = definedExternally
    var overviewRulerColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var before: ThemableDecorationAttachmentRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
    var after: ThemableDecorationAttachmentRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ThemableDecorationAttachmentRenderOptions {
    var contentText: String?
        get() = definedExternally
        set(value) = definedExternally
    var contentIconPath: dynamic /* String? | Uri? */
        get() = definedExternally
        set(value) = definedExternally
    var border: String?
        get() = definedExternally
        set(value) = definedExternally
    var borderColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var fontStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var fontWeight: String?
        get() = definedExternally
        set(value) = definedExternally
    var textDecoration: String?
        get() = definedExternally
        set(value) = definedExternally
    var color: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var backgroundColor: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var margin: String?
        get() = definedExternally
        set(value) = definedExternally
    var width: String?
        get() = definedExternally
        set(value) = definedExternally
    var height: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DecorationRenderOptions : ThemableDecorationRenderOptions {
    var isWholeLine: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var rangeBehavior: DecorationRangeBehavior?
        get() = definedExternally
        set(value) = definedExternally
    var overviewRulerLane: OverviewRulerLane?
        get() = definedExternally
        set(value) = definedExternally
    var light: ThemableDecorationRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
    var dark: ThemableDecorationRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DecorationOptions {
    var range: Range
    var hoverMessage: dynamic /* MarkdownString? | String? | `T$13`? | Array<dynamic /* MarkdownString | String | `T$13` */>? */
        get() = definedExternally
        set(value) = definedExternally
    var renderOptions: DecorationInstanceRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ThemableDecorationInstanceRenderOptions {
    var before: ThemableDecorationAttachmentRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
    var after: ThemableDecorationAttachmentRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DecorationInstanceRenderOptions : ThemableDecorationInstanceRenderOptions {
    var light: ThemableDecorationInstanceRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
    var dark: ThemableDecorationInstanceRenderOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$3` {
    val undoStopBefore: Boolean
    val undoStopAfter: Boolean
}

external interface TextEditor {
    val document: TextDocument
    var selection: Selection
    var selections: Array<Selection>
    val visibleRanges: Array<Range>
    var options: TextEditorOptions
    val viewColumn: ViewColumn?
    fun edit(callback: (editBuilder: TextEditorEdit) -> Unit, options: `T$3` = definedExternally): Thenable<Boolean>
    fun insertSnippet(
        snippet: SnippetString,
        location: Position = definedExternally,
        options: `T$3` = definedExternally
    ): Thenable<Boolean>

    fun insertSnippet(snippet: SnippetString): Thenable<Boolean>
    fun insertSnippet(snippet: SnippetString, location: Position = definedExternally): Thenable<Boolean>
    fun insertSnippet(
        snippet: SnippetString,
        location: Range = definedExternally,
        options: `T$3` = definedExternally
    ): Thenable<Boolean>

    fun insertSnippet(snippet: SnippetString, location: Range = definedExternally): Thenable<Boolean>
    fun insertSnippet(
        snippet: SnippetString,
        location: Array<Position> = definedExternally,
        options: `T$3` = definedExternally
    ): Thenable<Boolean>

    fun insertSnippet(snippet: SnippetString, location: Array<Position> = definedExternally): Thenable<Boolean>
    fun insertSnippet(
        snippet: SnippetString,
        location: Array<Range> = definedExternally,
        options: `T$3` = definedExternally
    ): Thenable<Boolean>

    fun insertSnippet(snippet: SnippetString, location: Array<Range> = definedExternally): Thenable<Boolean>
    fun setDecorations(decorationType: TextEditorDecorationType, rangesOrOptions: Array<Range>)
    fun setDecorations(decorationType: TextEditorDecorationType, rangesOrOptions: Array<DecorationOptions>)
    fun revealRange(range: Range, revealType: TextEditorRevealType = definedExternally)
    fun show(column: ViewColumn = definedExternally)
    fun hide()
}

external enum class EndOfLine {
    LF /* = 1 */,
    CRLF /* = 2 */
}

external interface TextEditorEdit {
    fun replace(location: Position, value: String)
    fun replace(location: Range, value: String)
    fun replace(location: Selection, value: String)
    fun insert(location: Position, value: String)
    fun delete(location: Range)
    fun delete(location: Selection)
    fun setEndOfLine(endOfLine: EndOfLine)
}

external interface `T$4` {
    val scheme: String
    val authority: String?
        get() = definedExternally
    val path: String?
        get() = definedExternally
    val query: String?
        get() = definedExternally
    val fragment: String?
        get() = definedExternally
}

external interface `T$5` {
    var scheme: String?
        get() = definedExternally
        set(value) = definedExternally
    var authority: String?
        get() = definedExternally
        set(value) = definedExternally
    var path: String?
        get() = definedExternally
        set(value) = definedExternally
    var query: String?
        get() = definedExternally
        set(value) = definedExternally
    var fragment: String?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Uri(scheme: String, authority: String, path: String, query: String, fragment: String) {
    open val scheme: String
    open val authority: String
    open val path: String
    open val query: String
    open val fragment: String
    open val fsPath: String
    open fun with(change: `T$5`): Uri
    open fun toString(skipEncoding: Boolean = definedExternally): String
    open fun toJSON(): Any

    companion object {
        fun parse(value: String, strict: Boolean = definedExternally): Uri
        fun file(path: String): Uri
        fun joinPath(base: Uri, vararg pathSegments: String): Uri
        fun from(components: `T$4`): Uri
    }
}

external interface CancellationToken {
    var isCancellationRequested: Boolean
    var onCancellationRequested: Event<Any>
}

external open class CancellationTokenSource {
    open var token: CancellationToken
    open fun cancel()
    open fun dispose()
}

external interface `T$6` {
    var dispose: () -> Any
}

external open class Disposable(callOnDispose: () -> Any) {
    open fun dispose(): Any

    companion object {
        fun from(vararg disposableLikes: `T$6`): Disposable
    }
}

external interface Event<T> {
    @nativeInvoke
    operator fun invoke(
        listener: (e: T) -> Any,
        thisArgs: Any = definedExternally,
        disposables: Array<Disposable> = definedExternally
    ): Disposable
}

external open class EventEmitter<T> {
    open var event: Event<T>
    open fun fire(data: T)
    open fun dispose()
}

external interface FileSystemWatcher : Disposable {
    val ignoreCreateEvents: Boolean
    val ignoreChangeEvents: Boolean
    val ignoreDeleteEvents: Boolean
    val onDidCreate: Event<Uri>
    val onDidChange: Event<Uri>
    val onDidDelete: Event<Uri>
}

external interface TextDocumentContentProvider {
    var onDidChange: Event<Uri>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideTextDocumentContent(uri: Uri, token: CancellationToken): dynamic /* String? | Thenable<String?>? */
}

external enum class QuickPickItemKind {
    Separator /* = -1 */,
    Default /* = 0 */
}

external interface `T$7` {
    var light: Uri
    var dark: Uri
}

external interface QuickPickItem {
    var label: String
    var kind: QuickPickItemKind?
        get() = definedExternally
        set(value) = definedExternally
    var iconPath: dynamic /* Uri? | `T$7`? | ThemeIcon? */
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
    var picked: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var alwaysShow: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var buttons: Array<QuickInputButton>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `L$0` {
    @nativeInvoke
    operator fun invoke(item: QuickPickItem): Any

    @nativeInvoke
    operator fun invoke(item: String): Any
}

external interface QuickPickOptions {
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var matchOnDescription: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var matchOnDetail: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var placeHolder: String?
        get() = definedExternally
        set(value) = definedExternally
    var ignoreFocusOut: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var canPickMany: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    val onDidSelectItem: `L$0`?
        get() = definedExternally
}

external interface WorkspaceFolderPickOptions {
    var placeHolder: String?
        get() = definedExternally
        set(value) = definedExternally
    var ignoreFocusOut: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$8` {
    @nativeGetter
    operator fun get(name: String): Array<String>?

    @nativeSetter
    operator fun set(name: String, value: Array<String>)
}

external interface OpenDialogOptions {
    var defaultUri: Uri?
        get() = definedExternally
        set(value) = definedExternally
    var openLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var canSelectFiles: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var canSelectFolders: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var canSelectMany: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var filters: `T$8`?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$9` {
    @nativeGetter
    operator fun get(name: String): Array<String>?

    @nativeSetter
    operator fun set(name: String, value: Array<String>)
}

external interface SaveDialogOptions {
    var defaultUri: Uri?
        get() = definedExternally
        set(value) = definedExternally
    var saveLabel: String?
        get() = definedExternally
        set(value) = definedExternally
    var filters: `T$9`?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MessageItem {
    var title: String
    var isCloseAffordance: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MessageOptions {
    var modal: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class InputBoxValidationSeverity {
    Info /* = 1 */,
    Warning /* = 2 */,
    Error /* = 3 */
}

external interface InputBoxValidationMessage {
    val message: String
    val severity: InputBoxValidationSeverity
}

external interface InputBoxOptions {
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var value: String?
        get() = definedExternally
        set(value) = definedExternally
    var valueSelection: dynamic /* JsTuple<Number, Number> */
        get() = definedExternally
        set(value) = definedExternally
    var prompt: String?
        get() = definedExternally
        set(value) = definedExternally
    var placeHolder: String?
        get() = definedExternally
        set(value) = definedExternally
    var password: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var ignoreFocusOut: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    val validateInput: ((value: String) -> dynamic)?
}

external open class RelativePattern {
    open var baseUri: Uri
    open var base: String
    open var pattern: String

    constructor(base: WorkspaceFolder, pattern: String)
    constructor(base: Uri, pattern: String)
    constructor(base: String, pattern: String)
}

external interface DocumentFilter {
    val language: String?
        get() = definedExternally
    val notebookType: String?
        get() = definedExternally
    val scheme: String?
        get() = definedExternally
    val pattern: dynamic /* String? | RelativePattern? */
        get() = definedExternally
}

external open class CodeActionKind(value: String) {
    open val value: String
    open fun append(parts: String): CodeActionKind
    open fun intersects(other: CodeActionKind): Boolean
    open fun contains(other: CodeActionKind): Boolean

    companion object {
        val Empty: CodeActionKind
        val QuickFix: CodeActionKind
        val Refactor: CodeActionKind
        val RefactorExtract: CodeActionKind
        val RefactorInline: CodeActionKind
        val RefactorMove: CodeActionKind
        val RefactorRewrite: CodeActionKind
        val Source: CodeActionKind
        val SourceOrganizeImports: CodeActionKind
        val SourceFixAll: CodeActionKind
        val Notebook: CodeActionKind
    }
}

external enum class CodeActionTriggerKind {
    Invoke /* = 1 */,
    Automatic /* = 2 */
}

external interface CodeActionContext {
    val triggerKind: CodeActionTriggerKind
    val diagnostics: Array<Diagnostic>
    val only: CodeActionKind?
}

external interface `T$10` {
    val reason: String
}

external open class CodeAction(title: String, kind: CodeActionKind = definedExternally) {
    open var title: String
    open var edit: WorkspaceEdit
    open var diagnostics: Array<Diagnostic>
    open var command: Command
    open var kind: CodeActionKind
    open var isPreferred: Boolean
    open var disabled: `T$10`
}

external interface CodeActionProvider<T : CodeAction> {
    fun provideCodeActions(
        document: TextDocument,
        range: Range,
        context: CodeActionContext,
        token: CancellationToken
    ): dynamic /* Array<dynamic /* Command | T */>? | Thenable<Array<dynamic /* Command | T */>?>? */

    fun provideCodeActions(
        document: TextDocument,
        range: Selection,
        context: CodeActionContext,
        token: CancellationToken
    ): dynamic /* Array<dynamic /* Command | T */>? | Thenable<Array<dynamic /* Command | T */>?>? */

    val resolveCodeAction: ((codeAction: T, token: CancellationToken) -> dynamic)?
}

external interface CodeActionProvider__0 : CodeActionProvider<CodeAction>

external interface `T$11` {
    val kind: CodeActionKind
    val command: Command
}

external interface CodeActionProviderMetadata {
    val providedCodeActionKinds: Array<CodeActionKind>?
        get() = definedExternally
    val documentation: Array<`T$11`>?
        get() = definedExternally
}

external open class CodeLens(range: Range, command: Command = definedExternally) {
    open var range: Range
    open var command: Command
    open val isResolved: Boolean
}

external interface CodeLensProvider<T : CodeLens> {
    var onDidChangeCodeLenses: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideCodeLenses(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* Array<T>? | Thenable<Array<T>?>? */

    val resolveCodeLens: ((codeLens: T, token: CancellationToken) -> dynamic)?
}

external interface CodeLensProvider__0 : CodeLensProvider<CodeLens>

external interface DefinitionProvider {
    fun provideDefinition(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? | Thenable<dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? */>? */
}

external interface ImplementationProvider {
    fun provideImplementation(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? | Thenable<dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? */>? */
}

external interface TypeDefinitionProvider {
    fun provideTypeDefinition(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? | Thenable<dynamic /* Location? | Array<Location>? | Array<DefinitionLink>? */>? */
}

external interface DeclarationProvider {
    fun provideDeclaration(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Location? | Array<Location>? | Array<LocationLink>? | Thenable<dynamic /* Location? | Array<Location>? | Array<LocationLink>? */>? */
}

external interface `T$12` {
    val enabledCommands: Array<String>
}

external open class MarkdownString(value: String = definedExternally, supportThemeIcons: Boolean = definedExternally) {
    open var value: String
    open var isTrusted: dynamic /* Boolean | `T$12` */
    open var supportThemeIcons: Boolean
    open var supportHtml: Boolean
    open var baseUri: Uri
    open fun appendText(value: String): MarkdownString
    open fun appendMarkdown(value: String): MarkdownString
    open fun appendCodeblock(value: String, language: String = definedExternally): MarkdownString
}

external interface `T$13` {
    var language: String
    var value: String
}

external open class Hover {
    open var contents: Array<dynamic /* MarkdownString | String | `T$13` */>
    open var range: Range

    constructor(contents: MarkdownString, range: Range = definedExternally)
    constructor(contents: MarkdownString)
    constructor(contents: String, range: Range = definedExternally)
    constructor(contents: String)
    constructor(contents: `T$13`, range: Range = definedExternally)
    constructor(contents: `T$13`)
    constructor(contents: Array<Any /* MarkdownString | String | `T$13` */>, range: Range = definedExternally)
    constructor(contents: Array<Any /* MarkdownString | String | `T$13` */>)
}

external interface HoverProvider {
    fun provideHover(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Hover? | Thenable<Hover?>? */
}

external open class EvaluatableExpression(range: Range, expression: String = definedExternally) {
    open val range: Range
    open val expression: String?
}

external interface EvaluatableExpressionProvider {
    fun provideEvaluatableExpression(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* EvaluatableExpression? | Thenable<EvaluatableExpression?>? */
}

external open class InlineValueText(range: Range, text: String) {
    open val range: Range
    open val text: String
}

external open class InlineValueVariableLookup(
    range: Range,
    variableName: String = definedExternally,
    caseSensitiveLookup: Boolean = definedExternally
) {
    open val range: Range
    open val variableName: String?
    open val caseSensitiveLookup: Boolean
}

external open class InlineValueEvaluatableExpression(range: Range, expression: String = definedExternally) {
    open val range: Range
    open val expression: String?
}

external interface InlineValueContext {
    val frameId: Number
    val stoppedLocation: Range
}

external interface InlineValuesProvider {
    var onDidChangeInlineValues: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideInlineValues(
        document: TextDocument,
        viewPort: Range,
        context: InlineValueContext,
        token: CancellationToken
    ): dynamic /* Array<dynamic /* InlineValueText | InlineValueVariableLookup | InlineValueEvaluatableExpression */>? | Thenable<Array<dynamic /* InlineValueText | InlineValueVariableLookup | InlineValueEvaluatableExpression */>?>? */
}

external enum class DocumentHighlightKind {
    Text /* = 0 */,
    Read /* = 1 */,
    Write /* = 2 */
}

external open class DocumentHighlight(range: Range, kind: DocumentHighlightKind = definedExternally) {
    open var range: Range
    open var kind: DocumentHighlightKind
}

external interface DocumentHighlightProvider {
    fun provideDocumentHighlights(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* Array<DocumentHighlight>? | Thenable<Array<DocumentHighlight>?>? */
}

external enum class SymbolKind {
    File /* = 0 */,
    Module /* = 1 */,
    Namespace /* = 2 */,
    Package /* = 3 */,
    Class /* = 4 */,
    Method /* = 5 */,
    Property /* = 6 */,
    Field /* = 7 */,
    Constructor /* = 8 */,
    Enum /* = 9 */,
    Interface /* = 10 */,
    Function /* = 11 */,
    Variable /* = 12 */,
    Constant /* = 13 */,
    String /* = 14 */,
    Number /* = 15 */,
    Boolean /* = 16 */,
    Array /* = 17 */,
    Object /* = 18 */,
    Key /* = 19 */,
    Null /* = 20 */,
    EnumMember /* = 21 */,
    Struct /* = 22 */,
    Event /* = 23 */,
    Operator /* = 24 */,
    TypeParameter /* = 25 */
}

external enum class SymbolTag {
    Deprecated /* = 1 */
}

external open class SymbolInformation {
    open var name: String
    open var containerName: String
    open var kind: SymbolKind
    open var tags: Array<SymbolTag>
    open var location: Location

    constructor(name: String, kind: SymbolKind, containerName: String, location: Location)
    constructor(
        name: String,
        kind: SymbolKind,
        range: Range,
        uri: Uri = definedExternally,
        containerName: String = definedExternally
    )

    constructor(name: String, kind: SymbolKind, range: Range)
    constructor(name: String, kind: SymbolKind, range: Range, uri: Uri = definedExternally)
}

external open class DocumentSymbol(
    name: String,
    detail: String,
    kind: SymbolKind,
    range: Range,
    selectionRange: Range
) {
    open var name: String
    open var detail: String
    open var kind: SymbolKind
    open var tags: Array<SymbolTag>
    open var range: Range
    open var selectionRange: Range
    open var children: Array<DocumentSymbol>
}

external interface DocumentSymbolProvider {
    fun provideDocumentSymbols(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* Array<SymbolInformation>? | Array<DocumentSymbol>? | Thenable<dynamic /* Array<SymbolInformation>? | Array<DocumentSymbol>? */>? */
}

external interface DocumentSymbolProviderMetadata {
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface WorkspaceSymbolProvider<T : SymbolInformation> {
    fun provideWorkspaceSymbols(query: String, token: CancellationToken): dynamic /* Array<T>? | Thenable<Array<T>?>? */
    val resolveWorkspaceSymbol: ((symbol: T, token: CancellationToken) -> dynamic)?
}

external interface WorkspaceSymbolProvider__0 : WorkspaceSymbolProvider<SymbolInformation>

external interface ReferenceContext {
    val includeDeclaration: Boolean
}

external interface ReferenceProvider {
    fun provideReferences(
        document: TextDocument,
        position: Position,
        context: ReferenceContext,
        token: CancellationToken
    ): dynamic /* Array<Location>? | Thenable<Array<Location>?>? */
}

external open class TextEdit(range: Range, newText: String) {
    open var range: Range
    open var newText: String
    open var newEol: EndOfLine

    companion object {
        fun replace(range: Range, newText: String): TextEdit
        fun insert(position: Position, newText: String): TextEdit
        fun delete(range: Range): TextEdit
        fun setEndOfLine(eol: EndOfLine): TextEdit
    }
}

external open class SnippetTextEdit(range: Range, snippet: SnippetString) {
    open var range: Range
    open var snippet: SnippetString

    companion object {
        fun replace(range: Range, snippet: SnippetString): SnippetTextEdit
        fun insert(position: Position, snippet: SnippetString): SnippetTextEdit
    }
}

external open class NotebookEdit(range: NotebookRange, newCells: Array<NotebookCellData>) {
    open var range: NotebookRange
    open var newCells: Array<NotebookCellData>
    open var newCellMetadata: Json
    open var newNotebookMetadata: Json

    companion object {
        fun replaceCells(range: NotebookRange, newCells: Array<NotebookCellData>): NotebookEdit
        fun insertCells(index: Number, newCells: Array<NotebookCellData>): NotebookEdit
        fun deleteCells(range: NotebookRange): NotebookEdit
        fun updateCellMetadata(index: Number, newCellMetadata: Json): NotebookEdit
        fun updateNotebookMetadata(newNotebookMetadata: Json): NotebookEdit
    }
}

external interface WorkspaceEditEntryMetadata {
    var needsConfirmation: Boolean
    var label: String
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var iconPath: dynamic /* Uri? | `T$7`? | ThemeIcon? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface WorkspaceEditMetadata {
    var isRefactoring: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$14` {
    val overwrite: Boolean?
        get() = definedExternally
    val ignoreIfExists: Boolean?
        get() = definedExternally
    val contents: dynamic /* Uint8Array? | DataTransferFile? */
        get() = definedExternally
}

external interface `T$15` {
    val recursive: Boolean?
        get() = definedExternally
    val ignoreIfNotExists: Boolean?
        get() = definedExternally
}

external interface `T$16` {
    val overwrite: Boolean?
        get() = definedExternally
    val ignoreIfExists: Boolean?
        get() = definedExternally
}

external open class WorkspaceEdit {
    open val size: Number
    open fun replace(uri: Uri, range: Range, newText: String, metadata: WorkspaceEditEntryMetadata = definedExternally)
    open fun insert(
        uri: Uri,
        position: Position,
        newText: String,
        metadata: WorkspaceEditEntryMetadata = definedExternally
    )

    open fun delete(uri: Uri, range: Range, metadata: WorkspaceEditEntryMetadata = definedExternally)
    open fun has(uri: Uri): Boolean
    open fun set(
        uri: Uri,
        edits: Array<Any /* TextEdit | SnippetTextEdit | JsTuple<Any, WorkspaceEditEntryMetadata?> | JsTuple<NotebookEdit, WorkspaceEditEntryMetadata?> */>
    )

    open fun set(uri: Uri, edits: Array<NotebookEdit>)
    open fun get(uri: Uri): Array<TextEdit>
    open fun createFile(
        uri: Uri,
        options: `T$14` = definedExternally,
        metadata: WorkspaceEditEntryMetadata = definedExternally
    )

    open fun deleteFile(
        uri: Uri,
        options: `T$15` = definedExternally,
        metadata: WorkspaceEditEntryMetadata = definedExternally
    )

    open fun renameFile(
        oldUri: Uri,
        newUri: Uri,
        options: `T$16` = definedExternally,
        metadata: WorkspaceEditEntryMetadata = definedExternally
    )

    open fun entries(): Array<dynamic /* JsTuple<Uri, Array<TextEdit>> */>
}

external open class SnippetString(value: String = definedExternally) {
    open var value: String
    open fun appendText(string: String): SnippetString
    open fun appendTabstop(number: Number = definedExternally): SnippetString
    open fun appendPlaceholder(value: String, number: Number = definedExternally): SnippetString
    open fun appendPlaceholder(value: String): SnippetString
    open fun appendPlaceholder(
        value: (snippet: SnippetString) -> Any,
        number: Number = definedExternally
    ): SnippetString

    open fun appendPlaceholder(value: (snippet: SnippetString) -> Any): SnippetString
    open fun appendChoice(values: Array<String>, number: Number = definedExternally): SnippetString
    open fun appendVariable(name: String, defaultValue: String): SnippetString
    open fun appendVariable(name: String, defaultValue: (snippet: SnippetString) -> Any): SnippetString
}

external interface `T$17` {
    var range: Range
    var placeholder: String
}

external interface RenameProvider {
    fun provideRenameEdits(
        document: TextDocument,
        position: Position,
        newName: String,
        token: CancellationToken
    ): dynamic /* WorkspaceEdit? | Thenable<WorkspaceEdit?>? */

    val prepareRename: ((document: TextDocument, position: Position, token: CancellationToken) -> dynamic)?
}

external open class SemanticTokensLegend(tokenTypes: Array<String>, tokenModifiers: Array<String> = definedExternally) {
    open val tokenTypes: Array<String>
    open val tokenModifiers: Array<String>
}

external open class SemanticTokensBuilder(legend: SemanticTokensLegend = definedExternally) {
    open fun push(
        line: Number,
        char: Number,
        length: Number,
        tokenType: Number,
        tokenModifiers: Number = definedExternally
    )

    open fun push(line: Number, char: Number, length: Number, tokenType: Number)
    open fun push(range: Range, tokenType: String, tokenModifiers: Array<String> = definedExternally)
    open fun push(range: Range, tokenType: String)
    open fun build(resultId: String = definedExternally): SemanticTokens
}

external open class SemanticTokens(data: Uint32Array, resultId: String = definedExternally) {
    open val resultId: String?
    open val data: Uint32Array
}

external open class SemanticTokensEdits(edits: Array<SemanticTokensEdit>, resultId: String = definedExternally) {
    open val resultId: String?
    open val edits: Array<SemanticTokensEdit>
}

external open class SemanticTokensEdit(start: Number, deleteCount: Number, data: Uint32Array = definedExternally) {
    open val start: Number
    open val deleteCount: Number
    open val data: Uint32Array?
}

external interface DocumentSemanticTokensProvider {
    var onDidChangeSemanticTokens: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideDocumentSemanticTokens(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* SemanticTokens? | Thenable<SemanticTokens?>? */

    val provideDocumentSemanticTokensEdits: ((document: TextDocument, previousResultId: String, token: CancellationToken) -> dynamic)?
}

external interface DocumentRangeSemanticTokensProvider {
    fun provideDocumentRangeSemanticTokens(
        document: TextDocument,
        range: Range,
        token: CancellationToken
    ): dynamic /* SemanticTokens? | Thenable<SemanticTokens?>? */
}

external interface FormattingOptions {
    var tabSize: Number
    var insertSpaces: Boolean

    @nativeGetter
    operator fun get(key: String): dynamic /* Boolean? | Number? | String? */

    @nativeSetter
    operator fun set(key: String, value: Boolean)

    @nativeSetter
    operator fun set(key: String, value: Number)

    @nativeSetter
    operator fun set(key: String, value: String)
}

external interface DocumentFormattingEditProvider {
    fun provideDocumentFormattingEdits(
        document: TextDocument,
        options: FormattingOptions,
        token: CancellationToken
    ): dynamic /* Array<TextEdit>? | Thenable<Array<TextEdit>?>? */
}

external interface DocumentRangeFormattingEditProvider {
    fun provideDocumentRangeFormattingEdits(
        document: TextDocument,
        range: Range,
        options: FormattingOptions,
        token: CancellationToken
    ): dynamic /* Array<TextEdit>? | Thenable<Array<TextEdit>?>? */

    val provideDocumentRangesFormattingEdits: ((document: TextDocument, ranges: Array<Range>, options: FormattingOptions, token: CancellationToken) -> dynamic)?
}

external interface OnTypeFormattingEditProvider {
    fun provideOnTypeFormattingEdits(
        document: TextDocument,
        position: Position,
        ch: String,
        options: FormattingOptions,
        token: CancellationToken
    ): dynamic /* Array<TextEdit>? | Thenable<Array<TextEdit>?>? */
}

external open class ParameterInformation {
    open var label: dynamic /* String | JsTuple<Number, Number> */
    open var documentation: dynamic /* String | MarkdownString */

    constructor(label: String, documentation: String = definedExternally)
    constructor(label: String)
    constructor(label: String, documentation: MarkdownString = definedExternally)
    constructor(label: Any, documentation: String = definedExternally)
    constructor(label: Any)
    constructor(label: Any, documentation: MarkdownString = definedExternally)
}

external open class SignatureInformation {
    open var label: String
    open var documentation: dynamic /* String | MarkdownString */
    open var parameters: Array<ParameterInformation>
    open var activeParameter: Number

    constructor(label: String, documentation: String = definedExternally)
    constructor(label: String)
    constructor(label: String, documentation: MarkdownString = definedExternally)
}

external open class SignatureHelp {
    open var signatures: Array<SignatureInformation>
    open var activeSignature: Number
    open var activeParameter: Number
}

external enum class SignatureHelpTriggerKind {
    Invoke /* = 1 */,
    TriggerCharacter /* = 2 */,
    ContentChange /* = 3 */
}

external interface SignatureHelpContext {
    val triggerKind: SignatureHelpTriggerKind
    val triggerCharacter: String?
    val isRetrigger: Boolean
    val activeSignatureHelp: SignatureHelp?
}

external interface SignatureHelpProvider {
    fun provideSignatureHelp(
        document: TextDocument,
        position: Position,
        token: CancellationToken,
        context: SignatureHelpContext
    ): dynamic /* SignatureHelp? | Thenable<SignatureHelp?>? */
}

external interface SignatureHelpProviderMetadata {
    val triggerCharacters: Array<String>
    val retriggerCharacters: Array<String>
}

external interface CompletionItemLabel {
    var label: String
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class CompletionItemKind {
    Text /* = 0 */,
    Method /* = 1 */,
    Function /* = 2 */,
    Constructor /* = 3 */,
    Field /* = 4 */,
    Variable /* = 5 */,
    Class /* = 6 */,
    Interface /* = 7 */,
    Module /* = 8 */,
    Property /* = 9 */,
    Unit /* = 10 */,
    Value /* = 11 */,
    Enum /* = 12 */,
    Keyword /* = 13 */,
    Snippet /* = 14 */,
    Color /* = 15 */,
    Reference /* = 17 */,
    File /* = 16 */,
    Folder /* = 18 */,
    EnumMember /* = 19 */,
    Constant /* = 20 */,
    Struct /* = 21 */,
    Event /* = 22 */,
    Operator /* = 23 */,
    TypeParameter /* = 24 */,
    User /* = 25 */,
    Issue /* = 26 */
}

external enum class CompletionItemTag {
    Deprecated /* = 1 */
}

external interface `T$18` {
    var inserting: Range
    var replacing: Range
}

external open class CompletionItem {
    open var label: dynamic /* String | CompletionItemLabel */
    open var kind: CompletionItemKind
    open var tags: Array<CompletionItemTag>
    open var detail: String
    open var documentation: dynamic /* String | MarkdownString */
    open var sortText: String
    open var filterText: String
    open var preselect: Boolean
    open var insertText: dynamic /* String | SnippetString */
    open var range: dynamic /* Range | `T$18` */
    open var commitCharacters: Array<String>
    open var keepWhitespace: Boolean
    open var textEdit: TextEdit
    open var additionalTextEdits: Array<TextEdit>
    open var command: Command

    constructor(label: String, kind: CompletionItemKind = definedExternally)
    constructor(label: String)
    constructor(label: CompletionItemLabel, kind: CompletionItemKind = definedExternally)
    constructor(label: CompletionItemLabel)
}

external open class CompletionList<T : CompletionItem>(
    items: Array<T> = definedExternally,
    isIncomplete: Boolean = definedExternally
) {
    open var isIncomplete: Boolean
    open var items: Array<T>
}

external enum class CompletionTriggerKind {
    Invoke /* = 0 */,
    TriggerCharacter /* = 1 */,
    TriggerForIncompleteCompletions /* = 2 */
}

external interface CompletionContext {
    val triggerKind: CompletionTriggerKind
    val triggerCharacter: String?
}

external interface CompletionItemProvider<T : CompletionItem> {
    fun provideCompletionItems(
        document: TextDocument,
        position: Position,
        token: CancellationToken,
        context: CompletionContext
    ): dynamic /* Array<T>? | CompletionList<T>? | Thenable<dynamic /* Array<T>? | CompletionList<T>? */>? */

    val resolveCompletionItem: ((item: T, token: CancellationToken) -> dynamic)?
}

external interface CompletionItemProvider__0 : CompletionItemProvider<CompletionItem>

external interface InlineCompletionItemProvider {
    fun provideInlineCompletionItems(
        document: TextDocument,
        position: Position,
        context: InlineCompletionContext,
        token: CancellationToken
    ): dynamic /* Array<InlineCompletionItem>? | InlineCompletionList? | Thenable<dynamic /* Array<InlineCompletionItem>? | InlineCompletionList? */>? */
}

external open class InlineCompletionList(items: Array<InlineCompletionItem>) {
    open var items: Array<InlineCompletionItem>
}

external interface InlineCompletionContext {
    val triggerKind: InlineCompletionTriggerKind
    val selectedCompletionInfo: SelectedCompletionInfo?
}

external interface SelectedCompletionInfo {
    val range: Range
    val text: String
}

external enum class InlineCompletionTriggerKind {
    Invoke /* = 0 */,
    Automatic /* = 1 */
}

external open class InlineCompletionItem {
    open var insertText: dynamic /* String | SnippetString */
    open var filterText: String
    open var range: Range
    open var command: Command

    constructor(insertText: String, range: Range = definedExternally, command: Command = definedExternally)
    constructor(insertText: String)
    constructor(insertText: String, range: Range = definedExternally)
    constructor(insertText: SnippetString, range: Range = definedExternally, command: Command = definedExternally)
    constructor(insertText: SnippetString)
    constructor(insertText: SnippetString, range: Range = definedExternally)
}

external open class DocumentLink(range: Range, target: Uri = definedExternally) {
    open var range: Range
    open var target: Uri
    open var tooltip: String
}

external interface DocumentLinkProvider<T : DocumentLink> {
    fun provideDocumentLinks(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* Array<T>? | Thenable<Array<T>?>? */

    val resolveDocumentLink: ((link: T, token: CancellationToken) -> dynamic)?
}

external interface DocumentLinkProvider__0 : DocumentLinkProvider<DocumentLink>

external open class Color(red: Number, green: Number, blue: Number, alpha: Number) {
    open val red: Number
    open val green: Number
    open val blue: Number
    open val alpha: Number
}

external open class ColorInformation(range: Range, color: Color) {
    open var range: Range
    open var color: Color
}

external open class ColorPresentation(label: String) {
    open var label: String
    open var textEdit: TextEdit
    open var additionalTextEdits: Array<TextEdit>
}

external interface `T$19` {
    val document: TextDocument
    val range: Range
}

external interface DocumentColorProvider {
    fun provideDocumentColors(
        document: TextDocument,
        token: CancellationToken
    ): dynamic /* Array<ColorInformation>? | Thenable<Array<ColorInformation>?>? */

    fun provideColorPresentations(
        color: Color,
        context: `T$19`,
        token: CancellationToken
    ): dynamic /* Array<ColorPresentation>? | Thenable<Array<ColorPresentation>?>? */
}

external enum class InlayHintKind {
    Type /* = 1 */,
    Parameter /* = 2 */
}

external open class InlayHintLabelPart(value: String) {
    open var value: String
    open var tooltip: dynamic /* String? | MarkdownString? */
    open var location: Location?
    open var command: Command?
}

external open class InlayHint {
    open var position: Position
    open var label: dynamic /* String | Array<InlayHintLabelPart> */
    open var tooltip: dynamic /* String? | MarkdownString? */
    open var kind: InlayHintKind
    open var textEdits: Array<TextEdit>
    open var paddingLeft: Boolean
    open var paddingRight: Boolean

    constructor(position: Position, label: String, kind: InlayHintKind = definedExternally)
    constructor(position: Position, label: String)
    constructor(position: Position, label: Array<InlayHintLabelPart>, kind: InlayHintKind = definedExternally)
    constructor(position: Position, label: Array<InlayHintLabelPart>)
}

external interface InlayHintsProvider<T : InlayHint> {
    var onDidChangeInlayHints: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideInlayHints(
        document: TextDocument,
        range: Range,
        token: CancellationToken
    ): dynamic /* Array<T>? | Thenable<Array<T>?>? */

    val resolveInlayHint: ((hint: T, token: CancellationToken) -> dynamic)?
}

external interface InlayHintsProvider__0 : InlayHintsProvider<InlayHint>

external open class FoldingRange(start: Number, end: Number, kind: FoldingRangeKind = definedExternally) {
    open var start: Number
    open var end: Number
    open var kind: FoldingRangeKind
}

external enum class FoldingRangeKind {
    Comment /* = 1 */,
    Imports /* = 2 */,
    Region /* = 3 */
}

external interface FoldingContext

external interface FoldingRangeProvider {
    var onDidChangeFoldingRanges: Event<Unit>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideFoldingRanges(
        document: TextDocument,
        context: FoldingContext,
        token: CancellationToken
    ): dynamic /* Array<FoldingRange>? | Thenable<Array<FoldingRange>?>? */
}

external open class SelectionRange(range: Range, parent: SelectionRange = definedExternally) {
    open var range: Range
    open var parent: SelectionRange
}

external interface SelectionRangeProvider {
    fun provideSelectionRanges(
        document: TextDocument,
        positions: Array<Position>,
        token: CancellationToken
    ): dynamic /* Array<SelectionRange>? | Thenable<Array<SelectionRange>?>? */
}

external open class CallHierarchyItem(
    kind: SymbolKind,
    name: String,
    detail: String,
    uri: Uri,
    range: Range,
    selectionRange: Range
) {
    open var name: String
    open var kind: SymbolKind
    open var tags: Array<SymbolTag>
    open var detail: String
    open var uri: Uri
    open var range: Range
    open var selectionRange: Range
}

external open class CallHierarchyIncomingCall(item: CallHierarchyItem, fromRanges: Array<Range>) {
    open var from: CallHierarchyItem
    open var fromRanges: Array<Range>
}

external open class CallHierarchyOutgoingCall(item: CallHierarchyItem, fromRanges: Array<Range>) {
    open var to: CallHierarchyItem
    open var fromRanges: Array<Range>
}

external interface CallHierarchyProvider {
    fun prepareCallHierarchy(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* CallHierarchyItem? | Array<CallHierarchyItem>? | Thenable<dynamic /* CallHierarchyItem? | Array<CallHierarchyItem>? */>? */

    fun provideCallHierarchyIncomingCalls(
        item: CallHierarchyItem,
        token: CancellationToken
    ): dynamic /* Array<CallHierarchyIncomingCall>? | Thenable<Array<CallHierarchyIncomingCall>?>? */

    fun provideCallHierarchyOutgoingCalls(
        item: CallHierarchyItem,
        token: CancellationToken
    ): dynamic /* Array<CallHierarchyOutgoingCall>? | Thenable<Array<CallHierarchyOutgoingCall>?>? */
}

external open class TypeHierarchyItem(
    kind: SymbolKind,
    name: String,
    detail: String,
    uri: Uri,
    range: Range,
    selectionRange: Range
) {
    open var name: String
    open var kind: SymbolKind
    open var tags: Array<SymbolTag>
    open var detail: String
    open var uri: Uri
    open var range: Range
    open var selectionRange: Range
}

external interface TypeHierarchyProvider {
    fun prepareTypeHierarchy(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* TypeHierarchyItem? | Array<TypeHierarchyItem>? | Thenable<dynamic /* TypeHierarchyItem? | Array<TypeHierarchyItem>? */>? */

    fun provideTypeHierarchySupertypes(
        item: TypeHierarchyItem,
        token: CancellationToken
    ): dynamic /* Array<TypeHierarchyItem>? | Thenable<Array<TypeHierarchyItem>?>? */

    fun provideTypeHierarchySubtypes(
        item: TypeHierarchyItem,
        token: CancellationToken
    ): dynamic /* Array<TypeHierarchyItem>? | Thenable<Array<TypeHierarchyItem>?>? */
}

external open class LinkedEditingRanges(ranges: Array<Range>, wordPattern: RegExp = definedExternally) {
    open val ranges: Array<Range>
    open val wordPattern: RegExp?
}

external interface LinkedEditingRangeProvider {
    fun provideLinkedEditingRanges(
        document: TextDocument,
        position: Position,
        token: CancellationToken
    ): dynamic /* LinkedEditingRanges? | Thenable<LinkedEditingRanges?>? */
}

external open class DocumentDropEdit {
    open var insertText: dynamic /* String | SnippetString */
    open var additionalEdit: WorkspaceEdit

    constructor(insertText: String)
    constructor(insertText: SnippetString)
}

external interface DocumentDropEditProvider {
    fun provideDocumentDropEdits(
        document: TextDocument,
        position: Position,
        dataTransfer: DataTransfer,
        token: CancellationToken
    ): dynamic /* DocumentDropEdit? | Thenable<DocumentDropEdit?>? */
}

external interface CommentRule {
    var lineComment: String?
        get() = definedExternally
        set(value) = definedExternally
    var blockComment: dynamic /* JsTuple<String, String> */
        get() = definedExternally
        set(value) = definedExternally
}

external interface IndentationRule {
    var decreaseIndentPattern: RegExp
    var increaseIndentPattern: RegExp
    var indentNextLinePattern: RegExp?
        get() = definedExternally
        set(value) = definedExternally
    var unIndentedLinePattern: RegExp?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class IndentAction {
    None /* = 0 */,
    Indent /* = 1 */,
    IndentOutdent /* = 2 */,
    Outdent /* = 3 */
}

external interface EnterAction {
    var indentAction: IndentAction
    var appendText: String?
        get() = definedExternally
        set(value) = definedExternally
    var removeText: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface OnEnterRule {
    var beforeText: RegExp
    var afterText: RegExp?
        get() = definedExternally
        set(value) = definedExternally
    var previousLineText: RegExp?
        get() = definedExternally
        set(value) = definedExternally
    var action: EnterAction
}

external enum class SyntaxTokenType {
    Other /* = 0 */,
    Comment /* = 1 */,
    String /* = 2 */,
    RegEx /* = 3 */
}

external interface AutoClosingPair {
    var open: String
    var close: String
    var notIn: Array<SyntaxTokenType>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$20` {
    var scope: String
    var open: String
    var lineStart: String
    var close: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$21` {
    var brackets: Any?
        get() = definedExternally
        set(value) = definedExternally
    var docComment: `T$20`?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$22` {
    var open: String
    var close: String
    var notIn: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$23` {
    var autoClosingPairs: Array<`T$22`>
}

external interface LanguageConfiguration {
    var comments: CommentRule?
        get() = definedExternally
        set(value) = definedExternally
    var brackets: Array<dynamic /* JsTuple<String, String> */>?
        get() = definedExternally
        set(value) = definedExternally
    var wordPattern: RegExp?
        get() = definedExternally
        set(value) = definedExternally
    var indentationRules: IndentationRule?
        get() = definedExternally
        set(value) = definedExternally
    var onEnterRules: Array<OnEnterRule>?
        get() = definedExternally
        set(value) = definedExternally
    var autoClosingPairs: Array<AutoClosingPair>?
        get() = definedExternally
        set(value) = definedExternally
    var __electricCharacterSupport: `T$21`?
        get() = definedExternally
        set(value) = definedExternally
    var __characterPairSupport: `T$23`?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class ConfigurationTarget {
    Global /* = 1 */,
    Workspace /* = 2 */,
    WorkspaceFolder /* = 3 */
}

external interface `T$24`<T> {
    var key: String
    var defaultValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var globalValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var workspaceValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var workspaceFolderValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var defaultLanguageValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var globalLanguageValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var workspaceLanguageValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var workspaceFolderLanguageValue: T?
        get() = definedExternally
        set(value) = definedExternally
    var languageIds: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface WorkspaceConfiguration {
    fun <T> get(section: String): T?
    fun <T> get(section: String, defaultValue: T): T
    fun has(section: String): Boolean
    fun <T> inspect(section: String): `T$24`<T>?
    fun update(
        section: String,
        value: Any,
        configurationTarget: ConfigurationTarget? = definedExternally,
        overrideInLanguage: Boolean = definedExternally
    ): Thenable<Unit>

    fun update(section: String, value: Any): Thenable<Unit>
    fun update(
        section: String,
        value: Any,
        configurationTarget: ConfigurationTarget? = definedExternally
    ): Thenable<Unit>

    fun update(
        section: String,
        value: Any,
        configurationTarget: Boolean? = definedExternally,
        overrideInLanguage: Boolean = definedExternally
    ): Thenable<Unit>

    fun update(section: String, value: Any, configurationTarget: Boolean? = definedExternally): Thenable<Unit>

    @nativeGetter
    operator fun get(key: String): Any?

    @nativeSetter
    operator fun set(key: String, value: Any)
}

external open class Location {
    open var uri: Uri
    open var range: Range

    constructor(uri: Uri, rangeOrPosition: Range)
    constructor(uri: Uri, rangeOrPosition: Position)
}

external interface LocationLink {
    var originSelectionRange: Range?
        get() = definedExternally
        set(value) = definedExternally
    var targetUri: Uri
    var targetRange: Range
    var targetSelectionRange: Range?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DiagnosticChangeEvent {
    val uris: Array<Uri>
}

external enum class DiagnosticSeverity {
    Error /* = 0 */,
    Warning /* = 1 */,
    Information /* = 2 */,
    Hint /* = 3 */
}

external open class DiagnosticRelatedInformation(location: Location, message: String) {
    open var location: Location
    open var message: String
}

external enum class DiagnosticTag {
    Unnecessary /* = 1 */,
    Deprecated /* = 2 */
}

external interface `T$25` {
    var value: dynamic /* String | Number */
        get() = definedExternally
        set(value) = definedExternally
    var target: Uri
}

external open class Diagnostic(range: Range, message: String, severity: DiagnosticSeverity = definedExternally) {
    open var range: Range
    open var message: String
    open var severity: DiagnosticSeverity
    open var source: String
    open var code: dynamic /* String | Number | `T$25` */
    open var relatedInformation: Array<DiagnosticRelatedInformation>
    open var tags: Array<DiagnosticTag>
}

external enum class LanguageStatusSeverity {
    Information /* = 0 */,
    Warning /* = 1 */,
    Error /* = 2 */
}

external interface LanguageStatusItem {
    val id: String
    var name: String?
    var selector: dynamic /* DocumentFilter | String | ReadonlyArray<dynamic /* DocumentFilter | String */> */
        get() = definedExternally
        set(value) = definedExternally
    var severity: LanguageStatusSeverity
    var text: String
    var detail: String?
        get() = definedExternally
        set(value) = definedExternally
    var busy: Boolean
    var command: Command?
    var accessibilityInformation: AccessibilityInformation?
        get() = definedExternally
        set(value) = definedExternally

    fun dispose()
}

external enum class ViewColumn {
    Active /* = -1 */,
    Beside /* = -2 */,
    One /* = 1 */,
    Two /* = 2 */,
    Three /* = 3 */,
    Four /* = 4 */,
    Five /* = 5 */,
    Six /* = 6 */,
    Seven /* = 7 */,
    Eight /* = 8 */,
    Nine /* = 9 */
}

external interface OutputChannel {
    val name: String
    fun append(value: String)
    fun appendLine(value: String)
    fun replace(value: String)
    fun clear()
    fun show(preserveFocus: Boolean = definedExternally)
    fun show()
    fun show(column: ViewColumn = definedExternally, preserveFocus: Boolean = definedExternally)
    fun show(column: ViewColumn = definedExternally)
    fun hide()
    fun dispose()
}

external interface LogOutputChannel : OutputChannel {
    val logLevel: LogLevel
    val onDidChangeLogLevel: Event<LogLevel>
    fun trace(message: String, vararg args: Any)
    fun debug(message: String, vararg args: Any)
    fun info(message: String, vararg args: Any)
    fun warn(message: String, vararg args: Any)
    fun error(error: String, vararg args: Any)
    fun error(error: Error, vararg args: Any)
}

external interface AccessibilityInformation {
    val label: String
    val role: String?
        get() = definedExternally
}

external enum class StatusBarAlignment {
    Left /* = 1 */,
    Right /* = 2 */
}

external interface StatusBarItem {
    val id: String
    val alignment: StatusBarAlignment
    val priority: Number?
    var name: String?
    var text: String
    var tooltip: dynamic /* String? | MarkdownString? */
        get() = definedExternally
        set(value) = definedExternally
    var color: dynamic /* String? | ThemeColor? */
        get() = definedExternally
        set(value) = definedExternally
    var backgroundColor: ThemeColor?
    var command: dynamic /* String? | Command? */
        get() = definedExternally
        set(value) = definedExternally
    var accessibilityInformation: AccessibilityInformation?
    fun show()
    fun hide()
    fun dispose()
}

external interface Progress<T> {
    fun report(value: T)
}

external interface Terminal {
    val name: String
    val processId: Thenable<Number?>
    val creationOptions: Readonly<dynamic /* TerminalOptions | ExtensionTerminalOptions */>
    val exitStatus: TerminalExitStatus?
    val state: TerminalState
    val shellIntegration: TerminalShellIntegration?
    fun sendText(text: String, shouldExecute: Boolean = definedExternally)
    fun show(preserveFocus: Boolean = definedExternally)
    fun hide()
    fun dispose()
}

external enum class TerminalLocation {
    Panel /* = 1 */,
    Editor /* = 2 */
}

external interface TerminalEditorLocationOptions {
    var viewColumn: ViewColumn
    var preserveFocus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TerminalSplitLocationOptions {
    var parentTerminal: Terminal
}

external interface TerminalState {
    val isInteractedWith: Boolean
}

external interface TerminalShellIntegration {
    val cwd: Uri?
    fun executeCommand(commandLine: String): TerminalShellExecution
    fun executeCommand(executable: String, args: Array<String>): TerminalShellExecution
}

external interface TerminalShellExecution {
    val commandLine: TerminalShellExecutionCommandLine
    val cwd: Uri?
    fun read(): dynamic /* AsyncIterable<String> */
}

external interface TerminalShellExecutionCommandLine {
    val value: String
    val isTrusted: Boolean
    val confidence: TerminalShellExecutionCommandLineConfidence
}

external enum class TerminalShellExecutionCommandLineConfidence {
    Low /* = 0 */,
    Medium /* = 1 */,
    High /* = 2 */
}

external interface TerminalShellIntegrationChangeEvent {
    val terminal: Terminal
    val shellIntegration: TerminalShellIntegration
}

external interface TerminalShellExecutionStartEvent {
    val terminal: Terminal
    val shellIntegration: TerminalShellIntegration
    val execution: TerminalShellExecution
}

external interface TerminalShellExecutionEndEvent {
    val terminal: Terminal
    val shellIntegration: TerminalShellIntegration
    val execution: TerminalShellExecution
    val exitCode: Number?
}

external interface TerminalLinkContext {
    var line: String
    var terminal: Terminal
}

external interface TerminalLinkProvider<T : TerminalLink> {
    fun provideTerminalLinks(
        context: TerminalLinkContext,
        token: CancellationToken
    ): dynamic /* Array<T>? | Thenable<Array<T>?>? */

    fun handleTerminalLink(link: T): dynamic /* Unit? | Thenable<Unit?>? */
}

external interface TerminalLinkProvider__0 : TerminalLinkProvider<TerminalLink>

external open class TerminalLink(startIndex: Number, length: Number, tooltip: String = definedExternally) {
    open var startIndex: Number
    open var length: Number
    open var tooltip: String
}

external interface TerminalProfileProvider {
    fun provideTerminalProfile(token: CancellationToken): dynamic /* TerminalProfile? | Thenable<TerminalProfile?>? */
}

external open class TerminalProfile {
    open var options: dynamic /* TerminalOptions | ExtensionTerminalOptions */

    constructor(options: TerminalOptions)
    constructor(options: ExtensionTerminalOptions)
}

external open class FileDecoration(
    badge: String = definedExternally,
    tooltip: String = definedExternally,
    color: ThemeColor = definedExternally
) {
    open var badge: String
    open var tooltip: String
    open var color: ThemeColor
    open var propagate: Boolean
}

external interface FileDecorationProvider {
    var onDidChangeFileDecorations: Event<dynamic /* Uri? | Array<Uri>? */>?
        get() = definedExternally
        set(value) = definedExternally

    fun provideFileDecoration(
        uri: Uri,
        token: CancellationToken
    ): dynamic /* FileDecoration? | Thenable<FileDecoration?>? */
}

external enum class ExtensionKind {
    UI /* = 1 */,
    Workspace /* = 2 */
}

external interface Extension<T> {
    val id: String
    val extensionUri: Uri
    val extensionPath: String
    val isActive: Boolean
    val packageJSON: Any
    var extensionKind: ExtensionKind
    val exports: T
    fun activate(): Thenable<T>
}

external enum class ExtensionMode {
    Production /* = 1 */,
    Development /* = 2 */,
    Test /* = 3 */
}

external interface `T$26` {
    fun dispose(): Any
}

external interface `T$27` {
    fun setKeysForSync(keys: Array<String>)
}

external interface ExtensionContext {
    val subscriptions: Array<`T$26`>
    val workspaceState: Memento
    val globalState: Memento /* Memento & `T$27` */
    val secrets: SecretStorage
    val extensionUri: Uri
    val extensionPath: String
    val environmentVariableCollection: GlobalEnvironmentVariableCollection
    fun asAbsolutePath(relativePath: String): String
    val storageUri: Uri?
    val storagePath: String?
    val globalStorageUri: Uri
    val globalStoragePath: String
    val logUri: Uri
    val logPath: String
    val extensionMode: ExtensionMode
    val extension: Extension<Any>
    val languageModelAccessInformation: LanguageModelAccessInformation
}

external interface Memento {
    fun keys(): Array<String>
    fun <T> get(key: String): T?
    fun <T> get(key: String, defaultValue: T): T
    fun update(key: String, value: Any): Thenable<Unit>
}

external interface SecretStorageChangeEvent {
    val key: String
}

external interface SecretStorage {
    fun get(key: String): Thenable<String?>
    fun store(key: String, value: String): Thenable<Unit>
    fun delete(key: String): Thenable<Unit>
    var onDidChange: Event<SecretStorageChangeEvent>
}

external enum class ColorThemeKind {
    Light /* = 1 */,
    Dark /* = 2 */,
    HighContrast /* = 3 */,
    HighContrastLight /* = 4 */
}

external interface ColorTheme {
    val kind: ColorThemeKind
}

external enum class TaskRevealKind {
    Always /* = 1 */,
    Silent /* = 2 */,
    Never /* = 3 */
}

external enum class TaskPanelKind {
    Shared /* = 1 */,
    Dedicated /* = 2 */,
    New /* = 3 */
}

external interface TaskPresentationOptions {
    var reveal: TaskRevealKind?
        get() = definedExternally
        set(value) = definedExternally
    var echo: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var focus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var panel: TaskPanelKind?
        get() = definedExternally
        set(value) = definedExternally
    var showReuseMessage: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var clear: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var close: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class TaskGroup(id: String, label: String) {
    open val isDefault: Boolean?
    open val id: String

    companion object {
        var Clean: TaskGroup
        var Build: TaskGroup
        var Rebuild: TaskGroup
        var Test: TaskGroup
    }
}

external interface TaskDefinition {
    var type: String

    @nativeGetter
    operator fun get(name: String): Any?

    @nativeSetter
    operator fun set(name: String, value: Any)
}

external interface `T$28` {
    @nativeGetter
    operator fun get(key: String): String?

    @nativeSetter
    operator fun set(key: String, value: String)
}

external interface ProcessExecutionOptions {
    var cwd: String?
        get() = definedExternally
        set(value) = definedExternally
    var env: `T$28`?
        get() = definedExternally
        set(value) = definedExternally
}

external open class ProcessExecution {
    constructor(process: String, options: ProcessExecutionOptions = definedExternally)
    constructor(process: String)
    constructor(process: String, args: Array<String>, options: ProcessExecutionOptions = definedExternally)
    constructor(process: String, args: Array<String>)

    open var process: String
    open var args: Array<String>
    open var options: ProcessExecutionOptions
}

external interface `T$29` {
    var escapeChar: String
    var charsToEscape: String
}

external interface ShellQuotingOptions {
    var escape: dynamic /* String? | `T$29`? */
        get() = definedExternally
        set(value) = definedExternally
    var strong: String?
        get() = definedExternally
        set(value) = definedExternally
    var weak: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ShellExecutionOptions {
    var executable: String?
        get() = definedExternally
        set(value) = definedExternally
    var shellArgs: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var shellQuoting: ShellQuotingOptions?
        get() = definedExternally
        set(value) = definedExternally
    var cwd: String?
        get() = definedExternally
        set(value) = definedExternally
    var env: `T$28`?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class ShellQuoting {
    Escape /* = 1 */,
    Strong /* = 2 */,
    Weak /* = 3 */
}

external interface ShellQuotedString {
    var value: String
    var quoting: ShellQuoting
}

external open class ShellExecution {
    constructor(commandLine: String, options: ShellExecutionOptions = definedExternally)
    constructor(commandLine: String)
    constructor(
        command: String,
        args: Array<Any /* String | ShellQuotedString */>,
        options: ShellExecutionOptions = definedExternally
    )

    constructor(command: String, args: Array<Any /* String | ShellQuotedString */>)
    constructor(
        command: ShellQuotedString,
        args: Array<Any /* String | ShellQuotedString */>,
        options: ShellExecutionOptions = definedExternally
    )

    constructor(command: ShellQuotedString, args: Array<Any /* String | ShellQuotedString */>)

    open var commandLine: String?
    open var command: dynamic /* String | ShellQuotedString */
    open var args: Array<dynamic /* String | ShellQuotedString */>
    open var options: ShellExecutionOptions
}

external open class CustomExecution(callback: (resolvedDefinition: TaskDefinition) -> Thenable<Pseudoterminal>)

external enum class TaskScope {
    Global /* = 1 */,
    Workspace /* = 2 */
}

external interface RunOptions {
    var reevaluateOnRerun: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external open class Task {
    constructor(taskDefinition: TaskDefinition, name: String, source: String)
    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally,
        problemMatchers: Array<String> = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally,
        problemMatchers: String = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally,
        problemMatchers: Array<String> = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally,
        problemMatchers: String = definedExternally
    )

    constructor(taskDefinition: TaskDefinition, scope: TaskScope, name: String, source: String)
    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: CustomExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: CustomExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: TaskScope,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    constructor(taskDefinition: TaskDefinition, scope: WorkspaceFolder, name: String, source: String)
    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: CustomExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: CustomExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: ProcessExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally
    )

    constructor(
        taskDefinition: TaskDefinition,
        scope: WorkspaceFolder,
        name: String,
        source: String,
        execution: ShellExecution = definedExternally,
        problemMatchers: Any = definedExternally
    )

    open var definition: TaskDefinition
    open val scope: dynamic /* TaskScope.Global? | TaskScope.Workspace? | WorkspaceFolder? */
    open var name: String
    open var detail: String
    open var execution: dynamic /* ProcessExecution | ShellExecution | CustomExecution */
    open var isBackground: Boolean
    open var source: String
    open var group: TaskGroup
    open var presentationOptions: TaskPresentationOptions
    open var problemMatchers: Array<String>
    open var runOptions: RunOptions
}

@OptIn(ExperimentalJsCollectionsApi::class)
external interface TaskProvider<T : Task> {
    fun provideTasks(token: CancellationToken): JsReadonlyArray<T>? /* Array<T>? | Thenable<Array<T>?>? */
    fun resolveTask(task: T, token: CancellationToken): T? /* T? | Thenable<T?>? */
}

external interface TaskProvider__0 : TaskProvider<Task>

external interface TaskExecution {
    var task: Task
    fun terminate()
}

external interface TaskStartEvent {
    val execution: TaskExecution
}

external interface TaskEndEvent {
    val execution: TaskExecution
}

external interface TaskProcessStartEvent {
    val execution: TaskExecution
    val processId: Number
}

external interface TaskProcessEndEvent {
    val execution: TaskExecution
    val exitCode: Number?
}

external interface TaskFilter {
    var version: String?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class FileType {
    Unknown /* = 0 */,
    File /* = 1 */,
    Directory /* = 2 */,
    SymbolicLink /* = 64 */
}

external enum class FilePermission {
    Readonly /* = 1 */
}

external interface FileStat {
    var type: FileType
    var ctime: Number
    var mtime: Number
    var size: Number
    var permissions: FilePermission?
        get() = definedExternally
        set(value) = definedExternally
}

external enum class FileChangeType {
    Changed /* = 1 */,
    Created /* = 2 */,
    Deleted /* = 3 */
}

external interface FileChangeEvent {
    val type: FileChangeType
    val uri: Uri
}

external interface `T$30` {
    val recursive: Boolean
    val excludes: Array<String>
}

external interface `T$31` {
    val create: Boolean
    val overwrite: Boolean
}

external interface `T$32` {
    val recursive: Boolean
}

external interface `T$33` {
    val overwrite: Boolean
}

external interface FileSystemProvider {
    val onDidChangeFile: Event<Array<FileChangeEvent>>
    fun watch(uri: Uri, options: `T$30`): Disposable
    fun stat(uri: Uri): dynamic /* FileStat | Thenable<FileStat> */
    fun readDirectory(uri: Uri): dynamic /* Array<dynamic /* JsTuple<String, FileType> */> | Thenable<Array<dynamic /* JsTuple<String, FileType> */>> */
    fun createDirectory(uri: Uri): dynamic /* Unit | Thenable<Unit> */
    fun readFile(uri: Uri): dynamic /* Uint8Array | Thenable<Uint8Array> */
    fun writeFile(uri: Uri, content: Uint8Array, options: `T$31`): dynamic /* Unit | Thenable<Unit> */
    fun delete(uri: Uri, options: `T$32`): dynamic /* Unit | Thenable<Unit> */
    fun rename(oldUri: Uri, newUri: Uri, options: `T$33`): dynamic /* Unit | Thenable<Unit> */
    val copy: ((source: Uri, destination: Uri, options: `T$33`) -> dynamic)?
}

external interface `T$34` {
    var recursive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var useTrash: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$35` {
    var overwrite: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FileSystem {
    fun stat(uri: Uri): Thenable<FileStat>
    fun readDirectory(uri: Uri): Thenable<Array<dynamic /* JsTuple<String, FileType> */>>
    fun createDirectory(uri: Uri): Thenable<Unit>
    fun readFile(uri: Uri): Thenable<Uint8Array>
    fun writeFile(uri: Uri, content: Uint8Array): Thenable<Unit>
    fun delete(uri: Uri, options: `T$34` = definedExternally): Thenable<Unit>
    fun rename(source: Uri, target: Uri, options: `T$35` = definedExternally): Thenable<Unit>
    fun copy(source: Uri, target: Uri, options: `T$35` = definedExternally): Thenable<Unit>
    fun isWritableFileSystem(scheme: String): Boolean?
}

external interface WebviewPortMapping {
    val webviewPort: Number
    val extensionHostPort: Number
}

external interface WebviewOptions {
    val enableScripts: Boolean?
        get() = definedExternally
    val enableForms: Boolean?
        get() = definedExternally
    val enableCommandUris: dynamic /* Boolean? | Array<String>? */
        get() = definedExternally
    val localResourceRoots: Array<Uri>?
        get() = definedExternally
    val portMapping: Array<WebviewPortMapping>?
        get() = definedExternally
}

external interface Webview {
    var options: WebviewOptions
    var html: String
    val onDidReceiveMessage: Event<Any>
    fun postMessage(message: Any): Thenable<Boolean>
    fun asWebviewUri(localResource: Uri): Uri
    val cspSource: String
}

external interface WebviewPanelOptions {
    val enableFindWidget: Boolean?
        get() = definedExternally
    val retainContextWhenHidden: Boolean?
        get() = definedExternally
}

external interface `T$36` {
    val light: Uri
    val dark: Uri
}

external interface WebviewPanel {
    val viewType: String
    var title: String
    var iconPath: dynamic /* Uri? | `T$36`? */
        get() = definedExternally
        set(value) = definedExternally
    val webview: Webview
    val options: WebviewPanelOptions
    val viewColumn: ViewColumn?
    val active: Boolean
    val visible: Boolean
    val onDidChangeViewState: Event<WebviewPanelOnDidChangeViewStateEvent>
    val onDidDispose: Event<Unit>
    fun reveal(viewColumn: ViewColumn = definedExternally, preserveFocus: Boolean = definedExternally)
    fun dispose(): Any
}

external interface WebviewPanelOnDidChangeViewStateEvent {
    val webviewPanel: WebviewPanel
}

external interface WebviewPanelSerializer<T> {
    fun deserializeWebviewPanel(webviewPanel: WebviewPanel, state: T): Thenable<Unit>
}

external interface WebviewPanelSerializer__0 : WebviewPanelSerializer<Any>

external interface WebviewView {
    val viewType: String
    val webview: Webview
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
    var badge: ViewBadge?
        get() = definedExternally
        set(value) = definedExternally
    val onDidDispose: Event<Unit>
    val visible: Boolean
    val onDidChangeVisibility: Event<Unit>
    fun show(preserveFocus: Boolean = definedExternally)
}

external interface WebviewViewResolveContext<T> {
    val state: T?
}

external interface WebviewViewResolveContext__0 : WebviewViewResolveContext<Any>

external interface WebviewViewProvider {
    fun resolveWebviewView(
        webviewView: WebviewView,
        context: WebviewViewResolveContext__0,
        token: CancellationToken
    ): dynamic /* Thenable<Unit> | Unit */
}

external interface CustomTextEditorProvider {
    fun resolveCustomTextEditor(
        document: TextDocument,
        webviewPanel: WebviewPanel,
        token: CancellationToken
    ): dynamic /* Thenable<Unit> | Unit */
}

external interface CustomDocument {
    val uri: Uri
    fun dispose()
}

external interface CustomDocumentEditEvent<T : CustomDocument> {
    val document: T
    fun undo(): dynamic /* Thenable<Unit> | Unit */
    fun redo(): dynamic /* Thenable<Unit> | Unit */
    val label: String?
        get() = definedExternally
}

external interface CustomDocumentContentChangeEvent<T : CustomDocument> {
    val document: T
}

external interface CustomDocumentBackup {
    val id: String
    fun delete()
}

external interface CustomDocumentBackupContext {
    val destination: Uri
}

external interface CustomDocumentOpenContext {
    val backupId: String?
    val untitledDocumentData: Uint8Array?
}

external interface CustomReadonlyEditorProvider<T : CustomDocument> {
    fun openCustomDocument(
        uri: Uri,
        openContext: CustomDocumentOpenContext,
        token: CancellationToken
    ): dynamic /* Thenable<T> | T */

    fun resolveCustomEditor(
        document: T,
        webviewPanel: WebviewPanel,
        token: CancellationToken
    ): dynamic /* Thenable<Unit> | Unit */
}

external interface CustomReadonlyEditorProvider__0 : CustomReadonlyEditorProvider<CustomDocument>

external interface CustomEditorProvider<T : CustomDocument> : CustomReadonlyEditorProvider<T> {
    val onDidChangeCustomDocument: dynamic /* Event<CustomDocumentEditEvent<T>> | Event<CustomDocumentContentChangeEvent<T>> */
        get() = definedExternally

    fun saveCustomDocument(document: T, cancellation: CancellationToken): Thenable<Unit>
    fun saveCustomDocumentAs(document: T, destination: Uri, cancellation: CancellationToken): Thenable<Unit>
    fun revertCustomDocument(document: T, cancellation: CancellationToken): Thenable<Unit>
    fun backupCustomDocument(
        document: T,
        context: CustomDocumentBackupContext,
        cancellation: CancellationToken
    ): Thenable<CustomDocumentBackup>
}

external interface CustomEditorProvider__0 : CustomEditorProvider<CustomDocument>

external interface Clipboard {
    fun readText(): Thenable<String>
    fun writeText(value: String): Thenable<Unit>
}

external enum class UIKind {
    Desktop /* = 1 */,
    Web /* = 2 */
}

external enum class LogLevel {
    Off /* = 0 */,
    Trace /* = 1 */,
    Debug /* = 2 */,
    Info /* = 3 */,
    Warning /* = 4 */,
    Error /* = 5 */
}

external interface WindowState {
    val focused: Boolean
    val active: Boolean
}

external interface UriHandler {
    fun handleUri(uri: Uri): dynamic /* Unit? | Thenable<Unit?>? */
}

external interface TreeViewOptions<T> {
    var treeDataProvider: TreeDataProvider<T>
    var showCollapseAll: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var canSelectMany: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var dragAndDropController: TreeDragAndDropController<T>?
        get() = definedExternally
        set(value) = definedExternally
    var manageCheckboxStateManually: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TreeViewExpansionEvent<T> {
    val element: T
}

external interface TreeViewSelectionChangeEvent<T> {
    val selection: Array<T>
}

external interface TreeViewVisibilityChangeEvent {
    val visible: Boolean
}

external interface DataTransferFile {
    val name: String
    val uri: Uri?
        get() = definedExternally

    fun data(): Thenable<Uint8Array>
}

external open class DataTransferItem(value: Any) {
    open fun asString(): Thenable<String>
    open fun asFile(): DataTransferFile?
    open val value: Any
}
