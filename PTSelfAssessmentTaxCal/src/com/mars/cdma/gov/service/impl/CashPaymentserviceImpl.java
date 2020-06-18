package com.mars.cdma.gov.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.AssessmentDAO;
import com.mars.cdma.gov.Dao.CashPaymentsDao;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.service.CashPaymentservice;



@Service("cashpaymentservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CashPaymentserviceImpl implements CashPaymentservice {
	
	
	@Autowired
	public CashPaymentsDao cashPaymentsDao;

	@Override
	public PaymentTransaction insertCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId) {
		
		return cashPaymentsDao.insertCashtransaction(uniqReqNumber,request,response,loginId);
	}

	@Override
	public void updateptSelfAssessement(long uniqReqNumber) {
		// TODO Auto-generated method stub
		cashPaymentsDao.updateptSelfAssessement(uniqReqNumber);
	}
	@Override
	public PaymentTransaction insertChequeDDCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId, String cheque_DD_No, Date cheque_DD_Date,
			String cheque_DD_BankBranch, String DDCheque) {
		// TODO Auto-generated method stub
		return cashPaymentsDao.insertChequeDDCashtransaction(uniqReqNumber, request, response, loginId, cheque_DD_No, cheque_DD_Date, cheque_DD_BankBranch, DDCheque);
	}
	
	


	@Override
	public List<PaymentTransaction> getsinglerecordForTransaction(
			long payment_transaction_receipt_id) {
		// TODO Auto-generated method stub
		return cashPaymentsDao.getsinglerecordForTransaction(payment_transaction_receipt_id);
	}

	@Override
	public AssessmentMaster getptselfAssestsinglerecord(long uniqReqNumber) {
		// TODO Auto-generated method stub
		return  cashPaymentsDao.getptselfAssestsinglerecord(uniqReqNumber);
	}

	@Override
	public AssessmentMaster getStatus1(long parseLong) {
		// TODO Auto-generated method stub
		return cashPaymentsDao.getStatus1(parseLong);
	}

}


