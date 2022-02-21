package com.aercie.ftc.cv

enum class DuckState {
    A,
    B,
    C;

    override fun toString(): String = when (this) {
        A -> "State A"
        B -> "State B"
        C -> "State C"
    }
}
