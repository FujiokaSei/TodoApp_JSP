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

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id:" + id);

			TaskDao taskDao = DaoFactory.createTaskDao();
			Task editTask = taskDao.findById(id);
			String mode = "editMode";

			Date timeLimit = editTask.getTimeLimit();

			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String timeLimitSdf = sdf.format(timeLimit);
			System.out.println("タイムリミット" + timeLimitSdf);

			request.setAttribute("timeLimit", timeLimitSdf);
			request.setAttribute("mode", mode);
			request.setAttribute("editTask", editTask);
			request.getRequestDispatcher("/main")
					.forward(request, response);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			//宣言部
			TaskDao taskDao = DaoFactory.createTaskDao();
			//			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<Task> taskList = taskDao.findAll();
			//			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();

			request.setAttribute("priorityList", priorityList);
			Integer taskId = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String detail = request.getParameter("detail");

			Integer taskTypeId = 1;
			Integer priorityId = Integer.parseInt(request.getParameter("priorityId"));
			String timeLimitStr = request.getParameter("timeLimit");

			//DBへの書き込み用フォーマット
			SimpleDateFormat sdfDB = new SimpleDateFormat("y-MM-dd HH:mm");
			Date timeLimit = null;

			//TODO:↓このnowSdfは必要？？mainServletで同じことしている。
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
			task.setId(taskId);//idを格納する。
			task.setTitle(title);
			task.setDetail(detail);
			//task.setAddingTime(timeLimit);
			task.setTimeLimit(timeLimit);
			//task.setUserId(1);
			//task.setTaskTypeId(taskTypeId);
			task.setPriorityId(priorityId);
			taskDao.update(task);

			System.out.println("title:" + title);
			System.out.println("detail:" + detail);
			System.out.println("taskType:" + taskTypeId);
			System.out.println("priorityId:" + priorityId);
			System.out.println("timeLimit:" + timeLimitStr);
			System.out.println("nowSdf:" + nowSdf);

			System.out.println("updateしました");

			/*			request.getRequestDispatcher("/main")
								.forward(request, response);*/
			response.sendRedirect("main");

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
