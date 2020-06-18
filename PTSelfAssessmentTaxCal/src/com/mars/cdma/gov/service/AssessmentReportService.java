package com.mars.cdma.gov.service;

import java.util.Date;
import java.util.List;

import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.Ulbs;

public interface AssessmentReportService {

	public List<Object[]> dashboard();

	public List<Object[]> getAssmntByAllDistrictWise();

	public List<Object[]> getAssmntByAllDistrictWiseByDate(Date fromDate,
			Date toDate);

	/* new implementaion */
	public List getAssmntByAllWiseByDate(Date fromDate, Date toDate);

	public List<Object[]> getAssmntBySelectedDistrictWise(String districtCode);

	public List<Object[]> getAssmntByAllUlbWise();

	public List<Object[]> getAssmntBySelectedUlbWise(String ulbCode);

	public List<Object[]> getAssmntBySelectedUlbWiseCount(String ulbCode);

	public List<Object[]> Olddashboard();

	public List<Object[]> getOldAssmntByAllDistrictWise();

	public List<Object[]> getOldAssmntBySelectedDistrictWise(String dist_id);

	public List<Object[]> getOldAssmntBySelectedUlbWise(String ulb_id);

	public List<Object[]> getOldAssmntBySelectedUlbWiseCount(String ulb_id);

	public List<Object[]> getOldAssmntByAllUlbWise();

	// charan written
	public List<Object[]> getAssmntByAllDistrictWiseCMS();

	public List<Object[]> getAssmntByAllUlbWiseCMS();

	public List<Object[]> getAssmntBySelectedUlbWiseCMS(String ulbCode);

	public List<Object[]> getAssmntBySelectedUlbWiseCountCMS(String ulbCode);

	public List<Object[]> getAssmntBySelectedDistrictWiseCMS(String districtCode);

	public List<Object[]> getAssmntByAllDistrictWiseMob();

	public List<Object[]> getAssmntByAllUlbWiseMob();

	public List<Object[]> getAssmntBySelectedUlbWiseMob(String ulbCode);

	public List<Object[]> getAssmntBySelectedUlbWiseCountMob(String ulbCode);

	public List<Object[]> getAssmntBySelectedDistrictWiseMob(String districtCode);

	public List<Object[]> dashboardCMS();

	public List<Object[]> dashboardMob();

	// new implementation
	public List<DtcpApplication> getDistrictcode();

	public List<Object[]> getDtcpDetail(int id);

	public List<Object[]> getUlbReport(String ulbCode);

	public DtcpApplication getSelfAssessmentApplicatio(int id);

	public List<Object[]> getDtcpDashboard(String ulbCode);

	public List<Object[]> getDtcpDashboardList(String ulbCode);

	public List<Object[]> getDtcpDashboardUlbWise(Integer districtId);

	public List<Object[]> getDtcpDashboardUlbDistrict(Integer districtId);

	public List<String> ULBReport(String ulbCode, String type);

	public List<String> ULBDetailReport(String ulb, String type);

	public List<String> gatewayCount(String ulbCode, String type);

	public DtcpApplication getDtcpPdfReport(long uniquerequestid);

	// Transaction Report
	public List<Object> getptSelfAsmntTrancationCollection();

	public List getptSelfAsmntTransactionDetail(String string);

	public List getptSelfAsmntTransStatus(int parseInt);

	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId);

	public List<Object> getptSelfAsmntTransCollection();

	public List getptSelfAsmntTransDetail(String string);

}
