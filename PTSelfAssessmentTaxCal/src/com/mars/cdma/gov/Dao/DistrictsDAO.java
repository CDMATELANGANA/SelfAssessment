package com.mars.cdma.gov.Dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.RegionMstr;
import com.mars.cdma.gov.service.DistrictsSearch;

 

public interface DistrictsDAO {
	/**
	 * This method returns Districts object for a given Id
	 * 
	 * @param id
	 *        as long
	 * @return Districts as model object
	 */
	public Districts get(long id);
	public RegionMstr getRegion(int id);
	
	/**
	 * This method returns count of the Districts objects from the database
	 * 
	 * @param DistrictsSearch
	 * @return long
	 * @exception Exception
	 */
	public long getDistrictsCount(DistrictsSearch searchOptions);

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
	public void save(Districts entity);

	/**
	 * This method to merge the Districts information to the database.
	 * 
	 * @param Districts
	 *        object
	 */
	public Districts merge(Districts entity);
	/**
	 * This method delete Districts for a given Id
	 * 
	 * @param id
	 *        as long
	 */
	public void delete(long id);

	/**
	 * This method will return a list of all Districts from the database
	 * 
	 * @return List of Districts
	 */
	public List<Districts> getAll();
	
	public List<Districts> getAllOrderByName();
	/**
	 * This method save/updates a list of Districts information to the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void saveAll(List<Districts> listDistricts);

	/**
	 * This method is used to delete a list of Districts records from the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void deleteAll(List<Districts> listDistricts);

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
	 * This method returns list of the Districts objects available based on DistrictsSearch searchOptions.
	 * 
	 * @param DistrictsSearch
	 *        searchOptions
	 * @return List
	 * @exception Exception
	 */
	public List<Districts> getDistrictsList(DistrictsSearch searchOptions);
}
