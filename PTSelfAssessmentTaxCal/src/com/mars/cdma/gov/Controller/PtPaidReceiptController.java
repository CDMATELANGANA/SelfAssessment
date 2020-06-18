package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mars.cdma.gov.service.PtPaidReceiptService;
import com.mars.cdma.gov.service.PtTaxPaidHistoryService;

/**
 * @author SARITA
 *
 */
@Controller
public class PtPaidReceiptController {
	@Autowired
	private PtPaidReceiptService ptPaidReceiptService;
	public Gson gson = new Gson();

	@RequestMapping("/getSearchAssLastPaidReceipt.do")
	public ModelAndView getSearchAssLastPaidReceipt() {
		return new ModelAndView("PtPaidReceiptSearch");
	}
	
	@RequestMapping("/getSearchAssLastPaidReceiptSms.do")
	public ModelAndView getSearchAssLastPaidReceiptSms(
			@RequestParam(value="assessment",required=true)String assessment,
			@RequestParam(value="mobile",required=true)String mobile,
			HttpServletRequest request, HttpServletResponse response
			) throws IOException  {
		String otp=ptPaidReceiptService.ptAssPaidReceiptSms(mobile, assessment);
		request.setAttribute("otp", otp);
		request.setAttribute("assessment", assessment);
		request.setAttribute("mobile", mobile);
	
		return new ModelAndView("PtPaidReceiptOtp");
	}
	
	@RequestMapping("/getAssPaidLastReceipt.do")
	public ModelAndView getAssPaidLastReceipt(
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
			 flag=ptPaidReceiptService.savePtAssPaidReceipt(assessment, mobile);
			 HashMap<String, Object> hmParams = new HashMap<String, Object>();
			 hmParams= ptPaidReceiptService.getPtAssPaidLastReceiptFromDb2(assessment.substring(0, 4), assessment);
			request.setAttribute("VC_ONRSURNAME",hmParams.get("VC_ONRSURNAME"));
			request.setAttribute("VC_ONRNAME",hmParams.get("VC_ONRNAME"));
			//request.setAttribute("VC_ONRDOORNO",hmParams.get("VC_ONRDOORNO"));
			request.setAttribute("DOORNo",hmParams.get("DOORNo"));
			request.setAttribute("VC_LCTYNAME",hmParams.get("VC_LCTYNAME"));
			request.setAttribute("DT_ETRYDT",hmParams.get("DT_ETRYDT"));
			request.setAttribute("I_SLNO",hmParams.get("I_SLNO"));
			request.setAttribute("C_RCPTNO",hmParams.get("C_RCPTNO"));
			request.setAttribute("C_AMTPAIDAT",hmParams.get("C_AMTPAIDAT"));
			request.setAttribute("C_PAYMODE",hmParams.get("C_PAYMODE"));
			request.setAttribute("Total",hmParams.get("Total"));
			request.setAttribute("DT_PAIDFRMPRDDT",hmParams.get("DT_PAIDFRMPRDDT"));
			request.setAttribute("DT_PAIDTOPRDDT",hmParams.get("DT_PAIDTOPRDDT"));
			request.setAttribute("VC_ULBNAME",hmParams.get("VC_ULBNAME"));
			request.setAttribute("grade_name",hmParams.get("grade_name"));
			request.setAttribute("ulb_name",hmParams.get("ulb_name"));
			request.setAttribute("Totalpaidamount",hmParams.get("Totalpaidamount"));
			request.setAttribute("balance",hmParams.get("balance"));
			System.out.println("hmParams="+hmParams);
			 return new ModelAndView("PtPaidReceipt","hmParams",hmParams);
		}
		else{
			return new ModelAndView( "redirect:/getSearchAssLastPaidReceipt.do");
		}
	}
}
