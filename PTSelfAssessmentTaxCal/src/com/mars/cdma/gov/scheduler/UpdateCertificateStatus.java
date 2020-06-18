package com.mars.cdma.gov.scheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mars.cdma.gov.Dao.ReportDAO;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.helper.Dbcon;

public class UpdateCertificateStatus {

	int i = 0;

	public void getSetCertificateStatusDb2ToLocal() {
		Connection con = null;
		try {
			con = new ReportDAO().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT uniqueRequestNumber,application_stage  FROM ptassessmenttax.ptassessment_master   Where  application_stage IS  NULL OR (application_stage!='Commissioner Rejected' "
							+ " AND application_stage!='Certificate Issued' AND application_stage!='Application Rejected' AND application_stage != 'Commissioner Approval Completed') ");
			while (rs.next()) {
				String uniqReNum = rs.getString(1);
				String applstage = rs.getString(2);
				try {
					
					AssessmentMaster appl = getstatusfromlive(uniqReNum,
							applstage);
					if (appl.getUniqueRequestNumber() != 0) {
						String status = updateToLocal(appl);
					}

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

	public AssessmentMaster getstatusfromlive(String uniqReqNum,
			String applstage) {
		AssessmentMaster asmtMaster = new AssessmentMaster();

		Connection con = null;

		try {
			String sql = "select REQSTATUS,VC_PAY_AMOUNT,C_ISPAYMENT_PAID,APPROVEDDATE,VC_PAY_AMOUNT_INSERT_DATE,SIGN_STATUS,RSNFORRJCN,APPACKNO from MESEVA_SUVIDA_INT_USR_DATA_TAB where DOCTYPE like '%NEWASSESMENT%' and  APPREQNO='"
					+ uniqReqNum
					+ "' and ULBID="
					+ uniqReqNum.substring(0, 4)
					+ "";
			// System.out.println(sql);
			String distname = Dbcon.getULBName(uniqReqNum.substring(0, 4));
			con = Dbcon.getdbfromdist(distname);
			System.out.println("sql " + sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("\n" + (i++) + " == "
						+ rs.getString("REQSTATUS") + " === == " + applstage
						+ "\n");
				asmtMaster.setApplication_stage(rs.getString("REQSTATUS"));
				asmtMaster.setApplicationappeoveddate(rs
						.getDate("APPROVEDDATE"));

				if (rs.getString("REQSTATUS").equalsIgnoreCase(
						"Commissioner Rejected")) {
					asmtMaster.setApplicatoinstatusflag('R');
				} else {
					asmtMaster.setApplicatoinstatusflag('N');
				}

				asmtMaster.setUniqueRequestNumber(Long.parseLong(uniqReqNum));
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

	public String updateToLocal(AssessmentMaster appl) {
		// String signStatus = appl.getSignStatus();
		String updateStatus = "f";
		Connection con = null;
		try {
			con = new ReportDAO().getConnection();
			Statement stmt = con.createStatement();
			String updateQry = "";

			updateQry = " update  ptassessmenttax.ptassessment_master SeT application_stage='"
					+ appl.getApplication_stage()
					+ "',"
					+ " applicationappeoveddate='"
					+ new java.sql.Date(appl.getApplicationappeoveddate()
							.getTime()) + "'," + " applicatoinstatusflag='"
					+ appl.getApplicatoinstatusflag() + "' "
					+ " where uniqueRequestNumber='"
					+ appl.getUniqueRequestNumber() + "'";

			stmt.executeUpdate(updateQry);
			updateStatus = "s";
		} catch (Exception e) {
			System.out.println("Failed to update == "
					+ appl.getUniqueRequestNumber() + " >> Error == " + e);
			updateStatus = "f";
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

		return updateStatus;
	}

	public static void main(String[] args) {
		new UpdateCertificateStatus().getSetCertificateStatusDb2ToLocal();
	}
	
}
