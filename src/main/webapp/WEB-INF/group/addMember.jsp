<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%
ResultSet rs = (ResultSet)request.getAttribute("result");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"href="./css/group.css">
<title>メンバ追加</title>
</head>
<body>
	<form action="AddMemberServlet"method="post">
       	ルームID:<input type="text"name="roomId"value=1><br>
        <table align="center">
        <%
        while(rs.next()) {
        %>
        <tr>
        	<td><input type="checkbox" name="insertUserIds"value="<%= rs.getString("userId")%>"></td>
        	<td><%= rs.getString("userId")%></td>
        	<td><%= rs.getString("name")%></td>
        	<td><%= rs.getString("email")%></td>
        </tr>
        <%
        }
        %>
        </table>
        <input type="submit"value="追加">
    </form>

</body>
</html>