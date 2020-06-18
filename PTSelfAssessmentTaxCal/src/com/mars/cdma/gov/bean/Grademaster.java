package com.mars.cdma.gov.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="grade_mstr_tbl")
public class Grademaster implements Serializable {
	private static final long serialVersionUID = -3165939043372343326L;
	@Id
	@Column(name="grade_code")
	private int gradeCode;
	@Column(name="grade_name")
	private String gradeName;
	@Column(name="grade_flag")
	private char gradeFlag;
	@Column(name="updated_time")
	@Temporal(TemporalType.DATE)
	private Date updated_time;
	
	public int getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public char getGradeFlag() {
		return gradeFlag;
	}
	public void setGradeFlag(char gradeFlag) {
		this.gradeFlag = gradeFlag;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
	
	

}
