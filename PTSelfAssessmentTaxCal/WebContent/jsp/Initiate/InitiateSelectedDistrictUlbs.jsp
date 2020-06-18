
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CDMA Assessment Application</title>
<%
	String path = request.getContextPath();
%>
</head>

<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>

<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
<style>
.style1 {
	text-align: center;
}

blink {
	-webkit-animation: blink 1s steps(50000, start) infinite;
	-moz-animation: blink 1s steps(50000, start) infinite;
	-o-animation: blink 1s steps(50000, start) infinite;
	animation: blink 1s steps(50000, start) infinite;
}

@
-webkit-keyframes blink {to { visibility:hidden;
	
}

}
@
-moz-keyframes blink {to { visibility:hidden;
	
}

}
@
-o-keyframes blink {to { visibility:hidden;
	
}

}
@
keyframes blink {to { visibility:hidden;
	
}
}
</style>

<body>

	<div class="container">


		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Self Assessment Initiate Dashboard</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">

							<hr style="border-color: #5dd5f5; margin-top: 45px"></hr>
							<div class="col-md-6 col-md-pull-1 col-md-offset-4" align="center" >
								<table id="gridview" 
									class="table table-condensed table-hover table-striped display cell-border"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											 <th align="center" style="width: 10px;text-align: center;">S.NO</th> 
				                            <th style="text-align: center;" class="col-md-6">ULB Name</th>
				                            <th style="text-align: center;" class="col-md-6">Duplicate Assessment List</th>						
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${DistrictULbs}" var="DistrictULbs"	varStatus="theCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;">${theCount.count}</td>
												<td style="text-align: center;"><a
													href="getInitiateSelectedUlb.do?ulbcode=${DistrictULbs[0]}"
													style="color: #00000F; font-weight: bold;">${DistrictULbs[1]}
												</a></td>
												<td style="text-align: center;"><a
													href="getInitiateSelectedUlbDuplicateAss.do?ulbcode=${DistrictULbs[0]}"
													style="color: #00000F; font-weight: bold;">Duplicate
												</a></td>
											</tr>
										</c:forEach>
									</tbody>

								</table>
							</div></div></div></div></fieldset></div>
							</body>


<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>





</html>

