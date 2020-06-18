package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.Dao.impl.AssessmentDAOImpl;
import com.mars.cdma.gov.Dao.impl.CashPaymentsDaoImpl;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.CashCounterLoginService;
import com.mars.cdma.gov.service.CashPaymentservice;

@Controller
public class CashCounterPaymentController {
	private static final Logger log = Logger
			.getLogger(CashCounterPaymentController.class);
	@Autowired
	private CashPaymentservice cashPaymentservice;

	@Autowired
	private Assessmentservice assessmentservice;

	SMSHttpPostClient sendsms = new SMSHttpPostClient();
	String citizenMsg;

	@RequestMapping("ptassessmentcashPayment")
	public ModelAndView cashCounterDate(
			@RequestParam(value = "UniqueNumber", required = true) long uniqReqNumber,
			@RequestParam(value = "id", required = true) String loginId,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			NamingException {

		System.out.println("id=" + loginId);
		long payment_transaction_receipt_id = 0;
		System.out.println("Cash payment section");
		AssessmentMaster ptAssmntMaster = cashPaymentservice
				.getptselfAssestsinglerecord(uniqReqNumber);
		PaymentTransaction paymentTransactionEntity = cashPaymentservice
				.insertCashtransaction(uniqReqNumber, request, response,
						loginId);// 1
		String DoorNo = "";
		String AssmntNo = "";
		if (paymentTransactionEntity.getPaid_update_flag() == 'S'
				&& paymentTransactionEntity.getTransaction_flag() == 'S') {
			payment_transaction_receipt_id = paymentTransactionEntity
					.getPayment_transaction_receipt_id();
			/*
			 * Parmanent DoorNumber Assignment
			 */
			System.out.println();
			// cashPaymentservice.updateptSelfAssessement(uniqReqNumber); // 2
			ptAssmntMaster.setDelflag('N');
			ptAssmntMaster.setPaymentflag('Y');
			ptAssmntMaster.setApplication_stage("Application Approved");
			assessmentservice.save(ptAssmntMaster);
			AssessmentMaster ptAssmntMaster1 = cashPaymentservice
					.getptselfAssestsinglerecord(uniqReqNumber);
			System.out.println("String.valueOf((ptAssmntMaster.getUlbcode()))"
					+ String.valueOf((ptAssmntMaster.getUlbcode())));
			DoorNo = DoorDemo.permanentDoorNo(uniqReqNumber);
			ptAssmntMaster1.setpDoorNo(DoorNo);
			AssmntNo = DoorDemo.getAssessmentNo(String.valueOf((ptAssmntMaster1
					.getUlbcode())));
			 System.out.println(AssmntNo+" first AssmntNo");
				AssmntNo=assessmentservice.checkAssessmentNo(AssmntNo);
				System.out.println(AssmntNo+" second AssmntNo");
			ptAssmntMaster1.setAssessmentNo(AssmntNo);
			assessmentservice.save(ptAssmntMaster1);
			@SuppressWarnings("unused")
			NewAssesment xmlbean = new NewAssesment();
			String xmlcontent = xmlbean.getprepairxmlcontent(ptAssmntMaster1);
			@SuppressWarnings("unused")
			String status = null;

			AssessmentDAOImpl asmntDaoImpl = new AssessmentDAOImpl();
			/********** Insert Record in Live Db2 **********/
			int getcountfromlive = asmntDaoImpl
					.getcountfromlive(ptAssmntMaster1);

			if (getcountfromlive == 0) {

				status = new AssessmentDAOImpl().insertlive(ptAssmntMaster1,
						xmlcontent);
				System.out.println("status @@@@@@@@@"+ status);
				

			} else {
				status = "success";

			}
			/********** End Insert Live Db2 **********/
			/********** For Citizen SMs **********/
			citizenMsg = "Dear Applicant,PT Self Assessment Application Fee"
					+ ptAssmntMaster.getPaymentamount()
					+ " SucessFully Done.This is your Assessment Number: "
					+ ptAssmntMaster1.getAssessmentNo()+"and Door Number:"+ptAssmntMaster1.getpDoorNo();
			sendsms.SendSMSmain(
					ptAssmntMaster1.getOwnerMobile(),
					citizenMsg);
			/********** End For Citizen SMs **********/

		} else {
			log.info("Transaction not Updated");

		}

		List<PaymentTransaction> paymentTransaction = (List<PaymentTransaction>) cashPaymentservice
				.getsinglerecordForTransaction(payment_transaction_receipt_id); // 4

		request.setAttribute("transactionReceipt", paymentTransaction);
		request.setAttribute("DoorNo", DoorNo);
		request.setAttribute("AssmntNo", AssmntNo);

		return new ModelAndView("cashpaymentPtAssessmentRecipt",
				"transactionReceipt", paymentTransaction);
	}

	@RequestMapping("cashPaymentQDD")
	public ModelAndView cashCounterpaymentQDD(
			@RequestParam(value = "UniqueNumber", required = true) long uniqReqNumber,
			@RequestParam(value = "id", required = true) String loginId,
			@RequestParam(value = "cheque_DD_No", required = true) String cheque_DD_No,
			@RequestParam(value = "cheque_DD_Date", required = true) Date cheque_DD_Date,
			@RequestParam(value = "cheque_DD_BankBranch", required = true) String cheque_DD_BankBranch,
			@RequestParam(value = "DDCheque", required = true) String DDCheque,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException,
			NamingException {
		long payment_transaction_receipt_id = 0;
		AssessmentMaster asmntmaster = cashPaymentservice
				.getptselfAssestsinglerecord(uniqReqNumber);
		PaymentTransaction paymentTransactionEntity = cashPaymentservice
				.insertChequeDDCashtransaction(uniqReqNumber, request,
						response, loginId, cheque_DD_No, cheque_DD_Date,
						cheque_DD_BankBranch, DDCheque);
		String DoorNo = "";
		String AssmntNo = "";
		if (paymentTransactionEntity.getPaid_update_flag() == 'S'
				&& paymentTransactionEntity.getTransaction_flag() == 'S') {
			payment_transaction_receipt_id = paymentTransactionEntity
					.getPayment_transaction_receipt_id();
			// cashPaymentservice.updateptSelfAssessement(uniqReqNumber);
			asmntmaster.setDelflag('N');
			asmntmaster.setPaymentflag('Y');
			asmntmaster.setApplication_stage("Application Approved");
			assessmentservice.save(asmntmaster);
			AssessmentMaster asmntmaster1 = cashPaymentservice
					.getptselfAssestsinglerecord(uniqReqNumber);
			DoorNo = DoorDemo.permanentDoorNo(uniqReqNumber);
			asmntmaster1.setpDoorNo(DoorNo);
			AssmntNo = DoorDemo.getAssessmentNo(String.valueOf((asmntmaster1
					.getUlbcode())));
			 System.out.println(AssmntNo+" first AssmntNo");
				AssmntNo=assessmentservice.checkAssessmentNo(AssmntNo);
				System.out.println(AssmntNo+" second AssmntNo");
			asmntmaster1.setAssessmentNo(AssmntNo);
			assessmentservice.save(asmntmaster1);

			String status = null;
			AssessmentDAOImpl etradeDaoimpl = new AssessmentDAOImpl();
			/********** Insert Record in Live Db2 **********/
			int getcountfromlive = etradeDaoimpl.getcountfromlive(asmntmaster1);
			if (getcountfromlive == 0) {
				NewAssesment xmlbean = new NewAssesment();
				String xmlcontent = xmlbean.getprepairxmlcontent(asmntmaster1);
				status = etradeDaoimpl.insertlive(asmntmaster1, xmlcontent);
			} else {
				status = "success";
				System.out.println("status" + status);
			}
			/********** End Insert Live Db2 **********/
			/********** For Citizen SMs **********/
			citizenMsg = "Dear Applicant,PT Self Assessment Application Fee"
					+ asmntmaster1.getPaymentamount()
					+ " SucessFully Done.This is your Assessment Number: "
					+ asmntmaster1.getAssessmentNo()+"and Door Number:"+asmntmaster1.getpDoorNo();
			sendsms.SendSMSmain(
					asmntmaster1.getOwnerMobile(),
					citizenMsg);
			/********** End For Citizen SMs **********/
		} else {
			log.info("Transaction not Updated");
		}
		List<PaymentTransaction> paymentTransaction = cashPaymentservice
				.getsinglerecordForTransaction(payment_transaction_receipt_id);

		request.setAttribute("transactionReceipt", paymentTransaction);
		request.setAttribute("DoorNo", DoorNo);
		request.setAttribute("AssmntNo", AssmntNo);
		return new ModelAndView("cashpaymentPtAssessmentRecipt",
				"transactionReceipt", paymentTransaction);
	}

	@RequestMapping(value = "/Ack.do", method = RequestMethod.GET)
	public ModelAndView getNoWaterDetails1(HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
		String dateString = format.format(new java.util.Date());
		request.setAttribute("date", dateString);
		request.setAttribute("noWaterBean", cashPaymentservice.getStatus1(Long
				.parseLong(request.getParameter("UniqueRequestNumber"))));
		AssessmentMaster asmntBean = cashPaymentservice.getStatus1(Long
				.parseLong(request.getParameter("UniqueRequestNumber")));
		request.setAttribute("UniqueNumber", asmntBean.getUniqueRequestNumber());
		request.setAttribute("DoorNumber", asmntBean.getOwnerDoorNo());
		request.setAttribute("Ownername", asmntBean.getOwnerName() + " "
				+ asmntBean.getOwnerSurName());
		request.setAttribute("PaymentAmount", asmntBean.getPaymentamount());
		return new ModelAndView("payMutationAmount");
	}

}
