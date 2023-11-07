<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"href="./css/group.css">
<title>メンバ追加</title>
</head>
<body>
	<form action="AddMemberServlet"method="post">
        <div>ルームID:<input type="text"name="roomId"><br></div>
        <div>ユーザID:<input type="text"name="insertUserIds"><br></div>
        <input type="submit"value="追加">
    </form>
</body>
</html>