package com.mars.cdma.gov.service.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.PermissionDao;
import com.mars.cdma.gov.bean.Permission;
import com.mars.cdma.gov.service.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionDao permissionDao;

	@Override
	public int savePermission(Permission permission) {
		return permissionDao.savePermission(permission);
	}

	@Override
	public List<Object> getPermission() {
		return permissionDao.getPermission();
	}

	@Override
	public List<Permission> getSelectedPermission(int permissionId) {
		return permissionDao.getSelectedPermission(permissionId);
	}

	@Override
	public int updatePermission(Permission permission, int permissionId) {
		return permissionDao.updatePermission(permission, permissionId);
	}

	// ////////////////
	public Hashtable<String, String> getRolePermissions() {
		return permissionDao.getRolePermissions();

	}

	public Hashtable<String, String> getPermissionsByUserGroupId(long userGroupId) {		
		return permissionDao.getPermissionsByUserGroupId(userGroupId);
	}

}
