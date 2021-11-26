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

	<title>Login</title>


</head>

<body>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
     	
	  
	<section class="vh-100 gradient-background ">
	  <form:form action="/bts/api/user/sign_in" method="POST" modelAttribute="user">
	  
	  <div class="container py-5 h-100">
	  
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
	        <div class="card bg-dark text-white" style="border-radius: 1rem;">
	          <div class="card-body p-5 text-center">
	
	            <div class="mb-md-5 mt-md-4 pb-5">
	
	              <h2 class="fw-bold mb-2 text-uppercase text-warning">Login</h2>
	              <p class="text-white-50 mb-5">Please enter your login and password!</p>
	
	              <div class="form-outline form-white mb-4">
	                <form:input type="email" id="email" class="form-control form-control-lg" path="email" />
	                <label class="form-label" for="email">Email</label>
	              </div>
	
	              <div class="form-outline form-white mb-4">
	                <form:input type="password" id="pwd" class="form-control form-control-lg" path="password" />
	                <label class="form-label" for="pwd">Password</label>
	              </div>
	
	              <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
	
	              <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
	
	              <div class="d-flex justify-content-center text-center mt-4 pt-1">
	                <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
	                <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
	                <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
	              </div>
	
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



