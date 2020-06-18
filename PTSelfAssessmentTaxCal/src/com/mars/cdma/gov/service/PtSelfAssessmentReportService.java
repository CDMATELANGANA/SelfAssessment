/**
 * 
 */
package com.mars.cdma.gov.service;

import java.util.List;

/**
 * @author RAHUL
 *
 */
public interface PtSelfAssessmentReportService {
	public List getSelfAsmntTransDetail(String tranYear);
	public List getSelfAsmntTransDetailByReqNumber(String uniqueRequestId);
	public List<Object> getSelfAsmntTransCollection();
	public List<Object> getSelfAsmntTransStatus(int transReceiptNo);

	

}
