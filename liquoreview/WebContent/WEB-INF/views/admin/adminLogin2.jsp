<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인 페이지</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="/admin/login/images/icons/favicon.ico" />
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/animate/animate.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/select2/select2.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css"	href="/admin/login/vendor/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/admin/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="/admin/login/css/main.css">
	<!--===============================================================================================-->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(function(){
			$("button").click(function(){
				login();
			});
		});
		function login() {
			var inputId=$("input[name='id']").val();
			var inputPass=$("input[name='pass']").val();
			
			if(inputId==""){
				//alert("아이디 입력하세용");
				return;
			}
			if(inputPass==""){
				//alert("비번 입력하세용");
				return;
			}
			$("form").attr({
				action:"/admin/login",
				method:"POST"
			});
			$("form").submit();
		}
</script>
	
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image:url(/admin/login/images/bg-01.jpg);">
					<span class="login100-form-title-1">
						관리자 로그인
					</span>
				</div>
				<form class="login100-form validate-form">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">ADMIN ID</span>
						<input class="input100" type="text" name="userid" placeholder="Enter Your ID"/>
						<span class="focus-input100"></span>					
					</div>
					<div class="wrap-input100 validate-input m-b-18" data-validate="Password is required">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="pass" placeholder="Enter Password"/>
						<span class="focus-input100"></span>					
					</div>
					<div class="flex-sb-m w-full p-b-30">
						<div class="contact100-form-checkbox">
							<input id="ckb1" class="input-checkbox100" type="checkbox" name="remember-me" />
							<label class="label-checkbox100" for="ckb1">Remember me</label>
						</div>
						<div>
							<a class="txt1" href="#none">Forgot Password?</a>
						</div>
					</div>
					<div class="container-login100-form-btn">
						<button class="loin100-form-btn">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<!--===============================================================================================-->
	<script src="/admin/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/vendor/bootstrap/js/popper.js"></script>
	<script src="/admin/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="/admin/login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="/admin/login/js/main.js"></script>


</body>
</html>