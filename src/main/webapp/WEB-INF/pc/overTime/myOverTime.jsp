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
	<title>myOverTime</title>
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
<body id="page-top">
<jsp:include page="/WEB-INF/pc/common/header.jsp" />
	<div id="wrapper">
		<jsp:include page="/WEB-INF/pc/common/sidebar.jsp" />
		<div id="content-wrapper">
			<div class="container-fluid">				
				<div class="card mb-3">
					<div class="card-header">
			            <i class="fas fa-table"></i>
			            	myOverTime
			        </div>
			        <div class="card-body">
						<div class="table-responsive">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
			        					<tr>
			        						<td>임직원번호</td>
			        						<td>임직원이름</td>
			        						<td>총누적근로시간</td>
			        						<td>연장근로시간</td>
			        						<td>야간근로시간</td>
			        						<td>휴일근로시간(8시간이상)</td>
			        						<td>휴일근로시간(8시간미만)</td>
			        					</tr>
			        					<tr>
			        						<td>${overTimeofEmployeeDto.employeeNo}</td>
			        						<td>${overTimeofEmployeeDto.employeeName}</td>
			        						<td>${overTimeofEmployeeDto.sumOfOverTime}(Hr)</td>
			        						<td>${overTimeofEmployeeDto.extensionOverTime}(Hr)</td>
			        						<td>${overTimeofEmployeeDto.nightTimeOvertime}(Hr)</td>
			        						<td>${overTimeofEmployeeDto.holidayOvertimeOfExceed8Hours}(Hr)</td>
			        						<td>${overTimeofEmployeeDto.holidayOvertimeOfNotExceed8Hours}(Hr)</td>
			        					</tr>	
			        				</thead>
			        			</table>
		        		</div>
			        </div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/pc/common/footer.jsp" />
</body>
</html>