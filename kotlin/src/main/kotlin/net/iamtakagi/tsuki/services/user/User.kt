package net.iamtakagi.tsuki.services.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val twitterUserId: Long,
    val at: String,
    val ats: String
)
