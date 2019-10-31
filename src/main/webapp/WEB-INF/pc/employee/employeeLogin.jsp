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
	<title>로그인</title>
	<!-- Custom fonts for this template-->
	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/vendor/fontawesome-free/css/all.min.css' />">
  	<!-- Custom styles for this template-->
  	<link rel="stylesheet" href="<c:url value='/bootstrapUiTemplate/css/sb-admin.css' />">
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery/jquery.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/bootstrapUiTemplate/vendor/jquery-easing/jquery.easing.min.js' />"></script>
	<script src="<c:url value='/js/common/common.js' />"></script>
	<script src="<c:url value='/js/employee/employeeLogin.js' />"></script>
	
</head>
<body class="bg-dark">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block"><img src="<c:url value='/images/employee/employeeLogin.jpg' />" width="500" height="450"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										 <h1 class="h4 text-gray-900 mb-4">로그인</h1>
									</div>
									<form:form id="employeeLoginFrm" modelAttribute="employeeDto">
										<div class="form-group">
											 <div class="form-label-group">
												<input type="text" id="employeeNo" name="employeeNo" class="form-control" placeholder="사원번호" required="required" autofocus="autofocus">
												<label for="employeeNo">사원번호</label>
												<form:errors id="errorsEmployeeNo" cssStyle="color: red;" path="employeeNo" /><br>
											 </div>
										</div>
										<div class="form-group">
											<div class="form-label-group">
												<input type="password" id="employeePassWord" name="employeePassWord" class="form-control" placeholder="비밀번호" required="required">
												<label for="employeePassWord">비밀번호</label>
												<form:errors id="errorsEmployeePassWord" cssStyle="color: red;" path="employeePassWord" /><br>
											</div>
										</div>
										<input type="button" class="btn btn-primary btn-block" id="employeeLoginBtn" value="로그인">
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>