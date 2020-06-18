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
<title>CDMA eTrade Application</title>
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

</head>
<body>

	<div class="container_12">

		<div class="panel panel-primary">
			<div class="panel-heading" align="center">Self Assessment
				Receipt</div>
			<div class="panel-body">
				<div class="panel-group">
					<div align="center" style="border: 1px solid #000;">
						<table border="0" class="default">
							<tr>
								<td>
									<div align="center" style="font-size: 24px">
										Commissioner & Director of Municipal Administration<br>
										Government of Telangana
									</div> <br> <br>
									<div align="center" style="font-size: 18px">Self
										Assessment Online Application Acknowledgment</div> <br> <br>
									<div align="center">
										<p>${requestScope.assessmentMaster}</p>
										<p>${requestScope.assessmentMaster}</p>
										<c:choose>
											<c:when test="${empty requestScope.UniqueNumber}">
										Transcation failed try Again..!
										</c:when>
											<c:when test="${ErrMsg eq 'failed'}">
										Transcation failed try Again..!
										</c:when>
											<c:when test="${not empty requestScope.UniqueNumber}">
												<div align="center" style="font-size: 12px">

													This is to acknowledge that the Self Assessment has been
													received from Mr/Mrs/Miss :


													<!-- application -->
													<%-- <c:forEach items="${requestScope.assessmentMaster}" var="applicatoindata"> --%>
													<b><c:out value="${requestScope.ownerSurName}" />.<c:out
															value="${requestScope.ownerName}" /></b> Having Application
													No. : <b><c:out value="${requestScope.UniqueNumber}" /></b>
													<c:set value="${requestScope.ownerSurName}"
														var="ownerSurName"></c:set>
													<c:set value="${requestScope.ownerName}" var="ownerName"></c:set>
													<c:set value="${requestScope.UniqueNumber}"
														var="uniqueRequest"></c:set>
													Dated:
													<c:out value="${requestScope.entrydate}" />
													<c:set value="${requestScope.entrydate}" var="date"></c:set>
													<%-- </c:forEach> --%>
													<p></p>
													<p></p>
													<span style="float: right;; padding-right: 30px;">&nbsp;
														Date: <fmt:formatDate type="date" value="${date}" />&nbsp;
													</span> <br> <br>

													<div class="row">
														<div class="col-md-6">
															<a style="text-decoration: none;"
																href="${pageContext.request.contextPath}/acknowledgement.do?uniquerequestid=<c:out value="${uniqueRequest}"/>"
																target="_blank">Download PDF</a>

														</div>
														<div class="col-md-6">
															<a><p onclick="javascript:receiptPrint('printDiv')">Print
																	Acknowledgment</p></a>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12 text-center">
															<a
																href="http://cdma.telangana.gov.in/FeedBack/FeedBack.aspx?UserId=${uniqueRequest}&UserName=PTSELFASSESSMENT"
																target="_blank"> <span
																style="color: maroon; font-size: 16px; font-weight: bold; text-decoration: none;">
																	Feedback</span>
															</a>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												Transcation failed try Again..!
												</c:otherwise>
										</c:choose>
									</div>
								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="content" style="width: 40%; top: 20%; display: none;"
		id="printDiv">
		<h2 style="color: black;; text-align: center;">Self Assessment
			Online Application Acknowledgment</h2>
		<table border="2" cellpadding="2" cellspacing="2"
			class="dashboardTable" style="" align="center">
			<tr>
				<td colspan="4" align="center"><span
					style="color: black; font-size: 18px;"> </span></td>
			</tr>
			<tr>
				<div align="center" style="font-size: 12px">

					This is to acknowledge that the Self Assessment has been received
					from Mr/Mrs/Miss :

					<!-- application -->

					<b><c:out value="${ownerSurName}" />.<c:out
							value="${ownerName}" /></b> Having Application No. : <b><c:out
							value="${uniqueRequest}" /></b> Dated:
					<c:out value="${date}" />

				</div>
			</tr>

		</table>
	</div>



</body>
</html>