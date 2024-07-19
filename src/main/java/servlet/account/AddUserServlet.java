package servlet.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.AccountBean;
import bean.AccountInfoBean;
import dao.AccountDao;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
@MultipartConfig(maxFileSize=1048576)
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * HTTP GETリクエストを処理し、ユーザー登録用のJSPページにフォワードします。
	 *
	 * @method doGet
	 * @param request HTTPリクエストオブジェクト
	 * @param response HTTPレスポンスオブジェクト
	 * @throws ServletException サーブレットで例外が発生した場合
	 * @throws IOException 入出力に関する例外が発生した場合
	 * @version 1.0.0
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author rerere
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/addUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * HTTP POSTリクエストを処理し、ユーザー情報をデータベースに追加します。
	 * また、ユーザー情報がCSVファイルで提供され、ファイル内の各行が1つのユーザー情報を表すことを想定しています。
	 * ユーザー情報が正常に追加された場合、ユーザー登録完了メッセージを表示します。追加中に問題が発生した場合、その行番号を含むエラーメッセージを表示します。
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
		request.setCharacterEncoding("utf-8");
		Part file = request.getPart("file");
		InputStream in = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String text = "ユーザー登録が完了しました。";

		String line;
		int i = 0;

		AccountInfoBean beans = new AccountInfoBean();

		while ((line = br.readLine()) != null) {
			String[] arr = line.split(",");
			AccountBean bean = new AccountBean(arr);
			beans.addUserRecord(bean);
			i += 1;
			if (arr.length != 4) {
				text = i + "行目に問題があります。";
				request.setAttribute("text", text);
				doGet(request, response);
				return;
			}
		}

		AccountDao accountDao = new AccountDao();
		accountDao.addUser(beans);

		request.setAttribute("text", text);
		doGet(request, response);
	}

}
