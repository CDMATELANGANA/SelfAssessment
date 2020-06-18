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
	
	$(document).ready(function() {
	var fromDate = $("#fromDate").datepicker({
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
			"targets" : 0
		} ],
		"order" : [ [ 1, 'asc' ] ],
		"lengthMenu" : [ [  -1 ], [  "All" ] ]
	});
	var toDate = $("#toDate").datepicker({
		defaultDate : "+0w",
		//dateFormat : 'mm/dd/yy',
		dateFormat : 'yy/mm/dd',
		changeMonth: true,
        changeYear: true,
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
<script type="text/javascript">
$(document).ready(function() {
		var t = $('#gridview1').DataTable({
			"columnDefs" : [ {
				"searchable" : false,
				"orderable" : false,
				"targets" : 0
			} ],
			"order" : [ [ 1, 'asc' ] ],
			"lengthMenu" : [ [ -1 ], [ "All" ] ]
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
        legend:true,
        height:500,
        width:600,
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
      chart.draw(data, options);
    }
</script>


</head>
<body>
<c:set	var="ulb" value="0"></c:set> 



<div class="panel with-nav-tabs">
<div class="panel-heading">
<ul class="nav nav-tabs">
  <li style="width: 50%" class="active"  ><a data-toggle="tab" href="#home" style="text-align: center;">ULB Report</a></li>
  <li style="width: 50%"><a data-toggle="tab" href="#menu1" style="text-align: center;">Gateway Report</a></li>

</ul>
</div>
</div>

<a href="./ptselfAssessmentAllTransaction.do"><h3 style="color:red;">Click Here For Detail Transaction Report</h3></a>
<div style="background-color:#E6E6FA" class="col-md-12"><br>
<form  action="<%=path%>/ptselfAssessmentIntervalData.do" >
<div class="col-md-12" >
<%-- <input type="hidden" id="type" name="type" value="${type}"> --%>
<%-- <input type="hidden" id="ulbcode" name="ulbcode" value="${ulbcode}">  --%>
<div class="col-md-4">From Date:<input type="text" id="fromDate" name="fromDate" readonly="readonly" >
 </div>
<div class="col-md-4">To Date: <input type="text" id="toDate" name="toDate" readonly="readonly"></div>
<div class="col-md-4"> <input type="submit" value="submit"></div><br><br>
</div>
</form>
</div>
<br>
<br><br><hr>
<%-- <div>
<input type="hidden" id="newfromDate" name="newfromDate" value="${fromDate}">
<input type="hidden" id="newtoDate" name="newtoDate" value="${toDate}">
<c:set var="fDate" value="${fromDate}"/>
<c:set var="tDate" value="${toDate}"/>
</div>
 --%>
<div class="row">


<div class="tab-content" >
  <div id="home" class="tab-pane fade in active" >
				<div class="col-md-12" style="color: black" >
					<table id="ulbTable" class="display" cellspacing="0" width="100%" >
						<thead>
							<tr>
								<th style="text-align: center;">S.No</th>
								<th style="text-align: center;">ULB</th>
								<th style="text-align: center;">District</th>
								<th style="text-align: center;">Transactions</th>
								<th style="text-align: center;">Total Amount</th>

							</tr>
						</thead>

						<tbody>
<c:set	var="transaction" value="0"></c:set> 
<c:set	var="amount" value="0"></c:set>
							<c:forEach items="${message}" var="item">
								<tr>
								
									<td style="text-align: center;">${item[1]}</td>
									<td style="text-align: center;">${item[4]}</td>
									<td style="text-align: center;">${item[0]}</td>
									<%-- <td><a href="./selfMutationDetailULB.do?ulb=${item[3]}">${item[1]}</a></td> --%>
									<c:choose>
										<c:when test="${(fromDate != null) && (toDate != null)}">
											<td style="text-align: center;"><a
												href="./ptselfAssessmentIntervalData.do?fromDate=${fromDate}&&toDate=${toDate}">${item[2]}</a></td>
										</c:when>
										<c:otherwise>
											<td style="text-align: center;"><a href="./ptselfAssessmentDetailULB.do?ulb=${item[3]}">${item[1]}</a></td>
										<c:set	var="ulb" value="${ulb+item[3]}"></c:set>
										</c:otherwise>
									</c:choose>
                                    <td style="text-align: center;">${item[2]}</td>
								</tr>
							 <c:set	var="transaction" value="${transaction+item[1]}"></c:set> 
							 <c:set	var="amount" value="${amount+item[2]}"></c:set>
							
							</c:forEach>
							
						</tbody>
						<tfoot>
						<tr style="background-color: #f3f8fa">
						<th style="text-align: center; border: 1px solid #9ee4fe;"
						colspan="3">Total:</th>
						<th style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${transaction}</th>											
						<th style="text-align: center; border: 1px solid #9ee4fe; border-left: 0">${amount}</th>
						</tr></tfoot>
					</table>
				</div>


			</div>
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
        
          <div class="col-md-7" >
  
 <div id="piechart_3d"></div>
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