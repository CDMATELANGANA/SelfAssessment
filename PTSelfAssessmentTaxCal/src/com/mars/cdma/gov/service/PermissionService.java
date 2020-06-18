package com.mars.cdma.gov.service;

import java.util.Hashtable;
import java.util.List;

import com.mars.cdma.gov.bean.Permission;

public interface PermissionService {
	public int savePermission(Permission permission);

	public List<Object> getPermission();

	public List<Permission> getSelectedPermission(int permissionId);

	public int updatePermission(Permission permission, int permissionId);
	///////
	public Hashtable<String, String> getRolePermissions();
	public Hashtable<String, String> getPermissionsByUserGroupId(long userGroupId);
}
