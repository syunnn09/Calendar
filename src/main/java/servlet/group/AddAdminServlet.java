package servlet.group;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;

@WebServlet("/AddAdminServlet")
/**
 * addAdminServelt class
 * グループの管理者を変更する画面の呼び出しを行う
 * @author　fukumori
 * @version　1.0
 */
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		try {
			int[] userIds = Stream.of(request.getParameterValues("insertUserIds")).mapToInt(Integer::parseInt).toArray();

			GroupDao gd = new GroupDao();
			gd.grant(userIds, roomId);

			request.setAttribute("result", gd.yesmemSelect(roomId));
			request.setAttribute("roomId", roomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("Group?groupId=" + roomId);
	}
}
