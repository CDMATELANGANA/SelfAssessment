package com.mars.cdma.gov.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.AssessmentDAO;
import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.Assessmentservice;

@Service("assessmentservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AssessmentServiceimpl implements Assessmentservice {

	@Autowired
	public AssessmentDAO assessmentDAO;

	
	@Override
	public boolean save(AssessmentMaster assessmentMaster) {
		// TODO Auto-generated method stub
		return assessmentDAO.save(assessmentMaster);
	}

	@Override
	public AssessmentMaster getsinglerecord(long uniquerequestid) {
		// TODO Auto-generated method stub
		return assessmentDAO.getrsinglerecord(uniquerequestid);
	}

	@Override
	public void save(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		assessmentDAO.save(taxcalservicebean);
	}

	@Override
	public Ulbs getulbByCode(int ulbCode) {
		// TODO Auto-generated method stub
		return assessmentDAO.getulbByCode(ulbCode);
	}

	@Override
	public boolean saveDet(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		return assessmentDAO.saveDet(taxcalservicebean);
	}

	@Override
	public boolean saveDtcpAppl(DtcpApplication dtcpApplication) {
		// TODO Auto-generated method stub
		return assessmentDAO.saveDtcpAppl(dtcpApplication);
	}

	@Override
	public boolean updateDtcpFlag( int id,long uniquerequestid) {
		// TODO Auto-generated method stub
		return assessmentDAO.updateDtcpFlag(id,uniquerequestid);
	}

	@Override
	public String getDistCodeByUlbName(Integer ULB_CODE) {
		// TODO Auto-generated method stub
		return assessmentDAO.getDistCodeByUlbName(ULB_CODE);
	}

	@Override
	public List<Integer> getTotalVisitor() {
		// TODO Auto-generated method stub
		return assessmentDAO.getTotalVisitor();
	}

	@Override
	public List<String> getPtCalculatorAllDistrictWiseDashboard() {
		// TODO Auto-generated method stub
		return assessmentDAO.getPtCalculatorAllDistrictWiseDashboard();
	}

	@Override
	public List<String> getPtCalculatorAllUlbWiseDashboard() {
		// TODO Auto-generated method stub
		return assessmentDAO.getPtCalculatorAllUlbWiseDashboard();
	}

	@Override
	public List<String> getPtCalculatorUlbWiseDashboard(String districtcode) {
		// TODO Auto-generated method stub
		return assessmentDAO.getPtCalculatorUlbWiseDashboard(districtcode);
	}

	@Override
	public PaymentTransaction inserttransaction(long uniqReqNumber,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws IOException {
		
		return assessmentDAO.inserttransaction(uniqReqNumber,paramHttpServletRequest,paramHttpServletResponse);
	}

	@Override
	public void updaterecord(AssessmentMaster ptAssessmentMaster) {
		
		assessmentDAO.updaterecord(ptAssessmentMaster);
	}
	
	@Override
	public void updateAssessment(AssessmentMaster ptAssessmentMaster) {
		
		assessmentDAO.updaterecord(ptAssessmentMaster);
	}
	@Override
	public Double getTotalPtSelfAssessmentTax(AssessmentMaster asmtMasterBean) throws SQLException{
		
		return assessmentDAO.getTotalPtSelfAssessmentTax(asmtMasterBean);
	}
	@Override
	public Double getTotalPtSelfAssessmentTaxFor_75SqrYards(
			AssessmentMaster assessmentMaster) throws SQLException{
		return assessmentDAO.getTotalPtSelfAssessmentTaxFor_75SqrYards(assessmentMaster);
	}
	
	@Override
	public boolean savePTAsmntFileData(AssessmentMaster ptAssessmentMaster) {
		// TODO Auto-generated method stub
		return assessmentDAO.savePTAsmntFileData(ptAssessmentMaster);
	}

	@Override
	public void updatPaymentAmount(double amount,String rquNumber) {
		assessmentDAO.updatPaymentAmount(amount,rquNumber);
		
	}

	@Override
	public String checkAssessmentNo(String assessmentNo) {
		// TODO Auto-generated method stub
		return assessmentDAO.checkAssessmentNo(assessmentNo);
	}

	@Override
	public boolean save(AsmtMeasurementMaster asmtMeasurementMaster) {
		// TODO Auto-generated method stub
		return assessmentDAO.save(asmtMeasurementMaster);
	}

	@Override
	public AsmtMeasurementMaster getSingleRecordForMeasurement(long uniquerequestid) {
		// TODO Auto-generated method stub
		return assessmentDAO.getSingleRecordForMeasurement(uniquerequestid);
	}

	

	

}
