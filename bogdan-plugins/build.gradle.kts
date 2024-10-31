plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("build-jvm") {
            id = "build-jvm"
            implementationClass = "BuildPluginJvm"
        }
        register("build-kmp") {
            id = "build-kmp"
            implementationClass = "BuildPluginMultiplatform"
        }
        register("build-simple-plugin") {
            id = "build-simple-plugin"
            implementationClass = "BuildBogdanSimplePlugin"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain?.codeSource?.location))
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.binaryCompatibilityValidator)

}
