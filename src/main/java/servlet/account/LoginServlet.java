package servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import model.UserModel;
import util.CommonUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    	dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		CommonUtil util = new CommonUtil();
		AccountDao ad = new AccountDao();

		int isLogined = ad.getIsLogined(email);
		if (isLogined == -1) {
			request.setAttribute("email", email);
			this.doGet(request, response);
			return;
		}

		if (isLogined != 0) {
			password = util.hash(password);
		}

		UserModel user = ad.login(email, password);
		if (user == null) {
			request.setAttribute("text", email);
			this.doGet(request, response);
			return;
		}

		session.setAttribute("userId", user.getUserId());
		System.out.println("userId: " + session.getAttribute("userId"));

		String forwardPath = "";

		if (!user.isLogined()) {
			forwardPath = "WEB-INF/account/change.jsp";
		} else {
			forwardPath = "WEB-INF/account/addUser.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}