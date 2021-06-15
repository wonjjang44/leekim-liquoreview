<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		function subcateModify() {
			let modi_subcategory_id = ${subcateInfo.subcategory_id};
			let modi_topcategory_id = $("#hidden_sub_topcategory_id").val();
			if ($("#modi_topSelect").val() != "") {
				modi_topcategory_id = $("#modi_topSelect").val();
			}
			console.log("세션으로부터 다시 modi_subcategory_id 변수로 담은 subcategory_id 확인 : "+modi_subcategory_id);
			console.log("sub 수정용 topid 확인 : "+modi_topcategory_id);
			if(!confirm("하위카테고리 정보를 수정하시겠습니까?")) {
				return;
			}
			$.ajax({
				url:"/rest/admin/alcohol/subcategory",
				type:"POST",
				data: {
					_method:"PUT",
					subcategory_id:modi_subcategory_id,
					topcategory_id:modi_topcategory_id,
					subname:$("#modi_sub_name").val()
				},
				success:function(result) {
					console.log("수정 요청 성공했나 확인");
					 let subcateModiResult = JSON.parse(result);
					 handleModiResult(subcateModiResult);
				},
				error:function(result) {
					console.log("수정 요청 실패했수다.");
					console.log(result);
				}
			});
		}
		
		//subcate 수정 모달 reset
		function resetModiModal() {
			$("#sub_modi_form")[0].reset();
		}
		
		//수정 요청 결과처리
		function handleModiResult(data) {
			console.log(data);
			if (data.resultCode === "1") {
				alert(data.msg);
				//리스트 갱신, 모달 close
				getSubCategoryList($("#hidden_sub_topcategory_id").val());
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
				하위카테고리 수정
			</h4>
		</div>
		<div class="modal-body">
			<div id="formerInfoArea">
				<p class="formerTopInfo form-group">
					<label>기존상위카테고리</label>
					<input type="text" value="${topcateInfo.topname }" readonly/>
				</p>
				<p class="formerSubArea form-group">
					<label>기존하위카테고리</label>
					<input type="text" value="${subcateInfo.subname }" readonly/>
				</p>
			</div>
			<form id="sub_modi_form" name="sub_modi_form">
				<div id="newInfoArea">
					<label for="modi_topSelect">상위변경</label>
					<select id="modi_topSelect" class="form-control" name="topcategory_id">
						<option value="">선택</option>
						<c:forEach var="top" items="${topcateList}">
							<option value="${top.topcategory_id }">${top.topname }</option>
						</c:forEach>
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
			<input id="hidden_sub_topcategory_id" type="hidden" name="topcategory_id" value="${topcateInfo.topcategory_id }"/>
		</div>
		<div class="modal-footer">
			<div class="confirmBtn">
				<button class="btn btn-primary btn-round" onClick="resetModiModal()">초기화</button>
				<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</body>
</html>