package net.iamtakagi.oyatsu.endpoints

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import net.iamtakagi.oyatsu.database.users
import net.iamtakagi.oyatsu.services.status.Status

fun Route.getStatus() {
    get {
        call.respond(Status(users.countDocuments()))
    }
}
