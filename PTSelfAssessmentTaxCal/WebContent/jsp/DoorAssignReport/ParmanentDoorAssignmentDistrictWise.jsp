
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
		var t = $('#gridview').DataTable({
			"columnDefs" : [ {
				"searchable" : true,
				"orderable" : false,
				"targets" : 0
			} ],
			"order" : [ [ 1, 'asc' ] ],
			"lengthMenu" : [ [ -1 ], [ "All" ] ]
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


<script type="text/javascript">
	$(document).ready(function() {
		var fromDate = $("#entryTo").datepicker({
			defaultDate : "+0w",
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
		//minDate : 0,

		});
		var toDate = $("#entryFrom").datepicker({
			defaultDate : "+0w",
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
		//minDate : 0,

		});

		var toDate = $("#entry_date").datepicker({
			defaultDate : "+0w",
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
		//minDate : 0,

		});

	});
</script>



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
				<div class="panel-heading">Door Number Assigned/Non-Assigned Dashboard</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						
						<c:if test="${repotype == 'P_DoorNo'}">

				
							<br>
							<hr style="border-color: #5dd5f5; margin-top: 45px"></hr>
							<!-- <div class="col-md-2"></div> -->
							<div class="col-md-12" align="center">
								<table id="gridview" title="${date_interval}"
									class="table table-condensed table-hover table-striped display cell-border"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											<th align="center" style="width: 45px;">S.NO</th> 
				                            <th style="text-align: center;" class="col-md-6">Total Application</th>
											<th style="text-align: center;" class="col-md-4">District</th>
											<th style="text-align: center;" class="col-md-4">Assigned Door No</th>
											<th style="text-align: center;" class="col-md-4">NonAssigned Door No</th>
											
										</tr>
									</thead>
									<tbody>
										<c:set var="totalApp" value="0"></c:set>
									
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;"></td>
												<td style="text-align: center;"><a
													href="getAssmntBySelectedDistrictWise.do?dist_id=${asmntList[0]}"
													style="color: #00000F; font-weight: bold;">${asmntList[1]}
												</a></td>

												<td style="text-align: center;">${asmntList[6]}</td>
											
												
												
												<c:set
														var="totalApp" value="${totalApp+asmntList[6]}"></c:set> 
					
											</tr>
										</c:forEach>
									</tbody>

									<tfoot>
										<tr style="background-color: #f3f8fa">
											<th style="text-align: right; border: 1px solid #9ee4fe;"
												colspan="2">Total:</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${totalApp}</th>

											
										</tr>
									</tfoot>

								</table>
							</div>
							<!-- <div class="col-md-2"></div> -->
						</c:if>

						<%-- <c:if test="${repotype == 'sel_district'}">

							<a href="getAssmntByAllUlbWise.do"
								style="color: maroon; text-decoration: none; font-weight: bold; text-align: left; float: left; font-size: 13px"><blink>Click
									to View Assessment Dashboard Ulb Wise</blink> </a>
							<br>
							<hr style="border-color: #5dd5f5;"></hr>
							<div class="col-md-12">
								<table id="gridview" title="${date_interval}"
									class="table table-condensed table-hover table-striped display cell-border"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											<th align="center" style="width: 45px;">S.NO</th>
											<th style="text-align: center;" class="col-md-6">Total Application</th>
											<th style="text-align: center;" class="col-md-4">District</th>
											<th style="text-align: center;" class="col-md-4">Assigned Door No</th>
											<th style="text-align: center;" class="col-md-4">NonAssigned Door No</th>
											
										
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;"></td>
												<td style="text-align: center;"><a
													href="getAssmntBySelectedUlbWise.do?ulb_id=${asmntList[0]}"
													style="color: #00000F; font-weight: bold;">${asmntList[1]}</a>

												</td>
												<td style="text-align: center;">${asmntList[6]}</td>
												
											</tr>
										</c:forEach>
									</tbody>

								</table>
							</div>
						</c:if>
 --%>
					</div>
				</div>
			</div>
		</fieldset>
	</div>

	<!-- <input type="text" id="distId" name="distId">	 -->
</body>


<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>

<script type="text/javascript">
	function checkValidation() {

		var entryFrom = document.assmtReport.entryFrom.value;
		var entryTo = document.assmtReport.entryTo.value;

		if (entryFrom == '' || entryFrom == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Assessment  Application From Date";
			document.getElementById("entryFrom").focus();
			return false;
		}
		if (entryTo == '' || entryTo == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Assessment Application To Date";
			document.getElementById("entryTo").focus();
			return false;
		}
		return true;
	}
	function checkValidation1() {

		var entry_date = document.assmtReport1.entry_date.value;

		if (entry_date == '' || entry_date == null) {
			document.getElementById("errorBox1").innerHTML = "Please Enter Assessment  Application  Date";
			document.getElementById("entry_date").focus();
			return false;
		}

		return true;
	}
</script>



</html>

