<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션에 저장된 값을 삭제하면 로그아웃이다.
	// "userName" 이라는 키값으로 저장된 값을 삭제
	session.removeAttribute("userName");
	
	//세션영역에 저장된 모든 정보 삭제
	session.invalidate();

	//응답하기	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
	<script>
		alert("로그 아웃 되었습니다");
		location.href="${pageContext.request.contextPath }/"; //  / 마지막에 슬래시로 "최상위 경로"요청해서 index페이지로 redirect 하는 것
		
	</script>

</body>
</html>