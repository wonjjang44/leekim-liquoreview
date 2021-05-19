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
				게시판 글머리 추가
			</h4>
		</div>
		<div class="modal-body">
			<form id="tag_add_form" name="tag_add_form">
				<p class="desArea">
					<label for="new_tag_name">추가할 게시판 글머리명</label>
					<input id="new_tag_name" type="text" required/>
				</p>
				<div class="addBtn">
					<input id="btnTagInsert" type="button" title="글머리추가" value="글머리등록" onClick="boardTagAdd()"/>
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