/*package com.mars.cdma.gov.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;

public class MeasureMentDetails {

	public static void main(String[] args) throws SQLException {
		getUlbCode(3);

	}
	
	
	
	public synchronized static String getUlbCode(int applicationId) throws SQLException {
		Connection con = MySqlDBConnection.getEpayDB();
		//String distname = Dbcon.getULBName("" + assessmententity.getUlbcode());
		Connection conDB2 = Dbcon.getdbfromdist("TEST");
		String bage="";
		String bcls="";
		String busage="";
		String len="";
		String wid="";
		String octy="";
		String floorNo="";
		int applicationId1=3;
		Statement stmt = con.createStatement();
		ResultSet rs1=stmt.executeQuery("SELECT * FROM ptassessmenttax.ptassessment_master m1 ,ptassessmenttax.ptasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id and m1.new_application_id="+applicationId1);
		//PreparedStatement pstmt=conDB2.prepareStatement("Insert into pt_asmt_dtls_tbl values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		int k=0;
 		while (rs1.next()) {
 			pstmt.setInt(1,rs1.getInt(1));
 			String Sql5 = "Insert into pt_asmt_dtls_tbl (I_ASMTDTLSNO,I_ASMTNO,VC_CNSTUN,I_BLDGAGE,I_CLSCODE,I_BLDGUSECODE,I_OCPNTYPECODE,D_CAPVAL,I_FLRNO,D_LNTH,D_WDTH,D_PLNTAREA,"
					+ "I_UR,D_MRV,D_ARV,I_DEPCODE,D_BLDGVAL,D_SITEVAL,D_TARV,D_PT,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,DT_ADDDT,VC_BLDGPERSNO,DT_BLDPRMT,IS_PLANAPRVD) Values ("
					+ ""
					+ new_application_id
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

 			k++;
 		}
 		return null;
	}
	
	
	
	
}
*/