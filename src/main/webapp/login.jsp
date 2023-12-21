<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String text = (String) request.getAttribute("text");
	text = text != null ? text : "";
%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <h1>LOGIN</h1>
        <form action="LoginServlet" name="loginForm" method="post">
            <input type="text" name="email" placeholder="user name" value="<%= text %>" required>
            <input type="password" name="password" id="password" placeholder="password" required>
            <button name="button">login</button>
        </form>
    </div>
</body>
<script>
const password = document.getElementById('password');

document.loginForm.button.addEventListener('click', async function() {
	const text = password.value;
    const uint8  = new TextEncoder().encode(text);
    const digest = await crypto.subtle.digest('SHA-256', uint8);
    loginForm.password.value = Array.from(new Uint8Array(digest)).map(v => v.toString(16).padStart(2,'0')).join('');
    document.loginForm.submit();
});
</script>
</html>
