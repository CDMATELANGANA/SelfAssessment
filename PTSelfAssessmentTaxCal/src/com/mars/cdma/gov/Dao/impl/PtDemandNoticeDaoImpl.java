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
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JsonDataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.PtDemandNoticeDao;
import com.mars.cdma.gov.bean.AssesmentBean;
import com.mars.cdma.gov.bean.DemandNoticeDownload;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.helper.SMSHttpPostClient;

/**
 * @author SARITA
 *
 */
@Repository
public class PtDemandNoticeDaoImpl implements PtDemandNoticeDao{
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
	public String assessmentDemandSms(String mobile, String assessment) {
		System.out.println("assessment="+assessment);
		System.out.println("mobile="+mobile);
		
		char[] Uniqueseq = sendsms.OTP(5);
		
		citizenMsg = "Dear "+assessment+" Your PT SelfAssessment Demand Notice Application OTP is  " +String.valueOf(Uniqueseq);
		
		sendsms.SendSMSmain(mobile,citizenMsg);
		return String.valueOf(Uniqueseq);
	}
	public int Count(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count(*) as count from ptassessmenttax.DemandNoticeDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int getDownloadCount(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count_download from ptassessmenttax.DemandNoticeDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	@Override
	public boolean saveAssDemandDownloadDetail(String assessment, String mobile) {
		int c=Count(assessment);
		DemandNoticeDownload demandNoticeDownload=new DemandNoticeDownload();
		if(c!=1)
		{
			demandNoticeDownload.setMobile(mobile);
			demandNoticeDownload.setAssessment(assessment);
			demandNoticeDownload.setRequestDate(new Date());
			demandNoticeDownload.setCount_download(1);
		Session session=null;
		 Transaction transaction=null;
			session=sessionFactory.openSession() ;
			transaction=session.beginTransaction();
		 session.save(demandNoticeDownload);
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
					Query query=session.createQuery("update DemandNoticeDownload set mobile='"+mobile+"',requestDate='"+ date1+"',count_download="+dcount+" where assessment="+assessment);
					query.executeUpdate();
					transaction.commit();
			return true;
		}

	}
	@Override
	public HashMap<String, Object> getDemandRecordFromDb2(String Ulb,
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
		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		PreparedStatement pstmt=null;
        String sql1="select i_dmndno,dt_dmnddt,vc_dmndyear,d_crnpt,d_crned,d_crnlcs,d_crnuauthcnstplty,d_arrpt,d_arred,d_arrlcs,d_arruauthcnstplty,d_pltyonarrs,d_adjdamt,i_asmtno,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_DMNDRGTR_TBL where c_delFlag='N' and i_asmtno="+assessment;
		System.out.println("sql1    "+sql1);
       
        String i_dmndno="";
        String dt_dmnddt="";
        String vc_dmndyear="";
        String d_crnpt="";
        String d_crned="";
        String d_crnlcs="";
        String d_crnuauthcnstplty="";
        String d_arrpt="";
        String d_arred="";
        String d_arrlcs="";
        String d_arruauthcnstplty="";
        String d_pltyonarrs="";
        String d_adjdamt="";
        String i_asmtno="";
        pstmt = con.prepareStatement(sql1);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			i_dmndno=rs.getString("i_dmndno");
			dt_dmnddt=rs.getString("dt_dmnddt");
			vc_dmndyear=rs.getString("vc_dmndyear");
			d_crnpt=rs.getString("d_crnpt");
			d_crned=rs.getString("d_crned");
			d_crnlcs=rs.getString("d_crnlcs");
			d_crnuauthcnstplty=rs.getString("d_crnuauthcnstplty");
			d_arrpt=rs.getString("d_arrpt");
			d_arred=rs.getString("d_arred");
			d_arrlcs=rs.getString("d_arrlcs");
			d_arruauthcnstplty=rs.getString("d_arruauthcnstplty");
			d_pltyonarrs=rs.getString("d_pltyonarrs");
			d_adjdamt=rs.getString("d_adjdamt");
			i_asmtno=rs.getString("i_asmtno");
		}
		hmParams.put("i_dmndno", i_dmndno);
		hmParams.put("dt_dmnddt",dt_dmnddt);
		hmParams.put("vc_dmndyear", vc_dmndyear);
		hmParams.put("d_crnpt", d_crnpt);
		hmParams.put("d_crned", d_crned);
		hmParams.put("d_crnlcs", d_crnlcs);
		hmParams.put("d_crnuauthcnstplty", d_crnuauthcnstplty);
		hmParams.put("d_arrpt", d_arrpt);
		hmParams.put("d_arred", d_arred);
		hmParams.put("d_arrlcs", d_arrlcs);
		hmParams.put("d_arruauthcnstplty", d_arruauthcnstplty);
		hmParams.put("d_pltyonarrs", d_pltyonarrs);
		hmParams.put("d_adjdamt", d_adjdamt);
		hmParams.put("i_asmtno", i_asmtno);
		pstmt.close();
		rs.close();
		//new 
		String sql2="select a.VC_ONRSURNAME,a.VC_ONRNAME,a.VC_ONRDOORNO,c.VC_LCTYNAME,b.VC_ULBNAME,e.I_RWNO,f.I_BLCKNO FROM pt_asmt_mstr_tbl a, CT_ULB_MSTR_TBL b,CT_LCTY_MSTR_TBL c,CT_RW_MSTR_TBL e,CT_BLCK_MSTR_TBL f  where a.I_ULBOBJID = b.I_ULBOBJID and a.I_ASMTNO="+assessment+" and a.C_DELFLAG='N' and a.C_WRTOFFFLAG='N' and a.I_LCTYOBJID=c.I_LCTYOBJID and b.I_ULBOBJID = c.I_ULBOBJID and c.C_DELFLAG='N' and a.I_RWOBJID=e.I_RWOBJID and a.I_BLCKOBJID=f.I_BLCKOBJID ";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		ResultSet rs2 = pstmt2.executeQuery();
		System.out.println("sql2="+sql2);
		String VC_ONRSURNAME="";
		String VC_ONRNAME="";
		String VC_ONRDOORNO="";
		String VC_LCTYNAME="";
		String VC_ULBNAME="";
		String I_RWNO="";
		String I_BLCKNO="";
		while (rs2.next()) {
			VC_ONRSURNAME=rs2.getString("VC_ONRSURNAME");
			VC_ONRNAME=rs2.getString("VC_ONRNAME");
			VC_ONRDOORNO=rs2.getString("VC_ONRDOORNO");
			VC_LCTYNAME=rs2.getString("VC_LCTYNAME");
			VC_ULBNAME=rs2.getString("VC_ULBNAME");
			I_RWNO=rs2.getString("I_RWNO");
			I_BLCKNO=rs2.getString("I_BLCKNO");
		}
		hmParams.put("VC_ONRSURNAME", VC_ONRSURNAME);
		hmParams.put("VC_ONRNAME", VC_ONRNAME);
		hmParams.put("VC_ONRDOORNO", VC_ONRDOORNO);
		hmParams.put("VC_LCTYNAME", VC_LCTYNAME);
		hmParams.put("VC_ULBNAME", VC_ULBNAME);
		hmParams.put("I_RWNO", I_RWNO);
		hmParams.put("I_BLCKNO", I_BLCKNO);
		pstmt2.close();
		rs2.close();
//dependent query
		//getting first period demand year
		String dmndYear = vc_dmndyear;
		int intYear = new Integer(dmndYear.substring(0,4)).intValue();
		int intPeriod = new Integer(dmndYear.substring(10)).intValue();
		int intFirstPeriod=intPeriod-1;
		 String DmndFirstPeriod= dmndYear.substring(0,10); 
		  DmndFirstPeriod=DmndFirstPeriod+intFirstPeriod;
		 //end getting period demand year
		String sql4="select d_crnpt,d_crned,d_crnlcs,d_crnuauthcnstplty,d_arrpt,d_arred,d_arrlcs,d_arruauthcnstplty from PT_DMNDRGTR_TBL where c_delFlag='Y' and i_asmtno="+assessment+" and VC_DMNDYEAR='"+DmndFirstPeriod+"'";
		PreparedStatement pstmt4 = con.prepareStatement(sql4);
		ResultSet rs4 = pstmt4.executeQuery();
		System.out.println("sql4="+sql4);
        String d_crnpt2="";
        String d_crned2="";
        String d_crnlcs2="";
        String d_crnuauthcnstplty2="";
        String d_arrpt2="";
        String d_arred2="";
        String d_arrlcs2="";
        String d_arruauthcnstplty2="";
		while (rs4.next()) {
			d_crnpt2=rs4.getString("d_crnpt");
			d_crned2=rs4.getString("d_crned");
			d_crnlcs2=rs4.getString("d_crnlcs");
			d_crnuauthcnstplty2=rs4.getString("d_crnuauthcnstplty");
			d_arrpt2=rs4.getString("d_arrpt");
			d_arred2=rs4.getString("d_arred");
			d_arrlcs2=rs4.getString("d_arrlcs");
			d_arruauthcnstplty2=rs4.getString("d_arruauthcnstplty");
		}
		hmParams.put("d_crnpt2", d_crnpt2);
		hmParams.put("d_crned2", d_crned2);
		hmParams.put("d_crnlcs2", d_crnlcs2);
		hmParams.put("d_crnuauthcnstplty2", d_crnuauthcnstplty2);
		hmParams.put("d_arrpt2", d_arrpt2);
		hmParams.put("d_arred2", d_arred2);
		hmParams.put("d_arrlcs2", d_arrlcs2);
		hmParams.put("d_arruauthcnstplty2", d_arruauthcnstplty2);
		pstmt4.close();
		rs4.close();

// end dependent query		
		
		con.close();
		
		
		/*String sql3="SELECT g.grade_name,d.DISTRICT_NAME,u.ULB_NAME FROM ptassessmenttax.grade_mstr_tbl g,ptassessmenttax.ulbs u,ptassessmenttax.districts d"
				+" where u.GRADE_CODE=g.grade_code and u.ULB_CODE="+Ulb+" and u.DISTRICT_ID=d.DISTRICT_ID";*/
		String sql3="SELECT g.grade_name,d.DISTRICT_NAME,u.ULB_NAME FROM ptassessmenttax.grade_mstr_tbl g,ptassessmenttax.ulbs u,ptassessmenttax.districts d"
        +" where u.GRADE_CODE=g.grade_code and u.ULB_CODE='"+Ulb+"' and u.DISTRICT_ID=d.DISTRICT_ID";
		PreparedStatement stmp3=null;
		System.out.println("sql3="+sql3);
		Connection conn3=MySqlDBConnection.getEpayDB();
		stmp3=conn3.prepareStatement(sql3);
		ResultSet rs1=stmp3.executeQuery();
		String grade_name="";
		String DISTRICT_NAME="";
		String ULB_NAME="";
		while(rs1.next())
		{
			grade_name=rs1.getString("grade_name");
			DISTRICT_NAME=rs1.getString("DISTRICT_NAME");
			ULB_NAME=rs1.getString("ULB_NAME");
		}
		hmParams.put("grade_name", grade_name.toUpperCase());
		hmParams.put("DISTRICT_NAME", DISTRICT_NAME.toUpperCase());
		hmParams.put("ULB_NAME", ULB_NAME.toUpperCase());
		conn3.close();
		stmp3.close();
		rs1.close();
		// Total Calculation
		//double arrearsTotaloncurrent = objDmnd1.getD_arrpt() + objDmnd1.getD_arred() + objDmnd1.getD_arrlcs()+ objDmnd1.getD_arruauthcnstplty();		
	//Double currentFirstHalfTotal = Double.parseDouble(d_crnpt)+ Double.parseDouble(d_crned) + Double.parseDouble(d_crnlcs) + Double.parseDouble(d_crnuauthcnstplty);
	Double	Current_total=Double.parseDouble(d_crnpt2)+Double.parseDouble(d_crned2)+Double.parseDouble(d_crnlcs2)+Double.parseDouble(d_crnuauthcnstplty2);
	//Double Arrears_total=Double.parseDouble(d_arrpt)+Double.parseDouble(d_arred)+Double.parseDouble(d_arrlcs)+Double.parseDouble(d_arruauthcnstplty);
	Double Arrears_total=Double.parseDouble(d_arrpt2)+Double.parseDouble(d_arred2)+Double.parseDouble(d_arrlcs2)+Double.parseDouble(d_arruauthcnstplty2);
	Double Current_frst_hlf_total=Double.parseDouble(d_crnpt2)+Double.parseDouble(d_crned2)+Double.parseDouble(d_crnlcs2)+Double.parseDouble(d_crnuauthcnstplty2);
	Double Total_Demand=Current_total+Arrears_total+Current_frst_hlf_total+Double.parseDouble(d_pltyonarrs);
	Double Demand_Adjs=Double.parseDouble(d_adjdamt)+Double.parseDouble(d_adjdamt);
	Double NetTotalDemand=Total_Demand-Demand_Adjs;
	hmParams.put("Current_total",Current_total);
	hmParams.put("Arrears_total",Arrears_total);
	hmParams.put("Current_frst_hlf_total",Current_frst_hlf_total);
	hmParams.put("Total_Demand",Total_Demand);
	hmParams.put("Demand_Adjs",Demand_Adjs);
	hmParams.put("NetTotalDemand",NetTotalDemand);
	String date0 = "";
	if(intPeriod == 1) {date0 = "31/03/"+intYear;}
	if(intPeriod == 2) {date0 = "30/09/"+intYear;}
	System.out.println("date0=="+date0);
	hmParams.put("date0",date0);
	return hmParams;

	}
	
	public void jasperDemandPDF(JasperReport jasperReport,
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
	

}
