<%@ page contentType="text/html; charset=UTF-8"%>
<div class="sidebar" data-color="white" data-active-color="danger">
	<!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
    -->
	<div class="logo">
		<a href="/" class="simple-text logo-mini">
			<div class="logo-image-small">
				<img src="/resources/admin/images/faces/logo_icon/logo-small.png">
			</div>
		</a> 
		<a href="/admin/index" class="simple-text logo-normal">
			LiquoReview 
			<!-- 
			<div class="logo-image-big">
            	<img src="../assets/img/logo-big.png">
            </div> 
            -->
		</a>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">

			<li <%if (request.getRequestURI().equals("/WEB-INF/views/admin/main/index.jsp")) {%>
					class="active " 
				 <%}%>
			>
				<a href="/admin/index">
					<i class="nc-icon nc-bank"></i>
					<p>관리자 메인</p>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/admin/dashboard/manager.jsp")) {%>
					class="active "
				<%}%>
			>
				<a href="/admin/auth">
					<i class="nc-icon nc-diamond"></i>
					<span>권한 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/WEB-INF/admin/alcohol/alcohol-table.jsp")
					|| request.getRequestURI().equals("/WEB-INF/admin/alcohol/alcohol-regist.jsp")
					|| request.getRequestURI().equals("/WEB-INF/admin/alcohol/alcohol-detail.jsp")) {%>
					class="active "
				<%}%>
			>
				<a href="/admin/alcohol/alcoholLst">
					<i class="nc-icon nc-spaceship"></i>
					<span>주류 정보 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/WEB-INF/admin/category/category-top-table.jsp")) {%>
				class="active " <%}%>
			>
				<a href="/admin/category/topCateLstIqr">
					<i	class="nc-icon nc-bell-55"></i>
					<span>탑 카테고리 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/WEB-INF/admin/category/category-sub-table.jsp")) {%>
					class="active " 
				<%}%>
			>
				<a href="/admin/category/subCateLstIqr">
					<i	class="nc-icon nc-satisfied"></i>
					<span>서브 카테고리 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/WEB-INF/admin/member/member-table.jsp")) {%>
					class="active "
				<%}%>
			>
				<a href="/admin/member">
					<i class="nc-icon nc-single-02"></i>
					<span>회원 정보 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/WEB-INF/admin/review/review-table.jsp")) {%>
					class="active "
				<%}%>
			>
				<a href="/admin/reviews">
					<i class="nc-icon nc-caps-small"></i>
					<span>리뷰 정보 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/admin/board/board-table.jsp")) {%>
					class="active " 
				<%}%>
			>
				<a href="/admin/dashboard/board-table.jsp">
					<i	class="nc-icon nc-tile-56"></i>
					<span>게시글 관리</span>
				</a>
			</li>
			<li
				<%if (request.getRequestURI().equals("/admin/board/board-table.jsp")) {%>
					class="active " 
				<%}%>
			>
				<a href="/">
					<i	class="nc-icon nc-send"></i>
					<span>클라이언트 페이지</span>
				</a>
			</li>
		</ul>
	</div>
</div>