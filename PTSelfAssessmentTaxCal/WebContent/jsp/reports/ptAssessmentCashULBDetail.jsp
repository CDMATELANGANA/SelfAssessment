<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript"
	src="../jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">
	
	  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	  
	  <link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/dist/css/AdminLTE.min.css">
	  
<link rel="stylesheet" href="https://adminlte.io/themes/AdminLTE/dist/css/skins/_all-skins.min.css">

<script type="text/javascript">
google.charts.load("current", {packages:["corechart"]});
	    google.charts.setOnLoadCallback(drawChart);
	    function drawChart() {
	    	var bd=${gateway[0][2] + 0};
	    	var hdfc=${gateway[1][2] + 0};
	    	var tw=${gateway[2][2] + 0};
	      var data = google.visualization.arrayToDataTable([
	        ['Gateway', 'Collection'],
	        ['BILL DESK',   bd],
	        ['HDFC',   hdfc],
	        ['TWALLET',   tw],       
	      ]);
	      var options = {
	    	        title: 'Gateway wise details ',
	    	        is3D: true,
	    	        height:450,
	    			 width:450,
	    	        }; 
 var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
	      chart.draw(data, options);
	    }
	</script>
<script type="text/javascript">
	
	$(document).ready(function() {
	var fromDate = $("#fromDate").datepicker({
		defaultDate : "+0w",
		//dateFormat : 'mm/dd/yy',
		dateFormat : 'yy/mm/dd',
		changeMonth: true,
        changeYear: true,
		
	});
	var toDate = $("#toDate").datepicker({
		defaultDate : "+0w",
		//dateFormat : 'mm/dd/yy',
		dateFormat : 'yy/mm/dd',
		changeMonth: true,
        changeYear: true,
	});

	 var t = $('#ulbTable').DataTable({
		"columnDefs" : [ {
			"searchable" : false,
			"orderable" : false,
			"destroy": true,
			"targets" : 0
			
		} ],
		"order" : [ [ 1, 'asc' ] ],
		//"lengthMenu" : [ [  -1 ], [  "All" ] ]
		"lengthMenu" :[ [10,15, 20, -1 ],
						 [10,15, 20, "All" ] ]
	});//.fnDestroy();
	
	t.on('order.dt search.dt', function() {
		t.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();
	
	});
</script>
<script type="text/javascript">
t.destroy();
	$(document).ready(function() {
		 var t = $('#ulbTable').DataTable({
			"columnDefs" : [ {
				"searchable" : false,
				"orderable" : false,
				"destroy": true,
				"targets" : 0
			} ],
			"order" : [ [ 1, 'asc' ] ],
			//"lengthMenu" : [ [ -1 ], [ "All" ] ]
			"lengthMenu" :[ [10,15, 20, -1 ],
							 [10,15, 20, "All" ] ]
		});//fnDestroy();
		
		t.on('order.dt search.dt', function() {
			t.column(0, {
				search : 'applied',
				order : 'applied'
			}).nodes().each(function(cell, i) {
				cell.innerHTML = i + 1;
			});
		}).draw();
		
	});
</script>
</head>
<body>
<%
String type = request.getParameter("type");
%>

<c:set var="nm" value="<%=type %>"/>


<div class="container" >



<!-- <div class="panel with-nav-tabs">
<div class="panel-heading">
<ul class="nav nav-tabs">
  <li style="width: 50%" class="active" ><a data-toggle="tab" href="#home" style="text-align: center;">ULB Report</a></li>
  <li style="width: 50%"><a data-toggle="tab" href="#menu1" style="text-align: center;">Gateway Report</a></li>

</ul>
</div>
</div> -->
  <c:forEach items="${detailReport}" var="item" varStatus="index">
<c:set	var="ulb" value="${item[6]}"></c:set>
</c:forEach>
<!-- <div style="background-color:#E6E6FA" class="col-md-12"><br><br> -->
<form action="<%=path%>/ptSelfAssesmentCashTransDtlsByDate.do">
<div class="col-md-12">
<%-- <input type="hidden" id="type" name="type" value="${type}"> --%>
<%-- <input type="hidden" id="ulb" name="ulb" value="${ulb}"> --%>
 <div class="col-md-4">From Date:<input type="text" id="fromDate" name="fromDate" readonly="readonly" >
</div>
<div class="col-md-4">To Date: <input type="text" id="toDate" name="toDate" readonly="readonly"></div>

<div class="col-md-4"> <input type="submit" value="submit"></div><br><br>  
</div>
</form>
<!-- </div> -->
<br>
<br>
<div class="row">
<div class="tab-content">



 <c:choose>
<c:when test="${(nm == 'wtax')||(nm == 'trade')||(nm == 'tradeRenw')||(nm == 'adv')}">
  <div id="home" class="tab-pane fade in active">
   <div class="col-md-12" style="color: black">
   
<table id="ulbTable" class="display" cellspacing="0" >

        <thead>
            <tr>
             <th>S.No</th>
             <th>Unique Request No</th>
              <th>Transaction Id</th>
                <!-- <th>Paid Year</th> -->
                <th>Amount</th>
                <th>Date-Time</th>
                <th>Owner name</th>
                <th>Door No</th>
                </tr>
        </thead>
       
        <tbody>
        
        <c:forEach items="${detailReport}" var="item" varStatus="index">
            <tr>
            <td>${index.count}</td>
             <td>${item[0]}</td>
              <td>${item[1]}</td>
                <td>${item[2]}</td>
                  <td>${item[3]}</td>
                  <td>${item[4]}</td>
                   <td>${item[5]}</td>
                    <%-- <td>${item[6]}</td> --%>
               </tr>
          </c:forEach>
        </tbody>
    </table>   


</div>
   
   
  </div>
 </c:when>
 <c:otherwise>
 <div id="home" class="tab-pane fade in active">
   <div class="col-md-12" style="color: black">
   
<table id="ulbTable" class="display" cellspacing="0" title="${date_interval}" >
        <thead>
            <tr>
             <th style="text-align: center;">S.No</th>
             <th style="text-align: center;">Request No.</th>
             <th style="text-align: center;">AssessmentNo.</th>
              <th style="text-align: center;">Cash Trans Id</th>
                <th style="text-align: left;">Amount</th>
                <th style="text-align: center;">Transaction Date</th>
                <th style="text-align: center;">DoorNo.</th>
                <th style="text-align: center;">Owner name</th>
                <!-- <th style="text-align: center;">Door No</th> -->
                <th style="text-align: center;">Counter Id</th>
                
            </tr>
        </thead>
       
        <tbody>
        <c:set	var="ulb" value="0"></c:set>

        <c:forEach items="${detailReport}" var="item" varStatus="index">
            <tr>
            <td style="text-align: center;">${index.count}</td>
             <td style="text-align: center;">${item[0]}</td>
             <td style="text-align: center;">${item[1]}</td>
              <td style="text-align: center;">${item[2]}</td>
                <td style="text-align: center;">${item[3]}</td><c:set	var="amount" value="${amount+item[2]}"></c:set>
                  <td style="text-align: center;">${item[4]}</td>
                  <td style="text-align: center;">${item[5]}</td>
                   <td style="text-align: center;">${item[6]}</td>
                   <%-- <td style="text-align: center;">${item[5]}</td> --%>
                   <td style="text-align: center;">${item[9]}</td>
                    <%--  <c:set	var="ulb" value="${item[6]}"></c:set> --%>
               </tr>
          </c:forEach>
        </tbody>
        <tfoot>
        <tr style="background-color: #f3f8fa">
						<th style="text-align: center; "
						colspan="3">Total:</th>
						<th style="text-align: center; "  >${amount}</th>
						<th style="text-align: left;  border-left: 0" ></th>
						<th style="text-align: left; border-left: 0" ></th>
						<th style="text-align: left;  border-left: 0" ></th>
						<th style="text-align: left;  border-left: 0" ></th>
						</tr></tfoot>
    </table>   


</div>
   
   
  </div>
</c:otherwise>
 </c:choose>
  <div id="menu1" class="tab-pane fade">
  <div class="row" >
  <div class="col-md-5">
          <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user-2">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-yellow" style="padding: 6px">
         
              <h3 class="widget-user-username">Gatewaywise Abstract </h3>
                      
            </div>
             <p>(Amount in Lakhs)</p>
        
            <div class="box-footer no-padding">
              <ul class="nav">
               <li><span class="col-md-4">GATEWAY</span> <span class="col-md-4">TRANSACTIONS</span> <span class="col-md-4">AMOUNT</span></li>
                 <li><span class="col-md-4">${gateway[0][0]}</span> <span class="col-md-4">${gateway[0][1]}</span> <span class="col-md-4">${gateway[0][2]}</span></li>
                <li><span class="col-md-4">${gateway[1][0]}</span>  <span class="col-md-4">${gateway[1][1]}</span> <span class="col-md-4">${gateway[1][2]}</span></li>
                <li><span class="col-md-4">${gateway[2][0]}</span> <span class="col-md-4">${gateway[2][1]}</span> <span class="col-md-4">${gateway[2][2]}</span></li>
                <li><span class="col-md-4">Total</span> <span class="col-md-4">${gateway[0][1] + gateway[1][1]+ gateway[2][1] }</span>  <span class="col-md-4">${gateway[0][2] + gateway[1][2] + gateway[2][2]}  </span></li>
              </ul>
            </div>
          </div>
          <!-- /.widget-user -->
        </div>
        
          <div class="col-md-8 col-xs-offset-6" >
  
<div id="piechart_3d" style="width: 900px; height: 500px;"></div>

 
  </div>
  
</div>
</div>
 </div>
</div>


</div>
</body>


<script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>
</html>