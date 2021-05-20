<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/board.js" />"></script>
</head>
<body class="">
	<div class="wrapper">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp" %>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp" %>
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">게시판 관리</h4>
								
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th></th>
											</tr>
										</thead>
										<tbody id="tbl_data_container">
										</tbody>
									</table>
								</div>
							</div>
							<div class="card-footer">
								<!-- start pager area -->
								<nav class="blog-pagination justify-content-center d-flex">
									<div class="prevPage"></div>
									<ul class="pagination"></ul>
									<div class="nextPage"></div>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>