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
   <title>사원등록</title>
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
   <script src="<c:url value='/js/common/common.js' />"></script>
   <script src="<c:url value='/js/employee/employeeRegist.js' />"></script>
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	

</head>
<script>
   $(document).ready(function() {
      $("#sidebarToggle").on('click', function(e) {
          e.preventDefault();
          $("body").toggleClass("sidebar-toggled");
          $(".sidebar").toggleClass("toggled");
      });   
   });
   function showMyApprovalReturnedDetail(thisDeployNo){
      var deployNo=thisDeployNo;
      window.open("/employee/showMyApprovalReturnedDetail.do/" + deployNo,"상세보기", "width=1000, height=800");
   }
</script>
<script>
   function employeeNoChanging(obj){
	   document.getElementById("employeeNoCheckFlag").value = "false";
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
                        	사원등록
                 </div>
                 <div class="card-body">
                    <div class="table-responsive">
                      <form:form id="employeeRegistFrm" modelAttribute="employeeDto">
                       <div id="errorsDiv">
							<form:errors id="errorsEmployeeNo" cssStyle="color: red;" path="employeeNo" />
							<form:errors id="errorsEmployeePassWord" cssStyle="color: red;" path="employeePassWord" />
							<form:errors id="errorsEmployeePassWordConfirm" cssStyle="color: red;" path="employeePassWordConfirm" />
							<form:errors id="errorsEmployeeName" cssStyle="color: red;" path="employeeName" />
							<form:errors id="errorsEmployeePhoneNumber" cssStyle="color: red;" path="employeePhoneNumber" />
							<form:errors id="errorsEmployeePostcode" cssStyle="color: red;" path="employeePostcode" />
							<form:errors id="errorsEmployeeCommonaddress" cssStyle="color: red;" path="employeeCommonaddress" />
							<form:errors id="errorsEmployeeDetailaddress" cssStyle="color: red;" path="employeeDetailaddress" />
							<form:errors id="errorsEmployeeCompanyPhoneNumber" cssStyle="color: red;" path="employeeCompanyPhoneNumber" />
							<form:errors id="errorsEmployeeEmail" cssStyle="color: red;" path="employeeEmail" />
							<form:errors id="errorsEmployeeJoinDate" cssStyle="color: red;" path="employeeJoinDate" />
<%-- 							<form:errors id="errorsDepartmentNo" cssStyle="color: red;" path="departmentNo" /> --%>
							<form:errors id="errorsPositionNo" cssStyle="color: red;" path="positionNo" />
                       </div>
                       <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                          <thead>
                             <tr>
                                <td>사원번호</td>
                                <td>
                                	<input type="text" id="employeeNo" name="employeeNo" class="form-control" placeholder="사원번호" value="${employeeDto.employeeNo}" onchange="employeeNoChanging(this)"/>
                                	<input type="button" id="employeeNoDuplicateBtn" name="employeeNoDuplicateBtn" class="btn btn-primary" value="중복체크"/> 
                                	<input type="hidden" id="employeeNoCheckFlag" value="false">
                                </td>
                             </tr>
                             <tr>
                                <td>비밀번호</td>
                                <td>
                                	<input type="text" id="employeePassWord" name="employeePassWord" class="form-control" placeholder="비밀번호" value="${employeeDto.employeePassWord}">
                                </td>
                             </tr>
                             <tr>
                                <td>비밀번호 확인</td>
                                <td>
                                	<input type="text" id="employeePassWordConfirm" name="employeePassWordConfirm" class="form-control" placeholder="비밀번호 확인" value="${employeeDto.employeePassWord}">
                                </td>
                             </tr>
                             <tr>
                                <td>사원이름</td>
                                <td>
                                	<input type="text" id="employeeName" name="employeeName" class="form-control" placeholder="사원이름" value="${employeeDto.employeeName}">
                                </td>
                             </tr>
                             <tr>   
                                <td>전화번호</td>
                                <td>
                                	<input type="text" id="employeePhoneNumber" name="employeePhoneNumber" class="form-control" placeholder="전화번호" value="${employeeDto.employeePhoneNumber}">
                                </td>
                             </tr>
                             <tr>   
                                <td>우편번호</td>
                                <td>
                                	<input type="text" id="employeePostcode" name="employeePostcode" placeholder="우편번호" value="${employeeDto.employeePostcode}" />
                                	<input type="button" id="addressBtn" name="addressBtn" class="btn btn-primary" value="우편번호 찾기" />
                                </td>
                             </tr>
                             <tr>   
                                <td>주소<td>
                                	<input type="text" id="employeeCommonaddress" name="employeeCommonaddress" class="form-control" placeholder="주소" value="${employeeDto.employeeCommonaddress}" />
                                </td>
                             </tr>
                             <tr>   
                                <td>상세주소</td>
                                <td>
                                	<input type="text" id="employeeDetailaddress" name="employeeDetailaddress" class="form-control" placeholder="상세주소" value="${employeeDto.employeeDetailaddress}" />
                                </td>
                             </tr>
                             <tr>   
                                <td>회사전화번호</td>
                                <td>
                                	<input type="text" id="employeeCompanyPhoneNumber" name="employeeCompanyPhoneNumber" class="form-control" placeholder="전화번호" value="${employeeDto.employeeCompanyPhoneNumber}" />
                                </td>
                             </tr>
                             <tr>   
                                <td>이메일</td>
                                <td>
                                	<input type="text" id="employeeEmail" name="employeeEmail" class="form-control" placeholder="이메일" value="${employeeDto.employeeEmail}" />
                                </td>
                             </tr>
                             <tr>   
                                <td>입사일</td>
                                <td>
                                	<input type="date" id="employeeJoinDate" name="employeeJoinDate" class="form-control" placeholder="입사일" value="${employeeDto.employeeJoinDate}" />
                                </td>
                             </tr>
                             <tr>   
                                <td>부서</td>
                                <td>
                                	<input type="text" id="depName" name="depName" class="form-control" placeholder="부서명" />
                                    <input type="button" class="btn btn-primary" value="부서 조회" id="searchDepartmentPopupBtn" />
                                    <input type="hidden" id="depNo" name="depNo" value="부서번호" />
                                </td>
                             </tr>
                             <tr>   
                                <td>직급번호</td>
                                <td>
                                	<input type="text" id="positionNo" name="positionNo" class="form-control" placeholder="직급번호" />
                                </td>
                             </tr>   
                          </thead>
                       </table>
                       <div class="modal-footer" align="center">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          <input type="button" id="employeeRegistBtn" class="btn btn-primary" value="사원등록">
                       </div>
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