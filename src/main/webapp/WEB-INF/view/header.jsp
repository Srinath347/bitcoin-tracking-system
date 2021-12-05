<header class="header-margin">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ page import="com.db.bts.entity.User"%>
<%@ page import="com.db.bts.entity.Admin"%>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top header-color">
	<%	
	HttpSession session1= request.getSession(false);
	String member = "none";
		User user=null;
		if(session1!=null){
			user= (User) session1.getAttribute("user");
			if(user!=null){
				member = user.getMember().getName().toLowerCase();
			}else{
				member="none";
			}
		}
		else{
			member="none";
		}
		Admin admin=null;
		if(session1!=null){
			admin= (Admin) session1.getAttribute("admin");
		}
	  	  

	
	
  if(user!=null){	%>
  <!-- Navbar -->
  
    	  <div class="container-fluid">
      
      		<div class="collapse navbar-collapse" id="navbarExample01">
        	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        	<li class="nav-item">
           		<img alt="" src="<%=request.getContextPath() %>/image/logo.jpg">
          	</li>
          	<li class="nav-item active" style="padding-top: 30px">
            	<a class="nav-link" aria-current="page" href="/bts/api/home">Home</a>
          	</li>
  
  
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/user/<%=user.getId()%>">Profile</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/transactions/<%=user.getId()%>">Transaction</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/account/<%=user.getId()%>">Wallet</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/api/about">About</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/api/logout">Logout</a>
          </li>
          </ul>
      </div>
    </div>
    <%}
  else if(admin!=null)
  {
	  if(admin.getRole().getId()==2){	  
  	%>
  		
  
    	<div class="container-fluid">
      
      	<div class="collapse navbar-collapse" id="navbarExample01">
     	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
     	<li class="nav-item">
        	<img alt="" src="<%=request.getContextPath() %>/image/logo.jpg">
       	</li>
       	<li class="nav-item active" style="padding-top: 30px">
         	<a class="nav-link" aria-current="page" href="/bts/api/home">Home</a>
       	</li>
		  <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/admin/<%=admin.getId()%>">Profile</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/transactions/transactionHistory">Users' Transaction History</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/users/trader/<%=admin.getId()%>">Issue Transaction</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/payments/trader/<%=admin.getId()%>">Pending Payments</a>
          </li>
          <li class="nav-item" style="padding-top: 30px ">
            <a class="nav-link" href="/bts/api/about">About</a>
          </li>
          <li class="nav-item" style="padding-top: 30px">
            <a class="nav-link" href="/bts/api/logout">Logout</a>
          </li>
          </ul>
      </div>
    </div>
	
 <%
	  }else{%>
		  
		  <div class="container-fluid">
	      
	      	<div class="collapse navbar-collapse" id="navbarExample01">
	     	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
	     	<li class="nav-item">
	        	<img alt="" src="<%=request.getContextPath() %>/image/logo.jpg">
	       	</li>
	       	<li class="nav-item active" style="padding-top: 30px">
	         	<a class="nav-link" aria-current="page" href="/bts/api/home">Home</a>
	       	</li>
			  <li class="nav-item" style="padding-top: 30px">
	            <a class="nav-link" href="/bts/admin/<%=admin.getId()%>">Profile</a>
	          </li>
	          <li class="nav-item" style="padding-top: 30px ">
	            <a class="nav-link" href="/bts/api/about">About</a>
	          </li>
	          <li class="nav-item" style="padding-top: 30px">
	            <a class="nav-link" href="/bts/api/logout">Logout</a>
	          </li>
	          </ul>
	      </div>
	    </div>
		  
	  <%}
  }
  %>
       
  <!-- Navbar -->
	<%
		if(member.equals("gold")){
	%>
		<div class="nav-item d-flex right-items mr-5">
			<%=user.getFirstName()%>&nbsp;<%=user.getLastName() %>
              <a class="nav-link" href="#">
               <i class="fa fa-bitcoin" style="color:#FFD700; font-size:24px;"></i>
                   
              </a>
         </div>
        
	<%}else if(member.equals("silver")){ %>
		
		<div class="nav-item d-flex right-items navbar-nav me-auto mb-2 mb-lg-0">
			<%=user.getFirstName()%>&nbsp;<%=user.getLastName() %>
			<a class="nav-link" href="#">
              <i class="fa fa-bitcoin" style="color:#BBC2CC; font-size:24px;"></i>
            </a>
        </div>
		
	<%}else{ %>
	
		<div class="nav-item d-flex right-items mr-5">
              <%=admin.getFirstName()%>&nbsp;<%=admin.getLastName()%>
              <a class="nav-link" href="#">
               <i class="fa fa-bitcoin" style="color:#000000; font-size:24px;"></i>
              </a>
        </div>
	<%} %>
	 
  </nav>
</header>