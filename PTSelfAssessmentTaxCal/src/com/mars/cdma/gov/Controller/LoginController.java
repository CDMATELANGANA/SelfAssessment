package com.mars.cdma.gov.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.bean.Login;
import com.mars.cdma.gov.bean.PtApplicationStatus;
import com.mars.cdma.gov.bean.SelfAssmentVerification;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
import com.mars.cdma.gov.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	Md5PasswordEncoder encoder;
	@Autowired
	LoginService loginService;


	public void setEncoder(Md5PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	
	
	/*@RequestMapping(value="login.do",method=RequestMethod.GET)
	public ModelAndView Login()
	{
		return new ModelAndView("login");
	}*/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpSession session) {
		if (session.getAttribute("permissionSession") == null) {
			return "login";
		} else {
			return "user";
		}
	}

	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute("login") Login login,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		login.setUserPassword(encoder.encodePassword(login.getUserPassword(),
				null));
		// System.out.println(login.getUserPassword());
		Map<String, String> permissionSessionValues = loginService
				.userLogin(login);

		if (!permissionSessionValues.containsKey("fail")) {
			session.setAttribute("permissionSession", permissionSessionValues);
			System.out.println("ulbcode=="+session.getAttribute("ulbCode"));
			
			//return new ModelAndView("redirect:/UlbReport.do");
			return new ModelAndView("welcome");
			
		} else {
			if (session != null) {
				session.invalidate();
			}
			return new ModelAndView("login", "message",
					"Login Faild! Try Again");
		}

	}

	
	
	@RequestMapping(value = "/logoutprecess", method = RequestMethod.GET)
	public String userLogout(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		// System.out.println("AAAAAAAAAAAAAAAAAAAAAA");

		if (session.getAttribute("permissionSession") != null) {
			session.removeAttribute("permissionSession");
			session.invalidate();
			request.setAttribute("message", "You Have Logged Out Successfully");
		} else {
			request.setAttribute("message", "");
		}
		return "login";
	}
	
	@RequestMapping(value = "/otp.do", method = RequestMethod.GET)
	public ModelAndView getOTP(HttpSession session) {
		return new ModelAndView("PT_OTP_Form");
		
	}
	
	@RequestMapping(value = "/PT_OTPSave", method = RequestMethod.POST)
	public ModelAndView getPT_OTP(@ModelAttribute("SelfAssmentVerification") SelfAssmentVerification selfAssmentVerification,
			BindingResult result, Model model,HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		//long Uniqueseq =  Calendar.getInstance().getTimeInMillis();
		boolean flag=loginService.saveAssmentVerification(selfAssmentVerification);
		request.setAttribute("mobileNo", selfAssmentVerification.getMobileNo());
		selfAssmentVerification.getMobileNo();
		System.out.println("selfAssmentVerification.getMobileNo()"+selfAssmentVerification.getMobileNo());
		if(flag==true ){
			System.out.println(flag);
			return new ModelAndView("PT_OTP_Conform");
		}
		else
		{
			return new ModelAndView("PT_OTP_Form");//, "msg", "No data save please try again !");
		}
	}
	@RequestMapping(value = "/PT_OTPConform", method = RequestMethod.POST)
	public String getPT_OTPConform(@RequestParam(value = "otpId", required = true) String otp,
			@RequestParam(value = "mobileNo", required = true) String mobileNo,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag=loginService.selectOtp(otp,mobileNo);
		if(flag==true)
		{
			request.setAttribute("repotype", "otpId");
			//return "redirect:/getassessment.do";
			return "redirect:/ptFormSalection.do";
		}
		else
		{
			request.setAttribute("mobileNo", mobileNo);
			return ("PT_OTP_Conform");
		}
}
	

}


	


