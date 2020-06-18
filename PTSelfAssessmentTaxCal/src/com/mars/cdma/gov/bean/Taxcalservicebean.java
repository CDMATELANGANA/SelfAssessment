package com.mars.cdma.gov.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dtcp_services")
public class Taxcalservicebean implements Serializable {

	private static final long serialVersionUID = 4595572994697383247L;

	@Id
	@Column(name = "filenumber")
	private String filenumber;  
	
	@Column(name = "districtcode")
	private String districtcode;
	@Column(name = "returnstatus")
	private Character returnstatus;

	@Column(name = "ulbid")
	private String i_ulbid;
	@Column(name = "delflag")
	private Character delflag;
	@Column(name = "zoneobjid")
	private String i_zoneobjid;
	@Column(name = "clscode")
	private String i_clscode;
	@Column(name = "bldgusecode")
	private String i_bldgusecode;
	@Column(name = "lctyobjid")
	private String i_lctyobjid;
	@Column(name = "ocpntypecode")
	private String i_ocpntypecode;
	@Column(name = "plintharea")
	private double plintharea;
	@Column(name = "arv")
	private double arv;
	@Column(name = "estimatedtax")
	private double estimatedtax;
	
	@Column(name = "visitor_Flag")
	private String visitorflag;
	
	public String getVisitorflag() {
		return visitorflag;
	}

	public void setVisitorflag(String visitorflag) {
		this.visitorflag = visitorflag;
	}

	public Taxcalservicebean(String districtcode, String ulbname,
			String filenumber, String i_ulbid, String i_zoneobjid,
			String i_clscode, String i_bldgusecode, String i_lctyobjid,
			String i_ocpntypecode, double plintharea, double arv,
			double estimatedtax,String visitorflag) {
		super();
		this.districtcode = districtcode;
		// this.ulbcode = ulbcode;
		this.filenumber = filenumber;
		this.i_ulbid = i_ulbid;
		this.i_zoneobjid = i_zoneobjid;
		this.i_clscode = i_clscode;
		this.i_bldgusecode = i_bldgusecode;
		this.i_lctyobjid = i_lctyobjid;
		this.i_ocpntypecode = i_ocpntypecode;
		this.plintharea = plintharea;
		this.arv = arv;
		this.estimatedtax = estimatedtax;
		this.visitorflag = visitorflag;
	}

	public Taxcalservicebean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DtcpServices [newId=" + ", districtcode=" + districtcode
				+ ", filenumber=" + filenumber + ", i_ulbid=" + i_ulbid
				+ ", i_zoneobjid=" + i_zoneobjid + ", i_clscode=" + i_clscode
				+ ", i_bldgusecode=" + i_bldgusecode + ", i_lctyobjid="
				+ i_lctyobjid + ", i_ocpntypecode=" + i_ocpntypecode
				+ ", plintharea=" + plintharea + ", arv=" + arv
				+ ", estimatedtax=" + estimatedtax +"visitorflag="+visitorflag+ "]";
	}

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public String getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(String filenumber) {
		this.filenumber = filenumber;
	}

	public String getI_ulbid() {
		return i_ulbid;
	}

	public void setI_ulbid(String i_ulbid) {
		this.i_ulbid = i_ulbid;
	}

	public String getI_zoneobjid() {
		return i_zoneobjid;
	}

	public void setI_zoneobjid(String i_zoneobjid) {
		this.i_zoneobjid = i_zoneobjid;
	}

	public String getI_clscode() {
		return i_clscode;
	}

	public void setI_clscode(String i_clscode) {
		this.i_clscode = i_clscode;
	}

	public String getI_bldgusecode() {
		return i_bldgusecode;
	}

	public void setI_bldgusecode(String i_bldgusecode) {
		this.i_bldgusecode = i_bldgusecode;
	}

	public String getI_lctyobjid() {
		return i_lctyobjid;
	}

	public void setI_lctyobjid(String i_lctyobjid) {
		this.i_lctyobjid = i_lctyobjid;
	}

	public String getI_ocpntypecode() {
		return i_ocpntypecode;
	}

	public void setI_ocpntypecode(String i_ocpntypecode) {
		this.i_ocpntypecode = i_ocpntypecode;
	}

	public double getPlintharea() {
		return plintharea;
	}

	public void setPlintharea(double plintharea) {
		this.plintharea = plintharea;
	}

	public Character getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(Character returnstatus) {
		this.returnstatus = returnstatus;
	}

	public Character getDelflag() {
		return delflag;
	}

	public void setDelflag(Character delflag) {
		this.delflag = delflag;
	}

	public double getArv() {
		return arv;
	}

	public void setArv(double arv) {
		this.arv = arv;
	}

	public double getEstimatedtax() {
		return estimatedtax;
	}

	public void setEstimatedtax(double estimatedtax) {
		this.estimatedtax = estimatedtax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
