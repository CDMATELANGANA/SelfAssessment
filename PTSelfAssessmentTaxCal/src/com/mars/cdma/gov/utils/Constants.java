package com.mars.cdma.gov.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.velocity.tools.config.Data;

public class Constants {

	public static final Integer STATUS_YES = new Integer(1);

	public static final Integer STATUS_NO = new Integer(2);

	public static final String STATUS_YES_LABEL = "Yes";

	public static final String STATUS_NO_LABEL = "No";

	public static final String BOOLEAN_TRUE_LABEL = "True";

	public static final String BOOLEAN_FALSE_LABEL = "False";

	public static final String COLLECTION_TYPE_CREDIT_CARD_LABEL = "Credit Card";

	public static final String COLLECTION_TYPE_PAYPAL_LABEL = "PayPal";

	public static final Integer DEFAULT_ROWS_PER_PAGE = new Integer(12);

	public static final Integer DEFAULT_PAGINATION_ALL_ROWS = new Integer(-1);

	public static final String DATE_FORMAT = "dd-MMM-yyyy";
	public static final String NEW_DATE_FORMAT = "yyyy-MM-dd";
	public static final String NEW_DATE_FORMAT_DB2 = "dd-MM-yyyy";
	
	public static final String DATE_TIME_FORMAT="MM/dd/yyyy hh:mm:ss a";

	public static final String DATE_LABEL_FORMAT_WITH_TIME = "dd-MMM-yyyy hh:mm a z";

	public static final String SESSION_TOKEN_KEY = "_synchronizerToken";

	public static final String CURRENCY_FORMAT = "0.00";
	
	public static final String CDMA_CURRENCY_FORMAT = "0";

	public static final String DISTANCE_FORMAT = "0.000";

	public static final Integer TOKEN_UNSED = 0;

	public static final Integer TOKEN_USED = 1;

	public static final Integer YES = 1;

	public static final Integer NO = 0;

	public static final Integer USERGROUP_END_USER = 3;

	public static final Integer INACTIVE = 0;

	public static final Integer ACTIVE = 1;

	public static final Integer CANCEL = 2;

	public static final Integer CANCEL_PENDING = 3;

	public static final Integer DELETE = 4;

	public static final Integer NOT_USED = 5;

	public static final Integer TRANSACTION_FAILED =6;

	public static final String INACTIVE_LABEL = "InActive";

	public static final String ACTIVE_LABEL = "Active";

	public static final String CANCEL_LABEL = "Cancelled";

	public static final String CANCEL_PENDING_LABEL = "Cancel Pending";

	public static final String NOT_USED_LABEL = "NOT_USED";

	public static final String TRANSACTION_FAILED_LABEL ="Transaction Failed";

	public static final String DELETE_LABEL = "Deleted";

	public static final Integer SEX_MALE = new Integer(0);

	public static final Integer SEX_FEMALE = new Integer(1);

	public static final String SEX_MALE_LABEL = "Male";

	public static final String SEX_FEMALE_LABEL = "Female";

	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";

	public static final String CONTENT_TYPE_PDF = "application/pdf";

	public static final String CONTENT_TYPE_WORD = "application/msword";

	public static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";

	public static final String CONTENT_TYPE_CSV = "text/csv";

	public static final String REPORT_TITLE2 = "CDMA";

	public static final String REPORT_TITLE1 = "Telangana";

	public static final Integer TEXTAREA_SIZE = new Integer(255);

	public static final Integer MAX_DAYS_YEAR = new Integer(366);

	public static final Integer UPLOAD_FILE_NAME_LENGTH = new Integer(7);

	public static final String UPLOAD_ROOT = "/uploads";

	public static final String UPLOAD_IMAGE_ROOT = "images/";

	public static final String UPLOAD_DOCUMENT_ROOT = "documents/";

	public static final Integer ADMIN = new Integer(1);

	public static final String ADMIN_LABEL = "Admin";

	public static final Integer TIMEOUT_MINUTES = new Integer(15);

	public static final int DEFAULT_ACTIVE = 1;
	public static final int FORUM_TYPE = 1;
	public static final int DELETED_FLAG_VALUE = -9;

	public static final String USR_FORUM_SCREEN_NAME ="usrForumScreen";

    public static final Integer WEBSERVICE_RESPONSECODE_FAILURE = 0;
	public static final Integer WEBSERVICE_RESPONSECODE_SUCCESS = 1;

	public static final String AES_KEY = "0123456789abcdef";
	public static final String IV = "AAAAAAAAAAAAAAAA";

	public static final String ACTIVATION_TYPE_ONLINE = "ONLINE";
	public static final String ACTIVATION_TYPE_OFFLINE = "OFFLINE";
	public static final String ACTIVATION_TYPE_PENDING = "PENDING";

	public static final String BANDWIDTH_UNITS = "kbps";

	public static final Integer INVOICE_BEFORE_DAYS=7;
	public static final Integer PG_RESPONSE_SUCCESS = 1;
	public static final int PRORATA_BILLING_POLICY = 0;
	public static final int FREE_BILLING_POLICY = 1;
	
	public static final String LOOKUP_TYPE_AGEGROUP = "AGE_GROUP";
	public static final String LOOKUP_TYPE_TIME_DURATION = "TIME DURATION";
	public static final String LOOKUP_TYPE_GENDER = "GENDER";
	public static final String LOOKUP_TYPE_EDUCATION = "EDUCATION";
	public static final String LOOKUP_TYPE_APPOINTED_AS = "APPOINTED_AS";
	public static final String LOOKUP_TYPE_ULB_NAME = "ULB_NAME";
	public static final String LOOKUP_TYPE_SECTION = "SECTION";
	public static final String LOOKUP_TYPE_Adminstarion = "Adminstarion";
	public static final String LOOKUP_TYPE_Accounts = "Accounts";
	public static final String LOOKUP_TYPE_Revenue = "Revenue";
	public static final String LOOKUP_TYPE_Statistical = "Statistical";
	public static final String LOOKUP_TYPE_Sanitation = "Sanitation";
	public static final String LOOKUP_TYPE_Engineering = "Engineering";
	public static final String LOOKUP_TYPE_MepmaUDA = "Mepma/UDA";
	public static final String LOOKUP_TYPE_TownPlanning = "Town Planning";
	public static final String LOOKUP_TYPE_TRAINING_ON = "TRAINING_ON";
	public static final String LOOKUP_TYPE_TRAINING_ON_Admin = "TRAINING_ON_Administration";
	public static final String LOOKUP_TYPE_TRAINING_ON_Eng = "TRAINING_ON_Engineering";
	public static final String LOOKUP_TYPE_TRAINING_ON_Eng_op = "TRAINING_ON_Engineering and Operators";
	public static final String LOOKUP_TYPE_TRAINING_ON_Eng_Health = "TRAINING_ON_Engineering or Health";
	public static final String LOOKUP_TYPE_TRAINING_ON_Fin_Rev = "TRAINING_ON_Finance or Revenue";
	public static final String LOOKUP_TYPE_TRAINING_ON_HOD = "TRAINING_ON_HOD ";
	public static final String LOOKUP_TYPE_TRAINING_ON_IT = "TRAINING_ON_IT";
	public static final String LOOKUP_TYPE_TRAINING_ON_MEPMA_UPA = "TRAINING_ON_MEPMA or UPA";
	public static final String LOOKUP_TYPE_TRAINING_ON_PLANING = "TRAINING_ON_PLANING";
	public static final String LOOKUP_TYPE_TRAINING_ON_Urban_Gov = "TRAINING_ON_Urban Governance";
	public static final String LOOKUP_TYPE_TRAINING_ON_PLANING_Urban_Gov = "TRAINING_ON_Planning or Urban Governance";
	public static final String LOOKUP_TYPE_GET_ALL_GROUP_TRAINING = "LOOKUP_TYPE LIKE 'TRAINING_ON_%'";
	
	public static final int CORPORATION_GRADE_CODE = 1;
	public static final int MUNICIPALITEIS_GRADE_CODE =2;
	public static final int NAGARA_PANCHAYATHI_GRADE_CODE =3;
	
	public static Map<Character, String> getCollectionType(){
		Map<Character, String> map =new HashMap<Character, String>();
		map.put('B',"Bill Collector");
		map.put('D',"Municipal Counter");
		map.put('E',"E - Seva");
		map.put('G'," G ");
		map.put('H',"HandHeld Machine");
		return map;
		
	}
	public static String getCollectionName(String collectionKey){
		String collectionVal=null;
		switch (collectionKey) {
		case "B":
			collectionVal="Bill Collector";	
			break;
		case "D":
			collectionVal="Municipal Counter";	
			break;
		case "E":
			collectionVal="E - Seva";	
			break;	
		case "G":
			collectionVal="G";	
			break;
		case "H":
			collectionVal="HandHeld Machine";	
			break;
		
		}
		return collectionVal;
		
	}
	public static String getPayMode(String payModeKey){
		String payModeVal=null;
		switch (payModeKey) {
		case "C":
			payModeVal="Cash";	
			break;
		case "D":
			payModeVal="Demand Draft";	
			break;
		case "I":
			payModeVal="Credit Card/Debit Card";	
			break;	
		case "M":
			payModeVal="Money Order";	
			break;
		case "P":
			payModeVal="Postal Order";	
			break;
		case "Q":
			payModeVal="Cheque";	
			break;
		}
		return payModeVal;
		
	}
	public static Map<Character, String> getFlagType(){
		Map<Character, String> map =new HashMap<Character, String>();
		map.put('N',"Court Case");
		map.put('D',"Demolition");
		map.put('E',"Duplicate Assessment");
		map.put('C',"Central Govt. Building");
		map.put('S',"State Govt. Building");
		return map; 
		
	}
	public static Map<String, String> getAsmntFlag(){
		Map<String, String> map =new HashMap<String, String>();
		map.put("N","Court Case");
		map.put("D","Demolition");
		map.put("E","Duplicate Assessment");
		map.put("C","Central Govt. Building");
		map.put("S","State Govt. Building");
		return map; 
		
	}
	public static Map<Integer, String> getNewULBs(){
		Map<Integer, String> districtmap =new HashMap<Integer, String>();
		districtmap.put(2,"KARIMNAGAR");
		districtmap.put(3,"KHAMMAM");
		districtmap.put(9,"MAHABUBNAGAR");
		districtmap.put(8,"MEDAK");
		districtmap.put(7,"NALGONDA");
		districtmap.put(5,"RANGA REDDY");
		districtmap.put(1,"WARANGAL");
		return districtmap;
	}
	
	public static Map<String, List<String>> getHHM(){
		Map<String, List<String>> HHmap =new HashMap<String, List<String>>();
		HHmap.put("ADILABAD",getADILABAD_ULBs());
		HHmap.put("KHAMMAM",getKHAMMAM_ULBs());
		HHmap.put("KARIMNAGAR",getKARIMNAGAR_ULBs());
		HHmap.put("RANGA REDDY",getRANGAREDDY_ULBs());
		HHmap.put("NIZAMABAD",getNIZAMABAD_ULBs());
		HHmap.put("NALGONDA",getNALGONDA_ULBs());
		HHmap.put("MEDAK",getMEDAK_ULBs());
		HHmap.put("MAHABUBNAGAR",getMAHABUBNAGAR_ULBs());
		
		return HHmap;
	}
	
	public static List<String> getMAHABUBNAGAR_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1039+"#Wanaparthy");
		hhmULBList.add(1036+"#Gadwal");
		
		return hhmULBList;
	}
	
	public static List<String> getMEDAK_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1111+"#Sadasivapet");
		hhmULBList.add(1112+"#Sangareddy");
		hhmULBList.add(1113+"#Siddipet");
		hhmULBList.add(1114+"#Zaheerabad");
		return hhmULBList;
	}
	
	public static List<String> getNIZAMABAD_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1044+"#Bodhan");
		hhmULBList.add(1045+"#Kamareddy");
		hhmULBList.add(1046+"#Nizamabad");
		hhmULBList.add(1126+"#Armur");
		return hhmULBList;
	}
	
	public static List<String> getRANGAREDDY_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1056+"#Tandur");
		return hhmULBList;
	}
	public static List<String> getKARIMNAGAR_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1101+"#Jagityal");
		hhmULBList.add(1102+"#Karimnagar");
		hhmULBList.add(1104+"#Ramagundam");
		hhmULBList.add(1105+"#Sircilla");
		return hhmULBList;
	}
	public static List<String> getADILABAD_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1094+"#Adilabad");
		hhmULBList.add(1095+"#Bellampally");
		hhmULBList.add(1096+"#Bhainsa");
		hhmULBList.add(1097+"#Kagaznagar");
		hhmULBList.add(1099+"#Manchiryal");
		hhmULBList.add(1100+"#Nirmal");
		return hhmULBList;
	}
	
	public static List<String> getKHAMMAM_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1106+"#Khammam");
		hhmULBList.add(1108+"#Palvancha");
		
		return hhmULBList;
	}
	public static List<String> getNALGONDA_ULBs(){
		List<String> hhmULBList=new ArrayList<String>();
		hhmULBList.add(1040+"#Bhongir");
		hhmULBList.add(1041+"#Miryalaguda");
		hhmULBList.add(1042+"#Nalgonda");
		hhmULBList.add(1043+"#Suryapet");
		return hhmULBList;
	}
	
	
	
	 public static String getDB2Yesterday(String todaydate){
		 java.util.Date yesterday=null;
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    GregorianCalendar gc = new GregorianCalendar();
		    java.util.Date d;
			try {
				d = sdf.parse(todaydate);
				gc.setTime(d);
			    int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
			    gc.roll(Calendar.DAY_OF_YEAR, -1);
			    int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
			    if(dayAfter > dayBefore) {
			        gc.roll(Calendar.YEAR, -1);
			    }
			    gc.get(Calendar.DATE);
			    yesterday = gc.getTime();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sdf.format(yesterday);    
		  
	 }
	 
	 
	 
	 public static String getDB2Today(){
		 String pattern = "MM/dd/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
		 return format.format(new Date());  
		  
	 }
	 public static String getDB2defaultdate(){
		 String pattern = "MM/dd/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
		 return format.format("01/01/1900");  
		  
	 }
	 public static String getToday_DMY(){
		 String pattern = "dd-MM-yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
		 return format.format(new Date());  
		  
	 }
	 
	 public static String getcurrentdate(){
	 DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
     Date dateobj = new Date();
     System.out.println(df.format(dateobj));
	return df.format(dateobj);
	 }
	 
	 
	 public static String getTodaydate(){
		 String pattern = "dd-MM-yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
		 return format.format(new Date());  
		  
	 }
	 
	 public static String getdefaultdate() throws ParseException{
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  System.out.println("ds"+dateFormat.format(dateFormat.parse("01/01/1990")));
		 return dateFormat.format(dateFormat.parse("01/01/1990"));  
		  
	 }
	 
	 public static String getcurrentdateddmmyyy(){
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	     Date dateobj = new Date();
	     System.out.println(df.format(dateobj));
		return df.format(dateobj);
		 }
	 public static String getdd_mm_yyyy(){
		 
		 SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
 	  
 	 return formatter1.format(new Date());
	 }
	 
public static String getmm_dd_yyyy(){
		 
		 SimpleDateFormat formatter1=new SimpleDateFormat("MM/dd/yyyy");
 	  
 	 return formatter1.format(new Date());
	 }
	 public static String getToday_YMD(){
		 String pattern = "yyyy-MM-dd";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
		 return format.format(new Date());  
		  
	 }
	 public static String getChange_DMY(String date){
		 String pattern = "dd-MM-yyyy";
		 String ret = null;
		 SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat myFormat = new SimpleDateFormat(pattern);
		 try {
			ret= myFormat.format(fromUser.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ret;
	 }
	 public static String getYesterday_YMD(String todaydate){
		 java.util.Date yesterday=null;
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    GregorianCalendar gc = new GregorianCalendar();
		    java.util.Date d;
			try {
				d = sdf.parse(todaydate);
				gc.setTime(d);
			    int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
			    gc.roll(Calendar.DAY_OF_YEAR, -1);
			    int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
			    if(dayAfter > dayBefore) {
			        gc.roll(Calendar.YEAR, -1);
			    }
			    gc.get(Calendar.DATE);
			    yesterday = gc.getTime();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		return sdf.format(yesterday);    
		  
	 }
	 public static String geWeek_YMD(String todaydate){
		 java.util.Date yesterday=null;
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    GregorianCalendar gc = new GregorianCalendar();
		    java.util.Date d;
			try {
				d = sdf.parse(todaydate);
				gc.setTime(d);
			    int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
			    gc.roll(Calendar.WEEK_OF_MONTH,-1);
			    int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
			    if(dayAfter > dayBefore) {
			        gc.roll(Calendar.YEAR, -1);
			    }
			    gc.get(Calendar.DATE);
			    yesterday = gc.getTime();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		return sdf.format(yesterday);    
		  
	 }
	 public static String getMonth_YMD(String todaydate){
		 java.util.Date yesterday=null;
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    GregorianCalendar gc = new GregorianCalendar();
		    java.util.Date d;
			try {
				d = sdf.parse(todaydate);
				gc.setTime(d);
			    int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
			    gc.roll(Calendar.MONTH, -1);
			    int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
			    if(dayAfter > dayBefore) {
			        gc.roll(Calendar.YEAR, -1);
			    }
			    gc.get(Calendar.DATE);
			    yesterday = gc.getTime();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		return sdf.format(yesterday);    
		  
	 }
	 public static long getDuration(Date curTime, Date dmdTime, int scale) {
	        long durationInMillis = curTime.getTime() - dmdTime.getTime();
	        switch (scale) {
	            case Calendar.MINUTE:
	                return durationInMillis;
	            case Calendar.MILLISECOND:
	                return durationInMillis;
	            case Calendar.SECOND:
	                return durationInMillis;
	            case Calendar.HOUR:
	                return durationInMillis ;
	            case Calendar.DAY_OF_YEAR:
	            case Calendar.DATE:
	                return durationInMillis ;
	            case Calendar.MONTH:
	                return durationInMillis ; // 30days per month
	        }
	        throw new IllegalArgumentException("invalid scale specified");
	    }
	 public static Date ChangeString_To_Date(String setdate){
		 
		 Date date =null;
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  try {
			  date = formatter.parse(setdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  return date;
	 }
	 public static String Monthdiffirence(Date d1,Date d2){
		Calendar sDate = Calendar.getInstance();
		Calendar eDate = Calendar.getInstance();
		sDate.setTime(d1);
		eDate.setTime(d2);
		int diff =sDate.get(Calendar.MONTH) - eDate.get(Calendar.MONTH);
		return String.valueOf(diff);
		
	 }
	 public static double monthsBetween(Date d1, Date d2) {
		 final double AVERAGE_MILLIS_PER_MONTH = 365.24 * 24 * 60 * 60 * 1000 / 12;
		    return (d1.getTime() - d2.getTime()) / AVERAGE_MILLIS_PER_MONTH; 
		}
	 
	 public static String checkDiffirence(Date d1, Date d2) {
		 final double AVERAGE_MILLIS_PER_MONTH = 365.24 * 24 * 60 * 60 * 1000 / 12;
		 Double diff=(double) Math.round(((d1.getTime() - d2.getTime()) / AVERAGE_MILLIS_PER_MONTH));
		 	return diff.toString(); 
		}
	 public static String checkMonthDiffirence(Date d1,Date d2){
			Calendar sDate = Calendar.getInstance();
			Calendar eDate = Calendar.getInstance();
			sDate.setTime(d1);
			eDate.setTime(d2);
			int diff=sDate.get(Calendar.MONTH) - eDate.get(Calendar.MONTH);
			double ddiff=Math.round(Double.parseDouble(String.valueOf(diff)));
			return String.valueOf(ddiff);
			
		 }

	 public static String roundMounth(String val) {

		 String dif=val;
			
			if(dif.contains(".")){
				String[] spStrings=dif.split("\\.");
				if(Integer.parseInt(spStrings[1])>0){
					int ret=Integer.parseInt(spStrings[0])+1;
					dif=String.valueOf(ret);
				}
				else{
					dif=spStrings[0];
				}
			}
			else{
				dif=val;
			}
			
			
			return dif;
		}
	 
	 	public static String replaceDateFormat(String strDate){
	 		SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
		 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String reformattedStr="";
		 try {
		      reformattedStr = myFormat.format(fromUser.parse(strDate));
			 } catch (ParseException e) {
		     e.printStackTrace();
		 }
		 return reformattedStr;
		 }
	 	
	 	public static String replaceYYYYMMDD_To_MMDDYYYY(String strDate){
	 			 		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
	 			 		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
	 			 		String reformattedStr="";
		 try {
		      reformattedStr = myFormat.format(fromUser.parse(strDate));
			 } catch (ParseException e) {
		     e.printStackTrace();
		 }
		 return reformattedStr;
		 }
public static String replaceYYYYMMDD_To_DDMONTHYYYY(String strDate){
		 		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		 		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");
		 		String reformattedStr="";
try {
  reformattedStr = myFormat.format(fromUser.parse(strDate));
 } catch (ParseException e) {
 e.printStackTrace();
}
return reformattedStr;
}
	 	public static String replaceDateFormatDMY(String strDate){
	 		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		 String reformattedStr="";
		 try {
		      reformattedStr = myFormat.format(fromUser.parse(strDate));
			 } catch (ParseException e) {
		     e.printStackTrace();
		 }
		 return reformattedStr;
		 }
	 	public static String getDayOfWeek(String strDate){
	 		String dayOfWeek="";
			try {
				Date  date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
				 dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	 		return dayOfWeek;
		 }
	 
	 	public static String get_15Days_Back(String todaydate){
			 java.util.Date yesterday=null;
			    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			    GregorianCalendar gc = new GregorianCalendar();
			    java.util.Date d;
				try {
					d = sdf.parse(todaydate);
					gc.setTime(d);
				    int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
				    gc.roll(Calendar.DAY_OF_YEAR, -15);
				    int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
				    if(dayAfter > dayBefore) {
				        gc.roll(Calendar.YEAR, -1);
				    }
				    gc.get(Calendar.DATE);
				    yesterday = gc.getTime();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return sdf.format(yesterday);    
			  
		 }
	 	public static String remove_Character(String strDate,Character character){
	 		String removeCharacterString;
	 		removeCharacterString=strDate.replaceAll(""+character, "");
	 	return 	removeCharacterString;
	 	}
	 
	 	public static  int getCurrent_year() {
	 		int year = Calendar.getInstance().get(Calendar.YEAR);	
	 		return year;
		}
	 	public static int getCurrentMonth()
		{
			java.util.Calendar cal=java.util.Calendar.getInstance();
			return (cal.get(java.util.Calendar.MONTH)+1);
		}
	 	public static String getcurrentdate_yyy_mm_ddd()
		{
	 	DateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(dformat.format(new java.util.Date()));
		 return dformat.format(new java.util.Date());
		}
		 
		public static void main(String[] args) {
			//System.out.println("Date Chenge---"+remove_Character(getChange_DMY("2016-09-01"),'-'));
	 	 //System.out.println(">> "+getcurrentdate());
			System.out.println(">> "+getcurrentdateddmmyyy());
		}
	 	
	 	
	
}
