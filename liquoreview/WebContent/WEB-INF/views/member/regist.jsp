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
    <div class="main">

        <!-- Sign up form START -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">회원가입</h2>
                        
                        <form class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="USERID" id="userid" placeholder="아이디"/>
                            </div>
                                <p id = "msg"></p>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="PASS" id="pass" placeholder="비밀번호"/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="RE_PASS" id="re_pass" placeholder="비밀번호 재확인"/>
                            </div>
                            <div class="form-group">
                                <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="USERNAME" id="username" placeholder="이름"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="EMAIL" id="email" placeholder="이메일"/>
                            </div>
                            <div class="form-group">
                                <label for="birth"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="BIRTH" id="birth" placeholder="생년월일"/>
                            </div>
                            <div class="form-group">
                                <label for="phonenum"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="PHONENUM" id="phonenum" placeholder="휴대전화"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>개인정보 수집 및 이용에 동의합니다.</label><br/><br/>
                            </div>
                        </form>
                        
                        <div class="form-group form-button">
                            <input type="button" name="signup" id="signup" class="form-submit" value="회원가입"/>
                        </div>
                        
                        <a href="/member/login" class="signup-image-link">전 이미 회원이에요!!</a>
                        
                    </div>
                    <div class="signup-image">
                        <figure><img src="/resources/assets/login&registBootTemp/images/signup-image.jpg" alt="sing up image"></figure>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sign up form END -->

    </div>


</body>
<script>
$(function(){
	/*아이디 중복 여부*/
	$("#userid").keyup(function(){
		loginChk();
	});
	
	
	$("#userid").blur(function(){
		idblurFlag = false;
		
		if(idblurFlag == false){
			if($("#userid").val() == null || $("#userid").val() == ""){
				$("#msg").text("필수 정보입니다.");
				$("#msg").css("color","red");
			}
		}
	});
	
	
	/*회원가입*/
	$("#signup").click(function(){
		var userid = $("#userid").val();
		var pass = $("#pass").val();
		var re_pass = $("#re_pass").val();
		var username = $("#username").val(); 
		var email = $("#email").val();
		var birth = $("#birth").val();
		var phonenum = $("#phonenum").val();
		
		var param = new Object();
		param.userid = userid;
		param.pass = pass;
		param.re_pass = re_pass;
		param.username = username;
		param.email = email;
		param.birth = birth;
		param.phonenum = phonenum;
		
		validCheck();
		
		if(flag == true){
			$.ajax({
				url : "/member/regist",
				type : "post",
				dataType : "json",
				contentType : "application/json; charset = UTF-8",
				data : JSON.stringify(param),
				success : function(data){
					console.log(data);
					
					if(data > 0){
						alert("회원가입을 축하드립니다~");
						location.href = "/member/login";
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

//체크박스 여부
var checkFlag = true;
//유효성 여부
var flag = true;
//아이디 중복처리 여부
var idchkFlag = true;
//아이디 input 텍스트박의 focus() 여부
var idblurFlag = true;

/* ====== User Functrion ====== */
/*체크박스 체크 유무 참수*/
function provisionCheck(){
	if(!$("#agree-term").is(":checked")){
		checkFlag = false;
	}else{
		checkFlag = true;
	}
}

/*아이디 중복체크*/
function loginChk(){
	var userid = $("#userid").val();
	
	$.ajax({
		url : "/member/idChk",
		type : "get",
		data : {
			"userid" : userid
		},
		success : function(data){
			if(data == "N"){
				$("#msg").text("이미 사용중이거나 탈퇴한 아이디입니다.");
				$("#msg").css("color","red");
				
				idchkFlag = false;
			}else{
				$("#msg").text("멋진 아이디네요!! XD");
				$("#msg").css("color","green");
				
				idchkFlag = true;
			}
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
}

/*유효성 체크 함수*/
function validCheck(){
	var userid = $("#userid").val();
	var pass = $("#pass").val();
	var re_pass = $("#re_pass").val();
	var username = $("#username").val(); 
	var email = $("#email").val();
	var birth = $("#birth").val();
	var phonenum = $("#phonenum").val();
	
	var provisionChk = provisionCheck();
	
	if(userid == null || userid == ""){
		alert("아이디를 입력해주세요");
		$("#userid").focus();
		flag = false;
	}else if(pass == null || pass == ""){
		alert("비밀번호를 입력해주세요");
		$("#pass").focus();
		flag = false;
	}else if(re_pass == null || re_pass == ""){
		alert("비밀번호를 입력해주세요");
		$("#re_pass").focus();
		flag = false;
	}else if(pass != re_pass){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pass").focus();
		flag = false;
	}else if(username == null || username == ""){
		alert("이름을 입력해주세요");
		$("#username").focus();
		flag = false;
	}else if(email == null || email == ""){
		alert("이메일을 입력해주세요");
		$("#email").focus();
		flag = false;
	}else if(birth == null || birth == ""){
		alert("생년월일을 입력해주세요");
		$("#birth").focus();
		flag = false;
	}else if(phonenum == null || phonenum == ""){
		alert("휴대전화번호를 입력해주세요");
		$("#phonenum").focus();
		flag = false;
	}else if(checkFlag == false){
		alert("약관에 동의해주세요");
		flag = false;
	}else if(idchkFlag == false){
		alert("이미 사용중이거나 탈퇴한 아이디입니다.");
		$("#userid").focus();
		flag = false;
	}else{
		flag = true;
	}
} 


</script>
</html>