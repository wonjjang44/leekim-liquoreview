<%@ page contentType ="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/admin/inc/head.jsp"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		//getAuthList();
		
		//권한추가 모달제어
		$("#authModal").hide();
		
		//체크박스 value 핸들
		//checkboxHandle();
		
		console.log("확인 : "+$("#new_des").val(),$("#adm_assign_chk").val(),
				$("#mem_adm_chk").val(),
				$("#cate_adm_chk").val(),
				$("#alc_adm_chk").val(),
				$("#rev_adm_chk").val(),
				$("#rev_comm_adm_chk").val(),
				$("#board_adm_chk").val(),
				$("#board_comm_adm_chk").val());
	});
	
	function getAuthList() {
		$.ajax({
			url:"/rest/auth",
			type:"GET",
			success:function(data){
				console.log(data);
			}
		});
	}
	
	//권한 추가 Modal pop 
	function authAddPop() {
		$("#authModal").modal('toggle');
	}
	
	function validateAuthModal() {
		if(!($("#new_des").val())){
			alert("권한명을 입력해주세요");
			return false;
		}else{
			return true;
		}
	}
	
	/*
						<form id="auth_add_form" name="auth_add_form">
												<p class="desArea">
													<label for="new_des">추가할 권한역할명</label>
													<input id="new_des" type="text" name="des" required	 />
												</p>
												<p class="checkArea1">
													<input id="adm_assign_chk" type="checkbox" name="adm_assign"/>
													<label for="adm_assign_chk">할당권한</label>
													<input id="mem_adm_chk"
	*/
	
	//checkbox value handling
	function checkboxHandle(){
		/* $("#auth_add_form").children("p").on("change", "input[type='checkbox']:checked", function(){
			$(this).parent().prevAll("p").find("input[type='checkbox']").attr('value', 'true');
		}); */														
		/* $("#auth_add_form").on("change", "input[type='checkbox']:checked", function(){
			$(this).parent().prevAll("p").find("input[type='checkbox']").attr('value', 'true');
		}); */
		
		$("#auth_add_form").children("p").children("input[type='checkbox']").each(function(){
			if($(this).prop("checked")) {
				$(this).attr('value','true');
			} else {
				$(this).attr('value','false');
			}
		});
		
		//console.log($("#newAuth").children("p"));
		/* $("#newAuth").children("p").on("change", "input[type='checkbox']:checked", function(){
			if($(this).prop("checked")) {
				$(this).attr('value','true');
				
			} else {
				$(this).attr('value','false');
			}
		}); */
	}
	
	//추가 권한 실제 insert 요청	
	function authAdd(){
		
		//모달 내 form validate
		var validResult = validateAuthModal();
		//첵박
		checkboxHandle();
		//확인
		console.log("첵박 세팅 후 확인 : "+$("#new_des").val(),$("#adm_assign_chk").val(),
				$("#mem_adm_chk").val(),
				$("#cate_adm_chk").val(),
				$("#alc_adm_chk").val(),
				$("#rev_adm_chk").val(),
				$("#rev_comm_adm_chk").val(),
				$("#board_adm_chk").val(),
				$("#board_comm_adm_chk").val());
		
		if (validResult) {
			$.ajax({
				url:"/rest/admin/auth",
				type:"POST",
				data:{
					des:$("#new_des").val(),
					adm_assign:$("#adm_assign_chk").val(),
					mem_adm:$("#mem_adm_chk").val(),
					cate_adm:$("#cate_adm_chk").val(),
					alc_adm:$("#alc_adm_chk").val(),
					rev_adm:$("#rev_adm_chk").val(),
					rev_comm_adm:$("#rev_comm_adm_chk").val(),
					board_adm:$("#board_adm_chk").val(),
					board_comm_adm:$("#board_comm_adm_chk").val()
				},
				success:function(data){
					if(data=="1"){
						console.log("권한추가 성공");
						console.log(data);
						$("#authModal").modal('toggle');
					}
				},error:function(data){
					console.log("권한추가 실패");
					console.log(data);
				}
			});
		}
	}
	
	
</script>

</head>
<body class="">
	<div class="wrapper">
		<%@ include file="/admin/inc/sidebar.jsp"%>
		<div class="main-panel">
			<!-- Navbar -->
			<%@ include file="/admin/inc/navi.jsp"%>
			<!-- End Navbar -->

			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">회원 권한 관리</h4>
								<!-- <button 
									class="btn btn-primary btn-round"
									type="button"
									onClick="location.href='/member/member-regist'"
								>
									신규 회원 정보 추가
								</button> -->
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead class="text-primary">
											<tr>
												<th>
													<input id="authCheck" type="checkbox" name="authCheck"/>
												</th>
												<th>권한<br/>코드</th>
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
										<tbody>
											<c:set var="num" value="${pager.num}"/>
											<c:set var="curPos" value="${pager.curPos}"/>
											<c:forEach var="auth" items="${authList}" begin="${pager.curPos}" end="${pager.curPos+pager.pageSize}">
												<tr>
													<td><input type="checkbox" value="${auth.auth_id }"/></td>
													<td>${auth.auth_id }</td>
													<td>${auth.des }</td>
													<td>
														<c:choose>
															<c:when test="${auth.adm_assign eq true}">
																<input type="checkbox" name="adm_assign" value="${auth.adm_assign }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="adm_assign" value="${auth.adm_assign }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.mem_adm eq true}">
																<input type="checkbox" name="mem_adm" value="${auth.mem_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="mem_adm" value="${auth.mem_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.cate_adm eq true}">
																<input type="checkbox" name="cate_adm" value="${auth.cate_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="cate_adm" value="${auth.cate_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.alc_adm eq true}">
																<input type="checkbox" name="alc_adm" value="${auth.alc_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="alc_adm" value="${auth.alc_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.rev_adm eq true}">
																<input type="checkbox" name="rev_adm" value="${auth.rev_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="rev_adm" value="${auth.rev_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.rev_comm_adm eq true}">
																<input type="checkbox" name="rev_comm_adm" value="${auth.rev_comm_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="rev_comm_adm" value="${auth.rev_comm_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.board_adm eq true}">
																<input type="checkbox" name="board_adm" value="${auth.board_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="board_adm" value="${auth.board_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${auth.board_comm_adm eq true}">
																<input type="checkbox" name="board_comm_adm" value="${auth.board_comm_adm }" checked="checked"/>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="board_comm_adm" value="${auth.board_comm_adm }"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>${auth.regdate }</td>
													<td>${auth.last_modi_ymd }</td>
												</tr>	
											</c:forEach>
										</tbody>
									</table>
									<input id="hidden_auth_id" type="hidden" name="auth_id" value=""/>
								</div>
							</div>
							<!-- modal setting-->
							<div id="authModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title" align="center">
												권한 추가
											</h4>							
										</div>
										<div class="modal-body">
											<form id="auth_add_form" name="auth_add_form">
												<p class="desArea">
													<label for="new_des">추가할 권한역할명</label>
													<input id="new_des" type="text" name="des" required	 />
												</p>
												<p class="checkArea1">
													<input id="adm_assign_chk" type="checkbox" name="adm_assign" value=""/>
													<label for="adm_assign_chk">할당권한</label>
													<input id="mem_adm_chk" type="checkbox" name="mem_adm" value=""/>
													<label for="mem_adm_chk">멤버관리</label>
													<input id="cate_adm_chk" type="checkbox" name="cate_adm" value=""/>
													<label for="cate_adm_chk">카테고리관리</label>
													<input id="alc_adm_chk" type="checkbox" name="alc_adm" value=""/>
													<label for="alc_adm_chk">알콜목록관리</label>
												</p>
												<p class="checkArea2">
													<input id="rev_adm_chk" type="checkbox" name="rev_adm" value=""/>
													<label for="rev_adm_chk">리뷰관리</label>
													<input id="rev_comm_adm_chk" type="checkbox" name="rev_adm" value=""/>
													<label for="rev_comm_adm_chk">리뷰댓글관리</label>
													<input id="board_adm_chk" type="checkbox" name="board_adm" value=""/>
													<label for="board_adm_chk">게시판관리</label>
													<input id="board_comm_adm_chk" type="checkbox" name="board_comm_adm" value=""/>
													<label for="board_comm_adm_chk">댓글관리</label>
												</p>
												<div class="addBtn">
													<input id="btnAuthInsert" type="button" title="권한추가" value="권한입력" onClick="authAdd()"/>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<div class="confirmBtn">
												<button class="btn btn-primary btn-round" onClick="reset()">초기화</button>
												<button class="btn btn-primary btn-round" data-dismiss="modal">닫기</button>
											</div>
										</div>
									</div>
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
										<button type="button" class="btn btn-primary btn-round" name="bt_edit" onClick="authModify()">
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
	<%@ include file="/admin/inc/tail.jsp"%>
</body>
</html>