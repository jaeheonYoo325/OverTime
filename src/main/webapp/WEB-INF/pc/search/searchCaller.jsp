<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>직원 조회 페이지</title>
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
	<%-- <script src="<c:url value='/js/search/searchEmployee.js' />"></script> --%>
</head>
<script>
$(document).ready(function() {
    $("#searchCallerBtn").click(function() {
       $("#searchCallerFrm").attr({
          method:"post",                                         
            action:"/search/searchCaller.do"
     }).submit();
    });
 });
 
 function useThisInterPhone(companyType,teamName,partName,PhoneNumber){
	      opener.document.getElementById("caller").value=partName
	      opener.document.getElementById("phoneNumber").value=PhoneNumber
	   window.close();
	}
</script>
	<body id="page-top">
		<div id="wrapper">
			<div id="content-wrapper">
		        <div class="card mb-3">
		        	<div class="card-header">
			            <i class="fas fa-table"></i>
			            	발신자조회
			        </div>
			        <div class="card-body">
			        	<form:form id="searchCallerFrm" modelAttribute="InterPhoneDto">
			        		<div id="CallerSearchDiv">
			        			발신자조회  : <input type="text" name="phoneNumber" id="phoneNumber"><input type="button" class="btn btn-info" id="searchCallerBtn" value="검색"><br><br>
			        		</div>
			        		<div class="table-responsive">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
										<tr>
							               <td>직영/협력업체</td>
							               <td>팀</td>
							               <td>담당파트</td>
							               <td>전화번호</td>
							               <td>사용</td>
							            </tr>
			        				</thead>
			        				<tbody>
										<c:choose>
											<c:when test="${not empty interPhone}">
												<c:forEach items="${interPhone}" var="interPhone">
													<tr>
														<td>${interPhone.companyType}</td>
														<td>${interPhone.teamName}</td>
														<td>${interPhone.partName}</td>
														<td>${interPhone.phoneNumber}</td>
														<td><input type="button" class="btn btn-primary" value="사용" onclick="useThisInterPhone('${interPhone.companyType}','${interPhone.teamName}','${interPhone.partName}','${interPhone.phoneNumber}')"></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
											   <tr>
											      <td colspan="3">해당 내용이 없습니다.</td>
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
	</body>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
    	<i class="fas fa-angle-up"></i>
  	</a>
</html>