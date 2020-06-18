package com.mars.cdma.gov.scheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mars.cdma.gov.Dao.ReportDAO;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.helper.Dbcon;

public class InitiatePropertyTaxStatus {
	int i = 0;
	String I_SLNO;
	String 	I_BOOKNO;
	String C_RCPTNO;
	String DT_RCPTDT;
	String DT_ETRYDT;
	String I_BNKCODE;
	String D_CRNPT;
	String D_CRNED;
	String D_CRNLCS,D_CRNUAUTHCNSTPLTY,I_EMPCODE,D_PLTYONCRN,D_ARRPT,D_ARRED,D_ARRLCS,D_ARRUAUTHCNSTPLTY,D_PLTYONARR,DT_PAIDFRMPRDDT,DT_PAIDTOPRDDT,D_ADVAMT,VC_PAYTYPE,C_AMTPAIDAT,I_ESEVAID,I_GRCID,C_PAYMODE,VC_CHQDDCCBNO,VC_CRCARDNO,DT_CHQDDCCBDT,I_ASMTNO,I_DMNDNO,C_ISRLSD,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,D_SERVCHRGS,D_REBATE;

	public void getSetCertificateStatusDb2ToLocal() {
		Connection con = null;
		try {
			con = new ReportDAO().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT pt.uniqueRequestNumber,pt.application_stage,pt.paymentamount,entrydate  FROM ptassessmenttax.ptassessment_master pt Where application_stage='Commissioner Approval Completed' and paymentflag='Y'");
			while (rs.next()) {
				String uniqReNum = rs.getString(1);
				String applstage = rs.getString(2);
				String payAmount =rs.getString(3);
				String entryDate = rs.getString(4);
				try {
					
					AssessmentMaster appl = getstatusfromliveAndUpdate(uniqReNum,
							applstage,payAmount,entryDate);
					

				} catch (Exception e) {
					System.out.println("Error ==" + e
							+ " >> Filed to update Status for Req Num == "
							+ uniqReNum);
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	/*private String updateInToDb2() {
		//Connection con = null;
		//String insertReceipt=null;
		try {
			con = new ReportDAO().getConnection();
			PreparedStatement pstmt = con.prepareStatement(insertReceipt);
  insertReceipt="insert into pt_asmtrcpt_tbl (I_SLNO,I_BOOKNO,C_RCPTNO,DT_RCPTDT,DT_ETRYDT,I_BNKCODE,D_CRNPT,D_CRNED,D_CRNLCS,D_CRNUAUTHCNSTPLTY,I_EMPCODE,D_PLTYONCRN,D_ARRPT,D_ARRED,D_ARRLCS,D_ARRUAUTHCNSTPLTY,D_PLTYONARR,DT_PAIDFRMPRDDT,DT_PAIDTOPRDDT,D_ADVAMT,VC_PAYTYPE,C_AMTPAIDAT,I_ESEVAID,I_GRCID,C_PAYMODE,VC_CHQDDCCBNO,VC_CRCARDNO,DT_CHQDDCCBDT,I_ASMTNO,I_DMNDNO,C_ISRLSD,I_ULBOBJID,C_DELFLAG,TS_DTTM,I_USRID,D_SERVCHRGS,D_REBATE) values (?,0,?,?,?,0,?,0.00,?,?,1,0.00,0.00,0.00,0.00,0.00,0.00,?,?,0.00,'Current + SelfAsmt','O',0,0,'I','0','0',?,?,?,'C',?,'N',CURRENT_TIMESTAMP,?,0.00,0.00)";
  String updatebalance="update  pt_asmtbal_tbl set D_CURRENTAMT=0  where I_ULBOBJID=? and  i_asmtno=?";
  pstmt.executeUpdate();
  return null;
	}
	}
*/
	private AssessmentMaster getstatusfromliveAndUpdate(String uniqReqNum,
			String applstage,String payAmount,String entryDate) {
		AssessmentMaster asmtMaster = new AssessmentMaster();

		Connection con = null;

		try {
			String sql = "select pa.I_SLNO+1,pd.I_DMNDNO,pb.D_CURRENTAMT from pt_asmtrcpt_tbl pa,pt_dmndrgtr_tbl pd,pt_asmtbal_tbl pb where pd.C_DELFLAG='N' and pd.I_ULBOBJID="+I_ULBOBJID+" and pd.I_ASMTNO="+I_ASMTNO+" and pb.I_ASMTNO=pd.I_ASMTNO and pb.I_ULBOBJID=pd.I_ULBOBJID order by pa.I_SLNO desc fetch first 1 rows only";
			
		String distname = Dbcon.getULBName(uniqReqNum.substring(0, 4));
			con = Dbcon.getdbfromdist(distname);
			System.out.println("sql " + sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String I_SLNO=rs.getString("I_SLNO");
				String I_DMNDNO=rs.getString("I_DMNDNO");
				String D_CURRENTAMT=rs.getString("D_CURRENTAMT");
				if(Integer.parseInt(payAmount) == Integer.parseInt(D_CURRENTAMT)){
					//updateInToDb2();
				}
				else if(Integer.parseInt(payAmount) > Integer.parseInt(D_CURRENTAMT)){
				}
				else{
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return asmtMaster;
	}

	}

	


