package servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;

/**
 * Servlet implementation class ScheduleListServlet
 */
@WebServlet("/ScheduleListServlet")
public class ScheduleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int groupId = Integer.parseInt(request.getParameter("name"));
		HttpSession session = request.getSession();
		int userId = Integer.parseInt((String)session.getAttribute("userId"));
		
		ScheduleDao scheduleDao = new ScheduleDao();
//		ArrayList<ScheduleRecordBean> schedules = scheduleDao.getSchedule(groupId);
		
		request.setAttribute("", );
        request.setAttribute("", );
        request.setAttribute("", );
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/scheduleList.jsp");//jspを入れます
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
