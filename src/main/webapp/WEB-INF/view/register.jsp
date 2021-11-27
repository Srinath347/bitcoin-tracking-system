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

	<title>Register</title>


</head>

<body>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
	<section class="login-background">
	  <form:form action="/bts/user" method="POST" modelAttribute="user">
	  <div class="container py-5 h-100">
	  
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
	        <div class="card bg-dark text-white" style="border-radius: 1rem;">
	          <div class="card-body p-5 text-center">
	
	            <div class="mb-md-5 mt-md-4 pb-5">
	
	              <h2 class="fw-bold mb-2 text-uppercase text-warning">Register</h2>
	              <h5 class="fw-bold mb-2 text-uppercase">Step 1 of 2</h5>
	              <p class="text-white-50 mb-5">Please enter your Details!</p>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="fname" class="form-control form-control-lg" path="firstName" required="required" />
	                <label class="form-label" for="fname">First Name</label>
	              </div>
				
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="lname" class="form-control form-control-lg" path="lastName" required="required" />
	                <label class="form-label" for="lname">Last Name</label>
	              </div>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="pnumber" class="form-control form-control-lg" path="phoneNumber" pattern=".{10}"  title="Please input valid phone number." required="required" />
	                <label class="form-label" for="pnumber">Phone Number</label>
	              </div>
	              
	              <div class="form-outline form-white mb-4">
	                <form:input type="text" id="cnumber" class="form-control form-control-lg" path="cellNumber" pattern=".{10}" title="Please input valid cell number."/>
	                <label class="form-label" for="cnumber">Cell Number</label>
	              </div>
	              <div class="form-outline form-white mb-4">
	                <form:input type="email" id="email" class="form-control form-control-lg" path="email" required="required" />
	                <label class="form-label" for="email">Email</label>
	              </div>
	
	              <div class="form-outline form-white mb-4">
	                <form:input type="password" id="pwd" class="form-control form-control-lg" path="password" required="required" />
	                <label class="form-label" for="pwd">Password</label>
	              </div>
	              
	              <div class="form-outline form-white mb-4">
	                <input type="password" id="confirmPwd" class="form-control form-control-lg" required="required" />
	                <label class="form-label" for="confirmPwd">Confirm Password</label>
	              </div>
	
	              <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
	
	              <button class="btn btn-outline-light btn-lg px-5" type="submit" onclick="return validatePwd()">Next step</button>
	
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
	
<script type="text/javascript">
    function validatePwd() {
        var password = document.getElementById("pwd").value;
        var confirmPassword = document.getElementById("confirmPwd").value;
        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>



