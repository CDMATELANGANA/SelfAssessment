package com.mars.cdma.gov.Controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mars.cdma.gov.Dao.ReportDAO;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.DtcpApplication;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.scheduler.UpdateCertificateStatus;
import com.mars.cdma.gov.service.AssessmentReportService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;

@Controller
public class AssessmentReportController {

	@Autowired
	private AssessmentReportService assessmentReportService;
	
	@Autowired
	private DistrictsService districtsService;
	@Autowired
	private UlbsService ulbService;
	
	@Autowired
	private Assessmentservice assessmentservice;
	public Gson gson = new Gson();
	@Autowired
	private SessionFactory sessionFactory;
	DtcpApplication dtcpbean=null;
	//By district
	//getOldDashboard.do
		 @RequestMapping("/getNewDashboard.do")
		public ModelAndView getDashboard(HttpServletRequest request,
				HttpServletResponse response) {

			List<Object[]> asmntList = assessmentReportService
					.dashboard();
			List<Object[]> districtCMS = assessmentReportService
					.dashboardCMS();
			
			List<Object[]> districtMob = assessmentReportService
					.dashboardMob();
			request.setAttribute("districtMob", districtMob);
			request.setAttribute("districtCMS", districtCMS);
			
			request.setAttribute("repotype", "district");
			
			return new ModelAndView("dashboard", "dashborad",
					asmntList);
		}
		 @RequestMapping("/getOldDashboard.do")
			public ModelAndView getOldDashboard(HttpServletRequest request,
					HttpServletResponse response) {

				List<Object[]> asmntList = assessmentReportService
						.Olddashboard();
				List<Object[]> districtCMS = assessmentReportService
						.dashboardCMS();
				
				List<Object[]> districtMob = assessmentReportService
						.dashboardMob();
				
				System.out.println("Size CMS "+districtCMS.size());
				System.out.println("Size Mob  "+districtMob.size());
				
				request.setAttribute("districtMob", districtMob);
				request.setAttribute("districtCMS", districtCMS);
				
				request.setAttribute("repotype", "district");
				
				return new ModelAndView("Olddashboard", "dashborad",
						asmntList);
			}
	//By district
		@RequestMapping("/getOldAssmntByAllDistrictWise.do")
		public ModelAndView getOldAssmntByAllDistrictWise(HttpServletRequest request,
				HttpServletResponse response) {

			List<Object[]> asmntList = assessmentReportService
					.getOldAssmntByAllDistrictWise();
			request.setAttribute("repotype", "district");
			return new ModelAndView("OldassessmentReportDistrictWise", "asmntList",
					asmntList);
		}
		
		@RequestMapping("/getOldAssmntBySelectedDistrictWise.do")
		public ModelAndView getOldAssmntBySelectedDistrictWise(
				HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "dist_id", required = true) String dist_id) {

			List<Object[]> asmntList = assessmentReportService.getOldAssmntBySelectedDistrictWise(dist_id);
			request.setAttribute("repotype", "sel_district");
			return new ModelAndView("OldassessmentReportDistrictWise", "asmntList",
					asmntList);
		}
		
		@RequestMapping("/getOldAssmntBySelectedUlbWise.do")
		public ModelAndView getOldAssmntBySelectedUlbWise(HttpServletRequest request,
				HttpServletResponse response, @RequestParam(value = "ulb_id", required = true) String ulb_id) {

			List<Object[]> asmntList = assessmentReportService
					.getOldAssmntBySelectedUlbWise(ulb_id);
			
			List<Object[]> ulbCount = assessmentReportService
					.getOldAssmntBySelectedUlbWiseCount(ulb_id);
			
			
			Iterator<Object[]> it=ulbCount.listIterator();		
			while(it.hasNext())
			{
				Object[] o= it.next();
				for(Object o1:o)
				{
					System.out.println("  == "+o1);
				}					
			}
			
			request.setAttribute("ulbCount", ulbCount);
			request.setAttribute("repotype", "sel_ulb");
			return new ModelAndView("OldassessmentReportUlbWise", "asmntList",
					asmntList);
		}
		@RequestMapping("/getOldAssmntByAllUlbWise.do")
		public ModelAndView getOldAssmntByAllUlbWise(HttpServletRequest request,
				HttpServletResponse response) {

			List<Object[]> asmntList = assessmentReportService
					.getOldAssmntByAllUlbWise();
			request.setAttribute("repotype", "ulb");
			return new ModelAndView("OldassessmentReportUlbWise", "asmntList",
					asmntList);
		}
		 @RequestMapping("/getPTCalculatorDashboard.do")
			public ModelAndView getPtCalculatorDashboard(HttpServletRequest request,
					HttpServletResponse response) {
			 List<Integer> TotalVisit=assessmentservice.getTotalVisitor();
			 return new ModelAndView("ptCalculatorDashboard","TotalVisit",TotalVisit); 
		 
		 }
		 
		 @RequestMapping("/getPtCalculatorAllDistrictWise.do")
			public ModelAndView getPtCalculatorAllDistrictWise(HttpServletRequest request,
					HttpServletResponse response) {
			 List<String> districtList=assessmentservice.getPtCalculatorAllDistrictWiseDashboard();
			 return new ModelAndView("ptCalculatorDashboardDistrictWise","districtList",districtList); 
		 
		 }
		 @RequestMapping("/getPtCalculatorUlbWise.do")
			public ModelAndView getPtCalculatorUlbWise(@RequestParam("districtcode")String districtcode,HttpServletRequest request,
					HttpServletResponse response) {
			List<String> districtulbList=assessmentservice.getPtCalculatorUlbWiseDashboard(districtcode);
			 return new ModelAndView("ptCalculatorDashboardUlbWise","districtulbList",districtulbList); 
		 
		 }
		 
		 @RequestMapping("/getPtCalculatorAllUlbWise.do")
			public ModelAndView getPtCalculatorAllUlbWise(HttpServletRequest request,
					HttpServletResponse response) {
			List<String> ulbList=assessmentservice.getPtCalculatorAllUlbWiseDashboard();
			 return new ModelAndView("ptCalculatorDashboardAllUlbWise","ulbList",ulbList); 
		 
		 }

	//By district
	@RequestMapping("/getAssmntByAllDistrictWise.do")
	public ModelAndView getAssmntByAllDistrictWise(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntByAllDistrictWise();
		request.setAttribute("repotype", "district");
		return new ModelAndView("assessmentReportDistrictWise", "asmntList",
				asmntList);
	}
	
	//charan written
	
	@RequestMapping("/getAssmntByAllDistrictWiseCMS.do")
	public ModelAndView getAssmntByAllDistrictWiseCMS(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService.getAssmntByAllDistrictWiseCMS();
				
		request.setAttribute("repotype", "district");
		return new ModelAndView("assessmentReportDistrictWiseCMS", "asmntList",
				asmntList);
	}

	@RequestMapping("/getAssmntByAllDistrictWiseMob.do")
	public ModelAndView getAssmntByAllDistrictWiseMob(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService.getAssmntByAllDistrictWiseMob();
				
		request.setAttribute("repotype", "district");
		return new ModelAndView("assessmentReportDistrictWiseMob", "asmntList",
				asmntList);
	}
	
	@RequestMapping("/getAssmntBySelectedDistrictWise.do")
	public ModelAndView getAssmntBySelectedDistrictWise(
			HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "dist_id", required = true) String dist_id) {

		List<Object[]> asmntList = assessmentReportService.getAssmntBySelectedDistrictWise(dist_id);
		request.setAttribute("repotype", "sel_district");
		return new ModelAndView("assessmentReportDistrictWise", "asmntList",
				asmntList);
	}
	//charan written
	
	@RequestMapping("/getAssmntBySelectedDistrictWiseCMS.do")
	public ModelAndView getAssmntBySelectedDistrictWiseCMS(
			HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "dist_id", required = true) String dist_id) {

		List<Object[]> asmntList = assessmentReportService.getAssmntBySelectedDistrictWiseCMS(dist_id);
		request.setAttribute("repotype", "sel_district");
		return new ModelAndView("assessmentReportDistrictWiseCMS", "asmntList",
				asmntList);
	}
	
	@RequestMapping("/getAssmntBySelectedDistrictWiseMob.do")
	public ModelAndView getAssmntBySelectedDistrictWiseMob(
			HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "dist_id", required = true) String dist_id) {

		List<Object[]> asmntList = assessmentReportService.getAssmntBySelectedDistrictWiseMob(dist_id);
		request.setAttribute("repotype", "sel_district");
		return new ModelAndView("assessmentReportDistrictWiseMob", "asmntList",
				asmntList);
	}
	
	
	//By ULB
	@RequestMapping("/getAssmntByAllUlbWise.do")
	public ModelAndView getAssmntByAllUlbWise(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntByAllUlbWise();
		request.setAttribute("repotype", "ulb");
		return new ModelAndView("assessmentReportUlbWise", "asmntList",
				asmntList);
	}
	
	/*New implementation*/
	@RequestMapping(value = "/assmntReportByDate.do", method = RequestMethod.GET)
	public ModelAndView assmntReportByDate(
			@RequestParam(value = "entryFrom", required = true) String fromDate,
			@RequestParam(value = "entryTo", required = true) String toDate,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException

	{

		SimpleDateFormat src = new SimpleDateFormat("dd/MM/yyyy");
		List asmntList = assessmentReportService.getAssmntByAllWiseByDate(src.parse(fromDate), src.parse(toDate));
				//getAssmntByAllDistrictWiseByDate(src.parse(fromDate), src.parse(toDate));
		//.getTradeByAllDistrictByDate(src.parse(entryFrom), src.parse(entryTo));
		System.out.println("ulb datewise");
		request.setAttribute("repotype", "ulb");
		request.setAttribute("date_interval", fromDate + " To " + toDate);
		return new ModelAndView("assessmentReportUlbWise", "asmntList",
				asmntList);
	}

	
	//charan written
	
	@RequestMapping("/getAssmntByAllUlbWiseCMS.do")
	public ModelAndView getAssmntByAllUlbWiseCMS(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntByAllUlbWiseCMS();
		request.setAttribute("repotype", "ulb");
		return new ModelAndView("assessmentReportUlbWiseCMS", "asmntList",
				asmntList);
	}
	
	@RequestMapping("/getAssmntByAllUlbWiseMob.do")
	public ModelAndView getAssmntByAllUlbWiseMob(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntByAllUlbWiseMob();
		request.setAttribute("repotype", "ulb");
		return new ModelAndView("assessmentReportUlbWiseMob", "asmntList",
				asmntList);
	}
	
	@RequestMapping("/getAssmntBySelectedUlbWise.do")
	public ModelAndView getAssmntBySelectedUlbWise(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "ulb_id", required = true) String ulb_id) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntBySelectedUlbWise(ulb_id);
		
		List<Object[]> ulbCount = assessmentReportService
				.getAssmntBySelectedUlbWiseCount(ulb_id);
		
		
		/*Iterator<Object[]> it=ulbCount.listIterator();		
		while(it.hasNext())
		{
			Object[] o= it.next();
			for(Object o1:o)
			{
				System.out.println("  == "+o1);
			}					
		}*/
		
		request.setAttribute("ulbCount", ulbCount);
		request.setAttribute("repotype", "sel_ulb");
		return new ModelAndView("assessmentReportUlbWise", "asmntList",
				asmntList);
	}
	
	//charan written
	
	@RequestMapping("/getAssmntBySelectedUlbWiseCMS.do")
	public ModelAndView getAssmntBySelectedUlbWiseCMS(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "ulb_id", required = true) String ulb_id) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntBySelectedUlbWiseCMS(ulb_id);
		
		List<Object[]> ulbCount = assessmentReportService
				.getAssmntBySelectedUlbWiseCountCMS(ulb_id);
		
		
		Iterator<Object[]> it=ulbCount.listIterator();		
		while(it.hasNext())
		{
			Object[] o= it.next();
			for(Object o1:o)
			{
				System.out.println("  == "+o1);
			}					
		}
		
		request.setAttribute("ulbCount", ulbCount);
		request.setAttribute("repotype", "sel_ulb");
		return new ModelAndView("assessmentReportUlbWiseCMS", "asmntList",
				asmntList);
	}

	@RequestMapping("/getAssmntBySelectedUlbWiseMob.do")
	public ModelAndView getAssmntBySelectedUlbWiseMob(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "ulb_id", required = true) String ulb_id) {

		List<Object[]> asmntList = assessmentReportService
				.getAssmntBySelectedUlbWiseMob(ulb_id);
		
		List<Object[]> ulbCount = assessmentReportService
				.getAssmntBySelectedUlbWiseCountMob(ulb_id);
		
		
		Iterator<Object[]> it=ulbCount.listIterator();		
		while(it.hasNext())
		{
			Object[] o= it.next();
			for(Object o1:o)
			{
				System.out.println("  == "+o1);
			}					
		}
		
		request.setAttribute("ulbCount", ulbCount);
		request.setAttribute("repotype", "sel_ulb");
		return new ModelAndView("assessmentReportUlbWiseMob", "asmntList",
				asmntList);
	}
	/************** Scheduler Start *******************/
	@RequestMapping("/schStart.do")
	public ModelAndView startSchedulerInitiateStart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		return new ModelAndView("schStart");
		
	}
	
	@RequestMapping(value= "/schStartCalled.do", method = RequestMethod.POST)
	public void startSchedulerInitiateCalled(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
	new	UpdateCertificateStatus().getSetCertificateStatusDb2ToLocal();		
		response.sendRedirect("getAssmntByAllDistrictWise.do");
	}
	/************** Scheduler End *******************/

	//charan written
	
	@RequestMapping("/getDashboardCMS.do")
	public ModelAndView getDashboardCMS(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.dashboardCMS();
		request.setAttribute("repotype", "district");
		return new ModelAndView("dashboardCMS", "dashborad",
				asmntList);
	}
	
	@RequestMapping("/getDashboardMob.do")
	public ModelAndView getDashboardMob(HttpServletRequest request,
			HttpServletResponse response) {

		List<Object[]> asmntList = assessmentReportService
				.dashboardMob();
		request.setAttribute("repotype", "district");
		return new ModelAndView("dashboardMob", "dashborad",
				asmntList);
	}
	
	@RequestMapping(value = "/getRestDashboardMob", method = RequestMethod.GET)
	 @ResponseBody
		public String getRestDashboardMob(HttpSession session,
				HttpServletRequest request, HttpServletResponse response)

		{ 
			
			Map<String, String> bulidreport=new HashMap<String, String>();
			 
			List<Object[]> asmntList = assessmentReportService.dashboardMob();
					
			
			Iterator<Object[]> itr = asmntList.iterator();
			while (itr.hasNext()) {
				Object[] data = (Object[]) itr.next();
			 
				 Integer riresult= Integer.parseInt((String) (data[4]=="null"?"0":data[4].toString()))-Integer.parseInt((String) (data[2]==null?"0":data[2].toString()))-Integer.parseInt((String) (data[3]==null?"0":data[3].toString()))-Integer.parseInt((String) (data[5]==null?"0":data[5]));
		
			 	bulidreport.put("Total Assessment",  ""+(data[4]==null?"0":data[4]));
				bulidreport.put("Under RO/RI Login",  ""+riresult); 
				bulidreport.put("Under Commissioner Login",  ""+(data[2]==null?"0":data[2]));													
				bulidreport.put("Application_Approved",  ""+(data[5]==null?"0":data[5]));
				bulidreport.put("Application_Rejected",  ""+(data[3]==null?"0":data[3]));
				bulidreport.put("Application_beyond15days",  ""+(data[6]==null?"0":data[6]));
			}
			
			return gson.toJson(bulidreport);
			 

		}
	
	@RequestMapping(value = "/getRestDashboardCMS", method = RequestMethod.GET)
	 @ResponseBody
		public String getRestDashboardCMS(HttpSession session,
				HttpServletRequest request, HttpServletResponse response)

		{ 
			Map<String, String> bulidreport=new HashMap<String, String>();
			 
			List<Object[]> asmntList = assessmentReportService.dashboardCMS();
					
			
			Iterator<Object[]> itr = asmntList.iterator();
			while (itr.hasNext()) {
				Object[] data = (Object[]) itr.next();
			 
				 Integer riresult= Integer.parseInt((String) (data[4]=="null"?"0":data[4].toString()))-Integer.parseInt((String) (data[2]==null?"0":data[2].toString()))-Integer.parseInt((String) (data[3]==null?"0":data[3].toString()))-Integer.parseInt((String) (data[5]==null?"0":data[5]));
		 
				bulidreport.put("Total Assessment",  ""+(data[4]==null?"0":data[4]));
				bulidreport.put("Under RO/RI Login",  ""+riresult);
				bulidreport.put("Under Commissioner Login",  ""+(data[2]==null?"0":data[2]));													
				bulidreport.put("Application_Approved",  ""+(data[5]==null?"0":data[5]));
				bulidreport.put("Application_Rejected",  ""+(data[3]==null?"0":data[3]));
				bulidreport.put("Application_beyond15days",  ""+(data[6]==null?"0":data[6]));
			}
			
			return gson.toJson(bulidreport);
			 

		}
	@RequestMapping(value = "/getRestDashboardUlbCodeCMS", method = RequestMethod.GET)
    @ResponseBody
           public String getRestDashboardUlbCodeCMS(@QueryParam("ulbcode") String ulbcode,HttpSession session,
                           HttpServletRequest request, HttpServletResponse response)

           {
                    
                   
                   Map<String, String> bulidreport=new HashMap<String, String>();
                    
                   List<Object[]> asmntList = assessmentReportService.getAssmntBySelectedUlbWiseCountCMS(ulbcode);
                   
                                   
                   
                   
                   Iterator<Object[]> itr = asmntList.iterator();
                   while (itr.hasNext()) {
                           Object[] data = (Object[]) itr.next();
                    
                            Integer riresult= Integer.parseInt((String) (data[6]=="null"?"0":data[6].toString()))-Integer.parseInt((String) (data[4]==null?"0":data[4].toString()))-Integer.parseInt((String) (data[5]==null?"0":data[5].toString()))-Integer.parseInt((String) (data[7]==null?"0":data[7]));
            
                           bulidreport.put("Total Assessment",  ""+(data[6]==null?"0":data[6]));
                           bulidreport.put("Under RO/RI Login",  ""+riresult);
                           bulidreport.put("Under Commissioner Login",  ""+(data[4]==null?"0":data[4]));                                                                                                        
                           bulidreport.put("Application_Approved",  ""+(data[7]==null?"0":data[7]));
                           bulidreport.put("Application_Rejected",  ""+(data[5]==null?"0":data[5]));
                           bulidreport.put("Application_beyond15days",  ""+(data[8]==null?"0":data[8]));
                           
                           
                   }
                   
                   return gson.toJson(bulidreport);
                    

           }
   
   @RequestMapping(value = "/getRestDashboardUlbCodeMob", method = RequestMethod.GET)
    @ResponseBody
           public String getRestDashboardUlbCodeMob(@QueryParam("ulbcode") String ulbcode,HttpSession session,
                           HttpServletRequest request, HttpServletResponse response)

           {
                   Map<String, String> bulidreport=new HashMap<String, String>();
                    
                   List<Object[]> asmntList = assessmentReportService
                                   .getAssmntBySelectedUlbWiseCountMob(ulbcode);                        
                   
                   Iterator<Object[]> itr = asmntList.iterator();
                   
                
                   while (itr.hasNext()) {
                           Object[] data = (Object[]) itr.next();
                    
                            Integer riresult= Integer.parseInt((String) (data[6]==null?"0":data[6].toString()))-Integer.parseInt((String) (data[4]==null?"0":data[4].toString()))-Integer.parseInt((String) (data[5]==null?"0":data[5].toString()))-Integer.parseInt((String) (data[7]==null?"0":data[7]));
            
                           bulidreport.put("Total Assessment",  ""+(data[6]==null?"0":data[6]));
                           bulidreport.put("Under RO/RI Login",  ""+riresult);
                           bulidreport.put("Under Commissioner Login",  ""+(data[4]==null?"0":data[4]));                                                                                                        
                           bulidreport.put("Application_Approved",  ""+(data[7]==null?"0":data[7]));
                           bulidreport.put("Application_Rejected",  ""+(data[5]==null?"0":data[5]));
                           bulidreport.put("Application_beyond15days",  ""+(data[8]==null?"0":data[8]));
                           
                           
                   }
                   
                   
                   
                   return gson.toJson(bulidreport);
                    

           }
      
   /*
    * new implementation
    */
   @RequestMapping("/UlbDetailReport.do")
	public ModelAndView revenueDetail(HttpSession session, HttpServletRequest request,Model m,
			@PathParam("type") String type,HttpServletResponse response) {
	   System.out.println("type=="+type);
		
		if (session.getAttribute("permissionSession") == null) {
			return new ModelAndView("login");
		} else {
			 String ulbCode=(String) session.getAttribute("ulb_code");
			 List<Object[]> dtcp = assessmentReportService.getUlbReport(ulbCode);
				
			   return new ModelAndView("DtcpDashboardUlbWise","dtcp",dtcp);
		   }
		}
   
   
   @RequestMapping(value = "/UlbDetailReportDownloadCeriticate.do", method = RequestMethod.GET)
	public String UlbDetailReportDownloadCeriticate(
			@RequestParam(value = "id", required = true) int id,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException,
			NamingException {
		/*DtcpApplication dtcpbean=assessmentReportService.getSelfAssessmentApplicatio(id);*/
	    dtcpbean=assessmentReportService.getSelfAssessmentApplicatio(id);
		
		
		System.out.println("certificate");
		try {

			HashMap<String, Object> hmParams = new HashMap<String, Object>();
				
           hmParams.put("ID",dtcpbean.getId());
           String block_Pdf_Desc=getblckdetails(dtcpbean.getUlbcode(),dtcpbean.getBlock_no_code());
           String bulding_Pdf_class=getBuldingClass(dtcpbean.getUlbcode(),dtcpbean.getBuilding_cls());
           String bulding_Pdf_Type=getBuildingType(dtcpbean.getUlbcode(), dtcpbean.getBuilding_type());
           hmParams.put("block_no_code",block_Pdf_Desc);
         //  dtcpbean.setBuilding_cls(bulding_Pdf_class);
           hmParams.put("building_cls",bulding_Pdf_class);
           hmParams.put("building_type",bulding_Pdf_Type);
           System.out.println("bulding_Pdf_Type===>"+bulding_Pdf_Type);
           String fileName = "Dtcpdata";
			String path = request.getContextPath();
			File reportFile = new File(request.getSession().getServletContext()
					.getRealPath("/jasper/" + fileName + ".jasper"));
			JasperCompileManager.compileReportToFile(
					request.getSession().getServletContext()
							.getRealPath("/jasper/" + fileName + ".jrxml"),
					request.getSession().getServletContext()
							.getRealPath("/jasper/" + fileName + ".jasper"));
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());

			jasperPDF(jasperReport, hmParams, response, String.valueOf(id));
          System.out.println("***********");

		} catch (Exception Exp) {
			System.out.println("Exception::" + Exp.toString());
		} finally {

		}

		return null;
	}

   public void jasperPDF(JasperReport jasperReport,
			Map<String, Object> hmParams, HttpServletResponse response,
			String filename) {
		try {
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport,
					hmParams, getConnection());
			response.reset();
			response.resetBuffer();

			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename + ".pdf");
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
		}
	}
  
   public String getblckdetails(int ulbCode,int blockNo) throws SQLException, IOException,
			NamingException {
	   Connection con = null;
       con= Dbcon.getdb(String.valueOf(ulbCode));
	String sql1 = "select VC_BLCKDESC from CT_BLCK_MSTR_TBL where I_ULBOBJID="+ulbCode+" and I_BLCKOBJID="+blockNo+"";
	System.out.println("sql1"+sql1);
	PreparedStatement ps1 = con.prepareStatement(sql1);
	ResultSet rs = ps1.executeQuery();
	String block_discription="";
	System.out.println("block_discription=="+block_discription);
	while(rs.next())
	{
		 block_discription=rs.getString("VC_BLCKDESC");
	}
	System.out.println("block_discription++++"+block_discription);
   return block_discription;
   }
   
   public String getBuldingClass(int ulbCode,String buldingclassid) throws SQLException, IOException,
	NamingException {
Connection con = null;
con= Dbcon.getdb(String.valueOf(ulbCode));

String sql1 = "select VC_CLSNAME from PT_BLDGCLS_MSTR_TBL where I_ULBOBJID="+ulbCode+" and I_CLSCODE="+buldingclassid+"";
System.out.println("sql1"+sql1);
PreparedStatement ps1 = con.prepareStatement(sql1);
ResultSet rs = ps1.executeQuery();
String bulding_cls="";
while(rs.next())
{
	bulding_cls=rs.getString("VC_CLSNAME");
}
return bulding_cls;
}
   public String getBuildingType(int ulbCode,String buldingtype) throws SQLException, IOException,
	NamingException {
Connection con = null;
con= Dbcon.getdb(String.valueOf(ulbCode));

String sql1 = "select VC_BLDGUSETYPE from PT_BLDGUSE_MSTR_TBL where I_ULBOBJID="+ulbCode+" and I_BLDGUSECODE="+buldingtype+"";
System.out.println("sql1="+sql1);
PreparedStatement ps1 = con.prepareStatement(sql1);
ResultSet rs = ps1.executeQuery();
String bilding_type="";
System.out.println("bilding_type---"+bilding_type);
while(rs.next())
{
	bilding_type=rs.getString("VC_BLDGUSETYPE");
}
System.out.println("bilding_type---"+bilding_type);

return bilding_type;
}
   
   public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/taxcal", "root", "root");
		} catch (ClassNotFoundException e) {
		}
		return conn;

	}


   
   
		
		/*List<String> totalCollection = new ArrayList<String>();
		List<String> gatewayCount = new ArrayList<String>();
		
		try {		
			//ulb_code
			 String ulbCode=(String) session.getAttribute("ulb_code");
			
			System.out.println("ulbcode==> "+ulbCode);
			totalCollection=assessmentReportService.ULBReport(ulbCode,type);
			
			gatewayCount=assessmentReportService.gatewayCount(ulbCode,type);
			
		m.addAttribute("gateway", gatewayCount);
			request.setAttribute("abstractReport", totalCollection);
			request.setAttribute("type", type);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ModelAndView("revenueULBReport", "message", totalCollection);
		
		
		}}*/
			
	
   @RequestMapping(value = "/findDistCode.do", method = RequestMethod.GET)
	public ModelAndView getDistrictcode() {
		System.out.println("open View find ");
		List dist = assessmentReportService.getDistrictcode();
		return new ModelAndView("NewReport", "dist", dist);
		// return new ModelAndView("FindUlb");
	}
   
   @RequestMapping(value = "/getUlbcode.do", method = RequestMethod.POST)
	public @ResponseBody
	String getSearchbclsvalues(@FormParam("d") String d,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, JSONException {
		System.out.println("cmng to cntrlr");
	
		String sql1 = "SELECT ULB_CODE, ULB_NAME   FROM taxcal.ulbs where DISTRICT_ID=" + d + "";
		 
		SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql1);
		 
		List<Object[]> l1 = query1.list();
		
				System.out.println(l1);
		Map<Integer,String>  map = new HashMap<>();
	
	 		for (Object[] result : l1) {
		    Integer id = (Integer)result[0];
		    String name = (String)result[1];
		   map.put(id, name);
		   
		}
		return gson.toJson(map);

	}
   @RequestMapping(value = "/dtcpData.do", method = RequestMethod.POST)
	//public @ResponseBody
	 
	public ModelAndView getUlbIdnew(@RequestParam("ulbName") Integer uid,HttpServletRequest request, HttpServletResponse response
			,HttpSession session)throws SQLException, JSONException {
		 System.out.println("ulbID==>"+uid);
		/* String sql1 = "SELECT id, dtcpfilenumber,aadharnumber,  emasRegNo, entry_date, length, localitycode, mobilenumber, occupant_type, occupantname, occupantsurname, ownercity, ownername, ownershiptype, relationname, relationsurname, revenue_ward_code, street_name_code, surname, ulbcode, width, zonecode FROM taxcal.dtcp_application where ulbcode=" + uid + "" ;
		 SQLQuery query1 = sessionFactory.openSession().createSQLQuery(sql1);
			List<Object[]> l1 = query1.list();
		*/
		List<Object[]> dtcp = assessmentReportService.getDtcpDetail(uid);
		//System.out.println("ulbList==" + dtcp);
		 
		//return gson.toJson(dtcp);
		return new ModelAndView("NewReport", "dtcp", dtcp);
	}
   
   
   @RequestMapping(value="/selfAssmentData.do", method = RequestMethod.GET)
   public ModelAndView getSelfAssmentData(@RequestParam("id") int id,HttpServletRequest request) {
	   System.out.println("controller id=="+id);
	 
	    DtcpApplication  dtcpApplication=assessmentReportService.getSelfAssessmentApplicatio(id);
	 
	    
	    List<Districts> districtsList = districtsService.getAllOrderByName();
		// System.out.println("district >>>>"+districtsList.size());
		request.setAttribute("districtsList", districtsList);

		Map<String, Object> subUlbsMap = new HashMap<String, Object>();

		List<Districts> ulbsByDistrict = districtsService.getAllOrderByName();
		// System.out.println(" >>  id "+ulbsByDistrict.size());
		// System.out.println("district "+ulbsByDistrict.size());
		for (Districts districtsObj : ulbsByDistrict) {
			List<Ulbs> ulbsListOnValue = ulbService.findByProperty(
					"districts.districtId", districtsObj.getDistrictId());

			if (ulbsListOnValue != null && ulbsListOnValue.size() != 0)
				subUlbsMap.put("" + districtsObj.getDistrictId(), ulbService
						.findByProperty("districts.districtId",
								districtsObj.getDistrictId()));
		}

		// System.out.println("ulb name "+subUlbsMap.keySet()+" value "+subUlbsMap.values());
		request.setAttribute("subUlbsMap", subUlbsMap);

	    
			//System.out.println("--> "+dtcpApplication.getAadharnumber());
			//System.out.println("--> "+dtcpApplication.getDtcpfilenumber());
	 		
	 		 
	 return new ModelAndView("NewAssessmentApplication","list",dtcpApplication);   
	   
   }
   @RequestMapping(value="/dtcpDashboard.do",method=RequestMethod.GET)
   public ModelAndView getDtcpDashboard(HttpSession session, HttpServletRequest request,ModelMap m,
			HttpServletResponse response)
   {
	  // List<Integer> dtcpTotal=assessmentReportService.getDtcpDashboardTotal();
	   String ulbCode=(String) session.getAttribute("ulb_code");
	 List<Object[]> dtcp=  assessmentReportService.getDtcpDashboard(ulbCode);
	 
	
	 System.out.println("dtcptotal=="+dtcp);
	   return new ModelAndView("DtcpDashboard","dtcptotal",dtcp);
   }
   
   @RequestMapping(value="/getDtcpDashboardList.do",method=RequestMethod.GET)
   public ModelAndView getDtcpDashboardList(HttpSession session, HttpServletRequest request,ModelMap m,
			HttpServletResponse response)
   {
	   String ulbCode=(String) session.getAttribute("ulb_code");
	   List<Object[]> dtcpl=  assessmentReportService.getDtcpDashboardList(ulbCode);
	   return new ModelAndView("DtcpDashboardList","dtcpl",dtcpl);
   }
   
   
   
   @RequestMapping(value="getDtcpDashboardUlbDistrict.do",method=RequestMethod.GET)
   public ModelAndView getDtcpDashboardUlbDistrict(HttpServletRequest request, 
		   HttpServletResponse response,@RequestParam(value = "districtid", required = true) Integer districtid)
   {
	   System.out.println("-------------");
	   System.out.println("districtid="+districtid);
	   List<Object[]> dtcp = assessmentReportService.getDtcpDashboardUlbDistrict(districtid);
		System.out.println("dtcp="+dtcp);
	   return new ModelAndView("DtcpDashboardUlbDistrict","dtcp",dtcp);
   }
   
   @RequestMapping(value="getDtcpDashboardUlbWise.do",method=RequestMethod.GET)
   public ModelAndView getDtcpDashboardUlbWise(HttpServletRequest request, 
		   HttpServletResponse response,@RequestParam(value = "ulbid", required = true) Integer ulbid)
   {
	   List<Object[]> dtcp = assessmentReportService.getDtcpDetail(ulbid);
		
	   return new ModelAndView("DtcpDashboardUlbWise","dtcp",dtcp);
   }
      public static boolean sendToFTP(String remotepath, InputStream localpath,
			String pathchkRemote) {

		// LoggerSample.info("ftp upload sign pdf started in sendToFTP(())");
		boolean uploadStatus = false;
		try {

			System.out.println("the remote path is " + remotepath
					+ " pathchkRemote  " + pathchkRemote);
			FTPClient client = new FTPClient();
			// client.connect("10.2.2.199"); // Password >>>password
			client.connect("10.3.3.83"); // Password >>>password@123
			// client.connect("10.166.7.11"); // Password >>>password@123
			client.enterLocalPassiveMode();
			  client.login("cdma-user1", "password@123");

			// LoggerSample.info("ftp server  connection status is " + b1);
			// client.setsetControlKeepAliveTimeout(300);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.BINARY_FILE_TYPE);

			if (!client.changeWorkingDirectory(pathchkRemote)) {
				System.out.println("***************/////////" + pathchkRemote);
				client.makeDirectory(pathchkRemote);
			}
			System.out.println("***************");

			boolean b = client.storeFile(remotepath, localpath);
			System.out.println("b " + b);
			// LoggerSample.info("the uploading file to  server   status is " +
			// b);
			uploadStatus = b;
			// LoggerSample.info("ftp upload sign pdf ended and status is in sendsendToFTP(())"
			// + uploadStatus);
			return uploadStatus;
		} catch (Exception e) {
			// LoggerSample.error(e);
			uploadStatus = false;
			return uploadStatus;
		}
	}
    
@RequestMapping(value="/dtcpPdf.do",method=RequestMethod.GET)
    
   public ModelAndView getdtcpPdf()
   {
	   return new ModelAndView("DtcpPdfReport");
   }
   
   
   @RequestMapping(value = "/dtcpPdfReport.do", method = RequestMethod.POST)
   public String acknowledgement(
   		@RequestParam(value = "uniquerequestid", required = true) long uniquerequestid,
   		// @RequestParam(value = "ApplFlag", required = true) String
   		Model model, HttpServletRequest request,
   		HttpServletResponse response) throws JRException, IOException,
   		NamingException {
   	System.out.println(">>>>>>>>>>>>" + uniquerequestid);
   	//AssessmentMaster application = assessmentservice.getsinglerecord(Long.parseLong(uniquerequestid));
DtcpApplication app=assessmentReportService.getDtcpPdfReport(uniquerequestid);

   	try {

   		HashMap<String, Object> hmParams = new HashMap<String, Object>();

   		   		String fileName = "AppAck";

   		
   		File reportFile = new File(request.getSession().getServletContext()
   				.getRealPath("/jasper/" + fileName + ".jasper"));

   		JasperCompileManager.compileReportToFile(
   				request.getSession().getServletContext()
   						.getRealPath("/jasper/" + fileName + ".jrxml"),
   				request.getSession().getServletContext()
   						.getRealPath("/jasper/" + fileName + ".jasper"));

   		JasperReport jasperReport = (JasperReport) JRLoader
   				.loadObjectFromFile(reportFile.getPath());

   		new ReportDAO().jasperPDF(jasperReport, hmParams, response,	String.valueOf(uniquerequestid));
   	} catch (Exception e) {
   		System.out.println("Exception::" + e.getMessage());
   	} finally {

   	}

   	return null;
   }

}