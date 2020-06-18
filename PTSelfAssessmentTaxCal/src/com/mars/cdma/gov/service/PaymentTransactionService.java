package com.mars.cdma.gov.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;

@Transactional(readOnly = true)
public interface PaymentTransactionService {

	public PaymentTransaction gettransingelrecord(
			long payment_transaction_receipt_id);

	public PaymentTransaction getsinglerecordFromUniqReqNumber(long uniqueReqNo);

	public TransactionReceipt getdetailsfromresponseurl(
			PaymentTransaction paymentTransactionEntity, String responseUrl) throws SQLException;

	public String updateInitiatLiveDB2(String payment_transaction_receipt_id,
			long uniqueReqNo) throws SQLException, IOException, NamingException;

	public boolean updatePaymentTransaction(
			PaymentTransaction paymentTransaction);

}
