package net.iamtakagi.tsuki.database

import net.iamtakagi.oyatsu.services.user.User
import net.iamtakagi.tsuki.common.Env
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

internal val mongodb: CoroutineClient by lazy {
    if (Env.DB_USER != null && Env.DB_PASS != null) {
        KMongo.createClient("mongodb://${Env.DB_USER}:${Env.DB_PASS}@${Env.DB_HOST}:${Env.DB_PORT}")
    } else {
        KMongo.createClient("mongodb://${Env.DB_HOST}:${Env.DB_PORT}")
    }.coroutine
}

internal val database: CoroutineDatabase by lazy {
    mongodb.getDatabase(Env.DB_NAME)
}

internal val users: CoroutineCollection<User> by lazy {
    database.getCollection("users")
}
