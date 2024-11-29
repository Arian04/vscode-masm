@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode

import tsstdlib.Iterable

typealias CancellationError = Error

typealias DefinitionLink = LocationLink

typealias DiagnosticCollection = Iterable<dynamic /* JsTuple<uri> */>

typealias FileSystemError = Error

typealias DataTransfer = Iterable<dynamic /* JsTuple<mimeType> */>
