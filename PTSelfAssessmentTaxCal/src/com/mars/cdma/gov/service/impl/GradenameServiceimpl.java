package com.mars.cdma.gov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.GradeDAO;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.service.GradeService;
@Service("GradeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GradenameServiceimpl implements GradeService {
@Autowired
private GradeDAO gradeDAO;
	
	@Override
	public Grademaster gradename(int gradecode) {
		// TODO Auto-generated method stub
		return gradeDAO.gradename(gradecode);
	}

}
