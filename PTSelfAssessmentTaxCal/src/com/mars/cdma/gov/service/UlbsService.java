package com.mars.cdma.gov.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;


@Transactional(readOnly = false)
public interface UlbsService {
	public List<Ulbs> findByProperty(String propertyName, Object value);
	
	public  Ulbs  getByUlbname(int ulbCode);
	
	 
	
}
