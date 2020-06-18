<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CDMA eTrade Application</title>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
        </script>
<script>

    $(document).ready(function() {
        function disableBack() { window.history.forward() }

        window.onload = disableBack();
        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    });

</script>


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

<body>

	<fieldset class="col-md-3"
		style="position: absolute; left: 0; right: 0; margin: auto;">
		<div class="panel panel-primary">
			<div class="panel-heading">Login Here</div>
			<div class=" panel-body ">
				<div class="panel-group" align="center">

					<form method="POST" action="cashCounterLoginAuthenticationQDD.do" name="CounterLogin" id="CounterLogin"
						onsubmit="return checkValidation();">
						<input type="hidden" name="UniqueNumber" id="UniqueNumber"
		value="<c:out value="${requestScope.UniqueNumber}"/>">
<input type="hidden" name="cheque_DD_No" id="cheque_DD_No"	value="<c:out value="${requestScope.cheque_DD_No}"/>">
<input type="hidden" name="cheque_DD_Date" id="cheque_DD_Date"	value="<c:out value="${requestScope.cheque_DD_Date}"/>">
<input type="hidden" name="cheque_DD_BankBranch" id="cheque_DD_BankBranch"	value="<c:out value="${requestScope.cheque_DD_BankBranch}"/>">
<input type="hidden" name="DDCheque" id="DDCheque"	value="<c:out value="${requestScope.DDCheque}"/>">

						<div class="form-group">
							<div class="inputGroupContainer">
								<div class="input-group col-md-12">
									<span
										style="color: #011311; font-size: 13px; font-weight: bold">Enter Counter
										User Id</span><input name="counterUserId" id="counterUserId" placeholder="User Id"
										class="form-control col-md-12" type="text">

								</div>
							</div>
						</div>

						<div class="form-group ">
							<div class="inputGroupContainer">
								<div class="input-group col-md-12">
									<span
										style="color: #011311; font-size: 13px; font-weight: bold">Enter Counter
										Password</span> <input name="counterUserPassword" id="counterUserPassword"
										placeholder="Password" class="form-control col-md-12"
										type="password">

								</div>
							</div>
						</div>

						<div class="col-md-12 form-group submit">
							<input type="submit" value="    Login    "
								style="font-size: 15px; color: #020f1c; font-weight: bold; margin: 5px 0 0 0px" />
							&nbsp;&nbsp;&nbsp;<input type="reset" value="    Reset    "
								style="font-size: 15px; color: #020f1c; font-weight: bold; margin: 5px 0 0 0px" />
						</div>
						<div class="input-group col-md-12" align="center">
							<div class="col-md-12" align="center" id="errorBox"
								style="color: red; font-size: 14px; font-weight: bold;"></div>
						</div>

					</form>
				</div>
			</div>
		</div>

		<div id="logoutMsg">
			<span style="color: #f13611; font-size: 15px; font-weight: bold;">
				<c:out value="${message}"></c:out>
			</span>
		</div>

	</fieldset>
</body>

<script type="text/javascript">
	function checkValidation() {

		var userId = document.login.userId.value;
		var userPassword = document.login.userPassword.value;
		document.getElementById("logoutMsg").innerHTML = "";
		if (userId == '' || userId == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Your Login Id";
			document.getElementById("userId").focus();
			return false;
		}
		if (userPassword == '' || userPassword == null) {
			document.getElementById("errorBox").innerHTML = "Please Enter Your Password";
			document.getElementById("userPassword").focus();
			return false;
		}

		return true;
	}
</script>

</html>