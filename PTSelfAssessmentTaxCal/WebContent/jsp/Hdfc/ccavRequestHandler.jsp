<%@page import="java.net.URLEncoder"%>
<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<form method="post" name="customerData" action="ccavRequestHandler.jsp">
<table width="50%" height="100" border='1' align="center">
				<tr>
					<td>Parameter Name:</td><td>Parameter Value:</td>
				</tr>
				<tr>
					<td colspan="2"> Compulsory information</td>
				</tr>
				<tr>
                    <td>Tid</td>
                    <td><input readonly="readonly" type="text" name="tid" id="tid" value=""/></td>
                </tr>
				<tr>
					<td>Merchant Id	:</td><td><input type="text" name="merchant_id" value="247501"/></td>
				</tr>
				<tr>
					<td>Order Id	:</td><td><input type="text" name="order_id" value="123654789"/></td>
				</tr>
				<tr>
					<td>Amount	:</td><td><input type="text" name="amount" value="6000.00"/></td>
				</tr>
				<tr>
					<td>Currency	:</td><td><input type="text" name="currency" value="INR"/></td>
				</tr>
				<tr>
					<td>Redirect URL	:</td><td><input type="text" name="redirect_url" value="http://192.168.0.89:8080/ccavResponseHandler.asp"/></td>
				</tr>
			 	<tr>
			 		<td>Cancel URL	:</td><td><input type="text" name="cancel_url" value="http://192.168.0.89:8080/ccavResponseHandler.asp"/></td>
			 	</tr>
			 	<tr>
					<td>Language	:</td><td><input type="text" name="language" value="EN"/></td>
				</tr>
		     <tr>
		        	<td></td><td><INPUT TYPE="submit" value="CheckOut"></td>
		        </tr>
	      	</table>
</form>
	<%
	  
	 /* String accessCode= "AVVU90HB30AF89UVFA";		//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey = "5FAA679860E5EA5F2E335EA09E83CC66";    //Put in the 32 Bit Working Key provided by CCAVENUES. 
	 // String merchantId = request.getParameter("merchant_id"); 
	  String encRequest=request.getParameter("encRequest");
	 String accessCode=request.getParameter("access_code"); 
	 System.out.println("hi");
	 Enumeration enumeration=request.getParameterNames();
	  String encRequest="",pvalue="", merchant_id="",order_id="",amount="",currency="",redirect_url="",cancel_url="",language="";
	 while(enumeration.hasMoreElements()){
		 encRequest=""+ enumeration.nextElement();
		 pvalue= request.getParameter(encRequest);
		 pvalue=request.getParameter(accessCode);
		 /* pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest);
		 pvalue= request.getParameter(encRequest); 
		  
		 System.out.println(pvalue);
	 }  
		 */ 
	 String accessCode = "AVVU90HB30AF89UVFA";	// Put in the Access Code provided by CCAVENUES
	 String workingKey = "5FAA679860E5EA5F2E335EA09E83CC66";    // Put in the Working Key provided by CCAVENUES								 
	                                                            
 	 Enumeration enumeration=request.getParameterNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = request.getParameter(pname);
	      ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
	 }
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest); 
	%>
	 
	<!--  Enumeration enumeration=request.getParameterNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = request.getParameter(pname);
	      ccaRequest = ccaRequest + pname + "=" + URLEncoder.encode(pvalue,"UTF-8") + "&";
	 }
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>
	 -->
	 
	%>
	<form id="nonseamless" method="post" name="redirect" action="https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction"/> 
		<input type="hidden" id="encRequest" name="encRequest" value="<%= encRequest %>">
		<input type="hidden" name="access_code" id="access_code" value="<%= accessCode %>">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
	
 </body> 
</html>
