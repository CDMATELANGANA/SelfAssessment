package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.PtDemandNoticeDao;
import com.mars.cdma.gov.Dao.PtDownloadDao;
import com.mars.cdma.gov.service.PtDemandNoticeService;
/**
 * @author SARITA
 *
 */
@Service
public class PtDemandNoticeServiceImpl implements PtDemandNoticeService {
	@Autowired
	private PtDemandNoticeDao ptDemandNoticeDao;

	@Override
	public String assessmentDemandSms(String mobile, String assessment) {
		return ptDemandNoticeDao.assessmentDemandSms(mobile, assessment);
	}

	@Override
	public boolean saveAssDemandDownloadDetail(String assessment, String mobile) {
		return ptDemandNoticeDao.saveAssDemandDownloadDetail(assessment, mobile);
	}

	@Override
	public HashMap<String, Object> getDemandRecordFromDb2(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		return ptDemandNoticeDao.getDemandRecordFromDb2(Ulb, assessment);
	}
	
}
