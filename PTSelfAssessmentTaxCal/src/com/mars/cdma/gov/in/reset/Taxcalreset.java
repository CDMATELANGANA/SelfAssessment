package com.mars.cdma.gov.in.reset;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mars.cdma.gov.helper.DbUtil;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
 @Controller
@Path("Tax")
public class Taxcalreset {

@Autowired
public SessionFactory sessionFactory;

 	public int ulbid;
	public String ulbName;
	public int localityid;
	public String locality;
	public Connection con=null;
	public  String sql=null,sql1=null,sql2=null;
	public PreparedStatement ps=null,ps1=null,ps2=null;;
	public ResultSet rs=null,rs1=null,rs2=null;
	 
	static Logger logger=Logger.getLogger(Taxcalreset.class.getName());
	public Gson gson=new Gson();
	@POST
	@Path("getzone")
	@Produces("application/json")
	public String  getzone(@FormParam("ulbcode") String ulbcode) throws SQLException {
		 String JCategory = "";
		 Map CategoryMap = new HashMap<>();
		 System.out.println("ulbcode >>>>From "+ulbcode);
		 try {
			 //ulbcode="1058";
			 logger.info("Connecting >>>>>>>> " + ulbcode);
				/************ Live Data Base connectoin************/
				 String distname=Dbcon.getULBName(ulbcode);
				 con = Dbcon.getdbfromdist(distname);
				//  System.out.println("District Name from ULB code"+distname);
				/************ Test Data Base connectoin************/
				//con = Dbcon.getdbfromdist("TEST");
				/*** ********* End  Live Data Base connectoin************/
				 
				 
		 System.out.println("Connectoin is"+con);
		 String sql="select * from ct_zone_mstr_tbl where   C_DELFLAG='N' and  I_ULBOBJID="+ulbcode;
		System.out.println("sql "+sql);	
		logger.info(sql);
		 PreparedStatement	ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
		 while (rs.next()) {
			 String CategoryID = rs.getString("I_ZONEOBJID");
			 String Category = rs.getString("VC_ZONEDESC");
			// System.out.println(">>>cat  "+CategoryID);
		     CategoryMap.put(CategoryID, Category);
		     //JCategory = gson.toJson(CategoryMap);
		 }
		 //System.out.println(CategoryMap);
		 JCategory = gson.toJson(CategoryMap);
		  //System.out.println(">>. "+JCategory);
		 } catch (Exception e) {
			 logger.shutdown();
			 
				// TODO: handle exception
			}
		 logger.info(sql);
		return JCategory;
	}
	
	@POST
	@Path("gettsubcategory")
	@Produces("application/json")
	public String  getsubcategory(@FormParam("ulbcode") String ulbcode,@FormParam("subcategory") String subcategory) throws SQLException {
		 String JCategory = "";
		// ulbcode="1058";
		 Map CategoryMap = new HashMap<>();
		 System.out.println("ulbcode >>>>subcategory "+subcategory);
		
		 logger.info("Connecting >>>>>>>> " + ulbcode);
			/************ Live Data Base connectoin************/
			 String distname=Dbcon.getULBName(ulbcode);
			 con = Dbcon.getdbfromdist(distname);
			//  System.out.println("District Name from ULB code"+distname);
			/************ Test Data Base connectoin************/
			//con = Dbcon.getdbfromdist("TEST");
			/************ End  Live Data Base connectoin************/
		 
		 String sql="select i_trsubctgycode,vc_trsubctgyname from DO_TRSUBCTGY_MSTR_TBL where c_delflag='N' and i_trctgycode="+subcategory;
		 logger.info(sql);
		 PreparedStatement	ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
		 while (rs.next()) {
			 String CategoryID = rs.getString("i_trsubctgycode");
			 String Category = rs.getString("vc_trsubctgyname");
		     CategoryMap.put(CategoryID, Category);
		 }
		 //System.out.println(CategoryMap);
		 logger.info(sql);
		 JCategory = gson.toJson(CategoryMap);
		return JCategory;
	}
	
	
	
	/*@POST
	@Path("getdata")
	@Produces("application/json")
	public String getdata(@FormParam("ulb")String ulbobjid,@FormParam("Accesscode")String Accesscode,@FormParam("Applicatoinnumber")int Applicationnumber,@FormParam("localityid")int localityid) throws IOException {
		System.out.println("ULB "+ulbobjid);
		String sql=null;
		Connection con=null;
		Layout playout=new PatternLayout();
		Appender appender=null;
		
		//appender=new  FileAppender(playout,"GetTradeData.txt", true);
		//logger.addAppender(appender);
		ResourceBundle  rb= ResourceBundle.getBundle("login");
		String getpass="password";
		String checkcode=rb.getString(getpass);
		// System.out.println("Accesscode>>> "+Accesscode);
		// System.out.println("checkcode>>> "+checkcode+" ULB "+ulbobjid);
		 if(ulbobjid.equals("0")||ulbobjid.length()!=4){
				logger.debug("ULB  Wrong >>>>> "+new Date());
				
				 return gson.toJson("Something went wrong Please try Again...!");
			}
	 if(!Accesscode.equals(checkcode)){
		 logger.debug("Access Denied >>>>> "+new Date());
		 return gson.toJson("Access Denied");
	 } 
	 
	try {
					 logger.info("Connecting >>>>>>>> " + ulbobjid);
					*//************ Live Data Base connectoin************//*
					 String distname=Dbcon.getULBName(ulbobjid);
					con = Dbcon.getdbfromdist(distname);
					//  System.out.println("District Name from ULB code"+distname);
					*//************ Test Data Base connectoin************//*
					//con = Dbcon.getdbfromdist("TEST");
					*//************ End  Live Data Base connectoin************//*
					StringBuffer querystrbuff = new StringBuffer();
					String subqurey = " and ";
			 
					//sql="select a.I_APLNNO as appno,a.DT_APLNDT as appdata,a.VC_TRTTL as Tradetitle,a.VC_TRDOORNO||'.'Tradedoorno,a.VC_TRADDR1 tradeaddrs,a.VC_TRCITY as Tradecity,a.VC_APLTSURNAME as  applicantsurname,a.VC_APLTNAME applicantname,a.VC_FHSURNAME applicantfathersurname,a.VC_FHNAME applicantfathername,a.VC_RSDLDOORNO||'.'resdoorno,a.VC_RSDLADDR1 resaddrs,a.VC_RSDLCITY as Residencecity,a.I_LCTYOBJID as localityid,e.VC_LCTYNAME as localityname,f.I_ZONENO as zone,g.I_RWNO as ward,h.I_BLCKNO as blockno,i.I_ELCNWARDNO as elecward,a.DT_FRMDT as Tradefromdate,a.DT_TODT as Tradetodate,b.I_TRCTGYCODE as categoryTypecode,c.VC_TRCNAME TradeCategoryname,b.I_TRSUBCTGYCODE as subCategorycode,d.VC_TRSUBCTGYNAME TradeSubCategoryname,a.C_TRTYPE as tradeType,a.C_TRNTUR as tradeNature,a.D_AMT as Amount from DO_APLN_MSTR_TBL a,DO_APLNTR_DTLS_TBL b,DO_TRCTGY_MSTR_TBL c,DO_TRSUBCTGY_MSTR_TBL d,CT_LCTY_MSTR_TBL e,CT_ZONE_MSTR_TBL f,CT_RW_MSTR_TBL g,CT_BLCK_MSTR_TBL h,CT_ELCNWARD_MSTR_TBL i where a.I_ULBOBJID=b.I_ULBOBJID and b.I_TRCTGYCODE=c.I_TRCTGYCODE and b.I_TRSUBCTGYCODE=d.I_TRSUBCTGYCODE and a.I_ULBOBJID="+ulbobjid;
					sql="select a.I_APLNNO as appno,a.DT_APLNDT as appdata,a.VC_TRTTL as Tradetitle,a.VC_TRDOORNO||'.'Tradedoorno,a.VC_TRADDR1 tradeaddrs,a.VC_TRCITY as Tradecity,a.VC_APLTSURNAME as  applicantsurname,a.VC_APLTNAME applicantname,a.VC_FHSURNAME applicantfathersurname,a.VC_FHNAME applicantfathername,a.VC_RSDLDOORNO||'.'resdoorno,a.VC_RSDLADDR1 resaddrs,a.VC_RSDLCITY as Residencecity,a.I_LCTYOBJID as localityid,e.VC_LCTYNAME as localityname,f.I_ZONENO as zone,g.I_RWNO as ward,h.I_BLCKNO as blockno,i.I_ELCNWARDNO as elecward,a.DT_FRMDT as Tradefromdate,a.DT_TODT as Tradetodate,b.I_TRCTGYCODE as categoryTypecode,c.VC_TRCNAME TradeCategoryname,b.I_TRSUBCTGYCODE as subCategorycode,d.VC_TRSUBCTGYNAME TradeSubCategoryname,a.C_TRTYPE as tradeType,a.C_TRNTUR as tradeNature,a.D_AMT as Amount,CASE WHEN a.C_CERTISSU='Y' THEN 'Issued' WHEN a.C_CERTISSU<>'Y' THEN 'Under-Process' END AS STATUS,(select C_LCNSNO from DO_LCNS_DTLS_TBL where I_APLNNO=a.I_APLNNO) as LicenseNo  from DO_APLN_MSTR_TBL a,DO_APLNTR_DTLS_TBL b,DO_TRCTGY_MSTR_TBL c,DO_TRSUBCTGY_MSTR_TBL d,CT_LCTY_MSTR_TBL e,CT_ZONE_MSTR_TBL f,CT_RW_MSTR_TBL g,CT_BLCK_MSTR_TBL h,CT_ELCNWARD_MSTR_TBL i where  b.I_TRCTGYCODE=c.I_TRCTGYCODE and b.I_TRSUBCTGYCODE=d.I_TRSUBCTGYCODE and c.I_TRCTGYCODE=d.I_TRCTGYCODE and a.I_APLNNO=b.I_APLNNO  and a.I_RWOBJID=g.I_RWOBJID and a.I_BLCKOBJID=h.I_BLCKOBJID and a.I_ZONEOBJID=f.I_ZONEOBJID and a.I_LCTYOBJID=e.I_LCTYOBJID and a.I_EWOBJID=i.I_EWOBJID and a.I_ULBOBJID="+ulbobjid;
					querystrbuff.append(sql);
					if(Applicationnumber!=0)
					 {
						subqurey=" and  a.I_APLNNO ="+Applicationnumber+" ";
						querystrbuff.append(subqurey);
					 }
					if(localityid!=0)
					{
						  subqurey=" and  a.I_LCTYOBJID="+localityid+"";
						  querystrbuff.append(subqurey);	
					} 	
					 System.out.println("Data"+querystrbuff.toString());
							String Qurrey = querystrbuff.toString()+" and  a.I_APLNNO=b.I_APLNNO and a.I_BLCKOBJID=h.I_BLCKOBJID and a.I_RWOBJID=g.I_RWOBJID and a.I_LCTYOBJID=e.I_LCTYOBJID and a.I_ZONEOBJID=f.I_ZONEOBJID and a.I_EWOBJID=i.I_EWOBJID group by a.I_APLNNO,a.DT_APLNDT,a.VC_TRTTL,a.VC_TRDOORNO||'.',a.VC_TRADDR1,a.VC_TRCITY,a.VC_APLTSURNAME,a.VC_APLTNAME,a.VC_FHSURNAME,a.VC_FHNAME,a.VC_RSDLDOORNO||'.',a.VC_RSDLADDR1,a.VC_RSDLCITY,a.I_LCTYOBJID,e.VC_LCTYNAME,f.I_ZONENO,g.I_RWNO,h.I_BLCKNO,i.I_ELCNWARDNO,a.DT_FRMDT,a.DT_TODT,b.I_TRCTGYCODE,c.VC_TRCNAME,b.I_TRSUBCTGYCODE,d.VC_TRSUBCTGYNAME,a.C_TRTYPE,a.C_TRNTUR,a.D_AMT,a.C_CERTISSU";  
							//System.out.println("Qurey "+Qurrey);
					ps=con.prepareStatement(Qurrey, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							//ps=con.prepareStatement(Qurrey);
					rs=ps.executeQuery();
					
					*//*********** Check Data Available or Not ****************//*
					if (Nodatafoundcheck(rs) == false) {
						return gson.toJson("No Data Found Try Again...!");
					} 
					while (rs.next()) {   
					 Tradebean tradebean=new Tradebean(rs.getInt("appno"),rs.getString("appdata"),rs.getString("Tradetitle"),rs.getString("Tradedoorno"),rs.getString("tradeaddrs"), rs.getString("Tradecity"),(rs.getString("applicantsurname")==null?" ":rs.getString("applicantsurname")+" "+rs.getString("applicantname")),( rs.getString("applicantfathersurname")==null?" ":rs.getString("applicantfathersurname")+" "+rs.getString("applicantfathername")),rs.getString("resdoorno"),rs.getString("RESADDRS"),rs.getString("Residencecity"),rs.getString("localityid"),rs.getString("localityname"),rs.getString("zone"), rs.getString("ward"),rs.getString("blockno"), rs.getString("elecward"),rs.getString("Tradefromdate"), rs.getString("Tradetodate"),rs.getString("categoryTypecode"),rs.getString("TradeCategoryname"),rs.getString("subCategorycode"),rs.getString("TradeSubCategoryname"),(rs.getString("tradeType").equals("E")?"Exclusive":rs.getString("tradeType").equals("C")?"Combination":rs.getString("tradeType")),((rs.getString("tradeNature").equals("T")?"Temporary":rs.getString("tradeNature").equals("P")?"Permanent":rs.getString("tradeNature"))),rs.getString("STATUS"),rs.getString("LicenseNo")==null?"Not Issued":rs.getString("LicenseNo"));
						tradedata.add(tradebean);
					}	
					
	} catch (Exception e) {
				e.printStackTrace();
				logger.info(e);
			} finally {
				try {
					ps.close();
					//rs.close();
					con.close();
				} catch (SQLException e) {
					logger.info(e);
					e.printStackTrace();
				}
			}
	logger.info("End Time "+new Date());
		return gson.toJson(tradedata);
	}
	*/
	/*@POST
	@Path("getlocality")
	@Produces("application/json")
	public String getlocality(@FormParam("ulb")String i_ulbobjid,@FormParam("Accesscode")String Accesscode) throws IOException, SQLException {
		 System.out.println("ULB "+ i_ulbobjid);
			String sql=null;
			 
			searchBean = new TradeSearchBean();
			Connection con=null;
			Layout playout=new PatternLayout();
			Appender appender=null;
			appender=new  FileAppender(playout,"GetLocality.txt", true);
			logger.addAppender(appender);
			ResourceBundle  rb= ResourceBundle.getBundle("login");
			String getpass="password";
			String checkcode=rb.getString(getpass);
			 System.out.println("Accesscode>>> "+Accesscode);
			 System.out.println("checkcode>>> "+checkcode+" ULB "+i_ulbobjid);
			if(i_ulbobjid.equals("0")||i_ulbobjid.length()!=4){
				logger.info("ULB  Wrong >>>>> "+new Date());
				 return gson.toJson("Something went wrong Please try Again...!");
			}
			 
		  if(!Accesscode.equals(checkcode)){
			 logger.info("Access Denied >>>>> "+new Date());
			 return gson.toJson("Access Denied");
		 } 
		try {
			logger.info("Connecting >>>>>>>> " + i_ulbobjid);
			
			 /************ Live Data Base connectoin************/
			/* String distname=Dbcon.getULBName(i_ulbobjid);
			con = Dbcon.getdbfromdist(distname);*/
			//System.out.println("con i s"+con);
			//System.out.println("District Name from ULB code"+distname);
			 //************ Test Data Base connectoin************//
			//con = Dbcon.getdbfromdist("TEST");
			 //************ End  Live Data Base connectoin************//*
		/*	System.out.println("con i s" + con);
			sql = "select b.I_ULBOBJID as ulbid ,b.VC_ULBNAME as ulbname,a.I_LCTYOBJID as localityid,a.VC_LCTYNAME as localityname from CT_LCTY_MSTR_TBL a, CT_ULB_MSTR_TBL b where a.I_ULBOBJID="+i_ulbobjid+" and a.I_ULBOBJID=b.I_ULBOBJID group by  b.I_ULBOBJID,b.VC_ULBNAME,a.I_LCTYOBJID,a.VC_LCTYNAME";
		ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		*/	//ps = con.prepareStatement(sql);
			//System.out.println("sql " + sql);
			/*rs = ps.executeQuery();
			//*********** Check Data Available or Not ****************//*
			if (Nodatafoundcheck(rs) == false) {
				return gson.toJson("No Data Found Try Again...!");
			}
			while (rs.next()) {
				searchBean = new TradeSearchBean(rs.getInt("ulbid"),rs.getString("ulbname"), rs.getInt("localityid"),rs.getString("localityname"));
				localitylist.add(searchBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		} finally {
			try {
				ps.close();
				//rs.close();
				con.close();
			} catch (SQLException e) {
				logger.info(e);
				e.printStackTrace();
			}
		}
		logger.info("End Time "+new Date()); 
		return  gson.toJson(localitylist);
	}*/
	 
public Boolean Nodatafoundcheck(ResultSet rs) throws SQLException {
			if (!rs.isBeforeFirst()==true){    
		           return false;
					}
		 return true;
	}

/*@GET
@Path("/updateStatus")
public void updatestatus(@QueryParam("reqnumber")String requestnumber){
	String result="";
	System.out.println("Request Number "+requestnumber);
	
	 ETradeApplication eTradeApplication= etradeApplicationService.getsinglerecord(Long.parseLong(requestnumber));	 
	
	Connection con=Dbcon.getmysqldb();
	
	
	System.out.println(">>> "+Long.parseLong(requestnumber));
	  
	System.out.println("Nodata");
	 
}*/

@POST
@Path("/getservice")
public String getservice(@FormParam("ulb")String i_ulbobjid,@FormParam("Accesscode")String Accesscode)  {
	System.out.println(">>>>>>>>>>>>>>>>>"+i_ulbobjid);
	System.out.println(">>>>>>>>>>>>>> "+Accesscode);
	/*String json_Data=readWebService("http://125.18.179.57:8081/TradeGeoTagWS/cdma/Trade/getlocality/");   
	if(!json_Data.equalsIgnoreCase("[]")){
		JsonArray jModifierArray = parser.parse(json_Data).getAsJsonArray();
		int total=0;
		for (int j = 0; j < jModifierArray.size(); j++) {
		 System.out.println("jModifierArray.size()");
		}
	}*/
	return "success";
}
@GET
@Path("/updateStatus")
//@Produces("application/json")
public String updatestatus(@QueryParam("reqnumber") String   reqnumber,@QueryParam("application_stage") String application_stage,@QueryParam("paymentdate")String paymentdate,@QueryParam("paymentflag")String paymentflag,@QueryParam("applicationappeoveddate")String applicationappeoveddate,@QueryParam("approved_flag")String approved_flag,@QueryParam("reasonforreject")String reasonforreject,@QueryParam("number") String number,@QueryParam("msg") String msg,@QueryParam("url") String url) throws SQLException {
	//String result="";
	String paymentstatus=null;
	System.out.println("Request Number "+reqnumber+" application_stage "+application_stage+" paymentdate "+paymentdate+" paymenyflag"+paymentflag+" applicationappeoveddate" +applicationappeoveddate+" approved_flag "+approved_flag +" reasonforreject "+reasonforreject);
	System.out.println("URL "+url); 
	//ETradeApplication eTradeApplication= etradeApplicationService.getsinglerecord(Long.parseLong(requestnumber));	 
	//EtradeDaoimpl etradeDaoimpl=new  EtradeDaoimpl();
	//etradeDaoimpl.getrsinglerecord(Long.parseLong(requestnumber));

	SMSHttpPostClient sms=new SMSHttpPostClient();
	 sms.SendSMSmain(number, msg);
	 
	 logger.info("Update Status from EMAS >>>>>>>>   Request Number "+reqnumber+" application_stage "+application_stage+" paymentdate "+paymentdate+" paymentflag"+paymentflag+" applicationappeoveddate" +applicationappeoveddate+" approved_flag "+approved_flag +" reasonforreject "+reasonforreject);
	System.out.println(">>> "+Long.parseLong(reqnumber));
  // entrydate, trade_type, uniqueRequestNumber, applicatoinstatusflag, paymentflag, cert_issue_flag, paymentdate, paymentamount, application_stage, applicationappeoveddate, reasonforreject, trade_license_number;
	System.out.println("Nodata");
	//try {
		Connection con=Dbcon.getmysqldb();	
		
		sql1="select application_stage,paymentdate,paymentflag,applicationappeoveddate,reasonforreject  from etrade.etrade_application  where uniqueRequestNumber="+reqnumber+"";
	System.out.println("sqdsfsdfl "+sql1);
		ps1=con.prepareStatement(sql1);
		rs1=ps1.executeQuery();
		 if (rs1.next()) {
			 System.out.println("loop "+rs1.getString("paymentflag"));
			 paymentstatus=rs1.getString("paymentflag");
		}
if(paymentstatus.equalsIgnoreCase("N")){
		 sql="update etrade_application set  application_stage='"+application_stage+"',paymentdate='"+paymentdate+"',paymentflag='"+paymentflag+"',applicationappeoveddate='"+applicationappeoveddate+"',reasonforreject='"+reasonforreject+"' where uniqueRequestNumber="+reqnumber+"";
		System.out.println("sql "+sql);
		ps=con.prepareStatement(sql);
		ps.executeUpdate();
}
	//} catch (Exception e) {
	//	con.close();
	//e.printStackTrace();
	//return url; 
	//}
con.close();
  
  
 System.out.println("No eRROR");
 return url; 
  
}

@GET
@Path("/test")
public String te (@PathParam ("t")String t){
	System.out.println("test "+t);
	
	return "sucess";
}
/*public void    status(@PathParam("reqnumber") String   reqnumber,@PathParam("application_stage") String application_stage,@PathParam("paymentamount") String paymentamount,@PathParam("paymentdate")String paymentdate,@PathParam("paymenyflag")String paymenyflag,@PathParam("approveddate")String approveddate,@PathParam("approved_flag")String approved_flag,@PathParam("reasonforreject")String reasonforreject){
	System.out.println(" Status "+reqnumber+" application_stage "+application_stage+ " paymentamount "+paymentamount+" paymentdate "+paymentdate+ " paymenyflag "+paymenyflag+ " approveddate "+approveddate +"approved_flag "+approved_flag+" reasonforreject "+reasonforreject);
	ETradeApplication eTradeApplication=new ETradeApplication();
	eTradeApplication=etradeApplicationService.getsinglerecord(Long.parseLong(reqnumber));
	System.out.println("AAdhar number "+eTradeApplication.getOwnerAadhar());
	
*/
 

public static void main(String[] args) {
	 
	 }
	  
 
}
