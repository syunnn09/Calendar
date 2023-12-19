<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
#request {
	cursor: pointer;
	border: 1px solid #000;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<body>
<input type="text" id="scheduleId" value="1">
<div id="request">request</div>
<div id="data"></div>
</body>
<script>
const parse = (data) => {
	console.log(data);
	text = '';
	text += 'detail: ' + data.detail + '<br>';
	text += 'endDate: ' + data.endDate + '<br>';
	text += 'place: ' + data.place + '<br>';
	text += 'roomId: ' + data.roomId + '<br>';
	text += 'scheduleId: ' + data.scheduleId + '<br>';
	text += 'startDate: ' + data.startDate + '<br>';
	text += 'title: ' + data.title;
	document.getElementById('data').innerHTML = text;
}

const request = () => {
	const scheduleId = document.getElementById('scheduleId').value;
	const url = 'http://localhost:8080/Calendar/api/schedule?scheduleId=' + scheduleId
	fetch(url, {
		'Content-Type': 'application/json; charset=UTF-8'
	})
		.then((data) => data.json())
		.then((res) => parse(res));
	fetch(url)
		.then((res) => res.arrayBuffer())
		.then((text) => console.log(new TextDecoder('utf-8').decode(text)))
}

document.getElementById('request').addEventListener('click', request);
</script>
</html>