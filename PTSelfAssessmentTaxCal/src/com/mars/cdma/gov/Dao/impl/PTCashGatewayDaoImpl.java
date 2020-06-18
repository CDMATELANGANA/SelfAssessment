package com.mars.cdma.gov.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PTCashGatewayDao;


@Repository("ptCashReportDao")
public class PTCashGatewayDaoImpl implements PTCashGatewayDao {
	

@Autowired
private SessionFactory sessionFactory;

	@Override
	public List<String> totalAmount(String ulbCode) {
		System.out.println(">>>----");
		String sql="";
		if(ulbCode!=null){
	sql = "select coalesce(round(sum(total_amount)/100000,2),0) as amt  from ptassessmenttax.transaction_history th inner join ptassessmenttax.ptassessment_master m on"
			+ " th.uniqueRequestNumber=m.uniquerequestNumber inner join ulbs on ulbs.ULB_CODE=m.ulbcode"
		+ " where paid_update_flag='S' and  ulb_code="+ulbCode+" and th.c_delflag='N' and th.gateway_name='CashCounter'and  th.transactionmode='Cash'and th.transaction_type='05' ";


		}	
	else
	{
		sql = "select coalesce(round(sum(total_amount)/100000,2),0),count(payment_transaction_receipt_id)  as count  from ptassessmenttax.transaction_history  where paid_update_flag='S' and transaction_response_code=0300 and c_delflag='N' and gateway_name='CashCounter' and transactionmode='Cash'and transaction_type='05'; ";
	}
		Session session = sessionFactory.openSession();

		// Fetch Valid User
		Query userQuery = session.createSQLQuery(sql);
		
		System.out.println(userQuery.list()+">>>>>>>>>>>>>");	
		
		return userQuery.list();
	}

	@Override
	public List<String> cashULBReport(String ulbCode) {
String sql="";
		
		if(ulbCode!=null){
			
			sql = "select d.DISTRICT_NAME,count(m.uniquerequestNumber), round(coalesce(sum(tr.total_amount)/100000,0),2) ,ULB_CODE ,u.ulb_name from ptassessmenttax.ptassessment_master m "+ 
					 " right  join ulbs u on ULB_CODE=m.ulbcode "+
					 " left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=m.uniquerequestNumber "+ 
					 " left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID "+
					 " where u.c_delflag='N' and tr.c_delflag='N' and ULB_CODE="+ulbCode+"  and  paid_update_flag='S' and gateway_name='CashCounter' and transaction_type=05 "+
					 " group by m.ulbcode  ";
		}
		else
		{
			 sql ="select d.DISTRICT_NAME,count(m.uniquerequestNumber),round(coalesce(sum(tr.total_amount)/100000,0),2),ULB_CODE,u.ulb_name from ptassessmenttax.ptassessment_master m "+ 
			 " right  join ulbs u on ULB_CODE=m.ulbcode "+
			 " left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=m.uniquerequestNumber "+ 
			 " left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID "+
			 " where u.c_delflag='N' and tr.c_delflag='N' and  paid_update_flag='S' and gateway_name='CashCounter' and transaction_type=05 "+
			 " group by m.ulbcode  ";
		}
		
	Session session = sessionFactory.openSession();
	Query userQuery = session.createSQLQuery(sql);
	System.out.println(sql+">>>>>>>>>>>>>");	
	return userQuery.list();
	}

	@Override
	public List<String> cashGatewayCount(String ulbCode) {
		String sql="";
		if(ulbCode!=null){
			
			
				sql="select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history th inner join ptassessmenttax.ptassessment_master m on "+ 
                " m.uniqueRequestNumber=th.uniqueRequestNumber  "+
                " where paid_update_flag='S' and m.ulbcode="+ulbCode+" and th.c_delflag='N' and gateway_name='CashCounter' and transaction_type=05 group by gateway_name";
				}
		else
		{
			sql="select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history where paid_update_flag='S' and c_delflag='N' and gateway_name='CashCounter' and transaction_type=05 group by gateway_name ";
		}
		Session session = sessionFactory.openSession();
        Query userQuery = session.createSQLQuery(sql);
		return userQuery.list();
	}

	@Override
	public List<String> cashULBDetailReport(String ulb) {
String sql="";
		
		
		sql="select th.uniqueRequestNumber,m.AssessmentNo,th.payment_transaction_receipt_id,total_amount,transdate,m.pDoorNo,th.receipt_owner_name,ULB_CODE,transaction_id,th.loginId from ptassessmenttax.transaction_history th "+  
				" inner join ptassessmenttax.ptassessment_master m on th.uniqueRequestNumber=m.uniqueRequestNumber  left "+
" join ulbs u on ULB_CODE=m.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID  where "+
" paid_update_flag='S' and  ULB_CODE="+ulb+"  and th.c_delflag='N' and gateway_name='CashCounter' and transaction_type=05 and m.AssessmentNo IS NOT NULL order by transdate  ";
		
	
		Session session = sessionFactory.openSession();
        Query userQuery = session.createSQLQuery(sql);
		
		System.out.println(sql+">>>>>>>>>>>>>");	
		return userQuery.list();
	}

	@Override
	public List<String> getCashDetailTransactionReport() {
		String sql="";
		sql="select m.AssessmentNo,th.payment_transaction_receipt_id,th.total_amount,th.transdate,th.receipt_owner_name,th.transaction_id,th.ulbname,th.loginId from ptassessmenttax.transaction_history th "+  
				" inner join ptassessmenttax.ptassessment_master m on th.uniqueRequestNumber=m.uniqueRequestNumber  left "+
                " join ptassessmenttax.ulbs u on ULB_CODE=m.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID  where "+
                " paid_update_flag='S' and th.c_delflag='N' and th.gateway_name='CashCounter' and transaction_type=05 and m.AssessmentNo IS NOT NULL order by th.ulbname";
	
		Session session = sessionFactory.openSession();
        Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();

	}

	@Override
	public List<String> getCashDataByInterval(String fromDate, String toDate,
			String ulb) {
String sql="";
		
		sql="select d.DISTRICT_NAME,count(m.uniquerequestNumber) as nooftrans,round(coalesce(sum(tr.total_amount)/100000,0),2),ULB_CODE,u.ulb_name from ptassessmenttax.ptassessment_master m" +
				" right  join ulbs u on ULB_CODE=m.ulbcode" +
				" left join ptassessmenttax.transaction_history tr on tr.uniqueRequestNumber=m.uniquerequestNumber" +
				" left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID" +
				" where  DATE(transdate)>='"+fromDate+"' and DATE(transdate)<='"+toDate+"' and  u.c_delflag='N' and tr.c_delflag='N' and transaction_response_code='0300' and paid_update_flag='S' and gateway_name='CashCounter' and transaction_type=05" +
				" group by m.ulbcode ";
		Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(sql);
		System.out.println(sql+">>>>>>>>>>>>>");	
		return userQuery.list();
	}

	@Override
	public List<String> cashgatewayCountByDate(String fromDate, String toDate) {
		String sql="";
		sql="select gateway_name,count(*),round(coalesce(sum(total_amount)/100000,0),2) as amount from ptassessmenttax.transaction_history where DATE(transdate)>='"+fromDate+"' and DATE(transdate)<='"+toDate+"'and paid_update_flag='S'  and c_delflag='N' and gateway_name='CashCounter' and transaction_type=05 group by gateway_name ";
		
		Session session = sessionFactory.openSession();
        Query userQuery = session.createSQLQuery(sql);
		
		System.out.println(sql+">>>>>>>>>>>>>");	
		return userQuery.list();
	}

	@Override
	public List<String> transCashDetailReportByDate(String fromDate,
			String toDate) {
String sql="";
		
		
		sql="select m.uniqueRequestNumber, m.AssessmentNo,th.payment_transaction_receipt_id,th.total_amount,th.transdate,m.pDoorNo,th.receipt_owner_name,ULB_CODE,th.transaction_id,th.loginId from ptassessmenttax.transaction_history th "+  
				" inner join ptassessmenttax.ptassessment_master m on th.uniqueRequestNumber=m.uniqueRequestNumber  left "+
                " join ptassessmenttax.ulbs u on ULB_CODE=m.ulbcode  inner join districts d on d.DISTRICT_ID=u.DISTRICT_ID  where "+
                " paid_update_flag='S' and th.transdate between :fromDate And :toDate and th.c_delflag='N' and th.gateway_name='CashCounter' and transaction_type=05 and m.AssessmentNo IS NOT NULL ";
		
	
		Session session = sessionFactory.openSession();
        Query userQuery = session.createSQLQuery(sql);

        userQuery.setParameter("fromDate", fromDate);
        userQuery.setParameter("toDate", toDate);
        //userQuery.setParameter("ulb", ulb);
        System.out.println(sql+">>>>>>>>>>>>>");	
		return userQuery.list();
	}
	}
	



