<%@ contentType ="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	function getDetail(member_id) {
		$("form").attr({
			action : "/admin/member" + member_id,
			method : "GET"
		});
		$("form").submit();
	}

	function search() {
		var searchMode = $("select[name='searchMode']").val();
		var searchAuth = $("select[name='searchAuth']").val();
		var searchWord = $("select[name='searchWord']").val();

		$("input[name='search_Mode']").val(searchMode);
		$("input[name='search_Auth']").val(searchAuth);
		$("input[name='search_Word']").val(searchWord);
		//if(searchWord==""){alert("검색어를 입력해주세요.");return;}
		$("form").attr({
			action : "/admin/member/search",
			method : "post"
		});
		$("form").submit();
	}
</script>
</head>
<body class="">
	<div class="wrapper">
		<!-- sidebar -->
		<%@  include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>

		<div class="main-panel">
			<!-- Navbar -->
			<%@  include file="/WEB-INF/views/admin/inc/navi.jsp"%>

			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">일반회원정보</h4>
								<button class="btn btn-primary btn-round" type="button"
									onClick="location.href='/member/member-regist'">
									신규 회원 정보 추가</button>
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
												<th>상세관리</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td colspan="3">
													<%-- <%for (int i = pager.getFirstPage(); i <= pager.getLastPage(); i++) {%> 
														<%if (i > pager.getTotalPage())break;%> 
														<a href="/admin/member?currentPage=<%=i%>">[<%=i%>]</a> 
													<%}%> --%>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
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
								<!-- start pager area -->
								<nav class="blog-pagination justify-content-center d-flex">
									<ul class="pagination">
										<!-- prev page arrow -->
										<li><a href="#none"> <strong>&laquo;</strong>
										</a></li>
										<li><a href="javascript:alert('첫 페이지입니다')"> <strong>&laquo;</strong>
										</a></li>
										<!-- page num links -->
										<%-- <li <%if(request.getParameter("currentPage") !=null && Integer.parseInt(request.getParameter("currentPage"))==i){%>class="active"<%}%>><a href="/admin/member?currentPage=<%=i%>">[<%=i %>]</a></li> --%>

										<!-- next page arrow -->
										<li><a href="#none"> <strong>&raquo;</strong>
										</a></li>
										<li><a href="javascript:alert('마지막 페이지입니다')"> <strong>&raquo;</strong>
										</a></li>
									</ul>
								</nav>
								<!-- pager end -->
							</div>
							<div class="card-footer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- main-panel end -->
	</div>
	<!-- wrapper end -->
	<!-- cord js files -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp'%>
</body>
</html>