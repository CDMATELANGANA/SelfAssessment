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
@Table(name = "permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer PERMISSION_ID;

	@Column
	Integer PERMISSION_GROUP_ID;
	@Column(unique = true)
	String PERMISSION_NAME;
	@Column
	String PERMISSION_URL;
	@Column
	String PERMISSION_DESCRIPTION;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date UPDATED_DATE;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	Date CREATED_DATE;

	public Integer getPERMISSION_ID() {
		return PERMISSION_ID;
	}

	public void setPERMISSION_ID(Integer pERMISSION_ID) {
		PERMISSION_ID = pERMISSION_ID;
	}

	public Integer getPERMISSION_GROUP_ID() {
		return PERMISSION_GROUP_ID;
	}

	public void setPERMISSION_GROUP_ID(Integer pERMISSION_GROUP_ID) {
		PERMISSION_GROUP_ID = pERMISSION_GROUP_ID;
	}

	public String getPERMISSION_NAME() {
		return PERMISSION_NAME;
	}

	public void setPERMISSION_NAME(String pERMISSION_NAME) {
		PERMISSION_NAME = pERMISSION_NAME;
	}

	public String getPERMISSION_URL() {
		return PERMISSION_URL;
	}

	public void setPERMISSION_URL(String pERMISSION_URL) {
		PERMISSION_URL = pERMISSION_URL;
	}

	public String getPERMISSION_DESCRIPTION() {
		return PERMISSION_DESCRIPTION;
	}

	public void setPERMISSION_DESCRIPTION(String pERMISSION_DESCRIPTION) {
		PERMISSION_DESCRIPTION = pERMISSION_DESCRIPTION;
	}

	public Date getUPDATED_DATE() {
		return UPDATED_DATE;
	}

	public void setUPDATED_DATE(Date uPDATED_DATE) {
		UPDATED_DATE = uPDATED_DATE;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

}
