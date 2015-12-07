<%@page import="com.uncc.fairshare.helper.CreateGroupDetail"%>
<%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link href="<%=request.getContextPath() %>/bootstrap/css/agency.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
</head>
<body id="page-top" class="index">
<!-- Header Navigation -->

<!-- 	<div>
		<table>
			<thead>
			<tr>
				<th> <a href="http://localhost:8085/FairShare/jsp/home.jsp"> Home </th>
				<th> <a href ="http://localhost:8085/FairShare/FetchPageData?userId=111" > Add Bill </a></th>
				<th> <a href ="http://localhost:8085/FairShare/ViewBillServlet?userId=111" > View Bill </a></th>
				<th> <a href ="http://localhost:8085/FairShare/ViewBillServlet?userId=111" > Create Group </a></th>
			</tr>
			</thead>
		</table>
	</div> -->
	

<%@include file="sidenavbar.jsp" %>	

	<%
		if(null != request.getAttribute("status")){
			System.out.println("home jsp ---> "+ request.getAttribute("status"));
			if((Integer)request.getAttribute("status") == CommonConstants.INT_SUCCESS){
			%>
				<div class="alert-box success"><span>success: </span><h4>Bill Added</h4></div>
			<%	
				}else{
			%>
				<div class="alert-box warning"><span>warning: </span><h4>Bill Not Added</h4></div>
			<%	
				}
			%>
			<%
				}else{
			%>
			<%
				}
	%>
	
	
	<%
		if(null != request.getAttribute("validDelete")){
			System.out.println("home jsp ---> "+ request.getAttribute("validDelete"));
			if((Integer)request.getAttribute("validDelete") == CommonConstants.INT_SUCCESS){
			%>
				<div class="alert-box success"><span>success: </span><h4>Bill Deleted </h4></div>
			<%	
				}else{
			%>
				<div class="alert-box warning"><span>warning: </span><h4>Bill Not Deleted</h4></div>
			<%	
				}
			%>
			<%
				}else{
			%>
			<%
				}
	%>
	
	<%
		if(null != session.getAttribute(CommonConstants.JSP_CREATE_GRP_OBJ)){
			CreateGroupDetail createGroupObj = (CreateGroupDetail)session.getAttribute(CommonConstants.JSP_CREATE_GRP_OBJ);
			if(createGroupObj.isCreated()){
			%>
				<div class="alert-box success"><span>success: </span><h4>Group Created </h4></div>
			<%	
				}else{
			%>
				<div class="alert-box warning"><span>warning: </span><h4>Group Not Created</h4></div>
			<%	
				}
			%>
			<%
				}else{
			%>
			<%
				}
	%>
	
	 <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">Welcome To Our App!</div>
                <!-- <div class="intro-heading">We help keep track of your expenses</div> -->
               
            </div>
        </div>
    </header>
    
    
	
</body>
</html>