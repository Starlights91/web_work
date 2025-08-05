<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// request 객체에 특정 key 값으로 담긴 정보 추출하기(필요에 따라서 list 나 dto 도 담아서 추출 가능.) 
	// request.getAttribute 는 1회성 데이터, login.jsp 에서 session.setAttribute 데이터를 계속 살아있게 할 때 사용.
	String orgFileName=(String)request.getAttribute("orgFileName");
	String saveFileName=(String)request.getAttribute("saveFileName");
	long fileSize=(long)request.getAttribute("fileSize");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/윤제.jsp</title>
</head>
<body>
	<div class="container">
		<p>윤제가 응답합니다</p>
		<p>원본 파일명 : <%=orgFileName %></p>
		<p>저장된 파일명 : <%=saveFileName %></p>
		<p>파일의 크기 : <%=fileSize %></p>
		
		<%-- el 을 이용하면 casting 필요없이 바로 추출할수도 있다. req 또는 session 영역에 담긴 내용이 필요하면 EL 즉, ${ } 로 추출 가능 --%>
		<p>원본 파일명 : ${requestScope.orgFileName }</p>
		<p>저장된 파일명 : ${requestScope.saveFileName }</p>
		<%-- requestScope. 은 생략 가능! --%>
		<p>파일의 크기 : ${fileSize }</p> 
		
		<%-- 다운로드 자체가 응답인거라, 아래의 페이지로 이동하진 않는다. --%>
		<a href="${pageContext.request.contextPath }/test/download?orgFileName=${orgFileName}&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a>
	</div>
</body>
</html>











