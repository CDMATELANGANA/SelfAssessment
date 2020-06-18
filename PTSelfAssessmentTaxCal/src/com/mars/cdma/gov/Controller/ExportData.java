package com.mars.cdma.gov.Controller;

import java.io.*;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.mars.cdma.gov.helper.Dbcon;

public class ExportData {

	        public static void main(String[] args) {
	                try{
	                	String ulb="1049";
	                	String distname = Dbcon.getULBName(ulb);
	            		Connection conn = Dbcon.getdbfromdist(distname);
	                        PreparedStatement psmnt = null;
	                        Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("select a.I_ASMTNO as AssessmentNumber,a.VC_ONRSURNAME as OwnerSurName,a.VC_ONRNAME as OwnerName,a.VC_FTHRNAME as OwnerFatherName,a.VC_ONRDOORNO||'.'   DOORNo,b.I_ZONENO as ZoneNo,c.I_RWNO as RevenueWardNo,"
							+ "d.I_BLCKNO as BlockNo,e.VC_LCTYNAME as LocalityName,a.VC_ONRMOBINO as OwnerMobileNumber,"
							+ "a.VC_SSIDNO as AddharNumber,sum(a.D_FXDPT+a.D_FXDED+a.D_FXDLCS +a.D_FXDUNAUTHPLNPLTY) as  AnnualtaxPaid,"
							+ "(select sum(D_PLNTAREA) from pt_asmt_dtls_tbl where a.I_ASMTNO=I_ASMTNO and c_delflag='N') as AreaoftheProperty  "
							+ "from pt_asmt_mstr_tbl  a,ct_zone_mstr_tbl b,CT_RW_MSTR_TBL c,CT_BLCK_MSTR_TBL d,CT_LCTY_MSTR_TBL e where a.c_delflag='N' and a.I_ZONEOBJID=b.I_ZONEOBJID and  a.I_RWOBJID=c.I_RWOBJID and a.I_BLCKOBJID=d.I_BLCKOBJID and a.I_LCTYOBJID=e.I_LCTYOBJID  and a.i_ulbobjid="+ulb+" and a.C_WRTOFFFLAG='N' group by   a.I_ASMTNO,a.VC_ONRSURNAME,a.VC_ONRNAME,a.VC_FTHRNAME,a.VC_ONRDOORNO||'.',b.I_ZONENO,c.I_RWNO,d.I_BLCKNO,e.VC_LCTYNAME,a.VC_ONRMOBINO,a.VC_SSIDNO order by a.I_ASMTNO");

	                        HSSFWorkbook wb = new HSSFWorkbook();
	                        HSSFSheet sheet = wb.createSheet("Excel Sheet");
	                        HSSFRow rowhead = sheet.createRow((short) 0);
	                        rowhead.createCell((short) 0).setCellValue("AssessmentNumber");
	                        rowhead.createCell((short) 1).setCellValue("OwnerSurName");
	                        rowhead.createCell((short) 2).setCellValue("OwnerName");
	                        rowhead.createCell((short) 3).setCellValue("OwnerFatherName");
	                        rowhead.createCell((short) 4).setCellValue("ZoneNo");
	                        rowhead.createCell((short) 5).setCellValue("RevenueWardNo");
	                        rowhead.createCell((short) 6).setCellValue("BlockNo");
	                        rowhead.createCell((short) 7).setCellValue("LocalityName");
	                        rowhead.createCell((short) 8).setCellValue("OwnerMobileNumber");
	                        rowhead.createCell((short) 9).setCellValue("AddharNumber");
	                        rowhead.createCell((short) 10).setCellValue("AnnualtaxPaid");
	                        rowhead.createCell((short) 11).setCellValue("AreaoftheProperty");
	                        rowhead.createCell((short) 12).setCellValue("RevenueWardNo");
	                        rowhead.createCell((short) 13).setCellValue("RevenueWardNo");
	                        
	                        int index = 1;
	                        while (rs.next()) {

	                                HSSFRow row = sheet.createRow((short) index);
	                                row.createCell((short) 0).setCellValue(rs.getInt(1));
	                                row.createCell((short) 1).setCellValue(rs.getString(2));
	                                row.createCell((short) 2).setCellValue(rs.getString(3));
	                                row.createCell((short) 3).setCellValue(rs.getInt(4));
	                                row.createCell((short) 4).setCellValue(rs.getString(5));
	                                row.createCell((short) 5).setCellValue(rs.getString(6));
	                                row.createCell((short) 6).setCellValue(rs.getString(7));
	                                row.createCell((short) 7).setCellValue(rs.getString(8));
	                                row.createCell((short) 8).setCellValue(rs.getString(9));
	                                row.createCell((short) 9).setCellValue(rs.getString(10));
	                                row.createCell((short) 10).setCellValue(rs.getString(11));
	                                row.createCell((short) 11).setCellValue(rs.getString(12));
	                                row.createCell((short) 12).setCellValue(rs.getString(13));
	                                row.createCell((short) 13).setCellValue(rs.getString(14));
	                                index++;
	                        }
	                        FileOutputStream fileOut = new FileOutputStream("c:\\excelFile.xls");
	                        wb.write(fileOut);
	                        fileOut.close();
	                        System.out.println("Data is saved in excel file.");
	                        rs.close();
	                        conn.close();
	                } catch (Exception e) {
	                }
	        }	

}
