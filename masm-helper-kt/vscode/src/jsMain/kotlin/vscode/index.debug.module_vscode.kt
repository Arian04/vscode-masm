@file:JsModule("vscode")
@file:JsQualifier("debug")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.debug

import vscode.*

external var activeDebugSession: DebugSession?

external var activeDebugConsole: DebugConsole

external var breakpoints: Array<Breakpoint>

external var onDidChangeActiveDebugSession: Event<DebugSession?>

external var onDidStartDebugSession: Event<DebugSession>

external var onDidReceiveDebugSessionCustomEvent: Event<DebugSessionCustomEvent>

external var onDidTerminateDebugSession: Event<DebugSession>

external var onDidChangeBreakpoints: Event<BreakpointsChangeEvent>

external var activeStackItem: dynamic /* DebugThread? | DebugStackFrame? */

external var onDidChangeActiveStackItem: Event<dynamic /* DebugThread? | DebugStackFrame? */>

external fun registerDebugConfigurationProvider(
    debugType: String,
    provider: DebugConfigurationProvider,
    triggerKind: DebugConfigurationProviderTriggerKind = definedExternally
): Disposable

external fun registerDebugAdapterDescriptorFactory(
    debugType: String,
    factory: DebugAdapterDescriptorFactory
): Disposable

external fun registerDebugAdapterTrackerFactory(debugType: String, factory: DebugAdapterTrackerFactory): Disposable

external fun startDebugging(
    folder: WorkspaceFolder?,
    nameOrConfiguration: String,
    parentSessionOrOptions: DebugSession = definedExternally
): Thenable<Boolean>

external fun startDebugging(folder: WorkspaceFolder?, nameOrConfiguration: String): Thenable<Boolean>

external fun startDebugging(
    folder: WorkspaceFolder?,
    nameOrConfiguration: String,
    parentSessionOrOptions: DebugSessionOptions = definedExternally
): Thenable<Boolean>

external fun startDebugging(
    folder: WorkspaceFolder?,
    nameOrConfiguration: DebugConfiguration,
    parentSessionOrOptions: DebugSession = definedExternally
): Thenable<Boolean>

external fun startDebugging(folder: WorkspaceFolder?, nameOrConfiguration: DebugConfiguration): Thenable<Boolean>

external fun startDebugging(
    folder: WorkspaceFolder?,
    nameOrConfiguration: DebugConfiguration,
    parentSessionOrOptions: DebugSessionOptions = definedExternally
): Thenable<Boolean>

external fun stopDebugging(session: DebugSession = definedExternally): Thenable<Unit>

external fun addBreakpoints(breakpoints: Array<Breakpoint>)

external fun removeBreakpoints(breakpoints: Array<Breakpoint>)

external fun asDebugSourceUri(source: DebugProtocolSource, session: DebugSession = definedExternally): Uri
