<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup-form.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원가입 양식</h1>
		<form action="signup.jsp" method="post"> <!-- 아이디,비번,이메일 이 signup.jsp 로 전송 -->
			<div>
				<label for="userName">아이디</label>
				<input type="text" name="userName" id="userName" />
			</div>
			<div>
				<label for="password">비밀번호</label>
				<input type="text" name="password" id="password" />
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" name="email" id="email" />
			</div>
			<button type="submit">가입</button>
		</form>
	</div>

</body>
</html>