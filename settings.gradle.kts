pluginManagement {
    plugins{
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "otus-tools-storage-helper"
include("m1f1-first-app")
