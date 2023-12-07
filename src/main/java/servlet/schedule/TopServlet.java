package servlet.schedule;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ScheduleInfoBean;
import dao.ScheduleDao;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		int userId = 1;
		ScheduleDao scheduleDao = new ScheduleDao();
		ScheduleInfoBean infoBean = scheduleDao.getAll(userId);
		request.setAttribute("infoBean", infoBean);
		System.out.println(infoBean.getScheduleRecordArray());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/schedule/top.jsp");
		dispatcher.forward(request, response);
	}

}
