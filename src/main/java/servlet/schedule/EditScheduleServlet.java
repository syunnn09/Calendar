package servlet.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ScheduleRecordBean;
import dao.ScheduleDao;

/**
 * Servlet implementation class EditScheduleServlet
 */
@WebServlet("/EditScheduleServlet")
public class EditScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ScheduleRecordBean bean = new ScheduleRecordBean();
		bean.setScheduleId(Integer.parseInt(request.getParameter("scheduleId")));
		bean.setTitle(request.getParameter("title"));
		bean.setRoomId(Integer.parseInt(request.getParameter("groupId")));
		bean.setStartDate(request.getParameter("startDate"));
		bean.setEndDate(request.getParameter("endDate"));
		bean.setDetail(request.getParameter("detail"));
		bean.setPlace(request.getParameter("place"));
		ScheduleDao scheduleDao = new ScheduleDao();
		scheduleDao.edit(bean);

		response.sendRedirect("top");
	}

}
