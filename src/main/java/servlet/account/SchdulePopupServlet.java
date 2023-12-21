package servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScheduleDao;

/**
 * Servlet implementation class SchdulePopupServlet
 */
@WebServlet("/SchdulePopupServlet")
public class SchdulePopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int scheduleId = Integer.parseInt(request.getParameter(""));
		
		ScheduleDao scheduleDao = new ScheduleDao();
//		ArrayList<ScheduleRecordBean> dayschedule = scheduleDao.getDaySchedule(scheduleId);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
