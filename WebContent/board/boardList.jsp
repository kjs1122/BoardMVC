<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	int listcount = (Integer)request.getAttribute("listCount");
	int maxPage = (Integer)request.getAttribute("maxPage");
	int startPage = (Integer)request.getAttribute("startPage");
	int endPage = (Integer)request.getAttribute("endPage");
	int nowPage = (Integer)request.getAttribute("page");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/cssBoard.css">
</head>
<body>
<div id="wrap" align="center">
<h1> 게시글 리스트 </h1>
<table class="list">
	<tr>
		<td colspan="5" style="border: white; text-align: right">
			<a href="BoardServlet?command=board_write_form">게시글 등록</a>
		</td>
	</tr>
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
	<c:forEach var="board" items="${boardList}">
	<tr class="record">
		<td>${board.num}</td>
		<td>
			<a href="BoardServlet?command=board_view&num=${board.num}">
			${board.title}
			</a>
		</td>
		<td>${board.name}</td>
		<td><fmt:formatDate value="${board.writedate}"/></td>
		<td>${board.readcount}</td>
	</tr>
	</c:forEach>
	<tr align="center" height="20">
		<td colspan="5">
			<%if(nowPage <= 1) {%>
				[이전]&nbsp;
			<%} else { %>		
				<a href="/BoardServlet?command=board_list&page=<%= nowPage -1%>">[이전]</a>&nbsp;
			<%} %>
			<%for(int a = startPage; a <= endPage; a++ ) 
				if(a == nowPage) {
			%>
				[<%=a%>]
				<%} else { %>		
				<a href="/BoardServlet?command=board_list&page=<%=a%>">[<%=a%>]</a>&nbsp;	
				<%} %>
			<%if(nowPage>=maxPage) {%>		
				[다음]				
			<%} else { %>
				<a href="/BoardServlet?command=board_list&page=<%= nowPage +1%>">[다음]</a>
			<%} %>
	
	
	
	</table>
</div>
</body>
</html>
