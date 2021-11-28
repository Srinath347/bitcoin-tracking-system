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

	<title>Profile</title>

	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section>
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
	           
	              <h5>${user.firstName}&nbsp;${user.lastName}</h5>
	              
	            <!--   <button class="edit-btn"><i class="fa fa-edit" ></i></button> -->
	     		  
	              
	            </div>
	            <div class="col-md-8">
	              <div class="card-body p-4">
	                <h6>Information</h6>
	                <hr class="mt-0 mb-4">
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>Email</h6>
	                    <%-- <form:input class="text-muted" type="text" path="email" id="email"/> --%><p class="text-muted">${user.email}</p>
	                  </div>
	                  <div class="col-6 mb-3">
	                    <h6>Phone</h6>
	                    <p class="text-muted">${user.phoneNumber}</p>
	                  </div>
	                </div>
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>Cell Number</h6>
	                    <p class="text-muted">${user.cellNumber}</p>
	                  </div>
	                  <div class="col-6 mb-3">
	                    <h6>Password</h6>
	                    <p class="text-muted"><a href="#">CHANGE</a></p>
	                  </div>
	                </div>
	                <h6>Address</h6>
	                <hr class="mt-0 mb-4">
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>Street Address</h6>
	                    <p class="text-muted">${address.street}</p>
	                  </div>
	                  <div class="col-6 mb-3">
	                    <h6>City</h6>
	                    <p class="text-muted">${address.city}</p>
	                  </div>
	                </div>
	                
	                <div class="row pt-1">
	                  <div class="col-6 mb-3">
	                    <h6>State</h6>
	                    <p class="text-muted">${address.state}</p>
	                  </div>
	                  <div class="col-6 mb-3">
	                    <h6>Zipcode</h6>
	                    <p class="text-muted">${address.zipcode}</p>
	                  </div>
	                </div>
	              
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
<%-- 	  </form:form> --%>
	</section>
	
</body>

</html>
