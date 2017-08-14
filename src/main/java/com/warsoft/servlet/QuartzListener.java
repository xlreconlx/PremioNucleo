/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warsoft.servlet;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
/**
 *
 * @author ander
 */
public class QuartzListener implements ServletContextListener {
        Scheduler scheduler = null;
        @Override
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Context Initialized");
               
                try {
                        // Setup the Job class and the Job group
                        JobDetail job = newJob(Conexion.class).withIdentity(
                                        "CronQuartzJob", "Group").build();
                        // Create a Trigger that fires every 5 minutes.
                        Trigger trigger = newTrigger()
                        .withIdentity("TriggerName", "Group")
                        .withSchedule(simpleSchedule().withIntervalInMinutes(1)
                        .repeatForever())
                        .build();
                        // Setup the Job and Trigger with Scheduler & schedule jobs
                        scheduler = new StdSchedulerFactory().getScheduler();
                        scheduler.start();
                        scheduler.scheduleJob(job, trigger);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                }
        }
        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
                try
                {
                        scheduler.shutdown();
                }
                catch (SchedulerException e)
                {
                        e.printStackTrace();
                }
        }
}
