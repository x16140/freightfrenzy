package com.aercie.manifest

val programs = manifest {
    program(
        program = "com.aercie.ftc.Debug",
        name = "Debug",
        group = "Debug",
        type = ProgramType.Controlled,
    )

    program(
        program = "com.aercie.ftc.Drive",
        name = "Drive",
        group = "Main",
        type = ProgramType.Controlled,
    )

    program(
        program = "com.aercie.ftc.InitPersistence",
        name = "Initialize Persistence",
        group = "Calibration",
        type = ProgramType.Controlled,
    )

    program(
        program = "com.aercie.ftc.Calibration",
        name = "Calibration",
        group = "Calibration",
        type = ProgramType.Controlled,
    )

    program(
        program = "com.aercie.ftc.Autonomous",
        name = "Autonomous",
        group = "Autonomous",
        type = ProgramType.Autonomous,
    )
}
