package com.mars.cdma.gov.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.AssessmentReportDao;
import com.mars.cdma.gov.bean.DtcpApplication;

@Repository("assessmentReportDao")
public class AssessmentReportDaoImpl implements AssessmentReportDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Object[]> dashboard() {
		/*
		 * String sql =
		 * "SELECT  SUM(CASE WHEN pt.application_stage like '%submitted%' THEN 1 ELSE 0 END) as App_Sub,"
		 * +
		 * "   SUM(CASE WHEN pt.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END) as Cmnr_App_Comp ,"
		 * +
		 * "  SUM(CASE WHEN pt.application_stage like '%Commissioner rejected Application%' THEN 1 ELSE 0 END) as Rejceted,"
		 * +"  count(pt.new_application_id) total"
		 * +"  FROM ptassessmenttax.ptassessment_master pt Where pt.delflag='N'"
		 * ;
		 */
		String sql = "SELECT  SUM(CASE WHEN pt.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved,"
				+ " count(pt.application_stage like '%Application Approved%') total"
				+ " FROM ptassessmenttax.ptassessment_master pt ,ptassessmenttax.transaction_history h Where pt.delflag='N'"
				+ " and pt.AssessmentNo IS NOT NULL and pt.paymentflag='Y' and h.paid_update_flag='S' and h.transaction_flag='S' and h.c_delflag='N'"
				+ " and pt.uniqueRequestNumber=h.uniqueRequestNumber";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> oldDashboard() {
		String sql = "SELECT  SUM(CASE WHEN pt.application_stage like '%submitted%' THEN 1 ELSE 0 END) as App_Sub,"
				+ "   SUM(CASE WHEN pt.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END) as Cmnr_App_Comp ,"
				+ "  SUM(CASE WHEN pt.application_stage like '%Commissioner rejected Application%' THEN 1 ELSE 0 END) as Rejceted,"
				+ "  count(pt.new_application_id) total"
				+ "  FROM ptassessmenttax.ptassessment_master pt Where pt.delflag='N'";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> getAssmntByAllDistrictWise() {

		/*
		 * String sql = "SELECT d.`DISTRICT_ID`, " // 0 +
		 * "  d.`DISTRICT_NAME`, " // 1 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ,"
		 * // 2 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ,"
		 * // 3 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ,"
		 * // 4 +
		 * " SUM(CASE WHEN asmt_master.application_stage like  '%ejected%' THEN 1 ELSE 0 END),"
		 * // 5 + " count(asmt_master.new_application_id), " +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), "
		 * // 7 +
		 * " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"
		 * // 8 +
		 * " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
		 * + " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`" +
		 * "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' group by d.`DISTRICT_ID` "
		 * ;
		 */
		/*String sql = "SELECT d.`DISTRICT_ID`,d.`DISTRICT_NAME`,SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved"
				+ " FROM ptassessmenttax.ptassessment_master asmt_master Inner Join ptassessmenttax.ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' and asmt_master.application_stage IS NOT NULL group by d.`DISTRICT_ID`";*/
		String sql="SELECT d.`DISTRICT_ID`,d.`DISTRICT_NAME`,SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved"
                    +" FROM ptassessmenttax.ptassessment_master asmt_master Inner Join ptassessmenttax.transaction_history h On asmt_master.uniqueRequestNumber=h.uniqueRequestNumber"
                    +" Inner Join ptassessmenttax.ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE` "
  +"  INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
 +" Where d.`c_delflag`='N' and u.`c_delflag`='N'  And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' and asmt_master.application_stage IS NOT NULL  and h.paid_update_flag='S' and h.transaction_flag='S' and h.c_delflag='N'"
 +" group by d.`DISTRICT_ID`;";
		
		
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List getAssmntByAllDistrictWiseByDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAssmntBySelectedDistrictWise(String districtCode) {

		/*
		 * String sql = "SELECT u.`ULB_CODE`," + " u.`ULB_NAME`, " // 1 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ,"
		 * // 2 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ,"
		 * // 3 +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ,"
		 * // 4 +
		 * " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END),"
		 * // 5 + " count(asmt_master.new_application_id), " +
		 * " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), "
		 * // 7 +
		 * " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"
		 * // 8 +
		 * " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
		 * + " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`" +
		 * "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And d.`DISTRICT_ID`=:districtCode group by u.`ULB_CODE`"
		 * ;
		 */
		String sql = "SELECT u.`ULB_CODE`,u.`ULB_NAME`,SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved"
				+ " FROM ptassessmenttax.ptassessment_master asmt_master Inner Join ptassessmenttax.ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN ptassessmenttax.districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ " Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' And d.`DISTRICT_ID`=:districtCode  group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("districtCode", districtCode);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List<Object[]> getAssmntByAllUlbWise() {

		/*String sql = "SELECT u.ULB_CODE,"
				 +" u.ULB_NAME,"
				 +" SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved,"
				 +" count(asmt_master.new_application_id)"
				+" FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+" Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y'   group by u.`ULB_CODE`";
*/
				
		String sql="SELECT u.ULB_CODE,"
				+ "u.ULB_NAME,"
				+ "SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved,"
				+ "count(asmt_master.new_application_id)"
				+ "FROM ptassessment_master asmt_master Inner Join ptassessmenttax.transaction_history h On asmt_master.uniqueRequestNumber=h.uniqueRequestNumber "
				+ " Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' and h.paid_update_flag='S' and h.transaction_flag='S'   group by u.`ULB_CODE";		
				
				/*"SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N'  group by u.`ULB_CODE`";
*/
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List getAssmntBySelectedUlbWise(String ulbCode) {

		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " asmt_master.`AssessmentNo`,"
				+ " asmt_master.`OWNER_NAME`," // 2
				+ " asmt_master.`OWNER_SURNAME`, " // 3
				+ " asmt_master.`pDoorNo`, "// 4
				+ " asmt_master.`OWNER_CITY`, "// 5
				+ " asmt_master.`OWNER_DISTRICT`," // 6
				+ " asmt_master.`application_stage`,"// 7
				+ " asmt_master.`entrydate` " // 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedUlbWiseCount(String ulbCode) {

		/*String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and  u.`ULB_CODE`=:ulbCode ";
*/
		String sql="SELECT u.`ULB_CODE`,"
 +" u.`ULB_NAME`,"
 +" SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved,"
 +" count(asmt_master.new_application_id)"
 +" FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
	+"Where  u.`c_delflag`='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y'   And asmt_master.delflag='N' and  u.`ULB_CODE`=:ulbCode";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> Olddashboard() {
		String sql = "SELECT  SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) , "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END),"
				+ " count(asmt_master.new_application_id),"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END),"
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"
				+ " FROM ptassessment_master asmt_master"
				+ " Where  asmt_master.delflag='N' ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> getOldAssmntByAllDistrictWise() {

		String sql = "SELECT d.`DISTRICT_ID`, " // 0
				+ "  d.`DISTRICT_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' group by d.`DISTRICT_ID` ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List getOldAssmntBySelectedDistrictWise(String districtCode) {

		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And d.`DISTRICT_ID`=:districtCode group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("districtCode", districtCode);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List getOldAssmntBySelectedUlbWise(String ulbCode) {

		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " asmt_master.`OWNER_NAME`," // 2
				+ " asmt_master.`OWNER_SURNAME`, " // 3
				+ " asmt_master.`OWNER_DOORNO`, "// 4
				+ " asmt_master.`OWNER_VILLIAGE`, "// 5
				+ " asmt_master.`OWNER_DISTRICT`," // 6
				+ " asmt_master.`application_stage`,"// 7
				+ " asmt_master.`entrydate` " // 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getOldAssmntBySelectedUlbWiseCount(String ulbCode) {

		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> getOldAssmntByAllUlbWise() {

		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N'  group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;

	}

	// charan written

	@Override
	public List<Object[]> getAssmntByAllDistrictWiseCMS() {
		// TODO Auto-generated method stub
		String sql = "SELECT d.`DISTRICT_ID`, " // 0
				+ "  d.`DISTRICT_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='C' group by d.`DISTRICT_ID` ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData1 = query.list();
		return transactionData1;
	}

	@Override
	public List<Object[]> getAssmntByAllUlbWiseCMS() {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='C'  group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedUlbWiseCMS(String ulbCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " asmt_master.`OWNER_NAME`," // 2
				+ " asmt_master.`OWNER_SURNAME`, " // 3
				+ " asmt_master.`OWNER_DOORNO`, "// 4
				+ " asmt_master.`OWNER_VILLIAGE`, "// 5
				+ " asmt_master.`OWNER_DISTRICT`," // 6
				+ " asmt_master.`application_stage`,"// 7
				+ " asmt_master.`entrydate` " // 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='c' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;

	}

	@Override
	public List getAssmntBySelectedUlbWiseCountCMS(String ulbCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='C' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedDistrictWiseCMS(String districtCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='C' And d.`DISTRICT_ID`=:districtCode group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("districtCode", districtCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> getAssmntByAllDistrictWiseMob() {
		// TODO Auto-generated method stub
		String sql = "SELECT d.`DISTRICT_ID`, " // 0
				+ "  d.`DISTRICT_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='M' group by d.`DISTRICT_ID` ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData1 = query.list();
		return transactionData1;
	}

	@Override
	public List<Object[]> getAssmntByAllUlbWiseMob() {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='M'  group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedUlbWiseMob(String ulbCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and  asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='M'  group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedUlbWiseCountMob(String ulbCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ "	Where  u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='M' and  u.`ULB_CODE`=:ulbCode ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("ulbCode", ulbCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List getAssmntBySelectedDistrictWiseMob(String districtCode) {
		// TODO Auto-generated method stub
		String sql = "SELECT u.`ULB_CODE`,"
				+ " u.`ULB_NAME`, " // 1
				+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
				+ " count(asmt_master.new_application_id), "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
				+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
				+ " INNER  JOIN districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
				+ "	Where d.`c_delflag`='N' and u.`c_delflag`='N'   And asmt_master.delflag='N' And asmt_master.status='M' And d.`DISTRICT_ID`=:districtCode group by u.`ULB_CODE`";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("districtCode", districtCode);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> dashboardCMS() {
		// TODO Auto-generated method stub
		String sql = "SELECT  SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) , "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END),"
				+ " count(asmt_master.new_application_id),"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END),"
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"
				+ " FROM ptassessment_master asmt_master"
				+ " Where  asmt_master.delflag='N' And asmt_master.status='C' ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	@Override
	public List<Object[]> dashboardMob() {
		// TODO Auto-generated method stub
		String sql = "SELECT  SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) , "
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ,"
				+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END),"
				+ " count(asmt_master.new_application_id),"
				+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END),"
				+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"
				+ " FROM ptassessment_master asmt_master"
				+ " Where  asmt_master.delflag='N' And asmt_master.status='M' ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();
		return transactionData;
	}

	// new implementation
	@Override
	public List<DtcpApplication> getDistrictcode() {
		Session session = null;
		List<DtcpApplication> ulbdist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			// SQLQuery
			// query=session.createSQLQuery("select ULB_CODE,ULB_NAME,ULB_STATUS,GRADE_CODE,DISTRICT_ID,c_delflag  From  ulbs where ULB_CODE='"+id+"'");
			SQLQuery query = session
					.createSQLQuery("SELECT DISTRICT_ID, DISTRICT_NAME FROM taxcal.districts");

			ulbdist = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return ulbdist;

	}

	@Override
	public List<Object[]> getDtcpDetail(int id) {
		Session session = null;
		List<Object[]> ulblist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();

			String sql1 = "SELECT id,dtcpfilenumber,aadharnumber,emasRegNo,entry_date,length,localitycode,mobilenumber,occupant_type,occupantname,occupantsurname,ownercity,ownername,ownershiptype,relationname,relationsurname,revenue_ward_code,street_name_code,surname,ulbcode,width,zonecode,emas_status FROM taxcal.dtcp_application where delflag='N' and ulbcode="
					+ id;

			SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql1);
			ulblist = query1.list();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return ulblist;
	}

	@Override
	public DtcpApplication getSelfAssessmentApplication(int id) {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(DtcpApplication.class);
		criteria.add(Restrictions.eq("id", id));

		DtcpApplication getsinglerecord = (DtcpApplication) criteria
				.uniqueResult();
		return getsinglerecord;
	}

	@Override
	public List<Object[]> getDtcpDashboard(String ulbCode) {
		String sql = "";
		if (ulbCode == null) {
			sql = "SELECT count(*)as totalcount, sum(case when   dtcpap.emas_status='N' then 1 else 0 end ) as completed,SUM(CASE WHEN dtcpap.emas_status='Y' then 1 else 0 end) as uncompleted FROM taxcal.dtcp_application dtcpap where dtcpap.delflag='N'";

		} else {
			sql = "SELECT count(*)as totalcount, sum(case when   dtcpap.emas_status='N' then 1 else 0 end ) as completed,SUM(CASE WHEN dtcpap.emas_status='Y' then 1 else 0 end) as uncompleted FROM taxcal.dtcp_application dtcpap where dtcpap.delflag='N' and ulbcode="
					+ ulbCode + "";
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();

		return transactionData;
	}

	@Override
	public List<Object[]> getDtcpDashboardUlbWise(Integer districtId) {
		String sql = "select u.`ULB_NAME`,count(*)as totalrequest,sum(case when u.c_delflag='N' then 1 else 0 end)as completed,"
				+ "sum(case when u.c_delflag='Y' then 1 else 0 end)as uncompleted  FROM ulbs u inner join districts d"
				+ "on u.district_id=d.district_id";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();

		return transactionData;

	}

	@Override
	public List<Object[]> getDtcpDashboardUlbDistrict(Integer districtId) {
		List transactionData = new ArrayList<>();
		try {

			String sql = "SELECT dtcp.`ulbcode`,u.ulb_name,count(dtcp.id)as totalrequesr,sum(case when dtcp.emas_status='N' then 1 else 0 end) as completed,"
					+ " sum( case when dtcp.emas_status='Y' then 1 else 0 end)as uncompleted"
					+ " from  dtcp_application dtcp join  ulbs u on dtcp.ulbcode=u.ulb_code"
					+ " join districts d on u.district_id=d.district_id where d.district_id=:districtId";

			/*
			 * String sql=
			 * "SELECT dtcp.`ulbcode`, u.`ULB_NAME`, u.`DISTRICT_ID`as ulbdisrid ,d.`DISTRICT_ID` as distdistid"
			 * +
			 * " from ulbs u inner join districts d on u.district_id=d.district_id "
			 * +
			 * " inner join dtcp_application dtcp  on dtcp.districtcode=u.district_id   where u.district_id=:districtId group by u.`DISTRICT_ID`"
			 * ;
			 */
			SQLQuery query = sessionFactory.openSession().createSQLQuery(sql);
			query.setParameter("districtId", districtId);
			transactionData = query.list();

		} catch (Exception e) {
			System.out.println("exception=" + e);
		}
		return transactionData;
	}

	@Override
	public List<Object[]> getDtcpDashboardList(String ulbCode) {
		String sql = "";
		if (ulbCode == null) {
			sql = "SELECT d.`DISTRICT_ID`, d.`DISTRICT_NAME`,"
					+ "count(*)as totalcount, sum(case when   dtcpap.emas_status='N' then 1 else 0 end ) as completed,"
					+ "SUM(CASE WHEN dtcpap.emas_status='Y' then 1 else 0 end) as uncompleted "
					+ "FROM taxcal.dtcp_application dtcpap"
					+ " Inner Join taxcal.ulbs u ON dtcpap.`ulbcode`=u.`ULB_CODE` inner join taxcal.districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
					+ "where d.`c_delflag`='N' and u.`c_delflag`='N' and  dtcpap.delflag='N' group by d.`DISTRICT_ID`";

		} else {
			sql = "SELECT d.`DISTRICT_ID`, d.`DISTRICT_NAME`,"
					+ "count(*)as totalcount, sum(case when   dtcpap.emas_status='N' then 1 else 0 end ) as completed,"
					+ "SUM(CASE WHEN dtcpap.emas_status='Y' then 1 else 0 end) as uncompleted "
					+ "FROM taxcal.dtcp_application dtcpap"
					+ " Inner Join taxcal.ulbs u ON dtcpap.`ulbcode`=u.`ULB_CODE` inner join taxcal.districts d ON d.`DISTRICT_ID`=u.`DISTRICT_ID`"
					+ "where d.`c_delflag`='N' and u.`c_delflag`='N' and  dtcpap.delflag='N' and ulb_code="
					+ ulbCode + " group by d.`DISTRICT_ID`";

		}

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List transactionData = query.list();

		return transactionData;

	}

	/*
	 * Session session = null; List<Integer> total = null;
	 * 
	 * try { session = sessionFactory.openSession(); session.beginTransaction();
	 * 
	 * String sql1 = "SELECT count(*)FROM taxcal.dtcp_application";
	 * 
	 * SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql1);
	 * total = query1.list();
	 * 
	 * System.out.println("totla=" + total);
	 * 
	 * } catch (Exception e) {
	 * 
	 * } finally { session.close(); }
	 * 
	 * return list; }
	 */

	@Override
	public DtcpApplication getDtcpPdfReport(long uniquerequestid) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(DtcpApplication.class);
		criteria.add(Restrictions.eq("id", uniquerequestid));

		DtcpApplication getsinglerecord = (DtcpApplication) criteria
				.uniqueResult();
		return getsinglerecord;

	}

	@Override
	public List<String> ULBReport(String ulbCode, String type) {
		String sql = "";

		if (ulbCode != null) {

			sql = "select d.DISTRICT_NAME,ULB_NAME,count(td.CDMA_TRANSACTION_RECEIPT_ID), round(coalesce(sum(tr.AMOUNT)/100000,0),2) ,ULB_CODE  from cdmaepay.transaction_details td "
					+ " right  join ulbs u on ULB_CODE=td.I_ULBOBJID "
					+ " left join cdmaepay.transaction_history tr on tr.CDMA_TRANSACTION_RECEIPT_ID=td.CDMA_TRANSACTION_RECEIPT_ID "
					+ " left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID "
					+ " where u.c_delflag='N' and ULB_CODE="
					+ ulbCode
					+ "  and  PAID_UPDATE_FLAG='S' "
					+ " group by ULB_CODE order by ULB_NAME ";

		} else {
			sql = "select d.DISTRICT_NAME,ULB_NAME,count(td.CDMA_TRANSACTION_RECEIPT_ID), round(coalesce(sum(tr.AMOUNT)/100000,0),2) ,ULB_CODE  from cdmaepay.transaction_details td "
					+ " right  join ulbs u on ULB_CODE=td.I_ULBOBJID "
					+ " left join cdmaepay.transaction_history tr on tr.CDMA_TRANSACTION_RECEIPT_ID=td.CDMA_TRANSACTION_RECEIPT_ID "
					+ " left join  districts d on d.DISTRICT_ID=u.DISTRICT_ID "
					+ " where u.c_delflag='N'   and  PAID_UPDATE_FLAG='S' "
					+ " group by ULB_CODE order by ULB_NAME ";
		}
		Session session = sessionFactory.openSession();

		// Fetch Valid User
		Query userQuery = session.createSQLQuery(sql);

		return userQuery.list();

	}

	@Override
	public List<String> ULBDetailReport(String ulb, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> gatewayCount(String ulbCode, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getUlbReport(String ulbCode) {
		Session session = null;
		String sql;
		List<Object[]> ulblist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();

			sql = "SELECT id,dtcpfilenumber,aadharnumber,emasRegNo,entry_date,length,localitycode,mobilenumber,occupant_type,occupantname,occupantsurname,ownercity,ownername,ownershiptype,relationname,relationsurname,revenue_ward_code,street_name_code,surname,ulbcode,width,zonecode,emas_status FROM taxcal.dtcp_application where delflag='N' ";

			if (ulbCode != null) {
				sql = sql + " and ulbcode=" + ulbCode;
			}

			SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql);
			ulblist = query1.list();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return ulblist;

	}

	@Override
	public List getAssmntByAllWiseByDate(Date fromDate, Date toDate) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						/*"SELECT u.`ULB_CODE`,"
								+ " u.`ULB_NAME`, " // 1
								+ " SUM(CASE WHEN asmt_master.application_stage = 'ApplicationSubmitted' THEN 1 ELSE 0 END) ," // 2
								+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Field verification' OR asmt_master.application_stage ='Under SI Verification' THEN 1 ELSE 0 END) ," // 3
								+ " SUM(CASE WHEN asmt_master.application_stage = 'Under Commissioner Approval' THEN 1 ELSE 0 END) ," // 4
								+ " SUM(CASE WHEN asmt_master.application_stage  like  '%ejected%' THEN 1 ELSE 0 END)," // 5
								+ " count(asmt_master.new_application_id), "
								+ " SUM(CASE WHEN asmt_master.application_stage = 'Commissioner Approval Completed' THEN 1 ELSE 0 END), " // 7
								+ " IFNULL(SUM(CASE WHEN (datediff(now(), asmt_master.entrydate)>15 and asmt_master.application_stage != 'Commissioner rejected Application' and  asmt_master.application_stage != 'Application Rejected'  AND asmt_master.application_stage != 'Commissioner Approval Completed') THEN 1 ELSE 0 END),0)"// 8
								+ " FROM ptassessment_master asmt_master Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
								+ "	Where  u.`c_delflag`='N' And asmt_master.delflag='N' And asmt_master.entrydate between :fromDate And :toDate  group by u.`ULB_CODE`");
*/
						"SELECT u.`ULB_CODE`,"
						+ " u.`ULB_NAME`,"
						+ " SUM(CASE WHEN asmt_master.application_stage like '%Application Approved%' THEN 1 ELSE 0 END) as Approved,"
						+ " count(asmt_master.new_application_id)"
						+ " FROM ptassessment_master asmt_master  Inner Join ptassessmenttax.transaction_history h On asmt_master.uniqueRequestNumber=h.uniqueRequestNumber"
						+ " Inner Join ulbs u On asmt_master.`ulbcode`=u.`ULB_CODE`"
						+ " Where  u.`c_delflag`='N' And asmt_master.delflag='N' and asmt_master.AssessmentNo is NOT Null and asmt_master.paymentflag='Y' and h.paid_update_flag='S' and h.transaction_flag='S' And asmt_master.entrydate between :fromDate And :toDate  group by u.`ULB_CODE`");
			
						
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		List ptAssmentData = query.list();
		return ptAssmentData;
	}

	@Override
	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId) {
		String sql = " Select ulb.ULB_NAME,"
				+ " ptasmnt.OWNER_NAME,"
				+ " ptasmnt.OWNER_SURNAME,"
				+ " tran.TRANSACTION_BANK_IN,"
				+ " tran.TRANSACTION_ID,"
				+ "	tran.gateway_name,"
				+ " tran.noc_amount,"
				+ " tran.TRADE_AMOUNT,"
				+ " tran.total_AMOUNT,"
				+ " tran.receipt_owner_name,"
				+ " (CASE WHEN tran.transaction_response_code = '0300' THEN 'Success' ELSE 'NA' END),"
				+ " (CASE WHEN ptasmnt.paymentflag = 'Y' THEN 'Success' ELSE 'Not Completed' END),"
				+ " tran.payment_transaction_receipt_id ,"
				+ " tran.uniqueRequestNumber,"
				+ " tran.transdate,"
				+ " IFNULL(tran.transaction_error_description,'')"
				+ " FROM ptassessment_master ptasmnt INNER JOIN transaction_history tran ON"
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
		String sql = "SELECT Sum(t.`total_amount`) FROM transaction_history t Inner Join ptassessment_master ptasmnt ON t.uniqueRequestNumber=ptasmnt.uniqueRequestNumber"
				+ " Where t.transaction_flag='S' and t.c_delflag='N' and ptasmnt.delflag='N' Order By  t.uniqueRequestNumber;";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);

		List transactionData = query.list();

		return transactionData;
	}

	@Override
	public List<Object> getptSelfAsmntTrancationCollection() {
		// TODO Auto-generated method stub
		return null;
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
				+ " FROM ptassessment_master ptasmnt INNER JOIN transaction_history tran ON"
				+ " ptasmnt.uniqueRequestNumber=tran.uniqueRequestNumber INNER JOIN ulbs ulb"
				+ " ON ptasmnt.ulbcode =ulb.ULB_CODE"
				+ " Where ulb.c_delflag='N' And ptasmnt.delflag='N' And tran.c_delflag='N'  order by  tran.payment_transaction_receipt_id DESC";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);// Where
		List transactionData = query.list();

		return transactionData;
	}

	@Override
	public List getptSelfAsmntTransactionDetail(String string) {
		return null;
	}

	@Override
	public List getptSelfAsmntTransStatus(int parseInt) {
		return null;
	}

}
