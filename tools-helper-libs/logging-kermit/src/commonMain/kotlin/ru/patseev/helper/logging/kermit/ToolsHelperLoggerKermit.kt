package ru.patseev.helper.logging.kermit

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import ru.patseev.helper.logging.IToolsHelperLogWrapper
import kotlin.reflect.KClass

@Suppress("unused")
fun toolsHelperLoggerKermit(loggerId: String): IToolsHelperLogWrapper {
    val logger = Logger(
        config = StaticConfig(
            minSeverity = Severity.Info,
        ),
        tag = "DEV"
    )
    return ToolsHelperLoggerWrapperKermit(
        logger = logger,
        loggerId = loggerId,
    )
}

@Suppress("unused")
fun toolsHelperLoggerKermit(cls: KClass<*>): IToolsHelperLogWrapper {
    val logger = Logger(
        config = StaticConfig(
            minSeverity = Severity.Info,
        ),
        tag = "DEV"
    )
    return ToolsHelperLoggerWrapperKermit(
        logger = logger,
        loggerId = cls.qualifiedName?: "",
    )
}
