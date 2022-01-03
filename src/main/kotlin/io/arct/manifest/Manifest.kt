package io.arct.manifest

val programs = manifest {
    program(
        program = "io.arct.ftc.Debug",
        name = "Debug",
        type = ProgramType.Controlled
    )
}
