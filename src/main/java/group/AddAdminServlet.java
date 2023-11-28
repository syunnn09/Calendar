package group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupInfoBean groupInfoBean; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			int roomId = Integer.parseInt(request.getParameter("roomId"));
			int userId=Integer.parseInt(request.getParameter("insertUserIds"));

			HttpSession session = request.getSession();

			GroupDao gd = new GroupDao();
			gd.grant(userId, roomId);

		}catch(NumberFormatException e) {

		}

		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/group/addAdmin.jsp");
		dispatcher.forward(request, response);
	}
}

//String[] userIds=request.getParameterValues("insertUserIds");
//int[] userIds=Stream.of(a).mapToInt(Integer::parseInt).toArray();
//for(int i=0;i<userId.length;i++) {
//	System.out.println(userId[i]);
//}