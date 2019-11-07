<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    $("#overTimeRequestBtn").click(function() {
	     $("#overTimeRequestFrm").attr({
	          method:"post",                                         
	            action:"/overTime/overTimeRequest.do"
	     }).submit();
    });  

    var i = -1;
    $('.addMeasurerAndMeasureDescription').click (function () {
  		i=i+1;
        $('.divMeasurerAndMeasureDescription').append (           
			$("<input type='text' name='measurerName"+i+"'id='measurerName"+i+"' value='조치자' readonly='readonly'><input type='hidden' name='measurer"+i+"' id='measurer"+i+"'><input type='button' class='btn btn-primary' value='검색' onclick='searchMeasurer("+i+")'><input type='text' name='measureDescription"+i+"' id='measureDescription"+i+"' value='조치내용'><br>")
        );
        
        MeasurerAndMeasureDescriptionSize = i;
        isMeasurerAndMeasureDescription = false;
        $('.removeMeasurerAndMeasureDescription').on('click', function () {     	  
            $(".divMeasurerAndMeasureDescription").html("");
            isMeasurerAndMeasureDescription = true;
            i = -1;
        });
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
<jsp:include page="/WEB-INF/pc/common/header.jsp" />
	<div id="wrapper">
		<jsp:include page="/WEB-INF/pc/common/sidebar.jsp" />
		<div id="content-wrapper">
			<div class="container-fluid">				
				<div class="card mb-3">
					<div class="card-header">
			            <i class="fas fa-table"></i>
			            	근무외시간요청
			        </div>
			        <div class="card-body">
						<div class="table-responsive">
		        			<form:form id="overTimeRequestFrm" modelAttribute="OverTimeDto" name="overTimeRequestFrm">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
			        					<tr>
			        						<td>일자</td>
			        						<td><input type="Date" id="acceptDate" name="acceptDate"></td>
			        					</tr>
			        					<tr>
			        						<td>접수시각</td>
			        						<td><input type="time" id="acceptTime" name="acceptTime"></td>
			        					</tr>
			        					<tr>
			        						<td>접수자</td>
			        						<td><input type="text" id="accepterName" name="accepterName" readonly="readonly">
			        							<input type="hidden" id="accepter" name="accepter">
			        						    <input type="button" class="btn btn-primary" value="검색" onclick="searchEmployee('accepter')">
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>발신자</td>
			        						<td><input type="text" id="caller" name="caller" readonly="readonly">
			        							<input type="button" class="btn btn-primary" value="검색" onclick="searchCaller()">
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>전화번호</td>
			        						<td><input type="text" id="phoneNumber" name="phoneNumber" readonly="readonly"></td>
			        					</tr>
			        					<tr>
			        						<td>접수내용</td>
			        						<td><input type="text" id="acceptDescription" name="acceptDescription" style="width: 1000px"></td>
			        					</tr>
			        					<tr>
			        						<td>조치자 및 조치내용</td>
			        						<td><input type="button" class="addMeasurerAndMeasureDescription btn btn-info" value="추가"><input type='button' class='removeMeasurerAndMeasureDescription btn btn-danger' id='removeMeasurerAndMeasureDescription' value='전체삭제'>
			        						    <div class="divMeasurerAndMeasureDescription">
										        </div>
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>조치시간</td>
			        						<td><input type="number" step="0.1" id="measureTime" name="measureTime">Hr</td>
			        					</tr>
			        					<tr>
			        						<td>원인</td>
			        						<td><input type="text" id="cause" name="cause"></td>
			        					</tr>
			        					<tr>
			        						<td>대책</td>
			        						<td><input type="text" id="measures" name="measures"></td>
			        					</tr>
			        					<tr>
			        						<td>관련체인</td>
			        						<td><input type="text" id="relatedChainName" name="relatedChainName" readonly="readonly"><input type="hidden" id="relatedChain" name="relatedChain">
			        							<input type="button" class="btn btn-primary" value="검색" onclick="searchChain()">
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>비고</td>
			        						<td><input type="text" id="remarks" name="remarks"></td>
			        					</tr>
			        					<tr>
			        						<td>처리형태</td>
			        						<td><input type="text" id="typeOfProcessing" name="typeOfProcessing"></td>
			        					<tr>
			        				</thead>
			        			</table>
								<input type="button" class="btn btn-primary" id="overTimeRequestBtn" value="요청">
		        			</form:form>		        		
		        		</div>
			        </div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/pc/common/footer.jsp" />
</body>
</html>