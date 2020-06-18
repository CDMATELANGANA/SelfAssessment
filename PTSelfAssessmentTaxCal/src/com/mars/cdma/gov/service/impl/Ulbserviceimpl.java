package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.omg.CORBA.ULongLongSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.UlbsDAO;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.UlbsService;

@Service("ulbService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class Ulbserviceimpl implements UlbsService{

	@Autowired 
	public UlbsDAO ulbsDAO; 
	
	@Override
	public List<Ulbs> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		 return ulbsDAO.findByProperty(propertyName, value);
		 
	}

	@Override
	public Ulbs getByUlbname(int ulbCode) {
		// TODO Auto-generated method stub
		return ulbsDAO.getulbnameByUlbsCode(ulbCode);
	}
	
	//shifted AssessmentServiceImpl

	/*@Override
	public void save(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		this.ulbsDAO.save(taxcalservicebean);
	}

	@Override
	public Ulbs getulbByCode(int ulbCode) {
		// TODO Auto-generated method stub
		return ulbsDAO.getulbByCode(ulbCode);
	}

	@Override
	public boolean saveDet(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		return ulbsDAO.saveDet(taxcalservicebean) ;
	}
*/
	 

	
	
}
