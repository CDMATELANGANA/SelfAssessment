
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<%
	String path = request.getContextPath();
%>
</head>

<script src="<%=path%>/jsp/js/jquery.min.js"></script>
<script src="<%=path%>/jsp/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<form action="" method="POST" name="AppSelect" id="AppSelect" >
	<fieldset>
		<div class="col-md-12">
			<br>
			<div class="panel panel-info">
				<div class="panel-heading" align="center">
				<font size="5"><p class="text-info" ><b>Select PT Self Assessment  Based On Your Requirement</b></p></font> </div>
				<div class="panel-body"><br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-12">
								<div class="form-group">
								<div class="col-md-2" style="margin-bottom: 15px;"></div>
								<div class="col-md-4" style="margin-bottom: 15px;">
								<a href="getassessment.do"><font color="black"><b>New Pt Self Assessment Application</b></font></a>
								</div>
								<div class="col-md-4" style="margin-bottom: 15px;">
								<a href="ptMeasurementFind.do"><font color="black"><b>Add New Measurement</b> </font></a>
								<input type="image"  src="<%=path%>/jsp/images/new-blink.gif" value="" width="40">
								</div>
									</div></div></div></div></div>
			</div>
		</div>
	</fieldset>
</form>

