package com.mars.cdma.gov.bean;

import java.io.Serializable;

public class RegionMstr implements Serializable{

	private static final long serialVersionUID = 5828581253127563325L;
	
	private int regionCode;
	private  String regionName;
	public int getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	

}
