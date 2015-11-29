<%@page import="com.uncc.fairshare.helper.GroupBillDetails"%>
<%@page import="com.uncc.fairshare.helper.BillDetails"%>
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
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
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
		int groupId = fetchDetails.getGroupId();
		String groupName = fetchDetails.getGroupName();
		String groupDesc = fetchDetails.getGroupDesc();
		int numOfPpl  = fetchDetails.getGroupMemberMap().size();
		session.setAttribute("jspSessionGroupDetail", fetchDetails.getGroupMemberMap());
%>
<!-- <div class="col-7-lg"> --><div class="floatdiv" >
<div class="newContainer" align="center">
	<form  name="splitbillform" id="splitbillform" action="SplitBill" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" id="<%=CommonConstants.EMAIL %>" name="<%=CommonConstants.EMAIL %>" value="<%=session.getAttribute(CommonConstants.EMAIL) %>">
	<input type="hidden" id="<%=CommonConstants.JSP_BILL_SPLIT %>"name="<%=CommonConstants.JSP_BILL_SPLIT %>">
 	<input type="hidden" id="<%=CommonConstants.JSP_GROUP_ID %>" name="<%=CommonConstants.JSP_GROUP_ID %>" value="<%=groupId%>">
 	<input type="hidden" id="<%=CommonConstants.JSP_SPLIT_TYPE%>" name="<%=CommonConstants.JSP_SPLIT_TYPE%>" value="0">
 	<input  type="hidden" id="<%=CommonConstants.JSP_NUM_USER%>" name="<%=CommonConstants.JSP_NUM_USER%>" value = "<%=numOfPpl%>">
	<div class="header"><h1><%=groupName %></h1></div>
		<div>
			<div class="mainbody">
				<div>
					<ul>
						<li><span><%=groupDesc%></span></li>
					</ul>
				</div>
			</div>
	</div>
	<!-- <div class="mainbody"> -->
		<div class="bodydiv">
		
			<div>
				<span><h4>Split By<h4></span>
			</div>
			<div>
			<div class="split-inline" id ="percent"><span id="percent"><input type="radio" id="rdsplit" name="rdsplit"><strong>Percent</strong></span></div>
			<div class="split-inline"><span id="equally"><input type="radio" id="rdsplit" name="rdsplit" checked="checked"><strong>Equally</strong></span></div>
			<!-- <div class="split-inline"><span id="share"><strong>Share</strong></span></div> -->
			</div>
			<div>
				<table>
					
					<tbody>
						<tr>
							<th>Bill Name</th>
							<th><input type="text" id="<%=CommonConstants.JSP_BILL_DESC%>" name="<%=CommonConstants.JSP_BILL_DESC%>"></th>
						</tr>
						<tr>
							<th>Bill Amount</th>
							<th><input type="text" id="<%=CommonConstants.JSP_BILL_AMT%>" name="<%=CommonConstants.JSP_BILL_AMT%>"></th>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<div class="hiddendivequal" id="togequal">
				<input type ="submit" id="billSubmitEqual" value="Split & Save" class="myButton">
			</div>
			<div class="hiddendivshare" id="togglediv">
					<div class="tablediv">	
			<div class="">
				<h6 align="center">Split with </h6>
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
				    	if(!pair.getValue().equals(session.getAttribute(CommonConstants.USER_NAME))){
				    %>
					<tr align="center">
				        <td><%= pair.getValue() %>
				        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
				        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
				        </td>
				        <td ><input type="text" id="<%=CommonConstants.JSP_SHARE %>" name="<%=CommonConstants.JSP_SHARE%>"> </td>
				     </tr>
					<%
				    	}
				     }
				     }%>
				     <%-- <tr>
				     	<td>
				     		<%=session.getAttribute(CommonConstants.USER_NAME)%>
				     		<input type ="hidden" id="billSubmit" name="<%=CommonConstants.JSP_FRND_EMAIL %>" value="<%=CommonConstants.EMAIL%>">
				     		<input type ="hidden" id="billSubmit" name="<%=CommonConstants.JSP_FRND_NAME %>" value="<%=CommonConstants.USER_NAME%>">
				     	</td>
				     	<td>
				     		<input type="text" id="share" name="share" >
				     	</td>
				     	
				     </tr> --%>
				     <tr>
				     	<td>
				     		<input type ="submit" id="billSubmit" value="Split & Save" class="myButton">
				     	</td>
				     </tr>
				     </thead>
				</table>
				
				</div>
			    </div>
			  </div>
			</div>
		</div>
	<!-- </div> -->
	<div class="tablediv">
		<div class="">
			<h4 align="center">Bills</h4>
		</div>
		<div>
		<% 
		HashMap groupBillDetail = fetchDetails.getGroupBillDetails();
	
		if(null != groupBillDetail && groupBillDetail.size()!=0){
		%>
		
		<div class="">
			<table >
				<thead>
					<tr>
						<th> Name </th>
						<th> Share </th>
						<th>Owed To</th>
						<th>Bill Desc</th>
						<th> Delete</th>
						
					</tr>
				</thead>
				<tbody>
				
				<%
			    	Iterator listIterator = groupBillDetail.entrySet().iterator();
			    	while(listIterator.hasNext()){
			    	Map.Entry pair = (Map.Entry) listIterator.next();	
			    	GroupBillDetails billObj = (GroupBillDetails) pair.getValue();
			    %>
			   
			    <div>
				<tr align="center">
			        <td><%= billObj.getOwedByName() %>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
			        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
			        </td>
			        <td><%=billObj.getBillShare() %></td>
			        <td><%=billObj.getAddedByName() %></td>
			        <td><%=billObj.getBillName() %></td>
			        <td ><!-- <input type="submit" id="friendChecked" value="Delete" class="myButton">  -->
			        	<a href="DeleteFromGrp?billId=<%=billObj.getBillId()%>&groupId=<%=groupId%>" class = "myButton">Delete</a>
			        </td>
			     </tr>
			     </div>
			     
				<%
			     }
			     %>
			     </tbody>
			</table>
			
		</div>
		<%
			}
		%>
		
		</div>
	</div>	
	<div class="tablediv">	
		<div class="">
			<h4 align="center">Members</h4>
		</div>
		<%
		  	 friendsList = fetchDetails.getGroupMemberMap();
		  	if(null != friendsList && friendsList.size() != 0 ){
	  	%>
	  	<div class="">
			<table >
				<thead>
					<tr>
						<th> Name </th>
						<th> Delete </th>
					</tr>
				</thead>
				<tbody>
				
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
			        <td ><!-- <input type="submit" id="friendChecked" value="Delete" class="myButton">  -->
			        	<a href="DeleteMemFromGrp?userId=<%=pair.getKey()%>&groupId=<%=groupId%>" class = "myButton">Delete</a>
			        </td>
			     </tr>
				<%
			     }%>
			     </tbody>
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
		%>
		</form>
	</div><!-- Div for col-7-lg -->
	</div>
	<%
	}
	
			  	
	%>
	</div> <!-- Div container class -->
	
</body>
<script>
	$(document).ready(function(){
		$("#percent").click(function(){
			//alert("percent");
			$("#togglediv").removeClass("hiddendivshare");
			$("#togglediv").addClass("showdivshare");
			
			$("#togequal").removeClass("showdivequal");
			$("#togequal").addClass("hiddendivequal");
		});
		$("#equally").click(function(){
			$("#togglediv").removeClass("showdivshare");
			$("#togglediv").addClass("hiddendivshare");
			$("#togequal").removeClass("hiddendivequal");
			$("#togequal").addClass("showdivequal");
		});
		
		$("#billSubmit").click(function(){
			collateData();
			$("#billSplitType").val(1); 
			$("#splitbillform").submit();
			
		});
		
		$("#billSubmitEqual").click(function(){
			if($("#billDescription").val() != "" && $("#billAmount").val() != ""){
				$("#billSplitType").val(0);
			}else{
				alert("Enter Bill Name and Amount");
			}
				
		});		
		
	});
	
	function collateData(){
		
		//alert("called");
		
		var email = document.getElementsByName("friendEmail");
		//alert(email);
		var value = document.getElementsByName("share");
		//alert(value);
		var emailStr = document.getElementById("email").value;
		var billAmount = document.getElementById("billAmount").value;
		//alert(emailStr);
		
		
		//salert("called after");
		var concatString = emailStr + "-"+billAmount +"," ;
		
		for(i=0 ; i < value.length ; i++){
			//alert(email[i].value +"-"+value[i].value);
			//alert(value[i].value);
			concatString = concatString + email[i].value +"-"+value[i].value + ",";
			//alert(concatString);
			//alert(i)
		}
		if(concatString != ""){
		//alert("outside loop"+concatString);
		document.getElementById("billSplitVal").value = concatString;
		
		//alert(document.getElementById("billSplitVal").value);
		}
	}
	
</script>
</html>