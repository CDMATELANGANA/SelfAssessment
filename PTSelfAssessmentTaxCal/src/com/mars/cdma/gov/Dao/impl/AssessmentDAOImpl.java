package com.mars.cdma.gov.Dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.AssessmentDAO;
import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.UlbsService;

@Repository("assessmentDAO")
public class AssessmentDAOImpl implements AssessmentDAO {
	private static final Logger log = Logger.getLogger(AssessmentDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private Assessmentservice assessmentservice;
	@Autowired
	private UlbsService ulbService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private static PaymentTransactionService paymentTransactionService;
	@Autowired
	private DistrictsService districtsService;

	public Connection con = null;
	//public Connection conDB2 = null;
	public String sql = "", sql1 = null, sql2 = null, sql3 = null, sql4 = null,
			sql5 = null, sql6 = null, sql7 = null, sql8 = null, sql9 = null,
			sql10 = null;
	public PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null, ps5 = null, ps6 = null, ps7 = null, ps8 = null,
			ps9 = null, ps10 = null;
	public ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null,
			rs5 = null, rs6 = null, rs7 = null, rs8 = null, rs9 = null,
			rs10 = null;
	public String citizenMsg;
	public String commMsg;
	public String siMsg;

	@Override
	public boolean save(AssessmentMaster assessmentEntity) {
		boolean flag = false;
		try {
			assessmentEntity.setEntrydate(new java.util.Date());
			log.info("Insert Success");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(assessmentEntity);
			tx.commit();
			flag = true;
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			log.error("failed local insert " + e);
		}
		return flag;
	}

	public String insertlive(AssessmentMaster assessmententity,
			String xmlContent) throws SQLException, IOException,
			NamingException {
		String returnresult = "fail";

		/************ Live Data Base connectoin ************/
		String distname = Dbcon.getULBName("" + assessmententity.getUlbcode());
		Connection conDB2 = Dbcon.getdbfromdist(distname);

		/************ Test Data Base connectoin ************/
		// conDB2 = Dbcon.getdbfromdist("TEST");
		/************ End Live Data Base connectoin ************/
		String table = "DB2INST1.MESEVA_SUVIDA_INT_USR_DATA_TAB";
		int Max = 0;
		int k = 0;
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");

		try {

			sql1 = "select (max(SNO)+1) as max from " + table + "";
			PreparedStatement ps1 = conDB2.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Max = rs1.getInt("max");
				System.out.println("Max NUmber  " + Max);
			}
			int ulbcode = assessmententity.getUlbcode();
			// Check the now assessment Flag
			String sql2 = "select max(APPACKNO) as APPACKNO  from MESEVA_SUVIDA_INT_USR_DATA_TAB where DOCTYPE='NEWASSESMENT' and  ULBID="
					+ ulbcode;
			PreparedStatement ps2 = conDB2.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			int APPACKNO = 0;
			while (rs2.next()) {
				APPACKNO = rs2.getInt("APPACKNO");
				System.out.println("APPACKNO " + APPACKNO);
			}
			String sql = "INSERT INTO "
					+ table
					+ ""
					+ "(SNO, DOCTYPE, REQSTATUS, SUBMITTEDDATE, APPROVEDDATE, XMLCONTENT, APPREQNO, APPACKNO, APPLICATIONSTATE, ULBID, UPLOADEDDOCSPATH, VC_PAY_AMOUNT, VC_PAY_AMOUNT_INSERT_DATE,VC_ASSESSMENTNO)"
					+ "VALUES " + "(" + Max
					+ ",'NEWASSESMENT', 'Application Approved','"
					+ df.format(assessmententity.getEntrydate()) + "','"
					+ df.format(assessmententity.getEntrydate()) + "','"
					+ xmlContent + "', '"
					+ assessmententity.getUniqueRequestNumber() + "','"
					+ APPACKNO + "', 'Approved', " + ulbcode + ", '"
					+ assessmententity.getDoc_path()
					+ "',  '0', '01/01/1990', '"
					+ assessmententity.getAssessmentNo() + "')";


			PreparedStatement ps = conDB2.prepareStatement(sql);
		//	k = ps.executeUpdate();
k=1;
			citizenMsg = "Dear Applicant Your Self Assessment Application Request is received with Request Number: "
					+ assessmententity.getUniqueRequestNumber() + "  ";
			// loggers.info(Msg);
			SMSHttpPostClient sendsms = new SMSHttpPostClient();

		/* Starting Point to send a mesage to commissioner*/
			
			
			/*if (assessmententity.getOwnerMobile() != null) {
				// sendsms.SendSMSmain(
				// assessmententity.getOwnerMobile(), citizenMsg);
			}

			commMsg = "Dear Commissioner New Assessment application Received with Request Number: "
					+ assessmententity.getUniqueRequestNumber() + "  ";
			 siMsg = "Dear   "; 

			sql2 = "select VC_EMPSURNAME,VC_EMPNAME,VC_OFFPHNO from CT_EMP_MSTR_TBL where I_DESGCODE=6 and C_DELFLAG='N' and I_EMPULBID="
					+ ulbcode + "  and LENGTH(VC_OFFPHNO)=10";*/
			// System.out.println("sql1 " + sql2);
			//ps2 = conDB2.prepareStatement(sql2);
			//rs2 = ps2.executeQuery();
			/*try {

				if (rs2.next()) {
					// System.out.println(">>> rs2 ");
					// sendsms.SendSMSmain(rs2.getString("VC_OFFPHNO"),
					// commMsg);
				}

			} catch (Exception e) {
				log.info(e.getMessage());
			}
*/
			/* Ending Point to send a mesage to commissioner*/
			
			if (k == 1) {
				insertPtMasterTable(assessmententity);
				returnresult = "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("failed live insert" + e);
			returnresult = "fail";
			// TODO: handle exception
		}
		// con.close();
		 conDB2.close();
		return returnresult;
	}

	/* PT Master Table Start for Insert */
	@SuppressWarnings("unused")
	public static String insertPtMasterTable(AssessmentMaster assessmententity)
			throws SQLException, IOException, NamingException {
		String returnresult = "fail";
		Connection conn = MySqlDBConnection.getEpayDB();

		String bage = "";
		String bcls = "";
		String busage = "";
		String len = "";
		String wid = "";
		String octy = "";
		String floorNo = "";

		PreparedStatement pstmt = null;

		String sql = "SELECT m.asmt_appl_id as Appid,m.bage as bage,m.bcls as bcls,m.busage as busage,m.len as len,m.wid as wid,m.octy as octy,m.floorNo as floorNo FROM ptassessmenttax.ptassessment_master m1 ,ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id and m1.new_application_id="
				+ assessmententity.getNewapplicationId();

		pstmt = conn.prepareStatement(sql);
		ResultSet rs1 = pstmt.executeQuery();

		while (rs1.next()) {
			bage = rs1.getString("bage");
			bcls = rs1.getString("bcls");
			busage = rs1.getString("busage");
			len = rs1.getString("len");
			wid = rs1.getString("wid");
			octy = rs1.getString("octy");
			floorNo = rs1.getString("floorNo");
			System.out.println("bage  " + bage);
		}

		char uacflag = 'N';
		String bpd = "";
		String rdd = "";
		String bpNo = "";
		String regNo = "";
		if (assessmententity.getPlanaprvl() == "N") {

			uacflag = 'Y';
			/*
			 * bpd="1900-01-01"; rdd="1900-01-01"; bpNo="0"; regNo="0";
			 */
		}
		if (assessmententity.getB_PermisionDate() != null
				&& assessmententity.getRegDate() != null) {
			bpd = "" + assessmententity.getB_PermisionDate();
			rdd = "" + assessmententity.getRegDate();
		} else {
			bpd = "1900-01-01";
			rdd = "" + assessmententity.getRegDate();
		}
		if (assessmententity.getB_PermisionNo() != null
				&& assessmententity.getRegdocno() != null) {
			bpNo = assessmententity.getB_PermisionNo();
			regNo = "" + assessmententity.getRegdocno();
		} else {
			bpNo = "0";
			regNo = "" + assessmententity.getRegdocno();
		}
		Connection con = MySqlDBConnection.getEpayDB();
		String PtId = "";
		// String transType="";
		String transMode = "";
		String cheque_DD_Date = "";
		String Cheque_DD_No = "";
		String C_AMTPAIDAT = "";
		String transdate = "";
		PreparedStatement stmt = null;

		// String Sql =
		// "SELECT t.payment_transaction_receipt_id as id,transactionmode,cheque_DD_Date,Cheque_DD_No,C_AMTPAIDAT,t.transdate FROM ptassessmenttax.transaction_history t  WHERE t.uniqueRequestNumber="+assessmententity.getUniqueRequestNumber()+" and t.paid_update_flag='s' and t.transaction_flag='S' and t.C_delflag='N' and t.transaction_response_code='0300'";
		String Sql = "SELECT t.payment_transaction_receipt_id as id,transactionmode,cheque_DD_Date,Cheque_DD_No,C_AMTPAIDAT,t.transdate FROM ptassessmenttax.transaction_history t  WHERE t.uniqueRequestNumber="
				+ assessmententity.getUniqueRequestNumber()
				+ " and  t.transaction_flag='S' and t.C_delflag='N' and t.transaction_response_code='0300'";
		System.out.println(" transaction : " + Sql);
		stmt = con.prepareStatement(Sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			PtId = rs.getString("id");
			transMode = rs.getString("transactionmode");
			cheque_DD_Date = rs.getString("cheque_DD_Date");
			Cheque_DD_No = rs.getString("Cheque_DD_No");
			C_AMTPAIDAT = rs.getString("C_AMTPAIDAT");
			transdate = rs.getString("transdate");

		}
		System.out.println("C_AMTPAIDAT==" + C_AMTPAIDAT);
		System.out.println("transMode==" + transMode);
		String transDate;
		// &&transdate!=null
		String CasNo;

		if (cheque_DD_Date != null)
			transDate = "" + cheque_DD_Date;
		else
			transDate = "1900-01-01";
		if (Cheque_DD_No != null)
			CasNo = "" + Cheque_DD_No;
		else
			CasNo = "0";
		// String demandYear="2019-2020-1";
		String demandYear = "2019-2020-2";
		String PaidfromDate = "";
		String PaidtoDate = "";
		int int1Year = new Integer(demandYear.substring(0, 4)).intValue();
		int int2Year = new Integer(demandYear.substring(5, 9)).intValue();

		int int1stPeriod = new Integer(demandYear.substring(10)).intValue();

		int intFirstPeriod = int1stPeriod;
		String DmndFirstPeriod = demandYear.substring(0, 10);
		DmndFirstPeriod = DmndFirstPeriod + intFirstPeriod;
		if (int1stPeriod == 1) {
			PaidfromDate = int1Year + "-04-01";
			PaidtoDate = int1Year + "-09-30";
			System.out.println("PaidfromDate " + PaidfromDate);
			System.out.println("PaidtoDate " + PaidtoDate);
		} else {
			PaidfromDate = int1Year + "-10-01";
			PaidtoDate = int2Year + "-03-31";
			System.out.println("PaidfromDate " + PaidfromDate);
			System.out.println("PaidtoDate " + PaidtoDate);
		}

		/************ Live Data Base connectoin ************/
		/************ Test Data Base connectoin ************/
		String distname = Dbcon.getULBName("" + assessmententity.getUlbcode());
		Connection conDB2 = Dbcon.getdbfromdist(distname);
		// Connection conDB2 = Dbcon.getdbfromdist("TEST");
		/************ End Live Data Base connectoin ************/
		//conDB2.setAutoCommit(false);
		int Ulbcode = assessmententity.getUlbcode();
		try {
			String sql1 = " insert into PT_ASMT_MSTR_TBL (i_asmtno,vc_onrsurname,vc_onrname,vc_fthrsurname,vc_fthrname,c_sex,vc_ssidno,i_strtobjid,i_lctyobjid,i_blckobjid,i_rwobjid,i_zoneobjid,i_ewobjid,vc_onrdoorno,vc_onraddr1,vc_onraddr2,vc_city,vc_stdcode,vc_onrphno,vc_onrmobino, vc_onreid,vc_onrfaxno,d_extoflnd,i_vcntlndasmtno, d_areaoveraptt,dt_cnstdt,dt_taxassddt,c_bldgplanoptn,vc_bldgprmnno,dt_bldgprmndt,vc_rgtddocno,dt_rgtddocdt,c_sprstru,c_athdbathroom,c_lift,c_tlts,c_drng,c_whp,c_ecty,c_wt,vc_siteonr,c_xptn,i_xptnpcode,i_flrtypecode,i_rftypecode,i_oscode,c_srvctax,i_srvctaxid,c_aprtcplxopt,i_aprtcplxcode,i_walltypecode,i_woodtypecode,vc_opntsurname,vc_opntname,d_fxdarv,d_fxdpt,d_fxded,d_fxdlcs,c_unauthplnplty,d_fxdunauthplnplty,i_ulbobjid,c_delflag,ts_dttm,i_usrid,C_WRTOFFFLAG,C_VCNYRMSNFLAG) values ("
					+ assessmententity.getAssessmentNo()
					+ ",'"
					+ assessmententity.getOwnerSurName()
					+ "','"
					+ assessmententity.getOwnerName()
					+ "','"
					+ assessmententity.getOwnerFatherHusbandSurName()
					+ "','"
					+ assessmententity.getOwnerFatherHusbandName()
					+ "','M','"
					+ assessmententity.getOwnerAadhar()
					+ "',"
					+ assessmententity.getStreet()
					+ ","
					+ assessmententity.getLocality()
					+ ","
					+ assessmententity.getBlock()
					+ ","
					+ assessmententity.getRevenueward()
					+ ","
					+ assessmententity.getZone()
					+ ","
					+ assessmententity.getElecward()
					+ ","
					+ " '"
					+ assessmententity.getpDoorNo()
					+ "','','','"
					+ assessmententity.getN_landMark()
					+ "','000','000000','"
					+ assessmententity.getOwnerMobile()
					+ "',"
					+ " '"
					+ assessmententity.getOwnerEmail()
					+ "','',0.0,0,0.0,"
					+ "current date"
					+ ",current date,'"
					+ assessmententity.getPlanaprvl().charAt(0)
					+ "','"
					+ bpNo
					+ "','"
					+ bpd
					+ "','"
					+ regNo
					+ "','"
					+ rdd
					+ "',"
					+ " 'N','Y','N','Y','N','N','Y','Y','','N',0,"
					+ assessmententity.getFloorType()
					+ ","
					+ assessmententity.getRoofType()
					+ ","
					+ assessmententity.getOwnershiptype()
					+ ",'N',0,'N',0,"
					+ assessmententity.getWaalType()
					+ ","
					+ assessmententity.getWoodType()
					+ ",'"
					+ assessmententity.getOccupantsurname()
					+ "','"
					+ assessmententity.getOccupantname()
					+ "',"
					+ assessmententity.getNArv()
					+ ","
					+ assessmententity.getFixdPT()
					+ ",0.0,"
					+ assessmententity.getFixdLcs()
					+ ",'"
					+ uacflag
					+ "',"
					+ assessmententity.getD_fxdunauthplnplty()
					+ ","
					+ assessmententity.getUlbcode()
					+ ",'N',current Timestamp,1,'N','N')";

			System.out.println("sql is  " + sql1);
			String sql2 = "insert into pt_dmndrgtr_tbl (I_DMNDNO,DT_DMNDDT,VC_DMNDYEAR,D_CRNPT,D_CRNED,D_CRNLCS,D_CRNUAUTHCNSTPLTY,D_ARRPT,D_ARRED,D_ARRLCS,D_ARRUAUTHCNSTPLTY,D_PLTYONARRS,D_ADJDAMT,I_ASMTNO,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID) values ("
					+ getMaxCount("DB2INST1.pt_dmndrgtr_tbl", "I_DMNDNO",
							Ulbcode)
					+ ","
					+ "current date"
					+ ",'"
					+ demandYear
					+ "',"
					+ assessmententity.getFixdPT()
					+ ",0.00,"
					+ assessmententity.getFixdLcs()
					+ ","
					+ assessmententity.getD_fxdunauthplnplty()
					+ ",0.00,0.00,0.00,0.00,0.00,0.00,"
					+ assessmententity.getAssessmentNo()
					+ ","
					+ assessmententity.getUlbcode()
					+ ",'N',"
					+ "current timestamp" + ",1)";
			System.out.println("sql2 " + sql2);

			/* Receipt Table saved data */
			String sql3 = "insert into pt_asmtrcpt_tbl (I_SLNO,I_BOOKNO,C_RCPTNO,DT_RCPTDT,DT_ETRYDT,I_BNKCODE,"
					+ "D_CRNPT,D_CRNED,D_CRNLCS,D_CRNUAUTHCNSTPLTY,I_EMPCODE,D_PLTYONCRN,D_ARRPT,D_ARRED,D_ARRLCS,D_ARRUAUTHCNSTPLTY,"
					+ "D_PLTYONARR,DT_PAIDFRMPRDDT,DT_PAIDTOPRDDT,D_ADVAMT,VC_PAYTYPE,"
					+ "C_AMTPAIDAT,I_ESEVAID,I_GRCID,C_PAYMODE,VC_CHQDDCCBNO,VC_CRCARDNO,DT_CHQDDCCBDT,I_ASMTNO,I_DMNDNO,C_ISRLSD,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID) values ("
					+  getMaxCount("DB2INST1.pt_asmtrcpt_tbl", "I_SLNO", Ulbcode)
					+ ",0,"
					+ "'"
					+ "0"
					+ "',"
					+ "current date,current date,"
					+ "0,"
					+ assessmententity.getFixdPT()
					+ ",0.00,"
					+ assessmententity.getFixdLcs()
					+ ","
					+ assessmententity.getD_fxdunauthplnplty()
					+ ",0,0.00,0.00,0.00,0.00,0.00,0.00,"
					+ "'"
					+ PaidfromDate
					+ "',"
					+ // fromdate
					"'"
					+ PaidtoDate
					+ "',"
					+ // todate
					"0.00,'Current + SelfAsmt',"
					+ "'"
					+ "O"
					+ "'"
					+ // paidat
					",0,0,"
					+ "'"
					+ "I"
					+ "'"
					+ // paid mode
					",'"
					+ "0"
					+ "'"
					+ // check or dd number
					",'0'"
					+ ","
					+ "current date"
					+ // check date + "current date"
					","
					+ assessmententity.getAssessmentNo()
					+ ","
					+ getMaxCount("DB2INST1.pt_dmndrgtr_tbl", "I_DMNDNO",
							Ulbcode)
					+ ",'C',"
					+ assessmententity.getUlbcode()
					+ ",'N'," + "current timestamp" + ",1)";

			System.out.println(sql3 + "sql3 ");

			String sql4 = "insert into pt_asmtbal_tbl (I_SLNO,D_AREARAMT,D_CURRENTAMT,D_PLTYONARRS,D_ADVAMT,I_ASMTNO,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID) values ("
					+ getMaxCountForBalance("DB2INST1.pt_asmtbal_tbl",
							"I_SLNO", Ulbcode)
					+ ",0.00,0.00,0.00,0.00,"
					+ assessmententity.getAssessmentNo()
					+ ","
					+ assessmententity.getUlbcode()
					+ ",'N',"
					+ "current timestamp" + ",1)";

			System.out.println("sql4 " + sql4);
			/* Start get MeasureMent Details from Mysql Database */

			/* End get Unit Rate details from DB2 */
			
			
			// "+assessmententity.getNewapplicationId()+"
			/*String Sql5 = "Insert into pt_asmt_dtls_tbl (I_ASMTDTLSNO,I_ASMTNO,VC_CNSTUN,I_BLDGAGE,I_CLSCODE,I_BLDGUSECODE,I_OCPNTYPECODE,D_CAPVAL,I_FLRNO,D_LNTH,D_WDTH,D_PLNTAREA,"
					+ "I_UR,D_MRV,D_ARV,I_DEPCODE,D_BLDGVAL,D_SITEVAL,D_TARV,D_PT,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,DT_ADDDT,VC_BLDGPERSNO,DT_BLDPRMT,IS_PLANAPRVD) Values ("
					+ ""
					+ assessmententity.getNewapplicationId()
					+ ","
					+ assessmententity.getAssessmentNo()
					+ ",'NEW',"
					+ bage
					+ ","
					+ bcls
					+ ","
					+ busage
					+ ","
					+ octy
					+ ",0,"
					+ floorNo
					+ ","
					+ len
					+ ","
					+ wid
					+ ","
					+ assessmententity.getPlengthArea()
					+ ","
					+ ""
					+ assessmententity.getUnitRate()
					+ ","
					+ assessmententity.getMarv()
					+ ","
					+ assessmententity.getNArv()
					+ ",0,0,0,0,"
					+ assessmententity.getFixdPT()
					+ ","
					+ assessmententity.getUlbcode()
					+ ",'N',CURRENT_TIMESTAMP,1,'"
					+ bpd
					+ "','"
					+ bpNo
					+ "','"
					+ bpd
					+ "','"
					+ assessmententity.getPlanaprvl().charAt(0)
					+ "')";

			System.out.println("Sql5  " + Sql5);*/
			 
			Statement ps = conDB2.createStatement();
			ps.addBatch(sql1);
			ps.addBatch(sql2);
			//ps.addBatch(sql3);
			ps.addBatch(sql4);
			//ps.addBatch(Sql5);
			PreparedStatement ps1 = conDB2.prepareStatement(sql3);
			int Success[] = ps.executeBatch();
			ps1.executeUpdate();
			conDB2.close();
			insertIntoDtlstbl(assessmententity);
			//System.out.println((Success[0] == 1 && Success[1] == 1&& Success[2] == 1 && Success[3] == 1)+ "Success[0]==1 &&Success[1]==1  &&Success[2]==1  &&Success[3]==1");
			if (Success[0] == 1 && Success[1] == 1 && Success[2] == 1) { //&& Success[3] == 1 && Success[4] == 1
				//conDB2.commit();
				
				returnresult = "success";
			} else {
				returnresult = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("failed live insert" + e);
			returnresult = "fail";
		}
		   conDB2.close();

		System.out.println("return " + returnresult);
		return returnresult;
	}
	

	public synchronized static String insertIntoDtlstbl(AssessmentMaster assessmententity) throws SQLException {
		String returnresult = "";
		Connection con = MySqlDBConnection.getEpayDB();
		String distname = Dbcon.getULBName("" + assessmententity.getUlbcode());
		Connection conDB2 = Dbcon.getdbfromdist(distname);
		int appId = 0;
		Statement stmt = con.createStatement();
		Statement stmt1 = conDB2.createStatement();
		ResultSet rs=stmt1.executeQuery("select max(I_ASMTDTLSNO) from pt_asmt_dtls_tbl");
		while (rs.next()) {
			appId=rs.getInt(1);
		}
		ResultSet rs1=stmt.executeQuery("SELECT * FROM ptassessmenttax.ptassessment_master m1 ,ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id  and delflag='N' and m1.new_application_id="+assessmententity.getNewapplicationId());
		//PreparedStatement pstmt=conDB2.prepareStatement("Insert into pt_asmt_dtls_tbl values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		int k=0;
 		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
 		String bpd,rdd;
 		while (rs1.next()) {
 			//if(rs1.getDate(48)!=null && rs1.getDate(36)!=null){
 			if(rs1.getDate("B_PermisionDate")!=null && rs1.getDate("regdate")!=null){
 			//if( df.format(rs1.getDate(48))!=null &&  df.format(rs1.getDate(36))!=null){
 				bpd=df.format(rs1.getDate("B_PermisionDate"));
 			    rdd=df.format(rs1.getDate("regdate"));
 			}
 			else{
 				bpd="1900-01-01";
 			    rdd=df.format(rs1.getDate("regdate"));
 			}
 			//pstmt.setInt(1,rs1.getInt(1));
 			System.out.println(rs1.getInt(1));
 			String Sql5 = "Insert into pt_asmt_dtls_tbl (I_ASMTDTLSNO,I_ASMTNO,VC_CNSTUN,I_BLDGAGE,I_CLSCODE,I_BLDGUSECODE,I_OCPNTYPECODE,D_CAPVAL,I_FLRNO,D_LNTH,D_WDTH,D_PLNTAREA,"
					+ "I_UR,D_MRV,D_ARV,I_DEPCODE,D_BLDGVAL,D_SITEVAL,D_TARV,D_PT,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,DT_ADDDT,VC_BLDGPERSNO,DT_BLDPRMT,IS_PLANAPRVD) Values ("
					+ ""
					+ appId// rs1.getString(68)
					+ ","
					+ rs1.getString("AssessmentNo")
					+ ",'NEW',"
					+ rs1.getString("bage")
					+ ","
					+ rs1.getString("bcls")
					+ ","
					+ rs1.getString("busage")
					+ ","
					+ rs1.getString("octy")
					+ ",0,"
					+ rs1.getString("floorNo")
					+ ","
					+ rs1.getString("len")
					+ ","
					+ rs1.getString("wid")
					+ ","
					+rs1.getString("D_PLNTAREA") //rs1.getString(63)
					+ ","
					+ ""
					+ rs1.getString("I_UR")// rs1.getString(57)
					+ ","
					+ rs1.getString("D_MRV")// rs1.getString(53)
					+ ","
					+ rs1.getString("D_ARV") //// rs1.getString(54)
					+ ","
					+rs1.getString("I_DEPCODE")
					+ ","
					+rs1.getString("D_BLDGVAL")
					+ ","
					+rs1.getString("D_SITEVAL")
					+ ","
					+rs1.getString("D_TARV")
					+ ","
					+ rs1.getString("D_PT")//rs1.getString(51)
					+ ","
					+ rs1.getString("ulbcode")
					+ ",'N',CURRENT_TIMESTAMP,1,'"
					+ bpd
					+ "','"
					+ rs1.getString("B_PermisionNo")
					+ "','"
					+  bpd
					+ "','"
					+  rs1.getString("planAprvl").charAt(0)
					+ "')";
 			System.out.println(Sql5);
 			//PreparedStatement pstmt=conDB2.prepareStatement(Sql5);
 			int i=stmt1.executeUpdate(Sql5);

 			//k=Integer.parseInt(rs1.getString(1));
 			//k++;
 			appId++;
 			if (i == 1) { 
				returnresult = "success";
			} else {
				returnresult = "fail";
			}
 		}
 		conDB2.close();
 		con.close();
 		return returnresult;
	}

	/* PT Master table End For Insert */
	public synchronized static String getMaxCount(String table, String column,
			int ulbcode) throws SQLException {
		String distname = Dbcon.getULBName("" + ulbcode);
		Connection conDB2 = Dbcon.getdbfromdist(distname);
		// Connection conDB2 = Dbcon.getdbfromdist("TEST");
		/************ End Live Data Base connectoin ************/
		conDB2.setAutoCommit(false);
		String Max = "";
		try {
			String sql1 = "";
			// if(column=="" ||column.equalsIgnoreCase("")){
			sql1 = "select (max(" + column + ")+1) as max from " + table;
			// }else
			// sql1 = "select (max("+column+")+1) as max from " + table +
			// " where I_ULBOBJID="+ulbcode;
			System.out.println("sql1   " + sql1);
			PreparedStatement ps1 = conDB2.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Max = rs1.getString("max");
				// System.out.println("Max NUmber  " + Max);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		conDB2.commit();
		conDB2.close();
		return Max;
	}

	public synchronized static String getMaxCountForBalance(String table,
			String column, int ulbcode) throws SQLException {
		String distname = Dbcon.getULBName("" + ulbcode);
		Connection conDB2 = Dbcon.getdbfromdist(distname);
		/************ End Live Data Base connectoin ************/
		conDB2.setAutoCommit(false);
		String Max = "";
		try {
			String sql1 = "";
			sql1 = "select (max(" + column + ")+1) as max from " + table
					+ " where I_ULBOBJID=" + ulbcode;
			System.out.println("sql1   " + sql1);
			PreparedStatement ps1 = conDB2.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Max = rs1.getString("max");
			}
		} catch (Exception e) {
		}
		conDB2.commit();
		conDB2.close();
		return Max;
	}

    @Override
	public AssessmentMaster getrsinglerecord(long uniquerequestid) {

		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria criteria = session.createCriteria(AssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniquerequestid));
		System.out.println("uniquerequestid" + uniquerequestid);

		AssessmentMaster getsinglerecord = (AssessmentMaster) criteria
				.uniqueResult();

		return getsinglerecord;
	}

    
    
    
	@Override
	public boolean saveDet(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		boolean flag = false;

		try {
			System.out.println("visitor daoimpl");
			taxcalservicebean.setVisitorflag("V");
			String Uniqueseq = (taxcalservicebean.getI_ulbid() + Calendar
					.getInstance().getTimeInMillis());
			taxcalservicebean.setFilenumber(Uniqueseq);

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(taxcalservicebean);

			tx.commit();
			flag = true;

		} catch (Exception e) {
			System.out.println("e==" + e);
			System.err.println("e==" + e);
			flag = false;
		}
		return flag;

	}

	@Override
	public void save(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		sessionFactory.openSession().save(taxcalservicebean);
	}

	@Override
	public Ulbs getulbByCode(int ulbCode) {
		// TODO Auto-generated method stub
		Ulbs getulbname = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Ulbs.class);
			criteria.add(Restrictions.eq("ulbCode", ulbCode));
			getulbname = (Ulbs) criteria.uniqueResult();
		} catch (Exception exception) {

			exception.printStackTrace();
		}
		return getulbname;
	}

	@Override
	public boolean saveDtcpAppl(DtcpApplication dtcpApplication) {
		// TODO Auto-generated method stub
		boolean flag = false;

		try {
			// System.out.println("stsus =====");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(dtcpApplication);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/*
	 * new implementation
	 */

	@Override
	public boolean updateDtcpFlag(int id, long uniquerequestid) {
		boolean flag = false;

		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DtcpApplication d = (DtcpApplication) session.get(
					DtcpApplication.class, id);
			d.setEmas_status('N');
			d.setEmasRegNo(uniquerequestid);
			session.saveOrUpdate(d);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public String getDistCodeByUlbName(Integer ULB_CODE) {
		String districtCode = null;
		Districts d = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("select DISTRICT_ID FROM taxcal.ulbs where ULB_CODE="
							+ ULB_CODE + "");
			d = (Districts) query.list();
			districtCode = String.valueOf(d);
			System.out.println("districtCode==" + districtCode);
			transaction.commit();

		} catch (Exception exception) {

			exception.printStackTrace();
		}
		return districtCode;
	}

	@Override
	public List<Integer> getTotalVisitor() {
		List<Integer> Totalvisitor = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("select count(*) from taxcal.dtcp_services where delflag='N' and visitor_Flag='V' ");
			Totalvisitor = query.list();

			transaction.commit();

		} catch (Exception e) {
		}
		return Totalvisitor;
	}

	@Override
	public List<String> getPtCalculatorAllDistrictWiseDashboard() {
		List<String> districtList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("select s.districtcode,d.DISTRICT_NAME,u.ULB_NAME,u.ULB_CODE ,sum(s.estimatedtax) as amount , count(s.districtcode)as visitrocount FROM taxcal.dtcp_services s"
							+ " Inner Join taxcal.districts d on s.districtcode=d.DISTRICT_ID inner join taxcal.ulbs u on s.ulbid=u.ULB_CODE"
							+ " where s.delflag='N' and s.visitor_Flag='V' group by s.districtcode");
			districtList = query.list();

			transaction.commit();

		} catch (Exception e) {
		}

		return districtList;
	}

	@Override
	public List<String> getPtCalculatorAllUlbWiseDashboard() {
		List<String> ulbList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("select s.districtcode,d.DISTRICT_NAME,u.ULB_NAME,u.ULB_CODE ,sum(s.estimatedtax) as amount , count(s.districtcode)as visitrocount FROM taxcal.dtcp_services s"
							+ " Inner Join taxcal.districts d on s.districtcode=d.DISTRICT_ID inner join taxcal.ulbs u on s.ulbid=u.ULB_CODE"
							+ " where s.delflag='N' and s.visitor_Flag='V' group by s.ulbid");
			ulbList = query.list();

			transaction.commit();

		} catch (Exception e) {
		}

		return ulbList;
	}

	@Override
	public List<String> getPtCalculatorUlbWiseDashboard(String districtcode) {

		List<String> districtulbList = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("select s.districtcode,d.DISTRICT_NAME,u.ULB_NAME,u.ULB_CODE ,sum(s.estimatedtax) as amount , count(s.districtcode)as visitrocount FROM taxcal.dtcp_services s"
							+ " Inner Join taxcal.districts d on s.districtcode=d.DISTRICT_ID inner join taxcal.ulbs u on s.ulbid=u.ULB_CODE"
							+ " where s.delflag='N' and s.visitor_Flag='V' and d.DISTRICT_ID="
							+ districtcode + " group by u.ULB_NAME");
			districtulbList = query.list();

			transaction.commit();

		} catch (Exception e) {
		}
		return districtulbList;
	}

	@Override
	public PaymentTransaction inserttransaction(long uniqReqNumber,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		// String result = "fail";
		PaymentTransaction paymentTransactionEntity = new PaymentTransaction();
		AssessmentMaster ptAssessmentMaster = getrsinglerecord(uniqReqNumber);
		System.out.println("ptAssessmentMaster" + ptAssessmentMaster);
		paymentTransactionEntity.setGateway_name("BILL DESK");
		if (paymentTransactionEntity.getGateway_name() == "BILL DESK") {
			// paymentTransactionEntity.setTransactionmode("I");

			paymentTransactionEntity.setC_AMTPAIDAT("O");
		}
		paymentTransactionEntity.setTotal_amount(ptAssessmentMaster
				.getPaymentamount());
		paymentTransactionEntity.setPaid_update_flag('N');

		paymentTransactionEntity.setReceipt_owner_door_no(ptAssessmentMaster
				.getOwnerDoorNo());
		paymentTransactionEntity.setReceipt_owner_name(ptAssessmentMaster
				.getOwnerSurName().concat(" ")
				.concat(ptAssessmentMaster.getOwnerName()));
		paymentTransactionEntity
				.setTransaction_mobile_number(ptAssessmentMaster
						.getOwnerMobile());
		paymentTransactionEntity.setTransaction_flag('N');
		paymentTransactionEntity.setUlbcode(ptAssessmentMaster.getUlbcode());
		paymentTransactionEntity.setUniqueRequestNumber(ptAssessmentMaster
				.getUniqueRequestNumber());
		paymentTransactionEntity.setC_delflag('N');
		paymentTransactionEntity.setTransdate(new java.util.Date());

		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity
				.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());

		paymentTransactionEntity.setUlbname(ulbs.getUlbName());

		Session session = sessionFactory.getCurrentSession();

		session.save(paymentTransactionEntity);
		return paymentTransactionEntity;
	}

	public int getcountfromlive(AssessmentMaster ptAssessmentMaster) {
		int i = 0;

		try {

			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(""
					+ ptAssessmentMaster.getUlbcode());
			Connection con  = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// con = Dbcon.getdbfromdist("TEST");
			/************ End Live Data Base connectoin ************/
			String sql = "select count(*) as count from MESEVA_SUVIDA_INT_USR_DATA_TAB where DOCTYPE like '%NEWASSESMENT%' and  APPREQNO='"
					+ ptAssessmentMaster.getUniqueRequestNumber()
					+ "' and ULBID=" + ptAssessmentMaster.getUlbcode() + "";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt("count");

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} /*
		 * finally { try { con.close(); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */ 

		return i;
	}

	@Override
	public void updaterecord(AssessmentMaster ptAssessmentMaster) {
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		session.load(AssessmentMaster.class,
				ptAssessmentMaster.getNewapplicationId());
		session.merge(ptAssessmentMaster);
		tx1.commit();
		session.close();
	}

	@Override
	public void updatPaymentAmount(double amount, String Reqnumber) {
		Session session = null;
		Transaction transaction = null;

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		Query query = session
				.createQuery("update AssessmentMaster set paymentamount="
						+ amount + " where uniqueRequestNumber=" + Reqnumber);
		query.executeUpdate();
		transaction.commit();
	}

	/*
	 * public Double getMesurement() { Session session =
	 * sessionFactory.getCurrentSession(); String sql=
	 * " SELECT len*wid from ptassessmenttax.ptasmtmeasurementmaster where measurement_id="
	 * ;
	 * 
	 * 
	 * String sql=
	 * "SELECT sum(m.len*m.wid) as sum from ptassessmenttax.ptasmtmeasurementmaster m , ptassessmenttax.ptassessment_master a where m.asmt_appl_id=a.new_application_id"
	 * +
	 * " and  a.uniqueRequestNumber="+asmtMasterBean.getUniqueRequestNumber()+""
	 * ;
	 * 
	 * 
	 * Query userQuery = session.createSQLQuery(sql); String
	 * l=userQuery.list().toString(); System.out.println("l="+l); return
	 * Double.parseDouble(l.replace("[","").replace("]", "")); }
	 */

	@Override
	public Double getTotalPtSelfAssessmentTax(AssessmentMaster asmtMasterBean)
			throws SQLException {
		/* new implementation */
		Connection con = MySqlDBConnection.getEpayDB();

		@SuppressWarnings("unused")
		String measureId = null;
		// Double area=0.0;
		PreparedStatement stmt = null;
		String Sql = "select m.measurement_id,m.len*wid,m.planAprvl as plan,a.ulbcode,a.zone,m.bcls,a.locality,m.bage,m.octy,m.busage,m.cPlinth,m.usageType from ptassessmenttax.ptasmtmeasurementmaster m , ptassessmenttax.ptassessment_master a where m.asmt_appl_id=a.new_application_id and a.uniqueRequestNumber="
				+ asmtMasterBean.getUniqueRequestNumber();
		Double d = 0.0;
		String plan = "";
		Double area = 0.0;
		String ulbcode = "";
		String zone = "";
		String bcls = "";
		String bage = "";
		String locality = "";
		String octy = "";
		String busage = "";
		String cPlinth="";
		String usageType="";
		stmt = con.prepareStatement(Sql);
		ResultSet rs9 = stmt.executeQuery();
		int count = 0;
		while (rs9.next()) {
			measureId = rs9.getString("measurement_id");
			AsmtMeasurementMaster asmtMeasurementMaster=assessmentservice.getSingleRecordForMeasurement(Long.parseLong(measureId));
			plan = rs9.getString("plan");
			area = rs9.getDouble(2);
			ulbcode = rs9.getString("ulbcode");
			zone = rs9.getString("zone");
			bcls = rs9.getString("bcls");
			bage = rs9.getString("bage");
			octy = rs9.getString("octy");
			busage = rs9.getString("busage");
			locality = rs9.getString("locality");
			cPlinth=rs9.getString("cPlinth");
			usageType=rs9.getString("usageType");
			System.out.println("plan=" + plan);
			System.out.println("area=" + area);
			d += getTotal(asmtMasterBean,asmtMeasurementMaster, plan, area, ulbcode, zone, bcls,
					bage, locality, octy, busage,cPlinth,usageType);
			count++;
			System.out.println("Count ::::" + count);
			System.out.println("tax== : " + d);
			assessmentservice.save(asmtMeasurementMaster);
		}
		/* end new implementation */
		return d;

	}
	/* This method for 75 Sqr Metrs  */
	@Override
	public Double getTotalPtSelfAssessmentTaxFor_75SqrYards(
			AssessmentMaster assessmentMaster) throws SQLException {
		/* new implementation */
		Connection con = MySqlDBConnection.getEpayDB();

		@SuppressWarnings("unused")
		String measureId = null;
		// Double area=0.0;
		PreparedStatement stmt = null;
		String Sql = "select m.measurement_id,m.len*wid,m.planAprvl as plan,a.ulbcode,a.zone,m.bcls,a.locality,m.bage,m.octy,m.busage,m.cPlinth,m.usageType,m.floorNo from ptassessmenttax.ptasmtmeasurementmaster m , ptassessmenttax.ptassessment_master a where m.asmt_appl_id=a.new_application_id and a.uniqueRequestNumber="
				+ assessmentMaster.getUniqueRequestNumber();
		Double d = 0.0;
		String plan = "";
		Double area = 0.0;
		String ulbcode = "";
		String zone = "";
		String bcls = "";
		String bage = "";
		String locality = "";
		String octy = "";
		String busage = "";
		String cPlinth="";
		String usageType="";
		String floorNo="";
		stmt = con.prepareStatement(Sql);
		ResultSet rs9 = stmt.executeQuery();
		int count = 0;
		while (rs9.next()) {
			measureId = rs9.getString("measurement_id");
			AsmtMeasurementMaster asmtMeasurementMaster=assessmentservice.getSingleRecordForMeasurement(Long.parseLong(measureId));
			plan = rs9.getString("plan");
			area = rs9.getDouble(2);
			ulbcode = rs9.getString("ulbcode");
			zone = rs9.getString("zone");
			bcls = rs9.getString("bcls");
			bage = rs9.getString("bage");
			octy = rs9.getString("octy");
			busage = rs9.getString("busage");
			locality = rs9.getString("locality");
			cPlinth=rs9.getString("cPlinth");
			usageType=rs9.getString("usageType");
			floorNo=rs9.getString("floorNo");
			System.out.println("plan=" + plan);
			System.out.println("area=" + area);
			d += getTotalFor_75SqrYards(assessmentMaster,asmtMeasurementMaster, plan, area, ulbcode, zone, bcls,
					bage, locality, octy, busage,cPlinth,usageType,floorNo);
			count++;
			System.out.println("Count ::::" + count);
			System.out.println("tax== : " + d);
			assessmentservice.save(asmtMeasurementMaster);
		}
		/* end new implementation */
		return d;

	}
	public Double getTotal(AssessmentMaster asmtMasterBean,AsmtMeasurementMaster asmtMeasurementMaster, String plan,
			Double area, String ulbcode, String zone, String bcls, String bage,
			String locality1, String octy, String busage,String cPlinth,String usageType) {
		PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6 = null, ps7 = null, ps8 = null;
		ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null, rs7 = null, rs8 = null;

		double pt = 0.0, lcs = 0.0, et = 0.0, uau = 0.0, tnarv = 0.0;
		try {

			// Set<AsmtMeasurementMaster> amSet = new HashSet<>();
			/*
			 * String[] bcls = asmtMasterBean.getBcls().split(","); String[]
			 * busage = asmtMasterBean.getBusage().split(","); String[] ocity =
			 * asmtMasterBean.getOcty().split(","); String[] bage =
			 * asmtMasterBean.getBage().split(","); String[] wid =
			 * asmtMasterBean.getWid().split(","); String[] len =
			 * asmtMasterBean.getLen().split(",");
			 */
			// for (int i = 0; i < bcls.length; i++) {

			if (!(bcls.equalsIgnoreCase("")) && !(busage.equalsIgnoreCase(""))
					&& !(octy.equalsIgnoreCase(""))) {
				String i_ulbid = ulbcode;// asmtMasterBean.getUlbcode().toString();
				String planaprvl = plan;// ""+asmtMasterBean.getPlanaprvl();
				int i_zoneobjid = Integer.parseInt(zone);// asmtMasterBean.getZone();
				int i_clscode = Integer.parseInt(bcls);
				int i_bldgusecode = Integer.parseInt(busage);
				int i_lctyobjid = Integer.parseInt(locality1);
				int i_ocpntypecode = Integer.parseInt(octy);

				// double l = Double.parseDouble(len[i]);
				// double w = Double.parseDouble(wid[i]);
				int buildage = Integer.parseInt(bage);

				// HttpSession s = request.getSession(true);
				double d_ur = 0.0;
				double D_DEPRATE = 0.0;
				int I_DEPRATECODE = 0;
				double ptResrate = 0, ptNResrate = 0;

				int ii_bldgage = 0;
				double d_plntarea = 0.0;
				double lcsp = 0, etp = 0, uaup = 0;
				// int i_bldgage = 0, flrno = 0;
				// String IS_PLANAPRVD="";
				// double ptf = 0, etf = 0, lcsf = 0, uauf = 0;
				// double D_FXDARV = 0.0, D_FXDPT = 0.0, D_FXDED = 0, D_FXDLCS =
				// 0, D_FXDUNAUTHPLNPLTY = 0, TOTTAX = 0.0;

				String blduse = null, ptname = null, c_resflag = null;
				String C_SPRSTRU = null;
				Date aplnDate = null;
				String zonename = null;
				String clsname = null;
				String useagename = null;
				String ocptype = null;
				String ULBname = null;
				String Dist = null;
				String locality = null;
				/************ Live Data Base connectoin ************/
				String distname = Dbcon.getULBName(i_ulbid);
				con = Dbcon.getdbfromdist(distname);
				// System.out.println("District Name from ULB code"+distname);
				/************ Test Data Base connectoin ************/
				// i_ulbid="1056";
				// con = Dbcon.getdbfromdist("TEST");
				/*** ********* End Live Data Base connectoin ************/

				// d_plntarea = l * w;
				d_plntarea = area;// CalculateTax.getMeasurement(asmtMasterBean.getUniqueRequestNumber());
                double SenctionPlinth=Double.parseDouble(cPlinth);
                double CalculatedDeviation=Math.round((d_plntarea-SenctionPlinth)/SenctionPlinth*100);
                double deviation=10;
                String busageType=usageType;
                System.out.println("CalculatedDeviation::::: "+CalculatedDeviation);
                System.out.println("SenctionPlinth  :::::::::::" +SenctionPlinth);
				sql6 = "select VC_ZONEDESC from ct_zone_mstr_tbl where I_ZONEOBJID="
						+ i_zoneobjid + " and I_ULBOBJID=" + i_ulbid;
				ps6 = con.prepareStatement(sql6);
				rs6 = ps6.executeQuery();
				while (rs6.next()) {
					zonename = rs6.getString("VC_ZONEDESC");
				}
				sql7 = "select I_CLSCODE,VC_CLSDESC from pt_bldgcls_mstr_tbl  where   C_DELFLAG='N' and  I_CLSCODE="
						+ i_clscode + " and I_ULBOBJID=" + i_ulbid;

				ps7 = con.prepareStatement(sql7);
				rs7 = ps7.executeQuery();
				while (rs7.next()) {
					clsname = rs7.getString("VC_CLSDESC");
				}

				sql8 = " select I_OCPNTYPECODE,VC_OCPNTYPEDESC from pt_ocpntype_mstr_tbl where c_delFlag='N' and  I_OCPNTYPECODE="
						+ i_ocpntypecode;
				ps8 = con.prepareStatement(sql8);
				rs8 = ps8.executeQuery();
				while (rs8.next()) {
					ocptype = rs8.getString("VC_OCPNTYPEDESC");
				}

				sql9 = "select a.VC_ULBNAME as ulbname,b.VC_DSTNAME as distname from CT_ULB_MSTR_TBL a,CT_DST_MSTR_TBL b where a.I_DSTOBJID=b.I_DSTOBJID and a.I_ULBOBJID="
						+ i_ulbid;
				ps9 = con.prepareStatement(sql9);
				rs9 = ps9.executeQuery();
				while (rs9.next()) {
					Dist = rs9.getString("distname");
					ULBname = rs9.getString("ulbname");
				}

				sql10 = " select I_LCTYOBJID,VC_LCTYNAME from  CT_LCTY_MSTR_TBL where c_delFlag='N' and I_LCTYOBJID="
						+ i_lctyobjid + " and    I_ULBOBJID=" + i_ulbid;
				ps10 = con.prepareStatement(sql10);
				rs10 = ps10.executeQuery();
				while (rs10.next()) {

					locality = rs10.getString("VC_LCTYNAME");
				}
				sql = "select d_ur from Pt_ur_mstr_tbl where i_zoneobjid="
						+ i_zoneobjid + " and i_clscode=" + i_clscode
						+ " and i_bldgusecode=" + i_bldgusecode
						+ " and i_ulbobjid=" + i_ulbid + " and c_delflag='N'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) {
					d_ur = rs.getDouble("d_ur");
				}
				sql1 = "select D_PERREBONUR,D_FXDREBONUR from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
						+ i_ulbid;

				ps1 = con.prepareStatement(sql1);
				rs1 = ps1.executeQuery();

				if (rs1.next()) {
					if (rs1.getDouble("D_PERREBONUR") != 0) {
						d_ur = d_ur
								- (d_ur * (rs1.getDouble("D_PERREBONUR") / 100));
					} else if (rs1.getDouble("D_FXDREBONUR") != 0) {
						d_ur = d_ur - rs1.getDouble("D_FXDREBONUR");
					}
				}
				// System.out.println("Unit Rate " + d_ur);
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);

				sql2 = "select D_DEPRATE,I_DEPRATECODE from  PT_DEPRATE_MSTR_TBL  WHERE  I_ULBOBJID="
						+ i_ulbid
						+ " and  I_FROMBLDGAGE<="
						+ buildage
						+ " and I_TOBLDGAGE>="
						+ buildage
						+ " and C_DELFLAG='N'and I_OCPNTYPECODE="
						+ i_ocpntypecode;
				ps2 = con.prepareStatement(sql2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					D_DEPRATE = rs2.getDouble("D_DEPRATE");
					I_DEPRATECODE = rs2.getInt("I_DEPRATECODE");
				}
				sql3 = "select C_SEESHOREFLG,D_SEESHOREPER  from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
						+ i_ulbid;
				ps3 = con.prepareStatement(sql3);
				rs3 = ps3.executeQuery();
				if (rs3.next()) {
					if (rs3.getString("C_SEESHOREFLG").equals("Y")) {
						D_DEPRATE = D_DEPRATE + rs3.getDouble("D_SEESHOREPER");
					}
				}

				sql4 = "select i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and i_bldgusecode="
						+ i_bldgusecode + " and I_ULBOBJID=" + i_ulbid;
				ps4 = con.prepareStatement(sql4);
				rs4 = ps4.executeQuery();
				if (rs4.next()) {
					useagename = rs4.getString("VC_BLDGUSEDESC");
					blduse = rs4.getString("VC_BLDGUSETYPE");
					c_resflag = rs4.getString("c_resflag");
				}

				if (blduse.equalsIgnoreCase("Residence")) {
					ptname = "Property Tax For Residence";
				} else {
					ptname = "Property Tax For Non Residence";
				}

				sql5 = "select VC_TAXRATETYPE,D_TAXRATE  from pt_taxrate_mstr_tbl where  i_ulbobjid="
						+ i_ulbid + " and c_delflag='N'";
				ps5 = con.prepareStatement(sql5);
				rs5 = ps5.executeQuery();
				while (rs5.next()) {
					if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
							"Property Tax For Residence")) {
						ptResrate = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Property Tax For Non Residence")) {
						ptNResrate = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Education Tax")) {
						etp = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Library Cess")) {
						lcsp = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Penalty on UA Construction")) {
						uaup = rs5.getDouble("D_TAXRATE");
					}
				}

				double MRV = Math.round(d_ur * d_plntarea);

				double BV = Math.round(MRV * (2.0 / 3.0));
				// System.out.println("BV " + BV);
				double SV = Math.round(MRV * (1.0 / 3.0));
				// System.out.println("SV " + SV);
				double NARV = 0.0;
				double GARV = Math.round((BV) * 12.0);
				double DEP = Math.round(GARV * (D_DEPRATE / 100));
				// System.out.println("MRV " + MRV + " GARV " + GARV + " DEP " +
				// DEP);
				NARV = (SV) * (12.0) + (GARV - DEP);
                //System.out.println("blduse ::::::::::"+blduse);
                String comercial="Commercial";
                String nonResidence="";
                //if(blduse!="Residence"==false){
                if(!blduse.equals("Residence")){
                	blduse=blduse.replace(blduse, comercial);
                	nonResidence=blduse;
                	/* System.out.println("nonResidence :::" +nonResidence);
                	 System.out.println("blduse@@@@@@ :::" +blduse);*/
                }
                //nonResidence.compareTo(busageType)!=0
                if (planaprvl.equalsIgnoreCase("Y")) {
				if (blduse.compareTo(busageType)!=0){
					/*System.out.println("blduse::::"+blduse +" nonResidence:: "+nonResidence);
					System.out.println("busageType:::::"+busageType);*/
					uaup=100;
					}
				else{
					
				 if(d_plntarea>SenctionPlinth && CalculatedDeviation>deviation){
                	 uaup = 50; 
                 }
				 if(d_plntarea>SenctionPlinth && (CalculatedDeviation==deviation||CalculatedDeviation<deviation)){
                	 uaup = 25; 
                 }
                 if(d_plntarea<SenctionPlinth||d_plntarea==SenctionPlinth){
                	 uaup=0;
                 }
					}
				}
				if (planaprvl.equalsIgnoreCase("N")) {

					uaup = 100;
				}/*
				 * else { uaup = 100; }
				 */

			 System.out.println("planaprvl " + planaprvl + " UAC " +
				 uaup);

				// System.out.println("res>>" + c_resflag);

				if (c_resflag.equalsIgnoreCase("Y")) {
					pt = (Math.round(NARV) * (ptResrate / 100));
				} else if (c_resflag.equalsIgnoreCase("N")) {
					pt = (Math.round(NARV) * (ptNResrate / 100));
				}

				 System.out.println("ptNResrate " + ptNResrate + "NARV " +
				 NARV);
				et = (Math.round(NARV) * (etp / 100));
				lcs = (Math.round((pt + et)) * (lcsp / 100));
				uau = Math.round((pt + et + lcs) * (uaup / 100));

				 System.out.println("Lcs " + lcs);
				 System.out.println("Uac " + uau + " pt " + pt);
				 
				tnarv += NARV;
				System.out.println("GARV==>"+GARV);
				asmtMeasurementMaster.setD_ARV(GARV);
				asmtMeasurementMaster.setD_MRV(MRV);
				asmtMeasurementMaster.setD_PLNTAREA(d_plntarea);
				asmtMeasurementMaster.setI_UR(d_ur);
				asmtMeasurementMaster.setD_PT(pt);
				asmtMeasurementMaster.setD_TARV(NARV);
				asmtMeasurementMaster.setD_SITEVAL(SV);
				asmtMeasurementMaster.setD_BLDGVAL(BV);
				asmtMeasurementMaster.setD_UAC(uau);
				asmtMeasurementMaster.setI_DEPCODE(I_DEPRATECODE);
				if(asmtMasterBean.getPlengthArea()==null)asmtMasterBean.setPlengthArea(0d);
				asmtMasterBean.setNArv(Math.round(tnarv)+asmtMasterBean.getNArv());
				asmtMasterBean.setFixdLcs(Math.round(lcs)+asmtMasterBean.getFixdLcs());
				asmtMasterBean.setFixdPT(Math.round(pt)+asmtMasterBean.getFixdPT());
				asmtMasterBean.setD_fxdunauthplnplty(Math.round(uau)+asmtMasterBean.getD_fxdunauthplnplty());
				asmtMasterBean.setPlengthArea(d_plntarea+asmtMasterBean.getPlengthArea());
				asmtMasterBean.setMarv(MRV+asmtMasterBean.getMarv());
				//asmtMasterBean.setUnitRate(d_ur+asmtMasterBean.getUnitRate());
				pt = Math.round(pt + lcs + uau);
				// System.out.println("d : " +d_ur);
				System.out.println("--------*********@@@@@@@@@--------" + pt);
			}

		} catch (Exception e) {
			System.out.println("e " + e);
			e.printStackTrace();
			// return new ModelAndView("gettax");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * finally {
		 * 
		 * ps.close(); ps1.close(); ps2.close(); ps3.close(); ps4.close();
		 * ps5.close(); ps6.close(); ps7.close(); ps8.close(); rs.close();
		 * rs1.close(); rs2.close(); rs3.close(); rs4.close(); rs5.close();
		 * rs6.close(); rs7.close(); rs8.close(); con.close(); }
		 */
		return pt;

	}
	public Double getTotalFor_75SqrYards(AssessmentMaster asmtMasterBean,AsmtMeasurementMaster asmtMeasurementMaster, String plan,
			Double area, String ulbcode, String zone, String bcls, String bage,
			String locality1, String octy, String busage,String cPlinth,String usageType, String floorNo) {
		PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6 = null, ps7 = null, ps8 = null;
		ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null, rs7 = null, rs8 = null;

		double pt = 0.0, lcs = 0.0, et = 0.0, uau = 0.0, tnarv = 0.0;
		try {
			if (!(bcls.equalsIgnoreCase("")) && !(busage.equalsIgnoreCase(""))
					&& !(octy.equalsIgnoreCase(""))) {
				String i_ulbid = ulbcode;// asmtMasterBean.getUlbcode().toString();
				String planaprvl = plan;// ""+asmtMasterBean.getPlanaprvl();
				int i_zoneobjid = Integer.parseInt(zone);// asmtMasterBean.getZone();
				int i_clscode = Integer.parseInt(bcls);
				int i_bldgusecode = Integer.parseInt(busage);
				int i_lctyobjid = Integer.parseInt(locality1);
				int i_ocpntypecode = Integer.parseInt(octy);
				System.out.println("i_ocpntypecode ::"+i_ocpntypecode);
				int buildage = Integer.parseInt(bage);
				double d_ur = 0.0;
				double D_DEPRATE = 0.0;
				int I_DEPRATECODE = 0;
				double ptResrate = 0, ptNResrate = 0;
				int ii_bldgage = 0;
				double d_plntarea = 0.0;
				double lcsp = 0, etp = 0, uaup = 0;
				String blduse = null, ptname = null, c_resflag = null;
				String C_SPRSTRU = null;
				Date aplnDate = null;
				String zonename = null;
				String clsname = null;
				String useagename = null;
				String ocptype = null;
				String ULBname = null;
				String Dist = null;
				String locality = null;
				/************ Live Data Base connectoin ************/
				String distname = Dbcon.getULBName(i_ulbid);
				con = Dbcon.getdbfromdist(distname);
				/************ Test Data Base connectoin ************/
				/*** ********* End Live Data Base connectoin ************/

				d_plntarea = area;// CalculateTax.getMeasurement(asmtMasterBean.getUniqueRequestNumber());
				System.out.println("d_plntarea::::::::" + d_plntarea);
                /*double SenctionPlinth=Double.parseDouble(cPlinth);
                double CalculatedDeviation=Math.round((d_plntarea-SenctionPlinth)/SenctionPlinth*100);
                double deviation=10;
                String busageType=usageType;
                System.out.println("CalculatedDeviation::::: "+CalculatedDeviation);
                System.out.println("SenctionPlinth  :::::::::::" +SenctionPlinth);*/
				sql6 = "select VC_ZONEDESC from ct_zone_mstr_tbl where I_ZONEOBJID="
						+ i_zoneobjid + " and I_ULBOBJID=" + i_ulbid;
				ps6 = con.prepareStatement(sql6);
				rs6 = ps6.executeQuery();
				while (rs6.next()) {
					zonename = rs6.getString("VC_ZONEDESC");
				}
				sql7 = "select I_CLSCODE,VC_CLSDESC from pt_bldgcls_mstr_tbl  where   C_DELFLAG='N' and  I_CLSCODE="
						+ i_clscode + " and I_ULBOBJID=" + i_ulbid;

				ps7 = con.prepareStatement(sql7);
				rs7 = ps7.executeQuery();
				while (rs7.next()) {
					clsname = rs7.getString("VC_CLSDESC");
				}

				sql8 = " select I_OCPNTYPECODE,VC_OCPNTYPEDESC from pt_ocpntype_mstr_tbl where c_delFlag='N' and  I_OCPNTYPECODE="
						+ i_ocpntypecode;
				ps8 = con.prepareStatement(sql8);
				rs8 = ps8.executeQuery();
				while (rs8.next()) {
					ocptype = rs8.getString("VC_OCPNTYPEDESC");
				}

				sql9 = "select a.VC_ULBNAME as ulbname,b.VC_DSTNAME as distname from CT_ULB_MSTR_TBL a,CT_DST_MSTR_TBL b where a.I_DSTOBJID=b.I_DSTOBJID and a.I_ULBOBJID="
						+ i_ulbid;
				ps9 = con.prepareStatement(sql9);
				rs9 = ps9.executeQuery();
				while (rs9.next()) {
					Dist = rs9.getString("distname");
					ULBname = rs9.getString("ulbname");
				}

				sql10 = " select I_LCTYOBJID,VC_LCTYNAME from  CT_LCTY_MSTR_TBL where c_delFlag='N' and I_LCTYOBJID="
						+ i_lctyobjid + " and    I_ULBOBJID=" + i_ulbid;
				ps10 = con.prepareStatement(sql10);
				rs10 = ps10.executeQuery();
				while (rs10.next()) {

					locality = rs10.getString("VC_LCTYNAME");
				}
				sql = "select d_ur from Pt_ur_mstr_tbl where i_zoneobjid="
						+ i_zoneobjid + " and i_clscode=" + i_clscode
						+ " and i_bldgusecode=" + i_bldgusecode
						+ " and i_ulbobjid=" + i_ulbid + " and c_delflag='N'";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) {
					d_ur = rs.getDouble("d_ur");
				}
				sql1 = "select D_PERREBONUR,D_FXDREBONUR from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
						+ i_ulbid;

				ps1 = con.prepareStatement(sql1);
				rs1 = ps1.executeQuery();

				if (rs1.next()) {
					if (rs1.getDouble("D_PERREBONUR") != 0) {
						d_ur = d_ur
								- (d_ur * (rs1.getDouble("D_PERREBONUR") / 100));
					} else if (rs1.getDouble("D_FXDREBONUR") != 0) {
						d_ur = d_ur - rs1.getDouble("D_FXDREBONUR");
					}
				}
				// System.out.println("Unit Rate " + d_ur);
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);

				sql2 = "select D_DEPRATE,I_DEPRATECODE from  PT_DEPRATE_MSTR_TBL  WHERE  I_ULBOBJID="
						+ i_ulbid
						+ " and  I_FROMBLDGAGE<="
						+ buildage
						+ " and I_TOBLDGAGE>="
						+ buildage
						+ " and C_DELFLAG='N'and I_OCPNTYPECODE="
						+ i_ocpntypecode;
				ps2 = con.prepareStatement(sql2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					D_DEPRATE = rs2.getDouble("D_DEPRATE");
					I_DEPRATECODE = rs2.getInt("I_DEPRATECODE");
				}
				sql3 = "select C_SEESHOREFLG,D_SEESHOREPER  from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
						+ i_ulbid;
				ps3 = con.prepareStatement(sql3);
				rs3 = ps3.executeQuery();
				if (rs3.next()) {
					if (rs3.getString("C_SEESHOREFLG").equals("Y")) {
						D_DEPRATE = D_DEPRATE + rs3.getDouble("D_SEESHOREPER");
					}
				}

				sql4 = "select i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and i_bldgusecode="
						+ i_bldgusecode + " and I_ULBOBJID=" + i_ulbid;
				ps4 = con.prepareStatement(sql4);
				rs4 = ps4.executeQuery();
				if (rs4.next()) {
					useagename = rs4.getString("VC_BLDGUSEDESC");
					blduse = rs4.getString("VC_BLDGUSETYPE");
					c_resflag = rs4.getString("c_resflag");
				}

				if (blduse.equalsIgnoreCase("Residence")) {
					ptname = "Property Tax For Residence";
				} else {
					ptname = "Property Tax For Non Residence";
				}

				sql5 = "select VC_TAXRATETYPE,D_TAXRATE  from pt_taxrate_mstr_tbl where  i_ulbobjid="
						+ i_ulbid + " and c_delflag='N'";
				ps5 = con.prepareStatement(sql5);
				rs5 = ps5.executeQuery();
				while (rs5.next()) {
					if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
							"Property Tax For Residence")) {
						ptResrate = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Property Tax For Non Residence")) {
						ptNResrate = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Education Tax")) {
						etp = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Library Cess")) {
						lcsp = rs5.getDouble("D_TAXRATE");
					} else if (rs5.getString("VC_TAXRATETYPE")
							.equalsIgnoreCase("Penalty on UA Construction")) {
						uaup = rs5.getDouble("D_TAXRATE");
					}
				}
				System.out.println("ocptype"+ ocptype);
				System.out.println("Before G+1");
				if(ocptype.equalsIgnoreCase("Owner") && d_plntarea<=62.7 && (1==Integer.parseInt(floorNo)||0==Integer.parseInt(floorNo))){
				double MRV = Math.round(d_ur * d_plntarea);
				double BV = Math.round(MRV * (2.0 / 3.0));
				// System.out.println("BV " + BV);
				double SV = Math.round(MRV * (1.0 / 3.0));
				// System.out.println("SV " + SV);
				double NARV = 0.0;
				double GARV = Math.round((BV) * 12.0);
				double DEP = Math.round(GARV * (D_DEPRATE / 100));
				// System.out.println("MRV " + MRV + " GARV " + GARV + " DEP " +
				// DEP);
				NARV = (SV) * (12.0) + (GARV - DEP);
               
                System.out.println("planaprvl " + planaprvl + " UAC " + uaup);


				if (c_resflag.equalsIgnoreCase("Y")) {
					pt = (Math.round(NARV) * (ptResrate / 100));
				} else if (c_resflag.equalsIgnoreCase("N")) {
					pt = (Math.round(NARV) * (ptNResrate / 100));
				}

				System.out.println("ptNResrate " + ptNResrate + "NARV " + NARV);
				et = (Math.round(NARV) * (etp / 100));
				lcs = (Math.round((pt + et)) * (lcsp / 100));
				uau = Math.round((pt + et + lcs) * (uaup / 100));

				 System.out.println("Lcs " + lcs);
				 System.out.println("Uac " + uau + " pt " + pt);
				 
				tnarv += NARV;
				System.out.println("GARV==>"+GARV);
				asmtMeasurementMaster.setD_ARV(GARV);
				asmtMeasurementMaster.setD_MRV(MRV);
				asmtMeasurementMaster.setD_PLNTAREA(d_plntarea);
				asmtMeasurementMaster.setI_UR(d_ur);
				asmtMeasurementMaster.setD_PT(pt);
				asmtMeasurementMaster.setD_TARV(NARV);
				asmtMeasurementMaster.setD_SITEVAL(SV);
				asmtMeasurementMaster.setD_BLDGVAL(BV);
				asmtMeasurementMaster.setD_UAC(uau);
				asmtMeasurementMaster.setI_DEPCODE(I_DEPRATECODE);
				if(asmtMasterBean.getPlengthArea()==null)asmtMasterBean.setPlengthArea(0d);
				asmtMasterBean.setNArv(Math.round(tnarv)+asmtMasterBean.getNArv());
				asmtMasterBean.setFixdLcs(Math.round(lcs)+asmtMasterBean.getFixdLcs());
				asmtMasterBean.setFixdPT(Math.round(pt)+asmtMasterBean.getFixdPT());
				asmtMasterBean.setD_fxdunauthplnplty(Math.round(uau)+asmtMasterBean.getD_fxdunauthplnplty());
				asmtMasterBean.setPlengthArea(d_plntarea+asmtMasterBean.getPlengthArea());
				asmtMasterBean.setMarv(MRV+asmtMasterBean.getMarv());
				//asmtMasterBean.setUnitRate(d_ur+asmtMasterBean.getUnitRate());
				pt = Math.round(pt + lcs + uau);
				pt=100;
				// System.out.println("d : " +d_ur);
				System.out.println("--------*********@@@@@@@@@--------" + pt);
			}

		}
		}
		catch (Exception e) {
			System.out.println("e " + e);
			e.printStackTrace();
			// return new ModelAndView("gettax");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * finally {
		 * 
		 * ps.close(); ps1.close(); ps2.close(); ps3.close(); ps4.close();
		 * ps5.close(); ps6.close(); ps7.close(); ps8.close(); rs.close();
		 * rs1.close(); rs2.close(); rs3.close(); rs4.close(); rs5.close();
		 * rs6.close(); rs7.close(); rs8.close(); con.close(); }
		 */
		return pt;

	}

	@Override
	public boolean savePTAsmntFileData(AssessmentMaster ptAssessmentMaster) {
		boolean flag = false;
		// TODO Auto-generated method stub

		try {
			Session session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();

			session.saveOrUpdate(ptAssessmentMaster);
			session.getTransaction().commit();
			session.close();
			flag = true;

			// sessionFactory.close();
			return flag;
		} catch (Exception e) {

			e.printStackTrace();

			return flag;
		}

	}

	@Override
	public String checkAssessmentNo(String assessmentNo) {
		Integer i = Integer.parseInt(assessmentNo);
		try {
			Session session = sessionFactory.getCurrentSession();
			// Transaction transaction=session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("SELECT (max(AssessmentNo)+1) as count FROM ptassessmenttax.ptassessment_master p WHERE ulbcode="+assessmentNo.substring(0, 4) +" and AssessmentNo>="+assessmentNo) ;
			List max =  query.list();
			if((Number)max.get(0)!=null){
				Number count = (Number) max.get(0);
				System.out.println("count==" + count.intValue());
				i=count.intValue();
			}
		
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return String.valueOf(i);
	}
	@Override
	public boolean save(AsmtMeasurementMaster asmtMeasurementMaster) {
		boolean flag = false;
		try {
			//assessmentEntity.setEntrydate(new java.util.Date());
			//log.info("Insert Success");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(asmtMeasurementMaster);
			tx.commit();
			flag = true;
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			log.error("failed local insert " + e);
		}
		return flag;
	}

	@Override
	public AsmtMeasurementMaster getSingleRecordForMeasurement(long measurement_id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AsmtMeasurementMaster.class);
		criteria.add(Restrictions.eq("measurementId", measurement_id));
		System.out.println("measurementId" + measurement_id);

		AsmtMeasurementMaster getsinglerecord = (AsmtMeasurementMaster) criteria
				.uniqueResult();

		return getsinglerecord;
	}

	

}
