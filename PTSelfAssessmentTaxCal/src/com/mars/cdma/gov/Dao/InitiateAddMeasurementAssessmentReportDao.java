/**
 * 
 */
package com.mars.cdma.gov.Dao;

import java.util.List;

/**
 * @author RAHUL
 *
 */
public interface InitiateAddMeasurementAssessmentReportDao {

	public List getptSelfAsmntTransStatus(int parseInt);

	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId);

	public List<Object> getptSelfAsmntTransCollection();

	public List getptSelfAsmntTransDetail(String tranYear);

}
