<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. form 전송되는 수정할 책 정보를 추출해서
	// GET 방식 파라미터로 전달되는 도서목록의 특정 도서번호 얻어내기
	int num=Integer.parseInt(request.getParameter("num"));
	// POST 방식으로 전달된 수정할 이름&주소 파라미터 얻어내기
	String title=request.getParameter("title");
	String author=request.getParameter("author");
	String publisher=request.getParameter("publisher");
	
	// 2. MemberDto 객체에 담고  //수정할 데이터 담기 & DAO를 이용해서 DB에 반영
	BookDto dto=new BookDto();
	dto.setNum(num);
	dto.setTitle(title);
	dto.setAuthor(author);
	dto.setPublisher(publisher);
	
	//3. DB 에 수정 반영하고
	boolean isSuccess=new BookDao().update(dto);
  	//4. 클라이언트에게 응답한다.

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/update.jsp</title>
</head>
<body>
	<%-- html 응답하면서 javascript 를 로딩시키기 --%>
	<script>
	<%-- 여기 script 안에 작성한 문자열은 클라이언트 웹브라우저가 javascript 로 평가해서 해석한다 --%>
	
		<%if (isSuccess){ %>
			//alert("java 책의 정보를...")
		 	alert("<%="title" %> 책의 정보를 성공적으로 수정했습니다"); 
		 	//javascript 를 이용해서 페이지 이동 (redirect 효과를 낼 수 있다)
		 	location.href="list.jsp";
		<%}else { %>
			alert("수정 실패!");
			//다시 수정 폼으로 이동 시킨다. //location.href 해당페이지로 알아서 이동 시킨다.
			location.href="updateform.jsp?num=<%=num %>"; 
		<%} %>	
	</script>
</body>
</html>