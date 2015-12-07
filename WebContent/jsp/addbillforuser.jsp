<%@page import="com.uncc.fairshare.helper.BillDataFetch"%>
<%@page import="com.uncc.fairshare.helper.FetchBillDetails"%>
<%@page import="com.uncc.fairshare.helper.GroupBillDetails"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.uncc.fairshare.helper.FriendsList"%>
<%@page import="com.uncc.fairshare.helper.FetchGroupDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function createBillObj(){
		alert("called");
		var index = 0;
		
		var billDesc = document.getElementById("billDescription");
		if(billDesc.value == ""){
			alert("Enter Bill Description");
			billDesc.focus();
			return false;
		}
		var billAmount = document.getElementById("billAmount");
		if(billAmount.value == ""){
			alert("Enter Bill Amount");
			billAmount.focus();
			return false;
		}
				
		var elementObj = document.getElementsByName("friendChecked");
		var userIdObj = document.getElementsByName("friendEmail");
		var userNameObj = document.getElementsByName("friendName")
		for(i=0; i< elementObj.length ; i++){
			
			alert(elementObj[i].checked); 
			
			if(elementObj[i].checked){
				alert(userIdObj[i].value);
				index = i+1;
				break;
			}
		}
		alert("after for  "+index);
		if(index >= 0){
			alert("after if")
			var friendId = userIdObj[index].value;
			alert(friendId);
			var friendName = userNameObj[index].value;
			
			document.getElementById("friendEmail").value = friendId;
			document.getElementById("friendName").value = friendName;
			
		//	alert(" set email -- "+document.getElementById("friendEmail").value);
		//	alert("set name ---- "+document.getElementById("friendName").value);
			
			//var url = "http://localhost:8085/FairShare/AddBill?userId=111&friendId="+friendId+
				//	  "&billDesc="+billDesc.value+"&billAmount="+billAmount.value+"";
			//alert("url --> " + url);
			 	//var theform = window.document.addBillForm;
			    //theform.action=url;
			    //theform.submit();
			    return true;
		}else{
			alert("Select a friend to add the bill to ");
			return false;
		}
		return false;
	}
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true; 
	}
	
	
	 function onlyAlphabets(e, t) {
         try {
             if (window.event) {
                 var charCode = window.event.keyCode;
             }
             else if (e) {
                 var charCode = e.which;
             }
             else { return true; }
             if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                 return true;
             else
                 return false;
         }
         catch (err) {
             alert(err.Description);
         }
     }
	 
</script>
<title>FairShare</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
 --%></head>
<body >
<%@include file="sidenavbar.jsp" %>
  <div class="container rounded">
                <div class="row vertical-offset-100 rounded">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">                                
                                <div class="row-fluid user-row">
									<h1 class="bg-primary center">Add  Bill</h1>
                                </div>
                            </div>
                            <div class="panel-body">
                                <form accept-charset="UTF-8"  name="addBillForm" action="AddBill" method="post" role="form" class="form-signin" enctype="application/x-www-form-urlencoded">
                                <input type="hidden" name="userId" id="userId" value="111">
								<input type="hidden" name="<%=CommonConstants.USER_NAME%>" value="<%=session.getAttribute(CommonConstants.USER_NAME)%>">
								<input type="hidden" name="<%=CommonConstants.EMAIL%>" value="<%=session.getAttribute(CommonConstants.EMAIL)%>">
								<input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" id="friendEmail">
								<input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" id="friendName">
                                    <fieldset>
                                        <label class="panel-login">
                                            <div class="login_result"></div>
                                        </label>
                                        <input class="form-control" placeholder="Bill Description" name="billDescription" id="billDescription" onkeypress="return onlyAlphabets(event,this)" type="text">
                                        <input class="form-control" placeholder="Bill Amount ($)" name="billAmount" id="billAmount" onkeypress="return isNumber(event)" type="text">
                                        <br></br>
                                        <input class="btn btn-lg btn-success btn-block" type="submit" onclick="return createBillObj()" value="Save Bill »">
                                    </fieldset>
                                </form>
                            </div>
                            <div class="panel-footer">
                            	<div class="centre"><h3 class="bg-primary center">Friends</h3></div>
                            	<div class="alert alert-info">
                            		<%
									  	HashMap friendsList = (HashMap) request.getAttribute("friendsList");
									  	if(null != friendsList && friendsList.size() != 0 ){
									  		Iterator listIterator = friendsList.entrySet().iterator();
									    	while(listIterator.hasNext()){
									    	Map.Entry pair = (Map.Entry) listIterator.next();
								  	%>
								  		<label class="radio control-label padding">
								  			<input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
			        						<input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
								  			<input type="radio" id="friendChecked" name="friendChecked" > <span><%= pair.getValue() %></span>
								  		</label>
								  			
								  	<%
									    	}
									  	}
								  	%>
                            	</div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>