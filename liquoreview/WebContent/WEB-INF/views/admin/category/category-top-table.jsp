<%@page import="com.liquoreview.common.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<!-- Start edit Modal -->
	<div id="editModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">카테고리 수정</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form name="edit_form">
						<input type="hidden" id="modal_topcategory_id" name="topcategory_id" value="">
						<input type="text" class="form-control" placeholder="원하는 이름을 입력해주세요." id="edit_name" name="name" maxlength="6"/>
					</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary btn-round" id="edit_btn" value="Edit">
					<button type="button" class="btn btn-primary btn-round" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End edit Modal -->
	
	<!-- Start del Modal -->
	<div id="delModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">카테고리 삭제</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form name="del_form">
						<input type="hidden" name="topcategory_id" value="">
						<p>삭제 하시겠습니까?</p>
					</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary btn-round" id="del_btn" value="Delete">
					<button type="button" class="btn btn-primary btn-round" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End del Modal -->
	<div class="wrapper ">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>

		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>
			<!-- End Navbar -->
			
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Top Category</h4>
								<div class="col-md-4 pr-1">
									<div class="form-group">
										<form id = "add_form" name="add_form">
											<input type="text" class="form-control" id = "topcategory_input" name="name" placeholder="신규 상위 카테고리 이름을 입력해주세요."/>
											<input type="button" class="btn btn-primary btn-round" id="add_btn" value="Top 카테고리 항목 추가"/>
										</form>
								</div>
							</div>
							
							<!-- 옵션선택 Start -->
							<div id="outter">
								<div style="float: right;" align="right">
									<select id="cntPerPage" name="sel" onchange="selChange()" class = "form-control">
										<option value="5"
											<c:if test="${pager.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
										<option value="10"
											<c:if test="${pager.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
										<option value="15"
											<c:if test="${pager.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
										<option value="20"
											<c:if test="${pager.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
									</select>
								</div> 
							</div>
							<!-- 옵션선택 End -->
							
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>순번</th>
												<th>신규 카테고리 명</th>
												<th>수정</th>
												<th>삭제</th>
											</tr>
										</thead>
										<tbody>
										<c:choose>
											<c:when test="${empty topCategoryVal}">
												<tr>
													<td colspan="4" align="center">조회 결과가 존재하지 않습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${topCategoryVal }" var="topCategoryVal">
													<tr>
														<td>${topCategoryVal.getTOPCATEGORY_ID() }</td>
														<td>${topCategoryVal.getTOP_NM() }</td>
														<td>
															<button type="button" class="btn btn-primary btn-round" onClick="changeModalValue()" 
																			  data-toggle="modal" data-target="#editModal" data-backdrop = "static">수정</button>
														</td>
														<td>
															<button type="button" class="btn btn-primary btn-round" onClick="changeModalValue()" 
																			  data-toggle="modal" data-target="#delModal" data-backdrop = "static">삭제</button>
														</td>
													</tr>
												</c:forEach>	
											</c:otherwise>
										</c:choose>
										</tbody>
									</table>
									<br/>
									
									<!-- 페이징 Start -->
									<div style="display: block; text-align: center;">		
										<ul class = "pagination" style = "justify-content: center;">
											<c:choose>
												<c:when test="${pager.startPage != 1 }">
													<li class = "page-item">
														<a class = "page-link" href="/admin/category/topCateLstIqr?nowPage=${pager.startPage - 1 }&cntPerPage=${pager.cntPerPage}">&lt;</a>
													</li>	
												</c:when>
												<c:otherwise>
													<a class = "page-link" href="javascript:alert('첫 페이지입니다');">&lt;</a>
												</c:otherwise>
											</c:choose>
											<c:forEach begin="${pager.startPage }" end="${pager.endPage }" var="p">
												<c:choose>
													<c:when test="${p == pager.nowPage }">
														<li class = "page-item active"><a class = "page-link" href="/admin/category/topCateLstIqr?nowPage=${p }&cntPerPage=${pager.cntPerPage}">${p }</a></li>
														<%-- <b>${p }</b> --%>
													</c:when>
													<c:when test="${p != pager.nowPage }">
														<li class = "page-item">
															<a class = "page-link" href="/admin/category/topCateLstIqr?nowPage=${p }&cntPerPage=${pager.cntPerPage}">${p }</a>
														</li>
													</c:when>
												</c:choose>
											</c:forEach>
											<c:choose>
												<c:when test="${pager.endPage != pager.lastPage}">
													<li class = "page-item">
														<a class = "page-link" href="/admin/category/topCateLstIqr?nowPage=${pager.endPage+1 }&cntPerPage=${pager.cntPerPage}">&gt;</a>
													</li>
												</c:when>
												<c:otherwise>
													<li>
														<a class = "page-link" href="javascript:alert('마지막 페이지입니다');">&gt;</a>
													</li>
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
									<!-- 페이징 End -->	
									
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
	<!--   Core JS Files   -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp"%>
</body>
<script>
$(function(){
	$("#add_btn").click(function(){

	});
	
	//로드 시 상위 카테고리 조회
	$.ajax({
		url : "/admin/category/topCateLstIqr",
		type : "get",
		success : function(data){
			var categoryVal = '${topCategoryVal}';
			var pager = '${pager}';
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	

	
	
});

function selChange() {
	var sel  = $("#cntPerPage").val();
	location.href="/admin/category/topCateLstIqr?nowPage=${pager.nowPage}&cntPerPage="+sel;
}
</script>
</html>