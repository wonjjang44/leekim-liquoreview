/**
 * 
 */
//체크박스 전체 제어용 변수
var flag = false;

jQuery(document).ready(function(){
	console.log("boardTag.js import 성공");
	$("#boardTagModal").hide();
	getBoardTagList();
});

// 글머리(boardTag) 리스트 조회
function getBoardTagList() {
	console.log("글머리 전체티스트 조회 클라이언트 요청 출발!");
	$.ajax({
		url:"/rest/admin/board/tag",
		type:"GET",
		success:function(data) {
			console.log(data);
			handleBoardTagList(data);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

function handleBoardTagList(data) {
	console.log(typeof(data));
	let con = $("#tbl_data_container");
	con.html("");//기존 데이터 init
	for(let key in data) {
		console.log(data[key]);
		let obj = data[key];
		con.append("<tr id='table_tr_"+obj.tag_id+"'>");
		//tag_id
		con.append("<td><input class='tag_id_checkbox' type='checkbox' name='tag_id' value='"+obj.tag_id+"'/></td>");
		//tag_name
		con.append("<td>"+obj.tag_name+"</td>");
		//regdate
		con.append("<td>"+obj.regdate+"</td>");
		//last_modi_ymd
		con.append("<td>"+obj.last_modi_ymd+"</td>");
		con.append("</tr>");
	}
	//modal close
	$("#boardTagModal").modal('hide');
}

//글머리추가버튼 모달 요청
function boardTagAddPop() {
	let checkArray = checkCnt();
	if (checkArray.length == 0) {
		boardTagModalPop();
	} else {
		alert("체크박스 해제 후 다시 진행해주세요.");
		$(".tag_id_checkbox").prop("checked", false);
		$("#hidden_tag_id").val(0);
	}
}

//글머리 수정버튼 모달 요청
function boardTagModiPop() {
	let checkArray = checkCnt();
	if (checkArray.length == 0) {
		alert("수정할 글머리를 체크한 후 다시 진행해주세요.");
	} else if(checkArray.length > 1) {
		alert("글머리 수정은 1회에 1건씩 진행해주세요.");
		$(".tag_id_checkbox").prop("checked",false);
	} else {
		boardTagModalPop();
	}
}

//글머리 추가 및 수정 Modal pop
function boardTagModalPop() {
	console.log("글머리추가/수정 버튼 클릭으로 boardTagModalPop 호출");
	
	//modal-dialog 영역 채우기 전 초기화
	$(".modal-dialog").empty();
	
	let tag_id = 0;
	if ($("#hidden_tag_id").val() != "" || $("#hidden_tag_id").val() != 0) {
		tag_id = $("#hidden_tag_id").val();
	}
	$.ajax({
		url:"/rest/admin/board/modal/"+tag_id,
		type:"GET",
		dataType:"html",
		contentType:"application/html; charset=UTF8",
		success: (data) => {
			console.log(data);
			$(".modal-dialog").html(data);
			$("boardTagModal").modal('toggle');
		},
		error: (data) => {
			console.log("에러발생");
			console.log(data);
			alert("에러가 발생했습니다.");
		}
	});
}

function validateboardTagModal() {
	if (!($("#new_tag_name").val())) {
		alert("글머리명을 입력해주세요.");
		return false;
	} else {
		return true;
	}
}

//체크박스 전체선택/해제 토클
function checkAll() {
	console.log("체크 되었는가?");
	if (flag==true) {
		$(".tag_id_checkbox").prop("checked",false);
	} else {
		$(".tag_id_checkbox").prop("checked",true);
	}
	//체크박스 조작 후 flag 반전
	flag=!flag;
	console.log("flag 반전 확인 : "+flag);
}

//count checked checkbox
function checkCnt() {
	console.log("선택된 체크박스 갯수와 value를 얻기 위해 checkCnt() 호출됨");
	let checkArray = new Array();
	$(".tag_id_checkbox").each(function(i) {
		console.log($(this).prop("checked"));
		if ($(this).prop("checked")) {
			checkArray.push(parseInt($(this).val()));
		}
	});
	return checkArray;
}

function checkboxHandle(tag_id) {
	console.log("체크박스 눌러 채집된 tag_id 확인: "+tag_id);
	$("#hidden_tag_id").val(tag_id);
	console.log("체크박스 눌러 hidden에 보관된 tag_id 확인 : "+$("#hidden_tag_id").val());
}

//모달 내 입력 초기화
function reset() {
	console.log("글머리 모달 내 초기화 버튼 클릭");
	$("#tag_add_form")[0].reset();
}

//글머리 추가
function tagAdd() {
	//validate form in modal
	let validResult = validateAuthModal();
	
	if (validResult) {
		$.ajax({
			url:"/rest/admin/board/tag",
			type:"POST",
			data: {
				tag_name:$("#new_tag_name").val()
			},
			success:(result) => {
				let tagInsertResult = JSON.parse(result);
				handleInsertResult(tagInsertResult);
			},
			error:(data) => {
				console.log("글머리 추가 실패");
				console.log(data);
			}
		});
	}
}

//글머리 등록 결과처리
function handleInsertResult(result) {
	console.log(result);
	if (result.resultCode==="1") {
		alert(result.msg);
		//리스트 갱신, 모달 close
		getBoardTagList();
	} else {
		alert(result.msg);
		return;
	}
}

//글머리 목록 삭제
function boardTagDel() {
	let checkArray = checkCnt();
	if (checkArray.length == 0) {
		alert("삭제할 글머리를 체크하세요.");
	} else {
		if(confirm("선택한 글머리를 정말 삭제하시겠습니까?") == true) {
			$.ajax({
				type:"DELETE",
				url:"/rest/admin/board/tag/"+checkArray,
				contentType:"application/json",
				dataType:'json',
				success:(result) => {
					console.log(result);
					getBoardTagList();
				},
				error:(result) => {
					console.log(result);
				}
			});
		}
	}
}