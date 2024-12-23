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
      
        <h2>応答</h2>
        <%= request.getAttribute("response") %>
         <form action="Dashboard" method="get" style="display:inline;">
        			<button type="submit" class="nav-button">訪問予定画面へ</button>
    	</form>
    </div>
</body>
</html>
