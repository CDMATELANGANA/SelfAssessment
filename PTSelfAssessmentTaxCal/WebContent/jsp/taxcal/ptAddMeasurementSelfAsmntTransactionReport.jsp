
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CDMA Self Mutation Application</title>
<%
	String path = request.getContextPath();
%>
</head>

<%-- <script src="<%=path %>/web/etrade/jqurey.1.6.2.js"></script> --%>
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
	/* $(document).ready(
			function() {
				$('#gridview').DataTable(
						{
							"lengthMenu" : [ [ 15, 25, 50, 80, -1 ],
									[ 15, 25, 50, 80, "All" ] ]
						});
			}); */

	$(document).ready(
			function() {
				var t = $('#gridview').DataTable(
						{
							"columnDefs" : [ {
								"searchable" : false,
								"orderable" : false,
								"targets" : 0
							} ],
							"order" : [ [ 1, 'asc' ] ],
							"lengthMenu" : [ [ 15, 25, 50, 80, -1 ],
									[ 15, 25, 50, 80, "All" ] ]
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

<body>

	<div class="container">



		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">PT Self Assessment Transaction Report</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">

						<form method="get" action="ptAddMeasurementSelfAsmntTransactionReportByUniqId.do"
							name="ptSelfAsmntTranReport" id="ptSelfAsmntTranReport"
							onsubmit="return checkValidation();">
							<div class="form-group">
								<label class="col-md-3 control-label">Unique Request
									Number</label>
								<div class="col-md-2 inputGroupContainer">
									<div class="input-group">
										<input name="uniqReqNumber" id="uniqReqNumber"
											placeholder="Unique Req Number" class="form-control"
											type="text">
									</div>
								</div>
							</div>
							<div class="form-group col-md-1">
								<div class="input-group submit">
									<input type="Submit" value="Search" class="submit">
								</div>
							</div>
							<div class="input-group">
								<div class="col-md-12" align="center" id="errorBox"
									style="color: red; font-size: 14px; margin: 10px 0 10px 0;"></div>
							</div>
						</form>
						<br>

						<hr style="border-color: #5dd5f5"></hr>

						<c:if test="${repotype == 'all'}">

							<table
								class="table table-condensed table-hover table-striped display col-md-12">
								<tr
									style="background-color: #5db5f5; font-size: 13px; font-weight: bold">
								<!-- 	<td class="col-md-1"></td> -->
									<!-- <td class="col-md-3">Total NOC Amount</td> -->
									<td class="col-md-4" style="padding-left: 50px">Total PT Self Assessment Collection</td>
									<!-- <td class="col-md-3">Total Collection</td> -->
									<td class="col-md-1"></td>
								</tr>
								<c:forEach items="${tradeCollection}" var="tradeCollection"
									end="0">
									<tr
										style="background-color: #fcf8e3; font-size: 12px; font-weight: bold">
									<!-- 	<td class="col-md-1"></td> -->
										<%-- <td class="col-md-3">${tradeCollection[0]}</td> --%>
										<td class="col-md-4"  style="padding-left: 50px">${tradeCollection}</td>
										<%-- <td class="col-md-3">${tradeCollection[2]}</td> --%>
										<td class="col-md-1"></td>
									</tr>
								</c:forEach>
							</table>


							<table id="gridview"
								class="table table-condensed table-hover table-striped display">
								<thead>
									<tr style="background-color: #b9e9ff; font-size: 13px">
										<th style="width: 50px">S.N.</th>
										<th align="center" class="col-md-2">Pt Self Asmnt ULB</th>
										<th align="center" class="col-md-2">Owner Name</th>
										<!-- <th align="center" class="col-md-2">Receipt Owner Name</th> -->
										<th align="center" class="col-md-2">Uniq Req. No.</th>
										<th align="center" class="col-md-2">Transaction Bank</th>
										<th align="center" class="col-md-2">Transaction ID</th>
										<th align="center" class="col-md-1">Gateway</th>
										<!-- <th align="center" class="col-md-1">NOC Amt</th>-->
										<th align="center" class="col-md-1">Status</th>
										<th align="center" class="col-md-1">Total Amt</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tranList}" var="tranList" varStatus="valCount">
										<tr
											style="height: 25px; font-size: 11px; font-family: verdana; color: navy;">
											<td style="width: 50px"></td>
											<td>${tranList[0]}</td>
											<td>${tranList[1]}</td>
											<%-- <td>${tranList[9]}</td> --%>
											<td>${tranList[11]}</td>
											<td>${tranList[3]}</td>
											<td>${tranList[4]}</td>
											<td>${tranList[5]}</td>
											<%-- <td>${tranList[6]}</td>--%>
											<td>${tranList[13]}</td> 
											<td>${tranList[6]}</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
						</c:if>


						<c:if test="${repotype == 'by_uniq_id'}">
							<table id="gridview"
								class="table table-condensed table-hover table-striped display">
								<thead>
									<tr style="background-color: #b9e9ff; font-size: 13px">
										<th style="width: 50px">S.N.</th>
										<th align="center" class="col-md-2">PT Asmnt ULB</th>
										<th align="center" class="col-md-2">Owner Name</th>
										<!-- <th align="center" class="col-md-2">Receipt Owner Name</th> -->
										<th align="center" class="col-md-2">Uniq Req. No.</th>
										<th align="center" class="col-md-2">Transaction Bank</th>
										<th align="center" class="col-md-2">Transaction ID</th>
										<th align="center" class="col-md-1">Gateway</th>
										<!-- <th align="center" class="col-md-1">NOC Amt</th> -->
										<th align="center" class="col-md-1">Status</th> 
										<th align="center" class="col-md-1">Total Amt</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tranList}" var="tranList">
										<tr
											style="height: 25px; font-size: 11px; font-family: verdana; color: navy;">
											<td style="width: 50px"></td>
											<td>${tranList[0]}</td>
											<td>${tranList[1]} ${tranList[2]}</td>
											<%-- <td>${tranList[9]}</td> --%>
											<td>${tranList[13]}</td>
											<td>${tranList[3]}</td>
											<td>${tranList[4]}</td>
											<td>${tranList[5]}</td>
											<%-- <td>${tranList[6]}</td>--%>
											<td>${tranList[15]}</td> 
											<td>${tranList[8]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>

					</div>
				</div>
			</div>
		</fieldset>

		<%-- </form> --%>
	</div>
</body>

<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
	function checkValidation() {

		var uniqueRequestId = document.ptSelfAsmntTranReport.uniqReqNumber.value;

		if (uniqueRequestId == '' || uniqueRequestId == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Unique Request Number";
			//document.getElementById("ownerName").value = "";
			document.getElementById("uniqReqNumber").focus();
			return false;
		}

		return true;
	}
</script>



</html>

