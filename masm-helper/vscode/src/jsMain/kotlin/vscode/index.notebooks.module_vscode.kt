@file:JsModule("vscode")
@file:JsQualifier("notebooks")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.notebooks

import vscode.Disposable
import vscode.NotebookCell
import vscode.NotebookCellStatusBarItemProvider
import vscode.NotebookController
import vscode.NotebookDocument
import vscode.NotebookRendererMessaging

external fun createNotebookController(
    id: String,
    notebookType: String,
    label: String,
    handler: (cells: Array<NotebookCell>, notebook: NotebookDocument, controller: NotebookController) -> Any = definedExternally
): NotebookController

external fun registerNotebookCellStatusBarItemProvider(
    notebookType: String,
    provider: NotebookCellStatusBarItemProvider
): Disposable

external fun createRendererMessaging(rendererId: String): NotebookRendererMessaging
