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
import dao.PriorityDao;
import dao.TaskDao;
import domain.Priority;
import domain.Task;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			//			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<Task> taskList = taskDao.findAll();
			//			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();

			request.setAttribute("priorityList", priorityList);

			String title = request.getParameter("title");
			String detail = request.getParameter("detail");

			Integer taskTypeId = 1;
			Integer priorityId = Integer.parseInt(request.getParameter("priorityId"));
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

			if (priorityId < 1 || priorityList.size() < priorityId) {
				isError = true;
				request.setAttribute("priorityError", "※優先度が無効です");
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
				request.setAttribute("priorityId", priorityId);
				request.setAttribute("timeLimit", timeLimitStr);
				request.setAttribute("now", nowSdf);
				request.setAttribute("priorityList", priorityList);
				request.getRequestDispatcher("/main")
						.forward(request, response);
				return;
			}

			//DBへデータを追加する
			Task task = new Task();
			task.setTitle(title);
			task.setDetail(detail);
			task.setAddingTime(timeLimit);
			task.setTimeLimit(timeLimit);
			task.setUserId(1);
			task.setTaskTypeId(taskTypeId);
			task.setPriorityId(priorityId);
			taskDao.insert(task);

			request.getRequestDispatcher("/main")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
