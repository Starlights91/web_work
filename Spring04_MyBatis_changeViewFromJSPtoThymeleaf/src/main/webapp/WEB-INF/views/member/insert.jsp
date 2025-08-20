<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/insert.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 추가 결과</h1>
		<c:choose>
			<c:when test="${isSuccess }">
				<p>회원 추가 등록 완료.</p>
				<c:if test="${not empty num }">
					<p>발급된 회원 번호: <strong>${num }</strong></p>
				</c:if>
			</c:when>
			<c:otherwise>
				<p>회원 추가 등록 실패.</p>
			</c:otherwise>
		</c:choose>
		
		<p>
			<a href="${pageContext.request.contextPath }/member/list">회원 목록으로 돌아가기</a>
			<a href="${pageContext.request.contextPath }/member/insert-form">회원추가 등록 하기</a>
		</p>

	</div>
</body>
</html>