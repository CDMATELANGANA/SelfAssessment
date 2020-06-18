package com.mars.cdma.gov.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.UserDao;
import com.mars.cdma.gov.bean.User;
import com.mars.cdma.gov.service.UserService;

@Service("userServive")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public List<Object> getUser() {
		return userDao.getUser();
	}

	@Override
	public List<User> getSelectedUser(int userId) {
		return userDao.getSelectedUser(userId);
	}

	@Override
	public int updateUser(User user, int userId) {
		return userDao.updateUser(user, userId);
	}

	// /
	@Override
	public User getByUserName(String userName) {

		return userDao.getByUserName(userName);
	}

	public User get(long id) {

		return userDao.get(id);
	}
}
