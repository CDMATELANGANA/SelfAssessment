/**
 * 
 */
package com.mars.cdma.gov.Dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PtSelfAssmntReportsDao;

/**
 * @author RAHUL
 *
 */

@Repository("ptSelfAssmntReportDao")
public class PtSelfAssmntReportsDaoImpl implements PtSelfAssmntReportsDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List getSelfAsmntTransDetail(String tranYear) {
		return null;
	}

	@Override
	public List getSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		
		// Get Transaction Status

				String sql = "Select ulb.ULB_NAME,"
						  +"  pt.occupantname,"
						  +"  pt.occupantsurname,"
						  +" tran.transaction_bank_in,"
						  +" tran.transaction_id," 
						  +" tran.gateway_name,"
						  +" tran.total_amount,"
						  +" tran.receipt_owner_name," 
						  +" (CASE WHEN tran.transaction_response_code = '0300' THEN 'Success' ELSE 'NA' END)," 
						  +" (CASE WHEN pt.paymentflag = 'Y' THEN 'Success' ELSE 'Not Completed' END),"
						  +" tran.payment_transaction_receipt_id,"
						  +" tran.uniqueRequestNumber,"
						  +" tran.transdate,"
						  +" IFNULL(tran.transaction_error_description,'')" 
						  +" FROM ptassessment_master pt INNER JOIN transaction_history tran ON"
						  +" pt.uniqueRequestNumber=tran.uniqueRequestNumber INNER JOIN ulbs ulb"
						  +" ON pt.ulbcode =ulb.ULB_CODE Where  pt.uniqueRequestNumber=:uniqueRequestId"
						  +" ANd  ulb.c_delflag='N'  And tran.c_delflag='N'";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				System.out.println(query);
				query.setParameter("uniqueRequestId", (uniqueRequestId));
				List transactionData = query.list();
				return transactionData;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getSelfAsmntTransCollection() {
		String sql = "SELECT Sum(t.`total_amount`) FROM transaction_history t Inner Join ptassessment_master pt ON t.uniqueRequestNumber=pt.uniqueRequestNumber"
			+ " Where t.transaction_flag='S' and t.c_delflag='N' and pt.delflag='N' Order By  t.uniqueRequestNumber";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		System.out.println(query);

		List transactionData = query.list();

		return transactionData;
		
	
	}

	@Override
	public List<Object> getSelfAsmntTransStatus(int transReceiptNo) {
		return null;
	}

}
