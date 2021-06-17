package net.iamtakagi.oyatsu.services

import blue.starry.penicillin.PenicillinClient
import blue.starry.penicillin.core.session.config.account
import blue.starry.penicillin.core.session.config.application
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import mu.KotlinLogging
import net.iamtakagi.oyatsu.common.Env
import net.iamtakagi.oyatsu.common.createLogger

private val logger = KotlinLogging.createLogger("kotlin.client")

val HttpClient by lazy {
    HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }

        Logging {
            level = LogLevel.INFO
            logger = object : Logger {
                private val logger = KotlinLogging.createLogger("kotlin.http")

                override fun log(message: String) {
                    logger.trace { message }
                }
            }
        }
    }
}

val TwitterClient by lazy {
    val (ck, cs) = Env.TWITTER_CK to Env.TWITTER_CS
    if (ck == null || cs == null) {
        logger.info { "Twitter の資格情報が設定されていません。" }
        return@lazy null
    }

    PenicillinClient {
        account {
            application(ck, cs)
        }
    }
}
