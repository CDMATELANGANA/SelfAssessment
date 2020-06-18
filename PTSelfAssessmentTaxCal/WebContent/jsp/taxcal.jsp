<%@page import="com.mars.cdma.gov.servlet.DBconnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html PUBLIC "9-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>

<html>
<head>
 
   
 <script type="text/javascript">
  
 
	function getListDistrictWise() {
 
		$('.ulbs').hide();
		$('#ulbsTR').show();
		// $("#tradecategory").empty();
		// $("#subcategory").empty();
		/* var option = $('<option />').val("0").text("select");
		$("#zone").empty();
		$("#zone").append(option);
		$("#bcls").empty();
		$("#bcls").append(option); */
		
	 $("#locality").empty();
	 $("#locality").append($('<option />').val("0").text("-Select-"));
		
	$("#zone").empty();
	$("#zone").append($('<option />').val("0").text("-Select-"));
	
	$("#bcls").empty();
	$("#bcls").append($('<option />').val("0").text("-Select-"));
	
	$("#busage").empty();
	$("#busage").append( $('<option />').val("0").text("-Select-"));
	 
	$("#octy").empty();
	$("#octy").append( $('<option />').val("0").text("-Select-"));
 
		

		var ulbId = document.getElementById("reqsearchDistrictId").value;

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
 
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
  <link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<script src="<%=path%>/jsp/css/jquery.min.js"></script>
<script src="<%=path%>/jsp/css/bootstrap.min.js"></script>

 
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
<title>TAX CALCULATOR</title>
</head>
<body>
<h2 style=  "text-align:center; color:purple;"><b>Property Tax Calculator</b></h2>
 
<div class="container_10">

<form   name="form1" action="<%=path %>/calculatetax.do" onsubmit="return Valid()" method="post">
<%-- <form   name="form1" action="<%=path %>/calculatetaxservices.do" onsubmit="return Valid()" method="post"> --%>
<!--  
<div class="container_12"> -->
<fieldset>
 
<div class="panel panel-primary">
					<!-- <div class="panel-heading"></div> -->
					<div class="panel-body">
							<div class="panel-group">

								<div class="form-group">
									<label class="col-md-3 control-label">District:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select name="tradeDistrict" id="reqsearchDistrictId"
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
									<label class="col-md-3 control-label">ULB Name:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
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
								</div>

							
							<div class="form-group">
									<label class="col-md-3 control-label">Locality Name:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
										<select class="form-control" name="locality" id="locality"
											onchange="javascript:getzone(this.value)">
											<option value="0">-Select-</option>

										</select>
											
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-3 control-label">Zone Name:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<select class="form-control" name="zone" id="zone"
												onchange="javascript:getbcls()">
												<option value="0">-Select-</option>

											</select>
											
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Classification of
										Building:</label>
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
										<select class="form-control" name="bcls" id="bcls"
												onchange="javascript:getusage()">
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
<select class="form-control" name="busage" id="busage"
												onchange="javascript:getocpnt()">
												<option value="0">-Select-</option>

											</select>
											
											
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Building Plan
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
								<label class="col-md-3 control-label"> Occupant type:</label>
								<div class="col-md-3 inputGroupContainer">
									<div class="input-group">
										<select class="form-control" name="octy" id="octy">
												<option value="0">-Select-</option>
											</select>
									</div>
								</div>
							</div> 
							 

								<div class="form-group">
									<label class="col-md-3 control-label">Building Age:(No.of Years)</label>
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
								
								 
									 
								<div class="col-md-12 control-label" align="center"
							id="errorBoxBasic"
							style="color: red; font-weight: bold; font-size: 15px;"></div>
								<div class="form-group">
									<label class="col-md-4 control-label" for="button1id"></label>
									<div class="col-md-8">
										<input type="submit" value="calculate" class="btn btn-success"
											name="submit"> <input type="reset"
											class="btn btn-danger">
									</div>
								</div>
							</div>

						</div>
							</div>
							<div><p class="error">Note:Occupant Type should be selected as Commercial for all Non-Residetial Building Usage.</p></div>
							<div><p class="error">Note:After the field inspection(Measurements and Occupant Type) by the revenue staff, Final PT will arrive + library cess will be added.</p></div>
							</fieldset>
							
							<input type="hidden" name="taxUlb" id="taxUlb" value="">
							</form>
							</div>
							
							 
							

<%-- 
<div class="form-group">
										<label class="col-md-2 control-label">District</label>
										<div class="col-md-4 inputGroupContainer">
											<div class="input-group">
												<select name="tradeDistrict" id="reqsearchDistrictId"
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
										<label class="col-md-2 control-label">Trade ULB</label>
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
													<select onchange="javascript:getzone(this.value)"
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
<table class="table">

<tr>
<td class="col-md-3"></td>
<td class="col-md-2">
<label class="control-label col-md-6" for="zone"><b>Zone Name :</b></label></td>

 <td><div class="col-md-12">
 <select class="form-control"   name="zone" id="zone" onchange="javascript:getbcls()">
<option value="0">-Select-</option>
 
</select></div>  <td class="col-md-3"></td></tr>


<tr>
<td class="col-md-3"></td>
<td class="col-md-2"><label class="control-label col-md-6" for="bcls">
<b>Classification of Building:</b></label></td>
<td><div class="col-md-12"><select  class="form-control" name="bcls" id="bcls" onchange="javascript:getusage()">
<option value="0">-Select-</option>
 
</select></div></td>
<td class="col-md-3"></td>

</tr>
<tr>
<td class="col-md-3"></td>
<td class="col-md-2"><label class="control-label col-md-7" for="busage">

<b>Type of Building usage:</b></label></td>
<td><div class="col-md-12"><select class="form-control"  name="busage" id="busage" onchange="javascript:getocpnt()">
<option value="0">-Select-</option>
 
</select>
</div></td>
<td class="col-md-3"></td>
</tr>

<tr><td class="col-md-3"></td>
<td class="col-md-2"><label class="control-label col-md-7" for="octy">
<b>  Occupant type:</b></label></td>
<td><div class="col-md-12"><select  class="form-control"name="octy" id="octy">
<option value="0">-Select-</option>
<!-- <option value="1">Owner</option>
<option value="2">Tenant</option>
<option value="3">Commercial</option> -->
</select></div></td>
<td class="col-md-3"></td></tr>

  <tr><td class="col-md-3"></td>
<td class="col-md-2"><label class="control-label col-md-7" for="res">
<b>Bulding Plan Apporval:</b></label></td>
<td><input type="radio" id="planaprvl" name="planaprvl" value="Y" checked="checked"> Yes
  <input type="radio" id="planaprvl" name="planaprvl" value="N"> No</td>
  </td>
  <td class="col-md-3"></td></tr>  
  <tr><td class="col-md-3"></td>
<td class="col-md-2"><label class="control-label col-md-7" for="res">
<b>Occupation
Type:</b></label></td>
<td> 
<select class="form-control"  name="ocpntype" id="ocpntype">
<option value="0">-Select-</option>
<%while(rs1.next()) {%>
<option value="<%=rs1.getString(1) %>"><%=rs1.getString(2) %></option><%} %>

</select>
</td>
  </td>
  <td class="col-md-3"></td></tr>  
  
  
 
<tr><td class="col-md-3"></td>
<td class="col-md-2">
<label class="control-label col-md-7" for="bage"><b>Building Age:</b></label></td>
<td><div class="col-md-12"><input class="form-control" type="text" name="bage" id="bage" Placeholder="enter Building Age"></div></td>
<td class="col-md-3"></td></tr>
 
<tr><td class="col-md-3"></td>
<td class="col-md-2">
<label class="control-label col-md-7" for="len"><b>Length:</b></label></td>
<td><div class="col-md-12"><input type="text" class="form-control " name="len" id="len" Placeholder="Enter Length"></div></td>
<td class="col-md-3">
</td></tr>

<tr><td class="col-md-3"></td>
<td class="col-md-2">
<label class="control-label col-md-7" for="wid"><b>Width:</b></label></td>
<td><div class="col-md-12">
<input type="text" class="form-control" name="wid" id="wid" placeholder="Enter Width"></div></td>
<td class="col-md-3"></td></tr>
<tr>
<td> <div class="col-md-12 control-label" align="center"
							id="errorBoxBasic"
							style="color: red; font-weight: bold; font-size: 15px;"></div>
							</td>
							</tr>
<tr><td class="col-md-2"></td>
     
     
<td><center><button style="text-align:center;" type="submit" class="btn btn-primary control-form center-block" name="submit" value="submit">CALCULATE</button></center></td>
<td class="col-md-2"></td></tr>

</table>
</fieldset>
</div>
 --%>
<!-- <input type="hidden" name="taxUlb" id="taxUlb" value="">
 -->
<%-- </form> --%>
 
<script type="text/javascript">

 

 function Valid()
{
	 
	var lent=document.getElementById("len").value;
	
	var widt=document.getElementById("wid").value;
	 
	var buage=document.getElementById("bage").value;
	//var zo=document.getElementById("zone").value;
	
	/* var zo = document.getElementById("zone");
    var z = zo.options[zo.selectedIndex].value;
	var bc = document.getElementById("bcls");
	var b = bc.options[bc.selectedIndex].value;
	var bu = document.getElementById("busage");
	var b2 = bu.options[bu.selectedIndex].value;
	var ot = document.getElementById("octy");
	var o = ot.options[ot.selectedIndex].value;
 */
	var ulbcode=document.getElementById("taxUlb").value;
	var zone=document.getElementById("zone").value;
	var locality=document.getElementById("locality").value;
	var bcls=document.getElementById("bcls").value;
	var busage=document.getElementById("busage").value;
	var octy=document.getElementById("octy").value;
	var doublecheck_reg=/^\d{0,2}(?:\.\d{0,2}){0,1}$/;
	var number_Regx = /^[0-9]{1,2}$/;
	 
	 
	if(ulbcode==0)
	{
		document.getElementById("errorBoxBasic").innerHTML = "Please Select Ulb";
	//alert(" Please Select Ulb");	
	
	return false;
	}
	
	if(locality==0)
	{
	//alert(" Please Select Zone");	
	document.getElementById("errorBoxBasic").innerHTML = "Please Select Locality";
	document.getElementById("locality").focus();
	return false;
	}
	
	if(zone==0)
	{
	//alert(" Please Select Zone");	
	document.getElementById("errorBoxBasic").innerHTML = "Please Select Zone";
	document.getElementById("zone").focus();
	return false;
	}
	if(bcls==0)
	{
	//alert(" Please Select Classification");	
	document.getElementById("errorBoxBasic").innerHTML = "Please Select Classification";
	document.getElementById("bcls").focus();
	return false;
	}
	if(busage==0)
	{
	//alert(" Please Select Usage");	
	document.getElementById("errorBoxBasic").innerHTML = "Please Select Usage";
	document.getElementById("busage").focus();
	return false;
	}
	if(octy==0)
	{
	//alert(" Please Select Occupant Type");	
	document.getElementById("errorBoxBasic").innerHTML = "Please Select Type";
	document.getElementById("octy").focus();
	return false;
	}
 
	if(!number_Regx.test(buage))
	{
	document.getElementById("errorBoxBasic").innerHTML = "Please Enter Building Age";
	document.getElementById("bage").value = "";
	document.getElementById("bage").focus();
	
	return false;
	}
	 
	  if(lent.length==0||!doublecheck_reg.test(lent))
		{
		//alert(" Please Enter length");	
		document.getElementById("errorBoxBasic").innerHTML = "Please Enter length";
		document.getElementById("len").value = "";
		document.getElementById("len").focus();
		return false;
		} 
		 
 
	if(widt.length==0||!doublecheck_reg.test(widt))
	{
	//alert(" Please Enter Width");
	document.getElementById("errorBoxBasic").innerHTML = "Please Enter Width";
	document.getElementById("wid").value = "";
	document.getElementById("wid").focus();
	return false;
	}
	
	
	
	
	
	  
}
 function getlocality(ulbid) {
		//alert("Locality >>"+ulbid);

		 
		//var soption = $('<option />').val("0").text("select");
		//$("#zone").append(soption);
	var option = $('<option />').val("0").text("-Select-");
			$("#locality").empty();
		// alert("set "+ulbid);
		 document.getElementById("taxUlb").value=ulbid; 
		 //document.getElementById("taxUlb").value="fd";

		 if (ulbid == 0) {
			
			 
			 $("#locality").empty();
				$("#locality").append($('<option />').val("0").text("-Select-"));
				
			$("#zone").empty();
			$("#zone").append($('<option />').val("0").text("-Select-"));
			
			$("#bcls").empty();
			$("#bcls").append($('<option />').val("0").text("-Select-"));
			
			$("#busage").empty();
			$("#busage").append( $('<option />').val("0").text("-Select-"));
			 
			$("#octy").empty();
			$("#octy").append( $('<option />').val("0").text("-Select-"));
		 
			 
			
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
var option = $('<option />').val("0").text("-Select-");
		$("#zone").empty();
	// alert("set "+ulbid);
	 //document.getElementById("taxUlb").value=ulbid; 
	 //document.getElementById("taxUlb").value="fd";
var ulbid=document.getElementById("taxUlb").value;
var locality=document.getElementById("locality").value;
	 if (ulbid == 0||locality==0) {
		
		$("#zone").empty();
		$("#zone").append($('<option />').val("0").text("-Select-"));
		
		$("#bcls").empty();
		$("#bcls").append($('<option />').val("0").text("-Select-"));
		
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	   
		
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
			i_lctyobjid:localityid
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
 	 if (ulbid == 0||locality==0) {
		
		$("#zone").empty();
		$("#zone").append($('<option />').val("0").text("-Select-"));
		
		$("#bcls").empty();
		$("#bcls").append($('<option />').val("0").text("-Select-"));
		
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	  	 
	}
	//return false;
}

function getbcls() {
	//alert(">>getbcls");

	var ulbid=document.getElementById("taxUlb").value; 
	//alert(ulbid);
	//var soption = $('<option />').val("0").text("select");
	//$("#bcls").append(soption);
var option = $('<option />').val("0").text("select");
		$("#bcls").empty();
	 
		var zone=document.getElementById("zone").value;
	 


	/* document.getElementById("tradeUlb").innerHTML = ulbid; */
	if (ulbid == 0||zone==0) {
	 

		$("#bcls").empty();
		$("#bcls").append($('<option />').val("0").text("-Select-"));
		
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	 
		 
		return false;
	}
	// var code=document.getElementById("searchUlbId" + ulbid).value;
	//var url = "${pageContext.request.contextPath}"
	//		+ "/cdma/Trade/gettcategory/";

	var url = "${pageContext.request.contextPath}/getbcls.do";
	//alert(url);
	//var option = $('<option />').val("0").text("select");
	//var option = $('<option />');
	$("#bcls").append(option);
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
				$("#bcls").append(option);

			});
			//var soption = $('<option />').val("0").text("select");
			//$("#bcls").append(soption);
		},
		error : function() {
			var soption = $('<option />').val("0").text("select");
			$("#bcls").append(soption);
		}
	});
	//alert(">>>" + ulbid);
 	if (ulbid == 0||zone==0) {
	 	$("#bcls").empty();
		$("#bcls").append($('<option />').val("0").text("-Select-"));
		
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	  
	}
	return false;
}
//i_bldgusecode


function  getusage() {
	//alert(">>getusage");

	var ulbid=document.getElementById("taxUlb").value; 
	//alert(ulbid);
	//var soption = $('<option />').val("0").text("select");
	//$("#bcls").append(soption);
var option = $('<option />').val("0").text("select");
		$("#busage").empty();
	 
		var bcls=document.getElementById("bcls").value;
		 

	/* document.getElementById("tradeUlb").innerHTML = ulbid; */
	if (ulbid == 0 ||bcls==0) {
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	 
		 
		return false;
	}
	// var code=document.getElementById("searchUlbId" + ulbid).value;
	//var url = "${pageContext.request.contextPath}"
	//		+ "/cdma/Trade/gettcategory/";

	var url = "${pageContext.request.contextPath}/getusage.do";
	//alert(url);
	//var option = $('<option />').val("0").text("select");
	//var option = $('<option />');
	$("#busage").append(option);
	$.ajax({

		type : "POST",
		url : url,
		data : {
			//i_bldgusecode:i_bldgusecode,
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
				$("#busage").append(option);

			});
			//var soption = $('<option />').val("0").text("select");
			//$("#bcls").append(soption);
		},
		error : function() {
			var soption = $('<option />').val("0").text("select");
			$("#busage").append(soption);
		}
	});
	//alert(">>>" + ulbid);
 	if (ulbid == 0 ||bcls==0) {
		$("#busage").empty();
		$("#busage").append( $('<option />').val("0").text("-Select-"));
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
 
	} 
	return false;
}


//getocpnt
function  getocpnt() {
	var ulbid=document.getElementById("taxUlb").value; 
	//alert(ulbid);
	//var soption = $('<option />').val("0").text("select");
	//$("#bcls").append(soption);
var option = $('<option />').val("0").text("select");
		$("#octy").empty();
	 
		var busage=document.getElementById("busage").value;
 
	/* document.getElementById("tradeUlb").innerHTML = ulbid; */
	if (ulbid == 0 ||busage==0) {
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	 
		return false;
	}
	// var code=document.getElementById("searchUlbId" + ulbid).value;
	//var url = "${pageContext.request.contextPath}"
	//		+ "/cdma/Trade/gettcategory/";

	var url = "${pageContext.request.contextPath}/getocpnt.do";
	//alert(url);
	//var option = $('<option />').val("0").text("select");
	//var option = $('<option />');
	$("#octy").append(option);
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
				$("#octy").append(option);

			});
			//var soption = $('<option />').val("0").text("select");
			//$("#bcls").append(soption);
		},
		error : function() {
			var soption = $('<option />').val("0").text("select");
			$("#octy").append(soption);
		}
	});
	//alert(">>>" + ulbid);
 if (ulbid == 0 ||busage==0) {
		 
		$("#octy").empty();
		$("#octy").append( $('<option />').val("0").text("-Select-"));
	 
	}
	return false;
}

 

</script>
<div id="page"></div>
<div id="results"></div>
 
 
<input type="hidden" name="reqsearchDistrictId" id="reqsearchDistrictId"
	value='<c:out value="${requestScope.districtId}"/>'>
<input type="hidden" name="ulbSelected" id="ulbSelected"
	value='<c:out value="${requestScope.districtId}"/>'>

</body>
</html>		
		
	
	
	