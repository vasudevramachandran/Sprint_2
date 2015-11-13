<%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	
</script>
</head>
<body>
<div class="toprightcorner" >
	<%
		if(null != session.getAttribute(CommonConstants.USER_NAME)){
			String userName = (String) session.getAttribute(CommonConstants.USER_NAME);
			String email =(String) session.getAttribute(CommonConstants.EMAIL);
	%>
	<input type="hidden" name="<%=CommonConstants.EMAIL%>" value="<%=email%>"/>
	<h1> Welcome! <%=userName%></h1>
	<%} %>
		<form action="LogOut" method="post" enctype="application/x-www-form-urlencoded">
		<input type="submit" class="styled-button-9" value="Log Out" onclick="" /> 
		</form>

</div>
</body>
</html>