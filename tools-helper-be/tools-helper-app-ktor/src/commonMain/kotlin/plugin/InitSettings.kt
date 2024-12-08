package ru.patseev.helper.ktor.plugin

import ToolsHelperCorSettings
import io.ktor.server.application.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.toolsHelper.biz.ToolsHelperOrderProcessor

fun Application.initAppSettings(): ToolsHelperAppSettings {
    val corSettings = ToolsHelperCorSettings(
        loggerProvider = getLoggerProviderConf(),
    )
    return ToolsHelperAppSettings(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        corSettings = corSettings,
        processor = ToolsHelperOrderProcessor(corSettings),
    )
}