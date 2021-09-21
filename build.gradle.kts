plugins {
    kotlin("jvm") version "1.5.30"
    java
}

group = "io.arct.ftc"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(fileTree(mapOf("dir" to "libs/sdk", "include" to listOf("*.jar"))))
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }

    test {
        java.srcDirs()
    }
}

task<Exec>("dex") {
    group = "android"
    dependsOn(tasks.withType(Jar::class))

    workingDir("./bin")
    commandLine("cmd", "/c", "dex.bat")
}

task<Exec>("upload") {
    group = "android"
    dependsOn("dex")

    workingDir("./bin")
    commandLine("cmd", "/c", "upload.bat")
}
