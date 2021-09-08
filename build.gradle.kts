plugins {
    kotlin("jvm") version "1.5.30"
    java
}

allprojects {
    apply(plugin = "java")

    group = "io.arct.ftc"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }

        test {
            java.srcDirs()
        }
    }
}
