package com.mars.cdma.gov.Dao;

import java.util.Date;
import java.util.List;

import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.Ulbs;

public interface AssessmentReportDao {

	public List<Object[]> dashboard();

	public List<Object[]> oldDashboard();

	public List<Object[]> getAssmntByAllDistrictWise();

	public List getAssmntByAllDistrictWiseByDate(Date fromDate, Date toDate);

	/* new implemanetaion */
	public List getAssmntByAllWiseByDate(Date fromDate, Date toDate);

	public List getAssmntBySelectedDistrictWise(String districtCode);

	public List<Object[]> getAssmntByAllUlbWise();

	public List getAssmntBySelectedUlbWise(String ulbCode);

	public List getAssmntBySelectedUlbWiseCount(String ulbCode);

	public List<Object[]> Olddashboard();

	public List<Object[]> getOldAssmntByAllDistrictWise();

	public List<Object[]> getOldAssmntBySelectedDistrictWise(String dist_id);

	public List<Object[]> getOldAssmntBySelectedUlbWise(String ulb_id);

	public List<Object[]> getOldAssmntBySelectedUlbWiseCount(String ulb_id);

	public List<Object[]> getOldAssmntByAllUlbWise();

	// charan written
	public List<Object[]> getAssmntByAllDistrictWiseCMS();

	public List<Object[]> getAssmntByAllUlbWiseCMS();

	public List getAssmntBySelectedUlbWiseCMS(String ulbCode);

	public List getAssmntBySelectedUlbWiseCountCMS(String ulbCode);

	public List getAssmntBySelectedDistrictWiseCMS(String districtCode);

	public List<Object[]> getAssmntByAllDistrictWiseMob();

	public List<Object[]> getAssmntByAllUlbWiseMob();

	public List getAssmntBySelectedUlbWiseMob(String ulbCode);

	public List getAssmntBySelectedUlbWiseCountMob(String ulbCode);

	public List getAssmntBySelectedDistrictWiseMob(String districtCode);

	public List<Object[]> dashboardCMS();

	public List<Object[]> dashboardMob();

	// new implementation

	public List<DtcpApplication> getDistrictcode();

	public List<Object[]> getDtcpDetail(int id);

	public List<Object[]> getUlbReport(String ulbCode);

	public DtcpApplication getSelfAssessmentApplication(int id);

	public List<Object[]> getDtcpDashboard(String ulbCode);

	public List<Object[]> getDtcpDashboardList(String ulbCode);

	public List<Object[]> getDtcpDashboardUlbWise(Integer districtId);

	public List<Object[]> getDtcpDashboardUlbDistrict(Integer districtId);

	public List<String> ULBReport(String ulbCode, String type);

	public List<String> ULBDetailReport(String ulb, String type);

	public List<String> gatewayCount(String ulbCode, String type);

	public DtcpApplication getDtcpPdfReport(long uniquerequestid);

	public List<Object> getptSelfAsmntTrancationCollection();

	public List getptSelfAsmntTransactionDetail(String string);

	public List getptSelfAsmntTransStatus(int parseInt);

	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId);

	public List<Object> getptSelfAsmntTransCollection();

	public List getptSelfAsmntTransDetail(String string);

}
