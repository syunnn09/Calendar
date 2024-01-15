package servlet.schedule;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupInfoBean;
import bean.ScheduleInfoBean;
import dao.GroupDao;
import dao.ScheduleDao;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		String groupId = (String) request.getParameter("groupId");
		System.out.println("groupId: " + groupId);

		ScheduleDao scheduleDao = new ScheduleDao();
		GroupDao groupDao = new GroupDao();
		ScheduleInfoBean infoBean;

		if (groupId != null) {
			infoBean = scheduleDao.getScheduleArray(Integer.parseInt(groupId));
		} else {
			infoBean = scheduleDao.getAll(userId);
		}

		GroupInfoBean bean = groupDao.getAllGroup(userId);
		request.setAttribute("infoBean", infoBean);
		request.setAttribute("groupListBean", bean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/schedule/top.jsp");
		dispatcher.forward(request, response);
	}

}
