/**
 *  리쿼 카테고리 관리 페이지 제어용 스크립트
 */

jQuery(document).ready(function(){
	console.log("category.js import 성공");
	$("#cateModal").hide();
	getTopcateList();
	
	$("#cateModal").on('hidden.bs.modal', function(){
		console.log('모달 닫힘');
	});
});

//페이지 로드되며 탑 카테고리 리스트 조회
function getTopcateList() {
	console.log("탑 카테고리 리스트 조회 클라이언트 요청 출발!");
	$.ajax({
		url:"/rest/admin/alcohol/topcategory",
		type:"GET",
		success:function(data) {
			console.log(data);
			handleTopcateList(data.topcateList);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

// 탑 카테고리 리스트 조회결과 처리
function handleTopcateList(data) {
	console.log(typeof(data));
	console.log(data);
	let con = $("#tbl_topcate_data_container");
	con.html("");//기존 데이터 init
	if (data != null && data.length != 0) {
		for (let key in data) {
			console.log(data[key]);
			let obj = data[key];
			con.append("<tr id='top_table_tr_"+obj.topcategory_id+"'>");
			con.append("<td><input class='topCate_id_checkbox' type='checkbox' name='topCate_id' value='"+obj.topcategory_id+"' onChange='topCheckboxHandle("+obj.topcategory_id+")'/></td>");
			con.append("<td>"+obj.topname+"</td>");
			con.append("<td>"+obj.regdate+"</td>");
			con.append("<td>"+obj.last_modi_ymd+"</td>");
			con.append("<td><button type='button' onClick='getSubCategoryList("+obj.topcategory_id+")'>Sub보기</button></td>");
			con.append("</tr>");
		}
	} else {
		con.append("<tr><td colspan='5' style='text-align:center; line-height: 1.2rem; vertical-align:middle;'>저장된 top category data가 없습니다.</td></tr>");
	}
	//modal close
	$("#cateModal").modal('hide');
}

//sub category list 조회
function getSubCategoryList(topcategory_id) {
	console.log("서브카테고리리스트 조회 클라이언트 요청 출발!");
	console.log("topcategory_id 확인 : "+topcategory_id);
	
	$.ajax({
		url:"/rest/admin/alcohol/subcategory?topcategory_id="+topcategory_id,
		type:"GET",
		success:function(data) {
			console.log(data);
			handleSubcateList(data.sortedSubcateList);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

// sub category list 조회결과 handle
function handleSubcateList(data) {
	console.log(typeof(data));
	console.log(data);
	let con = $("#tbl_subcate_data_container");
	con.html("");//기존 데이터 init
	if (data != null && data.length != 0) {
		for (let key in data) {
			console.log(data[key]);
			let obj = data[key];
			con.append("<tr id='sub_table_tr_"+obj.subcategory_id+"'>");
			con.append("<td><input class='subCate_id_checkbox' type='checkbox' name='subCate_id' value='"+obj.subcategory_id+"' onChange='subCheckHandle("+obj.subcategory_id+")'/></td>");
			con.append("<td>"+obj.topcategory.topname+"</td>");
			con.append("<td>"+obj.subname+"</td>");
			con.append("<td>"+obj.regdate+"</td>");
			con.append("<td>"+obj.last_modi_ymd+"</td>");
			con.append("</tr>");
		}
	} else {
		con.append("<tr><td colspan='5' style='text-align:center; line-height: 1.2rem; vertical-align:middle;'>저장된 sub category data가 없습니다.</td></tr>");
	}
	//modal close
	$("#cateModal").modal('hide');
}

//top category 추가 모달 호출
function topAddPop() {
	var checkArray = checkCnt();
	if (checkArray.length == 0) {
		topCateModalPop();
	} else {
		alert("체크박스 해제 후 다시 진행해주세요.");
	}
	$(".topCate_id_checkbox").prop("checked", false);
	$("#hidden_topcategory_id").val(0);
}

//top category 수정 모달 호출
function topModiPop(e) {
	console.log($(e).attr('name'));
	var checkArray = checkCnt(e.name);
	if (checkArray.length == 0) {
		alert("수정할 top category를 선택한 후 다시 진행해주세요.");
	} else if (checkArray.length > 1) {
		alert("top category 수정은 1회에 1건만 진행해주세요.");
		$(".topCate_id_checkbox").prop("checked", false);
	} else {
		topCateModalPop();
	}
}

//sub category 추가 모달 호출
function subAddPop() {
	var checkArray = checkCnt();
	if (checkArray.length == 0) {
		subCateModalPop();
	} else {
		alert("체크박스 해제 후 다시 진행해주세요");
	}
	$(".subCate_id_checkbox").prop("checked", false);
}

//sub category 수정 모달 호출
function subModiPop(e) {
	console.log($(e).attr('name'));
	var checkArray = checkCnt(e.name);
	console.log("checkArray.length확인 : "+checkArray.length);
	if (checkArray.length == 0) {
		alert("수정할 sub category를 선택한 후 다시 진행해주세요.");
	} else if (checkArray.length > 1) {
		alert("sub category 수정은 1회에 1건만 진행해주세요.");
		$(".subCate_id_checkbox").prop("checked", false);
	} else {
		subCateModalPop();
	}
}

//subcategory 수정 모달 팝업에 필요한 subcategory_id hidden set
function subCheckHandle(subcategory_id) {
	console.log("subcategory 체크박스 눌러 채집된 subcategory_id 확인 : "+subcategory_id);
	$("#hidden_subcategory_id").val(subcategory_id);
	console.log("체크박스 눌러 hidden에 보관된 subcategory_id 확인 : "+$("#hidden_subcategory_id").val());
}

//top category 추가 / 수정 Modal pop
function topCateModalPop() {
	console.log("top category 추가/수정버튼 클릭으로 topCateModalPop 호출");
	
	//modal-dialog 영역 채우기 전 초기화
	$(".modal-dialog").empty();
	
	let topcategory_id = 0;
	if($("#hidden_topcategory_id").val() != "" || $("#hidden_topcategory_id").val() !=  0) {
		topcategory_id = $("#hidden_topcategory_id").val();
	}
	console.log("topid 확인 : "+topcategory_id);
	$.ajax({
		url:"/rest/admin/topCate/modal/"+topcategory_id,
		type:"GET",
		dataType:"html",
		contentType:"application/html; charset=UTF8",
		success:function(data) {
			console.log(data);
			$(".modal-dialog").html(data);
			$("#cateModal").modal('toggle');
		},
		error:function(data) {
			console.log("에러발생");
			console.log(data);
			alert("에러가 발생했습니다.");
		}
	});
}

//sub cate 추가 /수정 Modal pop
function subCateModalPop() {
	console.log("sub category 추가/수정버튼 클릭으로 subCateModalPop 호출");
	
	//modal-dialog 영역 채우기 전 초기화
	$(".modal-dialog").empty();
	
	let subcategory_id = 0;
	if($("#hidden_subcategory_id").val() != "" || $("#hidden_subcategory_id").val() !=  0) {
		subcategory_id = $("#hidden_subcategory_id").val();
	}
	
	console.log("모달 호출 직전 subcategory_id확인 : "+subcategory_id);
	
	$.ajax({
		url:"/rest/admin/subCate/modal/"+subcategory_id,
		type:"GET",
		dataType:"html",
		contentType:"application/html; charset=UTF8",
		success:function(data) {
			console.log(data);
			$(".modal-dialog").html(data);
			$("#cateModal").modal('toggle');
		},
		error:function(data) {
			console.log("에러발생");
			console.log(data);
			alert("에러가 발생했습니다.");
		}
	});
}

function validateCateModal() {
	if (!($("#new_top_name").val())) {
		alert("등록할 카테고리명을 입력해주세요.");
		return false;
	} else {
		return true;
	}
}

function validateSubCateModal() {
	if (!($("#topSelect").val()) || !($("#new_sub_name").val())) {
		alert("상위 카테고리 선택 또는 하위카테고리명 입력을 확인해주세요.");
		return false;
	} else {
		return true;
	}
}

//count checked checkbox
function checkCnt(obj) {
	console.log("선택된 체크박스 갯수와 value를 얻기 위해 checkCnt() 호출됨");
	console.log("obj확인 : "+obj);
	
	let checkArray = new Array();
	if(obj != undefined) {
		//topcate 수정, subcate 수정 모달 구분
		if (obj.includes('top')) {
			$(".topCate_id_checkbox").each(function(i) {
				console.log($(this).prop("checked"));
				if ($(this).prop("checked")) {
					checkArray.push(parseInt($(this).val()));
				}
			});
		} else {
			$(".subCate_id_checkbox").each(function(i) {
				console.log($(this).prop("checked"));
				if ($(this).prop("checked")) {
					checkArray.push(parseInt($(this).val()));
				}
			});
		}
	}
	return checkArray;
}

function topCheckboxHandle(topcategory_id) {
	$("#hidden_topcategory_id").val(topcategory_id);
}

function reset() {
	console.log("category 모달 내 초기화 버튼 클릭");
	$("#topcate_add_form")[0].reset();
}

//top category insert
function topcateAdd() {
	let validResult = validateCateModal();
	if (validResult) {
		$.ajax({
			url: "/rest/admin/alcohol/topcategory",
			type: "POST",
			data: {
				topname: $("#new_top_name").val()
			},
			success:function(result) {
				let topcateInsertResult = JSON.parse(result);
				handleTopcateInsertResult(topcateInsertResult);
			},
			error:function(result) {
				console.log("topcategory 추가 실패");
				console.log(result);
			}
		});
	}
}

//top category insert result handle
function handleTopcateInsertResult(data) {
	console.log("result data 타입확인 : "+typeof(data));
	if (data.resultCode === "1") {
		alert(data.msg);
		//리스트 갱신, 모달 close
		getTopcateList();
	} else {
		alert(data.msg);
		return;
	}
}

// top category 삭제1 : sub category 보유여부 조사
function topDel(e) {
	console.log("topcate 삭제 요청받음");
	var checkArray = checkCnt(e.name);
	if (checkArray.length == 0) {
		alert("삭제할 topcategory를 체크하세요.");
	} else {
		if(confirm("선택한 topcategory 목록을 삭제하시겠습니까?")) {
			$.ajax({
				type:"GET",
				url:"/rest/admin/alcohol/subcategory/"+checkArray,
				success:function(result) {
					console.log(result);
					excuteTopDel(result);
				},
				error:function(result) {
					console.log(result);
				}
			});
		}
	}
}

function excuteTopDel(data) {
	console.log(typeof(data));
	console.log(data[0].hasSub);
	
	if(data[0].hasSub == "1") {
		if (confirm("해당 topcategory는 subcategory list를 포함하고 있으며\n 삭제 진행 시 subcategory list까지 모두 삭제됩니다. 진행하시겠습니까? ")) {
			doTopDel(data);
		} else {
			alert("포함된 subcategory list 삭제여부를 먼저 확인해주세요.");
			return;
		}
	} else {
		doTopDel(data);
	}
}

function doTopDel(data) {
	console.log(data);
	console.log(data.length);
	
	let topDelArray = new Array();
	for (let key in data) {
		console.log(data[key]);
		let obj = data[key];
		topDelArray.push(obj.testedTopId);
	}
	
	$.ajax({
		type:"DELETE",
		url:"/rest/admin/alcohol/topcategory/"+topDelArray,
		contentType:"application/json",
		dataType:'json',
		success:function(result) {
			console.log(result);
			getTopcateList();
		},
		error:function(result) {
			console.log(result);
		}
	});
	
	
	
	
}

/*
 * $.ajax({
				type:"DELETE",
				url:"/rest/admin/alcohol/topcategory/"+checkArray,
				contentType:"application/json",
				dataType:'json',
				success:function(result) {
					console.log(result);
					getTopcateList();
				},
				error:function(result) {
					console.log(result);
				}
			});
 * 
 * 
 * 
 * 
 * 
 * */


// sub category insert
function subcateAdd() {
	let validResult = validateSubCateModal();
	if (validResult) {
		$.ajax({
			url: "/rest/admin/alcohol/subcategory",
			type: "POST",
			data: {
				topcategory_id: $("#topSelect").val(),
				subname: $("#new_sub_name").val()
			},
			success:function(result) {
				let subcateInsertResult = JSON.parse(result);
				handleSubcateInsertResult(subcateInsertResult);
			},
			error:function(result) {
				console.log("subcategory 추가 실패");
				console.log(result);
			}
		});
	}
}

// sub category insert result handle
function handleSubcateInsertResult(data) {
	console.log(data);
	if (data.resultCode === "1") {
		alert(data.msg);
		//리스트 갱신, 모달 close
		console.log("서브리스트 갱신위한 top_id확인 : "+data.topcategory_id);
		getSubCategoryList(data.topcategory_id);
	} else {
		alert(data.msg);
		return;
	}
}

//sub category 삭제
function subDel(e) {
	console.log("subcate 삭제 요청받음");
	var checkArray = checkCnt(e.name);
	if (checkArray.length == 0) {
		alert("삭제할 subcategory를 체크하세요.");
	} else {
		if(confirm("선택한 subcategory 목록을 삭제하시겠습니까?")) {
			$.ajax({
				type:"DELETE",
				url:"/rest/admin/alcohol/subcategory/"+checkArray,
				contentType:"application/json",
				dataType:'json',
				success:function(result) {
					console.log(result);
					getSubCategoryList(result.topcategory_id);
				},
				error:function(result) {
					console.log(result);
				}
			});
		}
	}
}

//refresh category list
function refresh(obj) {
	if (obj.name.includes('top')) {
		//topcategory list의 목록 checkbox 선택 reset
		$(".topCate_id_checkbox").prop("checked", false);
		//checkbox 선택해서 담았던 hidden value reset
		$("#hidden_topcategory_id").val(0);
		getTopcateList();
	} else {
		//subcategory list의 목록 checkbox 선택 reset
		$(".subCate_id_checkbox").prop("checked", false);
		//checkbox 선택해서 담았던 hidden value reset
		$("#hidden_subcategory_id").val(0);
	}
}