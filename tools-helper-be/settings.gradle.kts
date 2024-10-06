dependencyResolutionManagement {
    versionCatalogs{
        create("libs"){
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement{
    repositories{
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.5.0")
}

rootProject.name = "otus-tools-storage-helper"
include(":tools-helper-temp")