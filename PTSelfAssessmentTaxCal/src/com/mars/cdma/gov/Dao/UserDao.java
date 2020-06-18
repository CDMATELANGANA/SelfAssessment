package com.mars.cdma.gov.Dao;

import java.util.List;
import java.util.Map;

import com.mars.cdma.gov.bean.User;

public interface UserDao {

	public int saveUser(User user);

	public List<Object> getUser();

	public List<User> getSelectedUser(int userId);

	public int updateUser(User user, int userId);
	
	////////
	public User getByUserName(String userName);
	public User get(long id);
	
	
}
