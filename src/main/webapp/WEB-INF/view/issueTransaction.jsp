<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Issue Transactions</title>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
</head>
<style>

</style>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<section class="mb-4">
	<div class="container col-md-6 py-5 mt-4">
	<form:form action="/bts/transactions/transaction" method="POST" modelAttribute="transaction">
	  
	  <div class="form-outline mb-4">
	  	
         
         <form:input type="hidden" path="roleId"/>
         <form:input type="hidden" path="traderId"/>
         
         
          <form:select id="user" class="form-control" path="userId">
          	<option value="" disabled selected hidden>Select user</option>
          	<c:forEach var="i" items="${usersList}">
	          	<option value=${i.id}>${i.firstName} ${i.lastName}</option>        
			</c:forEach>
          </form:select>
	  </div>
      <div class="form-outline mb-4">
	  	
         
          <form:select id="commissionType" class="form-control" path="commissionType">
          	<option value="" disabled selected hidden>Select option for commission payment</option>
          	<option value="bitcoin">Bitcoin</option>         
            <option value="fiat currency">Fiat currency</option>

          </form:select>
         
	  </div>
	
         
	  
	  
	   <div class="form-outline mb-4">
	    <form:input type="text" id="amount" class="form-control" placeholder="Enter bitcoins" path="bitcoin"/>
	  </div>
	
	
	  <div class="form-check buy-radio-button">
	    <form:radiobutton class="form-check-input" name="transactionType" id="buy" value="buy" path="type" checked="checked"/>
	  	<label class="form-check-label" for="buy">
	    	 BUY
	  	</label>
	  </div>
	  <div class="form-check sell-radio-button">
	  	<form:radiobutton class="form-check-input" name="transactionType" path="type" id="sell" value="sell"/>
	  	<label class="form-check-label" for="sell">
	    	SELL 
	  	</label>
	  
	  </div>

	  <!-- Submit button -->
	  <button type="submit" class="btn btn-dark btn-block mb-4">Submit</button>
	</form:form>
	</div>
 	</section>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src = "http://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
	<script src = "https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
	
	<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>