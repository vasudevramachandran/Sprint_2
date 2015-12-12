<%@page import="com.uncc.fairshare.helper.GroupBillDetails"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.uncc.fairshare.helper.FriendsList"%>
<%@page import="com.uncc.fairshare.helper.FetchGroupDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function ValidateFields(){
		alert("called");
		
		var userName = document.getElementsByName("friendEmail");
		var email    = document.getElementsByName("friendEmail");
		var checked  = document.getElementsByName("friendChecked");
		var index = 0;
		var selectedEmail = document.getElementById("email").value+"-"+document.getElementById("userName").value;
		for(i=0; i< checked.length ; i++){
					
					alert(checked[i].checked); 
					
					if(checked[i].checked){
						alert(email[i].value);
						index++ ;
						selectedEmail = selectedEmail +","+email[i].value+"-"+userName[i].value;
						
						
					}
				}
		//alert(selectedEmail);
		document.getElementById("selectedFriendList").value = selectedEmail;
		alert(document.getElementById("selectedFriendList").value);
		alert(userName[1].value); 
		if(index == 0){
			alert("select a friend to add to the group");
			return false;
		}
		
	}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FairShare</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
 --%></head>
<body>
<%@include file="sidenavbar.jsp" %>



<div class="container rounded">
		<div class="row vertical-offset-100 rounded">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="row-fluid user-row">
						<h1><span class="glyphicon glyphicon-plus-sign"></h1>
						<h1><span>Create Bill</span></h1>
					</div>
					<div class="panel-body">
			   			 <form class="form col-xs-12 toppad" action="CreateGroup" method="post" enctype="application/x-www-form-urlencoded">
					            <div class="form-group">
					             	<input type="text" placeholder="Group Name" required="" id="<%=CommonConstants.JSP_GROUP_NAME%>" name="<%=CommonConstants.JSP_GROUP_NAME%>" class="form-control input-lg" >
					            	<input type="hidden" name = "<%=CommonConstants.JSP_SEL_FRND_LIST%>" id="<%=CommonConstants.JSP_SEL_FRND_LIST%>">
									<input type ="hidden" name="<%=CommonConstants.EMAIL%>" id="<%=CommonConstants.EMAIL%>" value="<%=session.getAttribute(CommonConstants.EMAIL)%>">
									<input type ="hidden" name="<%=CommonConstants.USER_NAME%>" id="<%=CommonConstants.USER_NAME%>" value="<%=session.getAttribute(CommonConstants.USER_NAME)%>">
					            </div>
					            <div class="form-group ">
						            <%
								  		HashMap friendsList = (HashMap) session.getAttribute(CommonConstants.JSP_FRIENDS_LIST);
								  		if(null != friendsList && friendsList.size() != 0 ){
								  	%>
								  	<div class="row col-xs-12 checkbox">
								  	 	<%
									    	Iterator listIterator = friendsList.entrySet().iterator();
									    	while(listIterator.hasNext()){
									    	Map.Entry pair = (Map.Entry) listIterator.next();	
									    %> 
									     <div class="col-sx-6">
							              <label class="c-input c-checkbox">
											  <input type="checkbox" id="friendChecked" name="friendChecked" >
											  <span class="c-indicator" ></span>
											  <%=pair.getValue() %>
											  <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" id="<%=CommonConstants.JSP_FRND_EMAIL%>"  value="<%= pair.getKey()%>">
					        				  <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" id="<%=CommonConstants.JSP_FRND_EMAIL%>"value="<%= pair.getValue()%>">
										  </label>
										  </div>
										  <%
									    	}
										%>
									</div>
									<%
								  		}
									%>
					            </div>
					            
					            
					            <div class="form-group">
					              <button class="btn btn-primary btn-lg btn-block" onclick="return ValidateFields();">Create Group</button>
					             <!--  <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span> -->
					            </div>
					          </form>
					        </div>  
						</div>
					</div>
				</div>
			</div>




</div>

</body>
</html>