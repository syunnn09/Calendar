<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int roomId = Integer.parseInt(request.getParameter("roomId"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/chat.css">
	<title>Insert title here</title>
</head>
<body>
	<div class="frame">
		<div id="chat"></div>
	</div>
	<input type="text" id="msg">
	<button onclick="sendMsg()">Enter</button>
</body>
<script>
	var wsUrl;
	if (window.location.protocol == 'http:') {
		wsUrl = 'ws://';
	} else {
		wsUrl = 'wss://';
	}

	var ws = new WebSocket(wsUrl + window.location.host + '/Calendar/chat/echo/<%= roomId %>');

	ws.onmessage = function(event) {
		var elem = document.createElement('div');
		var chat = document.getElementById('chat');

		var data = JSON.parse(event.data);
		console.log(data)
		elem.innerHTML = data.message;

		elem.classList.add(data.isMine ? "mine" : "else");
		chat.appendChild(elem);
	}

	function sendMsg() {
		var msg = document.getElementById('msg');
		var message = msg.value;
		if (message) {
			ws.send(message);
		}
		msg.value = '';
	}
</script>
</html>