<%@page import="com.uncc.fairshare.helper.FetchGroupDetails"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
</head>
<body>
<%@include file="logoutJsp.jsp" %>
<div class="">
<div class="col-3-sm">
	<%@include file="sidenavbar.jsp" %>
</div>

<%
	if(null != request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ)){
		FetchGroupDetails fetchDetails = (FetchGroupDetails) request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ);
		
		String groupName = fetchDetails.getGroupName();
		String groupDesc = fetchDetails.getGroupDesc();
	
%>
<!-- <div class="col-7-lg"> --><div class="floatdiv" >
<div class="newContainer" align="center">
	<form  name="addBillForm" action="" method="">
	<div class="header"><%=groupName %></div>
		<div>
			<div class="mainbody">
				<div>
					<ul>
						<li><span><%=groupDesc%></span></li>
					</ul>
				</div>
			</div>
	</div>
	
		<div>
			<h4 align="center">Friends List</h4>
		</div>
		<%
		  	HashMap friendsList = fetchDetails.getGroupMemberMap();
		  	if(null != friendsList && friendsList.size() != 0 ){
	  	%>
	  	<div >
			<table >
				<thead>
					<tr>
						<th> Name </th>
						<th> Select </th>
					</tr>
				</thead>
				<thead>
				
				<%
			    	Iterator listIterator = friendsList.entrySet().iterator();
			    	while(listIterator.hasNext()){
			    	Map.Entry pair = (Map.Entry) listIterator.next();	
			    %>
				<tr align="center">
			        <td><%= pair.getValue() %>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
			        </td>
			        <td ><input type="radio" id="friendChecked" name="friendChecked" > </td>
			     </tr>
				<%
			     }%>
			     </thead>
			</table>
			
		</div>
		<%
		  	}else{
		%>
		
			<div >
	  			<h3><strong>Info!</strong> You have no friends to split your bill with :)</h3>
			</div>
		<%
		  	}
		%>
		</form>
	</div><!-- Div for col-7-lg -->
	</div>
	<%
	}%>
	</div> <!-- Div container class -->

</body>
</html>