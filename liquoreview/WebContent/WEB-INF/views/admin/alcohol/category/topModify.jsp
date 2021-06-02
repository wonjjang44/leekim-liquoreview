<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				권한 추가
			</h4>
		</div>
		<div class="modal-body">
			<form id="auth_add_form" name="auth_add_form">
				<p class="desArea">
					<label for="new_des">추가할 권한역할명</label>
					<input id="new_des" type="text" name="des" required/>
				</p>
				<p class="checkArea1">
					<input id="adm_assign_chk" type="checkbox" name="adm_assign" value=""/>
					<label for="adm_assign_chk">할당권한</label>
					<input id="mem_adm_chk" type="checkbox" name="mem_adm" value=""/>
					<label for="mem_adm_chk">멤버관리</label>
					<input id="cate_adm_chk" type="checkbox" name="cate_adm" value=""/>
					<label for="cate_adm_chk">카테고리관리</label>
					<input id="alc_adm_chk" type="checkbox" name="alc_adm" value=""/>
					<label for="alc_adm_chk">알콜목록관리</label>
				</p>
				<p class="checkArea2">
					<input id="rev_adm_chk" type="checkbox" name="rev_adm" value=""/>
					<label for="rev_adm_chk">리뷰관리</label>
					<input id="rev_comm_adm_chk" type="checkbox" name="rev_comm_adm" value=""/>
					<label for="rev_comm_adm_chk">리뷰댓글관리</label>
					<input id="board_adm_chk" type="checkbox" name="board_adm" value=""/>
					<label for="board_adm_chk">게시판관리</label>
					<input id="board_comm_adm_chk" type="checkbox" name="board_comm_adm" value=""/>
					<label for="board_comm_adm_chk">댓글관리</label>
				</p>
				<div class="addBtn">
					<input id="btnAuthInsert" type="button" title="권한추가" value="권한입력" onClick="authAdd()"/>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="reset()">초기화</button>
				<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>