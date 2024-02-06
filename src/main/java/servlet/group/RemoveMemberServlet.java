package servlet.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GroupBean;
import dao.GroupDao;

/**
 * Servlet implementation class RemoveMemberServlet
 */
@WebServlet("/RemoveMemberServlet")
public class RemoveMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int userId = Integer.parseInt(request.getParameter("userId"));

		//新しく追加した部分
		request.setAttribute("roomId", roomId);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", "hello");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/group/removeMember.jsp");
		
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int userId = Integer.parseInt(request.getParameter("userId"));

		GroupBean bean = new GroupBean(roomId, userId);
		GroupDao Dao = new GroupDao();

		Dao.delete(bean);
		response.sendRedirect("Group?groupId=" + roomId);
	}

}
