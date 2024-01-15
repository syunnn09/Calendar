package servlet.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.Session;

public class ChatSessionManager {
	static ChatSessionManager manager;

	public static ChatSessionManager getManager() {
		if (manager == null) {
			manager = new ChatSessionManager();
		}
		return manager;
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

	public void onMessage(String roomId, Session user, String message) {
		List<Session> session = sessions.get(roomId);
		for (Session userSession : session) {
			String isMine = user == userSession ? "true" : "false";
			userSession.getAsyncRemote().sendText("{\"message\": \"" + message + "\", \"isMine\": " + isMine + "}");
		}
	}
}
