
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
				<div class="panel-heading">Self Assessment Dashboard</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						<c:if test="${repotype == 'district'}">
							<%-- <form method="get" action="assmtReportByDistrictByDate.do"
								name="assmtReport" id="assmtReport"
								onsubmit="return checkValidation();">
								<div class="form-group">
									<label class="col-md-2 control-label">Application From
										Date</label>
									<div class="col-md-2 inputGroupContainer">
										<div class="input-group">
											<input name="entryFrom" id="entryFrom" readonly="readonly"
												placeholder="From Date" class="form-control" type="text">

										</div>
									</div>
									<label class="col-md-2 control-label">Application To
										Date</label>
									<div class="col-md-2 inputGroupContainer">
										<div class="input-group">
											<input name="entryTo" id="entryTo" placeholder="To Date"
												readonly="readonly" class="form-control" type="text">
										</div>
									</div>
									<div class="col-md-2 input-group submit">
										<input type="submit" value="Search" />
									</div>

									<div class="input-group">
										<div class="col-md-12" align="center" id="errorBox"
											style="color: red; font-size: 14px; margin: 10px 0 10px 0;"></div>
									</div>
								</div>
							</form> --%>
						</c:if>

						<c:if test="${repotype == 'district'}">

							<a href="getAssmntByAllUlbWiseMob.do"
								style="color: maroon; text-decoration: none; font-weight: bold; text-align: left; float: left; font-size: 13px"><blink>Click
									to View Assessment Dashboard Ulb Wise</blink> </a>
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
											<th align="center" class="col-md-4">District</th>
											<th align="center" class="col-md-2">Total Assessment</th>
											<!-- <th align="center" class="col-md-2">Submited</th> -->
											<th align="center" class="col-md-2">Under RO/RI Login</th>
											<th align="center" class="col-md-2">Under Commissioner
												Login</th>
												<th align="center" class="col-md-2">Applications Approved
												</th>
											<th align="center" class="col-md-3">Applications Rejected</th>
											<th align="center" class="col-md-3">Applications Beyond 15 Days</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="totalApp" value="0"></c:set>
										<c:set var="submited" value="0"></c:set>
										<c:set var="undervarification" value="0"></c:set>
										<c:set var="underProcessComm" value="0"></c:set>
										<c:set var="approved" value="0"></c:set>
										<c:set var="rejected" value="0"></c:set>
										<c:set var="beyond15Day" value="0"></c:set>
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;"></td>
												<td style="text-align: center;"><a
													href="getAssmntBySelectedDistrictWiseMob.do?dist_id=${asmntList[0]}"
													style="color: #00000F; font-weight: bold;">${asmntList[1]}
												</a></td>

												<td style="text-align: center;">${asmntList[6]}</td>
												<%-- <td style="text-align: center;">${asmntList[2]}</td> --%>

												<td style="text-align: center;">${asmntList[6]-asmntList[4]-asmntList[5]-asmntList[7]}</td>

												<td style="text-align: center;">${asmntList[4]}</td>
												<td style="text-align: center;">${asmntList[7]}</td>
												<td style="text-align: center;">${asmntList[5]} <c:set
														var="totalApp" value="${totalApp+asmntList[6]}"></c:set> <c:set
														var="undervarification"
														value="${undervarification+ asmntList[6]-asmntList[4]-asmntList[5]-asmntList[7]}"></c:set>
													<c:set var="underProcessComm"
														value="${underProcessComm + asmntList[4]}"></c:set> <c:set
														var="rejected" value="${rejected + asmntList[5]}"></c:set>
														<c:set
														var="approved" value="${approved + asmntList[7]}"></c:set>
														<c:set var="beyond15Day" value="${beyond15Day + asmntList[8]}"></c:set>

												</td>
												<td style="text-align: center;">${asmntList[8]} </td>
											</tr>
										</c:forEach>
									</tbody>

									<tfoot>
										<tr style="background-color: #f3f8fa">
											<th style="text-align: right; border: 1px solid #9ee4fe;"
												colspan="2">Total:</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${totalApp}</th>

											<%-- 	<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${submited}</th> --%>

											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${undervarification}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${underProcessComm}</th>
												<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${approved}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${rejected}</th>
												<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${beyond15Day}</th>
										</tr>
									</tfoot>

								</table>
							</div>
							<!-- <div class="col-md-2"></div> -->
						</c:if>

						<c:if test="${repotype == 'sel_district'}">

							<a href="getAssmntByAllUlbWiseMob.do"
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
											<th align="center" class="col-md-4">Ulb</th>
											<th align="center" class="col-md-2">Total Assessment</th>
											<!-- <th align="center" class="col-md-2">Submited</th> -->
											<th align="center" class="col-md-2">Under RO/RI Login</th>
											<th align="center" class="col-md-2">Under Commissioner
												Login</th>
											<th align="center" class="col-md-3">Applications Approved</th>
											<th align="center" class="col-md-3">Applications Rejected</th>
											<th align="center" class="col-md-3">Applications Beyond 15 Days</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;"></td>
												<td style="text-align: center;"><a
													href="getAssmntBySelectedUlbWiseMob.do?ulb_id=${asmntList[0]}"
													style="color: #00000F; font-weight: bold;">${asmntList[1]}</a>

												</td>
												<td style="text-align: center;">${asmntList[6]}</td>
												<%-- <td style="text-align: center;">${asmntList[2]}</td> --%>
												<td style="text-align: center;">${asmntList[6]-asmntList[4]-asmntList[5]-asmntList[7]}</td>
												<td style="text-align: center;">${asmntList[4]}</td>
												<td style="text-align: center;">${asmntList[7]}</td>
												<td style="text-align: center;">${asmntList[5]}</td>
												<td style="text-align: center;">${asmntList[8]}</td>
											</tr>
										</c:forEach>
									</tbody>

								</table>
							</div>
						</c:if>

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

