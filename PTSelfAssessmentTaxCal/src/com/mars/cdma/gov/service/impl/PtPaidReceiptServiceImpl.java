package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.PtPaidReceiptDao;
import com.mars.cdma.gov.service.PtPaidReceiptService;
/**
 * @author SARITA
 *
 */
@Service
public class PtPaidReceiptServiceImpl implements PtPaidReceiptService {
@Autowired
private PtPaidReceiptDao ptPaidReceiptDao;
	@Override
	public String ptAssPaidReceiptSms(String mobile, String assessment) {
		return ptPaidReceiptDao.ptAssPaidReceiptSms(mobile, assessment);
	}

	@Override
	public boolean savePtAssPaidReceipt(String assessment, String mobile) {
		return ptPaidReceiptDao.savePtAssPaidReceipt(assessment, mobile);
	}

	@Override
	public HashMap<String, Object> getPtAssPaidLastReceiptFromDb2(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		return ptPaidReceiptDao.getPtAssPaidLastReceiptFromDb2(Ulb, assessment);
	}

}
