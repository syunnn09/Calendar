package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		HttpSession session = request.getSession();
//		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int userId = (int) session.getAttribute("userId");
//		
//		GroupBean bean = new GroupBean(roomId, userId);

//		GroupDao Dao = new GroupDao();
		
//		 Dao.delete(bean);
		
		//新しく追加した部分
		request.setAttribute("userId", userId);
		request.setAttribute("userName", "hello");
		RequestDispatcher dispatcher = request.getRequestDispatcher("RemoveMember.jsp");
		
		dispatcher.forward(request, response);
		
//		response.sendRedirect("CreatServlet");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		
		//int roomId;
		//int userId;
		
		//roomId = Integer.parseInt(request.getParameter("roomid"));
		//userId = Integer.parseInt(request.getParameter("userid"));
		
		//GroupBean bean= new GroupBean(roomId, userId);
		
		//GroupDao Dao= new GroupDao();
		
		//Dao.delete(bean);
		
		//request.setAttribute("bean", bean);
		
		
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		GroupBean bean = new GroupBean(roomId, userId);
		
		GroupDao Dao = new GroupDao();
		
		Dao.delete(bean);
		
		response.sendRedirect("CreatServlet");
		
		//response.getWriter().println("ok");
		//RequestDispatcher dispatcher = request.getRequestDispatcher("");
		//dispatcher.forward(request, response);
	}

}