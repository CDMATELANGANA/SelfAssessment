<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%
	 String path = request.getContextPath(); 
%>
<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring" %>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form" %>


<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->

<html>

<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>PT Selfassessment Tax paid history</title>
<script type="text/javascript">
	function receiptPrint(elem) {
		var headstr = "<body ><br><table  align='center'><tr align='center'><td class='logoHdr'></h2><h2>Government of Telangana</h2><h2>Commissioner &amp; Director of Municipal Administration<br></td><td class='topLnk lnkBox' style='float:right;margin-right:30px;'></td></tr></table>"; 
		var footstr = "<table cellpadding='0' cellspacing='0' width='100%'  align='center'><tbody><tr><td colspan='3' align='center'><div><br>Copyright © 2017-C&DMA -Powered By MARS Telecom Systems </div></td></tr></tbody></table></div>";
		var printDiv = document.getElementById(elem).innerHTML;
		var mywindow = window.open('', 'PRINT', 'height=400,width=600');
		mywindow.document.write('</head><body >');
		mywindow.document.write(headstr + printDiv + footstr);
		mywindow.document.write('</body></html>');

		mywindow.document.close(); // necessary for IE >= 10
		mywindow.focus(); // necessary for IE >= 10*/

		mywindow.print();
		mywindow.close();
		var oldPage = document.body.innerHTML;
		//Restore orignal HTML
		document.body.innerHTML = oldPage;
		return true;
	}</script>
	<script type="text/javascript">
	//<title>Oline Self Mutation</title>
	function receiptPrint(elem,headDiv){
		var headstr = "<html><head></head><body ><table cellpadding='0' cellspacing='0' align='center'><tr align='center'></p></a></td><td style='float:right;margin-right:30px;'></td></tr></table>";
		var footstr = "<table cellpadding='0' cellspacing='0' width='100%'  align='center'><tbody><tr><td colspan='3' align='center'></td></tr></tbody></table></body>";
		var printDiv=document.getElementById(elem).innerHTML;
		var headDiv=document.getElementById(headDiv).innerHTML;
		var mywindow = window.open('', 'PRINT', 'height=200,width=200');
		//mywindow.document.write('</head><body >');
	    mywindow.document.write(headDiv+headstr+printDiv+footstr);
	    mywindow.document.write('</body></html>');

	    mywindow.document.close(); // necessary for IE >= 10
	    mywindow.focus(); // necessary for IE >= 10*/

	    mywindow.print();
	    mywindow.close();
	    var oldPage = document.body.innerHTML;
	  //Restore orignal HTML
	    document.body.innerHTML = oldPage;
	    return true;
	}

	</script>
</head>
<body>
<!-- <div class="container"> -->
		<div class="mainHdr">
		<div class="container-fluid">
			<div class="row-fluid">
				<!--/span-->
				<div class="span12" id="content">
					<div class="row-fluid">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left"
									style="color: black; font-size: 15px;">
		
		<table class="dataGrid table-striped" cellpadding="0" cellspacing="0" border="1"
			align="center" style="width: 100%" id="assessmentReceipts"
					style="background-color: #DDE7D9;">
					
					<thead>
						<tr>
							<td  align="center" bgcolor="#e6ffff" colspan="11" align="center">Payment history for
								Assessment No. <c:out value="${requestScope.assessment}"></c:out>
							</td>
						</tr>
						<tr bgcolor="#009999">

							<th align="center">BOOK NO</th>
							<th align="center">RECEIPT NO</th>
							<th align="center">RECEIPT DATE</th>
							<th align="center">ENTRY DATE</th>
							<th align="center">PAID Form - To Date</th>
							<th align="center">PAID MODE</th>
							<th align="center">AMOUNT PAID THROUGH</th>
							<th align="center">CURRENT AMOUNT</th>
							<th align="center">ARREAR AMOUNT</th>
							<th align="center">PENALTY AMOUNT</th>
							
							<!-- <td align="center">ADVANCE AMOUNT</td> -->
							<th align="center">TOTAL PAID AMOUNT</th>
							<th hidden="true" align="center">Status</th>
						</tr>
					</thead>
					<tbody id="transhistory">
						<c:set var="hasRows" value="false" />
						<c:set var="total_current" value="0" />
						<c:set var="total_arrear" value="0" />
						<c:set var="total_penality" value="0" />
						<c:set var="total_advance" value="0" />
						<c:set var="total_paid_amount" value="0" />

						<c:forEach var="assessmentReceipts"
							items="${requestScope.assessmentReceipts}" varStatus="rowNumber">
							<c:set var="hasRows" value="true" />
							<tr>

								<td align="center"><c:out
										value="${assessmentReceipts.bookNo}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.receiptNo}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.receipDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.entryDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.paidFromDate}" /> to <c:out
										value="${assessmentReceipts.paidUptoDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.paidMode}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.amountPaidAt}" /></td>
								<td align="center"><c:set var="total_current"
										value="${total_current + assessmentReceipts.currentAmount}" />
									<c:out value="${assessmentReceipts.currentAmount}" /></td>

								<td align="center"><c:set var="total_arrear"
										value="${total_arrear + assessmentReceipts.arrearAmount}" />
									<c:out value="${assessmentReceipts.arrearAmount}" /></td>

								<td align="center"><c:set var="total_penality"
										value="${total_penality + assessmentReceipts.penalityAmount}" />
									<c:out value="${assessmentReceipts.penalityAmount}" /></td>

								<%-- <td align="center">
				<c:set var="total_advance" value="${total_advance + assessmentReceipts.advanceAmount}" />
				<c:out	value="${assessmentReceipts.advanceAmount}" /></td> --%>
								<td align="center"><c:set var="total_paid_amount"
										value="${total_paid_amount + assessmentReceipts.totalPaidAmount}" />
									<c:out value="${assessmentReceipts.totalPaidAmount}" /></td>
									
									<td hidden="true" align="center">
									<c:out value="${assessmentReceipts.status}" /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr bgcolor="#e6ffff">
							<td align="center">Total Record - <c:out
									value="${fn:length(requestScope.assessmentReceipts)}" /></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>

							<td align="center"><c:out value="${total_current}" /></td>
							<td align="center"><c:out value="${total_arrear}" /></td>
							<td align="center"><c:out value="${total_penality}" /></td>
							<%-- <td align="center"><c:out value="${total_advance}" /></td> --%>
							<td align="center"><c:out value="${total_paid_amount}" /></td>
							<td hidden="true" align="center"><c:out value="${status}" /></td>
							
						</tr>
						<tr ><form action="" name="applicationstaus" method="post">
 			<div class="col-md-12">
				<div class="col-md-4"></div>
					<div class="col-md-6">
				<a
			style="text-decoration: none; font-size: 18px; font-weight: bold;"
			href="${pageContext.request.contextPath}/assessmentTaxPaidHistory.do?assessment=<c:out value="${requestScope.assessment}"/>"
			target="_blank"> Download Property Tax paid History
					</a>
					<input type="button"	class="btn btn-primary btn-sm"  name="print"	value="Print Receipt"	onclick="javascript:receiptPrint('printDiv','headDiv');" />
				</div>
				</div></form></tr>
					</tfoot>

				</table>
			</div>
	
			</div></div></div></div></div>
		</div>
	</div>
<div  style="width: 40%; display: none;" id="headDiv" >

		<div class="mainHdr">
		<div class="container-fluid">
			<div class="row-fluid">
				<!--/span-->
				<div class="span12" id="content">
					<div class="row-fluid">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left"
									style="color: black; font-size: 15px;">
		
		<table class="dataGrid table-striped" cellpadding="0" cellspacing="0" border="1"
			align="center" style="width: 100%" id="assessmentReceipts"
					style="background-color: #DDE7D9;">
					
					<thead>
						<tr>
							<td  align="center" bgcolor="#e6ffff" colspan="11" align="center">Payment history for
								Assessment No. <c:out value="${requestScope.assessment}"></c:out>
							</td>
						</tr>
						<div class="content" style="width: 40%; top: 20%;display: none;" id="printDiv" >
						<tr bgcolor="#009999">

							<th align="center">BOOK NO</th>
							<th align="center">RECEIPT NO</th>
							<th align="center">RECEIPT DATE</th>
							<th align="center">ENTRY DATE</th>
							<th align="center">PAID Form - To Date</th>
							<th align="center">PAID MODE</th>
							<th align="center">AMOUNT PAID THROUGH</th>
							<th align="center">CURRENT AMOUNT</th>
							<th align="center">ARREAR AMOUNT</th>
							<th align="center">PENALTY AMOUNT</th>
							
							<!-- <td align="center">ADVANCE AMOUNT</td> -->
							<th align="center">TOTAL PAID AMOUNT</th>
							<th hidden="true" align="center">Status</th>
						</tr>
					</thead>
					<tbody id="transhistory">
						<c:set var="hasRows" value="false" />
						<c:set var="total_current" value="0" />
						<c:set var="total_arrear" value="0" />
						<c:set var="total_penality" value="0" />
						<c:set var="total_advance" value="0" />
						<c:set var="total_paid_amount" value="0" />

						<c:forEach var="assessmentReceipts"
							items="${requestScope.assessmentReceipts}" varStatus="rowNumber">
							<c:set var="hasRows" value="true" />
							<tr>

								<td align="center"><c:out
										value="${assessmentReceipts.bookNo}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.receiptNo}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.receipDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.entryDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.paidFromDate}" /> to <c:out
										value="${assessmentReceipts.paidUptoDate}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.paidMode}" /></td>
								<td align="center"><c:out
										value="${assessmentReceipts.amountPaidAt}" /></td>
								<td align="center"><c:set var="total_current"
										value="${total_current + assessmentReceipts.currentAmount}" />
									<c:out value="${assessmentReceipts.currentAmount}" /></td>

								<td align="center"><c:set var="total_arrear"
										value="${total_arrear + assessmentReceipts.arrearAmount}" />
									<c:out value="${assessmentReceipts.arrearAmount}" /></td>

								<td align="center"><c:set var="total_penality"
										value="${total_penality + assessmentReceipts.penalityAmount}" />
									<c:out value="${assessmentReceipts.penalityAmount}" /></td>

								<%-- <td align="center">
				<c:set var="total_advance" value="${total_advance + assessmentReceipts.advanceAmount}" />
				<c:out	value="${assessmentReceipts.advanceAmount}" /></td> --%>
								<td align="center"><c:set var="total_paid_amount"
										value="${total_paid_amount + assessmentReceipts.totalPaidAmount}" />
									<c:out value="${assessmentReceipts.totalPaidAmount}" /></td>
									
									<td hidden="true" align="center">
									<c:out value="${assessmentReceipts.status}" /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr bgcolor="#e6ffff">
							<td align="center">Total Record - <c:out
									value="${fn:length(requestScope.assessmentReceipts)}" /></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td align="center"></td>

							<td align="center"><c:out value="${total_current}" /></td>
							<td align="center"><c:out value="${total_arrear}" /></td>
							<td align="center"><c:out value="${total_penality}" /></td>
							<%-- <td align="center"><c:out value="${total_advance}" /></td> --%>
							<td align="center"><c:out value="${total_paid_amount}" /></td>
							<td hidden="true" align="center"><c:out value="${status}" /></td>
							
						</tr>
						<tr ></tr>
					</tfoot>
</div>
				</table>
			</div>
	</div></div>
			</div></div></div></div></div>

</body>
</html>

<style>
.table-striped>tbody>tr:nth-child(odd)>td,
.table-striped>tbody>tr:nth-child(odd)>th {
	background-color:#ccccff;
}
.table-striped>tbody>tr:nth-child(even)>td,
.table-striped>tbody>tr:nth-child(even)>th {
	background-color: #f2ccff;
}
</style>