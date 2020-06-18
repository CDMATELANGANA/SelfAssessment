package com.mars.cdma.gov.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "assessment_master")
public class AssessmentMaster2 {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "assessment_applicationId")
	private long assessmentApplicationId;
	
	@Column(name = "owner_surName")
	String ownerSurName;	
	
	@Column(name = "owner_name")
	String ownerName;
	
	@Column(name = "owner_father_husband_sur_name")
	String ownerFatherHusbandSurName;	
	
	@Column(name = "owner_father_husband_nme")
	String ownerFatherHusbandName;
	
	@Column(name = "owner_pan")
	String ownerPan;
	
	@Column(name = "owner_aadhar")
	String ownerAadhar;
	
	@Column(name = "owner_door_no")
	String ownerDoorNo;
	
	@Column(name = "owner_street")
	String ownerStreet;
	
	@Column(name = "owner_pin_code")
	String ownerPinCode;
	
	@Column(name = "owner_villiage")
	String ownerVilliage;
	
	@Column(name = "owner_email")
	String ownerEmail;
	
	@Column(name = "owner_mobile")
	String ownerMobile;
	
	@Column(name = "owner_land_phone")
	String ownerLandPhone;
	
	@Column(name = "owner_fax")
	String ownerFax;
	
	@Column(name = "occupant_name")
	String occupantName;
	
	@Column(name = "occupant_sur_name")
	String occupantSurName;
	
	@Column(name = "ownership_type")
	String ownerShipType;
	
	@Column(name = "location_district")
	String locationDistrict;
	
	@Column(name = "location_ulb")
	String locationUlb;
	
	@Column(name = "locality")
	String locality;
	
	@Column(name = "zone")
	String zone;
	
	@Column(name = "revenue_ward")
	String revenueward;
	
	
	@Column(name = "block")
	String block;
	
	@Column(name = "elec_ward")
	String elecward;
	
	@Column(name = "street")
	String street;
	
	@Column(name = "building_plan")
	String buildingPlan;
	
	@Column(name = "permission_number")
	String permissionNumber;
	
	@Column(name = "owner_document_path")
	String ownerDocumentPath;
	
	@Column(name = "ownerbuilding_img_path")
	String ownerbuildingImgPath;
	
		
	@Column(name = "delflag")
	private Character delflag;
	
	@Column(name = "entrydate")
	@Temporal(TemporalType.DATE)
	private Date entrydate;
	
	
	
	
}
