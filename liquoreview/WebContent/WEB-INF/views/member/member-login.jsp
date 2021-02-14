<%@page import="com.liquoreview.model.domain.member.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/inc/head.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		$("#alert-danger").hide();
		$("input[name='bt_login']").click(function() {
			login();
		});

		//id찾기, pw재설정 모달 제어

	});

	//체크 다 했으면 로그인!
	function login() {
		console.log('login요청받음');
		$.ajax({
			url : "/rest/member/login",
			type : "POST",
			dataType : "text",
			data : {
				userid : $("input[name='userid']").val(),
				pass : $("input[name='pass'").val()
			},
			success : function(result) {
				console.log(result);
				var loginResult = JSON.parse(result);
				handleLoginResult(loginResult);
			},
			error : function(result) {
				console.log(result);
				$("#alert-danger").show();
			}
		});
	}

	function handleLoginResult(loginResult) {
		if (loginResult.resultCode === 1) {
			location.href = "/";
		} else {
			$("#alert-danger").show();
		}
	}

	//탭키 로그인
	function loginByEnter() {
		if (event.keyCode == 13) {
			login();
		}
	}

	//아이디찾기
	function findIdModal() {
		console.log("아이디 찾기 버튼 클릭");
		//modal-dialog 영역 채우기 전 초기화
		$(".modal-dialog").empty();
		
		//modal-content에 아이디 찾기 폼양식 불러오기
		$.ajax({
			url : "/rest/member/findId",
			type : "get",
			dataType : "html",
			contentType : "application/html; charset=UTF-8",
			success : function(data) {
				console.log(data);
				$(".modal-dialog").html(data);
				$("#userInfoModal").modal('toggle');
			},
			error : function(data) {
				console.log("에러발생");
				console.log(data);
				alert("에러가 발생했습니다.");
			}
		});
	}
	
	function resetPassModal() {
		console.log("비번 재설정 클릭");
		//modal-dialog 영역 채우기 전 초기화
		$(".modal-dialog").empty();
		
		$.ajax({
			url:"/rest/member/resetPw",
			type:"get",
			dataType:"html",
			contentType:"application/html; charset=UTF-8",
			success: function(data){
				console.log(data);
				$(".modal-dialog").html(data);
				$("#userInfoModal").modal('toggle');
			},
			error: function(data){
				console.log("에러발생");
				console.log(data);
				alert("에러가 발생했습니다.");
			}
		});
	}
</script>
</head>
<body>
	<div class="fh5co-loader"></div>

	<div id="page" style="background-color: #00000C;">
		<%@ include file="/inc/menubar.jsp"%>
		<div class="container-wrap">
			<div id="fh5co-work">
				<div class="row">
					<c:choose>
						<c:when test="${sessionScope.member eq null and sessionScope.admin eq null }">
							<h2 class="text-center" id="title">로그인</h2>	
						</c:when>
						<c:when test="${sessionScope.member ne null and sessionScope.admin eq null}">
							<h2 class="text-center" id="title">관리자로그인</h2>
						</c:when>
						<c:otherwise>
							<h2 class="text-center" id="title">로그인</h2>
						</c:otherwise>
					</c:choose>
					<!-- <h2 class="text-center" id="title">로그인</h2> -->
					<p class="text-center">
						<small id=class="text-muted"> 로그인 아이디와 비밀번호를 입력해주세요. </small>
					</p>
					<hr>
				</div>
				<div class="row">
					<div class="col-md-12 col-md-push-3 animate-box">
						<form role="form">
							<fieldset>
								<div class="row">
									<div class="form-group">
										<div class="col-md-6">
											<div class="form-group">
												<input type="text" name="userid" id="userid"
													class="form-control input-lg" placeholder="ID를 입력해주세요.">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input id="pass" type="password" name="pass"
												class="form-control input-lg" placeholder="비밀번호를 입력해주세요."
												onKeyDown="loginByEnter()" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="alert alert-danger" id="alert-danger">회원정보가
											일치하지 않습니다.</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-2">
										<input type="button" name="bt_login"
											class="btn btn-primary btn-modify" value="로그인">
									</div>
									<div class="col-md-2">
										<input class="btn btn-primary btn-modify" type="button"
											name="bt_login" value="회원가입"
											onclick="location.href = '/member/goRegist'">
									</div>
								</div>
								<div class="row">
									<span>ID 또는 비밀번호가 기억나지 않으시나요?</span> <a
										href="javascript:findIdModal();" title="아이디 찾기"> 아이디 찾기 </a> <span>&nbsp;||&nbsp;</span>
									<a href="javascript:resetPassModal();" title="비밀번호 재설정">
										비밀번호 재설정 </a>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<!-- modal setting -->
				<div id="userInfoModal" class="modal fade" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content :: id찾기 또는 pw재설정 jsp를 불러들임 -->

					</div>
				</div>
			</div>
		</div>
		<%@ include file="/inc/footer.jsp"%>
	</div>

	<%@ include file="/inc/tail.jsp"%>

</body>
</html>