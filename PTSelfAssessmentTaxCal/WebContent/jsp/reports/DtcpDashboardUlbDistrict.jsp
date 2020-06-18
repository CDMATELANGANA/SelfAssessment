<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false"%>
<%String path=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 -->
	<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
	
<title>Dtcp Dashboard Ulb Wise Report</title>
</head>
<body>
<div class="container">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">District List View</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						
						
						<hr style="border-color: #5dd5f5; margin-top: 45px"></hr>

					<div class="table-responsive" align="center">
						<table id="table"
							class="table  table-striped table-bordered table-hover"
							style="color: #00000F;">
							<thead>
							  <tr style="background-color: #b9e9ff; font-size: 13px">
							  		<th align="center" style="width: 45px;">S No.</th>
									<th align="center" style="width: 45px;">ULB Name</th>
									<th align="center" class="col-md-4 text-center">Total Request</th>
									<th align="center" class="col-md-2">Processed</th>
									<th align="center" class="col-md-2">Unprocessed</th>
								
								</tr>  
							</thead>
							<tbody>
							 <c:forEach var="dtcp" items="${dtcp}" varStatus="valCount"> 
							 <tr style="font-size: 13px; color: #00000F;">
							 <!--  <th align="center" style="width: 45px;">id</th> -->
									<%-- <td align="center" class="col-md-4 text-center">${dtcp.dtcpfilenumber}</td> --%>
									<td align="center" class="col-md-2">${valCount.index+1}</td>
										
									<td align="center" class="col-md-2">		
																							
									<a href="/Taxcal/getDtcpDashboardUlbWise.do?ulbid=${dtcp[0]}">${dtcp[1]}</a></td>
									</a></td>
									<td align="center" class="col-md-2">${dtcp[2]}</a></td>
									<td align="center" class="col-md-2">${dtcp[3]}</td>
									<td align="center" class="col-md-2">${dtcp[4]}</td>
									<%-- <td align="center" class="col-md-2">${dtcp[3]}</td> --%>
							 </c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				</div>
		
		</div>
	</div>
	</fieldset></div>
</body>
</html>