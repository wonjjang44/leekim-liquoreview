<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up</title>
<!-- JS -->
<script src="/resources/assets/login&registBootTemp/vendor/jquery/jquery.min.js"></script>
<script src="/resources/assets/login&registBootTemp/js/main.js"></script>
<script src="/resources/common/js/bootstrap.js"></script>
<!-- Font Icon -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/css/style.css">
<link rel="stylesheet" href="/resources/common/css/bootstrap.css">

</head>
<body>
		<%@ include file="/WEB-INF/inc/head.jsp" %>
        <!-- Sing in Form START -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="/resources/assets/login&registBootTemp/images/signin-image.jpg" alt="sing up image"></figure>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">로그인</h2>
                        
                        <form class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="userid"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="USERID" id="userid" placeholder="아이디"/>
                            </div>
                            <p id = "msg" style="color: red; font-size: 14px;"></p>
                            
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="PASS" id="pass" placeholder="비밀번호"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember Me</label>
                            </div>
                        </form>
                        <br/>
                        
                        <div class="form-group form-button">
                            <input type="button" name="signin" id="signin" class="form-submit" value="로그인"/>
                        </div>
                            
                            <p>회원이 아니시라구요? &nbsp;<a href="/member/regist" class="signup-image-link">회원가입</a></p>
                            <p>회원정보를 잊으셨나요?&nbsp;
	                            <a href="javascript:void(0);"  onClick = "fndIdModalPop()" class="signup-image-link">아이디 찾기</a>
	                            &nbsp;||&nbsp;<a href="javascript:void(0);" onClick = "fndPassPopView()" class="signup-image-link">비밀번호 찾기</a>
                            </p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sign in Form END -->
        
       <!-- 모달 영역 -->
	<!-- <div id="modalBox" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> -->
	<div class="modal fade" id="modalBox" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">

		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">모달 타이틀</h4>
				</div>
				<div class="modal-body">
					<h1>안녕하세요 모달 팝업창입니다.</h1>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-toggle = "modal">확인</button>
					<button type="button" class="btn btn-default" id="closeModalBtn">취소</button>
				</div>
			</div>
		</div>
	</div>

  
	<%@ include file="/WEB-INF/inc/tail.jsp" %>

</body>
<script>
$(function(){
	$("#modalBox").hide();
	
	$("#signin").click(function(){
		login();
	});
	
	//로그인 enter키로 시도하기
	$("input").keyup(function(){
		if (window.event.keyCode == 13) {
			login();
		}
	});
	
});

var flag = true; 

/*====== User Function ====== */
/*로그인 유효성 체크 함수*/
 function validChk(){
	var userid = $("#userid").val();
	var pass = $("#pass").val();
	
	if(userid == null || userid == ""){
		alert("아이디를 입력해주세요.");
		$("#userid").focus();
		flag = false;
	}else if(pass == null || pass == ""){
		alert("비밀번호를 입력해주세요");
		$("#pass").focus();
		flag = false;
	}else{
		return flag = true;
	}
}

/*비밀번호 찾기 팝업창*/
function fndPassPopView(){
	var url = "/member/popup/fndPassPopView";
	var name = "fndUserIdPop";
    var _width = '950';
    var _height = '520';
    var _top = "90";
    var _left = "160";
 
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    //var _left = Math.ceil(( window.screen.width - _width )/2);
    //var _top = Math.ceil(( window.screen.width - _height )/2); 
    
    window.open(url, name, 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
}

/*아이디 찾기 팝업창(모달)*/
function fndIdModalPop(){
	$('#modalBox').modal('show');
}


/*로그인 비동기 함수*/
function login(){
	var userid = $("#userid").val();
	var pass = $("#pass").val();
	
	var param = new Object();
	param.userid = userid;
	param.pass = pass;
	
	validChk();
	
	if(flag == true){
		$.ajax({
			url : "/member/login",
			type : "post",
			dataType : "json",
			contentType : "application/json; charset = UTF-8",
			data : JSON.stringify(param),
			success : function(data){
				if(data.msg == "idFail"){
					$("#msg").text("회원정보가 틀렸거나 존재하지 않는 회원입니다.");
				}else if(data.msg == "pwFail"){
					$("#msg").text("회원정보가 틀렸거나 존재하지 않는 회원입니다.");
				}else if(data.msg == "admin"){
					location.href = "/admin/index";
				}else if(data.msg == "user"){
					location.href = "/";	
				}
			},
			error : function(xhr){
				console.log(xhr);
				alert("어머나!! 예상치 못한 오류가 발생하였습니다.\n빠른 시일내에 해결하도록 하겠습니다.");
			}
		});
	}
}
</script>
</html>