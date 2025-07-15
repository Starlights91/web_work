<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /WEB-INF/include/navbar.jsp --%>   
<%
	//navbar.jsp 페이지가 어떤 페이지에 include 되었는지 파라미터 읽어오기
	String thisPage=request.getParameter("thisPage"); //여기에 전달되는 문자열은 index, member, book 이 된다.
%>

 
	<!-- (필수기능!)반응형 페이지: Navigation-bar 최상단이 화면의 폭에 따라서, 레이아웃이 변경되도록 하는 것. 따라서, 폭이 줄어들면 레이아웃이 메뉴바(셋삼표시) 안으로 숨겨지기도 함) -->
	<!-- bg=background bg-primary: 파랑, success: 초록, warning:노랑, danger:빨강 -->
	<!-- fixed-top : navibar 상단에 고정 & head 안에 <style> body{ padding-top: 60px } 넣기  -->
	<!-- data-bs-theme="dark" 해당 속성 추가/제거: 시인성 좋도록 글씨 흰색/검정색 적용. 따라서, 배경색이 어두울 경우, 시인성이 좋아지도록 하얀 글씨로 되게 입력 <-> 반대로 밝은색 계열은 data-bs-theme="dark"를 지우면 됨 -->
	<nav class="navbar navbar-expand-md bg-primary-subtle" data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath }/">Acorn</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link <%= thisPage.equals("member") ? "active":"" %>" href="${pageContext.request.contextPath }/member/list.jsp">Member</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <%= thisPage.equals("book") ? "active":"" %>" href="${pageContext.request.contextPath }/book/list.jsp">Book</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
