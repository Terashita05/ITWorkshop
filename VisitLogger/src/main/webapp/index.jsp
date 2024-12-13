<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
<link rel="stylesheet" href="css/login.css"> <!-- 統一されたCSSファイル -->
</head>
<body>
    <div class="login-container">
        <h1>どこつぶへようこそ</h1>
        <form action="Login" method="post" class="login-form">
            <div class="form-group">
                <label for="name">ユーザー名：</label>
                <input type="text" id="name" name="name" placeholder="ユーザー名を入力">
            </div>
            <div class="form-group">
                <label for="pass">パスワード：</label>
                <input type="password" id="pass" name="pass" placeholder="パスワードを入力">
            </div>
            <input type="submit" value="ログイン" class="submit-button">
        </form>
    </div>
</body>
</html>
