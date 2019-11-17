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
	<title>근무외시간요청</title>
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
	<style>
       .ui-autocomplete {
            max-height: 200px;
            overflow-y: auto;
            /* prevent horizontal scrollbar */
            overflow-x: hidden;
            /* add padding to account for vertical scrollbar */
            padding-right: 20px;
        } 
	</style>
</head>

<script>
$(document).ready(function() {
	var measurerAndMeasureDescriptionSize = 0;
	var relatedChainSize = 0;
	var isRelatedChain = true;
	var searchMeasurerCount = 0;
	var searchRelatedChainCount = 0;
	
	setAcceptDateAndTime();
	searchCallerAjax();
	
    $("#overTimeRequestBtn").click(function() {
    	
    	var acceptDate = $("#acceptDate").val();
    	var acceptTime = $("#acceptTime").val();
    	var employeeName = $("#employeeName").val();
    	var caller = $("#caller").val();
    	var acceptDescription = $("#acceptDescription").val();
    	var measureTime = $("#measureTime").val();
    	var cause = $("#cause").val();
    	var measures = $("#measures").val();
    	var relatedChain = $("#relatedChain").val();
    	
    	if ( acceptDate == "") {
    		alert("접수 날짜를 입력해주세요.");
    		return;
    	}
    	if ( acceptTime == "") {
    		alert("접수 시간을 입력해주세요.");
    		return;
    	}
    	if ( employeeName == "") {
    		alert("접수자를 조회해서 선택해주세요.");
    		return;
    	}
    	if ( caller == "") {
    		alert("발신자를 조회해서 선택해주세요.");
    		return;
    	}
    	if ( acceptDescription == "") {
    		alert("접수내용을 입력해주세요.");
    		return;
    	}
    	
    	var k = 0;
    	for (k = 0; k <= measurerAndMeasureDescriptionSize; k++) {
    		if ( $("#measurer" + k).val() == "") {
    			alert((k+1) + "번째 조치자를 검색해서 사용해주세요.");
    			return;
    		}
    		if ( $("#measureDescription" + k).val() == "") {
    			alert((k+1) + "번째 조치 내용을 입력해주세요.");
    			return;
    		}
    	}
    	
    	if ( measureTime == "" || measureTime == 0) {
    		alert("조치시간을 입력해주세요.");
    		return;
    	}
    	if ( cause == "") {
    		alert("원인 내용을 입력해주세요.");
    		return;
    	}
    	if ( measures == "") {
    		alert("대책 내용을 입력해주세요.");
    		return;
    	}
    	
    	if ( isRelatedChain == true ) {
    		alert("관련체인은 필수 입력 값입니다. 추가 버튼 클릭해서 관련 체인을 조회해주세요.");
    		return;
    	}
    	
    	for (k = 0; k <= relatedChainSize; k++) {
    		if ( $("#relatedChain" + k).val() == "") {
    			alert((k+1) + "번째 관련체인를 검색해서 사용해주세요.");
    			return;
    		}
    	}
    	
		$("#overTimeRequestFrm").attr({
			method:"post",                                         
			action:"/overTime/overTimeRequest.do"
		}).submit();
    });  

	var lastMeasurer = $("#lastMeasurer").val();
	var lastMeasureDescription = $("#lastMeasureDescription").val();
	var i=lastMeasurer;
	
	if(i==lastMeasurer) {
		i=lastMeasurer;
		i*=1;
	}
	else {
		i=-1;
		i*=1;
	}
    
	$('.removeMeasurerAndMeasureDescription').on('click', function () {     	  
		$(".divMeasurerAndMeasureDescription").html("");
		$('.divMeasurerAndMeasureDescription').append($("<c:set var='count' value='0' />"));
		$('.divMeasurerAndMeasureDescription').append($("<input type='text' name='measurerName${count}'id='measurer${count}' value='${sessionScope._USER_.employeeName}' readonly='readonly'>"));
		$('.divMeasurerAndMeasureDescription').append($("<input type='hidden' name='measurer${count}' id='measurer${count}' value='${sessionScope._USER_.employeeNo}' ><br>"));
		$('.divMeasurerAndMeasureDescription').append($("<textarea id='measureDescription${count}' name='measureDescription${count}' placeholder='조치내용입력' cols='100' rows='5'></textarea><br>"));
		$('.divMeasurerAndMeasureDescription').append($("<input type='hidden' name='lastMeasurer' id='lastMeasurer' value='${count}'>"));
		$('.divMeasurerAndMeasureDescription').append($("<input type='hidden' name='lastMeasureDescription' id='lastMeasureDescription' value='${count}'>"));
    	
		i = 0;
	});

	$('.addMeasurerAndMeasureDescription').click (function () {
		
	 	i=i+1;
	 	measurerAndMeasureDescriptionSize = i;
	 	searchMeasurerCount = measurerAndMeasureDescriptionSize;
	 	
	 	$('.divMeasurerAndMeasureDescription').append($("<div class='ui-widget'>"));
	 	$('.divMeasurerAndMeasureDescription').append($("<input type='text' name='searchMeasurer"+i+"'id='searchMeasurer"+i+"' placeholder='조치자검색'>"));
	 	$('.divMeasurerAndMeasureDescription').append($("<input type='hidden' name='measurerName"+i+"'id='measurerName"+i+"'>"));
	 	$('.divMeasurerAndMeasureDescription').append($("<input type='hidden' name='measurer"+i+"' id='measurer"+i+"'>"));	 	
	 	$('.divMeasurerAndMeasureDescription').append($("<div class='measurerDiv' id='measurerDiv'></div>"));
	 	$('.divMeasurerAndMeasureDescription').append($("<textarea id='measureDescription"+i+"' name='measureDescription"+i+"' placeholder='조치내용을 입력하세요.' cols='100' rows='5'></textarea><br>"));
	 	$('.divMeasurerAndMeasureDescription').append($("</div>"));
	 	
	 	searchMeasurerAjax(searchMeasurerCount);
	});
	
    var j = -1;   
    $('.removeRelatedChain').on('click', function () {          
        $(".divRelatedChain").html("");
        isRelatedChain = true;
        j = -1;
    });
    
    $('.addRelatedChain').click (function () {
        j=j+1;
        relatedChainSize = j;
        searchRelatedChainCount = relatedChainSize;
        $('.divRelatedChain').append ($("<div class='ui-widget'>"));
        $('.divRelatedChain').append ($("<input type='text' name='searchRelatedChain"+j+"' id='searchRelatedChain"+j+"' placeholder='관련체인검색'>"));
        $('.divRelatedChain').append ($("<input type='hidden' name='relatedChain"+j+"' id='relatedChain"+j+"'><br>"));
        $('.divRelatedChain').append ($("</div>"));
                
        isRelatedChain = false;
        
        searchRelatedChainAjax(searchRelatedChainCount);
    });
    
   
    function searchCallerAjax() {
    	$("#searchCaller").on("keyup",function() {
    		var caller = $(this).val();
    		var searchCallerArray = [];
    		var i = 0;
    		
    		$.post("/overTime/searchCallerAjax.do"
    				,{
    					"caller" : caller
    					
    				}
    				, function(response){
    										
    					for(i=0; i < response.interPhone.length; i++) {
    						searchCallerArray.push(response.interPhone[i].partName+"-"+response.interPhone[i].phoneNumber);
    					}
    					
    					$("#searchCaller").autocomplete(
    							{
    								source: searchCallerArray,								
    								max:10, 
    								scroll:true,
    								select : function(event, ui) {
    									
    									var autoCompleteValue = ui.item.value;
    									var subStringCallerArray = autoCompleteValue.split("-");
    									var inputCaller = subStringCallerArray[0];
    									var inputPhoneNumber = subStringCallerArray[1];
    									
    									$("#caller").val(inputCaller);
    									$("#phoneNumber").val(inputPhoneNumber);
    								}
    							}
    					);
    					
    			});
    				
    	});
    }
	
    function searchMeasurerAjax(searchMeasurerCount) {
    	$("#searchMeasurer" + searchMeasurerCount).on("keyup", function() {
    		var measurer = $(this).val();
    		var searchMeasurerArray = [];
    		var i = 0;
    		
    		$.post("/overTime/searchMeasurerAjax.do"
    				,{
    					"measurer" : measurer
    					
    				}
    				, function(response){
    										
    					for(i=0; i < response.searchEmployees.length; i++) {
    						searchMeasurerArray.push(response.searchEmployees[i].employeeName+"-"+response.searchEmployees[i].employeeNo);
    					}
    					
    					$("#searchMeasurer" + searchMeasurerCount).autocomplete(
    							{
    								source: searchMeasurerArray,								
    								max:10, 
    								scroll:true,
    								select : function(event, ui) {
    									
    									var autoCompleteValue = ui.item.value;
    									var subStringMeasurerArray = autoCompleteValue.split("-");
    									var inputMeasurerName = subStringMeasurerArray[0];
    									var inputMeasurer = subStringMeasurerArray[1];
    									
    									$("#measurerName" + searchMeasurerCount).val(inputMeasurerName);
    									$("#measurer" + searchMeasurerCount).val(inputMeasurer);
    								}
    							}
    					);
    			});
    		
    	});	
    }
    
    function searchRelatedChainAjax(searchRelatedChainCount) {
    	$("#searchRelatedChain" + searchRelatedChainCount).on("keyup", function() {
    		var relatedChain = $(this).val();
    		var searchRelatedChainArray = [];
    		var i = 0;
    		
    		$.post("/overTime/searchRelatedChainAjax.do"
    				,{
    					"relatedChain" : relatedChain
    				}
    				, function(response){
    										
    					for(i=0; i < response.searchChain.length; i++) {
    						searchRelatedChainArray.push(response.searchChain[i].chainName+"-"+response.searchChain[i].chainId);
    					}
    					
    					$("#searchRelatedChain" + searchRelatedChainCount).autocomplete(
    							{
    								source: searchRelatedChainArray,								
    								max:10, 
    								scroll:true,
    								select : function(event, ui) {
    									
    									var autoCompleteValue = ui.item.value;
    									var subStringRelatedChainArray = autoCompleteValue.split("-");
    									var inputMeasurerName = subStringRelatedChainArray[0];
    									var inputRelatedChain = subStringRelatedChainArray[1];
    									
    									$("#relatedChain" + searchRelatedChainCount).val(inputRelatedChain);
    								}
    							}
    					);
    			});    		
    	});
    }
		
    $("#sidebarToggle").on('click', function(e) {
	    e.preventDefault();
	    $("body").toggleClass("sidebar-toggled");
	    $(".sidebar").toggleClass("toggled");
	 });
    
  	function setAcceptDateAndTime() {
		var date = new Date();
		var nowDate = date.toLocaleDateString();
		var subStringYear = nowDate.substring(0,4);
		var subStringMonth = nowDate.substring(6,8);
		var subStringDay = nowDate.substring(10,12);
		nowDate = subStringYear + "." + subStringMonth + "." + subStringDay
		
		var nowTime = date.toTimeString();		
		var subStringNowTime = nowTime.substring(0,5);

		$("#acceptDate").val(nowDate);
		$("#acceptTime").val(subStringNowTime);
	}
  		
 });
</script>
<script>
//   	function searchEmployee(employeeSearchWhat){
// 	   window.open("/search/searchEmployee.do?employeeSearchWhat="+employeeSearchWhat,"임직원검색", "width=1000, height=800");
//  	}
  	
//     function searchMeasurer(measurerNo){
//     	window.open("/search/searchMeasurer.do?measurerNo="+measurerNo,"임직원검색", "width=1000, height=800");
//     }
  	
//   	function searchCaller(){
//  	   window.open("/search/searchCaller.do","발신자검색", "width=1000, height=800");
//   	}
  	
//   	function searchChain(){
//   		window.open("/search/searchChain.do","관련체인검색", "width=1000, height=800");
//   	}
  	
//   	function openPopup(src) {
//   		console.log(typeof src);
//   		var param = "";
//   		var url = "";
//   		if ( typeof src == "string" && src == "accepter" ) {
//   			url = "/search/searchEmployee.do?employeeSearchWhat=";
//   			param = "accepter";
//   		} else if ( typeof src == "string" && src == "caller" ){
//   			url = "/search/searchCaller.do";
//   			param = "";
//   		} else if ( typeof src == "number" ) {
//   			url = "/search/searchMeasurer.do?measurerNo=";
//   			param = src;
//   		}
//   		else if ( typeof src == "string" && src =="chain") {
//   			url = "/search/searchChain.do";
//   			param = "";
//   		}
  		
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
			            	근무외시간요청
			        </div>
			        <div class="card-body">			        
						<div class="table-responsive">
		        			<form:form id="overTimeRequestFrm" modelAttribute="overTimeDto" name="overTimeRequestFrm">
		        				<div id = "errorsDiv">
		        					<form:errors id="errorsAcceptDate" cssStyle="color: red;" path="acceptDate" />
		        					<form:errors id="errorsAcceptTime" cssStyle="color: red;" path="acceptTime" />
		        					<form:errors id="errorsAccepter" cssStyle="color: red;" path="accepter" />
		        					<form:errors id="errorsCaller" cssStyle="color: red;" path="caller" />
		        					<form:errors id="errorsPhoneNumber" cssStyle="color: red;" path="phoneNumber" />
		        					<form:errors id="errorsAcceptDescription" cssStyle="color: red;" path="acceptDescription" />
		        					<form:errors id="errorsMeasureTime" cssStyle="color: red;" path="measureTime" />
		        					<form:errors id="errorsCause" cssStyle="color: red;" path="cause" />
		        					<form:errors id="errorsMeasures" cssStyle="color: red;" path="measures" />
<%-- 		        					<form:errors id="errorsRelatedChain" cssStyle="color: red;" path="relatedChain" /> --%>
		        				</div>
<!-- 		        				<div id="popupLayer"></div> -->
								
			        			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			        				<thead>
			        					<tr>
			        						<td>일자</td>			        						
			        						<td><input type="text" id="acceptDate" name="acceptDate" value="${overtimeDto.acceptDate}" readonly="readonly"></td>
			        						<td>접수시각</td>
			        						<td><input type="text" id="acceptTime" name="acceptTime" value="${overtimeDto.acceptTime}" readonly="readonly"></td>
			        					</tr>
			        					<tr>
			        						<td>접수자</td>
			        						<td colspan="3">
			        							<input type="text" id="employeeName" name="employeeName" value="${sessionScope._USER_.employeeName}" readonly="readonly">
			        							<input type="hidden" id="accepter" name="accepter" value="${sessionScope._USER_.employeeNo}" readonly="readonly">
			        						</td>
			        					</tr>
			        					<!-- form -->
			        					<tr>
			        						<td>발신자</td>
			        						<td colspan="3">
			        							<div class="ui-widget">
			        							<input type="text" id="searchCaller" name="searchCaller" value="${overtimeDto.caller}" >
			        							<input type="hidden" id="caller" name="caller" value="${overtimeDto.caller}">
			        							<input type="hidden" id="phoneNumber" name="phoneNumber" value="${overtimeDto.phoneNumber}">
<!-- 			        							<input type="button" class="btn btn-primary" value="검색" onclick="openPopup('caller')"> -->
<!-- 			        							<input type="button" class="btn btn-primary" value="검색" onclick="searchCaller()"> -->
												</div>
			        						</td>
			        					</tr>
<!-- 			        					<tr> -->
<!-- 			        						<td>전화번호</td> -->
<%-- 			        						<td><input type="text" id="phoneNumber" name="phoneNumber" value="${overtimeDto.phoneNumber}" readonly="readonly"></td> --%>
<!-- 			        					</tr> -->
			        					<tr>
			        						<td>접수내용</td>			        						
			        						<td colspan="3"><textarea id="acceptDescription" name="acceptDescription" placeholder="접수 내용을 입력하세요." cols="100" rows="5">${overtimeDto.acceptDescription}</textarea></td>
			        					</tr>			        					
			        					<tr>
			        						<td>조치자 및 조치내용</td>
			        						<td colspan="3"><input type="button" class="addMeasurerAndMeasureDescription btn btn-info" value="추가"><input type='button' class='removeMeasurerAndMeasureDescription btn btn-danger' id='removeMeasurerAndMeasureDescription' value='전체삭제'>
			        						    <div class="divMeasurerAndMeasureDescription">
			        						    	<c:set var="count" value="0" />
			        						    	<input type="text" name="measurerName${count}"id="measurer${count}" value="${sessionScope._USER_.employeeName}" readonly="readonly">
			        						    	<input type="hidden" name="measurer${count}" id="measurer${count}" value="${sessionScope._USER_.employeeNo}" ><br>
			        						    	<textarea id="measureDescription${count}" name="measureDescription${count}" placeholder="조치내용을 입력하세요." cols="100" rows="5"></textarea><br>
			        						    	<input type="hidden" name="lastMeasurer" id="lastMeasurer" value="${count}">
													<input type="hidden" name="lastMeasureDescription" id="lastMeasureDescription" value="${count}">
										        </div>
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>조치시간</td>
			        						<td colspan="3"><input type="number" min="0" step="0.1" id="measureTime" name="measureTime" value="${overtimeDto.measureTime}" >Hr</td>
			        					</tr>
			        					<tr>
			        						<td>원인</td>
			        						<td colspan="3"><textarea id="cause" name="cause" placeholder="원인 내용을 입력하세요." cols="100" rows="5">${overtimeDto.cause}</textarea></td>
			        					</tr>												        					
			        					<tr>
			        						<td>대책</td>
			        						<td colspan="3"><textarea id="measures" name="measures" placeholder="대책 내용을 입력하세요." cols="100" rows="5">${overtimeDto.measures}</textarea></td>
			        					</tr>
			        					<!-- form -->			        					
			        					<tr>
			        						<td>관련체인</td>
			        						<td><input type="button" class="addRelatedChain btn btn-info" value="추가"><input type='button' class='removeRelatedChain btn btn-danger' id='removeRelatedChain' value='전체삭제'>
			        						    <div class="divRelatedChain">
										        </div>
			        						</td>
			        					</tr>
			        					<tr>
			        						<td>비고</td>
			        						<td colspan="3"><input type="text" id="remarks" name="remarks" placeholder="비고 내용을 입력하세요."></td>
			        					</tr>
			        					<tr>
			        						<td>처리형태</td>
			        						<td colspan="3"><input type="text" id="typeOfProcessing" name="typeOfProcessing" placeholder="처리형태 내용을 입력하세요."></td>
			        					<tr>
			        				</thead>
			        			</table>
			        			<input type="button" class="btn btn-success" id="overTimeSaveBtn" value="저장">
								<input type="button" class="btn btn-primary" id="overTimeRequestBtn" value="요청">
		        			</form:form>		        		
		        		</div>
			        </div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/pc/common/footer.jsp" />
</body>
<script>
const target = document.getElementById('measureTime');

target.addEventListener('mousewheel',function (e) {
      e.preventDefault();
      var num = Number(this.value);
      if(e.wheelDelta > 0){
        this.value = num + 0.5 ;
      }else{
        if(num < 0) return false;
        this.value = num - 0.5 ;
      }
});
</script>
</html>