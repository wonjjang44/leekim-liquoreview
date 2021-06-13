<%@ page contentType ="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp" %>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/category.js" />"></script>

</head>
<body class="">
	<div class="wrapper">
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
								<h4 class="card-title">알콜 컨텐츠 카테고리 관리</h4>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="card">
										<div class="card-header">
											<h5 class="card-sub-title">top category</h5>
										</div>
										<div class="card-body">
											<div class="table-responsive">
												<table class="table">
													<thead class="text-primary">
														<tr>
															<th>
																<input id="topCateCheck" type="checkbox" name="topCateCheck" onClick="checkAll()"/>
															</th>
															<th>Name</th>
															<th>REGDATE</th>
															<th>최종수정일시</th>
															<th>SUB</th>
														</tr>
													</thead>
													<tbody id="tbl_topcate_data_container">
													</tbody>
												</table>
												<input id="hidden_topcategory_id" type="hidden" name="topcategory_id" value=""/>
											</div>
										</div>
										<div class="card-footer">
											<nav class="blog-pagination justify-content-center d-flex">
												<div class="prevPage">이전</div>
												<ul class="pagination top_pagination">
													<li><a href="#none">1</a></li>
												</ul>
												<div class="nextPage">다음</div>
											</nav>
											<div class="row">
												<div class="update ml-3 mr-3">
													<button type="button" class="btn btn-primary btn-round" name="bt_top_add" onClick="topAddPop()">
														top cate 추가
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_top_edit" onClick="topModiPop(this)">
														top cate 수정
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_top_delete" onClick="topDel()">
														top cate 삭제
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_top_refresh" onClick="topRefresh()">
														refresh
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="card">
										<div class="card-header">
											<h5 class="card-sub-title">sub category</h5>
										</div>
										<div class="card-body">
											<div class="table-responsive">
												<table class="table">
													<thead class="text-primary">
														<tr>
															<th>No</th>
															<th>TopName</th>
															<th>SubName</th>
															<th>REGDATE</th>
															<th>최종수정일시</th>
														</tr>
													</thead>
													<tbody id="tbl_subcate_data_container">
													</tbody>
												</table>
												<input id="hidden_subcategory_id" type="hidden" name="subcategory_id" value=""/>
												<input id="hidden_sub_topcategory_id" type="hidden" name="topcategory_id" value=""/>
											</div>
										</div>
										<div class="card-footer">
											<nav class="blog-pagination justify-content-center d-flex">
												<div class="prevPage">이전</div>
												<ul class="pagination sub_pagination">
													<li><a href="#none">1</a></li>
												</ul>
												<div class="nextPage">다음</div>
											</nav>
											<div class="row">
												<div class="update ml-3 mr-3">
													<button type="button" class="btn btn-primary btn-round" name="bt_sub_add" onClick="subAddPop()">
														sub cate 추가
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_sub_edit" onClick="subModiPop(this)">
														sub cate 수정
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_sub_delete" onClick="subDel()">
														sub cate 삭제
													</button>
													<button type="button" class="btn btn-primary btn-round" name="bt_sub_refresh" onClick="subRefresh()">
														refresh
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- modal setting -->
							<div id="cateModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<!-- Modal content :: 카테고리명 추가 또는 수정 -->
								</div>
							</div>
							<!-- modal end -->
							<!-- card-body end -->
							<div class="card-footer">
								<!-- 검색영역 -->
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<select id="cateLevel" class="form-control" name="cateLevel">
												<option value="e">선택</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<c:choose>
												<c:when test="${empty param.searchWord}">
													<input class=form-control " type="text" name="searchWord" onkeydown="memberSerarchByEnter()"/>
												</c:when>
												<c:otherwise>
													<input class=form-control " type="text" name="searchWord" onkeydown="memberSerarchByEnter()" value="<c:out value="${param.searchWord }"/>"/>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div ;class="col-md-2">
										<div class="form-group">
											<button class="btn btn-primary" type="button"
												onClick="search()">검색</button>
										</div>
									</div>
									<div class="col-md-2">
										<button class="btn btn-primary" type="button" onClick="excelDown()">
											Excel 다운로드
										</button>
									</div>
								</div>
							</div>
							<!-- card-footer end -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- main-panel end -->
	</div>
	<!-- wrapper end -->
	<!--   Core JS Files   -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp"%>
</body>
</html>