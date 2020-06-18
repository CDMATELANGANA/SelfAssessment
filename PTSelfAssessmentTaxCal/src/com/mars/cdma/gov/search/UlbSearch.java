package com.mars.cdma.gov.search;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;

public class UlbSearch extends BaseSearchObject {

	private static final long serialVersionUID = 1L;

	private static final Logger log= Logger.getLogger(UlbSearch.class);

	public UlbSearch() {
		if (log.isDebugEnabled()) {
			log.debug("Districts Search created.");
		}
	}

	private String ulbName;
	
	private long ulbId;

	private int status = -1;

	
	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
		if (StringUtils.isNotEmpty(ulbName)) {
			setSearchParamSet(true);
		}
	}

	public long getUlbId() {
		return ulbId;
	}

	public void setUlbId(long ulbId) {
		this.ulbId = ulbId;
		if (ulbId > -1) {
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
