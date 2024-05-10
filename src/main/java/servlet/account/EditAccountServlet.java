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
	 * HTTP POSTリクエストを処理し、ユーザーのアカウント情報を更新します。
	 * リクエストからパスワードとユーザー名を取得し、それらを使用してアカウント情報を更新します。
	 *
	 * @method doPost
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレットで例外が発生した場合
	 * @throws IOException 入出力に関する例外が発生した場合
	 * @version 1.0.0
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author rerere
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