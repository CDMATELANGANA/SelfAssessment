package com.mars.cdma.gov.Dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JsonDataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.PtDownloadDao;
import com.mars.cdma.gov.bean.AssesmentBean;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.AssessmentRegCertificate;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.helper.SMSHttpPostClient;

/**
 * @author SARITA
 *
 */
@Repository
public class PtDownloadDaoImpl implements PtDownloadDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private HttpSession httpSession;
	SMSHttpPostClient sendsms = new SMSHttpPostClient();
	String citizenMsg;
	Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	AssesmentBean asmn1 = new AssesmentBean();
	@Override
	public String assessmentCopyOtp(String mobile, String assessment) {
		System.out.println("assessment="+assessment);
		System.out.println("mobile="+mobile);
		
		char[] Uniqueseq = sendsms.OTP(5);
		
		citizenMsg = "Dear "+assessment+" Your PT SelfAssessment Application OTP is  " +String.valueOf(Uniqueseq);
		
		sendsms.SendSMSmain(mobile,citizenMsg);
		return String.valueOf(Uniqueseq);
	}
	@Override
	public AssessmentMaster getPdfAssessmentReport(long uniquerequestid) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(AssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniquerequestid));
		AssessmentMaster getsinglerecord = (AssessmentMaster) criteria
				.uniqueResult();
		
		return getsinglerecord;
	}
	
	
	public void jasperPDF(JasperReport jasperReport,
			Map<String, Object> hmParams, HttpServletResponse response,String filename) {
		
		System.out.println("enter in DAOIMPL");
		try {
			String jsondata=new Gson().toJson(hmParams).toString();
			System.out.println("jsondata=="+jsondata);
			 ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(jsondata.getBytes());
			    //Create json datasource from json stream
			    JsonDataSource ds = new JsonDataSource(jsonDataStream);
			    
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport,hmParams,ds
					);
			
						response.reset();
			response.resetBuffer();
			
			response.setHeader("Content-Disposition", "attachment;filename="+filename+".pdf");
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
		@Override
	public List<String> getassessmentdata(String assessment_number)  {
	return null;
	}
	public  HashMap<String, Object> getRecordFromDb2(String Ulb,String assessment) throws SQLException, IOException, NamingException {
		System.out.println("assessment  @@@@@"+assessment);
		System.out.println("Ulb======="+Ulb);
		String distname = Dbcon.getULBName(Ulb);
		 con = Dbcon.getdbfromdist(distname);
		/****************** End Live DB Connection *******************************/
		/****************** Test DBConnection *******************************/
		// con=DbUtil.getConnection();
		/****************** End Test DB Connection *******************************/

		System.out.println("Connection " + con);
		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		PreparedStatement pstmt=null;
		//String sql1 = "select I_ASMTNO,VC_ONRSURNAME,VC_ONRNAME,VC_FTHRSURNAME,VC_FTHRNAME,VC_ONRDOORNO,I_ULBOBJID FROM pt_asmt_mstr_tbl where I_ASMTNO="+assessment+"";
		//String sql1 = "select a.I_ASMTNO,a.VC_ONRSURNAME,a.VC_ONRNAME,a.VC_FTHRSURNAME,a.VC_FTHRNAME,a.VC_ONRDOORNO,a.I_ULBOBJID,b.VC_ULBNAME FROM pt_asmt_mstr_tbl a join CT_ULB_MSTR_TBL b on  a.I_ULBOBJID = b.I_ULBOBJID where I_ASMTNO="+assessment+" and a.C_DELFLAG='N' and a.C_WRTOFFFLAG='N'";
        String sql1="select a.I_ASMTNO,a.VC_ONRSURNAME,a.VC_ONRNAME,a.VC_FTHRSURNAME,a.VC_FTHRNAME,a.VC_ONRDOORNO,c.VC_LCTYNAME,a.I_ULBOBJID,b.VC_ULBNAME FROM pt_asmt_mstr_tbl a, CT_ULB_MSTR_TBL b,CT_LCTY_MSTR_TBL c  where a.I_ULBOBJID = b.I_ULBOBJID and a.I_ASMTNO="+assessment+" and a.C_DELFLAG='N' and a.C_WRTOFFFLAG='N' and a.I_LCTYOBJID=c.I_LCTYOBJID and b.I_ULBOBJID = c.I_ULBOBJID and c.C_DELFLAG='N'";
		System.out.println("sql1    "+sql1);
       
        String I_ASMTNO="";
        String VC_ONRSURNAME="";
        String VC_ONRNAME="";
        String VC_FTHRSURNAME="";
        String VC_FTHRNAME="";
        String VC_ONRDOORNO="";
        String I_ULBOBJID="";
        String VC_ULBNAME="";
        String VC_LCTYNAME="";
        String PtHalfYearTax="";
        pstmt = con.prepareStatement(sql1);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			I_ASMTNO=rs.getString("I_ASMTNO");
			VC_ONRSURNAME=rs.getString("VC_ONRSURNAME");
			VC_ONRNAME=rs.getString("VC_ONRNAME");
			VC_FTHRSURNAME=rs.getString("VC_FTHRSURNAME");
			VC_FTHRNAME=rs.getString("VC_FTHRNAME");
			VC_ONRDOORNO=rs.getString("VC_ONRDOORNO");
			I_ULBOBJID=rs.getString("I_ULBOBJID");
			VC_ULBNAME=rs.getString("VC_ULBNAME");
			VC_LCTYNAME=rs.getString("VC_LCTYNAME");
		}
		hmParams.put("I_ASMTNO", I_ASMTNO);
		hmParams.put("VC_ONRSURNAME",VC_ONRSURNAME.toUpperCase());
		hmParams.put("VC_ONRNAME", VC_ONRNAME.toUpperCase());
		hmParams.put("VC_FTHRSURNAME", VC_FTHRSURNAME.toUpperCase());
		hmParams.put("VC_FTHRNAME", VC_FTHRNAME.toUpperCase());
		hmParams.put("VC_ONRDOORNO", VC_ONRDOORNO);
		hmParams.put("I_ULBOBJID", I_ULBOBJID);
		hmParams.put("VC_ULBNAME", VC_ULBNAME.toUpperCase());
		hmParams.put("VC_LCTYNAME", VC_LCTYNAME.toUpperCase());
		System.out.println("VC_ULBNAME"+VC_ULBNAME);
		pstmt.close();
		rs.close();
		//new 
		 String sql2 = "select I_ASMTNO,D_FXDPT+D_FXDLCS+D_FXDUNAUTHPLNPLTY as PtHalfYearTax from pt_asmt_mstr_tbl where C_DELFLAG='N' and C_XPTN='N' and C_WRTOFFFLAG='N'  and I_ASMTNO="+assessment;
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		ResultSet rs2 = pstmt2.executeQuery();
		while (rs2.next()) {
		PtHalfYearTax=String.valueOf(rs2.getDouble("PtHalfYearTax"));
		}
		System.out.println("PtHalfYearTax==="+PtHalfYearTax);
		hmParams.put("PtHalfYearTax", PtHalfYearTax);
		pstmt2.close();
		
		con.close();
		
		
		String sql="SELECT g.grade_name,d.DISTRICT_NAME,u.ULB_NAME,a.sequence_no FROM ptassessmenttax.grade_mstr_tbl g,ptassessmenttax.ulbs u,ptassessmenttax.districts d,ptassessmenttax.assessmentregcertificate a"
				+" where u.GRADE_CODE=g.grade_code and u.ULB_CODE="+Ulb+" and u.DISTRICT_ID=d.DISTRICT_ID and a.assessment="+assessment;
		PreparedStatement stmp=null;
		Connection conn3=MySqlDBConnection.getEpayDB();
		stmp=conn3.prepareStatement(sql);
		ResultSet rs1=stmp.executeQuery();
		String grade_name="";
		String DISTRICT_NAME="";
		String ULB_NAME="";
		String sequence_no="";
		while(rs1.next())
		{
			grade_name=rs1.getString("grade_name");
			DISTRICT_NAME=rs1.getString("DISTRICT_NAME");
			ULB_NAME=rs1.getString("ULB_NAME");
			sequence_no=String.valueOf(rs1.getInt("sequence_no"));
		}
		hmParams.put("grade_name", grade_name.toUpperCase());
		hmParams.put("DISTRICT_NAME", DISTRICT_NAME.toUpperCase());
		hmParams.put("ULB_NAME", ULB_NAME.toUpperCase());
		hmParams.put("SEQ_NO", sequence_no);
		conn3.close();
		stmp.close();
		hmParams.put("C_DATE", new Date());
		return hmParams;
	} 

	
	public int Count(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count(*) as count from ptassessmenttax.assessmentregcertificate where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int genSequence() {
		Session session = sessionFactory.openSession();
		String sql="select count(*) from ptassessmenttax.assessmentregcertificate";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int getSequence(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select sequence_no from ptassessmenttax.assessmentregcertificate where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int getDownloadCount(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count_download from ptassessmenttax.assessmentregcertificate where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	
	@Override
	public boolean saveAssessmentDownloadDetail(String assessment,
			String mobile) {
		int c=Count(assessment);
		int squence=0000;
		squence=(squence+genSequence());
		System.out.println("squence=="+squence);
		AssessmentRegCertificate assessmentRegCertificate=new AssessmentRegCertificate();
		if(c!=1)
		{
		assessmentRegCertificate.setMobile(mobile);
		assessmentRegCertificate.setAssessment(assessment);
		assessmentRegCertificate.setSequence_no(String.valueOf(squence+1));
		assessmentRegCertificate.setRequestDate(new Date());
		assessmentRegCertificate.setCount_download(1);
		Session session=null;
		 Transaction transaction=null;
			session=sessionFactory.openSession() ;
			transaction=session.beginTransaction();
		 session.save(assessmentRegCertificate);
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
					Query query=session.createQuery("update AssessmentRegCertificate set mobile='"+mobile+"',requestDate='"+ date1+"',count_download="+dcount+" where assessment="+assessment);
					System.out.println("query="+query);
					query.executeUpdate();
					transaction.commit();
			return true;
		}
	}
}
