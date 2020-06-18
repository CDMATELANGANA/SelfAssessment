package com.mars.cdma.gov.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

public class ConsumeWebservicesDemo {
	public static void main(String[] args) {
        try {

        URL url = new URL("https://ptc.erp.direct/getTotal?tenant=peerzadiguda");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            
            while ((output = br.readLine()) != null) {
                System.out.println(output);
               
            }
            JSONObject jo = new JSONObject(); 
            
            // putting data to JSONObject 
            jo.put("ULB_NAME", "PEERZADIGUDA MUNICIPAL CORPORATION"); 
            jo.put("COMPLETED", 221); 
            jo.put("PENDING", 94); 
            jo.put("Total", 315);
            PrintWriter pw = new PrintWriter("JSONExample.json"); 
            pw.write(jo.toString()); 
            System.out.println("pw.write(jo.toString()); "+pw.toString());
            conn.disconnect();

      } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }
}


