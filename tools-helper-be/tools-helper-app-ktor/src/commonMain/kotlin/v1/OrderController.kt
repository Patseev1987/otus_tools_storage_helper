package ru.patseev.helper.ktor.v1

import io.ktor.server.application.*
import ru.patseev.helper.api.models.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import kotlin.reflect.KClass

val clCreate: KClass<*> = ApplicationCall::createOrder::class
suspend fun ApplicationCall.createOrder(appSettings: ToolsHelperAppSettings) =
    processV1<OrderCreateRequest, OrderCreateResponse>(appSettings, clCreate, "create")

val clRead: KClass<*> = ApplicationCall::readOrder::class
suspend fun ApplicationCall.readOrder(appSettings: ToolsHelperAppSettings) =
    processV1<OrderReadRequest, OrderReadResponse>(appSettings, clRead, "read")

val clUpdate: KClass<*> = ApplicationCall::updateOrder::class
suspend fun ApplicationCall.updateOrder(appSettings: ToolsHelperAppSettings) =
    processV1<OrderUpdateRequest, OrderUpdateResponse>(appSettings, clUpdate, "update")

val clDelete: KClass<*> = ApplicationCall::deleteOrder::class
suspend fun ApplicationCall.deleteOrder(appSettings: ToolsHelperAppSettings) =
    processV1<OrderDeleteRequest, OrderDeleteResponse>(appSettings, clDelete, "delete")

val clSearch: KClass<*> = ApplicationCall::searchOrder::class
suspend fun ApplicationCall.searchOrder(appSettings: ToolsHelperAppSettings) =
    processV1<OrderSearchRequest, OrderSearchResponse>(appSettings, clSearch, "search")
