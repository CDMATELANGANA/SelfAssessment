
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
							<div class="col-md-12" align="center" >
								<table id="gridview" 
									class="table table-condensed table-hover table-striped display cell-border"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											 <th style="text-align: center;" class="col-md-1">S.NO</th> 
				                            <th style="text-align: center;" class="col-md-1">ULB Name</th>
				                            <th style="text-align: center;" class="col-md-1">Request Number</th>
				                            <th style="text-align: center;" class="col-md-1">Assessment No.</th>
				                            <th style="text-align: center;" class="col-md-1">Permanent Door no.</th>
				                            <th style="text-align: center;" class="col-md-1">Application stage</th>		
				                            <th style="text-align: center;" class="col-md-1">Initiate</th>					
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ulbDetailList}" var="ulbDetailList"	varStatus="theCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;">${theCount.count}</td>
												<td style="text-align: center;">${ulbDetailList[0]}</td>
												<td style="text-align: center;">										
												${ulbDetailList[1]}</td>
												<td style="text-align: center;">${ulbDetailList[2]}</td>
												<td style="text-align: center;">${ulbDetailList[3]}</td>
												<td style="text-align: center;">${ulbDetailList[4]}</td>
												<!-- <td style="text-align: center;">initiate</td> -->
												<td style="text-align: center;"><a
													href="updateInitiateTransactionStatusDB2.do?trans_id=${ulbDetailList[6]}&&uniqReqNo=${ulbDetailList[1]}"
													style="color: #00000F; font-weight: bold;">Initiate
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

