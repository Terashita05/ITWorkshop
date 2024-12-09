<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
//セッションスコープからユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	どこつぶログイン
	<% if(loginUser != null){ %>
		
		<p>ようこそ<%= loginUser.getName() %>さん</p>
		<a href="Main">つぶやき投稿・閲覧へ</a>
	<% }else{ %>
		<p>ログインに失敗しました</p>
		<a href="index.jsp">TOPへ</a>
	<% } %>
</body>
</html>