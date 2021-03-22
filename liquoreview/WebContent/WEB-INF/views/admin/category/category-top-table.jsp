<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//Pager pager = (Pager)request.getAttribute("pager");
	//List<Topcategory> topList = (List)request.getAttribute("topList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){

});
</script>
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
										<form name="add_form">
											<input type="text" class="form-control" placeholder="신규 상위 카테고리 이름을 입력해주세요." name="name"/>
											<input type="button" class="btn btn-primary btn-round" id="add_btn" value="Top 카테고리 항목 추가"/>
										</form>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>TopCategory_id</th>
												<th>Name</th>
												<th>수정</th>
												<th>삭제</th>
											</tr>
										</thead>
										<tbody>
										<tr>
											<td></td>
											<td></td>
											<td>
												<button type="button" class="btn btn-primary btn-round" onClick="changeModalValue()" 
																  data-toggle="modal" data-target="#editModal" data-backdrop = "static">수정</button>
											</td>
											<td>
												<button type="button" class="btn btn-primary btn-round" onClick="changeModalValue()" 
																  data-toggle="modal" data-target="#delModal" data-backdrop = "static">삭제</button>
											</td>
										</tr>
											
										</tbody>
										<tr>
											<td colspan="4">
												<a href="/admin/topcategories?currentPage=">1</a>
											</td>  
									 	</tr>
									</table>
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

</html>