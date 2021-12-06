<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<%@ page import="com.db.bts.entity.Admin"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<title>Users' Transaction History</title>
</head>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable({searching: false});
} );
</script>
<body>
	<%
	HttpSession session1= request.getSession(false);
	Admin admin=null;
    if(session1!=null){
    	admin= (Admin) session1.getAttribute("admin");
    }%>
	<jsp:include page="header.jsp"></jsp:include>    
    
	<div style="margin-top: 20px; margin-left: 20px; margin-right: 20px; margin-bottom: 20px">
		<h4 class="box-title" style="text-align: center; color: black">VIEW TRANSACTION HISTORY</h4>
		<br><br>
		<section>
		    <div class=" container col-md-4 justify-content-center justify-items-center" style="text-align: center">
		    	
				  <div class="form-group">
				    <select id="field" class="form-control" >
				    	<option selected value="none">Select Search Criteria</option>
					  	<option value="name">Name</option>
					  	<option value="email">Email</option>
					  	<option value="streetAddress">Street Address</option>
					  	<option value="city">City</option>
					  	<option value="type">Transaction type</option>
					</select>
				  </div>
				  <input type="text" id="value" class="form-control" placeholder="Enter search value"/><br>
				  <a href='' onclick="this.href='http://localhost:9000/bts/transactions/search?value='+document.getElementById('value').value+'&field='+document.getElementById('field').value"><button class="btn btn-dark">Submit</button></a>
		
		    
		    </div>
   		</section>
    	
		<table id="example" class="table table-striped table-bordered display mt-4" style="width: 100%; color: black">
			<thead>
			    <tr>
			      <th class="th-sm">No.
			      </th>
			      <th class="th-sm">User name
			      </th>
			      <th class="th-sm">Transaction Date
			      </th>
			      <th class="th-sm">Amount
			      </th>
			       <th class="th-sm">Transaction type
			      </th>
			      <th class="th-sm">Commission Value
			      </th>
			      <th class="th-sm">Commission Type
			      </th>
			      <th class="th-sm">Issuer name
			      </th>
			      <th class="th-sm">Bitcoin
			      </th>
			      <th class="th-sm">Action
			      </th>
			      
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var="i" items="${transactionList}" varStatus="j">
			   <tr>
			      <td>${j.count}</td>
			      <td>${i.user.firstName} ${i.user.lastName}</td>
			      <td>${i.time}</td>
			      <td>${i.amount }</td>
			      <td>${i.type}</td>
			      <td>${i.commissionValue}</td>
			      <td>${i.commissionType}</td>
			      <td>${i.trader.firstName} ${i.trader.lastName}</td>
			      <td>${i.bitcoin}</td>
			      <td><a href="/bts/transactions/cancel?userId=${i.user.id}&traderId=<%=admin.getId()%>&transactionId=${i.id}"><button id="cancel" class="btn btn-dark" >Cancel</button></a></td>
			      
			      <!-- commented for the future work -->
			      <%-- <% if(session.getAttribute("transactionIdToCancel")!=null){
			      	int id = (int)session.getAttribute("transactionIdToCancel");
			      	request.setAttribute("cancelledId", id);	
			      }
			      %>
			      
			      <c:choose>
			      <c:when test="${i.id == cancelledId}">
			      	<td><button id="cancel" class="btn btn-dark" disabled="disabled" >Cancel</button></td>
			      </c:when>
			      <c:otherwise>
			      <td><a href="/bts/transactions/cancel?userId=${i.user.id}&traderId=${i.trader.id}&transactionId=${i.id}"><button id="cancel" class="btn btn-dark" >Cancel</button></a></td>
			      </c:otherwise>
			      </c:choose>
			       --%>
			    </tr>
			  </c:forEach>
			  </tbody>
			  <tfoot>
			   <tr>
			   	<th>No.
			      </th>
			      <th>User name</th>
			     <th>Transaction Date
			     </th>
			     <th>Amount
			     </th>
			     <th>Transaction type
			     </th>
			     <th>Commission Value
			     </th>
			     <th>Commission Type
			     </th>
			     <th>Issuer name</th>
			     <th>Bitcoin</th>
			     <th>Action</th>
			   </tr>
			  </tfoot>
			
		</table>
		
	</div>

</body>
    
<jsp:include page="footer.jsp"></jsp:include>
</html>
    
    
