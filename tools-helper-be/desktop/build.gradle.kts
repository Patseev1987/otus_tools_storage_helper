
plugins{
    kotlin("multiplatform")
    id("build-simple-plugin")
}

kotlin{
    jvm("desktop")
    sourceSets{
        val javaMain = getByName("desktopMain"){
            dependencies {
                implementation(":multiplatform-temp")
            }
        }
    }
}

