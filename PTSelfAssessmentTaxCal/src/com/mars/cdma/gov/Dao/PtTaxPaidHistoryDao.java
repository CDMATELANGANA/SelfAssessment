package com.mars.cdma.gov.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import com.mars.cdma.gov.bean.AssessmentReceipt;


/**
 * @author SARITA
 *
 */
public interface PtTaxPaidHistoryDao {
	public String ptAssPaidHistorySms(String mobile,String assessment);
	public boolean savePtAssPaidHistory(String assessment,String mobile);
	public  HashMap<String, Object> getPtAssPaidHistoryFromDb2(String Ulb,String assessment) throws SQLException, IOException, NamingException ;
	public  List<AssessmentReceipt> getAssessmentReceiptList(String Ulb,String assessment)throws SQLException, IOException, NamingException ;
}
