<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.GroupInfoBean"%>
<%@ page import="bean.GroupBean"%>
<%@ page import="java.util.ArrayList"%>
<%
	GroupInfoBean gib = (GroupInfoBean) request.getAttribute("result");
	int roomId = (int) request.getAttribute("roomId");
	int admin = (int) request.getAttribute("admin");
	ArrayList<GroupBean> groupArray = gib.getGroupArray();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループ管理</title>
<link rel="stylesheet" href="./css/group.css">
</head>
<body>
	<!-- メンバ追加、管理者変更 -->
	<form action="Group" method="post">
		ルームID:<%=roomId%><br> <input type="hidden" name="groupId" value="<%=roomId%>">
		<button type="submit" value="addmember" name="button">メンバ追加</button>
		<button type="submit" value="admin" name="button" <% if(admin==0){ %>disabled<% } %>>管理者変更</button>
	</form>
	<!-- メンバ削除 -->
	<form action="RemoveMemberServlet" name="removeForm" method="post">
		<input type="hidden" name="roomId" value="<%=roomId%>">
		<table align="center">
			<tr>
				<td class="head">名前</td><td class="head">メールアドレス</td><td class="head">削除</td>
			</tr>
			<% for (int i = 0; i < gib.getArraySize(); i++) { %>
				<tr class="body">
					<td><%=groupArray.get(i).getUserName()%>　</td>
					<td><%=groupArray.get(i).getEmail()%>　</td>
					<td><button type="button" value="<%= groupArray.get(i).getUserId()%>" <% if(admin==0){ %>disabled<% } %> onclick="onClickRemoveBtn(<%= i %>)">削除</button></td>
				</tr>
			<% } %>
		</table>
		<div id="popup-wrapper">
			<div id="popup-inside">
				<div id="close" onclick="clickFalse()">x</div>
				<div id="message">
					<h2>本当に削除しますか？</h2>
					<p id="userName"></p>
					<div>
						<button type="submit" name="userId" <% if(admin==0){ %>disabled<% } %>>確定</button>
						<button type="button" onclick="clickFalse()">キャンセル</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<!-- ここからは追加機能 -->
	<script>
		var users = [];
		<% for (GroupBean group : groupArray) { %>
			users.push({ name: "<%= group.getUserName() %>", userId: "<%= group.getUserId() %>" });
		<% } %>
		const clickBtn = document.getElementById('click-ans');
		const popupWrapper = document.getElementById('popup-wrapper');
		const close = document.getElementById('close');

		/* clickBtn.addEventListener('click', () => {
	 		popupWrapper.style.display = "block";
		}); */
		function clickFalse() {
			popupWrapper.style.display = 'none';
		}
		function onClickRemoveBtn(index) {
			const userNameArea = document.getElementById('userName');
			const target = users[index];
			userNameArea.innerHTML = target.name;
			popupWrapper.style.display = "block";
			document.forms.removeForm.userId.value = target.userId;
		}
	</script>
	
	<style>

	#popup-wrapper {
  	background-color: rgba(0, 0, 0, .5);
  	position: fixed;
  	top: 0;
  	left: 0;
  	width: 100%;
  	height: 100%;
  	display: none;
	}

	#popup-inside {
  	text-align: center;
  	width: 100%;
  	max-width: 300px;
  	background: white;
  	margin: 10% auto;
  	padding: 20px;
  	position: relative;
	}

	#message a {
  	background: purple;
  	color: white;
  	text-decoration: none;
  	padding: 6px 10px;
	}

	#close {
  	position: absolute;
  	top: 0;
  	right: 5px;
  	cursor: pointer;
	}
	</style>
</body>
</html>