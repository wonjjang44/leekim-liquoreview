/**
 * 알콜 주류 컨텐츠 관리 페이지 관련 스크립트
 */
jQuery(document).ready(function(){
	console.log("alcohol.js import succeed");
	alcoholManageInit();
});

function alcoholManageInit() {
	//topcategory 조회
	getTopcategory();
	//sub
	
	//alcohol list 조회
	getAlcoholList();
	
	//alcohol modal hidden
	$("#alcoholModal").modal('hide');
} 

//alcohol List 조회
function getAlcoholList() {
	$.ajax({
		url:"/rest/admin/alcohol",
		type:"GET",
		success:function(data) {
			console.log(data);
			renderAlcList(data);
		},
		error: function(data) {
			console.log(data);
		}
	});
}

//topcategory 목록 얻어오기
function getTopcategory() {
	console.log("topcategory 조회 호출");
	$.ajax({
		url:"/rest/admin/alcohol/alltopcategory",
		type:"GET",
		success:function(result) {
			console.log(result);
			createTopOption(result);
		}
	});
}
//top select option 조립
function createTopOption(data) {
	console.log(typeof(data));
	console.log("topcategory select option 조립");
	console.log(data);
	
	$("#topSelect").html("");
	$("#topSelect").append("<option value='0' selected>상위분류선택</option>");
	for(let key in data ){
		console.log(data[key]);
		let obj = data[key];
		$("#topSelect").append("<option value=\""+obj.topcategory_id+"\">"+obj.topname+"</option>")
	}
}

//get subcategory list by selected topcategory_id
function getSubList(topcategory_id) {
	console.log("선택된 topid 확인 : "+topcategory_id);
	if (topcategory_id > 0) {
		$.ajax({
			url:"/rest/admin/alcohol/subcategory?topcategory_id="+topcategory_id,
			type:"GET",
			success:function(data) {
				console.log(data);
				createSubOption(data.sortedSubcateList);
			},
			error:function(data) {
				console.log(data);
			}
		});
	}
}

//subcategory list 조회결과 handle
function createSubOption(data) {
	console.log(data);
	$("#subSelect").html("");
	$("#subSelect").append("<option value='0' selected>하위분류선택</option>");
	for(let key in data ){
		console.log(data[key]);
		let obj = data[key];
		$("#subSelect").append("<option value=\""+obj.subcategory_id+"\">"+obj.subname+"</option>")
	}
}

//getSortedAlcList
function getSortedAlcList(subcategory_id) {
	console.log("subid 확인 : "+subcategory_id);
	if(subcategory_id > 0) {
		console.log("subid로 alcohol list 조회");
		$.ajax({
			url:"/rest/admin/alcohol/"+subcategory_id,
			type:"GET",
			success:function(data) {
				console.log(data);
				renderAlcList(data);
			},
			error:function(data) {
				console.log(data);
			}
		});
	}
}

// render selection list
function renderAlcList(data) {
	console.log(data);
	let con = $("#tbl_data_container");
	con.html("");
	if (data != null && data.length != 0) {
		//alc list 조립
		for (let key in data) {
			console.log(data[key]);
			let obj = data[key];
			con.append("<tr id='alc_table_tr_"+obj.alcohol_id+"'>");
			con.append("<td><input class='alcohol_id_checkbox' type='checkbox' name='alcohol_id' value='"+obj.alcohol_id+"' onChange='alcCheckHandle("+obj.alcohol_id+")'/></td>");
			con.append("<td>"+obj.alcohol_id+"</td>");
			con.append("<td>"+obj.filename+"</td>");
			con.append("<td>"+obj.subcategory.topcategory.topname+"</td>");
			con.append("<td>"+obj.subcategory.subname+"</td>");
			con.append("<td>"+obj.name+"</td>");
			con.append("<td>"+obj.degree+"</td>");
			con.append("<td>"+obj.regdate+"</td>");
			con.append("<td>"+obj.last_modi_ymd+"</td>");
			con.append("<td><button onClick='getAlcDetail("+obj.alcohol_id+")'>상세</button></td>");
			con.append("</tr>");
		}
	} else {
		con.append("<tr><td colspan='10' align='center'>조회된 alcohol list가 없습니다.</td></tr>");
	}
}

//alcohol 단건 등록을 위한 모달 호출
function alcAddPop() {
	console.log("alcAddPop 호출됨");
	
	//modal-dialog 영역 채우기 전 초기화
	$(".modal-dialog").empty();
	
	//add page 호출
	$.ajax({
		url:"/rest/admin/alcohol/modal",
		type:"GET",
		dataType:"html",
		contentType:"application/html;charset=UTF8",
		success:function(data) {
			$(".modal-dialog").html(data);
			$("#alcoholModal").modal('toggle');
		},
		error:function(data) {
			console.log("에러발생");
			console.log(data);
			alert("모달 호출 중 에러가 발생했습니다.");
		}
	});
}

//모달 subcategory list 얻어서 채우기
function getModalSubList(topcategory_id) {
	$.ajax({
		url:"/rest/admin/alcohol/subcategory?topcategory_id="+topcategory_id,
		type:"GET",
		success:function(data) {
			createModalSubOption(data.sortedSubcateList);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

function createModalSubOption(data) {
	$("#modalSubSelect").html("");
	$("#modalSubSelect").append("<option value='0' selected>하위분류선택</option>");
	for(let key in data ){
		console.log(data[key]);
		let obj = data[key];
		$("#modalSubSelect").append("<option value=\""+obj.subcategory_id+"\">"+obj.subname+"</option>")
	}
}

function reset() {
	console.log("alcohol 추가 modal 내 초기화 버튼 클릭");
	$("#alc_add_form")[0].reset();
}

//alcohol list upload by excel file
function excelUpload() {
	//excelUpload 버튼이 클릭되면 파일 선택 창을 띄운다
	$("#ajaxExcelFile").click();
}

//파일 선택 창의 onChange event handler
function ajaxFileChange() {
	//파일이 선택되면 업로드를 진행
	ajaxFileSubmit();
}

function ajaxFileSubmit() {
	let form = $("#ajaxExcelForm")[0];
	let formData = new FormData(form);
	$($("#ajaxExcelFile")[0].files).each(function(index,file){
		formData.append("alcExcelFile",file);
	});
	
	console.log("formData 확인 : ");
	console.log(formData);

	//등록요청
	$.ajax({
		url:"/rest/admin/alcohol/insertByExcel",
		type:"POST",
		processData: false,
		contentType: false,
		data: formData,
		success:function(result) {
			console.log(result);
			alert("success");
			//등록 후 form reset
			$("#ajaxExcelForm").trigger("reset");
			//list 갱신
			getAlcoholList();
		},
		error:function(result) {
			console.log(result);
		}
	});
}