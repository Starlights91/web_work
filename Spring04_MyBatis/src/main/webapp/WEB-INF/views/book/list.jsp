<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/book/list.jsp</title>
</head>
<body>
	<div class="container">
		<!-- <a href="">도서추가</a> -->
		<h1>도서 목록 페이지</h1>
		<a href="${pageContext.request.contextPath }/book/insert-form">도서 추가</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>책제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${bookList }">
					<tr>
						<td>${tmp.getNum() }</td>
						<td>${tmp.title }</td>
						<td>${tmp.author }</td>
						<td>${tmp.publisher }</td>
						<td>
							<a href="modify?num=${tmp.num}">수정</a>
						</td>
						<td>
							<a href="delete?num=${tmp.num}">삭제</a>
						</td>
					</tr>
				
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>