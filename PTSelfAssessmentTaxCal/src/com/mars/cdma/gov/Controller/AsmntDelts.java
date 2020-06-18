package com.mars.cdma.gov.Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mars.cdma.gov.bean.AssesmentDb2Bean;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
public class AsmntDelts {
@SuppressWarnings("unchecked")
public static void main(String[] args) throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		String UlbCode = null;
		PreparedStatement stmt = null;
		String Appid="";
		String bage="";
		String bcls="";
		String busage="";
		String len="";
		String wid="";
		String octy="";
		String floorNo="";
		//long uniqueReqNo=10381568891714740l;
		String Sql = "SELECT m.asmt_appl_id as Appid,m.bage as bage,m.bcls as bcls,m.busage as busage,m.len as len,m.wid as wid,m.octy as octy,"
				+ "m.floorNo as floorNo FROM ptassessmenttax.ptassessment_master m1 ,"
				+ "ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id and m1.new_application_id="+2852;
		stmt = con.prepareStatement(Sql);
 		ResultSet rs1 = stmt.executeQuery();
 		AssesmentDb2Bean bean=new AssesmentDb2Bean();
 		@SuppressWarnings("rawtypes")
		List measurelist=new ArrayList();
 		while (rs1.next()) {
 			Appid=rs1.getString("Appid");
 			bage=rs1.getString("bage");
 			bcls=rs1.getString("bcls");
 			busage=rs1.getString("busage");
 			len=rs1.getString("len");
 			wid=rs1.getString("wid");
 			octy=rs1.getString("octy");
 			floorNo=rs1.getString("floorNo");
 			bean.setAppid(Appid);
 			bean.setBage(bage);
 			bean.setBcls(bcls);
 			bean.setBusage(busage);
 			bean.setLen(len);
 			bean.setWid(wid);
 			bean.setOcty(octy);
 			bean.setFloorNo(floorNo);
 			measurelist.add( bean);
 			
 			System.out.println("measurelist  :"+measurelist);
 			}
 		List InsertList=new ArrayList();
 		/*for(AssesmentDb2Bean bean1 :measurelist){
 			
 		}*/
 		/*System.out.println("bage  :"+bage + "bcls :"+ bcls + "busage :" +busage + "len :"+len + " wid :"+ wid+ " octy: "+ octy + 
					"floorNo :" + floorNo);*/
 		Connection conDB2 = Dbcon.getdbfromdist("TEST");
 		
 		String Sql1 = "Insert into pt_asmt_dtls_tbl (I_ASMTDTLSNO,I_ASMTNO,VC_CNSTUN,I_BLDGAGE,"
 				+ "I_CLSCODE,I_BLDGUSECODE,"
 				+ "I_OCPNTYPECODE,D_CAPVAL,I_FLRNO,D_LNTH,D_WDTH,D_PLNTAREA,"
				+ "I_UR,D_MRV,D_ARV,I_DEPCODE,D_BLDGVAL,D_SITEVAL,D_TARV,"
				+ "D_PT,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,DT_ADDDT,"
				+ "VC_BLDGPERSNO,DT_BLDPRMT,IS_PLANAPRVD) Values ("
				+ ""+2852+","
				+ 2852
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
				+ 123
				+ ","
				+ ""+2+","
				+ 490.5
				+ ","
				+ 982.5
				+ ",0,0,0,0,"
				+ 873.5
				+ ","
				+ 1038
				+ ",'N',CURRENT_TIMESTAMP,1,'"
				+ 5345
				+ "','"
				+ 5423
				+ "','"
				+ 654
				+ "','"
				+ 0 + "')";

		System.out.println("Sql5  " +Sql1);
		//PreparedStatement ps = conDB2.prepareStatement(sql6);
		Statement ps=conDB2.createStatement();
		ps.addBatch(Sql1);
 		
	}

	}

