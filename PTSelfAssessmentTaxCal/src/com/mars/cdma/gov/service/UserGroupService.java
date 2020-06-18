package com.mars.cdma.gov.service;

import java.util.List;
import java.util.Set;

import com.mars.cdma.gov.bean.PermissionUserGroup;
import com.mars.cdma.gov.bean.UserGroup;

public interface UserGroupService {
	public int saveUserGroup(UserGroup userGroup);

	public List<Object> getUserGroup();

	public int saveUserGroup(UserGroup userGroup, List<Integer> allCheckedList);

	// public List<UserGroup> getSelectedUserGroup(int userGroupId);
	public List<UserGroup> getSelectedUserGroup(int userGroupId);

	
	public int updateUserGroup(UserGroup userGroup,int userGroupId);
	public int updateUserGroup(UserGroup userGroup,
			List<Integer> allCheckedList, int userGroupId);

	public List getUserGroupName();
}
