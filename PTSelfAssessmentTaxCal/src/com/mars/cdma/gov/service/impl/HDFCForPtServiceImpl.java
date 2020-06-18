package com.mars.cdma.gov.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.HDFCForPtDAO;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.service.HDFCForPtService;

/**
 * @author SARITA
 *
 */
@Service
public class HDFCForPtServiceImpl implements HDFCForPtService {
@Autowired
private HDFCForPtDAO hdfcForPaymentDao;
	@Override
	public PaymentTransaction hdfcInserttransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return hdfcForPaymentDao.hdfcInserttransaction(uniqReqNumber,request,response);
	}

}
