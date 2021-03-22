<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//Pager pager = (Pager)request.getAttribute("pager");
	//List<Subcategory> subList = (List)request.getAttribute("subList");
	
	if(request.getAttribute("msg") != null ){
		String msg=(String)request.getAttribute("msg");
		out.print("<script>alert('"+msg+"');</script>");
	}
	
%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	searchTopcategory();

	$("#regist_btn").click(function(){
		regist();
	});
	$("#edit_btn").click(function(){
		edit();
	});
	$("#del_btn").click(function(){
		del();
	});
	
});

function searchTopcategory(){
	$.ajax({
		url:"/rest/alcohols/topcategory",
		type:"get",
		success:function(result){
			createTopcategory(result);
		}
	});
}

function createTopcategory(obj){
	$("#topcategory").html("");
	$("#topcategory_modal").html("");
	for(var i=0;i<obj.length;i++){
		$("#topcategory").append("<option value='"+obj[i].topcategory_id+"'>"+obj[i].name+"</option>");
		$("#topcategory_modal").append("<option value='"+obj[i].topcategory_id+"'>"+obj[i].name+"</option>");
	}
}

function regist(){
	$("form[name='regist_form']").attr({
		method:"post",
		action:"/admin/subcategories"
	});
	$("form[name='regist_form']").submit();
}

function edit(){
	$("form[name='edit_form']").attr({
		method:"post",
		action:"/admin/subcategories/edit"
	});
	$("form[name='edit_form']").submit();
}

function del(){
	$("form[name='del_form']").attr({
		method:"post",
		action:"/admin/subcategories/delete/"+$("input[name='subcategory_id']").val()
	});
	$("form[name='del_form']").submit();
}

function changeModalValue(subcategory_id){
	$("input[name='subcategory_id']").val(subcategory_id);
}

</script>
</head>

<body class="">
	<div class="wrapper ">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>

		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>
			<!-- End Navbar -->
			<!-- <div class="panel-header panel-header-sm">
  
  
</div> -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Sub Category</h4>
								
								<div class="col-md-4 pr-1">
									<div class="row">
										<div class="form-group">
											<form name="regist_form">
												<select style="font-size: 14px;height: 50px;" class="form-control" name="topcategory_id" id="topcategory">
													<option value="null">TOP 카테고리 목록</option>
												</select>
												<br>
												<input type="text" class="form-control" placeholder="신규 하위 카테고리 이름을 입력해주세요." name="name"/>
												<input type="button" id="regist_btn" class="btn btn-primary btn-round" value="Sub 카테고리 항목 추가"/>
											</form>
										</div>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class=" text-primary">
											<th>SubCategory_ID</th>
											<th>TopCategory_Name</th>
											<th>SubCategory_Name</th>
											<th>수정</th>
											<th>삭제</th>
										</thead>
										<tbody>
											<tr>
												<td><button type="button"
														class="btn btn-primary btn-round" onClick="changeModalValue()" data-toggle="modal" data-target="#editModal">수정</button></td>
												<td><button type="button"
														class="btn btn-primary btn-round" onClick="changeModalValue()" data-toggle="modal" data-target="#delModal">삭제</button></td>
											</tr>
										<tr>
											<td colspan="5">
													<a href="/admin/subcategories?currentPage=">1</a>
											</td>  
									 	</tr>
										</tbody>
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