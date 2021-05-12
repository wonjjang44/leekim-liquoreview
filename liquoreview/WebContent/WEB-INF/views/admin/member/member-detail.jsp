<%@page import="com.liquoreview.common.BirthSetter"%>
<%@page import="com.liquoreview.model.domain.member.Member"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
var bt_editFlag;
$(function(){
	//권한 리스트 얻기
	getAuthList();
	//phonenum 초기화
	$("input[name='phonenum']").val(autoHypenPhone('<c:out value="${memberDetail.phonenum}"/>'));
	//생년월일 초기세팅
	initBirth();

	//Button 들 이벤트 추가
	bt_editFlag=false;
	$("button[name='bt_list']").click(function(){
		location.href="/admin/member?currentPage="+<c:out value="${currentPage}"/>;
	});
	
	$("button[name='bt_edit']").click(function(){
		bt_editFlag=!bt_editFlag;
		if(bt_editFlag){
			alert("회원 권한정보 수정이 가능합니다.");
			removeDisabled();
		}else{
			memberInfoEdit(<c:out value="${memberDetail.member_id}"/>);
		}
	});
	
	$("button[name='bt_delete']").click(function(){
		if(confirm("정말 삭제하시겠습니까?")) {
			memberInfoDel();
		}
	});
});

// 권한 리스트 얻어오기
function getAuthList() {
	$.ajax({
		url:"/rest/admin/auth",
		type:"GET",
		success:function(result) {
			console.log(result);
			handleAuthList(result);
		},error:function(result) {
			console.log(result);
		}
	})
}

// 권한 리스트 조회결과 처리
function handleAuthList(result) {
	console.log(typeof(result));
	let selectTag = $("select[name='auth_id']");
	selectTag.html("");//기존 데이터 init
	//option tag 생성
	for (let key in result) {
		console.log(result[key]);
		let obj = result[key];
		selectTag.append("<option value="+obj.auth_id+">"+obj.des+"</option>");
	}
	// member.auth.auth_id와 일치하는 option.value로 set
	$("select[name='auth_id']").val('<c:out value="${memberDetail.auth.auth_id}"/>');
}

// 권한 option고를 때마다 hidden input에 value set
function hiddenAuthIdListener(obj) {
	console.log("회원의 권한 수정위해 option 태그를 선택했다!! : "+obj.value);
	$("#new_auth_id").val(obj.value);
}

//회원 생년월일 초기설정
function initBirth(){
	let birthStr = "<c:out value="${memberDetail.birth}"/>";
	console.log(birthStr);
	console.log(typeof(birthStr));
	$("input[name='birthYear']").val(birthStr.substring(0, 4));
	$("input[name='birthMonth']").val(birthStr.substring(5, 7));
	$("input[name='birthDate']").val(birthStr.substring(8, 10));
}

//수정 버튼 눌렀을 때 input창 들 활성/비활성화 시키기
function removeDisabled(){
	//$("input").removeAttr("disabled");
	$("select[name='auth_id']").removeAttr("disabled");
	$("select[name='auth_id']").focus();
};
//==========================================================
//목록버튼 :: back to list
//==========================================================

//==========================================================
//수정 버튼 한번 더 눌렀을 때 정보 수정
//==========================================================
function memberInfoEdit(member_id) {
	if(!confirm("선택사항대로 회원의 권한을 수정하시겠습니까?")) {
		return;
	}
	let new_auth_id = $("#new_auth_id").val();
	$.ajax({
		url:"/rest/admin/member/"+member_id+"/"+new_auth_id,
		type:"PATCH",
		success:function(result) {
			console.log("수정 요청 성공했나 확인용");
			let memAuthModiResult = JSON.parse(result);
			handleModiResult(memAuthModiResult);
		},
		error:function(result) {
			console.log(result);
		}
	});
}

function handleModiResult(result) {
	//수정 성공 메시지
	alert(result.msg);
	//refresh info
	getAuthList();
}

//==========================================================
//삭제 버튼 눌렀을 때 정보 삭제
//==========================================================
function memberInfoDel(){
	$("form").attr({
		action : "/admin/member/delete",	
		method : "post"		
	});
	$("form").submit();
}
//=========================================================================
// 연락처 input 태그의 입력값을 받아와 (1) 숫자만 입력되게 하고, (2) 숫자 사이사이에 (-) 삽입되도록 함
//=========================================================================
function autoHypenPhone(str) {
	console.log("넘겨받은 폰번호 : "+str);
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if ((str.length < 4) || (str.length >14)) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else if (str.length < 11) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}
//==========================================================
//생일 select의 date값을 변경하기 위한 함수 - 왜? 2월달 떄문에 ㄱ-
//==========================================================
function setBirthDate() {
	var year = $("select[name='birthYear']").val();
	var month = $("select[name='birthMonth']").val();
	if (year != "생월" && month != "생월") {
		year = parseInt(year);
		month = parseInt(month);
		//alert(year+", "+month);

		$.ajax({
			url : "/member-regist/setBirthDate",
			type : "get",
			data : {
				year : year,
				month : month
			},
			success : function(result) {
				var optionSize = $("select[name='birthDate'] option").length;
				for (var i = 0; i < optionSize; i++) {
					$("select[name='birthDate'] option:first").remove();
				} 
				for (var i = 0; i < result.length; i++) {
					$("select[name='birthDate']").append(
							"<option>" + result[i] + "</option>");
				}
			}
		});
	}
}


</script>
</head>

<body class="">
	<div class="wrapper ">
		<!-- sidebar -->
		<%@  include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>
			<!-- End Navbar -->
			<!-- <div class="panel-header panel-header-sm">
  
  
</div> -->
			<div class="content">
				<div class="row">
					<div class="col-md-8">
						<div class="card card-user">
							<div class="card-header">
								<h5 class="card-title">회원 상세 정보</h5>
							</div>
							<div class="card-body">
								<!-- <form> -->
								<div class="row">
									<input type="hidden" name="member_id" value="${memberDetail.member_id}"/>
									<div class="col-md-8 pr-1">
										<div class="form-group">
											<label>ID</label> <input type="text"
												class="form-control" placeholder="ID를 입력해주세요." name="id"
												disabled value="${memberDetail.userid }">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8 pr-1">
										<div class="form-group">
											<label>Password</label> 
											<input 
												class="form-control" 
												type="text"
												name="pass"
												disabled 
												value="<c:out value="${detailMemberPw.pass }"/>"
											>
											<label>Password 최종수정일시</label> 
											<input 
												class="form-control" 
												type="text"
												placeholder="비밀번호를 입력해주세요." 
												name="passLastModiYmd"
												disabled 
												value="<c:out value="${detailMemberPw.last_modi_ymd }"/>"
											>
											<label>Password 변경횟수</label> 
											<input 
												class="form-control" 
												type="text"
												placeholder="비밀번호를 입력해주세요." 
												name="resetPassAuthCnt"
												disabled 
												value="<c:out value="${detailMemberPw.reset_pass_auth_cnt}"/>"
											>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8 pr-1">
										<div class="form-group">
											<label>이름</label> <input
												type="text" class="form-control" placeholder="이름을 입력해주세요."
												disabled name="name" value="${memberDetail.username }">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8 pr-1">
										<div class="form-group">
											<label>Email</label> <input type="text"
												class="form-control" placeholder="E-mail을 입력해주세요." 
												disabled name="email"value="${memberDetail.email }">
										</div>
									</div>
								</div>
								<div class="row">	
									<div class="col-md-8 pr-1">
										<div class="form-group">
											<label>연락처</label>
											<input
												class="form-control" 
												type="text" 
												name="phonenum" 
												placeholder="연락처를 입력해주세요."
												maxlength='13'
												disabled 
												value=""
											>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label>생년</label>
											<input 
												class="form-control"
												type="text"
												disabled
												name="birthYear"
												value=""
											/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>생월</label>
											<input 
												class="form-control"
												type="text"
												disabled
												name="birthMonth"
												value=""
											/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">		
											<label>생일</label>
											<input 
												class="form-control"
												type="text"
												disabled
												name="birthDate"
												value=""
											/>
										</div>
									</div>
								</div>
								<form>
									<div class="row">
										<div class="col-md-5">
											<div class="form-group">
												<label>권한</label> 
												<select
													id="auth_id" 
													class="form-control" 
													name="auth_id" 
													disabled
													onChange="javascript:hiddenAuthIdListener(this);"
												>
												</select>
												<input id="new_auth_id" type="hidden" name="auth_id" value=""/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="update ml-5 mr-5">
											<button type="button" class="btn btn-primary btn-round" name="bt_list">
												목록
											</button>
											<input id="prevPage" type="hidden" name="currentPage" value="<c:out value="${currentPage }"/>"/>
											<button type="button" class="btn btn-primary btn-round" name="bt_edit">
												수정
											</button>
											<button type="button" class="btn btn-primary btn-round" name="bt_delete">
												삭제
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--   Core JS Files   -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp"%>
</body>

</html>