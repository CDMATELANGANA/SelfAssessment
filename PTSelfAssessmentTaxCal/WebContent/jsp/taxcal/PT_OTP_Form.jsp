
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<%
	String path = request.getContextPath();
%>
</head>
<%-- <%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%> --%>

<script src="<%=path%>/jsp/js/jquery.min.js"></script>
<script src="<%=path%>/jsp/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">



<form action="PT_OTPSave.do" method="POST" name="PT_OTP" id="PT_OTP" onsubmit="return checkValidation();">
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
									<label class="col-md-5 control-label" for="textinput" align="right">Mobile Number:<font color="red">*</font> </label>
									<div class="col-md-3" style="margin-bottom: 15px;">
										<input type="text" class="form-control" name="mobileNo"
											id="mobileNo" placeholder="Mobile Number">
									</div>
									<div class="col-md-4" style="margin-bottom: 15px;">
										<input type="submit" class="btn btn-info"
											value="Send OTP">
								</div>
							</div>
							<div class="col-md-12 control-label" align="center"
								id="errorBoxContact"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
							
 						</div>

						
					</div>
				</div>

			</div>
		</div>
	</fieldset>
</form>
<div align="center"><h3 class="text-info">Demo Video for Property tax Self Assessment</h3>
<video width="1024" height="600" controls>
  <source src="/PTSelfAssessmentTaxCal/selfassessment.webm" type="video/WebM">
</video>
</div>
<script type="text/javascript">
	function checkValidation() {

		var mobile_reg = /^[6-9]+[0-9]{9}$/;
		var moNo = document.PT_OTP.mobileNo.value;
		if (!mobile_reg.test(moNo)) {
			document.getElementById("errorBoxContact").innerHTML = "Please Enter Valid Mobile Number ";
			document.getElementById("mobileNo").value = "";
			document.getElementById("mobileNo").focus();
			return false;
		}
			document.getElementById("errorBoxContact").innerHTML = "";
		
	}
	</script>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/js/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/js/jquery-ui.js"></script>