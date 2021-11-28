<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<title>Home Page</title>
	<%@ page import="com.db.bts.entity.User"%>
	<%@ page import="com.db.bts.entity.Admin"%>
	<%	
	HttpSession session1= request.getSession(false);
		User user=null;
		Admin admin = null;
		if(session1!=null){
			if(session1.getAttribute("user")!=null){
				
				user= (User) session1.getAttribute("user");
				
			}
			if(session1.getAttribute("admin")!=null){
				
				admin = (Admin) session1.getAttribute("admin");
			}
		}
	%>
			


</head>
<style>
body {
    background-image: url('<%=request.getContextPath()%>/image/bitcoin-background1.jpg');
    
}
.mask{
	margin-top: 58px;
}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	

	
<!-- Background image -->
	  <%-- <div
	          class="p-5 text-center bg-image"
	          style="
	          background-image: url('<%=request.getContextPath() %>/image/bitcoin-background.jpg');
	          height: 100%;
	          margin-top: 58px;
	        "
	  > --%>
	    <div class="mask" style="background-color: rgba(0, 0, 0, 0); height: 100%">
	      <div class="d-flex justify-content-left align-items-center pl-5 pt-4">
	        <div class="text-white">
	          <h1 class="mb-3">Welcome</h1>
	          <% if(user!=null){ %>
	          <h4 class="mb-3"><%=user.getFirstName() %>&nbsp;<%=user.getLastName() %></h4>
	          <%} if(admin!=null){ %>
	          <h4 class="mb-3"><%=admin.getFirstName() %>&nbsp;<%=admin.getLastName() %></h4>
	          <%} %>
	          <a class="btn btn-outline-light btn-lg" href="#" role="button">See Details</a>
	        </div>
	      </div>
	    </div>
	  <!-- </div> -->
	  <!-- Background image -->
  </body>
  </html>