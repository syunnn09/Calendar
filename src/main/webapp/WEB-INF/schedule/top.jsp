<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.ScheduleInfoBean, bean.ScheduleRecordBean, java.util.ArrayList, bean.GroupInfoBean, bean.GroupBean" %>
<%
	String currentGroupId = request.getParameter("groupId");
	int groupId = currentGroupId != null ? Integer.parseInt(currentGroupId) : 0;
	ScheduleInfoBean infoBean = (ScheduleInfoBean) request.getAttribute("infoBean");
	ArrayList<ScheduleRecordBean> record = infoBean.getScheduleRecordArray();
	GroupInfoBean groupListBean = (GroupInfoBean) request.getAttribute("groupListBean");
	GroupBean currentGroup = groupListBean.get(groupId);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<% if (currentGroup != null) { %>
	<title>カレンダー - <%= currentGroup.getRoomname() %></title>
	<% } else { %>
		<title>カレンダー - マイページ</title>
	<% } %>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
table {
	border-collapse: collapse;
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
	border: 1px solid #000;
	overflow: hidden;
	line-height: 60px;
	text-align: center;
	font-size: 11px;
	text-decoration: none;
	color: #00f;
	cursor: pointer;
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
.headerItemText2 {
	color: #000;
	text-decoration: none;
	font-size:25px;
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
	/* background-color: #faf; */
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
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	flex-direction: column;
	gap: 1px;
}
.daySpan {
	font-size: 13px;
	opacity: 0.5;
	cursor: pointer;
	user-select: none;
}
.daySpan:hover {
	opacity: 1;
}
.schedule {
	background-color: #eee;
	display: block;
	text-align: center;
	border-left: 5px solid var(--color);
	width: 100%;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
#popupBase {
	position: absolute;
	top: 0;
	right: 0;
	width: 100vw;
	height: 100vh;
	background-color: #6663;
	display: none;
	z-index: 9999;
}
.open {
	display: block !important;
}
.closePopup {
	position: absolute;
	top: 0;
	right: 0;
	width: 2rem;
	height: 2rem;
	cursor: pointer;
}
.popup {
	display: none;
	background-color: #fff;
	position: absolute;
	top: calc(50% - 150px);
	left: calc(50% - 230px);
	width: 460px;
	height: 200px;
	z-index: 10000;
	border-radius: 5px;
}
.popupInner {
	position: absolute;
	top: 2rem;
	left: 2rem;
}
button {
	border: 1px solid #000;
	border-radius: 5px;
	padding: 0 10px;
	background-color: #fff;
	cursor: pointer;
}
.addPopupHeader {
	position: absolute;
	top: 1rem;
	left: 0;
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
}
.addPopupInner {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
.addPopupLeft {
	padding-right: 1rem;
}
</style>

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
					<a href="?groupId=<%= group.getRoomId() %>" class="groupItem<%= group.getRoomId() == groupId ? " current" : "" %>" style="border-color: <%= group.getColor() %>">
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
					<% if (groupId != 0) { %>
						<div class="headerItem">
							<a href="chat?groupId=<%= groupId %>" class="headerItemText">チャット</a>
						</div>
					<% } %>
				</div>
				<div>
					<% if (groupId != 0) { %>
						<a href="Group?groupId=<%= groupId %>" class="headerItemText">&#x2699;</a>
					<% } %>
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
	<div id="popupBase"></div>
	<div id="popupMain" class="popup">
		<button onclick="closePopup()" class="closePopup">x</button>
		<div class="popupInner">
			<form action="CreateScheduleServlet" method="post" name="createScheduleForm">
				<table align="center">
					<tr>
						<td class="addPopupLeft">タイトル</td>
						<td><input type="text" name="title" required></td>
					</tr>
					<tr>
						<td class="addPopupLeft">グループ</td>
						<td>
							<select name="groupId">
								<% for (GroupBean group: groupListBean.getGroupArray()) { %>
									<option value="<%= group.getRoomId() %>"<%= group.getRoomId() == groupId ? " selected" : "" %>><%= group.getRoomname() %></option>
								<% } %>
							</select>
						</td>
					</tr>
					<tr>
						<td class="addPopupLeft">日時</td>
						<td>
							<input type="date" name="startDate" required>
							～
							<input type="date" name="endDate" required>
						</td>
					</tr>
					<tr>
						<td class="addPopupLeft">詳細</td>
						<td><textarea name="detail"></textarea></td>
					</tr>
					<tr>
						<td class="addPopupLeft">場所</td>
						<td><input type="textarea" name="place"></td>
					</tr>
				</table>
				<input type="submit"value="作成">
			</form>
		</div>
	</div>	
	<div id="popupDetail" class="popup">
		<button onclick="closePopup()" class="closePopup">x</button>
		<div class="popupInner">
			<table align="center">
				<tr>
					<td class="addPopupLeft">タイトル</td>
					<td><div id = "popuptitle"></div>
					</td>
				</tr>
				<tr>
					<td class="addPopupLeft">グループ</td>
					<td><div id = "popuproomId"></div></td>
				</tr>
				<tr>
					<td class="addPopupLeft">日時</td>
					<td><span id = "popupstartDate"></span>　～　<span id = "popupendDate"></span></td>
				</tr>
				<tr>
					<td class="addPopupLeft">詳細</td>
					<td><div id = "popupdetail"></div></td>
				</tr>
				<tr>
					<td class="addPopupLeft">場所</td>
					<td><div id = "popupplace"></div></td>
				</tr>
			</table>
		</div>
	</div>
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
							<td><input type="text" name="roomname" required></td>
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
	<button onclick="openPopup()">オープン</button>
</body>

<script>
const qs = (q) => document.getElementById(q);
const ce = (q) => document.createElement(q);
const calendar = qs('calendar');
var detail = qs('detail');

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
			daySpan.innerHTML = count;
			if (count > lastDay) {
				overCount += 1;
				el.innerHTML = '';
			}
			if (currentYear == year && currentMonth == month && count == day) {
				el.classList.add('today');
			}
			let datam = getData(count);
			for (let d of datam) {
				let scheduleEl = ce('div');
				scheduleEl.classList.add('schedule');
				scheduleEl.innerHTML = d.title;
				scheduleEl.dataset.scheduleId = d.scheduleId;
				scheduleEl.style.setProperty('--color', d.color);
				scheduleEl.addEventListener('click', () => getDetailData(d.scheduleId));
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
	Array.from(document.getElementsByClassName('daySpan')).forEach(el => {
		el.addEventListener('click', () => {
			openPopup(el.innerHTML);
		});
	});
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

async function getDetailData(scheduleId) {
	await fetch("http://localhost:8080/Calendar/api/schedule?scheduleId=" + scheduleId)
		.then(res => res.json())
		.then(data => openDetail(data));	
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
	var data_json = { scheduleId: <%= bean.getScheduleId() %>, title: '<%= bean.getTitle() %>', color: '<%= bean.getColor() %>' };
	data[startYear][startMonth][startDay].push(data_json);
<% } %>

const days = ['日', '月', '火', '水', '木', '金', '土'];
const table = ce('table');
table.classList.add('table');
table.border = 1;
const tr = ce('tr');

for (d of days) {
	let el = ce('th');
	el.innerHTML = d;
	 if (d === '日') {
		 el.classList.add('sunday');
		 } else if(d ==='土') {
		 el.classList.add('suturday');
	}	
	tr.appendChild(el);
}
table.appendChild(tr)

const openPopup = (day) => {
	const popup = qs('popupBase');
	qs('popupMain').classList.add('open');
	popup.classList.add('open');
	popup.addEventListener('click', closePopup);
	if (day) {
		const dispMonth = ('0' + (month + 1)).slice(-2);
		const dispDay = ('0' + day).slice(-2);
		const dispYMD = '' + year + '-' + dispMonth + '-' + dispDay;
		document.createScheduleForm.startDate.value = dispYMD;
		document.createScheduleForm.endDate.value = dispYMD;
	} else {
		document.createScheduleForm.startDate.value = null;
		document.createScheduleForm.endDate.value = null;
	}
}

const openDetail = (schedule) => {
	const popup = qs('popupBase');
	qs('popupDetail').classList.add('open');
	popup.classList.add('open');
	popup.addEventListener('click', closePopup);

	qs('popuptitle').innerHTML = schedule.title;
	qs('popuproomId').innerHTML = schedule.roomId;
	qs('popupstartDate').innerHTML = schedule.startDate;
	qs('popupendDate').innerHTML = schedule.endDate;
	qs('popupdetail').innerHTML = schedule.detail;
	qs('popupplace').innerHTML = schedule.place;
}

const closePopup = () => {
	const popup = qs('popupBase');
	qs('popupMain').classList.remove('open');
	qs('popupDetail').classList.remove('open');
	qs('addPopup').classList.remove('open');
	popup.classList.remove('open');
}

const addGroup = qs('addGroup');
addGroup.addEventListener('click', function() {
	const popup = qs('popupBase');
	popup.classList.add('open');
	popup.addEventListener('click', closePopup);
	qs('addPopup').classList.add('open');
});

getCalendar();

</script>
</html>