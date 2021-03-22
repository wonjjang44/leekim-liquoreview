<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 이메일 인증</title>
</head>
<script src="/resources/assets/login&registBootTemp/vendor/jquery/jquery.min.js"></script>
<script src = "/resources/js/bootstrap.js"></script>
<link rel = "stylesheet" href = "/resources/css/boostrap.css">
<link rel = "stylesheet" href = "/resources/css/bootstrap-theme.css">
<style>
.box{
	position: relative;
	z-index: 100;
	padding: 39px 40px 20px;
	border: 1px solid #e5e5e5;
}

.container {
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}


</style>
<body>
    <div class="container">
        <h2 class="form-title">비밀번호 찾기</h2>
        <p>비밀번호를 찾을 방법을 선택해 주세요!!!</p><br/>
			<div id="radioContent" class="box">
				<div id="radiobox_inn_sub"> 
						<input type="radio" id="show_email_tag" name="show_tag" value="email_radio">
						<label for="r_pn1" class="label_rd">회원정보에 등록한 이메일로 인증<span></span></label>
						<div id = "div_fndPass_email">
							<p class="dsc">회원정보에 등록한 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.</p><br/>	
							<label for="userid">이름</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="username" name="USERNAME" maxlength="40" style="width:217px"><br/>
							
							<span class="int_mob">
								<label for="phoneNo" id="lbl_phoneNo" class="lbl_mob">이메일주소</label>&nbsp;&nbsp;
								<input type="email" id="email" name="EMAIL" style="width:187px">
							</span>	
															
							<a href="javascript:void(0)" id="btnEmailAuthNo"><span class="blind">인증번호 받기</span></a><br/>
							
							<label for="t_ct1" class="blind">인증번호 입력</label>
							<span class="input_box2">
								<input type="text" id="phoneAuthNo" name="PHONEAUTHNO" maxlength="6" class="input_txt" style="width:217px" placeholder="인증번호 6자리 숫자 입력">
							</span>
						</div>		
				</div>
			</div>
			<br/><br/>
			<div id="radioContent2" class="box">
				<div>
					<input type="radio" id="show_phone_tag" name="show_tag" value="phone_radio">
					<label for="r_pn1" class="label_rd">회원정보에 등록된 휴대전화로 인증<span></span></label>
					<div id = "div_fndPass_phone">
						<p>아직 구현중에 있습니다...</p>
					</div>
					
				</div>
			</div>
    </div>
</body>
<script>
$(function(){
	//로드됨과 동시에 셋팅한다.
	initSetting();
	
	$("#btnEmailAuthNo").click(function(){
		var username = $("#username").val();
		var email = $("#email").val();
		
		var param = {
			"username" : username,
			"email" : email
		};
		
		$.ajax({
			url : "/member/popup/fndPassPop",
			method : "post",
			dataType : "json",
			contentType : "application/json; charset = UTF-8",
			data : JSON.stringify(param),
			success : function(data){
				console.log(data);
			},
			error : function(xhr){
				console.log(xhr);
			}
		});
		
	});
	
});



/*====== User Function ====== */
/**/
function initSetting(){
	/*페이지 로드와 동시에 라디오 이하 태그 숨기기*/
	$("#div_fndPass_email").css("display", "none");
	$("#div_fndPass_phone").css("display", "none");
	
	
	/*이메일 인증 라디오버튼 클릭 시 태그 활성화*/
	$("input:radio[name=show_tag]").click(function(){
	            
	    if($("input:radio[name=show_tag]:checked").val()=="email_radio"){
	    	$("#div_fndPass_email").css("display", "block");
	    	$("#div_fndPass_phone").css("display", "none");
	    }else {
	    	$("#div_fndPass_phone").css("display", "block");
	    	$("#div_fndPass_email").css("display", "none");
	    }
	});
}

 /*이메일 인증*/
function emailCty(){
	 
}
</script>
</html>