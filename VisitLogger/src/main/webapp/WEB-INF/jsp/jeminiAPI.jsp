<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Hugging Face API 結果</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="app-container">
        <h1>API応答結果</h1>
        <p><strong>質問：</strong><%= request.getParameter("question") %></p>
        <h2>応答</h2>
        <pre><%= request.getAttribute("response") %></pre>
        <a href="index.jsp">戻る</a>
    </div>
</body>
</html>
