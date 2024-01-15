<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.GroupInfoBean"%>
<%@ page import="bean.GroupBean"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/group.css">
<title>メンバ追加</title>
</head>
<body>
	<form action="AddMemberServlet" method="post">
		<%
		try {
			GroupInfoBean gib = (GroupInfoBean) request.getAttribute("result");
			ArrayList<GroupBean> groupArray = gib.getGroupArray();
			int roomId = groupArray.get(0).getRoomId();
		%>
		ルームID:<%=roomId%><br> <input type="hidden" name="roomId" value="<%=roomId%>">
		<table align="center">
			<%
			for (int i = 0; i < gib.getArraySize(); i++) {
			%>
			<tr class="body">
				<td><input type="checkbox" name="insertUserIds"
					value="<%=groupArray.get(i).getUserId()%>"></td>
				<td><%=groupArray.get(i).getUserId()%></td>
				<td><%=groupArray.get(i).getUserName()%></td>
				<td><%=groupArray.get(i).getEmail()%></td>
			</tr>
			<%
			}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("データないお");
			}
			%>
		</table>
			<button type="submit">追加</button>
	</form>

</body>
</html>