package net.iamtakagi.tsuki.services.tweet

import net.iamtakagi.tsuki.common.Env
import org.quartz.CronScheduleBuilder.cronSchedule
import org.quartz.JobBuilder.newJob
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import java.util.*

object TweetsScheduler {

    fun start() {
        val scheduler = StdSchedulerFactory.getDefaultScheduler()

        val job = newJob(TweetsJob::class.java)
            .withIdentity("TweetsJob", "Tsuki")
            .build()

        val trigger = newTrigger()
            .withIdentity("TweetsTrigger", "Tsuki")
            .withSchedule(cronSchedule(Env.CRON).inTimeZone(TimeZone.getTimeZone(Env.TZ)))
            .startNow()
            .forJob(job)
            .build()

        scheduler.scheduleJob(job, trigger)
        scheduler.start()
    }
}
