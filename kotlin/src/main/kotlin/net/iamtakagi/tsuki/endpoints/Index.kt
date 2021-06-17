package net.iamtakagi.tsuki.endpoints

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getIndex() {
    get {
        call.respondText("月作るぞ #tsuki_tsukuruzo")
    }
}
