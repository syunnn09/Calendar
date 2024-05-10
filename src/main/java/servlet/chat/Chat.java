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

	/**
	 * WebSocket接続がオープンされた際のイベントを処理します。
	 *
	 * @method onOpen
	 * @param roomId WebSocket接続に関連付けられたルームのIDです。
	 * @param curSession オープンされたWebSocketセッションです。
	 * @throws IllegalArgumentException ルームIDがnullの場合
	 * @version 1.0.0
	 */
	@OnOpen
	public void onOpen(@PathParam("roomId") String roomId, Session curSession) {
		String queryString = curSession.getQueryString();
		manager.addSession(roomId, curSession, queryString);
	}

	/**
	 * WebSocket接続がクローズされた際のイベントを処理します。
	 *
	 * @method onClose
	 * @author imamura
	 * @param roomId WebSocket接続に関連付けられたルームのIDです。
	 * @param curSession クローズされたWebSocketセッションです。
	 * @version 1.0.0
	 */
	@OnClose
	public void onClose(@PathParam("roomId") String roomId, Session curSession) {
		manager.removeSession(roomId, curSession);
	}

    /**
     * メッセージを受信した際に呼び出されるメソッドです。
     * 
     * @method onMessage
     * @param roomId メッセージが属するルームのID
     * @param message 受信したメッセージの内容
     * @param userSession メッセージを受信したユーザーのセッション
     * @author imamura
     * @version 1.0.0
     */
	@OnMessage
	public void onMessage(@PathParam("roomId") String roomId, String message, Session userSession) {
		manager.onMessage(roomId, userSession, message);
	}

    /**
     * HTTP GET メソッドを処理し、チャットページを表示します。
     * 
     * @method doGet
     * @param request HTTPリクエスト
     * @param response HTTPレスポンス
     * @author imamura
     * @version 1.0.0
     */
	@OnError
	public void onError(Throwable th) {
		th.printStackTrace();
	}
}
