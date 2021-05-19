<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/boardTag.js" />"></script>
</head>
<body class="">
	<div class="wrapper">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp" %>
			<!-- End Navbar -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">게시판 글머리 관리</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>
													<input id="tagCheck" type="checkbox" name="tagCheck" onClick="checkAll()"/>
												</th>
												<th>글머리명</th>
												<th>글머리 등록일시</th>
												<th>최종 수정일시</th>
											</tr>
										</thead>
										<tbody id="tbl_data_container">
										</tbody>
									</table>
									<input id="hidden_tag_id" type="hidden" name="tag_id"/>
								</div>
							</div>
							<!-- modal setting -->
							<div id="boardTagModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<!-- 글머리 목록 추가 또는 수정 -->
								</div>
							</div>
							<!-- modal end -->
							<!-- card-body end -->
							<div class="card-footer">
								<div class="row">
									<div class="update ml-5 mr-5">
										<button type="button" class="btn btn-primary btn-round" name="btn_add" onClick="boardTagAddPop()">
											글머리추가
										</button>
										<button type="button" class="btn btn-primary btn-round" name="btn_edit" onClick="boardTagModiPop()">
											글머리수정
										</button>
										<button type="button" class="btn btn-primary btn-round" name="btn_delete" onClick="boardTagDel()">
											글머리삭제
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
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp" %>
</body>
</html>