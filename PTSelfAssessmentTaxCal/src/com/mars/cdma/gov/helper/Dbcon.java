package com.mars.cdma.gov.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;


public class Dbcon {
	public static Connection getdb(String ulbcode)   {
		 
		 String districtname=getULBName(ulbcode);
		 Connection con=null;
		// System.out.println("In DB ULB_CODE is "+ulbcode);
		 //System.out.println("In DB ULB_NAME is "+districtname);
		 
		 ResourceBundle rb = ResourceBundle.getBundle("DISTDBNames");
			Enumeration <String> keys = rb.getKeys();
			 //Enumeration<String>keys1=rb1.getKeys();
			  
			 String strUrl=null;
			 String strPassword=null;
		/*while (keys.hasMoreElements()) {
				
				String key = keys.nextElement();
				String value = rb.getString(key);
				 
					if(key.equals(districtname)){
					String Connstr=value;
					strUrl = Connstr.substring(0, Connstr.indexOf("#"));
					System.out.println(" strUrl is "+strUrl);
					strPassword = Connstr.substring(Connstr.lastIndexOf("#")+1);
				//	System.out.println("Password  Name "+strPassword);
				} 	
			}*/
			 /***********  Enable For Test Connection ************/
            strUrl="jdbc:db2://10.3.3.64:50000/EMASDB";// strUrl="jdbc:db2://125.16.9.163:50000/EMASDB";
		    strPassword="8ShRx37ap3"; //strPassword=" db2inst1";

				
			try {  
				Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
			//	System.out.println("the driver loaded");
			 
				//Connection connection = DriverManager.getConnection(URL,"db2inst1","db2inst2@cdma");
				  con = DriverManager.getConnection(strUrl,"db2inst1",strPassword);
				System.out.println("Connetion getting from live server from getConnection() :"+strUrl);
				//System.out.println("Connection isd "+con);
				//return connection;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("the error of server is *******" + ex.getMessage());
			}
		 
		 
		return con;	
		 
	}
	 public static String getULBName(String ulbcode) {
			ResourceBundle rb = ResourceBundle.getBundle("ULBNames");
			Enumeration <String> keys = rb.getKeys();
			while (keys.hasMoreElements()) {
				
				String key = keys.nextElement();
				String value = rb.getString(key);
					if(key.equals(ulbcode)){
					//System.out.println("sri return ULB_NAME >>>>>>>>> "+value);
					ulbcode=value;
				} 	
			}
			return ulbcode;
		}
	 
	 
 public static Connection getdbfromdist(String DistName){
		 
		 String ULB_Name=getULBName(DistName);
		 Connection con=null;
		 //System.out.println("In DB ULB_CODE is "+DistName);
		 
		 ResourceBundle rb = ResourceBundle.getBundle("DISTDBNames");
			Enumeration <String> keys = rb.getKeys();

				 
			 String strUrl=null;
			 String strPassword=null;
			 System.out.println("ULB Code0 "+ULB_Name);
		 
		/*while (keys.hasMoreElements()) {				
				String key = keys.nextElement();
				String value = rb.getString(key);
				System.out.println("");
					if(key.equals(ULB_Name)){
					String Connstr=value;
					strUrl = Connstr.substring(0, Connstr.indexOf("#"));
					strPassword = Connstr.substring(Connstr.lastIndexOf("#")+1);					
				} 				 	
		}*/
			/***********  Enable For Test Connection ************/
	  strUrl="jdbc:db2://10.3.3.64:50000/EMASDB";// strUrl="jdbc:db2://125.16.9.163:50000/EMASDB";
			 strPassword="8ShRx37ap3"; //strPassword=" db2inst1"; 
			 
			try {  
				Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
				//System.out.println("the driver loaded"+strUrl);
			 
				//Connection connection = DriverManager.getConnection(URL,"db2inst1","db2inst2@cdma");
				  con = DriverManager.getConnection(strUrl,"db2inst1",strPassword);
				System.out.println("Connetion getting from live server from getConnection() :"+strUrl);
				//System.out.println("Connection isd "+con);
				//return connection;
			} catch (Exception ex) {
				
				ex.printStackTrace();
				System.out.println(
					"the error of server is *******" + ex.getMessage());
			 
			}
		 
		 
		return con;	
		 
	}
	 
 
 
 public static Connection getmysqldb()   {
	 Connection con=null;
	 try {
		
		 Class.forName("com.mysql.jdbc.Driver");     //10.166.7.166
		 con=DriverManager.getConnection("jdbc:mysql://localhost/sysibm","root","root");
		 System.out.println("con is"+con);
		 
	} catch (Exception e) {
		System.out.println("error in db");
		e.printStackTrace();
		// TODO: handle exception
	}
	
	 
	return con;
	
}
 
 
	 
	 public static String get_yyyy_mm_ddformat(String inputdate) throws ParseException {
		 
		 SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");;
		  
		// inputdate = "01-22-1980";
		 String outputdate=null;
			// Date.parse(input);
			//System.out.println("DD "+Date.parse(input));
			Date date1=null;
			SimpleDateFormat formatter1=null;
			//SimpleDateFormat formatter1=new SimpleDateFormat("dd-mm-yyyy");  
		     
			 try {
				
		 if (inputdate.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) // for yyyy/MM/dd format
	        {
			 System.out.println("1");
	    	formatter1=new SimpleDateFormat("yyyy/MM/dd");
	    	date1=formatter1.parse(inputdate);  
	    	outputdate=dformat.format(date1);
	        }
	    else if(inputdate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
	    	 System.out.println("2");
	    	formatter1=new SimpleDateFormat("dd/MM/yyyy");
	    	date1=formatter1.parse(inputdate);  
	    	outputdate=dformat.format(date1);
	    }
	    else if(inputdate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
	    	 System.out.println("3");
	    	formatter1=new SimpleDateFormat("yyyy-mm-dd");
	    	date1=formatter1.parse(inputdate);
	    	outputdate=dformat.format(date1);
	    }
	    else if(inputdate.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")){
	    	 System.out.println("4");
	    	formatter1=new SimpleDateFormat("mm-dd-yyyy");
	    	date1=formatter1.parse(inputdate);  
	    	outputdate=dformat.format(date1);
	    	
	    }

				} catch (Exception e) {
				e.printStackTrace();
				}
		
		    System.out.println(" Out put Format "+outputdate);
		    return outputdate;
	}
	 
		public Boolean Nodatafoundcheck(ResultSet rs) throws SQLException {
			if (!rs.isBeforeFirst()==true){    
		           return false;
					}
		 return true;
	}
	 public static void main(String[] args) throws ParseException {
		 get_yyyy_mm_ddformat("01/01/1900");
	}
	 
	 
	
}
