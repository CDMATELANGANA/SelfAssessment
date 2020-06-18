package com.mars.cdma.gov.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "user_group")
public class UserGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_GROUP_ID")
	Integer USER_GROUP_ID;
	@Column
	String USER_GROUP_NAME;
	@Column
	String DESCRIPTION;
	@Column
	Integer STATUS;
	@Temporal(TemporalType.TIMESTAMP)
	Date CREATED_DATE;
	@Temporal(TemporalType.TIMESTAMP)
	Date UPDATED_DATE;

	// @Transient
	// PermissionUserGroup perUserGroup;

	/*
	 * public PermissionUserGroup getPerUserGroup() { return perUserGroup; }
	 * 
	 * public void setPerUserGroup(PermissionUserGroup perUserGroup) {
	 * this.perUserGroup = perUserGroup; }
	 */

	@OneToMany(targetEntity = PermissionUserGroup.class, cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "USER_GROUP_ID",referencedColumnName="USER_GROUP_ID")	
	Set<PermissionUserGroup> permisionUserGroup;

	@Transient
	List<Integer> permissionsChk;

	public List<Integer> getPermissionsChk() {
		return permissionsChk;
	}

	public void setPermissionsChk(List<Integer> permissionsChk) {
		this.permissionsChk = permissionsChk;
	}

	public Set<PermissionUserGroup> getPermisionUserGroup() {
		return permisionUserGroup;
	}

	public void setPermisionUserGroup(
			Set<PermissionUserGroup> permisionUserGroup) {
		this.permisionUserGroup = permisionUserGroup;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public Integer getUSER_GROUP_ID() {
		return USER_GROUP_ID;
	}

	public void setUSER_GROUP_ID(Integer uSER_GROUP_ID) {
		USER_GROUP_ID = uSER_GROUP_ID;
	}

	public String getUSER_GROUP_NAME() {
		return USER_GROUP_NAME;
	}

	public void setUSER_GROUP_NAME(String uSER_GROUP_NAME) {
		USER_GROUP_NAME = uSER_GROUP_NAME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Date getUPDATED_DATE() {
		return UPDATED_DATE;
	}

	public void setUPDATED_DATE(Date uPDATED_DATE) {
		UPDATED_DATE = uPDATED_DATE;
	}

}
