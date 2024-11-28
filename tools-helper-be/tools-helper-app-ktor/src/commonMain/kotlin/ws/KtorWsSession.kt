package ru.patseev.helper.ktor.ws

import apiResponseSerialize
import io.ktor.websocket.*
import ru.patseev.helper.api.models.IResponse
import ws.IToolsHelperWsSession

data class KtorWsSession(
    private val session: WebSocketSession
) : IToolsHelperWsSession {
    override suspend fun <T> send(obj: T) {
        require(obj is IResponse)
        session.send(Frame.Text(apiResponseSerialize(obj)))
    }
}