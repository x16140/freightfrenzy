package com.aercie.util.extensions

val Boolean.value: Int
    get() = if (this) 1 else 0

private val risingMap = mutableMapOf<() -> Boolean, Boolean>()

fun (() -> Boolean).onRising(f: () -> Unit) {
    val state = this()
    val previous = if (this in risingMap) risingMap[this]!! else false
    risingMap[this] = state

    if (!previous && state)
        f()
}
