<%@page import="ani.domain.AniVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	AniVO vo = (AniVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>아이디 : </td>
		<td><%= vo.getMem_id() %></td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td><%= vo.getMem_pwd() %></td>
	</tr>
	<tr>
		<td>닉네임 : </td>
		<td><%= vo.getMem_nick() %></td>
	</tr>
	<tr>
		<td>가입일 : </td>
		<td><%= vo.getJoin_date() %></td>
	</tr>
</table>

</body>
</html>