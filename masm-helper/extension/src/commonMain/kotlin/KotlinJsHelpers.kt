import kotlin.js.Json

fun <T> Json.toObject(): T = this.unsafeCast<T>()

fun <T> makeJsObject(): T {
    return js("{}").unsafeCast<T>()
}

fun <T> makeJsObject(action: (T).() -> Unit): T {
    return js("{}").unsafeCast<T>().apply(action)
}