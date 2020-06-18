package com.mars.cdma.gov.bean;

public class AssessmentReceipt implements java.io.Serializable {
	private static final long serialVersionUID = -3366869048689898317L;
	private String bookNo;
	private String receiptNo;
	private String receipDate;
	private String entryDate;
	private String paidFromDate;
	private String paidUptoDate;
	private String paidMode;
	private String amountPaidAt;
	private String currentAmount;
	private String arrearAmount;
	private String penalityAmount;
	private String advanceAmount;
	private String totalPaidAmount;
	
	private String  status;
	
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getReceipDate() {
		return receipDate;
	}
	public void setReceipDate(String receipDate) {
		this.receipDate = receipDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getPaidFromDate() {
		return paidFromDate;
	}
	public void setPaidFromDate(String paidFromDate) {
		this.paidFromDate = paidFromDate;
	}
	public String getPaidUptoDate() {
		return paidUptoDate;
	}
	public void setPaidUptoDate(String paidUptoDate) {
		this.paidUptoDate = paidUptoDate;
	}
	public String getPaidMode() {
		return paidMode;
	}
	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}
	public String getAmountPaidAt() {
		return amountPaidAt;
	}
	public void setAmountPaidAt(String amountPaidAt) {
		this.amountPaidAt = amountPaidAt;
	}
	public String getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getArrearAmount() {
		return arrearAmount;
	}
	public void setArrearAmount(String arrearAmount) {
		this.arrearAmount = arrearAmount;
	}
	public String getPenalityAmount() {
		return penalityAmount;
	}
	public void setPenalityAmount(String penalityAmount) {
		this.penalityAmount = penalityAmount;
	}
	public String getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(String totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
