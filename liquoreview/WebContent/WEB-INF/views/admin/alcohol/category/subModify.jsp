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
				하위카테고리 수정
			</h4>
		</div>
		<div class="modal-body">
			<div id="formerInfoArea">
				<p class="formerTopInfo form-group">
					<label>기존상위카테고리</label>
					<input type="text" value="기존상위카테고리" readonly/>
				</p>
				<p class="formerSubArea form-group">
					<label>기존하위카테고리</label>
					<input type="text" value="기존하위카테고리" readonly/>
				</p>
			</div>
			<form id="sub_modi_form" name="sub_modi_form">
				<div id="newInfoArea">
					<label for="modi_topSelect">상위변경</label>
					<select id="modi_topSelect" class="form-control" name="topcategory_id">
						<option value="선택">선택</option>
					</select>
					<p class="new_nameArea">
						<label for="modi_sub_name">변경할 하위카테고리명</label>
						<input id="modi_sub_name" class="form-control" type="text" name="subname"/>
					</p>
				</div>
				<div class="modiBtn">
					<input id="btnSubcateUpdate" type="button" title="subcate수정" value="subcate변경" onClick="subcateModify()"/>
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