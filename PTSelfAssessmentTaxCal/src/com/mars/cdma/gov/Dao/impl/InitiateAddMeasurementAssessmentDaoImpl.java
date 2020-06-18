/**
 * 
 */
package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.InitiateAddMeasurementAssessmentReportDao;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.service.InitiatePaymentTransactionService;

/**
 * @author RAHUL
 *
 */
@Repository("initiateAssessmentReportDao")
public class InitiateAddMeasurementAssessmentDaoImpl implements
		InitiateAddMeasurementAssessmentReportDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List getptSelfAsmntTransStatus(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		String sql = " Select ulb.ULB_NAME,"
				+ " ptasmnt.OWNER_NAME,"
				+ " ptasmnt.OWNER_SURNAME,"
				+ " tran.TRANSACTION_BANK_IN,"
				+ " tran.TRANSACTION_ID,"
				+ "	tran.gateway_name,"
				+ " tran.total_AMOUNT,"
				+ " tran.receipt_owner_name,"
				+ " (CASE WHEN tran.transaction_response_code = '0300' THEN 'Success' ELSE 'NA' END),"
				+ " (CASE WHEN ptasmnt.paymentflag = 'Y' THEN 'Success' ELSE 'Not Completed' END),"
				+ " tran.payment_transaction_receipt_id ,"
				+ " tran.uniqueRequestNumber,"
				+ " tran.transdate,"
				+ " IFNULL(tran.transaction_error_description,'')"
				+ " FROM addassessmentmaster ptasmnt INNER JOIN addmeasurement_transaction_history tran ON"
				+ " ptasmnt.uniqueRequestNumber=tran.uniqueRequestNumber INNER JOIN ulbs ulb"
				+ " ON ptasmnt.ulbcode =ulb.ULB_CODE Where  ptasmnt.uniqueRequestNumber=:uniqueRequestId"
				+ " ANd  ulb.c_delflag='N'  And tran.c_delflag='N' ";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("uniqueRequestId", (uniqueRequestId));
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List<Object> getptSelfAsmntTransCollection() {
		String sql = "SELECT Sum(t.`total_amount`) FROM addmeasurement_transaction_history t Inner Join addassessmentmaster ptasmnt ON t.uniqueRequestNumber=ptasmnt.uniqueRequestNumber"
				+ " Where t.transaction_flag='S' and t.c_delflag='N' and ptasmnt.delflag='N' Order By  t.uniqueRequestNumber;";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);

		List transactionData = query.list();

		return transactionData;
	}

	@Override
	public List getptSelfAsmntTransDetail(String tranYear) {
		String sql = " Select ulb.ULB_NAME,"
				+ " ptasmnt.OWNER_NAME,"
				+ " ptasmnt.OWNER_SURNAME,"
				+ " tran.transaction_bank_in,"
				+ " tran.transaction_id,"
				+ " tran.gateway_name,"
				+ " tran.total_amount,"
				+ " tran.receipt_owner_name,"
				+ " (CASE WHEN tran.transaction_response_code = '0300' THEN 'Success' ELSE 'NA' END),"
				+ " (CASE WHEN ptasmnt.paymentflag = 'Y' THEN 'Success' ELSE 'Not Completed' END),"
				+ " tran.payment_transaction_receipt_id,"
				+ " tran.uniqueRequestNumber,"
				+ " tran.transdate,"
				+ " IFNULL(tran.transaction_error_description,'')transactionDesc"
				+ " FROM addassessmentmaster ptasmnt INNER JOIN addmeasurement_transaction_history tran ON"
				+ " ptasmnt.uniqueRequestNumber=tran.uniqueRequestNumber INNER JOIN ulbs ulb"
				+ " ON ptasmnt.ulbcode =ulb.ULB_CODE"
				+ " Where ulb.c_delflag='N' And ptasmnt.delflag='N' And tran.c_delflag='N'  order by  tran.payment_transaction_receipt_id DESC";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);// Where
		List transactionData = query.list();

		return transactionData;
	}

}
