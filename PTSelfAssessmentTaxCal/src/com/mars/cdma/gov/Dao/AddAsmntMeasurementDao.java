package com.mars.cdma.gov.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mars.cdma.gov.bean.AddAsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AddAssessmentMaster;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;

public interface AddAsmntMeasurementDao {
	public ArrayList<String> findExistAssessment(String assessment,
			String doorno) throws SQLException;

	public boolean save(AddAssessmentMaster addAssessmentMaster);

	public Double getTotalPtSelfAssessmentTaxForAddMeasurement(
			AddAssessmentMaster addAssessmentMaster) throws SQLException;

	public AddAssessmentMaster getrecordForAddMeasurement(long parseLong);

	public AddAsmtMeasurementMaster getSingleRecordForAddMeasurement(
			long measureId);

	public boolean save(AddAsmtMeasurementMaster addAsmtMeasurementMaster);

	public AddAssessmentMaster getBean(long uniqueNumber);

	public String userLogin(AddAssessmentMaster addAssessmentMaster,
			String vC_USRNAME, String vC_USRPWD) throws SQLException;

	public List<AddAssessmentMaster> getUniquetData(long uniqueNumber);

	public AddAssessmentMaster getptselfAssestsinglerecord(long uniqReqNumber);

	public AddMeasurementTransactionHistory insertCashtransaction(
			long uniqReqNumber, HttpServletRequest request,
			HttpServletResponse response, String loginId);

	public String checkAssessmentNo(String assmntNo);

	public List<AddMeasurementTransactionHistory> getsinglerecordForTransaction(
			long payment_transaction_receipt_id);

	public AddMeasurementTransactionHistory inserttransactionForDigital(
			long uniqReqNumber, HttpServletRequest request,
			HttpServletResponse response);

	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory addMeasurementaymentTransaction,
			String responseUrl);

	public AddMeasurementTransactionHistory getRecordForDigitalTransaction(
			long payment_transaction_receipt_id);

	public void updaterecord(AddAssessmentMaster ptAssessmentMaster);
}
