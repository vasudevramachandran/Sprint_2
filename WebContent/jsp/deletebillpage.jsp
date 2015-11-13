<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.uncc.fairshare.helper.BillDataFetch"%>
<%@page import="com.uncc.fairshare.helper.FetchBillDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View/Delete Bills</title>
</head>
<script type="text/javascript">

	function deleteBill(){
		
		var checkedList = document.getElementsByName("billChecked");
		var billId 		= document.getElementsByName("billId");
		var parameterList =" 0";
		
		var index = 0;
		//alert("before loop");		
		for(i=0; i < checkedList.length;i++){
		//alert("in Loop")
			if(checkedList[i].checked){
				
				//alert(i+"  --> checked");
				index = index + 1;
				parameterList = parameterList + " ," + billId[i].value;
			}else{
				//alert(i+"  unchecked");
			}
		}
		
		if(index < 1){
			alert("Please select a bill to delete");
			return false;
		}else{
			document.getElementById("deleteId").value = parameterList
			//alert("parameter list " + parameterList);
		}
		
		return true;
	}

</script>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/style.css" />
<body>
<%@include file= "logoutJsp.jsp"%>
<div>
	<!-- <div>
		<table>
			<thead>
			<tr>
				<th> <a href="http://localhost:8085/FairShare/jsp/home.jsp"> Home </th>
				<th> <a href ="http://localhost:8085/FairShare/FetchPageData?userId=111" > Add Bill </a></th>
				<div>
					<th> <a href ="http://localhost:8085/FairShare/ViewBillServlet?userId=111"> View Bill </a></th>
				</div>
			</tr>
			</thead>
		</table>
	</div> -->
</div>
<%@include file="sidenavbar.jsp" %>

	<div>
		<h4 align="center">Friends List</h4>
	</div>
	<%
	  	FetchBillDetails billDetails = (FetchBillDetails) request.getAttribute("billDataObj");
	  	if(null != billDetails && null!=billDetails.getBillDetails() && billDetails.getBillDetails().size() != 0 ){
  	%>
  	<div>
  		
  	
  	</div>
	<div>
		
	  <form method="get" action="DeleteBill">
		<table >
		<div align="center">
			<input type="submit"  class="myButton" value="Delete" onclick="return deleteBill();">
			<input type="hidden" name="deleteId" id="deleteId">
		</div>
			<thead>
				<tr>
					<th style="height: 50px;background-color: green;color: white;"> Bill Owed By </th>
					<th style="background-color: green;color: white;"> Bill Desc </th>
					<th style="background-color: green;color: white;"> Bill Amount </th>
					<th style="background-color: green;color: white;"> Select </th>
				</tr>
				
			</thead>
			<thead>
			
			<%
		    	Iterator listIterator = billDetails.getBillDetails().entrySet().iterator();
		    	while(listIterator.hasNext()){
		    	Map.Entry pair = (Map.Entry) listIterator.next();	
		    	BillDataFetch billData = (BillDataFetch) pair.getValue();
		    	
		    	int billId = billData.getBillId();
		    	long billAmount = billData.getBillAmount();
		    	String billDesc = billData.getBillDescription();
		    	String friendName = billData.getFriendName();
		    %>
			<tr align="center">
		        <td style="padding: 5px;"><%= friendName%>
		        <input type="hidden" name="billId" value=<%= billId %>>
		        </td>
		        <td style="padding: 5px;"><%= billDesc%>
		        </td>
		        <td style="padding: 5px;"><%= billAmount%>
		        </td>
		        <td style="padding: 5px;"><input type="checkbox" id="billChecked" name="billChecked" > </td>
		     </tr>
			<%
		     }%>
		     </thead>
		</table>
	 </form>
	</div>
	<%
	  	}else{
	%>
	
		<div class="alert-box notice">
  			<span><strong>Info!</strong></span> You have no Bills added yet
		</div>
	<%
	  	}
	%>
	
	
	
</body>
</html>