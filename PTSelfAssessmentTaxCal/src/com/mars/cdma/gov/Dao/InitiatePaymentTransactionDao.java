/**
 * 
 */
package com.mars.cdma.gov.Dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.TransactionReceipt;

/**
 * @author RAHUL
 *
 */
public interface InitiatePaymentTransactionDao {
	
	public AddMeasurementTransactionHistory getsingelrecord(
			long payment_transaction_receipt_id);

	public AddMeasurementTransactionHistory getsinglerecordFromUniqReqNumber(long uniqueReqNo);

	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory paymentTransactionEntity, String responseUrl) throws SQLException;

	public String updateInitiatLiveDB2(String payment_transaction_receipt_id,
			long uniqueReqNo) throws SQLException, IOException, NamingException;

	public boolean updatePaymentTransaction(
			AddMeasurementTransactionHistory paymentTransaction);



}
