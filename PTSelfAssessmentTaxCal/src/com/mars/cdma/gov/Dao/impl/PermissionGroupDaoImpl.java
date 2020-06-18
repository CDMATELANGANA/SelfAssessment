package com.mars.cdma.gov.Dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PermissionGroupDao;
import com.mars.cdma.gov.bean.PermissionGroup;
import com.mars.cdma.gov.bean.User;
import com.mars.cdma.gov.bean.UserGroup;

@Repository("permissionGroupDao")
public class PermissionGroupDaoImpl implements PermissionGroupDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int savePermissionGroup(PermissionGroup pg) {
		int uid;
		try {
			uid = (Integer) sessionFactory.getCurrentSession().save(pg);
		} catch (Exception e) {
			System.out.println(e);
			uid = 0;
		}
		return uid;
	}

	@Override
	public List<Object> getPermissionGroup() {
		String sql = "SELECT p.`PERMISSION_GROUP_ID`, p.`PERMISSION_GROUP_NAME`,"
				+ "	(CASE WHEN p.STATUS = '1' THEN 'Active' ELSE 'Disable' END) As STATUS,"
				+ " p.`CREATED_DATE`, p.`UPDATED_DATE` FROM permission_group p ";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(sql);

		List<Object> permissiongroup = qry.list();
		return permissiongroup;
	}

	@Override
	public List<PermissionGroup> getSelectedPermissionGroup(int pergroup_id) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(PermissionGroup.class)
				.add(Restrictions.eq("PERMISSION_GROUP_ID", pergroup_id));

		return crit.list();
	}

	@Override
	public int updatePermissionGroup(PermissionGroup pg, int pergroup_id) {
		int pid = 0;
		try {
			Session session = sessionFactory.openSession();
			PermissionGroup perm_group = (PermissionGroup) session.load(
					PermissionGroup.class, pergroup_id);

			Transaction tx = session.beginTransaction();
			perm_group.setPERMISSION_GROUP_NAME(pg.getPERMISSION_GROUP_NAME());
			perm_group.setUPDATED_DATE(pg.getUPDATED_DATE());
			perm_group.setSTATUS(pg.getSTATUS());

			session.update(perm_group);
			tx.commit();
			pid = 1;
		} catch (Exception e) {
			pid = 0;
		}
		return pid;
	}

	@Override
	public List getPermissionGroupName() {
		String hql = "SELECT p.`PERMISSION_GROUP_ID`, p.`PERMISSION_GROUP_NAME` from permission_group p";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(hql);

		List<Object> userGroup = qry.list();
		return userGroup;
	}

}
