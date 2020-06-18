package com.mars.cdma.gov.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
/**
 * @author SARITA
 *
 */
@Entity
@Table(name = "AddAssessmentMaster")
public class AddAssessmentMaster {

	private static final long serialVersionUID = 4595572994697383247L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "new_application_id")
	private long newapplicationId;

	@Column(name = "OWNER_RELATION")
	private Character ownerRelation;
	@Column(name = "application_doc_enclosed")
	private Character applicationDocEnclosed;

	@Column(name = "delflag")
	private Character delflag;

	@Column(name = "status")
	private Character status;

	@Column(name = "entrydate")
	@Temporal(TemporalType.DATE)
	private Date entrydate;

	@Column(name = "timestamp")
	@Temporal(TemporalType.DATE)
	private Date timestamp;

	@Column(name = "OWNER_MOBILE")
	private String ownerMobile;

	@Column(name = "OWNER_LAND_PHONE")
	private String ownerLandPhone;

	@Column(name = "OWNER_PIN_CODE")
	private String ownerPinCode;

	@Column(name = "OWNER_FAX")
	private Integer ownerFax;

	@Column(name = "OWNER_DISTRICT")
	private Integer ownerDistrict;

	@Column(name = "ulbcode")
	private Integer ulbcode;

	@Column(name = "OWNER_NAME")
	private String ownerName;
	@Column(name = "OWNER_SURNAME")
	private String ownerSurName;
	@Column(name = "OWER_PAN")
	private String ownerPan;
	@Column(name = "OWNER_FATHERHUSBAND_NAME")
	private String ownerFatherHusbandName;
	@Column(name = "OWNERFATHERHUSBAND_SURNAME")
	private String ownerFatherHusbandSurName;
	@Column(name = "OWNER_AADHAR")
	private String ownerAadhar;
	@Column(name = "TINGST")
	private String tinGST;

	@Column(name = "OWNER_DOORNO")
	private String ownerDoorNo;
	@Column(name = "OWNER_STREET")
	private String ownerStreet;
	@Column(name = "OWNER_CITY")
	private String ownerCity;
	@Column(name = "OWNER_EMAIL")
	private String ownerEmail;

	@Column(name = "")
	private String doc_path;

	@Column(name = "uniqueRequestNumber")
	private long uniqueRequestNumber;

	@Column(name = "application_stage")
	private String application_stage;

	@Column(name = "applicationappeoveddate")
	@Temporal(TemporalType.DATE)
	private Date applicationappeoveddate;

	@Column(name = "applicatoinstatusflag")
	private Character applicatoinstatusflag;

	@Column(name = "occupantname")
	private String occupantname;

	@Column(name = "occupantsurname")
	private String occupantsurname;

	@Column(name = "ownershiptype")
	private String ownershiptype;

	@Column(name = "locality")
	private Integer locality;

	@Column(name = "zone")
	private Integer zone;

	@Column(name = "revenueward")
	private Integer revenueward;

	@Column(name = "block")
	private Integer block;
	@Column(name = "elecward")
	private Integer elecward;
	@Column(name = "street")
	private Integer street;

	@Column(name = "planaprvl")
	private String planaprvl;

	@Column(name = "DocEnclosed")
	private Character DocEnclosed;

	@Column(name = "regdocno")
	private Integer regdocno;

	@Column(name = "regDate")
	@Temporal(TemporalType.DATE)
	private Date regDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "asmt_appl_id", referencedColumnName = "new_application_id")
	private Set<AddAsmtMeasurementMaster> addAsmtMeasurementMaster = new HashSet<>();
	
	@Column(name = "deedsAvailable")
	private Character deedsAvailable;

	@Column(name = "gender")
	private Character gender;

	@Column(name = "paymentdate")
	@Temporal(TemporalType.DATE)
	private Date paymentdate;

	@Column(name = "paymentamount")
	private Double paymentamount;

	@Column(name = "paymentflag")
	private Character paymentflag;

	@Column(name = "L_DoorNo")
	private String L_DoorNo;

	@Column(name = "R_DoorNo")
	private String R_DoorNo;

	@Column(name = "N_landMark")
	private String N_landMark;

	@Column(name = "AssessmentNo")
	private String AssessmentNo;

	@Column(name = "pDoorNo")
	private String pDoorNo;

	@Column(name = "floorType")
	private String floorType;

	@Column(name = "roofType")
	private String roofType;

	@Column(name = "waalType")
	private String waalType;

	@Column(name = "woodType")
	private String woodType;

	@Column(name = "NArv")
	private double NArv;

	@Column(name = "FixdPT")
	private double FixdPT;

	@Column(name = "FixdLcs")
	private double FixdLcs;

	@Column(name = "d_fxdunauthplnplty")
	private double d_fxdunauthplnplty;

	@Column(name = "B_PermisionNo")
	private String B_PermisionNo;

	@Column(name = "B_PermisionDate")
	private Date B_PermisionDate;
	@Column(name = "plengthArea")
	private Double plengthArea;

	@Column(name = "Marv")
	private double Marv;
	
	@Column(name = "UnitRate")
	private double UnitRate;
	
	
	@Transient
	private String bcls;

	@Transient
	private String busage;

	@Transient
	private String octy;

	@Transient
	private String bage;

	@Transient
	private String len;

	@Transient
	private String wid;
	@Transient
	private String floorNo;
	@Transient
	private String cPlinth;
	@Transient
	private String usageType;
	
	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public String getcPlinth() {
		return cPlinth;
	}

	public void setcPlinth(String cPlinth) {
		this.cPlinth = cPlinth;
	}

	public double getUnitRate() {
		return UnitRate;
	}

	public void setUnitRate(double unitRate) {
		UnitRate = unitRate;
	}

	public Double getPlengthArea() {
		return plengthArea;
	}

	public void setPlengthArea(Double plengthArea) {
		this.plengthArea = plengthArea;
	}

	public double getMarv() {
		return Marv;
	}

	public void setMarv(double marv) {
		this.Marv = marv;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public double getD_fxdunauthplnplty() {
		return d_fxdunauthplnplty;
	}

	public void setD_fxdunauthplnplty(double d_fxdunauthplnplty) {
		this.d_fxdunauthplnplty = d_fxdunauthplnplty;
	}

	public Date getB_PermisionDate() {
		return B_PermisionDate;
	}

	public void setB_PermisionDate(Date b_PermisionDate) {
		B_PermisionDate = b_PermisionDate;
	}

	public String getB_PermisionNo() {
		return B_PermisionNo;
	}

	public void setB_PermisionNo(String b_PermisionNo) {
		B_PermisionNo = b_PermisionNo;
	}

	public double getFixdPT() {
		return FixdPT;
	}

	public void setFixdPT(double fixdPT) {
		FixdPT = fixdPT;
	}

	public double getFixdLcs() {
		return FixdLcs;
	}

	public void setFixdLcs(double fixdLcs) {
		FixdLcs = fixdLcs;
	}

	public double getNArv() {
		return NArv;
	}

	public void setNArv(double nArv) {
		NArv = nArv;
	}

	public String getFloorType() {
		return floorType;
	}

	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public String getWaalType() {
		return waalType;
	}

	public void setWaalType(String waalType) {
		this.waalType = waalType;
	}

	public String getWoodType() {
		return woodType;
	}

	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}

	public String getAssessmentNo() {
		return AssessmentNo;
	}

	public void setAssessmentNo(String assessmentNo) {
		AssessmentNo = assessmentNo;
	}

	public String getpDoorNo() {
		return pDoorNo;
	}

	public void setpDoorNo(String pDoorNo) {
		this.pDoorNo = pDoorNo;
	}

	public String getN_landMark() {
		return N_landMark;
	}

	public void setN_landMark(String n_landMark) {
		this.N_landMark = n_landMark;
	}

	public String getL_DoorNo() {
		return L_DoorNo;
	}

	public void setL_DoorNo(String l_DoorNo) {
		this.L_DoorNo = l_DoorNo;
	}

	public String getR_DoorNo() {
		return R_DoorNo;
	}

	public void setR_DoorNo(String r_DoorNo) {
		this.R_DoorNo = r_DoorNo;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Double getPaymentamount() {
		return paymentamount;
	}

	public void setPaymentamount(Double paymentamount) {
		this.paymentamount = paymentamount;
	}

	public Character getPaymentflag() {
		return paymentflag;
	}

	public void setPaymentflag(Character paymentflag) {
		this.paymentflag = paymentflag;
	}

	public Character getDeedsAvailable() {
		return deedsAvailable;
	}

	public void setDeedsAvailable(Character deedsAvailable) {
		this.deedsAvailable = deedsAvailable;
	}
	public Set<AddAsmtMeasurementMaster> getAddAsmtMeasurementMaster() {
		return addAsmtMeasurementMaster;
	}

	public void setAddAsmtMeasurementMaster(
			Set<AddAsmtMeasurementMaster> addAsmtMeasurementMaster) {
		this.addAsmtMeasurementMaster = addAsmtMeasurementMaster;
	}

	public long getNewapplicationId() {
		return newapplicationId;
	}

	public void setNewapplicationId(long newapplicationId) {
		this.newapplicationId = newapplicationId;
	}

	public Character getOwnerRelation() {
		return ownerRelation;
	}

	public void setOwnerRelation(Character ownerRelation) {
		this.ownerRelation = ownerRelation;
	}

	public Character getApplicationDocEnclosed() {
		return applicationDocEnclosed;
	}

	public void setApplicationDocEnclosed(Character applicationDocEnclosed) {
		this.applicationDocEnclosed = applicationDocEnclosed;
	}

	public Character getDelflag() {
		return delflag;
	}

	public void setDelflag(Character delflag) {
		this.delflag = delflag;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getOwnerLandPhone() {
		return ownerLandPhone;
	}

	public void setOwnerLandPhone(String ownerLandPhone) {
		this.ownerLandPhone = ownerLandPhone;
	}

	public String getOwnerPinCode() {
		return ownerPinCode;
	}

	public void setOwnerPinCode(String ownerPinCode) {
		this.ownerPinCode = ownerPinCode;
	}

	public Integer getOwnerFax() {
		return ownerFax;
	}

	public void setOwnerFax(Integer ownerFax) {
		this.ownerFax = ownerFax;
	}

	public Integer getOwnerDistrict() {
		return ownerDistrict;
	}

	public void setOwnerDistrict(Integer ownerDistrict) {
		this.ownerDistrict = ownerDistrict;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSurName() {
		return ownerSurName;
	}

	public void setOwnerSurName(String ownerSurName) {
		this.ownerSurName = ownerSurName;
	}

	public String getOwnerPan() {
		return ownerPan;
	}

	public void setOwnerPan(String ownerPan) {
		this.ownerPan = ownerPan;
	}

	public String getOwnerFatherHusbandName() {
		return ownerFatherHusbandName;
	}

	public void setOwnerFatherHusbandName(String ownerFatherHusbandName) {
		this.ownerFatherHusbandName = ownerFatherHusbandName;
	}

	public String getOwnerFatherHusbandSurName() {
		return ownerFatherHusbandSurName;
	}

	public void setOwnerFatherHusbandSurName(String ownerFatherHusbandSurName) {
		this.ownerFatherHusbandSurName = ownerFatherHusbandSurName;
	}

	public String getOwnerAadhar() {
		return ownerAadhar;
	}

	public void setOwnerAadhar(String ownerAadhar) {
		this.ownerAadhar = ownerAadhar;
	}

	public String getTinGST() {
		return tinGST;
	}

	public void setTinGST(String tinGST) {
		this.tinGST = tinGST;
	}

	public String getOwnerDoorNo() {
		return ownerDoorNo;
	}

	public void setOwnerDoorNo(String ownerDoorNo) {
		this.ownerDoorNo = ownerDoorNo;
	}

	public String getOwnerStreet() {
		return ownerStreet;
	}

	public void setOwnerStreet(String ownerStreet) {
		this.ownerStreet = ownerStreet;
	}

	public String getOwnerCity() {
		return ownerCity;
	}

	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getDoc_path() {
		return doc_path;
	}

	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}

	public long getUniqueRequestNumber() {
		return uniqueRequestNumber;
	}

	public void setUniqueRequestNumber(long uniqueRequestNumber) {
		this.uniqueRequestNumber = uniqueRequestNumber;
	}

	public String getApplication_stage() {
		return application_stage;
	}

	public void setApplication_stage(String application_stage) {
		this.application_stage = application_stage;
	}

	public Date getApplicationappeoveddate() {
		return applicationappeoveddate;
	}

	public void setApplicationappeoveddate(Date applicationappeoveddate) {
		this.applicationappeoveddate = applicationappeoveddate;
	}

	public Character getApplicatoinstatusflag() {
		return applicatoinstatusflag;
	}

	public void setApplicatoinstatusflag(Character applicatoinstatusflag) {
		this.applicatoinstatusflag = applicatoinstatusflag;
	}

	public String getOccupantname() {
		return occupantname;
	}

	public void setOccupantname(String occupantname) {
		this.occupantname = occupantname;
	}

	public String getOccupantsurname() {
		return occupantsurname;
	}

	public void setOccupantsurname(String occupantsurname) {
		this.occupantsurname = occupantsurname;
	}

	public String getOwnershiptype() {
		return ownershiptype;
	}

	public void setOwnershiptype(String ownershiptype) {
		this.ownershiptype = ownershiptype;
	}

	public Integer getLocality() {
		return locality;
	}

	public void setLocality(Integer locality) {
		this.locality = locality;
	}

	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	public Integer getRevenueward() {
		return revenueward;
	}

	public void setRevenueward(Integer revenueward) {
		this.revenueward = revenueward;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getElecward() {
		return elecward;
	}

	public void setElecward(Integer elecward) {
		this.elecward = elecward;
	}

	public Integer getStreet() {
		return street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	public String getPlanaprvl() {
		return planaprvl;
	}

	public void setPlanaprvl(String planaprvl) {
		this.planaprvl = planaprvl;
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

	public Integer getRegdocno() {
		return regdocno;
	}

	public void setRegdocno(Integer regdocno) {
		this.regdocno = regdocno;
	}

	public Integer getUlbcode() {
		return ulbcode;
	}

	public void setUlbcode(Integer ulbcode) {
		this.ulbcode = ulbcode;
	}

	public Character getDocEnclosed() {
		return DocEnclosed;
	}

	public void setDocEnclosed(Character docEnclosed) {
		DocEnclosed = docEnclosed;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "AddAssessmentMaster [newapplicationId=" + newapplicationId
				+ ", ownerRelation=" + ownerRelation
				+ ", applicationDocEnclosed=" + applicationDocEnclosed
				+ ", delflag=" + delflag + ", status=" + status
				+ ", entrydate=" + entrydate + ", timestamp=" + timestamp
				+ ", ownerMobile=" + ownerMobile + ", ownerLandPhone="
				+ ownerLandPhone + ", ownerPinCode=" + ownerPinCode
				+ ", ownerFax=" + ownerFax + ", ownerDistrict=" + ownerDistrict
				+ ", ulbcode=" + ulbcode + ", ownerName=" + ownerName
				+ ", ownerSurName=" + ownerSurName + ", ownerPan=" + ownerPan
				+ ", ownerFatherHusbandName=" + ownerFatherHusbandName
				+ ", ownerFatherHusbandSurName=" + ownerFatherHusbandSurName
				+ ", ownerAadhar=" + ownerAadhar + ", tinGST=" + tinGST
				+ ", ownerDoorNo=" + ownerDoorNo + ", ownerStreet="
				+ ownerStreet + ", ownerCity=" + ownerCity + ", ownerEmail="
				+ ownerEmail + ", doc_path=" + doc_path
				+ ", uniqueRequestNumber=" + uniqueRequestNumber
				+ ", application_stage=" + application_stage
				+ ", applicationappeoveddate=" + applicationappeoveddate
				+ ", applicatoinstatusflag=" + applicatoinstatusflag
				+ ", occupantname=" + occupantname + ", occupantsurname="
				+ occupantsurname + ", ownershiptype=" + ownershiptype
				+ ", locality=" + locality + ", zone=" + zone
				+ ", revenueward=" + revenueward + ", block=" + block
				+ ", elecward=" + elecward + ", street=" + street
				+ ", planaprvl=" + planaprvl + ", DocEnclosed=" + DocEnclosed
				+ ", regdocno=" + regdocno + ", regDate=" + regDate
				+ ", addAsmtMeasurementMaster=" + addAsmtMeasurementMaster
				+ ", deedsAvailable=" + deedsAvailable + ", gender=" + gender
				+ ", paymentdate=" + paymentdate + ", paymentamount="
				+ paymentamount + ", paymentflag=" + paymentflag
				+ ", L_DoorNo=" + L_DoorNo + ", R_DoorNo=" + R_DoorNo
				+ ", N_landMark=" + N_landMark + ", AssessmentNo="
				+ AssessmentNo + ", pDoorNo=" + pDoorNo + ", floorType="
				+ floorType + ", roofType=" + roofType + ", waalType="
				+ waalType + ", woodType=" + woodType + ", NArv=" + NArv
				+ ", FixdPT=" + FixdPT + ", FixdLcs=" + FixdLcs
				+ ", d_fxdunauthplnplty=" + d_fxdunauthplnplty
				+ ", B_PermisionNo=" + B_PermisionNo + ", B_PermisionDate="
				+ B_PermisionDate + ", plengthArea=" + plengthArea + ", Marv="
				+ Marv + ", UnitRate=" + UnitRate + ", bcls=" + bcls
				+ ", busage=" + busage + ", octy=" + octy + ", bage=" + bage
				+ ", len=" + len + ", wid=" + wid + ", floorNo=" + floorNo
				+ ", cPlinth=" + cPlinth + ", usageType=" + usageType + "]";
	}

}
