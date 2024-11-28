import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.websocket.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.helper.ktor.module
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `root endpoint`() = testApplication {
        application { module(ToolsHelperAppSettings()) }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, world!", response.bodyAsText())
    }

    @Test
    fun webSocketTest() =
        testApplication {
            application {
                module()
            }
            val client = createClient {
                install(WebSockets)
            }

            client.webSocket("/v1/ws") {
                val receiveText = incoming.receive() as Frame.Text
                println(receiveText.readText())
                assertEquals(expected = "Hello from Tools Helper WebSocket!", actual = receiveText.readText())
                send("Tools Helper WebSocket!")
                val incomingMessage = incoming.receive() as Frame.Text
                assertEquals(
                    expected = "This is incoming message - Tools Helper WebSocket!",
                    actual = incomingMessage.readText()
                )
            }
        }
}