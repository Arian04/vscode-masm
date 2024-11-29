@file:JsModule("vscode")
@file:JsQualifier("tests")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.tests

import vscode.TestController

external fun createTestController(id: String, label: String): TestController
