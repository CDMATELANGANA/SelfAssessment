package com.mars.cdma.gov.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.service.PTSelfAsmntGateWayDashBoardService;

@Controller
public class PTSelfAsmntGateWayController {
	private static final Logger log = Logger.getLogger(PTSelfAsmntGateWayController.class);
@Autowired
public PTSelfAsmntGateWayDashBoardService dashboardService;
	@RequestMapping("/ptselfAssessmentGateWayReport")
	public ModelAndView gatewayReport(HttpSession session,
			HttpServletRequest request, ModelMap m, HttpServletResponse response) {

		/*
		 * if (session.getAttribute("permissionSession") == null) { //return new
		 * ModelAndView("login"); } else {
		 */
		List<String> totalCollection = new ArrayList<String>();

		String ulbCode = (String) session.getAttribute("ulb_code");
		String userId = (String) session.getAttribute("user_name");

		try {
			totalCollection = dashboardService.totalAmount(ulbCode);
			m.put("total", totalCollection);

			// request.setAttribute("total", totalCollection);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("ptselfAssessmentAbstract", "message",
				totalCollection);

		// }

	}
	
	@RequestMapping("/ptselfAssessmentDetail.do")
	public ModelAndView selfMutationDetail(HttpSession session,
			HttpServletRequest request, Model m, HttpServletResponse response) {

		/*
		 * if (session.getAttribute("permissionSession") == null) { return new
		 * ModelAndView("login"); } else {
		 */
		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();

		try {

			String ulbCode = (String) session.getAttribute("ulb_code");
			totalCollection = dashboardService.ULBReport(ulbCode);

			gatewayCount = dashboardService.gatewayCount(ulbCode);

			m.addAttribute("gateway", gatewayCount);
			request.setAttribute("abstractReport", totalCollection);
			// request.setAttribute("type", type);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("ptselfAssessmentUlbReport", "message",
				totalCollection);
	}
	
	
	
	@RequestMapping("/ptselfAssessmentDetailULB.do")
	public ModelAndView selfMutationDetailULB(HttpSession session,
			HttpServletRequest request, ModelMap m,
			@PathParam("ulb") String ulb, HttpServletResponse response) {

		/*
		 * if (session.getAttribute("permissionSession") == null) { return new
		 * ModelAndView("login"); } else {
		 */

		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();
		
		try {

			String ulbCode = (String) session.getAttribute("ulb") == null ? ulb
					: (String) session.getAttribute("ulb");
			
			totalCollection = dashboardService.ULBDetailReport(ulb);
			gatewayCount = dashboardService.gatewayCount(ulbCode);
			System.out.println("gatewayCount"+gatewayCount);

			m.addAttribute("gateway", gatewayCount);

			request.setAttribute("detailReport", totalCollection);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("ptselfAssessmentULBDetail", "message",
				totalCollection);
	}

	@RequestMapping(value = "/ptselfAssessmentAllTransaction.do", method = RequestMethod.GET)
	public ModelAndView selfMutationAllTransaction(
			HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		List<String> totalCollection = new ArrayList<String>();
		
		totalCollection = dashboardService.getDetailTransactionReport();
		request.setAttribute("detailReport", totalCollection);
		return new ModelAndView("ptselfAssessmentnAllTransationReport", "message",
						totalCollection);
			
	}
	
	@RequestMapping(value = "/ptselfAssessmentIntervalData.do", method = RequestMethod.GET)
	public ModelAndView getIntervalDetails(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {

System.out.println("fromDate="+fromDate);
		String ulb = (String) session.getAttribute("ulbcode");

		List<String> totalCollection = new ArrayList<String>();
		// List<String> gatewayCount = new ArrayList<String>();

		List<String> gatewayCountBydate = new ArrayList<String>();
		totalCollection = dashboardService.getDataByInterval(fromDate, toDate,ulb);
		// gatewayCount= dashboardService.gatewayCount(ulb);
		gatewayCountBydate = dashboardService.gatewayCountByDate(fromDate,
				toDate);
		// request.setAttribute("type", type);
		model.addAttribute("gateway", gatewayCountBydate);
		request.setAttribute("abstractReport", totalCollection);
		request.setAttribute("fomDate", fromDate);
		request.setAttribute("toDate", toDate);
		return new ModelAndView("ptselfAssessmentUlbReport", "message",
				totalCollection);
	}

	@RequestMapping(value = "/ptselfAssessmentTransDtlsByDate.do", method = RequestMethod.GET)//4
	public ModelAndView selfMutationUlbIntervalData(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			@PathParam("ulb") String ulb,
			HttpSession session, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String ulbCode = (String) session.getAttribute("ulb") == null ? ulb
				: (String) session.getAttribute("ulb");
		List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();
		totalCollection = dashboardService.transDetailReportByDate(fromDate,toDate);
		gatewayCount = dashboardService.gatewayCount(ulbCode);
		model.addAttribute("gateway", gatewayCount);
		request.setAttribute("detailReport", totalCollection);
		request.setAttribute("date_interval", fromDate + " To " + toDate);
		return new ModelAndView("ptselfAssessmentULBDetail", "message",
						totalCollection);
			
	}
}
