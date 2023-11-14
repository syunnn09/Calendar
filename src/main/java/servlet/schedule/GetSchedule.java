package servlet.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.ScheduleJsonBean;
import bean.ScheduleRecordBean;
import dao.ScheduleDao;

/**
 * Servlet implementation class GetSchedule
 */
// Ex) http://localhost:8080/Calendar/api/schedule?scheduleId=2
@WebServlet("/api/schedule")
public class GetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

		ScheduleDao dao = new ScheduleDao();
		ObjectMapper mapper = new ObjectMapper();

		ScheduleRecordBean recordBean = dao.getSchedule(scheduleId);
		ScheduleJsonBean bean = new ScheduleJsonBean(recordBean);

		try {
			String json = mapper.writeValueAsString(bean);
			response.getWriter().write(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
