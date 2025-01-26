plugins {
    id("build-kmp")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.toolsHelperCommon)
                api(projects.toolsHelperRepoCommon)
                implementation(libs.coroutines.core)
                implementation(libs.db.cache4k)
                implementation(libs.uuid)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(projects.toolsHelperRepoTests)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}
