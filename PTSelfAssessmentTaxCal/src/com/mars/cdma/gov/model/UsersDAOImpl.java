package com.mars.cdma.gov.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersDAOImpl implements UsersDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserModel getByUserName(String userName) {
		List<UserModel> users = new ArrayList<UserModel>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, userName).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
