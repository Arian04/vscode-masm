@file:JsModule("vscode")
@file:JsQualifier("tasks")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.tasks

import vscode.*

external fun registerTaskProvider(type: String, provider: TaskProvider__0): Disposable

external fun fetchTasks(filter: TaskFilter = definedExternally): Thenable<Array<Task>>

external fun executeTask(task: Task): Thenable<TaskExecution>

external var taskExecutions: Array<TaskExecution>

external var onDidStartTask: Event<TaskStartEvent>

external var onDidEndTask: Event<TaskEndEvent>

external var onDidStartTaskProcess: Event<TaskProcessStartEvent>

external var onDidEndTaskProcess: Event<TaskProcessEndEvent>
