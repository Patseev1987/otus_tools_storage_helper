package ru.patseev.helper.ktor.ws

import apiRequestDeserialize
import apiResponseSerialize
import controllerHelper
import fromTransport
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import models.ToolsHelperCommand
import ru.patseev.helper.api.models.IRequest
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import toTransportInit
import toTransportOrder
import kotlin.reflect.KClass

private val clWsV1: KClass<*> = WebSocketSession::wsHandlerV1::class
suspend fun WebSocketSession.wsHandlerV1(appSettings: ToolsHelperAppSettings) = with(KtorWsSession(this)) {
    val sessions = appSettings.corSettings.wsSessions
    sessions.add(this)

    // Handle init request
    appSettings.controllerHelper(
        {
            command = ToolsHelperCommand.INIT
            wsSession = this@with
        },
        { outgoing.send(Frame.Text(apiResponseSerialize(toTransportInit()))) },
        clWsV1,
        "wsV1-init"
    )

    // Handle flow
    incoming.receiveAsFlow().mapNotNull {
        val frame = it as? Frame.Text ?: return@mapNotNull
        // Handle without flow destruction
        try {
            appSettings.controllerHelper(
                {
                    fromTransport(apiRequestDeserialize<IRequest>(frame.readText()))
                    wsSession = this@with
                },
                {
                    val result = apiResponseSerialize(toTransportOrder())
                    // If change request, response is sent to everyone
                    outgoing.send(Frame.Text(result))
                },
                clWsV1,
                "wsV1-handle"
            )

        } catch (_: ClosedReceiveChannelException) {
            sessions.remove(this@with)
        } finally {
            // Handle finish request
            appSettings.controllerHelper(
                {
                    command = ToolsHelperCommand.FINISH
                    wsSession = this@with
                },
                { },
                clWsV1,
                "wsV1-finish"
            )
            sessions.remove(this@with)
        }
    }.collect()
}
