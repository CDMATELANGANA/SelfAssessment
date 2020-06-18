package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.cdma.gov.Dao.DistrictsDAO;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.RegionMstr;
import com.mars.cdma.gov.service.DistrictsSearch;
import com.mars.cdma.gov.service.DistrictsService;

 

 

/**
 * <p>
 * Title: DistrictsServiceImpl.java
 * </p>
 * 
 * @see com.mars.cdma.model.districtsService
 *      <p>
 *      Description: This class is used for hibernate operations for districtsService module
 *      </p>
 *      Copyright (c) 2014 Mars Telecom India Pvt Ltd
 * @version: 1.0
 * @author : eGov Development Team <Mars Telecom Systems Pvt Ltd>
 */
@Service("districtsService")
public class DistrictsServiceImpl implements DistrictsService {

	private static final Logger log= Logger.getLogger(DistrictsServiceImpl.class);

	@Autowired
	private DistrictsDAO districtsDAO;
	
	

	/**
	 * This method is saves the districtsService object
	 * 
	 * @param entity
	 *        as districtsService object
	 * @return
	 */
	public void save(Districts entity) {
		if (log.isDebugEnabled()) {
			log.debug("save Method is called ");
		}
		districtsDAO.save(entity);
	}

	/**
	 * This method to merge the districtsService information to the database.
	 * 
	 * @param districtsService
	 *        object
	 */
	public Districts merge(Districts entity) {
		if (log.isDebugEnabled()) {
			log.debug("merge Method is called ");
		}
		return districtsDAO.merge(entity);
	}

	/**
	 * This method delete the districts
	 * 
	 * @param Id
	 *        as long
	 * @return
	 */
	public void delete(long id) {
		if (log.isDebugEnabled()) {
			log.debug("delete Method is called ");
		}
		districtsDAO.delete(id);
	}

	/**
	 * This method is used to get the Model object from the database using hibernate based on id
	 * 
	 * @param Id
	 *        as long
	 * @return a districtsService Object
	 */
	public Districts get(long id) {
		if (log.isDebugEnabled()) {
			log.debug("get Method is called");
		}
		return districtsDAO.get(id);
	}
	public RegionMstr getRegion(int id) {
		if (log.isDebugEnabled()) {
			log.debug("get Method is called");
		}
		return districtsDAO.getRegion(id);
	}
	
	/**
	 * This method returns count of the Districts objects from the database
	 * 
	 * @param DistrictsSearch
	 *        searchOptions
	 * @return long
	 * @exception Exception
	 */
	public long getDistrictsCount(DistrictsSearch searchOptions) {
		if (log.isDebugEnabled()) {
			log.debug("getDistrictsCount Method is called");
		}
		return districtsDAO.getDistrictsCount(searchOptions);
	}

	/**
	 * This method returns districtsService object for a given Id
	 * 
	 * @param districtName
	 *        as String
	 * @return districtsService as model object
	 */
	public Districts getByDistrictName(String districtName) {
		if (log.isDebugEnabled()) {
			log.debug("get Method is called");
		}
		return districtsDAO.getByDistrictName(districtName);
	}

	/**
	 * This method will return a list of all districtsService from the database
	 * 
	 * @return List of districtsService
	 */
	public List<Districts> getAll() {
		if (log.isDebugEnabled()) {
			log.debug("getAll Method is called");
		}
		return districtsDAO.getAll();
	}

	
	public List<Districts> getAllOrderByName() {
		if (log.isDebugEnabled()) {
			log.debug("getAllOrderByName Method is called");
		}
		return districtsDAO.getAllOrderByName();
	}
	
	/**
	 * This method save/updates a list of districtsService information to the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void saveAll(List<Districts> listDistricts) {
		if (log.isDebugEnabled()) {
			log.debug("saveAll Method is called");
		}
		districtsDAO.saveAll(listDistricts);
	}

	/**
	 * This method is used to delete a list of Module records from the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void deleteAll(List<Districts> listDistricts) {
		if (log.isDebugEnabled()) {
			log.debug("deleteAll Method is called");
		}
		districtsDAO.deleteAll(listDistricts);
	}

	/**
	 * This method returns list of districtsService objects available based on the value for a particular property.
	 * 
	 * @param String
	 *        propertyName
	 * @param Object
	 *        value
	 * @return List
	 * @exception Exception
	 */
	public List<Districts> findByProperty(String propertyName, Object value) {
		if (log.isDebugEnabled()) {
			log.debug("findByProperty Method is called");
		}
		return districtsDAO.findByProperty(propertyName, value);
	}

	/**
	 * This method returns districtsService object for a given data
	 * 
	 * @param districtsService
	 *        as districts
	 * @return districtsService as model object
	 */
	public Districts checkForDuplicates(Districts districts) {
		if (log.isDebugEnabled()) {
			log.debug("checkForDuplicates Method is called");
		}
		return districtsDAO.checkForDuplicates(districts);

	}

	/**
	 * This method returns list of the Districts objects available based on DistrictsSearch searchOptions.
	 * 
	 * @param DistrictsSearch
	 *        searchOptions
	 * @return List
	 * @exception Exception
	 */
	public List<Districts> getDistrictsList(DistrictsSearch searchOptions) {

		if (log.isDebugEnabled()) {
			log.debug("getUserList Method is called");
		}
		return districtsDAO.getDistrictsList(searchOptions);
	}

	

}
