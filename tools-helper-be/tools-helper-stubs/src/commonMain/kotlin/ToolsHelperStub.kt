import ToolsHelperStubOrder.Order_CREATED_NEW_ORDER
import models.*


object ToolsHelperStub {

    fun get(): ToolsHelperOrder = Order_CREATED_NEW_ORDER.copy()

    fun prepareResult(block: ToolsHelperOrder.() -> Unit): ToolsHelperOrder = get().apply(block)

    fun prepareSearchList(ownerId: String, status: ToolsHelperOrderStatus, filter: String) = listOf(
        thOrderCompleted("com-111-01", ownerId, status, filter),
        thOrderCompleted("com-111-02", ownerId, status, filter),
        thOrderCompleted("com-111-03", ownerId, status, filter),
        thOrderCompleted("com-111-04", ownerId, status, filter),
        thOrderCompleted("com-111-05", ownerId, status, filter),
        thOrderCompleted("com-111-06", ownerId, status, filter)
    )


    private fun thOrderCompleted(id: String, ownerId: String, status: ToolsHelperOrderStatus, filter: String) =
        thOrder(Order_CREATED_NEW_ORDER, id = id, ownerId = ownerId, status = status, filter = filter)

    private fun thOrder(
        base: ToolsHelperOrder,
        id: String,
        ownerId: String,
        status: ToolsHelperOrderStatus,
        filter: String
    ) = base.copy(
        id = ToolsHelperOrderId(id),
        orderStatus = status,
        ownerId = ToolsHelperEmployeeId(ownerId),
        operationId = ToolsHelperOperationId("$filter 123321"),
    )
}