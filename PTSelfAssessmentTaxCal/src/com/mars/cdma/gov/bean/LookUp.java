package com.mars.cdma.gov.bean;

import com.mars.cdma.gov.utils.Constants;


public class LookUp implements java.io.Serializable{
	private static final long serialVersionUID = 5525992955324627073L;
	
	private long lookUpId;
    private String lookUpType;
    private String lookUpValue;
    private int status = Constants.ACTIVE;
    
    public long getLookUpId() {
		return lookUpId;
	}

	public void setLookUpId(long lookUpId) {
		this.lookUpId = lookUpId;
	}

	public String getLookUpType() {
		return lookUpType;
	}

	public void setLookUpType(String lookUpType) {
		this.lookUpType = lookUpType;
	}

	public String getLookUpValue() {
		return lookUpValue;
	}

	public void setLookUpValue(String lookUpValue) {
		this.lookUpValue = lookUpValue;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
