package com.mars.cdma.gov.Dao;

import java.sql.SQLException;
import java.util.List;

import com.mars.cdma.gov.bean.AssessmentMaster;

public interface CashCounterLoginDao {

	String userLogin(AssessmentMaster selfMutationApp, String vC_USRNAME,
			String vC_USRPWD) throws SQLException;

	List<AssessmentMaster> getUniquetData(long uniqueNumber);

	AssessmentMaster getBean(long uniqueNumber);

}
