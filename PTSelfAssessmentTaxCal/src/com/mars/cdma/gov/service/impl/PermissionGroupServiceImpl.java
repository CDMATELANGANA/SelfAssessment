package com.mars.cdma.gov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mars.cdma.gov.Dao.PermissionGroupDao;
import com.mars.cdma.gov.bean.PermissionGroup;
import com.mars.cdma.gov.service.PermissionGroupService;

@Service("permissionGroupService")
@Transactional
public class PermissionGroupServiceImpl implements PermissionGroupService {

	@Autowired
	PermissionGroupDao permissionGroupDao;

	@Override
	public int savePermissionGroup(PermissionGroup pg) {
		return permissionGroupDao.savePermissionGroup(pg);
	}

	@Override
	public List<Object> getPermissionGroup() {
		return permissionGroupDao.getPermissionGroup();
	}

	@Override
	public List<PermissionGroup> getSelectedPermissionGroup(int pergroup_id) {
		return permissionGroupDao.getSelectedPermissionGroup(pergroup_id);
	}

	@Override
	public int updatePermissionGroup(PermissionGroup pg, int pergroup_id) {
		return permissionGroupDao.updatePermissionGroup(pg, pergroup_id);
	}

	@Override
	public List getPermissionGroupName() {
		return permissionGroupDao.getPermissionGroupName();
	}

}
