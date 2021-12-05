<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<title>Login</title>


</head>
<%
HttpSession session1= request.getSession(false);
session1.invalidate();
%>

<body>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top login-header">
  
    <div class="container-fluid">
      
      <div class="collapse navbar-collapse" id="navbarExample01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
           <img alt="" src="<%=request.getContextPath() %>/image/logo.jpg">
         </li>
        </ul>
      </div>
     </div>
     </nav>
     	
	  
	<section>
	  <form:form action="/bts/api/user/sign_in" method="POST" modelAttribute="user">
	  
	  <div class="container py-5 h-100">
	  
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
	        <div class="card bg-dark text-white" style="border-radius: 1rem;">
	          <div class="card-body p-5 text-center">
	
	            <div class="mb-md-5 mt-md-4 pb-5">
	
	              <h2 class="fw-bold mb-2 text-uppercase text-warning">Login</h2>
	              <p class="text-white-50 mb-5">Please enter your login and password</p>
	
				  <div class="form-check user-radio-button mr-5">
	              	<input class="form-check-input" type="radio" name="login_type" id="userLogin" value="user" checked>
				  	<label class="form-check-label" for="user">
				    	User 
				  	</label>
				  </div>
				  <div class="form-check radio-button mb-4">
				  	<input class="form-check-input" type="radio" name="login_type" id="adminLogin" value="admin">
				  	<label class="form-check-label" for="admin">
				    	Admin 
				  	</label>
				  
				  </div>
				
	              <div class="form-outline form-white mb-4">
	                <form:input type="email" id="email" class="form-control form-control-lg" path="email" />
	                <label class="form-label" for="email">Email</label>
	              </div>
	
	              <div class="form-outline form-white mb-4">
	                <form:input type="password" id="pwd" class="form-control form-control-lg" path="password" />
	                <label class="form-label" for="pwd">Password</label>
	              </div>
	
	              <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
					<input type="hidden" id="loggedIn" value="1">
	              <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>           
	
	            </div>
	
	            <div>
	              <p class="mb-0">Don't have an account? <a href="/bts/api/user/register" class="text-white-50 fw-bold">Register</a></p>
	            </div>
	
	          </div>

	        </div>
	      </div>
	    </div>
	  </div>
	  </form:form>
	</section>
	</body>

</html>



