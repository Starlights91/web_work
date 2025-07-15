<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. form 전송되는 수정항 회원의 정보를 추출한다.
	// GET 방식 파라미터로 전달되는 회원의 번호 얻어내기
	int num =Integer.parseInt(request.getParameter("num"));
	// POST 방식으로 전달된 수정할 이름과 주소 파라미터 얻어내기
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
    //2. MemberDao 객체를 이용해서 DB 에 수정반영하기
	// MemberDto에 수정할 데이터 담기 & DAO를 이용해서 DB에 반영
    MemberDto dto = new MemberDto();
    dto.setNum(num);
    dto.setName(name);
    dto.setAddr(addr);
    boolean isSuccess=new MemberDao().update(dto);
    
    //3. 클라이언트에게 응답한다.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<%if(isSuccess){ %>
		<p>
			<strong><%=num %></strong> 번 회원의 정보를 수정했습니다.
			<a href="list.jsp">확인</a>
		</p>
	<%}else{ %>
		<p>
			수정실패! <a href="updateform.jsp?num=<%=num %>">다시 수정하러 가기</a>
		</p>	
	<%} %>


</body>
</html>