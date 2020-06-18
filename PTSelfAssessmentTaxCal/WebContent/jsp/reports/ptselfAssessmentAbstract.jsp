<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%String path=request.getContextPath(); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript"
	src="<%=path%>/jsp/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="<%=path%>/jsp/css/bootstrap.3.3.7.min.css">

<link rel="stylesheet"
	href="<%=path%>/jsp/grid/css/jquery.dataTables.min.css">


<html>
<head>
<link rel="stylesheet" type="text/css" href="./jsp/css/dashboard.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
                pieHole: 0.5,
                pieSliceTextStyle: {
                  color: 'black',
                },
                legend: 'none'
              };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }

      
      $(document).ready(function() {
  		var t = $('#example').DataTable({
  			"columnDefs" : [ {
  				"searchable" : false,
  				"orderable" : false,
  				"targets" : 0
  			} ],
  			"order" : [ [ 1, 'asc' ] ],
  			"lengthMenu" : [ [  -1 ], [  "All" ] ]
  		});

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

<style type="text/css">

#donutchart,
#chart {
  width: 500px;
  height: 500px;
  font-family: Arial;
}

#donutchart {
    position: relative;
}

#labelOverlay {
    width: 90px;
    height: 45px;
    position: absolute;
    top: 233px;
    left: 205px;
    text-align: center;
    cursor: default;
}

#labelOverlay p {
  line-height: 0.3;
  padding:0;
  margin: 8px;
}

#labelOverlay p.used-size {
  line-height: 0.5;
  font-size: 20pt;
  color: #8e8e8e;
}

#labelOverlay p.total-size {
  line-height: 0.5;
  font-size: 12pt;
  color: red;
}

</style>


</head>

<body>

 <!--  <div id="donutchart" style="width: 900px; height: 500px;">
  <div id="chart"></div>
  <div id="labelOverlay">
    <p class="used-size">41<span>GB</span></p>
    <p class="total-size"> of 50GB</p>
  </div>
</div>  --> 


 
<c:if test="${empty abstractReport}"> 

<div class="row">
        <div class="col-sm-4 col-xs-4" style="border-right: 1px solid #f4f4f4">
        
                   <c:forEach var="value" items="${total}" varStatus="loopCount">
         
  <%--  <p>${value[0]}</p>
     <p>${value[1]}</p> --%>
          <!-- small box -->
          <div class="small-box bg-aqua" style="background: #00968A;color: #fff" >
            <div class="inner">
          <!--   &#8377; -->
              <h3> <i class="fa fa-inr"></i>${value[0]} <sup style="font-size: 15px">Lakhs</sup> </h3>  
  
              <p>PT Self Assessment <span style="float: right;"><sup style="font-size: 13px;font-family:sans-serif;">From 04-07-2019 </sup></span></p> 
              
            </div>
            <div class="icon" >
              <i class="ion ion-bag"></i>
            </div>
        <p align="center">
        <a href="./ptselfAssessmentDetail.do" class="small-box-footer" ><font color="black">More info <i class="fa fa-arrow-circle-right"></i></font></a></p>
          </div>
          <p>&nbsp;&nbsp;</p>
          <p>&nbsp;&nbsp;</p>
          <p>&nbsp;&nbsp;</p>
        </div>
        <!-- ./col -->
       
      <%--  <h3> &#8377; <fmt:formatNumber value=" ${value[0]}" pattern="0.00"/> <sup style="font-size: 15px">Lakhs</sup></h3>
      --%>
            
        <!-- ./col -->
 
       
     <%--     <div class="col-lg-3 col-xs-3">
          <!-- small box -->
          <div class="small-box" style="background-color: #00968A;color: #fff">
            <div class="inner" align="center">
               <h3> &#8377; <fmt:formatNumber value="${value[0]}" pattern="0.00"/> <sup style="font-size: 15px">Lakhs</sup></h3>
 
              <p align="center">Total</p>
            </div>
            <div class="icon">
              <i class="ion ion-stats-bars"></i>
            </div>
            <a href="#" class="small-box-footer">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div> --%>
        </c:forEach>
      </div>
</c:if>

 </body>
 <script src="<%=path%>/jsp/grid/js/jquery-1.12.4.js"></script>
<script src="<%=path%>/jsp/grid/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" href="<%=path%>/jsp/datepicker/jquery-ui.css">
<script src="<%=path%>/jsp/datepicker/jquery-ui.js"></script>
</html>