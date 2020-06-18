package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Controller.DoorDemo;
import com.mars.cdma.gov.Dao.PaymentTransactionDAO;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.utils.CommonUtils;
@Repository("PaymentDao")
@Transactional
public class PaymentTransactionDAOimpl implements PaymentTransactionDAO {

	private static final Logger log = Logger
			.getLogger(PaymentTransactionDAOimpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private Assessmentservice assessmentservice;

	@Autowired
	private PaymentTransactionService paymenttrSerivce;

	@Override
	public PaymentTransaction getsinglerecord(long payment_transaction_receipt_id) {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		System.out.println("payment_transaction_receipt_id "
				+ payment_transaction_receipt_id);
		Session session = sessionFactory.getCurrentSession();
		paymentTransaction = (PaymentTransaction) session.get(
				PaymentTransaction.class, payment_transaction_receipt_id);

		/*System.out.println(" paymentTransaction "
				+ paymentTransaction.getGateway_name());*/
		return paymentTransaction;
	}

	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			PaymentTransaction paymentTransactionEntity1, String responseUrl) throws SQLException {
		TransactionReceipt transactionReceipt = new TransactionReceipt();

		String db2updatestatus = "fail";
		String DoorNo="";
		String AssmntNo="";
		//Session session = sessionFactory.getCurrentSession();
		System.out.println("url : " + responseUrl);
		String responseUrlArray[] = responseUrl.split("\\|");
		PaymentTransaction paymentTransactionEntity = getsinglerecord(Long
				.parseLong(responseUrlArray[1]));
 
		paymentTransactionEntity.setRESPONSE_URL(responseUrl);
	
		if (responseUrlArray[14].equals("0300")) {
			log.info("Payment Status " + responseUrlArray[14] + " URL "
					+ responseUrlArray[23]);
			paymentTransactionEntity.setTransaction_flag('S');
		}

		 else {

			paymentTransactionEntity.setTransaction_flag('F');

		}

		paymentTransactionEntity.setTransaction_error_description(responseUrlArray[24]);
		paymentTransactionEntity.setTranscation_id(responseUrlArray[2]);
		paymentTransactionEntity.setTransaction_response_code(responseUrlArray[14]);
		//System.out.println("requ number " + responseUrlArray[16]);
		paymentTransactionEntity.setUlbname(responseUrlArray[18]);
		paymentTransactionEntity.setDistrictname(responseUrlArray[19]);
		paymentTransactionEntity.setTransaction_bank_id(responseUrlArray[5]);
		paymentTransactionEntity.setTransaction_bank_in(responseUrlArray[6]);
		paymentTransactionEntity.setTransaction_bank_ref(responseUrlArray[3]);
		String temp=CommonUtils.getTransactionType(responseUrlArray[7]);
		System.out.println("temp   "+ temp);
	
		if(temp.equals("Debit Card") ||temp.equals("Credit Card")||temp.equals("Netbanking")|| temp.equals("WALLET"))
		{
			paymentTransactionEntity.setTransactionmode("I");	
		}
		System.out.println();
        //paymentTransactionEntity.setTransactionmode(CommonUtils.getTransactionType(responseUrlArray[7]));
        
		System.out.println(">>> before save"
				+ paymentTransactionEntity.getGateway_name()
				+ " Mode Of Payment " + responseUrlArray[7]
				+ " paymentTransactionEntity.getTransaction_flag() "
				+ paymentTransactionEntity.getTransaction_flag());
		  AssessmentMaster ptAssessmentMaster =assessmentservice.getsinglerecord(Long.parseLong(responseUrlArray[16]));
		  if (responseUrlArray[14].equals("0300")) {
			// update pay and del flag status
				Character delFlag = 'N';
				Character payFlag = 'Y';				
				String status = "fail";
				ptAssessmentMaster.setDelflag(delFlag);
				ptAssessmentMaster.setPaymentflag(payFlag);
				ptAssessmentMaster.setPaymentdate(new Date());
				ptAssessmentMaster.setApplication_stage("Application Approved");
				//updaterecord
				assessmentservice.updaterecord(ptAssessmentMaster);
				
				DoorNo=	DoorDemo.permanentDoorNo(ptAssessmentMaster.getUniqueRequestNumber());
				ptAssessmentMaster.setpDoorNo(DoorNo);
				AssmntNo=DoorDemo.getAssessmentNo(String.valueOf((ptAssessmentMaster.getUlbcode())));
				System.out.println(AssmntNo+" first AssmntNo");
				AssmntNo=assessmentservice.checkAssessmentNo(AssmntNo);
				System.out.println(AssmntNo+" second AssmntNo");
				System.out.println(ptAssessmentMaster.getUlbcode()+" &&&&&ptAssessmentMaster.getUlbcode()");
				ptAssessmentMaster.setAssessmentNo(AssmntNo);
				assessmentservice.updaterecord(ptAssessmentMaster);
				//assessmentservice.save(ptAssessmentMaster);
				System.out.println("Update Method Working");
				
			    NewAssesment xmlbean = new NewAssesment();
				String xmlcontent = xmlbean
						.getprepairxmlcontent(ptAssessmentMaster);
				try {
					
					AssessmentDAOImpl  daoimpl2=new AssessmentDAOImpl();
					
					int getcountfromlive = daoimpl2.getcountfromlive(ptAssessmentMaster);
					System.out.println("getcountfromlive@@@@@"+getcountfromlive);
					if (getcountfromlive==0) {

						db2updatestatus = new AssessmentDAOImpl().insertlive(ptAssessmentMaster,xmlcontent);	
						
						//status="fail";
						status="success";
					}else{
						status="success";
						db2updatestatus="success";
					}
				} catch (Exception e) {
					e.printStackTrace();
					status = "fail";
				}
			 	if (status.equalsIgnoreCase("success")) {
					//delFlag = 'N';
					//payFlag = 'Y';
					/*ptAssessmentMaster.setDelflag(delFlag);
					ptAssessmentMaster.setPaymentflag(payFlag);
					ptAssessmentMaster.setPaymentdate(new Date());
				
					//updaterecord
					assessmentservice.updaterecord(ptAssessmentMaster);*/
				
				System.out.println("db2updatestatus = status"+db2updatestatus);

				 }
		  }
		//Set Paid_update_Flag if DB2 Updated Successfully/Fail 
		if (db2updatestatus.equalsIgnoreCase("success")) {
			paymentTransactionEntity.setPaid_update_flag('S');
		} else {
			paymentTransactionEntity.setPaid_update_flag('F');
		}
	     sessionFactory.getCurrentSession().update(paymentTransactionEntity);
		if (db2updatestatus.equalsIgnoreCase("success")) {
			//Setting the  DB 2 update flag status if Success
			transactionReceipt.setUpdateStatus("Successful");
		}
		if (db2updatestatus.equalsIgnoreCase("fail")) {
		//	Setting the  DB 2 update flag status if Failed
			transactionReceipt
					.setUpdateStatus("Failed, Please Contact to CDMA OFFICE  : +91-6303298411 ");
		}
		
		transactionReceipt.setUnireqnumber(""
				+ paymentTransactionEntity.getUniqueRequestNumber());
		/*System.out.println("paymentTransactionEntity "
				+ transactionReceipt.getUnireqnumber());*/
		
		transactionReceipt.setTransactionReceiptNo(paymentTransactionEntity
				.getTranscation_id());
		
		transactionReceipt.setTransactionMode(paymentTransactionEntity
				.getTransactionmode());
		
		transactionReceipt.setC_AMTPAIDAT(paymentTransactionEntity.getC_AMTPAIDAT());
		
		/*transactionReceipt.setTransactionMode("I");
		transactionReceipt.setC_AMTPAIDAT("O");*/
	
		transactionReceipt.setFullName(paymentTransactionEntity
				.getReceipt_owner_name());
	
		transactionReceipt.setMobileNumber(paymentTransactionEntity
				.getTransaction_mobile_number());
		
		transactionReceipt.setTotalAmount(paymentTransactionEntity
				.getTotal_amount());
		
		// Setting the pay flag if pay successfully paid
		transactionReceipt.setTransactionStatus(getPaymentStatus(String
				.valueOf(paymentTransactionEntity.getTransaction_flag())));   
		
	transactionReceipt.setTransactionDate(""
				+ paymentTransactionEntity.getTransdate());
		transactionReceipt.setPaymentGateway(paymentTransactionEntity
				.getGateway_name());
	
		transactionReceipt.setUlbName(paymentTransactionEntity.getUlbname());
	
		transactionReceipt.setTransactionstatusflag(String
				.valueOf(paymentTransactionEntity.getPaid_update_flag()));

		transactionReceipt.setReason(paymentTransactionEntity
				.getTransaction_error_description());
		
		transactionReceipt.setDistrictName(paymentTransactionEntity
				.getDistrictname());
	   
	  PreparedStatement stmt = null;
		Connection Con=MySqlDBConnection.getEpayDB();
 		String Sql = "SELECT AssessmentNo,pDoorNo FROM ptassessmenttax.ptassessment_master p  WHERE p.uniqueRequestNumber="+ptAssessmentMaster.getUniqueRequestNumber()+" and p.paymentflag='Y' and delflag='N'";
 		stmt = Con.prepareStatement(Sql);
 		ResultSet rs = stmt.executeQuery();
 		String AssessmentNo="";
 		String doorNo="";
 		while (rs.next()) {
 			AssessmentNo=rs.getString("AssessmentNo");
 			doorNo=rs.getString("pDoorNo");
 		}
	    transactionReceipt.setAssessmentNo(AssessmentNo);
	    transactionReceipt.setDoorNo(doorNo);
	    System.out.println("AssessmentNo="+AssmntNo);
	    System.out.println("DoorNo="+DoorNo);
	    System.out.println("Last line");
	    Con.close();
	    System.out.println("transactionReceipt  ::::"  +transactionReceipt);
		return transactionReceipt;

	}


	private String getPaymentStatus(String flag) {
		String status = "NA";

		switch (flag) {
		case "S":
			status = "Successful";
			break;
		case "F":
			status = "Failed";
			break;

		}
		return status;
	}

	private String getDB2updateStatus(String flag) {
		String status = "NA";
        switch (flag) {
		case "S":
			status = "Successful";
			break;
		case "F":
			status = "Failed, Please Contact to CDMA OFFICE  :+91-6303298411  ";
			break;
         }
		return status;
	}
      public static void main(String[] args) {
		Character c = 'S';
		String cc = String.valueOf(c);
		System.out.println("cc " + cc);
		PaymentTransactionDAOimpl getPaymentStatus = new PaymentTransactionDAOimpl();
		System.out.println(getPaymentStatus.getPaymentStatus(cc));
		PaymentTransactionDAOimpl daOimpl = new PaymentTransactionDAOimpl();

	}


@Override
	public String updateInitiatLiveDB2(String payment_transaction_receipt_id,long uniqueReqNo) throws SQLException, IOException, NamingException {
		
		String db2status = "fail";
		String  status = "fail";
 		PaymentTransaction paymentTransaction = getsinglerecord(Long
				.parseLong(payment_transaction_receipt_id));
		AssessmentMaster ptAssessmentMaster =assessmentservice.getsinglerecord(uniqueReqNo);
		//return (new Paymentprocess().updatepayment(mutationBean,paymentTransaction));
		 NewAssesment xmlbean = new NewAssesment();
			String xmlcontent = xmlbean
					.getprepairxmlcontent(ptAssessmentMaster);
		if (paymentTransaction.getTransaction_response_code().equals("0300") && paymentTransaction.getPaid_update_flag()!='S')
		{
				// update pay and del flag status
					AssessmentDAOImpl assessmentDAOImpl=new AssessmentDAOImpl();
					//int getcountfromlive = assessmentDAOImpl.getcountfromlive(ptAssessmentMaster);				
					int getcountfromlive=0;
					if (getcountfromlive == 0) {
						db2status=assessmentDAOImpl.insertlive(ptAssessmentMaster,xmlcontent);
						} 
					else {
						
						db2status = "success";
						
					}
		
		}		
		
		 if (db2status.equalsIgnoreCase("success")) {
						paymentTransaction.setPaid_update_flag('S');
						ptAssessmentMaster.setDelflag('N');
						ptAssessmentMaster.setPaymentflag('Y');
						ptAssessmentMaster.setPaymentdate(ptAssessmentMaster.getEntrydate()); 
					} else {
						paymentTransaction.setPaid_update_flag('F');
						
							}
		
		 		  // boolean mutaionupdateflag= assessmentservice.savePTAsmntFileData(ptAssessmentMaster);
		 			boolean transcationupdateflag=	updatePaymentTransaction(paymentTransaction);
					if(transcationupdateflag==true){
						status ="success";
					} 					
					return status;
	}
	
	
	// Retrieve The Transaction Detail
	@Override
	public PaymentTransaction getsinglerecordFromUniqReqNumber(
			long uniqueReqNo) {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(PaymentTransaction.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqueReqNo));
		criteria.add(Restrictions.eq("transaction_flag", 'S'));
		criteria.add(Restrictions.eq("c_delflag", 'N'));

		PaymentTransaction getPayTransactionDetail = (PaymentTransaction) criteria
				.uniqueResult();

		return getPayTransactionDetail;
	}
	
	// update status after pay Success
	public int UpdatePayStatusDelFalg(AssessmentMaster ptAssessmentMaster) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		SQLQuery qry1 = session
				.createSQLQuery("update mutation set c_delflag=:c_delFlag,paymentflag=:payStatus,paymentdate=:payDate Where uniqueRequestNumber=:UniqReqNo ");

		qry1.setParameter("delFlag", ptAssessmentMaster.getDelflag());
		qry1.setParameter("payStatus", ptAssessmentMaster.getPaymentflag());
		qry1.setParameter("payDate", ptAssessmentMaster.getPaymentdate());
		qry1.setParameter("UniqReqNo", ptAssessmentMaster.getUniqueRequestNumber());
		
		int status = qry1.executeUpdate();

		System.out.println("PayDate ==================== "+sdf.format(new Date()));
		tx.commit();
 
		return status;
	}

	@Override
	public boolean updatePaymentTransaction(
			PaymentTransaction paymentTransaction) {
		boolean flag = false;
		try {
			Session session = sessionFactory.openSession();

		Transaction tx1 = session.beginTransaction();
		session.saveOrUpdate(paymentTransaction);
		flag = true;
		tx1.commit();
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		
		return flag;
	}

	

	
	

}
