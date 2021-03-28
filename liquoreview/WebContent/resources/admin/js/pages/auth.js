 /**
 * 
 */
//체크박스 전체 제어용 변수
var flag = false;

jQuery(document).ready(function(){
	console.log("auth.js import 성공");
	$("#authModal").hide();
	getAuthList();
});

//권한 리스트 조회
function getAuthList() {
	console.log("권한 전체리스트 조회 클라이언트 요청 출발!");
	$.ajax({
		url:"/rest/admin/auth",
		type:"GET",
		success:function(data){
			console.log(data);
			handleAuthList(data);
		},
		error:function(data){
			console.log(data);
		}
	});
}

//권한 리스트 조회결과 처리
function handleAuthList(data) {
	console.log(typeof(data));
	let con = $("#tbl_data_container");
	con.html("");//기존 데이터 init
	for (let key in data) {
		console.log(data[key]);
		let obj = data[key];
		con.append("<tr id='table_tr_"+obj.auth_id+"'>");
		//auth_id
		if (key<1) {//총관리자 권한 삭제방지
			con.append("<td><input type='checkbox' disabled='dsabled' name='auth_id' value='"+obj.auth_id+"'/></td>");
		} else {
			con.append("<td><input class='auth_id_checkbox' type='checkbox' name='auth_id' value='"+obj.auth_id+"' onChange='checkboxHandle("+obj.auth_id+")'/></td>");
		}
		//des
		con.append("<td>"+obj.des+"</td>");
		//adm_assign
		if(obj.adm_assign) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='adm_assign'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='dsabled' name='adm_assign'/></td>");
		}
		//mem_adm
		if (obj.mem_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='mem_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='mem_adm'/></td>");
		}
		//cate_adm
		if (obj.cate_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='cate_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='cate_adm'/></td>");
		}
		//alc_adm
		if (obj.alc_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='acl_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='acl_adm'/></td>");
		}
		//rev_adm
		if (obj.rev_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='rev_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='rev_adm'/></td>");
		}
		//rev_comm_adm
		if (obj.rev_comm_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='rev_comm_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='rev_comm_adm'/></td>");
		}
		//board_adm
		if (obj.board_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='board_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='board_adm'/></td>");
		}
		//board_comm_adm
		if (obj.board_comm_adm) {
			con.append("<td><input type='checkbox' disabled='dsabled' checked='checked' name='board_comm_adm'/></td>");
		} else {
			con.append("<td><input type='checkbox' disabled='disabled' name='board_comm_adm'/></td>");
		}
		//regdate
		con.append("<td>"+obj.regdate+"</td>");
		//last_modi_ymd
		con.append("<td>"+obj.last_modi_ymd+"</td>");
		con.append("</tr>");
	}
	//modal close
	$("#authModal").modal('hide');
}

//권한추가버튼 모달 요청
function authAddPop(){
	var checkArray = checkCnt();
	if(checkArray.length==0){
		authModalPop();
	}else{
		alert("체크박스 해제 후 다시 진행해주세요.");
		$(".auth_id_checkbox").prop("checked",false);
		$("#hidden_auth_id").val(0);
	}
}

//권한수정버튼 모달 요청
function authModiPop(){
	var checkArray = checkCnt();
	if(checkArray.length==0){
		alert("수정할 권한을 체크한 후 다시 진행해주세요.");
	}else if(checkArray.length>1){
		alert("권한수정은 1회에 1건만 진행해주세요.");
		$(".auth_id_checkbox").prop("checked",false);
	}else{
		authModalPop();
	}
}
//권한 추가 Modal pop - add
function authModalPop() {
	console.log("권한추가/수정 버튼 클릭으로 authModalPop 호출");
	
	//modal-dialog 영역 채우기 전 초기화
	$(".modal-dialog").empty();
	
	var auth_id=0;
	if($("#hidden_auth_id").val()!="" || $("#hidden_auth_id").val()!=0) {
		auth_id = $("#hidden_auth_id").val();
	}
	$.ajax({
		url:"/rest/admin/auth/modal/"+auth_id,
		type:"get",
		dataType:"html",
		contentType:"application/html; charset=UTF8",
		success:function(data) {
			console.log(data);
			$(".modal-dialog").html(data);
			$("#authModal").modal('toggle');
		},
		error:function(data) {
			console.log("에러발생");
			console.log(data);
			alert("에러가 발생했습니다.");
		}
	});
}

//권한 수정 Modal pop - add

function validateAuthModal() {
	if (!($("#new_des").val())) {
		alert("권한명을 입력해주세요.");
		return false;
	} else {
		return true;
	}
}

//체크박스 전체선택/해제 토클
function checkAll() {
	console.log("체크 되었는가?");
	if (flag==true) {
		$(".auth_id_checkbox").prop("checked",false);
	} else {
		$(".auth_id_checkbox").prop("checked",true);
	}
	//체크박스 조작 후 flag 반전
	flag=!flag;
	console.log("flag 반전 확인 : "+flag);
}
 

//count checked checkbox
function checkCnt() {
	console.log("선택된 체크박스 갯수와 value를 얻기 위해 checkCnt() 호출됨");
	let checkArray = new Array();
	$(".auth_id_checkbox").each(function(i) {
		console.log($(this).prop("checked"));
		if ($(this).prop("checked")) {
			checkArray.push(parseInt($(this).val()));
		}
	});
	return checkArray;
}

function checkboxHandle(auth_id) {
	console.log("체크박스 눌러 채집된 auth_id 확인 : "+auth_id);
	//auth_id를 보관할 hidden input의 value에 보관
	$("#hidden_auth_id").val(auth_id);
	console.log("체크박스 눌러 hidden에 보관된 auth_id 확인 : "+$("#hidden_auth_id").val());
}

//모달 내 입력 초기화
function reset() {
	console.log("권한추가모달 내 초기화 버튼 클릭");
	$("#auth_add_form")[0].reset();
}

function authAdd() {
	//validate form in modal
	var validResult = validateAuthModal();
	//checkboxHandle();
	
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
			data: {
				des:$("#new_des").val(),
				adm_assign:$("#adm_assign_chk").is(":checked"),
				mem_adm:$("#mem_adm_chk").is(":checked"),
				cate_adm:$("#cate_adm_chk").is(":checked"),
				alc_adm:$("#alc_adm_chk").is(":checked"),
				rev_adm:$("#rev_adm_chk").is(":checked"),
				rev_comm_adm:$("#rev_comm_adm_chk").is(":checked"),
				board_adm:$("#board_adm_chk").is(":checked"),
				board_comm_adm:$("#board_comm_adm_chk").is(":checked")
			},
			success:function(result) {
				let authInsertResult = JSON.parse(result);
				handleInsertResult(authInsertResult);
			},
			error:function(data) {
				console.log("권한추가 실패");
				console.log(data);
			}
		});
	}
}

//권한 등록 결과처리
function handleInsertResult(data) {
	console.log(data);
	console.log("result data타입확인 : "+typeof(data));
	if (data.resultCode==="1") {
		alert(data.msg);
		//리스트 갱신, 모달 close
		getAuthList();
	} else {
		alert(data.msg);
		return;
	}
}

//권한 목록 삭제
function authDel() {
	console.log("권한삭제 요청받음");
	//체크박스 카운트
	var checkArray = checkCnt();
	if (checkArray.length == 0) {
		alert("삭제할 상품을 체크하세요.");
	} else {
		console.log("checkArray확인 : "+checkArray);
		if (confirm("선택한 권한목록을 삭제하시겠습니까?") == true) {
			$.ajax({
				type:"DELETE",
				url:"/rest/admin/auth/"+checkArray,
				contentType:"application/json",
				dataType:'json',
				success:function(result) {
					console.log(result);
					getAuthList();
				},
				error:function(result) {
					console.log(result);
				}
			});
		}
	}
	
}