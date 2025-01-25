dependencyResolutionManagement {
    repositories{
        google()
        mavenCentral()
    }
}
rootProject.name = "otus-tools-storage-helper"

includeBuild("lessons")
includeBuild("tools-helper-be")
includeBuild("tools-helper-fe")
includeBuild("tools-helper-libs")