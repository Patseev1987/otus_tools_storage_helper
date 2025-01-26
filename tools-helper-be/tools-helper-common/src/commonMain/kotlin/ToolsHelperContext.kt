import kotlinx.datetime.Instant
import models.*
import repo.IRepoOrder
import stubs.ToolsHelperStubs
import ws.IToolsHelperWsSession


data class ToolsHelperContext(
    var command: ToolsHelperCommand = ToolsHelperCommand.NONE,
    var state: ToolsHelperState = ToolsHelperState.NONE,
    val errors: MutableList<ToolsHelperError> = mutableListOf(),

    var corSettings: ToolsHelperCorSettings = ToolsHelperCorSettings(),
    var workMode: ToolsHelperWorkMode = ToolsHelperWorkMode.PROD,
    var stubCase: ToolsHelperStubs = ToolsHelperStubs.NONE,
    var wsSession: IToolsHelperWsSession = IToolsHelperWsSession.NONE,

    var requestId: ToolsHelperRequestId = ToolsHelperRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var orderRequest: ToolsHelperOrder = ToolsHelperOrder(),
    var orderFilterRequest: ToolsHelperOrderFilter = ToolsHelperOrderFilter(),

    var orderValidating: ToolsHelperOrder = ToolsHelperOrder(),
    var orderFilterValidating: ToolsHelperOrderFilter = ToolsHelperOrderFilter(),

    var orderValidated: ToolsHelperOrder = ToolsHelperOrder(),
    var orderFilterValidated: ToolsHelperOrderFilter = ToolsHelperOrderFilter(),

    var orderRepo: IRepoOrder = IRepoOrder.NONE,
    var orderRepoRead: ToolsHelperOrder = ToolsHelperOrder(), // То, что прочитали из репозитория
    var orderRepoPrepare: ToolsHelperOrder = ToolsHelperOrder(), // То, что готовим для сохранения в БД
    var orderRepoDone: ToolsHelperOrder = ToolsHelperOrder(),  // Результат, полученный из БД
    var ordersRepoDone: MutableList<ToolsHelperOrder> = mutableListOf(),

    var orderResponse: ToolsHelperOrder = ToolsHelperOrder(),
    var ordersResponses: MutableList<ToolsHelperOrder> = mutableListOf(),

    )
