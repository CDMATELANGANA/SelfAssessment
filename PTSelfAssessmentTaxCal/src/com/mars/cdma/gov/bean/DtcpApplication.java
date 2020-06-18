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
@Table(name="dtcp_application")
public class DtcpApplication{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="relationsurname")
	private String relationsurname;
	@Column(name="aadharnumber")
	private long aadharnumber;
	@Column(name="ownercity")
	private String ownercity;
	@Column(name="mobilenumber")
	private String mobilenumber;
	@Column(name="occupantname")
	private String occupantname;
	@Column(name="occupantsurname")
	private String occupantsurname;
	@Column(name="ownershiptype")
	private String ownershiptype;
	@Column(name="districtcode")
	private int districtcode;
	@Column(name="ulbcode")
	private int ulbcode;
	@Column(name="localitycode")
	private int localitycode;
	@Column(name="zonecode")
	private int zonecode;
	@Column(name="revenue_ward_code")
	private int revenue_ward_code;
	@Column(name="block_no_code")
	private int block_no_code;
	@Column(name="electionwardcode")
	private int electionwardcode;
	@Column(name="street_name_code")
	private int street_name_code;
	@Column(name="building_plan_aprvl_code")
	private char building_plan_aprvl_code;
	@Column(name="reg_doc_no")
	private int reg_doc_no;
	@Column(name="reg_date")
	@Temporal(TemporalType.DATE)
	private Date reg_date;
	@Column(name="building_cls")
	private  String building_cls;
	@Column(name="building_type")
	private String building_type;
	@Column(name="occupant_type")
	private String occupant_type;
	@Column(name="building_age") 
	private int building_age;
	@Column(name="length")
	private int length;
	@Column(name="width")
	private int width;
	@Column(name="delflag")
	private char delflag;
	@Column(name="emas_status")
	private char emas_status;
	@Column(name="emasRegNo")
	private long emasRegNo;
	@Column(name="dtcpfilenumber")
	private String dtcpfilenumber;
	@Column(name="entry_date")
	@Temporal(TemporalType.DATE)
	private Date entry_date;
	@Column(name="surname")
	private String surname;
	@Column(name="ownername")
	private String ownername;
	@Column(name="relationname")
	private String relationname;
	
	
	
	public DtcpApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtcpApplication(String surname, String ownername,
			String relationname,String relationsurname, long aadharnumber, String ownercity,
			String mobilenumber, String occupantname, String occupantsurname,
			String ownershiptype, int districtcode, int ulbcode,
			int localitycode, int zonecode, int revenue_ward_code,
			int block_no_code, int electionwardcode, int street_name_code,
			char building_plan_aprvl_code, int reg_doc_no, Date reg_date,
			String building_cls, String building_type, String occupant_type,
			int building_age, int length, int width, char delflag,
			char emas_status, long emasRegNo, Date entry_date,String dtcpfilenumber) {
		super();
		this.surname = surname;
		this.ownername = ownername;
		this.relationname = relationname;
		this.relationsurname = relationsurname;
		this.aadharnumber = aadharnumber;
		this.ownercity = ownercity;
		this.mobilenumber = mobilenumber;
		this.occupantname = occupantname;
		this.occupantsurname = occupantsurname;
		this.ownershiptype = ownershiptype;
		this.districtcode = districtcode;
		this.ulbcode = ulbcode;
		this.localitycode = localitycode;
		this.zonecode = zonecode;
		this.revenue_ward_code = revenue_ward_code;
		this.block_no_code = block_no_code;
		this.electionwardcode = electionwardcode;
		this.street_name_code = street_name_code;
		this.building_plan_aprvl_code = building_plan_aprvl_code;
		this.reg_doc_no = reg_doc_no;
		this.reg_date = reg_date;
		this.building_cls = building_cls;
		this.building_type = building_type;
		this.occupant_type = occupant_type;
		this.building_age = building_age;
		this.length = length;
		this.width = width;
		this.delflag = delflag;
		this.emas_status = emas_status;
		this.emasRegNo = emasRegNo;
		this.entry_date = entry_date;
		this.dtcpfilenumber=dtcpfilenumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getRelationname() {
		return relationname;
	}
	public void setRelationname(String relationname) {
		this.relationname = relationname;
	}
	public String getRelationsurname() {
		return relationsurname;
	}
	public void setRelationsurname(String relationsurname) {
		this.relationsurname = relationsurname;
	}
	public long getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(long aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public String getOwnercity() {
		return ownercity;
	}
	public void setOwnercity(String ownercity) {
		this.ownercity = ownercity;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
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
	public int getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(int districtcode) {
		this.districtcode = districtcode;
	}
	public int getUlbcode() {
		return ulbcode;
	}
	public void setUlbcode(int ulbcode) {
		this.ulbcode = ulbcode;
	}
	public int getLocalitycode() {
		return localitycode;
	}
	public void setLocalitycode(int localitycode) {
		this.localitycode = localitycode;
	}
	public int getZonecode() {
		return zonecode;
	}
	public void setZonecode(int zonecode) {
		this.zonecode = zonecode;
	}
	public int getRevenue_ward_code() {
		return revenue_ward_code;
	}
	public void setRevenue_ward_code(int revenue_ward_code) {
		this.revenue_ward_code = revenue_ward_code;
	}
	public int getBlock_no_code() {
		return block_no_code;
	}
	public void setBlock_no_code(int block_no_code) {
		this.block_no_code = block_no_code;
	}
	public int getElectionwardcode() {
		return electionwardcode;
	}
	public void setElectionwardcode(int electionwardcode) {
		this.electionwardcode = electionwardcode;
	}
	public int getStreet_name_code() {
		return street_name_code;
	}
	public void setStreet_name_code(int street_name_code) {
		this.street_name_code = street_name_code;
	}
	public char getBuilding_plan_aprvl_code() {
		return building_plan_aprvl_code;
	}
	public void setBuilding_plan_aprvl_code(char building_plan_aprvl_code) {
		this.building_plan_aprvl_code = building_plan_aprvl_code;
	}
	public int getReg_doc_no() {
		return reg_doc_no;
	}
	public void setReg_doc_no(int reg_doc_no) {
		this.reg_doc_no = reg_doc_no;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getBuilding_cls() {
		return building_cls;
	}
	public void setBuilding_cls(String building_cls) {
		this.building_cls = building_cls;
	}
	public String getBuilding_type() {
		return building_type;
	}
	public void setBuilding_type(String building_type) {
		this.building_type = building_type;
	}
	public String getOccupant_type() {
		return occupant_type;
	}
	public void setOccupant_type(String occupant_type) {
		this.occupant_type = occupant_type;
	}
	public int getBuilding_age() {
		return building_age;
	}
	public void setBuilding_age(int building_age) {
		this.building_age = building_age;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public char getDelflag() {
		return delflag;
	}
	public void setDelflag(char delflag) {
		this.delflag = delflag;
	}
	public char getEmas_status() {
		return emas_status;
	}
	public void setEmas_status(char emas_status) {
		this.emas_status = emas_status;
	}
	public long getEmasRegNo() {
		return emasRegNo;
	}
	public void setEmasRegNo(long emasRegNo) {
		this.emasRegNo = emasRegNo;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	
	
	
	public String getDtcpfilenumber() {
		return dtcpfilenumber;
	}
	public void setDtcpfilenumber(String dtcpfilenumber) {
		this.dtcpfilenumber = dtcpfilenumber;
	}




	@Override
	public String toString() {
		return "DtcpApplication [id=" + id + ", surname=" + surname
				+ ", ownername=" + ownername + ", relationname=" + relationname
				+ ", relationsurname=" + relationsurname + ", aadharnumber="
				+ aadharnumber + ", ownercity=" + ownercity + ", mobilenumber="
				+ mobilenumber + ", occupantname=" + occupantname
				+ ", occupantsurname=" + occupantsurname + ", ownershiptype="
				+ ownershiptype + ", districtcode=" + districtcode
				+ ", ulbcode=" + ulbcode + ", localitycode=" + localitycode
				+ ", zonecode=" + zonecode + ", revenue_ward_code="
				+ revenue_ward_code + ", block_no_code=" + block_no_code
				+ ", electionwardcode=" + electionwardcode
				+ ", street_name_code=" + street_name_code
				+ ", building_plan_aprvl_code=" + building_plan_aprvl_code
				+ ", reg_doc_no=" + reg_doc_no + ", reg_date=" + reg_date
				+ ", building_cls=" + building_cls + ", building_type="
				+ building_type + ", occupant_type=" + occupant_type
				+ ", building_age=" + building_age + ", length=" + length
				+ ", width=" + width + ", delflag=" + delflag
				+ ", emas_status=" + emas_status + ", emasRegNo=" + emasRegNo
				+ ", entry_date=" + entry_date + "]";
	}
	
	
	

}
