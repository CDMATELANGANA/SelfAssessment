package com.mars.cdma.gov.Dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.type.NullableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.DistrictsDAO;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.RegionMstr;
import com.mars.cdma.gov.service.DistrictsSearch;
import com.mars.cdma.gov.utils.CommonUtils;
import com.mars.cdma.gov.utils.Constants;

 
/**
 * <p>
 * Title: DistrictsDAOImpl.java
 * </p>
 * 
 * @see com.mars.cdma.model.Districts
 *      <p>
 *      Description: This class is used for hibernate operations for Districts module
 *      </p>
 *      Copyright (c) 2014 Mars Telecom India Pvt Ltd
 * @version: 1.0
 * @author : Mars Telecom Systems
 */
@Repository("districtsDAO")
public class DistrictsDAOImpl extends HibernateDaoSupport implements DistrictsDAO {

	private static final Logger log= Logger.getLogger(DistrictsDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	/**
	 * This method returns Districts object for a given Id
	 * 
	 * @param id
	 *        as long
	 * @return Districts as model object
	 */
	public Districts get(long id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method is called " + id);
			}
			return (Districts) getHibernateTemplate().get(Districts.class, new Long(id));
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}
	
	public RegionMstr getRegion(int id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method is called " + id);
			}
			
			return (RegionMstr) getHibernateTemplate().get(RegionMstr.class, new Integer(id));
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method returns count of the Districts objects available based on search criteria.
	 * 
	 * @return long
	 * @exception Exception
	 */
	public long getDistrictsCount(DistrictsSearch searchOptions) {
		try {

			String sqlString = "select count(*) from Districts districts";
			String fromClause = getSearchQuery(searchOptions);

			sqlString = StringUtils.isNotEmpty(fromClause) ? sqlString + " where " + fromClause : sqlString;
			Query query = getSession().createQuery(sqlString);
			List list = query.list();
			if (list != null && list.size() > 0)
				return Long.parseLong(list.get(0).toString());
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * This method returns Districts object for a given Id
	 * 
	 * @param districtName
	 *        as String
	 * @return Districts as model object
	 */
	public Districts getByDistrictName(String districtName) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method with districts name is called " + districtName);
			}
			String strQuery = "select districts from Districts districts where upper(districts.districtName)=upper('" + districtName + "')";
			Query query = getSession().createQuery(strQuery);
			List<Districts> districtsList = query.list();
			if (districtsList != null && districtsList.size() > 0)
				return districtsList.get(0);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method save/updates the Districts information to the database.
	 * 
	 * @param Districts
	 *        object
	 */
	public void save(Districts entity) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("save Method is called ");
			}
			Districts districts = null;
			if (entity.getDistrictId() > 0) {
				districts = get(entity.getDistrictId());
				getSession().evict(districts);
				getSession().flush();
			}
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}

	/**
	 * This method to merge the Districts information to the database.
	 * 
	 * @param Districts
	 *        object
	 */
	public Districts merge(Districts entity) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("merge Method is called ");
			}
			return (Districts) getHibernateTemplate().merge(entity);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method is used to delete a record from the database using hibernate.
	 * 
	 * @param id
	 *        as long
	 */
	public void delete(long id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("deleting Districts instance");
			}
			Districts entity = (Districts) getHibernateTemplate().load(Districts.class, new Long(id));
			getHibernateTemplate().delete(entity);
			log.debug("delete successful");
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}

	/**
	 * This method will return a list of all Districts from the database
	 * 
	 * @return List of Districts
	 */
	public List<Districts> getAll() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get all Method is called for Districts");
			}
			return getHibernateTemplate().loadAll(Districts.class);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}
	
	public List<Districts> getAllOrderByName() {
		 Session  session=null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("getAllOrderByName Method is called");
			}
			/*** Kashireddy ****/  
			    session=sessionFactory.getCurrentSession();
			/*Criteria c = session.createCriteria(Districts.class);
			c.addOrder(Order.asc("districtName"));*/
			 Query query = session.createQuery("From Districts where c_delflag='N'    ");
			  
			  List<Districts> queryResult = (List<Districts>) query.list();
			 // System.out.println("result "+queryResult.size());
			   
			return queryResult;
			 
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			session.close();
			exception.printStackTrace(); 
		}
		return null;
	}
	/**
	 * This method is used to delete a list of Module records from the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void deleteAll(List<Districts> listDistricts) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("deleteAll Method is called for Districts");
			}
			getHibernateTemplate().deleteAll(listDistricts);
			log.debug("deleted successfuly a list of Districts records from database");
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}

	/**
	 * This method save/updates a list of Districts information to the database.
	 * 
	 * @param listDistricts
	 *        as List
	 */
	public void saveAll(List<Districts> listDistricts) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("saveAll Method is called ");
			}
			getHibernateTemplate().saveOrUpdateAll(listDistricts);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}

	

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
	public List<Districts> findByProperty(String propertyName, Object value) {
		try {
			log.debug("finding Districts instance with property: " + propertyName + ", value: " + value);
			String queryString = "select districts from Districts districts where districts." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method returns Districts object for a given data
	 * 
	 * @param Districts
	 *        as districts
	 * @return Districts as model object
	 */
	public Districts checkForDuplicates(Districts districts) {
		if (log.isDebugEnabled()) {
			log.debug("checkForDuplicates Method is called");
		}
		try {
			String fromClause = "select districts from Districts districts where ";

			if (districts.getDistrictId() > 0)
				fromClause = fromClause + " districtId <> " + districts.getDistrictId() + " and ";

			// Sample Code update accordingly
			//fromClause = fromClause +" districts.districtId = "+districts.getDistrictId()+" and ";
			fromClause = fromClause +" (upper(districtName) = upper('"+ CommonUtils.getEscapedSQLString(districts.getDistrictName()) +"')) " ;

			Query query = getSession().createQuery(fromClause);
			List list = query.list();

			if (list != null && list.size() > 0)
				return (Districts) list.get(0);

		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	public List<Districts> getDistrictsList(DistrictsSearch searchOptions) {
		try {
			long pageNumber = searchOptions.getCurrentPage();
			//long displayPageSize = searchOptions.getDisplayPageSize();
			String orderBy = searchOptions.getOrderBy();
			String sortBy = searchOptions.getSortBy();
			// to sort by userGroup
		/*	if (StringUtils.isNotEmpty(orderBy) && orderBy.equalsIgnoreCase("userGroup.userGroupId"))
				orderBy = "";
			else
				orderBy = "," + orderBy;*/

			String sqlString = "select districts from Districts districts ";

			String fromClause = getSearchQuery(searchOptions);

			sqlString = StringUtils.isNotEmpty(fromClause) ? sqlString + " where " + fromClause : sqlString;

			fromClause = sqlString + " order by " + orderBy + " " + sortBy;

			Query query = getSession().createQuery(fromClause);

			if (pageNumber != Constants.DEFAULT_PAGINATION_ALL_ROWS) {
				if (pageNumber > 1) {
					query.setFirstResult((int) ((pageNumber - 1) * Constants.DEFAULT_ROWS_PER_PAGE));
					query.setMaxResults((int) Constants.DEFAULT_ROWS_PER_PAGE);
				} else {
					query.setFirstResult(0);
					query.setMaxResults((int) Constants.DEFAULT_ROWS_PER_PAGE);
				}
			}
			List<Districts> list = query.list();

			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	private String getSearchQuery(DistrictsSearch searchOptions) {
		String condition = " and ";
		String fromClause = "";
		boolean conditionFound = false;
		if (searchOptions != null) {

			long strSearchDistrictId = searchOptions.getDistrictId();
			String strSearchDistrictName = searchOptions.getDistrictName();
			
			int status = searchOptions.getStatus();
			
			
			if (strSearchDistrictId>0) {
				if (conditionFound)
					fromClause = fromClause + condition;

				fromClause = fromClause + " districts.districtId="+strSearchDistrictId+" ";
				conditionFound = true;
			}
			
			if(StringUtils.isNotEmpty(strSearchDistrictName)){
				if (conditionFound)
					fromClause = fromClause + condition;

				fromClause = fromClause + " lower(districts.districtName) like lower('%" + CommonUtils.getEscapedSQLString(strSearchDistrictName) + "%')";
				conditionFound = true;
			}
			
			if (status > -1) {
				if (conditionFound)
					fromClause = fromClause + condition;
				fromClause = fromClause +"status="+status;
				conditionFound = true;
			}
			
		}
		return fromClause;
	}

	
}
