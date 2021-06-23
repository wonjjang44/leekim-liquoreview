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
} 

//topcategory 목록 얻어오기
function getTopcategory() {
	$.ajax({
		url:"/rest/admin/alcohol/alltopcategory/",
		type:"GET",
		success:function(result) {
			createTopOption(JSON.parse(result));
		}
	});
}