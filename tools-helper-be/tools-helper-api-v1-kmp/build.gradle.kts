import org.openapitools.generator.gradle.plugin.tasks.GenerateTask


plugins {
    id("build-kmp")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.crowdproj.generator)
}

kotlin {
    sourceSets {
        val serializationVersion: String by project
        val commonMain by getting {
            kotlin.srcDirs(layout.buildDirectory.dir("generate-resources/src/commonMain/kotlin"))
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

crowdprojGenerate {
    val specDir = layout.projectDirectory.dir("../../specs")
    packageName.set("${rootProject.group}.api")
    inputSpec.set("$specDir/specs-order-v1.yaml")
}


tasks {
    val openApiGenerateTask: GenerateTask = getByName("openApiGenerate", GenerateTask::class) {
        outputDir.set(layout.buildDirectory.file("generate-resources").get().toString())
        finalizedBy("compileCommonMainKotlinMetadata")
    }
    filter { it.name.startsWith("compile") }.forEach {
        it.dependsOn(openApiGenerateTask)
    }
}
