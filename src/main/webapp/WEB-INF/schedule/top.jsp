<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.ScheduleInfoBean, bean.ScheduleRecordBean, java.util.ArrayList, bean.GroupInfoBean, bean.GroupBean" %>
<%
	String currentGroupId = request.getParameter("groupId");
	int groupId = currentGroupId != null ? Integer.parseInt(currentGroupId) : 0;
	ScheduleInfoBean infoBean = (ScheduleInfoBean) request.getAttribute("infoBean");
	ArrayList<ScheduleRecordBean> record = infoBean.getScheduleRecordArray();
	GroupInfoBean groupListBean = (GroupInfoBean) request.getAttribute("groupListBean");
%>
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
.container {
	display: flex;
	gap: 1rem;
}
.groupContainer {
	height: 90vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
.groupItems {
	height: 100%;
	padding: 0 5px;
	border: 1px solid #000;
	overflow-y: scroll;
	overflow-x: hidden;
}
.groupItems::-webkit-scrollbar {
	display: none;
}
.groupItem {
	display: block;
	width: 60px;
	height: 60px;
	margin-top: 0.5rem;
	border-radius: 50%;
	border: 1px solid #faf;
	overflow: hidden;
	line-height: 60px;
	text-align: center;
	font-size: 11px;
	text-decoration: none;
}
.groupItem.current {
	border-color: #f00;
}
.groupItem.plus {
	text-align: center;
}
a.groupItem:hover {
	text-decoration: underline;
}
.headerItems {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border: 1px solid #000;
	padding-right: 1rem;
	margin-bottom: 1rem;
}
.headerLeft {
	display: flex;
}
.headerItem {
	padding: 0.3rem 1rem;
	border-right: 1px solid #000;
}
.headerItemText {
	color: #000;
	text-decoration: none;
}
.headerItemText:hover {
	text-decoration: underline;
}
.header {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 1rem;
	margin-bottom: 1rem;
}
.today {
	background-color: #faf;
}
.calendarContainer {
	display: flex;
	align-items: center;
	justify-content: center;
}
.table {
	border-collapse: collapse;
}
.dayContainer {
	width: calc(90vw / 7);
	height: 6rem;
}
.day {
	height: 100%;
	display: flex;
	flex-direction: column;
}
.daySpan {
	font-size: 13px;
	opacity: 0.6;
}
</style>

<body>
	<div class="container">
		<div class="groupContainer">
			<div class="groupItems">
				<form action="" method="POST">
					<div class="groupItem plus">+</div>
				</form>
				<a href="/Calendar/" class="groupItem<%= groupId == 0 ? " current" : "" %>">
					<p>マイページ</p>
				</a>
				<% for (GroupBean group: groupListBean.getGroupArray()) { %>
					<a href="?groupId=<%= group.getRoomId() %>" class="groupItem<%= group.getRoomId() == groupId ? " current" : "" %>">
						<p title="<%= group.getRoomname() %>"><%= group.getRoomname() %></p>
					</a>
				<% } %>
			</div>
		</div>
		<div class="calendarMain">
			<div class="headerItems">
				<div class="headerLeft">
					<div class="headerItem">
						<a href="" class="headerItemText">カレンダー</a>
					</div>
					<div class="headerItem">
						<a href="chat" class="headerItemText">チャット</a>
					</div>
				</div>
				<div>
					<a href="GroupManagement" class="headerItemText">&#x2699;</a>
				</div>
			</div>
			<div class="header">
			    <div id="now">
					<button onclick="now()">今月</button>
				</div>
				<div id="prev">
					<button onclick="prev()">先月</button>
				</div>
				<div id="date"></div>
				<div id="next">
					<button onclick="next()">翌月</button>
				</div>
			</div>
			<div class="calendarContainer">
				<div id="calendar"></div>
			</div>
		</div>
	</div>
</body>

<script>
const qs = (q) => document.getElementById(q);
const ce = (q) => document.createElement(q);
const calendar = qs('calendar');

let calendarElements = [];

const date = new Date();
const currentYear = date.getFullYear();
const currentMonth = date.getMonth();

let year = date.getFullYear();
let month = date.getMonth();
const day = date.getDate();

console.log(year, month+1, day);

const getData = (count) => {
	if (!data[year]) {
		return []
	}
	if (!data[year][month+1]) {
		return []
	}
	if (!data[year][month+1][count]) {
		return []
	}
	return data[year][month+1][count];
}

const getCalendar = () => {
	calendarElements.forEach(e => e.remove());
	calendarElements = [];

	const startDay = new Date(year, month, 1).getDay();
	const daysInMonth = new Date(year, month+1, 0).getDate();
	const lastDay = new Date(year, month+1, -1).getDate() + 1;

	const totalDays = daysInMonth + startDay;
	const totalWeeks = Math.ceil(totalDays / 7);

	let count = -1;
	let overCount = 0;
	let isCompleted = false;

	for (let i = 0; i < totalWeeks; i++) {
		let tr = ce('tr');
		for (let j = 0; j < 7; j++) {
			let el = ce('th');
			el.classList.add('dayContainer');
			let dayEl = ce('div');
			dayEl.classList.add('day');
			let daySpan = ce('span');
			daySpan.classList.add('daySpan');
			count += 1;
			daySpan.innerHTML = count;
			dayEl.appendChild(daySpan);
			el.appendChild(dayEl);
			if (!isCompleted) {
				if (count < startDay) {
					el.innerHTML = '';
				} else {
					count = 1;
					isCompleted = true;
				}
			}
			if (count > lastDay) {
				overCount += 1;
				el.innerHTML = '';
				if (overCount == 1) {
					// el.innerHTML = (month + 2) + '/' + overCount;
				} else {
					// el.innerHTML = overCount;
				}
			}
			if (currentYear == year && currentMonth == month && count == day) {
				el.classList.add('today');
			}
			let datam = getData(count);
			for (let d of datam) {
				let scheduleEl = ce('div');
				scheduleEl.classList.add('schedule');
				scheduleEl.innerHTML = d;
				dayEl.appendChild(scheduleEl);
				calendarElements.push(scheduleEl);
			}
			tr.appendChild(el);
			calendarElements.push(el);
		}
		table.appendChild(tr);
		calendarElements.push(tr);
	}

	calendar.appendChild(table);
	qs('date').innerHTML = year + '年 ' + (month+1) + '月';
}

const now = () => {
	year = currentYear;
	month = currentMonth;
	getCalendar();
}

const prev = () => {
	month -= 1;
	if (month == -1) {
		month = 11;
		year -= 1;
	}
	getCalendar();
}

const next = () => {
	month += 1;
	if (month == 12) {
		month = 0;
		year += 1;
	}
	getCalendar();
}

let data = {}
<% for (ScheduleRecordBean bean: record) { %>
	var startDate = '<%= bean.getStartDate() %>'.split('-');
	var startYear = startDate[0];
	var startMonth = parseInt(startDate[1]);
	var startDay = parseInt(startDate[2]);
	if (!data[startYear]) {
		data[startYear] = {};
	}
	if (!data[startYear][startMonth]) {
		data[startYear][startMonth] = {};
	}
	if (!data[startYear][startMonth][startDay]) {
		data[startYear][startMonth][startDay] = [];
	}
	data[startYear][startMonth][startDay].push('<%= bean.getTitle() %>');
<% } %>

const days = ['日', '月', '火', '水', '木', '金', '土'];
const table = ce('table');
table.classList.add('table');
table.border = 1;
const tr = ce('tr');

for (d of days) {
	let el = ce('th');
	el.innerHTML = d;
	tr.appendChild(el);
}
table.appendChild(tr)

getCalendar();

</script>
</html>