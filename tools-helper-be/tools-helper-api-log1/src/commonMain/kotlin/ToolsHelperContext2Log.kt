import kotlinx.datetime.Clock
import models.*
import ru.patseev.helper.api.log1.models.*

fun ToolsHelperContext.toLog(logId: String) = CommonLogModel(
    messageTime = Clock.System.now().toString(),
    logId = logId,
    source = "tools-helper",
    order = toToolsHelperLog(),
    errors = errors.map { it.toLog() },
)

private fun ToolsHelperContext.toToolsHelperLog(): ToolsHelperOrderLogModel? {
    val orderNone = ToolsHelperOrder()
    return ToolsHelperOrderLogModel(
        requestId = requestId.takeIf { it != ToolsHelperRequestId.NONE }?.asString(),
        requestOrder = orderRequest.takeIf { it != orderNone }?.toLog(),
        responseOrder = orderResponse.takeIf { it != orderNone }?.toLog(),
        responseOrders = ordersResponses.takeIf { it.isNotEmpty() }?.filter { it != orderNone }?.map { it.toLog() },
        requestFilter = orderFilterRequest.takeIf { it != ToolsHelperOrderFilter() }?.toLog(),
    ).takeIf { it != ToolsHelperOrderLogModel() }
}

private fun ToolsHelperOrderFilter.toLog() = OrderFilterLog(
    searchString = searchString.takeIf { it.isNotBlank() },
    ownerId = ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.asString(),
)

private fun ToolsHelperError.toLog() = ErrorLogModel(
    message = message.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    code = code.takeIf { it.isNotBlank() },
    level = level.name
)

private fun ToolsHelperOrder.toLog() = OrderLog(
    id = id.takeIf { it != ToolsHelperOrderId.NONE }?.asString(),

    ownerId = ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.asString(),
    permissions = permissionsClient.takeIf { it.isNotEmpty() }?.map { it.name }?.toSet(),
)
