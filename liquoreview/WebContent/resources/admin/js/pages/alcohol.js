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
	let prevPage = $("input[name='prevPage']").val();
	console.log("prevPage 확인 : "+prevPage);
	console.log("알콜 리스트 조회 클라이언트 요청");
	$.ajax({
		url:"/rest/admin/alcohol",
		type:"GET",
		data: {
			currentPage:prevPage
		},
		success:function(data) {
			console.log(data);
			renderAlcList(data);
			//페이징 refresh
			handlePaging(data.pager);
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
				//페이징 refresh
				handlePaging(data.pager);
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
	if (data.alcList.length > 0) {
		let curPos = data.pager.criteria.curPos;
		console.log("curPos: "+curPos);
		let alcoholList = data.alcList;
		//alc list 조립
		for (curPos in alcoholList) {
			console.log(alcoholList[curPos]);
			let obj = alcoholList[curPos];
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

//페이징 갱신
function handlePaging(data) {
	console.log("페이징관련데이터");
	console.log(data);
	console.log(typeof(data));
	console.log("페이징 관련데이터 파싱");
	
	//prev next btn 조립
	pageIndicator(data);
	//페이징 번호 버튼 조립
	pageNumBtn(data);
	//페이지 정보 유지 위한 hidden data set
	pageInfoSet(data.criteria);
	//비동기 페이지로드 이후 url 변경
	pageUrlSet(data);
}

//prev next btn 조립
function pageIndicator(pager) {
	let prevArea = $(".prevPage");
	let nextArea = $(".nextPage");
	//prev
	if (pager.firstPage-1 > 0) {
		prevArea.html("");
		prevArea.append("<a class='page_href=' href='/rest/admin/alcohol?currentPage="+pager.firstPage-1+"'><strong>&laquo;</strong></a>");
	} else {
		prevArea.html("");
		prevArea.append("<a class='page_href' href=\"javascript:alert('첫 페이지 입니다.');\"><strong>&laquo;</strong></a>")
	}
	//next
	if (pager.lastPage+1 < pager.totalPage) {
		nextArea.html("");
		nextArea.append("<a class='page_href' href='/rest/admin/alcohol?currentPage="+(pager.lastPage+1)+"'><strong>&raquo;</strong></a>");
	} else {
		nextArea.html("");
		nextArea.append("<a class='page_href' href=\"javascript:alert('마지막 페이지 입니다.');\"><strong>&raquo;</strong></a>");
	}
}

//page num btn 조립
function pageNumBtn(pager) {
	let con = $(".pagination");
	//초기화
	con.html("");
	
	if (pager.lastPage > pager.totalPage) {
		for (let i=pager.firstPage; i <=pager.totalPage; i++) {
			con.append("<li class='active'>");
			con.append("<a href='javascript:callAsyncPage("+i+")'>"+i+"</a>");
			con.append("</li>");
		}
	} else {
		for (let i=pager.firstPage; i <=pager.lastPage; i++) {
			con.append("<li class='active'>");
			con.append("<a href='javascript:callAsyncPage("+i+")'>"+i+"</a>");
			con.append("</li>");
		}
	}
}

//페이지 정보 유지 위한 hidden data set
function pageInfoSet(criteria) {
	let listPageForm = $("#hiddenListPageForm");
	
	$("input[name='currentPage']").val(criteria.currentPage);
	$("input[name='pageSize']").val(criteria.pageSize);
	$("input[name='curPos']").val(criteria.curPos);
	console.log("currentPage 확인 : "+$("input[name='currentPage']").val());
}

//비동기 페이지로드 이후 url 변경
function pageUrlSet(data) {
	console.log("pageUrlSet 위해 물고 온 currentPage 확인 : "+data.criteria.currentPage);
	
	//pushState가 지원되는 브라우저에서만 사용하도록 분기
	if (typeof(history.pushState) == 'function') {
		//get current url
		let renewUrl = location.href;
		//url string 값의 누적을 방지하기 위해 pageUrlSet 호출시마다 초기화한다
		//초기화 안해주면 &currentPage= 부분이 누적으로 붙는다.
		console.log("현재 url 확인 : "+renewUrl);
		renewUrl = renewUrl.replaceAll(/(\&|\?)currentPage=([0-9]+)/ig,'');
			// 정규표현식 사용할 때 특수문자를 일반문자로 인식시키려면 앞에 \를 붙인다
			// 또는은 |으로 구분시킨다.
			// 물음표나 앰퍼센드 뒤에 currentPage=숫자 를 공백으로 대체함 
		console.log("currentPage 한 번 잘라낸 url 확인 : "+renewUrl);
		
		//data에서 currentPage 추출해서 새로 부여할 url 조립한다
		renewUrl += '&currentPage='+data.criteria.currentPage;
		//page url 갱신
		history.pushState(null, null, renewUrl);
	} else {
		console.log("이 분기를 탔다면 구형 비표준 브라우저임. 기능 확인 필요.");
		renewUrl.setQuery('currentPage',currentPage);
	}
}

function callAsyncPage(targetPage) {
	console.log("페이징버튼 눌러 callAsyncPage함수호출됨");
	console.log("callAsyncPage targetPage : "+targetPage);
	$.ajax({
		url:"/rest/admin/alcohol?currentPage="+targetPage,
		type:"GET",
		success:function(data) {
			console.log("주류목록 조회 성공");
			console.log(data);
			//주류목록 조립
			renderAlcList(data);
			//페이징 refresh
			handlePaging(data.pager);
		},
		error:function(data) {
			console.log(data);
		}
	});
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

// alcohol detail 
function getAlcDetail(alcohol_id) {
	console.log("알콜 상세 정보 얻어올 alc_id 확인 : "+alcohol_id);
	let listPageForm = $("#hiddenListPageForm");
	let currentPage = listPageForm.find("[name='currentPage']").val();
	console.log("detail 조회 전 currentPage 확인 : "+currentPage);
	location.href="/admin/alcohol/"+alcohol_id+"/page?currentPage="+currentPage;
}