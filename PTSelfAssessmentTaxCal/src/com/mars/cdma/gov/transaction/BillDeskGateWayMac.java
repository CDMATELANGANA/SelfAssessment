package com.mars.cdma.gov.transaction;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.mars.cdma.gov.utils.CommonUtils;

public class BillDeskGateWayMac
{


public static String HmacSHA256(String message,String secret)  {
MessageDigest md = null;
	try {

		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		 SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		 sha256_HMAC.init(secret_key);


		byte raw[] = sha256_HMAC.doFinal(message.getBytes());

		StringBuffer ls_sb=new StringBuffer();
		for(int i=0;i<raw.length;i++)
			ls_sb.append(char2hex(raw[i]));
			return ls_sb.toString(); //step 6
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
}

public static String getCheckSumKey(Long receiptNo,Double amount,Long uniqueRequestNumber,int ulbCode,String ulbName,String districtName){
	 String ChecksumKey ="78FkNFx3KNvF";
	//String ChecksumKey ="nzk4r74yYXXz";//Trade CDMATELANG
	//MUTATION  mutation
	System.out.println("Ec "+receiptNo+" Amount "+ amount+" unique "+uniqueRequestNumber+" ulbcode"+ulbCode+" ulb name "+ulbName+"distnme "+districtName);
	
	return HmacSHA256("CDMADEPTEL|"+receiptNo+"|NA|"+amount+"|NA|NA|NA|INR|NA|R|cdmaeptel|NA|NA|F|"+uniqueRequestNumber+"|"+ulbCode+"|"+ulbName+"|"+districtName+"|PTSelfAssessmentTaxCal|NA|NA|"+CommonUtils.getUrl()+"/PTSelfAssessmentTaxCal/paymentconfirmationForMeasurement.do",ChecksumKey);
	
	
}

//Renew
public static String requestUrl(String receiptNo,String amount,String assessmentNo,String ulbCode,String ulbName,String districtName){
	String ChecksumKey ="78FkNFx3KNvF";
	String msg="CDMADEPTEL|"+receiptNo+"|NA|"+amount+"|NA|NA|NA|INR|NA|R|mutation|NA|NA|F|"+assessmentNo+"|"+ulbCode+"|"+ulbName+"|"+districtName+"|PTSelfAssessmentTaxCal|NA|NA|"+CommonUtils.getUrl()+"/PTSelfAssessmentTaxCal/paymentconfirmation.do|";
	String key=HmacSHA256(msg,ChecksumKey); 	
	String requestUrl=msg+""+key;
	
	System.out.println(" Url : "+requestUrl);	
	return requestUrl;	
}

/*public static String getrenwalCheckSumKey(Long receiptNo,Double amount,Long uniqueRequestNumber,int ulbCode,String ulbName,String districtName){
	String ChecksumKey ="w0dOynUnKi9S";
	System.out.println("Ec "+receiptNo+" Amount "+ amount+" unique "+ uniqueRequestNumber+" ulbcode"+ulbCode+" ulb name "+ulbName+"distnme "+districtName);
	
	return HmacSHA256("MUTATION|"+receiptNo+"|NA|"+amount+"|NA|NA|NA|INR|NA|R|mutation|NA|NA|F|"+uniqueRequestNumber+"|"+ulbCode+"|"+ulbName+"|"+districtName+"|NA|NA|NA|"+CommonUtils.getUrl()+"/SelfMutation/renewalPaymentConfirmation.do",ChecksumKey);
} 
*/

 public static String char2hex(byte x)

{
    char arr[]={
                  '0','1','2','3',
                  '4','5','6','7',
                  '8','9','A','B',
                  'C','D','E','F'
                };

    char c[] = {arr[(x & 0xF0)>>4],arr[x & 0x0F]};
    return (new String(c));
  }


}