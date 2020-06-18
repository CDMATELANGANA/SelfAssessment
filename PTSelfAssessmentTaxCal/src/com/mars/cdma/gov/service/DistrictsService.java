package com.mars.cdma.gov.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.RegionMstr;
 

/**
 * <p>
 * Title: DistrictService.java
 * </p>
 * <p>
 * Description: This interface is used for service implementations related to DistrictsService
 * </p>
 * 
 * @see com.mars.cdma.model.Districts Copyright (c) 2014 Mars Telecom India Pvt Ltd
 * @version: 1.0
 * @author : eGov Development Team <Mars Telecom Systems Pvt Ltd>
 */
@Transactional(readOnly = true)
public interface DistrictsService {

	/**
	 * This method returns Districts object for a given Id
	 * 
	 * @param id
	 *        as long
	 * @return Districts as model object
	 */
	
	public Districts get(long id);
	public RegionMstr  getRegion(int id);
	/**
	 * This method returns count of the Districts objects from the database
	 * 
	 * @param DistrictsSearch
	 *        searchOptions
	 * @return long
	 * @exception Exception
	 */
	//public long getDistrictsCount(DistrictsSearch searchOptions);

	/**
	 * This method returns Districts object for a given Id
	 * 
	 * @param districtName
	 *        as String
	 * @return Districts as model object
	 */
	public Districts getByDistrictName(String districtName);

	/**
	 * This method save/updates the Districts information to the database.
	 * 
	 * @param Districts
	 *        object
	 */
	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
	public void save(Districts entity);

	/**
	 * This method to merge the Districts information to the database.
	 * 
	 * @param Districts
	 *        object
	 */
	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
	public Districts merge(Districts entity);

	/**
	 * This method delete Districts for a given Id
	 * 
	 * @param id
	 *        as long
	 */
	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
	public void delete(long id);

	/**
	 * This method save/updates a list of DistrictsService information to the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
	public void saveAll(List<Districts> listDistricts);

	/**
	 * This method is used to delete a list of Module records from the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	@Transactional(readOnly = false, rollbackFor = java.lang.Exception.class)
	public void deleteAll(List<Districts> listDistricts);

	/**
	 * This method will return a list of all DistrictsService from the database
	 * 
	 * @return List of DistrictsService
	 */
	public List<Districts> getAll();
	
	public List<Districts> getAllOrderByName();

	/**
	 * This method returns list of Districts objects available based on the value for a particular property.
	 * 
	 * @param String
	 *        propertyName
	 * @param Object
	 *        value
	 * @return List
	 * @exception Exception
	 */
	public List<Districts> findByProperty(String propertyName, Object value);

	/**
	 * This method returns Districts object for a given data
	 * 
	 * @param Districts
	 *        as districts
	 * @return Districts as model object
	 */
	public Districts checkForDuplicates(Districts districts);

	/**
	 * This method returns list of Districts objects available based on the query names passed.
	 * 
	 * @param DistrictsSearch
	 *        searchOptions
	 * @return List
	 * @exception Exception
	 */
	//public List<Districts> getDistrictsList(DistrictsSearch searchOptions);
}
