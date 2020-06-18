package com.mars.cdma.gov.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mars.cdma.gov.Dao.CashCounterLoginDao;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.service.CashCounterLoginService;


@Service
public class CashCounterLoginServiceImpl implements CashCounterLoginService{
	
	
	@Autowired
	private CashCounterLoginDao cashcounterLoginDao;

	@Override
	public String userLogin(AssessmentMaster selfMutationApp,
			String vC_USRNAME, String vC_USRPWD) throws SQLException {
		return  cashcounterLoginDao.userLogin(selfMutationApp,vC_USRNAME,vC_USRPWD);
	}

	@Override
	public List<AssessmentMaster> getUniquetData(long uniqueNumber) {
		return cashcounterLoginDao.getUniquetData(uniqueNumber);
	}

	@Override
	public AssessmentMaster getBean(long uniqueNumber) {
		// TODO Auto-generated method stub
		return cashcounterLoginDao.getBean(uniqueNumber);
	}

}
