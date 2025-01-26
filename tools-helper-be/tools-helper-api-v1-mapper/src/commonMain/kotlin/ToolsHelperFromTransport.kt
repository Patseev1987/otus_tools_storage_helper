import exceptions.UnknownRequestClass
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import models.*
import ru.patseev.helper.api.models.*
import stubs.ToolsHelperStubs

private const val EMPTY_STRING = ""
private const val ZERO = 0

fun ToolsHelperContext.fromTransport(request: IRequest) = when (request) {
    is OrderCreateRequest -> fromTransport(request)
    is OrderDeleteRequest -> fromTransport(request)
    is OrderReadRequest -> fromTransport(request)
    is OrderSearchRequest -> fromTransport(request)
    is OrderUpdateRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.toString())
}

private fun String?.toOrderId() = this?.let { ToolsHelperOrderId(it) } ?: ToolsHelperOrderId.NONE
private fun String?.toOrderWithId() = ToolsHelperOrder(id = this.toOrderId())
private fun String?.toOrderLock() = this?.let { ToolsHelperOrderLock(it) } ?: ToolsHelperOrderLock.NONE

private fun OrderDebug?.transportToWorkMode(): ToolsHelperWorkMode = when (this?.mode) {
    OrderRequestDebugMode.PROD -> ToolsHelperWorkMode.PROD
    OrderRequestDebugMode.TEST -> ToolsHelperWorkMode.TEST
    OrderRequestDebugMode.STUB -> ToolsHelperWorkMode.STUB
    null -> ToolsHelperWorkMode.PROD
}

private fun OrderDebug?.transportToStubCase(): ToolsHelperStubs = when (this?.stub) {
    OrderRequestDebugStubs.SUCCESS -> ToolsHelperStubs.SUCCESS
    OrderRequestDebugStubs.NOT_FOUND -> ToolsHelperStubs.NOT_FOUND
    OrderRequestDebugStubs.BAD_ID -> ToolsHelperStubs.BAD_ID
    OrderRequestDebugStubs.CANNOT_DELETE -> ToolsHelperStubs.CANNOT_DELETE
    OrderRequestDebugStubs.BAD_SEARCH_STRING -> ToolsHelperStubs.BAD_SEARCH_STRING
    null -> ToolsHelperStubs.NONE
}

fun ToolsHelperContext.fromTransport(request: OrderCreateRequest) {
    command = ToolsHelperCommand.CREATE
    orderRequest = request.order?.toInternal() ?: ToolsHelperOrder()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ToolsHelperContext.fromTransport(request: OrderReadRequest) {
    command = ToolsHelperCommand.READ
    orderRequest = request.order?.toInternal() ?: ToolsHelperOrder()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun OrderReadObject?.toInternal(): ToolsHelperOrder = if (this != null)
    ToolsHelperOrder(id = id.toOrderId()) else ToolsHelperOrder()

fun ToolsHelperContext.fromTransport(request: OrderUpdateRequest) {
    command = ToolsHelperCommand.UPDATE
    orderRequest = request.order?.toInternal() ?: ToolsHelperOrder()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ToolsHelperContext.fromTransport(request: OrderDeleteRequest) {
    command = ToolsHelperCommand.DELETE
    orderRequest = request.order?.toInternal() ?: ToolsHelperOrder()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun OrderDeleteObject?.toInternal(): ToolsHelperOrder =
    if (this != null) ToolsHelperOrder(id = id.toOrderId(), lock = lock.toOrderLock())
    else ToolsHelperOrder()

fun ToolsHelperContext.fromTransport(request: OrderSearchRequest) {
    command = ToolsHelperCommand.SEARCH
    orderFilterRequest = request.orderFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun OrderSearchFilter?.toInternal(): ToolsHelperOrderFilter = ToolsHelperOrderFilter(
    searchString = this?.searchString ?: EMPTY_STRING,
    ownerId = this?.ownerId?.let { ToolsHelperEmployeeId(it) } ?: ToolsHelperEmployeeId.NONE,
    orderStatus = this?.orderStatus.fromTransport()
)

private fun OrderCreateObject.toInternal(): ToolsHelperOrder = ToolsHelperOrder(
    ownerId = this.employeeId.ownerIdFromTransport(),
    operationId = this.operationId.operationIdFromTransport(),
    orderStatus = this.orderStatus.fromTransport(),
    createTime = this.createTime?.toInstant() ?: Instant.NONE,
    partCount = this.partCount ?: ZERO

)

private fun OrderUpdateObject.toInternal(): ToolsHelperOrder = ToolsHelperOrder(
    id = this.id.toOrderId(),
    ownerId = this.employeeId.ownerIdFromTransport(),
    operationId = this.operationId.operationIdFromTransport(),
    orderStatus = this.orderStatus.fromTransport(),
    createTime = this.createTime?.toInstant() ?: Instant.NONE,
    partCount = this.partCount ?: ZERO,
    lock = lock.toOrderLock()
)

private fun String?.ownerIdFromTransport(): ToolsHelperEmployeeId = this?.let { ToolsHelperEmployeeId(it) }
    ?: ToolsHelperEmployeeId.NONE

private fun String?.operationIdFromTransport(): ToolsHelperOperationId = this?.let { ToolsHelperOperationId(it) }
    ?: ToolsHelperOperationId.NONE

private fun OrderStatus?.fromTransport(): ToolsHelperOrderStatus = when (this) {
    OrderStatus.CREATED -> ToolsHelperOrderStatus.CREATED
    OrderStatus.IN_PROGRESS -> ToolsHelperOrderStatus.IN_PROGRESS
    OrderStatus.COMPLETED -> ToolsHelperOrderStatus.COMPLETED
    OrderStatus.FINISHED -> ToolsHelperOrderStatus.FINISHED
    null -> ToolsHelperOrderStatus.NONE
}