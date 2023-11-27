package group;


import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupInfoBean groupInfoBean;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		try {

			int[] userIds=Stream.of(request.getParameterValues("insertUserIds")).mapToInt(Integer::parseInt).toArray();
			
			HttpSession session = request.getSession();
			
			GroupDao gd = new GroupDao();
			for(int i=0;i<userIds.length;i++) {
				gd.insert(userIds[i], roomId);
			}
			
		}catch(NumberFormatException e) {
			int userId=Integer.parseInt(request.getParameter("insertUserIds"));

			HttpSession session = request.getSession();

			GroupDao gd = new GroupDao();
			gd.insert(userId, roomId);

		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/group/addMember.jsp");
		dispatcher.forward(request, response);

	}

}
