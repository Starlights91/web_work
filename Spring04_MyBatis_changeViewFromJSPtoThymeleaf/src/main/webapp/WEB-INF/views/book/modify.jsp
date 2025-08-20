<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/book/modify.jsp</title>
</head>
<body>
	<div class="container">
		<h1>도서 정보 수정 폼</h1>
		<form action="${pageContext.request.contextPath }/book/update" method="post">
		<input type="hidden" name="num" id="num" value="${dto.num }" />
		
			<div>
				<label for="title">책제목</label>
				<input type="text" name="title" id="title" value="${dto.title }" />
			</div>
			<div>
				<label for="author">저자</label>
				<input type="text" name="author" id="author" value="${dto.author }" />
			</div>
			<div>
				<label for="publisher">출판사</label>
				<input type="text" name="publisher" id="publisher" value="${dto.publisher }" />
			</div>
			<button type="submit">수정 확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>