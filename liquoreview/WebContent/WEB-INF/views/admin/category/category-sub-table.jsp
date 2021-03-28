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
	//로드 시 상위 카테고리 목록 가져오기   
	$.ajax({
		url : "/admin/alcohol/topLst",
		type : "get",
		data : {},
		success : function(data){
			if(data != null || data != ""){
				$.each(data, function(index, value){
					$("#top_option_val").append("<option value = '"+value.topcategory_ID+"'>"+value.top_NM+"</option>");
				});
			}
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	
	
	//로드 시 하위 카테고리 목록 전체 조회
	$.ajax({
		url : "/admin/category/subCateLstIqr",
		type : "get",
		data :{},
		success : function(data){
			var sub_cateory_val = '${subCategoryVal}';			
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	
	
	
	
	
	
	
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
												<select style="font-size: 14px;height: 50px;" class="form-control" name="topcategory_id" id="top_option_val">
													<option value="">전체</option>
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
											<tr>
												<th>순번</th>
												<th>상위 카테고리 명</th>
												<th>하위 카테고리 명</th>
												<th>수정</th>
												<th>삭제</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${subCategoryVal != null }">
													<c:forEach items="${subCategoryVal }" var="subCategoryVal">
														<tr>
															<td>${subCategoryVal.getSUBCATEGORY_ID() }</td>
															<td>${subCategoryVal.getTOP_NM() }</td>
															<td>${subCategoryVal.getSUB_NM()}</td>
															<td>
																<button type="button"
																	class="btn btn-primary btn-round" data-toggle="modal" data-target="#editModal" data-backdrop = "static">수정</button>
															</td>
															<td>
																<button type="button"
																	class="btn btn-primary btn-round"  data-toggle="modal" data-target="#delModal" data-backdrop = "static">삭제</button>
															</td>
														</tr>
													</c:forEach>	
											 </c:when>	
											 <c:otherwise>
											 	<tr>
											 		<td colspan="5" align="center">조회 결과가 존재하지 않습니다.</td>
											 	</tr>
											 </c:otherwise>
									 	</c:choose>
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