@file:JsModule("vscode")
@file:JsQualifier("env")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.env

import vscode.Clipboard
import vscode.Event
import vscode.LogLevel
import vscode.TelemetryLogger
import vscode.TelemetryLoggerOptions
import vscode.TelemetrySender
import vscode.Thenable
import vscode.UIKind
import vscode.Uri

external var appName: String

external var appRoot: String

external var appHost: String

external var uriScheme: String

external var language: String

external var clipboard: Clipboard

external var machineId: String

external var sessionId: String

external var isNewAppInstall: Boolean

external var isTelemetryEnabled: Boolean

external var onDidChangeTelemetryEnabled: Event<Boolean>

external var onDidChangeShell: Event<String>

external fun createTelemetryLogger(
    sender: TelemetrySender,
    options: TelemetryLoggerOptions = definedExternally
): TelemetryLogger

external var remoteName: String?

external var shell: String

external var uiKind: UIKind

external fun openExternal(target: Uri): Thenable<Boolean>

external fun asExternalUri(target: Uri): Thenable<Uri>

external var logLevel: LogLevel

external var onDidChangeLogLevel: Event<LogLevel>
