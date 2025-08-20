<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/new-form.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 추가 양식</h1>
		<form action="${pageContext.request.contextPath }/member/save" method="post"> <!-- /member/save 이 요청을 처리할 controller 메소드를 만들어야 한다. -->
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name"/>
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr"/>
			</div>
			<button type="submit">저장</button>
		</form>
	</div>
</body>
</html>