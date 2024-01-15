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
	<form action="Group" method="post">
		ルームID:<%=roomId%><br> <input type="hidden" name="roomId" value="<%=roomId%>">
		<button type="submit" value="addmember" name="button">メンバ追加</button>
		<button type="submit" value="admin" name="button" <% if(admin==0){ %>disabled<% } %>>管理者変更</button>
	</form>
	<form action="RemoveMemberServlet" method="get">
		<input type="hidden" name="roomId" value="<%=roomId%>">
		<% try { %>
		<table align="center">
			<tr>
				<td class="head">名前</td><td class="head">メールアドレス</td><td class="head">削除</td>
			</tr>
			<% for (int i = 0; i < gib.getArraySize(); i++) { %>
			<tr class="body">
				<td><%=groupArray.get(i).getUserName()%>　</td>
				<td><%=groupArray.get(i).getEmail()%>　</td>
				<td>
					<button type="submit" value="<%= groupArray.get(i).getUserId()%>" name="userId" <% if(admin==0){ %>disabled<% } %>>削除</button>
				</td>
			</tr>
		<%
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("データないお");
		}
		%>
		</table>
	</form>
</body>
</html>