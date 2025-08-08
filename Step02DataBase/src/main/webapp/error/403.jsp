<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/error/403.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container text-center py-5">
        <h1 class="display-3 text-danger">403</h1>
        <h2 class="mb-3">접근 권한이 없습니다</h2>
        <p class="text-muted mb-4">요청하신 페이지에 접근할 권한이 없습니다. 
        <br/>로그인 상태를 확인하거나 관리자에게 문의하세요.</p>
        <a href="<%= request.getContextPath() %>/" class="btn btn-primary btn-lg">메인 페이지로 이동</a>
    </div>
</body>
</html>