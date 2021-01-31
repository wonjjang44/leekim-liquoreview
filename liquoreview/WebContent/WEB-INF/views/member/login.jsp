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
<!-- Font Icon -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/css/style.css">
</head>
<body>

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
                                <input type="text" name="userid" id="userid" placeholder="아이디"/>
                            </div>
                            <p id = "msg" style="color: red; font-size: 14px;"></p>
                            
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="비밀번호"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>로그인 상태 유지</label>
                            </div>
                        </form>
                        <br/>
                        
                        <div class="form-group form-button">
                            <input type="button" name="signin" id="signin" class="form-submit" value="로그인"/>
                        </div>
                            
                            <p>회원이 아니시라구요? &nbsp;<a href="/member/regist" class="signup-image-link">회원가입</a></p>
                        
                        <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sign in Form END -->

</body>
<script>
$(function(){
	$("#signin").click(function(){
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
				data : param,
				success : function(data){
					if(data == "idFail"){
						$("#msg").text("회원정보가 틀렸거나 존재하지 않는 회원입니다.");
					}else if(data == "pwFail"){
						$("#msg").text("회원정보가 틀렸거나 존재하지 않는 회원입니다.");
					}else if(data == "admin"){
						location.href = "/admin/index";
					}else if(data == "user"){
						location.href = "/";	
					}
				},
				error : function(xhr){
					console.log(xhr);
					alert("어머나!! 예상치 못한 오류가 발생하였습니다.\n빠른 시일내에 해결하도록 하겠습니다.");
				}
			});
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

/*로그인 상태 유지 함수*/
function keepLoginSession(){
	/**
	 * 		
	 */
}
</script>
</html>