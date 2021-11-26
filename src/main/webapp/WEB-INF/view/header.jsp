<header>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ page import="com.db.bts.entity.User"%>
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
	%>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
    <div class="container-fluid">
      <button
              class="navbar-toggler"
              type="button"
              data-mdb-toggle="collapse"
              data-mdb-target="#navbarExample01"
              aria-controls="navbarExample01"
              aria-expanded="false"
              aria-label="Toggle navigation"
      >
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarExample01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Transaction</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Account Details</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          </ul>
      </div>
    </div>
       
  <!-- Navbar -->
	<%
		if(member.equals("gold")){
	%>
		<div class="nav-item d-flex right-items mr-5">
              <a class="nav-link" href="#">
               <i class="fa fa-bitcoin" style="color:#FFD700; font-size:24px;"></i>
                   
              </a>
         </div>
        
	<%}else if(member.equals("silver")){ %>
	
		<div class="nav-item d-flex right-items mr-5">
              <a class="nav-link" href="#">
               <i class="fa fa-bitcoin" style="color:#BBC2CC; font-size:24px;"></i>
              </a>
        </div>
		
	<%}else{ %>
	
		<div class="nav-item d-flex right-items mr-5">
              <a class="nav-link" href="#">
               <i class="fa fa-bitcoin" style="color:#000000; font-size:24px;"></i>
                   
              </a>
        </div>
	<%} %>
	 
  </nav>
</header>