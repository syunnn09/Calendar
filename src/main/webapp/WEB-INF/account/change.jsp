<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>パスワード変更</title>
    <link rel="stylesheet" type="text/css" href="css/change.css">
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
#button {
	background-color: #ccc;
	cursor: default;
}
#button.ok {
	background-color: #ffaaff;
	cursor: pointer;
}
</style>

<body>
    <div class="password-change-container">
        <h1>パスワード変更</h1>
        <form action="ChangeServlet" name="changeForm" method="post">
            <input type="password" name="newPassword" placeholder="新しいパスワード" id="newPassword" required>
            <input type="password" name="confirmNewPassword" placeholder="新しいパスワードの確認" id="confirmNewPassword" required>
            <button id="button" name="button">変更</button>
        </form>
    </div>
</body>

<script>
const newPassword = document.getElementById('newPassword');
const confirmNewPassword = document.getElementById('confirmNewPassword');
const button = document.getElementById('button');

function onChangePassword() {
	let isValid = newPassword.value == confirmNewPassword.value;
	isValid = isValid && newPassword.value.length != 0;

	button.disabled = !isValid;
	if (isValid) {
		button.classList.add('ok');
	} else {
		button.classList.remove('ok');
	}
}

newPassword.addEventListener('input', onChangePassword);
confirmNewPassword.addEventListener('input', onChangePassword);
</script>
</html>
