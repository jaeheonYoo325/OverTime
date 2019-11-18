<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">	
	<title>OverTime 요청 현황 페이지</title>
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
<script type="text/javascript">
   $(document).ready(function() {
     
	   $("#sidebarToggle").on('click', function(e) {
		    e.preventDefault();
		    $("body").toggleClass("sidebar-toggled");
		    $(".sidebar").toggleClass("toggled");
		});
	   
       $("#categoryChain").change(function(){
    	   commonOverTimeListSubmit(); 
        });
        
        $("#categoryAcceptDate").change(function(){
        	commonOverTimeListSubmit();
         });
        
        $("#categoryStatus").change(function(){
      	 commonOverTimeListSubmit();
 			
         });
        
       $("#searchBtn").click(function(e) {
    	   e.preventDefault();
    	   var searchType = $("#searchType").val();
    	   var searchKeyword = $("#searchKeyword").val();
    	   
    	   if ( searchType != "검색타입" && searchKeyword == "") {
    		   alert("검색타입과 검색 키워드 모두 사용해주세요.");
    		   return;
    	   }
    	   if ( searchType == "검색타입" && searchKeyword != "") {
    		   alert("검색타입과 검색 키워드 모두 사용해주세요.");
    		   return;
    	   }
    	   commonOverTimeListSubmit();    	  
       });       
    });
   
   function commonOverTimeListSubmit() {
	   $("#listFrm").attr({
           method:"post",                                         
             action:"/overTime/overTimeList.do"
      }).submit();
   }
</script>

</head>
<body id="page-top" style="overflow:hidden;">
	<jsp:include page="/WEB-INF/pc/common/header.jsp" />
	<div id="wrapper">
		<jsp:include page="/WEB-INF/pc/common/sidebar.jsp" />
		<div id="content-wrapper">
			<div class="container-fluid">				 
				 <div class="card mb-3">
					<div class="card-header">
			            <i class="fas fa-table"></i>
			            	OverTime 요청 현황
			        </div>
			       <div style="white-space:nowrap; overflow:auto; height: 800px; width: 100%">
			        <div class="card-body">
			        	<form:form name="listFrm" id="listFrm">
			        		<select name="searchType" id="searchType">
								<c:forEach items="${masterCodeOfSearchTypeMap[categoryTypeDto.searchTypeString]}" varStatus="status">
									<option value="<c:out value='${masterCodeOfSearchTypeMap[categoryTypeDto.searchTypeString][status.index].codeName}'></c:out>" <c:if test="${categoryTypeDto.searchType eq masterCodeOfSearchTypeMap[categoryTypeDto.searchTypeString][status.index].codeName}">selected="selected"</c:if>>${masterCodeOfSearchTypeMap[categoryTypeDto.searchTypeString][status.index].codeName}</option>
								</c:forEach>
							</select> 
							<input type="text" name="searchKeyword" id="searchKeyword" value="${categoryTypeDto.searchKeyword}">
							<input type="button" class="btn btn-primary" value="검색" id="searchBtn">
							
							<div class="table-responsive">
				        		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="font-size: small;">
				        			<thead>
										<tr>
											<td>No</td>
											<td><select name="categoryAcceptDate" id="categoryAcceptDate">
													<c:forEach items="${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateAcceptDateString]}" varStatus="status">
														<option value="<c:out value='${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateAcceptDateString][status.index].codeName}'></c:out>" <c:if test="${categoryTypeDto.categoryAcceptDate eq categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateAcceptDateString][status.index].codeName}">selected="selected"</c:if>>${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateAcceptDateString][status.index].codeName}</option>
													</c:forEach>	
												</select>
											</td>
											<td>접수시각</td>
											<td>접수자</td>
											<td>발신자</td>
											<td>전화번호</td>
											<td>접수내용</td>
											<td>조치자 및 조치내용</td>
											<td>조치시간</td>
											<td>원인</td>
											<td>대책</td>
											<td><select name="categoryChain" id="categoryChain">
													<option value="관련체인" <c:if test="${categoryTypeDto.categoryChain eq '관련체인'}">selected="selected"</c:if>>관련체인</option> 
													<c:forEach items="${chain}" varStatus="status">
														<option value="<c:out value='${chain[status.index].chainId}'></c:out>" <c:if test="${categoryTypeDto.categoryChain eq chain[status.index].chainId}">selected="selected"</c:if>>${chain[status.index].chainName}</option>
													</c:forEach>  
												</select>
											</td>
											<td>비고</td>
											<td>처리형태</td>
											<td><select name="categoryStatus" id="categoryStatus">
												<c:forEach items="${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString]}" varStatus="status">
													<option value="<c:out value='${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeValue}'></c:out>" <c:if test="${categoryTypeDto.categoryStatus eq categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeValue}">selected="selected"</c:if>>${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeName}</option>
												</c:forEach>
											</select></td>
										</tr>
				        			</thead>
				        			<tbody>
										<c:forEach items="${overTime}" var="overTime">
											<tr>
												<td>${overTime.acceptNo}</td>	
												<td>${overTime.acceptDate}</td>
												<td>${overTime.acceptTime}</td>
												<td>${overTime.employeeName}</td>
												<td>${overTime.caller}</td>
												<td>${overTime.phoneNumber}</td>
												<td>${overTime.acceptDescription}</td>
												<td>
													<table border="0">
															<c:forEach items="${measurerMap[overTime.acceptNo]}" varStatus="status">
															<tr>
															<td>
																${measurerMap[overTime.acceptNo][status.index].employeeName}
															</td>
															<td>
																${measureDescriptionMap[overTime.acceptNo][status.index].measureDescription}
															</td>
															</tr>
															</c:forEach>
													</table>
												</td>
												<td>${overTime.measureTime}</td>
												<td>${overTime.cause}</td>
												<td>${overTime.measures}</td>
												<td>
													<c:forEach items="${relatedChainMap[overTime.acceptNo]}" varStatus="status">
														<c:out value="${relatedChainMap[overTime.acceptNo][status.index].relatedChain}"></c:out>(<c:out value="${relatedChainMap[overTime.acceptNo][status.index].chainName}"></c:out>)<br>
													</c:forEach>
												</td>
												<td>${overTime.remarks}</td>
												<td>${overTime.typeOfProcessing}</td>
												<td>
													<c:forEach items="${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString]}" begin="1" varStatus="status">
														<input type="radio" name="statusCode${overTime.acceptNo}"  <c:if test="${overTime.statusCode eq categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeValue}">checked="checked"</c:if>disabled="disabled">
                                          				<c:if test="${overTime.statusCode eq categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeValue}"><font color="red">${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeName}</font></c:if>
                                          				<c:if test="${overTime.statusCode ne categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeValue}">${categoryMasterCodesOfCodeTypeMap[categoryTypeDto.cateStatusString][status.index].codeName}</c:if>
													</c:forEach>		
												</td>
											</tr>
										</c:forEach>				        			
				        			</tbody>
				        		</table>
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