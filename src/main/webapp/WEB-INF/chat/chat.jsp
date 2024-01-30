<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.GroupInfoBean, bean.GroupBean, bean.ChatInfoBean, bean.ChatBean, javax.servlet.http.HttpSession" %>
<%
	int groupId = Integer.parseInt(request.getParameter("groupId"));
	int userId = (int) session.getAttribute("userId");
	GroupInfoBean groupListBean = (GroupInfoBean) request.getAttribute("groupListBean");
	GroupBean currentGroup = groupListBean.get(groupId);
	ChatInfoBean chatBean = (ChatInfoBean) request.getAttribute("chat");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/chat.css">
	<title>チャット - <%= currentGroup.getRoomname() %></title>
</head>
<body>
	<div class="container">
		<div class="groupContainer">
			<div class="groupItems">
				<form action="" method="POST">
					<div class="groupItem plus" id="addGroup">+</div>
				</form>
				<a href="top" class="groupItem<%= groupId == 0 ? " current" : "" %>">
					<p>マイページ</p>
				</a>
				<% for (GroupBean group: groupListBean.getGroupArray()) { %>
					<div class="group">
						<a href="?groupId=<%= group.getRoomId() %>" class="groupItem<%= group.getRoomId() == groupId ? " current" : "" %>" style="border-color: <%= group.getColor() %>">
							<p title="<%= group.getRoomname() %>"><%= group.getRoomname() %></p>
						</a>
						<% if (group.isNeedNotify()) { %>
							<p class="notify"></p>
						<% } %>
					</div>
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
					<a href="Group?gruopId=<%= groupId %>" class="headerItemText">&#x2699;</a>
				</div>
			</div>
			<div class="frame">
				<div id="chat">
					<% for (ChatBean chat : chatBean.getChatArray()) { %>
						<% if (!chat.isSystemUser()) { %>
							<div class="chatItem">
								<div class="chatIcon">
									<p class="usericon"><%= chat.getUserName().charAt(0) %></p>
								</div>
								<div class="chatItemMain">
									<div class="chatItemHeader">
										<p class="username"><%= chat.getUserName() %></p>
										<p class="timestamp"><%= chat.getTimestamp() %></p>
									</div>
									<div class="chatItemBody">
										<p><%= chat.getMessage() %></p>
									</div>
								</div>
							</div>
						<% } else { %>
							<div class="chatItem chatItemSystem">
								<p class="system"><%= chat.getMessage() %></p>
							</div>
						<% } %>
					<% } %>
				</div>
				<p id="saikabu">最下部に移動</p>
			</div>
			<textarea type="text" id="msg"></textarea>
			<button onclick="sendMsg()">送信</button>
		</div>
	</div>
	<div id="popupBase"></div>
	<div id="addPopup" class="popup">
		<div class="addPopupHeader">
			<p>グループ作成</p>
		</div>
		<div class="addPopupInner">
		<button onclick="closePopup()" class="closePopup">x</button>
			<div class="addPopupMain">
				<form action="CreatServlet" method="POST">
					<table border="0">
						<tr>
							<td>グループ名</td>
							<td><input type="text" name="roomname"></td>
						</tr>
						<tr>
							<td>色</td>
							<td><input type="color" name="color"></td>
						</tr>
					</table>
					<input type="submit" value="作成">
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	const qs = (q) => document.getElementById(q);
	const ce = (q) => document.createElement(q);
	const saikabu = qs('saikabu');
	saikabu.addEventListener('click', scrollToBottom);
	const so = saikabu.style.display;
	var page = 0;
	var isLoading = false;
	var chat = document.getElementById('chat');

	var wsUrl;
	if (window.location.protocol == 'http:') {
		wsUrl = 'ws://';
	} else {
		wsUrl = 'wss://';
	}

	var ws = new WebSocket(wsUrl + window.location.host + '/Calendar/chat/echo/<%= groupId %>?userId=<%= userId %>');

	ws.onmessage = function(event) {
		var elem = document.createElement('div');

		var data = JSON.parse(event.data);
		elem.innerHTML = data.name + ' : ' + data.message;

		chat.appendChild(elem);
		scrollToBottom();
	}

	function sendMsg() {
		var msg = document.getElementById('msg');
		var message = msg.value;
		if (message) {
			ws.send(message);
			msg.value = '';
		}
	}

	function scrollToBottom() {
		chat.scrollTop = chat.scrollHeight;
		saikabu.style.display = 'none';
	}
	scrollToBottom();

	function convertData(data) {
		console.log(data);
		var prevHeight = chat.scrollHeight;
		if (data.size == 0) return;

		for (chatContent of data.chatArray) {
			chat.innerHTML = chatContent.userName + ' : ' + chatContent.message + '<br>' + chat.innerHTML;
		}
		isLoading = false;
		chat.scrollTop = chat.scrollHeight - prevHeight;
	}

	async function getData() {
		await fetch("http://localhost:8080/Calendar/api/getChat?page=" + page + "&roomId=<%= groupId %>", {method: 'POST'})
			.then(res => res.json())
			.then(data => convertData(data));
	}

	chat.addEventListener('scroll', (e) => {
		if((chat.scrollHeight - chat.scrollTop) / chat.scrollHeight >= 0.75 ){
			saikabu.style.display = so;
		} else {
			saikabu.style.display ='none';
		}
		if (isLoading) return;

		if (chat.scrollTop == 0) {
			page += 1;
			isLoading = true;
			getData();
		}
	});

	const closePopup = () => {
		qs('popupBase').classList.remove('open');
		qs('addPopup').classList.remove('open');
	}

	const addGroup = qs('addGroup');
	addGroup.addEventListener('click', function() {
		const popup = qs('popupBase');
		popup.classList.add('open');
		popup.addEventListener('click', closePopup);
		qs('addPopup').classList.add('open');
	});
</script>
</html>