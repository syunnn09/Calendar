package servlet.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ScheduleRecordBean;
import dao.ScheduleDao;
import util.CommonUtil;

/**
 * Servlet implementation class DeleteScheduleServlet
 */
@WebServlet("/DeleteScheduleServlet")
public class DeleteScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		if (!CommonUtil.isJoinRoom(request, response, roomId)) {
			response.sendRedirect("top");
			return;
		}

		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		ScheduleDao scheduleDao = new ScheduleDao();
		ScheduleRecordBean schedule = scheduleDao.getSchedule(scheduleId);
		scheduleDao.delete(schedule);

		response.sendRedirect("top");
	}

}
