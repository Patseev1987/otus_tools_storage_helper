package ru.patseev.helper.logging

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * Инициализирует выбранный логер
 *
 * ```kotlin
 * // Обычно логер вызывается вот так
 * val logger = LoggerFactory.getLogger(this::class.java)
 * // Мы создаем экземпляр логер-провайдера вот так
 * val loggerProvider = ToolsHelperLoggerProvider { clazz -> toolsHelperLoggerKermit(clazz) }
 *
 * // В дальнейшем будем использовать этот экземпляр вот так:
 * val logger = loggerProvider.logger(this::class)
 * logger.info("My log")
 * ```
 */
class ToolsHelperLoggerProvider(
    private val provider: (String) -> IToolsHelperLogWrapper = { IToolsHelperLogWrapper.DEFAULT }
) {
    /**
     * Инициализирует и возвращает экземпляр логера
     */
    fun logger(loggerId: String): IToolsHelperLogWrapper = provider(loggerId)

    /**
     * Инициализирует и возвращает экземпляр логера
     */
    fun logger(clazz: KClass<*>): IToolsHelperLogWrapper = provider(clazz.qualifiedName ?: clazz.simpleName ?: "(unknown)")

    /**
     * Инициализирует и возвращает экземпляр логера
     */
    fun logger(function: KFunction<*>): IToolsHelperLogWrapper = provider(function.name)
}

