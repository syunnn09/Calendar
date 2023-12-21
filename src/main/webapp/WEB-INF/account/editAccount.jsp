<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String text = (String) request.getAttribute("userName");
	text = text != null ? text : "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント編集</title>
    <link rel="stylesheet" type="text/css" href="css/change.css">
</head>
<body>
    <div class="password-change-container">
    	<h1>名前変更</h1>
        <form action="EditAccountServlet" method="post">
            <input type="name" name="userName" placeholder="名前" id="userName" value="<%= text %>" required>
            <button type="submit" id="button">変更</button>
        </form>
    </div>
</body>
</html>