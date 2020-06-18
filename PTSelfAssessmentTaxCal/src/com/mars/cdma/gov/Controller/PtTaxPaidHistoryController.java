package com.mars.cdma.gov.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import com.mars.cdma.gov.Dao.impl.PtDemandNoticeDaoImpl;
import com.mars.cdma.gov.Dao.impl.PtTaxPaidHistoryDaoImpl;
import com.mars.cdma.gov.bean.AssessmentReceipt;
import com.mars.cdma.gov.service.PtTaxPaidHistoryService;

@Controller
public class PtTaxPaidHistoryController {
	@Autowired
	private PtTaxPaidHistoryService ptPaidHistoryService;
	public Gson gson = new Gson();
	
	@RequestMapping("/getSearchAssPtHistory.do")
	public ModelAndView getSearchAssPtHistory() {
		return new ModelAndView("PtPaidHistorySearch");
	}
	
	@RequestMapping("/getSearchAssHistorySms.do")
	public ModelAndView getSearchAssHistorySms(
			@RequestParam(value="assessment",required=true)String assessment,
			@RequestParam(value="mobile",required=true)String mobile,
			HttpServletRequest request, HttpServletResponse response
			) throws IOException  {
		String otp=ptPaidHistoryService.ptAssPaidHistorySms(mobile, assessment);
		request.setAttribute("otp", otp);
		request.setAttribute("assessment", assessment);
		request.setAttribute("mobile", mobile);
	
		return new ModelAndView("PtPaidHistoryOtp");
	}
	@RequestMapping("/getAssPaidHistory.do")
	public ModelAndView getAssPaidHistory(
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
			flag=ptPaidHistoryService.savePtAssPaidHistory(assessment, mobile);
			 PtTaxPaidHistoryDaoImpl ptTaxPaidHistoryDaoImpl=new PtTaxPaidHistoryDaoImpl();
			 List<AssessmentReceipt> assessmentReceipts =ptTaxPaidHistoryDaoImpl.getAssessmentReceiptList(assessment.substring(0, 4), assessment);
			 request.setAttribute("assessmentReceipts",	assessmentReceipts);
			
			 return new ModelAndView("PtPaidHistory","assessmentReceipts",assessmentReceipts);
		}
		else{
			return new ModelAndView( "redirect:/getSearchAssPtHistory.do");
		}
	}
	// PTSelfAssessmentTaxCal Jasper File Adding For Download Demand Notice
				@RequestMapping(value = "/assessmentTaxPaidHistory.do", method = RequestMethod.GET)
				public String assessmentRegCopy(
						@RequestParam(value = "assessment", required = true) String assessment,
						 HttpServletRequest request,
						HttpServletResponse response) throws JRException, IOException,
						NamingException {
					System.out.println("assessment====>"+assessment);
					try {

						/*HashMap<String, Object> hmParams = new HashMap<String, Object>();
						PtTaxPaidHistoryDaoImpl ptTaxPaidHistoryDaoImpl=new PtTaxPaidHistoryDaoImpl(); 
						hmParams= ptTaxPaidHistoryDaoImpl.getPtAssPaidHistoryFromDb2(assessment.substring(0, 4), assessment);
						*/
						
						 PtTaxPaidHistoryDaoImpl ptTaxPaidHistoryDaoImpl=new PtTaxPaidHistoryDaoImpl();
						 List<AssessmentReceipt> assessmentReceipts =ptTaxPaidHistoryDaoImpl.getAssessmentReceiptList(assessment.substring(0, 4), assessment);
						 HashMap<String, Object> hmParams = new HashMap<String, Object>();
						    
						    for(AssessmentReceipt assessmentReceipt : assessmentReceipts)
						    {
						    	hmParams.put(assessmentReceipt.getAdvanceAmount(), assessmentReceipt.getAdvanceAmount());
						    	hmParams.put(assessmentReceipt.getAmountPaidAt(), assessmentReceipt.getAmountPaidAt());
						    	hmParams.put(assessmentReceipt.getArrearAmount(), assessmentReceipt.getArrearAmount());
						    	hmParams.put(assessmentReceipt.getBookNo(), assessmentReceipt.getBookNo());
						    	hmParams.put(assessmentReceipt.getCurrentAmount(), assessmentReceipt.getCurrentAmount());
						    	hmParams.put(assessmentReceipt.getEntryDate(), assessmentReceipt.getEntryDate());
						    	hmParams.put(assessmentReceipt.getPaidFromDate(), assessmentReceipt.getPaidFromDate());
						    	hmParams.put(assessmentReceipt.getPaidMode(), assessmentReceipt.getPaidMode());
						    	hmParams.put(assessmentReceipt.getPaidUptoDate(), assessmentReceipt.getPaidUptoDate());
						    	hmParams.put(assessmentReceipt.getPenalityAmount(), assessmentReceipt.getPenalityAmount());
						    	hmParams.put(assessmentReceipt.getReceipDate(), assessmentReceipt.getReceipDate());
						    	hmParams.put(assessmentReceipt.getReceiptNo(), assessmentReceipt.getReceiptNo());
						    	hmParams.put(assessmentReceipt.getStatus(), assessmentReceipt.getStatus());
						    	hmParams.put(assessmentReceipt.getTotalPaidAmount(), assessmentReceipt.getTotalPaidAmount());
						    	
						    }
						// System.out.println("hmParams="+assessmentReceipts); 
						String fileName = "PtPaidHistory";
						  //  String fileName = "test";

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
