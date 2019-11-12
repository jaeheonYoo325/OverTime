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
	<title>상세 내역</title>
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
	<script src="<c:url value='/js/deploy/deployUpdate.js' />"></script>
</head>
<body id="page-top">
		<div id="wrapper">
			<div id="content-wrapper">
		        <div class="card mb-3">
		        	<div class="card-header">
			            <i class="fas fa-table"></i>
			            	상세 내역
			        </div>
			        <div class="card-body">
		        		<div class="table-responsive">
		        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		        				<thead>
		        					<tr>
		        						<td>접수No</td>
		        						<td>접수날짜</td>
		        						<td>접수시각</td>
		        						<td>접수자</td>
		        						<td>발신자</td>
		        						<td>전화번호</td>
		        						<td>접수내용</td>
		        						<td>조치자</td>
		        						<td>조치내용</td>
		        						<td>조치시간</td>
		        						<td>원인분석 및 현상파악</td>
		        						<td>대책</td>
		        						<td>관련체인</td>
		        						<td>비고</td>
		        						<td>처리형태</td>
		        					</tr>
		        				</thead>
		        				<tbody>
		        					<tr>
		        						<td>${overTimeRequestOfAcceptNo.acceptNo}</td>
		        						<td>${overTimeRequestOfAcceptNo.acceptDate}</td>
		        						<td>${overTimeRequestOfAcceptNo.acceptTime}</td>
		        						<td>${overTimeRequestOfAcceptNo.employeeName}</td>
		        						<td>${overTimeRequestOfAcceptNo.caller}</td>
		        						<td>${overTimeRequestOfAcceptNo.phoneNumber}</td>
		        						<td>${overTimeRequestOfAcceptNo.acceptDescription}</td>
		        						<td>
		        							<c:forEach items="${overTimeMeasurerOfAcceptNo}" varStatus="status">
												${overTimeMeasurerOfAcceptNo[status.index].employeeName}<br>
								            </c:forEach>
		        						</td>
		        						<td>
		        							<c:forEach items="${overTimeMeasureDescriptionOfAcceptNo}" varStatus="status">
												${overTimeMeasureDescriptionOfAcceptNo[status.index].measureDescription}<br>
								            </c:forEach>
		        						</td>
		        						<td>${overTimeRequestOfAcceptNo.measureTime}</td>
		        						<td>${overTimeRequestOfAcceptNo.cause}</td>
		        						<td>${overTimeRequestOfAcceptNo.measures}</td>
		        						<td>
		        							<c:forEach items="${overTimeRelatedChainOfAcceptNo}" varStatus="status">
												${overTimeRelatedChainOfAcceptNo[status.index].relatedChain}(${overTimeRelatedChainOfAcceptNo[status.index].chainName})<br>
								            </c:forEach>
		        						</td>
		        						<td>${overTimeRequestOfAcceptNo.remarks}</td>
		        						<td>${overTimeRequestOfAcceptNo.typeOfProcessing}</td>
		        					</tr>		        					
		        				</tbody>
		        			</table>
		        			<c:if test="${overTimeApprovalDetailCode eq 'myOverTimeApprovalDetail'}">
								<input type="button" value="결재하기" class="btn btn-primary" onclick="location.href='/employee/myOverTimeDoApprovaling.do/${overTimeRequestOfAcceptNo.acceptNo}'">
								<input type="button" value="반려하기" class="btn btn-danger" onclick="location.href='/employee/MyOverTimeDoReturning.do/${overTimeRequestOfAcceptNo.acceptNo}'">
							</c:if>   
		        		</div>
			        </div>
		        </div>
			</div>
		</div>
	</body>
</html>