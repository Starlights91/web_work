<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/book/delete.jsp</title>
</head>
<body>
	<%-- 
		파라미터로 전달되었던 값을 필요하다면 EL 로 추출해서 응답할때 활용할수도 있다.
		${param.파라미터명}
	 --%>
	<script>
		alert("${param.num} 번 회원의 정보를 삭제 했습니다.");
		location.href="${pageContext.request.contextPath }/book/list";
	</script>
</body>
</html>