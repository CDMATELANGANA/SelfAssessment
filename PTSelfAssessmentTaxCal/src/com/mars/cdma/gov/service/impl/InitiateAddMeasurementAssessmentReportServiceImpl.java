/**
 * 
 */
package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.InitiateAddMeasurementAssessmentReportDao;
import com.mars.cdma.gov.service.InitiateAddmeasurementAssessmentReportService;

/**
 * @author RAHUL
 *
 */
@Service("initiateAssessmentReportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InitiateAddMeasurementAssessmentReportServiceImpl implements InitiateAddmeasurementAssessmentReportService {

	@Autowired
	InitiateAddMeasurementAssessmentReportDao initiateAssessmentReportDao;
	@Override
	public List getptSelfAsmntTransStatus(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		// TODO Auto-generated method stub
		return initiateAssessmentReportDao.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
	}

	@Override
	public List<Object> getptSelfAsmntTransCollection() {
		// TODO Auto-generated method stub
		return initiateAssessmentReportDao.getptSelfAsmntTransCollection();
	}

	@Override
	public List getptSelfAsmntTransDetail(String tranYear) {
		// TODO Auto-generated method stub
		return initiateAssessmentReportDao.getptSelfAsmntTransDetail(tranYear);
	}

}
