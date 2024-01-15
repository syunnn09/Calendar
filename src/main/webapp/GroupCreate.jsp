<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<!-- link rel="stylesheet -->
<meta charset="UTF-8">
<title>グループ作成</title>
</head>
<body>
	<h1>グループ作成</h1>
	<div class="box_con">
		<form action="CreatServlet" method="post" name="mail_form">
			<tableclass "formTable">
	<!-- スクリプト -->
	<script type="text/javascript">
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
	</script>
		<tr>
			<th>グループ名<span></span></th>
			<td><input type="text"name="roomname"size="15"placeholder="例:グループ１"></td>
		</tr>

		</table> 
		<p class="btn" onclick="clickEvent()">
		    <span><input type="submit" value="   作成　"/></span>
		</p>
		</form>
	<p><a href="javascript:history.back()">一覧画面に戻る</a>
</body>
</html>