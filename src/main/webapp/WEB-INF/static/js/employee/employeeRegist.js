/**
 * Author : Yoo jaeheon
 */

var controller = $.extend(new $.CommonObj(), {
	eventInit:function() {
		
	}, onCreate:function() {
		

		
		$("#employeeNoDuplicateBtn").click(function() {
			
			var employeeNo = $("#employeeNo").val();
			if ( employeeNo == "") {
				alert("사원 번호를 입력해주세요.");
				return;
			}
			
			controller.employeeNoDuplicateCheck();
			
			if ( employeeNoCheckFlag == true ) {
				return true;
			} else {
				return false;
			}
			
		});
		
		$("#employeeRegistBtn").click(function() {
			if ( controller.validationCheck() == false ) {
				return;
			} else {
				$("#employeeRegistFrm").attr({
					method:"post", 					     				     	
			     	action:"/employee/employeeRegist.do"
				}).submit();
			}
			
		});
		
		$("#addressBtn").click(function(){
			controller.exeDaumPostcode();
		});
		
		$("#searchDepartmentPopupBtn").click(function() {
			controller.searchDepartment();
		});
		
		
	}, emptyCheck : function() {
		var employeeNo = $("#employeeNo").val();
		var employeePassWord = $("#employeePassWord").val();
		var employeePassWordConfirm = $("#employeePassWordConfirm").val();
		var employeeName = $("#employeeName").val();
		var employeePhoneNumber = $("#employeePhoneNumber").val();
		var employeePostcode = $("#employeePostcode").val();
		var employeeCommonaddress = $("#employeeCommonaddress").val();
		var employeeDetailaddress = $("#employeeDetailaddress").val();
		var employeeCompanyPhoneNumber = $("#employeeCompanyPhoneNumber").val();
		var employeeEmail = $("#employeeEmail").val();
		var employeeJoinDate = $("#employeeJoinDate").val();
		var departmentName = $("#depName").val();
		var positionNo = $("#positionNo").val();
		
		if ( employeeNo == "") {
			alert("사원번호를 입력해주세요.");
			return false;
		}
		
		if ( employeePassWord == "") {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		if ( employeePassWordConfirm == "") {
			alert("비밀번호 확인을 입력해주세요.");
			return false;
		}

		if ( employeeName == "") {
			alert("사원이름를 입력해주세요.");
			return false;
		}
		
		if ( employeePhoneNumber == "") {
			alert("전화번호를 입력해주세요.");
			return false;
		}
		
		if ( employeePostcode == "") {
			alert("우편번호를 입력해주세요.");
			return false;
		}
		
		if ( employeeCommonaddress == "") {
			alert("주소를 입력해주세요.");
			return false;
		}
		
		if ( employeeDetailaddress == "") {
			alert("상세 주소를 입력해주세요.");
			return false;
		}
		
		if ( employeeCompanyPhoneNumber == "") {
			alert("회사 전화번호를 입력해주세요.");
			return false;
		}
		
		
		if ( employeeEmail == "") {
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if ( employeeJoinDate == "") {
			alert("입사일을 입력해주세요.");
			return false;
		}
		
		if ( employeeJoinDate == "") {
			alert("입사일을 입력해주세요.");
			return false;
		}	
		
		if ( departmentName == "") {
			alert("부서명을 조회해주세요.");
			return false;
		}
		
		if ( positionNo == "") {
			alert("직급번호를 입력해주세요.");
			return false;
		}
		
	}, passwordConfirmCheck : function() {
		
		var employeePassWord = $("#employeePassWord").val();
		var employeePassWordConfirm = $("#employeePassWordConfirm").val();
		
		if ( employeePassWord != employeePassWordConfirm ) {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			return false;
		}
		
	}, employeeNoDuplicateCheck : function() {
		var employeeNo = $("#employeeNo").val();
		
		$.post("/employee/employeeNoDuplicate.do"
				,  {									 
					"employeeNo": $("#employeeNo").val()
				}
				, function(response) {
					
					if ( response.cnt > 0 ) {					 						
						alert("이미 존재하는 사원번호 입니다.");
						document.getElementById("employeeNoCheckFlag").value="false";

					}
					else {
						alert("사용가능한 사원번호 입니다.");
						document.getElementById("employeeNoCheckFlag").value="true";

					}
			});
		
	}, validationCheck : function() {
		if ( controller.emptyCheck() == false) {
			return false;
		} else {

			if ( document.getElementById("employeeNoCheckFlag").value=="false") {
				alert("사원번호 중복체크를 해주시기 바랍니다.");
				return false;
			}
			

			else if ( controller.passwordConfirmCheck() == false ) {
				return false;
			}

			else{
				return true;
			}
		}
		
	}, exeDaumPostcode : function() {
		new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { 
                	// 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { 
                	// 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
              // $("zipCode").val() = data.zonecode;
               document.getElementById('employeePostcode').value = data.zonecode;
               //5자리 새우편번호 사용
               //$("address").val() = fullAddr;
               document.getElementById('employeeCommonaddress').value = fullAddr;
               // 커서를 상세주소 필드로 이동한다.
               //$("Daddress").focus();
               document.getElementById('employeeDetailaddress').focus();
            }
        }).open();
		
	}, searchDepartment : function() {
		window.open("/search/searchDepartment.do","Department 검색", "width=1000, height=800");
		
	}
	
});


$(document).ready(function() {
	controller.init();
	isEmployeeDuplicateBtn = false;
});
