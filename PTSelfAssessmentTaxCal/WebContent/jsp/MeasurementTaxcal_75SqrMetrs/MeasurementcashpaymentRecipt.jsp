<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<title>CDMA Self Mutation Application</title>
<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js">
	
</script>
<style type="text/css">
.panel-heading {
	font-size: large;
}
</style>

<script type="text/javascript">
	function receiptPrint(elem) {
		var headstr = "<html><head><title>eSuvidha ePay Portal</title></head><body ><table  align='center'><tr align='center'><td class='logoHdr'><a href='http://www.cdma.telangana.gov.in/' style='text-decoration: none;'></h2><h2>Government of Telangana</h2><h2>Commissioner &amp; Director of Municipal Administration<br></a></td><td class='topLnk lnkBox' style='float:right;margin-right:30px;'></td></tr></table>";
		var footstr = "<table cellpadding='0' cellspacing='0' width='100%'  align='center'><tbody><tr><td colspan='3' align='center'><div>Copyright © 2017-C&DMA -Powered By MARS Telecom Systems </div></td></tr></tbody></table></body>";
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
	}

	window.history.forward(0);
</script>
	<script type="text/javascript">
	//<title>Oline Self Mutation</title>
	function receiptPrint(elem,headDiv){
		var headstr = "<html><head></head><body ><table cellpadding='0' cellspacing='0' align='center'><tr align='center'></p></a></td><td style='float:right;margin-right:30px;'></td></tr></table>";
		var footstr = "<table cellpadding='0' cellspacing='0' width='100%'  align='center'><tbody><tr><td colspan='3' align='center'></td></tr></tbody></table></body>";
		var printDiv=document.getElementById(elem).innerHTML;
		var headDiv=document.getElementById(headDiv).innerHTML;
		var mywindow = window.open('', 'PRINT', 'height=200,width=400');
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
	<div class="container_12">
		<c:forEach items="${transactionReceipt}" var="transactionReceipt">
		<%-- <c:if test="${not empty  requestScope.transactionReceipt}"> --%>
			<fieldset>
				<div class="panel panel-primary">
					<div class="panel-heading">
						Payment Information:
						<c:out value="${transactionReceipt[0]}" />
					</div>
					<div class="panel-body">
						<div class="panel-group">
							<!-- 1  -->
							<div class="col-md-6">
								<label class="col-md-4 ">Name :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[1]}" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<label class="col-md-4 ">Mobile Number :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[2]}" />
									</div>
								</div>
							</div>
							<!-- 1 end -->
							<!-- 2 -->
							<div class="col-md-6">
								<label class="col-md-4 ">ULB Name :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[3]}" />
									</div>
								</div>
							</div>
							 <%--  <div class="col-md-6">
								<label class="col-md-4 ">District Name :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[22]}" />
									</div>
								</div>
							</div>   --%>
							<!-- 2 end-->

							<!-- 3 -->
						<%-- 	<div class="col-md-6">
								<label class="col-md-4 ">Mode Of payment:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[5]}" />
									</div>
								</div>
							</div> --%>
							
							<div class="col-md-6">
								<label class="col-md-4 ">Payment Gateway :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[6]}" />
									</div>
								</div>
							</div>
							<!-- 3 end-->

							<!-- 4 -->
							<!-- if pay success -->
							<div class="col-md-6">
								<label class="col-md-4 ">Payment Staus:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[7]}" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<label class="col-md-4 ">Assessment No:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${AssmntNo}" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<label class="col-md-6" style="width: 170px;">DoorNo:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${DoorNo}" />
									</div>
								</div>
							</div>
							
							<!-- if DB uupdate Successfully -->
							<div class="col-md-6">
								<label class="col-md-4 ">Transcation Staus:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="Success" />
										
									</div>
								</div>
							</div>
							<!-- 4 end-->

							<!-- 5 -->
							<div class="col-md-6">
								<label class="col-md-4 ">Payment Amount:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${transactionReceipt[8]}" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<label class="col-md-6" style="width:200PX;">Period of Payment:</label>
								<div class="col-md-6 ">
									<div class="input-group">
										<c:out value="2019-10-01- 2020-03-31(6 Months Period)" />
									</div>
								</div>
							</div>
							
							
							<!-- 5 end-->

								<div class="col-md-12">
<div class="col-md-2 ">
									<input type="button"	class="btn btn-primary btn-sm"  name="print"	value="Print Receipt"	onclick="javascript:receiptPrint('printDiv','headDiv');" />
								</div>
									<div class="col-md-6">
										<div class="input-group">
											<a                                acknowledgement
												style="text-decoration: none; font-size: 18px; font-weight: bold;"
												href="${pageContext.request.contextPath}/acknowledgement.do?uniquerequestid=<c:out value="${transactionReceipt[0]}"/>"
												target="_blank"> Download PtSelfAssessment Acknowledgment
											</a>
										</div>
									</div>
									

</div>
						</div>
					</div>
						</fieldset>
		</c:forEach>
				</div>
<!--For Printer Call  -->
<div  style="width: 40%; display: none;" id="headDiv" >
<h2 align="center">Commissioner & Director of Municipal Administration</h2>
<h2 align="center">Government of Telangana</h2>
<h3 align="center">Property Tax Self Assessment With Payment</h3></div>
<div class="content" style="width: 40%; top: 20%;display: none;" id="printDiv" >
	<div class="container">
<div class="row">
			<div class="container_12">
		<c:forEach items="${transactionReceipt}" var="transactionReceipt">
		<%-- <c:if test="${not empty  requestScope.transactionReceipt}"> --%>
			<fieldset>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<b>Payment Information:
						<c:out value="${transactionReceipt[0]}" /></b>
					</div>
					<div class="panel-body">
						<div class="panel-group">
							<!-- 1  -->
<pre>
<b>Name :</b>   		 <c:out value="${transactionReceipt[1]}" />
<b>Mobile Number :</b>   	  <c:out value="${transactionReceipt[2]}" />
<b>ULB Name :</b>		  <c:out value="${transactionReceipt[3]}" />
<b>Payment Gateway :</b> 	  <c:out value="${transactionReceipt[6]}" />
<b>Payment Staus:</b>	 	  <c:out value="${transactionReceipt[7]}" />
<b>Assessment No:</b>	 	  <c:out value="${AssmntNo}" />
<b>DoorNo:</b>			 <c:out value="${DoorNo}" />
<b>Transcation Staus:</b>    <c:out value="Success" />
<b>Payment Amount:</b>		 <c:out value="${transactionReceipt[8]}" />
<b>Period of Payment:</b>	<c:out value="2019-10-01- 2020-03-31(6 Months Period)"/><br>
<b>This document is produced electronically and therefore does not require a signature.</b>
</pre>
								
							

							
					</div></div></div>
						</fieldset>
		</c:forEach>
</div></div></div></div>		
</body>
</html>