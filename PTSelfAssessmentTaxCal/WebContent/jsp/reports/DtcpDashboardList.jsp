<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
<title>Insert title here</title>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
</head>
<body>
	<div class="container">


		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">District Wise List View</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">



						<!-- <a href="getAssmntByAllUlbWise.do" -->
						
						<hr style="border-color: #5dd5f5; margin-top: 45px"></hr>
						<!-- <div class="col-md-2"></div> -->
						<div class="col-md-12" align="center">
							<table id="gridview"
								class="table table-condensed table-hover table-striped display cell-border"
								style="color: #00000F;">
								<thead>
									<tr style="background-color: #b9e9ff; font-size: 14px">
									<th align="center" class="col-md-2">S NO.</th>
										<th align="center" class="col-md-4">District Name</th>
										<th align="center" class="col-md-4">Total Request</th>
										<th align="center" class="col-md-2">Processed</th>
										<th align="center" class="col-md-2">Unprocessed</th>
								</thead>
								<tbody>
									<c:forEach items="${dtcpl}" var="dtcpl" varStatus="valCount">
										<tr style="font-size: 13px; color: #00000F;">
											
											<%-- <td style="text-align: center;">
											<a href="getAssmntBySelectedDistrictWise.do?dist_id=${asmntList[0]}"
												style="color: #00000F; font-weight: bold;">${asmntList[1]}
											</a></td> --%>
											<td style="text-align: center;">${valCount.index+1}</td>
											<td style="text-align: center;">
											<a href="/Taxcal/getDtcpDashboardUlbDistrict.do?districtid=${dtcpl[0]}"> ${dtcpl[1]}</a></td>
											<%-- <td style="text-align: center;">${asmntList[2]}</td> --%>

											<td style="text-align: center;">${dtcpl[2]}</td>

											<td style="text-align: center;">${dtcpl[3]}</td>
											<td style="text-align: center;">${dtcpl[4]}</td>
											
											</tr>
									</c:forEach>
								</tbody>

								
							</table>
						</div>
						<!-- <div class="col-md-2"></div> -->
						</div></div></div></fieldset></div>
</body>
</html>