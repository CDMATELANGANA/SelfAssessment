package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.PaymentTransactionDAO;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.PaymentTransactionService;
@Service("paymenttrSerivce")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaymentTransactionServiceimpl implements PaymentTransactionService {
	@Autowired
	public PaymentTransactionDAO paymentTransactionDAO;

	@Override
	public PaymentTransaction gettransingelrecord(
			long payment_transaction_receipt_id) {
	
		return paymentTransactionDAO
				.getsinglerecord(payment_transaction_receipt_id);
	}

	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			PaymentTransaction paymentTransactionEntity, String responseUrl) throws SQLException {
		return paymentTransactionDAO.getdetailsfromresponseurl(
				paymentTransactionEntity, responseUrl);
	}

	
	 @Override
		public String updateInitiatLiveDB2(String payment_transaction_receipt_id,
				long uniqueReqNo) throws SQLException, IOException, NamingException {
			
			return paymentTransactionDAO.updateInitiatLiveDB2(payment_transaction_receipt_id,uniqueReqNo);
		}
	@Override
	public PaymentTransaction getsinglerecordFromUniqReqNumber(
			long uniqueReqNo) {
		return paymentTransactionDAO.getsinglerecordFromUniqReqNumber(uniqueReqNo);
	}

	/* (non-Javadoc)
	 * @see com.mars.cdma.gov.service.PaymentTransactionService#updatePaymentTransaction(com.mars.cdma.gov.bean.PaymentTransaction)
	 */
	@Override
	public boolean updatePaymentTransaction(
			PaymentTransaction paymentTransaction) {
		
		return paymentTransactionDAO.updatePaymentTransaction(paymentTransaction);
	}

	

}
