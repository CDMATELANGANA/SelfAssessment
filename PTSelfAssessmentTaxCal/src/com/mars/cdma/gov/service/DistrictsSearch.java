package com.mars.cdma.gov.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;

import com.mars.cdma.gov.search.BaseSearchObject;

public class DistrictsSearch extends BaseSearchObject {

	private static final long serialVersionUID = 1L;

	private static final Logger log= Logger.getLogger(DistrictsSearch.class);

	public DistrictsSearch() {
		if (log.isDebugEnabled()) {
			log.debug("Districts Search created.");
		}
	}

	private String districtName;
	
	private long districtId;
	

	private int status = -1;

	
	public String getDistrictName() {
		return districtName;
	}
	
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
		if (StringUtils.isNotEmpty(districtName)) {
			setSearchParamSet(true);
		}
	}
	
	

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
		if (districtId > -1) {
			setSearchParamSet(true);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		if (status > -1) {
			setSearchParamSet(true);
		}
	}
	

	
}
