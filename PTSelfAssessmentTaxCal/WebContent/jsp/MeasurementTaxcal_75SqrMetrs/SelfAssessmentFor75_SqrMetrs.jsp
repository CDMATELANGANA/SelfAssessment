
<%-- <%@	include file="/pages/common/include.jsp"%> --%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Self Assessment Application</title>
<%
	String path = request.getContextPath();
%>
</head>

<%-- <script src="<%=path %>/web/etrade/jqurey.1.6.2.js"></script> --%>
<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>

<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>

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
<!-- <script type="text/javascript">
onPageSubmit('<c:out value="${contextRoot}"/>/cdma/etradeApplication.do');

</script> -->
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var counter = 2;

						$("#addButton")
								.click(
										function() {

											var ulbId = document
													.getElementById("taxUlb").value;
											var zone = document
													.getElementById("zone").value;
											var elecward = document
													.getElementById("elecward").value;

											if (counter > 3 || ulbId == 0
													|| ulbId == '' || zone == 0
													|| zone == ''
													|| elecward == ''
													|| elecward == 0) {
												//alert("Only 10 textboxes allow");
												return false;
											}
											getbcls(counter);
											$("#DivBoxDiv" + counter).show();
											counter++;
										});

						$("#removeButton").click(function() {
							//alert("counter"+counter);
							if (counter == 2) {
								//alert("No more textbox to remove");
								return false;
							}

							counter--;
							setDefaultMeasurment(counter);
							$("#DivBoxDiv" + counter).hide();

						});

					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[name="deedsAvailable"]:radio').on('change', function(e) {
			var palnVal = this.value;
			//console.log(palnVal);
			if (palnVal == 'Y') {

				$('#fileToUpload1').removeAttr("disabled");
				$('#fileToUpload2').removeAttr("disabled");
				$('#fileToUpload3').removeAttr("disabled");
				//$('#regdocno').removeAttr("disabled");
				//$('#regDate').removeAttr("disabled");

				$('#ownerSurName').attr('readonly', false);
				$('#ownerSurName').val("");

				$('#ownerName').attr('readonly', false);
				$('#ownerName').val("");

				$('#ownerFatherHusbandSurName').attr('readonly', false);
				$('#ownerFatherHusbandSurName').val("");

				$('#ownerFatherHusbandName').attr('readonly', false);
				$('#ownerFatherHusbandName').val("");
			} else {

				//alert(palnVal)
				$('#fileToUpload1').prop("disabled", "disabled");
				$('#fileToUpload2').prop("disabled", "disabled");
				//$('#fileToUpload3').prop("disabled", "disabled");
				//$('#regdocno').prop("disabled", "disabled");
				//$('#regDate').prop("disabled", "disabled");

				$('#ownerSurName').attr('readonly', true);
				$('#ownerSurName').val("Holder of Premises");

				$('#ownerName').attr('readonly', true);
				$('#ownerName').val("The Holder of the Premises");

				$('#ownerFatherHusbandSurName').attr('readonly', true);
				$('#ownerFatherHusbandSurName').val("Holder of Premises");

				$('#ownerFatherHusbandName').attr('readonly', true);
				$('#ownerFatherHusbandName').val("Holder of Premises");
			}
			return false;
		});
	});

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
				$('#usageType1').prop("disabled","disabled"); 
				$('#cPlinth1').prop("disabled","disabled"); 
				$('#B_PermisionNo').val(""); 
				$('#B_PermisionDate').val("");
				$('#usageType1').val(""); 
				$('#cPlinth1').val("");
				$('#fileToUpload2').prop("disabled", "disabled");
							}
			return false;
		});
	});
	
</script>
<script type="text/javascript">
 function checkBuildingPlan2()
{
 var select=document.getElementById("planaprvl1").value;
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
 
</script>
<body>

	<div class="container">

		<form name="newAppForm" method="POST" action="saveAsmntFor_75SqrYards.do"
			enctype='multipart/form-data' id="newAppForm"
			onsubmit="return checkValidation();" commandName="list">
			<fieldset>
				<div class="panel panel-primary">
					<div class="panel-heading">Applicant Information</div>
					<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font color="red"><b>Please Do Not Refresh/Do not Press Back Button on Page, Untill Application Successfully Completed With Payment!</b></font><p></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue"><b> All <font color="red"><b>*</b></font> Text Box Are  Mandatory</b></font></p> 

					<div class="panel-body ">
						<div class="panel-group">
							<div class="form-group">
								<label class="col-md-3 control-label">Registered Title
									Deed Available :</label>
								<div class="col-md-3 inputGroupContainer">
									<div class="input-group">
										<input type="radio" id="deedsAvailable" name="deedsAvailable"
											value="Y" checked="checked"> Yes <input type="radio"
											id="deedsAvailable" name="deedsAvailable" value="N">
										No
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Gender:</label>
								<div class="col-md-3 inputGroupContainer">
									<div class="input-group">
										<input type="radio" id="gender" name="gender"
											value="M" checked="checked"> Male <input type="radio"
											id="gender" name="gender" value="F">
										Female
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
											placeholder="Owner SurName" class="form-control" type="text">

									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Name<font color="red"><b> *</b></font></label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerName" id="ownerName"
											placeholder="Owner Name" class="form-control" type="text">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Relation Surname<font color="red"><b> *</b></font></label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerFatherHusbandSurName"
											id="ownerFatherHusbandSurName"
											placeholder="S/O, W/O, D/O, H/O" class="form-control"
											type="text">
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label">Relation Name<font color="red"><b> *</b></font></label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerFatherHusbandName"
											id="ownerFatherHusbandName" placeholder="First Name"
											class="form-control" type="text">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Pancard No</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerPan" id="ownerPan" placeholder="Pancard No"
											class="form-control" type="text">
									</div>
									<label style="font-size: 12px; color: red">Left Blank
										or Enter Valid PAN Number</label>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label">Aadhaar<font color="red"><b> *</b></font></label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">

										<input name="ownerAadhar" id="ownerAadhar"
											placeholder="Owner Aadhar" class="form-control" type="text">
									</div>
									<!-- <label style="font-size: 12px;color: red" >Left Blank or Enter Valid Aadhar Number</label> -->
								</div>
							</div>

							<div class="form-group">
								<!-- 	<label class="col-md-2 control-label">Pancard No</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">

										<input name="ownerPan" id="ownerPan" placeholder="Pancard No"
											class="form-control" type="text">
									</div>
								</div> -->
							</div>
						</div>


						<div class="col-md-12 control-label" align="center"
							id="errorBoxBasic"
							style="color: red; font-weight: bold; font-size: 15px;"></div>


					</div>
					<!-- End panel-body -->


					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Applicant
							Address & Contact Details </div>
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
												
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">Owner City<font color="red"><b> *</b></font></label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">

												<input name="ownerCity" id="ownerCity"
													placeholder="Owner City" class="form-control" type="text">
											</div>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-2 control-label">PIN<font color="red"><b> *</b></font></label>

										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="ownerPinCode" id="ownerPinCode"
													placeholder="PIN Code" class="form-control" type="text">
											</div>
										</div>
									</div>
                                    <div class="form-group">
										<label class="col-md-2 control-label">Email</label>
										<div class="col-md-4 ">
											<div class="input-group">

												<input name="ownerEmail" id="ownerEmail" placeholder="Email"
													class="form-control" type="text">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Mobile<font color="red"><b> *</b></font></label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="ownerMobile" id="ownerMobile"
													placeholder="Mobile" class="form-control" type="text">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">House Number :<font color="red"><b> *</b></font></label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="houseNumber" id="houseNumber"
													placeholder="House Number" class="form-control" type="text">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Door Number :<font color="red"><b> *</b></font></label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="doorNumber" id="doorNumber"
													placeholder="Door Number" class="form-control" type="text">
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

					

					<!-- New Field -->
					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Enter
							Nearest House Door No Details:</div>
						<div class="panel-body">
							<div class="panel-group">
							<div class="col-md-12 control-label" align="center"
							id="errorBoxBasic1"
							style="color: red; font-weight: bold; font-size: 15px;"></div>
							
								<div>
								<div  class="col-md-12>
									<div class="form-group">
										<label class="col-md-4 control-label">Left Hand Side Structure Door No :<font color="red"><b> *</b></font></label>
										<div class="col-md-6">
											<div class="input-group">

												<input name="L_DoorNo" id="L_DoorNo"
													placeholder="Left Side Nearest Door No"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">Right Hand Side Structure Door No  :<font color="red"><b> *</b></font></label>
										<div class="col-md-6 ">
											<div class="input-group">

												<input name="R_DoorNo" id="R_DoorNo"
													placeholder="Right Side Nearest Door No"
													class="form-control" type="text">
											</div>
										</div>
									</div></div>
									<div class="form-group">
										<label class="col-md-4 control-label">Nearest LandMark :<font color="red"><b> *</b></font></label>
										<div class="col-md-4 ">
											<div class="input-group">

												<input name="N_landMark" id="N_landMark"
													placeholder="Enter Nearest LandMark" class="form-control"
													type="text">
											</div>
										</div>
									</div>
							<!-- <div class="col-md-12 control-label" align="center"
								id="errorBoxContact"
								style="color: red; font-weight: bold; font-size: 15px;"></div> -->
						</div>
					</div>

					<!-- End New Field -->
					<!--   Prpperty Type -->

					<div class="panel panel-primary">
						<div class="panel-heading" style="background-color: #782669;">Ownership/Occupant
							Details</div>
						<div class="panel-body">
							<div class="panel-group">
								<div>
									<div class="form-group">
										<label class="col-md-2 control-label">Occupant Name<font color="red"><b> *</b></font></label>
										<div class="col-md-4 ">
											<div class="input-group">

												<input name="occupantname" id="occupantname"
													placeholder="Occupant Name" class="form-control"
													type="text">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Occupant SurName<font color="red"><b> *</b></font></label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="occupantsurname" id="occupantsurname"
													placeholder="Occupant SurName" class="form-control"
													type="text">
											</div>
										</div>
									</div>


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
									<p id="knowlocation" align="center">To Know your location
										Please select District and ULB</p>

								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">District :<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select name="District" id="reqsearchDistrictId"
												onchange="javascript:getListDistrictWise()"
												class="form-control ">
												<option value="0">Select District</option>

												<c:forEach items="${requestScope.districtsList}"
													var="districtsList">
													<option
														value="<c:out value="${districtsList.districtId}"/>">
														<c:out value="${districtsList.districtName}" />
													</option>
												</c:forEach>

											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">ULB Name:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group" id="ulbsTR">
											<!-- name="searchUlbId0" -->
											<select id="searchUlbId0" class="form-control ulbs ">
												<option value="0">Select ULB</option>
											</select>
										</div>

										<div class="input-group" id="ulbsTR">
											<c:forEach var="subUlb" items="${requestScope.subUlbsMap}">
												<c:set var="subUlbsList" value="${subUlb.value}" />
												<select onchange="javascript:getlocality(this.value)"
													id="searchUlbId${subUlb.key}" class="form-control ulbs"
													style="display: none;" name="searchUlb">
													<option value="0">Select ULB</option>
													<c:forEach items="${subUlbsList}" var="subUlb">
														<option value="${subUlb.ulbCode}">
															<c:out value="${subUlb.ulbName}" />
														</option>
													</c:forEach>
												</select>
											</c:forEach>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Locality Name:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="locality" id="locality"
												onchange="javascript:getzone(this.value)">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Zone Name:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="zone" id="zone"
												onchange="javascript:getward()">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Revenue Ward:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="revenueward"
												id="revenueward" onchange="javascript:getblock(this.value)">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Block No:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="block" id="block"
												onchange="javascript:getElecward()">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Election Ward:<font color="red"><b> *</b></font></label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="elecward" id="elecward"
												onchange="javascript:getstreet(this.value)">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">Street Name:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="street" id="street"
												onchange="javascript:getbcls('')">
												<option value="0">-Select-</option>

											</select>

										</div>
									</div>
								</div>


							</div>


						</div>
					</div>

					<!-- Property Details Type -->


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
													onchange="javascript:getFloorType(this.value)" class="form-control ">
													<option value="0">Select Floor Type</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Roof Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="roofType" id="roofType"
													onchange="javascript:getRoofType(this.value)" class="form-control ">
													<option value="0">Select Roof Type</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Wall Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="waalType" id="waalType"
													onchange="javascript:getWaalType(this.value)" class="form-control ">
													<option value="0">Select Wall Type</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Wood Type:</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="woodType" id="woodType"
													onchange="javascript:getWoodType(this.value)" class="form-control ">
													<option value="0">Select Wood Type</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color: #cff2f9;">Assessment
								Details</div>
							<div class="panel-body">
								<div class="panel-group">
									<div class="form-group">
										<label class="col-md-3 control-label">Bulding Plan
											Apporval:</label>
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
										<label class="col-md-3 control-label">Plot Number:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="plotNo" id="plotNo" placeholder="Plot Number"
													class="form-control" type="text">
											</div>  
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Plot Area:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="plotArea" id="plotArea" placeholder="Plot Area"
													class="form-control" type="text">
											</div>  
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Construction Area:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="constructionArea" id="constructionArea" placeholder="Construction Area"
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
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color: #cff2f9;">
								Measurement Details</div>
								
							<div class="panel-body" id="DivBoxesGroup">
<p><b><font color="red">Note: New enhanced penalty(25 times) will be imposed for any deviation in measurements. Ensure that mesurements are correct, before submit the self assessment application, CHECK carefully.</font></b></p>
								<!-- Measurement 1 -->
								<div class="panel-group" id="DivBoxDiv1">
									<div class="form-group">
										<label class="col-md-3 control-label">Classification
											of Building:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<select class="form-control" name="cmbbcls" id="cmbbcls"
													onchange="javascript:getusage('')">
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
												<select class="form-control" name="cmbbusage" id="cmbbusage"
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
												<select class="form-control" name="cmbocty" id="cmbocty"
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
										<label class="col-md-3 control-label">Sanctioned Plinth Area in(Mtrs.):</label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input type="text" class="form-control" name="cPlinth" id="cPlinth1" placeholder="Sanctioned Plinth Area">
											</div>
										</div>
									</div>
									<label class="col-md-3 control-label">Sanctioned Building Usage:</label>
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
												<select class="form-control" name="cmbbcls" id="cmbbcls2"
													onchange="javascript:getusage('2')">
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
												<select class="form-control" name="cmbbusage"
													id="cmbbusage2" onchange="javascript:getocpnt('2')">
													<option value="0">-Select-</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label"> Occupant type:<font color="red"><b> *</b></font></label>
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<select class="form-control" name="cmbocty" id="cmbocty2"
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
												<div class="col-md-7"  style="font-weight: bold;"><label id="f2">2.Upload Building Permission Copy(PDF.) <font color="red"><b> Upload  .pdf File</b></font></label><font color="red"><b> *</b></font></div>
												<div class="col-md-5">
													<input name="file2" id="fileToUpload2" type="file" >
												    
												</div>
											</div>
											
											<div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7" style="font-weight: bold;">3.Photo
													of Building(JPG/PNG) <font color="red"><b> Upload .JPG/.PNG File</b></font><font color="red"><b> *</b></font></div>
												<div class="col-md-5">
													<input name="file3" id="fileToUpload3" type="file">
												</div>
											</div>
                                            <div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7" style="font-weight: bold;">4.upload
													Occupancy Certificate <font color="red"><b> Upload .pdf  File</b></font><font color="red"><b> *</b></font></div>
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
</fieldset>
						<div align="center" id="errorBox"
							style="color: red; font-size: 14px; margin-bottom: 15px;"></div>
						<div align="center" class="submit" id="submitButton">
							<input type="submit" class="btn btn-primary" value="Submit" onclick="submitForm()" />
						</div>

						<br>
					</div>
				</div>
				
				<!-- 	<input type="hidden" name="tradeUlb" id="tradeUlb" value=""> -->

				<input type="hidden" name="dtcpappno" id="dtcpappno" value="0">
				<input type="hidden" name="ulbcode" id="taxUlb" value=""> <input
					type="hidden" name="localityid" id="localityid" value=""> <input
					type="hidden" name="ownerDistrict" id="ownerDistrict" value="">

				<input type="hidden" name="bcls" id="taxbcls" value=""> <input
					type="hidden" name="busage" id="taxbusage" value=""> <input
					type="hidden" name="octy" id="taxocty" value=""> <input
					type="hidden" name="bcls" id="taxbcls2" value=""> <input
					type="hidden" name="busage" id="taxbusage2" value=""> <input
					type="hidden" name="octy" id="taxocty2" value=""> <input
					type="hidden" name="bcls" id="taxbcls3" value=""> <input
					type="hidden" name="busage" id="taxbusage3" value=""> <input
					type="hidden" name="octy" id="taxocty3" value=""> <input
					type="hidden" name="bcls" id="taxbcls4" value=""> <input
					type="hidden" name="busage" id="taxbusage4" value=""> <input
					type="hidden" name="octy" id="taxocty4" value="">
					<input type="hidden" name="bcls" id="taxbcls5" value=""> 
					<input type="hidden" name="busage" id="taxbusage5" value="">
					<input type="hidden" name="octy" id="taxocty5" value="">
					<input type="hidden" name="bcls" id="taxbcls6" value=""> 
					<input type="hidden" name="busage" id="taxbusage6" value="">
					<input type="hidden" name="octy" id="taxocty6" value="">
					<input type="hidden" name="bcls" id="taxbcls7" value=""> 
					<input type="hidden" name="busage" id="taxbusage7" value="">
					<input type="hidden" name="octy" id="taxocty7" value="">
					<input type="hidden" name="bcls" id="taxbcls8" value=""> 
					<input type="hidden" name="busage" id="taxbusage8" value="">
					<input type="hidden" name="octy" id="taxocty8" value="">
					<input type="hidden" name="bcls" id="taxbcls9" value=""> 
					<input type="hidden" name="busage" id="taxbusage9" value="">
					<input type="hidden" name="octy" id="taxocty9" value="">
					<input type="hidden" name="bcls" id="taxbcls10" value=""> 
					<input type="hidden" name="busage" id="taxbusage10" value="">
					<input type="hidden" name="octy" id="taxocty10" value="">
					<input type="hidden" name="bcls" id="taxbcls11" value=""> 
					<input type="hidden" name="busage" id="taxbusage11" value="">
					<input type="hidden" name="octy" id="taxocty11" value="">
					<input type="hidden" name="bcls" id="taxbcls12" value=""> 
					<input type="hidden" name="busage" id="taxbusage12" value="">
					<input type="hidden" name="octy" id="taxocty12" value="">
					<input type="hidden" name="bcls" id="taxbcls13" value=""> 
					<input type="hidden" name="busage" id="taxbusage13" value="">
					<input type="hidden" name="octy" id="taxocty13" value="">

			</fieldset>

		</form>
		</div>
	
</body>

<script type="text/javascript">
	function getListDistrictWise() {

		$('.ulbs').hide();
		$('#ulbsTR').show();
		// $("#tradecategory").empty();
		// $("#subcategory").empty();
		var option = $('<option />').val("0").text("select");
		$("#tradecategory").empty();
		$("#tradecategory").append(option);
		
		
		$("#subcategory").empty();
		var soption = $('<option />').val("0").text("select");
		$("#subcategory").append(soption);
		
		document.getElementById("knowlocation").innerHTML = "To Know your location Please select District and ULB";
		var ulbId = document.getElementById("reqsearchDistrictId").value;
		document.getElementById("ownerDistrict").value = ulbId;
		var dis = '<c:out value="${requestScope.districtId}" />';

		if (ulbId != 0) {
			document.getElementById("searchUlbId" + ulbId).style.display = "block";
		} else if (dis != 0) {
			document.getElementById("searchUlbId" + dis).style.display = "block";
		} else {
			document.getElementById("searchUlbId0").style.display = "block";
		}
		//Adding floor
		var url = "${pageContext.request.contextPath}/getFloorType.do";
		$("#floorType").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid
				//ulbcode : 1038
			},
			dataType : 'json',
			success : function(data) {
				var array = data;

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					$("#floorType").append(option);
				});
			},
			error : function() {
				var soption = $('<floorType />').val("0").text("select");
				$("#floorType").append(soption);
			}
		});
// Adding Roof Type
		var url = "${pageContext.request.contextPath}/getRoofType.do";
		$("#roofType").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid
				//ulbcode : 1038
			},
			dataType : 'json',
			success : function(data) {
				var array = data;

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					$("#roofType").append(option);
				});
			},
			error : function() {
				var soption = $('<roofType />').val("0").text("select");
				$("#roofType").append(soption);
			}
		});
/* Ending Roof Type in District */
 
 
 
/* Adding Waal Type  */
var url = "${pageContext.request.contextPath}/getWaalType.do";
		$("#waalType").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid
				//ulbcode : 1038
			},
			dataType : 'json',
			success : function(data) {
				var array = data;

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					$("#waalType").append(option);
				});
			},
			error : function() {
				var soption = $('<waalType />').val("0").text("select");
				$("#waalType").append(soption);
			}
		});
/* Ending Waal Type In District */

		/* Adding Wood Type  */
var url = "${pageContext.request.contextPath}/getWoodType.do";
		$("#woodType").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid
				//ulbcode : 1038
			},
			dataType : 'json',
			success : function(data) {
				var array = data;

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					$("#woodType").append(option);
				});
			},
			error : function() {
				var soption = $('<woodType />').val("0").text("select");
				$("#woodType").append(soption);
			}
		});
		/* Ending Wood Type In District */

	}
</script>


<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/css/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/css/jquery-ui.js"></script>

<script type="text/javascript">
	$(function() {

		var fromDate = $("#permissionDate").datepicker({
			defaultDate : "+1w",
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			//numberOfMonths: 2,
			minDate : 0,

			onSelect : function(date) {
				var date2 = $('#fromdate').datepicker('getDate');
				date2.setDate(date2.getDate() + 1);
				// $('#todate').datepicker('setDate', date2);
				//sets minDate to dt1 date + 1
				// $('#todate').datepicker('option', 'minDate', date2);
			}

		});
	});
</script>

<script type="text/javascript">
	function checkValidation() {
		/* var surname_Regx = /^[a-z A-Z]{1,50}$/; */
		var surname_Regx =  /^[a-z A-Z]{2,20}$/;
		var name_Regx = /^[a-z A-Z]{2,50}$/;
		//var mobile_reg = /^[6-9]+[0-9]{9}$/;
		var mobile_reg = /^[1-9]\d{9}$|^[1-9]\d{9}$/g;
		var rex_pannumber = /^[A-Z]{3}[CHFATBLJGP]{1}[A-Z]{1}[0-9]{4}[A-Z]$/;
		var email_Regx = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
		var number_Regx = /^[0-9]+$/;
		var ownerName = document.newAppForm.ownerName.value;
		var ownerSurName = document.newAppForm.ownerSurName.value;
		var doublecheck_reg = /^[0-9]+\.?[0-9]*$/;
		var age_Regx = /^[0-9]{1,2}$/;
		var ownerFatherHusbandName = document.newAppForm.ownerFatherHusbandName.value;
		var ownerFatherHusbandSurName = document.newAppForm.ownerFatherHusbandSurName.value;
		var ownerAadhar = document.newAppForm.ownerAadhar.value;
		var ownerPan = document.newAppForm.ownerPan.value;
		var ownerDoorNo = document.newAppForm.ownerDoorNo.value;
		var ownerStreet = document.newAppForm.street.value;
		var ownerCity = document.newAppForm.ownerCity.value;
		var ownerDistrict = document.getElementById('reqsearchDistrictId').value;
		var ownerPinCode = document.newAppForm.ownerPinCode.value;
		var ownerEmail = document.newAppForm.ownerEmail.value;
		var ownerMobile = document.newAppForm.ownerMobile.value;
		var N_landMark=  document.newAppForm.N_landMark.value;
		var L_DoorNo_Regx= /^((1-9a-zA-Z!(0))[0-9a-zA-Z]{9})$/;
		var R_DoorNo_Regx= /^((1-9a-zA-Z!(0))[0-9a-zA-Z]{9})$/;
		 var L_DoorNo= document.newAppForm.L_DoorNo.value.charAt(0); 
		var R_DoorNo= document.newAppForm.R_DoorNo.value.charAt(0); 
		var occupantname = document.newAppForm.occupantname.value;
		var occupantsurname = document.newAppForm.occupantsurname.value;
		var ownershiptype = document.newAppForm.ownershiptype.value;
		var searchUlbId0 = document.getElementById("taxUlb").value;
		var locality = document.getElementById("locality").value;
		var zone = document.getElementById("zone").value;
		var revenueward = document.getElementById("revenueward").value;
		var block = document.getElementById("block").value;
		var elecward = document.getElementById("elecward").value;
		//var planaprvl=document.getElementById("planaprvl").value;
		var regdocno = document.getElementById("regdocno").value;
		var regDate = document.getElementById("regDate").value;
		var B_PermisionNo = document.getElementById("B_PermisionNo").value;
		var B_PermisionDate = document.getElementById("B_PermisionDate").value;
		var bcls = document.getElementsByName("bcls");
		var busage = document.getElementsByName("busage");
		var octy = document.getElementsByName("octy");
		var bage = document.getElementsByName("bage");
		var blen = document.getElementsByName("len");
		var bwid = document.getElementsByName("wid");
		var bfloorNo=document.getElementsByName("floorNo");
		var floorNo_reg= /^[0-9]+$/;
		var L_DoorNo= document.newAppForm.L_DoorNo.value.charAt(0); 
		var R_DoorNo= document.newAppForm.R_DoorNo.value.charAt(0);
		var L_DoorNo1 = document.getElementById("L_DoorNo").value;
		var R_DoorNo1 = document.getElementById("R_DoorNo").value;
		var file1 = document.getElementById("fileToUpload1").value;
		var file2 = document.getElementById("fileToUpload2").value;
		var file3 = document.getElementById("fileToUpload3").value;
		var ownerPinCode_Regx = /^[0-9]{6}$/;
		var planaprvl = document.getElementsByName('planaprvl');
		var deeds = document.newAppForm.deedsAvailable.value;
		var plancheckedval = '';
		for (var i = 0, length = planaprvl.length; i < length; i++) {
			if (planaprvl[i].checked) {
				plancheckedval = planaprvl[i].value;
				break;
			}
		}
		if (!surname_Regx.test(ownerSurName)) {
		
			document.getElementById("errorBoxBasic").innerHTML = "Enter valid Sur-Name/ Only Alphbets are allowed  ";
			document.getElementById("ownerSurName").focus();
			return false;
		}
		if (ownerSurName.length>20) {
			
			document.getElementById("errorBoxBasic").innerHTML = "Sur-Name Should be Less than 20 Character!  ";
			document.getElementById("ownerSurName").focus();
			return false;
		}
		if (!name_Regx.test(ownerName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Enter valid  Name/Only Alphbets  are allowed in Name And Should be Less than 50 Character ";
			//document.getElementById("ownerName").value = "";
			document.getElementById("ownerName").focus();
			return false;
		}
		if (ownerFatherHusbandSurName == '') {	
			document.getElementById("errorBoxBasic").innerHTML = "Please Enter  Father's/Husband SurName";
			//document.getElementById("ownerFatherHusbandSurName").value = "";
			document.getElementById("ownerFatherHusbandSurName").focus();
			return false;
		}
		if (!surname_Regx.test(ownerFatherHusbandSurName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Only Alphbets  are allowed in Father's/Husband SurName And Should be Less than 20 character!";
			//document.getElementById("ownerFatherHusbandSurName").value = "";
			document.getElementById("ownerFatherHusbandSurName").focus();
			return false;
		}

		if (!name_Regx.test(ownerFatherHusbandName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Enter valid  Father's/Husband Name/Only Alphbets  are allowed   ";
			//document.getElementById("ownerFatherHusbandName").value = "";
			document.getElementById("ownerFatherHusbandName").focus();
			return false;
		}
		
		if ((ownerAadhar == '')) {
			document.getElementById("errorBoxBasic").innerHTML = "Please Enter   Aadhaar Number";
			document.getElementById("ownerAadhar").value = "";
			document.getElementById("ownerAadhar").focus();//alert(verhoeffLookup(ownerAadhar, 0));
			return false;
		}

		if ((ownerAadhar != '' && !number_Regx.test(ownerAadhar))) {
			document.getElementById("errorBoxBasic").innerHTML = "Please Enter  Valid Aadhaar Number";
			document.getElementById("ownerAadhar").value = "";
			document.getElementById("ownerAadhar").focus();//alert(verhoeffLookup(ownerAadhar, 0));
			return false;
		}

		if (verhoeffLookup(ownerAadhar, 0) != 0) {
			document.getElementById("errorBoxBasic").innerHTML = "Please Enter  Valid Aadhaar Number";
			document.getElementById("ownerAadhar").value = "";
			document.getElementById("ownerAadhar").focus();
			return false;
		}

		if (!rex_pannumber.test(ownerPan) && ownerPan != '') {
			//alert("invalid Pan");
			document.getElementById("errorBoxBasic").innerHTML = "Please Enter Valid Pan Number";
			document.getElementById("ownerPan").value = "";
			document.getElementById("ownerPan").focus();
			return false;
		}
		
		document.getElementById("errorBoxAddress").innerHTML = "";
		
		if (!name_Regx.test(ownerCity)) {
			document.getElementById("errorBoxAddress").innerHTML = "Please Enter City ";
			//document.getElementById("ownerVilliage").value = "";
			document.getElementById("ownerCity").focus();
			return false;
		}
			if (!ownerPinCode_Regx.test(ownerPinCode)) {
				document.getElementById("errorBoxAddress").innerHTML = "Enter valid Pincode/ Only 6 digits are allowed  ";
				document.getElementById("ownerPinCode").value = "";
				document.getElementById("ownerPinCode").focus();
				return false;
			}
		
		//document.getElementById("errorBoxAddress").innerHTML = "";

		if (ownerEmail != '' && !email_Regx.test(ownerEmail)) {
			document.getElementById("errorBoxContact").innerHTML = "Please Enter Valid Email Id";
			document.getElementById("ownerEmail").value = "";
			document.getElementById("ownerEmail").focus();
			return false;
		}

		/* 	if (ownerMobile == '' || ownerMobile == null) {
				document.getElementById("errorBoxContact").innerHTML = "Please Enter Mobile Number ";
				//document.getElementById("ownerPinCode").value = "";
				document.getElementById("ownerMobile").focus();
				return false;
			} */
		if (!mobile_reg.test(ownerMobile)) {
			document.getElementById("errorBoxContact").innerHTML = "Please Enter Valid Mobile Number Only 10 Digit";
			document.getElementById("ownerMobile").value = "";
			document.getElementById("ownerMobile").focus();
			return false;
		}
			
		document.getElementById("errorBoxContact").innerHTML = "";
		/* if (L_DoorNo == '') {
			document.getElementById("errorBoxBasic1").innerHTML = "Please Enter Left Door Number";
			//document.getElementById("ownerFatherHusbandSurName").value = "";
			document.getElementById("L_DoorNo").focus();
			return false;
		}
		if (R_DoorNo == '') {
			document.getElementById("errorBoxBasic1").innerHTML = "Please Enter Right Door Number";
			//document.getElementById("ownerFatherHusbandSurName").value = "";
			document.getElementById("R_DoorNo").focus();
			return false; */
			
			 if (L_DoorNo==0||L_DoorNo==''||L_DoorNo1.length>20) {
			document.getElementById("errorBoxBasic1").innerHTML = "Zero Not Accept Please Enter Door No. Leassthan 20 Char.!";
			document.getElementById("L_DoorNo").value = "";
			document.getElementById("L_DoorNo").focus();
			return false;
			 
		 }
	    if (R_DoorNo==0||R_DoorNo==''||R_DoorNo.length>20) {
			document.getElementById("errorBoxBasic1").innerHTML = "Zero Not Accept Please Enter Door No. Leassthan 20 Char.!";
			document.getElementById("R_DoorNo").value = "";
			document.getElementById("R_DoorNo").focus();
			return false; 
		} 
	    if (N_landMark==''||N_landMark.length>20) {
			document.getElementById("errorBoxBasic1").innerHTML = "Please Enter Near Land Mark Leassthan 20 Char.!";
			document.getElementById("N_landMark").value = "";
			document.getElementById("N_landMark").focus();
			return false; 
		} 
	    document.getElementById("errorBoxBasic1").innerHTML="";
		
		
		if (N_landMark == '') {
			document.getElementById("errorBoxBasic1").innerHTML = "Please Enter Nearest Land Mark";
			//document.getElementById("ownerFatherHusbandSurName").value = "";
			document.getElementById("N_landMark").focus();
			return false;
		}
		
		if (!name_Regx.test(occupantname)) {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Enter Occupant Name";
			document.getElementById("occupantname").value = "";
			document.getElementById("occupantname").focus();
			return false;
		}
		if (occupantname.length>50) {
			document.getElementById("errorBoxOccupant").innerHTML = "Occupant Name Should be less than 50 Charcter!";
			document.getElementById("occupantname").value = "";
			document.getElementById("occupantname").focus();
			return false;
		}
		if (!name_Regx.test(occupantsurname)) {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Enter Occupant-Sur Name";
			document.getElementById("occupantsurname").value = "";
			document.getElementById("occupantsurname").focus();
			return false;
		}
		if (occupantsurname.length>20) {
			document.getElementById("errorBoxOccupant").innerHTML = "Occupant-Sur Name Should be less than 20 Charcter!";
			document.getElementById("occupantsurname").value = "";
			document.getElementById("occupantsurname").focus();
			return false;
		}
		document.getElementById("errorBoxOccupant").innerHTML ="";

		if (ownershiptype == '0' || ownershiptype == null
				|| ownerDistrict == '') {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Select Ownership Type ";
			document.getElementById("ownershiptype").focus();
			return false;
		}

		document.getElementById("errorBoxOccupant").innerHTML = "";

		if (ownerDistrict == '0' || ownerDistrict == null
				|| ownerDistrict == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your District ";
			document.getElementById("reqsearchDistrictId").focus();
			return false;
		}

		if (searchUlbId0 == '0' || searchUlbId0 == null || searchUlbId0 == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your ULB ";
			return false;
		}

		if (locality == '0' || locality == null || locality == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your Locality ";
			return false;
		}

		if (zone == '0' || zone == null || zone == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your Zone ";
			return false;
		}
		if (revenueward == '0' || revenueward == null || revenueward == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your Revenue Ward ";
			return false;
		}

		if (block == '0' || block == null || block == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your Block ";
			return false;
		}

		if (elecward == '0' || elecward == null || elecward == '') {
			document.getElementById("errorBoxlocation").innerHTML = "Please Select Your Election Ward ";
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
		//plancheckedval
		//regdocno,regDate
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
		document.getElementById("errorBoxassessmentdetails").innerHTML = "";

		  
		if (bcls[0].value == '0' || bcls[0].value == null
				|| bcls[0].value == '') {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Select  Classification of Building  ";
			return false;
		}
		if (busage[0].value == '0' || busage[0].value == null
				| busage[0].value == '') {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Select  Building usage  ";
			return false;
		}
		if (octy[0].value == '0' || octy[0].value == null | octy[0].value == '') {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Occupant type ";
			return false;
		}
		if (bage[0].value == '' || !(age_Regx.test(bage[0].value))) {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Building Age  ";
			document.getElementById("bage").focus();
			return false;
		}

		if (blen[0].value == '' || blen[0].value == '0'
				|| !(doublecheck_reg.test(blen[0].value))) {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Length  ";
			document.getElementById("len").focus();
			return false;
		}
		
		if (bwid[0].value == '' || bwid[0].value == '0'
				|| !(doublecheck_reg.test(bwid[0].value))) {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Width  ";
			document.getElementById("wid").focus();
			return false;
		}
		/* if (bfloorNo[0].value=='') {
			document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Floor No.  ";
			document.getElementById("floorNo").focus();
			return false;
		}
		 */
		
		
		
if (deeds=='Y' && planaprvl=='N'){
			
			if (file1 == "") {
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload1").value = "";
				document.getElementById("fileToUpload1").focus();
				return false;
			}
			
			if (file2 == "") {
				
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload2").value = "";
				document.getElementById("fileToUpload2").focus();
				return false;
			}	
			
			   if (file3 == "") {
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload2").value = "";
				document.getElementById("fileToUpload3").focus();
				return false;
			}
			   
			}
		if (deeds=='Y' && planaprvl=='Y'){
			
			if (file1 == "") {
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload1").value = "";
				document.getElementById("fileToUpload1").focus();
				return false;
			}
			
			if (file2 == "") {
				
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload2").value = "";
				document.getElementById("fileToUpload2").focus();
				return false;
			}	
			
			   if (file3 == "") {
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload2").value = "";
				document.getElementById("fileToUpload3").focus();
				return false;
			}
			   
			}
			else{
			if (file3 == "") {
				document.getElementById("errorBoxBasicPT").innerHTML = "please upload valid file";
				//document.getElementById("fileToUpload2").value = "";
				document.getElementById("fileToUpload3").focus();
				return false;
			}
				
			}
		

		for (var i = 1; i <= bcls.length; i++) {

			if (bcls[i].value != '') {
				if (bcls[i].value == '0' || bcls[i].value == null
						|| bcls[0].value == '') {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Select  Classification of Building for Mearurement "
							+ (i + 1);
					return false;
				}
				if (busage[i].value == '0' || busage[i].value == null
						| busage[i].value == '') {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Select  Building usage for Mearurement "
							+ (i + 1);
					return false;
				}
				if (octy[i].value == '0' || octy[i].value == null
						| octy[i].value == '') {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Occupant type for Mearurement "
							+ (i + 1);
					return false;
				}
				if (bage[i].value == '' || !(age_Regx.test(bage[i].value))) {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Building for Mearurement "
							+ (i + 1);
					document.getElementById("bage" + (i + 1)).focus();
					return false;
				}

				if (blen[i].value == '' || blen[i].value == '0'
						|| !(doublecheck_reg.test(blen[i].value))) {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Length for Mearurement "
							+ (i + 1);
					document.getElementById("len" + (i + 1)).focus();

					return false;
				}
				if (bwid[i].value == '' || bwid[i].value == '0'
						|| !(doublecheck_reg.test(bwid[i].value))) {
					document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter Width  for Mearurement "
							+ (i + 1);
					document.getElementById("wid" + (i + 1)).focus();
					return false;
				}
				/* if (bfloorNo[i].value == '' || bfloorNo[i].value == '0'
					|| !(floorNo_Regx.test(bfloorNo[i].value))) {
				document.getElementById("errorBoxmeasurments").innerHTML = "Please Enter floor No  for Mearurement "
						+ (i + 1);
				document.getElementById("floorNo" + (i + 1)).focus();
				return false;
			} */

			}
		}
		
		document.getElementById("errorBoxContact").innerHTML = "";
		document.getElementById("errorBoxTradeDetail").innerHTML = "";
		return true;
	}
	
	
	function checkmeasurement(){
        alert("Verify Measurement Carefully");
	}
	
	function submitForm(){
        confirm("I agree to the above inputs and if found any discrepancies. I will abide to the penalty as per the act. ");
	}
	
	function getmeasurement(){
		alert("hello");
		document.getElementByID("plinth").value=blen*bwid;	
	}
	
	
	function verhoeffLookup(aNum, aOffset) {

		var d = [ [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
				[ 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 ],
				[ 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 ],
				[ 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 ],
				[ 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 ],
				[ 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 ],
				[ 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 ],
				[ 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 ],
				[ 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 ],
				[ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 ] ];

		var p = [ [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
				[ 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 ],
				[ 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 ],
				[ 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 ],
				[ 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 ],
				[ 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 ],
				[ 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 ],
				[ 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 ] ];

		var lookup = 0;
		for (i = aNum.length - 1; i >= 0; i = i - 1) {
			lookup = d[lookup][p[(aNum.length - 1 - i + aOffset) % 8][parseInt(aNum
					.substr(i, 1))]];
		}

		return lookup;
	}

	function getlocality(ulbid) {
		//alert("Locality >>"+ulbid);

		//var soption = $('<option />').val("0").text("select");
		//$("#zone").append(soption);
		var option = $('<option />').val("0").text("-Select-");
		$("#locality").empty();
		// alert("set "+ulbid);
		document.getElementById("taxUlb").value = ulbid;
		//document.getElementById("taxUlb").value="fd";

		if (ulbid == 0) {

			$("#locality").empty();
			$("#locality").append($('<option />').val("0").text("-Select-"));

			$("#zone").empty();
			$("#zone").append($('<option />').val("0").text("-Select-"));

			$("#cmbbcls").empty();
			$("#cmbbcls").append($('<option />').val("0").text("-Select-"));

			$("#cmbbusage").empty();
			$("#cmbbusage").append($('<option />').val("0").text("-Select-"));

			$("#cmbocty").empty();
			$("#cmbocty").append($('<option />').val("0").text("-Select-"));

			return false;
		}
//adding floor type
		var url = "${pageContext.request.contextPath}/getFloorType.do";
		$("#floorType").append(option);
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
					$("#floorType").append(option);
				});
			},
			error : function() {
				var soption = $('<floorType />').val("0").text("select");
				$("#floorType").append(soption);
			}
		});
//End adding floor type
	/* 	Adding Roof Type */
	var url = "${pageContext.request.contextPath}/getRoofType.do";
		$("#roofType").append(option);
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
					$("#roofType").append(option);
				});
			},
			error : function() {
				var soption = $('<roofType />').val("0").text("select");
				$("#roofType").append(soption);
			}
		});
/* Ending Roof Type	 */
 
 /* Adding Waal Type  */
 var url = "${pageContext.request.contextPath}/getWoodType.do";
		$("#woodType").append(option);
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
					$("#woodType").append(option);
				});
			},
			error : function() {
				var soption = $('<woodType />').val("0").text("select");
				$("#woodType").append(soption);
			}
		});
 /* Ending Waal Type  */
 
 
 /* Adding Waal Type  */
 var url = "${pageContext.request.contextPath}/getWaalType.do";
		$("#waalType").append(option);
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
					$("#waalType").append(option);
				});
			},
			error : function() {
				var soption = $('<waalType />').val("0").text("select");
				$("#waalType").append(soption);
			}
		});
 /* Ending Wwood Type  */
 
		
		var knwlocationurl = "${pageContext.request.contextPath}/Gazette/"
				+ ulbid + ".pdf";
		document.getElementById("knowlocation").innerHTML = "<a href="+knwlocationurl+" target='_blank'  style='text-decoration:none'><b><u>Download </b></u>Municipality Gazzette Copy- for More information</a>";
		var url = "${pageContext.request.contextPath}/getlocality.do";
		$("#locality").append(option);
		//$('#grid').html('<img  src="../images/Loading.gif" width="30" height="30" />');
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#locality").append(option);
				});
				//var soption = $('<option />').val("0").text("select");
				//$("#zone").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#locality").append(soption);
			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0) {
			var option = $('<option />').val("0").text("select");
			$("#locality").empty();
			$("#locality").append(option);
		}
		//return false;
	}

	
	
	
	
	function getzone(localityid) {
		// alert("get zone >>"+localityid);

		//var soption = $('<option />').val("0").text("select");
		//$("#zone").append(soption);
		document.getElementById("localityid").value = localityid;
		var option = $('<option />').val("0").text("-Select-");
		$("#zone").empty();
		// alert("set "+ulbid);
		//document.getElementById("taxUlb").value=ulbid; 
		//document.getElementById("taxUlb").value="fd";
		var ulbid = document.getElementById("taxUlb").value;
		var locality = document.getElementById("locality").value;
		if (ulbid == 0 || locality == 0) {

			$("#zone").empty();
			$("#zone").append($('<option />').val("0").text("-Select-"));

			$("#cmbbcls").empty();
			$("#cmbbcls").append($('<option />').val("0").text("-Select-"));

			/* 	$("#busage").empty();
				$("#busage").append( $('<option />').val("0").text("-Select-"));
			 */
			/* $("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-"));
			 */

			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getzone.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#zone").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				ulbcode : ulbid,
				i_lctyobjid : localityid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {

					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#zone").append(option);

				});
				//var soption = $('<option />').val("0").text("select");
				//$("#zone").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#zone").append(soption);
			}
		});
		
				
		//alert(">>>" + ulbid);
		if (ulbid == 0 || locality == 0) {

			$("#zone").empty();
			$("#zone").append($('<option />').val("0").text("-Select-"));

			$("#cmbbcls").empty();
			$("#cmbbcls").append($('<option />').val("0").text("-Select-"));

			$("#cmbbusage").empty();
			$("#cmbbusage").append($('<option />').val("0").text("-Select-"));

			$("#cmbocty").empty();
			$("#cmbocty").append($('<option />').val("0").text("-Select-"));

		}
		//return false;
	}

	function getward() {
		// alert(">>getward" );
		var localityid = document.getElementById("localityid").value;
		var ulbid = document.getElementById("taxUlb").value;
		//alert(ulbid);
		//var soption = $('<option />').val("0").text("select");
		//$("#bcls").append(soption);
		var option = $('<option />').val("0").text("select");
		$("#revenueward").empty();

		//var zone = document.getElementById("revenueward").value;

		/* document.getElementById("tradeUlb").innerHTML = ulbid; */
		if (ulbid == 0 || localityid == 0) {

			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			$("#revenueward").empty();
			$("#revenueward").append($('<option />').val("0").text("-Select-"));

			/* $("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getward.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#revenueward").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				localityid : localityid,
				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {

					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#revenueward").append(option);

				});
				//var soption = $('<option />').val("0").text("select");
				//$("#bcls").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#revenueward").append(soption);
			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0 || localityid == 0) {
			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			/* $("#busage").empty();
			$("#busage").append( $('<option />').val("0").text("-Select-"));
			 
			$("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

		}
		return false;
	}

	function getElecward() {
		// alert(">>elecward" );

		var ulbid = document.getElementById("taxUlb").value;
		//alert(ulbid);
		//var soption = $('<option />').val("0").text("select");
		//$("#bcls").append(soption);
		var option = $('<option />').val("0").text("select");
		$("#elecward").empty();

		//var zone = document.getElementById("revenueward").value;

		/* document.getElementById("tradeUlb").innerHTML = ulbid; */
		if (ulbid == 0) {

			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			$("#elecward").empty();
			$("#elecward").append($('<option />').val("0").text("-Select-"));

			/* $("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getElecward.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#elecward").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {

				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {

					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#elecward").append(option);

				});
				//var soption = $('<option />').val("0").text("select");
				//$("#bcls").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#elecward").append(soption);
			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0) {
			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			/* $("#busage").empty();
			$("#busage").append( $('<option />').val("0").text("-Select-"));
			 
			$("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

		}
		return false;
	}
	function getstreet() {
		// alert(">>getstreet" );
		getbcls('');

		var ulbid = document.getElementById("taxUlb").value;
		var localityid = document.getElementById("localityid").value;
		//alert(ulbid);
		//var soption = $('<option />').val("0").text("select");
		//$("#bcls").append(soption);
		var option = $('<option />').val("0").text("select");
		$("#street").empty();

		//var zone = document.getElementById("revenueward").value;

		/* document.getElementById("tradeUlb").innerHTML = ulbid; */
		if (ulbid == 0) {

			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			$("#street").empty();
			$("#street").append($('<option />').val("0").text("-Select-"));

			/* $("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getstreet.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#street").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				localityid : localityid,
				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {

					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#street").append(option);

				});
				//var soption = $('<option />').val("0").text("select");
				//$("#bcls").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#street").append(soption);
			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0) {
			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			/* $("#busage").empty();
			$("#busage").append( $('<option />').val("0").text("-Select-"));
			 
			$("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

		}
		return false;
	}

	function getblock(reward) {
		//alert(">>getblck" + reward);

		var ulbid = document.getElementById("taxUlb").value;
		var localityid = document.getElementById("localityid").value;
		//alert(ulbid);
		//var soption = $('<option />').val("0").text("select");
		//$("#bcls").append(soption);
		var option = $('<option />').val("0").text("select");
		$("#block").empty();

		var zone = document.getElementById("block").value;

		/* document.getElementById("tradeUlb").innerHTML = ulbid; */
		if (ulbid == 0 || reward == 0) {

			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			$("#block").empty();
			$("#block").append($('<option />').val("0").text("-Select-"));

			/* $("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getblock.do";
		// alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#block").append(option);
		$.ajax({

			type : "POST",
			url : url,
			data : {
				localityid : localityid,
				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {

					option = $('<option />').val(i).text(elem);
					//alert(option);
					$("#block").append(option);

				});
				//var soption = $('<option />').val("0").text("select");
				//$("#bcls").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#block").append(soption);
			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0 || reward == 0) {
			/* $("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			 */
			/* $("#busage").empty();
			$("#busage").append( $('<option />').val("0").text("-Select-"));
			 
			$("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-")); */

		}
		return false;
	}

	function getbcls(bclsNum) {
		//alert(">>getbcls");

		var ulbid = document.getElementById("taxUlb").value;
		//alert(ulbid);
		//var soption = $('<option />').val("0").text("select");
		//$("#bcls").append(soption);
		var option = $('<option />').val("0").text("select");
		$("#cmbbcls" + bclsNum).empty();
		/* $("#bcls2").empty();
		$("#bcls3").empty();
		$("#bcls4").empty(); */
		//alert('1');
		var zone = document.getElementById("zone").value;
		//alert('2');
		/* document.getElementById("tradeUlb").innerHTML = ulbid; */
		if (ulbid == 0 || zone == 0) {
			/* 	$("#bcls").empty();
				$("#bcls").append($('<option />').val("0").text("-Select-"));

				$("#busage").empty();
				$("#busage").append($('<option />').val("0").text("-Select-"));

				$("#octy").empty();
				$("#octy").append($('<option />').val("0").text("-Select-"));
			 */
			return false;
		}
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getbcls.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
		$("#cmbbcls" + bclsNum).append(option);
		/* $("#bcls2").append(option);
		$("#bcls3").append(option);
		$("#bcls4").append(option); */
		//alert('4');
		$.ajax({

			type : "POST",
			url : url,
			data : {

				ulbcode : ulbid
			},
			// data : JSON.stringify(data),
			dataType : 'json',
			success : function(data) {
				var array = data;
				// alert(array);

				$.each(array, function(i, elem) {
					option = $('<option />').val(i).text(elem);
					$("#cmbbcls" + bclsNum).append(option);
					///$("#bcls2").append(option);
					//$("#bcls3").append(option);
					//$("#bcls4").append(option);
				});
				//var soption = $('<option />').val("0").text("select");
				//$("#bcls").append(soption);
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#cmbbcls" + bclsNum).append(soption);
				/* $("#bcls2").append(soption);
				$("#bcls3").append(soption);
				$("#bcls4").append(soption); */

			}
		});
		//alert(">>>" + ulbid);
		if (ulbid == 0 || zone == 0) {
			$("#cmbbcls" + bclsNum).empty();
			$("#cmbbcls" + bclsNum).append(
					$('<option />').val("0").text("-Select-"));

			$("#cmbbusage" + bclsNum).empty();
			$("#cmbbusage" + bclsNum).append(
					$('<option />').val("0").text("-Select-"));

			$("#cmbocty" + bclsNum).empty();
			$("#cmbocty" + bclsNum).append(
					$('<option />').val("0").text("-Select-"));

		}
		return false;
	}

	function getusage(measurementNum) {

		var ulbid = document.getElementById("taxUlb").value;
		var option = $('<option />').val("0").text("select");
		$("#cmbbusage" + measurementNum).empty();

		var bcls = document.getElementById('cmbbcls' + measurementNum).value;
		$("#taxbcls" + measurementNum).val(bcls);

		if (ulbid == 0 || bcls == 0 || bcls == '') {
			//alert(ulbid);
			$("#cmbbusage" + measurementNum).empty();
			$("#cmbbusage" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

			$("#cmbocty" + measurementNum).empty();
			$("#cmbocty" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

			return false;
		}

		var url = "${pageContext.request.contextPath}/getusage.do";

		$("#cmbbusage" + measurementNum).append(option);
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
					$("#cmbbusage" + measurementNum).append(option);

				});

			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#cmbbusage" + measurementNum).append(soption);
				//alert('err');
			}
		});

		if (ulbid == 0 || bcls == 0) {
			$("#cmbbusage" + measurementNum).empty();
			$("#cmbbusage" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

			$("#cmbocty" + measurementNum).empty();
			$("#cmbocty" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

		}
		return false;
	}

	//getocpnt
	function getocpnt(measurementNum) {
		var ulbid = document.getElementById("taxUlb").value;
		var option = $('<option />').val("0").text("select");

		$("#cmbocty" + measurementNum).empty();

		var busage = document.getElementById('cmbbusage' + measurementNum).value;
		$("#taxbusage" + measurementNum).val(busage);

		if (ulbid == 0 || busage == 0 || busage == '') {

			$("#cmbocty" + measurementNum).empty();
			$("#cmbocty" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

			return false;
		}

		var url = "${pageContext.request.contextPath}/getocpnt.do";
		$("#cmbocty" + measurementNum).append(option);
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
					$("#cmbocty" + measurementNum).append(option);
				});
			},
			error : function() {
				var soption = $('<option />').val("0").text("select");
				$("#cmbocty" + measurementNum).append(soption);
			}
		});
		if (ulbid == 0 || busage == 0) {

			$("#cmbocty" + measurementNum).empty();
			$("#cmbocty" + measurementNum).append(
					$('<option />').val("0").text("-Select-"));

		}
		return false;
	}

	function setocpnt(measurementNum) {
		var cmbocty = document.getElementById('cmbocty' + measurementNum).value;
		$("#taxocty" + measurementNum).val(cmbocty);
	}

	function setDefaultMeasurment(measurementNum) {

		$("#cmbbusage" + measurementNum).empty();
		$("#cmbbusage" + measurementNum).append(
				$('<option />').val("0").text("-Select-"));

		$("#cmbocty" + measurementNum).empty();
		$("#cmbocty" + measurementNum).append(
				$('<option />').val("0").text("-Select-"));

		$("#bage" + measurementNum).val('');
		$("#len" + measurementNum).val('');
		$("#wid" + measurementNum).val('');

		$("#taxbcls" + measurementNum).val('');
		$("#taxbusage" + measurementNum).val('');
		$("#taxocty" + measurementNum).val('');

	}
	/* 
	 function setErrorPos() {

	 var myElement = document.getElementById('errorBox'), pageWidth = window.innerWidth, pageHeight = window.innerHeight, myElementWidth = myElement.offsetWidth, myElementHeight = myElement.offsetHeight;

	 myElement.style.top = (pageHeight / 2) - (myElementHeight / 2) + "px";
	 myElement.style.left = (pageWidth / 2) - (myElementWidth / 2) + "px";
	 alert('gdfg');

	 /* for (var lx=0, ly=0; myElement != null;
	 lx += myElement.offsetLeft, ly += myElement.offsetTop, myElement = myElement.offsetParent);
	 alert(x: lx,y: ly);

	 } */
</script>



</html>
<div id="page"></div>
<div id="results"></div>


<input type="hidden" name="reqsearchDistrictId" id="reqsearchDistrictId"
	value='<c:out value="${requestScope.districtId}"/>'>
<input type="hidden" name="ulbSelected" id="ulbSelected"
	value='<c:out value="${requestScope.districtId}"/>'>

