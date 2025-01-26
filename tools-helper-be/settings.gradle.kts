dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
    repositories {
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
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

rootProject.name = "tools-helper-be"

// Включает вот такую конструкцию
//implementation(projects.m2l5Gradle.sub1.ssub1)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":tools-helper-api-v1-kmp")
include(":tools-helper-common")
include(":tools-helper-api-v1-mapper")
include(":tools-helper-app-ktor")
include(":tools-helper-stubs")
include(":tools-helper-biz")
include(":tools-helper-app-common")
include(":tools-helper-api-log1")
include(":tools-helper-app-ktor")
include(":tools-helper-repo-common")
include(":tools-helper-repo-tests")
include(":tools-helper-repo-inmemory")
include(":tools-helper-repo-stubs")