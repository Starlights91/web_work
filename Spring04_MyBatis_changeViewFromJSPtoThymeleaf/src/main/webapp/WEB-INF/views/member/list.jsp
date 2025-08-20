<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/list.jsp</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/member/new-form">회원 추가</a>
		<h1>회원 목록 입니다</h1>
		<a href="${pageContext.request.contextPath }/member/insert-form">추가</a> <!-- 연습: 추가 -->
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정1</th>
					<th>삭제1</th>
					
					<!-- 추가 -->
					<th>회원상세보기</th> <!-- 연습 -->
					<th>수정</th> <!-- 연습 -->
					<th>삭제</th> <!-- 연습 -->
				</tr>
			</thead>
			<tbody>
				<%--
					"memberList" 라는 키값으로 담긴 데이터는 List<MemberDto> 이다
					따라서 tmp 는 MemberDto type 이다
					tmp.getNum() 하면 번호를 얻어낼수 있는데 EL 에서는 tmp.num 해도 자동으로 getter 메소드를
					호출해준다.  
				 --%>
				<c:forEach var="tmp" items="${memberList }"> <!-- List<MemberDto -->
					<tr>
						<td>${tmp.getNum() }</td>
						<td>${tmp.name }</td>
						<td>${tmp.addr }</td>

						<td>
							<a href="edit?num=${tmp.num }">수정1</a> <!-- /member/delete1 요청이 되는 것 -->
						</td>
						<td>
							<a href="delete1?num=${tmp.num }">삭제1</a> <!-- /member/delete1 요청이 되는 것 -->
						</td>
						
						<!-- 아래 3개는 연습 -->
						<td><a href="${pageContext.request.contextPath }/member/view?num=${tmp.num}">보기</a></td>
						<td><a href="${pageContext.request.contextPath }/member/update-form?num=${tmp.num}">수정</a></td>
						<td><a href="${pageContext.request.contextPath }/member/delete?num=${tmp.num}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>








