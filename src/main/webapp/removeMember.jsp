<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<
  int userId = (int) request.getAttribute("userId");
  String userName = (String) request.getAttribute("userName");
  >
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
					<th>ユーザID</th><input id="userText" type="text">
						<td><input type="text" value="< userId %>" name="userId" size="20" readonly></td>
					</tr>
					<tr>
					<th>ユーザ名</th>
						<td><input type="text" value="< userName %>" name="userName" size="20" readonly></td>
					</tr>
				</table> 
			<p class="btn">
			    <span><input type="submit" value="   削除　"/></span>
			</p>
			</form>
		<p><a href="javascript:history.back()">一覧画面に戻る</a>

</body>
</html>
-->

<!-- <p class="btn" onclick="clickEvent()">
	<button id="btn">削除</button>
</p>
<script>
	function clickEvent() {
		prompt('本当に削除しますか？');
	 } -->
	 
	 
<!--<p class="btn" onclick="clickEvent()">
<input type="button" value="削除" />
</p>
<script>
    function clickEvent() {
		if(confirm('本当に削除しますか?')){
			alert('削除しました')//trueの処理
			return true;
		}else{
			return false;
		}
    }
</script>  -->
<style>
#click-btn {
  display: block;
  margin: 20px auto;
  background-color: purple;
  color: white;
  border: 0;
  padding: 6px 10px;
}

#popup-wrapper {
  background-color: rgba(0, 0, 0, .5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
}

#popup-inside {
  text-align: center;
  width: 100%;
  max-width: 300px;
  background: white;
  margin: 10% auto;
  padding: 20px;
  position: relative;
}

#message a {
  background: purple;
  color: white;
  text-decoration: none;
  padding: 6px 10px;
}

#close {
  position: absolute;
  top: 0;
  right: 5px;
  cursor: pointer;
}
</style>

<button id="click-btn">削除</button>
<div id="popup-wrapper">
  <div id="popup-inside">
    <div id="close">x</div>
      <div id="message">
      <h2>本当に削除しますか？</h2>
      
      <a href="#">確定</a>
      <a href="#">キャンセル</a>
      </div>
  </div>
</div>

<script>
const clickBtn = document.getElementById('click-btn');
const popupWrapper = document.getElementById('popup-wrapper');
const close = document.getElementById('close');

// ボタンをクリックしたときにポップアップを表示させる
clickBtn.addEventListener('click', () => {
  popupWrapper.style.display = "block";
});

</script>