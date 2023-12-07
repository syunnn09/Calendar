<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.GroupBean" %>
<%
GroupBean b = (GroupBean)request.getAttribute("bean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認画面</title>
</head>
<body>
<h1>確認</h1>
	    <p>roomId：<%= b.getRoomId()%></p>
		<p>roomName：<%= b.getRoomname()%></p>
		
		

</body>
</html>