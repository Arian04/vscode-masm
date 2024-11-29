@file:JsModule("vscode")
@file:JsQualifier("extensions")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.extensions

import vscode.Event
import vscode.Extension

external fun <T> getExtension(extensionId: String): Extension<T>?

external var all: Array<Extension<Any>>

external var onDidChange: Event<Unit>
