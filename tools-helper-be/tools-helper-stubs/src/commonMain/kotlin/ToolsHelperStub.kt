import ToolsHelperStubOrder.Order_CREATED_NEW_ORDER
import models.*


object ToolsHelperStub {
    fun get(): ToolsHelperOrder = Order_CREATED_NEW_ORDER.copy()

    fun prepareSearchList(ownerId: String, operationId: String, status: ToolsHelperOrderStatus) = listOf(
        thOrderCompleted("com-111-01", ownerId, operationId, status),
        thOrderCompleted("com-111-02", ownerId, operationId, status),
        thOrderCompleted("com-111-03", ownerId, operationId, status),
        thOrderCompleted("com-111-04", ownerId, operationId, status),
        thOrderCompleted("com-111-05", ownerId, operationId, status),
        thOrderCompleted("com-111-06", ownerId, operationId, status)
    )


    private fun thOrderCompleted(id: String, ownerId: String, operationId: String, status: ToolsHelperOrderStatus) =
        thOrder(Order_CREATED_NEW_ORDER, id = id, ownerId = ownerId, status = status, operationId = operationId)

    private fun thOrder(
        base: ToolsHelperOrder,
        id: String,
        ownerId: String,
        operationId: String,
        status: ToolsHelperOrderStatus
    ) = base.copy(
        id = ToolsHelperOrderId(id),
        orderStatus = status,
        ownerId = ToolsHelperEmployeeId(ownerId),
        operationId = ToolsHelperOperationId(operationId),
    )
}