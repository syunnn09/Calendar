<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>パスワード変更</title>
    <link rel="stylesheet" type="text/css" href="css/change.css">
</head>
<body>
    <div class="password-change-container">
        <h1>パスワード変更</h1>
        <form action="PasswordChangeServlet" method="post">
            <input type="password" name="currentPassword" placeholder="現在のパスワード" required>
            <input type="password" name="newPassword" placeholder="新しいパスワード" required>
            <input type="password" name="confirmNewPassword" placeholder="新しいパスワードの確認" required>
            <button type="submit">変更</button>
        </form>
    </div>
</body>
</html>