package com.mars.cdma.gov.service;

import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.bean.Grademaster;
@Transactional(readOnly = true)
public interface GradeService {

	public Grademaster gradename(int gradecode); 
	
}
