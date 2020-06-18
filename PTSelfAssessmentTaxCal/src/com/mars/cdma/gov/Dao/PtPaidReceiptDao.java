package com.mars.cdma.gov.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;


/**
 * @author SARITA
 *
 */
public interface PtPaidReceiptDao {
	public String ptAssPaidReceiptSms(String mobile,String assessment);
	public boolean savePtAssPaidReceipt(String assessment,String mobile);
	public  HashMap<String, Object> getPtAssPaidLastReceiptFromDb2(String Ulb,String assessment) throws SQLException, IOException, NamingException ;

}
