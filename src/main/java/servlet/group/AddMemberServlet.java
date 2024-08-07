package servlet.group;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;

@WebServlet("/AddMemberServlet")
/**
 * addMenberServlet class
 * グループのメンバー追加ができる画面の呼び出しを行う
 * @author　ootubo,fukumori
 * @version　1.0
 */
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int roomId = Integer.parseInt(request.getParameter("roomId"));
		try {
			int[] userIds = Stream.of(request.getParameterValues("insertUserIds")).mapToInt(Integer::parseInt).toArray();

			GroupDao gd = new GroupDao();
			gd.insert(userIds, roomId);

			request.setAttribute("result", gd.notmemSelect(roomId));
			request.setAttribute("roomId", roomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("Group?groupId=" + roomId);

	}

}
