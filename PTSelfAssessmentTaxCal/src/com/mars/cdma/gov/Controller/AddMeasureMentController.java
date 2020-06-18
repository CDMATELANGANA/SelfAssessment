package com.mars.cdma.gov.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.impl.AddAsmntMeasurementDaoImpl;
import com.mars.cdma.gov.Dao.impl.AssessmentDAOImpl;
import com.mars.cdma.gov.bean.AddAsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AddAssessmentMaster;
import com.mars.cdma.gov.bean.AddMeasurementTransactionHistory;
import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
import com.mars.cdma.gov.in.reset.Taxcalreset;
import com.mars.cdma.gov.service.AddAsmntMeasurementService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;
import com.mars.cdma.gov.transaction.BillDeskGateWayMac;
import com.mars.cdma.gov.utils.CommonUtils;

@Controller
public class AddMeasureMentController {
	// static Logger logger = Logger.getLogger(Taxcalreset.class.getName());
	private static final Logger log = Logger
			.getLogger(AddMeasureMentController.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DistrictsService districtsService;

	@Autowired
	private AddAsmntMeasurementService addAssessmentservice;
	@Autowired
	private UlbsService ulbService;
	
	@Autowired
	private Assessmentservice assessmentservice;
	
	Connection con = null;
	public Gson gson = new Gson();
	@Autowired
	private MessageSource msgSrc;
	public String sql = null;
	
	SMSHttpPostClient sendsms = new SMSHttpPostClient();
	String citizenMsg;

	@RequestMapping(value = "/saveAddMeasurementAsmtDetail.do", method = RequestMethod.POST)
	public ModelAndView saveAddMeasurementAsmtDetail(
			@ModelAttribute("applicationForm") AddAssessmentMaster addAssessmentMaster,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		System.out.println("addAssessmentMaster"
				+ addAssessmentMaster.getUniqueRequestNumber());

		// Integer ulbcode=1181;
		System.out.println("ULB::::" + addAssessmentMaster.getUlbcode());
		long Uniqueseq = (addAssessmentMaster.getUlbcode() + Calendar
				.getInstance().getTimeInMillis());
		String UniqueNumber = addAssessmentMaster.getUlbcode() + "" + Uniqueseq;
		// String UniqueNumber = addAssessmentMaster.getUlbcode() + "" +
		// Uniqueseq;
		addAssessmentMaster
				.setUniqueRequestNumber(Long.parseLong(UniqueNumber));
		addAssessmentMaster.setDocEnclosed('N');
		String msg = "";
		MultipartHttpServletRequest m = (MultipartHttpServletRequest) request;
		MultipartFile[] file = { m.getFile("file1"), m.getFile("file2"),
				m.getFile("file3") };
		long fileSize = 0;
		String fname = "";
		for (int i = 0; i < file.length; i++) {
			try {
				fileSize = file[i].getSize();
				fname = file[i].getName();
			} catch (Exception e) {
				fileSize = 0;
				fname = null;
			}
			if (fname != null && fileSize != 0) {

				System.out.println("Path>>> 1111 "
						+ addAssessmentMaster.getDoc_path() + " File>>> "
						+ fname + " path " + addAssessmentMaster.getUlbcode()
						+ " file.getSize() " + fileSize);

				addAssessmentMaster.setDocEnclosed('S');
				// String path ="C:\\MesevaUsersDocumets"+"\\"+
				// application.getTradeUlb() +"\\";

				// String path
				// ="C:\\MesevaUsersDocumets"+"\\0000\\"+UniqueNumber;
				String path = "C:\\MesevaUsersDocumets" + "\\"
						+ addAssessmentMaster.getUlbcode() + "\\"
						+ UniqueNumber;
				File f = new File(path);
				if (!f.exists()) {
					boolean c = f.mkdirs();
					System.out.println("FLAG FILE " + c + " PATH= " + path);
				}
				String filename = file[i].getOriginalFilename();
				System.out.println(path + " " + filename);
				byte[] bytes = file[i].getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path + File.separator
								+ filename)));
				stream.write(bytes);
				stream.flush();
				stream.close();
				addAssessmentMaster.setDoc_path(path);
				System.out.println("save Method");

				/****************** Prepare TO send FTP Server **************************/
				// String ulbID="0000";
				// String ulbID = "" + addAssessmentMaster.getUlbcode();
				String ulbID = "" + addAssessmentMaster.getUlbcode();
				String docName = filename;
				String MAIN_FOLDER_PATH = "C:\\MesevaUsersDocumets";
				String FULL_PATH = MAIN_FOLDER_PATH + "/" + ulbID;
				String folderName = ""
						+ addAssessmentMaster.getUniqueRequestNumber();
				StringBuffer ftPUploadPath = new StringBuffer();
				File allAcksDir = new File(FULL_PATH, "/" + folderName);
				System.out.println("pdf " + allAcksDir + " >>> " + ulbID);
				File pdfOutPath = null;
				try {
					pdfOutPath = new File(allAcksDir, docName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				InputStream is = new FileInputStream(pdfOutPath);

				ftPUploadPath.append("/" + ulbID + "/" + folderName + "/"
						+ docName);
				String pathchk = "/" + ulbID + "/" + folderName + "/";
				// sendToFTP(ftPUploadPath.toString(), is, pathchk);

				// sendToFTP(path, is, path);
			} else {
				String folderName = ""
						+ addAssessmentMaster.getUniqueRequestNumber();
				String ulbID = "" + addAssessmentMaster.getUlbcode();
				// String ulbID = "" + ulbcode;
				String pathchk = "/" + ulbID + "/" + folderName + "/";
				FTPClient client = new FTPClient();
				client.connect("10.3.3.83"); // Password >>>password@123
				client.enterLocalPassiveMode();
				client.login("cdma-user1", "password@123");

				client.setFileType(FTP.BINARY_FILE_TYPE);
				client.setFileTransferMode(FTP.BINARY_FILE_TYPE);

				if (!client.changeWorkingDirectory(pathchk)) {
					// System.out.println("***************/////////" + pathchk);
					client.makeDirectory(pathchk);
				}
			}
		}
		System.out.println("Before Measurement");
		Set<AddAsmtMeasurementMaster> amSet = new HashSet<>();
		String[] bcls = addAssessmentMaster.getBcls().split(",");
		System.out.println("all bcls===>" + String.valueOf(bcls));
		String[] busage = addAssessmentMaster.getBusage().split(",");
		String[] ocity = addAssessmentMaster.getOcty().split(",");
		String[] bage = addAssessmentMaster.getBage().split(",");
		String[] wid = addAssessmentMaster.getWid().split(",");
		String[] len = addAssessmentMaster.getLen().split(",");
		String[] floorNo = addAssessmentMaster.getFloorNo().split(",");
		String[] planaprvl = addAssessmentMaster.getPlanaprvl().split(",");
		String[] cPlinth = addAssessmentMaster.getcPlinth().split(",");
		String[] usageType = addAssessmentMaster.getUsageType().split(",");
		System.out.println("bage==" + bage);
		System.out.println("bcls ::::::" + bcls.length);
		System.out.println("bage ::::::::::" + bage.length);
		int j = 0;
		for (int i = 0; i < bage.length; i++) {
			System.out.println("enter in iterate (bcls[i]" + bcls[i]
					+ "I::::  " + i + " bcls.length ::::" + bcls.length);
			if (!(bcls[i].equalsIgnoreCase(""))
					&& !(busage[i].equalsIgnoreCase(""))
					&& !(ocity[i].equalsIgnoreCase(""))) {
				AddAsmtMeasurementMaster am = new AddAsmtMeasurementMaster();
				am.setBcls(bcls[i]);
				am.setBusage(busage[i]);
				am.setOcty(ocity[i]);
				am.setBage(bage[i]);
				am.setWid(wid[i]);
				am.setLen(len[i]);
				am.setFloorNo(floorNo[i]);
				am.setPlanAprvl(planaprvl[i]);
				
				if(planaprvl[i].equals("N")){
					am.setcPlinth("0");	
				    am.setUsageType("");
				}
				else{
					am.setcPlinth(cPlinth[j]);
					am.setUsageType(usageType[j]);
					j++;
				}

				amSet.add(am);
				System.out.println("am ::::" + am);
			}
		}
		System.out.println("After MeasureMent");
		addAssessmentMaster.setAddAsmtMeasurementMaster(amSet);
		/*
		 * assessmentMaster.setPaymentamount(assessmentservice
		 * .getTotalPtSelfAssessmentTax(assessmentMaster));
		 */

		/******** Saving the basic details *****/
		addAssessmentMaster.setDelflag('Y');
		// String Length=assessmentMaster.getLen();
		addAssessmentMaster.setApplicatoinstatusflag('N');
		System.out.println(addAssessmentMaster.getOwnerCity());
		System.out.println("addAssessmentMaster::::::: " + addAssessmentMaster);
		boolean flag = addAssessmentservice.save(addAssessmentMaster);

		if (flag == true) {
			Double amount = addAssessmentservice
					.getTotalPtSelfAssessmentTaxForAddMeasurement(addAssessmentMaster);
			System.out.println("amount::::::" + amount);
			addAssessmentMaster.setPaymentamount(amount);
			AddAssessmentMaster am = addAssessmentservice
					.getrecordForAddMeasurement(Long.parseLong(UniqueNumber));
			am.setFixdLcs(addAssessmentMaster.getFixdLcs());
			am.setFixdPT(addAssessmentMaster.getFixdPT());
			am.setNArv(addAssessmentMaster.getNArv());
			am.setD_fxdunauthplnplty(addAssessmentMaster
					.getD_fxdunauthplnplty());
			am.setPlengthArea(addAssessmentMaster.getPlengthArea());
			am.setMarv(addAssessmentMaster.getMarv());
			am.setUnitRate(addAssessmentMaster.getUnitRate());
			am.setPaymentamount(amount);
			addAssessmentservice.save(am);
			// assessmentservice.updatPaymentAmount(amount,UniqueNumber);
			request.setAttribute("UniqueNumber", UniqueNumber);
			request.setAttribute("ownerName",
					addAssessmentMaster.getOwnerName() + " "
							+ addAssessmentMaster.getOwnerSurName());
			request.setAttribute(
					"ownerRelationName",
					addAssessmentMaster.getOwnerFatherHusbandName()
							+ " "
							+ addAssessmentMaster
									.getOwnerFatherHusbandSurName());
			request.setAttribute("OWNERVILLAGE",
					addAssessmentMaster.getOwnerCity());
			request.setAttribute("entrydate",
					addAssessmentMaster.getEntrydate());
			// request.setAttribute("paymentdate",
			// assessmentMaster.getPaymentdate());
			request.setAttribute("PaymentAmount",
					addAssessmentMaster.getPaymentamount());
			// return new ModelAndView("selfassessmentReceipt");

			// return "redirect:/Ack.do?UniqueRequestNumber="+UniqueNumber;
			return new ModelAndView("MeasurementPaySelfPTAssmntAmount");
		} else {
			// request.setAttribute("UniqueNumber", UniqueNumber);
			request.setAttribute("ErrMsg", "failed... Please apply Again...!");
			return new ModelAndView("selfassessmentReceipt");
		}
	}
	/* Digital Payment Gateway Applied*/
@RequestMapping("ptselfAssessmentPayForMeasurement")
	
	/* public ModelAndView paynow(@RequestParam(value = "requestnumber",
	 required = true) long uniqReqNumber, Model model, HttpServletRequest
	  request, HttpServletResponse response) throws IOException {*/
	 
	// Live
	public void paynow(@RequestParam(value = "requestnumber", required = true) long uniqReqNumber,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		AddMeasurementTransactionHistory paymentTransactionHistory = addAssessmentservice
				.inserttransactionForDigital(uniqReqNumber, request, response);

		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionHistory
				.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());
		Long receiptNo = paymentTransactionHistory
				.getPayment_transaction_receipt_id();
		int ulbCode = paymentTransactionHistory.getUlbcode();
		String ulbName = ulbs.getUlbName();
		String districtName = districts.getDistrictName();
		Long uniqueRequestNumber = paymentTransactionHistory
				.getUniqueRequestNumber();
		String uniqueRequestNumbers = "" + uniqueRequestNumber;
	    double amount = paymentTransactionHistory.getTotal_amount();
		//double amount = 1.00;

		String checkSumKey = BillDeskGateWayMac.getCheckSumKey(receiptNo,
				amount, uniqueRequestNumber, ulbCode, ulbName, districtName);
		String msg = "CDMADEPTEL|" + receiptNo + "|NA|" + amount
				+ "|NA|NA|NA|INR|NA|R|cdmadeptel|NA|NA|F|" + ""
				+ uniqueRequestNumbers + "|" + ulbCode + "|" + ulbName + "|"
				+ districtName + "|PTSelfAssessmentTaxCal|NA|NA|" + CommonUtils.getUrl()
				+ "/PTSelfAssessmentTaxCal/paymentconfirmation.do|"
				+ checkSumKey + "";
		System.out.println("msg"+msg);
		log.info("Sending Gateway>> " + msg);
		log.shutdown();
		response.sendRedirect("https://pgi.billdesk.com/pgidsk/PGIMerchantPayment?msg="
				+ msg);
		// return new ModelAndView("redirect:paymentconfirmationForMeasurement.do");
		
	}
//For Test
	// @RequestMapping(value = "paymentconfirmationForMeasurement", method =RequestMethod.GET)
	// For Live
	@RequestMapping(value = "paymentconfirmationForMeasurement", method = RequestMethod.POST)
	public ModelAndView paymentconfirmationBillDesk(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			DataAccessResourceFailureException, HibernateException,
			IllegalStateException, SQLException {

		AddMeasurementTransactionHistory addMeasurementaymentTransaction = new AddMeasurementTransactionHistory();

		 String responseUrl = request.getParameter("msg");
		//String responseUrl ="CDMADEPTEL|2501|QUR27606580687|915411855342|260.00|UR2|607909|03|INR|RDDIRECT|NA-113112|NA|00000000.00|03-06-2019 11:31:12|0300|NA|10381569845843221|1037|Ramagundam|Peddapalli|PTSelfAssessmentTaxCal|NA|NA|NA|PGS10001-Success|E92BC18302DA739345035D3FF59EB0703A558D4414FD6E96BD77357591446EED";
		
		/*************** Logger *************/
		log.info("Response  from Gateway>> " + responseUrl);
		log.shutdown();
		/****************************/
		String responseUrlArray[] = responseUrl.split("\\|");

		if (log.isDebugEnabled()) {
			log.debug("Invoking url-- /paymentconfirmation.do");

			log.debug("responseUrl : " + responseUrl);
			log.shutdown();
		}

		addMeasurementaymentTransaction = addAssessmentservice.getRecordForDigitalTransaction(Long.parseLong(responseUrlArray[1]));
		TransactionReceipt transactionReceipt = addAssessmentservice
				.getdetailsfromresponseurl(addMeasurementaymentTransaction, responseUrl);
		System.out.println("Last Controller");
		
		request.setAttribute("transactionReceipt", transactionReceipt);
		return new ModelAndView("measurementSelfAssmntDigitalPaymentReceipet");

	}
	
  /* new Implementation For Add Measurement */
	@RequestMapping("/findExistAsmt.do")
	public ModelAndView findExistAsm(
			@RequestParam(value = "asmt", required = true) String assessment,
			@RequestParam(value = "doorno", required = true) String doorno,
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, NamingException, SQLException {
		System.out.println("asmt=" + assessment);
		System.out.println("doorno=" + doorno);
		ArrayList<String> list = addAssessmentservice.findExistAssessment(
				assessment, doorno);
		if(list!=null)
		{
			AddAsmntMeasurementDaoImpl addAsmntMeasurementDaoImpl=new AddAsmntMeasurementDaoImpl();
			HashMap<String, List> hmParams=addAsmntMeasurementDaoImpl.getMeasurementFloor(assessment, doorno);
			request.setAttribute("I_BLDGAGE", hmParams.get("I_BLDGAGE"));
			request.setAttribute("ClassificationofBuilding", hmParams.get("ClassificationofBuilding"));
			request.setAttribute("TypeofBuildingusage", hmParams.get("TypeofBuildingusage"));
			request.setAttribute("Occupanttype", hmParams.get("Occupanttype"));
			request.setAttribute("I_FLRNO", hmParams.get("I_FLRNO"));
			request.setAttribute("D_LNTH", hmParams.get("D_LNTH"));
			request.setAttribute("D_WDTH", hmParams.get("D_WDTH"));
			request.setAttribute("D_PLNTAREA", hmParams.get("D_PLNTAREA"));
			request.setAttribute("IS_PLANAPRVD", hmParams.get("IS_PLANAPRVD"));
			//request.setAttribute("floorlist", floorlist);
			request.setAttribute("assessment", assessment);
		request.setAttribute("doorno", doorno);
		return new ModelAndView("PtSelfAsmtRecordView", "list", list);
		}
		else
		{
			return new ModelAndView("ptSelfAsmtFindForMeasurement", "msg", "Door No Or Assessment No does not exist.Please Enter Valid Assessment No or Door No.!");
			
		}
	}

	/* for Getting Measurement datails */

	@RequestMapping(value = "/getbclsMeasurement", method = RequestMethod.POST)
	public @ResponseBody String Measurement(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		try {

			log.info("Connecting >>>>>>>> " + ulbcode);
			log.shutdown();
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/

			sql = "select I_CLSCODE,VC_CLSDESC from pt_bldgcls_mstr_tbl  where   C_DELFLAG='N' and  I_ULBOBJID="
					+ ulbcode;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_CLSCODE");
				String Category = rs.getString("VC_CLSDESC");
				CategoryMap.put(CategoryID, Category);
			}
			JCategory = gson.toJson(CategoryMap);
		} catch (Exception e) {
			log.info(e);
			log.shutdown();
		} finally {
			// ps.close();
			// rs.close();
			// con.close();
		}
		log.info(sql);
		log.shutdown();
		return JCategory;
	}

	@RequestMapping(value = "/getusageMeasurement", method = RequestMethod.POST)
	public @ResponseBody String getSearchusagevaluesMeasurement(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		try {
			log.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin ************/
			String distname = Dbcon.getULBName(ulbcode);
			con = Dbcon.getdbfromdist(distname);
			// System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin ************/
			// ulbcode="1056";
			// con = Dbcon.getdbfromdist("TEST");
			/*** ********* End Live Data Base connectoin ************/
			sql = "select i_bldgusecode,vc_bldgusetype,vc_bldgusedesc,c_resflag,i_ulbobjid,c_delflag,ts_dttm,i_usrid from PT_BLDGUSE_MSTR_TBL where c_delFlag='N' and   I_ULBOBJID="
					+ ulbcode;
			log.info(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("i_bldgusecode");
				String Category = rs.getString("vc_bldgusedesc");
				CategoryMap.put(CategoryID, Category);
			}
			JCategory = gson.toJson(CategoryMap);
		} catch (Exception e) {
			log.info(e);
			log.shutdown();
		} finally {
			// ps.close();
			// rs.close();
			// con.close();
		}
		log.info(sql);
		log.shutdown();
		return JCategory;

	}

	@RequestMapping(value = "/getocpntMeasurement.do", method = RequestMethod.POST)
	public @ResponseBody String getSearchocpntvaluesMeasurement(
			@FormParam("ulbcode") String ulbcode, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {

		String JCategory = "";
		Map CategoryMap = new HashMap<>();
		// System.out.println("ulbcode >>>>From "+ulbcode);
		try {

			log.info("Connecting >>>>>>>> " + ulbcode);
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
			log.info(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String CategoryID = rs.getString("I_OCPNTYPECODE");
				String Category = rs.getString("VC_OCPNTYPEDESC");
				CategoryMap.put(CategoryID, Category);
			}
			JCategory = gson.toJson(CategoryMap);
		} catch (Exception e) {
			System.out.println("error " + e);
			log.info(e);
			log.shutdown();
		} finally {
			// ps.close();
			// rs.close();
			// con.close();
		}
		log.info(sql);
		log.shutdown();
		return JCategory;

	}
	@RequestMapping(value = "/ptFormSalection.do", method = RequestMethod.GET)
	public ModelAndView ptFormSalection(HttpSession session) {
		return new ModelAndView("PtSelfAsmtAppSelectionForm");
		
	}
	@RequestMapping(value = "/ptMeasurementFind.do", method = RequestMethod.GET)
	public ModelAndView ptMeasurementFind(HttpSession session) {
		return new ModelAndView("ptSelfAsmtFindForMeasurement");
		
	}
/*Login Controller*/
	@RequestMapping("/MeasurementCashcounterLogin")
	public ModelAndView measurementCounterLogin(@RequestParam(value="requestnumber",required = true) String UniqueNumber
			,Model model, HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		System.out.println("login="+UniqueNumber);
		
		request.setAttribute("UniqueNumber", UniqueNumber);
		
		
		return new ModelAndView ("MeasurementCashCounterLogin");
		
	}
	@RequestMapping(value = "/MeasurementAuthontication", method = RequestMethod.POST)
	public ModelAndView measurementAuthontication(@RequestParam("counterUserId")String VC_USRNAME,@RequestParam("counterUserPassword")String VC_USRPWD,
			
			@RequestParam("UniqueNumber") long UniqueNumber,
			  HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws SQLException {
		System.out.println("VC_USRNAME"+VC_USRNAME);
		System.out.println("VC_USRPWD"+VC_USRPWD);
		System.out.println("uniqReqNumber="+UniqueNumber);
		AddAssessmentMaster addAssessmentMaster=addAssessmentservice.getBean(UniqueNumber);
		
		String userId = addAssessmentservice.userLogin(addAssessmentMaster,VC_USRNAME,VC_USRPWD);
		String userId1=userId.replace("[","").replace("]", "");
		System.out.println("userId"+userId1);
		request.setAttribute("userId1", userId1);
		request.setAttribute("UniqueNumber", UniqueNumber);
		List<AddAssessmentMaster> getUniqueData = new ArrayList<AddAssessmentMaster>();
		//String l="99991543408306913";
		getUniqueData = addAssessmentservice.getUniquetData(UniqueNumber);
		System.out.println("getUniqueData="+getUniqueData);
		
		return new ModelAndView("MeasurementCashcounterPayment","getUniqueData",getUniqueData);
	}
	
	@RequestMapping("MeasurementPtCashPayment")
	public ModelAndView measurementPtCashPayment(
			@RequestParam(value = "UniqueNumber", required = true) long uniqReqNumber,
			@RequestParam(value = "id", required = true) String loginId,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			NamingException {

		System.out.println("id=" + loginId);
		long payment_transaction_receipt_id = 0;
		System.out.println("Cash payment section");
		String DoorNo="";
		String AssmntNo="";
		AddAssessmentMaster addAsmnttMaster = addAssessmentservice
				.getptselfAssestsinglerecord(uniqReqNumber);
		AddMeasurementTransactionHistory paymentTransactionEntity = addAssessmentservice
				.insertCashtransaction(uniqReqNumber, request, response,
						loginId);// 1
		if (paymentTransactionEntity.getPaid_update_flag() == 'S'
				&& paymentTransactionEntity.getTransaction_flag() == 'S') {
			payment_transaction_receipt_id = paymentTransactionEntity
					.getPayment_transaction_receipt_id();
			
			addAsmnttMaster.setDelflag('N');
			addAsmnttMaster.setPaymentflag('Y');
			addAsmnttMaster.setApplication_stage("Application Approved");
			addAssessmentservice.save(addAsmnttMaster);
		      DoorNo=addAsmnttMaster.getpDoorNo();
			AssmntNo=addAsmnttMaster.getAssessmentNo();
			System.out.println("Before Live insertion ");
			@SuppressWarnings("unused")
			String status = null;

			/********** Insert Record in Live Db2 **********/
			
            System.out.println(" Insert Record in Live Db2");
		     status = new AddAsmntMeasurementDaoImpl().insertlive(addAsmnttMaster);

			/********** End Insert Live Db2 **********/
			/********** For Citizen SMs **********/
			citizenMsg = "Dear Applicant,PT Self Assessment Application Fee"
					+ addAsmnttMaster.getPaymentamount()
					+ " SucessFully Done.This is your Assessment Number: "
					+ addAsmnttMaster.getAssessmentNo() + "and Door Number:"
					+ addAsmnttMaster.getpDoorNo();
			sendsms.SendSMSmain(addAsmnttMaster.getOwnerMobile(), citizenMsg);
			/********** End For Citizen SMs **********/

		} else {
			log.info("Transaction not Updated");

		}

		List<AddMeasurementTransactionHistory> paymentTransaction = (List<AddMeasurementTransactionHistory>) addAssessmentservice
				.getsinglerecordForTransaction(payment_transaction_receipt_id); // 4

		request.setAttribute("transactionReceipt", paymentTransaction);
		request.setAttribute("DoorNo", DoorNo);
		request.setAttribute("AssmntNo", AssmntNo);
		return new ModelAndView("MeasurementcashpaymentRecipt",
				"transactionReceipt", paymentTransaction);
	}
	/* 75 Squre meters implementation*/
	@RequestMapping("/getDetailsFor75_SqrMtrs.do")
	public ModelAndView getDetailsFor75_SqrMtrs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			DataAccessResourceFailureException, HibernateException,
			IllegalStateException, SQLException, RemoteException {
		List<Districts> districtsList = districtsService.getAllOrderByName();
		request.setAttribute("districtsList", districtsList);

		Map<String, Object> subUlbsMap = new HashMap<String, Object>();

		List<Districts> ulbsByDistrict = districtsService.getAllOrderByName();
		for (Districts districtsObj : ulbsByDistrict) {
			List<Ulbs> ulbsListOnValue = ulbService.findByProperty(
					"districts.districtId", districtsObj.getDistrictId());

			if (ulbsListOnValue != null && ulbsListOnValue.size() != 0)
				subUlbsMap.put("" + districtsObj.getDistrictId(), ulbService
						.findByProperty("districts.districtId",
								districtsObj.getDistrictId()));
		}

		request.setAttribute("subUlbsMap", subUlbsMap);

		return new ModelAndView("SelfAssessmentFor75_SqrMetrs");

	}
	
////////////////with G+1  measurement record
@RequestMapping(value = "/saveAsmntFor_75SqrYards.do", method = RequestMethod.POST)
public ModelAndView saveAsmntFor_75SqrYards(
@ModelAttribute("applicationForm") AssessmentMaster assessmentMaster,
BindingResult result, Model model, HttpSession session,
HttpServletRequest request, HttpServletResponse response)
throws IOException, SQLException {


long Uniqueseq = (assessmentMaster.getUlbcode() + Calendar
	.getInstance().getTimeInMillis());
String UniqueNumber = assessmentMaster.getUlbcode() + "" + Uniqueseq;
assessmentMaster.setUniqueRequestNumber(Long.parseLong(UniqueNumber));
assessmentMaster.setDocEnclosed('N');
String msg = "";
MultipartHttpServletRequest m = (MultipartHttpServletRequest) request;
MultipartFile[] file = { m.getFile("file1"), m.getFile("file2"),
	m.getFile("file3") };
long fileSize = 0;
String fname = "";
for (int i = 0; i < file.length; i++) {
try {
	fileSize = file[i].getSize();
	fname = file[i].getName();
} catch (Exception e) {
	fileSize = 0;
	fname = null;
}
if (fname != null && fileSize != 0) {

	System.out.println("Path>>> 1111 "
			+ assessmentMaster.getDoc_path() + " File>>> " + fname
			+ " path " + assessmentMaster.getUlbcode()
			+ " file.getSize() " + fileSize);

	assessmentMaster.setDocEnclosed('S');
	// String path ="C:\\MesevaUsersDocumets"+"\\"+
	// application.getTradeUlb() +"\\";

	// String path
	// ="C:\\MesevaUsersDocumets"+"\\0000\\"+UniqueNumber;
	String path = "C:\\MesevaUsersDocumets" + "\\"
			+ assessmentMaster.getUlbcode() + "\\" + UniqueNumber;
	File f = new File(path);
	if (!f.exists()) {
		boolean c = f.mkdirs();
		System.out.println("FLAG FILE " + c + " PATH= " + path);
	}
	String filename = file[i].getOriginalFilename();
	System.out.println(path + " " + filename);
	byte[] bytes = file[i].getBytes();
	BufferedOutputStream stream = new BufferedOutputStream(
			new FileOutputStream(new File(path + File.separator
					+ filename)));
	stream.write(bytes);
	stream.flush();
	stream.close();
	assessmentMaster.setDoc_path(path);
	System.out.println("save Method");

	/****************** Prepare TO send FTP Server **************************/
	// String ulbID="0000";
	String ulbID = "" + assessmentMaster.getUlbcode();
	String docName = filename;
	String MAIN_FOLDER_PATH = "C:\\MesevaUsersDocumets";
	String FULL_PATH = MAIN_FOLDER_PATH + "/" + ulbID;
	String folderName = ""
			+ assessmentMaster.getUniqueRequestNumber();
	StringBuffer ftPUploadPath = new StringBuffer();
	File allAcksDir = new File(FULL_PATH, "/" + folderName);
	System.out.println("pdf " + allAcksDir + " >>> " + ulbID);
	File pdfOutPath = null;
	try {
		pdfOutPath = new File(allAcksDir, docName);
	} catch (Exception e) {
		e.printStackTrace();
	}
	InputStream is = new FileInputStream(pdfOutPath);

	ftPUploadPath.append("/" + ulbID + "/" + folderName + "/"
			+ docName);
	String pathchk = "/" + ulbID + "/" + folderName + "/";
	//sendToFTP(ftPUploadPath.toString(), is, pathchk);

	//sendToFTP(path, is, path);
} else {
	String folderName = ""
			+ assessmentMaster.getUniqueRequestNumber();
	String ulbID = "" + assessmentMaster.getUlbcode();
	String pathchk = "/" + ulbID + "/" + folderName + "/";
	FTPClient client = new FTPClient();
	client.connect("10.3.3.83"); // Password >>>password@123
	client.enterLocalPassiveMode();
	client.login("cdma-user1", "password@123");

	client.setFileType(FTP.BINARY_FILE_TYPE);
	client.setFileTransferMode(FTP.BINARY_FILE_TYPE);

	if (!client.changeWorkingDirectory(pathchk)) {
		client.makeDirectory(pathchk);
	}
}
}

Set<AsmtMeasurementMaster> amSet = new HashSet<>();
String[] bcls = assessmentMaster.getBcls().split(",");
String[] busage = assessmentMaster.getBusage().split(",");
String[] ocity = assessmentMaster.getOcty().split(",");
String[] bage = assessmentMaster.getBage().split(",");
String[] wid = assessmentMaster.getWid().split(",");
String[] len = assessmentMaster.getLen().split(",");
String[] floorNo = assessmentMaster.getFloorNo().split(",");
String[] planaprvl = assessmentMaster.getPlanaprvl().split(",");
String[] cPlinth = assessmentMaster.getcPlinth().split(",");
String[] usageType = assessmentMaster.getUsageType().split(",");
//int cPlinthlength=cPlinth.length;
int j=0;
for (int i = 0; i < bcls.length; i++) {

if (!(bcls[i].equalsIgnoreCase(""))
		&& !(busage[i].equalsIgnoreCase(""))
		&& !(ocity[i].equalsIgnoreCase(""))) {
	AsmtMeasurementMaster am = new AsmtMeasurementMaster();
	am.setBcls(bcls[i]);
	am.setBusage(busage[i]);
	am.setOcty(ocity[i]);
	am.setBage(bage[i]);
	am.setWid(wid[i]);
	am.setLen(len[i]);
	am.setFloorNo(floorNo[i]);
	//if(planaprvl.length>0)
	am.setPlanAprvl(planaprvl[i]);
	/*System.out.println(planaprvl[i] +" @@@@@@@@"+planaprvl[i].equals("N"));
	System.out.println("gudfadjsf"+(cPlinth.length>0 && cPlinth[i]!=null));*/
	if(planaprvl[i].equals("N")){
		am.setcPlinth("0");	
	    am.setUsageType("");
	}
	else{
		am.setcPlinth(cPlinth[j]);
		am.setUsageType(usageType[j]);
		j++;
	}
	
	amSet.add(am);
}
}
assessmentMaster.setAsmtMeasurementMaster(amSet);

/******** Saving the basic details *****/
assessmentMaster.setDelflag('Y');
//String Length=assessmentMaster.getLen();
assessmentMaster.setApplicatoinstatusflag('N');
System.out.println(assessmentMaster.getOwnerCity());
boolean flag = assessmentservice.save(assessmentMaster);

if (flag == true) {
Double amount = assessmentservice
		.getTotalPtSelfAssessmentTaxFor_75SqrYards(assessmentMaster);
assessmentMaster.setPaymentamount(amount);
AssessmentMaster am = assessmentservice.getsinglerecord(Long
		.parseLong(UniqueNumber));
am.setFixdLcs(10);
am.setFixdPT(20);
am.setNArv(30);
am.setD_fxdunauthplnplty(assessmentMaster.getD_fxdunauthplnplty());
am.setPlengthArea(assessmentMaster.getPlengthArea());
am.setMarv(40);
am.setUnitRate(assessmentMaster.getUnitRate());
am.setPaymentamount(amount);
assessmentservice.save(am);
request.setAttribute("UniqueNumber", UniqueNumber);
request.setAttribute("ownerName", assessmentMaster.getOwnerName()
		+ " " + assessmentMaster.getOwnerSurName());
request.setAttribute("ownerRelationName",
		assessmentMaster.getOwnerFatherHusbandName() + " "
				+ assessmentMaster.getOwnerFatherHusbandSurName());
request.setAttribute("OWNERVILLAGE",
		assessmentMaster.getOwnerCity());
request.setAttribute("entrydate", assessmentMaster.getEntrydate());
request.setAttribute("PaymentAmount",
		assessmentMaster.getPaymentamount());

return new ModelAndView("paySelfPTAssmntAmount");
} else {
request.setAttribute("ErrMsg", "failed... Please apply Again...!");
return new ModelAndView("selfassessmentReceipt");
}
}
/* Initiate faild Transaction for Add Measurement */



	public static boolean sendToFTP(String remotepath, InputStream localpath,
			String pathchkRemote) {

		// LoggerSample.info("ftp upload sign pdf started in sendToFTP(())");
		boolean uploadStatus = false;
		try {
			FTPClient client = new FTPClient();
			client.enterLocalPassiveMode();
			client.login("cdma-user1", "password@123");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			if (!client.changeWorkingDirectory(pathchkRemote)) {
				client.makeDirectory(pathchkRemote);
			}
			boolean b = client.storeFile(remotepath, localpath);
			uploadStatus = b;
			return uploadStatus;
		} catch (Exception e) {
			e.printStackTrace();
			uploadStatus = false;
			return uploadStatus;
		}
	}
}
