import ru.patseev.helper.logging.ToolsHelperLoggerProvider

data class ToolsHelperCorSettings(
    val loggerProvider: ToolsHelperLoggerProvider = ToolsHelperLoggerProvider(),
) {
    companion object {
        val NONE = ToolsHelperCorSettings()
    }
}
