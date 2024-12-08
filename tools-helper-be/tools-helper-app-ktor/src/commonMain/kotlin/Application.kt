package ru.patseev.helper.ktor

import io.ktor.server.application.*
import ru.patseev.helper.ktor.plugin.configureCORS
import ru.patseev.helper.ktor.plugin.configureRouting
import ru.patseev.helper.ktor.plugin.configureWebSocket
import ru.patseev.helper.ktor.plugin.initAppSettings

fun Application.module(
    appSettings: ToolsHelperAppSettings = initAppSettings()
) {
    configureCORS()
    configureRouting(appSettings)
    configureWebSocket(appSettings)
}