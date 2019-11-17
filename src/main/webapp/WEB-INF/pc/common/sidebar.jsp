<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
		<ul class="sidebar navbar-nav">
		  <li class="nav-item active">
	        <a class="nav-link" href="<c:url value='/main/main.do' />">
	          <i class="fas fa-fw fa-tachometer-alt"></i>
	          <span>메인</span>
	        </a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa fa-file"></i>
	          <span>연장근로실적</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
<!-- 	          <h6 class="dropdown-header">연장근로실적</h6> -->
	          <a class="dropdown-item" href="<c:url value='/overTime/overTimeRequest.do' />">연장근로실적등록</a>
	          <a class="dropdown-item" href="<c:url value='/overTime/overTimeList.do' />">연장근로실적조회</a>
	          <a class="dropdown-item" href="<c:url value='/overTime/myOverTime.do' />">My 연장근로실적조회</a>
	        </div>
	      </li>
	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa fa-edit"></i>
	          <span>결재선</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
<!-- 	          <h6 class="dropdown-header">결재선</h6> -->
	          <a class="dropdown-item" href="<c:url value='/employee/myOverTimeWillApproval.do' />">결재</a>
	          <a class="dropdown-item" href="<c:url value='/employee/myOverTimeApproved.do' />">결재내역</a>
	          <a class="dropdown-item" href="<c:url value='/employee/myOverTimeCompleted.do' />">완료함</a>
	          <a class="dropdown-item" href="<c:url value='/employee/myOverTimeReturned.do' />">반려함</a>
	          <a class="dropdown-item" href="<c:url value='/employee/myOverTimeSaved.do' />">저장함</a>
	        </div>
	      </li>
	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa fa-male"></i>
	          <span>관리자</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
	          <h6 class="dropdown-header">직원 관리</h6>
	          <a class="dropdown-item" href="<c:url value='/employee/employeeRegist.do' />">직원 등록</a>	          
	        </div>
	      </li>	      
		</ul>