package servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * HTTP GETリクエストを処理し、ログアウトします。
	 * セッションからユーザーIDを削除し、ログインページにリダイレクトします。
	 *
	 * @method doGet
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレットで例外が発生した場合
	 * @throws IOException 入出力に関する例外が発生した場合
	 * @version 1.0.0
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author rerere
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//格納されている値の破棄
		session.removeAttribute("userId");
		response.sendRedirect("login");
	}
}