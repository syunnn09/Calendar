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
 * Servlet implementation class CreateScheduleServlet
 */
@WebServlet("/CreateScheduleServlet")
public class CreateScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ScheduleRecordBean srb=new ScheduleRecordBean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/schedule/createScheduleServlet.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		srb.setTitle(request.getParameter("title"));
		srb.setRoomId(Integer.parseInt(request.getParameter("roomId")));
		srb.setStartDate(request.getParameter("startDate"));
		srb.setEndDate(request.getParameter("endDate"));
		srb.setDetail(request.getParameter("detail"));
		srb.setPlace(request.getParameter("place"));
		ScheduleDao sd =new ScheduleDao();
		sd.createSchedule(srb);
		doGet(request, response);
	}

}
