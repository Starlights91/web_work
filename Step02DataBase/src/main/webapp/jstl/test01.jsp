<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	JSTL 사용 설정하기
	uri="import 할 경로"
	prefix="접두어"
--%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!-- 여기 상단은 서블릿에 있다고 가정. 하단 html은 jsp 페이지로 응답을 위임을 했다라고 가정 (forward)  -->
<%
	//여긴 로직만 : Business Logic (Servlet)
	//테스트를 위해 sample 데이터를 request scope 에 담는다. 여기 request.setAttribute 는 하단에서  getAttribute 로 불러낼 수 있음
	List<String> names=new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
	// "list"라는 키값으로 request scope 에 ArrayList 객체에 담아두기 (특정 키값, 데이터를 담음) (응답에 필요한 데이터는 request/session에 담아놓고, 하단에서 응)
	request.setAttribute("list", names);
	
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test01.jsp</title>
</head>
<body>
<%
	//여긴 뷰만 : UI 를 응답 view page (jsp thymeleaf))
	//request scope 에 "list" 라는 키값으로 저장된 친구 목록 얻어내기
	List<String> list = (List<String>)request.getAttribute("list");
%>
	<h1>친구목록</h1>
	<ul><!-- list에 잇는 것 하나씩 추출 해서 반복문 -->
		<%for(String tmp:list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	
	<h1> EL 과 JSTL 을 활용해서 위와 동일한 동작 </h1>
	<ul>
		<!-- requestScope. == 위의 request.geAttribute 에 넣어둔 데이터 값을 불러오는 것. (requestScope. 은 생략 가능) -->
		<c:forEach var="tmp" items="${requestScope.list }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 인덱스 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스 : ${status.index }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>찬구 목록 순서 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>순서 : ${status.count }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 첫번째 순서인지 여부</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>
				${tmp } 
				<strong>첫번째 : ${status.first }</strong>
				<c:if test="${status.first }"> <!-- 조건문 출력할 때 -->
					제일 잘생긴 친구
				</c:if>
			</li>
			
		</c:forEach>
	</ul>
	
	<h1>찬구 목록 마지막 번째 순서인지 여부</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>
				${tmp } 
				<strong>마지막 번째 : ${status.last }</strong>
				<c:if test="${status.last }">
					제일 마지막 친구
				</c:if>
			</li>
		</c:forEach>
	</ul>
</body>
</html>