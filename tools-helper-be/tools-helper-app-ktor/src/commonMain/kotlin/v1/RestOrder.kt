package ru.patseev.helper.ktor.v1

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings

fun Route.v1Order(appSettings: ToolsHelperAppSettings) {
    route("order") {
        post("create") {
            call.createOrder(appSettings)
        }
        post("read") {
            call.readOrder(appSettings)
        }
        post("update") {
            call.updateOrder(appSettings)
        }
        post("delete") {
            call.deleteOrder(appSettings)
        }
        post("search") {
            call.searchOrder(appSettings)
        }
    }
}