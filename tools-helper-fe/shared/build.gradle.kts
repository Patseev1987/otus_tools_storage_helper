plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.material)
            }
        }

        jvmMain {
            dependencies {
                api(compose.desktop.currentOs)
            }
        }
    }
}

