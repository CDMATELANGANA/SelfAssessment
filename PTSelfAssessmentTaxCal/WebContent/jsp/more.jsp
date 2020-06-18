<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%String path=request.getContextPath(); %>
<%--    <%@taglib uri="/struts-bootstrap-tags" prefix="sb" %>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="http://code.jquery.com/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    
 <link rel="stylesheet" href="<%=path %>/jsp/gridcss/reset.css" />
<link rel="stylesheet" href="<%=path %>/jsp/gridcss/text.css" />
<link rel="stylesheet" href="<%=path %>/jsp/gridcss/960.css" />
<link rel="stylesheet" href="<%=path %>/jsp/gridcss/demo.css" />
 
<title>CDMA PROPERTY TAX</title>
<style type="text/css">
body{
background-color: #F0F8FF;
 }
 .heading 
 {
 position: center;
 background-color: red;
 text-align: center;
 
 }
 .content
 {
 text-align: center;
 height: 50px;
 width: 100px;
 }
 .row > div {
    background:#F0F8FF;
    border: 1px solid grey;
    
    
}
 
 
</style>

<style> 

	 .verticalLine {
	    border-left: thin solid white;
	    height: 60px;
	    widows: 100px;
	}
.remove_field
{
color: red;
}
	</style>
	
 <script type="text/javascript">
 function sum()
 {
	 alert("hai");
	 var length=document.getElementById("length").value;
	 alert(length);
	 var width=document.getElementById("width").value;
	 //alert(width);
	 var c= parseInt(length) + parseInt(width);
	 document.getElementById("plinth").value=c;
	 //alert(c);
	 document.getElementById("plinth").readOnly = true;
 }
  
  </script>
  
   <script type="text/javascript">
 function sum1()
 {
	 alert("hai");
	 var length=document.getElementById("length1").value;
	 alert(length);
	 var width=document.getElementById("width1").value;
	 //alert(width);
	 var c= parseInt(length) + parseInt(width);
	 document.getElementById("plinth1").value=c;
	 //alert(c);
	 document.getElementById("plinth1").readOnly = true;
 }
  
  </script>
  
	<script  >
	$(document).ready(function() {
		//alert("hai");
		 
	    var max_fields      = 10; //maximum input boxes allowed
	    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
	    var add_button      = $(".add_field_button"); //Add button ID
 
	    
	    var x = 1; //initlal text box count
	    $(add_button).click(function(e){ //on add input button click
	    	 
	        e.preventDefault();
	        if(x < max_fields){ //max input box allowed
	            x++; //text box increment
	            $(wrapper).append('<div>Length<input type="text" id="length1"   name="length1"/>width<input type="text" id="width1" name="width1"  onblur="sum1()"/>Plinth<input id="plinth1"  type="text" name="plinth1" /><a href="#" class="remove_field" >Remove</a></div>'); //add input box
	           
	        }
	    });
	   
	    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
	        e.preventDefault(); $(this).parent('div').remove(); x--;
	    })
	});
	
	</script>
</head>

<body>
   
<div class="input_fields_wrap">
    <button class="add_field_button">Add More Fields</button>
    <div>
 Length<input type="text" id="length" cssClass="textfield" name="length"> 
		 	width<input type="text"  id="width" cssClass="textfield" name="width"  onblur="sum()">
		 	Plinth<input type="text" id="plinth" cssClass="textfield" name="plinth">
    </div>
    
</div>

 <s:submit></s:submit>
 
 
          
 
 
 
</body>
</html>