package com.mars.cdma.gov.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.service.PTCashGatewaytService;





@Controller
public class PTCashCounterGatewayController {
	
	@Autowired
	public PTCashGatewaytService cashReportService;
	

	
	@RequestMapping("/ptcashCounterPaymentReport.do")
	public ModelAndView gatewayReport(HttpSession session,
			HttpServletRequest request, ModelMap m, HttpServletResponse response) {

	
		List<String> totalCollection = new ArrayList<String>();

		String ulbCode = (String) session.getAttribute("ulb_code");
		String userId = (String) session.getAttribute("user_name");

		try {
			totalCollection = cashReportService.totalAmount(ulbCode);
			m.put("total", totalCollection);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("ptselfAssessmentCashReport", "message",totalCollection);

	}
	
	@RequestMapping("/ptpaymentDetail.do")
	public ModelAndView selfMutationCashDetail(HttpSession session,
			HttpServletRequest request, Model m, HttpServletResponse response) {
		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();

		try {

			String ulbCode = (String) session.getAttribute("ulb_code");
			totalCollection = cashReportService.cashULBReport(ulbCode);

			gatewayCount = cashReportService.cashGatewayCount(ulbCode);

			m.addAttribute("gateway", gatewayCount);
			request.setAttribute("abstractReport", totalCollection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("ptCashCounterDetailReport", "message",totalCollection);
	}
	
	@RequestMapping("/ptAssessementCashDetailULB.do")
	public ModelAndView selfMutationCashDetailULB(HttpSession session,
			HttpServletRequest request, ModelMap m,
			@PathParam("ulb") String ulb, HttpServletResponse response) {

		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();

		try {

			String ulbCode = (String) session.getAttribute("ulb") == null ? ulb
					: (String) session.getAttribute("ulb");
			totalCollection = cashReportService.cashULBDetailReport(ulb);
			gatewayCount = cashReportService.cashGatewayCount(ulbCode);

			m.addAttribute("gateway", gatewayCount);
			request.setAttribute("detailReport", totalCollection);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("ptAssessmentCashULBDetail", "message",	totalCollection);
	}
	
	
	@RequestMapping(value = "/ptSelfAssesmntAllCashTransaction.do", method = RequestMethod.GET)
	public ModelAndView selfMutationAllCashTransaction(
			HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		List<String> totalCollection = new ArrayList<String>();
		
		totalCollection = cashReportService.getCashDetailTransactionReport();
		request.setAttribute("detailReport", totalCollection);
		return new ModelAndView("ptselfAssesmntAllCashTransationReport", "message",totalCollection);
			
	}
	
	@RequestMapping(value = "/ptAssessmentCashIntervalData.do", method = RequestMethod.GET)
	public ModelAndView getCashIntervalDetails(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {


		String ulb = (String) session.getAttribute("ulbcode");

		List<String> totalCollection = new ArrayList<String>();
		// List<String> gatewayCount = new ArrayList<String>();

		List<String> gatewayCountBydate = new ArrayList<String>();
		totalCollection = cashReportService.getCashDataByInterval(fromDate, toDate, ulb);
		// gatewayCount= dashboardService.gatewayCount(ulb);
		gatewayCountBydate = cashReportService.cashgatewayCountByDate(fromDate,
				toDate);
		// request.setAttribute("type", type);
		model.addAttribute("gateway", gatewayCountBydate);
		request.setAttribute("abstractReport", totalCollection);
		request.setAttribute("fomDate", fromDate);
		request.setAttribute("toDate", toDate);
		return new ModelAndView("ptCashCounterDetailReport", "message",totalCollection);
}
	
	@RequestMapping(value = "/ptSelfAssesmentCashTransDtlsByDate.do", method = RequestMethod.GET)
	public ModelAndView selfMutationCashTransDtlsByDate(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			@PathParam("ulb") String ulb,
			HttpSession session, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String ulbCode = (String) session.getAttribute("ulb") == null ? ulb
				: (String) session.getAttribute("ulb");
		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();
		totalCollection = cashReportService.transCashDetailReportByDate(fromDate,toDate);
		gatewayCount = cashReportService.cashGatewayCount(ulbCode);
         
		model.addAttribute("gateway", gatewayCount);
		request.setAttribute("detailReport", totalCollection);
		request.setAttribute("date_interval", fromDate + " To " + toDate);
		return new ModelAndView("ptAssessmentCashULBDetail", "message",totalCollection);
			
}
}