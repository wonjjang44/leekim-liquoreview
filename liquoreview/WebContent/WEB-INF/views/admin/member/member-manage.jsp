<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/member.js" />"></script>
</head>
<body class="">
	<div class="wrapper">
		<!-- sidebar -->
		<%@  include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>

			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">일반회원정보</h4>
								
								
								<button 
									class="btn btn-primary btn-round" 
									type="button"
									onClick="location.href='/member/member-regist'"
								>
									신규 회원 정보 추가
								</button>
								<button 
									class="btn btn-primary btn-round" 
									type="button"
									onClick="saveAsExcel()"
								>
									excel다운로드
								</button>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>No</th>
												<th>USERID</th>
												<th>USERNAME</th>
												<th>BIRTH</th>
												<th>EMAIL</th>
												<th>PHONE</th>
												<th>REGDATE</th>
												<th>AUTH</th>
												<th>휴면회원여부</th>
												<th>삭제회원여부</th>
												<th>회원상세</th>
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
									<div class="prevPage">
										<!-- prev page arrow -->
										<!--
										<a href="javascript:prevPage()"> 
											<strong>&laquo;</strong>
										</a>
										-->
									</div>
									<!-- page num links -->
									<ul class="pagination">
										<%-- 
											<li <%if(request.getParameter("currentPage") !=null && Integer.parseInt(request.getParameter("currentPage"))==i){%>class="active"<%}%>>
												<a href="/admin/member?currentPage=<%=i%>">
													[<%=i %>]
												</a>
											</li> 
										--%>
									</ul>
									<div class="nextPage">
										<!-- next page arrow -->
										<!--
										<a href="javascript:nextPage()"> 
											<strong>&raquo;</strong>
										</a>
										 -->
									</div>
									<form id="hiddenListPageForm">
										<input type="hidden" name="currentPage" value="" />
										<input type="hidden" name="pageSize" value="" />
									</form>
								</nav>
								<!-- pager end -->
								<!-- 검색영역 -->
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<select class="form-control" name="searchMode">
												<option value="userid">ID</option>
												<option value="username">NAME</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<select class="form-control" name="searchAuth">
												<option value="allMember">전체회원</option>
												<option value="client">일반회원</option>
												<option value="manager">관리자</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<input class=form-control " type="text" name="searchWord" />
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<button class="btn btn-primary btn-round" type="button"
												onClick="search()">검색</button>
										</div>
									</div>
								</div>
								<!-- 검색영역 종료-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- main-panel end -->
	</div>
	<!-- wrapper end -->
	<!-- cord js files -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp"%>
</body>
</html>