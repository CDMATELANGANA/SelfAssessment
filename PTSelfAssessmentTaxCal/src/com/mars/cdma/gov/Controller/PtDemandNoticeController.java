package com.mars.cdma.gov.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

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
import com.mars.cdma.gov.Dao.impl.PtDemandNoticeDaoImpl;
import com.mars.cdma.gov.Dao.impl.PtDownloadDaoImpl;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.PtDemandNoticeService;
import com.mars.cdma.gov.service.UlbsService;

/**
 * @author SARITA
 *
 */
@Controller
public class PtDemandNoticeController {
	@Autowired
	private PtDemandNoticeService ptDemandNoticeService;
	@Autowired
	public UlbsService ulbService;
	@Autowired
	public GradeService gradeService;
	public Gson gson = new Gson();
	
	@RequestMapping("/getSearchAssessmentDemand.do")
	public ModelAndView getSearchAssessmentDemand() {
		return new ModelAndView("PtDemandSearch");
	}

	@RequestMapping("/getSearchAssDemandSms.do")
	public ModelAndView getSearchAssDemandSms(
			@RequestParam(value="assessment",required=true)String assessment,
			@RequestParam(value="mobile",required=true)String mobile,
			HttpServletRequest request, HttpServletResponse response
			) throws IOException  {
		String otp=ptDemandNoticeService.assessmentDemandSms(mobile, assessment);
		request.setAttribute("otp", otp);
		request.setAttribute("assessment", assessment);
		request.setAttribute("mobile", mobile);
	
		return new ModelAndView("PtDemandNoticeOtp");
	}
	
	@RequestMapping("/getAssessmentDemandnotice.do")
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
			request.setAttribute("assessment", assessment);
			flag=ptDemandNoticeService.saveAssDemandDownloadDetail(assessment, mobile);
			 return new ModelAndView("PtSingelDemandNotice");
		}
		else{
			return new ModelAndView( "redirect:/getSearchAssessmentDemand.do");
		}
	}
	
	// PTSelfAssessmentTaxCal Jasper File Adding For Download Demand Notice
			@RequestMapping(value = "/assessmentDemandNotice.do", method = RequestMethod.GET)
			public String assessmentRegCopy(
					@RequestParam(value = "assessment", required = true) String assessment,
					 HttpServletRequest request,
					HttpServletResponse response) throws JRException, IOException,
					NamingException {
				System.out.println("assessment====>"+assessment);
				try {

					HashMap<String, Object> hmParams = new HashMap<String, Object>();
					PtDemandNoticeDaoImpl ptDemandNoticeDaoImpl=new PtDemandNoticeDaoImpl();
					hmParams= ptDemandNoticeDaoImpl.getDemandRecordFromDb2(assessment.substring(0, 4), assessment);
					System.out.println("hmParams="+hmParams); 
					String fileName = "DemandNotice2";

					System.out.println("hmParams==" + hmParams);
					File reportFile = new File(request.getSession().getServletContext().getRealPath("/jasper/" + fileName + ".jasper"));

					JasperCompileManager.compileReportToFile(
							request.getSession().getServletContext()
									.getRealPath("/jasper/" + fileName + ".jrxml"),
							request.getSession().getServletContext()
									.getRealPath("/jasper/" + fileName + ".jasper"));

					JasperReport jasperReport = (JasperReport) JRLoader
							.loadObjectFromFile(reportFile.getPath());
		new PtDemandNoticeDaoImpl().jasperDemandPDF(jasperReport, hmParams, response, assessment);
				} catch (Exception e) {
					System.out.println("Exception::" + e.getMessage());
				} finally {

				}

				return null;
			}

}
