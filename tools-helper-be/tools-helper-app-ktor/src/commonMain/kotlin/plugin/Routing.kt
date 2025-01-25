package ru.patseev.helper.ktor.plugin

import apiMapper
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.patseev.helper.ktor.ToolsHelperAppSettings
import ru.patseev.helper.ktor.v1.v1Order

fun Application.configureRouting(appSettings: ToolsHelperAppSettings) {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        get("/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello, $name! =)))")
        }
        route("/v1") {
            install(ContentNegotiation) {
                json(apiMapper)
            }
            v1Order(appSettings)
        }
    }
}