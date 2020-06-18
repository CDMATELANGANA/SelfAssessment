package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.PtTaxPaidHistoryDao;
import com.mars.cdma.gov.bean.AssessmentReceipt;
import com.mars.cdma.gov.service.PtTaxPaidHistoryService;
/**
 * @author SARITA
 *
 */
@Service
public class PtTaxPaidHistoryServiceImpl implements PtTaxPaidHistoryService {
@Autowired
private PtTaxPaidHistoryDao ptTaxPaidHistoryDao;
	@Override
	public String ptAssPaidHistorySms(String mobile, String assessment) {
		return ptTaxPaidHistoryDao.ptAssPaidHistorySms(mobile, assessment);
	}

	@Override
	public boolean savePtAssPaidHistory(String assessment, String mobile) {
		return ptTaxPaidHistoryDao.savePtAssPaidHistory(assessment, mobile);
	}

	@Override
	public HashMap<String, Object> getPtAssPaidHistoryFromDb2(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		return ptTaxPaidHistoryDao.getPtAssPaidHistoryFromDb2(Ulb, assessment);
	}

	@Override
	public List<AssessmentReceipt> getAssessmentReceiptList(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		return ptTaxPaidHistoryDao.getAssessmentReceiptList(Ulb, assessment);
	}

}
