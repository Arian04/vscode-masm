@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package tsstdlib

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import kotlin.js.Date
import kotlin.js.RegExp

external interface DateConstructor {
    @nativeInvoke
    operator fun invoke(): String
    val prototype: Date
    fun parse(s: String): Number
    fun UTC(
        year: Number,
        month: Number,
        date: Number = definedExternally,
        hours: Number = definedExternally,
        minutes: Number = definedExternally,
        seconds: Number = definedExternally,
        ms: Number = definedExternally
    ): Number

    fun now(): Number
}

typealias RegExpMatchArray = Array<String>

typealias RegExpExecArray = Array<String>

external interface RegExpConstructor {
    @nativeGetter
    operator fun get(key: String): String?

    @nativeSetter
    operator fun set(key: String, value: String)

    @nativeInvoke
    operator fun invoke(pattern: RegExp, flags: String = definedExternally): RegExp

    @nativeInvoke
    operator fun invoke(pattern: RegExp): RegExp

    @nativeInvoke
    operator fun invoke(pattern: String, flags: String = definedExternally): RegExp

    @nativeInvoke
    operator fun invoke(pattern: String): RegExp
    val prototype: RegExp
    var lastMatch: String
}

external interface ErrorConstructor {
    @nativeInvoke
    operator fun invoke(message: String = definedExternally): Error
    val prototype: Error
}

external interface ConcatArray<T> {
    val length: Number

    @nativeGetter
    operator fun get(n: Number): T?

    @nativeSetter
    operator fun set(n: Number, value: T)
    fun join(separator: String = definedExternally): String
    fun slice(start: Number = definedExternally, end: Number = definedExternally): Array<T>
}

external interface ArrayConstructor {
    fun <T> from(iterable: Iterable<T>): Array<T>
    fun <T> from(iterable: ArrayLike<T>): Array<T>
    fun <T, U> from(iterable: Iterable<T>, mapfn: (v: T, k: Number) -> U, thisArg: Any = definedExternally): Array<U>
    fun <T, U> from(iterable: Iterable<T>, mapfn: (v: T, k: Number) -> U): Array<U>
    fun <T, U> from(iterable: ArrayLike<T>, mapfn: (v: T, k: Number) -> U, thisArg: Any = definedExternally): Array<U>
    fun <T, U> from(iterable: ArrayLike<T>, mapfn: (v: T, k: Number) -> U): Array<U>
    fun <T> of(vararg items: T): Array<T>

    @nativeInvoke
    operator fun invoke(arrayLength: Number = definedExternally): Array<Any>

    @nativeInvoke
    operator fun invoke(): Array<Any>

    @nativeInvoke
    operator fun <T> invoke(arrayLength: Number): Array<T>

    @nativeInvoke
    operator fun <T> invoke(vararg items: T): Array<T>
    fun isArray(arg: Any): Boolean
    val prototype: Array<Any>
}

external interface PromiseLike<T> {
    fun then(
        onfulfilled: ((value: T) -> Any?)? = definedExternally,
        onrejected: ((reason: Any) -> Any?)? = definedExternally
    ): PromiseLike<dynamic /* TResult1 | TResult2 */>
}

external interface ArrayLike<T> {
    val length: Number

    @nativeGetter
    operator fun get(n: Number): T?

    @nativeSetter
    operator fun set(n: Number, value: T)
}

typealias Readonly<T> = Any

typealias Record<K, T> = Any

external interface ArrayBufferTypes {
    var ArrayBuffer: ArrayBuffer
}

external interface ArrayBufferConstructor {
    val prototype: ArrayBuffer
    fun isView(arg: Any): Boolean
}

external interface Uint8ArrayConstructor {
    fun from(
        arrayLike: Iterable<Number>,
        mapfn: (v: Number, k: Number) -> Number = definedExternally,
        thisArg: Any = definedExternally
    ): Uint8Array

    fun from(arrayLike: Iterable<Number>): Uint8Array
    fun from(arrayLike: Iterable<Number>, mapfn: (v: Number, k: Number) -> Number = definedExternally): Uint8Array
    val prototype: Uint8Array
    val BYTES_PER_ELEMENT: Number
    fun of(vararg items: Number): Uint8Array
    fun from(arrayLike: ArrayLike<Number>): Uint8Array
    fun <T> from(
        arrayLike: ArrayLike<T>,
        mapfn: (v: T, k: Number) -> Number,
        thisArg: Any = definedExternally
    ): Uint8Array

    fun <T> from(arrayLike: ArrayLike<T>, mapfn: (v: T, k: Number) -> Number): Uint8Array
}

external interface Uint32ArrayConstructor {
    fun from(
        arrayLike: Iterable<Number>,
        mapfn: (v: Number, k: Number) -> Number = definedExternally,
        thisArg: Any = definedExternally
    ): Uint32Array

    fun from(arrayLike: Iterable<Number>): Uint32Array
    fun from(arrayLike: Iterable<Number>, mapfn: (v: Number, k: Number) -> Number = definedExternally): Uint32Array
    val prototype: Uint32Array
    val BYTES_PER_ELEMENT: Number
    fun of(vararg items: Number): Uint32Array
    fun from(arrayLike: ArrayLike<Number>): Uint32Array
    fun <T> from(
        arrayLike: ArrayLike<T>,
        mapfn: (v: T, k: Number) -> Number,
        thisArg: Any = definedExternally
    ): Uint32Array

    fun <T> from(arrayLike: ArrayLike<T>, mapfn: (v: T, k: Number) -> Number): Uint32Array
}