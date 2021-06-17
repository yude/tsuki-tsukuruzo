package net.iamtakagi.tsuki.services.user

import blue.starry.penicillin.PenicillinClient
import blue.starry.penicillin.core.session.ApiClient
import blue.starry.penicillin.core.session.config.account
import blue.starry.penicillin.core.session.config.application
import blue.starry.penicillin.core.session.config.token
import net.iamtakagi.tsuki.common.Env

fun User.getClient(): ApiClient? {
    val (ck, cs) = Env.TWITTER_CK to Env.TWITTER_CS
    if (ck == null || cs == null) return null
    return PenicillinClient {
        account {
            application(ck, cs)
            token(at, ats)
        }
    }
}
