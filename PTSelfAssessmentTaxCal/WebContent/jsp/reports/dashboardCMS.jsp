
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
				<div class="panel-heading">PT Self Assessment
					Dashboard</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">
						<div class="col-md-2"></div>
						<div class="col-md-8" align="center">
							<a href="getAssmntByAllDistrictWise.do"><c:forEach
									items="${dashborad}" var="dashborad">
									<table id="gridview" title="${date_interval}"
										class="table table-condensed table-hover table-striped display cell-border"
										style="color: #00000F;">


										<!-- 	<thead>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-4" colspan="2">VLT Dashboard</th>
												
											</tr>
										</thead> -->


										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Total Assessment</th>
												<th align="center" class="col-md-6">${dashborad[4]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Under RO/RI Login</th>
												<th align="center" class="col-md-6">${dashborad[4]-dashborad[2]-dashborad[3]-dashborad[5]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Under Commissioner
													Login</th>
												<th align="center" class="col-md-6">${dashborad[2]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Applications
													Approved</th>
												<th align="center" class="col-md-6">${dashborad[5]}</th>
											</tr>
										</tbody>

										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Applications
													Rejected</th>
												<th align="center" class="col-md-6">${dashborad[3]}</th>
											</tr>
										</tbody>
										<tbody>
											<tr style="background-color: #b9e9ff; font-size: 14px">
												<th align="center" class="col-md-6">Applications Beyond
													15 Days</th>
												<th align="center" class="col-md-6">${dashborad[6]}</th>
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

