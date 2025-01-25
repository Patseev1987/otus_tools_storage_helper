import ru.patseev.helper.logging.ToolsHelperLoggerProvider
import ws.IToolsHelperWsSessionRepo

data class ToolsHelperCorSettings(
    val loggerProvider: ToolsHelperLoggerProvider = ToolsHelperLoggerProvider(),
    val wsSessions: IToolsHelperWsSessionRepo = IToolsHelperWsSessionRepo.NONE,
) {
    companion object {
        val NONE = ToolsHelperCorSettings()
    }
}
