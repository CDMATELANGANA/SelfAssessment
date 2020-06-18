/**
 * 
 */
package com.mars.cdma.gov.Dao;

import java.util.List;

/**
 * @author RAHUL
 *
 */
public interface PtSelfAssmntReportsDao {
	
	public List getSelfAsmntTransDetail(String tranYear);
	public List getSelfAsmntTransDetailByReqNumber(String uniqueRequestId);
	public List<Object> getSelfAsmntTransCollection();
	public List<Object> getSelfAsmntTransStatus(int transReceiptNo);

	


}
