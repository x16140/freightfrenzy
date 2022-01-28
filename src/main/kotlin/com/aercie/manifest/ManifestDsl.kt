package com.aercie.manifest

typealias Manifest = MutableList<ManifestEntry>

inline fun manifest(f: Manifest.() -> Unit): Array<ManifestEntry> {
    val dsl = mutableListOf<ManifestEntry>()
    f(dsl)
    return dsl.toTypedArray()
}

fun Manifest.program(
    program: String,
    name: String,
    group: String,
    type: ProgramType
) = add(ManifestEntry(name, group, type.k, program))

enum class ProgramType(val k: Int) {
    Autonomous(0),
    Controlled(1),
    Disabled(2),
}

class ManifestEntry(
    @JvmField val name: String,
    @JvmField val group: String,
    @JvmField val type: Int,
    @JvmField val program: String,
)
