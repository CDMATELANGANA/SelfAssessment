package com.mars.cdma.gov.Dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.PermissionUserGroupDao;
import com.mars.cdma.gov.Dao.UserGroupDao;
import com.mars.cdma.gov.bean.PermissionUserGroup;
import com.mars.cdma.gov.bean.UserGroup;

@Repository("userGroupDao")
public class UserGroupDaoImpl implements UserGroupDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int saveUserGroup(UserGroup userGroup) {

		int uid;
		try {
			uid = (Integer) sessionFactory.getCurrentSession().save(userGroup);
		} catch (Exception e) {
			System.out.println(e);
			uid = 0;
		}
		return uid;

	}

	@Override
	public int saveUserGroup(UserGroup userGroup, List<Integer> allCheckedList) {
		int id = 0;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			id = (Integer) session.save(userGroup);
			Set<PermissionUserGroup> permissionUserGroupList = new HashSet<PermissionUserGroup>();
			Date date = new Date();
			Iterator itr = allCheckedList.iterator();
			while (itr.hasNext()) {

				PermissionUserGroup pug = new PermissionUserGroup();
				pug.setCREATED_DATE(new Timestamp(date.getTime()));
				pug.setUPDATED_DATE(new Timestamp(date.getTime()));
				pug.setPERMISSION_ID(Integer.parseInt(itr.next().toString()));
				pug.setUSER_GROUP_ID(id);
				permissionUserGroupList.add(pug);
			}
			userGroup.setPermisionUserGroup(permissionUserGroupList);

			session.update(userGroup);
			tx.commit();
			id = 1;
		} catch (Exception e) {
			id = 0;
			// System.out.print(e);
		}

		return id;
	}

	@Override
	public List<Object> getUserGroup() {

		String hql = "Select u.USER_GROUP_ID,u.USER_GROUP_NAME,u.DESCRIPTION,"
				+ " (CASE WHEN u.STATUS = '1' THEN 'Active' ELSE 'Disable' END) As STATUS,"
				+ " u.CREATED_DATE,u.UPDATED_DATE from user_group u";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(hql);
		//qry.setFirstResult(1);
		//qry.setMaxResults(5);

		// crit.
		// from UserGroup
		List<Object> userGroup = qry.list();
		return userGroup;
	}

	@Override
	public List<UserGroup> getSelectedUserGroup(int userGroupId) {
		// TODO Auto-generated method stub

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(UserGroup.class)
				.add(Restrictions.eq("USER_GROUP_ID", userGroupId));

		// String hql =
		// "Select u.USER_GROUP_ID,u.USER_GROUP_NAME,u.DESCRIPTION,"
		// + " u.STATUS from user_group u  Where u.USER_GROUP_ID=:user_grp_id";
		// Query qry = sessionFactory.getCurrentSession().createSQLQuery(hql);
		// qry.setParameter("user_grp_id", userGroupId);

		List<UserGroup> userGroup = crit.list();

		return userGroup;

	}

	@Override
	public int updateUserGroup(UserGroup userGroup, int userGroupId) {
		int uid = 0;
		try {
			Session session = sessionFactory.openSession();
			UserGroup user_group = (UserGroup) session.load(UserGroup.class,
					userGroupId);

			Transaction tx = session.beginTransaction();
			user_group.setUSER_GROUP_NAME(userGroup.getUSER_GROUP_NAME());
			user_group.setDESCRIPTION(userGroup.getDESCRIPTION());
			user_group.setUPDATED_DATE(userGroup.getUPDATED_DATE());
			user_group.setSTATUS(userGroup.getSTATUS());
			session.update(user_group);
			tx.commit();
			uid = 1;
		} catch (Exception e) {
			uid = 0;
		}
		return uid;
	}

	@Override
	public int updateUserGroup(UserGroup userGroup,
			List<Integer> allCheckedList, int userGroupId) {
		int uid = 0;
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query qry = session
					.createSQLQuery("Delete from permission_usergroup Where USER_GROUP_ID=:ug_id");
			qry.setParameter("ug_id", userGroupId);
			qry.executeUpdate();

			UserGroup user_group = (UserGroup) session.load(UserGroup.class,
					userGroupId);

			user_group.setUSER_GROUP_NAME(userGroup.getUSER_GROUP_NAME());
			user_group.setDESCRIPTION(userGroup.getDESCRIPTION());
			user_group.setUPDATED_DATE(userGroup.getUPDATED_DATE());
			user_group.setSTATUS(userGroup.getSTATUS());

			Set<PermissionUserGroup> permissionUserGroupList = new HashSet<PermissionUserGroup>();

			Date date = new Date();
			Iterator itr = allCheckedList.iterator();
			while (itr.hasNext()) {
				// System.out.println(userGroupId);
				PermissionUserGroup pug = new PermissionUserGroup();
				pug.setCREATED_DATE(new Timestamp(date.getTime()));
				pug.setUPDATED_DATE(new Timestamp(date.getTime()));
				pug.setPERMISSION_ID(Integer.parseInt(itr.next().toString()));
				pug.setUSER_GROUP_ID(userGroupId);
				permissionUserGroupList.add(pug);
			}
			user_group.setPermisionUserGroup(permissionUserGroupList);

			session.update(user_group);
			tx.commit();
			uid = 1;
		} catch (Exception e) {
			uid = 0;
		}
		return uid;
	}

	@Override
	public List getUserGroupName() {

		String hql = "Select u.USER_GROUP_ID,u.USER_GROUP_NAME from user_group u";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(hql);

		List<Object> userGroup = qry.list();
		return userGroup;
	}

}
