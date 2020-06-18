<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*" %>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%String path=request.getContextPath(); %>
        <%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
  <link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>

<title>TAX CALCULATOR</title>
<style type="text/css">
.h2{
font-size: 30px;
}
p.double {border-style: double;}
</style>
<style type="text/css">
 .error{
 color: red;
 }
 .success{
 color: green;
 }
 .col-md-6{
 padding-top:4px;
 padding-bottom: 10px;
 }
 
 input:focus {
  border: 2px solid #337ab7;
    border-radius: 4px;
}
.miniheader{

background-color:#2e6da4;
padding-bottom: 3px;
color: #fff;
font-size: large;
}
.headdiv{
padding-top:3px;
}
.minifont{
font-size: smaller;
}
.tableborder{
border:2px solid #337ab7
}
 </style> 
</head>
<%-- <h2 style=
 
    "background: #782669;
    position: relative;
    width: 100%;
    
     padding-top: 0px;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 30px;
    font-weight: bold;
    color:white;
    text-align:center;
    top: 10;
    height: 200px;">
<img src="<%=path %>/images/cdma.png">
Commissioner & Director of Municipal Administration<br>
Government of Telangana</h2> --%>

<body>
<p align="right">
<a  href="<%=path %>/gettax.do">Home</a></p>
<h2 style="text-align:center; color:purple;"><b>PROPERTY TAX CALCULATOR</b></h2>
 <form class="form-horizontal"><center>
 <div class="container_6 " style="border-style: double;">
  <table class="table">
					<thead>
						<tr>
							<h2 style="text-align:center;">	 District : <%=session.getAttribute("Dist")%>
							</h2></tr>
							<tr>
							<h2 style="text-align:center;">ULB
									Name : <%=session.getAttribute("ULBname")%> </h2> 
						</tr>
					</thead>
					
					
					
					<%-- <div class="error" align="center">${requestScope.Checkflag}</div> --%>
					
					
					
					<tbody>
					
					
					<tr>
							<td><label>Locality name :</label></td>
							<td><%=session.getAttribute("locality")%></td>
						</tr>
						<tr>
							<td><label>Zone name :</label></td>
							<td><%=session.getAttribute("zname")%></td>
						</tr>
						<!-- 2 -->
						<tr>
							<td>
								<label>Classification of Building::</label></td>
							<td>
								<%=session.getAttribute("cname")%></td>
						</tr>
						<!-- 2 -->
						<tr>
							<td>
								<label> Building usage:</label></td>
							<td>
								<%=session.getAttribute("uname")%></td>
						</tr>
						<!-- 3 -->
						<tr>
							<td>
								<label>Occupant Type :</label>
						<td>
								<%=session.getAttribute("ocptype")%></td>
						</tr>
						<!-- 4 -->
						<tr>
							<td>
								<label>Building Age:(No.of Years)</label>
							<td>
								<%=session.getAttribute("bage")%></td>
						</tr>
						<!-- 5-->
						<tr>
							<td>
								<label>Length :</label></td>
							<td>
								<%=session.getAttribute("len")%>&nbsp;Mtrs</td>
						</tr>
						<!-- 5 -->
						<tr>
							<td>
								<label>Width :</label></td>
							<td>
								<%=session.getAttribute("wid")%>&nbsp;Mtrs</td>
						</tr>
						
						<c:choose><c:when test="${requestScope.Checkflag=='Y'}">
                   <tr>
							<td><div class="error" align="center">${requestScope.errormsg}</div></td><td></td>
							</tr>
                 </c:when>
                 <c:otherwise>
                    <!-- 7 -->
						<tr>
							<td>
								<label>NET ANNUAL RENTAL VALUE :(ARV)</label></td>
							<td>
								<%=session.getAttribute("nrv") %></td>
						</tr>
						<!-- 7 -->
						<tr>
							<td>
								<label>TOTAL PROPERTY TAX:(For 6 Months)</label></td>
							<td>
								<%=session.getAttribute("ptp") %>
							</td>
						</tr> 
                 </c:otherwise>
              </c:choose>
              
						
 </tbody>
 </table>
 </div>
 </center>
<%--  <table class="table">
 
 
 <tr><td><b>Classification of Building:</b></td><td><%=session.getAttribute("cname")%></td></tr>
 <tr><td><b>Building Usage Type :</b></td><td><%=session.getAttribute("uname")%></td></tr>
 <tr><td><b>Occupant Type :</b></td><td><%=session.getAttribute("ocptype")%></td></tr>
 <tr><td><b>Building Age:</b></td><td><%=session.getAttribute("bage1")%></td></tr>
<tr><td><b> Length :</b></td><td><%=session.getAttribute("len")%></td></tr>
<tr><td><b> Width :</b></td><td><%=session.getAttribute("wid")%></td></tr>
<tr><td><b> NET ANNUAL RENTAL VALUE :</b>  </td><td><%=session.getAttribute("nrv") %></td></tr>
<tr><td><h4 style="color:green;"><b> TOTAL PROPERTY TAX:</b></h3> </td><td><h4 style="color:green;"><b><%=session.getAttribute("ptp") %></b></h4></td></tr>

</table></center></div> --%>
</form>
</body>
</html>