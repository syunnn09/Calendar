package servlet.chat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ChatInfoBean;
import bean.GroupInfoBean;
import dao.ChatDao;
import dao.GroupDao;
import util.CommonUtil;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * HTTP GET メソッドを処理し、チャットページを表示します。
	 * 
	 * @param request HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException Servlet例外が発生した場合
	 * @throws IOException 入出力例外が発生した場合
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.checkLogin(request, response)) {
			return;
		}
		String group = request.getParameter("groupId");
		if (group == null) {
			response.sendRedirect("top");
			return;
		}

		HttpSession session = request.getSession();

		int userId = (int) session.getAttribute("userId");
		int roomId = Integer.parseInt(group);

		if (!CommonUtil.isJoinRoom(request, response, roomId)) {
			response.sendRedirect("top");
			return;
		}

		GroupDao groupDao = new GroupDao();
		ChatDao chatDao = new ChatDao();
		groupDao.setLastsaw(userId, roomId);

		ChatInfoBean chatBean = chatDao.getChat(roomId, 0);
		GroupInfoBean bean = groupDao.getAllGroup(userId);
		request.setAttribute("groupListBean", bean);
		request.setAttribute("chat", chatBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chat/chat.jsp");
		dispatcher.forward(request, response);
	}

}
