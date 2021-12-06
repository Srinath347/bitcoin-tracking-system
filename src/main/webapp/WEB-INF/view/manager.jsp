<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<title>Bitcoin Tracking System</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').DataTable( {
            "paging":   true,
            "ordering": true,
            "info":     true
        } );

    $( "#datepickerFrom" ).datepicker({
        format: 'yyyy-mm-dd',
        showOn: "button",
        buttonImage: "<%=request.getContextPath() %>/image/icons8-calendar-48.png",
        buttonImageOnly: true,
        buttonText: "Select from date"
    });
    $( "#datepickerTo" ).datepicker({
            showOn: "button",
            buttonImage: "<%=request.getContextPath() %>/image/icons8-calendar-48.png",
            buttonImageOnly: true,
            buttonText: "Select to date"
        });
} );
</script>

<style>
body {

}

.manager-container {
    margin-top: 7rem;
    padding: 0rem 1.5rem;
}

.t-width{
    width: 60%;
}

.ui-datepicker-trigger {
    width: 2rem;
}

.submit-button {
    display: flex;
    align-items: flex-end;
}

.sub-container {
    margin-top: 4rem;
}

.sell {
    margin-left: 10rem !important;
}
.dataTables_filter {
    justify-content: flex-end;
    display: flex;
}
.pagination {
    justify-content: end;
}

.date-text {
    font-size: 11px;
    display: flex;
    align-items: center;
    color: #8b8b8b;
}
</style>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="manager-container">
	<div class="row">
       <div class='col-md-3'>
            <p class="mb-0">Date from: </p>
            <div class="d-flex">
                <input type="text" class="form-control" id="datepickerFrom">
            </div>
       </div>
       <div class='col-md-3'>
            <p class="mb-0">Date till: </p>
            <div class="d-flex">
                <input type="text" class="form-control" id="datepickerTo">
            </div>
       </div>
       <div class="submit-button ml-3">
            <a href='' onclick="this.href='http://localhost:9000/bts/transactions/time?from='+document.getElementById('datepickerFrom').value+'&to='+document.getElementById('datepickerTo').value"><button class="btn btn-dark">Submit</button></a>
       </div>
    </div>
        <div class="sub-container">
            <div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="d-flex">
                            <h4>BUY TRANSACTIONS</h4>
                            <span class="date-text">
                                <c:out value="${empty transactionStatistics.from ? '' : transactionStatistics.from}" />
                                <c:out value="${empty transactionStatistics.from ? '' : '-'}" />
                                <c:out value="${empty transactionStatistics.to ? '' : transactionStatistics.to}" />
                            </span>
                        </div>
                        <table class="table t-width">
                            <tbody class="font-weight-normal">
                                <tr>
                                    <td><span>Total transactions:</span></td>
                                    <td><span>
                                         <c:out value="${empty transactionStatistics.buy ? '-' : transactionStatistics.buy.count}" />
                                    </span></td>
                                </tr>
                                <tr>
                                    <td><span>Total amount:</span></td>
                                    <td><span>
                                        <c:out value="${empty transactionStatistics.buy ? '-' : transactionStatistics.buy.amount}" />
                                    </span></td>
                                </tr>
                                <tr>
                                   <td><span>Total commission:</span></td>
                                   <td><span>
                                        <c:out value="${empty transactionStatistics.buy ? '-' : transactionStatistics.buy.commission}" />
                                   </span></td>
                                </tr>
                            </tbody>
                       </table>
                    </div>
                    <div class="col-md-4 mb-4 sell">
                    <div class="d-flex">
                        <h4>SELL TRANSACTIONS</h4>
                            <span class="date-text">
                                <c:out value="${empty transactionStatistics.from ? '' : transactionStatistics.from}" />
                                <c:out value="${empty transactionStatistics.from ? '' : '-'}" />
                                <c:out value="${empty transactionStatistics.to ? '' : transactionStatistics.to}" />
                            </span>
                        </div>
                        <table class="table t-width">
                            <tbody class="font-weight-normal">
                                <tr>
                                    <td><span>Total transactions:</span></td>
                                    <td><span>
                                        <c:out value="${empty transactionStatistics.sell ? '-' : transactionStatistics.sell.count}" />
                                    </span></td>
                                </tr>
                                <tr>
                                    <td><span>Total amount:</span></td>
                                    <td><span>
                                        <c:out value="${empty transactionStatistics.sell ? '-' : transactionStatistics.sell.amount}" />
                                    </span></td>
                                </tr>
                                <tr>
                                    <td><span>Total commission:</span></td>
                                    <td><span>
                                        <c:out value="${empty transactionStatistics.sell ? '-' : transactionStatistics.sell.commission}" />
                                    </span></td>
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
    <div style="margin-top: 20px; margin-left: 20px; margin-right: 20px; margin-bottom: 20px">
    		<h4 class="box-title" style="text-align: center; color: black">TRANSACTIONS</h4>
    		<br><br>
    		<table id="example" class="table table-striped table-bordered display mt-4" style="width: 100%; color: black">
    			<thead>
    			    <tr>
    			      <th class="th-sm">No.
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
    			      <!-- <th class="th-sm">Issuer name
    			      </th> -->
    			      <th class="th-sm">Bitcoin
    			      </th>

    			    </tr>
    			  </thead>
    			  <tbody>
    			  <c:forEach var="i" items="${transactionStatistics.transactionList}" varStatus="j">
    			   <tr>
    			      <td>${j.count}</td>
    			      <td>${i.time}</td>
    			      <td>${i.amount }</td>
    			      <td>${i.type}</td>
    			      <td>${i.commissionValue}</td>
    			      <td>${i.commissionType}</td>

    			      <td>${i.bitcoin}</td>

    			    </tr>
    			  </c:forEach>
    			  </tbody>
    			  <tfoot>
    		        <!-- <tr>
            		    <th>No.</th>
            			<th>Transaction Date</th>
            			<th>Amount</th>
            			<th>Transaction type</th>
            			<th>Commission Value</th>
            			<th>Commission Type</th>
            		</tr> -->
    			  </tfoot>

    		</table>

    	</div>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>

</html>