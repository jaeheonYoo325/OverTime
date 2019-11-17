<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>결재</title>
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
		$("#sidebarToggle").on('click', function(e) {
		    e.preventDefault();
		    $("body").toggleClass("sidebar-toggled");
		    $(".sidebar").toggleClass("toggled");
		});	
	});
	
	function showApprovalDetail(acceptNo)	{
		$("#divToggle" + acceptNo).toggle();
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
			            	결재
			        </div>
			        <div class="card-body">
			        	<div class="table-responsive">
			        		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        			<thead>
			        				<tr>
										<td>접수 No</td>
										<td>기안자</td>
										<td>기안일</td>
										<td>결재내용</td>
										<td>결재요청일</td>
										<td>결재선</td>
										<td>상세내역</td>
									</tr>
			        			</thead>
			        			<tbody>
			        				<c:choose>
										<c:when test="${not empty overTimeApproval}">
											<c:forEach items="${overTimeApproval}" var="overTimeApproval" varStatus="status">												
												<tr id="overTimeApprovalTr">													
													<td>${overTimeApproval.acceptNo}</td>
													<td>${overTimeApproval.drafterName}</td>
													<td>${overTimeApproval.draftDate}</td>
													<td>${overTimeApproval.codeName}</td>
													<td>${overTimeApproval.approvalRequestDate}</td>
													<td>${overTimeApproval.approvalLineName}</td>
													<td><input type="button" value="상세내역" class="btn btn-primary" onclick="showApprovalDetail(${overTimeApproval.acceptNo})"></td>
												</tr>
												<tr id="divToggle${overTimeApproval.acceptNo}" style="display:none;">
													<td colspan="7">
													<c:forEach items="${overTimeRequestOfAcceptNo}" var="overTimeRequestOfAcceptNo">
														<c:if test="${overTimeApproval.acceptNo eq overTimeRequestOfAcceptNo.acceptNo}">
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
												        							<c:forEach items="${overTimeMeasurerofAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo]}" varStatus="status">
																						${overTimeMeasurerofAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo][status.index].employeeName}<br>
																		            </c:forEach>
												        						</td>
												        						<td>
												        							<c:forEach items="${overTimeMeasureDescriptionOfAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo]}" varStatus="status">
																						${overTimeMeasureDescriptionOfAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo][status.index].measureDescription}<br>
																		            </c:forEach>
												        						</td>
												        						<td>${overTimeRequestOfAcceptNo.measureTime}</td>
												        						<td>${overTimeRequestOfAcceptNo.cause}</td>
												        						<td>${overTimeRequestOfAcceptNo.measures}</td>
												        						<td>
												        							<c:forEach items="${overTimeRelatedChainOfAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo]}" varStatus="status">
																						${overTimeRelatedChainOfAcceptNoMap[overTimeRequestOfAcceptNo.acceptNo][status.index].relatedChain}<br>
																		            </c:forEach>
												        						</td>
												        						<td>${overTimeRequestOfAcceptNo.remarks}</td>
												        						<td>${overTimeRequestOfAcceptNo.typeOfProcessing}</td>
												        					</tr>
											        					
										        				</tbody>
															</table>
															<input type="button" value="결재하기" class="btn btn-primary" onclick="location.href='/employee/myOverTimeDoApprovaling.do/${overTimeRequestOfAcceptNo.acceptNo}'">
															<input type="button" value="반려하기" class="btn btn-danger" onclick="location.href='/employee/MyOverTimeDoReturning.do/${overTimeRequestOfAcceptNo.acceptNo}'">
														</c:if>
									        		</c:forEach>
													</td>
												</tr>																								
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="7">내역이 없습니다</td>
											</tr>
										</c:otherwise>
									</c:choose>
			        			</tbody>
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