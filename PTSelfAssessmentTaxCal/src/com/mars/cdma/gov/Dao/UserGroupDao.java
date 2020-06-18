package com.mars.cdma.gov.Dao;

import java.util.List;
import java.util.Set;

import com.mars.cdma.gov.bean.PermissionUserGroup;
import com.mars.cdma.gov.bean.UserGroup;

public interface UserGroupDao {

	public int saveUserGroup(UserGroup userGroup);
	public int saveUserGroup(UserGroup userGroup,List<Integer> allCheckedList);
	
	public List<Object> getUserGroup();
	
	public List<UserGroup> getSelectedUserGroup(int userGroupId);	
	
	public int updateUserGroup(UserGroup userGroup,int userGroupId);
	public int updateUserGroup(UserGroup userGroup,List<Integer> allCheckedList,int userGroupId);
	
	public List getUserGroupName();
}
