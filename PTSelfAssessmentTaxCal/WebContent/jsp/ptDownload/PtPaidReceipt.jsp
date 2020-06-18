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
<div class="container" >
<div class="row" >
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
 <div class="col-md-3"></div>
 <div class="col-md-8">
 <font color="black"><b>MUNCIPAL ADMINSTRATION DEPARTMENT</b></font>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
 <div class="col-md-2"></div>
 <div class="col-md-10">
 <h4><b><c:out value="${fn:toUpperCase(requestScope.ulb_name)}"/>  <c:out value="${requestScope.grade_name}"/></b></h4>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
 <div class="col-md-4"></div>
 <div class="col-md-6">
 <p><b>                       Receipt of Property Tax</b></p>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
 
 <div class="col-md-3">
	<label>Date :</label>
 </div>
<div class="col-md-3">
<c:out value="${requestScope.DT_ETRYDT}"/>      
</div>
<div class="col-md-3">
	<label>Receipt No. :</label>
 </div>
<div class="col-md-3">
<c:out value="${requestScope.C_RCPTNO}"/>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-3">
	<label>Door No. :</label>
 </div>
 <div class="col-md-4">
<c:out value="${requestScope.DOORNo}"/>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-4">
	<label>Name of the Remitter :</label>
 </div>
<div class="col-md-5">
<c:out value="${requestScope.VC_ONRSURNAME}${requestScope.VC_ONRNAME}"/>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-4">
	<label>Name of Locality :</label>
 </div>
<div class="col-md-4">
<c:out value="${requestScope.VC_LCTYNAME}"/>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-4">
	<label>Details of Tax :</label>
 </div>
<div class="col-md-4">
<c:out value="${requestScope.Total}"/>/-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Arrear/Current/Total</label>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-3">
	<label>Demand :</label>
 </div>
<div class="col-md-6">
<c:out value="${requestScope.DT_PAIDFRMPRDDT}"/> To <c:out value="${requestScope.DT_PAIDTOPRDDT}"/>
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-5">
	<label>Amount Collected :</label>
 </div>
<div class="col-md-4">
<c:out value="${requestScope.Total}"/>/-
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-5">
	<label>Balance :</label>
 </div>
 <div class="col-md-4">
<c:out value="${requestScope.balance}"/>/-
</div></div>

<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-5">
	<label>Period :</label>
 </div>
<div class="col-md-6">
<c:out value="${requestScope.Totalpaidamount}"/>/-&nbsp;&nbsp;&nbsp;&nbsp;(Paid Amount)
</div></div>
<div class="col-md-6 col-md-pull-2 col-md-offset-4" style="background-color:#e0f2f1;">
<div class="col-md-4"></div>
<div class="col-md-4">
<input type="button"	class="btn btn-primary btn-sm"  name="print"	value="Print Receipt"	onclick="javascript:receiptPrint('printDiv','headDiv');" />
	</div>			
	</div></div></div>

<!--For Printer Call  -->
<div  style="width: 40%; display: none;" id="headDiv" >
<div class="content" style="width: 40%; top: 20%;display: none;" id="printDiv" >
	<div class="container">
<div class="row">
<pre>				<b>MUNCIPAL ADMINSTRATION DEPARTMENT</b></pre>
<h4 align="center"><c:out value="${fn:toUpperCase(requestScope.ulb_name)}"/>  <c:out value="${requestScope.grade_name}"/></h4>
 <pre>				<b>Receipt of Property Tax</b></pre>
 <div class="col-md-12">
 <div class="col-md-3">
<pre>Date :      	<c:out value="${requestScope.DT_ETRYDT}"/>  					    Receipt No.:   <c:out value="${requestScope.C_RCPTNO}"/> </pre>
</div></div>
 <div class="col-md-12">
<div class="col-md-3">
	<pre>Door No. :	<c:out value="${requestScope.DOORNo}"/></pre>
 </div></div>
 
 <div class="col-md-12">
<div class="col-md-4">
	<pre>Name of the Remitter :		<c:out value="${requestScope.VC_ONRSURNAME}${requestScope.VC_ONRNAME}"/></pre>
 </div>
 <div class="col-md-12">
<div class="col-md-4">
	<pre>Name of Locality:		<c:out value="${requestScope.VC_LCTYNAME}"/>_ </pre>
 </div></div>
<div class="col-md-12">
<div class="col-md-4">
	<pre>Details of Tax :		<c:out value="${requestScope.Total}"/>/-  	Arrear/Current/Total</pre>
 </div></div>
<div class="col-md-12">
<div class="col-md-3">
	<pre>Demand :			<c:out value="${requestScope.DT_PAIDFRMPRDDT}"/> To <c:out value="${requestScope.DT_PAIDTOPRDDT}"/></pre>
 </div></div>
<div class="col-md-12">
<div class="col-md-5">
	<pre>Amount Collected :		<c:out value="${requestScope.Total}"/>/-</pre>
 </div></div>
<div class="col-md-12">
<div class="col-md-5">
	<pre>Balance :		<c:out value="${requestScope.balance}"/>/-</pre>
 </div></div>
<div class="col-md-12">
<div class="col-md-5">
	<pre>Period :			<c:out value="${requestScope.Totalpaidamount}"/>/-   (Paid Amount)</pre>
 </div></div>
<pre>This document is produced electronically and therefore does not require a signature.</pre>
</div></div></div></div>						
</body>
</html>

