package com.mars.cdma.gov.Dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.type.NullableType;

import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.search.UlbSearch;

 

public interface UlbsDAO {
	
	/**
	 * This method returns Ulbs object for a given Id
	 * 
	 * @param id
	 *        as long
	 * @return Ulbs as model object
	 */
	public Ulbs get(long id);

	/**
	 * This method returns Ulbs object for a given Id
	 * 
	 * @param userName
	 *        as String
	 * @return Ulbs as model object
	 */
	public Ulbs getByUlbsName(String userName);
	
	public Ulbs getulbnameByUlbsCode(int ulbCode);
	
	/**
	 * This method save/updates the Ulbs information to the database.
	 * 
	 * @param Ulbs
	 *        object
	 */
	public void save(Ulbs entity);

	/**
	 * This method to merge the Ulbs information to the database.
	 * 
	 * @param Ulbs
	 *        object
	 */
	public Ulbs merge(Ulbs entity);

	/**
	 * This method delete Ulbs for a given Id
	 * 
	 * @param id
	 *        as long
	 */
	public void delete(long id);

	/**
	 * This method will return a list of all Ulbs from the database
	 * 
	 * @return List of Ulbs
	 */
	public List<Ulbs> getAll();
	
	/**
	 * This method will return a list of all Ulbs in order from the database
	 * 
	 * @return List of Ulbs
	 */
	public List<Ulbs> getAllOrderByName();

	/**
	 * This method save/updates a list of Ulbs information to the database.
	 * 
	 * @param listUlbs
	 *        as List
	 */
	public void saveAll(List<Ulbs> listUlbs);

	/**
	 * This method is used to delete a list of Ulbs records from the database.
	 * 
	 * @param listUlbs
	 *        as List
	 */
	public void deleteAll(List<Ulbs> listUlbs);


	/**
	 * This method returns list of Ulbs objects available based on the value for a particular property.
	 * 
	 * @param String
	 *        propertyName
	 * @param Object
	 *        value
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> findByProperty(String propertyName, Object value);

	/**
	 * This method returns list of Ulbs objects available based on the query names passed.
	 * 
	 * @param String
	 *        namedQuery
	 * @param hstKeyValues
	 *        as Hashatble containing the Parameter name as key and value.
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> findByDistrict(String propertyName, Districts districts);
	
	public List<Ulbs> findNewUlbs(String propertyName,String val,Integer districtId);
	
	public List<Ulbs> getListByNamedQuery(String namedQuery, Hashtable<String, Object> hstKeyValues);

	/**
	 * This method returns list of Ulbs objects available based on the query names passed.
	 * 
	 * @param String
	 *        hqlQuery
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> getListByHqlQuery(String hqlQuery);

	/**
	 * This method returns list of Ulbs objects available based on the SQL query and parameters.
	 * 
	 * @param String
	 *        SQL Query
	 * @param hstDataTypes
	 *        as Hashatble containing the Scalar Variable and Value
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> getEntityListBySQLQuery(String strSql, Hashtable<String, NullableType> hstDataTypes);

	/**
	 * This method returns list selected columns as list based on the SQL query and parameters.
	 * 
	 * @param String
	 *        SQL Query
	 * @return List
	 * @exception Exception
	 */
	public List<ArrayList> getListBySQLQuery(String strSql, Hashtable<String, NullableType> hstDataTypes);
	/**
	 * This method returns count of the Ulbs objects from the database
	 * 
	 * @param UlbSearch
	 * @return long
	 * @exception Exception
	 */
	public long getUlbsCount(UlbSearch searchOptions);
	/**
	 * This method returns list of the Ulbs objects available based on UlbSearch searchOptions.
	 * 
	 * @param UlbSearch
	 *        searchOptions
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> getUlbsList(UlbSearch searchOptions);

	/**
	 * This method returns Ulbs object for a given data
	 * 
	 * @param Ulbs
	 *        as ulbs
	 * @return Ulbs as model object
	 */
	public Ulbs checkForDuplicates(Ulbs ulbs);

	public Grademaster getGrade(int id);
	
	
}
