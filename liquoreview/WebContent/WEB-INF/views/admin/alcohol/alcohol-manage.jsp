<%@ page contentType ="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp" %>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/js/pages/alcohol.js" />"></script>
</head>
<body class="">
	<div class="wrapper">
		<!-- sidebar -->
		<%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp" %>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/WEB-INF/views/admin/inc/navi.jsp" %>
			<!-- End Navbar -->
			
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">알콜 컨텐츠 관리</h4>
								<select id="topSelect" onChange="getSubList(this.value)">
									<option>상위분류선택</option>
								</select>
								<select id="subSelect" onChange="getSortedAlcList(this.value)">
									<option>하위분류선택</option>
								</select>
								<button 
									class="btn btn-primary btn-round" 
									type="button"
									onClick="alcAddPop()"
								>
									단건 등록
								</button>
								<button 
									class="btn btn-primary btn-round" 
									type="button"
									onClick="excelUpload()"
								>
									Excel Upload
								</button>
								<button 
									class="btn btn-primary btn-round" 
									type="button"
									onClick="excelDownload()"
								>
									Excel Download
								</button>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>
													<input id="alcCheck" type="checkbox" name="alcCheck" onClick="checkAll()"/>
												</th>
												<th>No</th>
												<th>이미지</th>
												<th>topCate</th>
												<th>subCate</th>
												<th>이름</th>
												<th>도수</th>
												<th>등록일</th>
												<th>최종수정일</th>
												<th>상세</th>
											</tr>
										</thead>
										<tbody id="tbl_data_container">
										</tbody>
									</table>
									<input id="hidden_alcohol_id" type="hidden" name="alcohol_id" value=""/>
								</div>
								<div class="excelFileFormArea">
									<form id="ajaxExcelForm" class="excelFileForm" enctype="multipart/form-data">
										<input id="ajaxExcelFile" type="file" name="ajaxExcelFile" onChange="ajaxFileChange()" style="display:none;"/>
									</form>
								</div>
							</div>
							<!-- modal setting -->
							<div id="alcoholModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<!-- Modal content -->
								</div>
							</div>
							<!-- modal end -->
							<!-- card-body end -->
							<div class="card-footer">
								<!-- start pager area -->
								<nav class="blog-pagination justify-content-center">
									<div class="prevPage">
										<!-- prev page arrow -->
										<a href="javascript:prevPage()"><strong>&laquo;</strong></a>
									</div>
									<!-- page num links -->
									<ul class="pagination">
									</ul>
									<!-- next page arrow -->
									<div class="nextPage">
										<a href="javascript:nextPage()"><strong>&raquo;</strong></a>
									</div>
									<form id="hiddenListPageForm">
										<input type="hidden" name="currentPage" value=""/>
										<input type="hidden" name="pageSize" value=""/>
										<input type="hidden" name="curPos" value=""/>
									</form>
								</nav>
								<!-- pager end -->
								<!-- searching area -->
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<select id="searchType" class="form-control" name="searchType">
												<option value="e" <c:out value="${searchCriteria.searchType == null ? 'selected' : ' '}"/>>선택</option>
												<option value="i" <c:out value="${searchCriteria.searchType eq 'i' ? 'selected' : ' '}"/>>ID</option>
												<option value="n" <c:out value="${searchCriteria.searchType eq 'n' ? 'selected' : ' '}"/>>NAME</option>
												<option value="p" <c:out value="${searchCriteria.searchType eq 'p' ? 'selected' : ' '}"/>>PHONENUM</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<c:choose>
												<c:when test="${empty param.searchWord}">
													<input class="form-control" type="text" name="searchWord" onkeydown="alcoholSerarchByEnter()"/>
												</c:when>
												<c:otherwise>
													<input class=form-control " type="text" name="searchWord" onkeydown="alcoholSerarchByEnter()" value="<c:out value="${param.searchWord }"/>"/>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<button class="btn btn-primary btn-round" type="button" onClick="search()">
												검색
											</button>
										</div>
									</div>
								</div>
								<!-- 검색영역 종료 -->
							</div>
							<!-- card footer end -->
						</div>
						<!-- card end -->
					</div>
				</div>
			</div>
		</div>
		<!-- main-panel end -->
	</div>
	<!-- wrapper end -->
	<!-- core js files -->
	<%@ include file="/WEB-INF/views/admin/inc/tail.jsp" %>
</body>
</html>