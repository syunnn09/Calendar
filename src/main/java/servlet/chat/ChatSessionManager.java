package servlet.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import bean.ChatBean;
import bean.ChatSessionBean;
import dao.ChatDao;
import dao.GroupDao;
import util.Consts;

public class ChatSessionManager {
	static ChatSessionManager manager;
	LocalDateTime now;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	ChatDao chatDao = new ChatDao();
	GroupDao groupDao = new GroupDao();
	private final String SYSTEM_USER_NAME = "SYSTEM";

    /**
     * ChatSessionManager のインスタンスを取得します。
     * 
     * @method getManager
     * @return ChatSessionManager のインスタンス
     * @author imamura
     * @version 1.0.0
     */
	public static ChatSessionManager getManager() {
		if (manager == null) {
			manager = new ChatSessionManager();
		}
		return manager;
	}

    /**
     * クエリ文字列からパラメータを抽出し、マップとして返します。
     * 
     * @method getQueryMap
     * @param query クエリ文字列
     * @return パラメータが格納されたマップ。クエリが null の場合は null を返します。
     * @author imamura
     * @version 1.0.0
     */
	public static Map<String, String> getQueryMap(String query) {
		if (query == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();
		String[] params = query.split("&");
		for (String param : params) {
			String[] values = param.split("=");
			map.put(values[0], values[1]);
		}
		return map;
	}

	HashMap<String, List<ChatSessionBean>> sessions = new HashMap<>();

    /**
     * セッションを追加します。
     * 
     * @method addSession
     * @param roomId ルームID
     * @param curSession 現在のセッション
     * @param queryString クエリ文字列
     * @author imamura
     * @version 1.0.0
     */
	public void addSession(String roomId, Session curSession, String queryString) {
		List<ChatSessionBean> session = sessions.get(roomId);
		if (session == null) {
			List<ChatSessionBean> newSession = new ArrayList<>();
			sessions.put(roomId, newSession);
			session = sessions.get(roomId);
		}
		Map<String, String> queryMap = getQueryMap(queryString);
		int userId = Integer.parseInt(queryMap.get("userId"));
		String userName = chatDao.getUserName(userId);
		ChatSessionBean chatSessionBean = new ChatSessionBean(curSession, userId, userName);
		session.add(chatSessionBean);
		sessions.replace(roomId, session);
	}

	/**
     * 指定されたルームとセッションに対応するチャットセッションを取得します。
     * 
     * @method getUserSession
     * @param roomId ルームID
     * @param session セッション
     * @return ChatSessionBean 指定されたルームとセッションに対応するチャットセッション。見つからない場合は null を返します。
     * @author imamura
     * @version 1.0.0
     */
	private ChatSessionBean getUserSession(String roomId, Session session) {
		List<ChatSessionBean> list = sessions.get(roomId);
		for (ChatSessionBean bean : list) {
			if (bean.getSession() == session) {
				return bean;
			}
		}
		return null;
	}

	/**
     * メッセージを含む HashMap を JSON 形式の文字列に変換します。
     * 
     * @method getJson
     * @param messages メッセージを含む HashMap
     * @return String JSON 形式の文字列。メッセージが空の場合は空の JSON オブジェクト "{}" を返します。
     * @author imamura
     * @version 1.0.0
     */
	private String getJson(HashMap<String, String> messages) {
		String text = "{";
		int count = 0;
		for (Map.Entry<String, String> entry : messages.entrySet()) {
			text += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\"";
			count += 1;
			if (count != messages.size()) {
				text += ", ";
			}
		}
		text += "}";
		return text;
	}

	/**
     * 指定されたルームとセッションに対応するユーザーセッションを削除します。
     * 
     * @method removeSession
     * @param roomId ルームID
     * @param curSession 現在のセッション
     * @throws NullPointerException 指定されたセッションが存在しない場合
     * @author imamura
     * @version 1.0.0
     */
	public void removeSession(String roomId, Session curSession) {
		ChatSessionBean userSession = getUserSession(roomId, curSession);
		List<ChatSessionBean> session = sessions.get(roomId);
		session.remove(userSession);
		sessions.replace(roomId, session);
	}

	/**
     * チャットを送信します。
     * 
     * @method sendMessage
     * @param chat 送信するチャット情報を含む ChatBean オブジェクト
     * @author imamura
     * @version 1.0.0
     */
	public void sendMessage(ChatBean chat) {
		now = LocalDateTime.now();
		String nowstr = dtf.format(now);
		chat.setTimestamp(nowstr);
		chatDao.insert(chat);

		String message = chat.getMessage();
		int roomId = chat.getRoomId();
		List<ChatSessionBean> session = sessions.get(String.valueOf(chat.getRoomId()));
		if (session == null) return;

		String userName = chat.getUserName();
		HashMap<String, String> messages = new HashMap<>() {
			{
				put("message", message);
				put("userName", userName);
				put("timestamp", nowstr);
				put("systemUser", chat.getUserId() == Consts.SYSTEM_USER_ID ? "true" : "false");
			}
		};
		String sendMessage = getJson(messages);
		for (ChatSessionBean userSession : session) {
			groupDao.setLastsaw(userSession.getUserId(), roomId);
			userSession.getSession().getAsyncRemote().sendText(sendMessage);
		}
	}

	/**
     * メッセージを受信した際に呼び出されるメソッドです。
     * 
     * @method onMessage
     * @param roomId メッセージが属するルームのID
     * @param user メッセージを送信したユーザーのセッション
     * @param message 受信したメッセージの内容
     * @author imamura
     * @version 1.0.0
     */
	public void onMessage(String roomId, Session user, String message) {
		message = message.replace("\n", "<br>");
		int room = Integer.parseInt(roomId);
		ChatSessionBean session = getUserSession(String.valueOf(room), user);
		ChatBean chat = new ChatBean(room, session.getUserId(), message);
		chat.setUserName(session.getUserName());
		this.sendMessage(chat);
	}

	/**
     * システムメッセージを送信します。
     * 
     * @method sendSystemMessage
     * @param roomId メッセージを送信するルームのID
     * @param message 送信するメッセージの内容
     */
	public void sendSystemMessage(int roomId, String message) {
		ChatBean chat = new ChatBean();
		chat.setRoomId(roomId);
		chat.setUserId(Consts.SYSTEM_USER_ID);
		chat.setUserName(SYSTEM_USER_NAME);
		chat.setMessage(message);
		this.sendMessage(chat);
	}

}
