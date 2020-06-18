
<html>
<head>
<!-- <p><a href="getassessmentlink.do">Link Aadhaar</a></p> -->
<!-- <p>  <a href="getreport.do"> Report</a></p>
   <p><a href="livereport.do">Live Report</a></p> -->
<%
	String path = request.getContextPath();
%>

<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>

<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>
<style type="text/css">
hr.new1 {
	border: 1px solid black;
}

#a {
	display: block;
	padding: 0;
	background: #b9f6ca;
}

#a:hover {
	color: #fff;
	background: #ffff00;
}

#b {
	display: block;
	padding: 0;
	background: #ffff00;
}

#b:hover {
	color: #fff;
	background: #c6ff00;
}

#c {
	display: block;
	padding: 0;
	background: #e1bee7;
}

#c:hover {
	color: #fff;
	background: #c6ff00;
}

#d {
	display: block;
	padding: 0;
	background: #ffbb33;
}

#d:hover {
	color: #fff;
	background: #ffff00;
}

#e {
	display: block;
	padding: 0;
	background: #ffff00;
}

#e:hover {
	color: #fff;
	background: #c6ff00;
}

ul {
	padding: 0;
	list-style: none;
	background: #ffbb33;
}

ul li {
	display: inline-block;
	position: relative;
	line-height: 15px;
	text-align: left;
}

ul li a {
	display: block;
	padding: 4px 20px;
	color: #333;
	text-decoration: none;
	background: #ccff90;
}

ul li a:hover {
	color: #fff;
	background: #007E33;
}

ul li ul.dropdown {
	min-width: 100%; /* Set width of the dropdown */
	background: #f2f2f2;
	display: none;
	position: absolute;
	z-index: 999;
	left: 0;
}

ul li:hover ul.dropdown {
	display: block; /* Display the dropdown */
}

ul li ul.dropdown li {
	display: block;
}
</style>


</head>
<!-- <p>
	<a  style="text-decoration:none" href="gettax.do">Calculate Tax</a>
</p> -->
<%-- <c:if test="${repotype=='otpId'}">
<p>
	<a  style="text-decoration:none" href="getassessment.do">New PT Self Assessment Application</a>
</p>
</c:if>
 --%>

<p id="a">
	<a style="text-decoration: none" href="otp.do"><font color="black"><b>New
				PT Self Assessment Application</b></font></a>
</p>
<!--  <p id="b">
	<a  style="text-decoration:none" href="getSearch.do"><font color="black">Search Application</font></a>
</p>
 -->

<hr class="new1">

<!-- <p>
	<a  style="text-decoration:none" href="getassessmentCMS.do">New PT Assessment ApplicationCMS</a>
</p>
<p>
	<a  style="text-decoration:none" href="getassessmentMob.do">New PT Assessment ApplicationMob</a>
</p> -->
<p id="c">
	<a style="text-decoration: none" href="getNewDashboard.do"><font
		color="black">New Self Assessment Dashboard</font></a>
</p>
<p id="c">
	<a style="text-decoration: none" href="getOldDashboard.do"><font
		color="black">OldSelf Assessment Dashboard</font></a>
</p>

<!-- <p>
	<a  style="text-decoration:none" href="getPTCalculatorDashboard.do">PT Calculator Dashboard</a>
</p> -->

<!-- <p>
	<a  style="text-decoration:none" href="getDashboardCMS.do">Dashboard CMS</a>
</p>
<p>
	<a  style="text-decoration:none" href="getDashboardMob.do">Dashboard Mob</a>
</p> -->
<p id="c">
	<a style="text-decoration: none" href="getAssmntByAllDistrictWise.do"><font
		color="black">Assessment Report District Wise</font></a>

</p>
<hr class="new1">

<p id="d">
	<a style="text-decoration: none"
		href="http://epaycdma.telangana.gov.in:8080/ePayPortal/cdma/getYourDues.do"
		target="_blank"><font color="black">Know Your Property Tax
			Dues</font></a>
</p>

<ul>
	<li><b> Download Property Tax Detail&#9662;</b>
		<ul class="dropdown">
			<li><a href="getSearchAssessmentCopy.do">Property Tax
					Certified Copy </a></li>
			<li><a href="getSearchAssessmentDemand.do">Property Tax
					Demand Notice Copy</a></li>
			<li><a href="getSearchAssLastPaidReceipt.do">Property Tax
					Last Paid Receipt</a></li>
			<li><a href="getSearchAssPtHistory.do">Property Tax
					Transaction History </a></li>
			<!--  <li><a href="http://epaycdma.telangana.gov.in:8080/ePayPortal/cdma/assessmentReceipt.do" target="_blank">Complete PTAX Paid History </a></li> -->
			<li><a
				href="http://epaycdma.telangana.gov.in:8080/ePayPortal/cdma/getYourDues.do"
				target="_blank">Know Your Property Dues </a></li>
		</ul></li>
</ul>

<!-- new implementation -->
<!-- <p>
			<a href="ptSelfAsmntTransactionReport.do">PT SelfAssessment Transaction Report</a>
		</p>
		<p>
			<a href="ptSelfAsmntTransactionStatusReport.do"> PT SelfAssessment Transaction Status Report</a>
		</p> -->

<!-- <p>
	<a  style="text-decoration:none" href="getAssmntByAllDistrictWiseCMS.do">Assessment Report District
		WiseCMS</a>
</p>
<p>
	<a  style="text-decoration:none" href="getAssmntByAllDistrictWiseMob.do">Assessment Report District
		WiseMob</a>
</p> -->

<hr class="new1">
<p id="b">
	<a style="text-decoration: none" href="" target="_blank"><font
		color="black">PT Self Assessment Procedure</font></a>
</p>
<p id="e">
	<!-- <a  style="text-decoration:none" href="#" target="_blank">Feedback/Grievance</a> -->
	<a style="text-decoration: none" href="#"><font color="black">Feedback/Grievance</font></a>
<p id="e">
	<a style="text-decoration: none"
		href="/PTSelfAssessmentTaxCal/selfassessment.webm"><font
		color="black">Video Tutorial For PT Self Assessment</font></a>
</p>
<hr class="new1">

<!-- <p>
	<a  style="text-decoration:none" href="#">GWMC Self Property Tax Certification Assessment application</a>
</p>

<p>
	<a  style="text-decoration:none" href="http://gwmc.gov.in/TaxCalculation.aspx" target="_blank">GWMC
		Property tax Calculator</a>
</p> -->


<c:set var="usermenu" value="1" scope="page" />
<c:forEach var="report" items="${permissionSession}">
	<c:if
		test="${(report.key ==  'revenuedashboard') ||  (report.key ==  'revenuedashboard')}">
		<c:if test="${reportmenu==1}">
			<hr style="border-color: #9999FF;">
			<c:set var="reportmenu" value="0" scope="page" />
		</c:if>

		<!-- 			 <a href="revenue.do">Dashboard-</a>
 -->
		<!-- <p>
			<a  style="text-decoration:none" href="getSearch.do">Search Application Status</a>
		</p> -->
		<!-- <p>
			<a  style="text-decoration:none" href="tradeReport.do">Trade Report ULB Wise</a>
		</p>-->
		<p>
			<a style="text-decoration: none" href="tradeReportDistrictWise.do">
				District Wise</a>
		</p>




	</c:if>
</c:forEach>

<c:forEach var="usermn" items="${permissionSession}">
	<c:if
		test="${(usermn.key ==  'user') ||  (usermn.key ==  'User') 
		|| (usermn.key ==  'user group') ||  (usermn.key ==  'User Group')}">

		<c:if test="${usermenu==1}">
			<hr style="border-color: #9999FF;">
			<c:set var="usermenu" value="0" scope="page" />
		</c:if>

		<c:if test="${(usermn.key ==  'user') ||  (usermn.key ==  'User')}">
			<p>
				<a style="text-decoration: none" href="UlbDetailReport.do">UlbReport</a>

			</p>

			<p>
				<a href="ptSelfAsmntTransactionReport.do">PT SelfAssessment
					Transaction Report</a>
			</p>
			<p>
				<a href="ptSelfAsmntTransactionStatusReport.do"> PT
					SelfAssessment Transaction Status Report</a>
			</p>
			
			<p>
				<a href="ptAddMeasurementSelfAsmntTransactionReport.do">PT Add Measurement
					Transaction Report</a>
			</p>
			<p>
				<a href="ptAddMeasurementSelfAsmntTransactionStatusReport.do"> PT
					Add Measurement Transaction Status Report</a>
			</p>
			<p>
				<a style="text-decoration: none" href="getInitiateDistrict.do">Initiate
					Dashboard </a>
			</p>

			<!-- <p>
	<a  style="text-decoration:none" href="findDistCode.do" target="_blank">New Report
		</a>
</p>
 -->

			<!-- <p>
				<a  style="text-decoration:none" href="user.do">Users</a>
			</p> -->
		</c:if>
	</c:if>
	<c:if
		test="${(usermn.key ==  'revenuedashboard') ||  (usermn.key ==  'revenuedashboard') 
		|| (usermn.key ==  'user group') ||  (usermn.key ==  'User Group')}">

		<c:if test="${usermenu==1}">
			<hr style="border-color: #9999FF;">
			<c:set var="usermenu" value="0" scope="page" />
		</c:if>

		<c:if
			test="${(usermn.key ==  'revenuedashboard') ||  (usermn.key ==  'revenuedashboard')}">
			<p>
				<!-- <a  style="text-decoration:none" href="UlbDetailReport.do">UlbReport</a> -->
			</p>
			<!-- <p>
	<a  style="text-decoration:none" href="findDistCode.do" target="_blank">New Report
		</a>
</p>
 -->
			<!-- <p>
	<a  style="text-decoration:none" href="dtcpDashboard.do" target="_blank">Dtcp Dashboard
		</a>
</p> -->

		</c:if>
	</c:if>

</c:forEach>


</body>
</html>
