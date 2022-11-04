package com.example.task;

import com.example.task.job.HelloJob;
import com.example.task.job.SimpleJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Simple1Example {
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();
        // compute a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(runTime)
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);

        sched.start();

        Thread.sleep(90L * 1000L);

        sched.shutdown(true);
    }

    //Job #1 is scheduled to run every 20 seconds
    @Test
    public void job1() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("job01", "group1")
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("trigger01", "group1")
                .withSchedule(cronSchedule("0/20 * * * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #2 is scheduled to run every other minute, starting at 15 seconds past the minute.
    @Test
    public void job2() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job2", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("15 0/2 * * * ?"))
                .build();
        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #3 is scheduled to every other minute, between 8am and 5pm (17 o'clock).
    @Test
    public void job3() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job3", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #4 is scheduled to run every three minutes but only between 5pm and 11pm
    @Test
    public void job4() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job4", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger4", "group1")
                .withSchedule(cronSchedule("0 0/3 17-23 * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #5 is scheduled to run at 10am on the 1st and 15th days of the month
    @Test
    public void job5() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job5", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger5", "group1")
                .withSchedule(cronSchedule("0 0 10am 1,15 * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #6 is scheduled to run every 30 seconds on Weekdays (Monday through Friday)
    @Test
    public void job6() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job6", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger6", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * MON-FRI"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }

    //Job #7 is scheduled to run every 30 seconds on Weekends (Saturday and Sunday)
    @Test
    public void job7() throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = null;
        CronTrigger trigger = null;
        job = newJob(SimpleJob.class)
                .withIdentity("job7", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * SAT,SUN"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();
        Thread.sleep(300L * 1000L);
        sched.shutdown(true);
    }
}
