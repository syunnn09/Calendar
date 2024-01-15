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
@WebServlet("/GroupManagement")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomId=1;
		int userId=1;
		GroupDao gd =new GroupDao();
		request.setAttribute("result", gd.yesmemSelect(roomId));
		request.setAttribute("roomId", roomId);
		request.setAttribute("admin", gd.adminCheck(userId,roomId));		
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
			request.setAttribute("result", gd.yesmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addAdmin.jsp");
		} else if (value.equals("addmember")) {
			request.setAttribute("result", gd.notmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addMember.jsp");
		} else if (value.equals("delmember")) {
			request.setAttribute("result", gd.yesmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/r.jsp");			
		}
		dispatcher.forward(request, response);
	}

}
