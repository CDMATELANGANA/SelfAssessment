package com.mars.cdma.gov.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mars.cdma.gov.Dao.CashCounterLoginDao;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.service.CashCounterLoginService;

@Repository("cashCounterLoginDao")
public class CashCounterLoginDaoImpl implements CashCounterLoginDao {

	@Autowired
	HttpSession httpsession;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private CashCounterLoginService cashcounterLoginService;

	@Override
	public String userLogin(AssessmentMaster selfMutationApp,
			String vC_USRNAME, String vC_USRPWD) throws SQLException {
		String distname = Dbcon.getULBName("" + selfMutationApp.getUlbcode());
		Connection con = null;
		con = Dbcon.getdbfromdist(distname);
		System.out.println("District Name from ULB code****" + distname);
		/************ Test Data Base connectoin ************/
		//con = Dbcon.getdbfromdist("TEST");
		/************ End Live Data Base connectoin ************/

		String userId = null;// DB2INST1.
		String sql1="select VC_USRNAME from DB2INST1.CT_USR_MSTR_TBL where VC_USRNAME='"+vC_USRNAME+"' and VC_USRPWD='"+vC_USRPWD+"'  and c_delflag='N'";
		//String sql1 = "SELECT I_USRID FROM CT_USR_MSTR_TBL WHERE VC_USRNAME LIKE '%COU%' and c_delflag='N' and date(TS_DTTM)>='08/26/2019' and VC_USRNAME='"+vC_USRNAME+"' and VC_USRPWD='"+vC_USRPWD+"'";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		int Max = 0;

		while (rs1.next()) {

			userId = rs1.getString(1);
			System.out.println("Max---->>" + userId);
		}

		System.out.println("userId=" + userId);
		return userId;
	}

	@Override
	public List<AssessmentMaster> getUniquetData(long uniqueNumber) {
		String userSQL = "select p.uniqueRequestNumber,p.OWNER_CITY,Concat(p.OWNER_SURNAME,' ',p.OWNER_NAME )as Name,Concat(p.OWNERFATHERHUSBAND_SURNAME,p.OWNER_FATHERHUSBAND_NAME) as FatherName,p.OWNER_MOBILE,p.paymentamount,d.DISTRICT_NAME FROM ptassessment_master p,districts d where p.uniqueRequestNumber="+uniqueNumber+" and p.delflag='Y' and p.OWNER_DISTRICT=d.DISTRICT_ID";
        Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(userSQL);
		List<AssessmentMaster> list = userQuery.list();
		return list;
	}

	@Override
	public AssessmentMaster getBean(long uniqueNumber) {
		/*
		 * Session session = sessionFactory.openSession(); String
		 * sql="select * from mutation.mutation where uniqueRequestNumber="
		 * +uniqueRequestNumber+""; Query userQuery =
		 * session.createSQLQuery(sql); MutationBean
		 * mutationBean=(MutationBean)userQuery.list();
		 */
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(AssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqueNumber));
		AssessmentMaster ptAsmntBean = (AssessmentMaster) criteria
				.uniqueResult();

		return ptAsmntBean;
	}

}
