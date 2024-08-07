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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * HTTP GETリクエストを処理し、ログインページにフォワードします。
	 * もし既にログインしている場合はトップページにリダイレクトします。
	 *
	 * @method doGet
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレットで例外が発生した場合
	 * @throws IOException 入出力に関する例外が発生した場合
	 * @version 1.0.0
	 * @author rerere
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (CommonUtil.isLogined(request)) {
			response.sendRedirect("top");
			return;
		}

    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    	dispatcher.forward(request, response);
	}
    
    /**
     * HTTP POSTリクエストを処理し、ユーザーのログインを試みます。
     * 入力されたメールアドレスとパスワードを使用してログインを試み、ログインに成功した場合はセッションにユーザーIDを保存します。
     * ログインに成功した場合、ログイン状態によって遷移先のページが異なります。
     * ログインに失敗した場合、ログイン画面に戻り、エラーメッセージを表示します。
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
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AccountDao ad = new AccountDao();

		int isLogined = ad.getIsLogined(email);
		if (isLogined == -1) {
			request.setAttribute("email", email);
			this.doGet(request, response);
			return;
		} else if (isLogined != 0) {
			password = CommonUtil.hash(password);
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
			response.sendRedirect("top");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}