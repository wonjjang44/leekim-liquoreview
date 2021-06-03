/**
 * 회원 정보 관리 페이지 관련 스크립트
 */
jQuery(document).ready(function(){
	console.log("member.js import 성공");
	//회원 리스트 조회
	getMemberList();
});

//회원 리스트 조회
function getMemberList() {
	let prevPage = $("input[name='prevPage']").val();
	console.log("prevPage확인 : "+prevPage);
	console.log("회원 리스트 조회 클라이언트 요청");
	$.ajax({
		url:"/rest/admin/member",
		type:"GET",
		data:{
			currentPage:prevPage
		},
		success:function(data) {
			console.log("회원목록 조회 성공");
			console.log(data);
			//회원목록 조립
			handleMemberList(data);
			//페이징 refresh
			handlePaging(data.pager);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

//회원 리스트 조회결과 처리
function handleMemberList(data) {
	console.log("회원리스트 조회결과 처리 호출됨");
	console.log(data);
	console.log(typeof(data));
	console.log("글번호 사용변수 확인 num : "+data.pager.num);
	let con = $("#tbl_data_container");
	con.html("");//기존 데이터 init
	//data.length = 0;
	if(data.memList.length>0){
		let curPos = data.pager.criteria.curPos;
		console.log("curPos: "+curPos);
		let memList = data.memList;
		for(curPos in memList) {
			console.log(memList[curPos]);
			console.log(parseInt(curPos)+1);
			let obj = memList[curPos];
			con.append("<tr id='table_tr_"+obj.member_id+"'>");
			con.append("<td>"+(data.pager.num)--+"</td>");
			con.append("<td>"+obj.userid+"</td>");
			con.append("<td>"+obj.username+"</td>");
			con.append("<td>"+obj.birth+"</td>");
			con.append("<td>"+obj.email+"</td>");
			con.append("<td>"+obj.phonenum+"</td>");
			con.append("<td>"+obj.regdate+"</td>");
			con.append("<td>"+obj.auth.des+"</td>");
			if(obj.hiber_yn === false) {
				con.append("<td>N</td>");
			}else{
				con.append("<td>Y</td>");
			}
			con.append("<td>N</td>");
			con.append("<td><input type='button' value='상세' onClick='getDetail("+obj.member_id+")'/></td>");
			con.append("</tr>");
		}
	} else {
		con.append("<tr><td colspan='11' style='text-align: center;'>조회된 회원 목록이 없습니다.</td></tr>");
	}
	
}

//페이징 갱신
function handlePaging(data) {
	console.log("페이징관련데이터");
	console.log(data);
	console.log(typeof(data));
	console.log("페이징 관련데이터 파싱");
	
	console.log("totalPage: "+data.totalPage);
	console.log(typeof(data.totalPage));
	console.log("currentPage : "+data.criteria.currentPage);
	//prev next btn 조립
	pageIndicator(data);
	// 페이징 번호 버튼 조립
	pageNumBtn(data);
	//페이지 정보 유지 위한 hidden data set
	pageInfoSet(data.criteria);
	//비동기 페이지로드 이후 url변경
	pageUrlSet(data);
}

//prev next btn 조립
function pageIndicator(pager) {
	let prevArea = $(".prevPage");
	let nextArea = $(".nextPage"); 
	//prev
	if (pager.firstPage-1>0) {
		prevArea.html("");
		prevArea.append("<a class='page_href' href='/rest/admin/member?currentPage="+pager.firstPage-1+"'><strong>&laquo;</strong></a>");
	} else {
		prevArea.html("");
		prevArea.append("<a class='page_href' href=\"javascript:alert('첫 페이지 입니다.');\"><strong>&laquo;</strong></a>");
	}
	//next
	if (pager.lastPage+1<pager.totalPage) {
		nextArea.html("");
		nextArea.append("<a class='page_href' href='/rest/admin/member?currentPage="+(pager.lastPage+1)+"'><strong>&raquo;</strong></a>");
	} else {
		nextArea.html("");
		nextArea.append("<a class='page_href' href=\"javascript:alert('마지막 페이지 입니다.');\"><strong>&raquo;</strong></a>");
	}
}

//page num btn 조립
function pageNumBtn(pager) {
	let con = $(".pagination");
	// 초기화
	con.html("");
	console.log("lastpage 확인 : "+pager.lastPage);
	console.log("totalPage 확인 : "+pager.totalPage);
	if(pager.lastPage > pager.totalPage){
		for(let i=pager.firstPage;i<=pager.totalPage;i++) {
			con.append("<li class='active'>");
			//con.append("<a href='/rest/admin/member?currentPage="+i+"'>"+i+"</a>");
			con.append("<a href='javascript:callAsyncPage("+i+")'>"+i+"</a>");
			con.append("</li>");
		}
	} else {
		for(let i=pager.firstPage;i<=pager.lastPage;i++) {
			con.append("<li class='active'>");
			//con.append("<a href='/rest/admin/member?currentPage="+i+"'>"+i+"</a>");
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
}

//비동기 페이지로드 이후 url변경
function pageUrlSet(data) {
	//pager obj를 parameter로 받아옴
	//currentPage 추출 :: data.criteria.currentPage
	console.log("비동기 페이지로드 이후 url도 맞춰서 변경시키기 위한 함수호출됨");
	console.log("pageUrlSet위해 물고 온 currentPage확인 : "+data.criteria.currentPage);
	
	//pushState가 지원되는 브라우저에서만 사용하도록 분기
	if (typeof(history.pushState) == 'function') {
		//get current url
		let renewUrl = location.href;
		//url string 값이 누적되는 것을 방지하기 위해 pageUrlSet 호출시마다 초기화
		//초기화 안해주면 &currentPage= 부분이 누적으로 붙는다.
		console.log("현재 url 확인 : "+renewUrl);
		renewUrl = renewUrl.replaceAll(/(\&|\?)currentPage=([0-9]+)/ig,'');
			// 정규표현식 사용할 때 특수문자를 일반문자로 인식시키려면 앞에 \를 붙인다
			// 또는은 |으로 구분시킨다.
			// 물음표나 앰퍼센드 뒤에 currentPage=숫자 를 공백으로 대체함 
		console.log("currentPage 한 번 잘라낸 url 확인 : "+renewUrl);
		
		//data에서 currentPage 추출해서 새로 부여할 url 조립
		renewUrl += '&currentPage='+data.criteria.currentPage;
		//페이지 url 갱신
		history.pushState(null, null, renewUrl);
	} else {
		console.log("이 분기를 탔다면 구형 비표준 브라우저임. 기능 확인 필요.");
		renewUrl.setQuery('currentPage',currentPage);
	}
}

function callAsyncPage(targetPage){
	console.log("페이징버튼 눌러 callAsyncPage함수호출됨");
	console.log("callAsyncPage targetPage : "+targetPage);
	$.ajax({
		url:"/rest/admin/member?currentPage="+targetPage,
		type:"GET",
		success:function(data) {
			console.log("회원목록 조회 성공");
			console.log(data);
			//회원목록 조립
			handleMemberList(data);
			//페이징 refresh
			handlePaging(data.pager);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

//회원 1명 상세조회
/*
 * member_id와 pager.criteria 를 함께 넘겨
 * 상세조회 이전의 목록페이지 정보를 유지하도록 한다. 
 * */
function getDetail(member_id) {
	console.log("회원 상세보기 클라이언트 사이드 요청시작");
	//location.href="/admin/member/" + member_id;
	let listPageForm = $("#hiddenListPageForm");
	let currentPage = listPageForm.find("[name='currentPage']").val();
	location.href="/admin/member/"+member_id+"/page?currentPage="+currentPage;
}

//회원 검색
function search() {
	let searchType = $("#searchType :selected").val();
	let searchWord = $("input[name='searchWord']").val();
	console.log("searchType확인 : "+searchType);
	console.log("searchWord 확인 : "+searchWord);
	if (searchWord == "") {
		console.log("searchWord가 공란인채로 검색 눌러서 회원정보 전체조회요청 보내기 직전");
		//location.href="/rest/admin/member";
		getMemberList();
	} else {
		console.log("searchWord로 검색요청 보내기 직전");
		$.ajax({
			type:"get",
			url:"/rest/admin/member/search?searchType="+searchType+"&searchWord="+searchWord,
			success:function(data) {
				console.log("검색 결과를 얻어왔다.");
				//검색된 회원목록 조립
				handleMemberList(data);
				//페이징 refresh
				handlePaging(data.pager);
			}
		});
	}
}

// 회원검색 엔터 키 적용
function memberSerarchByEnter() {
	if(event.keyCode == 13) {
		search();
	}
}

//전체목록 excel 받기
function saveAsExcel() {
	console.log("excel 다운로드 버튼 눌러 호출됐다.");
	if(confirm("엑셀다운로드를 진행하시겠습니까?")) {
		location.href="/admin/excelDownload?target=member";
	}
}