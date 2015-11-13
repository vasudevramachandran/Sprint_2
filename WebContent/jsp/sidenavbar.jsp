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
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
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
		alert("set value"+ document.getElementById("selectedGroupId").value);
	}
	
</script>
</head>
<body>

<div class ="col-3-sm">
<form action="FetchGroup" id="fetchGroupAction">
	<input type="hidden" name="<%=CommonConstants.JSP_SEL_GROUP_ID%>" id="<%=CommonConstants.JSP_SEL_GROUP_ID%>">
</form>
<div id='cssmenu'>
<ul>
   <li><a href='#'><span>Home</span></a></li>
   <li class='active has-sub'><a href='#'><span>Friends</span></a>
      <ul>
        <% User user = (User) session.getAttribute(CommonConstants.OBJ_USER);
            	if(null!=user && null != user.getFriendsMap()){
            		if(user.getFriendsMap().size() != 0 ){
            			Iterator itObj = user.getFriendsMap().entrySet().iterator();
            			while(itObj.hasNext()){
            				Map.Entry pair = (Map.Entry) itObj.next();
            				String keyUserName = (String)pair.getValue();
            				String keyEmail = (String)pair.getKey();
            				
         %>
         <li class='active has-sub'><a href='<%=request.getContextPath()%>/FetchPageData'>
         <span><%=keyUserName %><input type="hidden" id="selectedUser" value="<%=keyEmail%>"/></span></a>
         </li>
         <%
            			}
            	}
            }
         %>
         
      </ul>
   </li>
   <li><a href='FetchPageData?email='<%=session.getAttribute(CommonConstants.EMAIL) %>><span>Add Bill</span></a></li>
   <li><a href='ViewBillServlet?email='<%=session.getAttribute(CommonConstants.EMAIL) %>><span>View Bill</span></a></li>
   <li><a href='FetchCreateGroup'><span>Create Group</span></a></li>
   <li><a href='FetchCreateGroup'><span>My Groups</span></a>
	   <ul>
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
	   	
	   		 <li class='active has-sub'><a href="#" name="<%=CommonConstants.JSP_SEL_HREF_NAME %>" id="<%=keyid%>" class="aHref">
	   		 
         		<span><%=keyGroupName %> <form><input type="hidden" id="<%=CommonConstants.JSP_GROUP_ID%>" value="<%=keyid%>"/>
         		</form></span></a>
        	 </li>
        	 
	   	<%		
	   					}
	   				}
	   			}
	   	%>
	   </ul>
   </li>
   <li><a href='#'><span>About</span></a></li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
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
	
	
	
});


</script>
</html>