<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		function boardTagModify() {
			let modi_tag_id = ${tagInfo.tag_id};
			if (!confirm("게시판 글머리명을 입력한대로 수정하시겠습니까")) {
				return;
			}
			$.ajax({
				url:"/rest/admin/board/tag",
				type:"POST",
				data: {
					_method:"PUT",
					tag_id:modi_tag_id,
					tag_name:$("#modi_tag_name").val()
				},
				success: function(result) {
					let boardTagModiResult = JSON.parse(result);
					handleModiResult(boardTagModiResult);
				},
				error: function(result) {
					console.log(result);
				}
			});
		}
		
		//글머리 수정 모달 reset
		function resetModiModal() {
			$("#tag_modi_form")[0].reset();
		}
		
		//수정 요청 결과처리
		function handleModiResult(data) {
			if (data.resultCode === "1") {
				alert(data.msg);
				getBoardTagList();
			} else {
				alert(data.msg);
				return;
			}
		}
	</script>
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				게시판 글머리 수정
			</h4>
		</div>
		<div class="modal-body">
			<form id="tag_modi_form" name="tag_modi_form">
				<p class="desArea">
					<label for="modi_tag_name">수정할 글머리 이름</label>
					<input id="modi_tag_name" type="text" required value="${tagInfo.tag_name }"/>
				</p>
				<div class="modiBtn">
					<input id="btnTagUpdate" type="button" title="글머리수정" value="글머리수정" onClick="boardTagModify()"/>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="resetModiModal()">초기화</button>
				<button class="btn btn-close-modal btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>