<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<title>Dtcp Report in PDF Format</title>
</head>
<body>
<div class="container">
		<div class="panel-heading"></div>
		<div class="panel-body">

			<form action="dtcpPdfReport.do" method="POST" commandName="id">
				<div class="row">
					<div class="col-md-3">
						<label class="control-label" for="districtId"> Enter ID :</label>
					</div>
					<div class="col-md-3">
						<input type="text" id="id" name="id">
									</div>
				</div><br>

				<div class="row">
					<div class="col-md-3">
					<label class="control-label" for="districtId"></label></div>
					 <div class="col-md-3">
					 
					<input type="submit" value="submit" >
					</div>
					</div>
					
				</form>
									</div>
					</div>
			
</body>
</html>
