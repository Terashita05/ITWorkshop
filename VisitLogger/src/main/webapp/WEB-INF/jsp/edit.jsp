<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>訪問記録の編集</title>
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/edit.css">
</head>
<body>
    <div class="app-container">
        <h1>訪問記録の編集</h1>
        <form action="Update" method="post" class="edit-form">
            <input type="hidden" name="id" value="${visitRecord.id}" />

            <div class="form-group">
                <label for="clientName">顧客名</label>
                <input type="text" id="clientName" name="clientName" value="${visitRecord.clientName}" />
            </div>

            <div class="form-group">
                <label for="visitDate">訪問日時</label>
                <input type="datetime-local" id="visitDate" name="visitDate" value="${visitRecord.visitDate}" />
            </div>

            <div class="form-group">
                <label for="address">住所</label>
                <input type="text" id="address" name="address" value="${visitRecord.address}" />
            </div>

            <div class="form-group">
                <label for="visitNotes">訪問内容</label>
                <textarea id="visitNotes" name="visitNotes">${visitRecord.visitNotes}</textarea>
            </div>
			<div class="form-group">
                <label for="followUpDate">次回訪問日</label>
                <input type="datetime-local" id="followUpDate" name="followUpDate" value="${visitRecord.followUpDate}" />
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-button">更新</button>
            </div>
        </form>
    </div>
</body>
</html>
