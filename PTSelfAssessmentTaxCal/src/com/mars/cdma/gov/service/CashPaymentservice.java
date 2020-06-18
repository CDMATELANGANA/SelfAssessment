package com.mars.cdma.gov.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;

public interface CashPaymentservice {
	public PaymentTransaction insertCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId);
	public void updateptSelfAssessement(long uniqReqNumber);

	public List<PaymentTransaction> getsinglerecordForTransaction(
			long payment_transaction_receipt_id);
	public AssessmentMaster getptselfAssestsinglerecord(long uniqReqNumber);
	public AssessmentMaster getStatus1(long parseLong);
	
	public PaymentTransaction insertChequeDDCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId, String cheque_DD_No, Date cheque_DD_Date,
			String cheque_DD_BankBranch, String DDCheque);

	

}
