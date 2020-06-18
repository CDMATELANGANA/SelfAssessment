package com.mars.cdma.gov.Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.impl.UlbDAOimpl;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;

@Controller
public class TaxPropertyController {
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
	public String sql = null, sql1 = null, sql2 = null, sql3 = null;
	public PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null,
			ps4 = null;
	public ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null, rs4 = null;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFloorType", method = RequestMethod.POST)
	public @ResponseBody String getFloorTypeByvalue(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {
		String FType = "";
		Map FloorTypeMap = new HashMap<>();
		try {
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			sql = "select I_FLRTYPECODE,VC_FLRTYPEDESC from PT_FLRTYPE_MSTR_TBL where C_DELFLAG='N'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String I_FLRTYPECODE = rs.getString("I_FLRTYPECODE");
				String VC_FLRTYPEDESC = rs.getString("VC_FLRTYPEDESC");
				FloorTypeMap.put(I_FLRTYPECODE, VC_FLRTYPEDESC);
			}
			FType = gson.toJson(FloorTypeMap);
			System.out.println(">>. " + FType);
		} catch (Exception e) {
			System.out.println("error " + e);
		} finally {
			ps.close();
			rs.close();
			con.close();
		}

		return FType;

	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRoofType", method = RequestMethod.POST)
	public @ResponseBody String getRoofTypeByvalue(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {
		String RType = "";
		Map RoofTypeMap = new HashMap<>();
		try {
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			sql = "select I_RFTYPECODE,VC_RFTYPEDESC from PT_RFTYPE_MSTR_TBL where C_DELFLAG='N'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String I_RFTYPECODE = rs.getString("I_RFTYPECODE");
				String VC_RFTYPEDESC = rs.getString("VC_RFTYPEDESC");
				RoofTypeMap.put(I_RFTYPECODE, VC_RFTYPEDESC);
			}
			RType = gson.toJson(RoofTypeMap);
			System.out.println(">>. " + RType);
		} catch (Exception e) {
			System.out.println("error " + e);
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		return RType;

	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getWaalType", method = RequestMethod.POST)
	public @ResponseBody String getWaalTypeByvalue(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {
		String WType = "";
		Map WaalTypeMap = new HashMap<>();
		try {
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			sql = "select I_WALLTYPECODE,VC_WALLTYPEDESC from PT_WALLTYPE_MSTR_TBL where C_DELFLAG='N'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String I_WALLTYPECODE = rs.getString("I_WALLTYPECODE");
				String VC_WALLTYPEDESC = rs.getString("VC_WALLTYPEDESC");
				WaalTypeMap.put(I_WALLTYPECODE, VC_WALLTYPEDESC);
			}
			WType = gson.toJson(WaalTypeMap);
			System.out.println(">>. " + WType);
		} catch (Exception e) {
			System.out.println("error " + e);
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		return WType;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getWoodType", method = RequestMethod.POST)
	public @ResponseBody String getWoodTypeByvalue(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {
		String WoodType = "";
		Map WoodTypeMap = new HashMap<>();
		try {
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			sql = "select I_WOODTYPECODE,VC_WOODTYPEDESC from PT_WOODTYPE_MSTR_TBL where C_DELFLAG='N'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String I_WOODTYPECODE = rs.getString("I_WOODTYPECODE");
				String VC_WOODTYPEDESC = rs.getString("VC_WOODTYPEDESC");
				WoodTypeMap.put(I_WOODTYPECODE, VC_WOODTYPEDESC);
			}
			WoodType = gson.toJson(WoodTypeMap);
			System.out.println(">>. " + WoodType);
		} catch (Exception e) {
			System.out.println("error " + e);
		} finally {
			ps.close();
			rs.close();
			con.close();
		}
		return WoodType;

	}
	
}
