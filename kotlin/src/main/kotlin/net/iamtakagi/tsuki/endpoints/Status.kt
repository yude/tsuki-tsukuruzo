package net.iamtakagi.tsuki.endpoints

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import net.iamtakagi.tsuki.database.users
import net.iamtakagi.tsuki.services.status.Status

fun Route.getStatus() {
    get {
        call.respond(Status(users.countDocuments()))
    }
}
