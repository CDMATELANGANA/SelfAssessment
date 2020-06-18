package com.mars.cdma.gov.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.PTSelfAsmntGateWayDAO;
import com.mars.cdma.gov.service.PTSelfAsmntGateWayDashBoardService;



@Service("PTSelfAsmntGateWayDashBoardService")
public class PTSelfAsmntGateWayDashBoardServiceimpl implements PTSelfAsmntGateWayDashBoardService {
	private static final Logger log= Logger.getLogger(PTSelfAsmntGateWayDashBoardServiceimpl.class);
	
	@Autowired
	private PTSelfAsmntGateWayDAO ptSelfAsmntGateWayDAO;

	@Override
	public List<String> totalAmount(String ulbCode)
			throws DataAccessResourceFailureException, HibernateException,
			IllegalStateException, SQLException {
		if(log.isDebugEnabled()){
			log.debug("total amount is called");
		}
		// TODO Auto-generated method stub
		return ptSelfAsmntGateWayDAO.totalAmount(ulbCode);
	}

	@Override
	public List<String> ULBReport(String ulbCode) {
		
		if(log.isDebugEnabled()){
			log.debug("gatewayCount Method is called");
		}
		return ptSelfAsmntGateWayDAO.ulbReport(ulbCode);
	}

	@Override
	public List<String> gatewayCount(String ulbCode) {
		if (log.isDebugEnabled()) {
			log.debug("gatewayCount Method is called");
		}
		return ptSelfAsmntGateWayDAO.gatewayCount(ulbCode);
	}

	@Override
	public List<String> ULBDetailReport(String ulb) {
		if (log.isDebugEnabled()) {
			log.debug("ULBDetailReport Method is called");
		}
		return ptSelfAsmntGateWayDAO.ulbDetailReport(ulb);
	}

	@Override
	public List<String> getDataByInterval(String fromDate, String toDate,
			String ulb) {
		if (log.isDebugEnabled()) {
			log.debug("GetDataByInterval Method is called");
		}
		return ptSelfAsmntGateWayDAO.getDataByInterval(fromDate,toDate,ulb);
	}

	@Override
	public List<String> gatewayCountByDate(String fromDate, String toDate) {
		if (log.isDebugEnabled()) {
			log.debug("GetDataByInterval Method is called");
		}
		return ptSelfAsmntGateWayDAO.gatewayCountByDate(fromDate,toDate);
	}

	@Override
	public List<String> transDetailReportByDate(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return ptSelfAsmntGateWayDAO.transDetailReportByDate(fromDate, toDate);
	}

	@Override
	public List<String> getDetailTransactionReport() {
		// TODO Auto-generated method stub
		return  ptSelfAsmntGateWayDAO.getDetailTransactionReport();
	}

}
