import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeout
import ru.patseev.helper.api.models.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.helper.ktor.module
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class WebSocketTest {

    @Test
    fun createStub() {
        val request = OrderCreateRequest(
            order = OrderCreateObject(
                employeeId = "123",
                operationId = "44",
                partCount = 777,
                orderStatus = OrderStatus.IN_PROGRESS,
            ),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun readStub() {
        val request = OrderReadRequest(
            order = OrderReadObject("666"),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun updateStub() {
        val request = OrderUpdateRequest(
            order = OrderUpdateObject(
                employeeId = "123",
                operationId = "44",
                partCount = 777,
                orderStatus = OrderStatus.FINISHED,
            ),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun deleteStub() {
        val request = OrderDeleteRequest(
            order = OrderDeleteObject(
                id = "666",
            ),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }

    @Test
    fun searchStub() {
        val request = OrderSearchRequest(
            orderFilter = OrderSearchFilter(),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals(ResponseResult.SUCCESS, it.result)
        }
    }


    private inline fun <reified T> testMethod(
        request: IRequest,
        crossinline assertBlock: (T) -> Unit
    ) = testApplication {
        application { module(ToolsHelperAppSettings(corSettings = ToolsHelperCorSettings())) }
        val client = createClient {
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(apiMapper)
            }
        }

        client.webSocket("/v1/ws/order") {
            withTimeout(3000) {
                val response = receiveDeserialized<IResponse>() as T
                assertIs<OrderInitResponse>(response)
            }
            sendSerialized(request)
            withTimeout(3000) {
                val response = receiveDeserialized<IResponse>() as T
                assertBlock(response)
            }
        }
    }
}