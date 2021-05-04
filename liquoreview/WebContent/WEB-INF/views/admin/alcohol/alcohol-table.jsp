<%@page import="com.liquoreview.common.Pager"%>
<%@page import="com.liquoreview.model.domain.Alcohol"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	//페이지가 로드 될 때 주류 항목 조회
	$.ajax({
		url : "/admin/alcohol/alcoholLst",
		type : "get",
		//dataType : "json",
		//contentType : "application/json; charset = UTF-8",
		data : {},
		success : function(data){
			var alcoholVal = '${alcoholVal}';
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	
	
	//술 항목 추가
	$("#al_add_btn").click(function(){
		location.href="/admin/alcohol/alcoholReg";
	});
	
	//모달 팝업창 주류 삭제
	$("#al_del_btn").click(function(){
		alert("주류 삭제 완료");
		
		//모달 팝업창 닫기
		$('#delModal').modal("hide");
	});

});

function changeModalValue(alcohol_id){
	$("input[name='alcohol_id']").val(alcohol_id);
}

function del(){
	$("form[name='del_form']").attr({
		method:"post",
		action:"/admin/alcohols/delete/"+$("input[name='alcohol_id']").val()
	});
	$("form[name='del_form']").submit();
}


//페이징 함수
function selChange() {
	//var sel = document.getElementById('cntPerPage').value;
	var sel  = $("#cntPerPage").val();
	location.href="/admin/alcohol/alcoholLst?nowPage=${pager.nowPage}&cntPerPage="+sel;
}


/*************excel upload*************/
function checkFileType(filePath) {
	var fileFormat = filePath.split(".");

	//엑셀 파일인지 체크
	if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
		return true;
	} else {
		return false;
	}
}

function check() {
	var file = $("#excelFile").val();

	if (file == "" || file == null) {
		alert("파일을 선택해주세요.");

		return false;
	} else if (!checkFileType(file)) {
		alert("엑셀 파일만 업로드 가능합니다.");

		return false;
	}

	var param = {
		excelFile : $("#excelFile").val()
	};
	
	if (confirm("업로드 하시겠습니까?")) {
		var data = new FormData(document.getElementById('excelUploadForm'));//$로 하니까 안먹힘. $("#excelUploadForm")
		
        $.ajax({
            url: "/excelUpload",
            data: data,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
				console.log(data);
				console.log(data.excelResult);
                if(data> 0){
	                alert("업로드 완료");
                }
            },
            error : function(xhr){
            	alert("이런.. 파일업로드에 문제가 생겼습니다..");
            	console.log(xhr);
            }
        })

	}
}

</script>
</head>

<body>
 	<!-- Start Modal -->
		<div id="delModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Start Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">카테고리 삭제</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form name="del_form">
							<input type="hidden" name="alcohol_id">
							<p>정말 삭제 하시겠습니까?</p>
						</form>
					</div>
					
					<div class="modal-footer">
						<input type="button" class="btn btn-primary btn-round" id="al_del_btn" value="Delete">
						<button type="button" class="btn btn-primary btn-round" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- End Modal content-->
			</div>
	</div>
	<!-- End Modal -->

	<div class="wrapper ">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>

		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>
			<!-- End Navbar -->
			<!-- <div class="panel-header panel-header-sm"></div> -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">주류 정보 관리</h4>
								<button type="button" id = "al_add_btn" class="btn btn-primary btn-round" >주류 항목 추가</button>
								
									<!-- 파일 업로드 Start -->
									<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data">
					                	<div class="form-group row" align="right">
								            <label for="inputFile" class="col-sm-2 col-form-label" style = "left:820px;"><strong>첨부 파일</strong></label>
								            <div class="col-sm-10"  align="right">
								                <div class="custom-file" id="inputFile" style = "width : 270px;">
								                    <input name="excelFile" type="file" class="custom-file-input" id="excelFile" style = "width : 270px;">
								                    <label class="custom-file-label" for="excelFile" style="position: absolute; left : 20px;">파일선택</label>
								                </div>
								                
									          <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" style = "position: relative; top : -15px;"><span>추가</span></button>
								            </div>
							        	</div>
									</form>
								<!-- 파일 업로드 End -->
								
								<!-- 옵션선택 Start -->
								<div id="outter">
									<div style="float: right;">
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
								
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class=" text-primary">
											<tr>
												<th>번호</th>
												<th>분류</th>
												<th>주류종류</th>
												<th>주류명</th>
												<th>도수</th>
												<th>삭제</th>
											</tr>
										</thead>
										<tbody>
										<c:choose>
											<c:when test="${alcoholVal == null}">
												<tr>
													<td colspan="6" align="center">조회 결과가 존재하지 않습니다.</td>  
											 	</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${alcoholVal }" var="alcoholVal">
													<tr>
														<td>${alcoholVal.getALCOHOL_ID() }</td>
														<td>${alcoholVal.getTOP_NM() }</td>
														<td>${alcoholVal.getSUB_NM() }</td>
														<td><a href = "#">${alcoholVal.getALCOHOL_NM()}</a></td>
														<td>${alcoholVal.getALC_DEGREE() }</td>
														<td>
															<button type = "button" id = "al_del" class = "btn btn-primary btn-round" 
																		  data-toggle="modal"  data-target="#delModal" data-backdrop = "static">삭제
															</button>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										</tbody>
									</table>
									
									<!-- 페이징 Start -->
									<div style="display: block; text-align: center;">		
										<ul class = "pagination" style = "justify-content: center;">
											<c:choose>
												<c:when test="${pager.startPage != 1 }">
													<li class = "page-item">
														<a class = "page-link" href="/admin/alcohol/alcoholLst?nowPage=${pager.startPage - 1 }&cntPerPage=${pager.cntPerPage}">&lt;</a>
													</li>	
												</c:when>
												<c:otherwise>
													<a class = "page-link" href="javascript:alert('첫 페이지입니다');">&lt;</a>
												</c:otherwise>
											</c:choose>
											<c:forEach begin="${pager.startPage }" end="${pager.endPage }" var="p">
												<c:choose>
													<c:when test="${p == pager.nowPage }">
														<li class = "page-item active"><a class = "page-link" href="/admin/alcohol/alcoholLst?nowPage=${p }&cntPerPage=${pager.cntPerPage}">${p }</a></li>
														<%-- <b>${p }</b> --%>
													</c:when>
													<c:when test="${p != pager.nowPage }">
														<li class = "page-item">
															<a class = "page-link" href="/admin/alcohol/alcoholLst?nowPage=${p }&cntPerPage=${pager.cntPerPage}">${p }</a>
														</li>
													</c:when>
												</c:choose>
											</c:forEach>
											<c:choose>
												<c:when test="${pager.endPage != pager.lastPage}">
													<li class = "page-item">
														<a class = "page-link" href="/admin/alcohol/alcoholLst?nowPage=${pager.endPage+1 }&cntPerPage=${pager.cntPerPage}">&gt;</a>
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

</html>