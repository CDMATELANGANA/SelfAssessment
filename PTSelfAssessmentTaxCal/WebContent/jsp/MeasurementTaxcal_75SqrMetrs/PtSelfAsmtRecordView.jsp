
<%-- <%@	include file="/pages/common/include.jsp"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Self Assessment Application</title>
<%
	String path = request.getContextPath();
%>
</head>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>

<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>
<script type="text/javascript"src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/css/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/css/jquery-ui.js"></script>


<script type="text/javascript" src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>

<%-- <script type="text/javascript"	src="<%=path%>/pages/etrade/gen_validatorv4.js"></script> --%>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		var fromDate = $("#regDate").datepicker({
			//defaultDate : "+0w",
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			changeYear : true,
			//numberOfMonths: 2,
			//minDate : 0,
			//startDate:new Date(1980, 1, 1),
			//minDate: new Date(1980, 1, 1),		

			onSelect : function(date) {
				var date2 = $('#regDate').datepicker('getDate');
			}

		});
		
		var fromDate = $("#B_PermisionDate").datepicker({
			//defaultDate : "+0w",
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			changeYear : true,
			
			//numberOfMonths: 2,
			//minDate : 0,
			//startDate:new Date(1980, 1, 1),
			//minDate: new Date(1980, 1, 1),		

			onSelect : function(date) {
				var date2 = $('#B_PermisionDate').datepicker('getDate');
			}

		});

	});
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var counter = 2;

						$("#addButton")
								.click(
										function() {
//alert("mearuement are saved!")
											var ulbId = document
													.getElementById("ulbid").value;
														if (counter > 13 || ulbId == 0){
												return false;
											}
											$("#DivBoxDiv" + counter).show();
											counter++;
										});

						$("#removeButton").click(function() {
							if (counter == 2) {
								return false;
							}

							counter--;
							$("#DivBoxDiv" + counter).hide();

						});

					});
</script>

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

.man_heading {
	background-color: #c93311;
}

.sub_heading {
	background-color: #cff2f9;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	$('input[name="planaprvl"]:radio').on('change', function(e) {
		var palnVal = this.value;
		//console.log(palnVal);
		if (palnVal == 'Y') {

			$('#regdocno').removeAttr("disabled");
			$('#regDate').removeAttr("disabled");
		    $('#B_PermisionNo').removeAttr("disabled"); 
			$('#B_PermisionDate').removeAttr("disabled");

		} else {

			//$('#regdocno').prop("disabled", "disabled");
			//$('#regDate').prop("disabled", "disabled");
			 $('#B_PermisionNo').prop("disabled","disabled"); 
			$('#B_PermisionDate').prop("disabled","disabled");
			$('#usageType').prop("disabled","disabled"); 
			$('#cPlinth').prop("disabled","disabled"); 
			$('#B_PermisionNo').val(""); 
			$('#B_PermisionDate').val("");
			$('#usageType').val(""); 
			$('#cPlinth').val("");
			$('#fileToUpload2').prop("disabled", "disabled");
						}
		return false;
	});
});

 function checkBuildingPlan2()
{
 var select=document.getElementById("planaprvl1").value;
	alert(select);
	if(select=='Y')
		{
		$('#usageType2').removeAttr("disabled");
		$('#cPlinth2').removeAttr("disabled");
		}
	else{
		$('#usageType2').prop("disabled","disabled"); 
		$('#cPlinth2').prop("disabled","disabled");
	} 
}
 function checkBuildingPlan3()
 {
  var select=document.getElementById("planaprvl2").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType3').removeAttr("disabled");
 		$('#cPlinth3').removeAttr("disabled");
 		}
 	else{
 		$('#usageType3').prop("disabled","disabled"); 
 		$('#cPlinth3').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan4()
 {
  var select=document.getElementById("planaprvl3").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType4').removeAttr("disabled");
 		$('#cPlinth4').removeAttr("disabled");
 		}
 	else{
 		$('#usageType4').prop("disabled","disabled"); 
 		$('#cPlinth4').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan5()
 {
  var select=document.getElementById("planaprvl4").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType5').removeAttr("disabled");
 		$('#cPlinth5').removeAttr("disabled");
 		}
 	else{
 		$('#usageType5').prop("disabled","disabled"); 
 		$('#cPlinth5').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan6()
 {
  var select=document.getElementById("planaprvl5").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType6').removeAttr("disabled");
 		$('#cPlinth6').removeAttr("disabled");
 		}
 	else{
 		$('#usageType6').prop("disabled","disabled"); 
 		$('#cPlinth6').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan7()
 {
  var select=document.getElementById("planaprvl6").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType7').removeAttr("disabled");
 		$('#cPlinth7').removeAttr("disabled");
 		}
 	else{
 		$('#usageType7').prop("disabled","disabled"); 
 		$('#cPlinth7').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan8()
 {
  var select=document.getElementById("planaprvl7").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType8').removeAttr("disabled");
 		$('#cPlinth8').removeAttr("disabled");
 		}
 	else{
 		$('#usageType8').prop("disabled","disabled"); 
 		$('#cPlinth8').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan9()
 {
  var select=document.getElementById("planaprvl8").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType9').removeAttr("disabled");
 		$('#cPlinth9').removeAttr("disabled");
 		}
 	else{
 		
 		$('#usageType9').prop("disabled","disabled"); 
 		$('#cPlinth9').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan10()
 {
  var select=document.getElementById("planaprvl9").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType10').removeAttr("disabled");
 		$('#cPlinth10').removeAttr("disabled");
 		}
 	else{
 		$('#usageType10').prop("disabled","disabled"); 
 		$('#cPlinth10').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan11()
 {
  var select=document.getElementById("planaprvl10").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType11').removeAttr("disabled");
 		$('#cPlinth11').removeAttr("disabled");
 		}
 	else{
 		$('#usageType11').prop("disabled","disabled"); 
 		$('#cPlinth11').prop("disabled","disabled");
 	} 
 }
 function checkBuildingPlan12()
 {
  var select=document.getElementById("planaprvl11").value;
 	alert(select);
 	if(select=='Y')
 		{
 		$('#usageType12').removeAttr("disabled");
 		$('#cPlinth12').removeAttr("disabled");
 		}
 	else{
 		$('#usageType12').prop("disabled","disabled"); 
 		$('#cPlinth12').prop("disabled","disabled");
 	} 
 }
</script>

<body>
	<div class="container">
		<form name="newAppForm" method="POST" action="saveAddMeasurementAsmtDetail.do" enctype='multipart/form-data' id="newAppForm"
			onsubmit="return checkValidation();">
			
			<fieldset>
		<c:if test="${not empty list}">
				<div class="panel panel-primary">
					<div class="panel-heading">Existing Applicant Detail</div>

					<div class="panel-body ">
						<div class="panel-group">
							<div class="form-group">
								<label class="col-md-2 control-label">Gender:</label>
								<div class="col-md-3 inputGroupContainer">
									<div class="input-group">
										<input type="text" id="gender" name="gender"
											value="${list[0]}" class="form-control" readonly>
									</div>
								</div>
							</div>
							
						</div>
					</div>

					<div class="panel-body">
						<div class="panel-group">
							<div class="form-group">
								<label class="col-md-2 control-label">SurName<font color="red"><b> *</b></font></label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerSurName" id="ownerSurName"
										value="${list[1]}"	class="form-control" type="text" readonly>

									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Name</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerName" id="ownerName"
											value="${list[2]}" class="form-control" type="text" readonly>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Relation Surname</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerFatherHusbandSurName"
											id="ownerFatherHusbandSurName"
											value="${list[3]}" class="form-control"
											type="text" readonly>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label">Relation Name</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerFatherHusbandName"
											id="ownerFatherHusbandName" value="${list[4]}"
											class="form-control" type="text" readonly>
									</div>
								</div>
							</div>
						</div>
					</div></div>
					<!-- End panel-body -->

<!-- Address Start panel-body -->
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Existing Applicant
							Address</div>
						<div class=" panel-body ">
							<div class="panel-group">
								<div>
									<input name="ownerDoorNo" id="ownerDoorNo"
										placeholder="OwnerDoor No" class="form-control" type="hidden">
									<!-- class="panel panel-primary panel-body" -->
									<div class="form-group" style="display: none;">
										<label class="col-md-2 control-label">Owner Door No</label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">
												<!-- <input name="ownerDoorNo" id="ownerDoorNo"
													placeholder="OwnerDoor No" class="form-control" type="hidden"> -->
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">Owner City</label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">

												<input name="ownerCity" id="ownerCity"
													value="${list[5]}" class="form-control" type="text" readonly>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Occupant Name</label>
										<div class="col-md-4 ">
											<div class="input-group">

												<input name="occupantname" id="occupantname"
													value="${list[7]}" class="form-control"
													type="text" readonly>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Occupant SurName</label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="occupantsurname" id="occupantsurname"
													value="${list[8]}" class="form-control"
													type="text" readonly>
											</div>
										</div>
									</div>
									
									
									<div class="form-group">

										<!-- <label class="col-md-2 control-label">PIN</label> -->

										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <input name="ownerPinCode" id="ownerPinCode"
													placeholder="PIN Code" class="form-control" type="text"> -->
											</div>
										</div>
									</div>

									
								</div>


							</div>
							<div class="col-md-12 control-label" align="center"
								id="errorBoxAddress"
								style="color: red; font-weight: bold; font-size: 15px;"></div>

						</div>
					</div>
<!--End Address Start panel-body -->
								
										<!--Start Location Details -->
										<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">
							Location Details</div>
						<div class="panel-body">
							<div class="panel-group">
								<div class="col-md-12 control-label" align="center"
									id="errorBoxlocation"
									style="color: red; font-weight: bold; font-size: 15px;"></div>
								<div class="col-md-12 control-label" align="left"
									id="errorBoxlocation"
									style="color: red; font-weight: bold; font-size: 16px;">
										
		<p align="center"><a href="${pageContext.request.contextPath}/Gazette/${list[11]}.pdf" target='_blank'  style='text-decoration:none'><b><u>Download </b></u>Municipality Gazzette Copy- for More information</a></p>

								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">District :</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select name="ownerDistrict" id="ownerDistrict"		
												class="form-control " ReadOnly = true>
												<option value="${list[9]}">${list[10]}</option>

											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">ULB Name:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group" id="ulbsTR">
											<!-- name="searchUlbId0" -->
											<select id="ulbid" name="ulbcode" class="form-control ulbs " ReadOnly = true>
												<option value="${list[11]}">${list[12]}</option>
											</select>
										</div>

										
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Locality Name:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="locality" id="locality" ReadOnly = true>
												<option value="${list[13]}">${list[14]}</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Zone Name:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="zone" id="zone"
												ReadOnly = true>
												<option value="${list[15]}">${list[16]}</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Revenue Ward:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="revenueward"
												id="revenueward" ReadOnly = true>
												<option value="${list[17]}">${list[18]}</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Block No:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="block" id="block"
												ReadOnly = true>
												<option value="${list[19]}">${list[20]}</option>

											</select>

										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Election Ward:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="elecward" id="elecward"
												ReadOnly = true>
												<option value="${list[21]}">${list[22]}</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Street Name:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="street" id="street"
												ReadOnly = true>
												<option value="${list[23]}">${list[24]}</option>

											</select>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!--Start Location Details -->
					
					<!-- Start Property Details Type -->
					
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">
							Property Details Type</div>
						<div class="panel-body">
							<div class="panel-group">
								<div>
									<div class="form-group">
										<label class="col-md-2 control-label">Floor Type :</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="floorType" id="floorType"
													ReadOnly = true class="form-control ">
													<option value="${list[25]}">${list[26]}</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Roof Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="roofType" id="roofType"
													ReadOnly = true class="form-control ">
													<option value="${list[27]}">${list[28]}</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Wall Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="waalType" id="waalType"
													ReadOnly = true class="form-control ">
													<option value="${list[29]}">${list[30]}</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Wood Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="woodType" id="woodType"
													ReadOnly = true class="form-control ">
													<option value="${list[31]}">${list[32]}</option>
												</select>
											</div>
										</div>
									</div></div></div></div></div></c:if></div>
									<!--Cotect Detail Start panel-body -->
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Existing Measurement 
							 Details</div>
						<div class="panel-body">
						<div class="form-group">
						<%-- <c:forEach var="getdata" items="${floorlist}"> --%>
<table border=1 align="center" class="table">
<tr ><th colspan=9 style="text-align:center">Floor Wise Measurement</th></tr>
<tr>
<th >Building Age</th><th>Bulding Class</th><th>Building Usage</th><th>Occupant Type</th><th>Floor No.</th>
<th>Length</th><th>Width</th><th>Plinth Area</th><th>Plan Approval</th>
</tr>
<tr>
<td style="text-align:center">
<c:forEach items="${I_BLDGAGE}" var="I_BLDGAGE">
<c:out  value="${I_BLDGAGE}"/><br></c:forEach></td>
 <td style="text-align:center">
<c:forEach items="${ClassificationofBuilding}" var="ClassificationofBuilding">
<c:out  value="${ClassificationofBuilding}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${TypeofBuildingusage}" var="TypeofBuildingusage">
<c:out  value="${TypeofBuildingusage}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${Occupanttype}" var="Occupanttype">
<c:out  value="${Occupanttype}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${I_FLRNO}" var="I_FLRNO">
<c:out  value="${I_FLRNO}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${D_LNTH}" var="D_LNTH">
<c:out  value="${D_LNTH}"/><br></c:forEach></td>


<td style="text-align:center">
<c:forEach items="${D_WDTH}" var="D_WDTH">
<c:out  value="${D_WDTH}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${D_PLNTAREA}" var="D_PLNTAREA">
<c:out  value="${D_PLNTAREA}"/><br></c:forEach></td>

<td style="text-align:center">
<c:forEach items="${IS_PLANAPRVD}" var="IS_PLANAPRVD">
<c:out  value="${IS_PLANAPRVD}"/><br></c:forEach></td>
 
</tr></table>
									</div>

									<div class="col-md-12 control-label" align="center"
								id="errorBoxContact"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
						</div>
					</div>
							<!--End Cotect Detail Start panel-body -->						
									
					<!--  End Property Details Type-->
<font color="blue"><h3 align="center">Enter New Measurement</h3></font>
					  <!--Ownership/Occupant Details -->
	 <div class="panel panel-primary">
	<div class="panel-heading" style="background-color: #782669;"> 
	<!-- <div class="panel panel-default">
	<div class="panel-heading" style="background-color: #cff2f9;"> -->
						<!-- <div class="panel-heading" style="background-color: #782669;"> -->Ownership/Occupant
							Details</div>
						<div class="panel-body">
							<div class="panel-group">
								<div>
									<div class="form-group">
										<label class="col-md-2 control-label">OwnerShip Type<font color="red"><b> *</b></font></label>
										<div class="col-md-4 inputGroupContainer ">
											<div class="input-group">
												<!-- <input name="ownershiptype" id="ownershiptype"
													placeholder="OwnerShip Type" class="form-control" type="text"> -->

												<select class="form-control" name="ownershiptype"
													id="ownershiptype">
													<option value="0">-Select-</option>
													<option value="1">Central Govt. Building</option>
													<option value="2">House Owned Under EWSHS</option>
													<option value="3">State Govt. Building</option>
													<option value="4">Private Buildings</option>
													<option value="5">Private Buildings-Companies</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Mobile<font color="red"><b>*</b></font></label>
										<div class="col-md-4  ">
											<div class="input-group">
											 <input name="ownerMobile" id="ownerMobile"
													value="" class="form-control" type="text" >

												<%-- <input name="ownerMobile" id="ownerMobile"
													value="${list[6]}" class="form-control" type="text" readonly> --%>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label"></label>
										<div class="col-md-4  ">
											<div class="input-group"></div>
										</div>
									</div>



								</div>
								<div class="col-md-12 control-label" align="center"
									id="errorBoxOccupant"
									style="color: red; font-weight: bold; font-size: 15px;"></div>
							</div>
						</div>
					</div>


					<!--End Ownership/Occupant Details -->
					  
					 
					<!--Start Assessment Details  -->
					<div class="panel panel-default">
						<!-- <div class="panel-heading" style="background-color: #782669;"> -->
							<div class="panel-heading" style="background-color: #cff2f9;">Assessment
								Details</div>
							<div class="panel-body">
								<div class="panel-group">

								
									<div class="form-group">
										<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="radio" id="planaprvl" name="planaprvl"
													value="Y" checked="checked"> Yes <input
													type="radio" id="planaprvl" name="planaprvl" value="N">
												No
											</div>
										</div>
									</div>
                                    <div class="form-group">
										<label class="col-md-3 control-label">Registered Document No:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="regdocno" id="regdocno" placeholder="Registered Document No"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									
                                                                        <div class="form-group">
										<label class="col-md-3 control-label">Building Permission Date:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="B_PermisionDate" id="B_PermisionDate" placeholder="Building Permission Date"
													class="form-control" type="text" readonly="readonly">
											</div>  
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Registered Document Date:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer ">
											<div class="input-group">
												<input name="regDate" id="regDate" placeholder="Registered Document Date"
													class="form-control" type="text" readonly="readonly">
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Building Permission No:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">

												<input name="B_PermisionNo" id="B_PermisionNo" placeholder="Building Permission No"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									
                                   
										
									
									
									
								</div>
								<div class="col-md-12 control-label" align="center"
									id="errorBoxassessmentdetails"
									style="color: red; font-weight: bold; font-size: 15px;"></div>
							</div>
						</div>
					
					<!--End Assessment Details  -->
												
					<!-- start Measurement Details -->
					<div class="panel panel-default">
							<div class="panel-heading" style="background-color: #cff2f9;">
								Measurement Details</div>
								
							<div class="panel-body" id="DivBoxesGroup">
<p><b><font color="red">Note: New enhanced penalty(25 times) will be imposed for any deviation in measurements. Ensure that measurements are correct, before submit the self assessment application, CHECK carefully.</font></b></p>
								<!-- Measurement 1 -->
								<div class="panel-group" id="DivBoxDiv1">
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls"
													"> -->
													<select class="form-control" name="bcls" id="bcls"
													>
													<!--  ('bcls','busage','octy') -->
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage" id="cmbbusage"
													onchange="javascript:getocpnt('')"> -->
													<select class="form-control" name="busage" id="busage"
													onchange="javascript:getocpnt('')">
													
													<!--  ('octy','busage') -->
													<option value="0">-Select-</option>

												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty"
													onchange="javascript:setocpnt('')"> -->
													<select class="form-control" name="octy" id="octy"
													onchange="javascript:setocpnt('')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth1" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType1">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len" id="len"
													Placeholder="Enter Length" onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid"
													placeholder="Enter Width" onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
								
																	
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo1"
													placeholder="Enter Floor Number">
											</div>
											<font color="red">Floor No. Accept Only Number <b>Eg: 01</b></font>
										</div>
									</div>
									<!-- Measurement 2 -->
								<div class="panel-group" id="DivBoxDiv2" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl1" onchange="javascript:checkBuildingPlan2()">
													<option value="_"> Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls2"
													> --><select class="form-control" name="bcls" id="bcls2"
													>
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage2" onchange="javascript:getocpnt('2')"> -->
													<select class="form-control" name="busage"
													id="busage2" onchange="javascript:getocpnt('2')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty2"
													onchange="javascript:setocpnt('2')"> -->
													<select class="form-control" name="octy" id="octy2"
													onchange="javascript:setocpnt('2')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage2" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth2" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType2">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label"> Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len2" Placeholder="Enter Length" onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid2"
													placeholder="Enter Width" onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
								
									
									<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo2"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
								</div>
<!-- Measurement 3 -->
								<div class="panel-group" id="DivBoxDiv3" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
										<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl2" onchange="javascript:checkBuildingPlan3()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls3"
													onchange="javascript:getusage('3')"> -->
													<select class="form-control" name="bcls" id="bcls3"
													onchange="javascript:getusage('3')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage3" onchange="javascript:getocpnt('3')"> -->
													<select class="form-control" name="busage"
													id="busage3" onchange="javascript:getocpnt('3')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty3"
													onchange="javascript:setocpnt('3')"> -->
													<select class="form-control" name="octy" id="octy3"
													onchange="javascript:setocpnt('3')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage3" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth3" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType3">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len3" Placeholder="Enter Length" onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid3"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo3"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
								</div>

								<!-- Measurement 4 -->
								<div class="panel-group" id="DivBoxDiv4" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl3" onchange="javascript:checkBuildingPlan4()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls4"
													onchange="javascript:getusage('4')"> -->
													<select class="form-control" name="bcls" id="bcls4"
													onchange="javascript:getusage('4')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage4" onchange="javascript:getocpnt('4')"> -->
													<select class="form-control" name="busage"
													id="busage4" onchange="javascript:getocpnt('4')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty4"
													onchange="javascript:setocpnt('4')"> -->
													<select class="form-control" name="octy" id="octy4"
													onchange="javascript:setocpnt('4')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage4" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth4" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType4">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									   <div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len4" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid4"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo4"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
										
								</div>

<!-- Measurement 5 -->
								<div class="panel-group" id="DivBoxDiv5" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl4" onchange="javascript:checkBuildingPlan5()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls5"
													onchange="javascript:getusage('5')"> -->
													<select class="form-control" name="bcls" id="bcls5"
													onchange="javascript:getusage('5')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage5" onchange="javascript:getocpnt('5')"> -->
													<select class="form-control" name="busage"
													id="busage5" onchange="javascript:getocpnt('5')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty5"
													onchange="javascript:setocpnt('5')"> -->
													<select class="form-control" name="octy" id="octy5"
													onchange="javascript:setocpnt('5')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage5" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth5" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType5">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len5" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid5"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo5"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
								</div>
<!-- Measurement 6 -->
								<div class="panel-group" id="DivBoxDiv6" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl5" onchange="javascript:checkBuildingPlan6()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
										
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<!-- 	<select class="form-control" name="cmbbcls" id="cmbbcls6"
													onchange="javascript:getusage('6')"> -->
														<select class="form-control" name="bcls" id="bcls6"
													onchange="javascript:getusage('6')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage6" onchange="javascript:getocpnt('6')"> -->
													<select class="form-control" name="busage"
													id="busage6" onchange="javascript:getocpnt('6')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty6"
													onchange="javascript:setocpnt('6')"> -->
													<select class="form-control" name="octy" id="octy6"
													onchange="javascript:setocpnt('6')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage6" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth6" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType6">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len6" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid6"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									
									
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo6"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
										
								</div>
								<!-- Measurement 7 -->
								<div class="panel-group" id="DivBoxDiv7" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl6" onchange="javascript:checkBuildingPlan7()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls7"
													onchange="javascript:getusage('7')"> -->
													<select class="form-control" name="bcls" id="bcls7"
													onchange="javascript:getusage('7')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage7" onchange="javascript:getocpnt('7')"> -->
													<select class="form-control" name="busage"
													id="busage7" onchange="javascript:getocpnt('7')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty7"
													onchange="javascript:setocpnt('7')"> -->
													<select class="form-control" name="octy" id="octy7"
													onchange="javascript:setocpnt('7')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage7" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth7" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType7">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len7" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid7"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									
									
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo7"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
									
								</div>
								<!-- Measurement 8 -->
								<div class="panel-group" id="DivBoxDiv8" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl7" onchange="javascript:checkBuildingPlan8()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls8"
													onchange="javascript:getusage('8')"> -->
													<select class="form-control" name="bcls" id="bcls8"
													onchange="javascript:getusage('8')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage8" onchange="javascript:getocpnt('8')"> -->
													<select class="form-control" name="busage"
													id="busage8" onchange="javascript:getocpnt('8')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty8"
													onchange="javascript:setocpnt('8')"> -->
													<select class="form-control" name="octy" id="octy8"
													onchange="javascript:setocpnt('8')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage8" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth8" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType8">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len8" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid8"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									
									
									
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo8"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
																		
								</div>
								<!-- Measurement 9 -->
								<div class="panel-group" id="DivBoxDiv9" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl8" onchange="javascript:checkBuildingPlan9()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls9">
													<option value="0">-Select-</option> -->
														<select class="form-control" name="bcls" id="bcls9">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage9" onchange="javascript:getocpnt('9')"> -->
													<select class="form-control" name="busage"
													id="busage9" onchange="javascript:getocpnt('9')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty9"
													onchange="javascript:setocpnt('9')"> -->
													<select class="form-control" name="octy" id="octy9"
													onchange="javascript:setocpnt('9')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage9" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth9" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType9">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len9" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid9"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo9"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
										
								</div>
								<!-- Measurement 10 -->
								<div class="panel-group" id="DivBoxDiv10" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl9" onchange="javascript:checkBuildingPlan10()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls10"> -->
													<select class="form-control" name="bcls" id="bcls10">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage10" onchange="javascript:getocpnt('10')"> -->
													<select class="form-control" name="busage"
													id="busage10" onchange="javascript:getocpnt('10')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty10"
													onchange="javascript:setocpnt('10')"> -->
													<select class="form-control" name="octy" id="octy10"
													onchange="javascript:setocpnt('10')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage10" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth10" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType10">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len10" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid10"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div> 
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo10"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
									
								</div>
								<!-- Measurement 11 -->
								<div class="panel-group" id="DivBoxDiv11" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl10" onchange="javascript:checkBuildingPlan11()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls11"> -->
												<select class="form-control" name="bcls" id="bcls11">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage11" onchange="javascript:getocpnt('11')"> -->
													<select class="form-control" name="busage"
													id="busage11" onchange="javascript:getocpnt('11')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty11"
													onchange="javascript:setocpnt('11')"> -->
													<select class="form-control" name="octy" id="octy11"
													onchange="javascript:setocpnt('11')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage11" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth11" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType11">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len11" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid11"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo11"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
										
								</div>
								<!-- Measurement 12 -->
								<div class="panel-group" id="DivBoxDiv12" style="display: none;">
									<div class="col-md-12">
										<hr>
									</div>
									<label class="col-md-3 control-label">Bulding Plan
											Apporval:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="planaprvl" id="planaprvl11" onchange="javascript:checkBuildingPlan12()">
													<option value="_">Select</option>
													<option value="Y">Yes</option>
													<option value="N">NO</option>
												</select>
												
											</div>
										</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbcls" id="cmbbcls12"> -->
												<select class="form-control" name="bcls" id="bcls12">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Building
											usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbbusage"
													id="cmbbusage12" onchange="javascript:getocpnt('12')"> -->
													<select class="form-control" name="busage"
													id="busage12" onchange="javascript:getocpnt('12')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<!-- <select class="form-control" name="cmbocty" id="cmbocty12"
													onchange="javascript:setocpnt('12')"> -->
													<select class="form-control" name="octy" id="octy12"
													onchange="javascript:setocpnt('12')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Building
											Age:(No.of Years)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input class="form-control" type="text" name="bage"
													id="bage12" Placeholder="enter Building Age">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth12" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>	
									
									<label class="col-md-3 control-label">Sanctioned Building Usage:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
											<select class="form-control" name="usageType" id="usageType12">
													<option value="_"> Select</option>
													<option value="Residence">Residence</option>
													<option value="Commercial">Commercial</option>
												</select>
												</div>
										</div>
										
										
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Length:(in
											Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control " name="len"
													id="len12" Placeholder="Enter Length"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Constructed Width:(in Mtrs.)<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="wid" id="wid12"
													placeholder="Enter Width"  onchange="javascript:checkmeasurement()">
											</div>
										</div>
									</div>
									
										<div class="form-group">
										<label class="col-md-3 control-label">Floor Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="floorNo" id="floorNo12"
													placeholder="Enter Floor Number">
											</div>
										</div>
									</div>
									
										
								</div>
								<div class="panel-group">
									<div align="right" class="col-md-12 ">
										<input type="button" class="btn btn-primary"
											value="Add Another Measurement" id='addButton' /> <input
											type='button' class="btn btn-primary"
											value='Remove Measurement' id='removeButton'>
									</div>
								</div>

								<div class="col-md-12 control-label" align="center"
									id="errorBoxmeasurments"
									style="color: red; font-weight: bold; font-size: 15px;"></div>
							</div>
                        
						</div>
					</div>
					
					<!-- vbkdfo -->
					<div class="panel panel-primary">
						<div class="panel-heading" style="background-color: #782669;">Enclosed
							Documents</div>
						<div class="panel-body">
							<div class="list-group">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color: #cff2f9;">
										Documents to be uploaded:
										<!-- :<a data-toggle="modal"
											data-target="#myModel1">List of Documents for Trades </a> -->
									</div>
									<div class="panel-body">
										<div class="form-group">
											<!-- <label class="col-md-2 control-label">Document's</label> -->

											<div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7" style="font-weight: bold;">1.Registered
													Document/Court Decree/Affidavit(PDF.) <font color="red"><b> Upload .pdf File</b></font><font color="red"><b> *</b></font></div>
												<div class="col-md-5">
													<input name="file1" id="fileToUpload1" type="file">
												</div>
											</div>
											
											<div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7"  style="font-weight: bold;"><label id="f2">2.Upload Building Permission Copy(.pdf) <font color="red"><b> Upload .pdf File</b></font></label><font color="red"><b> *</b></font></div>
												<div class="col-md-5">
													<input name="file2" id="fileToUpload2" type="file" >
												    
												</div>
											</div>
											
											<div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7" style="font-weight: bold;">3.Photo
													of Building(JPG/PNG) <font color="red"><b> Upload Only JPG/PNG File</b></font><font color="red"><b> *</b></font></div>
												<div class="col-md-5">
													<input name="file3" id="fileToUpload3" type="file">
												</div>
											</div>



										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12 control-label" align="center"
								id="errorBoxBasicPT"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
						</div>

						<div align="center" id="errorBox"
							style="color: red; font-size: 14px; margin-bottom: 15px;"></div>
						<div align="center" class="submit" id="submitButton">
							<input type="submit" class="btn btn-primary" value="Submit" onclick="submitForm()" />
						</div>

						<br>
					</div>
					<input type="hidden" name="pDoorNo" id="pDoorNo" value="${doorno}">	
					<input type="hidden" name="AssessmentNo" id="AssessmentNo" value="${assessment}">	
					<!-- <input type="hidden" name="busage" id="cmbbusage" value="">  -->
				    <!-- <input	type="hidden" name="octy" id="cmbocty" value=""> -->
					<!-- <input type="hidden" name="bcls" id="cmbbcls" value="">  -->				
				<input type="hidden" name="dtcpappno" id="dtcpappno" value="0">
				 <input
					type="hidden" name="localityid" id="localityid" value=""> 
					<!--<input type="hidden" name="ownerDistrict" id="ownerDistrict" value=""> -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
       </script>
       <!-- <script type="text/javascript">
       function bclass()
       {
    	   alert("hhhh");
    	   var ulbid = document.getElementById("searchUlbId0").value; 
    	   var url = "${pageContext.request.contextPath}/getbclsMeasurement.do";
    		$.ajax({
				type : "POST",
				url : url,
				data : {
					ulbcode : ulbid
				},
				dataType : 'json',
				success : function(data) {
				//	alert("enter");
					var array = data;
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#cmbbcls").append(option);
					});
				},
				error : function() {
					alert("error");
					var soption = $('<option />').val("0").text("select");
					$("#cmbbcls").append(soption);
				}
			});
       }
      
       </script> -->
	<script type="text/javascript">
	window.onload = function() {
			var ulbid = document.getElementById("ulbid").value;
			var url = "${pageContext.request.contextPath}/getbclsMeasurement.do";
			$.ajax({
				type : "POST",
				url : url,
				data : {
					ulbcode : ulbid
				},
				dataType : 'json',
				success : function(data) {
					var array = data;
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls").append(option);
					});
					  $.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls2").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls3").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls4").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls5").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls6").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls7").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls8").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls9").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls10").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls11").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#bcls12").append(option);
					}); 
				},
				error : function() {
					alert("error");
					var soption = $('<option />').val("0").text("select");
					$("#bcls").append(soption);
				}
			});
			
		}
	
	 /* Adding building uses  */
	 var ulbid = document.getElementById("ulbid").value;
	var url = "${pageContext.request.contextPath}/getusageMeasurement.do";
		//	$("#cmbbusage").append(option);
			$.ajax({

				type : "POST",
				url : url,
				data : {
					ulbcode : ulbid
				},
				dataType : 'json',
				success : function(data) {
					var array = data;
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage").append(option);
					});
					 $.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage2").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage3").append(option);
					});					
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage4").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage5").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage6").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage7").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage8").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage9").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage10").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage11").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#busage12").append(option);
					});  
				},
				error : function() {
					alert("buse error")
					var soption = $('<option />').val("0").text("select");
					
				}
			});
			
	/* Adding occupent type */
	 var ulbid = document.getElementById("ulbid").value;
	 var url = "${pageContext.request.contextPath}/getocpntMeasurement.do";
			$.ajax({

				type : "POST",
				url : url,
				data : {
					ulbcode : ulbid
				},
				dataType : 'json',
				success : function(data) {
					var array = data;
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy").append(option);
					});
				$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy2").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy3").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy4").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy5").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy6").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy7").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy8").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy9").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy10").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy11").append(option);
					});
					$.each(array, function(i, elem) {
						option = $('<option />').val(i).text(elem);
						$("#octy12").append(option);
					}); 
 					
				},
				error : function() {
					alert("buse error")
					var soption = $('<option />').val("0").text("select");
					$("#octy").append(soption);
				}
			});

	</script>
 	<script type="text/javascript">
/* 	function checkValidation() {
		var ownershiptype = document.newAppForm.ownershiptype.value;
		var regdocno = document.getElementById("regdocno").value;
		var regDate = document.getElementById("regDate").value;
		document.getElementById("errorBoxOccupant").innerHTML ="";

	/* 	if (ownershiptype == '0' || ownershiptype == null
				|| ownerDistrict == '') {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Select Ownership Type ";
			document.getElementById("ownershiptype").focus();
			return false;
		} 
		document.getElementById("errorBoxassessmentdetails").innerHTML="";
		if (regdocno == ''|| regdocno == null) {	
			document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter  Reg. Document Number";
			document.getElementById("regdocno").focus();
			return false;
		}
		if (regDate == '') {	
			document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter  Reg. Date";
			document.getElementById("regDate").focus();
			return false;
		}
		if (plancheckedval == 'Y') {
			if (regdocno == '' || regdocno.length == 0) {
				document.getElementById("regdocno").focus();
				document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter Doc No";
				return false;
			}
			if (regDate == null || regDate == '') {
				document.getElementById("regDate").focus();
				document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter Date";
				return false;
			}
			if(B_PermisionNo==''|| B_PermisionNo.length==0){
				document.getElementById("B_PermisionNo").focus();
				document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter Building Permision No";
				return false;
			}
			if (B_PermisionDate == null || B_PermisionDate == '') {
				document.getElementById("B_PermisionDate").focus();
				document.getElementById("errorBoxassessmentdetails").innerHTML = "Please Enter Building Permision Date";
				return false;
			}
		}

		if (ownershiptype == '0' || ownershiptype == null
				|| ownerDistrict == '') {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Select Ownership Type ";
			document.getElementById("ownershiptype").focus();
			return false;
		} */
	
	</script>
</html>
