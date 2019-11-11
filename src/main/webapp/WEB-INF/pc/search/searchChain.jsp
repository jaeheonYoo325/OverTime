<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>부문 조회 페이지</title>
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
    $("#searchChainBtn").click(function() {
       $("#searchChainFrm").attr({
          method:"post",                                         
            action:"/search/searchChain.do"
     }).submit();
    });
 });
 
 function useThisChain(chainId,chainName){
	
	parent.document.getElementById("relatedChain").value = chainId;
	parent.document.getElementById("relatedChainName").value = chainName;
	
	parent.$("#popupLayer").bPopup().close(); 
	parent.$("#popupLayer").html("");    
  }
 
//  function useThisChain(chainId,chainName){
//      opener.document.getElementById("relatedChain").value=chainId
//      opener.document.getElementById("relatedChainName").value=chainName
//      window.close();
//   }
</script>
	<body id="page-top">
		<div id="wrapper">
			<div id="content-wrapper">
		        <div class="card mb-3">
		        	<div class="card-header">
			            <i class="fas fa-table"></i>
			            	부문 조회
			        </div>
			        <div class="card-body">
			        	<form:form id="searchChainFrm" modelAttribute="chainTableDto">
			        		<div id="chainSearchDiv">
			        			부문 조회 : <input type="text" name="chainName" id="chainName"><input type="button" class="btn btn-info" id="searchChainBtn" value="검색"><br><br>
			        		</div>
			        		<div class="table-responsive">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
										<tr>
							               <td>부문번호</td>
							               <td>부문이름</td>
							               <td>선택</td>
							            </tr>
			        				</thead>
			        				<tbody>
										<c:choose>
											<c:when test="${not empty searchChain}">
												<c:forEach items="${searchChain}" var="chain">
													<tr>
														<td>${chain.chainId}</td>
														<td>${chain.chainName}</td>
														<td><input type="button" class="btn btn-primary" value="사용" onclick="useThisChain('${chain.chainId}','${chain.chainName}')"></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
											   <tr>
											      <td colspan="3">해당 부문이 없습니다.</td>
											   </tr>
											</c:otherwise>
										</c:choose>
			        				</tbody>
			        			</table>
			        		</div>
						</form:form>
			        </div>
		        </div>
			</div>
		</div>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top">
	    	<i class="fas fa-angle-up"></i>
	  	</a>
	</body>
</html>