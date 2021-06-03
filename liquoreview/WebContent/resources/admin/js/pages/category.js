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
		url:"/rest/admin/topCate",
		type:"GET",
		success:function(data) {
			console.log(data);
			handleTopcateList(data);
		},
		error:function(data) {
			console.log(data);
		}
	});
}

// 탑 카테고리 리스트 조회결과 처리
function handleTopcateList(data) {
	console.log(typeof(data));
	let con = $("#tbl_topcate_data_container");
	con.html("");//기존 데이터 init
	if (data.topcateList != null) {
		for (let key in data) {
			console.log(data[key]);
			let obj = data[key];
			con.append("<tr id='top_table_tr_"+obj.topcategory_id+">");
			con.append("<td><input class='topCate_id_checkbox' type='checkbox' name='topCate_id' value='"+obj.topcategory_id+"'/></td>");
			con.append("<td>"+obj.name+"</td>");
			con.append("<td>"+obj.regdate+"</td>");
			con.append("<td>"+obj.last_modi_ymd+"</td>");
			con.append("<td><button type='button' onClick='getSubCategory("+obj.topcategory_id+")'>Sub보기</button></td>");
			con.append("</tr>");
		}
	} else {
		con.append("<tr><td colspan='5' style='text-align:center; line-height: 1.2rem; vertical-align:middle;'>저장된 top category data가 없습니다.</td></tr>");
	}
	//modal close
	$("#cateModal").modal('hide');
}

