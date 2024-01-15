<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.GroupInfoBean, bean.GroupBean" %>
<%
	int groupId = Integer.parseInt(request.getParameter("groupId"));
	GroupInfoBean groupListBean = (GroupInfoBean) request.getAttribute("groupListBean");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/chat.css">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="groupContainer">
			<div class="groupItems">
				<form action="" method="POST">
					<div class="groupItem plus">+</div>
				</form>
				<a href="top" class="groupItem<%= groupId == 0 ? " current" : "" %>">
					<p>マイページ</p>
				</a>
				<% for (GroupBean group: groupListBean.getGroupArray()) { %>
					<a href="chat?groupId=<%= group.getRoomId() %>" class="groupItem<%= group.getRoomId() == groupId ? " current" : "" %>">
						<p title="<%= group.getRoomname() %>"><%= group.getRoomname() %></p>
					</a>
				<% } %>
			</div>
		</div>
		<div class="chatMain">
			<div class="headerItems">
				<div class="headerLeft">
					<div class="headerItem">
						<a href="top?groupId=<%= groupId %>" class="headerItemText">カレンダー</a>
					</div>
					<% if (groupId != 0) { %>
						<div class="headerItem">
							<a href="chat?groupId=<%= groupId %>" class="headerItemText">チャット</a>
						</div>
					<% } %>
				</div>
				<div>
					<a href="GroupManagement" class="headerItemText">&#x2699;</a>
				</div>
			</div>
			<div class="frame">
				<div id="chat"></div>
			</div>
			<input type="text" id="msg">
			<button onclick="sendMsg()">送信</button>
		</div>
	</div>
</body>
<script>
	var wsUrl;
	if (window.location.protocol == 'http:') {
		wsUrl = 'ws://';
	} else {
		wsUrl = 'wss://';
	}

	var ws = new WebSocket(wsUrl + window.location.host + '/Calendar/chat/echo/<%= groupId %>');

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