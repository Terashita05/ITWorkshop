<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Gemini API 結果</title>
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
    <div class="app-container">
        <!-- Header Section -->
        <form action="Dashboard" method="get" style="display:inline;">
            <button type="submit" class="nav-button">訪問予定画面へ</button>
        </form>
        <header>
            <h1>訪問記録管理</h1>
            <div class="user-info">
                <span><c:out value="${loginUser.name}" /> さん、ログイン中</span>
                <a href="Logout" class="logout-button">ログアウト</a>
            </div>
        </header>
        
        <!-- Gemini API Question Section -->
        <main>
            <section class="gemini-api-section">
                <h2>Gemini API 質問フォーム</h2>
                <form action="GeminiAPI" method="post">
                    <label for="prompt">質問を入力してください：</label><br>
                    <textarea id="prompt" name="prompt" rows="5" cols="50" required></textarea><br>
                    <button type="submit">送信</button>
                </form>
            </section>

            <!-- Gemini API Response Section -->
            <section class="gemini-api-response">
                <h2>APIの応答結果</h2>
                <c:if test="${not empty response}">
                    <p><strong>Gemini API の応答:</strong></p>
                    <pre>${response}</pre>
                </c:if>
                <c:if test="${empty response}">
                    <p>質問に対する応答がまだありません。</p>
                </c:if>
            </section>
        </main>

        <!-- Footer Section -->
        <footer>
            <p>© 2024 どこつぶ</p>
        </footer>
    </div>
</body>
</html>
