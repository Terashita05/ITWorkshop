
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>どこつぶ</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>どこつぶメイン</h1>

        <!-- User Info Section -->
        <p>
            <c:out value="${loginUser.name}" /> さん、ログイン中
            <a href="Logout">ログアウト</a>
        </p>

        <!-- Update Section -->
        <p><a href="Main">更新</a></p>

        <!-- Search Form -->
        <form action="Main" method="post">
            <input type="text" name="search" value="${search}" placeholder="検索キーワードを入力">
            <input type="submit" value="検索">
        </form>

        <!-- Post Form -->
        <form action="Main" method="post">
        	<label for="visit_date">訪問日時:</label>
        	<input type="datetime-local" id="visit_date" name="visit_date">
        	<br><br>
        	<input type="text" name="clientName" placeholder="顧客名"><br>
            <input type="text" name="text" placeholder="つぶやきを入力"><br>
            <input type="text" name="address" placeholder="住所を入力"><br>
            <input type="submit" value="投稿">
        </form>

        <!-- Mutter List Display -->
        <div class="mutter-list">
            <c:forEach var="mutter" items="${mutterList}">
                <div class="mutter">
                    <strong><c:out value="${mutter.clientName}" />:</strong>
                    <p><c:out value="${mutter.visitDate}" /></p>
                    <p><c:out value="${mutter.contactPerson}" /></p>
                    <p><c:out value="${mutter.visitNotes}" /></p>
                    <p><c:out value="${mutter.address}" /></p>
                </div>
            </c:forEach>
        </div>

        <!-- Error Message Display -->
        <c:if test="${not empty errorMsg}">
            <p class="error">${errorMsg}</p>
        </c:if>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>© 2024 どこつぶ</p>
    </footer>
</body>
</html>
