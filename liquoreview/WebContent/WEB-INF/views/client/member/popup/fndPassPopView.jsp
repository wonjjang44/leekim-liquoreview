<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<script src = "/resources/js/bootstrap.js"></script>
<link rel = "stylesheet" href = "/resources/css/boostrap.css">
<link rel = "stylesheet" href = "/resources/css/bootstrap-theme.css">
<!-- JS -->
<script src="/resources/assets/login&registBootTemp/vendor/jquery/jquery.min.js"></script>
<script src="/resources/assets/login&registBootTemp/js/main.js"></script>
<!-- Font Icon -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="/resources/assets/login&registBootTemp/css/style.css">
<body>

        <!-- fndPass Form START -->
            <div class="container">
                <div class="signin-content">
                    <div class="signin-form">
                        <h2 class="form-title">비밀번호 찾기</h2>
                        <p>비밀번호를 찾고자 하는 아이디를 입력해 주세요.</p><br/>
                        
                        <form class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="userid"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="userid" id="userid" placeholder="아이디"/>
                            </div>
                        </form>
                        <br/>
                        
                        <div class="form-group form-button">
                            <input type="button" name="fndPassBtn" id="fndPassBtn" class="form-submit" value="다음"/>
                        </div>
                        
                        <p>아이디가 기억나지 않으신다구요? &nbsp;<a href="javascript:void(0);" onClick = "" class="signup-image-link">아이디 찾기</a></p>
                    </div>
                </div>
            </div>
        <!-- fndPass Form END -->
		
</body>
<script>
$(function(){
	/*아이디 체크*/
	$("#fndPassBtn").click(function(){
		var userid = $("#userid").val();
		
		userIdValidChk();
		
		if(flag == true){
			$.ajax({
				url : "/member/idChk",
				type : "get",
				data : {
					"userid" : userid
				},
				success : function(data){
					if(data == "Y"){
						alert("해당 아이디를 찾을 수 없습니다.");
						$("#userid").focus();
					}else{
						location.href = "/member/popup/fndPassPop";
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

/*====== User Function ====== */
/*아이디 찾기 팝업창으로 이동(추후에 반영할 예정(now not use))*/
function fndUserIdPop(){
	var url = "/member/popup/fndUseriIdPop";
	var name = "fndUserIdPop";
    var _width = '650';
    var _height = '380';
 
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var _left = Math.ceil(( window.screen.width - _width )/2);
    var _top = Math.ceil(( window.screen.width - _height )/2); 
    
    window.open(url, name, 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
	
}

/*아이디 유효성 체크*/
var flag = true;

function userIdValidChk(){
	var userid = $("#userid").val();
	
	if(userid == null || userid == ""){
		alert("아이디를 입력해주세요");
		$("#userid").focus();
		flag = false;
	}else{
		flag = true;
	}
}
</script>
</html>