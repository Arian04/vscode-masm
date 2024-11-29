@file:JsModule("vscode")
@file:JsQualifier("authentication")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.authentication

import vscode.AuthenticationGetSessionOptions
import vscode.AuthenticationProvider
import vscode.AuthenticationProviderOptions
import vscode.AuthenticationSession
import vscode.AuthenticationSessionAccountInformation
import vscode.AuthenticationSessionsChangeEvent
import vscode.Disposable
import vscode.Event
import vscode.Thenable

external interface `T$58` {
    var createIfNone: Boolean
}

external fun getSession(
    providerId: String,
    scopes: Array<String>,
    options: AuthenticationGetSessionOptions /* AuthenticationGetSessionOptions & `T$58` | AuthenticationGetSessionOptions & `T$59` */
): dynamic /* Thenable | Thenable */

external interface `T$59` {
    var forceNewSession: dynamic /* Boolean | AuthenticationForceNewSessionOptions */
        get() = definedExternally
        set(value) = definedExternally
}

external fun getSession(providerId: String, scopes: Array<String>): Thenable<AuthenticationSession?>

external fun getAccounts(providerId: String): Thenable<Array<AuthenticationSessionAccountInformation>>

external var onDidChangeSessions: Event<AuthenticationSessionsChangeEvent>

external fun registerAuthenticationProvider(
    id: String,
    label: String,
    provider: AuthenticationProvider,
    options: AuthenticationProviderOptions = definedExternally
): Disposable
