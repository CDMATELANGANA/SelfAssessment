package com.mars.cdma.gov.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Hbm_Utils {

	
	
	
	public Hbm_Utils() {

	}
	
	public static SessionFactory  getHbmutils() {
		
		SessionFactory sfactroy=null;
		if(sfactroy==null)
		{
		AnnotationConfiguration  configuration=new AnnotationConfiguration(); 
		configuration.configure();
		sfactroy=configuration.buildSessionFactory();
		}
		return sfactroy;
		
	}
 
	public static SessionFactory closeconnection() {
		SessionFactory sfactroy = closeconnection();
		 
		sfactroy.close();
		return  sfactroy; 
		 
	}
	
	
	
}
