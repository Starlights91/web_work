<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.util.DbcpBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//<% new DbcpBean().getConn(); 
	//1. 회원 목록을 MemberDao 객체를 이용해서 얻어낸다
	List<MemberDto> list=new MemberDao().selectAll();
	//2. 응답한다
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="member" name="thisPage"/>
	</jsp:include>

	<div class="container">
		<a href="${pageContext.request.contextPath }/member/insertform.jsp">회원 추가</a>
		<h1>회원 목록입니다</h1>
			<table class="table table-striped">
				<thead class="table-primary">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>주소</th>
						<th>수정</th> <!-- 수정 칼럼 추가 -->
						<th>삭제</th> <!-- 삭제 칼럼 추가 -->
					</tr>
				</thead>
				<tbody>
				<%for(MemberDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getName() %></td>	
						<td><%=tmp.getAddr() %></td>
						<td><a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a></td>			
						<td><a href="javascript:" class="delete-link" data-num="<%=tmp.getNum()%>">삭제</a></td>	<!-- 파라미터 get 방식으로 숫자동일하게 가져올 수 있다.  -->		
					
					</tr>
				<%} %>
				</tbody>
			</table>
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
	<script>
		//여기에 forEach 대신 for 문 사용도 가능함
		document.querySelectorAll(".delete-link").forEach(item=>{
			//item 은 클릭한 a 요소의 참조값
			item.addEventListener("click", (e)=>{
				// e.target 은 이벤트가 발생한 바로 그 요소의 참조 값이다.
				// "click" 이벤트가 발생한 a 요소에 data-num 속성의 value 를 읽어오기
				const num=e.target.getAttribute("data-num");
				const isDelete=confirm("삭제 하시겠습니까?");
				if(isDelete){
					//delete.jsp 페이지로 이동하게 하면서 삭제할 회원의 번호도 같이 전달되도록 한다.
					location.href="${pageContext.request.contextPath }/member/delete.jsp?num="+num; // 여기에 해당번호가 가져와지면
				}
			});
		});
		
	</script>
</body>
</html>