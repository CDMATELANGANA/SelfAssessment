/**
 * 
 */
package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.PtSelfAssmntReportsDao;
import com.mars.cdma.gov.service.PtSelfAssessmentReportService;

/**
 * @author RAHUL
 *
 */

@Service("ptSelfAsmntReportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PtSelfAssessmentReportServiceImpl implements
		PtSelfAssessmentReportService {
@Autowired
private PtSelfAssmntReportsDao ptSelfAssmntReportDao;
	@Override
	public List getSelfAsmntTransDetail(String tranYear) {
		return ptSelfAssmntReportDao.getSelfAsmntTransDetail(tranYear) ;
	}

	@Override
	public List getSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		return ptSelfAssmntReportDao.getSelfAsmntTransDetailByReqNumber(uniqueRequestId);
	}

	@Override
	public List<Object> getSelfAsmntTransCollection() {
		return ptSelfAssmntReportDao.getSelfAsmntTransCollection();
	}

	@Override
	public List<Object> getSelfAsmntTransStatus(int transReceiptNo) {
		return ptSelfAssmntReportDao.getSelfAsmntTransStatus(transReceiptNo);
	}

}
