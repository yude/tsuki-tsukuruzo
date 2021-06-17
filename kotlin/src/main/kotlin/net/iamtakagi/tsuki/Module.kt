package net.iamtakagi.tsuki

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import net.iamtakagi.oyatsu.endpoints.*
import net.iamtakagi.tsuki.common.Env
import net.iamtakagi.tsuki.common.createLogger
import net.iamtakagi.tsuki.endpoints.callback
import net.iamtakagi.tsuki.endpoints.getIndex
import net.iamtakagi.tsuki.endpoints.getUrl
import net.iamtakagi.tsuki.services.tweet.TweetsScheduler

fun Application.module() {

    install(XForwardedHeaderSupport)

    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = true
            }
        )
    }

    install(CORS) {
        anyHost()
        method(HttpMethod.Get)
        header(HttpHeaders.Origin)
        header(HttpHeaders.Authorization)
        header(HttpHeaders.Accept)
        header(HttpHeaders.ContentType)
        maxAgeInSeconds = 3600
        allowCredentials = true
    }

    install(CallLogging) {
        logger = KotlinLogging.createLogger("oyatsu.server")
        format { call ->
            when (val status = call.response.status()) {
                HttpStatusCode.Found -> "$status: ${call.request.toLogString()} -> ${call.response.headers[HttpHeaders.Location]}"
                null -> ""
                else -> "$status: ${call.request.httpMethod.value} ${call.request.uri}"
            }
        }
    }

    routing {
        route(Env.BASE_URI) {
            getIndex()
            route("status") {
                getStatus()
            }
            route("oauth") {
                route("getUrl") {
                    getUrl()
                }
                route("callback") {
                    callback()
                }
            }
        }
    }

    TweetsScheduler.start()
}
