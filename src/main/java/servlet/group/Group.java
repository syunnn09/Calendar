package servlet.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDao;
import util.CommonUtil;

/**
 * Servlet implementation class kari
 */
@WebServlet("/Group")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * javadocClass Group<br>
	 * HTTP GETリクエストを処理し、グループ情報を表示します。
	 * @author otubo
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレット例外が発生した場合
	 * @throws IOException 入出力例外が発生した場合
	 * @version 1.0.0
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.checkLogin(request, response)) {
			return;
		}
		HttpSession session = request.getSession();	
		int roomId = Integer.parseInt(request.getParameter("groupId"));
		int userId = (int)session.getAttribute("userId");
		GroupDao gd = new GroupDao();
		request.setAttribute("result", gd.yesmemSelect(roomId));
		request.setAttribute("roomId", roomId);
		request.setAttribute("admin", gd.adminCheck(userId,roomId));		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/group/group.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * javadocClass Group<br>
	 * HTTP POSTリクエストを処理し、グループに関連する操作画面を表示します。
	 * @author otubo
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレット例外が発生した場合
	 * @throws IOException 入出力例外が発生した場合
	 * @version 1.0.0
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDao gd = new GroupDao();
		String value = request.getParameter("button");
		int roomId = Integer.parseInt(request.getParameter("groupId"));
		request.setAttribute("roomId", roomId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/group/group.jsp");
		if (value.equals("admin")) {
			request.setAttribute("result", gd.yesmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addAdmin.jsp");
		} else if (value.equals("addmember")) {
			request.setAttribute("result", gd.notmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/addMember.jsp");
		} else if (value.equals("delmember")) {
			request.setAttribute("result", gd.yesmemSelect(roomId));
			dispatcher = request.getRequestDispatcher("WEB-INF/group/r.jsp");			
		}
		dispatcher.forward(request, response);
	}

}
