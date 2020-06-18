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
			
<%-- 
<div class="container">
<div class="row">
<div class="col-md-10 col-md-pull-2 col-md-offset-2">
  <div class="panel-group">
    <div class="panel panel-primary">
      <div class="panel-heading">Assessment No. :${requestScope.assessment}</div>
      <div class="panel-body">
 <div class="col-md-2">
		Name  :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.surname}${requestScope.name}"/>
</div>

 <div class="col-md-2">
		S/o/W/o :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.fhsurname}${requestScope.fhname}"/>
</div>
<div class="col-md-2">
		District :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.district}"/>
</div>

<div class="col-md-2">
		Muncipality :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.muncipality}"/>
</div>

<div class="col-md-2">
		Door No. :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.door}"/>
</div>
<div class="col-md-2">
		ULB Name :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.ulbname}"/>
</div>

<div class="col-md-2">
		Locality  :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.locality}"/>
</div>


<div class="col-md-2">
		Tax Amount (Half Year) :
 </div>
<div class="col-md-4">
<c:out value="${requestScope.taxamt}"/>
</div>
</div></div></div></div> --%>
<h4 align="center"><c:out value="${requestScope.ulbname}"/> <c:out value="${requestScope.muncipality}"/></h4>
<h4 align="center"><c:out value="${requestScope.district}"/> District</h4>
<h4 align="center"><u>Assessment Register Certificate</u></h4><br><br>
<p>Sequence No:<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${requestScope.seqno}"/>&nbsp;&nbsp;/2019</b>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Date: <b><c:out value="${requestScope.cdate}"/></b> </p>    <br><br>
<p align="center">This is to certify that the house bearing Door No.<b><c:out value="${requestScope.door}"/></b> and Assessment No.<b><c:out value="${requestScope.ulbname}"/></b> situated at <b><c:out value="${requestScope.locality}"/></b>
within <b><c:out value="${requestScope.ulbname}"/></b> Muncipality is stands on the name of Sri/Smt:<b><c:out value="${requestScope.surname}${requestScope.name}"/></b> S/o/W/o : <b><c:out value="${requestScope.fhsurname}${requestScope.fhname}"/></b> as per the
available records of the Muncipality.</p><br>
<p> To</p>
<p> Sri/Smt :&nbsp;&nbsp;&nbsp;<c:out value="${requestScope.surname}${requestScope.name}"/></p>
<p> Door No. :&nbsp;&nbsp;&nbsp;<c:out value="${requestScope.door}"/></p>
<p>Locality :&nbsp;&nbsp;&nbsp;<c:out value="${requestScope.locality}"/></p>
<p> Taxdetail:&nbsp;&nbsp;&nbsp;<c:out value="${requestScope.taxamt}"/></p>
<p align="right">This document is produced electronically and therefore does not
require a signature.
</p>
 			<div class="col-md-12">
				<div class="col-md-4"></div>
					<div class="col-md-6">
				<a
			style="text-decoration: none; font-size: 18px; font-weight: bold;"
			href="${pageContext.request.contextPath}/assessmentRegCopy.do?assessment=<c:out value="${requestScope.assessment}"/>"
			target="_blank"> Download Assessment Register Certificate
					</a>
				</div>
				
		</div>
		</form>
	
</body>
</html>