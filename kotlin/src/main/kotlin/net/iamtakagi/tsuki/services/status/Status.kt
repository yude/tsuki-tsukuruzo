package net.iamtakagi.tsuki.services.status

import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val usersSize: Long
)
