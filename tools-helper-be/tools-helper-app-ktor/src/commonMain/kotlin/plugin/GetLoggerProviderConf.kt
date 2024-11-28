package ru.patseev.helper.ktor.plugin

import io.ktor.server.application.*
import ru.patseev.helper.logging.ToolsHelperLoggerProvider
import ru.patseev.helper.logging.kermit.toolsHelperLoggerKermit

fun Application.getLoggerProviderConf(): ToolsHelperLoggerProvider =
    when (val mode = environment.config.propertyOrNull("ktor.logger")?.getString()) {
        "kmp", null -> ToolsHelperLoggerProvider { toolsHelperLoggerKermit(it) }
        else -> throw Exception("Logger $mode is not allowed. Additted values are kmp and socket")
    }