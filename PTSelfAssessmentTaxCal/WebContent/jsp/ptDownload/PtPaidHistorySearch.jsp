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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PT Selfassessment Seach Page</title>
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
				.getElementById("assessment").value;
		var reqno = /^[1]+[0-9]{5}$/;
		if (!reqno.test(uniqueRequestNumber)) {
			document.getElementById("error").innerHTML = "Enter valid Request Number..";
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div class="container_12">
		<div class="row">
			<div class="col-md-12">

				<div class="col-md-4"></div>
				<div class="col-md-4" style="font-weight: bold;">Search
					Application Status</div>
				<div class="col-md-4"></div>
			</div>
	</div>
			<br> <br>

			<form action="getSearchAssHistorySms.do" name="applicationstaus" method="post"
				onsubmit="return validreqno()">
				<div class="col-md-12">

					<label class="col-md-4">Enter Assessment Number</label>
					<div class="col-md-6">
						<input type="text" id="assessment"
							name="assessment" value="${assessment}"><font color="red">*(10 Digit Assessment No)</font>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="col-md-12">

					<label class="col-md-4">Enter Mobile No.</label>
					<div class="col-md-4">
						<input type="text" id="mobile"
							name="mobile" value="${mobile}">
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2 minifont" align="center"></div>
					<div class="col-md-4">
						<%-- <p class="error" id="error">${requestScope.errormsg}</p> --%>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-4"></div>
					<div class="col-md-4"><br>
						<input type="submit" id="target" value="Send OTP"
							class="btn btn-primary">
					</div>
					<div class="col-md-4"></div>
				</div>
		
		<div>
			<br>
		</div>
	</form></div>	
	
</body>
</html>