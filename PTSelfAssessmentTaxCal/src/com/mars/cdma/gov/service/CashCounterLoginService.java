package com.mars.cdma.gov.service;

import java.sql.SQLException;
import java.util.List;

import com.mars.cdma.gov.bean.AssessmentMaster;

public interface CashCounterLoginService {

	String userLogin(AssessmentMaster selfMutationApp, String vC_USRNAME,
			String vC_USRPWD)  throws SQLException;

	List<AssessmentMaster> getUniquetData(long uniqueNumber);

	AssessmentMaster getBean(long uniqueNumber);

}
