@file:JsModule("vscode")
@file:JsQualifier("commands")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.commands

import vscode.Disposable
import vscode.TextEditor
import vscode.TextEditorEdit
import vscode.Thenable

external fun registerCommand(
    command: String,
    callback: (args: Any) -> Any,
    thisArg: Any = definedExternally
): Disposable

external fun registerTextEditorCommand(
    command: String,
    callback: (textEditor: TextEditor, edit: TextEditorEdit, args: Any) -> Unit,
    thisArg: Any = definedExternally
): Disposable

external fun <T> executeCommand(command: String, vararg rest: Any): Thenable<T>

external fun getCommands(filterInternal: Boolean = definedExternally): Thenable<Array<String>>
