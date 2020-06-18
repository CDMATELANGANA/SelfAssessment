<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
<body>
<div class="container">


		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">DTCP
					Dashboard</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						<div class="col-md-2"></div>
						<div class="col-md-8" align="center">
							 <a href="getDtcpDashboardList.do"> 
							<c:forEach items="${dtcptotal}" var="dtcptotal">
									<table id="gridview" title=""
										class="table table-condensed table-hover table-striped display cell-border"
										style="color: #00000F;">


										<!-- 	<thead>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-4" colspan="2">VLT Dashboard</th>
												
											</tr>
										</thead> -->


										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Total Application</th>
												<th align="center" class="col-md-6">${dtcptotal[0]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Processed</th>
												<th align="center" class="col-md-6">${dtcptotal[1]==null?'0':dtcptotal[1]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Unprocessed</th>
												<th align="center" class="col-md-6">${dtcptotal[2]==null?'0':dtcptotal[2]}</th>
											</tr>
										</tbody>

										

										<tbody>
											<tr>
												<th align="center" class="col-md-12" colspan="2"></th>
											</tr>
										</tbody>
									</table>
								</c:forEach> </a>
						</div>
						<div class="col-md-2"></div>
					</div>
				</div>
			</div>
			</fieldset>
			</div>

</body>
</html>