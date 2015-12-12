<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<html lang="en"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Welcome to fairSplit</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/smoothscroll.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath() %>/bootstrap/css/agency.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
<script>
	function ValidateFields(){
		var userName = document.getElementById("reguserName").value;
		var passWord = document.getElementById("regpassWord").value;
		var email = document.getElementById("regemail").value;
		var rePassWord = document.getElementById("re-passWord").value;
		//alert('called');
		if(null== userName || userName ==''){
			alert('Enter Username');
			return false;
		}
		if(null== passWord || passWord ==''){
			alert('Enter Password');
			return false;
		}
		if(null== email || email ==''){
			alert('Enter email');
			return false;
		}
		if(null== rePassWord || rePassWord ==''){
			alert('Re enter password');
			return false;
		}
		
		if(passWord != rePassWord){
			alert("Passwords do not match");
			return false;
		}
		return true;
		
	}
</script>
</head>
<body>

<%
	if(null != request.getAttribute(CommonConstants.ERR_LOGIN_ERR)){
%>
	<div class="alert-box error">
	<span>Error!</span><h4> <%=CommonConstants.ERMSG_LOGIN_MSG%></h4>
	</div>
<%
	}
%>
<%
	if(null != request.getAttribute("registerSuccess")){
		boolean status = Boolean.getBoolean((String)request.getAttribute("registerSuccess"));
		if(!status){
%>
	<div class="alert-box error">
	<span>Error!</span><h4> <%=CommonConstants.ERMSG_LOGIN_MSG%></h4>
	</div>
<%
		}else{
%>
<div class="alert-box success">
	<span>Success!</span><h4> User Successfully registered</h4>
	</div>
<%
		}
	}
%>

<div class="container">
	<section id="content">
		<form action="<%=request.getContextPath()%>/LoginServlet" method="post" enctype="application/x-www-form-urlencoded">
			<h1>Login</h1> 
			<div>
				<input type="text" required="required" placeholder="Username" required="" id="email" name="email" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="passWord" name="passWord"/>
			</div>
			<div>
				<input type="submit" value="Log in"  onclick="return ValidatFields();"/>
				<a class="register-hov" data-toggle="modal" data-target="#registermodal"><span class="glyphicon glyphicon-user"></span>Register</a>
			</div>
		</form><!-- form -->
		
	</section><!-- content -->
	
	<div id="registermodal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		          <h1 class="text-center">New User?</h1>
		      </div>
		      <div class="modal-body">
		      	
		          <form class="form col-md-12 center-block" action="<%=request.getContextPath()%>/Register">
		            <div class="form-group">
		              <input type="text" id="reg<%=CommonConstants.USER_NAME%>" name="<%=CommonConstants.USER_NAME%>" class="form-control input-lg" placeholder="User Name">
		            </div>
		            <div class="form-group ">
		              <input type="text" id="reg<%=CommonConstants.EMAIL%>" name="<%=CommonConstants.EMAIL%>" class="form-control input-lg" placeholder="Email-Id">
		            </div>
		            <div class="form-group ">
		              <input type="password" id="reg<%=CommonConstants.PASS_WORD%>" name="<%=CommonConstants.PASS_WORD%>" class="form-control input-lg" placeholder="Password">
		            </div>
		            <div class="form-group ">
		              <input type="password" id="re-<%=CommonConstants.PASS_WORD%>" name="re-<%=CommonConstants.PASS_WORD%>" class="form-control input-lg" placeholder="Re-Password">
		            </div>
		            
		            
		            
		            <div class="form-group">
		              <button class="btn btn-primary btn-lg btn-block" id="billSubmit"  onclick=" return ValidateFields();">Register</button>
		             <!--  <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span> -->
		            </div>
		          </form>
		      </div>
		      <div class="modal-footer">
		          <div class="col-md-12">
		          	<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				  </div>	
		      </div>
		  	</div>
		</div><!-- modal content close -->
		</div><!-- <!-- modal div close -->
	
	
	
</div><!-- container -->






</body>
</html>