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
				상위카테고리 추가
			</h4>
		</div>
		<div class="modal-body">
			<form id="topcate_add_form" name="topcate_add_form">
				<p class="nameArea">
					<label for="new_top_name">추가할 상위카테고리명</label>
					<input id="new_top_name" class="form-control" type="text" name="name" required/>
				</p>
				<div class="addBtn">
					<input id="btnTopcateInsert" type="button" title="상위카테고리추가" value="입력" onClick="topcateAdd()"/>
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