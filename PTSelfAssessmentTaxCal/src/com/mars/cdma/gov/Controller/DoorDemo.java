/**
 * 
 */
package com.mars.cdma.gov.Controller;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.derby.impl.sql.compile.GetCurrentConnectionNode;

import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
/**
 * @author RAHUL
 *
 */
public class DoorDemo {
	public static void main(String[] args) throws SQLException {
		System.out.println(permanentDoorNo(11771572932525859l));
		//System.out.println(getAssessmentNo("1037"));
		//getFloorType("1038");
		//getRoofType("1037");
		//getWaalType("1037");
		//getWoodType("1037");
		//getRecordFromDb2("1188");
		//insertInToMaster("1037");
		//transactioDetails(11931568635800050l);
		 // getUlbCode(10371572615213284l);
		//countAssessemntNo(1037045342);
		//("1038009633");
		
	}
	public synchronized static String getUlbCode(long uniqueReqNo) throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		String UlbCode = null;
		PreparedStatement stmt = null;
		//long uniqueReqNo=10371572009724222l;
		String Sql = "Select ulbcode FROM ptassessmenttax.ptassessment_master where uniqueRequestNumber="
				+ uniqueReqNo + " and delflag='N'";
		System.out.println("Sql@@@@@@@@"+Sql);
		stmt = con.prepareStatement(Sql);
		ResultSet rs = stmt.executeQuery();
		//String dno = "";
		while (rs.next()) {
			UlbCode = rs.getString("ulbcode");
			//System.out.println(UlbCode);
		}
		return UlbCode; 
	}
	
	
	public synchronized static String permanentDoorNo(long uniqueReqNo) throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		//long l = 10371572009724222l;
		String Bno = null;
		String ewd = null;
		String loc = null;
		String ulb = null;
		PreparedStatement stmt = null;
		String Sql = "Select block,revenueward,locality,ulbcode FROM ptassessmenttax.ptassessment_master where uniqueRequestNumber="
				+ uniqueReqNo + " and delflag='N'";
        System.out.println("Sql@@@@@@@@"+Sql);
		stmt = con.prepareStatement(Sql);
		ResultSet rs = stmt.executeQuery();
		//String dno = "";
		while (rs.next()) {
			Bno = rs.getString("block");
			ewd = rs.getString("revenueward");
			loc = rs.getString("locality");
			ulb = rs.getString("ulbcode");
		}
		String Sql3 = "SELECT count(*) as count from ptassessmenttax.ptassessment_master  where entrydate>='2019-10-18'  and delflag='N' and paymentflag='Y' and locality="+loc+""
                      +" and block="+Bno+" and revenueward="+ewd+" and ulbcode="+ulb;
		System.out.println(Sql3);
		stmt = con.prepareStatement(Sql3);
		ResultSet rs2 = stmt.executeQuery();
		int i = 0;
		while (rs2.next()) {
			i = Integer.parseInt(rs2.getString("count"));
			System.out.println(i);
		}
		String doorcount = "";
		if (i > 0 && i <= 9) {
			doorcount = "000" + i;
		} else if (i > 9 && i <= 99) {
			doorcount = "00" + i;
		}
		else if (i > 99 && i <= 999) {
			doorcount = "0" + i;}
		else {
			doorcount = "" + i;
		}
		String distname = Dbcon.getULBName(ulb);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt1 = null;
		String sql = "select a.I_BLCKNO,b.VC_LCTYNAME,c.I_RWNO from CT_BLCK_MSTR_TBL a ,CT_LCTY_MSTR_TBL b,CT_RW_MSTR_TBL c where a.I_BLCKOBJID="
				+ Bno
				+ " and a.I_ULBOBJID="
				+ ulb
				+ " and b.I_LCTYOBJID="
				+ loc + " and c.I_RWOBJID=" + ewd + "";
		
		stmt1 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt1.executeQuery();
		String dno = "";
		while (rs1.next()) {
			String Block = rs1.getString("I_BLCKNO");
			String Locality = rs1.getString("VC_LCTYNAME");
			String rward = rs1.getString("I_RWNO");
		
		
			dno = rward.concat("-").concat(Block).concat("-")
					.concat(Locality.substring(0,2)).concat(doorcount);
			System.out.println("dno"+dno);
			
		}
		//return doorno+dno.split(Locality.substring(0,2))+(Integer.parseInt(());
		return dno;
	}



	public synchronized static String getAssessmentNo(String ulb) throws SQLException{
		String distname = Dbcon.getULBName(ulb);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt1 = null;
		String sql = "select coalesce((max(i_asmtno)+1),0) as i_asmtno  from pt_asmt_mstr_tbl where  I_ULBOBJID ="+ulb;
		stmt1 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt1.executeQuery();
		String i_asmtno="";
		while (rs1.next()) {
			i_asmtno = rs1.getString("i_asmtno");
	}
		return i_asmtno;
}
	public synchronized static void getFloorType(String ulb) throws SQLException{
		String distname = Dbcon.getULBName(ulb);
		System.out.println("distname"+distname);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt1 = null;
		String sql = "select I_FLRTYPECODE,VC_FLRTYPEDESC from PT_FLRTYPE_MSTR_TBL where C_DELFLAG='N'";
		stmt1 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt1.executeQuery();
		String I_FLRTYPECODE="";
		String VC_FLRTYPEDESC="";
		while (rs1.next()) {
			I_FLRTYPECODE = rs1.getString("I_FLRTYPECODE");
			VC_FLRTYPEDESC=rs1.getString("VC_FLRTYPEDESC");
			System.out.println("I_FLRTYPECODE  "+I_FLRTYPECODE+"  "+ "VC_FLRTYPEDESC  "+VC_FLRTYPEDESC);
	}
}
	public synchronized static void getWaalType(String ulb) throws SQLException{
		String distname = Dbcon.getULBName(ulb);
		System.out.println("distname"+distname);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt2 = null;
		String sql = "select I_WALLTYPECODE,VC_WALLTYPEDESC from PT_WALLTYPE_MSTR_TBL where C_DELFLAG='N'";
		stmt2 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt2.executeQuery();
		String I_WALLTYPECODE="";
		String VC_WALLTYPEDESC="";
		while (rs1.next()) {
			I_WALLTYPECODE = rs1.getString("I_WALLTYPECODE");
			VC_WALLTYPEDESC=rs1.getString("VC_WALLTYPEDESC");
			System.out.println("I_WALLTYPECODE  "+I_WALLTYPECODE+"  "+ "VC_WALLTYPEDESC  "+VC_WALLTYPEDESC);
	}
}	
	public synchronized static void getRoofType(String ulb) throws SQLException{
		String distname = Dbcon.getULBName(ulb);
		System.out.println("distname"+distname);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt1 = null;
		String sql = "select I_RFTYPECODE,VC_RFTYPEDESC from PT_RFTYPE_MSTR_TBL where C_DELFLAG='N'";
		
		stmt1 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt1.executeQuery();
		String I_RFTYPECODE="";
		String VC_RFTYPEDESC="";
		while (rs1.next()) {
			I_RFTYPECODE = rs1.getString("I_RFTYPECODE");
			VC_RFTYPEDESC=rs1.getString("VC_RFTYPEDESC");
			System.out.println("I_RFTYPECODE  "+I_RFTYPECODE+"  "+ "VC_RFTYPEDESC  "+VC_RFTYPEDESC);
	}
}	
	public synchronized static void getWoodType(String ulb) throws SQLException{
		String distname = Dbcon.getULBName(ulb);
		System.out.println("distname"+distname);
		Connection conn = Dbcon.getdbfromdist(distname);
		PreparedStatement stmt1 = null;
		String sql = "select I_WOODTYPECODE,VC_WOODTYPEDESC from PT_WOODTYPE_MSTR_TBL where C_DELFLAG='N'";
		stmt1 = conn.prepareStatement(sql);
		ResultSet rs1 = stmt1.executeQuery();
		String I_WOODTYPECODE="";
		String VC_WOODTYPEDESC="";
		while (rs1.next()) {
			I_WOODTYPECODE = rs1.getString("I_WOODTYPECODE");
			VC_WOODTYPEDESC=rs1.getString("VC_WOODTYPEDESC");
			System.out.println("I_WOODTYPECODE  "+I_WOODTYPECODE+"  "+ "VC_WOODTYPEDESC  "+VC_WOODTYPEDESC);
	}
}	
	public static String getRecordFromDb2(String Ulb) throws SQLException {
		String distname = Dbcon.getULBName(Ulb);
		Connection conn2 = Dbcon.getdbfromdist(distname);
		/****************** End Live DB Connection *******************************/
		/****************** Test DBConnection *******************************/
		// con=DbUtil.getConnection();
		/****************** End Test DB Connection *******************************/
         
		System.out.println("Connection " + conn2);
		System.out.println("Ulb"+Ulb);
        String AsmntNo="1188000030";
		String sql1 = "select I_ASMTNO,VC_ONRSURNAME,VC_ONRNAME,VC_FTHRSURNAME,	VC_FTHRNAME,VC_ONRDOORNO,I_ULBOBJID from pt_asmt_mstr_tbl where I_ASMTNO="+AsmntNo;
        PreparedStatement pstmt=null;
        String I_ASMTNO="";
        pstmt=conn2.prepareStatement(sql1);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			I_ASMTNO=rs.getString("I_ASMTNO");
			System.out.println("I_ASMTNO"+I_ASMTNO);
		}

		conn2.close();
		pstmt.close();
		return I_ASMTNO;
	} 

	public static String insertInToMaster(String Ulb) throws SQLException {
		
		Connection c=MySqlDBConnection.getEpayDB();
		
		
		
		
		String distname = Dbcon.getULBName(Ulb);
		Connection inCon = Dbcon.getdbfromdist(distname);
		
		AssessmentMaster asmnt=new AssessmentMaster();
		System.out.println("asmnt.getAssessmentNo()"+asmnt.getAssessmentNo());
		asmnt.setAssessmentNo("1188000030");
		String table = "DB2INST1.pt_asmt_mstr_tbl";
		/*
		String sql1 = "select (max(SNO)+1) as max from " + table + "";
		PreparedStatement ps1 = inCon.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		int Max=0;
		while (rs1.next()) {
			Max = rs1.getInt("max");
		}
		int ulbcode = asmnt.getUlbcode();*/
		
		/*String sql="Insert into "+table+"(I_ASMTNO)"
		+"Values"+"("+asmnt.getAssessmentNo()+")";*/
		String sql="insert into pt_asmtbal_tbl (I_SLNO,D_AREARAMT,D_CURRENTAMT,D_PLTYONARRS,D_ADVAMT,I_ASMTNO,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID) values (1102020223,0.00,1812.00,0.00,0.00,1102000000,1102,'N','2019-03-25-19.42.35.350856',1)";

		PreparedStatement ps = inCon.prepareStatement(sql);
		int k = ps.executeUpdate();
		System.out.println(k);
		System.out.println(sql);
	return sql;
}
	public synchronized static String transactioDetails(long uniqueReqNo) throws SQLException{
		long l = 11931568635800050l;
	   Connection con = MySqlDBConnection.getEpayDB();
       String PtId="";
       //String transType="";
       String transMode="";
       String cheque_DD_Date="";
       String Cheque_DD_No="";
		PreparedStatement stmt = null;
		String Sql = "SELECT t.payment_transaction_receipt_id as id,transactionmode,cheque_DD_Date,Cheque_DD_No FROM ptassessmenttax.transaction_history t  WHERE t.uniqueRequestNumber="+uniqueReqNo+" and t.paid_update_flag='s' and t.transaction_flag='S' and t.C_delflag='N' and t.transaction_response_code='0300'";
		stmt = con.prepareStatement(Sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			 PtId=rs.getString("id");
			// transType=rs.getString("gateway_name");
			transMode=rs.getString("transactionmode");
			cheque_DD_Date=rs.getString("cheque_DD_Date");
			Cheque_DD_No=rs.getString("Cheque_DD_No");
			
			 System.out.println(PtId);
			// System.out.println(transType);
			 System.out.println(transMode);
			 System.out.println(cheque_DD_Date);
			 System.out.println(Cheque_DD_No);
		}
		return Sql;
	
	}
	public static int countAssessemntNo(int assessmentNo) throws SQLException{
		Connection con = MySqlDBConnection.getEpayDB();
		int  count =assessmentNo ;
		int asmnt=1037045342;
		PreparedStatement stmt = null;
		//long uniqueReqNo=10381568891714740l;
		String Sql = "SELECT count(*) as count FROM ptassessmenttax.ptassessment_master p WHERE p.`AssessmentNo`="+asmnt+" and delflag='N'";
		System.out.println("Sql@@@@@@@@"+Sql);
		stmt = con.prepareStatement(Sql);
		ResultSet rs = stmt.executeQuery();
		//String dno = "";
		while (rs.next()) {
			count = rs.getInt(0);
			System.out.println(count);
		}
		if(count>0){
			count=count++;
		}
		return count;
		
		
		
	}
	
	public static String demo(String asmntNo) throws SQLException{
		Connection conn = MySqlDBConnection.getEpayDB();
		//int asmnt=1038009633;
		String  bage="";
		String bcls="";
		String busage="";
		String len="";
		String wid="";
		String octy="";
		String floorNo="";
		   
		PreparedStatement pstmt = null;
		
		//String sql="SELECT m.asmt_appl_id as Appid,m.bage as bage,m.bcls as bcls,m.busage as busage,m.len as len,m.wid as wid,m.octy as octy,m.floorNo as floorNo FROM ptassessmenttax.ptassessment_master m1 ,ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id and m1.new_application_id=3";
		String sql2="insert into pt_asmt_dtls_tbl(I_ASMTDTLSNO,I_ASMTNO,VC_CNSTUN,I_BLDGAGE,I_CLSCODE,I_BLDGUSECODE,I_OCPNTYPECODE,D_CAPVAL,I_FLRNO,D_LNTH,D_WDTH,D_PLNTAREA,"
					+ "I_UR,D_MRV,D_ARV,I_DEPCODE,D_BLDGVAL,D_SITEVAL,D_TARV,D_PT,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,DT_ADDDT,VC_BLDGPERSNO,DT_BLDPRMT,IS_PLANAPRVD) select SELECT m.asmt_appl_id as Appid,m.bage as bage,m.bcls as bcls,m.busage as busage,m.len as len,m.wid as wid,m.octy as octy,m.floorNo as floorNo from ptassessmenttax.ptassessment_master m1 ,ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id and m1.new_application_id=3 ";
		pstmt = conn.prepareStatement(sql2);
 		ResultSet rs1 = pstmt.executeQuery();
 		
 		while (rs1.next()) {
 			bage=rs1.getString("bage");
 			bcls=rs1.getString("bcls");
 			busage=rs1.getString("busage");
 			len=rs1.getString("len");
 			wid=rs1.getString("wid");
 			octy=rs1.getString("octy");
 			floorNo=rs1.getString("floorNo");
 			System.out.println("bage  "+bage);
 		}
 
		
		return null;
	}
}



