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
	<title>About Page</title>
</head>

<style>
body {

}

.about-container {
    margin-top: 7rem;
    padding: 0rem 10.5rem;
}

.t-width{
    width: 60%;
}
</style>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="about-container">
        <div class="sub-container">
            <div>
                <div class="mb-5">
                    <h4>Bitcoin Tracking System</h4>
                    <p>
                    At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti.
                    Blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias.
                    </p>
                </div>
                <div class="row">
                    <div class="col">
                        <div>
                            <h4><FaMapMarkerAlt /> Address </h4>
                            <p class="mb-0">634 Park Avenue</p>
                            <p class="mb-0">Dallas</p>
                            <p class="mb-0">TX 75340</p>
                            <p class="mb-0">United States</p>
                        </div>
                        <div class="mb-3 mt-3">
                            <h4><FaPhoneAlt class="phone" /> Contact</h4>
                            <p class="mb-0">Phone: +1 (874) 536-7825</p>
                            <p class="mb-0">Email: bts@gmail.com</p>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <h4><FaElementor class="scheduler" /> Hours</h4>
                        <table class="table t-width">
                            <tbody class="font-weight-normal">
                                <tr>
                                    <td><span>Mon - Thu:</span></td>
                                    <td><span>8am - 9pm</span></td>
                                </tr>
                                <tr>
                                    <td><span>Fri - Sat:</span></td>
                                    <td><span>8am - 1am</span></td>
                                </tr>
                                <tr>
                                    <td><span>Sunday:</span></td>
                                    <td><span>9am - 10pm</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>

</html>