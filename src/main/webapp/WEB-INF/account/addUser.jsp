<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String text = (String) request.getAttribute("text");
	text = text != null ? text : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー作成</title>
<link rel="stylesheet" href="css/addUser.css">
</head>
<body>
	<p class="text"><%= text %></p>
	<div id="dropArea" ondrop="ondrop(event);" ondragover="ondragover(event);">
		<p>csvファイルをドラッグ</p>
	</div>
	<form action="AddUserServlet" method="POST" enctype="multipart/form-data">
		<input type="file" name="file" id="file" accept="text/.csv">
		<input type="submit" value="送信">
	</form>
</body>
<script>
const dropArea = document.getElementById('dropArea');
const file = document.getElementById('file');
dropArea.addEventListener('dragover', function(e) {
	e.preventDefault();
	dropArea.classList.add('dropped');
});

dropArea.addEventListener('drop', function(e) {
	e.preventDefault();
	let files = e.dataTransfer.files;
	if (files.length != 1) {
		alert('1つのファイルのみ指定することができます。');
		return;
	}
	if (files[0].type != 'text/csv') {
		alert('csvファイルのみ指定することができます。')
		return;
	}

	file.files = files;
	dropArea.classList.remove('dropped');
});

dropArea.addEventListener('dragleave', function(e) {
	e.preventDefault();
	dropArea.classList.remove('dropped');
});
</script>
</html>