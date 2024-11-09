dependencyResolutionManagement {
    versionCatalogs{
        create("libs"){
            from(files("../gradle/libs.versions.toml"))
        }
    }
    repositories{
        gradlePluginPortal()
        mavenCentral()
    }
}

pluginManagement {
    includeBuild("../bogdan-plugins")
    plugins {
        id("build-jvm") apply false
        id("build-kmp") apply false
    }
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.5.0")
}

rootProject.name = "tools-helper-be"

include(":tools-helper-v1-kmp")
include(":tools-helper-common")
include(":tools-helper-mapper")