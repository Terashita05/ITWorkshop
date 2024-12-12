<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- <%@ page import="model.User, model.Mutter, java.util.List" %>--%>

<%--<%
//セッションスコープに保存されたユーザー情報を取得
	User loginUser = (User)session.getAttribute("loginUser");
//アプリケーションスコープに保存されたつぶやきリストを取得
	List<Mutter> visitRecordList = (List<Mutter>)application.getAttribute("visitRecordList");
//リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg = (String)request.getAttribute("errorMsg");
	%>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶメイン</h1>
	<p>
	<c:out value="${loginUser.name }" />さん、ログイン中
   <%--  <%= loginUser.getName() %>さん、ログイン中--%>
		<a href="Logout">ログアウト</a>
	</p>
	<p><a href="Main">更新</a></p>
	<form action="Main" method="post">
		<input type="text" name="search" value="${search}">
		<input type="submit" value="検索">
	</form>
	<form action="Main" method="post">
		<input type="text" name="text">
		<input type="submit" value="つぶやく">
	</form>
<%-- 	
	<%if(errorMsg != null){ %>
		<p><%= errorMsg %></p>
	<% } %>
	<% for(Mutter visitRecord : visitRecordList){ %>
		<p><%= visitRecord.getUserName() %> : <%= visitRecord.getText() %></p>
	<% } %>
--%>
	<c:if test="${not empty errorMsg }">
		<p><c:out value="${errorMsg }" /></p>
	</c:if>
	<c:forEach var="visitRecord" items="${visitRecordList }">
		<p><c:out value="${visitRecord.createdAt} " /><br>
		<c:out value="${visitRecord.userName }" />
		   <c:out value="${visitRecord.text} " />
		   <c:out value="${visitRecord.notes} " />
		   </p>
	</c:forEach>
</body>
</html>