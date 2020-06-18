
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
				<div class="panel-heading">PT Self Assessment Transaction Status
					Report</div>

				<div class=" panel-body ">
					<div class="panel-group" align="center">


						<div class="form-group">
							<label class="col-md-12"> <c:choose>
									<c:when
										test="${LocalDBStatus =='success'}">
										<span
											style="color: green; font-size: 15px; font-weight: bold;">Transaction
											Detail Updated Successfully</span>
									</c:when>
									<%-- <c:when
										test="${LocalDBStatus =='fail'}">
										<span
											style="color: green; font-size: 15px; font-weight: bold;">
											Transaction Updated successfully to DB2 </span>
										<span style="color: red; font-size: 15px; font-weight: bold;">
											But Failed to update in local;</span>
									</c:when> --%>
									<c:when test="${LocalDBStatus =='fail'}">
										<span style="color: red; font-size: 15px; font-weight: bold;">Transaction
											Failed To Update</span>
									</c:when>
								</c:choose>

							</label>
						</div>

						<form method="get"
							action="ptAddMeasurementSelfAsmntTransactionStatusReportByUniqId.do"
							name="tradeTranReport" id="tradeTranReport"
							onsubmit="return checkValidation();">
							<div class="form-group">
								<label class="col-md-3 control-label">Unique Request
									Number</label>
								<div class="col-md-2 inputGroupContainer">
									<div class="input-group">
										<input name="uniqueRequestId" id="uniqueRequestId"
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



							<table id="gridview"
								class="table table-condensed table-hover table-striped display">
								<thead>
									<tr style="background-color: #b9e9ff; font-size: 13px">
										<th>#</th>
										<th align="center" class="col-md-2">Owner Name</th>
										<th align="center" class="col-md-1">Uniq Req. No.</th>
										<th align="center" class="col-md-1">Transaction number</th>
										<th align="center" class="col-md-1">PT Asmnt ULB</th>
										<th align="center" class="col-md-1">Trans Date</th>
										<th align="center" class="col-md-1">Transaction Bank</th>
										<th align="center" class="col-md-2">Transaction ID</th>
										<th align="center" class="col-md-1">Gateway</th>
										<th align="center" class="col-md-1">Total Amt</th>
										<th align="center" class="col-md-1">Pay Status</th>
										<th align="center" class="col-md-1">DB2 Update Status</th>
									</tr>
								</thead>

							</table>
						</c:if>


						<c:if test="${repotype == 'by_uniq_id'}">
							<table id="gridview"
								class="table table-condensed table-hover table-striped display">
								<thead>
									<tr style="background-color: #b9e9ff; font-size: 13px">
										<th>#</th>
										<th align="center" class="col-md-2">Owner Name</th>
										<th align="center" class="col-md-1">Uniq Req. No.</th>
										<th align="center" class="col-md-1">Transaction Number</th>
										<th align="center" class="col-md-1">PT Asmnt ULB</th>
										<th align="center" class="col-md-1">Transaction Date</th>
										<th align="center" class="col-md-1">Transaction Bank</th>
										<th align="center" class="col-md-1">Transaction ID</th>
										<th align="center" class="col-md-1">Gateway</th>
										<th align="center" class="col-md-1">Total Amt</th>
										<th align="center" class="col-md-1">Pay Status</th>
										<th align="center" class="col-md-1">DB2 Update Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tranList}" var="tranList">
										<tr
											style="height: 25px; font-size: 10px; font-family: verdana; color: navy;">
											<td></td>
											<td>${tranList[1]}${tranList[2]}</td>
											<td>${tranList[11]}</td>
											<td align="center">${tranList[10]}</td>
											<td>${tranList[0]}</td>
											<td>${tranList[12]}</td>
											<td>${tranList[3]}</td>
											<td>${tranList[4]}</td>
											<td>${tranList[5]}</td>
											<td>${tranList[6]}</td>
											<td>${tranList[8]}</td>
										   

											<td><c:choose>
													<c:when
														test="${(tranList[8] eq 'Success') && (tranList[9] eq 'Not Completed')}">
														<a  
															href="updateAddMeasurementInitiateTransactionStatusDB2.do?trans_id=${tranList[10]}&uniqReqNo=${tranList[11]}"
															title="Update To DB2">Initiate </a>
													</c:when>
													<c:when
														test="${(tranList[8] eq 'Success') && (tranList[9] eq 'Success')}">
															Success
														</c:when>
														<c:otherwise>
															 Not Completed
												 		</c:otherwise>
												</c:choose>
												 <%-- <c:choose>
														<c:when
															test="${(tranList[10] eq 'Success') && (tranList[11] eq 'Not Completed')}">
															<a
																href="updateInitiateTransactionStatusDB2.do?trans_id=${tranList[12]}&uniqReqNo=${tranList[13]}"
																title="Update To DB2">Initiate </a>
														</c:when>
														<c:when
															test="${(tranList[10] eq 'Success') && (tranList[11] eq 'Success')}">
															Success
														</c:when>
														<c:otherwise>
															${tranList[13]}Not Completed
												 		</c:otherwise>
													</c:choose>
											 --%>
											 </td>
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

		var uniqueRequestId = document.tradeTranReport.uniqueRequestId.value;

		if (uniqueRequestId == '' || uniqueRequestId == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Unique Request Number";
			//document.getElementById("ownerName").value = "";
			document.getElementById("uniqueRequestId").focus();
			return false;
		}

		return true;
	}
</script>

<script type="text/javascript">
	function checkValidation1() {

		var transactionNo = document.tradeTranReport1.transactionNo.value;

		if (transactionNo == '' || transactionNo == null) {
			document.getElementById("errorBox1").innerHTML = "Please Enter Trnsaction Number";
			//document.getElementById("ownerName").value = "";
			document.getElementById("tradeTranReport").focus();
			return false;
		}

		return true;
	}
</script>


</html>

