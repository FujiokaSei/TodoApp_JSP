package controller;

import java.io.IOException;
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

			request.setAttribute("taskList", taskList);
			request.setAttribute("taskTypeList", taskTypeList);

			request.getRequestDispatcher("WEB-INF/view/addTask.jsp")
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

		//		try(){
		//
		//		}catch() {
		//
		//		}

		String title = request.getParameter("title");
		System.out.println("title:" + title);
		String detail = request.getParameter("detail");
		System.out.println("detail:" + detail);
		Integer taskTypeId = Integer.parseInt(request.getParameter("taskTypeId"));
		System.out.println("taskType:" + taskTypeId);
		String timeLimitStr = request.getParameter("timeLimit");
		System.out.println("timeLimit:" + timeLimitStr);




		TaskDao taskDao = DaoFactory.createTaskDao();

	}

}
