package net.iamtakagi.tsuki.services.user

import blue.starry.penicillin.endpoints.statuses
import blue.starry.penicillin.endpoints.statuses.create

fun User.tweetWhenRegister() {
    val client = getClient() ?: return
    client.statuses.create("月作るぞ に登録しました #tsuki_tsukuruzo https://tsuki-tsukuruzo.iamtakagi.net")
}

fun User.tweet() {
    val client = getClient() ?: return
    client.statuses.create("月作るぞ #tsuki_tsukuruzo https://tsuki-tsukuruzo.iamtakagi.net")
}
