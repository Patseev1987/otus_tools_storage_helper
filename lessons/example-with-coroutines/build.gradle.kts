plugins{
    alias(libs.plugins.kotlin.jvm)
}

dependencies{
    implementation(libs.coroutines.core)
    implementation(libs.okhttp.client)
    implementation(libs.jackson.module)
    implementation(libs.kotlin.test)
}

