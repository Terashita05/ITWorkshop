<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ダッシュボード</title>
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
    <form action="Main" method="get" style="display:inline;">
        <button type="submit" class="main-button">訪問記録画面へ</button>
    </form>

    <%
        // セッションスコープからユーザー情報を取得
        User loginUser = (User)session.getAttribute("loginUser");
    %>
    <% if (loginUser != null) { %>
        <p><%= loginUser.getName() %>さん</p>
    <% } else { %>
        <p>ログインし直してください</p>
        <a href="index.jsp">TOPへ</a>
    <% } %>
    <h1>訪問予定</h1>
    
    <section>
       
        <table>
            <thead>
                <tr>
                    <th>顧客名</th>
                    <th>次回訪問日</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="visit" items="${nextVisits}">
                    <tr>
                        <td><c:out value="${visit.getClientName()}" /></td>
                        <td><c:out value="${visit.getFollowUpDate()}" /></td>
                        <td>
                            <form action="Edit" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${visit.getId()}" />
                                <button type="submit" class="edit-button">編集</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</body>
</html>
