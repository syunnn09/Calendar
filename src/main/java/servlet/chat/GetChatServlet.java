package servlet.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.ChatInfoBean;
import dao.ChatDao;

/**
 * Servlet implementation class GetChatServlet
 */
@WebServlet("/api/getChat")
public class GetChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatDao chatDao = new ChatDao();

	/**
	 * HTTP POST メソッドを処理し、チャット情報を取得して JSON 形式で応答します。
	 * 
	 * @method doPost
	 * @param request HTTPリクエスト
	 * @param response HTTPレスポンス
	 * @throws ServletException Servlet例外が発生した場合
	 * @throws IOException 入出力例外が発生した場合
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		ChatInfoBean chat = chatDao.getChat(roomId, page, true);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(chat);
		response.getWriter().println(json);
	}

}
