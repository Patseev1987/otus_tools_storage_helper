plugins {
    kotlin("jvm") apply false
}

group = "ru.patseev"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

kotlin {
    jvmToolchain(21)
}

subprojects{
    group = rootProject.group
    version = rootProject.version
}