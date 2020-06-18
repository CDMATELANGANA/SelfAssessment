<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%String path=request.getContextPath(); %>

 
<title>Dtcp Dashboard Ulb Wise Report</title>
 
<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>
 
<script type="text/javascript">
	/* 	$(document).ready(
	 function() {
	 $('#gridview').DataTable(
	 {
	 "lengthMenu" : [ [ 15, 25, 50, 80, -1 ],
	 [ 15, 25, 50, 80, "All" ] ]
	 });
	 }); */
	$(document).ready(function() {
		var t = $('#table').DataTable({
			"columnDefs" : [ {
				"searchable" : true,
				"orderable" : false,
				"targets" : 0
			} ],
			"order" : [ [ 1, 'asc' ] ],
			//"lengthMenu" : [ [ -1 ], [ "All" ] ]
		
			"lengthMenu" :[ [25,50, 100, -1 ],
				 [25,50, 100, "All" ] ]
		});

		t.on('order.dt search.dt', function() {
			t.column(0, {
				search : 'applied',
				order : 'applied'
			}).nodes().each(function(cell, i) {
				cell.innerHTML = i + 1;
			});
		}).draw();

	});
</script>
</head>
<body>

<div class="container">
		<!-- <div class="panel-heading"></div>
		<div class="panel-body"> -->
<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">ULB List View</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">



						<!-- <a href="getAssmntByAllUlbWise.do" -->
						
						<hr style="border-color: #5dd5f5; margin-top: 45px"></hr>
			


					<div class="table-responsive" align="center">
						<table id="table"
							class="table  table-striped table-bordered table-hover"
							style="color: #00000F;">
							<thead>
							  <tr style="background-color: #b9e9ff; font-size: 13px">
									<th align="center" style="width: 45px;">S.NO</th>
									<th align="center" class="col-md-4 text-center">Dtcp File Number</th>
									<!-- <th align="center" class="col-md-2">aadharnumber</th> -->
									<th align="center" class="col-md-2">Emas Genarate Reg No</th>
									<th align="center" class="col-md-2">Entry_date</th>
 									<th align="center" class="col-md-2">Mobile Number</th> 
									<th align="center" class="col-md-2">Owner City</th>
									<th align="center" class="col-md-2">Owner Name</th>
									<th align="center" class="col-md-2">Relation Name</th>
									<th align="center" class="col-md-2">Relation Surname</th>
  									<th align="center" class="col-md-2">Surname</th>
  									<th align="center" class="col-md-2">Download Report</th>
	 							
								</tr>  
							</thead>
							<tbody>
							 <c:forEach var="dtcp" items="${dtcp}" varStatus="valCount"> 
							 <tr style="font-size: 13px; color: #00000F;">
							 <!--  <th align="center" style="width: 45px;">id</th> -->
									<%-- <td align="center" class="col-md-4 text-center">${dtcp.dtcpfilenumber}</td> --%>
									<td align="center" class="col-md-2">${dtcp[0]}</td>
									
									
									<c:choose>  
    								<c:when test="${dtcp[22]==89}"> 
									<!-- http://localhost:8080 -->
									<td align="center" class="col-md-2"><a href="/Taxcal/selfAssmentData.do?id=${dtcp[0]}">${dtcp[1]}</a></td>
									</c:when>
									<c:otherwise>
									<td align="center" class="col-md-2">${dtcp[1]}</td>
									</c:otherwise>  
									</c:choose> 
									<td align="center" class="col-md-2">${dtcp[3]}</td>
									<td align="center" class="col-md-2">${dtcp[4]}</td>
									<td align="center" class="col-md-2">${dtcp[7]}</td> 
									<td align="center" class="col-md-2">${dtcp[11]}</td>
									<td align="center" class="col-md-2">${dtcp[12]}</td>
									<td align="center" class="col-md-2">${dtcp[14]}</td>
									<td align="center" class="col-md-2">${dtcp[15]}</td>
									<td align="center" class="col-md-2">${dtcp[18]}</td>
									<td align="center" class="col-md-2">
									<a href="/Taxcal/UlbDetailReportDownloadCeriticate.do?id=${dtcp[0]}">
									<img border="0"
															src="${pageContext.request.contextPath}/jsp/images/Download.png"
															alt="Download Details" value="Download Details"
															width="60" height="25"></a></td>
									  
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