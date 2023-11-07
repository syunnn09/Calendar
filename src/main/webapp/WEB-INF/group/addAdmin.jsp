<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者追加</title>
</head>
<body>
	<form action="AddAdminServlet"method="post">
        ルームID:<input type="text"name="roomId"><br>
        ユーザID:<input type="text"name="insertUserIds"><br>
        <input type="submit"value="追加">
    </form>
</body>
</html>