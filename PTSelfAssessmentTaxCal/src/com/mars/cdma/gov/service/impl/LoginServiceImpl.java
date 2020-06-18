package com.mars.cdma.gov.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.LoginDao;
import com.mars.cdma.gov.bean.Login;
import com.mars.cdma.gov.bean.SelfAssmentVerification;
import com.mars.cdma.gov.service.LoginService;

@Service("LoginServive")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	public Map<String, String> userLogin(Login login) {
		return loginDao.userLogin(login);
	}

	@Override
	public boolean saveAssmentVerification(
			SelfAssmentVerification selfAssmentVerification) {
		return loginDao.saveAssmentVerification(selfAssmentVerification);
	}

	@Override
	public boolean selectOtp(String otp ,String mobileNo) {
		return loginDao.selectOtp(otp,mobileNo);
	}

}
