package com.mars.cdma.gov.Dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.PtTaxPaidHistoryDao;
import com.mars.cdma.gov.bean.AssessmentReceipt;
import com.mars.cdma.gov.bean.PtPaidHistoryDownload;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
import com.mars.cdma.gov.utils.Constants;

/**
 * @author SARITA
 *
 */
@Repository
public class PtTaxPaidHistoryDaoImpl implements PtTaxPaidHistoryDao{
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
	public String ptAssPaidHistorySms(String mobile, String assessment) {
		System.out.println("assessment="+assessment);
		System.out.println("mobile="+mobile);
		
		char[] Uniqueseq = sendsms.OTP(5);
		
		citizenMsg = "Dear "+assessment+" Your PT SelfAssessment Transaction History Application OTP is  " +String.valueOf(Uniqueseq);
		
		sendsms.SendSMSmain(mobile,citizenMsg);
		return String.valueOf(Uniqueseq);
	}
	public int Count(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count(*) as count from ptassessmenttax.PtPaidHistoryDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	public int getDownloadCount(String assessment) {
		Session session = sessionFactory.openSession();
		String sql="select count_download from ptassessmenttax.PtPaidHistoryDownload where assessment='"+assessment+"'";
        Query userQuery = session.createSQLQuery(sql);
        String l=userQuery.list().toString();
        return Integer.parseInt(l.replace("[","").replace("]", ""));
	}
	@Override
	public boolean savePtAssPaidHistory(String assessment, String mobile) {
		int c=Count(assessment);
		PtPaidHistoryDownload ptPaidHistoryDownload=new PtPaidHistoryDownload();
		if(c!=1)
		{
			ptPaidHistoryDownload.setMobile(mobile);
			ptPaidHistoryDownload.setAssessment(assessment);
			ptPaidHistoryDownload.setRequestDate(new Date());
			ptPaidHistoryDownload.setCount_download(1);
		Session session=null;
		 Transaction transaction=null;
			session=sessionFactory.openSession() ;
			transaction=session.beginTransaction();
		 session.save(ptPaidHistoryDownload);
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
					Query query=session.createQuery("update PtPaidHistoryDownload set mobile='"+mobile+"',requestDate='"+ date1+"',count_download="+dcount+" where assessment="+assessment);
					query.executeUpdate();
					transaction.commit();
			return true;
		}

	}
	@Override
	public HashMap<String, Object> getPtAssPaidHistoryFromDb2(String Ulb,
			String assessment) throws SQLException, IOException,
			NamingException {
		/*System.out.println("assessment  @@@@@"+assessment);
		System.out.println("Ulb======="+Ulb);
		String distname = Dbcon.getULBName(Ulb);
		 con = Dbcon.getdbfromdist(distname);
		*//****************** End Live DB Connection *******************************//*
		*//****************** Test DBConnection *******************************//*
		// con=DbUtil.getConnection();
		*//****************** End Test DB Connection *******************************//*

		System.out.println("Connection " + con);
		HashMap<String, Object> hmParams= new HashMap<String, Object>();
		PreparedStatement pstmt=null;
        String sql1="select I_BOOKNO as BookNo,C_RCPTNO as ReceiptNo,DT_RCPTDT as Receiptdate,DT_ETRYDT as Entrydate,DT_PAIDFRMPRDDT as paidfromdate,"
					 +" DT_PAIDTOPRDDT as paiduptodate,C_PAYMODE as paidmode,C_AMTPAIDAT as amountpaidat,"
					 +" sum(D_CRNPT+D_CRNED+D_CRNLCS+D_CRNUAUTHCNSTPLTY) as currentamount, "
					 +" sum(D_ARRPT+D_ARRED+D_ARRLCS+D_ARRUAUTHCNSTPLTY) as arrearamount,"
					 +" sum(D_PLTYONCRN+D_PLTYONARR) as penaltyamount,D_ADVAMT as advanceamount,"
					 +" sum(D_CRNPT+D_CRNED+D_CRNLCS+D_CRNUAUTHCNSTPLTY+D_ARRPT+D_ARRED+D_ARRLCS+D_ARRUAUTHCNSTPLTY+D_PLTYONCRN+D_PLTYONARR) Totalpaidamount,C_ISRLSD" 
					 +" from pt_asmtrcpt_tbl where i_asmtno="+assessment+" group by I_BOOKNO,C_RCPTNO,DT_RCPTDT,DT_ETRYDT,DT_PAIDFRMPRDDT,DT_PAIDTOPRDDT,C_PAYMODE,C_AMTPAIDAT,D_ADVAMT,C_ISRLSD Order by DT_ETRYDT desc";
		System.out.println("sql1    "+sql1);
		String I_BOOKNO="";
		String C_RCPTNO="";
		String ReceiptNo="";
		String DT_RCPTDT="";
		String DT_ETRYDT="";
		String DT_PAIDFRMPRDDT="";
		String DT_PAIDTOPRDDT="";
		String C_PAYMODE="";
		String C_AMTPAIDAT="";
		String currentamount="";
		String arrearamount="";
		String advanceamount="";
		String Totalpaidamount="";
		String C_ISRLSD="";
		 pstmt = con.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				I_BOOKNO=rs.getString("BookNo");
				C_RCPTNO=rs.getString("ReceiptNo");
				ReceiptNo=rs.getString("Receiptdate");
				DT_RCPTDT=rs.getString("Entrydate");
				DT_ETRYDT=rs.getString("paidfromdate");
				DT_PAIDFRMPRDDT=rs.getString("paiduptodate");
				DT_PAIDTOPRDDT=rs.getString("paidmode");
				C_PAYMODE=rs.getString("paidmode");
				C_AMTPAIDAT=rs.getString("amountpaidat");
				currentamount=rs.getString("currentamount");
				arrearamount=rs.getString("arrearamount");
				advanceamount=rs.getString("advanceamount");
				Totalpaidamount=rs.getString("Totalpaidamount");
				C_ISRLSD=rs.getString("C_ISRLSD");
			}
			hmParams.put("I_BOOKNO", I_BOOKNO);
			hmParams.put("C_RCPTNO", C_RCPTNO);
			hmParams.put("ReceiptNo", ReceiptNo);
			hmParams.put("DT_RCPTDT", DT_RCPTDT);
			hmParams.put("DT_ETRYDT", DT_ETRYDT);
			hmParams.put("DT_PAIDFRMPRDDT", DT_PAIDFRMPRDDT);
			hmParams.put("DT_PAIDTOPRDDT", DT_PAIDTOPRDDT);
			hmParams.put("C_PAYMODE", C_PAYMODE);
			hmParams.put("C_AMTPAIDAT", C_AMTPAIDAT);
			hmParams.put("currentamount", currentamount);
			hmParams.put("arrearamount", arrearamount);
			hmParams.put("advanceamount", advanceamount);
			hmParams.put("Totalpaidamount", Totalpaidamount);
			hmParams.put("C_ISRLSD", C_ISRLSD);
			pstmt.close();
			rs.close();
			con.close();
			return hmParams;
			
*/
		return null;}

	
	public void jasperPtTaxPaidHistoryPDF(JasperReport jasperReport,
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
	public List<AssessmentReceipt> getAssessmentReceiptList(String Ulb,
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
		PreparedStatement pstmt=null;
        String sql1="select I_BOOKNO as BookNo,C_RCPTNO as ReceiptNo,DT_RCPTDT as Receiptdate,DT_ETRYDT as Entrydate,DT_PAIDFRMPRDDT as paidfromdate,"
					 +" DT_PAIDTOPRDDT as paiduptodate,C_PAYMODE as paidmode,C_AMTPAIDAT as amountpaidat,"
					 +" sum(D_CRNPT+D_CRNED+D_CRNLCS+D_CRNUAUTHCNSTPLTY) as currentamount, "
					 +" sum(D_ARRPT+D_ARRED+D_ARRLCS+D_ARRUAUTHCNSTPLTY) as arrearamount,"
					 +" sum(D_PLTYONCRN+D_PLTYONARR) as penaltyamount,D_ADVAMT as advanceamount,"
					 +" sum(D_CRNPT+D_CRNED+D_CRNLCS+D_CRNUAUTHCNSTPLTY+D_ARRPT+D_ARRED+D_ARRLCS+D_ARRUAUTHCNSTPLTY+D_PLTYONCRN+D_PLTYONARR) Totalpaidamount,C_ISRLSD" 
					 +" from pt_asmtrcpt_tbl where i_asmtno="+assessment+" group by I_BOOKNO,C_RCPTNO,DT_RCPTDT,DT_ETRYDT,DT_PAIDFRMPRDDT,DT_PAIDTOPRDDT,C_PAYMODE,C_AMTPAIDAT,D_ADVAMT,C_ISRLSD Order by DT_ETRYDT desc";
		System.out.println("sql1    "+sql1);
		 pstmt = con.prepareStatement(sql1);
			ResultSet resultSet = pstmt.executeQuery();
			List<AssessmentReceipt> list = new ArrayList<AssessmentReceipt>();
			while (resultSet.next()) {
				AssessmentReceipt receipt = new AssessmentReceipt();
				receipt.setBookNo(resultSet.getString(1));
				receipt.setReceiptNo(resultSet.getString(2));
				receipt.setReceipDate(resultSet.getString(3));
				receipt.setEntryDate(resultSet.getString(4));
				receipt.setPaidFromDate(resultSet.getString(5));
				receipt.setPaidUptoDate(resultSet.getString(6));
				receipt.setPaidMode(Constants.getPayMode(resultSet.getString(7)));
				receipt.setAmountPaidAt(Constants.getCollectionName(resultSet.getString(8)));
				receipt.setCurrentAmount(resultSet.getString(9));
				receipt.setArrearAmount(resultSet.getString(10));
				receipt.setPenalityAmount(resultSet.getString(11));
				receipt.setAdvanceAmount(resultSet.getString(12));
				receipt.setTotalPaidAmount(resultSet.getString(13));
				receipt.setStatus(resultSet.getString(14));

				list.add(receipt); 
			}
			return list;
	}
	public static String getPayMode(String payModeKey){
		String payModeVal=null;
		switch (payModeKey) {
		case "C":
			payModeVal="Cash";	
			break;
		case "D":
			payModeVal="Demand Draft";	
			break;
		case "I":
			payModeVal="Credit Card/Debit Card";	
			break;	
		case "M":
			payModeVal="Money Order";	
			break;
		case "P":
			payModeVal="Postal Order";	
			break;
		case "Q":
			payModeVal="Cheque";	
			break;
		}
		return payModeVal;
		
	}
}
