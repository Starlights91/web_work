<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insertform.jsp</title>
    <!-- 복사한 bootstrap css 로딩시키기 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<!-- 복사한 bootstrap icons css 로딩시키기 -->

</head>
<body>
	<div class="container">
		<h1>회원 정보 추가 양식</h1>
		<form action="${pageContext.request.contextPath }/member/insert.jsp" method="post"> <!-- 3. /member/insert.jsp 페이지가 요청이되면서 전송이된다. -->
			<!-- mb = margin-bottom 1(4px) ~ 5단계까지  예) 8px 떨어뜨리고 싶으면 "mb-2"  -->
			<div class="mb-2">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" /> <!--  2. input 요소에 입력한 이름&주소가 name, addr 라는 파라미터명으로 -->
				<!-- 조금 연한 회색으로 help-text 적용: class="form-text"> -->
				<div class="form-text">10 글자 이내로 입력해 주세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="addr">주소</label>
				<input class="form-control" type="text" name="addr" id="addr" />
				<!-- 조금 연한 회색으로  help-text 적용: class="form-text"> -->
				<div class="form-text">실제 거주하는 주소를 입력해 주세요</div>			
			</div>
			<!--  primary :파랑, success:초록, info:하늘 danger:빨강  btn-outline-primary: 외곽선 스타 & btn-sm : 버튼 크기 작게 -->
			<button class="btn btn-primary btn-sm" type="submit">
				저장
				<!-- 이미지를 글자로 만들어져 있어서, 크기/색상 vector 로 만들어져서, 크기/색상을 맘대로 바꾸더라도 깨지지 않는다. -->
				<!-- svg 는 간단하게 한개의 이미지 파일만 불러올 때 사용 -->
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-floppy2-fill" viewBox="0 0 16 16">
				  <path d="M12 2h-2v3h2z"/>
				  <path d="M1.5 0A1.5 1.5 0 0 0 0 1.5v13A1.5 1.5 0 0 0 1.5 16h13a1.5 1.5 0 0 0 1.5-1.5V2.914a1.5 1.5 0 0 0-.44-1.06L14.147.439A1.5 1.5 0 0 0 13.086 0zM4 6a1 1 0 0 1-1-1V1h10v4a1 1 0 0 1-1 1zM3 9h10a1 1 0 0 1 1 1v5H2v-5a1 1 0 0 1 1-1"/>
				</svg>
			</button> <!-- 1. form 안에 있는 submit 버튼 누르면 -->
		</form>
	</div>
</body>
</html>