pluginManagement {
    plugins{
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}
rootProject.name = "otus-tools-storage-helper"
includeBuild("lessons")

includeBuild("lessons")
includeBuild("tools-helper-be")
includeBuild("tools-helper-fe")