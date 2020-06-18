package com.mars.cdma.gov.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.LoginDao;
import com.mars.cdma.gov.bean.Login;
import com.mars.cdma.gov.bean.SelfAssmentVerification;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.SMSHttpPostClient;

@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private HttpSession httpSession;
	SMSHttpPostClient sendsms = new SMSHttpPostClient();
	String citizenMsg;

	@Override
	public Map<String, String> userLogin(Login login) {
		// TODO Auto-generated method stub

		String userSQL = "SELECT  u.`USER_GROUP_ID`,u.`FIRST_NAME`, u.`MIDDLE_NAME`, u.`LAST_NAME`, u.`PHONE_NO`,u.`STATUS`, u.`ULB_CODE` "
				+ "FROM ptassessmenttax.`user` u Inner join ptassessmenttax.user_group ug On u.`USER_GROUP_ID`=ug.`USER_GROUP_ID` AND ug.`STATUS`=1 And u.`STATUS`=1 "
				+ " And u.`USER_NAME`=:user_name And u.`PASSWORD`=:user_password ";

		String permissionSQL = "SELECT  p.`PERMISSION_ID`,p.`PERMISSION_NAME`,p.`PERMISSION_URL`,pg.`PERMISSION_GROUP_NAME`  FROM ptassessmenttax.permission_group pg "
				+ " Inner Join ptassessmenttax.permission p ON pg.`PERMISSION_GROUP_ID`=p.`PERMISSION_GROUP_ID` Inner Join permission_usergroup pug "
				+ " ON pug.`PERMISSION_ID`=p.`PERMISSION_ID` AND pug.`USER_GROUP_ID`=:user_group_id And pg.`STATUS`= 1";

		Session session = sessionFactory.openSession();

		// Fetch Valid User
		Query userQuery = session.createSQLQuery(userSQL);
		userQuery.setParameter("user_name", login.getUserId());
		userQuery.setParameter("user_password", login.getUserPassword());
		List<Object[]> userDetail = userQuery.list();
		Map<String, String> sessionList = new HashedMap();

		// Fetch Permission For User
		if (!userDetail.isEmpty()) {

			// For User
			String user_group = "";
			Iterator<Object[]> useriterator = userDetail.iterator();
			while (useriterator.hasNext()) {
				Object[] detail = (Object[]) useriterator.next();
				// System.out.print(detail[0]+"");
				String u_name = detail[1] + " " + detail[2] + " " + detail[3];
				System.out.println("User Name " + detail[1] + " <> "
						+ detail[2] + "5 " + detail[3] + " " + detail[4]
						+ " 5 " + detail[5] + " 6 " + detail[6]);

				user_group = detail[0].toString();
				sessionList.put("user_name", u_name);
				sessionList.put("user_login_id", login.getUserId());
				httpSession.setAttribute("user_name", detail[1]);
				httpSession.setAttribute("ulb_code", detail[6]);
				break;
			}
			// System.out.println(user_group);
			// for Permission
			Query permisionQuery = session.createSQLQuery(permissionSQL);
			permisionQuery.setParameter("user_group_id", user_group);
			List<Object[]> permissionList = permisionQuery.list();

			Iterator<Object[]> permissionIterator = permissionList.iterator();
			while (permissionIterator.hasNext()) {
				Object[] detail = (Object[]) permissionIterator.next();
				String permissionName = detail[1] + "";
				// System.out.println(permissionName);
				sessionList.put(permissionName, permissionName);
			}

			return sessionList;
		} else {
			sessionList.put("fail", "fail");
			return sessionList;

		}

	}

	@Override
	public boolean saveAssmentVerification(
			SelfAssmentVerification selfAssmentVerification) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			selfAssmentVerification.setEntrydate(new Date());
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			char[] Uniqueseq = sendsms.OTP(5);
			// System.out.println("Uniqueseq otp is:::"+String.valueOf(Uniqueseq));
			selfAssmentVerification.setOtpId(String.valueOf(Uniqueseq));
			selfAssmentVerification.setDelflag('N');
			session.saveOrUpdate(selfAssmentVerification);
			flag = true;
			transaction.commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		citizenMsg = "Dear Applicant Your PT SelfAssessment Application OTP is  " + selfAssmentVerification.getOtpId();
		if (flag = true) {

			sendsms.SendSMSmain(selfAssmentVerification.getMobileNo(),
					citizenMsg);
		}
		return flag;

	}

	@SuppressWarnings("null")
	@Override
	public boolean selectOtp(String otp, String mobileNo) {
		Session session = null;
		String sql = "";
		boolean flag = false;

		try {
			session = sessionFactory.openSession();
			// sql="select OTPID from ptassessmenttax.selfassmentverification where OTPID='"+otp+"' ";
			sql = "SELECT OTPID,mobileNo FROM ptassessmenttax.selfassment_verification s where mobileNo='"
					+ mobileNo+"' order by appNo desc limit 1";
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> userDetail = query.list();
			if (!userDetail.isEmpty()) {
				Iterator<Object[]> useriterator = userDetail.iterator();
				while (useriterator.hasNext()) {
					Object[] detail = (Object[]) useriterator.next();
					String u_name = detail[0]+"";
					System.out.println(u_name);
					if (detail[0].equals(otp)) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}else flag=false;
		/*	
			String sql1 = "SELECT * FROM SelfAssmentVerification WHERE mobileNo = :mobileNo";
			SQLQuery query1 = session.createSQLQuery(sql);
			query1.addEntity(SelfAssmentVerification.class);
			query1.setParameter("mobileNo", mobileNo);
			List results = query1.list();
			for (Iterator iterator = results.iterator(); iterator.hasNext();){
				SelfAssmentVerification employee = (SelfAssmentVerification) iterator.next(); 
	            System.out.print("First Name: " + employee.getAppNo()); 
	            System.out.print("  Last Name: " + employee.getMobileNo()); 
	            System.out.println("  Salary: " + employee.getOtpId()); 
	         }*/
			//flag=false;
			 }
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			//flag=false;
		}

		return flag;

	}

}
