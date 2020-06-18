
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

						$("#addButton").click(function() {
 
											var ulbId = document
													.getElementById("taxUlb").value;
											var zone = document
													.getElementById("zone").value;
											var elecward = document
													.getElementById("elecward").value;

											if (counter > 5 || ulbId == 0
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
				
				
				$('#fileToUpload1').prop("disabled", "disabled");
				$('#fileToUpload2').prop("disabled", "disabled");
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
			
			} else {
				
				$('#regdocno').prop("disabled", "disabled");
				$('#regDate').prop("disabled", "disabled");
				$('#regdocno').val("");
				$('#regDate').val("");
				
				
			}
			return false;
		});		
	});
	
</script>
<body>

	<div class="container">

		<form name="newAppForm" method="POST" action="saveAsmtDetail.do"
			enctype='multipart/form-data' id="newAppForm"
			onsubmit="return checkValidation();">
			<fieldset>
				<div class="panel panel-primary">
					<div class="panel-heading">Applicant Information</div>


					 <div class="panel-body ">
						<div class="panel-group">
							<div class="form-group">
								<label class="col-md-3 control-label">Registered Title Deed Available :</label>
								<div class="col-md-9 inputGroupContainer">
									<div class="input-group">
										<input type="radio" id="deedsAvailable" name="deedsAvailable" value="Y"
											checked="checked"> Yes <input type="radio"
											id="deedsAvailable" name="deedsAvailable" value="N"> No
									</div>
								</div>
							</div>
						</div>
					</div> 

					<div class="panel-body">
						<div class="panel-group">




							<div class="form-group">
								<label class="col-md-2 control-label">SurName</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerSurName" id="ownerSurName"
											placeholder="Owner SurName" class="form-control" type="text">

									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Name</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerName" id="ownerName"
											placeholder="Owner Name" class="form-control" type="text">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Relation Surname</label>
								<div class="col-md-4 inputGroupContainer">
									<div class="input-group">
										<input name="ownerFatherHusbandSurName"
											id="ownerFatherHusbandSurName"
											placeholder="Father's/Husband Surname" class="form-control"
											type="text">
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label">Relation Name</label>
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
								<label class="col-md-2 control-label">Aadhaar</label>
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

									<!-- <div class="form-group">
										<label class="col-md-2 control-label">Street</label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">

												<input name="ownerStreet" id="ownerStreet"
													placeholder="Street" class="form-control" type="text">
											</div>
										</div>
									</div>
 -->
									<div class="form-group">
										<label class="col-md-2 control-label">Owner City</label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">

												<input name="ownerVilliage" id="ownerVilliage"
													placeholder="Owner City" class="form-control" type="text">
											</div>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-2 control-label">PIN</label>

										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="ownerPinCode" id="ownerPinCode"
													placeholder="PIN Code" class="form-control" type="text">
											</div>
										</div>
									</div>

									<%-- <div class="form-group">
										<label class="col-md-2 control-label">District</label>
										<div class="col-md-4  inputGroupContainer">
											<div class="input-group">
												<select name="ownerDistrict" id="ownerDistrict"
													class="form-control">
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
									</div> --%>

									<!-- <div class="form-group">

										<label class="col-md-2 control-label">PIN</label>

										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="ownerPinCode" id="ownerPinCode"
													placeholder="PIN Code" class="form-control" type="text">
											</div>
										</div>
									</div> -->
								</div>


							</div>
							<div class="col-md-12 control-label" align="center"
								id="errorBoxAddress"
								style="color: red; font-weight: bold; font-size: 15px;"></div>

						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Applicant
							Contact Details</div>
						<div class="panel-body">
							<div class="panel-group">
								<div>
									<!-- class="panel panel-primary panel-body" -->

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
										<label class="col-md-2 control-label">Mobile</label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="ownerMobile" id="ownerMobile"
													placeholder="Mobile" class="form-control" type="text">
											</div>
										</div>
									</div>


									<div class="form-group" style="display: none">
										<label class="col-md-2 control-label">Land No</label>
										<div class="col-md-4  ">
											<div class="input-group">
												<input name="ownerLandPhone" id="ownerLandPhone"
													placeholder="Land No" class="form-control" type="text">

											</div>
										</div>
									</div>
									<div class="form-group" style="display: none">
										<label class="col-md-2 control-label">Fax</label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="ownerFax" id="ownerFax" placeholder="Fax"
													class="form-control" type="text">
											</div>
										</div>
									</div>

								</div>


							</div>
							<div class="col-md-12 control-label" align="center"
								id="errorBoxContact"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
						</div>
					</div>


					<div class="panel panel-primary">
						<div class="panel-heading" style="background-color: #782669;">Ownership/Occupant
							Details</div>


						<div class="panel-body">
							<div class="panel-group">
								<div>

									<!-- class="panel panel-primary panel-body" -->

									<div class="form-group">
										<label class="col-md-2 control-label">Occupant Name</label>
										<div class="col-md-4 ">
											<div class="input-group">

												<input name="occupantname" id="occupantname"
													placeholder="Occupant Name" class="form-control"
													type="text">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Occupant SurName</label>
										<div class="col-md-4  ">
											<div class="input-group">

												<input name="occupantsurname" id="occupantsurname"
													placeholder="Occupant SurName" class="form-control"
													type="text">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">OwnerShip Type</label>
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

								<!-- <div class="form-group">
										<label class="col-md-2 control-label">Trade Door No</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<input name="tradeDoorNo" id="tradeDoorNo"
													placeholder="Trade Door No" class="form-control"
													type="text">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-2 control-label">Street</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">

												<input name="tradeStreet" id="tradeStreet"
													placeholder="Trade Street" class="form-control" type="text">
											</div>
										</div>
									</div> -->


								<!-- <div class="form-group">
										<label class="col-md-2 control-label">Trade City</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<input name="tradevillCity" id="tradevillCity"
													placeholder="Trade City" class="form-control" type="text">

											</div>
										</div>
									</div> -->

								<div class="form-group">
									<label class="col-md-2 control-label">District :</label>
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
									<label class="col-md-2 control-label">ULB Name:</label>
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
									<label class="col-md-2 control-label">Locality Name:</label>
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
									<label class="col-md-2 control-label">Zone Name:</label>
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
									<label class="col-md-2 control-label">Revenue Ward:</label>
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
									<label class="col-md-2 control-label">Block No:</label>
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
									<label class="col-md-2 control-label">Election Ward:</label>
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

								<!-- <div class="form-group">
										<label class="col-md-2 control-label">PIN Code</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<input name="tradePin" id="tradePin" placeholder="PIN Code"
													class="form-control" type="text">
											</div>
										</div>
									</div> -->
							</div>
							<div class="col-md-12 control-label" align="center"
								id="errorBoxlocation"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading" style="background-color: #cff2f9;">Assessment
							Details</div>
						<div class="panel-body">
							<div class="panel-group">

								<!-- 								<div class="form-group">
										<label class="col-md-2 control-label">Bulding Paln</label>
										<div class="col-md-4 inputGroupContainer ">
											<div class="input-group">
												 <label> <input type="radio" name="Buldingpaln"
													value="S" />yes
												</label>&nbsp;&nbsp;<label><input type="radio"
													name="Buldingpaln" value="N" /> no
												</label>
								<input type="text" >
											</div>
										</div>
									</div>
	 -->
								<div class="form-group">
									<label class="col-md-3 control-label">Bulding Plan
										Apporval:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="radio" id="planaprvl" name="planaprvl" value="Y"
												checked="checked"> Yes <input type="radio"
												id="planaprvl" name="planaprvl" value="N"> No
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Reg Doc No:</label>
									<div class="col-md-4 inputGroupContainer">
										<div class="input-group">

											<input name="regdocno" id="regdocno" placeholder="Reg No"
												class="form-control" type="text">
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-2 control-label">Reg Date:</label>
									<div class="col-md-4 inputGroupContainer ">
										<div class="input-group">
											<!--  <label> <input type="radio" name="Buldingpaln"
													value="S" />yes
												</label>&nbsp;&nbsp;<label><input type="radio"
													name="Buldingpaln" value="N" /> no
												</label> -->
											<input name="regDate" id="regDate" placeholder="Reg Date"
												class="form-control" type="text" readonly="readonly">
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
							Measurment Details</div>
						<div class="panel-body" id="DivBoxesGroup">

							<!-- Measurement 1 -->
							<div class="panel-group" id="DivBoxDiv1">
								<div class="form-group">
									<label class="col-md-3 control-label">Classification of
										Building:</label>
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
									<label class="col-md-3 control-label">Type of Building
										usage:</label>
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
									<label class="col-md-3 control-label"> Occupant type:</label>
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
										Age:(No.of Years)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input class="form-control" type="text" name="bage" id="bage"
												Placeholder="enter Building Age">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Length:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control " name="len" id="len"
												Placeholder="Enter Length">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Width:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control" name="wid" id="wid"
												placeholder="Enter Width">
										</div>
									</div>
								</div>
							</div>

							<!-- Measurement 2 -->
							<div class="panel-group" id="DivBoxDiv2" style="display: none;">
								<div class="col-md-12">
									<hr>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Classification of
										Building:</label>
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
									<label class="col-md-3 control-label">Type of Building
										usage:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbbusage" id="cmbbusage2"
												onchange="javascript:getocpnt('2')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"> Occupant type:</label>
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
										Age:(No.of Years)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input class="form-control" type="text" name="bage"
												id="bage2" Placeholder="enter Building Age">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Length:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control " name="len" id="len2"
												Placeholder="Enter Length">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Width:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control" name="wid" id="wid2"
												placeholder="Enter Width">
										</div>
									</div>
								</div>
							</div>

							<!-- Measurement 3 -->
							<div class="panel-group" id="DivBoxDiv3" style="display: none;">
								<div class="col-md-12">
									<hr>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Classification of
										Building:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbbcls" id="cmbbcls3"
												onchange="javascript:getusage('3')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Type of Building
										usage:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbbusage" id="cmbbusage3"
												onchange="javascript:getocpnt('3')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"> Occupant type:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbocty" id="cmbocty3"
												onchange="javascript:setocpnt('3')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Building
										Age:(No.of Years)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input class="form-control" type="text" name="bage"
												id="bage3" Placeholder="enter Building Age">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Length:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control " name="len" id="len3"
												Placeholder="Enter Length">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Width:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control" name="wid" id="wid3"
												placeholder="Enter Width">
										</div>
									</div>
								</div>
							</div>

							<!-- Measurement 4 -->
							<div class="panel-group" id="DivBoxDiv4" style="display: none;">
								<div class="col-md-12">
									<hr>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Classification of
										Building:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbbcls" id="cmbbcls4"
												onchange="javascript:getusage('4')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Type of Building
										usage:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbbusage" id="cmbbusage4"
												onchange="javascript:getocpnt('4')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"> Occupant type:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="cmbocty" id="cmbocty4"
												onchange="javascript:setocpnt('4')">
												<option value="0">-Select-</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Building
										Age:(No.of Years)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input class="form-control" type="text" name="bage"
												id="bage4" Placeholder="enter Building Age">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Length:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control " name="len" id="len4"
												Placeholder="Enter Length">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Width:(in Mtrs.)</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<input type="text" class="form-control" name="wid" id="wid4"
												placeholder="Enter Width">
										</div>
									</div>
								</div>
							</div>


							<div class="panel-group">
								<div align="right" class="col-md-12 ">
									<input type="button" class="btn btn-primary"
										value="Add Measurement" id='addButton' /> <input
										type='button' class="btn btn-primary"
										value='Remove Measurement' id='removeButton'>
								</div>
							</div>

							<div class="col-md-12 control-label" align="center"
								id="errorBoxmeasurments"
								style="color: red; font-weight: bold; font-size: 15px;"></div>
						</div>

					</div>



					<%-- <div class="panel panel-default">
							<div class="panel-heading" style="background-color: #cff2f9;">Trade
								Categories</div>
							<div class="panel-body">
								<div class="panel-group">
									<div class="form-group">
										<label class="col-md-2 control-label">Category Type</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select id="tradecategory" name="tradecategory"
													onchange="javascript:getsubtrade(this.value)"
													class="form-control">
													<option value="0">Select</option>
												</select>
												 <c:forEach items="${state.stateMap}" var="data" varStatus="status">
												            <tr>
												                <td>${data.value}</td>
												            </tr>
												        </c:forEach>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Sub Category</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="subcategory" id="subcategory"
													class="form-control"
													onchange="javascript:getsubcat(this.value)">
													<option value="0">Select</option>
												</select> <input name="trade_amount" id="trade_amount" value="0"
													class="form-control" type="hidden" readonly="readonly">
											</div>
										</div>
									</div>

									<div class="form-group">
										<!-- <label class="col-md-2 control-label">Whether
											documents submitted</label>
										<div class="col-md-4">
											<div class="radio">
												<label> <input type="radio" name="tradeDocEnclosed"
													value="S" />yes
												</label>&nbsp;&nbsp; <label> <input type="radio"
													name="tradeDocEnclosed" value="N" /> no
												</label>
											</div>

										</div> -->
									</div>
									<!-- <div class="form-group">
										<label class="col-md-2 control-label">Trade Amount</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">

												<input name="trade_amount" id="trade_amount" value="0"
													class="form-control" type="text" readonly="readonly">
											</div>
										</div>
									</div> -->
								</div>

								<div class="col-md-12 control-label" align="center"
									id="errorBoxTradeDetail"
									style="color: red; font-weight: bold; font-size: 15px;"></div>
									
								<div class="col-md-12 control-label" align="center" id="errorBoxSubmitButton"
									style="color: red; font-size: 15px; margin-bottom: 15px; font-weight: bold;"></div>

							</div>
						</div> --%>
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
											<div class="col-md-7" style="font-weight: bold;">1.OwnerShip
												Document</div>
											<div class="col-md-5">
												<input name="file1" id="fileToUpload1" name="fileToUpload"
													type="file">
											</div>
										</div>
										<div class="col-md-12" style="line-height: 35px">
											<div class="col-md-7" style="font-weight: bold;">2.Photo
												of Building</div>
											<div class="col-md-5">
												<input name="file2" id="fileToUpload2" name="fileToUpload"
													type="file">
											</div>
										</div>
										<!-- <div class="col-md-12" style="line-height: 35px">
												<div class="col-md-7" style="font-weight: bold;"> </div>
												<div class="col-md-5">
													<input name="file3" id="fileToUpload3" name="fileToUpload"
														type="file">
												</div>
											</div> -->


									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 control-label" align="center"
							id="errorBoxTradeDoc"
							style="color: red; font-weight: bold; font-size: 15px;"></div>
					</div>

					<div align="center" id="errorBox"
						style="color: red; font-size: 14px; margin-bottom: 15px;"></div>
					<div align="center" class="submit" id="submitButton">
						<input type="submit" class="btn btn-primary" value="Submit" />
					</div>

					<br>
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
					
					<!-- charan written -->
					<input type="hidden" name="status" id="status" value="C" >

			</fieldset>

		</form>
	</div>
	<!-- <button type="button" data-toggle="modal" data-target="#myModel1">Clikc</button> -->

	<!-- <div class="modal fade1" id="myModel1" role="dialog" class="container">
		<div class="modal-dialog modal-lg">

			Modal content
			<div class="modal-content" class="">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">List</h4>
				</div>
				<div class="modal-body">
					List:
					<li>1: Fire Department : Fire Works,Petrol Bunk,Cinema Theatre
						etc...</li>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div> -->
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

		var surname_Regx = /^[a-zA-Z ]{1,50}$/;
		var name_Regx = /^[a-zA-Z ]{2,50}$/;
		var mobile_reg = /^[6-9]+[0-9]{9}$/;
		var rex_pannumber = /^[A-Z]{3}[CHFATBLJGP]{1}[A-Z]{1}[0-9]{4}[A-Z]$/;
		var email_Regx = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
		var number_Regx = /^[0-9]+$/;
		var ownerName = document.newAppForm.ownerName.value;
		var ownerSurName = document.newAppForm.ownerSurName.value;
			
		var doublecheck_reg = /^[0-9]+\.?[0-9]*$/;
		var age_Regx = /^[0-9]{1,2}$/;

		//alert(bcls+" "+busage+" "+octy+" "+bage+" "+len+" "+wid);

		var ownerFatherHusbandName = document.newAppForm.ownerFatherHusbandName.value;
		var ownerFatherHusbandSurName = document.newAppForm.ownerFatherHusbandSurName.value;
		var ownerAadhar = document.newAppForm.ownerAadhar.value;
		var ownerPan = document.newAppForm.ownerPan.value;

		var ownerDoorNo = document.newAppForm.ownerDoorNo.value;
		var ownerStreet = document.newAppForm.street.value;
		var ownerVilliage = document.newAppForm.ownerVilliage.value;

		var ownerDistrict = document.getElementById('reqsearchDistrictId').value;
		var ownerPinCode = document.newAppForm.ownerPinCode.value;
		var ownerEmail = document.newAppForm.ownerEmail.value;
		var ownerMobile = document.newAppForm.ownerMobile.value;
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

		var bcls = document.getElementsByName("bcls");
		var busage = document.getElementsByName("busage");
		var octy = document.getElementsByName("octy");
		var bage = document.getElementsByName("bage");
		var blen = document.getElementsByName("len");
		var bwid = document.getElementsByName("wid");
		var ownerPinCode_Regx=/^[0-9]{6}$/;
		var planaprvl = document.getElementsByName('planaprvl');
		var plancheckedval = '';
		for ( var i = 0, length = planaprvl.length; i < length; i++) {
			if (planaprvl[i].checked) {
				plancheckedval = planaprvl[i].value;
				break;
			}
		}

		//var palnApproval = $("input[name='planaprvl']:checked").val();
		//alert($("#planaprvl").prop("checked"));

		if (!surname_Regx.test(ownerSurName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Enter valid Sur-Name/ Only Alphbets are allowed  ";
			//document.getElementById("ownerSurName").value = "";
			document.getElementById("ownerSurName").focus();
			return false;
		}
		if (!ownerPinCode_Regx.test(ownerPinCode)) {
			document.getElementById("ownerPinCode").innerHTML = "Enter valid Pincode/ Only 6 digits are allowed  ";
			document.getElementById("ownerPinCode").value = "";
			document.getElementById("ownerPinCode").focus();
			return false;
		}

		if (!name_Regx.test(ownerName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Enter valid  Name/Only Alphbets  are allowed in Name ";
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
		if (!name_Regx.test(ownerFatherHusbandSurName)) {
			document.getElementById("errorBoxBasic").innerHTML = "Only Alphbets  are allowed in Father's/Husband SurName ";
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

		
		/* if (ownerDoorNo == '' || ownerDoorNo == null) {
			document.getElementById("errorBoxAddress").innerHTML = "Please Enter Door Number";
			//document.getElementById("ownerAadhar").value = "";
			document.getElementById("ownerDoorNo").focus();
			return false;
		} */
		document.getElementById("errorBoxAddress").innerHTML = "";

		/* 	if (ownerStreet == '' || ownerStreet == null) {
				document.getElementById("errorBoxAddress").innerHTML = "Please Enter Street ";
				document.getElementById("ownerStreet").focus();
				return false;
			} */

		if (!name_Regx.test(ownerVilliage)) {
			document.getElementById("errorBoxAddress").innerHTML = "Please Enter City ";
			//document.getElementById("ownerVilliage").value = "";
			document.getElementById("ownerVilliage").focus();
			return false;
		}
		document.getElementById("errorBoxAddress").innerHTML = "";

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
			document.getElementById("errorBoxContact").innerHTML = "Please Enter Valid Mobile Number ";
			document.getElementById("ownerMobile").value = "";
			document.getElementById("ownerMobile").focus();
			return false;
		}
			document.getElementById("errorBoxContact").innerHTML = "";
			
		if (!name_Regx.test(occupantname)) {
			document.getElementById("errorBoxOccupant").innerHTML = "Please Enter Occupant Name";
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

		for ( var i = 1; i <= bcls.length; i++) {

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

			}
		}
		document.getElementById("errorBoxContact").innerHTML = "";
		document.getElementById("errorBoxTradeDetail").innerHTML = "";
		return true;
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
		// var code=document.getElementById("searchUlbId" + ulbid).value;
		//var url = "${pageContext.request.contextPath}"
		//		+ "/cdma/Trade/gettcategory/";

		var url = "${pageContext.request.contextPath}/getlocality.do";
		//alert(url);
		//var option = $('<option />').val("0").text("select");
		//var option = $('<option />');
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

