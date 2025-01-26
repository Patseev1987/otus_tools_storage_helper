import repo.IRepoOrder
import ru.patseev.helper.logging.ToolsHelperLoggerProvider
import ws.IToolsHelperWsSessionRepo

data class ToolsHelperCorSettings(
    val loggerProvider: ToolsHelperLoggerProvider = ToolsHelperLoggerProvider(),
    val wsSessions: IToolsHelperWsSessionRepo = IToolsHelperWsSessionRepo.NONE,
    val repoStub: IRepoOrder = IRepoOrder.NONE,
    val repoTest: IRepoOrder = IRepoOrder.NONE,
    val repoProd: IRepoOrder = IRepoOrder.NONE,
) {
    companion object {
        val NONE = ToolsHelperCorSettings()
    }
}
