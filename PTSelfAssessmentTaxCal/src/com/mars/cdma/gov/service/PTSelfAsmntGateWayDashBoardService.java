package com.mars.cdma.gov.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessResourceFailureException;

public interface PTSelfAsmntGateWayDashBoardService {
	
	 public List<String> totalAmount(String ulbCode)throws DataAccessResourceFailureException, HibernateException, IllegalStateException, SQLException;

		
	 public	List<String> ULBReport(String ulbCode);

	
	 public	List<String> gatewayCount(String ulbCode);

	
	 public List<String> ULBDetailReport(String ulb);

	 public List<String> getDataByInterval(String fromDate, String toDate,String ulb);


	/**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<String> gatewayCountByDate(String fromDate, String toDate);
	public List<String> transDetailReportByDate(String fromDate, String toDate);
	public List<String> getDetailTransactionReport();
}


