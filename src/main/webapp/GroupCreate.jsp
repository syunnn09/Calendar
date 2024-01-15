<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.time.LocalDateTime" %>
<%@ page import= "java.time.format.DateTimeFormatter" %>
<% 
	LocalDateTime date1 = LocalDateTime.now();
	DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("MM");
	DateTimeFormatter dtformat2 = DateTimeFormatter.ofPattern("dd");
	DateTimeFormatter dtformat3 = DateTimeFormatter.ofPattern("SSSSSSS");
	
	int a = Integer.parseInt(dtformat1.format(date1));
	int b = Integer.parseInt(dtformat2.format(date1));
	int c = a + b;

	String fdate1 = String.valueOf(c);
	fdate1 = fdate1.concat(dtformat3.format(date1));
%>

<!DOCTYPE html>
<html>
<head>
<!-- link rel="stylesheet"href="css/style.css" -->
<meta charset="UTF-8">
<title>グループ作成</title>
</head>

<body>
    <h1>グループ作成</h1>
    
    <div class="box_con">
	<form action="CreatServlet" method="post" name="mail_form">
	<table class "formTable">
	
	
	
	<!-- スクリプト -->
	
	<script type="text/javascript">
	`
	function clickEvent() {
		if(mail_form.roomname.value==""){
			//名前が未入力の場合
			alert("グループ名を入力してください");    //エラーメッセージを出力
                    return false;    //送信ボタン本来の動作をキャンセル
		}else{	
			//エラーがない場合
			return true;//送信される
		}
	}
		`
	</script>
	
		
		<tr>
			<td><input type="hidden" name="roomid" value="<%= fdate1 %>" size="15"></td>
		</tr>
		
		
		
		<tr>
			<th>グループ名<span></span></th>
			<td><input type="text"name="roomname"size="15"placeholder="例:グループ１"></td>
		</tr>
		
		
		</table> 
		<p class="btn" onclick="clickEvent()">
		    <span><input type="submit" value="   作成　"/></span>
		</p>
		</form>
		<!-- <p><a href="javascript:history.back()">一覧に戻る</a> -->

</body>
</html>