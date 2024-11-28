package ru.patseev.helper.ktor.plugin

import apiMapper
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.helper.ktor.ws.wsHandlerV1


fun Application.configureWebSocket(toolsHelperAppSettings: ToolsHelperAppSettings) {
    install(WebSockets) {
        pingPeriodMillis = 15_000
        timeoutMillis = 15_000
        maxFrameSize = Long.MAX_VALUE
        masking = false
        contentConverter = KotlinxWebsocketSerializationConverter(apiMapper)
    }
    routing {
        route("v1") {
            webSocket("/ws") {
                send("Hello from Tools Helper WebSocket!")
                val receivedText = incoming.receive() as Frame.Text
                send("This is incoming message - ${receivedText.readText()}")
            }

            webSocket("/ws/order") {
                wsHandlerV1(toolsHelperAppSettings)
            }
        }
    }
}