package com.mars.cdma.gov.bean;

import java.io.Serializable;
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
@Table(name = "transaction_history")
public class PaymentTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_transaction_receipt_id")
	private Long payment_transaction_receipt_id;

	@Column(name = "transaction_id")
	private String transcation_id;

	@Column(name = "transaction_bank_ref")
	private String transaction_bank_ref;

	@Column(name = "transaction_flag")
	private Character transaction_flag;

	@Column(name = "response_url")
	private String RESPONSE_URL;

	@Column(name = "transaction_response_code")
	private String transaction_response_code;
	@Column(name = "transaction_mobile_number")
	private String transaction_mobile_number;
	@Column(name = "transaction_type")
	private String transaction_type;
	@Column(name = "transaction_bank_id")
	private String transaction_bank_id;
	@Column(name = "transaction_bank_in")
	private String transaction_bank_in;
	@Column(name = "transaction_error_description")
	private String transaction_error_description;
	@Column(name = "receipt_owner_name")
	private String receipt_owner_name;
	@Column(name = "receipt_owner_door_no")
	private String receipt_owner_door_no;
	@Column(name = "receipt_owner_address")
	private String receipt_owner_address;
	@Column(name = "ulbcode")
	private int ulbcode;
	@Column(name = "ulbname")
	private String ulbname;
	@Column(name = "districtname")
	private String districtname;
	@Column(name = "transdate")
	@Temporal(TemporalType.DATE)
	private Date transdate;
	@Column(name = "uniqueRequestNumber")
	private long uniqueRequestNumber;
	@Column(name = "paid_update_flag")
	private Character paid_update_flag;
	@Column(name = "transactionmode")
	private String transactionmode;
	@Column(name = "C_AMTPAIDAT")
	private String C_AMTPAIDAT;
	@Column(name = "C_PAYMODE")
	private String C_PAYMODE;
	@Column(name = "gateway_name")
	private String gateway_name;
	@Column(name = "total_amount")
	private double total_amount;
	@Column(name = "c_delflag")
	private Character c_delflag;
	@Column(name = "Cheque_DD_No")
	private String cheque_DD_No;
	@Column(name = "cheque_DD_Date")
	private Date cheque_DD_Date;
	@Column(name = "Cheque_DD_BankBranch")
	private String cheque_DD_BankBranch;
	@Column(name = "loginId")
	private String loginId;
	@Column(name = "assessmentNo")
	private String assessmentNo;
	@Column(name = "DoorNo")
	private String DoorNo;

	public String getAssessmentNo() {
		return assessmentNo;
	}

	public void setAssessmentNo(String assessmentNo) {
		this.assessmentNo = assessmentNo;
	}

	public String getDoorNo() {
		return DoorNo;
	}

	public void setDoorNo(String doorNo) {
		DoorNo = doorNo;
	}

	public String getC_AMTPAIDAT() {
		return C_AMTPAIDAT;
	}

	public void setC_AMTPAIDAT(String c_AMTPAIDAT) {
		C_AMTPAIDAT = c_AMTPAIDAT;
	}

	public String getC_PAYMODE() {
		return C_PAYMODE;
	}

	public void setC_PAYMODE(String c_PAYMODE) {
		C_PAYMODE = c_PAYMODE;
	}

	public String getCheque_DD_No() {
		return cheque_DD_No;
	}

	public void setCheque_DD_No(String cheque_DD_No) {
		this.cheque_DD_No = cheque_DD_No;
	}

	public Date getCheque_DD_Date() {
		return cheque_DD_Date;
	}

	public void setCheque_DD_Date(Date cheque_DD_Date) {
		this.cheque_DD_Date = cheque_DD_Date;
	}

	public String getCheque_DD_BankBranch() {
		return cheque_DD_BankBranch;
	}

	public void setCheque_DD_BankBranch(String cheque_DD_BankBranch) {
		this.cheque_DD_BankBranch = cheque_DD_BankBranch;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Long getPayment_transaction_receipt_id() {
		return payment_transaction_receipt_id;
	}

	public void setPayment_transaction_receipt_id(
			Long payment_transaction_receipt_id) {
		this.payment_transaction_receipt_id = payment_transaction_receipt_id;
	}

	public String getTranscation_id() {
		return transcation_id;
	}

	public void setTranscation_id(String transcation_id) {
		this.transcation_id = transcation_id;
	}

	public String getTransaction_bank_ref() {
		return transaction_bank_ref;
	}

	public void setTransaction_bank_ref(String transaction_bank_ref) {
		this.transaction_bank_ref = transaction_bank_ref;
	}

	public Character getTransaction_flag() {
		return transaction_flag;
	}

	public void setTransaction_flag(Character transaction_flag) {
		this.transaction_flag = transaction_flag;
	}

	public String getRESPONSE_URL() {
		return RESPONSE_URL;
	}

	public void setRESPONSE_URL(String rESPONSE_URL) {
		RESPONSE_URL = rESPONSE_URL;
	}

	public String getTransaction_response_code() {
		return transaction_response_code;
	}

	public void setTransaction_response_code(String transaction_response_code) {
		this.transaction_response_code = transaction_response_code;
	}

	public String getTransaction_mobile_number() {
		return transaction_mobile_number;
	}

	public void setTransaction_mobile_number(String transaction_mobile_number) {
		this.transaction_mobile_number = transaction_mobile_number;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getTransaction_bank_id() {
		return transaction_bank_id;
	}

	public void setTransaction_bank_id(String transaction_bank_id) {
		this.transaction_bank_id = transaction_bank_id;
	}

	public String getTransaction_bank_in() {
		return transaction_bank_in;
	}

	public void setTransaction_bank_in(String transaction_bank_in) {
		this.transaction_bank_in = transaction_bank_in;
	}

	public String getTransaction_error_description() {
		return transaction_error_description;
	}

	public void setTransaction_error_description(
			String transaction_error_description) {
		this.transaction_error_description = transaction_error_description;
	}

	public String getReceipt_owner_name() {
		return receipt_owner_name;
	}

	public void setReceipt_owner_name(String receipt_owner_name) {
		this.receipt_owner_name = receipt_owner_name;
	}

	public String getReceipt_owner_door_no() {
		return receipt_owner_door_no;
	}

	public void setReceipt_owner_door_no(String receipt_owner_door_no) {
		this.receipt_owner_door_no = receipt_owner_door_no;
	}

	public String getReceipt_owner_address() {
		return receipt_owner_address;
	}

	public void setReceipt_owner_address(String receipt_owner_address) {
		this.receipt_owner_address = receipt_owner_address;
	}

	public long getUniqueRequestNumber() {
		return uniqueRequestNumber;
	}

	public void setUniqueRequestNumber(long uniqueRequestNumber) {
		this.uniqueRequestNumber = uniqueRequestNumber;
	}

	public Character getPaid_update_flag() {
		return paid_update_flag;
	}

	public void setPaid_update_flag(Character paid_update_flag) {
		this.paid_update_flag = paid_update_flag;
	}

	public String getGateway_name() {
		return gateway_name = gateway_name;
	}

	public void setGateway_name(String gateway_name) {
		this.gateway_name = gateway_name;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public Character getC_delflag() {
		return c_delflag;
	}

	public void setC_delflag(Character c_delflag) {
		this.c_delflag = c_delflag;
	}

	public int getUlbcode() {
		return ulbcode;
	}

	public void setUlbcode(int ulbcode) {
		this.ulbcode = ulbcode;
	}

	public String getUlbname() {
		return ulbname;
	}

	public void setUlbname(String ulbname) {
		this.ulbname = ulbname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public Date getTransdate() {
		return transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public String getTransactionmode() {
		return transactionmode;
	}

	public void setTransactionmode(String transactionmode) {
		this.transactionmode = transactionmode;
	}

	/*
	 * @OneToOne(cascade = javax.persistence.CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "uniqueRequestNumber") public ETradeApplication
	 * geteTradeApplication() { return eTradeApplication; }
	 * 
	 * public void seteTradeApplication(ETradeApplication eTradeApplication) {
	 * this.eTradeApplication = eTradeApplication; }
	 */

}
