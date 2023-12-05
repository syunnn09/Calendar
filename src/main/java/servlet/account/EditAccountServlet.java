package servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AccountBean;
import dao.AccountDao;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId"); 
		AccountDao accountDao = new AccountDao();
		String name = accountDao.getName(userId);
		
		request.setAttribute("userName",name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/editAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String password = request.getParameter("password");
		String name = request.getParameter("userName");
		int userId = (int) session.getAttribute("userId");
		
		AccountBean bean = new AccountBean();
		bean.setUserName(name);
		bean.setPassword(password);
		bean.setUserId(userId);
		
		AccountDao accountDao = new AccountDao();
		accountDao.update(bean);
	}
}
