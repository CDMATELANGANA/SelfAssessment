package com.mars.cdma.gov.service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author SARITA
 *
 */
public interface AssessmentInitiateService {
	public List<Object[]> getInitiateAllDistrictList();
	public List<Object[]> getInitiateSelectedDistrictUlbs(String districtid);
	public List<String> getInitiateSelectedUlbList(String ulbcode) throws SQLException;
	public List<Object[]> getInitiateSelectedUlbDetailList(List<String> assmentList,String ulbcode);
	public List<String> getInitiateSelectedUlbDuplicateAssList(String ulbcode) throws SQLException;
	public List<Object[]> getInitiateSelectedUlbDuplicateAssDetailList(List<String> assmentList,String ulbcode);
	public void updateBeforeInitiate(String assessment,String uniqueno) throws SQLException;
}
