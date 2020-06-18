/**
 * 
 */
package com.mars.cdma.gov.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.sun.net.ssl.HttpsURLConnection;

/**
 * @author Gajendra
 *
 */
public class SMSHttpPostClient {
		 static String username ="tscdma";
		 static String password = "SMS@1234";
		 static String key="35EE3933F9191B";
		 static String campaign="113";
		 static String routeid="6";
		 static String type="text";
		 static String senderid="VESIPL";
		 public void  SendSMSmain(String mobileNo,String message){
			 sendBulkSMS(username, password, senderid, mobileNo, message,key);
		 }
		 public int sendBulkSMS(String username, String password ,  String senderId, String mobileNo,String message , String secureKey){
			 String responseString = "";
			 int  responsecode = 0;
			 SSLSocketFactory sf=null;
			 SSLContext context=null;
			 try {
				 
				System.out.println("mobileNo"+mobileNo);
		        String requestUrl  = "https://manage.smssolutions.in/smsapi/index?&" +
	            "username=" + URLEncoder.encode(username, "UTF-8") +
	            "&password=" + URLEncoder.encode(password, "UTF-8") +
	            "&key=" + URLEncoder.encode(key, "UTF-8") +
	            "&campaign=" + URLEncoder.encode(campaign, "UTF-8") +
	            "&routeid=" + URLEncoder.encode(routeid, "UTF-8") +
	            "&type="+ URLEncoder.encode(type, "UTF-8") +
	            "&contacts="+URLEncoder.encode(mobileNo, "UTF-8")  +
	            "&senderid="+URLEncoder.encode(senderid, "UTF-8")  +
	            "&msg=" + URLEncoder.encode(message, "UTF-8");
		URI url = new URI(requestUrl);
		//context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
		context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
		X509TrustManager tm = new X509TrustManager() {

		    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		    }

		    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		    }

		    public X509Certificate[] getAcceptedIssuers() {
		        return null;
		    }
		};
		context.init(null, new TrustManager[]{tm}, null);
		sf=new SSLSocketFactory(context, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme scheme=new Scheme("https",443,sf);
	    HttpClient client=new DefaultHttpClient();
		client.getConnectionManager().getSchemeRegistry().register(scheme);
		HttpPost uc=new HttpPost(url);
		HttpResponse response=client.execute(uc);
		responsecode = response.getStatusLine().getStatusCode();
		BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		while((line=bf.readLine())!=null){
			responseString = responseString+line;
			
		}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
			return responsecode;
}
		 public char[] OTP(int len) 
		    { 
		     String numbers = "098765789765"; 
		     Random rndm_method = new Random(); 
		     char[] otp = new char[len]; 
		     for (int i = 0; i < len; i++) {
		      otp[i] = 
		      numbers.charAt(rndm_method.nextInt(numbers.length())); 
		        } 
		        return otp; 
		    } 
	}

