<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>
<script type="text/javascript"	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/css/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/css/jquery-ui.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.error {
	color: red;
}

.success {
	color: green;
}

.col-md-6 {
	padding-top: 4px;
	padding-bottom: 10px;
}

input:focus {
	border: 2px solid #337ab7;
	border-radius: 4px;
}

.miniheader {
	background-color: #2e6da4;
	padding-bottom: 3px;
	color: #fff;
	font-size: large;
}

.headdiv {
	padding-top: 3px;
}

.minifont {
	font-size: smaller;
}
</style>
<script type="text/javascript">
	function validreqno() {
		var uniqueRequestNumber = document
				.getElementById("uniqueRequestNumber").value;
		var reqno = /^[1]+[0-9]{16}$/;
		if (!reqno.test(uniqueRequestNumber)) {
			document.getElementById("error").innerHTML = "Enter valid Request Number..";
			return false;
		}

		return true;
	}
</script>
<script type="text/javascript">
 
$(function() {
	var fromDate = $("#cheque_DD_Date").datepicker({
		 defaultDate : "+1w",
		//defaultDate : "+0w",
		dateFormat : 'mm/dd/yy',
		//changeMonth : false,
		//numberOfMonths: 2,
		changeMonth: true,
        changeYear: true,
        minDate: '-179D',
		 maxDate:"+0D",
});

$("#cheque_DD_Date").click(function() {
	$(this).blur();
});
});

</script>
</head>
<body>
	<div class="container_12">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">
					Application Information:
					<c:out value="${UniqueNumber}" />
				</div>
				<div class="panel-body">
				<div style="font: bold; color: red;"><marquee>*  Cash/Cheque/DD Counter Is Only Applicable For Office Use.  *</marquee></div>
					<div class="panel-group">

						<div class="col-md-12">
							<label class="col-md-3 "></label>
							<div class="col-md-6 " style="color: blue;font-size: 16px;line-height: 30px">
								Application Submitted Successfully
								Please Pay the Provisional Self Assessment Amount
							</div>
						<div class="col-md-12">
							<label class="col-md-3 "></label>
								<!-- <div style="color: blue;font-size: 16px;line-height: 30px">
								&nbsp;&nbsp;<b>Note: Application Submitted Successfully
								Please Pay the Provisional Self Assessment Amount </b>
							</div> -->
							
							<label class="col-md-3 "></label>
						</div>
						<div class="col-md-6">
							<label class="col-md-6 ">Self Assessment No:</label>
							<div class="col-md-6 ">
								<div class="input-group">${requestScope.UniqueNumber}</div>
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-md-6 ">Name :</label>
							<div class="col-md-4 ">
								<div class="input-group">${requestScope.ownerName}</div>
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-md-6 ">Owner Address:</label>
							<div class="col-md-4 ">
								<div class="input-group">${requestScope.OWNERVILLAGE}</div>
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-md-6 ">Payment Amount :</label>
							<div class="col-md-4 ">
								<div class="input-group">
									<c:out value="${requestScope.PaymentAmount}" />
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
		<label class="col-md-2">Payment  Mode :</label>
		<font color="red"><b>Note: Befor Pay the Prpoerty Tax Self Assessment,Please Note Down 17 digit Application Number.</b></font>
		<div class="col-md-10">
		<div class="input-group">
     	<select name="payMode" id="payMode"	onchange="PayActiveFunction()" class="form-control ">
		<option>Select Payment Mode</option>
	    <option value="Digital">Digital Payment</option>
	    <option value="Cash">Cash Payment (Office Counter)</option>
	    <option value="Q">Q-Cheque Payment (Office Counter)</option>
	    <option value="DD">DD-DemandDraft Payment (Office Counter)</option>
	    </select>
       </div></div></div>
						
		<div class="col-md-12">
							<div class="col-md-4 "></div>
							<div class="col-md-4 ">
								<div class="input-group">
									<form action="cashcounterLogin.do" name="requestnumber" method="post">
										<input type="hidden" name="requestnumber" id="requestnumber"
											value="<c:out value="${UniqueNumber}"/>"> <input
											type="submit" class="btn btn-primary" value="Cash Payment" id="submitC">
									</form>
								</div>
							</div>				
		<!-- For Cheque  -->
		<form action="cashCounterLoginQDD.do" name="requestnumber" method="post" id="qdd" onsubmit="return validateform()">								
		  <input type="hidden" name="requestnumber" id="requestnumber"
		 value="<c:out value="${requestScope.UniqueNumber}"/>">
		 <input type="hidden" name="DDCheque" id="DDCheque" value="">
			
			<div class="row" >
					<div class="col-md-6">
					<label class="col-md-5 ">Cheque/DD Date:<font color="red">*</font></label>
						<div class="col-md-5">
				<input name="cheque_DD_Date" id="cheque_DD_Date" readonly="readonly" placeholder="cheque_DD_Date" 
							class="form-control" type="text"/>
						</div>
					</div>
					
					<div class="col-md-6">
					<label class="col-md-5 ">Cheque/DD Number:<font color="red">*</font></label>
						<div class="col-md-2">
							<input type="text" name="cheque_DD_No" id="cheque_DD_No">
						</div>
					</div>
					<div class="col-md-6">
					<label class="col-md-5 ">Bank Branch Namer:<font color="red">*</font></label>
						<div class="col-md-2">
							<input type="text" name="cheque_DD_BankBranch" id="cheque_DD_BankBranch">
							
						</div>
						<div class="col-md-8">
						<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;care fully enter Bank Branch Name</font></div>
					</div></div>
					<div class="col-md-6">
					<font color='red'><div id="errormsg"></div></font>
					</div>
			
		<div class="col-md-4"> </div>
		<div class="col-md-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" class="btn btn-primary" value="Cheque/DD Payment" id="submitQDD"></div>
		</form>
		<!-- End Cheque  -->				
						<div class="col-md-12">
							<div class="col-md-4 "></div>
							<div class="col-md-4 ">
								<div class="input-group">
									<!-- <form action="ptselfAssessmentPay.do" name="requestnumber" method="post"> -->
									<form action="hdfcptPayTest.do" name="requestnumber" method="post">
									<input type="hidden" name="requestnumber" id="requestnumber"
											value="<c:out value="${UniqueNumber}"/>"> <input
											type="submit" class="btn btn-primary" value="Digital Payment" id="submitD">
									</form>
								</div>
							</div>
							<div class="col-md-4 "></div>
						</div>


					</div>
				</div>
			</div>
		</fieldset>
	</div>
	</div>
	<script type="text/javascript">
	function validateform() {


		var bank = document.getElementById("cheque_DD_BankBranch").value;
		var QDD = document.getElementById("cheque_DD_No").value;
		var QddDate = document.getElementById("cheque_DD_Date").value;
		//var rex_qdd = /^[1-9]+[0-9]{6}$/;
		var rex_qdd = /^[0-9]{6}$/;
		var rex_bname = /^[a-zA-Z]*$/;
		
		if (QddDate == "") {
			alert("lll");
			document.getElementById("errormsg").innerHTML = "please select Date";
			document.getElementById("cheque_DD_Date").value = "";
			document.getElementById("cheque_DD_Date").focus();
			return false;
		}
		if (bank == "") {
			alert("Ente.");
			document.getElementById("errormsg").innerHTML = "please enter  bank branch name";
			document.getElementById("cheque_DD_BankBranch").value = "";
			document.getElementById("cheque_DD_BankBranch").focus();
			return false;
		} 
		if (!rex_bname.test(bank)
				&& bank.length >= 1) {
			alert("Ente.");
			document.getElementById("errormsg").innerHTML = "please enter valid bank branch name";
			document.getElementById("cheque_DD_BankBranch").value = "";
			document.getElementById("cheque_DD_BankBranch").focus();
			return false;
		}
		if (QDD == "") {
			alert("Ente.");
			document.getElementById("errormsg").innerHTML = "please enter  cheque/DD No.";
			document.getElementById("cheque_DD_No").value = "";
			document.getElementById("cheque_DD_No").focus();
			return false;
		} 
		if (!rex_qdd.test(QDD))
			 {
			alert("Ente.");
			document.getElementById("errormsg").innerHTML = "please enter valid cheque/DD No.";
			document.getElementById("cheque_DD_No").value = "";
			document.getElementById("cheque_DD_No").focus();
			return false;
		}
		return true;
	}
	</script>
	
	
</body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
       </script>
	<script>
	window.onload = function() {
	    document.getElementById("submitD").style.visibility = "hidden";
	    document.getElementById("submitC").style.visibility = "hidden";
	    document.getElementById("qdd").style.visibility = "hidden";
	    document.getElementById("submitQDD").style.visibility = "hidden";
	    $(document).ready(function() {
	        function disableBack() { window.history.forward() }

	        window.onload = disableBack();
	        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
	    });

	    
	}</script>
	<!-- <script>
	window.onload = function() {
	    document.getElementById("submitD").style.visibility = "hidden";
	    document.getElementById("submitC").style.visibility = "hidden";
	}
	</script> -->
<script>
function PayActiveFunction()
	{
var select=document.getElementById("payMode").value;
var e = document.getElementById("payMode");
var value = e.options[e.selectedIndex].value;
var text = e.options[e.selectedIndex].text;
document.getElementById("DDCheque").value=value;

if(select===("Digital"))
{
	alert("You Are Salected Digital Payment(Accept Only Credit Card/NetBanking)");
	
	document.getElementById("submitD").style.visibility = "visible";
	document.getElementById("submitC").style.visibility = "hidden";
	document.getElementById("qdd").style.visibility = "hidden";
	 document.getElementById("submitQDD").style.visibility = "hidden";
	}
if(select===("Cash"))
{
	alert("You Are Salected Cash Counter Payment(For Office Use Only)");
	document.getElementById("submitC").style.visibility = "visible";
	document.getElementById("submitD").style.visibility = "hidden";
	document.getElementById("qdd").style.visibility = "hidden";
	 document.getElementById("submitQDD").style.visibility = "hidden";
	}
	
if(select===("Q"))
{
	alert("You Are Salected Cash Counter Payment(For Office Use Only)");
	document.getElementById("qdd").style.visibility = "visible";
	document.getElementById("submitQDD").style.visibility = "visible";
	document.getElementById("submitD").style.visibility = "hidden";
	document.getElementById("submitC").style.visibility = "hidden";
	}
if(select===("DD"))
{
	alert("You Are Salected Cash Counter Payment(For Office Use Only)");
	document.getElementById("qdd").style.visibility = "visible";
	 document.getElementById("submitQDD").style.visibility = "visible";
	document.getElementById("submitD").style.visibility = "hidden";
	document.getElementById("submitQ").style.visibility = "hidden";
	}
	}
</script>

</html>