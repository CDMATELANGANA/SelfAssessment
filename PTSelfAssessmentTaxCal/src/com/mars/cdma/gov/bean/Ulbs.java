package com.mars.cdma.gov.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ulbs")
public class Ulbs implements Serializable{
	//ULB_ID, ULB_CODE, ULB_NAME, DISTRICT_ID, GRADE_CODE, ULB_STATUS, Collection_Type, WATER_STATUS, VACANTLAND_STATUS, D_O_TRADE_STATUS, SRO_CODE, ULB_SRO_CODE, SRO_CODE_STATUS, GisModificationDate
	private static final long serialVersionUID = 1891599726284700590L;
	@Id
	@Column(name="ULB_ID")
	private long ulbId;
	@Column(name="ULB_CODE")
	private int ulbCode;
	
	@Column(name="ULB_NAME")
	private String ulbName;
	@Column(name="ULB_STATUS")
	private String ulbStatus;
	
	@Column(name="GRADE_CODE")
	private int  grade_code;
	
	@Column(name="DISTRICT_ID")
	private int  district_id ;
	
	@Column(name="c_delflag")
	private Character c_delflag;
	
	private Districts districts=new Districts();
 
	private Grademaster gradeMstr=null;
	
    public long getUlbId() {
		return ulbId;
	}
	public void setUlbId(long ulbId) {
		this.ulbId = ulbId;
	}
	public int getUlbCode() {
		return ulbCode;
	}
	public void setUlbCode(int ulbCode) {
		this.ulbCode = ulbCode;
	}
	public String getUlbName() {
		return ulbName;
	}
	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}
	public Districts getDistricts() {
		return districts;
	}
	public void setDistricts(Districts districts) {
		this.districts = districts;
	}
	public Grademaster getGradeMstr() {
		return gradeMstr;
	}
	public void setGradeMstr(Grademaster gradeMstr) {
		this.gradeMstr = gradeMstr;
	}
	public String getUlbStatus() {
		return ulbStatus;
	}
	public void setUlbStatus(String ulbStatus) {
		this.ulbStatus = ulbStatus;
	}
	public int getGrade_code() {
		return grade_code;
	}
	public void setGrade_code(int grade_code) {
		this.grade_code = grade_code;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public Character getC_delflag() {
		return c_delflag;
	}
	public void setC_delflag(Character c_delflag) {
		this.c_delflag = c_delflag;
	}
	 
    
    
}
