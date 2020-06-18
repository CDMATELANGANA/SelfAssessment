package com.mars.cdma.gov.scheduler;


import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;



/**
 * Application Lifecycle Listener implementation class DailyJobDoneListner
 *
 */

public class DailyJobSchedulerCertificateStatus implements ServletContextListener {
	 public Scheduler sched=null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
    	 Scheduler sched=null;
    	//String cron="0 0/1 * 1/1 * ? *";
            
    	 try {
         ResourceBundle rb=ResourceBundle.getBundle("login");   	
            	String certificate_update_scheduler=rb.getString("certificate_update_scheduler_time");
            	 SchedulerFactory sf=new StdSchedulerFactory();
            	   sched=sf.getScheduler();
            	   System.out.println(" DailyJobScheduler " +certificate_update_scheduler);
                   
              JobDetail jd=new JobDetail("job1","group1",CronJobCertificateStatus.class); // CronJob.class is the class which you want to execute
              
                 CronTrigger ct=new CronTrigger("cronTrigger","groupPT",certificate_update_scheduler);
                 	
                  sched.scheduleJob(jd,ct); 
                  sched.start();
                  
            }
            catch (Exception e) {
                    e.printStackTrace();
            }
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

   
}
