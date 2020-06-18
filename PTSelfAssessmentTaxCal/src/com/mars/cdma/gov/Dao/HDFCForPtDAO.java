package com.mars.cdma.gov.Dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mars.cdma.gov.bean.PaymentTransaction;

public interface HDFCForPtDAO  {

	public PaymentTransaction hdfcInserttransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response);

}
