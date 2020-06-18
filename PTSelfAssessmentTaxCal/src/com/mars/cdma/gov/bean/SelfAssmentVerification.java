package com.mars.cdma.gov.bean;

import java.io.Serializable;
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
@Table(name="SelfAssment_Verification")
public class SelfAssmentVerification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6753549479414320455L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appNo")
	private long appNo;
	
	 
	@Column(name = "mobileNo")
	private String mobileNo;
	
	@Column(name = "OTPID")
	private String otpId;
	
	@Column(name = "uniqueRequestNo")
	private long uniqueRequestNo;
	
	@Column(name = "entrydate")
	@Temporal(TemporalType.DATE)
	private Date entrydate;

	@Column(name = "timestamp")
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	@Column(name = "delflag")
	private Character delflag;

	
	
	
	public SelfAssmentVerification() {
	}

	public long getAppNo() {
		return appNo;
	}

	public void setAppNo(long appNo) {
		this.appNo = appNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtpId() {
		return otpId;
	}

	public void setOtpId(String otpId) {
		this.otpId = otpId;
	}

	public long getUniqueRequestNo() {
		return uniqueRequestNo;
	}

	public void setUniqueRequestNo(long uniqueRequestNo) {
		this.uniqueRequestNo = uniqueRequestNo;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Character getDelflag() {
		return delflag;
	}

	public void setDelflag(Character delflag) {
		this.delflag = delflag;
	}
	
	
	
	
	
	

}
