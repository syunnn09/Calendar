<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple Calendar</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            margin: 20px;
        }

        .calendar {
            width: 100%;
        }

        .calendar th, .calendar td {
            text-align: center;
            padding: 10px;
        }

        .calendar th {
            background-color: #007bff;
            color: #fff;
        }
        
         .calendar td.sun {
        	color: red; /* 日曜日の日にちの文字色を赤に設定 */
    	}

        .calendar td {
            border: 1px solid #ddd;
        }

        .today {
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mt-4 mb-4">Simple Calendar</h2>
        <table class="calendar table table-bordered">
            <thead>
                <tr>
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                </tr>
            </thead>
            <tbody>
			    <%-- カレンダーの表示部分 --%>
			    <%
			        java.util.Calendar cal = java.util.Calendar.getInstance();
			        int month = cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
			        int firstDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
			        int lastDayOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			        int dayCount = 1;
			    %>
			    <h2>month</h2>
			    <%
			        for (int i = 1; i <= 6; i++) {
			    %>
			            <tr>
			                <%
			                    for (int j = 1; j <= 7; j++) {
			                        if (i == 1 && j < firstDayOfWeek) {
			                %>
			                            <td></td>
			                <%
			                        } else if (dayCount <= lastDayOfMonth) {
			                            String cellClass = (j == java.util.Calendar.SUNDAY) ? "sun" : "";
			                %>
			                            <td class="<%= cellClass %>"><%= dayCount %></td>
			                <%
			                            dayCount++;
			                        }
			                    }
			                %>
			            </tr>
			    <%
			        }
			    %>
			</tbody>

        </table>
    </div>
</body>
</html>
