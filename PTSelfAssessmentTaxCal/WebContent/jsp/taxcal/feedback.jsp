
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

<script src="<%=path%>/jsp/js/jquery.min.js"></script>
<script src="<%=path%>/jsp/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<script>
	/* $(function(e) {

		var isValid = $("#isValid").val();
		var uniqueRequestNumber = $("#uniqueRequestNumber").val();

		if (isValid == 'y') {
			var url = "http://cdma.telangana.gov.in/FeedBack/FeedBack.aspx?UserId="
					+ uniqueRequestNumber + "&UserName=PTSELFASSESSMENT";
			window.open(url, '_blank');
		}

	}); */

	function checkValidation() {

		if ($("#uniqueRequestNumber").val() == ''
				|| $("#uniqueRequestNumber").val() == 0) {

			$("#errorMsg").html('Please Enter Your Unique Request Number.');//alert('dd');
			return false;
		}
	}
</script>

<form action="feedback.do" method="POST" name="feedback" id="feedback"
	onsubmit="return checkValidation();">
	<fieldset>
		<div class="col-md-12">
			<br>
			<div class="panel panel-primary">
				<div class="panel-heading">Applicant Feedback</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-6 control-label" for="textinput">Enter
										Your PT Assessment Number </label>
									<div class="col-md-6" style="margin-bottom: 15px;">
										<input class="form-control" name="uniqueRequestNumber"
											id="uniqueRequestNumber" placeholder="Uniq request number"
											value="${applStatus.uniqueRequestNumber}">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="col-md-6" style="margin-bottom: 15px;">
										<input type="submit" class="btn btn-success"
											value="    Feedback    ">

									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12 text-center">
							<c:if
								test="${applStatus.uniqueRequestNumber != 0 && not empty applStatus}">
								<input name="isValid" id="isValid" value="y" type="hidden">
								<!-- <span style="color:navy;  font-size: 13px;font-weight: bold;"> 
								<br>Please
									allow the Pop-Up for this site to provide your feedback. </span> -->
							</c:if>
						</div>
					</div>



					<div class="col-md-12 control-label" align="center"
						style="color: red; font-weight: bold; font-size: 15px;">
						<br> ${message} <br>
						<div id="errorMsg"></div>
					</div>

				</div>

			</div>
		</div>
	</fieldset>
</form>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/js/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/js/jquery-ui.js"></script>