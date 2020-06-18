package com.mars.cdma.gov.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.AddAsmntMeasurementDao;
import com.mars.cdma.gov.Dao.AssessmentInitiateDao;
import com.mars.cdma.gov.bean.AddAsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AddAssessmentMaster;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.AddAsmntMeasurementService;
@Service("AddAsmntMeasurementService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AddAsmntMeasurementServiceImpl implements
		AddAsmntMeasurementService {

	@Autowired
	private AddAsmntMeasurementDao addAsmntMeasurementDao;
	@Override
	public ArrayList<String> findExistAssessment(String assessment,
			String doorno) throws SQLException {
		
		return addAsmntMeasurementDao.findExistAssessment(assessment, doorno);
	}

	@Override
	public boolean save(AddAssessmentMaster addAssessmentMaster) {
		// TODO Auto-generated method stub
		return addAsmntMeasurementDao.save(addAssessmentMaster);
	}

	@Override
	public Double getTotalPtSelfAssessmentTaxForAddMeasurement(
			AddAssessmentMaster addAssessmentMaster) throws SQLException {
		// TODO Auto-generated method stub
		return addAsmntMeasurementDao.getTotalPtSelfAssessmentTaxForAddMeasurement(addAssessmentMaster);
	}

	@Override
	public AddAssessmentMaster getrecordForAddMeasurement(long measurementId) {
		// TODO Auto-generated method stub
		return addAsmntMeasurementDao.getrecordForAddMeasurement(measurementId);
	}

	@Override
	public AddAsmtMeasurementMaster getSingleRecordForAddMeasurement(
			long measureId) {
		return addAsmntMeasurementDao.getSingleRecordForAddMeasurement(measureId);
	}

	@Override
	public boolean save(AddAsmtMeasurementMaster addAsmtMeasurementMaster) {
		return addAsmntMeasurementDao.save(addAsmtMeasurementMaster);
	}

	@Override
	public AddAssessmentMaster getBean(long uniqueNumber) {
		
		return addAsmntMeasurementDao.getBean(uniqueNumber);
	}

	@Override
	public String userLogin(AddAssessmentMaster addAssessmentMaster,
			String vC_USRNAME, String vC_USRPWD) throws SQLException {
		
		return addAsmntMeasurementDao.userLogin(addAssessmentMaster, vC_USRNAME, vC_USRPWD);
	}

	@Override
	public List<AddAssessmentMaster> getUniquetData(long uniqueNumber) {
		
		return addAsmntMeasurementDao.getUniquetData(uniqueNumber);
	}

	@Override
	public AddAssessmentMaster getptselfAssestsinglerecord(long uniqReqNumber) {
		return addAsmntMeasurementDao.getptselfAssestsinglerecord(uniqReqNumber);
	}

	@Override
	public AddMeasurementTransactionHistory insertCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId) {
		return addAsmntMeasurementDao.insertCashtransaction(uniqReqNumber, request, response, loginId);
	}

	@Override
	public String checkAssessmentNo(String assmntNo) {
		// TODO Auto-generated method stub
		return addAsmntMeasurementDao.checkAssessmentNo(assmntNo);
	}

	@Override
	public List<AddMeasurementTransactionHistory> getsinglerecordForTransaction(
			long payment_transaction_receipt_id) {
		
		return addAsmntMeasurementDao.getsinglerecordForTransaction(payment_transaction_receipt_id);
	}

	@Override
	public AddMeasurementTransactionHistory inserttransactionForDigital(
			long uniqReqNumber, HttpServletRequest request,
			HttpServletResponse response) {
		
		return addAsmntMeasurementDao.inserttransactionForDigital(uniqReqNumber, request, response);
	}

	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory addMeasurementaymentTransaction,
			String responseUrl) {
		return addAsmntMeasurementDao.getdetailsfromresponseurl(addMeasurementaymentTransaction, responseUrl);
	}

	@Override
	public AddMeasurementTransactionHistory getRecordForDigitalTransaction(
			long payment_transaction_receipt_id) {
		return addAsmntMeasurementDao.getRecordForDigitalTransaction(payment_transaction_receipt_id);
	}

	@Override
	public void updaterecord(AddAssessmentMaster ptAssessmentMaster) {
		// TODO Auto-generated method stub
		addAsmntMeasurementDao.updaterecord(ptAssessmentMaster);
	}

}
