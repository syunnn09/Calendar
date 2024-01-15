package servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import util.CommonUtil;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/ChangeServlet")
@MultipartConfig(maxFileSize=1048576)
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/change.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CommonUtil util = new CommonUtil();

		int userId = (int) session.getAttribute("userId");
		String password = request.getParameter("newPassword");
		password = util.hash(password);

		AccountDao ad = new AccountDao();
		boolean isSuccess = ad.changePassword(userId, password);

		if (!isSuccess) {
			request.setAttribute("text", "パスワード変更に失敗しました。");
			doGet(request, response);
			return;
		}
		response.sendRedirect("TopServlet");
	}
}