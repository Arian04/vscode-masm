@file:JsModule("vscode")
@file:JsQualifier("chat")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.chat

import vscode.ChatParticipant
import vscode.ChatRequestHandler

external fun createChatParticipant(id: String, handler: ChatRequestHandler): ChatParticipant
