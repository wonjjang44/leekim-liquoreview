<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		/* */ 
		$(function(){
			console.log("auth modify 모달 떴나 확인");
			console.log($("#modi_adm_assign_chk").is(":checked"));
			console.log($("#modi_mem_adm_chk").is(":checked"));
			console.log($("#modi_cate_adm_chk").is(":checked"));
			console.log($("#modi_alc_adm_chk").is(":checked"));
			console.log($("#modi_rev_adm_chk").is(":checked"));
			console.log($("#modi_rev_comm_adm_chk").is(":checked"));
			console.log($("#modi_board_adm_chk").is(":checked"));
			console.log($("#modi_board_comm_adm_chk").is(":checked"));
		});
		
		function authModify() {
			console.log("권한 수정버튼 눌렀음");
			//console.log("수정요청 받은 권한의 id 확인 : "+${authInfo.auth_id});
			let modi_auth_id = ${authInfo.auth_id};
			console.log("세션으로부터 다시 modi_auth_id 변수로 담은 auth_id 확인 : "+modi_auth_id);
			if (!confirm("선택사항대로 권한을 수정하시겠습니까?")) {
				return;
			}
			console.log("수정요청 출발 직전 체크박스 t/f확인");
			console.log("assign : "+$("#modi_adm_assign_chk").is(":checked"));
			console.log("mem : "+$("#modi_mem_adm_chk").is(":checked"));
			console.log("cate : "+$("#modi_cate_adm_chk").is(":checked"));
			console.log("alc : "+$("#modi_alc_adm_chk").is(":checked"));
			console.log("rev : "+$("#modi_rev_adm_chk").is(":checked"));
			console.log("rev_comm : "+$("#modi_rev_comm_adm_chk").is(":checked"));
			console.log("board : "+$("#modi_board_adm_chk").is(":checked"));
			console.log("board_comm : "+$("#modi_board_comm_adm_chk").is(":checked"));
			$.ajax({
				url:"/rest/admin/auth",
				type:"POST",
				data: {
					_method:"PUT",
					auth_id:modi_auth_id,
					des:$("#modi_des").val(),
					adm_assign:$("#modi_adm_assign_chk").is(":checked"),
					mem_adm:$("#modi_mem_adm_chk").is(":checked"),
					cate_adm:$("#modi_cate_adm_chk").is(":checked"),
					alc_adm:$("#modi_alc_adm_chk").is(":checked"),
					rev_adm:$("#modi_rev_adm_chk").is(":checked"),
					rev_comm_adm:$("#modi_rev_comm_adm_chk").is(":checked"),
					board_adm:$("#modi_board_adm_chk").is(":checked"),
					board_comm_adm:$("#modi_board_comm_adm_chk").is(":checked")
				},
				success:function(result) {
					console.log("수정 요청 성공했나 확인");
					let authModiResult = JSON.parse(result);
					handleModiResult(authModiResult);
				},
				error:function(result) {
					console.log("수정 요청 실패했수다.");
					console.log(result);
				}
			});
		}
		
		//수정 요청 결과처리
		function handleModiResult(data){
			console.log(data);
			console.log("result data의 타입확인 : "+typeof(data));
			//수정 성공 메시지 띄우기
			if(data.resultCode === "1") {
				alert(data.msg);
				//리스트 갱신, 모달 close
				getAuthList();
			}else {
				alert(data.msg);
				return;
			}
		}
	</script>
</head>
<body>
	<%-- <c:redirect url="/rest/admin/auth/${auth_id}"></c:redirect> --%>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				권한 수정
			</h4>
		</div>
		<div class="modal-body">
			<form id="auth_modi_form" name="auth_modi_form">
				<p class="desArea">
					<label for="modi_des">수정할 권한역할명</label>
					<input id="modi_des" type="text" name="des" required value="${authInfo.des}"/>
				</p>
				<p class="checkArea1">
					<c:choose>
						<c:when test="${authInfo.adm_assign}">
							<input id="modi_adm_assign_chk" type="checkbox" name="adm_assign" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_adm_assign_chk" type="checkbox" name="adm_assign" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_adm_assign_chk">할당권한</label>
					<c:choose>
						<c:when test="${authInfo.mem_adm}">
							<input id="modi_mem_adm_chk" type="checkbox" name="mem_adm" value="" checked/>	
						</c:when>
						<c:otherwise>
							<input id="modi_mem_adm_chk" type="checkbox" name="mem_adm" value=""/>	
						</c:otherwise>
					</c:choose>
					<label for="modi_mem_adm_chk">멤버관리</label>
					<c:choose>
						<c:when test="${authInfo.cate_adm}">
							<input id="modi_cate_adm_chk" type="checkbox" name="cate_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_cate_adm_chk" type="checkbox" name="cate_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_cate_adm_chk">카테고리관리</label>
					<c:choose>
						<c:when test="${authInfo.alc_adm}">
							<input id="modi_alc_adm_chk" type="checkbox" name="alc_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_alc_adm_chk" type="checkbox" name="alc_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_alc_adm_chk">알콜목록관리</label>
				</p>
				<p class="checkArea2">
					<c:choose>
						<c:when test="${authInfo.rev_adm}">
							<input id="modi_rev_adm_chk" type="checkbox" name="rev_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_rev_adm_chk" type="checkbox" name="rev_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_rev_adm_chk">리뷰관리</label>
					<c:choose>
						<c:when test="${authInfo.rev_comm_adm}">
							<input id="modi_rev_comm_adm_chk" type="checkbox" name="rev_comm_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_rev_comm_adm_chk" type="checkbox" name="rev_comm_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_rev_comm_adm_chk">리뷰댓글관리</label>
					<c:choose>
						<c:when test="${authInfo.board_adm}">
							<input id="modi_board_adm_chk" type="checkbox" name="board_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_board_adm_chk" type="checkbox" name="board_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_board_adm_chk">게시판관리</label>
					<c:choose>
						<c:when test="${authInfo.board_comm_adm}">
							<input id="modi_board_comm_adm_chk" type="checkbox" name="board_comm_adm" value="" checked/>
						</c:when>
						<c:otherwise>
							<input id="modi_board_comm_adm_chk" type="checkbox" name="board_comm_adm" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="modi_board_comm_adm_chk">댓글관리</label>
				</p>
				<div class="modiBtn">
					<input id="btnAuthUpdate" type="button" title="권한수정" value="권한수정" onClick="authModify()"/>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="reset()">초기화</button>
				<button class="btn btn-close-modal btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>