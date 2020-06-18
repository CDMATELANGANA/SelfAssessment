package com.mars.cdma.gov.Controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.impl.PtDownloadDaoImpl;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.PtDownloadService;
import com.mars.cdma.gov.service.UlbsService;

/**
 * @author SARITA
 *
 */
@Controller
public class PtDownloadController {
	@Autowired
	private PtDownloadService ptDownloadService;
	@Autowired
	public UlbsService ulbService;
	@Autowired
	public GradeService gradeService;
	public Gson gson = new Gson();
	
	@RequestMapping("/getSearchAssessmentCopy.do")
	public ModelAndView getSearchAssessmentCopy() {
		return new ModelAndView("ApplicationSeachAssessmentCopy");
	}
	
	@RequestMapping("/getSearchAssessmentCopySms.do")
	public ModelAndView getSearchAssessmentCopySms(
			@RequestParam(value="assessment",required=true)String assessment,
			@RequestParam(value="mobile",required=true)String mobile,
			HttpServletRequest request, HttpServletResponse response
			) throws IOException  {
		String otp=ptDownloadService.assessmentCopyOtp(mobile, assessment);
		request.setAttribute("otp", otp);
		request.setAttribute("assessment", assessment);
		request.setAttribute("mobile", mobile);
		return new ModelAndView("ApplicationSeachAssessmentCopyOtp");
	}
	@RequestMapping("/getSearchAssessmentCopyOtpValid.do")
	public ModelAndView getSearchAssessmentCopyOtpValid(
			@RequestParam(value="assessment",required=true)String assessment,
			@RequestParam(value="mobile",required=true)String mobile,
			@RequestParam(value="otp",required=true)String otp,
			@RequestParam(value="otpt",required=true)String otpt,
			HttpServletRequest request, HttpServletResponse response
			) throws JRException, IOException,
			NamingException, SQLException  {
		System.out.println("Controller mobile=============>"+mobile);
		boolean flag=false;
		System.out.println("otp=="+otp);
		System.out.println("otpt=="+otpt);
		if(otp.equals(otpt))
		{
			System.out.println("same");
			 flag=ptDownloadService.saveAssessmentDownloadDetail(assessment, mobile);
			 HashMap<String, Object> hmParams = new HashMap<String, Object>();
			 PtDownloadDaoImpl ptDownloadDaoImpl=new PtDownloadDaoImpl();
			 hmParams= ptDownloadDaoImpl.getRecordFromDb2(assessment.substring(0, 4),assessment);
			// ArrayList<String> list = new ArrayList(hmParams.values());
			 request.setAttribute("assessment", assessment);
			 request.setAttribute("surname", hmParams.get("VC_ONRSURNAME"));
			 request.setAttribute("name", hmParams.get("VC_ONRNAME"));
			 request.setAttribute("fhsurname", hmParams.get("VC_FTHRSURNAME"));
			 request.setAttribute("fhname", hmParams.get("VC_FTHRNAME"));
			 request.setAttribute("door", hmParams.get("VC_ONRDOORNO"));
			 request.setAttribute("taxamt", hmParams.get("PtHalfYearTax"));
			 request.setAttribute("ulbname", hmParams.get("VC_ULBNAME"));
			 request.setAttribute("locality", hmParams.get("VC_LCTYNAME"));
			 request.setAttribute("district", hmParams.get("DISTRICT_NAME"));
			 request.setAttribute("muncipality", hmParams.get("grade_name"));
			 request.setAttribute("seqno", hmParams.get("SEQ_NO"));
			 request.setAttribute("cdate", hmParams.get("C_DATE"));
			 return new ModelAndView("ApplicationDownloadAssCertificate");
		}
		//request.setAttribute("assessment", assessment);
		else{
			return new ModelAndView( "redirect:/getSearchAssessmentCopy.do");
			//return new ModelAndView("ApplicationDownloadAssCertificate");
		}
		//return new ModelAndView("ApplicationDownloadAssCertificate");
	}
	
	
		// PTSelfAssessmentTaxCal Jasper File Adding For Download Certificate
		@RequestMapping(value = "/assessmentRegCopy.do", method = RequestMethod.GET)
		public String assessmentRegCopy(
				@RequestParam(value = "assessment", required = true) String assessment,
				 HttpServletRequest request,
				HttpServletResponse response) throws JRException, IOException,
				NamingException {
			try {

				HashMap<String, Object> hmParams = new HashMap<String, Object>();
				PtDownloadDaoImpl ptDownloadDaoImpl=new PtDownloadDaoImpl();
				hmParams= ptDownloadDaoImpl.getRecordFromDb2(assessment.substring(0, 4),assessment);
				System.out.println("hmParams="+hmParams); 
				String fileName = "AssReg";

				System.out.println("hmParams==" + hmParams);
				File reportFile = new File(request.getSession().getServletContext().getRealPath("/jasper/" + fileName + ".jasper"));

				JasperCompileManager.compileReportToFile(
						request.getSession().getServletContext()
								.getRealPath("/jasper/" + fileName + ".jrxml"),
						request.getSession().getServletContext()
								.getRealPath("/jasper/" + fileName + ".jasper"));

				JasperReport jasperReport = (JasperReport) JRLoader
						.loadObjectFromFile(reportFile.getPath());
	new PtDownloadDaoImpl().jasperPDF(jasperReport, hmParams, response,assessment);
			} catch (Exception e) {
				System.out.println("Exception::" + e.getMessage());
			} finally {

			}

			return null;
		}

}
