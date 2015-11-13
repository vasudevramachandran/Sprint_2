<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Add Bill to Group</title>
<!-- <link href="../bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bootstrap/js/addbillscript.js"></script> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/style.css" />
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Fair Split</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="home.jsp">Home<span class="sr-only"></span></a></li>
        <li class="active"><a href="http://localhost:8085/FairShare/FetchPageData?userId=111">Add a bill</a>
        	
        </li>
      </ul>
    </div>
  </div>
</nav>
   
  <h5 align="center"><span class="label label-default" >Add A Bill</span></h5>
  <div class="form-group">
   <form action="AddBill" method="post" class="bs-example bs-example-form" role="form">
	   	<div class="col-xs-10 col-sm-6">
	   	  <label for="billDescription">Bill Description</label>
		  <input type="text" class="form-control" name="billDescription" placeholder="Add a bill description.." aria-describedby="basic-addon1">
		</div>
		<div class="col-xs-10 col-sm-6">
	   	  <label for="amountValue">$</label>
		  <input type="text" class="form-control" name="amountValue" placeholder="Add bill value" aria-label="Amount (to the nearest dollar)">
		  </div>
	<div class="input-group">
  	<% 
  	HashMap friendsList = (HashMap) request.getAttribute("friendsList");
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
		        <input type="hidden" name="friendId">
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
   	<div class="input-group">  
	  <input type="submit" class="btn btn-info btn-block" onclick="collateDataAndForward();" value="Submit" aria-label="Amount (to the nearest dollar)">
	  
  	</div>
   </form>
  </div>

</body>
</html>