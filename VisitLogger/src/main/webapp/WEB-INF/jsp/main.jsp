<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>どこつぶ - 訪問記録管理</title>
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
	
    <div class="app-container">
        <!-- Header Section -->
        <form action="Dashboard" method="get" style="display:inline;">
        	<button type="submit" class="nav-button">訪問予定画面へ</button>
    	</form>
		<form action="GeminiAPI" method="get" style="display:inline;">
        	<button type="submit" class="nav-button">Gemini</button>
    	</form>
        <h1>Gemini API 質問フォーム</h1>
        <form action="GeminiAPI" method="post">
            <label for="prompt">質問を入力してください：</label><br>
            <textarea id="prompt" name="prompt" rows="5" cols="50" required></textarea><br>
            <button type="submit">送信</button>
        </form>
    
        <header>
        
            <h1>訪問記録管理</h1>
            <div class="user-info">
                <span><c:out value="${loginUser.name}" /> さん、ログイン中</span>
                <a href="Logout" class="logout-button">ログアウト</a>
            </div>
        </header>
		
        <!-- Main Content -->
        <main>
            <!-- Search Section -->
            <section class="search-section">
                <form action="Main" method="post" style="display:inline;">
                    <input type="text" name="search" value="${search}" placeholder="検索キーワードを入力">
                    <button type="submit">検索</button>     
                </form>
                <form action="Main" method="get" style="display:inline;">
        			<button type="submit" class="nav-button">検索クリア</button>
    			</form>
                <!--<a href="Main" class="refresh-button">検索クリア</a>-->
                
            </section>

            <!-- Post Section -->
            <section class="post-section">
                <h2>訪問記録を登録</h2>
                <form action="Main" method="post">
                    <!-- Grouped Fields for Horizontal Layout -->
                    <div class="form-group-horizontal">
                        <div class="form-group">
                            <label for="visit_date">訪問日時</label>
                            <input type="datetime-local" id="visit_date" name="visit_date">
                        </div>
                        <div class="form-group">
                            <label for="clientName">顧客名</label>
                            <input type="text" id="clientName" name="clientName" placeholder="顧客名を入力">
                        </div>
                        <div class="form-group">
                            <label for="address">住所</label>
                            <input type="text" id="address" name="address" placeholder="住所を入力">
                        </div>
                    </div>

                    <!-- Separate Field for Visit Record -->
                    <div class="form-group">
                        <label for="text">訪問記録</label>
                        <textarea id="text" name="text" placeholder="訪問記録を入力" rows="8"></textarea>
                    </div>
                    <div class="form-group">
                            <label for="followUp_date">次回訪問日</label>
                            <input type="datetime-local" id="followUp_date" name="followUp_date">
                    </div>
                    <button type="submit" class="submit-button">登録</button>
                </form>
            </section>

            <!-- Mutter List Section -->
            <section class="visitRecord-list">
                <h2>訪問記録一覧</h2>
                <c:forEach var="visitRecord" items="${visitRecordList}">
                    <div class="visitRecord-card">
                        <p><strong>顧客名:</strong> <c:out value="${visitRecord.clientName}" /></p>
                        <p><strong>訪問日時:</strong> <c:out value="${visitRecord.visitDate}" /></p>
                        <p><strong>連絡担当者:</strong> <c:out value="${visitRecord.contactPerson}" /></p>
                        <p><strong>訪問内容:</strong> <c:out value="${visitRecord.visitNotes}" /></p>
                         <p><strong>次回訪問予定:</strong> <c:out value="${visitRecord.followUpDate}" /></p>
                        <p>
                            <strong>住所:</strong> <c:out value="${visitRecord.address}" />
                            <a href="https://www.google.com/maps/search/?api=1&query=<c:out value='${visitRecord.address}' />" 
                               target="_blank" class="map-button">地図を表示</a>
                        </p>
                        <!-- 編集ボタン -->
				        <form action="Edit" method="get" style="display:inline;">
				            <input type="hidden" name="id" value="${visitRecord.id}" />
				            <button type="submit" class="edit-button">編集</button>
				        </form>
				
				        <!-- 削除ボタン -->
				        <form action="Delete" method="post" style="display:inline;">
				            <input type="hidden" name="id" value="${visitRecord.id}" />
				            <button type="submit" class="delete-button" onclick="return confirm('本当に削除しますか？');">削除</button>
				        </form>
                    </div>
                </c:forEach>
            </section>

            <!-- Error Message Display -->
            <c:if test="${not empty errorMsg}">
                <p class="error-message">${errorMsg}</p>
            </c:if>
        </main>
		
        <!-- Footer Section -->
        <footer>
            <p>© 2024 どこつぶ</p>
        </footer>
    </div>
</body>
</html>
