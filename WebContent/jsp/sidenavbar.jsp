<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.uncc.fairshare.constants.CommonConstants"%>
<%@page import="com.uncc.fairshare.helper.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>navbar</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/smoothscroll.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/* function FetchBillDetails(){
		alert("called");
		var selectedId = document.getElementById("groupId").value;
		if(selectedId != ""){
			document.getElementById("selectedGroupId").value = selectedId;
			alert(selectedId); 
		}
		return true;
	} */
	function assignValue(selectedId){
		
		document.getElementById("selectedGroupId").value = selectedId;
		//alert("set value"+ document.getElementById("selectedGroupId").value);
	}
	
</script>
</head>
<body>
<%
	String userName = "";
	String email = "";
		if(null != session.getAttribute(CommonConstants.USER_NAME)){
			 userName = (String) session.getAttribute(CommonConstants.USER_NAME);
			 email =(String) session.getAttribute(CommonConstants.EMAIL);
		}
	%>


<div >
<form action="FetchGroup" id="fetchGroupAction">
	<input type="hidden" name="<%=CommonConstants.JSP_SEL_GROUP_ID%>" id="<%=CommonConstants.JSP_SEL_GROUP_ID%>">
</form>

<div class="container">
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Fair Share</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li  id="activeclick"><a href="HomePage?"><span class="glyphicon glyphicon-home"></span><span class="sr-only">(current)</span></a></li>
        <li id="activeclick"><a href='FetchPageData?email='<%=session.getAttribute(CommonConstants.EMAIL) %>><span class="glyphicon glyphicon-plus"></span>Add Bills</a></li>
        <li id="activeclick"><a href='ViewBillServlet?email='<%=session.getAttribute(CommonConstants.EMAIL) %>><span class="glyphicon glyphicon-trash"></span>View/Delete Bills</a></li>
        <li id="activeclick"><a href='FetchCreateGroup?email='<%=session.getAttribute(CommonConstants.EMAIL)%>><span class="glyphicon glyphicon-plus"></span>Create Group</a></li>
        <li class="dropdown" id="activeclick">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-th-list"></span>My Groups<span class="caret"></span></a>
        	<ul class="dropdown-menu">
		        	 
			   	<%
			   		User userObj = (User) session.getAttribute(CommonConstants.OBJ_USER);
			   			if(null != userObj && null != userObj.getGroupMap()){
			   				if(userObj.getGroupMap().size() != 0 && !userObj.getGroupMap().isEmpty()){
			   					Iterator itObj = userObj.getGroupMap().entrySet().iterator();
			   					while(itObj.hasNext()){
			   						Map.Entry pair = (Map.Entry) itObj.next();
			   						String keyid = (String) pair.getKey();
			   						String  keyGroupName  = (String)pair.getValue();
			   	%>	
			   		
			   		<li><a href="<%=request.getContextPath() %>/FetchGroup?<%=CommonConstants.JSP_GROUP_ID %>=<%=keyid %>" name="<%=CommonConstants.JSP_SEL_HREF_NAME %>" id="<%=keyid%>" >
			   		 
		         		<%=keyGroupName %></a></li>
		         		<!--  <li role="separator" class="divider"></li> -->
		        	 
		        	 
			   	<%		
			   					}
			   				}
			   			}
			   	%>
			   
        	</ul>
        	</li>
        <li class="dropdown" id="activeclick">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-globe"></span>My Friends <span class="caret"></span></a>
          <ul class="dropdown-menu">
          
          	<% User user = (User) session.getAttribute(CommonConstants.OBJ_USER);
            	if(null!=user && null != user.getFriendsMap()){
            		if(user.getFriendsMap().size() != 0 ){
            			Iterator itObj = user.getFriendsMap().entrySet().iterator();
            			while(itObj.hasNext()){
            				Map.Entry pair = (Map.Entry) itObj.next();
            				String keyUserName = (String)pair.getValue();
            				String keyEmail = (String)pair.getKey();
            				
         %>
       <li>  <%-- <a href='<%=request.getContextPath()%>/FetchPageData'> --%>
         <%=keyUserName %></a><input type="hidden" id="selectedUser" value="<%=keyEmail%>"/><li>
         <li role="separator" class="divider"></li>
         
         <%
            			}
            	}
            }
         %>
          
            
          </ul>
        </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span><%=userName %> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">My Profile</a></li>
            
            <li role="separator" class="divider"></li>
            <li><a href="LogOut"><span class="glyphicon glyphicon-off"></span>Logout</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	//alert("jquery called");
	$(".aHref").click(function(){
		
		//alert($(this).attr('id'));
		
		assignValue($(this).attr('id'));
		
		$("#fetchGroupAction").submit();
		
		return true;
	});

	$("#activeclick").click(function(){
		$(this).addClass("active");
		
	});	
});


</script>
</html>