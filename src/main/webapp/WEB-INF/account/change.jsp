<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String text = (String) request.getAttribute("text");
	text = text != null ? text : "";
%>
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

newPassword.addEventListener('input', function() {
	console.log(newPassword.value);
	if (newPassword.value == confirmNewPassword.value) {
		console.log("パスワードが一致しています。");
		button.classList.add('ok');
	} else {
		console.log("パスワードが一致していません。");
		button.classList.remove('ok');
	}
	button.disabled = newPassword.value != confirmNewPassword.value;
});

document.changeForm.button.addEventListener('click', async function() {
	const text = newPassword.value
    const uint8  = new TextEncoder().encode(text)
    const digest = await crypto.subtle.digest('SHA-256', uint8)
    newPassword.value = Array.from(new Uint8Array(digest)).map(v => v.toString(16).padStart(2,'0')).join('')
    document.changeForm.submit()
})

</script>
</html>
