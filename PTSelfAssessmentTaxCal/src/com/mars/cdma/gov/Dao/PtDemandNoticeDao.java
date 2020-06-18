package com.mars.cdma.gov.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;

/**
 * @author SARITA
 *
 */
public interface PtDemandNoticeDao {
	public String assessmentDemandSms(String mobile,String assessment);
	public boolean saveAssDemandDownloadDetail(String assessment,String mobile);
	public  HashMap<String, Object> getDemandRecordFromDb2(String Ulb,String assessment) throws SQLException, IOException, NamingException ;
}
