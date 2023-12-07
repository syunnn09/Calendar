package servlet.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.ScheduleJsonBean;
import bean.ScheduleRecordBean;
import dao.ScheduleDao;

/**
 * Servlet implementation class GetSchedule
 */
// Ex) http://localhost:8080/Calendar/api/schedule?scheduleId=2
@WebServlet("/api/schedule")
public class GetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // パラメータからスケジュールIDを取得
	    int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

	    // スケジュールDAOのインスタンスを作成
	    ScheduleDao dao = new ScheduleDao();
	    // JSONの変換を行うためのObjectMapperのインスタンスを作成
	    ObjectMapper mapper = new ObjectMapper();

	    // スケジュールIDを使ってデータベースからスケジュール情報を取得
	    ScheduleRecordBean recordBean = dao.getSchedule(scheduleId);
	    // 取得した情報をJSON形式に変換するためのビーンクラスのインスタンスを作成
	    ScheduleJsonBean bean = new ScheduleJsonBean(recordBean);

	    try {
	        // JSON形式に変換してレスポンスに書き込み
	        String json = mapper.writeValueAsString(bean);
	        response.getWriter().write(json);
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	}

}
