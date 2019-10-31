<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>

	 <nav class="navbar navbar-expand navbar-dark bg-dark static-top">
	 	<a class="navbar-brand mr-1" href="<c:url value='/main/main.do' />">OverTime</a>
	
	    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
	      <i class="fas fa-bars"></i>
	    </button>
	    
	    <ul class="navbar-nav ml-auto ml-md-10">	    		
			<li class="nav-item dropdown no-arrow active">
		        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <i class="fas fa-user-circle fa-fw"></i>
		        </a>
		        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
		          <a class="dropdown-item" href="#">${sessionScope._USER_.employeeName}님</a>
		          <a class="dropdown-item" href="#">비밀번호 변경</a>
		          <div class="dropdown-divider"></div>
		          <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">로그아웃</a>
		        </div>
		    </li>
		</ul>
	 </nav>
	 
	   <!-- Logout Modal-->
	  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog" role="document">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h5 class="modal-title" id="exampleModalLabel">로그아웃</h5>
	          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
	            <span aria-hidden="true">×</span>
	          </button>
	        </div>
	        <div class="modal-body">로그아웃 하시겠습니까?</div>
	        <div class="modal-footer">
	          <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
	          <a class="btn btn-primary" href="<c:url value='/employee/employeeLogout.do' />">로그아웃</a>
	        </div>
	      </div>
	    </div>
	  </div>

