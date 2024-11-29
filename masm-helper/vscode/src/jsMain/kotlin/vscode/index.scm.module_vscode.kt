@file:JsModule("vscode")
@file:JsQualifier("scm")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.scm

import vscode.SourceControl
import vscode.SourceControlInputBox
import vscode.Uri

external var inputBox: SourceControlInputBox

external fun createSourceControl(id: String, label: String, rootUri: Uri = definedExternally): SourceControl
