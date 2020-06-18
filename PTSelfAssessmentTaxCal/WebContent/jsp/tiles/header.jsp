<!-- <h2>Header</h2> -->

<html>
<head>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<style type="text/css">
.header1 {
	background-color: #782669;
}

.col-md-8 {
	color: #fff;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<div class="header">
	<div class="row">
		<div class="col-md-2">
			<div id="logo" style="float: left;">
				<a href="http://cdma.telangana.gov.in/" target="_blank"><img src="./jsp/images/logo1.png"></a>
			</div>
		</div>
		<div class="col-md-8">
			<div style="clear: both;"></div>
			<h1 class="comm" align="center">Commissioner &amp; Director of
				Municipal Administration</h1>
			<h1 align="center">Government of Telangana</h1>
			<h4 align="center">Property Tax Self Assessment With Payment</h4>
		</div>
	</div>

	  <c:choose>
		<c:when test="${not empty permissionSession}">
			<div style="right: 50px; margin: auto; position: absolute;"
				align="right">
				<a href="logoutprecess.do"><span
					style="font-size: 14px; color: #FFF000;">Logout</span></a>
			</div>
		</c:when>
		<c:otherwise>
			<div
				style="right: 50px; margin: auto; padding: 0 0 20px 0; position: absolute;"
				align="right">
				<a href="login.do" style="color: white; text-decoration: none;font-size: 14px;">Login</a>
			</div>
		</c:otherwise>
	</c:choose>  

	<%-- <c:if test="${not empty permissionSession}">
		<div style="right: 50px; margin: auto; position: absolute;"
			align="right">
			<a href="logoutprecess.do"><span
				style="font-size: 14px; color: #FFF000;">Logout</span></a>
		</div>
	</c:if> --%>

</div>

<hr />

</html>