

import kotlinx.datetime.Instant
import models.*
import models.ToolsHelperWorkMode
import stubs.ToolsHelperStubs


data class ToolsHelperContext(
    var command: ToolsHelperCommand = ToolsHelperCommand.NONE,
    var state: ToolsHelperOrderStatus = ToolsHelperOrderStatus.NONE,
    val errors: MutableList<ToolsHelperError> = mutableListOf(),

    var workMode: ToolsHelperWorkMode = ToolsHelperWorkMode.PROD,
    var stubCase: ToolsHelperStubs = ToolsHelperStubs.NONE,

    var requestId: ToolsHelperRequestId = ToolsHelperRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var orderRequest: ToolsHelperOrder = ToolsHelperOrder(),
    var orderFilterRequest: ToolsHelperOrderFilter = ToolsHelperOrderFilter(),

    var orderResponse: ToolsHelperOrder = ToolsHelperOrder(),
    var ordersResponse: MutableList<ToolsHelperOrder> = mutableListOf(),

    )
