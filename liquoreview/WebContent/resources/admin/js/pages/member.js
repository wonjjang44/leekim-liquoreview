/**
 * 회원 정보 관리 페이지 관련 스크립트
 */

jQuery(document).ready(function(){
	console.log("member.js import 성공");
	getMemberList();
});

//회원 리스트 조회
function getMemberList() {
	console.log("회원 리스트 조회 클라이언트 요청");
	$.ajax({
		url:"/rest/admin/member",
		type:"GET",
		success:function(data) {
			console.log("회원목록 조회 성공");
			console.log(data);
			handleMemberList(data);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

//회원 리스트 조회결과 처리
function handleMemberList(data) {
	console.log("회원리스트 조회결과 처리 호출됨");
	console.log(typeof(data));
	let con = $("#tbl_data_container");
	con.html("");//기존 데이터 init
	//data.length = 0;
	if(data.length>0){
		for(let key in data) {
			console.log(data[key]);
			console.log(parseInt(key)+1);
			let obj = data[key];
			con.append("<tr id='table_tr_"+obj.member_id+"'>");
			con.append("<td>"+(parseInt(key)+1)+"</td>");
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

//회원 1명 상세조회

function getDetail(member_id) {
	console.log("회원 상세보기 클라이언트 사이드 요청시작");

	$.ajax({
		url:"/rest/admin/member/"+member_id,
		type:"GET",
		success:function(data) {
			console.log("회원상세 조회 성공");
			console.log(data);
		},
		error:function(data) {
			console.log(data);
		}
	});
}
/*
function search() {
	var searchMode = $("select[name='searchMode']").val();
	var searchAuth = $("select[name='searchAuth']").val();
	var searchWord = $("select[name='searchWord']").val();

	$("input[name='search_Mode']").val(searchMode);
	$("input[name='search_Auth']").val(searchAuth);
	$("input[name='search_Word']").val(searchWord);
	//if(searchWord==""){alert("검색어를 입력해주세요.");return;}
	$("form").attr({
		action : "/admin/member/search",
		method : "post"
	});
	$("form").submit();
}
*/