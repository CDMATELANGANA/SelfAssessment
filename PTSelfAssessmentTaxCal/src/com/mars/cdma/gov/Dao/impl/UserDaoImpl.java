package com.mars.cdma.gov.Dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.UserDao;
import com.mars.cdma.gov.bean.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int saveUser(User user) {
		int uid;
		try {
			uid = (Integer) sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			System.out.println(e);
			uid = 0;
		}
		return uid;
	}

	@Override
	public List<Object> getUser() {
		String sql = "SELECT u.USER_ID,u.USER_NAME, u.PASSWORD, u.USER_GROUP_ID,ug.USER_GROUP_NAME, u.FIRST_NAME, "
				+ " u.MIDDLE_NAME, u.LAST_NAME, u.PHONE_NO, "
				+ "(CASE WHEN u.STATUS = '1' THEN 'Active' ELSE 'Disable' END) As STATUS,"
				+ " u.CREATED_DATE, u.UPDATED_DATE "
				+ " FROM user u Inner Join user_group ug ON u.USER_GROUP_ID=ug.USER_GROUP_ID";
		Query qry = sessionFactory.getCurrentSession().createSQLQuery(sql);

		List<Object> user = qry.list();
		return user;
	}

	@Override
	public List<User> getSelectedUser(int userId) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("USER_ID", userId));

		return crit.list();
	}

	@Override
	public int updateUser(User user, int userId) {
		int uid = 0;
		try {
			Session session = sessionFactory.openSession();
			User user_data = (User) session.load(User.class, userId);

			Transaction tx = session.beginTransaction();

			user_data.setFIRST_NAME(user.getFIRST_NAME());
			user_data.setMIDDLE_NAME(user.getMIDDLE_NAME());
			user_data.setLAST_NAME(user.getLAST_NAME());
			user_data.setSTATUS(user.getSTATUS());
			user_data.setPASSWORD(user.getPASSWORD());
			user_data.setPHONE_NO(user.getPHONE_NO());
			user_data.setUSER_GROUP_ID(user.getUSER_GROUP_ID());
			user_data.setUPDATED_DATE(user.getUPDATED_DATE());

			session.update(user_data);
			tx.commit();
			uid = 1;
		} catch (Exception e) {
			uid = 0;
		}
		return uid;
	}
	@Override
	public User getByUserName(String userName) {
		try {
			Session session = sessionFactory.openSession();
			String strQuery = "select user from User user where upper(user.userName)=upper('" + userName + "')";
			Query query = session.createQuery(strQuery);
			List<User> userList = query.list();
			if (userList != null && userList.size() > 0)
				return userList.get(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	public User get(long id) {
		try {
			Session session = sessionFactory.openSession();
			return (User) session.get(User.class, new Long(id));
		} catch (Exception exception) {		
			exception.printStackTrace();
		}
		return null;
	}
	
}
