import kotlinx.serialization.encodeToString
import ru.patseev.helper.api.models.IResponse
import ru.patseev.helper.api.models.OrderCreateResponse
import ru.patseev.helper.api.models.OrderResponseObject
import ru.patseev.helper.api.models.OrderStatus
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseApiSerializationTest {
    private val response: IResponse = OrderCreateResponse(
        order = OrderResponseObject(
            operationId = "10",
            employeeId = "1",
            orderStatus = OrderStatus.COMPLETED,
            partCount = 77,
            id = "11111"
        ),

        )


    @Test
    fun serialize() {

        val json = apiMapper.encodeToString(response)

        println(json)

        assertContains(json, Regex("\"id\":\\s*\"11111\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiMapper.encodeToString(response)
        val obj = apiMapper.decodeFromString<IResponse>(json) as OrderCreateResponse

        assertEquals(response, obj)
    }
}