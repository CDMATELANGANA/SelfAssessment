/**
 * 
 */
package com.mars.cdma.gov.Dao;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
/**
 * @author RAHUL
 *
 */
public interface PaymentTransactionDAO 
{
public PaymentTransaction getsinglerecord(long payment_transaction_receipt_id);
public PaymentTransaction getsinglerecordFromUniqReqNumber(long uniqueReqNo);
public TransactionReceipt  getdetailsfromresponseurl(PaymentTransaction paymentTransactionEntity,String responseUrl) throws SQLException;//Update live db2	
//public String updateInitiatLiveDB2(String payment_transaction_receipt_id,long uniqueReqNo);
public String updateInitiatLiveDB2(String payment_transaction_receipt_id,long uniqueReqNo) throws SQLException, IOException, NamingException;
public boolean updatePaymentTransaction(PaymentTransaction paymentTransaction);
		
	

}
