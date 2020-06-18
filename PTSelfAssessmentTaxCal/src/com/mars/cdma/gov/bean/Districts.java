package com.mars.cdma.gov.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="districts")
public class Districts implements Serializable{
	 
	private static final long serialVersionUID = 5163830535595334897L;
	@Id
	@Column(name="DISTRICT_ID")
	private long districtId;
	@Column(name="DISTRICT_NAME")
	private String districtName;
	@Column(name="Category")
	private String category;
	@Column(name="DATABASE_NAME")
	private String databaseName;
	 @Column(name="REGN_CODE")
	private String regionMstr;// = new RegionMstr();
	 
	 
	 @Column(name="c_delflag")
		private Character c_delflag;
	 
	public long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/*public RegionMstr getRegionMstr() {
		return regionMstr;
	}
	public void setRegionMstr(RegionMstr regionMstr) {
		this.regionMstr = regionMstr;
	}*/
	
	
	public String getCategory() {
		return category;
	}
	public String getRegionMstr() {
		return regionMstr;
	}
	public void setRegionMstr(String regionMstr) {
		this.regionMstr = regionMstr;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public Character getC_delflag() {
		return c_delflag;
	}
	public void setC_delflag(Character c_delflag) {
		this.c_delflag = c_delflag;
	}

	
}
