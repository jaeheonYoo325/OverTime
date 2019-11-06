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
	          <span>overTime</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
	          <h6 class="dropdown-header">OverTime</h6>
	          <a class="dropdown-item" href="<c:url value='/overTime/overTimeRequest.do' />">OverTime요청</a>
	          <a class="dropdown-item" href="<c:url value='/overTime/overTimeList.do' />">OverTime요청현황</a>
	          <a class="dropdown-item" href="<c:url value='/overTime/myOverTime.do' />">MyOverTime</a>
	        </div>
	      </li>
	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa fa-edit"></i>
	          <span>1차 결재선</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
	          <h6 class="dropdown-header">1차 결재선</h6>
	          <a class="dropdown-item" href="#">1차 결재</a>
	          <a class="dropdown-item" href="#">2차 결재내역</a>
	        </div>
	      </li>
	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa fa-check-square"></i>
	          <span>2차 결재선</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
	          <h6 class="dropdown-header">2차 결재선</h6>
	          <a class="dropdown-item" href="#">2차 결재</a>
	          <a class="dropdown-item" href="#">2차 결재내역</a>
	        </div>
	      </li>
	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fas fa-fw fa-folder"></i>
	          <span>결재함</span>
	        </a>
	        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
	          <h6 class="dropdown-header">완료</h6>
	          <a class="dropdown-item" href="#">완료함</a>
	          <div class="dropdown-divider"></div>
	          <h6 class="dropdown-header">반려</h6>
	          <a class="dropdown-item" href="#">반려함</a>
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