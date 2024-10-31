plugins{
    alias(libs.plugins.kotlin.multiplatform) apply false
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "ru.patseev.helper"
version = "0.0.1"

subprojects {
    repositories {
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version

}

