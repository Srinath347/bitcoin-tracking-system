<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Pending Payments</title>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
</head>
<style>

</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').DataTable( {
        "paging":   true,
        "ordering": true,
        "info":     true
    } );
} );
</script>



 					<div style="margin-top: 100px; margin-left: 20px; margin-right: 20px; margin-bottom: 50px">
 					<h4 class="box-title" style="text-align: center; color: black">VIEW PENDING PAYMENTS LIST</h4>
					<br><br>
					<table id="example" class="table table-striped table-bordered display mt-4" style="width: 100%; color: black">
						<thead>
						    <tr>
						      <th class="th-sm">No.
						      </th>
						      <th class="th-sm">User Name
						      </th>
						      <th class="th-sm">Amount
						      </th>
						       <th class="th-sm">Time
						      </th>
						      <th class="th-sm">Action
						      </th>
						      
						    </tr>
						  </thead>
						  <tbody>
						  <c:forEach var="i" items="${paymentList}" varStatus="j">
						   <tr>
						      <td>${j.count}</td>
						      <td>${i.user.firstName}<%out.println(" ");%>${i.user.lastName}</td>
						      <td>${i.amount}</td>
						      <td>${i.time}</td>
						      <td><a href="http://localhost:9000/bts/payment/status?paymentId=${i.pid}&traderId=${i.trader.id}&status=accepted"><button id="accept" class="btn btn-dark">Accept</button></a>
						      &nbsp<a href="http://localhost:9000/bts/payment/status?paymentId=${i.pid}&traderId=${i.trader.id}&status=rejected"><button id="reject" class="btn btn-dark">Reject</button></a></td>
						  
						    </tr>
						  </c:forEach>
						  </tbody>
						  <tfoot>
						   <tr>
						   	<th>No.
						      </th>
						     <th>User name
						     </th>
						     <th>Amount
						     </th>
						     <th>Time
						     </th>
						     <th>Action
						     </th>
						   </tr>
						  </tfoot>
						
					</table>
					</div>
		
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src = "http://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
	<script src = "https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
	
	<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>