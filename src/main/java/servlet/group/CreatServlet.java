package servlet.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupBean;
import dao.GroupDao;

/**
 * Servlet implementation class CreatServlet
 */
@WebServlet("/CreatServlet")
public class CreatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//設定した画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//文字コードの指定
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 値を保存する変数の宣言
		String roomName = request.getParameter("roomname");
		String color = request.getParameter("color");
		if (roomName == null || roomName == "") {
			doGet(request, response);
			return;
		}

		int userId = (int) session.getAttribute("userId");
		// beanのコンストラクタを使って、値を格納する。
		
		GroupBean bean = new GroupBean();
		bean.setUserId(userId);
		bean.setRoomname(roomName);
		bean.setColor(color);
		// Daoを使う準備
		GroupDao Dao = new GroupDao();
		// DaoのUpdateメソッドを使用(引数：bean)
		int roomId = Dao.insert(bean);
		
		bean.setRoomId(roomId);

		Dao.admin(bean);
		// 【テスト用】beanをスコープに保存
		request.setAttribute("bean", bean);
		// 【テスト用】beanにちゃんと格納できたか確認するページに飛ぶ。
		//RequestDispatcher dispatcher = request.getRequestDispatcher("CreatServlet.java");
		//dispatcher.forward(request, response);
		response.sendRedirect("top");
	}

}
