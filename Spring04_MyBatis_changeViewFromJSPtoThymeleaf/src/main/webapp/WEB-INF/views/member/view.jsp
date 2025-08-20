<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/view.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 상세 페이지</h1>
	
		<table>
			<thead><strong>${memberView.name }</strong>님의 회원정보</thead>
			<tr>
				<th>번호</th>
				<td>${memberView.num }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${memberView.name }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${memberView.addr }</td>
			</tr>
		</table>
		
		<p>
			<!--  <a href="${pageContext.request.contextPath }/member/"></a> -->
			<a href="${pageContext.request.contextPath }/member/update-form?num=${memberView.num}">수정</a>
			<a href="${pageContext.request.contextPath }/member/delete?num=${memberView.num}">삭제</a>
			<a href="${pageContext.request.contextPath }/member/list">회원 목록으로 돌아가기</a>
		</p>
		
	</div>
</body>
</html>