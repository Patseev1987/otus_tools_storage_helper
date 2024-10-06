
plugins{
    kotlin("multiplatform")
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

