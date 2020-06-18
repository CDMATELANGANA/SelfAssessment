package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.service.AssessmentReportService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.PtSelfAssessmentReportService;
@Controller
public class InitiateTransReportStatusController {
	@Autowired
	private PaymentTransactionService paymentTransactionService;
	@Autowired
	private  AssessmentReportService assessmentReportService;

	/*private static final Logger log = Logger
			.getLogger(InitiateTransReportStatusController.class);
	@Autowired
	private PtSelfAssessmentReportService selfAsmntReportService;
	
	
	// Status Report
		@RequestMapping(value = "/ptAsmntTransactionStatusReport.do", method = RequestMethod.GET)
		public ModelAndView selfMutationTransactionStatusReport(
				HttpServletRequest request, HttpServletResponse response)

		{
			List<Object> tradeCollection = selfAsmntReportService
					.getSelfAsmntTransCollection();
			request.setAttribute("tradeCollection", tradeCollection);
			request.setAttribute("repotype", "all");
			return new ModelAndView("PtSelfAsmtTransactionStatusReport",
					"tranList", "tranList");
		}

		@RequestMapping(value = "/selfAsmntTransactionStatusReportByUniqId", method = RequestMethod.GET)
		public ModelAndView selfMutationTransactionStatusReportByuniqueReqId(
				@RequestParam(value = "uniqueRequestId", required = true) String uniqueRequestId,
				HttpServletRequest request, HttpServletResponse response)

		{
			List tranList = selfAsmntReportService
					.getSelfAsmntTransDetailByReqNumber(uniqueRequestId);
			request.setAttribute("repotype", "by_uniq_id");
			return new ModelAndView("PtSelfAsmtTransactionStatusReport",
					"tranList", tranList);

		}
		// Initiate and Update Live DB2 Database
		
		//@SuppressWarnings("null")
		@RequestMapping(value = "/updateInitiateTransactionStatusDB2", method = RequestMethod.GET)
		public ModelAndView updateInitiateDB2Live(
				@RequestParam(value = "trans_id", required = true) String trans_id,
				@RequestParam(value = "uniqReqNo", required = true) long uniqReqNo,
				HttpServletRequest request, HttpServletResponse response)
				throws SQLException, JSONException {
	        System.out.println("trans_id "+trans_id+"uniqReqNo "+uniqReqNo);
			//PaymentTransaction paymentTransaction=paymenttrSerivce.gettransingelrecord(Long.parseLong(trans_id));
			String status=paymenttransactionSerivce.updateInitiatLiveDB2(trans_id, uniqReqNo);
			System.out.println("status---------------->"+status);
			//request.setAttribute("DB2UpdateStatus", db2status);
			request.setAttribute("LocalDBStatus", status);

			return new ModelAndView("selfMutationTransactionStatusReport");
					 }
		*/
	
	// Initiate and Update Live DB2 Database
	
				//@SuppressWarnings("null")
				@RequestMapping(value = "/updateInitiateTransactionStatusDB2", method = RequestMethod.GET)
				public ModelAndView updateInitiateDB2Live(
						@RequestParam(value = "trans_id", required = true) String trans_id,
						@RequestParam(value = "uniqReqNo", required = true) long uniqReqNo,
						HttpServletRequest request, HttpServletResponse response)
						throws SQLException, JSONException, IOException, NamingException {
					String status=paymentTransactionService.updateInitiatLiveDB2(trans_id, uniqReqNo);
					request.setAttribute("LocalDBStatus", status);

					return new ModelAndView("ptSelfAsmntTransactionStatusReport");
							 }
			 
			//PTSelf Assesssment Transaction Report
			
				// @SuppressWarnings("rawtypes")
				@RequestMapping(value = "/ptSelfAsmntTransactionReport", method = RequestMethod.GET)//----------1
				public ModelAndView ptSelfAsmntTransactionReport(
						HttpServletRequest request, HttpServletResponse response)

				{
					List<Object> tradeCollection = assessmentReportService
				                              .getptSelfAsmntTransCollection();
					request.setAttribute("tradeCollection", tradeCollection);

					List tranList = assessmentReportService
							.getptSelfAsmntTransDetail(("" + new GregorianCalendar()
									.get(java.util.Calendar.YEAR)));
					request.setAttribute("repotype", "all");
					return new ModelAndView("ptSelfAsmntTransactionReport", "tranList",
							tranList);
				}

				@RequestMapping(value = "/ptSelfAsmntTransactionReportByUniqId", method = RequestMethod.GET)//--2
				public ModelAndView ptSelfAsmntTransactionReportByuniqueReqId(
						@RequestParam(value = "uniqReqNumber", required = true) String uniqueRequestId,
						HttpServletRequest request, HttpServletResponse response)

				{
					List tranList = assessmentReportService
							.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
					request.setAttribute("repotype", "by_uniq_id");
					return new ModelAndView("ptSelfAsmntTransactionReport", "tranList",
							tranList);

				}
		        // Status Report
				@RequestMapping(value = "/ptSelfAsmntTransactionStatusReport", method = RequestMethod.GET)//--3
				public ModelAndView selfMutationTransactionStatusReport(
						HttpServletRequest request, HttpServletResponse response)

				{
					List<Object> tradeCollection = assessmentReportService
							.getptSelfAsmntTransCollection();
					request.setAttribute("tradeCollection", tradeCollection);
					request.setAttribute("repotype", "all");
					return new ModelAndView("ptSelfAsmntTransactionStatusReport",
							"tranList", "tranList");
				}

				@RequestMapping(value = "/ptSelfAsmntTransactionStatusReportByUniqId", method = RequestMethod.GET)//---4
				public ModelAndView ptSelfAsmntTransactionStatusReportByuniqueReqId(
						@RequestParam(value = "uniqueRequestId", required = true) String uniqueRequestId,
						HttpServletRequest request, HttpServletResponse response)

				{
					List tranList = assessmentReportService
							.getptSelfAsmntTransDetailByReqNumber(uniqueRequestId);
					request.setAttribute("repotype", "by_uniq_id");
					return new ModelAndView("ptSelfAsmntTransactionStatusReport",
							"tranList", tranList);

				}
		
	
	
	// Initiate and Update Live DB2 Database
	
		//@SuppressWarnings("null")
		/*@RequestMapping(value = "/updateInitiateTransactionStatusDB2", method = RequestMethod.GET)
		public ModelAndView updateInitiateDB2Live(
				@RequestParam(value = "trans_id", required = true) String trans_id,
				@RequestParam(value = "uniqReqNo", required = true) long uniqReqNo,
				HttpServletRequest request, HttpServletResponse response)
				throws SQLException, JSONException {
	        System.out.println("trans_id "+trans_id+"uniqReqNo "+uniqReqNo);
			//PaymentTransaction paymentTransaction=paymenttrSerivce.gettransingelrecord(Long.parseLong(trans_id));
			String status=paymenttrSerivce.updateInitiatLiveDB2(trans_id, uniqReqNo);
			System.out.println("status---------------->"+status);
			//request.setAttribute("DB2UpdateStatus", db2status);
			request.setAttribute("LocalDBStatus", status);

			return new ModelAndView("selfMutationTransactionStatusReport");
					 }
	*/

}
