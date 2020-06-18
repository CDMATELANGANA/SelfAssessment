package com.mars.cdma.gov.Dao;

import java.util.Map;

import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.Login;
import com.mars.cdma.gov.bean.SelfAssmentVerification;

public interface LoginDao {
	public Map<String, String> userLogin(Login user);
	
	public boolean saveAssmentVerification(SelfAssmentVerification selfAssmentVerification);
	public boolean selectOtp(String otp,String mobileNo);
}
