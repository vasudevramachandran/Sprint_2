<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.uncc.fairshare.helper.AddBillForFriend" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Add Bill</title>
</head>
<script type="text/javascript">
	function createBillObj(){
		//alert("called");
		var index;
		
		var billDesc = document.getElementById("billDescription");
		if(billDesc.value == ""){
			//alert("Enter Bill Description");
			billDesc.focus();
			return false;
		}
		var billAmount = document.getElementById("billAmount");
		if(billAmount.value == ""){
			//alert("Enter Bill Amount");
			billAmount.focus();
			return false;
		}
				
		var elementObj = document.getElementsByName("friendChecked");
		var userIdObj = document.getElementsByName("friendEmail");
		var userNameObj = document.getElementsByName("friendName")
		for(i=0; i< elementObj.length ; i++){
			
		//	alert(elementObj[i].checked); 
			
			if(elementObj[i].checked){
			//	alert(userIdObj[i].value);
				index = i+1;
				break;
			}
		}
		if(index >= 0){
			//alert("after if")
			var friendId = userIdObj[index].value;
	//		alert(friendId);
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
<body>

<div class="">
<div class="">
	<%@include file="sidenavbar.jsp" %>
</div>
<div class="" align="center">
	<form  name="addBillForm" action="AddBill" method="post">
	<div >
		
			<table >
				<tbody>
					<tr>
						<th>Bill Description</th>
						<th><input type="text" name="billDescription" id="billDescription" onkeypress="return onlyAlphabets(event,this)">
						<input type="hidden" name="userId" id="userId" value="111">
						<input type="hidden" name="<%=CommonConstants.USER_NAME%>" value="<%=session.getAttribute(CommonConstants.USER_NAME)%>">
						<input type="hidden" name="<%=CommonConstants.EMAIL%>" value="<%=session.getAttribute(CommonConstants.EMAIL)%>">
						<input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" id="friendEmail">
						<input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" id="friendName">
						</th>
					</tr>
					<tr>
						<th>Amount </th>
						<th><input type="text" name="billAmount" id="billAmount" onkeypress="return isNumber(event)"></th>
					</tr>
					<tr>
						<th> Add Bill </th>
						<th> <input type="submit" value="Submit" onclick="return createBillObj()"></th>
					</tr>
				</tbody>
			</table>
			
	</div>
	
		<div>
			<h4 align="center">Friends List</h4>
		</div>
		<%
		  	HashMap friendsList = (HashMap) request.getAttribute("friendsList");
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
	</div> <!-- Div container class -->

</body>
</html>