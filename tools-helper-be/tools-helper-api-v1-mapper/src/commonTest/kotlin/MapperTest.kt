import kotlinx.datetime.Clock
import models.*
import ru.patseev.helper.api.models.*
import stubs.ToolsHelperStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {


    @Test
    fun fromTransport() {

        val time = Clock.System.now()
        val req = OrderCreateRequest(
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS,
            ),
            order = OrderCreateObject(
                employeeId = "123",
                operationId = "44",
                partCount = 777,
                orderStatus = OrderStatus.IN_PROGRESS,
                createTime = time.toString(),
            ),
        )

        val context = ToolsHelperContext()
        context.fromTransport(req)

        assertEquals(ToolsHelperStubs.SUCCESS, context.stubCase)
        assertEquals(ToolsHelperWorkMode.STUB, context.workMode)
        assertEquals("123", context.orderRequest.ownerId.asString())
        assertEquals("44", context.orderRequest.operationId.asString())
        assertEquals(ToolsHelperOrderStatus.IN_PROGRESS, context.orderRequest.orderStatus)
        assertEquals(777, context.orderRequest.partCount)
        assertEquals(time, context.orderRequest.createTime)
    }

    @Test
    fun toTransport() {

        val time = Clock.System.now()
        val context = ToolsHelperContext(
            requestId = ToolsHelperRequestId("999"),
            command = ToolsHelperCommand.READ,
            orderResponse = ToolsHelperOrder(
                ownerId = ToolsHelperEmployeeId("111"),
                operationId = ToolsHelperOperationId("10"),
                partCount = 777,
                orderStatus = ToolsHelperOrderStatus.COMPLETED,
                createTime = time,
                tools = mapOf("2004-9060" to 100, "3070-1010" to 10)
            ),
            errors = mutableListOf(
                ToolsHelperError(
                    code = "error",
                    group = "request",
                    field = "partCount",
                    message = "wrong part count",
                )
            ),
            state = ToolsHelperState.RUNNING
        )

        val req = context.toTransportOrder() as OrderReadResponse

        assertEquals("10", req.order?.operationId)
        assertEquals("111", req.order?.employeeId)
        assertEquals(777, req.order?.partCount)
        assertEquals(time.toString(), req.order?.createTime)
        assertEquals(1, req.errors?.size)
        assertEquals("error", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("partCount", req.errors?.firstOrNull()?.field)
        assertEquals("wrong part count", req.errors?.firstOrNull()?.message)
        assertEquals(setOf("2004-9060", "3070-1010"), req.order?.tools?.keys)
    }
}