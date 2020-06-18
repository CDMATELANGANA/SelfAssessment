package com.mars.cdma.gov.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.service.CashCounterLoginService;



@Controller
public class CashCounterLoginController {
	
	@Autowired
	Md5PasswordEncoder encoder;

	public void setEncoder(Md5PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	@Autowired
	private CashCounterLoginService cashcounterLoginService;

	
	
	
	@RequestMapping("/cashcounterLogin")
	public ModelAndView counterLogin(@RequestParam(value="requestnumber",required = true) String UniqueNumber
			,Model model, HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		System.out.println("login="+UniqueNumber);
		
		request.setAttribute("UniqueNumber", UniqueNumber);
		
		
		return new ModelAndView ("cashCounterLogin");
		
	}
	
	@RequestMapping(value = "/cashcounterLogin1", method = RequestMethod.POST)
	public ModelAndView counteUserLogin(@RequestParam("counterUserId")String VC_USRNAME,@RequestParam("counterUserPassword")String VC_USRPWD,
			
			@RequestParam("UniqueNumber") long UniqueNumber,
			  HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws SQLException {
		System.out.println("VC_USRNAME"+VC_USRNAME);
		System.out.println("VC_USRPWD"+VC_USRPWD);
		System.out.println("uniqReqNumber="+UniqueNumber);
		AssessmentMaster selfMutationApp=cashcounterLoginService.getBean(UniqueNumber);
		System.out.println("selfMutationApp"+selfMutationApp.getUlbcode());
		
		String userId = cashcounterLoginService.userLogin(selfMutationApp,VC_USRNAME,VC_USRPWD);
		String userId1=userId.replace("[","").replace("]", "");
		System.out.println("userId"+userId1);
		request.setAttribute("userId1", userId1);
		request.setAttribute("UniqueNumber", UniqueNumber);
		List<AssessmentMaster> getUniqueData = new ArrayList<AssessmentMaster>();
		//String l="99991543408306913";
		getUniqueData = cashcounterLoginService.getUniquetData(UniqueNumber);
		System.out.println("getUniqueData="+getUniqueData);
		
		return new ModelAndView("cashcounterPayment","getUniqueData",getUniqueData);
	}
	
	@RequestMapping(value = "/cashCounterLoginAuthentication", method = RequestMethod.POST)
	public ModelAndView counteUserLogin1(@RequestParam("counterUserId")String VC_USRNAME,@RequestParam("counterUserPassword")String VC_USRPWD,
			
			@RequestParam("UniqueNumber") long UniqueNumber,
			  HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws SQLException {
		System.out.println("VC_USRNAME"+VC_USRNAME);
		System.out.println("VC_USRPWD"+VC_USRPWD);
		System.out.println("uniqReqNumber="+UniqueNumber);
		AssessmentMaster eTradeApplication=cashcounterLoginService.getBean(UniqueNumber);
		String userId = cashcounterLoginService.userLogin(eTradeApplication,VC_USRNAME,VC_USRPWD);
		String userId1=userId.replace("[","").replace("]", "");
		System.out.println("userId"+userId1);
		request.setAttribute("userId1", userId1);
		request.setAttribute("UniqueNumber", UniqueNumber);
		List<AssessmentMaster> getUniqueData = new ArrayList<AssessmentMaster>();
		getUniqueData = cashcounterLoginService.getUniquetData(UniqueNumber);
		return new ModelAndView("ptCashPayment","getUniqueData",getUniqueData);
	}
	

@RequestMapping("/cashCounterLoginQDD")
public ModelAndView counterLoginQDD(@RequestParam(value="requestnumber") String UniqueNumber
		,@RequestParam(value = "cheque_DD_No", required = true) String  cheque_DD_No,
		@RequestParam(value = "cheque_DD_Date", required = true) String  cheque_DD_Date,
		@RequestParam(value = "cheque_DD_BankBranch", required = true) String  cheque_DD_BankBranch
		,@RequestParam(value = "DDCheque", required = true) String  DDCheque,
		 HttpServletRequest request,
		 HttpServletResponse response) throws IOException {
	System.out.println("requestnumber="+UniqueNumber);
	System.out.println("DDCheque+++++="+DDCheque);
	
	request.setAttribute("UniqueNumber", UniqueNumber);
	request.setAttribute("cheque_DD_No", cheque_DD_No);
	request.setAttribute("cheque_DD_Date", cheque_DD_Date);
	request.setAttribute("cheque_DD_BankBranch", cheque_DD_BankBranch);
	request.setAttribute("DDCheque", DDCheque);
	
	return new ModelAndView ("CounterLoginQDD");
}


@RequestMapping(value = "/cashCounterLoginAuthenticationQDD", method = RequestMethod.POST)
public ModelAndView counteUserLoginQDD(@RequestParam("counterUserId")String VC_USRNAME,@RequestParam("counterUserPassword")String VC_USRPWD,
		
		@RequestParam("UniqueNumber") long UniqueNumber,
		@RequestParam(value = "cheque_DD_No", required = true) String  cheque_DD_No,
		@RequestParam(value = "cheque_DD_Date", required = true) String  cheque_DD_Date,
		@RequestParam(value = "cheque_DD_BankBranch", required = true) String  cheque_DD_BankBranch
		,@RequestParam(value = "DDCheque", required = true) String  DDCheque,
		  HttpServletRequest request,
		HttpServletResponse response, HttpSession session) throws SQLException {
	System.out.println("VC_USRNAME"+VC_USRNAME);
	System.out.println("VC_USRPWD"+VC_USRPWD);
	System.out.println("uniqReqNumber="+UniqueNumber);
	request.setAttribute("cheque_DD_No", cheque_DD_No);
	request.setAttribute("cheque_DD_Date", cheque_DD_Date);
	request.setAttribute("cheque_DD_BankBranch", cheque_DD_BankBranch);
	request.setAttribute("DDCheque", DDCheque);
	AssessmentMaster asmntmaster=cashcounterLoginService.getBean(UniqueNumber);
	String userId = cashcounterLoginService.userLogin(asmntmaster,VC_USRNAME,VC_USRPWD);
	String userId1=userId.replace("[","").replace("]", "");
	System.out.println("userId"+userId1);
	request.setAttribute("userId1", userId1);
	request.setAttribute("UniqueNumber", UniqueNumber);
	List<AssessmentMaster> getUniqueData = new ArrayList<AssessmentMaster>();
	getUniqueData = cashcounterLoginService.getUniquetData(UniqueNumber);
	System.out.println("getUniqueData="+getUniqueData);
	return new ModelAndView("ptCashPaymentQDD","getUniqueData",getUniqueData);
}
	
}
