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
				.getElementById("otpt").value;
		var reqno = /^[1]+[0-9]{4}$/;
		if (!reqno.test(uniqueRequestNumber)) {
			document.getElementById("error").innerHTML = "Enter valid Request Number..";
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	
			<form action="getAssPaidHistory.do" name="applicationstaus" method="post"
				onsubmit="return validreqno()">
				<div class="col-md-12">
<input type="hidden" id="otp" name="otp" value="${requestScope.otp}">
<input type="hidden" id="assessment" name="assessment" value="${requestScope.assessment}">
<input type="hidden" id="mobile" name="mobile" value="${requestScope.mobile}">
					<label class="col-md-4">Enter OTP</label>
					<div class="col-md-6">
						<input type="text" id="otpt"
							name="otpt" value="${otpt}">
					</div>
					<div class="col-md-4"></div>
				</div>
					<div class="col-md-12">
					<div class="col-md-4"></div>
					<div class="col-md-4"><br>
						<input type="submit" id="target" value="Submit"	class="btn btn-primary">
					</div>
					<div class="col-md-4"></div>
				</div>
		
		<div>
			<br>
		</div>
		</form></div>
	
</body>
</html>