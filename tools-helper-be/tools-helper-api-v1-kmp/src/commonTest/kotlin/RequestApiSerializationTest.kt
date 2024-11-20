import kotlinx.serialization.encodeToString
import ru.patseev.helper.api.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class RequestApiSerializationTest {

    private val request: IRequest = OrderCreateRequest(
        debug = OrderDebug(
            mode = OrderRequestDebugMode.STUB,
            stub = OrderRequestDebugStubs.BAD_ID
        ),
        order = OrderCreateObject(
            operationId = "10",
            employeeId = "1",
            orderStatus = OrderStatus.COMPLETED,
            partCount = 77,
        )
    )

    private val response: IResponse = OrderCreateResponse(
        result = ResponseResult.SUCCESS,
        order = OrderResponseObject(
            operationId = "10",
            employeeId = "1",
            orderStatus = OrderStatus.COMPLETED,
            partCount = 77,
            id = "111",
            tools = mapOf("2004-9060" to 50, "3070-1010" to 10)
        )
    )

    @Test
    fun serialize() {
        val json = apiMapper.encodeToString(IRequest.serializer(), request)

        println(json)

        assertContains(json, Regex("\"operationId\":\\s*\"10\""))
        assertContains(json, Regex("\"employeeId\":\\s*\"1\""))
        assertContains(json, Regex("\"orderStatus\":\\s*\"completed\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badId\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiMapper.encodeToString(request)
        val obj = apiMapper.decodeFromString<IRequest>(json) as OrderCreateRequest

        assertEquals(request, obj)
    }


    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"order": null}
        """.trimIndent()
        val obj = apiMapper.decodeFromString<OrderCreateRequest>(jsonString)

        assertEquals(null, obj.order)
    }
}