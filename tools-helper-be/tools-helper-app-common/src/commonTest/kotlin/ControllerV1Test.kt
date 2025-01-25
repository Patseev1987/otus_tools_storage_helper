import kotlinx.coroutines.test.runTest
import ru.patseev.helper.api.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ControllerV1Test {

    private val request = OrderCreateRequest(
        order = OrderCreateObject(
            employeeId = "123",
            operationId = "44",
            partCount = 777,
            orderStatus = OrderStatus.IN_PROGRESS,
        ),
        debug = OrderDebug(mode = OrderRequestDebugMode.STUB, stub = OrderRequestDebugStubs.SUCCESS)
    )

    private val appSettings: IToolsHelperAppSettings = object : IToolsHelperAppSettings {
        override val corSettings: ToolsHelperCorSettings = ToolsHelperCorSettings()
        override val processor: ToolsHelperOrderProcessor = ToolsHelperOrderProcessor(corSettings)
    }

    class TestApplicationCall(private val request: IRequest) {
        var res: IResponse? = null

        @Suppress("UNCHECKED_CAST")
        fun <T : IRequest> receive(): T = request as T
        fun respond(res: IResponse) {
            this.res = res
        }
    }

    private suspend fun TestApplicationCall.createAdKtor(appSettings: IToolsHelperAppSettings) {
        val resp = appSettings.controllerHelper(
            getRequest = { fromTransport(receive<OrderCreateRequest>()) },
            toResponse = { toTransportOrder() },
            ControllerV1Test::class,
            "controller-v1-test"
        )
        respond(resp)
    }

    @Test
    fun ktorHelperTest() = runTest {
        val testApp = TestApplicationCall(request).apply { createAdKtor(appSettings) }
        val res = testApp.res as OrderCreateResponse
        assertEquals(ResponseResult.SUCCESS, res.result)
    }
}