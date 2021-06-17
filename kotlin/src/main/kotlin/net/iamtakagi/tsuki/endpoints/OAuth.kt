package net.iamtakagi.tsuki.endpoints

import blue.starry.penicillin.endpoints.oauth
import blue.starry.penicillin.endpoints.oauth.authenticateUrl
import blue.starry.penicillin.endpoints.oauth.requestToken
import io.ktor.application.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import net.iamtakagi.oyatsu.services.HttpClient
import net.iamtakagi.oyatsu.services.TwitterClient
import net.iamtakagi.tsuki.common.respondOr404
import net.iamtakagi.tsuki.database.users
import net.iamtakagi.tsuki.services.oauth.OAuthUrlResponse
import net.iamtakagi.tsuki.services.user.User
import net.iamtakagi.tsuki.services.user.save
import net.iamtakagi.tsuki.services.user.tweetWhenRegister

fun Route.getUrl() {
    get {
        call.respondOr404 {
            val oauth = TwitterClient?.oauth ?: return@respondOr404 null
            val rt = oauth.requestToken("").requestToken
            OAuthUrlResponse(oauth.authenticateUrl(rt).toString())
        }
    }
}

fun Route.callback() {
    get {
        val oauthToken = call.request.queryParameters["oauth_token"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val oauthVerifier = call.request.queryParameters["oauth_verifier"] ?: return@get call.respond(HttpStatusCode.NotFound)
        val res = HttpClient.post<String>("https://api.twitter.com/oauth/access_token") {
            parameter("oauth_token", oauthToken)
            parameter("oauth_verifier", oauthVerifier)
        }
        val result = res.split("&").map { parameter ->
            parameter.split("=").let { it.first() to it.last() }
        }.toMap()
        val (at, ats) = result["oauth_token"] to result["oauth_token_secret"]
        val userId = result["user_id"]
        if (at == null || ats == null || userId == null) return@get call.respond(HttpStatusCode.NotFound)
        User(
            users.countDocuments().plus(1),
            userId.toLong(),
            at,
            ats
        ).save().tweetWhenRegister()
        call.respondRedirect("https://oyatsu-nichiyou.iamtakagi.net")
    }
}
