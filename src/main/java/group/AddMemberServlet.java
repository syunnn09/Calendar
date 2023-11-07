package group;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupInfoBean groupInfoBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int userId=Integer.parseInt(request.getParameter("insertUserIds"));
		
		HttpSession session = request.getSession();
		
		GroupDao gd = new GroupDao();
		gd.insert(userId, roomId);
		
		}catch(NumberFormatException e) {
			
		}

		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/group/addMember.jsp");
		dispatcher.forward(request, response);
		
	}

}
