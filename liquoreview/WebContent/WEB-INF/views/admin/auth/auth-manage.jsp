<%@ page contentType ="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/auth.js" />"></script>
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
								<h4 class="card-title">회원 권한 관리</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>
													<input id="authCheck" type="checkbox" name="authCheck" onClick="checkAll()"/>
												</th>
												<th>역할</th>
												<th>할당<br/>권한</th>
												<th>멤버<br/>관리</th>
												<th>카테고리<br/>관리</th>
												<th>알콜목록<br/>관리</th>
												<th>리뷰<br/>관리</th>
												<th>리뷰댓글<br/>관리</th>
												<th>게시판<br/>관리</th>
												<th>댓글<br/>관리</th>
												<th>권한 생성일시</th>
												<th>최종 수정일시</th>
											</tr>
										</thead>
										<tbody id="tbl_data_container">
										</tbody>
									</table>
									<input id="hidden_auth_id" type="hidden" name="auth_id" value=""/>
								</div>
							</div>
							<!-- modal setting-->
							<div id="authModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<!-- Modal content :: 권한 추가 또는 권한 수정 -->
									
								</div>
							</div>
							<!-- modal end -->
							<!-- card-body end -->
							<div class="card-footer">
								<div class="row">
									<div class="update ml-5 mr-5">
										<button type="button" class="btn btn-primary btn-round" name="bt_add" onClick="authAddPop()">
											권한추가
										</button>
										<button type="button" class="btn btn-primary btn-round" name="bt_edit" onClick="authModiPop()">
											권한수정
										</button>
										<button type="button" class="btn btn-primary btn-round" name="bt_delete" onClick="authDel()">
											권한삭제
										</button>
									</div>
								</div>
							</div>
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