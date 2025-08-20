<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/insert-form.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 추가 양식</h1>	
	  	<form action="${pageContext.request.contextPath }/member/insert" method="post">
	  		<div>
	  			<label for="name">이름</label>
	     		<input type="text" name="name" required/>
	  		</div>
	  		<div>
	  			<label>주소</label>
		      	<input type="text" name="addr" required/>	  		
	  		</div>
	
		    <div>
		      <button type="submit">저장</button>
		      <a href="${pageContext.request.contextPath }/member/list">취소</a>
		    </div>
	  	</form>
	</div>
</body>
</html>