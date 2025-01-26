package ru.patseev.helper.ktor.plugin

import ToolsHelperCorSettings
import io.ktor.server.application.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ToolsHelperOrderProcessor
import base.KtorWsSessionRepo
import ru.patseev.helper.ktor.ws.KtorWsSession

fun Application.initAppSettings(): ToolsHelperAppSettings {
    val corSettings = ToolsHelperCorSettings(
        loggerProvider = getLoggerProviderConf(),
        wsSessions = KtorWsSessionRepo(),
        repoStub = OrderRepoStub(),
        repoTest = OrderRepoInMemory(),
        repoProd = OrderRepoInMemory(),
    )
    return ToolsHelperAppSettings(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        corSettings = corSettings,
        processor = ToolsHelperOrderProcessor(corSettings),
    )
}