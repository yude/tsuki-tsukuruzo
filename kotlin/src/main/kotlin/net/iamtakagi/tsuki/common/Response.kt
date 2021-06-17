package net.iamtakagi.tsuki.common

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun ApplicationCall.respondOr404(res: () -> Any?) {
    respond(res() ?: HttpStatusCode.NotFound)
}
