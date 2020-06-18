package com.mars.cdma.gov.Controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mars.cdma.gov.service.AssessmentInitiateService;
import com.mars.cdma.gov.service.AssessmentReportService;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.UlbsService;

/**
 * @author SARITA
 *
 */
@Controller
public class AssessmentInitiate {
	
	@Autowired
	private AssessmentInitiateService assessmentInitiateService;
	
	@Autowired
	private DistrictsService districtsService;
	@Autowired
	private UlbsService ulbService;

	 @RequestMapping("/getInitiateDistrict.do")
		public ModelAndView getInitiateDistrict(HttpServletRequest request,
				HttpServletResponse response) {
		 List<Object[]> allDistrict=assessmentInitiateService.getInitiateAllDistrictList();
			return new ModelAndView("InitiateAllDistrictList", "allDistrict",allDistrict);
		}
	 @RequestMapping("/getInitiateSelectedDistrictUlbs.do")
		public ModelAndView getInitiateSelectedDistrictUlbs(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value = "dist_id", required = true) String districtid) {
		 List<Object[]> DistrictULbs=assessmentInitiateService.getInitiateSelectedDistrictUlbs(districtid);
			
		 return new ModelAndView("InitiateSelectedDistrictUlbs", "DistrictULbs",DistrictULbs);
		}
	 @RequestMapping("/getInitiateSelectedUlb.do")
		public ModelAndView getInitiateSelectedUlb(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value = "ulbcode", required = true) String ulbcode) throws SQLException {
		 List<String> ULbsAssmentList=assessmentInitiateService.getInitiateSelectedUlbList(ulbcode);
		 List<Object[]> ulbDetailList=assessmentInitiateService.getInitiateSelectedUlbDetailList(ULbsAssmentList, ulbcode);
		 return new ModelAndView("InitiateUlbWiseAssmentList","ulbDetailList",ulbDetailList);
		}
	 @RequestMapping("/getInitiateSelectedUlbDuplicateAss.do")
		public ModelAndView getInitiateSelectedUlbDuplicateAss(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value = "ulbcode", required = true) String ulbcode) throws SQLException {
		 List<String> ULbsAssmentDuplicateList=assessmentInitiateService.getInitiateSelectedUlbDuplicateAssList(ulbcode);
		 List<Object[]> ulbDuplicateList=assessmentInitiateService.getInitiateSelectedUlbDuplicateAssDetailList(ULbsAssmentDuplicateList, ulbcode);
		 return new ModelAndView("InitiateUlbWiseDuplicateList","ulbDuplicateList",ulbDuplicateList);
		}

}
