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
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ①データを受け取る
		 *
		 * ②データをセットする
		 *
		 * ③MainServletにフォワードする。
		 *
		 * */

		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			List<Task> taskList = taskDao.findAll();
			List<TaskType> taskTypeList = taskTypeDao.findAll();

			request.setAttribute("taskTypeList", taskTypeList);

			String title = request.getParameter("title");
			String detail = request.getParameter("detail");
			Integer taskTypeId = Integer.parseInt(request.getParameter("taskTypeId"));
			String timeLimitStr = request.getParameter("timeLimit");

			//DBへの書き込み用フォーマット
			SimpleDateFormat sdfDB = new SimpleDateFormat("y-MM-dd HH:mm");
			Date timeLimit = null;

			//JSPにフォワードするためのsdfフォーマット
			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String nowSdf = sdf.format(now);

			//バリデーション
			boolean isError = false;
			if (title.isEmpty()) {
				isError = true;
				request.setAttribute("titleError", "※タスク名が未入力です。");
			}

			if (title.length() > 50) {
				isError = true;
				request.setAttribute("titleError", "※50文字以下で入力してください。");
			}

			if (detail.length() > 100) {
				isError = true;
				request.setAttribute("detailError", "※100文字以下で入力してください。");
			}

			if (taskTypeId < 1 || taskTypeList.size() < taskTypeId) {
				isError = true;
				request.setAttribute("taskTypeError", "※タスク種別が無効です");
			}

			if (timeLimitStr == "") {
				isError = true;
				request.setAttribute("timeLimitError", "※期限を設定してください");
			} else {
				timeLimitStr = timeLimitStr.replace("T", " ");
				timeLimit = sdfDB.parse(timeLimitStr);
			}

			if (isError) {
				request.setAttribute("title", title);
				request.setAttribute("detail", detail);
				request.setAttribute("taskTypeId", taskTypeId);
				/*				request.setAttribute("timeLimit", timeLimitStr);*/
				request.setAttribute("timeLimit", timeLimitStr);
				request.setAttribute("now", nowSdf);
				request.setAttribute("taskTypeList", taskTypeList);

				request.getRequestDispatcher("/main")
						.forward(request, response);
				return;
			}

			//DBへデータを追加する
			Task task = new Task();
			//task.setId(taskTypeId);
			task.setTitle(title);
			task.setDetail(detail);
			task.setAddingTime(timeLimit);
			task.setTimeLimit(timeLimit);
			task.setUserId(1);
			task.setTaskTypeId(taskTypeId);
			taskDao.insert(task);

			System.out.println("title:" + title);
			System.out.println("detail:" + detail);
			System.out.println("taskType:" + taskTypeId);
			System.out.println("timeLimit:" + timeLimitStr);
			System.out.println("nowSdf:" + nowSdf);

			System.out.println("insertしました");

			request.getRequestDispatcher("/main")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
