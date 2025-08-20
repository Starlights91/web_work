<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/hello.jsp</title>
</head>
<body>
	<!-- jstl 코드  -->
	<h1>jstl 은 Java Standard Tag Library</h1>
	<!-- 마크업 속성: 변수명: i, 시작: 0, 끝: 4  -->
	<c:forEach var="i" begin="0" end="4">
		<p>
			Hello JSTL <strong>${i }</strong>
		</p> 
	</c:forEach>
	<hr />
	
	<!-- java 코드  -->
	<%for(int i=0; i<=4; i++){ %>
		<p>
			Hello JSTL <strong><%=i %></strong>
		</p> 	         
	<%} %>
</body>
</html>