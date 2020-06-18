package com.mars.cdma.gov.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.PtDownloadDao;
import com.mars.cdma.gov.bean.AssesmentBean;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.service.PtDownloadService;

/**
 * @author SARITA
 *
 */
@Service
public class PtDownloadServiceImpl implements PtDownloadService {
	@Autowired
	private PtDownloadDao PtDownloadDao;
	@Override
	public String assessmentCopyOtp(String mobile, String assessment) {

		return PtDownloadDao.assessmentCopyOtp(mobile, assessment);
	}
	@Override
	public AssessmentMaster getPdfAssessmentReport(long uniquerequestid) {
		return PtDownloadDao.getPdfAssessmentReport(uniquerequestid);
	}
	@Override
	public List<String> getassessmentdata(String assessment_number) throws SQLException {
		return PtDownloadDao.getassessmentdata(assessment_number);
	}
	@Override
	public boolean saveAssessmentDownloadDetail(String assessment,
			String mobile) {
		return PtDownloadDao.saveAssessmentDownloadDetail(assessment, mobile);
	}
	

}
