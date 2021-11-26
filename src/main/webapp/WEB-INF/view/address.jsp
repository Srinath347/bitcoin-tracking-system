<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Address Details</title>


</head>

<body>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
	<section class="vh-100 login-background">
	  <form:form action="/bts/address" method="POST" modelAttribute="address">
	  <div class="container py-5 h-100">
	  	<form:input type="hidden" id="user" path="user"/>
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
	        <div class="card bg-dark text-white" style="border-radius: 1rem;">
	          <div class="card-body p-5 text-center">
	
	            <div class="mb-md-5 mt-md-4 pb-5">
	
	              <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
	              <h5 class="fw-bold mb-2 text-uppercase">Step 2 of 2</h5>
	              <p class="text-white-50 mb-5">Enter address details</p>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="street" class="form-control form-control-lg" path="street" required="required" />
	                <label class="form-label" for="street">Street address</label>
	              </div>
				
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="city" class="form-control form-control-lg" path="city" required="required" />
	                <label class="form-label" for="city">City</label>
	              </div>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="state" class="form-control form-control-lg" path="state" required="required" />
	                <label class="form-label" for="state">State</label>
	              </div>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="zipcode" class="form-control form-control-lg" path="zipcode" />
	                <label class="form-label" for="zipcode">Zipcode</label>
	              </div>
	              
	             
	
	              <button class="btn btn-outline-light btn-lg px-5" type="submit" >Register</button>
	
	              <div class="d-flex justify-content-center text-center mt-4 pt-1">
	                <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
	                <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
	                <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
	              </div>
	
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



