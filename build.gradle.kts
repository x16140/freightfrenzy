plugins {
    kotlin("jvm") version "1.5.30"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.30"
    java
}

group = "com.aercie.ftc"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(fileTree(mapOf("dir" to "libs/android", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs/sdk", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
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
