package ru.patseev.helper.ktor.v1

import controllerHelper
import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.patseev.helper.api.models.IRequest
import ru.patseev.helper.api.models.IResponse
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import toTransportOrder
import kotlin.reflect.KClass

suspend inline fun <reified Q : IRequest, @Suppress("unused") reified R : IResponse> ApplicationCall.processV1(
    appSettings: ToolsHelperAppSettings,
    clazz: KClass<*>,
    logId: String,
) = appSettings.controllerHelper(
    {
        fromTransport(this@processV1.receive<Q>())
    },
    { this@processV1.respond(toTransportOrder() as R) },
    clazz,
    logId,
)