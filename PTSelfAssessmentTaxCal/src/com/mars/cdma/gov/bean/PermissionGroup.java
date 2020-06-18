package com.mars.cdma.gov.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="permission_group")
public class PermissionGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer PERMISSION_GROUP_ID;

	@Column(unique = true)
	String PERMISSION_GROUP_NAME;
	@Column
	Integer STATUS;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date CREATED_DATE;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date UPDATED_DATE;

	public Integer getPERMISSION_GROUP_ID() {
		return PERMISSION_GROUP_ID;
	}

	public void setPERMISSION_GROUP_ID(Integer pERMISSION_GROUP_ID) {
		PERMISSION_GROUP_ID = pERMISSION_GROUP_ID;
	}

	public String getPERMISSION_GROUP_NAME() {
		return PERMISSION_GROUP_NAME;
	}

	public void setPERMISSION_GROUP_NAME(String pERMISSION_GROUP_NAME) {
		PERMISSION_GROUP_NAME = pERMISSION_GROUP_NAME;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
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
