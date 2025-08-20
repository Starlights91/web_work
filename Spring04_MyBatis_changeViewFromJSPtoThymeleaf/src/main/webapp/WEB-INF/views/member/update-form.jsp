<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/update-form.jsp</title>
</head>
<body>
	<div class="container">
	<h1>회원정보 수정</h1>
		<form action="update.jsp" method="post">
			<label for="num">회원번호</label>
			<input type="text" name="num" id="num" value=${getByNum.num } readonly/>
			<br />
			<label for="name">이름</label>
			<input type="text" name="name" id="name" value=${getByNum.name } />
			<br />		
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" value=${getByNum.addr } />
			<br />
			
			<button type="submit">수정 저장</button>
			<a href="${pageContext.request.contextPath}/member/view?num=${getByNum.num}">취소</a>
			
		</form>
	</div>

</body>
</html>