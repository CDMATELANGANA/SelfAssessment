package com.mars.cdma.gov.Dao;

import java.util.List;

public interface PTSelfAsmntGateWayDAO {

	List<String> totalAmount(String ulbCode);

	List<String> gatewayCount(String ulbCode);

	List<String> ulbDetailReport(String ulb);

	List<String> getDataByInterval(String fromDate, String toDate, String ulb);

	List<String> gatewayCountByDate(String fromDate, String toDate);

	List<String> transDetailReportByDate(String fromDate, String toDate);

	List<String> getDetailTransactionReport();

	List<String> ulbReport(String ulbCode);

	

}
