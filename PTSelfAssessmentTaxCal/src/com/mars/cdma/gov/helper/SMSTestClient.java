package com.mars.cdma.gov.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class SMSTestClient {
	public static void main(String[] args) throws IOException{
	 String username ="tscdma";
	 String password = "SMS@1234";
	 String key="35EE3933F9191B";
	 String campaign="113";
	 String routeid="6";
	 String type="text";
	 String contacts="9700530854";
	 String senderid="VESIPL";
	 String msg="Rahul Kumar";
	
	String requestUrl  = "https://manage.smssolutions.in/smsapi/index?&" +
            "username=" + URLEncoder.encode(username, "UTF-8") +
            "&password=" + URLEncoder.encode(password, "UTF-8") +
            "&key=" + URLEncoder.encode(key, "UTF-8") +
            "&campaign=" + URLEncoder.encode(campaign, "UTF-8") +
            "&routeid=" + URLEncoder.encode(routeid, "UTF-8") +
            "&type="+ URLEncoder.encode(type, "UTF-8") +
            "&contacts="+URLEncoder.encode(contacts, "UTF-8")  +
            "&senderid="+URLEncoder.encode(senderid, "UTF-8")  +
            "&msg=" + URLEncoder.encode(msg, "UTF-8");
	 URL url = new URL(requestUrl);
     HttpURLConnection uc = (HttpURLConnection)url.openConnection();
     System.out.println("requestUrl::::"+requestUrl);
     System.out.println(uc.getResponseMessage());

     uc.disconnect();
	
}
}	
  