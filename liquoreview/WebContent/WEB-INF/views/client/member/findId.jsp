<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		function findId() {
			console.log("id찾기버튼 클릭");
			console.log("이름 : "+$("input[name='username']").val());
			console.log("전화번호 : "+$("input[name='phonenum']").val());
			$.ajax({
				url:"/rest/member/findId",
				type:"post",
				data:{
					username:$("input[name='username']").val(),
					phonenum:$("input[name='phonenum']").val()
				},
				success:function(data){
					console.log(data);
					handleResult(data);
				},
				error:function(data){
					console.log(data);
					alert('id찾기 실패');
				}	
			});
		}
		
		function handleResult(result){
			console.log(result);
			if(result.resultCode==="1"){
				console.log("id조회됨");
				$("#findIdResult").val(result.userid);
			}else{
				console.log("id조회안됨");
				$("#findIdResult").val(result.msg);
			}
		}
		
		//reset()
		function reset(){
			console.log("초기화 버튼 클릭");
			$("form[name='findIdForm']")[0].reset();
			$("input[name='findIdResult']").val("");
		}
		
	</script>
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title">ID찾기</h4>
			<p>가입 시 기재한 성명과 전화번호를 입력해주세요.</p>
		</div>
		<div class="modal-body">
			<form name="findIdForm">
				<div id="findMyId">
					<input
						id="username" type="text" name="username"
						placeholder="이름을 입력해주세요."
						required
					/> 
					<input
						id="phonenum" type="tel" name="phonenum"
						pattern="\d{3}-\d{3,4}-\d{4}"
						placeholder="- 를 제외하고 입력"
						required 
					/>
				</div>
				<div class="findBtn">
					<input id="btnFindId" type="button" title="찾기버튼" value="ID찾기"
						onClick="findId()" />
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="findResult">
				<input 
					id="findIdResult" type="text" name="findIdResult"
					placeholder="ID찾기 결과" readonly
				/>
			</div>
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="reset()">초기화</button>
				<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>