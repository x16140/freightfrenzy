package com.aercie.manifest

val programs = manifest {
//    program(
//        program = "com.aercie.ftc.Debug",
//        name = "Debug",
//        type = ProgramType.Controlled,
//    )

    program(
        program = "com.aercie.ftc.Drive",
        name = "Drive",
        type = ProgramType.Controlled,
    )
}
