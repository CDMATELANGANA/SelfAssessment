/**
 * 
 */
package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ccavenue.security.AesCryptUtil;
import com.google.gson.Gson;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.AssessmentReportService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.HDFCForPtService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.UlbsService;
import com.mars.cdma.gov.transaction.BillDeskGateWayMac;
import com.mars.cdma.gov.utils.CommonUtils;

/**
 * @author SARITA
 *
 */
@Controller
public class HDFCForPtController {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DistrictsService districtsService;
	@Autowired
	private UlbsService ulbService;
	@Autowired
	private HDFCForPtService hdfcAssessmentService;

	@RequestMapping("hdfcptPay")
	// Live
	public void paynow(
			@RequestParam(value = "requestnumber", required = true) long uniqReqNumber,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("Start");
		PaymentTransaction paymentTransactionEntity = hdfcAssessmentService
				.hdfcInserttransaction(uniqReqNumber, request, response);

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
		//double amount = paymentTransactionEntity.getTotal_amount();
		double amount = 1.00;
		/*Merchant id	247501
		Account Name	COMMISSIONER & DIRECTOR OF MUNICIPAL 
		Domain Registered for Testing	https://cdma.telangana.gov.in/ 
		Access Code	AVVU90HB30AF89UVFA 
		Working Key	 5FAA679860E5EA5F2E335EA09E83CC66*/

		 String accessCode="AVVU90HB30AF89UVFA";		//Put in the Access Code in quotes provided by CCAVENUES.
		 String workingKey ="5FAA679860E5EA5F2E335EA09E83CC66";    //Put in the 32 Bit Working Key provided by CCAVENUES.  
	
		Long d = new Date().getTime();
		String tid=d.toString();
		String ccaRequest="";
		ccaRequest = ccaRequest+ URLEncoder.encode(tid,"UTF-8") + "&"
		+ URLEncoder.encode("247501","UTF-8") + "&"
		+ URLEncoder.encode(receiptNo.toString(),"UTF-8") + "&"
		+ URLEncoder.encode(amount+"","UTF-8") + "&"
		+ URLEncoder.encode("INR","UTF-8") + "&"
		+ URLEncoder.encode(ulbCode+"","UTF-8") + "&"
	    + URLEncoder.encode(ulbName,"UTF-8") + "&"
		+ URLEncoder.encode(uniqueRequestNumbers,"UTF-8") + "&"
		+ URLEncoder.encode(districtName,"UTF-8") + "&"
		+ URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/","UTF-8") + "&"
		+ URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/getassessment.do","UTF-8") + "&"
		+ URLEncoder.encode("EN","UTF-8")
		/*+ URLEncoder.encode(accessCode,"UTF-8")*/;
		System.out.println("After Encript");
		System.out.println("ccaRequest::::" + ccaRequest);
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 String encRequest = aesUtil.encrypt(ccaRequest);
		/* request.setAttribute("encRequest", encRequest);
		 request.setAttribute("access_code", accessCode);*/
		// return new ModelAndView("ccavRequestHandler");
		response.sendRedirect("https://test.ccavenue.com/transaction.do?command=initiateTransaction?encRequest="+ encRequest+"&access_code"+accessCode);
		System.out.println("Hiuijdhfdakshalsfsal");
		//+"&access_code="+accessCode

	}
@RequestMapping("hdfcptPayTest")
// Live
public ModelAndView paynowForHdfc(
		@RequestParam(value = "requestnumber", required = true) long uniqReqNumber,
		Model model, HttpServletRequest request,
		HttpServletResponse response) throws IOException {
	System.out.println("Start");
	PaymentTransaction paymentTransactionEntity = hdfcAssessmentService
			.hdfcInserttransaction(uniqReqNumber, request, response);

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
	double amount = 1.00;
	 String accessCode="AVVU90HB30AF89UVFA";//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey ="5FAA679860E5EA5F2E335EA09E83CC66";//Put in the 32 Bit Working Key provided by CCAVENUES.  
    Long d = new Date().getTime();
	String tid=d.toString();
	String ccaRequest="";
	ccaRequest = ccaRequest + "tid" + "=" + URLEncoder.encode(tid,"UTF-8") + "&"
			+"merchant_id" +"=" + URLEncoder.encode("247501","UTF-8") + "&"
			+"order_id" +"=" + URLEncoder.encode(receiptNo.toString(),"UTF-8") + "&"
			+"amount" +"=" + URLEncoder.encode(amount+"","UTF-8") + "&"
			+"currency" +"=" + URLEncoder.encode("INR","UTF-8") + "&"
			+"ulbCode" +"=" + URLEncoder.encode(ulbCode+"","UTF-8") + "&"
			+"ulbName" +"=" + URLEncoder.encode(ulbName,"UTF-8") + "&"
			+"uniqueNo" +"=" + URLEncoder.encode(uniqueRequestNumbers,"UTF-8") + "&"
			+"districtName" +"=" + URLEncoder.encode(districtName,"UTF-8") + "&"
			+"redirect_url" +"=" + URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/","UTF-8") + "&"
			+"cancel_url" +"=" + URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/getassessment.do","UTF-8") + "&"
			+"language" +"=" + URLEncoder.encode("EN","UTF-8");
	System.out.println("After Encript");
	System.out.println("ccaRequest::::" + ccaRequest);
	AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	 request.setAttribute("encRequest", encRequest);
	 request.setAttribute("access_code", accessCode);
	 return new ModelAndView("ccavRequestHandler");
	//response.sendRedirect("https://test.ccavenue.com/transaction.do?command=initiateTransaction?encRequest="+ encRequest+"&access_code"+accessCode);
	//+"&access_code="+accessCode

}


}
/*ccaRequest = ccaRequest + "tid" + "=" + URLEncoder.encode(tid,"UTF-8") + "&"
		+"merchant_id" +"=" + URLEncoder.encode("247501","UTF-8") + "&"
		+"order_id" +"=" + URLEncoder.encode(receiptNo.toString(),"UTF-8") + "&"
		+"amount" +"=" + URLEncoder.encode(amount+"","UTF-8") + "&"
		+"currency" +"=" + URLEncoder.encode("INR","UTF-8") + "&"
		+"ulbCode" +"=" + URLEncoder.encode(ulbCode+"","UTF-8") + "&"
		+"ulbName" +"=" + URLEncoder.encode(ulbName,"UTF-8") + "&"
		+"uniqueNo" +"=" + URLEncoder.encode(uniqueRequestNumbers,"UTF-8") + "&"
		+"districtName" +"=" + URLEncoder.encode(districtName,"UTF-8") + "&"
		+"redirect_url" +"=" + URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/","UTF-8") + "&"
		+"cancel_url" +"=" + URLEncoder.encode("http://125.18.179.59:8081/PTSelfAssessmentTaxCal/getassessment.do","UTF-8") + "&"
		+"language" +"=" + URLEncoder.encode("EN","UTF-8")+ "&"
		+"access_code" +"=" + URLEncoder.encode(accessCode,"UTF-8") + "|";
		System.out.println("After Encript");
		System.out.println("ccaRequest::::" + ccaRequest);
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		 String encRequest = aesUtil.encrypt(ccaRequest);


*/
