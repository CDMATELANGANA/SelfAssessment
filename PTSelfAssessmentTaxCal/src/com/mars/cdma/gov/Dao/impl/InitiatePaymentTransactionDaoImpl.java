/**
 * 
 */
package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.InitiatePaymentTransactionDao;
import com.mars.cdma.gov.bean.AddAsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AddAssessmentMaster;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.AddAsmntMeasurementService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.InitiatePaymentTransactionService;
import com.mars.cdma.gov.service.PaymentTransactionService;

/**
 * @author RAHUL
 *
 */
@Repository("initiatePaymentTransactionDao")
@Transactional
public class InitiatePaymentTransactionDaoImpl implements
		InitiatePaymentTransactionDao {
	
	private static final Logger log = Logger
			.getLogger(PaymentTransactionDAOimpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private AddAsmntMeasurementService addAsmntMeasurementService;

	@Autowired
	private InitiatePaymentTransactionService initiatepaymenttrSerivce;
	

	@Override
	public AddMeasurementTransactionHistory getsingelrecord(
			long payment_transaction_receipt_id) {
		AddMeasurementTransactionHistory paymentTransaction = new AddMeasurementTransactionHistory();
			System.out.println("payment_transaction_receipt_id "
					+ payment_transaction_receipt_id);
			Session session = sessionFactory.getCurrentSession();
			paymentTransaction = (AddMeasurementTransactionHistory) session.get(
					AddMeasurementTransactionHistory.class, payment_transaction_receipt_id);
			return paymentTransaction;
		}
	@Override
	public AddMeasurementTransactionHistory getsinglerecordFromUniqReqNumber(
			long uniqueReqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory paymentTransactionEntity,
			String responseUrl) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateInitiatLiveDB2(String payment_transaction_receipt_id,
			long uniqueReqNo) throws SQLException, IOException, NamingException {
		String db2status = "fail";
		String  status = "fail";
		System.out.println("payment_transaction_receipt_id ::::::"  +payment_transaction_receipt_id);
		AddMeasurementTransactionHistory paymentTransaction = getsingelrecord(Long
				.parseLong(payment_transaction_receipt_id));
		AddAssessmentMaster ptAssessmentMaster =addAsmntMeasurementService.getrecordForAddMeasurement(uniqueReqNo);
		System.out.println("ptAssessmentMaster  :::::::::  "+ptAssessmentMaster.getPaymentflag());
		if (paymentTransaction.getTransaction_response_code().equals("0300") && paymentTransaction.getPaid_update_flag()!='S')
		{
			AddAsmntMeasurementDaoImpl assessmentDAOImpl=new AddAsmntMeasurementDaoImpl();
					//int getcountfromlive = assessmentDAOImpl.getcountfromlive(ptAssessmentMaster);				
					int getcountfromlive=0;
					if (getcountfromlive == 0) {
						db2status=assessmentDAOImpl.insertlive(ptAssessmentMaster);
						System.out.println("@@ db2status @@::::::::::::"+db2status);
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
		
		 			boolean transcationupdateflag=	updatePaymentTransaction(paymentTransaction);
					if(transcationupdateflag==true){
						status ="success";
					} 					
					return status;
	
	}

	@Override
	public boolean updatePaymentTransaction(
			AddMeasurementTransactionHistory paymentTransaction) {
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
