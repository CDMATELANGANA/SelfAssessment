package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mars.cdma.gov.Dao.PTCashGatewayDao;
import com.mars.cdma.gov.service.PTCashGatewaytService;

@Service
public class PTCashGatewaytServiceImpl implements PTCashGatewaytService {
	
	@Autowired
	private PTCashGatewayDao  cashReportDao;

	@Override
	public List<String> totalAmount(String ulbCode) {
		// TODO Auto-generated method stub
		return cashReportDao.totalAmount(ulbCode);
	}

	@Override
	public List<String> cashULBReport(String ulbCode) {
		// TODO Auto-generated method stub
		return cashReportDao.cashULBReport(ulbCode);
	}

	@Override
	public List<String> cashGatewayCount(String ulbCode) {
		// TODO Auto-generated method stub
		return cashReportDao.cashGatewayCount(ulbCode);
	}

	@Override
	public List<String> cashULBDetailReport(String ulb) {
		// TODO Auto-generated method stub
		return cashReportDao.cashULBDetailReport(ulb);
	}

	@Override
	public List<String> getCashDetailTransactionReport() {
		// TODO Auto-generated method stub
		return cashReportDao.getCashDetailTransactionReport();
	}

	@Override
	public List<String> getCashDataByInterval(String fromDate, String toDate,
			String ulb) {
		// TODO Auto-generated method stub
		return cashReportDao.getCashDataByInterval(fromDate, toDate, ulb);
	}

	@Override
	public List<String> cashgatewayCountByDate(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return cashReportDao.cashgatewayCountByDate(fromDate, toDate);
	}

	@Override
	public List<String> transCashDetailReportByDate(String fromDate,
			String toDate) {
		// TODO Auto-generated method stub
		return cashReportDao.transCashDetailReportByDate(fromDate, toDate);
	}

}
