package com.mars.cdma.gov.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;

public interface AssessmentDAO {

	public boolean save(AssessmentMaster assessmentMaster);

	public AssessmentMaster getrsinglerecord(long uniquerequestid);

	// charan written
	public boolean saveDet(Taxcalservicebean taxcalservicebean);

	public void save(Taxcalservicebean taxcalservicebean);

	// public long getDistIdByUlbId(long ulbId);
	public Ulbs getulbByCode(int ulbCode);

	public boolean saveDtcpAppl(DtcpApplication dtcpApplication);

	// new implementation
	public boolean updateDtcpFlag(int id, long uniquerequestid);

	public String getDistCodeByUlbName(Integer ULB_CODE);

	public List<Integer> getTotalVisitor();

	public List<String> getPtCalculatorAllDistrictWiseDashboard();

	public List<String> getPtCalculatorAllUlbWiseDashboard();

	public List<String> getPtCalculatorUlbWiseDashboard(String districtcode);

	public PaymentTransaction inserttransaction(long uniqReqNumber,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse);

	public void updaterecord(AssessmentMaster ptAssessmentMaster);

	public boolean savePTAsmntFileData(AssessmentMaster ptAssessmentMaster);

	public Double getTotalPtSelfAssessmentTax(AssessmentMaster asmtMasterBean)
			throws SQLException;

	public void updatPaymentAmount(double amount, String rquNumber);

	public String checkAssessmentNo(String assessmentNo);

	public boolean save(AsmtMeasurementMaster asmtMeasurementMaster);

	public AsmtMeasurementMaster getSingleRecordForMeasurement(long uniquerequestid);

	public Double getTotalPtSelfAssessmentTaxFor_75SqrYards(
			AssessmentMaster assessmentMaster) throws SQLException;
}
