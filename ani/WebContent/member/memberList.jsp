<%@page import="java.util.List"%>
<%@page import="ani.domain.AniVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기까지 옴?</h1>
	<p>왔으면 리퀘에 저장된거 적당히 뿌리면 됨</p>
	
	<%
		List<AniVO> list = (List<AniVO>)request.getAttribute("mem_id");
//*
		int listCount = (int) request.getAttribute("mem_pwd");
		int maxPage = (int) request.getAttribute("maxPage");
		int startPageNum = (int) request.getAttribute("startPageNum");
		int endPageNum = (int) request.getAttribute("endPageNum");
		int nowPage = (int) request.getAttribute("nowPage");
	
//*/	
		int dataCnt = list.size();
	%>
	<table>
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>가입일</th>
		</tr>

	<%
		for(int i = 0; i<dataCnt; i++){
			AniVO vo = list.get(i);	
	%>
		<tr>
			<td> <%= vo.getMem_id() %> </td>
			<td> <%= vo.getMem_nick() %> </td>
			<td> <%= vo.getJoin_date() %> </td>
			<td> <label hidden="hidden"> 비밀 번호 <%= vo.getMem_pwd() %></label> </td>
		</tr>
	<% } %>
	</table>
	
	
	<p>
		<a href="./BoardList.babo?page=1">맨처음</a>&nbsp;&nbsp;&nbsp;
		<a href="./BoardList.babo?page=<%=nowPage-1 %>">이전 페이지</a>&nbsp;&nbsp;&nbsp;
	<%
//*	
		for(int q=startPageNum; q<= endPageNum; q++){
			if(q == nowPage){
				out.println("<a href=\"./memberList.aho?page="+ q+"\" class=\"nowPage\">"+1+"</a>&nbsp;&nbsp;&nbsp;");
			} else{
				out.println("<a href=\"./memberList.aho?page="+ q+" \">"+q+"</a>&nbsp;&nbsp;&nbsp;");
			}
		}
//*/	
	%>		
		<a href="./BoardList.babo?page=<%=nowPage+1 %>">다음 페이지</a>&nbsp;&nbsp;&nbsp;
		<a href="./BoardList.babo?page=<%=maxPage %>">맨 끝</a>&nbsp;&nbsp;&nbsp;
	</p>
	<a href="./BoardWrite.babo">글쓰기</a>

	
</body>
</html>