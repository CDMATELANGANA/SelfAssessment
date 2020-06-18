package com.mars.cdma.gov.service;

import java.util.List;

/**
 * @author RAHUL
 *
 */

public interface InitiateAddmeasurementAssessmentReportService {
	public List getptSelfAsmntTransStatus(int parseInt);

	public List getptSelfAsmntTransDetailByReqNumber(String uniqueRequestId);

	public List<Object> getptSelfAsmntTransCollection();

	public List getptSelfAsmntTransDetail(String string);

}
