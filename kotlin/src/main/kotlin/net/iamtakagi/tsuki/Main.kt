package net.iamtakagi.tsuki

import io.ktor.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import net.iamtakagi.tsuki.common.Env

fun main() {
    embeddedServer(
        CIO,
        port = Env.PORT,
        host = Env.HOST,
        module = Application::module
    ).start(wait = true)
}
