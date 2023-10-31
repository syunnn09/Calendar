<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/LoginServlet" method = "post">
ユーザID:<input type="text" name = "userId"><br>
パスワード:<input type="password" name = "password"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>