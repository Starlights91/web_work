<%@page import="test.dto.MemberDto"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// inserform.jsp 에서 form 에 button submit 버튼을 눌러서 여기 페이지로 이동하는 것!
	//1. 폼 전송되는 추가할 회원의 이름과 주소를 추출한다.
	String name = request.getParameter("name");
	String addr= request.getParameter("addr");
	//2. DB 에 저장하기 위해 name, addr 을 MemberDto 객체에 담는다.
	MemberDto dto=new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	//3. DB 에 저장한다.
	MemberDao dao=new MemberDao();
	boolean isSuccess=dao.insert(dto);
	//여기까지가 서버가 수행할 일
	//4. 클라이언트에게 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
    <!-- 복사한 bootstrap css 로딩시키기 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<!-- 복사한 bootstrap icons css 로딩시키기 (vector 이미지 글꼴,class 전체를 한번에 로딩시키기 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container">
		<%if(isSuccess){%>
				<!--  class="alert alert-success" 초록색 & mt-5 == margin-top 40px -->
				<p class="alert alert-success mt-5">
					<!-- check-circle-검정 바탕 아이콘 불러오기 -->
					<i class="bi bi-check-circle-fill"></i>
					<strong><%=name %></strong> 님의 정보를 성공적으로 저장했습니다.
					<a class="alert-link" href="${pageContext.request.contextPath }/member/list.jsp">확인</a>
				</p>
			<%}else{%>
				<!--  class="alert alert-danger" 빨간색 & mt-5  == margin-top 40px -->
				<p class="alert alert-danger mt-5">
					<!-- x표시-circle-검정 바탕 아이콘 불러오기 -->
					<i class="bi bi-x-circle-fill"></i>
					회원 정보 저장 실패!
					<!--  위의 배경 빨간색에 어울리는 것으로 나오게  -->
					<a class="alert-link" href="${pageContext.request.contextPath }/member/insertform.jsp">다시입력하기</a>
					<!--  다시입력하기 누르면: insertform.jsp 페이지로 돌아가는 이동 ui 추가한 것 -->
				</p>
			<%}%>
			<!--  확인누르면: 해당 목록 으로 가는이동 ui 2개도 각각 추가한 것 -->
	</div>
</body>
</html>