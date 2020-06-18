package com.mars.cdma.gov.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class AssesmentBean implements Serializable {

	private String assessment_number;
	private String surname;
	private String name;
	private String fathersurname;
	private String fathername;
	private String doornumber;
	private String ulbname;
	
	
	public String getAssessment_number() {
		return assessment_number;
	}
	public void setAssessment_number(String assessment_number) {
		this.assessment_number = assessment_number;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathersurname() {
		return fathersurname;
	}
	public void setFathersurname(String fathersurname) {
		this.fathersurname = fathersurname;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getDoornumber() {
		return doornumber;
	}
	public void setDoornumber(String doornumber) {
		this.doornumber = doornumber;
	}
	public String getUlbname() {
		return ulbname;
	}
	public void setUlbname(String ulbname) {
		this.ulbname = ulbname;
	}


}
