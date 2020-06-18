package com.mars.cdma.gov.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.AssessmentInitiateDao;
import com.mars.cdma.gov.service.AssessmentInitiateService;

/**
 * @author SARITA
 *
 */
@Service
public class AssessmentInitiateServiceImpl implements AssessmentInitiateService {
	@Autowired
	private AssessmentInitiateDao assessmentInitiateDao;

	@Override
	public List<Object[]> getInitiateAllDistrictList() {
		return assessmentInitiateDao.getInitiateAllDistrictList();
	}

	@Override
	public List<Object[]> getInitiateSelectedDistrictUlbs(String districtid) {
		return assessmentInitiateDao.getInitiateSelectedDistrictUlbs(districtid);
	}

	@Override
	public List<String> getInitiateSelectedUlbList(String ulbcode) throws SQLException {
		return assessmentInitiateDao.getInitiateSelectedUlbList(ulbcode);
	}

	@Override
	public List<Object[]> getInitiateSelectedUlbDetailList(
			List<String> assmentList,String ulbcode) {
		return assessmentInitiateDao.getInitiateSelectedUlbDetailList(assmentList,ulbcode);
	}

	@Override
	public List<String> getInitiateSelectedUlbDuplicateAssList(String ulbcode)
			throws SQLException {
		return assessmentInitiateDao.getInitiateSelectedUlbDuplicateAssList(ulbcode);
	}

	@Override
	public List<Object[]> getInitiateSelectedUlbDuplicateAssDetailList(
			List<String> assmentList, String ulbcode) {
		return assessmentInitiateDao.getInitiateSelectedUlbDuplicateAssDetailList(assmentList, ulbcode);
	}

	@Override
	public void updateBeforeInitiate(String assessment, String uniqueno) throws SQLException {
		assessmentInitiateDao.updateBeforeInitiate(assessment, uniqueno);		
	}

}
