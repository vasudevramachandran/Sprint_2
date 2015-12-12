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
<title>Insert title here</title>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css" />
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
 --%></head>
<body >
<%@include file="sidenavbar.jsp" %>

<%
	if(null != request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ)){
		FetchGroupDetails fetchDetails = (FetchGroupDetails) request.getAttribute(CommonConstants.JSP_FETCH_GRP_OBJ);
		int groupId = fetchDetails.getGroupId();
		String groupName = fetchDetails.getGroupName();
		String groupDesc = fetchDetails.getGroupDesc();
		int numOfPpl  = fetchDetails.getGroupMemberMap().size();
		session.setAttribute("jspSessionGroupDetail", fetchDetails.getGroupMemberMap());
%>
<form name="splitbillform" id="splitbillform" action="SplitBill" method="post" enctype="application/x-www-form-urlencoded">
		
		<!-- hidden variables to pass to server on form submit -->
		
		<input type="hidden" id="<%=CommonConstants.EMAIL %>" name="<%=CommonConstants.EMAIL %>" value="<%=session.getAttribute(CommonConstants.EMAIL) %>">
		<input type="hidden" id="<%=CommonConstants.JSP_BILL_SPLIT %>"name="<%=CommonConstants.JSP_BILL_SPLIT %>">
	 	<input type="hidden" id="<%=CommonConstants.JSP_GROUP_ID %>" name="<%=CommonConstants.JSP_GROUP_ID %>" value="<%=groupId%>">
	 	<input type="hidden" id="<%=CommonConstants.JSP_SPLIT_TYPE%>" name="<%=CommonConstants.JSP_SPLIT_TYPE%>" value="0">
	 	<input  type="hidden" id="<%=CommonConstants.JSP_NUM_USER%>" name="<%=CommonConstants.JSP_NUM_USER%>" value = "<%=numOfPpl%>">
	 	<!-- End of hidden variables -->
	
<div class="col-sm-3 block-inline">
               <div class="list-group">
                 <a class="" data-toggle="modal" data-target="#createBill"><span class="glyphicon glyphicon-plus-sign"></span></a>
               </div>
</div>

	<div id="createBill" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		          <h1 class="text-center">Add A Bill To Group - <%=groupName %></h1>
		      </div>
		      <div class="modal-body">
		      	<div class="list-group">
		      		<ul class="list-group">
		      			
		      				<label class="radio-inline">Split Bill By:</label>
		      				<label class="radio-inline"><input type="radio" id="percent" name="rdsplit"><strong>Percent</strong></label>
		      				<label class="radio-inline"><input type="radio" id="equally" name="rdsplit" checked="true"><strong>Equally</strong></label>
		      			
		      			
		      		</ul>
		      	</div>
		          <form class="form col-md-12 center-block" action="#">
		            <div class="form-group">
		              <input type="text" id="<%=CommonConstants.JSP_BILL_DESC%>" name="<%=CommonConstants.JSP_BILL_DESC%>" class="form-control input-lg" placeholder="Bill Name">
		            </div>
		            <div class="form-group ">
		              <input type="text" id="<%=CommonConstants.JSP_BILL_AMT%>" name="<%=CommonConstants.JSP_BILL_AMT%>" class="form-control input-lg" placeholder="Bill Amount ($)">
		            </div>
		            <div class="hidden" id="togglequal">
		            </div>
		            <div class="hidden" id="togglediv">
		           		<%
		           		HashMap friendsList = fetchDetails.getGroupMemberMap();
					  	if(null != friendsList && friendsList.size() != 0 ){
		           		%>
		           			
		           			<table class="table table-striped">
		           				<thead>
		           					<tr>
		           						<th>Name</th>
		           						<th>Percentage</th>
		           					</tr>
		           				</thead>
		           				<tbody>
		           					<%
		           					Iterator listIterator = friendsList.entrySet().iterator();
		    						int i = 0;
		    				    	while(listIterator.hasNext()){
		    				    	Map.Entry pair = (Map.Entry) listIterator.next();	
		    				    	i = i+1;
		    				    	if(!pair.getValue().equals(session.getAttribute(CommonConstants.USER_NAME))){
		           					%>
		           						<tr>
		           							<td class="active"><%= pair.getValue() %>
										        <input type="hidden" name="<%=CommonConstants.JSP_FRND_EMAIL%>" value=<%= pair.getKey() %>>
										        <input type="hidden" name="<%=CommonConstants.JSP_FRND_NAME%>" value=<%= pair.getValue() %>>
									        </td>
									        <td class="danger"><input type="text"  class= "form-control input-sm col-sm-4" id="<%=CommonConstants.JSP_SHARE %>" name="<%=CommonConstants.JSP_SHARE%>"> </td>
		           						</tr>
		           					<% 
		    				    	}//if loop close
		    				    	}//iterate loop close
		           					%>
		           					
		           				</tbody>
		           			</table>
		           		<%
		           		}//close if block
		           		%>
		           		
		            </div>
		            
		            <div class="form-group">
		              <button class="btn btn-primary btn-lg btn-block" id="billSubmit" >Split and Save</button>
		             <!--  <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span> -->
		            </div>
		          </form>
		      </div>
		      <div class="modal-footer">
		          <div class="col-md-12">
		          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				  </div>	
		      </div>
		  	</div>
		</div><!-- modal content close -->
		</div><!-- <!-- modal div close -->


<div class="size6" align="center" >
		<!-- <div class="span12">
			<div class="row-fluid">
				<div class="span12"> -->	
					<div class = "list-group">
						
						<a href="#" class="list-group-item active"><strong><%=groupName %></strong><span></span></a>
						<!-- Button to open modal window to display the users and delete users -->
						<button type ="button" class="btn btn-secondary btn-lg btn-block" data-toggle="modal" data-target="#memberModal" >Members
							<span class="badge" align="right"><%=numOfPpl %></span>
						</button>
						
							<!-- modal window content -->
						<div id="memberModal" class="modal fadde" role="dialog">	
						  <div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title"> Member List </h4>
								</div><!-- div close modal header -->
								<!-- Modal Body -->
									<div class="modal-body">
										<%
											HashMap friendList = fetchDetails.getGroupMemberMap();
											if(null!=friendList && friendList.size()!=0){
												Iterator memberIterator = friendList.entrySet().iterator();
												while(memberIterator.hasNext()){
												Map.Entry pair = (Map.Entry) memberIterator.next();
										%>
												<ul class="list-group">
													<li class="list-group-item">
														<%=pair.getValue() %>
														<span class="label label-default label-pill pull-right"><a href="DeleteMemFromGrp?userId=<%=pair.getKey()%>&groupId=<%=groupId%>">Delete</a></span>
													</li>
												</ul>
										
										<%
												}//while loop close
											}//friend list if condition close
										%>
									</div>
								<!-- MOdal Body close -->
								
								<!-- Modal footer  -->
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
								<!-- Modal Footer ends -->
							</div><!-- modal content div close -->
							<!-- END- modal window content -->
						 </div><!-- Modal dialog sm close -->
						</div><!-- Member modal div close -->
						<!-- Modal button operations end -->
						
						<div	class="panel-group" id="accordion">
						<div class="panel panel-default">
						
						<%
							HashMap groupBillDetail = fetchDetails.getGroupBillDetails();
							if(null != groupBillDetail && groupBillDetail.size()!=0){
							//Start of the loop
								int count = 0;
								Iterator listIterator = groupBillDetail.entrySet().iterator();
						    	while(listIterator.hasNext()){
						    	Map.Entry pair = (Map.Entry) listIterator.next();	
						    	GroupBillDetails billObj = (GroupBillDetails) pair.getValue();
						    	int groupid = billObj.getBillId();
						%>
						
									<div class="panel-heading">
									<span class="label label-default label-pill pull-right">$<%=billObj.getBillShare() %></span>
										<h4>
											<div class="collapseToggle" data-toggle ="collapse" data-parent="#accordion" href="#collapse<%=count%>">
												<a data-toggle="modal" data-target="#billDetailDialog<%=count%>"><%=billObj.getBillName() %></a>	
											</div>
										</h4>
										<div id = "collapse<%=count%>" class = "panel-collapse collapse in">
									
								        	 <div class = "panel-body">
								            	Shared with <%=numOfPpl %> member(s).
								         	 </div>
		    							</div>
									</div><!-- panel-heading close -->	
									<%
										/* HashMap billDrilledDetail = fetchDetails.getGroupBillDetails();
									
										if(null != billDrilledDetail && billDrilledDetail.size()!=0){
											Iterator detailsIterator = billDrilledDetail.entrySet().iterator();
									    	while(detailsIterator.hasNext()){
									    	Map.Entry detailPair = (Map.Entry) detailsIterator.next();	
									    	GroupBillDetails detailsObj = (GroupBillDetails) pair.getValue();
									    	int detailGrpId = billObj.getBillId();
									    	if(groupid == detailGrpId){ */
									%>
										
									<!-- modal views for each bill item -->
										<div id="billDetailDialog<%=count%>" class="modal fade" role="dialog">
											<div class="modal-dialog">
												<div class="modal-content">	
													<div class="modal-header">
														<h4><%=billObj.getBillName() %></h4>
														<h4>Owed to <%=billObj.getAddedByName() %></h4>
													</div>
													<div class="modal-body">
														<ul class="list-group">
														<li class="list-group-item  btn btn-info btn-lg active">
															<%=billObj.getOwedByName()%>
															<span class="label label-default label-pill pull-right noline"><a href="DeleteFromGrp?billId=<%=billObj.getBillId()%>&groupId=<%=billObj.getGroupId()%>">Delete Bill</a></span>
														</li>
														</ul>
													
													</div><!-- modal body end -->
													<div class="modal-footer">
														<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
													</div><!-- modal footer end -->
												</div><!-- modal content end -->
											
											</div><!-- modal dialog end -->
										</div><!-- modal dialog div end -->
									<%
									    	//}//Inside If block
									    //	}//While loop ends
						    			//}//If block ends
									%>	
									<!--  modal views end for each bill item -->
									
									
    						
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


</form>	
<%
	}
%>
</body>



<script>
	$(document).ready(function(){
		$("#percent").click(function(){
			//alert("percent");
			$("#togglediv").removeClass("hidden");
		//	$("#togglediv").addClass("showdivshare");
			$("#billSplitType").val(1);
			//$("#togequal").removeClass("showdivequal");
			$("#togequal").addClass("hidden");
			//alert($("#billSplitType").val());
		});
		$("#equally").click(function(){
			//alert("test");
			//$("#togglediv").removeClass("showdivshare");
			$("#togglediv").addClass("hidden");
			$("#billSplitType").val(0);
			$("#togequal").removeClass("hidden");
			//$("#togequal").addClass("showdivequal");
			//alert($("#billSplitType").val());
		});
		
		$("#billSubmit").click(function(){
			//alert("called");
			if($("#billSplitType").val() == 1){
				//alert("inloop");
				collateData();
				//alert($("#billSplitVal").val());
			}
			
			
			//$("#billSplitType").val(1); 
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
		
		
		//alert("called after");
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