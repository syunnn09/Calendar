package servlet.chat;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/echo/{roomId}")
public class Chat {
	static ChatSessionManager manager = ChatSessionManager.getManager();

	@OnOpen
	public void onOpen(@PathParam("roomId") String roomId, Session curSession) {
		String queryString = curSession.getQueryString();
		manager.addSession(roomId, curSession, queryString);
	}

	@OnClose
	public void onClose(@PathParam("roomId") String roomId, Session curSession) {
		manager.removeSession(roomId, curSession);
	}

	@OnMessage
	public void onMessage(@PathParam("roomId") String roomId, String message, Session userSession) {
		manager.onMessage(roomId, userSession, message);
	}

	@OnError
	public void onError(Throwable th) {
		th.printStackTrace();
	}
}
