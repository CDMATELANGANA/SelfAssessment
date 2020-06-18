package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PtPaidReceiptDao;
import com.mars.cdma.gov.bean.PtPaidReceiptDownload;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.helper.SMSHttpPostClient;

/**
 * @author SARITA
 *
 */
@Repository
public class PtPaidReceiptDaoImpl implements PtPaidReceiptDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private HttpSession httpSession;
	SMSHttpPostClient sendsms = new SMSHttpPostClient();
	String citizenMsg;
	Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public String ptAssPaidReceiptSms(String mobile, String assessment) {
		System.out.println("assessment="+assessment);
		System.out.println("mobile="+mobile);
		
		char[] Uniqueseq = sendsms.OTP(5);
		
		citizenMsg = "Dear "+assessment+" Your PT SelfAssessment Last Paid Tax Receipt Application Search OTP is  " +String.valueOf(Uniqueseq);
		
		sendsms.SendSMSmain(mobile,citizenMsg);
		return String.valueOf(Uniqueseq);
	}
	public int Count(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count(*) as count from ptassessmenttax.PtPaidReceiptDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int getDownloadCount(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count_download from ptassessmenttax.PtPaidReceiptDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	@Override
	public boolean savePtAssPaidReceipt(String assessment, String mobile) {
		int c=Count(assessment);
		PtPaidReceiptDownload ptPaidReceiptDownload=new PtPaidReceiptDownload();
		if(c!=1)
		{
			ptPaidReceiptDownload.setMobile(mobile);
			ptPaidReceiptDownload.setAssessment(assessment);
			ptPaidReceiptDownload.setRequestDate(new Date());
			ptPaidReceiptDownload.setCount_download(1);
		Session session=null;
		 Transaction transaction=null;
			session=sessionFactory.openSession() ;
			transaction=session.beginTransaction();
		 session.save(ptPaidReceiptDownload);
			transaction.commit();					
			return true;
		}
		else{
			Session session=null;
			 Transaction transaction=null;
				session=sessionFactory.openSession() ;
				transaction=session.beginTransaction();
			
				int dcount=(1+getDownloadCount(assessment));
					session=sessionFactory.openSession() ;
					transaction=session.beginTransaction();
					String PATTERN="yyyy-MM-dd";
					SimpleDateFormat dateFormat=new SimpleDateFormat();
					dateFormat.applyPattern(PATTERN);
					String date1=dateFormat.format(Calendar.getInstance().getTime());
					Query query=session.createQuery("update PtPaidReceiptDownload set mobile='"+mobile+"',requestDate='"+ date1+"',count_download="+dcount+" where assessment="+assessment);
					query.executeUpdate();
					transaction.commit();
			return true;
		}
	}

	@Override
	public HashMap<String, Object> getPtAssPaidLastReceiptFromDb2(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		System.out.println("assessment  @@@@@"+assessment);
		System.out.println("Ulb======="+Ulb);
		String distname = Dbcon.getULBName(Ulb);
		 con = Dbcon.getdbfromdist(distname);
		/****************** End Live DB Connection *******************************/
		/****************** Test DBConnection *******************************/
		// con=DbUtil.getConnection();
		/****************** End Test DB Connection *******************************/

		System.out.println("Connection " + con);
		HashMap<String, Object> hmParams= new HashMap<String, Object>();
		PreparedStatement pstmt=null;
		String sql1="select a.VC_ONRSURNAME,a.VC_ONRNAME,a.VC_ONRDOORNO||'.' DOORNo,c.VC_LCTYNAME,b.DT_ETRYDT,b.I_SLNO,"
	+" b.C_RCPTNO,b.C_AMTPAIDAT,b.C_PAYMODE,sum(b.D_CRNPT+b.D_CRNLCS+b.D_CRNUAUTHCNSTPLTY+b.D_PLTYONCRN+b.D_ARRPT+b.D_ARRLCS+b.D_ARRUAUTHCNSTPLTY+b.D_PLTYONARR) as Total,"
	+" b.DT_PAIDFRMPRDDT, b.DT_PAIDTOPRDDT,d.VC_ULBNAME from pt_asmt_mstr_tbl a, pt_asmtrcpt_tbl b, CT_LCTY_MSTR_TBL c,CT_ULB_MSTR_TBL d where a.I_LCTYOBJID=c.I_LCTYOBJID "
	+" and a.I_ASMTNO="+assessment+" and a.I_ASMTNO=b.I_ASMTNO and a.I_ULBOBJID="+Ulb+" and a.C_DELFLAG='N' and b.C_DELFLAG='N' and b.C_ISRLSD='C' and  a.C_WRTOFFFLAG='N' and b.I_ULBOBJID=d.I_ULBOBJID"
	+" and c.I_ULBOBJID=d.I_ULBOBJID and a.I_ULBOBJID=d.I_ULBOBJID group by c.VC_LCTYNAME,a.VC_ONRDOORNO||'.',a.VC_ONRSURNAME,a.VC_ONRNAME,b.DT_PAIDFRMPRDDT, b.DT_PAIDTOPRDDT,b.DT_ETRYDT,"
	+" d.VC_ULBNAME,b.I_SLNO,b.C_RCPTNO,b.C_AMTPAIDAT,b.C_PAYMODE order by b.DT_ETRYDT desc fetch first 1 rows only";
		System.out.println("sql1    "+sql1);
		String VC_ONRSURNAME="";
		String VC_ONRNAME="";
		//String VC_ONRDOORNO="";
		String DOORNo="";
		String VC_LCTYNAME="";
		String DT_ETRYDT="";
		String I_SLNO="";
		String C_RCPTNO="";
		String C_AMTPAIDAT="";
		String C_PAYMODE="";
		String Total="";
		String DT_PAIDFRMPRDDT="";
		String DT_PAIDTOPRDDT="";
		String VC_ULBNAME="";
		pstmt = con.prepareStatement(sql1);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			VC_ONRSURNAME=rs.getString("VC_ONRSURNAME");
			VC_ONRNAME=rs.getString("VC_ONRNAME");
			//VC_ONRDOORNO=rs.getString("VC_ONRDOORNO");
			DOORNo=rs.getString("DOORNo");
			VC_LCTYNAME=rs.getString("VC_LCTYNAME");
			DT_ETRYDT=rs.getString("DT_ETRYDT");
			I_SLNO=rs.getString("I_SLNO");
			C_RCPTNO=rs.getString("C_RCPTNO");
			C_AMTPAIDAT=rs.getString("C_AMTPAIDAT");
			C_PAYMODE=rs.getString("C_PAYMODE");
			Total=rs.getString("Total");
			DT_PAIDFRMPRDDT=rs.getString("DT_PAIDFRMPRDDT");
			DT_PAIDTOPRDDT=rs.getString("DT_PAIDTOPRDDT");
			VC_ULBNAME=rs.getString("VC_ULBNAME");
	}
		hmParams.put("VC_ONRSURNAME", VC_ONRSURNAME);
		hmParams.put("VC_ONRNAME", VC_ONRNAME);
		//hmParams.put("VC_ONRDOORNO", VC_ONRDOORNO);
		hmParams.put("DOORNo", DOORNo);
		hmParams.put("VC_LCTYNAME", VC_LCTYNAME);
		hmParams.put("DT_ETRYDT", DT_ETRYDT);
		hmParams.put("I_SLNO", I_SLNO);
		hmParams.put("C_RCPTNO", C_RCPTNO);
		hmParams.put("C_AMTPAIDAT", C_AMTPAIDAT);
		hmParams.put("C_PAYMODE", C_PAYMODE);
		hmParams.put("Total", Total);
		hmParams.put("DT_PAIDFRMPRDDT", DT_PAIDFRMPRDDT);
		hmParams.put("DT_PAIDTOPRDDT", DT_PAIDTOPRDDT);
		hmParams.put("VC_ULBNAME", VC_ULBNAME);
		pstmt.close();
		rs.close();
		PreparedStatement pstmt2=null;
		String sql3="select sum(D_CRNPT+D_CRNED+D_CRNLCS+D_CRNUAUTHCNSTPLTY+D_ARRPT+D_ARRED+D_ARRLCS+D_ARRUAUTHCNSTPLTY+D_PLTYONCRN+D_PLTYONARR) Totalpaidamount from pt_asmtrcpt_tbl where i_asmtno="+assessment+"";
		pstmt2 = con.prepareStatement(sql3);
		ResultSet rs3 = pstmt2.executeQuery();
		String Totalpaidamount="";
		while (rs3.next()) {
			Totalpaidamount=rs3.getString("Totalpaidamount");
		}
		hmParams.put("Totalpaidamount", Totalpaidamount);
		pstmt2.close();
		rs3.close();
		PreparedStatement pstmt3=null;
		String sql4="select sum(a.D_AREARAMT+a.D_CURRENTAMT+a.D_PLTYONARRS)as balance from pt_asmtbal_tbl a  where  a.I_ASMTNO in (select I_ASMTNO from pt_asmt_mstr_tbl  where I_ASMTNO=a.I_ASMTNO and C_DELFLAG='N' and C_XPTN='N' and C_WRTOFFFLAG='N')  and a.I_ASMTNO="+assessment+"";
		pstmt3 = con.prepareStatement(sql4);
		ResultSet rs4 = pstmt3.executeQuery();
		String balance="";
		while (rs4.next()) {
			balance=rs4.getString("balance");
		}
		hmParams.put("balance", balance);
		pstmt3.close();
		rs4.close();
		String sql2="SELECT g.grade_name,u.ULB_NAME FROM ptassessmenttax.grade_mstr_tbl g,ptassessmenttax.ulbs u,ptassessmenttax.districts d"
				+" where u.GRADE_CODE=g.grade_code and u.ULB_CODE="+Ulb+" and u.DISTRICT_ID=d.DISTRICT_ID";
		System.out.println("sql12    "+sql2);
		PreparedStatement stmp2=null;
		Connection conn3=MySqlDBConnection.getEpayDB();
		stmp2=conn3.prepareStatement(sql2);
		ResultSet rs2=stmp2.executeQuery();
		String grade_name="";
		String ulb_name="";
		/*pstmt2 = con.prepareStatement(sql2);
		System.out.println("1");
		ResultSet rs2 = pstmt2.executeQuery();
		System.out.println("2");*/
		while (rs2.next()) {
			grade_name=rs2.getString("grade_name");
			ulb_name=rs2.getString("ulb_name");
		}
		hmParams.put("grade_name", grade_name);
		hmParams.put("ulb_name", ulb_name);
		stmp2.close();
		rs2.close();
		con.close();
		return hmParams;
	}
}
