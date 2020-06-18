
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

					<div class="panel panel-primary">
				<div class="panel-heading">Assessment Report ULB Wise</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						<c:if test="${repotype == 'ulb'}">
							<form method="get" action="assmntReportByDate.do"
								name="tradeReport" id="tradeReport"
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
							</form>
						</c:if>
	
	
						<c:if test="${repotype == 'sel_ulb'}">
							<form method="get" action="assmntSelectedUlbByDate.do"
								name="assmntReport" id="assmntReport"
								onsubmit="return checkValidation();">
								<div class="form-group" style="display: none;">
									<label class="col-md-2 control-label">Appl From date</label>
									<div class="col-md-2 inputGroupContainer">
										<div class="input-group">
											<input name="entryFrom" id="entryFrom"
												placeholder="From Date" class="form-control" type="text">

										</div>
									</div>
									<label class="col-md-2 control-label">Appl To date</label>
									<div class="col-md-2 inputGroupContainer">
										<div class="input-group">
											<input name="entryTo" id="entryTo" placeholder="toDate"
												class="form-control" type="text">
										</div>
									</div>
									<div class="col-md-2 input-group submit">
										<input type="submit" value="Submit" />
									</div>

									<div class="input-group">
										<input name="ulb_id" id="ulb_id" type="hidden"
											value="${ulb_id}" readonly="readonly">
										<div class="col-md-12" align="center" id="errorBox"
											style="color: red; font-size: 14px; margin: 10px 0 10px 0;"></div>
									</div>

								</div>
							</form>
						</c:if>

						<hr style="margin-top: -5px; border-color: #5dd5f5"></hr>



						<c:if test="${repotype == 'ulb'}">
							<div class="col-md-1"></div>
							<div class="col-md-10" align="center">
								<table id="gridview" title="${date_interval}"
									class="table table-condensed table-hover table-striped display cell-border"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											<th align="center" style="width: 45px;">S.NO</th>
											<th style="text-align: center;" class="col-md-6">Ulb</th>
											<th style="text-align: center;" class="col-md-4">Total Assessment</th>
											<!-- <th align="center" class="col-md-2">Submited</th> -->
											<!-- <th align="center" class="col-md-2">Under RO/RI login</th>
											<th align="center" class="col-md-2">Under Commissioner
												Login</th>
											<th align="center" class="col-md-3">Applications Approved</th>
											<th align="center" class="col-md-3">Applications Rejected</th>
											<th align="center" class="col-md-3">Applications Beyond 15 Days</th> -->
										</tr>
									</thead>
									<tbody>
										<c:set var="totalApp" value="0"></c:set>
										<%-- <c:set var="submited" value="0"></c:set>
										<c:set var="undervarification" value="0"></c:set>
										<c:set var="underProcessComm" value="0"></c:set>
										<c:set var="approved" value="0"></c:set>
										<c:set var="rejected" value="0"></c:set>
										<c:set var="beyond15Day" value="0"></c:set> --%>
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;"></td>
												<td style="text-align: center;"><a
													href="getAssmntBySelectedUlbWise.do?ulb_id=${asmntList[0]}"
													style="color: #00000F; font-weight: bold;">
														${asmntList[1]} </a></td>
												 <td style="text-align: center;">${asmntList[2]}</td>
												<%-- <td style="text-align: center;">${asmntList[2]}</td> --%>
												
												<%-- <td style="text-align: center;">${asmntList[6]-asmntList[4]-asmntList[5]-asmntList[7]}
												
												</td>
												
												<td style="text-align: center;">${asmntList[4]}</td>
												<td style="text-align: center;">${asmntList[7]}</td>
												<td style="text-align: center;">${asmntList[5]} 
												 --%>
												
												
												<c:set
														var="totalApp" value="${totalApp+asmntList[2]}"></c:set> 
														<%-- <c:set
														var="submited" value="${submited+asmntList[2]}"></c:set> <c:set
														var="undervarification"
														value="${undervarification+ asmntList[6]-asmntList[4]-asmntList[5]-asmntList[7]}"></c:set> <c:set
														var="underProcessComm"
														value="${underProcessComm + asmntList[4]}"></c:set> <c:set
														var="rejected" value="${rejected + asmntList[5]}"></c:set>
														<c:set
														var="approved" value="${approved + asmntList[7]}"></c:set>
														<c:set var="beyond15Day" value="${beyond15Day + asmntList[8]}"></c:set> --%>

												</td>
												<%-- <td style="text-align: center;">${asmntList[8]}</td> --%>
											</tr>
										</c:forEach>
									</tbody>

									<tfoot>
										<tr style="background-color: #f3f8fa">
											<th style="text-align: right; border: 1px solid #9ee4fe;"
												colspan="2">Total:</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${totalApp}</th>

											<%-- <th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${submited}</th> --%>

											<%-- <th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${undervarification}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${underProcessComm}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${approved}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${rejected}</th>
											<th
												style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${beyond15Day}</th> --%>
										</tr>
									</tfoot>

								</table>

							</div>
							<div class="col-md-1"></div>
						</c:if>



						<c:if test="${repotype == 'sel_ulb'}">


							<div class="col-md-12">
								<table class="table-striped display" style="color: #00000F;">

									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											<th style="text-align: center;" class="col-md-2">Ulb</th>
											<th style="text-align: center;" class="col-md-2">Total Assessment</th>
											<!-- <th align="center" class="col-md-2">Submited</th> -->
											<!-- <th align="center" class="col-md-2">Under RO/RI Login </th>
											<th align="center" class="col-md-2">Under Commissioner
												Login</th>
											<th align="center" class="col-md-3">Applications Approved</th>
											<th align="center" class="col-md-3">Applications Rejected</th>
											<th align="center" class="col-md-3">Applications Beyond 15 Days</th> -->	
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${ulbCount}" var="ulbCount"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="text-align: center;">${ulbCount[1]}</td>
												 <td style="text-align: center;">${ ulbCount[2]}</td>
												<%--<td style="text-align: center;">${ulbCount[2]}</td>
												<td style="text-align: center;">${ulbCount[6]-ulbCount[4]-ulbCount[5]-ulbCount[7]}</td>
												<td style="text-align: center;">${ulbCount[4]}</td>
												<td style="text-align: center;">${ulbCount[7]}</td>
												<td style="text-align: center;">${ulbCount[5]}</td>
												<td style="text-align: center;">${ulbCount[8]}</td> --%>
											</tr>
										</c:forEach>
									</tbody>


								</table>
							</div>



							<div class="col-md-12">
								<hr style="border-color: #f86429"></hr>
								<table id="gridview" title="${date_interval}"
									class="table table-condensed table-hover table-striped display"
									style="color: #00000F;">
									<thead>
										<tr style="background-color: #b9e9ff; font-size: 14px">
											<th align="center" data-sortable="false" style="width: 50px"
												width="50">S.N.</th>
											<th align="center" class="col-md-2">Assessment No</th>	
											<th align="center" class="col-md-2">More Info</th>
											<th align="center" class="col-md-2">OwnerName</th>
											<th align="center" class="col-md-2">DoorNo</th>
											<!-- <th align="center" class="col-md-2">District</th> -->
											<th align="center" class="col-md-2">Application Status</th>
											<th align="center" class="col-md-2">Date</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${asmntList}" var="asmntList"
											varStatus="valCount">
											<tr style="font-size: 13px; color: #00000F;">
												<td style="width: 50px" width="50"></td>
												<td style="color: #00000F;">${asmntList[2]}</td>
												<td style="color: #00000F;"><b><font color="red"><a href="${pageContext.request.contextPath}/cmnrLogin.do">More Info </font>
												
												</a></b></td>
												<td style="color: #00000F;">${asmntList[4]}
													${asmntList[3]}</td>
												<td>Door No.: ${asmntList[5]}</td>
												<%-- <td>${asmntList[6]}</td> --%>
												<td>${asmntList[8]}</td>
												<td>${asmntList[9]}</td>
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
</body>


<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>

<script type="text/javascript">
	function checkValidation() {

		var entryFrom = document.assmntReport.entryFrom.value;
		var entryTo = document.assmntReport.entryTo.value;

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

		var entry_date = document.assmntReport1.entry_date.value;

		if (entry_date == '' || entry_date == null) {
			document.getElementById("errorBox1").innerHTML = "Please Enter Trade  Application  Date";
			document.getElementById("entry_date").focus();
			return false;
		}

		return true;
	}
</script>



</html>

