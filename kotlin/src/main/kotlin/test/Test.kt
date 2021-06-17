package test

import net.iamtakagi.oyatsu.common.Env
import net.iamtakagi.oyatsu.services.tweet.TweetsJob
import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import java.util.*

fun main() {
    val scheduler = StdSchedulerFactory.getDefaultScheduler()

    val job = JobBuilder.newJob(TweetsJob::class.java)
        .withIdentity("TweetsJob", "Zetsumetsu")
        .build()

    val trigger = TriggerBuilder.newTrigger()
        .withIdentity("TweetsTrigger", "Zetsumetsu")
        // .withSchedule(cronSchedule("0 0 * * TUE"))
        .withSchedule(CronScheduleBuilder.cronSchedule("0 56 0 ? * WED").inTimeZone(TimeZone.getTimeZone(Env.TZ)))
        .startNow()
        .build()

    scheduler.scheduleJob(job, trigger)
    scheduler.start()
}
