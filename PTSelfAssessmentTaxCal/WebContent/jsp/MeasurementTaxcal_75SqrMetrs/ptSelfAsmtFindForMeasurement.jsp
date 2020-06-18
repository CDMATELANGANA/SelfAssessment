
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Non Hoarding Advertisement</title>
<%
	String path = request.getContextPath();
%>
</head>
<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<%@page isELIgnored="false" %>
<script src="<%=path%>/jsp/js/jquery.min.js"></script>
<script src="<%=path%>/jsp/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

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

.tableborder {
	border: 2px solid #337ab7
}
</style>

<body>

<form action="findExistAsmt.do" method="POST" name="findAsmt" id="findAsmt"
	onsubmit="return checkValidation();">
	<fieldset>
		<div class="col-md-12">
			<br>
			<div class="panel panel-info">
				<div class="panel-heading" align="center"><font size="5"><p class="text-info" ><b>Enter Detail For PT Add Measurement <b></b></p> </div></font>
				<div class="panel-body"><br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-md-5 control-label" for="asmt" align="right">Enter Assessment No.<font color="red">*</font> </label>
									<div class="col-md-3" style="margin-bottom: 15px;">
										<input type="text" class="form-control" name="asmt"
											id="asmt">
									</div>
									<label class="col-md-5 control-label" for="doorno" align="right">Enter Door No.<font color="red">*</font> </label>
									<div class="col-md-3" style="margin-bottom: 15px;">
										<input type="text" class="form-control" name="doorno"
											id="doorno">
									</div>
									</div></div>
									<h4><font color="red">${msg}</font></h4>
									<div class="col-md-12">
							<div class="col-md-12">
							<div class="col-md-6"></div>
									<div class="col-md-2" style="margin-bottom: 15px;">
										<input type="submit" class="btn btn-info"
											value="Search"></div>
								</div></div>		
 						</div>
					</div>
				</div>

			</div>
		</div>
	</fieldset>

</html>
<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/js/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/js/jquery-ui.js"></script>