package com.mars.cdma.gov.helper;

 

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mars.cdma.gov.utils.Constants;


/**
 * @author sunith
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DbUtil {

	public DbUtil() {
	}
		public static final int RWWARD_CODE =0;
		public static final int  ZONE_CODE = 1;
		public static final int BLOCK_CODE =2;

		public static final String RWWARD_COL="I_RWNO";
		public static final String ZONE_COL="I_ZONENO";
		public static final String BLOCK_COL="I_BLCKNO";
		
		public static Connection getConnection1()
		throws IOException, SQLException, NamingException {
		
		try {
			
			//UlbIDBean.ulbID;
			//System.out.println("UlbId is : "+UlbIDBean.ulbID);
			Properties properties = new Properties();
        	properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
			properties.setProperty(Context.PROVIDER_URL,"iiop:// localhost:2809");
					
			InitialContext initialcontext = new InitialContext(properties);
			//javax.sql.DataSource ds = (DataSource)initialcontext.lookup(MASProperties.getProperty("jdbc/EMASDB"));
			javax.sql.DataSource ds = (DataSource)initialcontext.lookup("jdbc/EMASDB");
		    //System.out.println ("DataSource is:  "+ds);
 			Connection con = ds.getConnection();
			//System.out.println("After connection from datasource"+con);
			return con;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("the error of server is *******" + ex.getMessage());
		}
		return null;
	}

	 
	public static Connection getConnection()
		throws IOException, SQLException, NamingException {
			
		//	Telangana Districts
		

		//Adilabad

			  //String URL="jdbc:db2://10.3.3.61:50000/EMASDB";String Pwd="vtD8S3MUdR";

	//	khammam

			  //String URL="jdbc:db2://10.3.3.65:50000/EMASDB";String Pwd="Ve7zaDN8JL";
		
	//Nizamabad

			  //String URL="jdbc:db2://10.3.3.68:50000/EMASDB";String Pwd=niz@ts2015;
	  
	//testddc-106

			  //String URL="jdbc:db2://10.2.2.106:50000/EMASDB";String Pwd="test@123";
	
	//testdb-226

		  //String URL="jdbc:db2://10.2.2.226:50000/EMASDB";String Pwd="db2inst2@cdma";
	
	//mahaboobnagar

			  //String URL="jdbc:db2://10.3.3.66:50000/EMASDB";String Pwd="6JF4XxBPnH";

	//Warangal

			 // String URL="jdbc:db2://10.3.3.70:50000/EMASDB";String Pwd="y9F5cxfzJ9";

	// Nalgonda

		   //String URL="jdbc:db2://10.3.3.62:50000/EMASDB";String Pwd="N6hCSmJhLd";
		   
	//Medak

			  //String URL="jdbc:db2://10.3.3.67:50000/EMASDB";String Pwd="Cv2GjWJReW";
		
	//Karimnagar

		  String URL="jdbc:db2://10.3.3.64:50000/EMASDB";String Pwd="8ShRx37ap3";

	//Ranga Reddy

			//String URL="jdbc:db2://10.3.3.69:50000/EMASDB";String Pwd="3w7R4xrKp9";

		try {  
			Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
			System.out.println("the driver loaded");
		 
			//Connection connection = DriverManager.getConnection(URL,"db2inst1","db2inst2@cdma");
			Connection connection = DriverManager.getConnection(URL,"db2inst1",Pwd);
			System.out.println("Connetion getting from live server from getConnection() :"+URL);
			return connection;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
				"the error of server is *******" + ex.getMessage());
		}
		return null;
	}
		
	public static Connection getConnection(int i)
			throws IOException, SQLException, NamingException {
			String URL=null;
			String Pwd=null;
				//Telangana Districts
			
			//1':'Adilabad','2':'Karimnagar','3':'Khammam','4':'Mahabubnagar','5':'Medak','6':'Nalgonda','7':'Nizamabad','8':'Ranga Reddy','9':'Warangal','10':test Database
			//AdilabadvtD8S3MUdR
		
			if( i==1){
				//Adilabad
		       URL="jdbc:db2://10.3.3.61:50000/EMASDB";  Pwd="vtD8S3MUdR";
			}else if(i==2){	
				//Karimnagar
				  URL="jdbc:db2://10.2.2.106:50000/EMASDB";  Pwd="test@123";
			}else if(i==3){
				//khammam
			  URL="jdbc:db2://10.3.3.65:50000/EMASDB";  Pwd="Ve7zaDN8JL";
			}else if(i==4){
				//mahaboobnagar
				  URL="jdbc:db2://10.3.3.66:50000/EMASDB";  Pwd="6JF4XxBPnH";
			}else if(i==5){
				//Medak
				  URL="jdbc:db2://10.3.3.67:50000/EMASDB";  Pwd="Cv2GjWJReW";
			}else if(i==6){
				//Nalgonda
				   URL="jdbc:db2://10.3.3.62:50000/EMASDB";  Pwd="N6hCSmJhLd";
			}else if(i==7){
				//Nizamabad
			  URL="jdbc:db2://10.3.3.68:50000/EMASDB";  Pwd="NkbE7mwZEn";		 
			}else if(i==8){
				//	 Ranga Reddy
				  URL="jdbc:db2://10.3.3.69:50000/EMASDB";  Pwd="3w7R4xrKp9";
			}else if(i==9){
				//Warangal
				  URL="jdbc:db2://10.3.3.70:50000/EMASDB";  Pwd="y9F5cxfzJ9";
			}/*else if(i==10){
				 //testddc-102
				  URL="jdbc:db2://10.2.2.106:50000/EMASDB";  Pwd="test@123";
			}else if(i==11){
				//testdb-226
			  // URL="jdbc:db2://10.2.2.226:50000/EMASDB";  Pwd="db2inst1";
			}
	 */
			
			try {  
				Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
				//System.out.println("the driver loaded");
				//Connection connection = DriverManager.getConnection(URL,"db2inst1","db2inst2@cdma");
				Connection connection = DriverManager.getConnection(URL,"db2inst1",Pwd);
				//System.out.println("Connetion getting from live server from getConnection() :"+URL);
			//	System.out.println("connection is"+connection);
				return connection;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println(
					"the error of server is *******" + ex.getMessage());
			}
			return null;
		}
		
	
	public static void main(String args[]) throws Exception {
		
		/* String inputdate="01/01/1900";  
	   // Date date=Date.valueOf(str);//converting string into sql date  
	    //System.out.println(date);
		 SimpleDateFormat dformat=new SimpleDateFormat("dd/MM/yyyy");
			Date date1=null;
			SimpleDateFormat formatter1=null;
		   System.out.println(new SimpleDateFormat("MM/dd/yyyy").parse("08/16/2011"));
		   formatter1=new SimpleDateFormat("dd/MM/yyyy");
	       date1=(Date) formatter1.parse(inputdate);
	    	System.out.println(date1);
	    	 dformat.format(date1);*/
    	//String 	outputdate=dformat.format(date1);
    	//System.out.println(">> "+outputdate);
		DateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(dformat.format(new java.util.Date()));
		
		
	}
 
		 
	
}