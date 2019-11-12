<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>근무외시간요청</title>
	<!-- Custom fonts for this template-->
	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/vendor/fontawesome-free/css/all.min.css' />">
  	<!-- Custom styles for this template-->
  	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/css/sb-admin.css' />">
  	<!-- Page level plugin CSS-->
  	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/vendor/datatables/dataTables.bootstrap4.css' />">
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery/jquery.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery-easing/jquery.easing.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/js/sb-admin.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/datatables/jquery.dataTables.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/datatables/dataTables.bootstrap4.js' />"></script>
</head>
<script>
$(document).ready(function() {
    $("#overTimeRequestUpdateBtn").click(function() {
	     $("#overTimeRequestDailFrm").attr({
	          method:"post",                                         
	            action:"/employee/showMyApprovalReturnedDetail.do"
	     }).submit();
    });  

	var lastMeasurer = $("#lastMeasurer").val();
	var lastMeasureDescription = $("#lastMeasureDescription").val();
	var i=lastMeasurer;
	
	if(i==lastMeasurer) {
		i=lastMeasurer;
		i*=1;
	}
	else {
		i=-1;
		i*=1;
	}
	
	$('.removeMeasurerAndMeasureDescription').on('click', function () {     	  
		$(".divMeasurerAndMeasureDescription").html("");
		i = -1;
	});

	$('.addMeasurerAndMeasureDescription').click (function () {
		
	 	i=i+1;
	 	$('.divMeasurerAndMeasureDescription').append (           
			$("<input type='text' name='measurerName"+i+"'id='measurerName"+i+"' value='조치자' readonly='readonly'><input type='hidden' name='measurer"+i+"' id='measurer"+i+"'><input type='button' class='btn btn-primary' value='검색' onclick='searchMeasurer("+i+")'><input type='text' name='measureDescription"+i+"' id='measureDescription"+i+"' value='조치내용'><br>")
	    );
	});
	
	
	var lastRelatedChain = $("#lastRelatedChain").val();
	var j=lastRelatedChain;
	
	if(j==lastRelatedChain) {
		j=lastRelatedChain;
		j*=1;
	}
	else {
		j=-1;
		j*=1;
	}
	
	$('.removeRelatedChain').on('click', function () {     	  
		$(".divRelatedChain").html("");
		j = -1;
	});

	$('.addRelatedChain').click (function () {
		
	 	j=j+1;
	 	$('.divRelatedChain').append (           
	 		$("<input type='text' name='relatedChain"+j+"' id='relatedChain"+j+"' placeholder='관련체인검색'><br>")
	    );
	});
	
	
	
	
    $("#sidebarToggle").on('click', function(e) {
	    e.preventDefault();
	    $("body").toggleClass("sidebar-toggled");
	    $(".sidebar").toggleClass("toggled");
	 });
 });
</script>
<script>
  	function searchEmployee(employeeSearchWhat){
	   window.open("/search/searchEmployee.do?employeeSearchWhat="+employeeSearchWhat,"임직원검색", "width=1000, height=800");
 	}
  	
    function searchMeasurer(measurerNo){
    	window.open("/search/searchMeasurer.do?measurerNo="+measurerNo,"임직원검색", "width=1000, height=800");
    }
  	
  	function searchCaller(){
 	   window.open("/search/searchCaller.do","발신자검색", "width=1000, height=800");
  	}
  	
  	function searchChain(){
  		window.open("/search/searchChain.do","관련체인검색", "width=1000, height=800");
  	}
</script>
<body id="page-top">
	<div id="wrapper">
		<div id="content-wrapper">
			<div class="container-fluid">				
				<div class="card mb-3">
					<div class="card-header">
			            <i class="fas fa-table"></i>
			            	근무외시간요청
			        </div>
			        <div class="card-body">
						<div class="table-responsive">
		        			<form:form id="overTimeRequestDailFrm" modelAttribute="OverTimeDto" name="overTimeRequestDailFrm">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
			        					<tr>
			        						<td>No</td>
			        						<td colspan="6">${overTimeRequestOfAcceptNo.acceptNo}<input type="hidden" name="acceptNo" id="acceptNo" value="${overTimeRequestOfAcceptNo.acceptNo}"></td>		        						
			        					</tr>
			        					<tr>
			        						<td>일자</td>
			        						<td><input type="Date" id="acceptDate" name="acceptDate" value="${overTimeRequestOfAcceptNo.acceptDate}"></td>
			        					</tr>
			        					<tr>
			        						<td>접수시각</td>
			        						<td><input type="time" id="acceptTime" name="acceptTime" value="${overTimeRequestOfAcceptNo.acceptTime}"></td>
			        					</tr>
			        					<tr>
			        						<td>접수자</td>
			        						<td><input type="text" id="accepterName" name="accepterName" value="${overTimeRequestOfAcceptNo.employeeName}" readonly="readonly">
			        							<input type="hidden" id="accepter" name="accepter" value="${overTimeRequestOfAcceptNo.accepter}">
			        						    <input type="button" class="btn btn-primary" value="검색" onclick="searchEmployee('accepter')">
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>발신자</td>
			        						<td><input type="text" id="caller" name="caller" value="${overTimeRequestOfAcceptNo.caller}" readonly="readonly">
			        							<input type="button" class="btn btn-primary" value="검색" onclick="searchCaller()">
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>전화번호</td>
			        						<td><input type="text" id="phoneNumber" name="phoneNumber" value="${overTimeRequestOfAcceptNo.phoneNumber}" readonly="readonly" ></td>
			        					</tr>
			        					<tr>
			        						<td>접수내용</td>
			        						<td><input type="text" id="acceptDescription" name="acceptDescription" value="${overTimeRequestOfAcceptNo.acceptDescription}" style="width: 1000px"></td>
			        					</tr>
			        					<tr>
			        						<td>조치자 및 조치내용</td>
			        						<td><input type="button" class="addMeasurerAndMeasureDescription btn btn-info" value="추가"><input type='button' class='removeMeasurerAndMeasureDescription btn btn-danger' id='removeMeasurerAndMeasureDescription' value='전체삭제'>
			        						    <div class="divMeasurerAndMeasureDescription">
			        						    	<c:forEach items="${overTimeMeasurerOfAcceptNo}" varStatus="status">
														<input type="text" name="measurerName${status.index}" id="measurerName${status.index}" value="<c:out value="${overTimeMeasurerOfAcceptNo[status.index].employeeName}" />" readonly="readonly">
														<input type="hidden" name="measurer${status.index}" id="measurer${status.index}" value="<c:out value="${overTimeMeasurerOfAcceptNo[status.index].measurer}" />" readonly="readonly">
														<input type="button" class="btn btn-primary" value="검색" onclick="searchMeasurer(${status.index})">
														<input type="text" name="measureDescription${status.index}" id="measureDescription${status.index}" value="<c:out value="${overTimeMeasureDescriptionOfAcceptNo[status.index].measureDescription}" />"><br>							
													</c:forEach>
													<input type="hidden" name="lastMeasurer" id="lastMeasurer" value="${fn:length(overTimeMeasurerOfAcceptNo)-1}">
													<input type="hidden" name="lastMeasureDescription" id="lastMeasureDescription" value="${fn:length(overTimeMeasureDescriptionOfAcceptNo)-1}">
										        </div>
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>조치시간</td>
			        						<td><input type="number" step="0.1" id="measureTime" name="measureTime" value="${overTimeRequestOfAcceptNo.measureTime}">Hr</td>
			        					</tr>
			        					<tr>
			        						<td>원인</td>
			        						<td><input type="text" id="cause" name="cause" value="${overTimeRequestOfAcceptNo.cause}"></td>
			        					</tr>
			        					<tr>
			        						<td>대책</td>
			        						<td><input type="text" id="measures" name="measures" value="${overTimeRequestOfAcceptNo.measures}"></td>
			        					</tr>
			        					<tr>
			        						<td>관련체인</td>
			        						<td><input type="button" class="addRelatedChain btn btn-info" value="추가"><input type='button' class='removeRelatedChain btn btn-danger' id='removeRelatedChain' value='전체삭제'>
			        						    <div class="divRelatedChain">
			        						   		<c:forEach items="${overTimeRelatedChainOfAcceptNo}" varStatus="status">
			        						   			<input type="text" name="relatedChain${status.index}" id="relatedChain${status.index}" value="<c:out value="${overTimeRelatedChainOfAcceptNo[status.index].relatedChain}"/>"><br>
								            		</c:forEach>
								            		<input type="hidden" name=lastRelatedChain id="lastRelatedChain" value="${fn:length(overTimeRelatedChainOfAcceptNo)-1}">
										        </div>
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>비고</td>
			        						<td><input type="text" id="remarks" name="remarks" value="${overTimeRequestOfAcceptNo.remarks}"></td>
			        					</tr>
			        					<tr>
			        						<td>처리형태</td>
			        						<td><input type="text" id="typeOfProcessing" name="typeOfProcessing" value="${overTimeRequestOfAcceptNo.typeOfProcessing}"></td>
			        					<tr>
			        				</thead>
			        			</table>
								<input type="button" class="btn btn-primary" id="overTimeRequestUpdateBtn" value="재요청하기">
		        			</form:form>		        		
		        		</div>
			        </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>