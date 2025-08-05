<%@page import="test.dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//삭제할 댓글번호
	int num=Integer.parseInt(request.getParameter("num"));
	//리다일렉트 이동할때 필요한 원글의 글번호
	String parentNum=request.getParameter("parentNum");
	//dao 객체를 이용해서 삭제하고 (CommentDao 에서 댓글 삭제하는메소드 추가 후, 하단 입력)
	CommentDao.getInstance().delete(num);
	
	//redirect 이동 (Context경로+ view.jsp의 (parentNum)해당글로 다시 돌아가도록 함.) 따라서 html 페이지 필요 없음.
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/gallery/view.jsp?num="+parentNum);
%>
