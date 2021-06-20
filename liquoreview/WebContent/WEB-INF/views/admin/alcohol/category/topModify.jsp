<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		function topcateModify() {
			let modi_topcategory_id = ${topcateInfo.topcategory_id};
			console.log("top 수정용 topid 확인 : "+modi_topcategory_id);
			if (!confirm("상위카테고리 정보를 수정하시겠습니까?")) {
				return;
			}
			$.ajax({
				url:"/rest/admin/alcohol/topcategory",
				type:"POST",
				data:{
					_method:"PUT",
					topcategory_id:modi_topcategory_id,
					topname:$("#modi_top_name").val()
				},
				success:function(result) {
					console.log("수정 요청 성공했나 확인");
					let topcateModiResult = JSON.parse(result);
					handleTopModiResult(topcateModiResult);
				},
				error:function(result) {
					console.log("수정 요청 실패했수다.");
					console.log(result);
				}
			});
		}
		
		//topcate 수정 모달 reset		
		function resetTopModiModal() {
			$("#top_modi_form")[0].reset();
		}
		
		//topcate 수정 요청 결과처리
		function handleTopModiResult(data) {
			console.log("topcate 수정 결과 handle data 확인 : "+data);
			if(data.resultCode === "1") {
				alert(data.msg);
				//리스트 갱신, 모달 close
				getTopcateList();
				//해당 하위 sub 보기
				getSubCategoryList(data.topcategory_id);
			}
		}
	</script>
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				상위카테고리 수정
			</h4>
		</div>
		<div class="modal-body">
			<form id="top_modi_form" name="top_modi_form">
				<label for="new_des">상위카테고리명</label>
				<p class="form-group">
					<input id="modi_top_name"type="text" name="topname" placeholder="${topcateInfo.topname }" required/>
				</p>
				<div class="modiBtn">
					<input id="btnTopcateUpdate" type="button" title="topcate수정" value="topcate변경" onClick="topcateModify()"/>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="resetTopModiModal()">초기화</button>
				<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>