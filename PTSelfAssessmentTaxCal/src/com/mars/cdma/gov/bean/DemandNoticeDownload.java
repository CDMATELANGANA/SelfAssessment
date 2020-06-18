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

/**
 * @author SARITA
 *
 */
@Entity
@Table(name="DemandNoticeDownload")
public class DemandNoticeDownload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "demnadNoticeId")
	private Integer demnadNoticeId;
	
	@Column(name = "assessment")
	private String assessment;
	
	@Column(name="requestDate")
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	@Column(name = "Mobile")
	private String mobile;

	@Column(name = "count_download")
	private int count_download;

	public Integer getDemnadNoticeId() {
		return demnadNoticeId;
	}

	public void setDemnadNoticeId(Integer demnadNoticeId) {
		this.demnadNoticeId = demnadNoticeId;
	}

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCount_download() {
		return count_download;
	}

	public void setCount_download(int count_download) {
		this.count_download = count_download;
	}


}
