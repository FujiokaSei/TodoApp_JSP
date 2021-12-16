package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.TaskDao;
import dao.TaskTypeDao;
import domain.Task;
import domain.TaskType;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			List<Task> taskList = taskDao.findAll();
			List<TaskType> taskTypeList = taskTypeDao.findAll();

			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String nowSdf = sdf.format(now);

			request.setAttribute("now", nowSdf);
			request.setAttribute("taskList", taskList);
			request.setAttribute("taskTypeList", taskTypeList);

			request.getRequestDispatcher("WEB-INF/view/main.jsp")
					.forward(request, response);
		}

		catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * TODO:①データを受け取る
		 *
		 * TODO:②データをセットする
		 *
		 * TODO:③main.jspにフォワードする
		 * */

		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			List<Task> taskList = taskDao.findAll();
			List<TaskType> taskTypeList = taskTypeDao.findAll();

			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String nowSdf = sdf.format(now);

			request.setAttribute("now", nowSdf);
			request.setAttribute("taskList", taskList);
			request.setAttribute("taskTypeList", taskTypeList);

			request.getRequestDispatcher("WEB-INF/view/main.jsp")
					.forward(request, response);


			//
			//			request.setAttribute("taskTypeList", taskTypeList);
			//
			//			String title = request.getParameter("title");
			//			String detail = request.getParameter("detail");
			//			Integer taskTypeId = Integer.parseInt(request.getParameter("taskTypeId"));
			//			String timeLimitStr = request.getParameter("timeLimit");
			//
			//			//バリデーション
			//			boolean isError = false;
			//			if (title.isEmpty()) {
			//				isError = true;
			//				request.setAttribute("titleError", "※タスク名が未入力です。");
			//			}
			//
			//			if (title.length() > 50) {
			//				isError = true;
			//				request.setAttribute("titleError", "※50文字以下で入力してください。");
			//			}
			//
			//			if (detail.length() > 3000) {
			//				isError = true;
			//				request.setAttribute("detailError", "※3000文字以下で入力してください。");
			//			}
			//
			//			//taskTypeId=10;
			//			if (taskTypeId < 1 || taskTypeList.size() < taskTypeId) {
			//				isError = true;
			//				request.setAttribute("taskTypeError", "※タスク種別が無効です");
			//			}
			//
			//			//timeLimitStrのNullチェック
			//			Date timeLimit = null;
			//
			//			if (timeLimitStr == "") {
			//				isError = true;
			//				request.setAttribute("timeLimitError", "※期限を設定してください");
			//			}
			//
			//			request.setAttribute("title", title);
			//			request.setAttribute("detail", detail);
			//			request.setAttribute("taskTypeId", taskTypeId);
			//			request.setAttribute("timeLimit", timeLimitStr);
			//			request.setAttribute("now", nowSdf);
			//			request.setAttribute("taskTypeList", taskTypeList);
			//
			//			/*暫定対応
			//			 * 期限入力に初期値を設定することで下記のParseException例外対策を不要とする。
			//			 */
			//			//			try {
			//			//				timeLimit = sdf.parse(timeLimitStr);
			//			//			} catch (ParseException e) {
			//			//				request.getRequestDispatcher("WEB-INF/view/addTask.jsp")
			//			//						.forward(request, response);
			//			//			}
			//
			//			if (isError) {
			//				request.getRequestDispatcher("WEB-INF/view/main.jsp")
			//						.forward(request, response);
			//				return;
			//			}
			//
			//			//DBへデータを追加する
			//			Task task = new Task();
			//			//task.setId(taskTypeId);
			//			task.setTitle(title);
			//			task.setDetail(detail);
			//			task.setAddingTime(timeLimit);
			//			task.setTimeLimit(timeLimit);
			//			task.setUserId(1);
			//			task.setTaskTypeId(taskTypeId);
			//			taskDao.insert(task);
			//
			//			System.out.println("title:" + title);
			//			System.out.println("detail:" + detail);
			//			System.out.println("taskType:" + taskTypeId);
			//			System.out.println("timeLimit:" + timeLimit);
			//			System.out.println("nowSdf:" + nowSdf);
			//
			//			System.out.println("insertしました");

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
