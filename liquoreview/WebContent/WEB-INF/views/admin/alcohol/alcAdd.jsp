<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				Alcohol 추가
			</h4>
		</div>
		<div class="modal-body">
			<form id="alc_add_form" name="alc_add_form" enctype="multipart/form-data">
				<p class="topcategoryArea">
					<label for="modalTopSelect">상위분류명</label>
					<select name="modalTopSelect" id="modalTopSelect" onChange="getModalSubList(this.value)">
						<option value="">선택</option>
						<c:forEach var="top" items="${topcateInfo }">
							<option value="${top.topcategory_id }">${top.topname }</option>													
						</c:forEach>
					</select>
				</p>
				<p class="subcategoryArea">
					<label for="modalSubSelect">하위분류명</label>
					<select name="modalSubSelect" id="modalSubSelect">
						<option value="">선택</option>
					</select>
				</p>
				<div class="desArea">
					<p>
						<label for="new_alc_name">주류명</label>
						<input id="new_alc_name" type="text" required/>
					</p>
					<p>
						<label for="new_alc_degree">도수</label>
						<input id="new_alc_degree" type="text" required/>					
					</p>
					<p>
						<label for="new_alc_detail">상세</label>
						<textarea name="detail" id="new_alc_detail" cols="50" rows="5" placeholder="상세 설명을 적어주세요."></textarea>
					</p>
					<p>
						<label for="new_alc_img">이미지</label>
						<input id="fake_input" class="fake_input" type="text" value="선택 파일명" disabled="disabled"/>
						<input class="hidden" type="file" id="alc_img" name="myFile" multiple="multiple" onChange="getImgInfo(this)"/>
					</p>
				</div>
				<div class="addBtn">
					<input id="btnAlcInsert" type="button" title="Alcohol추가" value="등록" onClick="alcoholAdd()"/>
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