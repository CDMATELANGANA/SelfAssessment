package com.mars.cdma.gov.search;

import java.io.Serializable;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.mars.cdma.gov.utils.Constants;


/**
 * Base class for Model objects. This is basically for the toString, equals and hashCode methods.
 */
public class BaseSearchObject implements Serializable {

	private long currentPage = Constants.DEFAULT_PAGINATION_ALL_ROWS;

	private String orderBy;

	private String sortBy = "asc";

	private boolean searchParamSet = false;
	
	private long displayPageSize = Constants.DEFAULT_ROWS_PER_PAGE;

	private long offset = 0;

	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the searchParamSet
	 */
	public boolean isSearchParamSet() {
		return searchParamSet;
	}

	/**
	 * @param searchParamSet
	 *        the searchParamSet to set
	 */
	public void setSearchParamSet(boolean searchParamSet) {
		this.searchParamSet = searchParamSet;
	}

	/**
	 * @return the offset
	 */
	public long getOffset() {
		offset = (this.currentPage - 1) * Constants.DEFAULT_ROWS_PER_PAGE;
		return offset;
	}

	/**
	 * @param offset
	 *        the offset to set
	 */
	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getDisplayPageSize() {
		return displayPageSize;
	}

	public void setDisplayPageSize(long displayPageSize) {
		this.displayPageSize = displayPageSize;
	}
	
}
