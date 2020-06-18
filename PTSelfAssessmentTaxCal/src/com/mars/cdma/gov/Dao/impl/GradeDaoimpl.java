package com.mars.cdma.gov.Dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.GradeDAO;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.bean.Ulbs;
@Repository("GradeDao")
public class GradeDaoimpl implements GradeDAO  {

	@Autowired SessionFactory sessionFactory;
	@Override
	public Grademaster gradename(int gradecode) {
		// TODO Auto-generated method stub
		 Grademaster  grademaster;
		 Session  session=sessionFactory.openSession();
		 session.beginTransaction();
		 Criteria criteria=session.createCriteria(Grademaster.class);
		 criteria.add(Restrictions.eq("gradeCode", gradecode));
		 //grade_code, grade_name, grade_flag, updated_time
		 grademaster=(Grademaster)criteria.uniqueResult(); 
		 
		
		return grademaster;
	}

	
}

