package net.iamtakagi.tsuki.services.user

import com.mongodb.client.model.Filters
import net.iamtakagi.tsuki.database.users

suspend fun User.delete(): Boolean {
    return users.deleteOne(Filters.eq("twitterUserId", twitterUserId)).wasAcknowledged()
}
