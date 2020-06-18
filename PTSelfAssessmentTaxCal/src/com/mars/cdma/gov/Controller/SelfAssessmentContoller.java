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
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
import com.mars.cdma.gov.bean.AsmtMeasurementMaster;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.TransactionReceipt;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.MySqlDBConnection;
import com.mars.cdma.gov.service.AssessmentReportService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.UlbsService;
import com.mars.cdma.gov.transaction.BillDeskGateWayMac;
import com.mars.cdma.gov.utils.CommonUtils;

@Controller
public class SelfAssessmentContoller {
	private static final Logger log = Logger
			.getLogger(SelfAssessmentContoller.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DistrictsService districtsService;
	@Autowired
	private UlbsService ulbService;

	@Autowired
	private Assessmentservice assessmentservice;
	
	@Autowired
	private AssessmentReportService assessmentReportService;
	

	@Autowired
	private PaymentTransactionService paymentTransactionService;

	Connection con = null;
	public Gson gson = new Gson();
	@Autowired
	private MessageSource msgSrc;

	@RequestMapping("/getassessment.do")
	public ModelAndView etradeApplication(HttpServletRequest request,
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

		return new ModelAndView("selfassessment");
		// return new ModelAndView("selfassessmentTest");

	}

	
	
	// charan written

	@RequestMapping("/getassessmentCMS.do")
	public ModelAndView etradeApplicationCMS(HttpServletRequest request,
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

		return new ModelAndView("selfassessmentCMS");

	}
	@RequestMapping("/getassessmentMob.do")
	public ModelAndView etradeApplicationMob(HttpServletRequest request,
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

		return new ModelAndView("selfassessmentMob");
		// return new ModelAndView("selfassessmentTest");

	}

	// //////////////with multiple measurement record
	@RequestMapping(value = "/saveAsmtDetail.do", method = RequestMethod.POST)
	public ModelAndView saveAsmtDetail(
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
					//System.out.println("***************/////////" + pathchk);
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
		/*assessmentMaster.setPaymentamount(assessmentservice
				.getTotalPtSelfAssessmentTax(assessmentMaster));*/

		/******** Saving the basic details *****/
		assessmentMaster.setDelflag('Y');
		//String Length=assessmentMaster.getLen();
		assessmentMaster.setApplicatoinstatusflag('N');
		System.out.println(assessmentMaster.getOwnerCity());
		boolean flag = assessmentservice.save(assessmentMaster);
		
		if (flag == true) {
			Double amount = assessmentservice
					.getTotalPtSelfAssessmentTax(assessmentMaster);
			assessmentMaster.setPaymentamount(amount);
			AssessmentMaster am = assessmentservice.getsinglerecord(Long
					.parseLong(UniqueNumber));
			am.setFixdLcs(assessmentMaster.getFixdLcs());
			am.setFixdPT(assessmentMaster.getFixdPT());
			am.setNArv(assessmentMaster.getNArv());
			am.setD_fxdunauthplnplty(assessmentMaster.getD_fxdunauthplnplty());
			am.setPlengthArea(assessmentMaster.getPlengthArea());
			am.setMarv(assessmentMaster.getMarv());
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
	

	@RequestMapping("ptselfAssessmentPay")
	
	/* public ModelAndView paynow(@RequestParam(value = "requestnumber",
	 required = true) long uniqReqNumber, Model model, HttpServletRequest
	  request, HttpServletResponse response) throws IOException {*/
	 
	// Live
	public void paynow(@RequestParam(value = "requestnumber", required = true) long uniqReqNumber,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PaymentTransaction paymentTransactionEntity = assessmentservice
				.inserttransaction(uniqReqNumber, request, response);

		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity
				.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());
		Long receiptNo = paymentTransactionEntity
				.getPayment_transaction_receipt_id();
		int ulbCode = paymentTransactionEntity.getUlbcode();
		String ulbName = ulbs.getUlbName();
		String districtName = districts.getDistrictName();
		Long uniqueRequestNumber = paymentTransactionEntity
				.getUniqueRequestNumber();
		String uniqueRequestNumbers = "" + uniqueRequestNumber;
	    double amount = paymentTransactionEntity.getTotal_amount();
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
		// return new ModelAndView("redirect:paymentconfirmation.do");
		
	}

	// For Test
	// @RequestMapping(value = "paymentconfirmation", method =RequestMethod.GET)
	// For Live
	@RequestMapping(value = "paymentconfirmation", method = RequestMethod.POST)
	public ModelAndView paymentconfirmationBillDesk(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			DataAccessResourceFailureException, HibernateException,
			IllegalStateException, SQLException {

		PaymentTransaction paymentTransaction = new PaymentTransaction();

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

		paymentTransaction = paymentTransactionService.gettransingelrecord(Long
				.parseLong(responseUrlArray[1]));
		TransactionReceipt transactionReceipt = paymentTransactionService
				.getdetailsfromresponseurl(paymentTransaction, responseUrl);
		System.out.println("Last Controller");
		
		request.setAttribute("transactionReceipt", transactionReceipt);
		/*request.setAttribute("doorNo", doorNo);
		request.setAttribute("AssessmentNo", AssessmentNo);*/
		return new ModelAndView("selfAssmntPaymentReceipet");
}
	public static boolean sendToFTP(String remotepath, InputStream localpath,
			String pathchkRemote) {

		// LoggerSample.info("ftp upload sign pdf started in sendToFTP(())");
		boolean uploadStatus = false;
		try {

			FTPClient client = new FTPClient();
			// client.connect("10.2.2.199"); // Password >>>password
			//client.connect("10.3.3.83"); // Password >>>password@123
			// client.connect("10.166.7.11"); // Password >>>password@123
			client.enterLocalPassiveMode();
			client.login("cdma-user1", "password@123");

			// LoggerSample.info("ftp server  connection status is " + b1);
			// client.setsetControlKeepAliveTimeout(300);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.BINARY_FILE_TYPE);

			if (!client.changeWorkingDirectory(pathchkRemote)) {
				client.makeDirectory(pathchkRemote);
			}

			boolean b = client.storeFile(remotepath, localpath);
			// LoggerSample.info("the uploading file to  server   status is " +
			// b);
			uploadStatus = b;
			// LoggerSample.info("ftp upload sign pdf ended and status is in sendsendToFTP(())"
			// + uploadStatus);
			return uploadStatus;
		} catch (Exception e) {
			// LoggerSample.error(e);
			e.printStackTrace();
			uploadStatus = false;
			return uploadStatus;
		}
	}
	
	

	// feedback
	@RequestMapping(value = "/feedback.do", method = RequestMethod.GET)
	public ModelAndView getFeedbackPage(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		return new ModelAndView("feedback");
	}

	@RequestMapping(value = "/feedback.do", method = RequestMethod.POST)
	public ModelAndView feedback(
			@ModelAttribute("applicationForm") AssessmentMaster application,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		long uniqueRequestNumber = application.getUniqueRequestNumber();

		AssessmentMaster applStatus = assessmentservice
				.getsinglerecord(uniqueRequestNumber);

		if (applStatus == null || applStatus.getUniqueRequestNumber() == 0) {
			request.setAttribute("message",
					"No record found for unique request number : "
							+ uniqueRequestNumber
							+ ". Please try with valid unique request number.");
			return new ModelAndView("feedback");
		}

		response.sendRedirect("http://cdma.telangana.gov.in/FeedBack/FeedBack.aspx?UserId="
				+ uniqueRequestNumber + "&UserName=PTSELFASSESSMENT");

		return new ModelAndView("feedback", "applStatus", applStatus);

	}
	//@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFloorCalculation", method = RequestMethod.POST)
	public @ResponseBody String getFloorCalculation(
			@FormParam("cmbbcls") String cmbbcls,
			@FormParam("cmbbusage") String cmbbusage,
			@FormParam("cmbocty") String cmbocty,
			@FormParam("bage") String bage,
			@FormParam("len") String len,
			@FormParam("wid") String wid,
			@FormParam("planaprvl") String planaprvl,
			@FormParam("District") String District,
			@FormParam("ulbId") String ulbId,
			@FormParam("zone") String zone,
			@FormParam("locality") String locality,
			HttpServletRequest request,
			HttpServletResponse response) throws SQLException, JSONException {
		System.out.println("enter json check");
		System.out.println("cmbbcls="+cmbbcls);
		System.out.println("cmbbusage="+cmbbusage);
		System.out.println("cmbocty="+cmbocty);
		System.out.println("bage="+bage);
		System.out.println("len="+len);
		System.out.println("wid="+wid);
		System.out.println("planaprvl="+planaprvl);
		System.out.println("District=="+District);
		System.out.println("ulbId=="+ulbId);
		System.out.println("zone=="+zone);
		System.out.println("locality=="+locality);
		return null;		
	}
	

}



