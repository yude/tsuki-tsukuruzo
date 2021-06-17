package net.iamtakagi.tsuki.services.oauth

import kotlinx.serialization.Serializable

@Serializable
data class OAuthUrlResponse(
    val url: String
)
