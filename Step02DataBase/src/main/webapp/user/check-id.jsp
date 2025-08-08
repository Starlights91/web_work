<%@page import="test.dao.UserDao"%>
<%@page import="test.dto.UserDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%> <%-- application/json 는 json 문자열을 응답하겐단 의미 --%>
<%
	//get 방식 파라미터로 넘어오는 입력한 id 을 읽어온다.
	String inputId = request.getParameter("inputId");
	//dao 를 이용해서 해당정보가 있는지 select 해본다. (null 일 가능성 있음)
	UserDto dto=UserDao.getInstance().getByUserName(inputId);
	//아이디 사용가능 여부 알아내기
	boolean canUse = dto == null ? true : false;
%>
<%-- 응답되는 문자열은 {"canUse":<%=canUse %>} {"canUse":true} 또는 {"canUse":false} --%>
{"canUse":<%=canUse %>}