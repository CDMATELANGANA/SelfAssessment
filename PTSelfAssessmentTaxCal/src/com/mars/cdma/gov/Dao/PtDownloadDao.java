package com.mars.cdma.gov.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.mars.cdma.gov.bean.AssesmentBean;
import com.mars.cdma.gov.bean.AssessmentMaster;

/**
 * @author SARITA
 *
 */
public interface PtDownloadDao {
	
	public String assessmentCopyOtp(String mobile,String assessment);
	public AssessmentMaster getPdfAssessmentReport(long uniquerequestid);
	public List<String> getassessmentdata(String assessment_number) throws SQLException;
	
	public boolean saveAssessmentDownloadDetail(String assessment,String mobile);
}
