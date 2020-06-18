<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
%>
</head>
<%@page isELIgnored="false" %>
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
<link rel="stylesheet" href="<%=path%>/jsp/css/jquery-ui.css">
<script src="<%=path%>/jsp/css/jquery-1.9.1.js"></script>
<script src="<%=path%>/jsp/css/jquery-ui.js"></script>
<script type="text/javascript">
function getimage() {
alert("ur in");
	var url = "${pageContext.request.contextPath}/Test.do";
	$.ajax({

		type : "POST",
		url : url,
		data : {
			//ulbcode : ulbid
		},
		dataType : 'text',
		success : function(data) {
			//var array = data;
$("#test1").text(data);
	 	 alert("one"+data);

 		},
		error : function() {
			 alert("failure");
		}
	});
	
	var url = "${pageContext.request.contextPath}/Test1.do";
	$.ajax({

		type : "POST",
		url : url,
		data : {
			//ulbcode : ulbid
		},
		dataType : 'text',
		success : function(data) {
			//var array = data;
$("#test2").text(data);
		// alert("one"+data);
		alert("-->> "+text);
		},
		error : function() {
			 alert("failure");
		}
	});
	
	return false;
}
</script>
<body>

<button onclick="javascript:getimage()" value="submit">submit</button>
<p id="test1"></p>
<p id="test2"></p>
${requestScope.text} --
 <%=request.getAttribute("text") %>
 <%-- <%=session.getAttribute("text1") %> --%>
</body>
</html>