package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;








import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mysql.jdbc.Statement;

  
public class Paymentprocess {
	

	private static final Logger log = Logger.getLogger(Paymentprocess.class);

@Autowired
private SessionFactory sessionFactory;
@Autowired
private Assessmentservice assessmentservice;
@Autowired
private PaymentTransactionService paymenttransactionSerivce;
	
public Connection con=null;
public static  String sql=null;

public static String sql1=null;

public String sql2=null;
public PreparedStatement ps=null;

public static PreparedStatement ps1=null;

public PreparedStatement ps2=null;;
public ResultSet rs=null;

public static ResultSet rs1=null;

public ResultSet rs2=null;
	
TransactionReceipt receipt=new TransactionReceipt();
PaymentTransaction paymentTransactionEntity=null;
public PaymentTransaction  getdetailsfromresponseurl(PaymentTransaction paymentTransactionEntity,String responseUrl) throws SQLException
{
	//paymentTransactionEntity=new PaymentTransaction();
	System.out.println("url "+responseUrl);
	String responseUrlArray[] = responseUrl.split("\\|");
	System.out.println("rspnse "+responseUrlArray[1]);
	
	System.out.print("CHk2"); 
//	paymenttransactionSerivce.gettransingelrecord(10000000560L);
	paymentTransactionEntity.setRESPONSE_URL(responseUrl==null?"":responseUrl);
 if(responseUrlArray[14].equals("0300")){
	 log.info("Payment Status "+responseUrlArray[14]);
	 System.out.println("Success>>>>>>>>>>>>");
	 paymentTransactionEntity.setPaid_update_flag('Y');
 }else{
	 
	 paymentTransactionEntity.setPaid_update_flag('N');
	 
 }
 paymentTransactionEntity.setTransaction_error_description(responseUrlArray[23]==null?"":responseUrlArray[23]); 
 paymentTransactionEntity.setTranscation_id(responseUrlArray[2]);
 paymentTransactionEntity.setTransaction_response_code(responseUrlArray[14]);
 System.out.println("requ number "+responseUrlArray[16]);
 paymentTransactionEntity.setTransaction_bank_id(responseUrlArray[5]);
 paymentTransactionEntity.setTransaction_bank_in(responseUrlArray[6]);
 paymentTransactionEntity.setTransaction_bank_ref(responseUrlArray[3]);
 System.out.println(">>> before save"+paymentTransactionEntity.getGateway_name());
 
 //sessionFactory.getCurrentSession().save(paymentTransactionEntity);
 
 System.out.println(">>> after save");
 
	 AssessmentMaster ptAssessmentMaster=null;
 if(paymentTransactionEntity.getPaid_update_flag().equals("Y")){
	 updatepayment(ptAssessmentMaster,paymentTransactionEntity); 
	 
	}
	 
	return paymentTransactionEntity;
	
	}

	public String  updatepayment(AssessmentMaster ptAssessmentMaster,PaymentTransaction paymentTransactionEntity)  {
	
	
		System.out.println(" Payment Process for selfMutation"+ptAssessmentMaster.getOwnerAadhar());
		return sql2;
		
	}
	}
 
