plugins {
    id("build-kmp")
}

kotlin {
    sourceSets {
        all { languageSettings.optIn("kotlin.RequiresOptIn") }

        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":tools-helper-common"))
                implementation(project(":tools-helper-stubs"))

                implementation(libs.cor)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(libs.coroutines.test)
                implementation(projects.toolsHelperRepoStubs)
                implementation(projects.toolsHelperRepoInmemory)
            }
        }
        jvmMain {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}
