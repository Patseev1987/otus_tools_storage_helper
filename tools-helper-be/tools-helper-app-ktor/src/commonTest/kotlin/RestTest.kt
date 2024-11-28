import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import ru.patseev.helper.api.models.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.helper.ktor.module
import kotlin.test.Test
import kotlin.test.assertEquals

class RestTest {

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
        testMethod(
            func = "create",
            request = request,
        ) {
            val response = it.body<OrderCreateResponse>()
            assertEquals(ResponseResult.SUCCESS, response.result)
            assertEquals(99, response.order?.partCount)
            assertEquals("7", response.order?.employeeId)
            assertEquals("111", response.order?.operationId)
            assertEquals(OrderStatus.CREATED, response.order?.orderStatus)
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
        testMethod(
            func = "read",
            request = request,
        ) {
            val response = it.body<OrderReadResponse>()

            assertEquals(ResponseResult.SUCCESS, response.result)
            assertEquals(99, response.order?.partCount)
            assertEquals("7", response.order?.employeeId)
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

        testMethod(
            func = "update",
            request = request,
        ) {
            val response = it.body<OrderUpdateResponse>()
            assertEquals(ResponseResult.SUCCESS, response.result)
            assertEquals(OrderStatus.CREATED, response.order?.orderStatus)
        }
    }

    @Test
    fun deleteStub() {
        val request = OrderDeleteRequest(
            order = OrderDeleteObject(
                id = "777",
            ),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            )
        )

        testMethod(
            func = "delete",
            request = request,
        ) {
            val response = it.body<OrderDeleteResponse>()
            assertEquals(ResponseResult.SUCCESS, response.result)
            assertEquals(99, response.order?.partCount)
        }
    }

    @Test
    fun searchStub() = testMethod(
        func = "search",
        request = OrderSearchRequest(
            orderFilter = OrderSearchFilter(),
            debug = OrderDebug(
                mode = OrderRequestDebugMode.STUB,
                stub = OrderRequestDebugStubs.SUCCESS
            ),
        )
    ) {
        val response = it.body<OrderSearchResponse>()
        assertEquals(ResponseResult.SUCCESS, response.result)
    }

    private inline fun <reified T : IRequest> testMethod(
        func: String,
        request: T,
        crossinline function: suspend (HttpResponse) -> Unit,
    ): Unit = testApplication {
        application {
            module(ToolsHelperAppSettings(corSettings = ToolsHelperCorSettings()))
        }
        val client = createClient {
            install(ContentNegotiation) {
                json(apiMapper)
            }
        }
        val response = client.post("/v1/order/$func") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        function(response)
    }
}