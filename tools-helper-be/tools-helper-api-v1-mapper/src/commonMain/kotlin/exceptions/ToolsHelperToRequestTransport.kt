package exceptions

import models.*
import ru.patseev.helper.api.models.OrderCreateObject
import ru.patseev.helper.api.models.OrderDeleteObject
import ru.patseev.helper.api.models.OrderReadObject
import ru.patseev.helper.api.models.OrderUpdateObject
import toTransportOrder

fun ToolsHelperOrder.toTransportCreate() = OrderCreateObject(
    operationId = operationId.takeIf { it != ToolsHelperOperationId.NONE }?.asString(),
    employeeId = ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.asString(),
    createTime = createTime.epochSeconds.toString(),
    partCount = partCount,
    orderStatus = orderStatus.toTransportOrder(),
)

fun ToolsHelperOrder.toTransportRead() = OrderReadObject(
    id = id.takeIf { it != ToolsHelperOrderId.NONE }?.asString(),
)

fun ToolsHelperOrder.toTransportUpdate() = OrderUpdateObject(
    id = id.takeIf { it != ToolsHelperOrderId.NONE }?.asString(),
    lock = lock.takeIf { it != ToolsHelperOrderLock.NONE }?.asString(),
    operationId = operationId.takeIf { it != ToolsHelperOperationId.NONE }?.asString(),
    employeeId = ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.asString(),
    createTime = createTime.epochSeconds.toString(),
    partCount = partCount,
)

fun ToolsHelperOrder.toTransportDelete() = OrderDeleteObject(
    id = id.takeIf { it != ToolsHelperOrderId.NONE }?.asString(),
    lock = lock.takeIf { it != ToolsHelperOrderLock.NONE }?.asString(),
)