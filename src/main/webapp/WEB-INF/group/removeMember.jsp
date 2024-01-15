<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  int userId = (int) request.getAttribute("userId");
  String userName = (String) request.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>下記のユーザを削除しますか？</h1>
		<div class="box_con">
			<form action="RemoveMemberServlet" method="post" name="mail_form">
				<tableclass "formTable">
					<tr>
					<th>ユーザID</th>
						<td><input type="text" value="<%= userId %>" name="userId" size="20" readonly></td>
					</tr>
					<tr>
					<th>ユーザ名</th>
						<td><input type="text" value="<%= userName %>" name="userName" size="20" readonly></td>
					</tr>
				</table> 
			<p class="btn">
			    <span><input type="submit" value="   削除　"/></span>
			</p>
			</form>
		<p><a href="javascript:history.back()">一覧画面に戻る</a>

</body>
</html>