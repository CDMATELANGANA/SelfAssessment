/*package com.mars.cdma.gov.helper;
package com.mars.cdma.gov.helper;


import java.io.*;
import java.net.*;
import java.sql.Connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.derby.tools.sysinfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
*//**
 * @author : Kashireddy Srinivasreddy
 * Class Name : SMSHttpPostClient.java
 *//*
public class SMSHttpPostClient {
	
	 *//****************Ap****************//*
	   static String username = "CDMA";
	 static String password = "eSuvidha@123";
	 static String senderid = "CDMASM";
	  *//******** Telangana ********//*
	static String username = "CDMATELANGANA";
	 static String password = "india@999";
	 static String senderid = "TSCDMA";
	 static String secureKey="65ff1daa-95e8-49b5-a954-1a41a9bfabb3";
	
	static String scheduledTime = "20110701 02:27:00";
	static HttpURLConnection connection = null;
	
	//public static void main(String[] args) {
	public void   SendSMSmain(String mobileNo,String message) {
		
		  //int returncode=0;
		try {
			 URL url = new URL("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
			//URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequ");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true); 
			
			 
			
			*//************------ Enable Below connection Class for Different type of SMS Mode  *Kashireddy*------********************//*
			
			------ Enable for Single SMS Mode *Kashireddy*------******************
			 //returncode = sendSingleSMS(username, password, senderid, mobileNo, message);
			 
			//returncode= sendSingleSMS(username, password, senderid, mobileNo, message, secureKey);
			  
			   
			*//************------ Enable for Bulk SMS Mode  *Kashireddy*------*//*
			  sendBulkSMS(username, password, senderid, mobileNo, message, secureKey);
			 *//************------ Enable for Schedule SMS Mode *Kashireddy* ------*******************//*
			 //returncode = sendScheduledSMS(username, password, senderid,mobileNos, message, scheduledTime);
			 *//************------ Enable for Uni-Code SMS Mode *Kashireddy* ------*******************//*
			// returncode=sendSingleUicodeSMS(username, password, senderid, mobileNo, message);
			 
				*//******************* END *Kashireddy***********************************//*
			 
			 
			 
			
		

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			 
			 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		//return  returncode;
		 
	}
	 
public int sendSingleSMS(String username, String password ,  String senderId, String mobileNumber,String message ,String secureKey){
		
		
		String responseString = "";
		SSLSocketFactory sf=null;
		SSLContext context=null;
		String encryptedPassword;
		 int responsecode=0;
		try {
			//context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
			context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			Scheme scheme=new Scheme("https",443,sf);
			HttpClient client=new DefaultHttpClient();
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			HttpPost post=new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
			encryptedPassword  = MD5(password);
			String genratedhashKey = hashGenerator(username, senderId, message, secureKey);
			List nameValuePairs=new ArrayList(1);
			nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
			nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
			nameValuePairs.add(new BasicNameValuePair("content", message));
			nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
			nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse  response=client.execute(post);
			//System.out.println("Status "+response.getStatusLine().getStatusCode());
			
			  responsecode = response.getStatusLine().getStatusCode();
			BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line="";
			
			while((line=bf.readLine())!=null){
				
				//System.out.println("----- "+responseString+" line "+line.);
				responseString = responseString+line;
				
			}
			 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responsecode;
	}

	 private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  
		{ 
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1");
			byte[] md5 = new byte[64];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			md5 = md.digest();
			return convertedToHex(md5);
		}
	 private static String convertedToHex(byte[] data) 
		{ 
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < data.length; i++) 
			{ 
				int halfOfByte = (data[i] >>> 4) & 0x0F;
				int twoHalfBytes = 0;

				do 
				{ 
					if ((0 <= halfOfByte) && (halfOfByte <= 9)) 
					{
						buf.append( (char) ('0' + halfOfByte) );
					}

					else 
					{
						buf.append( (char) ('a' + (halfOfByte - 10)) );
					}

					halfOfByte = data[i] & 0x0F;

				} while(twoHalfBytes++ < 1);
			} 
			return buf.toString();
		}
	 
	 
	 protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
			// TODO Auto-generated method stub
			StringBuffer finalString=new StringBuffer();
			finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
			//		logger.info("Parameters for SHA-512 : "+finalString);
			String hashGen=finalString.toString();
			StringBuffer sb = null;
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-512");
				md.update(hashGen.getBytes());
				byte byteData[] = md.digest();
				//convert the byte to hex format method 1
				sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb.toString();
		}
			// Method for sending single SMS.
 
			
			// method for sending bulk SMS
	 						
	 public int sendBulkSMS(String username, String password ,  String senderId, String mobileNumber,String message , String secureKey){
			
			String responseString = "";
			SSLSocketFactory sf=null;
			SSLContext context=null;
			String encryptedPassword;
			int  responsecode = 0;
			try {
				//context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
				context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
				context.init(null, null, null);
				sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
				Scheme scheme=new Scheme("https",443,sf);
				HttpClient client=new DefaultHttpClient();
				client.getConnectionManager().getSchemeRegistry().register(scheme);
				HttpPost post=new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
				encryptedPassword  = MD5(password);
				String genratedhashKey = hashGenerator(username, senderId, message, secureKey);
				List nameValuePairs=new ArrayList(1);
				nameValuePairs.add(new BasicNameValuePair("bulkmobno", mobileNumber));
				nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
				nameValuePairs.add(new BasicNameValuePair("content", message));
				nameValuePairs.add(new BasicNameValuePair("smsservicetype", "bulkmsg"));
				nameValuePairs.add(new BasicNameValuePair("username", username));
				nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
				nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response=client.execute(post);
				 responsecode = response.getStatusLine().getStatusCode();
				BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line="";
				while((line=bf.readLine())!=null){
					responseString = responseString+line;
					
				}
			 
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  responsecode ;		
		}
			
	 public int sendUnicodeSMS(String username, String password , String message , String senderId, String mobileNumber,String secureKey){
			

			String finalmessage = "";
			for(int i = 0 ; i< message.length();i++){

				char ch = message.charAt(i);
				int j = (int) ch;
				String sss = "&#"+j+";";
				finalmessage = finalmessage+sss;
			}
		
			
			
			String responseString = "";
			SSLSocketFactory sf=null;
			SSLContext context=null;
			String encryptedPassword;
			int responsecode = 0;
			try {
				//context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
				context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
				context.init(null, null, null);
				sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
				Scheme scheme=new Scheme("https",443,sf);
				HttpClient client=new DefaultHttpClient();
				client.getConnectionManager().getSchemeRegistry().register(scheme);
				HttpPost post=new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequest");
				encryptedPassword  = MD5(password);
				String genratedhashKey = hashGenerator(username, senderId, finalmessage, secureKey);
				List nameValuePairs=new ArrayList(1);
				nameValuePairs.add(new BasicNameValuePair("bulkmobno", mobileNumber));
				nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
				nameValuePairs.add(new BasicNameValuePair("content", finalmessage));
				nameValuePairs.add(new BasicNameValuePair("smsservicetype", "unicodemsg"));
				nameValuePairs.add(new BasicNameValuePair("username", username));
				nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
				nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response=client.execute(post);
				 responsecode = response.getStatusLine().getStatusCode();
				BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line="";
				while((line=bf.readLine())!=null){
					responseString = responseString+line;
					
				}
			 
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  responsecode ;
		}
		
			
public static HttpURLConnection sendBulkUnicodeSMS(String username,
					String password, String senderId, String mobileNos, String message) {
			try {
				
				System.out.println(message);
				String finalmessage = "";
				String sss = "";
				char ch = 0;
			
			for(int i = 0 ; i < message.length();i++){
				
				 ch = message.charAt(i);
				int j = (int) ch;
			//	System.out.println("iiii::"+j);
				
				sss = "&#"+j+";";
				finalmessage = finalmessage+sss;
			}
			System.out.println("ddd"+finalmessage);
			
			message=finalmessage;
			System.out.println("unicoded message=="+message);
				
					String smsservicetype = "unicodemsg"; // For bulk msg
					String query = "username=" + URLEncoder.encode(username)
						+ "&password=" + URLEncoder.encode(password)
						+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
						+ "&content=" + URLEncoder.encode(message) 
			+ "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8") 
			+ "&senderid=" + URLEncoder.encode(senderid);
			
					connection.setRequestProperty("Content-length", String
						.valueOf(query.length()));
					connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
					connection.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
			
					// open up the output stream of the connection
					DataOutputStream output = new DataOutputStream(connection
						.getOutputStream());
			
					// write out the data
					int queryLength = query.length();
					output.writeBytes(query);
					// output.close();
			
					System.out.println("Resp Code:" + connection.getResponseCode());
					System.out.println("Resp Message:" + connection.getResponseMessage());
			
					// get ready to read the response from the cgi script
					DataInputStream input = new DataInputStream(connection
						.getInputStream());
			
					// read in each character until end-of-stream is detected
					for (int c = input.read(); c != -1; c = input.read())
						System.out.print((char) c);
					input.close();
					} catch (Exception e) {
						System.out.println("Something bad just happened.");
						System.out.println(e);
						e.printStackTrace();
					}
					return connection;
				}

	 public char[] OTP(int len) 
	    { 
	        // Using numeric values 
	        String numbers = "0123456789"; 
	  
	        // Using random method 
	        Random rndm_method = new Random(); 
	  
	        char[] otp = new char[len]; 
	  
	        for (int i = 0; i < len; i++) 
	        { 
	            // Use of charAt() method : to get character value 
	            // Use of nextInt() as it is scanning the value as int 
	            otp[i] = 
	             numbers.charAt(rndm_method.nextInt(numbers.length())); 
	        } 
	        return otp; 
	    } 
public static void main(String[] args) {
	SMSHttpPostClient ss=new SMSHttpPostClient();
			 ss.SendSMSmain("9014953536", "hai");
	  String username = "CDMATELANGANA";
	   String password = "india@999";
	   String senderid = "TSCDMA";
	 
	 String  message="tesing with has";
	 
	 String secureKey="65ff1daa-95e8-49b5-a954-1a41a9bfabb3";
//ss.sendSingleSMS(username, password, message, senderid, "9014953536", secureKey);

}
			}
				

			*/