/**
 * 
 */
package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.InitiatePaymentTransactionDao;
import com.mars.cdma.gov.Dao.PaymentTransactionDAO;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.InitiatePaymentTransactionService;
import com.mars.cdma.gov.service.PaymentTransactionService;

/**
 * @author RAHUL
 *
 */

@Service("initiatePaymentTransactionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class InitiatePaymentTransactionServiceImpl implements
		InitiatePaymentTransactionService {
	
	@Autowired
	public InitiatePaymentTransactionDao initiatePaymentTransactionDAO;
	@Override
	public AddMeasurementTransactionHistory gettransingelrecord(
			long payment_transaction_receipt_id) {
		return null;
	}
	@Override
	public AddMeasurementTransactionHistory getsinglerecordFromUniqReqNumber(
			long uniqueReqNo) {
		return initiatePaymentTransactionDAO.getsinglerecordFromUniqReqNumber(uniqueReqNo);
	}
	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory paymentTransactionEntity,
			String responseUrl) throws SQLException {
		return null;
	}
	@Override
	public String updateInitiatLiveDB2(String payment_transaction_receipt_id,
			long uniqueReqNo) throws SQLException, IOException, NamingException {
		return initiatePaymentTransactionDAO.updateInitiatLiveDB2(payment_transaction_receipt_id, uniqueReqNo);
	}
	@Override
	public boolean updatePaymentTransaction(
			AddMeasurementTransactionHistory paymentTransaction) {
		return initiatePaymentTransactionDAO.updatePaymentTransaction(paymentTransaction);
	}
}
