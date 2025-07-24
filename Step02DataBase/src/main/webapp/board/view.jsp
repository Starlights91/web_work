<%@page import="test.dto.CommentDto"%>
<%@page import="test.dao.CommentDao"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.text.StringEscapeUtils"%>
<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // get 방식 파라미터로 전달되는 글 번호 얻어내기
    int num = Integer.parseInt(request.getParameter("num"));
    // DB 에서 해당 글의 자세한 정보를 얻어낸다
    BoardDto dto = BoardDao.getInstance().getByNum(num);
    // 로그인된 userName (null일 가능성이 있다)
    String userName = (String)session.getAttribute("userName");
    // 만일 본인 글 자세히 보기가 아니면 조회수를 1 증가 시킨다
    if(!dto.getWriter().equals(userName)){
        BoardDao.getInstance().addViewCount(num);
    }
    // 댓글 목록 DB 에서 읽어오기
    List<CommentDto> commentList = CommentDao.getInstance().selectList(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/view.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
    <div class="container pt-3">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/board/list.jsp">Board</a>
                </li>
                <li class="breadcrumb-item active">Detail</li>
            </ol>
        </nav>
        <h1>게시글 상세보기</h1>
        <div class="btn-group mb-2">
            <a class="btn btn-outline-secondary bg-primary-subtle btn-sm <%=dto.getPrevNum() == 0? "disabled" : "" %>" href="view.jsp?num=<%=dto.getPrevNum()%>">    
                <i class="bi bi-arrow-left"></i>
                Prev
            </a>
            <a class="btn btn-outline-secondary bg-primary-subtle btn-sm <%=dto.getNextNum() == 0? "disabled" : "" %>" href="view.jsp?num=<%=dto.getNextNum()%>">
                Next
                <i class="bi bi-arrow-right"></i>
            </a>
        </div>
        <table class="table table-striped">
            <colgroup>
                <col class="col-2" />
                <col class="col" />
            </colgroup>
            <tr>
                <th>글번호</th>
                <td><%=num%></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>
                    <%if(dto.getProfileImage() == null) {%>
                        <i style="font-size:100px;" class="bi bi-person-circle"></i>
                    <%} else{%>    
                        <img src="${pageContext.request.contextPath }/upload/<%=dto.getProfileImage() %>" 
                             style="width:100px;height:100px;border-radius:50%;"/>
                    <%} %>
                    <%=dto.getWriter()%>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td><%=dto.getTitle()%></td>
            </tr>
            <tr>
                <th>조회수</th>
                <td><%=dto.getViewCount() %></td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><%=dto.getCreatedAt()%></td>
            </tr>
        </table>
        <%-- 
            클라이언트가 작성한 글 제목이나 내용을 그대로 클라이언트에게 출력하는 것은
            javascript 주입 공격을 받을 수 있다
            따라서 해당 문자열을 escape 해서 출력하는 것이 안전하다
         --%>
        <div class="card mt-4">
            <div class="card-header bg-light">
                <strong>본문 내용</strong>
            </div>
            <div class="card-body p-1">
                <%=dto.getContent()%>
            </div>
        </div>
        <%if(dto.getWriter().equals(userName)) {%>
            <div class="text-end pt-2">
                <a class="btn btn-warning btn-sm" href="edit.jsp?num=<%=dto.getNum()%>">Edit</a>
                <a class="btn btn-danger btn-sm" href="delete.jsp?num=<%=dto.getNum()%>">Delete</a>
            </div>
        <%} %>

        <div class="card my-3">
            <div class="card-header bg-primary-subtle text-white">
                댓글을 입력해 주세요
            </div>
            <div class="card-body">
                <!-- 원글의 댓글을 작성할 폼 -->
                <form action="save-comment.jsp" method="post">
                    <!-- 숨겨진 입력값 -->
                    <input type="hidden" name="parentNum" value="<%=dto.getNum() %>"/>
                    <input type="hidden" name="targetWriter" value="<%=dto.getWriter() %>" />

                    <div class="mb-3">
                        <label for="commentContent" class="form-label">댓글 내용</label>
                        <textarea id="commentContent" name="content" rows="5" class="form-control" placeholder="댓글을 입력하세요"></textarea>
                    </div>

                    <button type="submit" class="btn bg-primary-subtle text-white">등록</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
