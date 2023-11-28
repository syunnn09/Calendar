<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"href="./css/group.css">
<title>管理者追加</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#bu").click(function(){
			$('div').append('ユーザID:<input type="text"name="insertUserIds"><br>')
		})
	})
</script>
</head>
<body>
	<form action="AddAdminServlet"method="post">
        <div>
        	ルームID:<input type="text"name="roomId"><br>
        	ユーザID:<input type="text"name="insertUserIds"><br>
        </div>
        <input type="button"value="+"id="bu">
        <input type="submit"value="追加">
    </form>

</body>
</html>