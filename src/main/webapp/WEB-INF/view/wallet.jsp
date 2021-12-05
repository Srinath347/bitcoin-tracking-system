<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Wallet</title>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
</head>
<script type="text/javascript">
document.getElementById('amount').value=null;

</script>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="footer-margin">
	  <%-- <form:form action="/bts/user" method="POST" modelAttribute="user"> --%>
	  <div class="container py-5 mt-4">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col col-lg-6 mb-4 mb-lg-0">
	        <div class="card mb-3" style="border-radius: .5rem;">
	          <div class="row g-0">
	            <div class="col-md-4 bg-dark text-center text-white" style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
	             <!--  <img
	                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.png"
	                alt="..."
	                class="img-fluid my-5"
	                style="width: 80px;"
	              /> -->
	           
	              <h5>${account.user.firstName}&nbsp;${account.user.lastName}</h5>
	              
	              <!-- <button class="edit-btn"><i class="fa fa-edit" ></i></button> -->
	     		  
	              
	            </div>
	            <div class="col-md-8">
	              <div class="card-body p-4">
	                <h6>Wallet Information</h6>
	                <hr class="mt-0 mb-4">
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>Current Balance in USD</h6>
	                    <p class="text-muted">${account.balance}</p>
	                  </div>
	                </div>
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>Current Bitcoin Balance</h6>
	                    <p class="text-muted">${account.bitcoin}</p>
	                  </div>
	                </div>
	                <%
						HttpSession session1= request.getSession(false);
	                	
						if(session1.getAttribute("added").equals("true")){%>
							<div class="form-outline form-white mb-4">
	                		<h6>Payment request sent successfully!!</h6>
	                		<h6><label class="form-label">Want to add amount again?</label></h6>
	                		<a href='http://localhost:9000/bts/account/${account.user.id}'><button class="btn btn-outline-dark btn-sm mt-2 px-5">Click here</button></a>
	              			</div>
						<%}else{%>
							<div class="form-outline form-white mb-4">
		                	<h6><label class="form-label" for="amount">Add amount</label></h6>
		                	<input type="text" id="amount" class="form-control form-control-lg" />
		                	<a href='' onclick="this.href='http://localhost:9000/bts/account/wallet/add?userId=${account.user.id}&amount='+document.getElementById('amount').value"><button class="btn btn-outline-dark btn-sm mt-2 px-5" type="submit">Add</button></a>
		              		</div>
						<%}
					%>
	                
	                
	
	                
	           
	              
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
<%--  	 </form:form> --%>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
