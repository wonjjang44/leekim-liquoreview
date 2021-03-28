<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(function(){
	//searchTopcategory();
});

function searchTopcategory(){
	$.ajax({
		url:"/rest/alcohols/topcategory",
		type:"get",
		success:function(result){
			createTopcategory(result);
		}
	});
}

function createTopcategory(obj){
	
	for(var i=0;i<obj.length;i++){
		$("ul[id='topcategory']").append("<li><a href='/alcohols/review/top?topcategory_id="+obj[i].topcategory_id+"' style='font-size:15px;'>"+obj[i].name+"</a></li>");
	}
}

function logout(){
	if(confirm("로그아웃 하시겠습니까?")){		
		location.href="/member/logout";
	}
}

</script>
<nav class="fh5co-nav" role="navigation">
	<div class="container-wrap">
		<div class="top-menu">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo">
						<a href="/" style="font-size:25px;">드렁큰</a>
					</div>
				</div>
				<div class="col-xs-15 text-right menu-1">
					<ul>
						<!-- class="active" 되어 있는 메뉴가 활성화 됨 -->
						<li <%if(request.getRequestURI().equals("/WEB-INF/views/index.jsp")){%>class="active"<%}%>><a href="/" style="font-size:15px;">메인</a></li>
						<li class="has-dropdown 
							<%if(
									request.getRequestURI().equals("/WEB-INF/review/review.jsp")
									||request.getRequestURI().equals("/WEB-INF/review/review-detail.jsp")
									||request.getRequestURI().equals("/WEB-INF/review/review-category.jsp")
								){%>
								active
							<%}%>"
						>
							<a href="#" style="font-size:15px;">알콜 리뷰</a>
							<ul class="dropdown" id="topcategory">
								<li><a href="/alcohols" style="font-size:15px;">전체</a></li>
							</ul>
						</li>
						
						<%-- <li <%if(request.getRequestURI().equals("/info.jsp")){%>class="active"<%}%>><a href="/info.jsp">뉴스 & 정보</a></li> --%>
						<li <%if(request.getRequestURI().equals("/WEB-INF/board/board.jsp")){%>class="active"<%}%>>
							<a href="/boards" style="font-size:15px;">게시판</a>
						</li>
						<li <%if(request.getRequestURI().equals("/WEB-INF/aboutUs/aboutUs.jsp")){%>class="active"<%}%>>
								<a href="/aboutUs" style="font-size:15px;">운영진</a>
						</li>
						
						<c:if test="${loginSession.getAUTH_ID() == 1 }">
							<li><a href="/admin/index" style="font-size:15px;">ADMIN</a></li>
						</c:if>
						
						<%if(!flag){ %>
						<li class="has-drropdown 
							<%if(
									request.getRequestURI().equals("/WEB-INF/member/member-login.jsp")
									||request.getRequestURI().equals("/WEB-INF/member/member-regist.jsp")
									||request.getRequestURI().equals("/WEB-INF/member/member-welcome.jsp")
								){%>
								active
							<%}%>">
							<c:choose>
								<c:when test="${loginSession == null}">
									<a href="/member/login" style="font-size:15px;">로그인</a>
									<a href="/member/regist" style="font-size:15px;">회원가입</a>
								</c:when>
								
								<c:when test="${loginSession != null }">
									<a href="#" style="font-size:15px;">${ loginSession.getUSERNAME()}님 환영합니다</a>
									<a href="/member/logout" style="font-size:15px;">로그아웃</a>
								</c:when>
							</c:choose>
						</li>
						<%}else { %>
						<li class="has-dropdown" ><a href="#" style="font-size:15px;">환영합니다</a>
							<ul class="dropdown">
								<li><a href="/member/member-mypage" style="font-size:15px;">MyPage</a></li>
								<li onClick="logout()"><a href="#" style="font-size:15px;">로그아웃</a></li>
							</ul>
						</li>
						<%} %>
					</ul>
				</div>
			</div>

			<!-- <div class="row">
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<li class="has-dropdown" ><a href="">탑1</a>
							<ul class="dropdown">
								<li><a href="/member/member-login.jsp">서브1</a></li>
								<li><a href="/member/member-regist.jsp">서브2</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div> -->
			
		</div>
	</div>
</nav>