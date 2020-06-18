package com.mars.cdma.gov.Dao;

import java.util.List;

public interface PTCashGatewayDao {

	List<String> totalAmount(String ulbCode);

	List<String> cashULBReport(String ulbCode);

	List<String> cashGatewayCount(String ulbCode);

	List<String> cashULBDetailReport(String ulb);

	List<String> getCashDetailTransactionReport();

	List<String> getCashDataByInterval(String fromDate, String toDate,
			String ulb);

	List<String> cashgatewayCountByDate(String fromDate, String toDate);

	List<String> transCashDetailReportByDate(String fromDate, String toDate);

}
