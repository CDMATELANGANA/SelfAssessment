package com.mars.cdma.gov.Dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.NullableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.UlbsDAO;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.Grademaster;
import com.mars.cdma.gov.bean.NewAssesment;
import com.mars.cdma.gov.bean.Taxcalservicebean;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.search.UlbSearch;
import com.mars.cdma.gov.utils.CommonUtils;
import com.mars.cdma.gov.utils.Constants;

 

@Repository("ulbsDAO")
public class UlbDAOimpl extends HibernateDaoSupport implements UlbsDAO{
	
	private static final Logger log= Logger.getLogger(UlbDAOimpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}


	/**
	 * This method returns Ulbs object for a given Id
	 * 
	 * @param id
	 *        as long
	 * @return Ulbs as model object
	 */
	public Ulbs get(long id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method is called " + id);
			}
			return (Ulbs) getHibernateTemplate().get(Ulbs.class, new Long(id));
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	public Grademaster getGrade(int id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method is called " + id);
			}
			
			return (Grademaster) getHibernateTemplate().get(Grademaster.class, new Integer(id));
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}
	/**
	 * This method returns Ulbs object for a given Id
	 * 
	 * @param userName
	 *        as String
	 * @return Ulbs as model object
	 */
	public Ulbs getByUlbsName(String ulbName) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get Method with ulbs name is called " + ulbName);
			}
			String strQuery = "select ulbs from Ulbs ulbs where upper(ulbs.ulbName)=upper('" + ulbName + "')";
			Query query = getSession().createQuery(strQuery);
			List<Ulbs> ulbsList = query.list();
			if (ulbsList != null && ulbsList.size() > 0)
				return ulbsList.get(0);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	public Ulbs getulbnameByUlbsCode(int ulbcode) {
		 Ulbs getulbname = null;
		try {
		 
			/*String strQuery = "select ulbs from Ulbs ulbs where ulbs.ulbCode=" + ulbcode + "";
			Query query = getSession().createQuery(strQuery);
			List<Ulbs> ulbsList = query.list();
			if (ulbsList != null && ulbsList.size() > 0)
				return ulbsList.get(0);
			*/
			//System.out.println("ulbcode "+ulbcode);
			 /*Session  session=sessionFactory.getCurrentSession();*/
			 Session  session=sessionFactory.openSession();
			 session.beginTransaction();
			 Criteria criteria=session.createCriteria(Ulbs.class);
			 criteria.add(Restrictions.eq("ulbCode", ulbcode));
			 
			 getulbname=(Ulbs)criteria.uniqueResult();  
		} catch (Exception exception) {
			 
			exception.printStackTrace();
		}
		return getulbname;
	}
	
	/**
	 * This method save/updates the Ulbs information to the database.
	 * 
	 * @param Ulbs
	 *        object
	 */
	public void save(Ulbs entity) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("save Method is called ");
			}
			Ulbs ulbs = null;
			if (entity.getUlbId() > 0) {
				ulbs = get(entity.getUlbId());
				getSession().evict(ulbs);
				getSession().flush();
			}
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}
	/*public void save(Ulbs entity) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("save Method is called ");
			}
			Ulbs ulbs=null;
			if (entity.getUlbId() > 0) {
				ulbs = get(entity.getUlbId());
				getSession().evict(ulbs);
				getSession().flush();
			}
			getHibernateTemplate().saveOrUpdate(entity);
		}
		catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}*/
	/**
	 * This method to merge the Ulbs information to the database.
	 * 
	 * @param Ulbs
	 *        object
	 */
	public Ulbs merge(Ulbs entity) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("merge Method is called ");
			}
			/*Ulbs ulbs=null;
			if (entity.getUlbId() > 0) {
				ulbs = get(entity.getUlbId());
				getSession().evict(ulbs);
				getSession().flush();
			}*/
			return (Ulbs) getHibernateTemplate().merge(entity);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method delete Ulbs for a given Id
	 * 
	 * @param id
	 *        as long
	 */
	public void delete(long id) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("deleting Ulbs instance");
			}
			Ulbs entity = (Ulbs) getHibernateTemplate().load(Ulbs.class, new Long(id));
			getHibernateTemplate().delete(entity);
			log.debug("delete successful");
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}

	/**
	 * This method will return a list of all Ulbs from the database
	 * 
	 * @return List of Ulbs
	 */
	public List<Ulbs> getAll() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get all Method is called for Ulbs");
			}
						
			return getHibernateTemplate().loadAll(Ulbs.class);
			
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace(); 
		}
		return null;
	}
	
	public List<Ulbs> getAllOrderByName() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("get all Method is called for Ulbs");
			}
			
			Criteria c = getSession().createCriteria(Ulbs.class);
			c.addOrder(Order.asc("ulbName"));
			return c.list();
			
			//return getHibernateTemplate().loadAll(Ulbs.class);
			
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace(); 
		}
		return null;
	}

	/**
	 * This method save/updates a list of Ulbs information to the database.
	 * 
	 * @param listUlbs
	 *        as List
	 */
	public void saveAll(List<Ulbs> listUlbs) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("saveAll Method is called ");
			}
			getHibernateTemplate().saveOrUpdateAll(listUlbs);
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		
	}

	/**
	 * This method is used to delete a list of Ulbs records from the database.
	 * 
	 * @param listUlbs
	 *        as List
	 */
	public void deleteAll(List<Ulbs> listUlbs) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("deleteAll Method is called for Ulbs");
			}
			getHibernateTemplate().deleteAll(listUlbs);
			log.debug("deleted successfuly a list of Ulbs records from database");
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
	}


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
	public List<Ulbs> findByProperty(String propertyName, Object value) {
		try {
			Session  session=sessionFactory.getCurrentSession();
			
			
			  
	 		/*log.debug("finding Ulbs instance with property: " + propertyName + ", value: " + value);
			System.out.println(">>> get ulbcodes  "+propertyName+" valure "+value);
			String queryString = "select ulbs from Ulbs ulbs where ulbs." + propertyName + "= ?";			
			//String queryString = "select * from ulbs   where  ULB_ID=?";
			queryString = queryString + " order by  ULB_NAME asc";
			Query queryObject = session.createQuery("from Ulbs  where  DISTRICT_ID=?  order by  ULB_NAME asc");
			
			queryObject.setParameter(0, value);
			System.out.println("size "+queryObject.toString()); 
			*/
			  Query query = session.createQuery("From Ulbs  where  DISTRICT_ID=? and c_delflag='N' order by  ULB_NAME asc  ");
			 query.setParameter(0, value);
			    List<Ulbs> queryResult = (List<Ulbs>) query.list();
			 //List<Ulbs> queryResult=new ArrayList<Ulbs>(); 
			 
			 return queryResult;
			
			
			
				/*Criteria c = session.createCriteria(Districts.class);
				c.addOrder(Order.asc("districtName"));*/
				 //Query query = session.createQuery("From Districts  ");
				  
				 
			
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<Ulbs> findByDistrict(String propertyName, Districts districts) {
		try {
			log.debug("finding Ulbs instance with property: " + propertyName + ", value: " + districts.getDistrictId());
			String queryString = "select ulbs from Ulbs ulbs where " + propertyName + "= ?";			
			queryString = queryString + " order by ulbs.ulbName asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, districts.getDistrictId());
			return queryObject.list();
			
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		
		return null;
	}

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
	public List<Ulbs> getListByNamedQuery(String namedQuery, Hashtable<String, Object> hstKeyValues) {
		try {
			Query queryObject = getSession().getNamedQuery(namedQuery);
			if (hstKeyValues != null && hstKeyValues.size() > 0) {
				Enumeration<String> enumeration = hstKeyValues.keys();
				while (enumeration.hasMoreElements()) {
					String strKey = enumeration.nextElement();
					if (hstKeyValues.get(strKey) instanceof Collection) {
						queryObject.setParameterList(strKey, (Collection) hstKeyValues.get(strKey));
					} else {
						queryObject.setParameter(strKey, hstKeyValues.get(strKey));
					}
				}
			}
			return queryObject.list();
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method returns list of Ulbs objects available based on the query names passed.
	 * 
	 * @param String
	 *        hqlQuery
	 * @return List
	 * @exception Exception
	 */
	public List<Ulbs> getListByHqlQuery(String hqlQuery) {
		try {
			Query query = getSession().createQuery(hqlQuery);
			List list = query.list();
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

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
	public List<Ulbs> getEntityListBySQLQuery(String strSql, Hashtable<String, NullableType> hstDataTypes) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("getEntitytListBySQLQuery Method is called ");
			}
			SQLQuery query = getSession().createSQLQuery(strSql);
			if (hstDataTypes != null && hstDataTypes.size() > 0) {
				Enumeration<String> enumeration = hstDataTypes.keys();
				while (enumeration.hasMoreElements()) {
					String strKey = enumeration.nextElement();
					query.addScalar(strKey, (NullableType) hstDataTypes.get(strKey));
				}
			}
			query.addEntity(Ulbs.class);
			return query.list();
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * This method returns list selected columns as list based on the SQL query and parameters.
	 * 
	 * @param String
	 *        SQL Query
	 * @return List
	 * @exception Exception
	 */
	public List<ArrayList> getListBySQLQuery(String strSql, Hashtable<String, NullableType> hstDataTypes) {
		return null;
	}


	/**
	 * This method returns count of the Ulbs objects available based on search criteria.
	 * 
	 * @return long
	 * @exception Exception
	 */
	public long getUlbsCount(UlbSearch searchOptions) {
		try {

			String sqlString = "select count(*) from Ulbs ulbs";
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


	@Override
	public List<Ulbs> getUlbsList(UlbSearch searchOptions) {
		
		try {
			long pageNumber = searchOptions.getCurrentPage();
		
			String orderBy = searchOptions.getOrderBy();
			String sortBy = searchOptions.getSortBy();
			String sqlString = "select ulbs from Ulbs ulbs ";

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
			List<Ulbs> list = query.list();

			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}
	
	private String getSearchQuery(UlbSearch searchOptions) {
		String condition = " and ";
		String fromClause = "";
		boolean conditionFound = false;
		if (searchOptions != null) {

			long strSearchUlbId = searchOptions.getUlbId();
			String strSearchUlbName= searchOptions.getUlbName();

			
			int status = searchOptions.getStatus();
			
			
			if (strSearchUlbId>0) {
				if (conditionFound)
					fromClause = fromClause + condition;

				fromClause = fromClause + " ulbs.ulbId="+strSearchUlbId+" ";
				conditionFound = true;
			}
			
			if(StringUtils.isNotEmpty(strSearchUlbName)){
				if (conditionFound)
					fromClause = fromClause + condition;

				fromClause = fromClause + " lower(ulbs.ulbName) like lower('%" + CommonUtils.getEscapedSQLString(strSearchUlbName) + "%')";
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

	/**
	 * This method returns Ulbs object for a given data
	 * 
	 * @param Ulbs
	 *        as ulbs
	 * @return Ulbs as model object
	 */
	public Ulbs checkForDuplicates(Ulbs ulbs) {
		if (log.isDebugEnabled()) {
			log.debug("checkForDuplicates Method is called");
		}
		try {
			String fromClause = "select ulbs from Ulbs ulbs where ";

			if (ulbs.getUlbId()> 0)
				fromClause = fromClause + " ulbId <> " + ulbs.getUlbId() + " and ";

			// Sample Code update accordingly
			//fromClause = fromClause +" districts.districtId = "+districts.getDistrictId()+" and ";
			fromClause = fromClause +" (upper(ulbName) = upper('"+ CommonUtils.getEscapedSQLString(ulbs.getUlbName()) +"')) " ;

			Query query = getSession().createQuery(fromClause);
			List list = query.list();

			if (list != null && list.size() > 0)
				return (Ulbs) list.get(0);

		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Ulbs> findNewUlbs(String propertyName,String val,Integer districtId){
		try {
			log.debug("finding Ulbs instance with property: " + propertyName + ", value: " + val+ ", Distict ID " + districtId);
			/*String queryString = "select ulbs from Ulbs ulbs where " + propertyName + "= ?";			
			queryString = queryString + " order by ulbs.ulbName asc";*/
			
			String queryString = "select ulbs from Ulbs ulbs where " + propertyName + "= ? and DISTRICT_ID = ?";			
			queryString = queryString + " order by ulbs.ulbName asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, val);
			queryObject.setParameter(1, districtId);
			return queryObject.list();
			
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			exception.printStackTrace();
		}
		
		return null;
	}

/*shifted to AssessmentDAOImpl */
	/*@Override
	public void save(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		 sessionFactory.openSession().save(taxcalservicebean);
	}


	@Override
	public Ulbs getulbByCode(int ulbCode) {
		// TODO Auto-generated method stub
		Ulbs getulbname = null;
		try {
		 
			String strQuery = "select ulbs from Ulbs ulbs where ulbs.ulbCode=" + ulbcode + "";
			Query query = getSession().createQuery(strQuery);
			List<Ulbs> ulbsList = query.list();
			if (ulbsList != null && ulbsList.size() > 0)
				return ulbsList.get(0);
			
			//System.out.println("ulbcode "+ulbcode);
			 Session  session=sessionFactory.getCurrentSession();
			 session.beginTransaction();
			 Criteria criteria=session.createCriteria(Ulbs.class);
			 criteria.add(Restrictions.eq("ulbCode", ulbCode));
			 
			 getulbname=(Ulbs)criteria.uniqueResult();  
		} catch (Exception exception) {
			 
			exception.printStackTrace();
		}
		return getulbname;
	}


	@Override
	public boolean saveDet(Taxcalservicebean taxcalservicebean) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			System.out.println("stsus =====");
			
			
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				//session.save(taxcalservicebean);
				session.saveOrUpdate(taxcalservicebean);
				tx.commit();
				flag = true;
						
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}*/


	 

 


	
}
