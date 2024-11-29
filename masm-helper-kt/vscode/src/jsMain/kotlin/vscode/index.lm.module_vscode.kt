@file:JsModule("vscode")
@file:JsQualifier("lm")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.lm

import vscode.*

external var onDidChangeChatModels: Event<Unit>

external fun selectChatModels(selector: LanguageModelChatSelector = definedExternally): Thenable<Array<LanguageModelChat>>

external fun <T> registerTool(name: String, tool: LanguageModelTool<T>): Disposable

external var tools: Array<LanguageModelToolInformation>

external fun invokeTool(
    name: String,
    options: LanguageModelToolInvocationOptions<Any?>,
    token: CancellationToken = definedExternally
): Thenable<LanguageModelToolResult>
