package net.iamtakagi.tsuki.common

import kotlin.properties.ReadOnlyProperty

object Env {
    val TZ by string { "Asia/Tokyo" }
    val HOST by string { "0.0.0.0" }
    val PORT by int { 8080 }
    val BASE_URI by string { "/api" }
    val LOG by string { "DEBUG" }

    val DB_HOST by string { "0.0.0.0" }
    val DB_PORT by int { 27017 }
    val DB_USER by stringOrNull
    val DB_PASS by stringOrNull
    val DB_NAME by string { "tsuki" }

    val CRON by string { "0 0 0 ? * MON" }

    val TWITTER_CK by stringOrNull
    val TWITTER_CS by stringOrNull
}

private fun boolean(default: () -> Boolean): ReadOnlyProperty<Env, Boolean> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toBooleanFuzzy() ?: default()
}

private val stringOrNull: ReadOnlyProperty<Env, String?>
    get() = ReadOnlyProperty { _, property ->
        System.getenv(property.name)
    }

private fun string(default: () -> String): ReadOnlyProperty<Env, String> = ReadOnlyProperty { _, property ->
    System.getenv(property.name) ?: default()
}

private fun int(default: () -> Int): ReadOnlyProperty<Env, Int> = ReadOnlyProperty { _, property ->
    System.getenv(property.name)?.toIntOrNull() ?: default()
}
