package com.mars.cdma.gov.bean;

import java.io.Serializable;

public class AssesmentDb2Bean  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Appid;
	private String bage;
	private String bcls;
	private String busage;
	private String len;
	private String wid;
	private String octy;
	private String floorNo;
	
	
	public String getAppid() {
		return Appid;
	}
	public void setAppid(String appid) {
		this.Appid = appid;
	}
	public String getBage() {
		return bage;
	}
	public void setBage(String bage) {
		this.bage = bage;
	}
	public String getBcls() {
		return bcls;
	}
	public void setBcls(String bcls) {
		this.bcls = bcls;
	}
	public String getBusage() {
		return busage;
	}
	public void setBusage(String busage) {
		this.busage = busage;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getOcty() {
		return octy;
	}
	public void setOcty(String octy) {
		this.octy = octy;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	@Override
	public String toString() {
		return "AssesmentDb2Bean [bage=" + bage + ", bcls=" + bcls
				+ ", busage=" + busage + ", len=" + len + ", wid=" + wid
				+ ", octy=" + octy + ", floorNo=" + floorNo + "]";
	}
	
}
