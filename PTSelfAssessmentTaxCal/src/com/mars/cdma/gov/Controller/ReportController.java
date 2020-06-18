package com.mars.cdma.gov.Controller;

import java.io.File;
//import java.io.File;
import java.io.IOException;
//import java.sql.DriverManager;
//import java.text.ParseException;
import java.util.HashMap;

import javax.naming.NamingException;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mars.cdma.gov.Dao.ReportDAO;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.UlbsService;
//import com.sun.corba.se.spi.orbutil.fsm.Input;
//import java.util.List;
//import java.util.Map;

@Controller
public class ReportController {

	@Autowired
	private DistrictsService districtsService;

	@Autowired
	private UlbsService ulbService;

	@Autowired
	private GradeService gradeService;
@Autowired
private Assessmentservice assessmentservice;


@RequestMapping(value = "/acknowledgement", method = RequestMethod.GET)
public String acknowledgement(
		@RequestParam(value = "uniquerequestid", required = true) String uniquerequestid,
		// @RequestParam(value = "ApplFlag", required = true) String
		Model model, HttpServletRequest request,
		HttpServletResponse response) throws JRException, IOException,
		NamingException {
	//System.out.println(">>>>>>>>>>>>" + uniquerequestid);
	AssessmentMaster application = assessmentservice.getsinglerecord(Long.parseLong(uniquerequestid));

	try {

		HashMap<String, Object> hmParams = new HashMap<String, Object>();

		Ulbs ulbs = ulbService.getByUlbname(application.getUlbcode());
		System.out.println("ulbs "+ulbs.getGrade_code());
		
		Grademaster grademaster = gradeService.gradename(ulbs.getGrade_code());	
		
		/*System.out.println("ulbs.getGrade_code() " + ulbs.getGrade_code()
				+ " grademaster " + grademaster.getGradeName());			
		System.out.println(" application.getUlb() ? "+ ulbs.getUlbName());*/
		
		hmParams.put("ulb_Name", ulbs.getUlbName().toUpperCase());
		hmParams.put("PaymentAmount", application.getPaymentamount());
		hmParams.put("ulb_grade", grademaster.getGradeName().toUpperCase());
		hmParams.put("OWNER_NAME", application.getOwnerSurName() + ","+ application.getOwnerName());
		/*System.out.println(">> "+application.getOwnerSurName() + ",== "+ application.getOwnerName());*/
		hmParams.put("Unique_Req_Num", application.getAssessmentNo());
		hmParams.put("Door_No", application.getpDoorNo());
		hmParams.put("Entry_Date", application.getEntrydate());
		
		String fileName = "AppAck";

		
		File reportFile = new File(request.getSession().getServletContext()
				.getRealPath("/jasper/" + fileName + ".jasper"));

		JasperCompileManager.compileReportToFile(
				request.getSession().getServletContext()
						.getRealPath("/jasper/" + fileName + ".jrxml"),
				request.getSession().getServletContext()
						.getRealPath("/jasper/" + fileName + ".jasper"));

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObjectFromFile(reportFile.getPath());

		new ReportDAO().jasperPDF(jasperReport, hmParams, response,
				uniquerequestid);

	} catch (Exception e) {
		/*System.out.println("Exception::" + e.getMessage());*/
	} finally {

	}

	return null;
}

@RequestMapping(value = "/cashAcknowledgement", method = RequestMethod.GET)
public String Cashacknowledgement(
		@RequestParam(value = "uniquerequestid", required = true) String uniquerequestid,
		// @RequestParam(value = "ApplFlag", required = true) String
		Model model, HttpServletRequest request,
		HttpServletResponse response) throws JRException, IOException,
		NamingException {
	//System.out.println(">>>>>>>>>>>>" + uniquerequestid);
	AssessmentMaster application = assessmentservice.getsinglerecord(Long.parseLong(uniquerequestid));

	try {

		HashMap<String, Object> hmParams = new HashMap<String, Object>();

		Ulbs ulbs = ulbService.getByUlbname(application.getUlbcode());
		System.out.println("ulbs "+ulbs.getGrade_code());
		
		Grademaster grademaster = gradeService.gradename(ulbs.getGrade_code());	
		
		System.out.println("ulbs.getGrade_code() " + ulbs.getGrade_code()
				+ " grademaster " + grademaster.getGradeName());			
		System.out.println(" application.getUlb() ? "+ ulbs.getUlbName());
		
		hmParams.put("ulb_Name", ulbs.getUlbName().toUpperCase());
		hmParams.put("PaymentAmount", application.getPaymentamount());
		hmParams.put("ulb_grade", grademaster.getGradeName().toUpperCase());
		hmParams.put("OWNER_NAME", application.getOwnerSurName() + ","+ application.getOwnerName());
		System.out.println(">> "+application.getOwnerSurName() + ",== "+ application.getOwnerName());
		hmParams.put("Unique_Req_Num", application.getUniqueRequestNumber());
		hmParams.put("Entry_Date", application.getEntrydate());
		
		String fileName = "AppAck";

		
		File reportFile = new File(request.getSession().getServletContext()
				.getRealPath("/jasper/" + fileName + ".jasper"));

		JasperCompileManager.compileReportToFile(
				request.getSession().getServletContext()
						.getRealPath("/jasper/" + fileName + ".jrxml"),
				request.getSession().getServletContext()
						.getRealPath("/jasper/" + fileName + ".jasper"));

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObjectFromFile(reportFile.getPath());

		new ReportDAO().jasperPDF(jasperReport, hmParams, response,
				uniquerequestid);

	} catch (Exception e) {
		System.out.println("Exception::" + e.getMessage());
	} finally {

	}

	return null;
}
	public static void main(String[] args) {
		 
	}
}