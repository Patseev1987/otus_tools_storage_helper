plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("build-jvm") {
            id = "build-jvm"
            implementationClass = "ru.otus.otuskotlin.marketplace.plugin.BuildPluginJvm"
        }
        register("build-kmp") {
            id = "build-kmp"
            implementationClass = "ru.otus.otuskotlin.marketplace.plugin.BuildPluginMultiplatform"
        }
        register("build-simple-plugin") {
            id = "build-simple-plugin"
            implementationClass = "ru.otus.otuskotlin.marketplace.plugin.BuildBogdanSimplePlugin"
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
