package com.mars.cdma.gov.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.poi.hssf.record.formula.functions.False;

@Entity
@Table(name = "permission_usergroup")
public class PermissionUserGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	Integer PERMISSION_USERGROUP_ID;

	@Column(name="USER_GROUP_ID",nullable=false)
	Integer USER_GROUP_IDs;
	@Column
	Integer PERMISSION_ID;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date CREATED_DATE;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date UPDATED_DATE;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_GROUP_ID",referencedColumnName="USER_GROUP_ID", nullable = false)
	private UserGroup userGroup;

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
*/
	public Integer getPERMISSION_USERGROUP_ID() {
		return PERMISSION_USERGROUP_ID;
	}

	public void setPERMISSION_USERGROUP_ID(Integer pERMISSION_USERGROUP_ID) {
		PERMISSION_USERGROUP_ID = pERMISSION_USERGROUP_ID;
	}

	public Integer getUSER_GROUP_ID() {
		return USER_GROUP_IDs;
	}

	public void setUSER_GROUP_ID(Integer uSER_GROUP_ID) {
		USER_GROUP_IDs = uSER_GROUP_ID;
	}

	public Integer getPERMISSION_ID() {
		return PERMISSION_ID;
	}

	public void setPERMISSION_ID(Integer pERMISSION_ID) {
		PERMISSION_ID = pERMISSION_ID;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public Date getUPDATED_DATE() {
		return UPDATED_DATE;
	}

	public void setUPDATED_DATE(Date uPDATED_DATE) {
		UPDATED_DATE = uPDATED_DATE;
	}

}
