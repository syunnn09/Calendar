package servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScheduleDao;

/**
 * Servlet implementation class DeleteScheduleServlet
 */
@WebServlet("/DeleteScheduleServlet")
public class DeleteScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int scheduleId = Integer.parseInt(request.getParameter("name"));
		
		ScheduleDao scheduleDao = new ScheduleDao();
//		int count = scheduleDao.delete(scheduleId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
