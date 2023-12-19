package servlet.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;

/**
 * Servlet implementation class kari
 */
@WebServlet("/Group")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/group/group.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		GroupDao gd = new GroupDao();
		String value = request.getParameter("button");
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		request.setAttribute("roomId", roomId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/group/group.jsp");
		if (value.equals("admin")) {
			request.setAttribute("result", gd.adminselect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addAdmin.jsp");
		} else if (value.equals("member")) {
			request.setAttribute("result", gd.memberselect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addMember.jsp");
		}
		dispatcher.forward(request, response);
	}

}
