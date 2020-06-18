package com.mars.cdma.gov.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.AssessmentInitiateDao;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;

/**
 * @author SARITA
 *
 */
@Repository
public class AssessmentInitiateDaoImpl implements AssessmentInitiateDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Object[]> getInitiateAllDistrictList() {
		String sql = "SELECT p.OWNER_District,d.DISTRICT_NAME FROM ptassessmenttax.ptassessment_master p inner join ptassessmenttax.districts d on p.OWNER_District=d.DISTRICT_ID"
				+ " WHERE p.`AssessmentNo` is not null and delflag='N' and paymentflag='Y' and entrydate>'2019-11-21' group by p.OWNER_District";
		SQLQuery query = sessionFactory.openSession().createSQLQuery(sql);
		List<Object[]> districtList = query.list();
		return districtList;
	}

	@Override
	public List<Object[]> getInitiateSelectedDistrictUlbs(String districtid) {
		String sql = "SELECT u.ULB_CODE,u.ULB_NAME FROM ptassessment_master p Inner Join ulbs u On p.ulbcode=u.ULB_CODE"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.DISTRICT_ID"
				+ " WHERE p.`AssessmentNo` is not null and delflag='N' and paymentflag='Y' and entrydate>'2019-11-21'"
				+ " and d.DISTRICT_ID=" + districtid + " group by u.ULB_CODE";
		SQLQuery query = sessionFactory.openSession().createSQLQuery(sql);
		List<Object[]> districtUlbList = query.list();
		return districtUlbList;
	}

	@Override
	public List<String> getInitiateSelectedUlbList(String ulbcode)
			throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		/************ Live Data Base connectoin ************/
		String distname = Dbcon.getULBName(ulbcode);
		Connection con1 = Dbcon.getdbfromdist(distname);
		/************ Test Data Base connectoin ************/
		String sql = "SELECT p.`AssessmentNo` as assno FROM ptassessmenttax.ptassessment_master p"
				+ " WHERE p.`AssessmentNo` is not null and delflag='N' and paymentflag='Y' and entrydate>'2019-11-21' and ulbcode="
				+ ulbcode;
		List<String> assmentlist = new ArrayList<String>();
		PreparedStatement ps1 = con.prepareStatement(sql);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			assmentlist.add(rs1.getString("assno"));
		}
		// System.out.println("assmentlist="+assmentlist);
		con.close();
		String no = "";
		List<String> assList = new ArrayList<String>();
		ListIterator<String> assmentNo = assmentlist.listIterator();
		String temp = "";
		while (assmentNo.hasNext()) {
			temp = assmentNo.next();
			String sql2 = "select count(I_ASMTNO) as I_ASMTNO  from PT_ASMT_MSTR_TBL where I_ASMTNO="
					+ temp;
			PreparedStatement ps = con1.prepareStatement(sql2);
			ResultSet rs2 = ps.executeQuery();
			// System.out.println("sql==="+sql2);
			while (rs2.next()) {
				no = rs2.getString("I_ASMTNO");
				if (Integer.parseInt(no) == 0) {
					assList.add(temp);
				}
				// System.out.println("sql2="+no);
			}

		}
		// System.out.println("assList="+assList);
		con1.close();
		return assList;
	}

	@Override
	public List<Object[]> getInitiateSelectedUlbDetailList(
			List<String> assmentList, String ulbcode) {

		// String a= assmentList.toString().replace("[", "").replace("]", "");
		String sql = "SELECT u.ULB_NAME,p.`uniqueRequestNumber`,p.`AssessmentNo`,p.`pDoorNo`, p.`application_stage`,p.entrydate,h.payment_transaction_receipt_id  FROM ptassessmenttax.ptassessment_master p,ulbs u,transaction_history h"
				+ " WHERE p.`AssessmentNo` is not null and p.delflag='N' and p.paymentflag='Y' and p.entrydate>'2019-11-21' and p.ulbcode="
				+ ulbcode
				+ " and p.ulbcode=u.ULB_CODE and h.uniqueRequestNumber=p.uniqueRequestNumber  and  p.AssessmentNo in("
				+ assmentList.toString().replace("[", "").replace("]", "")
				+ ")";//and p.entrydate>'2019-11-28'
		System.out.println("list query=" + sql);
		SQLQuery query = sessionFactory.openSession().createSQLQuery(sql);
		List<Object[]> districtUlbList = query.list();
		return districtUlbList;
	}

	@Override
	public List<String> getInitiateSelectedUlbDuplicateAssList(String ulbcode)
			throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		List<String> assDuplicatelist = new ArrayList<String>();
		String sql = "SELECT  p.AssessmentNo as assno, COUNT(p.AssessmentNo) FROM"
				+ " ptassessmenttax.ptassessment_master p where p.ulbcode="
				+ ulbcode
				+ " GROUP BY p.AssessmentNo HAVING COUNT(p.AssessmentNo) > 1";
		PreparedStatement ps1 = con.prepareStatement(sql);
		System.out.println("sql=" + sql);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			assDuplicatelist.add(rs1.getString("assno"));
		}
		con.close();
		return assDuplicatelist;
	}

	@Override
	public List<Object[]> getInitiateSelectedUlbDuplicateAssDetailList(
			List<String> assmentList, String ulbcode) {
		if (assmentList.isEmpty() == false) {
			String sql = "SELECT u.ULB_NAME,p.`uniqueRequestNumber`,p.`AssessmentNo`,p.`pDoorNo`, p.`application_stage`,p.entrydate,h.payment_transaction_receipt_id,"
					+ " p.delflag,p.paymentflag FROM ptassessmenttax.ptassessment_master p,ulbs u,transaction_history h"
					+ " WHERE p.`AssessmentNo` is not null and p.delflag='N' and p.paymentflag='Y' and p.entrydate>'2019-11-21' and p.ulbcode="
					+ ulbcode
					+ " and p.ulbcode=u.ULB_CODE and h.uniqueRequestNumber=p.uniqueRequestNumber and  p.AssessmentNo in("
					+ assmentList.toString().replace("[", "").replace("]", "")
					+ ")";//and p.entrydate>'2019-11-28' 
			SQLQuery query = sessionFactory.openSession().createSQLQuery(sql);
			System.out.println("Duplicate list==" + sql);
			List<Object[]> UlbDuplicateDetailList = query.list();
			return UlbDuplicateDetailList;
		} else {
			return null;
		}
	}

	@Override
	public void updateBeforeInitiate(String assessment, String uniqueno) throws SQLException {

		String sql1="UPDATE ptassessment_master set paymentflag='N' where uniqueRequestNumber="+uniqueno+" and AssessmentNo="+assessment;
		SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql1);
		query1.list();
		
		String sql2="UPDATE transaction_history SET paid_update_flag='F' WHERE uniqueRequestNumber="+uniqueno;
		SQLQuery query2 = sessionFactory.openSession().createSQLQuery(sql2);
		query2.list();
		
		/************ Live Data Base connectoin ************/
		String distname = Dbcon.getULBName(uniqueno.substring(0,4));
		Connection con1 = Dbcon.getdbfromdist(distname);
		/************ Test Data Base connectoin ************/
		
		String sql3="delete from MESEVA_SUVIDA_INT_USR_DATA_TAB where   APPREQNO='"+uniqueno+"'";
		PreparedStatement ps1 = con1.prepareStatement(sql3);
		ResultSet rs1 = ps1.executeQuery();
		
	}


}
