<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#repeat-pw-alert-danger").hide();
		});
		function infoCheck(){
			alert("메일인증을 시작합니다.");
			var idCheck = $("input[name='idCheck']").val();
			var emailCheck = $("input[name='emailCheck']").val();
			console.log("메일발송 전 확인할 아이디 : "+idCheck);
			console.log("메일발송 전 확인할 이메일 : "+emailCheck);
			if(!idCheck || !emailCheck){
				alert("아이디, 이메일은 필수입력 값입니다.");
				return;
			}else{
				$.ajax({
					url:"/rest/member",
					type:"POST",
					data:{
						userid:idCheck,
						email:emailCheck
					},
					success:function(data){
						console.log("infoCheck() success 콜백에서 확인한 결과데이터 type: "+typeof(data));
						var infoResult = JSON.parse(data);
						handleInfoResult(infoResult);
					},
					error:function(data){
						console.log(data);
					}
				});
			}
		}
		
		function handleInfoResult(infoResult){
			if(infoResult.resultCode==="1"){
				alert(infoResult.msg);
				sendMail();
				setMemberInfo(infoResult.member_id);
			}else{
				alert(infoResult.msg);
				return;
			}
		}
		//인증시도 멤버의 pk 저장
		function setMemberInfo(member_id) {
			$("#hidden_member_id").val(member_id);
		}
		
		//인증메일 발송
		function sendMail() {
			console.log("infoCheck 성공했으므로 인증코드 메일 발송시작");
			$.ajax({
				url:"/rest/member/sendMail",
				type:"POST",
				success:function(data){
					console.log("sendMail success 콜백 확인 data : "+data);
					handleSendResult(data);
				},
				error:function(data){
					console.log("sendMail error 콜백 확인 data : "+data);
				}
			});
		}
		
		function handleSendResult(result){
			console.log("handleSendResult 확인 result : "+result);
			if(result==="true"){
				//발송 알림
				alert("인증메일을 발송했습니다.");
				//인증코드 입력, 확인버튼 활성화
				$("#confirmCode,#btnConfirm").attr("disabled",false);
			}else{
				alert("인증과정 오류 발생");
			}
		}
		
		//인증코드 확인
		function checkCode(){
			if(($("input[name='confirmCode']").val())!=""){
				alert("인증코드를 확인합니다. 잠시만 기다려주세요.");
				$.ajax({
					url:"/rest/member/codeCheck",
					type:"POST",
					data:{
						userid:$("#idCheck").val(),
						email:$("#emailCheck").val(),
						num:$("#confirmCode").val()
					},
					success:function(data){
						console.log("checkCode success콜백 확인 data : "+data);
						console.log("data type 확인 : "+typeof(data));
						var codeCheckResult = JSON.parse(data);
						handleCheckCodeResult(codeCheckResult);
					},
					error:function(data){
						console.log(data);
					}
				});
			}else{
				alert("수신한 인증코드 입력 후 진행하시기 바랍니다.");
				return;
			}
		}
		function handleCheckCodeResult(codeCheckResult){
			if(codeCheckResult.resultCode==="1") {
				//메시지 표출
				alert(codeCheckResult.msg);
				//새 비밀번호 입력란, 입력버튼 활성화
				$("#newpass,#newpass2,#btnResetPw").attr("disabled",false);
			} else {
				alert(codeCheckResult.msg);
				return;
			}
		}
		
		//재설정용 비밀번호 validation
		function newPassValidate() {
			var newpass = $("#newpass").val();
			var newpass2 = $("#newpass2").val();
			if((newpass.length < 6 && newpass2.length < 6) || (newpass != newpass2)) {
				$("#repeat-pw-alert-danger").show();
			} else {
				$("#repeat-pw-alert-danger").hide();
			}
		}
		
		//비밀번호 재설정
		function resetPw() {
			alert("입력하신 새 비밀번호로 재설정을 진행합니다. 잠시만 기다려주세요.");
			console.log("hidden member_id 보관상태 확인 : "+$("#hidden_member_id").val());
			$.ajax({
				type:"POST",
				url:"/rest/member/resetPw/",
				data:{
					pass:$("input[name='newpass']").val(),
					member_id:$("#hidden_member_id").val()
				},
				success:function(data){
					console.log(data);
					handlResetPwResult(data);
				},
				error:function(data){
					console.log(data);
				}
			});
		}
		function handlResetPwResult(result){
			if(result.resultCode==="1"){
				alert(result.msg);
				//userInfoModal 닫기
				$("#userInfoModal").modal('hide');
			} else {
				alert(result.msg);
			}
			location.href="redirect:/member/gologin";
		}
	</script>
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title">PW 재설정</h4>
			<p>비밀번호 재설정 코드를 받을 이메일을 입력해주세요.</p>
		</div>
		<div class="modal-body">
			<div class="checkEmail">
				<input id="idCheck" type="text" name="idCheck" placeholder="아이디를 입력해주세요." required/>
				<input
					id="emailCheck" type="email" name="emailCheck"
					placeholder="이메일을 입력해주세요." required
				/>
			</div>
			<div class="sendMailBtn">
				<input id="btnSendMail" type="button" title="코드메일발송버튼" value="Send Mail" onClick="infoCheck()"/>
			</div>
			<div class="checkCode">
				<input id="confirmCode" type="text" name="confirmCode" placeholder="인증코드 입력" required disabled="true"/>
			</div>
			<div class="checkConfirm">
				<input id="btnConfirm" type="button" title="인증코드 확인버튼" value="Confirm" onClick="checkCode()" disabled="true"/>
			</div>
			<div class="resetPw">
				<input 
					id="newpass" type="password" name="newpass"
					placeholder="새 비밀번호를 6자 이상 입력해주세요."
					required disabled="true"
					onKeyUp="newPassValidate()"
				/>
				<input 
					id="newpass2" type="password" name="newpass2"
					placeholder="새 비밀번호 재입력 확인"
					required 
					disabled="true"
					onKeyUp="newPassValidate()"
				/>
				<input id="hidden_member_id" type="hidden" name="member_id"/>
				<input type="hidden" />
				<div  id="repeat-pw-alert-danger" class="alert alert-danger">
					입력하신 비밀번호를 확인해주세요. (길이, 일치여부 등)
				</div>
			</div>
			<div class="resetPwBtn">
				<input id="btnResetPw" type="button" title="비번재설정버튼" value="PW Reset" onClick="resetPw()" disabled="true"/>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary btn-round" onClick="reset()">초기화</button>
			<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
		</div>
	</div>
</body>
</html>