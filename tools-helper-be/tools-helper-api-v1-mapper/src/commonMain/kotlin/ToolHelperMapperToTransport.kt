import exceptions.UnknownToolsHelperCommand
import models.*
import ru.patseev.helper.api.models.*


fun ToolsHelperContext.toTransportOrder(): IResponse = when (val cmd = command) {
    ToolsHelperCommand.READ -> toTransportRead()
    ToolsHelperCommand.CREATE -> toTransportCreate()
    ToolsHelperCommand.DELETE -> toTransportDelete()
    ToolsHelperCommand.UPDATE -> toTransportUpdate()
    ToolsHelperCommand.SEARCH -> toTransportSearch()
    ToolsHelperCommand.NONE -> throw UnknownToolsHelperCommand(cmd)
}

fun ToolsHelperContext.toTransportCreate() = OrderCreateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    order = orderResponse.toTransportOrder()
)

fun ToolsHelperContext.toTransportRead() = OrderReadResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    order = orderResponse.toTransportOrder()
)

fun ToolsHelperContext.toTransportUpdate() = OrderUpdateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    order = orderResponse.toTransportOrder()
)

fun ToolsHelperContext.toTransportDelete() = OrderCreateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    order = orderResponse.toTransportOrder()
)

fun ToolsHelperContext.toTransportSearch() = OrderSearchResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    // order = orderResponse.toTransportOrder()
)

fun List<ToolsHelperOrder>.toTransportOrder(): List<OrderResponseObject>? = this
    .map { it.toTransportOrder() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ToolsHelperOrder.toTransportOrder(): OrderResponseObject = OrderResponseObject(
    id = id.takeIf { it != ToolsHelperOrderId.NONE }?.asString(),
    operationId = operationId.takeIf { it != ToolsHelperOperationId.NONE }?.asString(),
    employeeId = ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.asString(),
    createTime = createTime.toString(),
    orderStatus = orderStatus.toTransportOrder(),
    partCount = partCount.takeIf { it != 0 },
    tools = tools.takeIf { it.isNotEmpty() },
    missingTools = missingTools
)

private fun Set<ToolsHelperOrderPermissionClient>.toTransportOrder() = this
    .map { it.toTransportOrder() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun ToolsHelperOrderPermissionClient.toTransportOrder() = when (this) {
    ToolsHelperOrderPermissionClient.READ -> OrderPermissions.READ
    ToolsHelperOrderPermissionClient.UPDATE -> OrderPermissions.UPDATE
    ToolsHelperOrderPermissionClient.DELETE -> OrderPermissions.DELETE
    ToolsHelperOrderPermissionClient.CHANGE_STATUS -> OrderPermissions.CHANGE_STATUS
}

private fun ToolsHelperOrderStatus.toTransportOrder() = when (this) {
    ToolsHelperOrderStatus.CREATED -> OrderStatus.CREATED
    ToolsHelperOrderStatus.NONE -> null
    ToolsHelperOrderStatus.IN_PROGRESS -> OrderStatus.IN_PROGRESS
    ToolsHelperOrderStatus.COMPLETED -> OrderStatus.COMPLETED
    ToolsHelperOrderStatus.FINISHED -> OrderStatus.FINISHED
}

private fun List<ToolsHelperError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportOrder() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ToolsHelperError.toTransportOrder() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun ToolsHelperState.toResult(): ResponseResult? = when (this) {
    ToolsHelperState.NONE -> null
    ToolsHelperState.RUNNING -> ResponseResult.SUCCESS
    ToolsHelperState.FAILING -> ResponseResult.ERROR
    ToolsHelperState.FINISHING -> ResponseResult.SUCCESS
}