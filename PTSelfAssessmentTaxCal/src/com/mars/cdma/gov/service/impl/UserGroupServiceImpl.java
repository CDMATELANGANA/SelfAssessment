package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.UserGroupDao;
import com.mars.cdma.gov.bean.UserGroup;
import com.mars.cdma.gov.service.UserGroupService;

@Service("userGroupService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	UserGroupDao userGroupDao;

	@Override
	public int saveUserGroup(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return userGroupDao.saveUserGroup(userGroup);
	}

	@Override
	public int saveUserGroup(UserGroup userGroup, List<Integer> allCheckedList) {
		return userGroupDao.saveUserGroup(userGroup, allCheckedList);
	}

	@Override
	public List<Object> getUserGroup() {
		return userGroupDao.getUserGroup();
	}

	@Override
	public List<UserGroup> getSelectedUserGroup(int userGroupId) {
		return userGroupDao.getSelectedUserGroup(userGroupId);
	}

	@Override
	public int updateUserGroup(UserGroup userGroup, int userGroupId) {
		return userGroupDao.updateUserGroup(userGroup, userGroupId);
	}

	@Override
	public int updateUserGroup(UserGroup userGroup,
			List<Integer> allCheckedList, int userGroupId) {
		return userGroupDao.updateUserGroup(userGroup, allCheckedList,
				userGroupId);
	}

	@Override
	public List getUserGroupName() {
		return userGroupDao.getUserGroupName();
	}

}
