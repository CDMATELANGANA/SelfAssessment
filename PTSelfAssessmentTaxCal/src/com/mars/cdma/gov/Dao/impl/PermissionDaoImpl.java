package com.mars.cdma.gov.Dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PermissionDao;
import com.mars.cdma.gov.bean.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int savePermission(Permission permission) {
		int uid;
		try {
			uid = (Integer) sessionFactory.getCurrentSession().save(permission);
		} catch (Exception e) {
			System.out.println(e);
			uid = 0;
		}
		return uid;
	}

	@Override
	public List<Object> getPermission() {
		String sql = "SELECT p.`PERMISSION_ID`, p.`PERMISSION_NAME`, p.`PERMISSION_URL`, p.`PERMISSION_DESCRIPTION`, "
				+ " p.`PERMISSION_GROUP_ID`,pg.`PERMISSION_GROUP_NAME`, p.`CREATED_DATE`, p.`UPDATED_DATE` "
				+ " FROM permission p Inner Join permission_group pg ON p.`PERMISSION_GROUP_ID`=pg.`PERMISSION_GROUP_ID`";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object> user = qry.list();
		return user;
	}

	@Override
	public List<Permission> getSelectedPermission(int permissionId) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Permission.class)
				.add(Restrictions.eq("PERMISSION_ID", permissionId));

		return crit.list();
	}

	@Override
	public int updatePermission(Permission permission, int permissionId) {
		int uid = 0;
		try {
			Session session = sessionFactory.openSession();
			Permission permission_data = (Permission) session.load(
					Permission.class, permissionId);

			Transaction tx = session.beginTransaction();

			permission_data.setPERMISSION_NAME(permission.getPERMISSION_NAME());
			permission_data.setPERMISSION_DESCRIPTION(permission
					.getPERMISSION_DESCRIPTION());
			permission_data.setUPDATED_DATE(permission.getUPDATED_DATE());
			permission_data.setPERMISSION_URL(permission.getPERMISSION_URL());
			permission_data.setPERMISSION_GROUP_ID(permission
					.getPERMISSION_GROUP_ID());

			session.update(permission_data);
			tx.commit();
			uid = 1;
		} catch (Exception e) {
			uid = 0;
		}
		return uid;
	}

	@Override
	public Hashtable<String, String> getRolePermissions() {
		Hashtable<String, String> hstPermissionRoles = null;
		try {
			hstPermissionRoles = new Hashtable<String, String>();
			String fromClause = "SELECT PERMISSION_URL, USER_GROUP.USER_GROUP_ID FROM PERMISSION_USERGROUP, USER_GROUP, PERMISSION"
					+ " WHERE USER_GROUP.USER_GROUP_ID = PERMISSION_USERGROUP.USER_GROUP_ID AND PERMISSION_USERGROUP.PERMISSION_ID = PERMISSION.PERMISSION_ID ORDER BY USER_GROUP_ID";

			Session session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(fromClause);
			query.addScalar("PERMISSION_URL", Hibernate.STRING);
			query.addScalar("USER_GROUP_ID", Hibernate.LONG);

			List<Object[]> list = query.list();
			for (Object[] obj : list) {
				String strURL = (String) obj[0];
				strURL = strURL.split("\\*")[0];
				long roleId = (Long) obj[1];
				if (hstPermissionRoles.containsKey(strURL)) {
					String strRoles = hstPermissionRoles.get(strURL).toString();
					strRoles = strRoles + ",ROLE_ID_" + roleId;
					hstPermissionRoles.put(strURL, strRoles);
				} else {
					String strRoles = "ROLE_ID_" + roleId;
					hstPermissionRoles.put(strURL, strRoles);
				}
			}
		} catch (NullPointerException nullpointerexception) {
			throw new NullPointerException();
		} catch (HibernateException hibernateException) {
			throw new HibernateException(hibernateException.getMessage());
		} catch (Exception exception) {
			throw new RuntimeException(exception.getCause());
		}
		return hstPermissionRoles;
	}

	@Override
	public Hashtable<String, String> getPermissionsByUserGroupId(
			long userGroupId) {
		Hashtable<String, String> hstPermissionRoles = null;
		try {
			hstPermissionRoles = new Hashtable<String, String>();

			String fromClause = "SELECT PERMISSION_URL FROM PERMISSION, PERMISSION_USERGROUP WHERE PERMISSION_USERGROUP.PERMISSION_ID = PERMISSION.PERMISSION_ID"
					+ " AND PERMISSION_USERGROUP.USER_GROUP_ID="
					+ userGroupId
					+ " ORDER BY PERMISSION_URL";
			Session session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(fromClause);
			query.addScalar("PERMISSION_URL", Hibernate.STRING);

			List<Object> list = query.list();
			for (Object object : list) {
				String strURL = object.toString();
				strURL = strURL.split("\\*")[0];
				if (hstPermissionRoles.containsKey(strURL)) {
					hstPermissionRoles.put(strURL, strURL);
				} else {
					hstPermissionRoles.put(strURL, strURL);
				}
			}
		} catch (NullPointerException nullpointerexception) {
			throw new NullPointerException();
		} catch (HibernateException hibernateException) {
			throw new HibernateException(hibernateException.getMessage());
		} catch (Exception exception) {
			throw new RuntimeException(exception.getCause());
		}
		return hstPermissionRoles;
	}

}
