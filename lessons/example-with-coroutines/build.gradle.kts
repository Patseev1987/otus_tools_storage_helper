plugins{
    alias(libs.plugins.kotlin.jvm)
}

dependencies{
    implementation(libs.coroutines)
    implementation(libs.okhttp.client)
    implementation(libs.jackson.module)
    implementation(libs.kotlin.test)
}

