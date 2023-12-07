<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予定作成</title>
<link rel="stylesheet"href="./css/group.css">
</head>
<body>
	<form action="CreateScheduleServlet" method="post">
		<table align="center">
			<tr>
			<td>タイトル</td>
			<td><input type="text" name="title"></td>
			</tr>
			<tr>
			<td>ルームID</td>
			<td><input type="text" name="roomId"></td>
			</tr>
			<tr>
			<td>日時</td>
			<td><input type="date" name="startDate">
			～ <input type="date" name="endDate"></td>
			</tr>
			<tr>
			<td>詳細</td>
			<td><textarea name="detail"></textarea></td>
			</tr>
			<tr>
			<td>場所</td>
			<td><input type="textarea" name="place"></td>
			</tr>
		</table>
		<input type="submit"value="作成">
	</form>
</body>
</html>