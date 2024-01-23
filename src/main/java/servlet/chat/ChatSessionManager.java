package servlet.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import bean.ChatBean;
import dao.ChatDao;

public class ChatSessionManager {
	static ChatSessionManager manager;
	LocalDateTime now;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/DD HH:mm:ss");
	ChatDao chatDao = new ChatDao();

	public static ChatSessionManager getManager() {
		if (manager == null) {
			manager = new ChatSessionManager();
		}
		return manager;
	}

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

	HashMap<String, List<Session>> sessions = new HashMap<>();

	public void addSession(String roomId, Session curSession) {
		List<Session> session = sessions.get(roomId);
		if (session == null) {
			List<Session> newSession = new ArrayList<>();
			sessions.put(roomId, newSession);
			session = sessions.get(roomId);
		}
		session.add(curSession);
		sessions.replace(roomId, session);
	}

	public void removeSession(String roomId, Session curSession) {
		List<Session> session = sessions.get(roomId);
		session.remove(curSession);
	}

	public void onMessage(String roomId, Session user, String message, String queryString) {
		Map<String, String> queryMap = getQueryMap(queryString);
		int userId = Integer.parseInt(queryMap.get("userId"));
		message = message.replace("\n", "<br>");

		now = LocalDateTime.now();
		String nowstr = dtf.format(now);
		String userName = chatDao.getUserName(userId);
		int room = Integer.parseInt(roomId);
		ChatBean chat = new ChatBean(room, userId, message, nowstr, userName);
		chatDao.insert(chat);
		List<Session> session = sessions.get(roomId);
		for (Session userSession : session) {
			userSession.getAsyncRemote().sendText("{\"message\": \"" + message + "\", \"name\": \"" + userName + "\"}");
		}
	}

}
