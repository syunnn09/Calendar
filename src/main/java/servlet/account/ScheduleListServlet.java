package servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScheduleListServlet
 */
@WebServlet("/ScheduleListServlet")
public class ScheduleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ScheduleListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String groupId = request.getParameter("name");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("name");
		
		// getSchedule(roomid);
	}

}
