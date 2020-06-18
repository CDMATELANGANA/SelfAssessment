package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.impl.UlbDAOimpl;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.in.reset.Taxcalreset;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;

@Controller
public class TaxController {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DistrictsService districtsService;
	@Autowired
	private UlbsService ulbService;
	@Autowired
	private Assessmentservice assessmentService;
	
	@Autowired
	private UlbDAOimpl ulbDAOimpl;
	
	Connection con = null;
	public Gson gson = new Gson();
	@Autowired
	private MessageSource msgSrc;

	static Logger logger = Logger.getLogger(Taxcalreset.class.getName());
	public PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null, ps5 = null, ps6 = null, ps7 = null, ps8 = null,
			ps9 = null, ps10 = null;
	public String sql = null, sql1 = null, sql2 = null, sql3 = null,
			sql4 = null, sql5 = null, sql6 = null, sql7 = null, sql8 = null,
			sql9 = null, sql10 = null;
	public ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null,
			rs5 = null, rs6 = null, rs7 = null, rs8 = null, rs9 = null,
			rs10 = null;

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "";
		// System.out.println("welcome>>>>>");
		return new ModelAndView("welcome", "message", message);
	}

	@RequestMapping("/gettax.do")
	public ModelAndView etradeApplication(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			DataAccessResourceFailureException, HibernateException,
			IllegalStateException, SQLException, RemoteException {
		List<Districts> districtsList = districtsService.getAllOrderByName();
		// System.out.println("district >>>>"+districtsList.size());
		request.setAttribute("districtsList", districtsList);

		Map<String, Object> subUlbsMap = new HashMap<String, Object>();

		List<Districts> ulbsByDistrict = districtsService.getAllOrderByName();
		// System.out.println(" >>  id "+ulbsByDistrict.size());
		// System.out.println("district "+ulbsByDistrict.size());
		for (Districts districtsObj : ulbsByDistrict) {
			List<Ulbs> ulbsListOnValue = ulbService.findByProperty(
					"districts.districtId", districtsObj.getDistrictId());

			if (ulbsListOnValue != null && ulbsListOnValue.size() != 0)
				subUlbsMap.put("" + districtsObj.getDistrictId(), ulbService
						.findByProperty("districts.districtId",
								districtsObj.getDistrictId()));
		}

		// System.out.println("ulb name "+subUlbsMap.keySet()+" value "+subUlbsMap.values());
		request.setAttribute("subUlbsMap", subUlbsMap);

		return new ModelAndView("taxapp");
	}

	@RequestMapping(value = "/getzone", method = RequestMethod.POST)
	public @ResponseBody String getSearchzonevalues(
			@FormParam("ulbcode") String ulbcode,
			@FormParam("i_lctyobjid") String i_lctyobjid,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode get zone >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is "+con);
			// sql="select I_ZONEOBJID,VC_ZONEDESC from ct_zone_mstr_tbl where   C_DELFLAG='N' and  I_ULBOBJID="+ulbcode;

			// sql="select a.I_RWOBJID,a.I_BLCKOBJID,a.I_ZONEOBJID as zoneid,b.VC_ZONEDESC  as zonedesc from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b where a.I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID = "+i_lctyobjid+" and a.c_delflag = 'N' and a.i_ulbobjid ="+ulbcode+" and a.I_ZONEOBJID=b.I_ZONEOBJID";

			sql = "select c.I_RWOBJID,c.VC_RWDESC,d.I_BLCKOBJID,d.VC_BLCKDESC,b.I_ZONEOBJID as zoneid,b.VC_ZONEDESC as zonedesc  from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b,CT_RW_MSTR_TBL c,CT_BLCK_MSTR_TBL d where I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID="
					+ i_lctyobjid
					+ " and a.I_ZONEOBJID=b.I_ZONEOBJID and a.I_RWOBJID=c.I_RWOBJID and a.I_BLCKOBJID=d.I_BLCKOBJID and a.c_delflag = 'N' and a.i_ulbobjid="
					+ ulbcode;

			// sql="select a.I_RWOBJID,a.I_BLCKOBJID,a.I_ZONEOBJID as zoneid ,b.VC_ZONEDESC  as zonedesc from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b where a.I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID = "+i_lctyobjid+" and a.c_delflag = 'N' and a.i_ulbobjid ="+ulbcode;
			// from live
			// sql="select I_RWOBJID,I_BLCKOBJID,I_ZONEOBJID,VC_ZONEDESC from CT_LCTY_MAP_TBL where I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND I_LCTYOBJID =  "+i_lctyobjid+"  and c_delflag = 'N' and i_ulbobjid =="+ulbcode;
			// System.out.println("get zone from locality sql "+sql);
			logger.info(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("zoneid");
				String Category = rs.getString("zonedesc");
				// System.out.println(">>>cat  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();

			// TODO: handle exception
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getward", method = RequestMethod.POST)
	public @ResponseBody String getSearchwardvalues(
			@FormParam("ulbcode") String ulbcode,
			@FormParam("localityid") String localityid,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode get zone >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>>get Ward " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is "+con);
			// sql="select I_ZONEOBJID,VC_ZONEDESC from ct_zone_mstr_tbl where   C_DELFLAG='N' and  I_ULBOBJID="+ulbcode;
			// sql="select a.I_RWOBJID,a.I_BLCKOBJID,a.I_ZONEOBJID,b.VC_ZONEDESC from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b where a.I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID = "+i_lctyobjid+" and a.c_delflag = 'N' and a.i_ulbobjid ="+ulbcode+" and a.I_ZONEOBJID=b.I_ZONEOBJID";
			// sql="select a.I_RWOBJID as  rwobjid,a.VC_RWDESC as rwdesc from CT_RW_MSTR_TBL a,CT_ZONE_MSTR_TBL b where  a.C_DELFLAG='N' and  a.I_ULBOBJID=b.I_ULBOBJID and a.I_ULBOBJID="+ulbcode+" and b.I_ZONEOBJID="+zoneid;
			// sql="select a.I_RWOBJID as rwobjid,r.VC_RWDESC as rwdesc,a.I_BLCKOBJID,a.I_ZONEOBJID as zoneid,b.VC_ZONEDESC  as zonedesc from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b,CT_RW_MSTR_TBL r where a.I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID = "+localityid+" and a.c_delflag = 'N' and a.i_ulbobjid ="+ulbcode+" and a.I_ZONEOBJID=b.I_ZONEOBJID and a.I_LCTYOBJID=r.I_LCTYOBJID";
			// sql=" select a.I_RWOBJID as rwobjid,r.VC_RWDESC as rwdesc,a.I_BLCKOBJID,a.I_ZONEOBJID as zoneid,b.VC_ZONEDESC  as zonedesc from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b,CT_RW_MSTR_TBL r where a.I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID = "+localityid+" and a.c_delflag = 'N' and a.i_ulbobjid ="+ulbcode+" and a.I_ZONEOBJID=b.I_ZONEOBJID and a.I_RWOBJID=r.I_RWOBJID";

			sql = "select c.I_RWOBJID as rwobjid,c.VC_RWDESC as rwdesc,d.I_BLCKOBJID,d.VC_BLCKDESC,b.I_ZONEOBJID as zoneid,b.VC_ZONEDESC as zonedesc  from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b,CT_RW_MSTR_TBL c,CT_BLCK_MSTR_TBL d where I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID="
					+ localityid
					+ " and a.I_ZONEOBJID=b.I_ZONEOBJID and a.I_RWOBJID=c.I_RWOBJID and a.I_BLCKOBJID=d.I_BLCKOBJID and a.c_delflag = 'N' and a.i_ulbobjid="
					+ ulbcode;
			// System.out.println("get Reward from Zone  sql "+sql);
			logger.info(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("rwobjid");
				String Category = rs.getString("rwdesc");
				// System.out.println(">>>cat  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();

			// TODO: handle exception
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getblock", method = RequestMethod.POST)
	public @ResponseBody String getSearchblockvalues(
			@FormParam("ulbcode") String ulbcode,
			@FormParam("localityid") String localityid,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode get zone >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is "+con);
			// sql="select I_ZONEOBJID,VC_ZONEDESC from ct_zone_mstr_tbl where   C_DELFLAG='N' and  I_ULBOBJID="+ulbcode;
			// sql="select I_BLCKOBJID as blckid,VC_BLCKDESC as blckdesc from CT_BLCK_MSTR_TBL where  C_DELFLAG='N' and   I_RWOBJID="+reward+"  and I_ULBOBJID="+ulbcode;
			sql = "select c.I_RWOBJID as rwobjid,c.VC_RWDESC as rwdesc,d.I_BLCKOBJID as blckid ,d.VC_BLCKDESC as blckdesc,b.I_ZONEOBJID as zoneid,b.VC_ZONEDESC as zonedesc  from CT_LCTY_MAP_TBL a,CT_ZONE_MSTR_TBL b,CT_RW_MSTR_TBL c,CT_BLCK_MSTR_TBL d where I_LCTYOBJID  in (select I_LCTYOBJID from CT_LCTY_MSTR_TBL) AND a.I_LCTYOBJID="
					+ localityid
					+ " and a.I_ZONEOBJID=b.I_ZONEOBJID and a.I_RWOBJID=c.I_RWOBJID and a.I_BLCKOBJID=d.I_BLCKOBJID and a.c_delflag = 'N' and a.i_ulbobjid="
					+ ulbcode;

			// System.out.println("get Block from Ward  sql "+sql);
			logger.info(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("blckid");
				String Category = rs.getString("blckdesc");
				// System.out.println(">>>cat  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();

			// TODO: handle exception
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	// Block

	@RequestMapping(value = "/getElecward", method = RequestMethod.POST)
	public @ResponseBody String getSearchElecwardvalues(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode get zone >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is "+con);
			// sql="select I_ZONEOBJID,VC_ZONEDESC from ct_zone_mstr_tbl where   C_DELFLAG='N' and  I_ULBOBJID="+ulbcode;
			sql = "select I_EWOBJID,VC_ELCNWARDDESC from ct_elcnward_mstr_tbl where C_DELFLAG='N'  and I_ULBOBJID="
					+ ulbcode;
			// System.out.println("get elec from block  sql "+sql);
			logger.info(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_EWOBJID");
				String Category = rs.getString("VC_ELCNWARDDESC");
				// System.out.println(">>>cat  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();

			// TODO: handle exception
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getbcls", method = RequestMethod.POST)
	public @ResponseBody String getSearchbclsvalues(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>> " + ulbcode);
			logger.shutdown();
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is"+con);
			sql = "select I_CLSCODE,VC_CLSDESC from pt_bldgcls_mstr_tbl  where   C_DELFLAG='N' and  I_ULBOBJID="
					+ ulbcode;
			// System.out.println("sql "+sql);
			// logger.info(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_CLSCODE");
				String Category = rs.getString("VC_CLSDESC");
				// System.out.println(">>>I_CLSCODE  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();

			// TODO: handle exception
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	// zoneid:zoneid,
	// ulbcode : ulbid

	// select
	// i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid
	// from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and
	// i_bldgusecode="+i_bldgusecode+" and I_ULBOBJID=
	@RequestMapping(value = "/getusage", method = RequestMethod.POST)
	public @ResponseBody String getSearchusagevalues(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {
			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			// System.out.println("Connectoin is"+con);
			sql = "select i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and   I_ULBOBJID="
					+ ulbcode;
			// System.out.println("sql "+sql);
			logger.info(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("i_bldgusecode");
				String Category = rs.getString("vc_bldgusedesc");
				// System.out.println(">>>bcls  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			// System.out.println("error "+e);
			logger.info(e);
			logger.shutdown();
		} finally {

			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getlocality", method = RequestMethod.POST)
	public @ResponseBody String getSearchlocalityvalues(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {
			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			try {

				// System.out.println("Connectoin is"+con);
				sql = " select I_LCTYOBJID,VC_LCTYNAME from  CT_LCTY_MSTR_TBL where c_delFlag='N' and   I_ULBOBJID="
						+ ulbcode;
				// System.out.println("sql get locality from ulbcode  "+sql);
				logger.info(sql);
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					String CategoryID = rs.getString("I_LCTYOBJID");
					String Category = rs.getString("VC_LCTYNAME");
					// System.out.println(">>>lcity  "+CategoryID);
					CategoryMap.put(CategoryID, Category);
					// JCategory = gson.toJson(CategoryMap);
				}

			} catch (Exception e) {
				// System.out.println("----------- "+e.getMessage());
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			System.out.println("error " + e);
			logger.info(e);
			logger.shutdown();
		} finally {
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getstreet", method = RequestMethod.POST)
	public @ResponseBody String getSearchstreetvalues(
			@FormParam("ulbcode") String ulbcode,
			@FormParam("localityid") String localityid,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {
			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			System.out.println("Connectoin is" + con);
			sql = "  select  I_STRTOBJID,VC_STRTNAME  from ct_strt_dtls_tbl where C_DELFLAG='N'  and I_ULBOBJID="
					+ ulbcode + " and I_LCTYOBJID=" + localityid;
			System.out.println("sql " + sql);
			logger.info(sql);
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_STRTOBJID");
				String Category = rs.getString("VC_STRTNAME");
				System.out.println(">>>Street  " + CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			System.out.println("error " + e);
			logger.info(e);
			logger.shutdown();
		} finally {
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	// select * from pt_ocpntype_mstr_tbl
	@RequestMapping(value = "/getocpnt.do", method = RequestMethod.POST)
	public @ResponseBody String getSearchocpntvalues(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {

			logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			System.out.println("Connectoin is" + con);
			sql = "select I_OCPNTYPECODE,VC_OCPNTYPEDESC from pt_ocpntype_mstr_tbl where c_delFlag='N' ";
			// System.out.println("sql "+sql);
			logger.info(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_OCPNTYPECODE");
				String Category = rs.getString("VC_OCPNTYPEDESC");
				// System.out.println(">>>I_OCPNTYPECODE  "+CategoryID);
				CategoryMap.put(CategoryID, Category);
				// JCategory = gson.toJson(CategoryMap);
			}
			// System.out.println(CategoryMap);
			JCategory = gson.toJson(CategoryMap);
			// System.out.println(">>. "+JCategory);
		} catch (Exception e) {
			System.out.println("error " + e);
			logger.info(e);
			logger.shutdown();
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		logger.info(sql);
		logger.shutdown();
		return JCategory;

	}

	@RequestMapping("/calculatetax.do")
	public ModelAndView calculate(
			@ModelAttribute("Taxcalservicebean") Taxcalservicebean taxcalservicebean,
			@QueryParam("filenumber") String filenumber,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, DataAccessResourceFailureException,
			HibernateException, IllegalStateException, SQLException,
			RemoteException {
		try {
			

			String i_ulbid = request.getParameter("taxUlb");
			String planaprvl = request.getParameter("planaprvl");
			int i_zoneobjid = Integer.parseInt(request.getParameter("zone"));
			int i_clscode = Integer.parseInt(request.getParameter("bcls"));
			int i_bldgusecode = Integer
					.parseInt(request.getParameter("busage"));
			int i_lctyobjid = Integer
					.parseInt(request.getParameter("locality"));
			int i_ocpntypecode = Integer.parseInt(request.getParameter("octy"));

			double l = Double.parseDouble(request.getParameter("len"));
			double w = Double.parseDouble(request.getParameter("wid"));
			int buildage = Integer.parseInt(request.getParameter("bage"));

			HttpSession s = request.getSession(true);
			double d_ur = 0.0;
			double D_DEPRATE = 0.0;
			double ptResrate = 0, ptNResrate = 0;

			double pt = 0.0, lcs = 0.0, et = 0.0, uau = 0.0, tnarv = 0.0;
			int ii_bldgage = 0;
			double d_plntarea = 0.0;
			double lcsp = 0, etp = 0, uaup = 0;
			int i_bldgage = 0, flrno = 0;
			// String IS_PLANAPRVD="";
			double ptf = 0, etf = 0, lcsf = 0, uauf = 0;
			double D_FXDARV = 0.0, D_FXDPT = 0.0, D_FXDED = 0, D_FXDLCS = 0, D_FXDUNAUTHPLNPLTY = 0, TOTTAX = 0.0;

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

			d_plntarea = l * w;

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
			System.out.println("Unit Rate " + d_ur);
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);

			sql2 = "select D_DEPRATE from  PT_DEPRATE_MSTR_TBL  WHERE  I_ULBOBJID="
					+ i_ulbid
					+ " and  I_FROMBLDGAGE<="
					+ buildage
					+ " and I_TOBLDGAGE>="
					+ buildage
					+ " and C_DELFLAG='N'and I_OCPNTYPECODE=" + i_ocpntypecode;
			ps2 = con.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			if (rs2.next()) {
				D_DEPRATE = rs2.getDouble("D_DEPRATE");
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
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Property Tax For Non Residence")) {
					ptNResrate = rs5.getDouble("D_TAXRATE");
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Education Tax")) {
					etp = rs5.getDouble("D_TAXRATE");
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Library Cess")) {
					lcsp = rs5.getDouble("D_TAXRATE");
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Penalty on UA Construction")) {
					uaup = rs5.getDouble("D_TAXRATE");
				}
			}

			double MRV = Math.round(d_ur * d_plntarea);
			double BV = Math.round(MRV * (2.0 / 3.0));
			System.out.println("BV " + BV);
			double SV = Math.round(MRV * (1.0 / 3.0));
			System.out.println("SV " + SV);
			double NARV = 0.0;
			double GARV = Math.round((BV) * 12.0);
			double DEP = Math.round(GARV * (D_DEPRATE / 100));
			System.out.println("MRV " + MRV + " GARV " + GARV + " DEP " + DEP);
			NARV = (SV) * (12.0) + (GARV - DEP);

			if (planaprvl.equalsIgnoreCase("Y")) {

				uaup = 0;
			} else {
				uaup = 100;
			}

			System.out.println("planaprvl " + planaprvl + " UAC " + uaup);

			System.out.println("res>>" + c_resflag);

			if (c_resflag.equalsIgnoreCase("Y")) {
				pt = (Math.round(NARV) * (ptResrate / 100));
			} else if (c_resflag.equalsIgnoreCase("N")) {
				pt = (Math.round(NARV) * (ptNResrate / 100));
			}

			System.out.println("ptNResrate " + ptNResrate + "NARV " + NARV);
			et = (Math.round(NARV) * (etp / 100));
			lcs = (Math.round((pt + et)) * (lcsp / 100));
			uau = Math.round((pt + et + lcs) * (uaup / 100));
			System.out.println("Lcf " + lcs);
			System.out.println("Uac " + uau + " pt " + pt);
			tnarv += NARV;
			pt = Math.round(pt + lcs + uau);

			System.out.println("----------------" + pt);
			System.out.println("flrno>>>" + flrno + "  NARV>>> " + NARV
					+ " uaup>>>> " + uaup + "  ptf>>>> " + pt + "  etf>>> "
					+ et + "  lcsf>>> " + lcs + "  uauf>>>" + uau + "pt>>>"
					+ pt + "ptResrate>>" + ptResrate + "ptNResrate"
					+ ptNResrate);
			s.setAttribute("Dist", Dist);
			s.setAttribute("ULBname", ULBname);
			s.setAttribute("locality", locality);
			s.setAttribute("bage", buildage);
			s.setAttribute("len", l);
			s.setAttribute("wid", w);
			s.setAttribute("zname", zonename);
			s.setAttribute("cname", clsname);
			s.setAttribute("uname", useagename);
			s.setAttribute("ocptype", ocptype);
			s.setAttribute("nrv", tnarv);
			s.setAttribute("ptp", pt);

			if (pt == 0.0) {
				request.setAttribute("Checkflag", "Y");
				request.setAttribute("errormsg",
						"Unit Rate is not fixed for this combination in "
								+ ULBname + " ULB");
			}

			Integer ULB_CODE=Integer.parseInt(i_ulbid);
			//String districtcode = assessmentService.getDistCodeByUlbName(ULB_CODE);
			Ulbs u=ulbDAOimpl.getulbnameByUlbsCode(ULB_CODE);
			//getulbnameByUlbsCode(int ulbcode)
			//String districtcode = String.valueOf(DistId);
			//System.out.println("&&&&&&&&&&&&   districtcode="+districtcode);
			taxcalservicebean.setDistrictcode(String.valueOf(u.getDistrict_id()));
			taxcalservicebean.setI_bldgusecode(String.valueOf(i_bldgusecode));
			taxcalservicebean.setReturnstatus('N');
			taxcalservicebean.setI_ulbid(i_ulbid);
			taxcalservicebean.setDelflag('N');
			taxcalservicebean.setI_zoneobjid(String.valueOf(i_zoneobjid));
			taxcalservicebean.setI_clscode(String.valueOf(i_clscode));
			taxcalservicebean.setI_lctyobjid(String.valueOf(i_lctyobjid));
			taxcalservicebean.setI_ocpntypecode(String.valueOf(i_ocpntypecode));
			taxcalservicebean.setPlintharea(10);//Constant for testing purpose 
			taxcalservicebean.setEstimatedtax(pt);
			taxcalservicebean.setArv(GARV);
			
			
			boolean flag1 = assessmentService.saveDet(taxcalservicebean);
			return new ModelAndView("result");
		} catch (Exception e) {
			System.out.println("e " + e);
			return new ModelAndView("gettax");
		} finally {
			ps.close();
			ps1.close();
			ps2.close();
			ps3.close();
			ps4.close();
			ps5.close();
			ps6.close();
			ps7.close();
			ps8.close();
			rs.close();
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			rs5.close();
			rs6.close();
			rs7.close();
			rs8.close();
			con.close();
		}
	}

	@RequestMapping(value = "/calculatetaxservices.do", method = RequestMethod.POST)
	@ResponseBody
	public String calculateServices(
			@ModelAttribute("taxcalservicebean") Taxcalservicebean taxcalservicebean,
			@QueryParam("i_ulbid") String i_ulbid,

			@QueryParam("i_zoneobjid") String i_zoneobjid,
			@QueryParam("i_clscode") String i_clscode,
			@QueryParam("i_bldgusecode") String i_bldgusecode,
			@QueryParam("i_lctyobjid") String i_lctyobjid,
			@QueryParam("i_ocpntypecode") String i_ocpntypecode,
			@QueryParam("length") String length,
			@QueryParam("width") String width,
			@QueryParam("filenumber") String filenumber,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, DataAccessResourceFailureException,
			HibernateException, IllegalStateException, SQLException,
			RemoteException {
		// Assgin Values

		try {

			java.io.InputStream dsnInStream = this.getClass().getClassLoader()
					.getResourceAsStream("login.properties");

			Properties properties = new Properties();
			properties.load(dsnInStream);
			String serviceEnable = properties.getProperty("DPMSservice");

			if (serviceEnable.equalsIgnoreCase("Y")) {
				System.out
						.println("-----------service enable-----------------------");
				response.setStatus(401);
				return "{\"error\":Access Denied\"}";
			}

		} catch (Exception e) {
			response.setStatus(401);
			return "{\"error\":Access Denied\"}";
		}

		// System.out.println(i_zoneobjid+" - "+
		// i_clscode+" - "+i_bldgusecode+" - "+i_lctyobjid+" - "+i_ocpntypecode+" - "+length+" - "+width+" - "+filenumber);

		String planaprvl = "Y";
		int buildingage = 0;

		/************* Check data validation **************************/
		if (validatecheck(i_ulbid) == false
				|| validatecheck(i_zoneobjid) == false
				|| validatecheck(i_clscode) == false
				|| validatecheck(i_bldgusecode) == false
				|| validatecheck(i_lctyobjid) == false ||
				/* validatecheck(i_ocpntypecode)==false|| */
				validatecheck(i_ocpntypecode) == false
				|| validatecheck(length) == false
				|| validatecheck(width) == false
				|| validatecheck(filenumber) == false) {
			System.out
					.println("-------------------------------------------------------");
			response.setStatus(403);
			return "{\"error\":Something went wrong Please try Again...!\"}";

		}

		try {

			System.out.println("welcome>>>>>>>>>>>>>>>>");

			String JCategory = "";
			Map CategoryMap = new HashMap<>();

			HttpSession s = request.getSession(true);
			double d_ur = 0.0;
			double D_DEPRATE = 0.0;
			double ptResrate = 0, ptNResrate = 0;

			double pt = 0.0, lcs = 0.0, et = 0.0, uau = 0.0, tnarv = 0.0;
			int ii_bldgage = 0;
			double d_plntarea = 0.0;
			double lcsp = 0, etp = 0, uaup = 0;
			int i_bldgage = 0, flrno = 0;
			// String IS_PLANAPRVD="";
			double ptf = 0, etf = 0, lcsf = 0, uauf = 0;
			double D_FXDARV = 0.0, D_FXDPT = 0.0, D_FXDED = 0, D_FXDLCS = 0, D_FXDUNAUTHPLNPLTY = 0, TOTTAX = 0.0;

			String blduse = null, ptname = null, c_resflag = null;
			String C_SPRSTRU = null;
			Date aplnDate = null;
			// String zonename = null;
			// String clsname = null;
			// String useagename = null;
			// String ocptype = null;
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

			d_plntarea = Double.parseDouble(length) * Double.parseDouble(width);

			/*
			 * sql6 =
			 * "select VC_ZONEDESC from ct_zone_mstr_tbl where I_ZONEOBJID=" +
			 * i_zoneobjid + " and I_ULBOBJID=" + i_ulbid; ps6 =
			 * con.prepareStatement(sql6); rs6 = ps6.executeQuery(); while
			 * (rs6.next()) { zonename = rs6.getString("VC_ZONEDESC"); }
			 */
			/*
			 * sql7 =
			 * "select I_CLSCODE,VC_CLSDESC from pt_bldgcls_mstr_tbl  where   C_DELFLAG='N' and  I_CLSCODE="
			 * + i_clscode + " and I_ULBOBJID=" + i_ulbid;
			 * 
			 * ps7 = con.prepareStatement(sql7); rs7 = ps7.executeQuery(); while
			 * (rs7.next()) { clsname = rs7.getString("VC_CLSDESC"); }
			 */

			/*
			 * sql8 =
			 * " select I_OCPNTYPECODE,VC_OCPNTYPEDESC from pt_ocpntype_mstr_tbl where c_delFlag='N' and  I_OCPNTYPECODE="
			 * + i_ocpntypecode; ps8 = con.prepareStatement(sql8); rs8 =
			 * ps8.executeQuery(); while (rs8.next()) { ocptype =
			 * rs8.getString("VC_OCPNTYPEDESC"); }
			 */
			// select a.VC_ULBNAME,b.VC_DSTNAME from CT_ULB_MSTR_TBL
			// a,CT_DST_MSTR_TBL b where a.I_DSTOBJID=b.I_DSTOBJID and
			// a.I_ULBOBJID=1113

			// charan written
			int ulbcode = Integer.parseInt(i_ulbid);
			/*
			 * Ulbs distId = ulbService.getByUlbname(ulbcode);
			 * 
			 * int DistId = distId.getDistrict_id();
			 */
			Ulbs distId = assessmentService.getulbByCode(ulbcode);
			int DistId = distId.getDistrict_id();
			System.out.println("-->>>" + distId);
			// int DistId = 3;

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

			// System.out.println("select d_ur from Pt_ur_mstr_tbl where i_zoneobjid="+i_zoneobjid+" and i_clscode="+i_clscode+" and i_bldgusecode="+i_bldgusecode+" and i_ulbobjid="+i_ulbid+" and c_delflag='N'");
			sql = "select d_ur from Pt_ur_mstr_tbl where i_zoneobjid="
					+ i_zoneobjid + " and i_clscode=" + i_clscode
					+ " and i_bldgusecode=" + i_bldgusecode
					+ " and i_ulbobjid=" + i_ulbid + " and c_delflag='N'";
			ps = con.prepareStatement(sql);
			// System.out.println("sql--->>>"+sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				d_ur = rs.getDouble("d_ur");
				// System.out.println("d_ur>>"+d_ur);
			}

			// System.out.println(" in foolr no list  loop ");
			// System.out.println("select D_PERREBONUR,D_FXDREBONUR from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="+i_ulbid);
			sql1 = "select D_PERREBONUR,D_FXDREBONUR from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
					+ i_ulbid;
			// System.out.println("--< "+sql1);
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
			System.out.println("Unit Rate " + d_ur);
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);

			sql2 = "select D_DEPRATE from  PT_DEPRATE_MSTR_TBL  WHERE  I_ULBOBJID="
					+ i_ulbid
					+ " and  I_FROMBLDGAGE<="
					+ buildingage
					+ " and I_TOBLDGAGE>="
					+ buildingage
					+ " and C_DELFLAG='N'and I_OCPNTYPECODE=" + i_ocpntypecode;
			ps2 = con.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			if (rs2.next()) {
				D_DEPRATE = rs2.getDouble("D_DEPRATE");
				// System.out.println("D_DEPRATE>>"+D_DEPRATE);
			}
			// System.out.println(" Deprate  sri "+D_DEPRATE);
			// --
			// System.out.println("select C_SEESHOREFLG,D_SEESHOREPER  from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="+i_ulbid);
			sql3 = "select C_SEESHOREFLG,D_SEESHOREPER  from  pt_ulb_setup_tbl where c_delflag='N' and i_ulbobjid="
					+ i_ulbid;
			ps3 = con.prepareStatement(sql3);
			rs3 = ps3.executeQuery();
			if (rs3.next()) {
				if (rs3.getString("C_SEESHOREFLG").equals("Y")) {
					D_DEPRATE = D_DEPRATE + rs3.getDouble("D_SEESHOREPER");
					// System.out.println("rs11.getDouble(D_SEESHOREPER)  :  "+rs5.getDouble("D_SEESHOREPER"));
				}
			}

			sql4 = "select i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and i_bldgusecode="
					+ i_bldgusecode + " and I_ULBOBJID=" + i_ulbid;
			ps4 = con.prepareStatement(sql4);
			// System.out.println("sql 4 "+sql4);
			rs4 = ps4.executeQuery();
			if (rs4.next()) {
				// useagename = rs4.getString("VC_BLDGUSEDESC");
				blduse = rs4.getString("VC_BLDGUSETYPE");
				// System.out.println(" VC_BLDGUSETYPE "+blduse);
				c_resflag = rs4.getString("c_resflag");
			}
			// System.out.println("res "+c_resflag);

			if (blduse.equalsIgnoreCase("Residence")) {
				ptname = "Property Tax For Residence";
			} else {
				ptname = "Property Tax For Non Residence";
			}
			// System.out.println("ptname>>"+ptname);

			System.out.println("Dep rate is 22:  " + D_DEPRATE);

			// System.out.println("<<ptrate>>select VC_TAXRATETYPE,D_TAXRATE from pt_taxrate_mstr_tbl where  i_ulbobjid="+i_ulbid+" and c_delflag='N'");
			sql5 = "select VC_TAXRATETYPE,D_TAXRATE  from pt_taxrate_mstr_tbl where  i_ulbobjid="
					+ i_ulbid + " and c_delflag='N'";
			ps5 = con.prepareStatement(sql5);
			rs5 = ps5.executeQuery();
			// System.out.println("sql 5 "+sql5);
			while (rs5.next()) {
				// System.out.println("rs type "+rs7.getString("VC_TAXRATETYPE"));

				if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Property Tax For Residence")) {
					ptResrate = rs5.getDouble("D_TAXRATE");
					// //
					// System.out.println("Residence>>"+ptname+"ptrate>>"+ptResrate);
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Property Tax For Non Residence")) {
					ptNResrate = rs5.getDouble("D_TAXRATE");
					// //System.out.println("Non Residence>>"+ptname+"ptrate>>"+ptResrate);
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Education Tax")) {
					etp = rs5.getDouble("D_TAXRATE");
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Library Cess")) {
					lcsp = rs5.getDouble("D_TAXRATE");
				} else if (rs5.getString("VC_TAXRATETYPE").equalsIgnoreCase(
						"Penalty on UA Construction")) {
					uaup = rs5.getDouble("D_TAXRATE");
				}
			}
			System.out.println("Residence>>" + ptname + "ptrate>>" + ptResrate
					+ " Non " + ptNResrate);
			System.out.println("uaup " + uaup + " d_ur " + d_ur
					+ " d_plntarea " + d_plntarea);

			double MRV = Math.round(d_ur * d_plntarea);
			double BV = Math.round(MRV * (2.0 / 3.0));
			System.out.println("BV " + BV);
			double SV = Math.round(MRV * (1.0 / 3.0));
			System.out.println("SV " + SV);
			double NARV = 0.0;
			double GARV = Math.round((BV) * 12.0);
			double DEP = Math.round(GARV * (D_DEPRATE / 100));
			System.out.println("MRV " + MRV + " GARV " + GARV + " DEP " + DEP);
			NARV = (SV) * (12.0) + (GARV - DEP);

			if (planaprvl.equalsIgnoreCase("Y")) {

				uaup = 0;
			} else {
				uaup = 100;
			}

			// System.out.println("planaprvl " + planaprvl + " UAC " + uaup);

			// System.out.println("res>>" + c_resflag);

			if (c_resflag.equalsIgnoreCase("Y")) {
				pt = (Math.round(NARV) * (ptResrate / 100));
			} else if (c_resflag.equalsIgnoreCase("N")) {
				pt = (Math.round(NARV) * (ptNResrate / 100));
			}

			// System.out.println("ptNResrate " + ptNResrate + "NARV " + NARV);
			et = (Math.round(NARV) * (etp / 100));
			lcs = (Math.round((pt + et)) * (lcsp / 100));
			uau = Math.round((pt + et + lcs) * (uaup / 100));
			// System.out.println("Lcf " + lcs);
			// System.out.println("Uac " + uau + " pt " + pt);
			tnarv += NARV;
			pt = Math.round(pt + lcs + uau);

			// pt+=ptf;
			// et+=etf;
			// lcs+=lcsf;
			// uau+=uauf;
			// boolean flag = ulbService.save(taxcalservicebean);
			System.out.println("---------------->>>" + pt);
			// System.out.println("flrno>>>" + flrno + "  NARV>>> " + NARV
			// + " uaup>>>> " + uaup + "  ptf>>>> " + pt + "  etf>>> "
			// + et + "  lcsf>>> " + lcs + "  uauf>>>" + uau + "pt>>>"
			// + pt + "ptResrate>>" + ptResrate + "ptNResrate"
			// + ptNResrate);

			s.setAttribute("Dist", Dist);
			s.setAttribute("ULBname", ULBname);
			s.setAttribute("locality", locality);
			s.setAttribute("bage", buildingage);
			s.setAttribute("len", length);
			s.setAttribute("wid", width);
			// s.setAttribute("zname", zonename);
			// s.setAttribute("cname", clsname);
			// s.setAttribute("uname", useagename);

			// s.setAttribute("ocptype", ocptype);
			s.setAttribute("nrv", tnarv);
			s.setAttribute("ptp", pt);
			// s.setAttribute("plintharea", d_plntarea);
			// s.setAttribute(arg0, arg1);

			if (pt == 0.0) {

				response.setStatus(403);
				return "{\"error\":Unit Rate is not fixed for this combination in "
						+ ULBname + " ULB...!\"}";
			}

			// Taxcalservicebean taxcalservicebean=new Taxcalservicebean(Dist,
			// ULBname, filenumber, i_ulbid,d_plntarea, tnarv, pt);
			// charan written
			double plintharea = d_plntarea;
			System.out.println("---->" + plintharea);
			taxcalservicebean.setPlintharea(plintharea);
			taxcalservicebean.setEstimatedtax(pt);
			taxcalservicebean.setArv(GARV);
			String distId1 = String.valueOf(DistId);
			taxcalservicebean.setDistrictcode(distId1);
			Character del_flag = 'N';
			Character return_status = 'N';
			taxcalservicebean.setDelflag(del_flag);
			taxcalservicebean.setReturnstatus(return_status);
			taxcalservicebean.setFilenumber(filenumber);
			JCategory = gson.toJson(taxcalservicebean);

			System.out.println("before saving");
			// ulbService.save(taxcalservicebean);
			boolean flag1 = assessmentService.saveDet(taxcalservicebean);
			System.out.println("result for insertion" + flag1);
			System.out.println("after saving");

			return JCategory;

		} catch (Exception e) {
			response.setStatus(403);
			return "{\"error\":Something went wrong Please try Again****...!\"}";
		} finally {
			ps.close();
			ps1.close();
			ps2.close();
			ps3.close();
			ps4.close();
			ps5.close();
			/*
			 * ps6.close(); ps7.close(); ps8.close();
			 */
			rs.close();
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			rs5.close();
			/*
			 * rs6.close(); rs7.close(); rs8.close();
			 */
			con.close();
		}

	}

	public boolean validatecheck(String data) {

		boolean retunvalue = false;

		if (data.length() == 0 || data == "" || data.equals(null)
				|| data.trim().length() == 0) {

			retunvalue = false;
		} else {
			retunvalue = true;
		}

		return retunvalue;
	}

	public String m() throws IOException {

		java.io.InputStream dsnInStream = this.getClass().getClassLoader()
				.getResourceAsStream("login.properties");

		if (dsnInStream != null) {
			Properties properties = new Properties();
			System.out.println(dsnInStream.toString());
			properties.load(dsnInStream);
			String str = properties.getProperty("DPMSservice");
			System.out.println("Str -- " + str);

		} else {

			System.out.println("else");
		}

		return "success";
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		String sDate1 = "31/12/1998";
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		System.out.println("date1 " + date1);
	}

	// charan written

	@RequestMapping(value = "/dtcpapplservices.do", method = RequestMethod.POST)
	@ResponseBody
	public String dtcpAppicationServices(
			@ModelAttribute("dtcpApplication") DtcpApplication dtcpApplication,
			@QueryParam("surname") String surname,

			@QueryParam("ownername") String ownername,
			@QueryParam("relationname") String relationname,
			@QueryParam("relationsurname") String relationsurname,
			@QueryParam("aadharnumber") String aadharnumber,
			@QueryParam("ownercity") String ownercity,
			@QueryParam("mobilenumber") String mobilenumber,
			@QueryParam("length") String length,
			@QueryParam("width") String width,
			@QueryParam("reg_date") String reg_date,
			@QueryParam("occupantname") String occupantname,
			@QueryParam("occupantsurname") String occupantsurname,
			@QueryParam("ownershiptype") String ownershiptype,
			@QueryParam("districtcode") String districtcode,
			@QueryParam("ulbcode") String ulbcode,
			@QueryParam("localitycode") String localitycode,
			@QueryParam("zonecode") String zonecode,
			@QueryParam("revenue_ward_code") String revenue_ward_code,
			@QueryParam("block_no_code") String block_no_code,
			@QueryParam("building_cls") String building_cls,
			@QueryParam("building_type") String building_type,
			@QueryParam("occupant_type") String occupant_type,
			@QueryParam("dtcpfilenumber") String dtcpfilenumber,

			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, DataAccessResourceFailureException,
			HibernateException, IllegalStateException, SQLException,
			RemoteException, ParseException {

		/************* Set Default Parameters ****************/
		String JCategory = "";
		JCategory = gson.toJson(dtcpApplication);
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		dtcpApplication.setReg_date(new SimpleDateFormat("dd/MM/yyyy")
				.parse(reg_date));
		dtcpApplication.setEntry_date(new Date());
		dtcpApplication.setDelflag('N');
		dtcpApplication.setEmas_status('Y');
		dtcpApplication.setBuilding_plan_aprvl_code('Y');
		dtcpApplication.setBuilding_age(0);
		dtcpApplication.setElectionwardcode(0);
		dtcpApplication.setReg_doc_no(0);
		boolean flag1 = assessmentService.saveDtcpAppl(dtcpApplication);

		return JCategory;
	}

	@RequestMapping(value = "/Test", method = RequestMethod.POST)
	public @ResponseBody String Test(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, JSONException {
		System.out.println("Welcome");

		String JCategory = "welcome";
		Map CategoryMap = new HashMap<>();
		// session.invalidate();

		// System.out.println("ulbcode get zone >>>>From "+ulbcode);
		request.setAttribute("text", "text456456");

		// session.setAttribute("text1","text123457657");

		System.out.println("get session " + session.getAttribute("text1"));

		System.out.println("get Request " + request.getAttribute("text"));
		// System.out.println(CategoryMap);
		// JCategory = gson.toJson(CategoryMap);
		// System.out.println(">>. "+JCategory);

		return JCategory;

	}

	@RequestMapping(value = "/Test1", method = RequestMethod.POST)
	public @ResponseBody String Test1(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException,
			InterruptedException {
		System.out.println("Welcome");

		request.setAttribute("image", "image data loaded");
		Thread th = new Thread();
		th.sleep(3000);
		// request.setAttribute("text","text data");
		String JCategory = "welcome 2";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode get zone >>>>From "+ulbcode);

		// System.out.println(CategoryMap);
		// JCategory = gson.toJson(CategoryMap);
		// System.out.println(">>. "+JCategory);

		return JCategory;

	}

}
