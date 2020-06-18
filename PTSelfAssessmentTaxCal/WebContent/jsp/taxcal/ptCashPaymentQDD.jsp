<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/tiles-jsp.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tlds/spring-form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">
<title>CDMA Self Mutation Application</title>
<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js">
	
</script>
<style type="text/css">
.panel-heading {
	font-size: large;
}
</style>
</head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js">
        </script>
<script>

    $(document).ready(function() {
        function disableBack() { window.history.forward() }

        window.onload = disableBack();
        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    });

</script>
<body>
	<div class="col-md-12"> 
			<fieldset>
				<div class="panel panel-primary">
					<div class="panel-heading">
						Payment Information:
						<c:out value="${requestScope.UniqueNumber}" />
					</div>
					<form action="cashPaymentQDD.do" method="post" OnSubmit="checkAmount()">
					<div class="panel-body">
						<div class="panel-group">
						<c:forEach var="getUniqueData" items="${getUniqueData}"	varStatus="status">
						<div class="col-md-6">
							<label class="col-md-6 ">Name :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${getUniqueData[2]}" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<label class="col-md-6 ">Mobile Number :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${getUniqueData[4]}" />
									</div>
								</div>
							</div>
						<div class="col-md-6">
								<label class="col-md-6 ">Application No(17 digit):</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${getUniqueData[0]}" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<label class="col-md-6 ">OWNER DISTRICT :</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${getUniqueData[6]}" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<label class="col-md-6 ">Assessment Amount:</label>
								<div class="col-md-4 ">
									<div class="input-group">
										<c:out value="${getUniqueData[5]}" />
									</div>
								</div>
							</div>
							</c:forEach>
							
							
							<div class="col-md-12">
								<label class="col-md-4 "></label>
								<div class="col-md-4 ">
									<div class="input-group">
							<input type="submit" id="submit" class="btn btn-primary" value="Save">
							</div></div></div></div></div>
							
							<input type="hidden" name="feeamount" id="feeamount" value="<c:out value="${getUniqueData[5]}" />"/>
							<input type="hidden" name="enterAmount" id="enterAmount" />
							<input type="hidden" name="id" id="id" value="<c:out value="${requestScope.userId1}" />"/>
							<input type="hidden" name="UniqueNumber" id="UniqueNumber" value="<c:out value="${requestScope.UniqueNumber}" />"/>
							<input type="hidden" name="cheque_DD_No" id="cheque_DD_No"	value="<c:out value="${requestScope.cheque_DD_No}"/>">
<input type="hidden" name="cheque_DD_Date" id="cheque_DD_Date"	value="<c:out value="${requestScope.cheque_DD_Date}"/>">
<input type="hidden" name="cheque_DD_BankBranch" id="cheque_DD_BankBranch"	value="<c:out value="${requestScope.cheque_DD_BankBranch}"/>">
<input type="hidden" name="DDCheque" id="DDCheque"	value="<c:out value="${requestScope.DDCheque}"/>">
							
							</form></div>
			</fieldset>
			
</body>
</html>