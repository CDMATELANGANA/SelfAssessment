<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>New Report</title>
</head>
<body>
<div class="container">
		<div class="panel-heading"></div>
		<div class="panel-body">

			<form action="dtcpData.do" method="POST" commandName="dtcp">
				<div class="row">
					<div class="col-md-3">
						<label class="control-label" for="districtId"> Select
							District Name :</label>
					</div>
					<div class="col-md-3">
						<select name="district_id" class="form-control" id="district_id"
							onchange="getName()">
							<!--  <select name="userId" class="form-control" id="userId"  > -->
							<option>select</option>
							<c:forEach var="udist" items="${dist}">
								<option value="${udist[0]}">${udist[1]}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3">
						<label class="control-label" for="ulbName"> Select ULB
							Name :</label>
					</div>
					<div class="col-md-3">
						<select name="ulbName" class="form-control" id="ulbName"><!-- onchange="getId() -->
							<option value="0">select</option>
						</select>

					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
					 
					<input type="submit" value="Fetch" >
					</div></div>
					
				</form>
				<div class=" panel-body ">

					<div class="table-responsive" align="center">
						<table id="table"
							class="table  table-striped table-bordered table-hover"
							style="color: #00000F;">
							<thead>
							  <tr style="background-color: #b9e9ff; font-size: 13px">
									<th align="center" style="width: 45px;">id</th>
									<th align="center" class="col-md-4 text-center">dtcpfilenumber</th>
									<th align="center" class="col-md-2">aadharnumber</th>
									<th align="center" class="col-md-2">emasRegNo</th>
									<th align="center" class="col-md-2">entry_date</th>
										<th align="center" class="col-md-2">length</th>
									<th align="center" class="col-md-2">localitycode</th>
									<th align="center" class="col-md-2">mobilenumber</th>
									<th align="center" class="col-md-2">occupant_type</th>
									<th align="center" class="col-md-2">occupantname</th>
									<th align="center" class="col-md-2">occupantsurname</th>
									<th align="center" class="col-md-2">ownercity</th>
									<th align="center" class="col-md-2">ownername</th>
									<th align="center" class="col-md-2">ownershiptype</th>
									<th align="center" class="col-md-2">relationname</th>
									<th align="center" class="col-md-2">relationsurname</th>
									<th align="center" class="col-md-2">revenue_ward_code</th>
									<th align="center" class="col-md-2">street_name_code</th>
									<th align="center" class="col-md-2">surname</th>
									<th align="center" class="col-md-2">ulbcode</th>
									<th align="center" class="col-md-2">width</th>
									<th align="center" class="col-md-2">zonecode</th>
								
								</tr>  
							</thead>
							<tbody>
							 <c:forEach var="dtcp" items="${dtcp}" varStatus="valCount"> 
							 <tr style="font-size: 13px; color: #00000F;">
							 <!--  <th align="center" style="width: 45px;">id</th> -->
									<%-- <td align="center" class="col-md-4 text-center">${dtcp.dtcpfilenumber}</td> --%>
									<td align="center" class="col-md-2">${dtcp[0]}</td>
									<td align="center" class="col-md-2"><a href="http://localhost:8080/Taxcal/selfAssmentData.do?id=${dtcp[1]}">${dtcp[1]}</a></td>
									<td align="center" class="col-md-2">${dtcp[2]}</td>
									<td align="center" class="col-md-2">${dtcp[3]}</td>
									<td align="center" class="col-md-2">${dtcp[4]}</td>
									<td align="center" class="col-md-2">${dtcp[5]}</td>
									<td align="center" class="col-md-2">${dtcp[6]}</td>
									<td align="center" class="col-md-2">${dtcp[7]}</td>
									<td align="center" class="col-md-2">${dtcp[8]}</td>
									<td align="center" class="col-md-2">${dtcp[9]}</td>
									<td align="center" class="col-md-2">${dtcp[10]}</td>
									<td align="center" class="col-md-2">${dtcp[11]}</td>
									<td align="center" class="col-md-2">${dtcp[12]}</td>
									<td align="center" class="col-md-2">${dtcp[13]}</td>
									<td align="center" class="col-md-2">${dtcp[14]}</td>
									<td align="center" class="col-md-2">${dtcp[15]}</td>
									<td align="center" class="col-md-2">${dtcp[16]}</td>
									<td align="center" class="col-md-2">${dtcp[17]}</td>
									<td align="center" class="col-md-2">${dtcp[18]}</td>
									<td align="center" class="col-md-2">${dtcp[19]}</td>
									<td align="center" class="col-md-2">${dtcp[20]}</td>
									<td align="center" class="col-md-2">${dtcp[21]}</td>  
							 </c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				</div>
		
		</div>
	</div>


	
	<script>
		function getName() {

			var distid=$("#district_id").val();
			var url = "http://localhost:8080/Taxcal/getUlbcode.do";
			alert(distid);
			//option = $('<option />').val("0").text("select");
			//$("#ulbName").append(option);
			$("#ulbName").empty();
			$("#ulbName").append($('<option />').val("0").text("-Select-"));
			$.ajax({

				type : "POST",
				url : url,
				data : {

					d : distid
				},
				 
				dataType : 'json',

				success : function(data) {
					var array = data;
					 

					$.each(array, function(i, elem) {

						option = $('<option />').val(i).text(elem);
						$("#ulbName").append(option);

					});

				},
				error : function() {
					option = $('<option />').val("0").text("select");
					$("#ulbName").append(option);
					alert("error");
				}
			});
			return false;
		}
	</script>
	<script>
		function getId() {
			
			var ulbId = $("#ulbName").val();
			alert("ulbid" + ulbId);
			var url = "http://localhost:8081/Taxcal/dtcpData.do";
						
			option = $('<option />').val("0").text("select");
			$("#ulbName").append(option);
			$.ajax({

				type : "POST",
				url : url,
				data : {

					uid : ulbId
				}});
				/* },
				 
				dataType : 'json',

				success : function(data) {
					/* if(data!=0)
						{ */
					//var array = data;
					 
							/*	$.each(data, function(key, value) {
					      var tr = $("<tr />")
					     $.each(value, function(k, v) {
					       tr.append(
					         $("<td />", {
					           html: v
					          })[0].outerHTML
					       );
					      $("table tbody").append(tr)
					     });
					   });/* }else {
						   $("#table tbody").empty();
					   } */
				
					/*	},
				error : function() {
					alert("error");
				}
			});
 */			return false;
		}
					
					
	</script>

</body>
</html>