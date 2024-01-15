package servlet.chat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupInfoBean;
import dao.GroupDao;
import util.CommonUtil;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.checkLogin(request, response)) {
			return;
		}

		HttpSession session = request.getSession();
		GroupDao groupDao = new GroupDao();

		int userId = (int) session.getAttribute("userId");
		GroupInfoBean bean = groupDao.getAllGroup(userId);
		request.setAttribute("groupListBean", bean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chat/chat.jsp");
		dispatcher.forward(request, response);
	}

}
