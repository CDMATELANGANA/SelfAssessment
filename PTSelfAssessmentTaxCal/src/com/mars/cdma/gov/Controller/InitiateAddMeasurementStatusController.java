/**
 * 
 */
package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.service.InitiateAddmeasurementAssessmentReportService;
import com.mars.cdma.gov.service.InitiatePaymentTransactionService;

/**
 * @author RAHUL
 *
 */
@Controller
public class InitiateAddMeasurementStatusController {

	@Autowired
	private InitiatePaymentTransactionService initiatePaymentTransactionService;
	@Autowired
	private InitiateAddmeasurementAssessmentReportService initiateAssessmentReportService;

	// Initiate and Update Live DB2 Database

	// @SuppressWarnings("null")
	@RequestMapping(value = "/updateAddMeasurementInitiateTransactionStatusDB2", method = RequestMethod.GET)
	public ModelAndView updateInitiateDB2Live(
			@RequestParam(value = "trans_id", required = true) String trans_id,
			@RequestParam(value = "uniqReqNo", required = true) long uniqReqNo,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException, IOException, NamingException {
		String status = initiatePaymentTransactionService.updateInitiatLiveDB2(
				trans_id, uniqReqNo);
		request.setAttribute("LocalDBStatus", status);

		return new ModelAndView("ptAddMeasurementSelfAsmntTransactionStatusReport");
	}

	// PTSelf Assesssment Transaction Report

	// @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ptAddMeasurementSelfAsmntTransactionReport", method = RequestMethod.GET)
	// ----------1
	public ModelAndView ptSelfAsmntTransactionReport(
			HttpServletRequest request, HttpServletResponse response)

	{
		List<Object> tradeCollection = initiateAssessmentReportService
				.getptSelfAsmntTransCollection();
		request.setAttribute("tradeCollection", tradeCollection);

		List tranList = initiateAssessmentReportService
				.getptSelfAsmntTransDetail(("" + new GregorianCalendar()
						.get(java.util.Calendar.YEAR)));
		request.setAttribute("repotype", "all");
		return new ModelAndView("ptAddMeasurementSelfAsmntTransactionReport", "tranList",
				tranList);
	}

	@RequestMapping(value = "/ptAddMeasurementSelfAsmntTransactionReportByUniqId", method = RequestMethod.GET)
	// --2
	public ModelAndView ptAddMeasurementSelfAsmntTransReportByuniqueReqId(
			@RequestParam(value = "uniqReqNumber", required = true) String uniqueRequestId,
			HttpServletRequest request, HttpServletResponse response)

	{
		List tranList = initiateAssessmentReportService
				.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
		request.setAttribute("repotype", "by_uniq_id");
		return new ModelAndView("ptAddMeasurementSelfAsmntTransactionReport", "tranList",
				tranList);

	}

	// Status Report
	@RequestMapping(value = "/ptAddMeasurementSelfAsmntTransactionStatusReport", method = RequestMethod.GET)
	// --3
	public ModelAndView AddMeasurementTransactionStatusReport(
			HttpServletRequest request, HttpServletResponse response)

	{
		List<Object> tradeCollection = initiateAssessmentReportService
				.getptSelfAsmntTransCollection();
		request.setAttribute("tradeCollection", tradeCollection);
		request.setAttribute("repotype", "all");
		return new ModelAndView("ptAddMeasurementSelfAsmntTransactionStatusReport",
				"tranList", "tranList");
	}

	@RequestMapping(value = "/ptAddMeasurementSelfAsmntTransactionStatusReportByUniqId", method = RequestMethod.GET)
	// ---4
	public ModelAndView ptAddMeasurementSelfAsmntTransStatusReportById(
			@RequestParam(value = "uniqueRequestId", required = true) String uniqueRequestId,
			HttpServletRequest request, HttpServletResponse response)

	{
		List tranList = initiateAssessmentReportService
				.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
		request.setAttribute("repotype", "by_uniq_id");
		return new ModelAndView("ptAddMeasurementSelfAsmntTransactionStatusReport",
				"tranList", tranList);

	}

}
