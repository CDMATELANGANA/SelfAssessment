package com.mars.cdma.gov.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.ibm.db2.jcc.am.SqlException;
import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;


@Transactional(readOnly = true)
public interface Assessmentservice {
	public boolean save(AssessmentMaster assessmentMaster);
	public boolean save(AsmtMeasurementMaster asmtMeasurementMaster);
	public AsmtMeasurementMaster getSingleRecordForMeasurement(long uniquerequestid);
	public AssessmentMaster getsinglerecord(long uniquerequestid);
	public void save(Taxcalservicebean taxcalservicebean);
	public Ulbs getulbByCode(int ulbCode);
	public boolean saveDet(Taxcalservicebean taxcalservicebean);
	public boolean saveDtcpAppl(DtcpApplication dtcpApplication);
	public boolean updateDtcpFlag(int id,long uniquerequestid);
	public String getDistCodeByUlbName(Integer ULB_CODE);
	public List<Integer> getTotalVisitor();
	public List<String> getPtCalculatorAllDistrictWiseDashboard();
	public List<String> getPtCalculatorAllUlbWiseDashboard();
	public List<String> getPtCalculatorUlbWiseDashboard(String districtcode);
	public abstract PaymentTransaction inserttransaction(long uniqReqNumber,HttpServletRequest paramHttpServletRequest,HttpServletResponse paramHttpServletResponse) throws IOException;
	public abstract void updaterecord(AssessmentMaster ptAssessmentMaster);
	public abstract void updateAssessment(AssessmentMaster ptAssessmentMaster);
	public boolean savePTAsmntFileData(AssessmentMaster ptAssessmentMaster);
    public Double getTotalPtSelfAssessmentTax(AssessmentMaster assessmentbean) throws SQLException;
	public void updatPaymentAmount(double amount, String rquNumber);
	public String checkAssessmentNo(String assessmentNo);
	public Double getTotalPtSelfAssessmentTaxFor_75SqrYards(
			AssessmentMaster assessmentMaster)throws SQLException;
	
	
	
	
	
	
	

}
