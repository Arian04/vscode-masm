@file:JsModule("vscode")
@file:JsQualifier("workspace")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.workspace

import vscode.*

external var fs: FileSystem

external var rootPath: String?

external var workspaceFolders: Array<WorkspaceFolder>?

external var name: String?

external var workspaceFile: Uri?

external var onDidChangeWorkspaceFolders: Event<WorkspaceFoldersChangeEvent>

external fun getWorkspaceFolder(uri: Uri): WorkspaceFolder?

external fun asRelativePath(pathOrUri: String, includeWorkspaceFolder: Boolean = definedExternally): String

external fun asRelativePath(pathOrUri: String): String

external fun asRelativePath(pathOrUri: Uri, includeWorkspaceFolder: Boolean = definedExternally): String

external fun asRelativePath(pathOrUri: Uri): String

external interface `T$50` {
    val uri: Uri
    val name: String?
        get() = definedExternally
}

external fun updateWorkspaceFolders(start: Number, deleteCount: Number?, vararg workspaceFoldersToAdd: `T$50`): Boolean

external fun createFileSystemWatcher(
    globPattern: String,
    ignoreCreateEvents: Boolean = definedExternally,
    ignoreChangeEvents: Boolean = definedExternally,
    ignoreDeleteEvents: Boolean = definedExternally
): FileSystemWatcher

external fun createFileSystemWatcher(globPattern: String): FileSystemWatcher

external fun createFileSystemWatcher(
    globPattern: String,
    ignoreCreateEvents: Boolean = definedExternally
): FileSystemWatcher

external fun createFileSystemWatcher(
    globPattern: String,
    ignoreCreateEvents: Boolean = definedExternally,
    ignoreChangeEvents: Boolean = definedExternally
): FileSystemWatcher

external fun createFileSystemWatcher(
    globPattern: RelativePattern,
    ignoreCreateEvents: Boolean = definedExternally,
    ignoreChangeEvents: Boolean = definedExternally,
    ignoreDeleteEvents: Boolean = definedExternally
): FileSystemWatcher

external fun createFileSystemWatcher(globPattern: RelativePattern): FileSystemWatcher

external fun createFileSystemWatcher(
    globPattern: RelativePattern,
    ignoreCreateEvents: Boolean = definedExternally
): FileSystemWatcher

external fun createFileSystemWatcher(
    globPattern: RelativePattern,
    ignoreCreateEvents: Boolean = definedExternally,
    ignoreChangeEvents: Boolean = definedExternally
): FileSystemWatcher

external fun findFiles(
    include: String,
    exclude: String? = definedExternally,
    maxResults: Number = definedExternally,
    token: CancellationToken = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(include: String): Thenable<Array<Uri>>

external fun findFiles(include: String, exclude: String? = definedExternally): Thenable<Array<Uri>>

external fun findFiles(
    include: String,
    exclude: String? = definedExternally,
    maxResults: Number = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(
    include: String,
    exclude: RelativePattern? = definedExternally,
    maxResults: Number = definedExternally,
    token: CancellationToken = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(include: String, exclude: RelativePattern? = definedExternally): Thenable<Array<Uri>>

external fun findFiles(
    include: String,
    exclude: RelativePattern? = definedExternally,
    maxResults: Number = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(
    include: RelativePattern,
    exclude: String? = definedExternally,
    maxResults: Number = definedExternally,
    token: CancellationToken = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(include: RelativePattern): Thenable<Array<Uri>>

external fun findFiles(include: RelativePattern, exclude: String? = definedExternally): Thenable<Array<Uri>>

external fun findFiles(
    include: RelativePattern,
    exclude: String? = definedExternally,
    maxResults: Number = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(
    include: RelativePattern,
    exclude: RelativePattern? = definedExternally,
    maxResults: Number = definedExternally,
    token: CancellationToken = definedExternally
): Thenable<Array<Uri>>

external fun findFiles(include: RelativePattern, exclude: RelativePattern? = definedExternally): Thenable<Array<Uri>>

external fun findFiles(
    include: RelativePattern,
    exclude: RelativePattern? = definedExternally,
    maxResults: Number = definedExternally
): Thenable<Array<Uri>>

external fun save(uri: Uri): Thenable<Uri?>

external fun saveAs(uri: Uri): Thenable<Uri?>

external fun saveAll(includeUntitled: Boolean = definedExternally): Thenable<Boolean>

external fun applyEdit(edit: WorkspaceEdit, metadata: WorkspaceEditMetadata = definedExternally): Thenable<Boolean>

external var textDocuments: Array<TextDocument>

external fun openTextDocument(uri: Uri): Thenable<TextDocument>

external fun openTextDocument(path: String): Thenable<TextDocument>

external interface `T$51` {
    var language: String?
        get() = definedExternally
        set(value) = definedExternally
    var content: String?
        get() = definedExternally
        set(value) = definedExternally
}

external fun openTextDocument(options: `T$51` = definedExternally): Thenable<TextDocument>

external fun openTextDocument(): Thenable<TextDocument>

external fun registerTextDocumentContentProvider(scheme: String, provider: TextDocumentContentProvider): Disposable

external var onDidOpenTextDocument: Event<TextDocument>

external var onDidCloseTextDocument: Event<TextDocument>

external var onDidChangeTextDocument: Event<TextDocumentChangeEvent>

external var onWillSaveTextDocument: Event<TextDocumentWillSaveEvent>

external var onDidSaveTextDocument: Event<TextDocument>

external var notebookDocuments: Array<NotebookDocument>

external fun openNotebookDocument(uri: Uri): Thenable<NotebookDocument>

external fun openNotebookDocument(
    notebookType: String,
    content: NotebookData = definedExternally
): Thenable<NotebookDocument>

external fun openNotebookDocument(notebookType: String): Thenable<NotebookDocument>

external var onDidChangeNotebookDocument: Event<NotebookDocumentChangeEvent>

external var onWillSaveNotebookDocument: Event<NotebookDocumentWillSaveEvent>

external var onDidSaveNotebookDocument: Event<NotebookDocument>

external fun registerNotebookSerializer(
    notebookType: String,
    serializer: NotebookSerializer,
    options: NotebookDocumentContentOptions = definedExternally
): Disposable

external var onDidOpenNotebookDocument: Event<NotebookDocument>

external var onDidCloseNotebookDocument: Event<NotebookDocument>

external var onWillCreateFiles: Event<FileWillCreateEvent>

external var onDidCreateFiles: Event<FileCreateEvent>

external var onWillDeleteFiles: Event<FileWillDeleteEvent>

external var onDidDeleteFiles: Event<FileDeleteEvent>

external var onWillRenameFiles: Event<FileWillRenameEvent>

external var onDidRenameFiles: Event<FileRenameEvent>

external fun getConfiguration(
    section: String = definedExternally,
    scope: Uri? = definedExternally
): WorkspaceConfiguration

external fun getConfiguration(): WorkspaceConfiguration

external fun getConfiguration(section: String = definedExternally): WorkspaceConfiguration

external fun getConfiguration(
    section: String = definedExternally,
    scope: TextDocument? = definedExternally
): WorkspaceConfiguration

external fun getConfiguration(
    section: String = definedExternally,
    scope: WorkspaceFolder? = definedExternally
): WorkspaceConfiguration

external fun getConfiguration(
    section: String = definedExternally,
    scope: `T$61`? = definedExternally
): WorkspaceConfiguration

external var onDidChangeConfiguration: Event<ConfigurationChangeEvent>

external fun registerTaskProvider(type: String, provider: TaskProvider__0): Disposable

external interface `T$52` {
    val isCaseSensitive: Boolean?
        get() = definedExternally
    val isReadonly: dynamic /* Boolean? | MarkdownString? */
        get() = definedExternally
}

external fun registerFileSystemProvider(
    scheme: String,
    provider: FileSystemProvider,
    options: `T$52` = definedExternally
): Disposable

external var isTrusted: Boolean

external var onDidGrantWorkspaceTrust: Event<Unit>
