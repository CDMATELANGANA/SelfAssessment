package com.mars.cdma.gov.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.AssessmentReportDao;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.service.AssessmentReportService;

@Service("assessmentReportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AssessmentReportServiceImpl implements AssessmentReportService {

	@Autowired
	AssessmentReportDao assessmentReportDao;

	@Override
	public List<Object[]> dashboard() {
		return assessmentReportDao.dashboard();
	}
	@Override
	public List<Object[]> Olddashboard() {
		return assessmentReportDao.Olddashboard();
	}

	@Override
	public List<Object[]> getOldAssmntByAllDistrictWise() {
		return assessmentReportDao.getOldAssmntByAllDistrictWise();
	}
	
	@Override
	public List getOldAssmntBySelectedDistrictWise(String districtCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao
				.getOldAssmntBySelectedDistrictWise(districtCode);
	}
	
	@Override
	public List getOldAssmntBySelectedUlbWise(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getOldAssmntBySelectedUlbWise(ulbCode);
	}

	@Override
	public List getOldAssmntBySelectedUlbWiseCount(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getOldAssmntBySelectedUlbWiseCount(ulbCode);
	}
	
@Override
	public List<Object[]> getOldAssmntByAllUlbWise() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getOldAssmntByAllUlbWise();
	}

	@Override
	public List<Object[]> getAssmntByAllDistrictWise() {
		return assessmentReportDao.getAssmntByAllDistrictWise();
	}

	@Override
	public List getAssmntByAllDistrictWiseByDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllDistrictWiseByDate(fromDate,
				toDate);
	}

	@Override
	public List getAssmntBySelectedDistrictWise(String districtCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao
				.getAssmntBySelectedDistrictWise(districtCode);
	}

	@Override
	public List<Object[]> getAssmntByAllUlbWise() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllUlbWise();
	}

	@Override
	public List getAssmntBySelectedUlbWise(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWise(ulbCode);
	}

	@Override
	public List getAssmntBySelectedUlbWiseCount(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWiseCount(ulbCode);
	}

	@Override
	public List<Object[]> getAssmntByAllDistrictWiseCMS() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllDistrictWiseCMS();
	}

	@Override
	public List<Object[]> getAssmntByAllUlbWiseCMS() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllUlbWiseCMS();
	}

	@Override
	public List getAssmntBySelectedUlbWiseCMS(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWiseCMS(ulbCode);
	}

	@Override
	public List getAssmntBySelectedUlbWiseCountCMS(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWiseCountCMS(ulbCode);
	}

	@Override
	public List getAssmntBySelectedDistrictWiseCMS(String districtCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedDistrictWiseCMS(districtCode);
	}

	@Override
	public List<Object[]> getAssmntByAllDistrictWiseMob() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllDistrictWiseMob();
		
	}

	@Override
	public List<Object[]> getAssmntByAllUlbWiseMob() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllUlbWiseMob();
	}

	@Override
	public List getAssmntBySelectedUlbWiseMob(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWiseMob(ulbCode);
		}

	@Override
	public List getAssmntBySelectedUlbWiseCountMob(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedUlbWiseCountMob(ulbCode);
	}

	@Override
	public List getAssmntBySelectedDistrictWiseMob(String districtCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntBySelectedDistrictWiseMob(districtCode);
	}

	@Override
	public List<Object[]> dashboardCMS() {
		// TODO Auto-generated method stub
		return assessmentReportDao.dashboardCMS();
	}

	@Override
	public List<Object[]> dashboardMob() {
		// TODO Auto-generated method stub
		return assessmentReportDao.dashboardMob();
	}

	
	@Override
	public List<DtcpApplication> getDistrictcode() {
		// TODO Auto-generated method stub
		return assessmentReportDao.getDistrictcode();
	}

	@Override
	public List<Object[]> getDtcpDetail(int id) {
		// TODO Auto-generated method stub
		return  assessmentReportDao.getDtcpDetail(id);
	}

	@Override
	public  DtcpApplication getSelfAssessmentApplicatio(int id) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getSelfAssessmentApplication(id);
	}

	@Override
	public List<Object[]> getDtcpDashboard(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getDtcpDashboard(ulbCode);
	}

	@Override
	public List<Object[]> getDtcpDashboardList(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getDtcpDashboardList(ulbCode);
	}

	@Override
	public DtcpApplication getDtcpPdfReport(long uniquerequestid) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getDtcpPdfReport(uniquerequestid);
	}

	@Override
	public List<Object[]> getDtcpDashboardUlbWise(Integer districtId) {
		
		return assessmentReportDao.getDtcpDashboardUlbWise(districtId);
	}

	@Override
	public List<Object[]> getDtcpDashboardUlbDistrict(Integer districtId) {
		return assessmentReportDao.getDtcpDashboardUlbDistrict(districtId);
	}

	@Override
	public List<String> ULBReport(String ulbCode, String type) {
		// TODO Auto-generated method stub
		return assessmentReportDao.ULBReport(ulbCode, type);
	}

	@Override
	public List<String> ULBDetailReport(String ulb, String type) {
		// TODO Auto-generated method stub
		return assessmentReportDao.ULBDetailReport(ulb, type);
	}

	@Override
	public List<String> gatewayCount(String ulbCode, String type) {
		// TODO Auto-generated method stub
		return assessmentReportDao.gatewayCount(ulbCode, type);
	}

	@Override
	public List<Object[]> getUlbReport(String ulbCode) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getUlbReport(ulbCode);
	}

	@Override
	public List getAssmntByAllWiseByDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return assessmentReportDao.getAssmntByAllWiseByDate(fromDate, toDate);
	}
	
	@Override
	public List<Object> getptSelfAsmntTrancationCollection() {
		return assessmentReportDao.getptSelfAsmntTrancationCollection();
	}

	@Override
	public List getptSelfAsmntTransactionDetail(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getptSelfAsmntTransStatus(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		
		return assessmentReportDao.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
	}

	@Override
	public List<Object> getptSelfAsmntTransCollection() {
		
		return assessmentReportDao.getptSelfAsmntTransCollection();
	}

	@Override
	public List getptSelfAsmntTransDetail(String tranYear) {
		
		return assessmentReportDao.getptSelfAsmntTransDetail(tranYear);
	}
	
	

}










