package net.iamtakagi.tsuki.services.tweet

import kotlinx.coroutines.*
import net.iamtakagi.tsuki.database.users
import org.quartz.Job
import org.quartz.JobExecutionContext

class TweetsJob : Job {

    override fun execute(context: JobExecutionContext?) {
        runBlocking(Dispatchers.Default) {
            users.find().toList().forEach {
                launch {
                    it.tweet()
                }
            }
        }
    }
}
