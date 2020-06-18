package com.mars.cdma.gov.scheduler;

import java.util.Date;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class CronJobCertificateStatus implements Job {
	
 public void execute(JobExecutionContext arg0) throws JobExecutionException {

	
	  System.out.println("Task Start PT Self ASMT  certificate status  "+new Date());

	 try {
		 
		new UpdateCertificateStatus().getSetCertificateStatusDb2ToLocal();
		
		
} catch (Exception e) {
	e.printStackTrace();
	System.out.println("Ur r in loop Exception "+e);
} 
  
  
 }
}

// to compile the above two classes you need quartz-all-1.6.6.jar file 