<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" align="center">
				하위카테고리추가
			</h4>
		</div>
		<div class="modal-body">
			<form id="subcate_add_form" name="subcate_add_form">
				<p class="topArea form-group">
					<label for="topSelect">상위카테고리</label>
					<select id="topSelect" class="form-control" name="topcategory_id">
						<option value="선택">선택</option>
						<c:forEach var="top" items="${topcateList}" varStatus="status">
							<option value="${top.topcategory_id }">${top.topname}</option>
						</c:forEach>
					</select>
				</p>
				<p class="nameArea">
					<label for="new_sub_name">추가할 하위카테고리명</label>
					<input id="new_sub_name" class="form-control" type="text" name="subname" required/>
				</p>
				<div class="addBtn">
					<input id="btnSubcateInsert" type="button" title="하위카테고리추가" value="입력" onClick="subcateAdd()"/>
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