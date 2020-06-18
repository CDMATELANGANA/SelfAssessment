package com.mars.cdma.gov.service;

import java.util.List;

import com.mars.cdma.gov.bean.PermissionGroup;

public interface PermissionGroupService {
	public int savePermissionGroup(PermissionGroup pg);

	public List<Object> getPermissionGroup();

	public List<PermissionGroup> getSelectedPermissionGroup(int pergroup_id);

	public int updatePermissionGroup(PermissionGroup pg, int pergroup_id);

	public List getPermissionGroupName();
}
