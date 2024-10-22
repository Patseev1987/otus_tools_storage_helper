plugins{
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin{
    jvm("desktop")

    sourceSets{
        val javaMain = getByName("desktopMain"){
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop{
    application{
        mainClass = "MainKt"
    }
}