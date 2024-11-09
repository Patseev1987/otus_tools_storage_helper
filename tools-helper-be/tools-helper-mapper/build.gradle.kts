plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":tools-helper-common"))
    implementation(project(":tools-helper-v1-kmp"))

    testImplementation(kotlin("test-junit"))
}
