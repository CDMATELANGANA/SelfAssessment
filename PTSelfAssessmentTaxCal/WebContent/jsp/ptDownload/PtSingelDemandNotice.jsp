<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PT Selfassessment download Page</title>
<style type="text/css">
.error {
	color: red;
}

.success {
	color: green;
}

.col-md-6 {
	padding-top: 4px;
	padding-bottom: 10px;
}

input:focus {
	border: 2px solid #337ab7;
	border-radius: 4px;
}

.miniheader {
	background-color: #2e6da4;
	padding-bottom: 3px;
	color: #fff;
	font-size: large;
}

.headdiv {
	padding-top: 3px;
}

.minifont {
	font-size: smaller;
}
</style>
</head>
<body>
	
			<form action="" name="applicationstaus" method="post">
			

 			<div class="col-md-12">
				<div class="col-md-4"></div>
					<div class="col-md-6">
				<a
			style="text-decoration: none; font-size: 18px; font-weight: bold;"
			href="${pageContext.request.contextPath}/assessmentDemandNotice.do?assessment=<c:out value="${requestScope.assessment}"/>"
			target="_blank"> Download Assessment Demand Notice
					</a>
				</div>
				
		</div>
		</form>
	
</body>
</html>