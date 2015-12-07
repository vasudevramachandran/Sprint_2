<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
<script type="text/javascript">
	function ValidateFields(){
		//alert("called");
		
		var userName = document.getElementsByName("friendEmail");
		var email    = document.getElementsByName("friendEmail");
		var checked  = document.getElementsByName("friendChecked");
		var index = 0;
		var selectedEmail = document.getElementById("email").value+"-"+document.getElementById("userName").value;
		for(i=0; i< checked.length ; i++){
					
					//alert(checked[i].checked); 
					
					if(checked[i].checked){
						//alert(email[i].value);
						index++ ;
						selectedEmail = selectedEmail +","+email[i].value+"-"+userName[i].value;
						
						
					}
				}
		//alert(selectedEmail);
		document.getElementById("selectedFriendList").value = selectedEmail;
		alert(document.getElementById("selectedFriendList").value);
		//alert(userName[1].value); 
		if(index == 0){
			alert("select a friend to add to the group");
			return false;
		}
		
	}

</script>
</head>
<body>


<div>
<div>
<%@include file="sidenavbar.jsp" %>
</div>

	<div class="container">
		<section id="content">
			<form action="CreateGroup" method="post" enctype="application/x-www-form-urlencoded">
				<h1>Create Your Groups</h1>
				<div>
					<input type="text" placeholder="Group Name" required="" id="<%=CommonConstants.JSP_GROUP_NAME%>" name="<%=CommonConstants.JSP_GROUP_NAME%>" />
					<input type="hidden" name = "<%=CommonConstants.JSP_SEL_FRND_LIST%>" id="<%=CommonConstants.JSP_SEL_FRND_LIST%>">
					<input type ="hidden" name="<%=CommonConstants.EMAIL%>" id="<%=CommonConstants.EMAIL%>" value="<%=session.getAttribute(CommonConstants.EMAIL)%>">
					<input type ="hidden" name="<%=CommonConstants.USER_NAME%>" id="<%=CommonConstants.USER_NAME%>" value="<%=session.getAttribute(CommonConstants.USER_NAME)%>">
				</div>
				
	
				<div>
					<input type="submit" value="Create Group"  onclick="return ValidateFields();"/>
					
				</div>
			</form><!-- form -->
			
		</section><!-- content -->
	</div><!-- container -->
	<div class="input-group">
  	<% 
  	HashMap friendsList = (HashMap) session.getAttribute(CommonConstants.JSP_FRIENDS_LIST);
  	if(null != friendsList && friendsList.size() != 0 ){
  	%>
		  	<div class="container">
		  <!-- <h2>Contextual Classes</h2>
		  <p>Contextual classes can be used to color table rows or table cells. The classes that can be used are: .active, .success, .info, .warning, and .danger.</p> 
		   -->           
		  <table class="table">
		    <thead>
		      <tr>
		        <th>Name</th>
		        <th>select</th>
		      </tr>
		    </thead>
		    <tbody>
		    <%
		    	Iterator listIterator = friendsList.entrySet().iterator();
		    	while(listIterator.hasNext()){
		    	Map.Entry pair = (Map.Entry) listIterator.next();	
		    %>
		      <tr>
		        <td><%= pair.getValue() %>
		        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" id="<%=CommonConstants.JSP_FRND_EMAIL%>"  value="<%= pair.getKey()%>">
		        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" id="<%=CommonConstants.JSP_FRND_EMAIL%>"value="<%= pair.getValue()%>">
		        </td>
		        <td><input type="checkbox" id="friendChecked" name="friendChecked" > </td>
		      </tr>
		     <%
		     }%>
		    </tbody>
		  </table>
		</div>
	<%
	  	}else{
	  		
	%>
		<div class="alert alert-info">
  			<h3><strong>Info!</strong> You have no friends to split your bill with :)</h3>
		</div>
	<%
	  	}
	%>
	</div>
	</div>
</body>
</html>