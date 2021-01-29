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
                        
                        <form method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="userid" id="name" placeholder="userid"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="비밀번호"/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="비밀번호 재확인"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="이메일"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="이름"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="생년월일"/>
                            </div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="휴대전화"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>개인정보 수집 및 이용에 동의합니다.&nbsp;<a href="/member/provision" class="term-service">이용약관</a></label>
                            </div>
                        </form>
                       
                        
                        <div class="form-group form-button">
                            <input type="button" name="signup" id="signup" class="form-submit" value="회원가입"/>
                        </div>
                        
                    </div>
                    <div class="signup-image">
                        <figure><img src="/resources/assets/login&registBootTemp/images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="/member/login" class="signup-image-link">전 이미 회원이에요!!</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sign up form END -->

    </div>


</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>