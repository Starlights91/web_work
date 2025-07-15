<%@page import="test.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//삭제할 번호
	int num=Integer.parseInt(request.getParameter("num"));
	//DB 에서 삭제하고
	new BookDao().deleteByNum(num);
	//새로운 경로로 요청을 다시 하라고 응답
	String cPath=request.getContextPath();
	
	// HttpServletResponse 객체의 메소드를 이용해서 책목록 페이지를 다시 요청하도록 한다 (페이지 refresh 효과) 그냥 해당 페이지 자체에서 특정 항목 삭제만 됨.
	response.sendRedirect(cPath+"/book/list.jsp"); //이 한줄 자체가 응답인거라서 이걸로 끝.
	//즉,
	//   /book/list.jsp (여기서 삭제 누르면) =>  /book/delete.jsp (여기로 이동) =>  /book/list.jsp (list로 이동)
%>

