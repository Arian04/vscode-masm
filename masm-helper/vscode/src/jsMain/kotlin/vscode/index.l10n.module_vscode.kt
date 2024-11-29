@file:JsModule("vscode")
@file:JsQualifier("l10n")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.l10n

import vscode.`T$28`
import vscode.Uri

external fun t(message: String, vararg args: Any /* String | Number | Boolean */): String

external interface `T$60` {
    var message: String
    var args: dynamic /* Array<dynamic /* String | Number | Boolean */>? | Record<String, Any>? */
        get() = definedExternally
        set(value) = definedExternally
    var comment: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
}

external fun t(options: `T$60`): String

external var bundle: `T$28`?

external var uri: Uri?
