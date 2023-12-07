<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.ScheduleInfoBean, bean.ScheduleRecordBean, java.util.ArrayList" %>
<%
	ScheduleInfoBean infoBean = (ScheduleInfoBean) request.getAttribute("infoBean");
	ArrayList<ScheduleRecordBean> record = infoBean.getScheduleRecordArray();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<% if (record == null) { %>
		<p>登録されていません</p>
	<% } else { %>
		<% for (ScheduleRecordBean bean: record) { %>
			<p><%= bean.getTitle() %></p>
		<% } %>
	<% } %>
</body>
</html>