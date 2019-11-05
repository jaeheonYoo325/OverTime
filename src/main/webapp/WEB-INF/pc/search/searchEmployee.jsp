'<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    var employeeSearchWhere=window.location.search.slice(window.location.search.indexOf('=')+1);
    $("#searchEmployeeBtn").click(function() {
       $("#searchEmpFrm").attr({
          method:"post",                                         
            action:"/search/searchEmployee.do?employeeSearchWhere="+employeeSearchWhere
     }).submit();
    });
 });
 
 function useThisEmployee(employeeNo,employeeName){
	   var employeeSearchWhere=window.location.search.slice(window.location.search.indexOf('=')+1);
	   if(employeeSearchWhere=='accepter'){
	      opener.document.getElementById("accepterName").value=employeeName
	      opener.document.getElementById("accepter").value=employeeNo
	   }  
	   window.close();
	}
</script>
	<body id="page-top">
		<div id="wrapper">
			<div id="content-wrapper">
		        <div class="card mb-3">
		        	<div class="card-header">
			            <i class="fas fa-table"></i>
			            	직원 조회
			        </div>
			        <div class="card-body">
			        	<form:form id="searchEmpFrm" modelAttribute="employeeDto">
			        		<div id="employeeSearchDiv">
			        			직원 이름 조회 : <input type="text" name="employeeName" id="employeeName"><input type="button" class="btn btn-info" id="searchEmployeeBtn" value="검색"><br><br>
			        		</div>
			        		<div class="table-responsive">
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
										<tr>
							               <td>사원번호</td>
							               <td>사원이름</td>
							               <td>선택</td>
							            </tr>
			        				</thead>
			        				<tbody>
										<c:choose>
											<c:when test="${not empty searchEmployees}">
												<c:forEach items="${searchEmployees}" var="employee">
													<tr>
														<td>${employee.employeeNo}</td>
														<td>${employee.employeeName}</td>
														<td><input type="button" class="btn btn-primary" value="사용" onclick="useThisEmployee(${employee.employeeNo},'${employee.employeeName}')"></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
											   <tr>
											      <td colspan="3">해당 직원이 없습니다.</td>
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