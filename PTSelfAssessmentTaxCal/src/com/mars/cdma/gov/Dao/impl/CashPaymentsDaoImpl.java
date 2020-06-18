package com.mars.cdma.gov.Dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.CashPaymentsDao;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;


@Repository("assessmentptselfDAO")
public class CashPaymentsDaoImpl implements CashPaymentsDao{
	
	private static final Logger log = Logger.getLogger(CashPaymentsDaoImpl.class);
	
	Connection con = null;
	
	@Autowired
	private UlbsService ulbService;

	@Autowired
	SessionFactory  sessionFactory;
	
	@Autowired
	private DistrictsService districtsService;
	
	
	
	public int Count(long uniqReqNumber) {
		Session session = sessionFactory.getCurrentSession();
		String sql="select count(*) as count from ptassessmenttax.transaction_history where uniqueRequestNumber="+uniqReqNumber+" and c_delflag='N'";
	
		
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
         //l=l.replace("[","").replace("]", "");
        int c=Integer.parseInt(l.replace("[","").replace("]", ""));
        
        System.out.println("Couunt history =="+c);
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	

	@Override
	public PaymentTransaction insertCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId) {
		
		Session session = sessionFactory.getCurrentSession();
		PaymentTransaction paymentTransactionEntity = new PaymentTransaction();
	
		int c=Count(uniqReqNumber);
		if(c!=1)
		{
		System.out.println("enter in if");
		String result = "fail";
		//String result = "fail";
		AssessmentMaster assessmenymaster =getptselfAssestsinglerecord(uniqReqNumber) ;
		//System.out.println("fetch data from db "+assessmenymaster.getAssesmentno());
		paymentTransactionEntity.setGateway_name("CashCounter");
		if(paymentTransactionEntity.getGateway_name()=="CashCounter"){
			paymentTransactionEntity.setC_AMTPAIDAT("D");
		}
		paymentTransactionEntity.setTotal_amount(assessmenymaster
				.getPaymentamount());
		paymentTransactionEntity.setPaid_update_flag('S');
		paymentTransactionEntity.setTransaction_response_code("0300");
		paymentTransactionEntity.setTransaction_error_description("Success");
		paymentTransactionEntity.setTransaction_type("05");
		paymentTransactionEntity.setReceipt_owner_door_no(assessmenymaster
				.getOwnerDoorNo());
		paymentTransactionEntity.setDistrictname(assessmenymaster.getOwnerCity());
		paymentTransactionEntity.setTransactionmode("C");
		paymentTransactionEntity.setReceipt_owner_name(assessmenymaster.getOwnerSurName().concat(" ").concat(assessmenymaster
				.getOwnerName()));
		paymentTransactionEntity.setTransaction_mobile_number(assessmenymaster.getOwnerMobile());
		paymentTransactionEntity.setTransaction_flag('S');
		paymentTransactionEntity.setUlbcode(assessmenymaster.getUlbcode());
		paymentTransactionEntity.setUniqueRequestNumber(assessmenymaster.getUniqueRequestNumber());
		paymentTransactionEntity.setC_delflag('N');
		paymentTransactionEntity.setTransdate(new java.util.Date());
		paymentTransactionEntity.setLoginId(loginId);
		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());
        paymentTransactionEntity.setUlbname(ulbs.getUlbName());
		session.save(paymentTransactionEntity);
		return paymentTransactionEntity;
		}
		else
		{
			System.out.println("enter in else");
			Criteria criteria = session.createCriteria(PaymentTransaction.class);
			criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
			paymentTransactionEntity = (PaymentTransaction) criteria.uniqueResult();
			//paymentTransactionEntity=(PaymentTransaction)session.get(PaymentTransaction.class, uniqReqNumber);
			return paymentTransactionEntity;
		}
	}

	@Override
	public void updateptSelfAssessement(long UniqueRequestNumber) {
		System.out.println("uniqReqNumber @@@@@@@@@@"+UniqueRequestNumber);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction=null;
			session=sessionFactory.openSession() ;
			transaction=session.beginTransaction();
			Query query=session.createQuery("update AssessmentMaster set delflag='N',paymentflag='Y' where uniqueRequestNumber="+UniqueRequestNumber);
			System.out.println("query  "+query);
			query.executeUpdate();
			transaction.commit();
			session.close();
			}
	@Override
	public AssessmentMaster getptselfAssestsinglerecord(long uniqueRequestNumber) {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(AssessmentMaster.class);
			System.out.println("uniqueRequestNumber "+uniqueRequestNumber);
			criteria.add(Restrictions.eq("uniqueRequestNumber", uniqueRequestNumber));
			AssessmentMaster getsinglerecord = (AssessmentMaster) criteria.uniqueResult();
			return getsinglerecord;
		}
	@Override
	public List<PaymentTransaction> getsinglerecordForTransaction(
			long payment_transaction_receipt_id) {
				System.out.println("payment_transaction_receipt_id "
						+ payment_transaction_receipt_id);
				Session session = sessionFactory.getCurrentSession();
				String sql="SELECT t.uniqueRequestNumber,t.receipt_owner_name,t.transaction_mobile_number,t.ulbname,t.districtname,t.transactionmode,"
			+" t.gateway_name,t.transaction_error_description,t.total_amount,t.transdate FROM transaction_history t"
			+" where t.payment_transaction_receipt_id="+payment_transaction_receipt_id+"";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				List<PaymentTransaction> paymentTransaction = query.list();
				return  paymentTransaction;
			}

	


	@Override
	public AssessmentMaster getStatus1(long parseLong) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		System.out.println("GeStatus >>> " + parseLong);
		AssessmentMaster getSingleRecord = getptselfAssestsinglerecord(parseLong);
		System.out.println("getSingleRecord"+getSingleRecord);
		return getSingleRecord;
	}
	@Override
	public PaymentTransaction insertChequeDDCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId, String cheque_DD_No, Date cheque_DD_Date,
			String cheque_DD_BankBranch, String DDCheque) {
		System.out.println("dDCheque***********"+DDCheque);
		Session session = sessionFactory.getCurrentSession();
		PaymentTransaction paymentTransactionEntity = new PaymentTransaction();
		int c=Count(uniqReqNumber);
		if(c!=1)
		{
		
		String result = "fail";
		
		AssessmentMaster asmntmaster =getptselfAssestsinglerecord(uniqReqNumber) ;
		//System.out.println("fetch data from db "+eTradeApplication.getAssesmentno());
		paymentTransactionEntity.setGateway_name("CashCounter");
		if(paymentTransactionEntity.getGateway_name()=="CashCounter"){
			paymentTransactionEntity.setC_AMTPAIDAT("D");
		}
		paymentTransactionEntity.setTotal_amount(asmntmaster
				.getPaymentamount());
		paymentTransactionEntity.setPaid_update_flag('S');
		paymentTransactionEntity.setTransaction_response_code("0300");
		paymentTransactionEntity.setTransaction_error_description("Success");
		paymentTransactionEntity.setTransaction_type("05");
		paymentTransactionEntity.setReceipt_owner_door_no(asmntmaster.getOwnerDoorNo());
		
		paymentTransactionEntity.setReceipt_owner_name(asmntmaster.getOwnerSurName().concat(" ").concat(asmntmaster.getOwnerName()));
		paymentTransactionEntity.setTransaction_mobile_number(asmntmaster.getOwnerMobile());
		paymentTransactionEntity.setTransaction_flag('S');
		paymentTransactionEntity.setUlbcode(asmntmaster.getUlbcode());
		paymentTransactionEntity.setUniqueRequestNumber(asmntmaster.getUniqueRequestNumber());
		paymentTransactionEntity.setC_delflag('N');
		paymentTransactionEntity.setTransdate(new java.util.Date());
		paymentTransactionEntity.setLoginId(loginId);
		paymentTransactionEntity.setCheque_DD_BankBranch(cheque_DD_BankBranch);
		paymentTransactionEntity.setCheque_DD_Date(cheque_DD_Date);
		paymentTransactionEntity.setCheque_DD_No(cheque_DD_No);;
		if(DDCheque.equalsIgnoreCase("Q"))
		{
			paymentTransactionEntity.setTransactionmode("Q");
		}
		if(DDCheque.equalsIgnoreCase("DD"))
		{
			paymentTransactionEntity.setTransactionmode("D");
		}
		
		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity
				.getUlbcode());
		System.out.println("ulbs " + ulbs.getUlbName());
		Districts districts = districtsService.get(ulbs.getDistrict_id());
		System.out.println("distname " + districts.getDistrictName());
        paymentTransactionEntity.setDistrictname(districts.getDistrictName());
		paymentTransactionEntity.setUlbname(ulbs.getUlbName());
		session.save(paymentTransactionEntity);
		return paymentTransactionEntity;
		}
		else 
		{
			System.out.println("enter in else");
			Criteria criteria = session.createCriteria(PaymentTransaction.class);
			criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
			paymentTransactionEntity = (PaymentTransaction) criteria.uniqueResult();
			return paymentTransactionEntity;
		
	}
	}
	
	
	}



