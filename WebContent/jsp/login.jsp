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
<script>
	function ValidateFields(){
		var userName = document.getElementById("userName").value;
		var passWord = document.getElementById("passWord").value;
		
		
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

<div class="container">
	<section id="content">
		<form action="../LoginServlet" method="post" enctype="application/x-www-form-urlencoded">
			<h1>Login Form</h1> 
			<div>
				<input type="text" placeholder="Username" required="" id="email" name="email" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="passWord" name="passWord"/>
			</div>
			<div>
				<input type="submit" value="Log in"  onclick="return ValidatFields();"/>
				<a href="#">Lost your password?</a>
				<a href="register.jsp">Register</a>
			</div>
		</form><!-- form -->
		
	</section><!-- content -->
</div><!-- container -->
</body>
</html>