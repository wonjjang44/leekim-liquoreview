<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/admin/inc/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
<script>
$(function(){
	//searchTopcategory();
	
	//로드 후 바로 상위 카테고리 조회
	$.ajax({
		url : "/admin/alcohol/topLst",
		type : "get",
		success : function(data){
			//console.log(data);
			if(data != null || data != ""){
				$.each(data, function(index, value){
					$("#top_option_val").append("<option value = '"+value.topcategory_ID+"'>"+value.top_NM+"</option>");
				});
			}
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	
	//주류 하위 카테고리 조회
	$("#top_option_val").change(function(){
		var param = new Object();
		param.TOPCATEGORY_ID = $("#top_option_val option:selected").val();
		
		$.ajax({
			url : "/admin/alcohol/subLst",
			type : "post",
			dataType :"json", 
			contentType :"application/json;charset=UTF-8", 
			data : JSON.stringify(param),
			success : function(data){
				//console.log(data);
				var subSelTag = $("#sub_option_val");
				
				subSelTag.children().remove();
				subSelTag.prepend("<option value = ''>전체</option>");
				
				if(data != null || data != ""){
					$.each(data, function(index, value){
						subSelTag.append("<option value = '"+data[index].subcategory_ID+"'>"+data[index].sub_NM+"</option>");
					});
				}
				
			},
			error : function(xhr){
				console.log(xhr);
			}
		});
	});
	
	//주류 등록
	$("#regist_btn").click(function(){
		var top_val = $("#top_option_val option:selected").val();//top 카테고리 값
		var sub_val = $("#sub_option_val option:selected").val();//sub 카테고리 값
		var alc_name = $("#alc_name").val();// 주류 명칭
		var alc_degree = $("#alc_degree").val();//주류 도수 
		var filename = $("#image_file_name").val();//첨부파일 명
		
		//썸네일 여부가 체크되었다면 값 Y로 셋팅, 아니라면 N으로 셋팅
		if($("#thumb_yn").is(":checked") == true){
			$("#thumb_yn").attr("value",1);
		}else{
			$("#thumb_yn").attr("value",0);
		}
		
		var thumb_yn = $("#thumb_yn").val();//썸네일 여부
		
		//CKEDITOR 값을 가져올 시 자동으로 생성되는 <p>태그 제거  (trim() => 개행 방지)
		var alc_detail = CKEDITOR.instances.alc_detail.getData().replace("<p>", "").trim("</p>").replace("</p>", "");
		
		var param = new Object();
		param.TOPCATEGORY_ID = top_val;
		param.SUBCATEGORY_ID = sub_val;
		param.ALCOHOL_NM = alc_name;
		param.ALC_DEGREE = alc_degree;
		param.ALC_DETAIL = alc_detail;
		param.FILENAME = filename;
		param.THUMB_YN = thumb_yn;
		
		//유효성 검증
		regVaildChk();
		
		if(flag == true){
			if(!confirm("주류 정보를 등록합니다.")){
				return false;
			}else{
				$.ajax({
					url : "/admin/alcohol/alcoholReg",
					type :"post",
					dataType : "json",
					contentType : "application/json;charset = UTF-8",
					data : JSON.stringify(param),
					success : function(data){
						if(data > 0){
							alert("등록 완료");
							location.href = "/admin/alcohol/alcoholLst";
						}else{
							alert("이런.. 문제가 발생했습니다. 시스템 관리자에게 문의하세요.");
						}
					},
					error : function(xhr){
						console.log(xhr);
					}
				});	
			}
		}
		
	});
	
	
	//돌아가기
	$("button[name='bak_btn']").click(function(){
		if(!confirm("입력하신 정보는 저장되지 않습니다.\n돌아가시겠습니까?")){
			return false;
		}
		
		location.href="/admin/alcohol/alcoholLst";
	});
	
	//로드 시 CKEDITOR 적용
	CKEDITOR.replace( "detail" );
	
	//로드시 썸네일 div 숨기기
	$("#thumbnail_zone").css("display", "none");
	
});



//=========== UserFunction ===========
var flag = true;//validChk용 전역 플래그 값

//주류 등록 validChk
function regVaildChk(){
	var top_val = $("#top_option_val").val();//top 카테고리 값
	var sub_val = $("#sub_option_val").val();//sub 카테고리 값
	var alc_name = $("#alc_name").val();// 주류 명칭
	var alc_degree = $("#alc_degree").val();//주류 도수 
	
	//CKEDITOR 값을 가져올 시 자동으로 생성되는 <p>태그 제거  (trim() => 개행 방지)
	var alc_detail = CKEDITOR.instances.alc_detail.getData().replace("<p>", "").trim("</p>").replace("</p>", "");
	
	if(top_val == null || top_val == "" || top_val == "default"){
		alert("상위 카테고리는 필수 선택 사항입니다.");
		$("#top_option_val").focus();
		return flag = false;
	}else if(sub_val == null || sub_val == "" || sub_val == "default"){
		alert("하위 카테고리는 필수 선택 사항입니다.");
		$("#sub_option_val").focus();
		return flag = false;
	}else if(alc_name == null || alc_name == ""){
		alert("주류명은 필수 입력 사항입니다.");
		$("#alc_name").focus();
		return flag = false;
	}else if(alc_degree == null || alc_degree == ""){
		alert("도수는 필수 입력 사항입니다.");
		$("#alc_degree").focus();
		return flag = false;
	}else if(alc_detail == null || alc_detail == ""){
		alert("세부정보는 필수 입력 사항입니다.");
		$("#alc_detail").focus();
		return flag = false;
	}else{
		return flag = true;
	}
	
}
	

//이미지 등록 이벤트
function addPhoto(obj){
	var path = URL.createObjectURL(obj.files[0]); 
	//debug path = blob:http://localhost:8787/7a5bcec4-f52e-4556-81ad-11b2321231d9
	
	//업로드 할 파일명을 가져온다
	var filename = event.target.files[0].name;
	
	//닷(.) 기준으로 나누어서 arrFilename라는 배열에 담는다.
	var arrFilename = filename.split(".");
	
	//최종적으로 업로드할 파일의 확장자를 가져온다.
	var getFileExtension = arrFilename[1];
	
	//파일을 몇개 등록했는지 카운팅
	var fileCnt = obj.files.length;
	
	//이미지 파일이 아니면 등록 불가    
	if(getFileExtension != "jpg" && getFileExtension != "png" && getFileExtension != "jpeg" && getFileExtension != "gif"){
		alert("이미지 파일만 등록 가능합니다.");
		$("#image_file_name").focus();
	}else{
		//업로드할 이미지 파일 경로 표시
		var filename = $("#image_file_name").next('.custom-file-label').html(event.target.files[0].name);
		
		//이미지 파일을 등록했다면 썸네일 화면 나타내기
		if(filename != ""){
			$("#thumbnail_zone").css("display", "block");
		}
		
		$("#carousel-items").html("");
		$("#carousel-indicators").html("");
		$("#carousel-items").append("<div class='item active'><img class='img-thumbnail' src='"+path+"' style='width: 100%;height: 300px;'/></div>");
		$("#carousel-indicators").append("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
		
		//다중 이미지 첨부(한 번에 두 건 이상 등록할 시)
		for(var i=1;i<obj.files.length;i++){
			path = URL.createObjectURL(obj.files[i]);
			$("#carousel-items").append("<div class='item'><img class='img-thumbnail' src='"+path+"' style='width: 100%;height: 300px;'/></div>");
			$("#carousel-indicators").append("<li data-target='#myCarousel' data-slide-to='"+i+"'></li>");
		}
	}
	
}


</script>
</head>

<body class="">
   <div class="wrapper ">
      <%@ include file="/WEB-INF/views/admin/inc/sidebar.jsp"%>
      <div class="main-panel">
         <!-- Navbar -->
         <%@ include file="/WEB-INF/views/admin/inc/navi.jsp"%>
         <!-- End Navbar -->

		<div class="content">
            <div class="row">
               <div class="col-md-3">
                  <!-- Carousel Start -->
                  <div id="myCarousel" class="carousel slide" data-ride="carousel" style="width: 100%;">
                  
					  <!-- 등록된 파일 갯수 -->
					  <ol class="carousel-indicators" id="carousel-indicators">
					    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					  </ol>
					
					  <!-- 등록된 이미지 파일들 -->
					  <div class="carousel-inner" id="carousel-items">
					    <div class="item active">
					      <img name="photo" class="img-thumbnail" src="/resources/images/icon_camera.png" alt="" style="width: 100%;height: 300px;">
					    </div>
					  </div>
					
					  <!-- Left and right controls -->
					  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden = "true"></span>
					    <!-- <span class="sr-only">Previous</span> -->
					  </a>
					  <a class="right carousel-control" href="#myCarousel" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden = "true"></span>
					    <!-- <span class="sr-only">Next</span> -->
					  </a>
					</div>
                  	<!-- Carousel End -->
               </div>

               <div class="col-md-8">
                  <div class="card card-user">
                     <div class="card-header">
                        <h4 class="card-title">주류 세부 정보</h4>
                     </div>
                     <div class="card-body">
                     
                        <form enctype="multipart/form-data">
                        
                           <div class="row">
                              <div class="col-md-6">
                                 <div class="form-group">
                                    <label for="topcategory">주류 상위 카테고리</label> 
                                    <select class="form-control" id="top_option_val" name="topcategory_id" style="font-size: 14px;height:50px;">
                                       <option value="default">전체</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                           
                           <div class="row">
                              <div class="col-md-6">
                                 <div class="form-group">
                                    <label for="subcategory">주류 하위 카테고리</label> 
                                    <select class="form-control" id="sub_option_val" name="subcategory_id" style="font-size: 14px;height:50px;">
                                       <option value="default">전체</option>
                                    </select>
                                 </div>
                              </div>
                            </div>
                            
                           <div class="row">
                              <div class="col-md-5 pr-1">
                                 <div class="form-group">
                                    <label>주류 이름</label> 
                                    <input type="text" class="form-control" id = "alc_name" name="name" placeholder="Input Alcohol's Name" value="">
                                 </div>
                              </div>
                           </div>
                            
                           <div class="row">
                              <div class="col-md-6 pr-1">
                                 <div class="form-group">
                                    <label>도수</label> 
                                    <input type="text" id = "alc_degree" name="degree" class="form-control" placeholder="ex)15.0" value="" style = "width:70px;" maxlength="5">
                                 </div>
                              </div>
                           </div>
                           
                           <div class="row">
                              <div class="col-md-6 pr-1">
                                 	<label>Image</label></br>
                                 	<div class="custom-file" id="inputFile" style = "width : 400px;">
					                    <input name="imageFile" type="file" onchange="addPhoto(this)" multiple="multiple" class="custom-file-input" id="image_file_name">
					                    <label class="custom-file-label" for="excelFile" style="position: absolute; text-align : left; height: 30px;">파일선택</label>
					                </div>
                              </div>
                           </div>
                           </br></br>
                           
                           <!-- 썸네일 여부 체크박스 영역 Start -->
                           <div class="row" id = "thumbnail_zone">
                              <div class="col-md-6 pr-1">
                                 <div class="form-group">
					                <label id = "thumb_yn_label" for = "thumb_yn">썸네일 동의</label>&nbsp;
					                <input type = "checkbox"  id = "thumb_yn" value = "0"/><!-- 체크박스로 썸네일 여부 체크만 하도록... -->
                                 </div>
                              </div>
                           </div>
                           <!-- 썸네일 여부 체크박스 영역 End -->
                           
                           <div class="row">
                              <div class="col-md-12">
                                 <div class="form-group">
                                    <label for="detail">세부정보</label>
                                    <textarea id="alc_detail" name="detail" class="form-control textarea" placeholder="Input Details"></textarea>
                                 </div>
                              </div>
                           </div>
                           
                           <div class="row">
                              <div class="update ml-auto mr-auto">
                                 <button type="button" id = "regist_btn" name="regist_btn" class="btn btn-primary btn-round">등록하기</button>
                                 <button type="button" name="bak_btn" class="btn btn-primary btn-round">돌아가기</button>
                              </div>
                           </div>
                        </form>
                        
                     </div>
                  </div>
               </div>
            </div>
         </div>

      </div>
   </div>
   <!--   Core JS Files   -->
   <%@ include file="/WEB-INF/views/admin/inc/tail.jsp"%>
</body>

</html>