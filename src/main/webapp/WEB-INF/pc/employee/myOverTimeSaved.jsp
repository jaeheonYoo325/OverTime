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
	<title>저장함</title>
	<!-- Custom fonts for this template-->
	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/vendor/fontawesome-free/css/all.min.css' />">
  	<!-- Custom styles for this template-->
  	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/css/sb-admin.css' />">
  	<!-- Page level plugin CSS-->
  	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/vendor/datatables/dataTables.bootstrap4.css' />">
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery/jquery.min.js' />"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery-easing/jquery.easing.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/js/sb-admin.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/datatables/jquery.dataTables.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/datatables/dataTables.bootstrap4.js' />"></script>
	<script src="<c:url value='/js/common/jquery.bpopup.min.js' />"></script>
	<style type="text/css">
		#popupLayer {display:none;border:5px solid #cccccc;margin:0;padding:5px;background-color:#ffffff;z-index:5;}
        #popupLayer .b-close {position:absolute;top:10px;right:25px;color:#f37a20;font-weight:bold;cursor:hand;}
        #popupLayer .popupContent {margin:0;padding:0;text-align:center;border:0;}
		#popupLayer .popupContent iframe {width:1000px;height:800px;border:0;padding:0px;margin:0;z-index:10;}
	</style>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#sidebarToggle").on('click', function(e) {
		    e.preventDefault();
		    $("body").toggleClass("sidebar-toggled");
		    $(".sidebar").toggleClass("toggled");
		});	
	});
	
//   	function openPopup(src) {
//   		console.log(src);
//   		var param = src;
//   		var url = "/employee/showMyApprovalReturnedDetail.do/";  		

//   		console.log(url + param);
//         $("#popupLayer").bPopup({
//         	modalClose: false,
//             content:'iframe',
//             iframeAttr:'frameborder="auto"',
//             iframeAttr:'frameborder="0"',
//             contentContainer:'.popupContent',
//             loadUrl: url + param,            
//             onOpen: function() {
//             	$("#popupLayer").append("<div class='popupContent'></div><div class='b-close'><img src='<c:url value='/images/employee/layerPopupCancel.jpg'/>' width='30' height='30'></div>");            	
//             }, 
//             onClose: function() {
//             	$("#popupLayer").html("");
//             }
//         },
//         function() {
//         });
//     }
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
			            	저장함
			        </div>
			        <div class="card-body">
			        	<div class="table-responsive">
			        		<div id="popupLayer"></div>
				        		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="font-size: small;">
				        			<thead>
										<tr>
											<td>No</td>
											<td>요청날짜</td>
											<td>접수시각</td>
											<td>접수자</td>
											<td>발신자</td>
											<td>전화번호</td>
											<td>접수내용</td>
											<td>조치자 및 조치 내용</td>
<!-- 											<td>조치내용</td> -->
											<td>조치시간</td>
											<td>원인</td>
											<td>대책</td>
											<td>관련체인</td>
											<td>비고</td>
											<td>처리형태</td>
											<td>수정</td>
										</tr>
				        			</thead>
				        			<tbody>
				        				<c:choose>
											<c:when test="${not empty overTimeSavedOfAcceptNo}">
												<c:forEach items="${overTimeSavedOfAcceptNo}" var="overTimeSavedOfAcceptNo">
											<tr>
												<td>${overTimeSavedOfAcceptNo.acceptNo}</td>	
												<td>${overTimeSavedOfAcceptNo.acceptDate}</td>
												<td>${overTimeSavedOfAcceptNo.acceptTime}</td>
												<td>${overTimeSavedOfAcceptNo.employeeName}</td>
												<td>${overTimeSavedOfAcceptNo.caller}</td>
												<td>${overTimeSavedOfAcceptNo.phoneNumber}</td>
												<td>${overTimeSavedOfAcceptNo.acceptDescription}</td>
				        						<td>
													<table border="0">
														<c:forEach items="${overTimeMeasurerofAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo]}" varStatus="status">
														<tr>
															<td>
																${overTimeMeasurerofAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].employeeName}
															</td>
															<td>
																${overTimeMeasureDescriptionOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].measureDescription}
															</td>
														</tr>
														</c:forEach>
													</table>
												</td>												
<!-- 												<td> -->
<%-- 													<c:forEach items="${overTimeMeasurerofAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo]}" varStatus="status"> --%>
<%-- 														<c:out value="${overTimeMeasurerofAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].employeeName}"></c:out><br> --%>
<%-- 													</c:forEach> --%>
<!-- 												</td> -->
<!-- 												<td> -->
<%-- 													<c:forEach items="${overTimeMeasureDescriptionOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo]}" varStatus="status"> --%>
<%-- 															${overTimeMeasureDescriptionOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].measureDescription}<br> --%>
<%-- 													</c:forEach> --%>
<!-- 												</td> -->
												<td>${overTimeSavedOfAcceptNo.measureTime}</td>
												<td>${overTimeSavedOfAcceptNo.cause}</td>
												<td>${overTimeSavedOfAcceptNo.measures}</td>
												<td>
													<c:forEach items="${overTimeRelatedChainOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo]}" varStatus="status">
														<c:out value="${overTimeRelatedChainOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].relatedChain}"></c:out>(<c:out value="${overTimeRelatedChainOfAcceptNoMap[overTimeSavedOfAcceptNo.acceptNo][status.index].chainName}"></c:out>)<br>
													</c:forEach>
												</td>
												<td>${overTimeSavedOfAcceptNo.remarks}</td>
												<td>${overTimeSavedOfAcceptNo.typeOfProcessing}</td>
												<td>
													<a href="<c:url value='/overTime/overTimeUpdate.do/${overTimeSavedOfAcceptNo.acceptNo}' />"  class="btn btn-primary">수정 페이지</a>
<%-- 													<input type="button" value="상세내역" class="btn btn-primary" onclick="openPopup(${overTimeSavedOfAcceptNo.acceptNo})"> --%>
												</td>		
												
											</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="16">내역이 없습니다</td>
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