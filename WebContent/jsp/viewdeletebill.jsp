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
<title>FairShare</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
 --%></head>
<body >
<%@include file="sidenavbar.jsp" %>

	<div class="size6 rounded" align="center" >
		<!-- <div class="span12">
			<div class="row-fluid">
				<div class="span12"> -->	
					<div class = "list-group">
						
						<a href="#" class="list-group-item active"><strong>My Bills</strong><span></span></a>
						<!-- Button to open modal window to display the users and delete users -->
						
						
							<!-- modal window content -->
						<!-- Modal button operations end -->
						
						<div	class="panel-group" id="accordion">
						<div class="panel panel-default">
						
						<%
							FetchBillDetails billDetails = (FetchBillDetails) request.getAttribute("billDataObj");
						  	if(null != billDetails && null!=billDetails.getBillDetails() && billDetails.getBillDetails().size() != 0 ){
							//Start of the loop
								int count=0;
								Iterator listIterator = billDetails.getBillDetails().entrySet().iterator();
						    	while(listIterator.hasNext()){
						    	Map.Entry pair = (Map.Entry) listIterator.next();	
						    	BillDataFetch billData = (BillDataFetch) pair.getValue();
						    	
						    	int billId = billData.getBillId();
						    	long billAmount = billData.getBillAmount();
						    	String billDesc = billData.getBillDescription();
						    	String friendName = billData.getFriendName();
						%>
						
									<div class="panel-heading">
									<span class="label label-default label-pill pull-right">$<%=billAmount%></span>
									<span class="label label-default label-pill pull-right fontcol"><a href="DeleteBill?deleteId=<%=billId %>" class="noline">Delete</a></span>
										<h4>
											<div class="collapseToggle" data-toggle ="collapse" data-parent="#accordion" href="#collapse<%=count%>">
												<a data-toggle="modal" data-target="#billDetailDialog<%=count%>"><%=billDesc %></a>	
											</div>
										</h4>
										<div id = "collapse<%=count%>" class = "panel-collapse collapse in">
									
								        	 <div class = "panel-body">
								            	Lent to <%=friendName %>
								         	 </div>
		    							</div>
									</div><!-- panel-heading close -->	
								
									
    						
    						<% 
    							count = count+1;
					    		}//close of the loop
							}//closing if block
    						%>
    						</div> <!-- panel default close -->
							</div><!-- panel group close -->
															
						
						
					</div>
			<!-- 	</div>	
			</div>	
		</div>	 -->
	
</div>
	

</body>
</html>