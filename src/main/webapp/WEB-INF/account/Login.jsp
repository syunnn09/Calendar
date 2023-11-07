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
        <form action="LoginServlet" method="post">
            <input type="text" name="username" placeholder="user name" required>
            <input type="password" name="password" placeholder="password" required>
            <button type="submit">login</button>
        </form>
    </div>
</body>
</html>
