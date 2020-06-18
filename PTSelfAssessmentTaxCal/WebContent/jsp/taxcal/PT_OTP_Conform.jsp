
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



<form action="PT_OTPConform.do" method="POST" name="PT_OTPconfrm" id="PT_OTPconfrm"
	onsubmit="return checkValidation();">
	<fieldset>
		<div class="col-md-12">
			<br>
			<div class="panel panel-info">
				<div class="panel-heading" align="center"><font size="5"><p class="text-info" ><b>PT Self Assessment Form<b></b></p> </div></font>
				<div class="panel-body"><br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-md-5 control-label" for="textinput" align="right">OTP:<font color="red">*</font> </label>
									<div class="col-md-3" style="margin-bottom: 15px;">
										<input type="text" class="form-control" name="otpId"
											id="otpId">
											<input type="hidden" class="form-control" name="mobileNo"
											id="mobileNo" placeholder="Mobile Number" value="${mobileNo}">
											
									</div>
									<div class="col-md-2" style="margin-bottom: 15px;">
										<input type="submit" class="btn btn-info"
											value="Submit OTP"></div>
										<div class="col-md-1" style="margin-bottom: 15px;">
										<button type="button" class="btn btn-danger">ResendOTP</button>
										<!-- <input type="submit" class="btn btn-link"
											><forn color="red">value="ResendOTP"</font> -->
								</div>
							
							
 						</div>

						
					</div>
				</div>

			</div>
		</div>
	</fieldset>
</form>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/js/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/js/jquery-ui.js"></script>