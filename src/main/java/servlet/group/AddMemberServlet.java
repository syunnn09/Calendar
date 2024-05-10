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
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * javadocClass AddMemberServlet<br>
	 * このメソッドは、HTTP POSTリクエストを処理し、特定の操作を実行します。
	 * リクエストに含まれるパラメータを使用して、グループにユーザーを追加します。
	 * @author otubo
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレット例外が発生した場合
	 * @throws IOException 入出力例外が発生した場合
	 * @version 1.0.0
	 */
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
