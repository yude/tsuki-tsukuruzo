package net.iamtakagi.tsuki.services.user

import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import net.iamtakagi.tsuki.database.users

suspend fun User.save(): User {
    users.replaceOne(
        Filters.eq("twitterUserId", twitterUserId),
        this,
        ReplaceOptions().upsert(true)
    )
    return this
}
