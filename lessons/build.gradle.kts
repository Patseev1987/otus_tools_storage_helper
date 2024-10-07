plugins {
    alias(libs.plugins.kotlin.jvm)
}


repositories {
    mavenCentral()
    mavenLocal()
}



subprojects {
    repositories {
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version

}

kotlin {
    jvmToolchain(17)
}