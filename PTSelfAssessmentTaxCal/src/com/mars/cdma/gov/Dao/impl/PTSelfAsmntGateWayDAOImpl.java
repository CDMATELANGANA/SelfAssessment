package com.mars.cdma.gov.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PTSelfAsmntGateWayDAO;

@Repository("PTSelfAsmntGateWayDAO")
public class PTSelfAsmntGateWayDAOImpl implements PTSelfAsmntGateWayDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<String> totalAmount(String ulbCode) {

		String sql = "";
		if (ulbCode != null) {
			sql = "select coalesce(round(sum(total_amount)/100000,2),0) as amt  from ptassessmenttax.transaction_history th inner join ptassessmenttax.ptassessment_master pt on "
					+ "	th.uniqueRequestNumber=pt.uniquerequestNumber inner join ulbs on ulbs.ULB_CODE=pt.ulbcode "
					+ "	where paid_update_flag='S' and  ulb_code="
					+ ulbCode
					+ " and th.c_delflag='N' ";
		} else {
			sql = "  select coalesce(round(sum(total_amount)/100000,2),0),count(payment_transaction_receipt_id)  as count  from ptassessmenttax.transaction_history  where paid_update_flag='S' and transaction_response_code=0300 and c_delflag='N' ";
		}
		Session session = sessionFactory.openSession();

		// Fetch Valid User
		Query userQuery = session.createSQLQuery(sql);
		return userQuery.list();

	}

	@Override
	public List<String> gatewayCount(String ulbCode) {
		String sql = "";
		if (ulbCode != null) {

			sql = "select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history th"
					+ " inner join ptassessmenttax.ptassessment_master pt on"
					+ " pt.uniqueRequestNumber=th.uniqueRequestNumber"
					+ " where paid_update_flag='S' and pt.ulbcode="
					+ ulbCode
					+ " and th.c_delflag='N' and gateway_name='BILL DESK' group by gateway_name";
		} else {
			sql = "select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history where paid_update_flag='S' and c_delflag='N' and gateway_name='BILL DESK' group by gateway_name ";
		}
		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();
	}

	@Override
	public List<String> ulbDetailReport(String ulb) {

		String sql = "";

		sql = "select th.uniqueRequestNumber,th.payment_transaction_receipt_id,total_amount,th.transdate,receipt_owner_name,ULB_CODE,transaction_id"
				+ " from ptassessmenttax.transaction_history th"
				+ " inner join ptassessmenttax.ptassessment_master pt on th.uniqueRequestNumber=pt.uniqueRequestNumber left"
				+ " join ulbs u on ULB_CODE=pt.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID"
				+ " where paid_update_flag='S' and  ULB_CODE="
				+ ulb
				+ " and th.c_delflag='N'";

		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();

	}

	@Override
	public List<String> getDataByInterval(String fromDate, String toDate,
			String ulb) {
		String sql = "";

		sql = "select d.DISTRICT_NAME,count(pt.uniquerequestNumber) as nooftrans,round(coalesce(sum(tr.total_amount)/100000,0),2),ULB_CODE"
				+ " ,u.ulb_name from ptassessmenttax.ptassessment_master pt"
				+ " right  join ulbs u on ULB_CODE=pt.ulbcode"
				+ " left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=pt.uniquerequestNumber"
				+ "	 left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID"
				+ "  where  DATE(transdate)>='"
				+ fromDate
				+ "' "
				+ " and DATE(transdate)<='"
				+ toDate
				+ "' "
				+ " and  u.c_delflag='N' and tr.c_delflag='N' and gateway_name='BILL DESK' and transaction_response_code='0300' and paid_update_flag='S'"
				+ " group by pt.ulbcode order by u.ulb_name";

		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		List<String> list = userQuery.list();

		return list;

	}

	@Override
	public List<String> gatewayCountByDate(String fromDate, String toDate) {
		String sql = "";
		sql = "select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history where DATE(transdate)>='"
				+ fromDate
				+ "' and DATE(transdate)<='"
				+ toDate
				+ "'and paid_update_flag='S'  and gateway_name='BILL DESK' and c_delflag='N' group by gateway_name ";

		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> transDetailReportByDate(String fromDate, String toDate) {
		String sql = "";

		sql = "select pt.uniqueRequestNumber,th.payment_transaction_receipt_id,th.total_amount,th.transdate,th.receipt_owner_name,ULB_CODE,th.transaction_id from ptassessmenttax.transaction_history th"
				+ " inner join ptassessmenttax.ptassessment_master pt on th.uniqueRequestNumber=pt.uniqueRequestNumber  left"
				+ " join ptassessmenttax.ulbs u on ULB_CODE=pt.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID"
				+ "  where paid_update_flag='S' and th.transdate between :fromDate And :toDate and th.c_delflag='N' and gateway_name='BILL DESK'";
		;

		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		userQuery.setParameter("fromDate", fromDate);
		userQuery.setParameter("toDate", toDate);
		// userQuery.setParameter("ulb", ulb);

		return userQuery.list();

	}

	@Override
	public List<String> getDetailTransactionReport() {
		String sql = "";

		sql = "select th.uniqueRequestNumber,th.transdate,th.payment_transaction_receipt_id,th.total_amount,th.receipt_owner_name,th.receipt_owner_door_no,th.transaction_id,th.ulbname from ptassessmenttax.transaction_history th"
				+ " inner join ptassessmenttax.ptassessment_master pt on th.uniqueRequestNumber=pt.uniqueRequestNumber  left"
				+ " join ptassessmenttax.ulbs u on ULB_CODE=pt.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID"
				+ "  where th.paid_update_flag='S'and gateway_name='BILL DESK' and th.c_delflag='N' order by th.ulbname";

		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();
	}

	@Override
	public List<String> ulbReport(String ulbCode) {
		String sql = "";

		if (ulbCode != null) {

			sql = "select d.DISTRICT_NAME,u.ulb_name,count(m.uniquerequestNumber) as noOfTrans, round(coalesce(sum(tr.total_amount)/100000,0),2) as totalamount ,ULB_CODE ,u.ulb_name from ptassessmenttax.ptassessment_master m"
					+ "  right  join ulbs u on ULB_CODE=m.ulbcode"
					+ " left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=m.uniquerequestNumber"
					+ "  left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID"
					+ "  where u.c_delflag='N' and tr.c_delflag='N' and ULB_CODE="
					+ ulbCode
					+ "  and paid_update_flag='S' and gateway_name='BILL DESK'"
					+ "	  group by m.ulbcode order by u.ulb_name";
		} else {
			sql = "select d.DISTRICT_NAME,count(pt.uniquerequestNumber),round(coalesce(sum(tr.total_amount)/100000,0),2),ULB_CODE,u.ulb_name from ptassessmenttax.ptassessment_master pt"
					+ " right  join ulbs u on ULB_CODE=pt.ulbcode"
					+ " left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=pt.uniquerequestNumber"
					+ " left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID"
					+ " where u.c_delflag='N' and tr.c_delflag='N' and  paid_update_flag='S' and gateway_name='BILL DESK'"
					+ "  group by pt.ulbcode order by u.ulb_name";
		}

		Session session = sessionFactory.openSession();

		// Fetch Valid User
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();

	}

}
