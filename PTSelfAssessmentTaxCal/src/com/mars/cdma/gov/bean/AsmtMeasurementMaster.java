package com.mars.cdma.gov.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ptasmtmeasurementmaster")
public class AsmtMeasurementMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "measurement_id")
	private long measurementId;

	@Column(name = "bcls")
	private String bcls;

	@Column(name = "busage")
	private String busage;

	@Column(name = "octy")
	private String octy;

	@Column(name = "bage")
	private String bage;

	@Column(name = "len")
	private String len;

	@Column(name = "wid")
	private String wid;

	@Column(name = "floorNo")
	private String floorNo;

	@Column(name = "planAprvl")
	private String planAprvl;

	@Column(name = "I_UR")
	private double I_UR;
	@Column(name = "D_MRV")
	private double D_MRV;
	@Column(name = "D_ARV")
	private double D_ARV;
	@Column(name = "D_PLNTAREA")
	private double D_PLNTAREA;
	@Column(name = "D_PT")
	private double D_PT;
	@Column(name = "I_DEPCODE")
	private double I_DEPCODE;
	@Column(name = "D_BLDGVAL")
	private double D_BLDGVAL;
	@Column(name = "D_SITEVAL")
	private double D_SITEVAL;
	@Column(name = "D_TARV")
	private double D_TARV;

	@Column(name = "cPlinth")
	private String cPlinth;

	@Column(name = "D_UAC")
	private double D_UAC;
	
	@Column(name = "usageType")
	private String  usageType;

	
	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public long getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(long measurementId) {
		this.measurementId = measurementId;
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

	public String getOcty() {
		return octy;
	}

	public void setOcty(String octy) {
		this.octy = octy;
	}

	public String getBage() {
		return bage;
	}

	public void setBage(String bage) {
		this.bage = bage;
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

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getPlanAprvl() {
		return planAprvl;
	}

	public void setPlanAprvl(String planAprvl) {
		this.planAprvl = planAprvl;
	}

	public double getI_UR() {
		return I_UR;
	}

	public void setI_UR(double i_UR) {
		I_UR = i_UR;
	}

	public double getD_MRV() {
		return D_MRV;
	}

	public void setD_MRV(double d_MRV) {
		D_MRV = d_MRV;
	}

	public double getD_ARV() {
		return D_ARV;
	}

	public void setD_ARV(double d_ARV) {
		D_ARV = d_ARV;
	}

	public double getD_PLNTAREA() {
		return D_PLNTAREA;
	}

	public void setD_PLNTAREA(double d_PLNTAREA) {
		D_PLNTAREA = d_PLNTAREA;
	}

	public double getD_PT() {
		return D_PT;
	}

	public void setD_PT(double d_PT) {
		D_PT = d_PT;
	}

	public double getI_DEPCODE() {
		return I_DEPCODE;
	}

	public void setI_DEPCODE(double i_DEPCODE) {
		I_DEPCODE = i_DEPCODE;
	}

	public double getD_BLDGVAL() {
		return D_BLDGVAL;
	}

	public void setD_BLDGVAL(double d_BLDGVAL) {
		D_BLDGVAL = d_BLDGVAL;
	}

	public double getD_SITEVAL() {
		return D_SITEVAL;
	}

	public void setD_SITEVAL(double d_SITEVAL) {
		D_SITEVAL = d_SITEVAL;
	}

	public double getD_TARV() {
		return D_TARV;
	}

	public void setD_TARV(double d_TARV) {
		D_TARV = d_TARV;
	}

	public String getcPlinth() {
		return cPlinth;
	}

	public void setcPlinth(String cPlinth) {
		this.cPlinth = cPlinth;
	}

	public double getD_UAC() {
		return D_UAC;
	}

	public void setD_UAC(double d_UAC) {
		D_UAC = d_UAC;
	}

}
