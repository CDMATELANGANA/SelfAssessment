package com.mars.cdma.gov.service;

import java.util.List;
import java.util.Map;

import com.mars.cdma.gov.bean.User;

public interface UserService {
	public int saveUser(User user);

	public List<Object> getUser();

	public List<User> getSelectedUser(int userId);

	public int updateUser(User user, int userId);
	///////////
	public User getByUserName(String userName);
	public User get(long id);
}
