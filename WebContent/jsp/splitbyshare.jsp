<%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
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

<div class="">
<%
	if(null != request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ)){
		FetchGroupDetails	fetchDetails = (FetchGroupDetails) request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ);
		
	
	
%>


<!-- <div class="col-7-lg"> -->
<!-- <div class="floatdiv" > -->
<!-- <div class="newContainer" align="center"> -->
	<form  name="addBillForm" action="" method="">
	

		
	<div class="tablediv">	
		<div class="">
			<h4 align="center">Friends List</h4>
		</div>
		<%
		  	HashMap friendsList = fetchDetails.getGroupMemberMap();
		  	if(null != friendsList && friendsList.size() != 0 ){
	  	%>
	  	<div class="">
			<table >
				<thead>
					<tr>
						<th> Name </th>
						<th> Share </th>
					</tr>
				</thead>
				<thead>
				
				<%
			    	Iterator listIterator = friendsList.entrySet().iterator();
					int i = 0;
			    	while(listIterator.hasNext()){
			    	Map.Entry pair = (Map.Entry) listIterator.next();	
			    	i = i+1;
			    %>
				<tr align="center">
			        <td><%= pair.getValue() %>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
			        </td>
			        <td ><input type="text" id="share[<%=i %>]" name="share[<%=i %>]"> </td>
			     </tr>
				<%
			     }%>
			     </thead>
			</table>
			
		</div>
		</div>
		<%
		  	}else{
		%>
		
			<div >
	  			<h3><strong>Info!</strong> You have no friends to split your bill with :)</h3>
			</div>
		<%
		  	}
		  	
	}
		%>
		</form>
	<!-- </div>Div for col-7-lg -->
	<!-- </div> -->
	
	</div> <!-- Div container class -->
	
</body>
</html>