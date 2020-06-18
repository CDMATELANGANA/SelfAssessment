package com.mars.cdma.gov.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.mars.cdma.gov.Controller.DoorDemo;
import com.mars.cdma.gov.Dao.AddAsmntMeasurementDao;
import com.mars.cdma.gov.bean.AddAsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AddAssessmentMaster;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.service.AddAsmntMeasurementService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.UlbsService;
import com.mars.cdma.gov.utils.CommonUtils;
@Repository("AddAsmntMeasurementDao")
public class AddAsmntMeasurementDaoImpl implements AddAsmntMeasurementDao {

	private static final Logger log = Logger.getLogger(AssessmentDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private AddAsmntMeasurementService addAsmntMeasurementService;
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
	public ArrayList<String> findExistAssessment(String assessment,
			String doorno) throws SQLException {
		Connection con = null;
		String distname = Dbcon.getULBName("" + assessment.substring(0,4));
		con = Dbcon.getdbfromdist(distname);
		//System.out.println("District Name from ULB code****" + distname);
		/************ Test Data Base connectoin ************/
		/************ End Live Data Base connectoin ************/
		
		String query="select count(I_ASMTNO) from pt_asmt_mstr_tbl where I_ASMTNO="+assessment+" and VC_ONRDOORNO='"+doorno+"' and C_DELFLAG='N'";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		int count = 0;
		while (resultSet.next()) {
			count = resultSet.getInt(1);
		}
		if(count!=0)
		{

		String sql="select C_SEX,VC_ONRSURNAME,VC_ONRNAME,VC_FTHRSURNAME,VC_FTHRNAME,VC_CITY,VC_ONRMOBINO,VC_OPNTNAME,VC_OPNTSURNAME,"
+"I_LCTYOBJID,I_ZONEOBJID,I_RWOBJID,I_BLCKOBJID,I_EWOBJID,I_STRTOBJID,I_FLRTYPECODE,I_RFTYPECODE,I_WALLTYPECODE,I_WOODTYPECODE from pt_asmt_mstr_tbl where I_ASMTNO="+assessment+" and VC_ONRDOORNO='"+doorno+"'";
		System.out.println("sql="+sql);
		PreparedStatement ps1 = con.prepareStatement(sql);
		ResultSet rs1 = ps1.executeQuery();
		String VC_ONRSURNAME="";
		String VC_ONRNAME="";
		String VC_FTHRSURNAME="";
		String VC_FTHRNAME="";
		String C_SEX="";
		String I_STRTOBJID="";
		String I_LCTYOBJID="";
		String I_BLCKOBJID="";
		String I_ZONEOBJID="";
		String I_EWOBJID="";
		String VC_CITY="";
		String VC_ONRMOBINO="";
		String I_RWOBJID="";
		String I_FLRTYPECODE="";
		String I_RFTYPECODE="";
		String I_WALLTYPECODE="";
		String I_WOODTYPECODE="";
		String locality="";
		String localityId="";
		String zone="";
		//String zoneId="";
		String revenueward="";
		//String revenuewardId="";
		String block="";
		String blockId="";
		String elecward="";
		String street="";
		String floorType="";
		String roofType="";
		String waalType="";
		String woodType="";
		String VC_OPNTNAME="";
		String VC_OPNTSURNAME="";
		String I_DSTOBJID="";
		String VC_ULBNAME="";
		String I_ULBOBJID="";
		String VC_DSTNAME="";
		while (rs1.next()) {

			 VC_ONRSURNAME=rs1.getString("VC_ONRSURNAME");
			  VC_ONRNAME=rs1.getString("VC_ONRNAME");
			 VC_FTHRSURNAME=rs1.getString("VC_FTHRSURNAME");
			 VC_FTHRNAME=rs1.getString("VC_FTHRNAME");
			 C_SEX=rs1.getString("C_SEX");
			 I_STRTOBJID=rs1.getString("I_STRTOBJID");
			 I_LCTYOBJID=rs1.getString("I_LCTYOBJID");
			 I_BLCKOBJID=rs1.getString("I_BLCKOBJID");
			 I_ZONEOBJID=rs1.getString("I_ZONEOBJID");
			 I_EWOBJID=rs1.getString("I_EWOBJID");
			 VC_CITY=rs1.getString("VC_CITY");
			 VC_ONRMOBINO=rs1.getString("VC_ONRMOBINO");
			 I_RWOBJID=rs1.getString("I_RWOBJID");
			 I_FLRTYPECODE=rs1.getString("I_FLRTYPECODE");
			 I_RFTYPECODE=rs1.getString("I_RFTYPECODE");
			 I_WALLTYPECODE=rs1.getString("I_WALLTYPECODE");
			 I_WOODTYPECODE=rs1.getString("I_WOODTYPECODE");
			 VC_OPNTNAME=rs1.getString("VC_OPNTNAME");
			 VC_OPNTSURNAME=rs1.getString("VC_OPNTSURNAME");

		}
		
		
		String sql2="select I_LCTYOBJID,VC_LCTYNAME  from CT_LCTY_MSTR_TBL where I_LCTYOBJID="+I_LCTYOBJID;
		PreparedStatement pstmt2 = null;
		pstmt2 = con.prepareStatement(sql2);
		ResultSet rs2 = pstmt2.executeQuery();
		while(rs2.next())
		{
			localityId=rs2.getString("I_LCTYOBJID");
			locality=rs2.getString("VC_LCTYNAME");
		}
		//pstmt2.close();
		//rs2.close();
		
		String sql3="select I_ZONEOBJID,VC_ZONEDESC from CT_ZONE_MSTR_TBL where I_ZONEOBJID="+I_ZONEOBJID;
		PreparedStatement pstmt3 = null;
		pstmt3 = con.prepareStatement(sql3);
		ResultSet rs3 = pstmt3.executeQuery();
		while(rs3.next())
		{
			zone=rs3.getString("VC_ZONEDESC");
		}
		//pstmt3.close();
		//rs3.close();
		
		String sql4="select I_RWOBJID,VC_RWDESC from CT_RW_MSTR_TBL where I_RWOBJID="+I_RWOBJID;
		PreparedStatement pstmt4 = null;
		pstmt4 = con.prepareStatement(sql4);
		ResultSet rs4 = pstmt4.executeQuery();
		while(rs4.next())
		{
			revenueward=rs4.getString("VC_RWDESC");
		}
		//pstmt4.close();
		//rs4.close();
		
		String sql5="select I_BLCKOBJID,VC_BLCKDESC  from CT_BLCK_MSTR_TBL where I_BLCKOBJID="+I_BLCKOBJID;
		PreparedStatement pstmt5 = null;
		pstmt5 = con.prepareStatement(sql5);
		ResultSet rs5 = pstmt5.executeQuery();
		while(rs5.next())
		{
			blockId=rs5.getString("I_BLCKOBJID");
			block=rs5.getString("VC_BLCKDESC");
		}
		//pstmt5.close();
		//rs5.close();
		
		String sql6="select I_EWOBJID,VC_ELCNWARDDESC from ct_elcnward_mstr_tbl where I_EWOBJID="+I_EWOBJID;
		PreparedStatement pstmt6 = null;
		pstmt6 = con.prepareStatement(sql6);
		ResultSet rs6 = pstmt6.executeQuery();
		while(rs6.next())
		{
			elecward=rs6.getString("VC_ELCNWARDDESC");
		}
		//pstmt6.close();
		//rs6.close();
		
		String sql7="select I_STRTOBJID,VC_STRTNAME  from ct_strt_dtls_tbl where I_STRTOBJID="+I_STRTOBJID;
		PreparedStatement pstmt7 = null;
		pstmt7 = con.prepareStatement(sql7);
		ResultSet rs7 = pstmt7.executeQuery();
		while(rs7.next())
		{
			street=rs7.getString("VC_STRTNAME");
		}
		//pstmt7.close();
		//rs7.close();
		
		String sql8="select I_FLRTYPECODE,VC_FLRTYPEDESC from PT_FLRTYPE_MSTR_TBL where I_FLRTYPECODE="+I_FLRTYPECODE;
		PreparedStatement pstmt8 = null;
		pstmt8 = con.prepareStatement(sql8);
		ResultSet rs8 = pstmt8.executeQuery();
		while(rs8.next())
		{
			floorType=rs8.getString("VC_FLRTYPEDESC");
		}
		
		String sql9="select I_RFTYPECODE,VC_RFTYPEDESC from PT_RFTYPE_MSTR_TBL where I_RFTYPECODE="+I_RFTYPECODE;
		PreparedStatement pstmt9 = null;
		pstmt9 = con.prepareStatement(sql9);
		ResultSet rs9 = pstmt9.executeQuery();
		while(rs9.next())
		{
			roofType=rs9.getString("VC_RFTYPEDESC");
		}
		
		String sql10="select I_WALLTYPECODE,VC_WALLTYPEDESC from PT_WALLTYPE_MSTR_TBL where I_WALLTYPECODE="+I_WALLTYPECODE;
		PreparedStatement pstmt10 = null;
		pstmt10 = con.prepareStatement(sql10);
		ResultSet rs10 = pstmt10.executeQuery();
		while(rs10.next())
		{
			waalType=rs10.getString("VC_WALLTYPEDESC");
		}
		
		String sql11="select I_WOODTYPECODE,VC_WOODTYPEDESC from PT_WOODTYPE_MSTR_TBL where I_WOODTYPECODE="+I_WOODTYPECODE;
		PreparedStatement pstmt11 = null;
		pstmt11 = con.prepareStatement(sql11);
		ResultSet rs11 = pstmt11.executeQuery();
		while(rs11.next())
		{
			woodType=rs11.getString("VC_WOODTYPEDESC");
		}
		
		String sql12="select I_DSTOBJID,I_ULBOBJID,VC_ULBNAME from CT_ULB_MSTR_TBL where I_ULBOBJID="+assessment.substring(0,4);
		PreparedStatement pstmt12 = null;
		pstmt12 = con.prepareStatement(sql12);
		ResultSet rs12 = pstmt12.executeQuery();
		while(rs12.next())
		{
			I_DSTOBJID=rs12.getString("I_DSTOBJID");
			VC_ULBNAME=rs12.getString("VC_ULBNAME");
			I_ULBOBJID=rs12.getString("I_ULBOBJID");
		}
		String sql13="select VC_DSTNAME from CT_DST_MSTR_TBL where I_DSTOBJID="+I_DSTOBJID;
		PreparedStatement pstmt13 = null;
		pstmt13 = con.prepareStatement(sql13);
		ResultSet rs13 = pstmt13.executeQuery();
		while(rs13.next())
		{
			VC_DSTNAME=rs13.getString("VC_DSTNAME");
		}
		Connection con2=MySqlDBConnection.getEpayDB();
		String sql14="SELECT DISTRICT_ID FROM ptassessmenttax.ulbs where ULB_CODE="+assessment.substring(0,4);
		PreparedStatement pstmt14 = null;
		pstmt14 = con2.prepareStatement(sql14);
		ResultSet rs14 = pstmt14.executeQuery();
		String DISTRICT_ID="";
		while(rs14.next())
		{
			DISTRICT_ID=rs14.getString("DISTRICT_ID");
		}
		  ArrayList<String> arrlist = new ArrayList<String>();
		  arrlist.add(C_SEX); 
		arrlist.add(VC_ONRSURNAME);
		arrlist.add(VC_ONRNAME);
		arrlist.add(VC_FTHRSURNAME);
		arrlist.add(VC_FTHRNAME);
		arrlist.add(VC_CITY);
		arrlist.add(VC_ONRMOBINO);
		arrlist.add(VC_OPNTNAME);
		arrlist.add(VC_OPNTSURNAME);
		arrlist.add(DISTRICT_ID);
		arrlist.add(VC_DSTNAME);
		arrlist.add(I_ULBOBJID);
		arrlist.add(VC_ULBNAME);
		arrlist.add(I_LCTYOBJID);
		arrlist.add(locality);
		arrlist.add(I_ZONEOBJID);
		arrlist.add(zone);
		arrlist.add(I_RWOBJID);
		arrlist.add(revenueward);
		arrlist.add(I_BLCKOBJID);
		arrlist.add(block);
		arrlist.add(I_EWOBJID);
		arrlist.add(elecward);
		arrlist.add(I_STRTOBJID);
		arrlist.add(street);
		arrlist.add(I_FLRTYPECODE);
		arrlist.add(floorType);
		arrlist.add(I_RFTYPECODE);
		arrlist.add(roofType);
		arrlist.add(I_WALLTYPECODE);
		arrlist.add(waalType);
		arrlist.add(I_WOODTYPECODE);
		arrlist.add(woodType);		
		
		System.out.println("arrlist="+arrlist);
return arrlist;
		}
		else
		{
			return null;
		}
	}
	@Override
	public boolean save(AddAssessmentMaster addAssessmentMaster) {
		boolean flag = false;
		try {
			//assessmentEntity.setEntrydate(new java.util.Date());
			//log.info("Insert Success");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(addAssessmentMaster);
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
	public boolean save(AddAsmtMeasurementMaster addAsmtMeasurementMaster) {
		boolean flag = false;
		try {
			//assessmentEntity.setEntrydate(new java.util.Date());
			//log.info("Insert Success");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(addAsmtMeasurementMaster);
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
	public AddAsmtMeasurementMaster getSingleRecordForAddMeasurement(long measureId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AddAsmtMeasurementMaster.class);
		criteria.add(Restrictions.eq("measurementId", measureId));
		System.out.println("measurementId" + measureId);

		AddAsmtMeasurementMaster getsinglerecord = (AddAsmtMeasurementMaster) criteria
				.uniqueResult();

		return getsinglerecord;
	}
	
	
	

	@Override
	public Double getTotalPtSelfAssessmentTaxForAddMeasurement(
			AddAssessmentMaster addAssessmentMaster) throws SQLException, NumberFormatException {
		Connection con = MySqlDBConnection.getEpayDB();

		@SuppressWarnings("unused")
		String measureId = null;
		// Double area=0.0;
		PreparedStatement stmt = null;
		System.out.println("Request Number :::::::"+addAssessmentMaster.getUniqueRequestNumber());
		String Sql = "select m.measurement_id,m.len*wid,m.planAprvl as plan,a.ulbcode,a.zone,m.bcls,a.locality,m.bage,m.octy,m.busage,m.cPlinth,m.usageType from ptassessmenttax.addasmtmeasurementmaster m , ptassessmenttax.addassessmentmaster a where m.asmt_appl_id=a.new_application_id and a.uniqueRequestNumber="
				+ addAssessmentMaster.getUniqueRequestNumber();
		System.out.println("Sql::::"+Sql);
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
			 AddAsmtMeasurementMaster addAsmtMeasurementMaster=addAsmntMeasurementService.getSingleRecordForAddMeasurement(Long.parseLong(measureId));
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
			d += getTotalForAddMeasurement(addAssessmentMaster,addAsmtMeasurementMaster, plan, area, ulbcode, zone, bcls,
					bage, locality, octy, busage,cPlinth,usageType);
			count++;
			System.out.println("Count ::::" + count);
			System.out.println("tax== : " + d);
			addAsmntMeasurementService.save(addAsmtMeasurementMaster);
		}
		/* end new implementation */
		return d;


	}

	public Double getTotalForAddMeasurement(
			AddAssessmentMaster addAssessmentMaster,
			AddAsmtMeasurementMaster addAsmtMeasurementMaster, String plan,
			Double area, String ulbcode, String zone, String bcls, String bage,
			String locality1, String octy, String busage, String cPlinth,
			String usageType) {
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
				System.out.println("locality1:::::::"+locality1);
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
                System.out.println("blduse ::::::::::"+blduse);
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
				}

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
				addAsmtMeasurementMaster.setD_ARV(GARV);
				addAsmtMeasurementMaster.setD_MRV(MRV);
				addAsmtMeasurementMaster.setD_PLNTAREA(d_plntarea);
				addAsmtMeasurementMaster.setI_UR(d_ur);
				addAsmtMeasurementMaster.setD_PT(pt);
				addAsmtMeasurementMaster.setD_TARV(NARV);
				addAsmtMeasurementMaster.setD_SITEVAL(SV);
				addAsmtMeasurementMaster.setD_BLDGVAL(BV);
				addAsmtMeasurementMaster.setD_UAC(uau);
				addAsmtMeasurementMaster.setI_DEPCODE(I_DEPRATECODE);
				if(addAssessmentMaster.getPlengthArea()==null)addAssessmentMaster.setPlengthArea(0d);
				addAssessmentMaster.setNArv(Math.round(tnarv)+addAssessmentMaster.getNArv());
				addAssessmentMaster.setFixdLcs(Math.round(lcs)+addAssessmentMaster.getFixdLcs());
				addAssessmentMaster.setFixdPT(Math.round(pt)+addAssessmentMaster.getFixdPT());
				addAssessmentMaster.setD_fxdunauthplnplty(Math.round(uau)+addAssessmentMaster.getD_fxdunauthplnplty());
				addAssessmentMaster.setPlengthArea(d_plntarea+addAssessmentMaster.getPlengthArea());
				addAssessmentMaster.setMarv(MRV+addAssessmentMaster.getMarv());
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


	@Override
	public AddAssessmentMaster getrecordForAddMeasurement(long uniquerequestid) {
		Session session = sessionFactory.getCurrentSession();
		// session.beginTransaction();
		Criteria criteria = session.createCriteria(AddAssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniquerequestid));
		System.out.println("uniquerequestid" + uniquerequestid);

		AddAssessmentMaster getsinglerecord = (AddAssessmentMaster) criteria
				.uniqueResult();

		return getsinglerecord;
	}
	@Override
	public AddAssessmentMaster getBean(long uniqueNumber) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(AddAssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqueNumber));
		AddAssessmentMaster ptAsmntBean = (AddAssessmentMaster) criteria
				.uniqueResult();

		return ptAsmntBean;
	}
	@Override
	public String userLogin(AddAssessmentMaster addAssessmentMaster,
			String vC_USRNAME, String vC_USRPWD) throws SQLException {
		String distname = Dbcon.getULBName("" + addAssessmentMaster.getUlbcode());
		Connection con = null;
		con = Dbcon.getdbfromdist(distname);
		System.out.println("District Name from ULB code****" + distname);
		/************ Test Data Base connectoin ************/
		//con = Dbcon.getdbfromdist("TEST");
		/************ End Live Data Base connectoin ************/

		String userId = null;// DB2INST1.
		String sql1="select I_USRID from DB2INST1.CT_USR_MSTR_TBL where VC_USRNAME='"+vC_USRNAME+"' and VC_USRPWD='"+vC_USRPWD+"'  and c_delflag='N'";
		//String sql1 = "SELECT I_USRID FROM CT_USR_MSTR_TBL WHERE VC_USRNAME LIKE '%COU%' and c_delflag='N' and date(TS_DTTM)>='10/17/2019' and VC_USRNAME='"+vC_USRNAME+"' and VC_USRPWD='"+vC_USRPWD+"'";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ResultSet rs1 = ps1.executeQuery();
		int Max = 0;

		while (rs1.next()) {

			userId = rs1.getString(1);
			System.out.println("Max---->>" + userId);
		}

		System.out.println("userId=" + userId);
		return userId;

	}
	@Override
	public List<AddAssessmentMaster> getUniquetData(long uniqueNumber) {
		String userSQL = "select p.uniqueRequestNumber,p.OWNER_CITY,Concat(p.OWNER_SURNAME,' ',p.OWNER_NAME )as Name,Concat(p.OWNERFATHERHUSBAND_SURNAME,p.OWNER_FATHERHUSBAND_NAME) as FatherName,p.OWNER_MOBILE,p.paymentamount,d.DISTRICT_NAME FROM addassessmentmaster p,districts d where p.uniqueRequestNumber="+uniqueNumber+" and p.delflag='Y' and p.OWNER_DISTRICT=d.DISTRICT_ID";
        Session session = sessionFactory.openSession();
		Query userQuery = session.createSQLQuery(userSQL);
		List<AddAssessmentMaster> list = userQuery.list();
		return list;
	}
	public int getcountfromlive(AddAssessmentMaster ptAssmntMaster1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public AddAssessmentMaster getptselfAssestsinglerecord(long uniqReqNumber) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(AddAssessmentMaster.class);
		System.out.println("uniqueRequestNumber "+uniqReqNumber);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
		AddAssessmentMaster getsinglerecord = (AddAssessmentMaster) criteria.uniqueResult();
		return getsinglerecord;
	}
	@Override
	public AddMeasurementTransactionHistory insertCashtransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response,
			String loginId) {
		Session session = sessionFactory.getCurrentSession();
		AddMeasurementTransactionHistory paymentTransactionEntity = new AddMeasurementTransactionHistory();
	
		int c=Count(uniqReqNumber);
		if(c!=1)
		{
		System.out.println("enter in if");
		String result = "fail";
		//String result = "fail";
		AddAssessmentMaster assessmenymaster =getptselfAssestsinglerecord(uniqReqNumber) ;
		//System.out.println("fetch data from db "+assessmenymaster.getAssesmentno());
		paymentTransactionEntity.setGateway_name("CashCounter");
		if(paymentTransactionEntity.getGateway_name()=="CashCounter"){
			paymentTransactionEntity.setC_AMTPAIDAT("D");
		}
		paymentTransactionEntity.setTotal_amount(assessmenymaster
				.getPaymentamount());
		paymentTransactionEntity.setPaid_update_flag('S');
		paymentTransactionEntity.setTransaction_response_code("0300");
		paymentTransactionEntity.setTransaction_error_description("Success");
		paymentTransactionEntity.setTransaction_type("05");
		paymentTransactionEntity.setTransactionmode("C");
		paymentTransactionEntity.setReceipt_owner_name(assessmenymaster.getOwnerSurName().concat(" ").concat(assessmenymaster
				.getOwnerName()));
		paymentTransactionEntity.setTransaction_mobile_number(assessmenymaster.getOwnerMobile());
		paymentTransactionEntity.setTransaction_flag('S');
		paymentTransactionEntity.setUlbcode(assessmenymaster.getUlbcode());
		paymentTransactionEntity.setUniqueRequestNumber(assessmenymaster.getUniqueRequestNumber());
		paymentTransactionEntity.setC_delflag('N');
		paymentTransactionEntity.setTransdate(new java.util.Date());
		paymentTransactionEntity.setLoginId(loginId);
		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());
        paymentTransactionEntity.setUlbname(ulbs.getUlbName());
        paymentTransactionEntity.setDistrictname(districts.getDistrictName());
		session.save(paymentTransactionEntity);
		return paymentTransactionEntity;
		}
		else
		{
			System.out.println("enter in else");
			Criteria criteria = session.createCriteria(PaymentTransaction.class);
			criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
			paymentTransactionEntity = (AddMeasurementTransactionHistory) criteria.uniqueResult();
			//paymentTransactionEntity=(PaymentTransaction)session.get(PaymentTransaction.class, uniqReqNumber);
			return paymentTransactionEntity;
	}
	}
	public int Count(long uniqReqNumber) {
			Session session = sessionFactory.getCurrentSession();
			String sql="select count(*) as count from ptassessmenttax.addmeasurement_transaction_history where uniqueRequestNumber="+uniqReqNumber+" and c_delflag='N'";
		
			
	        Query userQuery = session.createSQLQuery(sql);
	        String l=userQuery.list().toString();
	         //l=l.replace("[","").replace("]", "");
	        int c=Integer.parseInt(l.replace("[","").replace("]", ""));
	        
	        System.out.println("Couunt history =="+c);
	        return Integer.parseInt(l.replace("[","").replace("]", ""));
		}
		
	
	@Override
	public String checkAssessmentNo(String assmntNo) {
		Integer i = Integer.parseInt(assmntNo);
		try {
			Session session = sessionFactory.getCurrentSession();
			// Transaction transaction=session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("SELECT (max(AssessmentNo)+1) as count FROM ptassessmenttax.ptassessment_master p WHERE ulbcode="+assmntNo.substring(0, 4) +" and AssessmentNo>="+assmntNo) ;
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
	public List<AddMeasurementTransactionHistory> getsinglerecordForTransaction(
			long payment_transaction_receipt_id) {

		System.out.println("payment_transaction_receipt_id "
				+ payment_transaction_receipt_id);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT t.uniqueRequestNumber,t.receipt_owner_name,t.transaction_mobile_number,t.ulbname,t.districtname,t.transactionmode,"
				+ " t.gateway_name,t.transaction_error_description,t.total_amount,t.transdate FROM addmeasurement_transaction_history t"
				+ " where t.payment_transaction_receipt_id="
				+ payment_transaction_receipt_id + "";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<AddMeasurementTransactionHistory> paymentTransaction = query
				.list();
		return paymentTransaction;
	}
	public String insertlive(AddAssessmentMaster addAsmnttMaster) throws SQLException {
		
		PreparedStatement ps1 = null;
		ResultSet resultSet=null;
		String returnresult = "";
		System.out.println("Before insertlive");
		Connection con = MySqlDBConnection.getEpayDB();
		String distname = Dbcon.getULBName("" + addAsmnttMaster.getUlbcode());
		Connection conDB2 = Dbcon.getdbfromdist(distname);
		int appId = 0;
		Statement stmt = con.createStatement();
		Statement stmt1 = conDB2.createStatement();
		ResultSet rs=stmt1.executeQuery("select max(I_ASMTDTLSNO)+1 from pt_asmt_dtls_tbl");
		while (rs.next()) {
			appId=rs.getInt(1);
			System.out.println("appId"+appId);
		}
		ResultSet rs1=stmt.executeQuery("SELECT * FROM ptassessmenttax.addassessmentmaster m1 ,ptassessmenttax.addasmtmeasurementmaster m where m1.new_application_id=m.asmt_appl_id  and delflag='N' and m1.new_application_id="+addAsmnttMaster.getNewapplicationId());
 		int k=0;
 		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
 		String bpd,rdd;
 		while (rs1.next()) {
 			//if(rs1.getDate(48)!=null && rs1.getDate(36)!=null){
 			if(rs1.getDate("B_PermisionDate")!=null && rs1.getDate("regdate")!=null ){
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
 			
 			String D_FXDARV=null;
 			String D_FXDPT=null;
 			String D_FXDLCS=null;
 			String D_FXDUNAUTHPLNPLTY=null;
 			String selectSql="Select D_FXDARV,D_FXDPT,D_FXDLCS,D_FXDUNAUTHPLNPLTY from pt_asmt_mstr_tbl where  c_delflag='N' and C_WRTOFFFLAG='N' and I_ASMTNO="+addAsmnttMaster.getAssessmentNo();
 			ps1 = conDB2.prepareStatement(selectSql);
			resultSet = ps1.executeQuery();
			while (resultSet.next()) {
				D_FXDARV = resultSet.getString("D_FXDARV");
				D_FXDPT=  resultSet.getString("D_FXDPT");
				D_FXDLCS = resultSet.getString("D_FXDLCS");
				D_FXDUNAUTHPLNPLTY = resultSet.getString("D_FXDUNAUTHPLNPLTY");
			}
			double total_D_FXDARV=(Double.parseDouble(D_FXDARV)+addAsmnttMaster.getNArv());
			double total_D_FXDPT=(Double.parseDouble(D_FXDPT)+addAsmnttMaster.getFixdPT());
			double total_D_FXDLCS=(Double.parseDouble(D_FXDLCS)+addAsmnttMaster.getFixdLcs());
			double total_D_FXDUNAUTHPLNPLTY=(Double.parseDouble(D_FXDUNAUTHPLNPLTY)+addAsmnttMaster.getD_fxdunauthplnplty());
 			
 			//String updateSql="update pt_asmt_mstr_tbl set D_FXDARV="+addAsmnttMaster.getNArv()+",D_FXDPT="+addAsmnttMaster.getFixdPT()+",D_FXDLCS="+addAsmnttMaster.getFixdLcs()+",C_UNAUTHPLNPLTY='"+addAsmnttMaster.getPlanaprvl().charAt(0)+"',D_FXDUNAUTHPLNPLTY="+addAsmnttMaster.getD_fxdunauthplnplty()+",VC_ONRMOBINO='"+addAsmnttMaster.getOwnerMobile()+"' where c_delflag='N' and C_WRTOFFFLAG='N' and I_ASMTNO="+addAsmnttMaster.getAssessmentNo();
			String updateSql="update pt_asmt_mstr_tbl set D_FXDARV="+total_D_FXDARV+",D_FXDPT="+total_D_FXDPT+",D_FXDLCS="+total_D_FXDLCS+",C_UNAUTHPLNPLTY='"+addAsmnttMaster.getPlanaprvl().charAt(0)+"',D_FXDUNAUTHPLNPLTY="+total_D_FXDUNAUTHPLNPLTY+",VC_ONRMOBINO='"+addAsmnttMaster.getOwnerMobile()+"' where c_delflag='N' and C_WRTOFFFLAG='N' and I_ASMTNO="+addAsmnttMaster.getAssessmentNo();
			
			System.out.println("updateSql::::::::"+updateSql);
 			Statement ps = conDB2.createStatement();
			ps.addBatch(Sql5);
			ps.addBatch(updateSql);
			int Success[] = ps.executeBatch();
 			
 			//int i=stmt1.executeUpdate(Sql5);
 			appId++;
 			if (Success[0] == 1 && Success[1] == 1) { 
				returnresult = "success";
				System.out.println("returnresult :::"+returnresult);
			} else {
				returnresult = "fail";
			}
 		}
 		conDB2.close();
 		con.close();
 		return returnresult;


	}
	public HashMap<String, List> getMeasurementFloor(String assessment,
			String doorno) throws SQLException {
		Connection con = null;
		String distname = Dbcon.getULBName("" + assessment.substring(0,4));
		con = Dbcon.getdbfromdist(distname);
		//System.out.println("District Name from ULB code****" + distname);
		/************ Test Data Base connectoin ************/
		HashMap<String, List> hmParams = new HashMap<String, List>();
		List<Object> I_BLDGAGE=new ArrayList<Object>();
		List<Object> ClassificationofBuilding=new ArrayList<Object>();
		List<Object> TypeofBuildingusage=new ArrayList<Object>();
		List<Object> Occupanttype=new ArrayList<Object>();
		List<Object> I_FLRNO=new ArrayList<Object>();
		List<Object> D_LNTH=new ArrayList<Object>();
		List<Object> D_WDTH=new ArrayList<Object>();
		List<Object> D_PLNTAREA=new ArrayList<Object>();
		List<Object> IS_PLANAPRVD=new ArrayList<Object>();
		String sql15="select k.I_BLDGAGE,l.VC_CLSNAME as ClassificationofBuilding,m.VC_BLDGUSETYPE as TypeofBuildingusage,n.VC_OCPNTYPEDESC as  Occupanttype,k.I_FLRNO,k.D_LNTH,k.D_WDTH,k.D_PLNTAREA,k.IS_PLANAPRVD from pt_asmt_mstr_tbl a,pt_asmt_dtls_tbl k,PT_BLDGCLS_MSTR_TBL l,PT_BLDGUSE_MSTR_TBL m,PT_OCPNTYPE_MSTR_TBL n where k.I_CLSCODE=l.I_CLSCODE and k.I_BLDGUSECODE=m.I_BLDGUSECODE and k.I_OCPNTYPECODE=n.I_OCPNTYPECODE and a.i_asmtno=k.i_asmtno and a.c_delflag='N' and a.C_WRTOFFFLAG='N' and k.c_delflag='N' and a.I_ASMTNO="+assessment;
		PreparedStatement pstmt15 = null;
		System.out.println("sql15=="+sql15);
		pstmt15 = con.prepareStatement(sql15);
		ResultSet rs15 = pstmt15.executeQuery();
		while(rs15.next())
		{
			 I_BLDGAGE.add(rs15.getString("I_BLDGAGE"));
			 ClassificationofBuilding.add(rs15.getString("ClassificationofBuilding"));
			 TypeofBuildingusage.add(rs15.getString("TypeofBuildingusage"));
			 Occupanttype.add(rs15.getString("Occupanttype"));
			 I_FLRNO.add(rs15.getString("I_FLRNO"));
			 D_LNTH.add(rs15.getString("D_LNTH"));
			 D_WDTH.add(rs15.getString("D_WDTH"));
			 D_PLNTAREA.add(rs15.getString("D_PLNTAREA"));
			 IS_PLANAPRVD.add(rs15.getString("IS_PLANAPRVD"));
		}
		hmParams.put("I_BLDGAGE", I_BLDGAGE);
		hmParams.put("ClassificationofBuilding", ClassificationofBuilding);
		hmParams.put("TypeofBuildingusage", TypeofBuildingusage);
		hmParams.put("Occupanttype", Occupanttype);
		hmParams.put("I_FLRNO", I_FLRNO);
		hmParams.put("D_LNTH", D_LNTH);
		hmParams.put("D_WDTH", D_WDTH);
		hmParams.put("D_PLNTAREA", D_PLNTAREA);
		hmParams.put("IS_PLANAPRVD", IS_PLANAPRVD);
			  return hmParams;
}
	@Override
	public AddMeasurementTransactionHistory inserttransactionForDigital(
			long uniqReqNumber, HttpServletRequest request,
			HttpServletResponse response) {
		// String result = "fail";
				AddMeasurementTransactionHistory paymentTransactionHistory = new AddMeasurementTransactionHistory();
				AddAssessmentMaster addAssessmentMaster = getrsinglerecord(uniqReqNumber);
				System.out.println("ptAssessmentMaster" + addAssessmentMaster);
				paymentTransactionHistory.setGateway_name("BILL DESK");
				if (paymentTransactionHistory.getGateway_name() == "BILL DESK") {
                paymentTransactionHistory.setC_AMTPAIDAT("O");
				}
				paymentTransactionHistory.setTotal_amount(addAssessmentMaster
						.getPaymentamount());
				paymentTransactionHistory.setPaid_update_flag('N');
				paymentTransactionHistory.setReceipt_owner_name(addAssessmentMaster
						.getOwnerSurName().concat(" ")
						.concat(addAssessmentMaster.getOwnerName()));
				paymentTransactionHistory.setTransaction_mobile_number(addAssessmentMaster.getOwnerMobile());
				paymentTransactionHistory.setTransaction_flag('N');
				paymentTransactionHistory.setUlbcode(addAssessmentMaster.getUlbcode());
				paymentTransactionHistory.setUniqueRequestNumber(addAssessmentMaster.getUniqueRequestNumber());
				paymentTransactionHistory.setC_delflag('N');
				paymentTransactionHistory.setTransdate(new java.util.Date());
				Ulbs ulbs = ulbService.getByUlbname(paymentTransactionHistory.getUlbcode());
				Districts districts = districtsService.get(ulbs.getDistrict_id());
                paymentTransactionHistory.setDistrictname(districts.getDatabaseName());
				paymentTransactionHistory.setUlbname(ulbs.getUlbName());
				Session session = sessionFactory.getCurrentSession();
				session.save(paymentTransactionHistory);
				return paymentTransactionHistory;
	}
	public AddAssessmentMaster getrsinglerecord(long uniqReqNumber) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AddAssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
		System.out.println("uniquerequestid" + uniqReqNumber);
        AddAssessmentMaster getsinglerecord = (AddAssessmentMaster)criteria.uniqueResult();
		return getsinglerecord;
	}
	@Override
	public TransactionReceipt getdetailsfromresponseurl(
			AddMeasurementTransactionHistory addMeasurementaymentTransaction,
			String responseUrl) {
		TransactionReceipt transactionReceipt = new TransactionReceipt();

		String db2updatestatus = "fail";
		String DoorNo="";
		String AssmntNo="";
		//Session session = sessionFactory.getCurrentSession();
		System.out.println("url : " + responseUrl);
		String responseUrlArray[] = responseUrl.split("\\|");
		AddMeasurementTransactionHistory paymentTransactionEntity = getRecordForDigitalTransaction(Long
				.parseLong(responseUrlArray[1]));
 
		paymentTransactionEntity.setRESPONSE_URL(responseUrl);
	
		if (responseUrlArray[14].equals("0300")) {
			log.info("Payment Status " + responseUrlArray[14] + " URL "
					+ responseUrlArray[23]);
			paymentTransactionEntity.setTransaction_flag('S');
		}

		 else {

			paymentTransactionEntity.setTransaction_flag('F');

		}

		paymentTransactionEntity.setTransaction_error_description(responseUrlArray[24]);
		paymentTransactionEntity.setTranscation_id(responseUrlArray[2]);
		paymentTransactionEntity.setTransaction_response_code(responseUrlArray[14]);
		//System.out.println("requ number " + responseUrlArray[16]);
		paymentTransactionEntity.setUlbname(responseUrlArray[18]);
		paymentTransactionEntity.setDistrictname(responseUrlArray[19]);
		paymentTransactionEntity.setTransaction_bank_id(responseUrlArray[5]);
		paymentTransactionEntity.setTransaction_bank_in(responseUrlArray[6]);
		paymentTransactionEntity.setTransaction_bank_ref(responseUrlArray[3]);
		String temp=CommonUtils.getTransactionType(responseUrlArray[7]);
		System.out.println("temp   "+ temp);
	
		if(temp.equals("Debit Card") ||temp.equals("Credit Card")||temp.equals("Netbanking")|| temp.equals("WALLET"))
		{
			paymentTransactionEntity.setTransactionmode("I");	
		}
		System.out.println();
        //paymentTransactionEntity.setTransactionmode(CommonUtils.getTransactionType(responseUrlArray[7]));
        
		System.out.println(">>> before save"
				+ paymentTransactionEntity.getGateway_name()
				+ " Mode Of Payment " + responseUrlArray[7]
				+ " paymentTransactionEntity.getTransaction_flag() "
				+ paymentTransactionEntity.getTransaction_flag());
		  AddAssessmentMaster ptAssessmentMaster =addAsmntMeasurementService.getptselfAssestsinglerecord(Long.parseLong(responseUrlArray[16]));
		  if (responseUrlArray[14].equals("0300")) {
			// update pay and del flag status
				Character delFlag = 'N';
				Character payFlag = 'Y';				
				String status = "fail";
				ptAssessmentMaster.setDelflag(delFlag);
				ptAssessmentMaster.setPaymentflag(payFlag);
				ptAssessmentMaster.setPaymentdate(new Date());
				ptAssessmentMaster.setApplication_stage("Application Approved");
				//updaterecord
				addAsmntMeasurementService.updaterecord(ptAssessmentMaster);
				DoorNo=ptAssessmentMaster.getpDoorNo();
				AssmntNo=ptAssessmentMaster.getAssessmentNo();
				System.out.println("Update Method Working");
			try {
				db2updatestatus = new AddAsmntMeasurementDaoImpl()
						.insertlive(ptAssessmentMaster);

			} catch (Exception e) {
				e.printStackTrace();
				status = "fail";
			}

		}
		//Set Paid_update_Flag if DB2 Updated Successfully/Fail 
		if (db2updatestatus.equalsIgnoreCase("success")) {
			paymentTransactionEntity.setPaid_update_flag('S');
		} else {
			paymentTransactionEntity.setPaid_update_flag('F');
		}
	     sessionFactory.getCurrentSession().update(paymentTransactionEntity);
		if (db2updatestatus.equalsIgnoreCase("success")) {
			//Setting the  DB 2 update flag status if Success
			transactionReceipt.setUpdateStatus("Successful");
		}
		if (db2updatestatus.equalsIgnoreCase("fail")) {
		//	Setting the  DB 2 update flag status if Failed
			transactionReceipt
					.setUpdateStatus("Failed, Please Contact to CDMA OFFICE  : +91-6303298411 ");
		}
		
		transactionReceipt.setUnireqnumber(""
				+ paymentTransactionEntity.getUniqueRequestNumber());
		/*System.out.println("paymentTransactionEntity "
				+ transactionReceipt.getUnireqnumber());*/
		
		transactionReceipt.setTransactionReceiptNo(paymentTransactionEntity
				.getTranscation_id());
		
		transactionReceipt.setTransactionMode(paymentTransactionEntity
				.getTransactionmode());
		
		transactionReceipt.setC_AMTPAIDAT(paymentTransactionEntity.getC_AMTPAIDAT());
		
		/*transactionReceipt.setTransactionMode("I");
		transactionReceipt.setC_AMTPAIDAT("O");*/
	
		transactionReceipt.setFullName(paymentTransactionEntity
				.getReceipt_owner_name());
	
		transactionReceipt.setMobileNumber(paymentTransactionEntity
				.getTransaction_mobile_number());
		
		transactionReceipt.setTotalAmount(paymentTransactionEntity
				.getTotal_amount());
		
		// Setting the pay flag if pay successfully paid
		transactionReceipt.setTransactionStatus(getPaymentStatus(String
				.valueOf(paymentTransactionEntity.getTransaction_flag())));   
		
	transactionReceipt.setTransactionDate(""
				+ paymentTransactionEntity.getTransdate());
		transactionReceipt.setPaymentGateway(paymentTransactionEntity
				.getGateway_name());
	
		transactionReceipt.setUlbName(paymentTransactionEntity.getUlbname());
	
		transactionReceipt.setTransactionstatusflag(String
				.valueOf(paymentTransactionEntity.getPaid_update_flag()));

		transactionReceipt.setReason(paymentTransactionEntity
				.getTransaction_error_description());
		
		transactionReceipt.setDistrictName(paymentTransactionEntity
				.getDistrictname());
	    transactionReceipt.setAssessmentNo(AssmntNo);
	    transactionReceipt.setDoorNo(DoorNo);
	    System.out.println("Last line");
	    System.out.println("transactionReceipt  ::::"  +transactionReceipt);
		return transactionReceipt;


	}
	private String getPaymentStatus(String flag) {
		String status = "NA";

		switch (flag) {
		case "S":
			status = "Successful";
			break;
		case "F":
			status = "Failed";
			break;

		}
		return status;
	}
	@Override
	public AddMeasurementTransactionHistory getRecordForDigitalTransaction(
			long payment_transaction_receipt_id) {
		AddMeasurementTransactionHistory paymentTransaction = new AddMeasurementTransactionHistory();
		System.out.println("payment_transaction_receipt_id "
				+ payment_transaction_receipt_id);
		Session session = sessionFactory.getCurrentSession();
		paymentTransaction = (AddMeasurementTransactionHistory) session.get(
				AddMeasurementTransactionHistory.class, payment_transaction_receipt_id);
		return paymentTransaction;
	}
	@Override
	public void updaterecord(AddAssessmentMaster ptAssessmentMaster) {
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		session.load(AssessmentMaster.class,
				ptAssessmentMaster.getNewapplicationId());
		session.merge(ptAssessmentMaster);
		tx1.commit();
		session.close();
		
	}

}
	

