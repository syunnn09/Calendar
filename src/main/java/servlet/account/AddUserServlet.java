package servlet.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
@MultipartConfig(maxFileSize=1048576)
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/addUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Part file = request.getPart("file");
		InputStream in = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String text = "ユーザー登録が完了しました。";

		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			String[] arr = line.split(",");
			i += 1;
			System.out.println(Arrays.toString(arr));
			if (arr.length != 3) {
				text = i + "行目に問題があります。";
			}
		}

		request.setAttribute("text", text);

		doGet(request, response);
	}

}
